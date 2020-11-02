package com.example.vitrinovademo.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.bumptech.glide.Glide;
import com.example.vitrinovademo.adapter.ChooseEditorAdapter;
import com.example.vitrinovademo.adapter.CategoryAdapter;
import com.example.vitrinovademo.adapter.CollectionAdapter;
import com.example.vitrinovademo.adapter.NewVitrinAdapter;
import com.example.vitrinovademo.adapter.ProductAdapter;
import com.example.vitrinovademo.adapter.SlidingAdapter;
import com.example.vitrinovademo.databinding.ActivityMainBinding;
import com.example.vitrinovademo.model.Category;
import com.example.vitrinovademo.model.Shop;
import com.example.vitrinovademo.model.Shops;
import com.example.vitrinovademo.model.Collection;
import com.example.vitrinovademo.model.Featured;
import com.example.vitrinovademo.model.Product;
import com.example.vitrinovademo.model.ResponseModel;
import com.example.vitrinovademo.model.Vitrin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    List<Product> products = new ArrayList<>();
    List<Category> categories = new ArrayList<>();
    List<Collection> collections = new ArrayList<>();
    List<Shops> shops = new ArrayList<>();
    List<Shops> newVitrin = new ArrayList<>();

    ResponseModel responseVitrinova;
    private List<ResponseModel> userList = null;
    private SlidingAdapter slidingAdapter;
    private ProductAdapter productAdapter;
    private CategoryAdapter categoryAdapter;
    private CollectionAdapter collectionAdapter;
    private ChooseEditorAdapter chooseEditorAdapter;
    private NewVitrinAdapter newVitrinAdapter;

    public ActivityMainBinding getActivityMainBinding() {
        return activityMainBinding;
    }

    public static final String BASE_URL = "https://www.vitrinova.com/api/discover";

    public static final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        getVitrinovaList();
        speechToText();
        activityMainBinding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getVitrinovaList();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @SuppressLint("ClickableViewAccessibility")
    private void speechToText() {

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                activityMainBinding.etSearch.setText(data.get(0));
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

       activityMainBinding.btmicrofon.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent motionEvent) {
               if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                   speechRecognizer.stopListening();
               }
               if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                   speechRecognizer.startListening(speechRecognizerIntent);
               }
               return false;
           }
       });

    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestCode && grantResults.length > 0 ){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"Permission Granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }

    private void setSlidingAdapter(ArrayList<Featured> featureds) {
        slidingAdapter = new SlidingAdapter(featureds);
        activityMainBinding.viewPager.setAdapter(slidingAdapter);
        activityMainBinding.tabDots.setupWithViewPager(activityMainBinding.viewPager);
        final float MIN_SCALE = 0.75f;
        activityMainBinding.viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View view, float position) {
                int pageWidth = view.getWidth();

                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    view.setAlpha(0f);

                } else if (position <= 0) { // [-1,0]
                    // Use the default slide transition when moving to the left page
                    view.setAlpha(1f);
                    view.setTranslationX(0f);
                    view.setScaleX(1f);
                    view.setScaleY(1f);

                } else if (position <= 1) { // (0,1]
                    // Fade the page out.
                    view.setAlpha(1 - position);

                    // Counteract the default slide transition
                    view.setTranslationX(pageWidth * -position);

                    // Scale the page down (between MIN_SCALE and 1)
                    float scaleFactor = MIN_SCALE
                            + (1 - MIN_SCALE) * (1 - Math.abs(position));
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);

                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    view.setAlpha(0f);
                }

            }
        });

    }

    private void setProductAdapter(ArrayList<Product> products) {
        productAdapter = new ProductAdapter(products);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        activityMainBinding.rvProducts.setLayoutManager(layoutManager);
        activityMainBinding.rvProducts.setAdapter(productAdapter);
        activityMainBinding.rvProducts.setHasFixedSize(true);
        productAdapter.notifyDataSetChanged();
    }

    private void setCategoryAdapter(ArrayList<Category> categories) {
        categoryAdapter = new CategoryAdapter(categories);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        activityMainBinding.rvCategories.setLayoutManager(layoutManager);
        activityMainBinding.rvCategories.setAdapter(categoryAdapter);
        activityMainBinding.rvCategories.setHasFixedSize(true);


    }

    private void setCollectionAdapter(ArrayList<Collection> collections){
        collectionAdapter = new CollectionAdapter(collections);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        activityMainBinding.rvCollection.setLayoutManager(layoutManager);
        activityMainBinding.rvCollection.setAdapter(collectionAdapter);
        activityMainBinding.rvCollection.setHasFixedSize(true);
        collectionAdapter.notifyDataSetChanged();
    }

    private void setChooseEditorAdapter(final ArrayList<Shops> shops){
        chooseEditorAdapter = new ChooseEditorAdapter(shops, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        activityMainBinding.rvChooseEditor.setLayoutManager(layoutManager);
        activityMainBinding.rvChooseEditor.setAdapter(chooseEditorAdapter);
        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(activityMainBinding.rvChooseEditor);
        activityMainBinding.rvChooseEditor.setOnFlingListener(null);
        activityMainBinding.rvChooseEditor.setHasFixedSize(true);
        Glide.with(getApplicationContext()).load(shops.get(0)
                .getCover().getUrl()).into(activityMainBinding.viewChooseEditor);

        activityMainBinding.rvChooseEditor.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE) {
                    View centerView = snapHelper.findSnapView(layoutManager);
                    int pos = layoutManager.getPosition(centerView);
                    Glide.with(getApplicationContext()).load(shops.get(pos)
                            .getCover().getUrl()).into(activityMainBinding.viewChooseEditor);
                }
            }
        });
    }

   private void setNewVitrinAdapter(ArrayList<Shops> newVitrin){
        newVitrinAdapter = new NewVitrinAdapter(newVitrin,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        activityMainBinding.rvNewVitrin.setLayoutManager(layoutManager);
        activityMainBinding.rvNewVitrin.setAdapter(newVitrinAdapter);
        newVitrinAdapter.notifyDataSetChanged();
    }

    public void onAllNewProduct(View view) {
        Intent intent = new Intent(this, AllProductActivity.class);
        intent.putParcelableArrayListExtra("all", (ArrayList<Product>) products);
        startActivity(intent);
    }

    public void onAllCollection(View view){
        Intent intent = new Intent(this, CollectionActivity.class);
        intent.putParcelableArrayListExtra("collection", (ArrayList<Collection>) collections);
        startActivity(intent);

    }

    public void onAllChooseEditor(View view){
        Intent intent = new Intent(this, ChooseEditorActivity.class);
        intent.putParcelableArrayListExtra("chooseEditor", (ArrayList<Shops>) shops);
        startActivity(intent);
    }

    public void onAllNewVitrin(View view){
        Intent intent = new Intent(this, NewVitrinActivity.class);
        intent.putParcelableArrayListExtra("newVitrin", (ArrayList<Shops>) newVitrin);
        startActivity(intent);

    }


    private void getVitrinovaList() {
        final Gson gson;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.get(BASE_URL)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {

                        List<ResponseModel> vitrinovaList = Arrays.asList(gson.fromJson(String.valueOf(response), ResponseModel[].class));
                        setSlidingAdapter((ArrayList<Featured>) vitrinovaList.get(0).getFeatureds());

                        activityMainBinding.tvEnYeni.setText(vitrinovaList.get(1).getTitle().toUpperCase());
                        products = vitrinovaList.get(1).getProducts();
                        setProductAdapter((ArrayList<Product>) products);
                        Log.i("sorun ", vitrinovaList.get(1).getProducts().toString() );
                        for (int i=0; i<vitrinovaList.get(1).getProducts().size(); i++){
                            Log.i("sorun2 ", vitrinovaList.get(1).getProducts().get(i).getTitle());
                        }

                        activityMainBinding.tvCategoryTitle.setText(vitrinovaList.get(2).getTitle().toUpperCase());
                        categories = vitrinovaList.get(2).getCategories();
                        setCategoryAdapter((ArrayList<Category>) categories);

                        activityMainBinding.tvCollectionTitle.setText(vitrinovaList.get(3).getTitle().toUpperCase());
                        collections = vitrinovaList.get(3).getCollections();
                        setCollectionAdapter((ArrayList<Collection>) collections);

                        activityMainBinding.tvChooseEditorTitle.setText(vitrinovaList.get(4).getTitle().toUpperCase());
                        shops =vitrinovaList.get(4).getShops();
                        setChooseEditorAdapter((ArrayList<Shops>) shops);
                        Log.i("resim", shops.get(0).getCover().getUrl());
                        Log.i("resim2", shops.get(1).getCover().getUrl());
                        Log.i("resim3", shops.get(2).getCover().getUrl());
                        Log.i("resim4", shops.get(3).getCover().getUrl());
                        Log.i("resim5", shops.get(4).getCover().getUrl());


                        activityMainBinding.tvNewVitrinTitle.setText(vitrinovaList.get(5).getTitle().toUpperCase());
                        newVitrin = vitrinovaList.get(5).getShops();
                        setNewVitrinAdapter((ArrayList<Shops>)newVitrin);

                        activityMainBinding.cardview.setVisibility(View.VISIBLE);
                        activityMainBinding.tvAll.setVisibility(View.VISIBLE);
                        activityMainBinding.tvAllCollection.setVisibility(View.VISIBLE);
                        activityMainBinding.tvAllChoose.setVisibility(View.VISIBLE);
                        activityMainBinding.tvAllNewVitrin.setVisibility(View.VISIBLE);
                        activityMainBinding.layoutCategory.setVisibility(View.VISIBLE);
                        activityMainBinding.viewChooseEditor.setVisibility(View.VISIBLE);

                        activityMainBinding.swipeRefresh.setRefreshing(false);
                        
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


    }
}