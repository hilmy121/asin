package com.raivstudio.katalogfilm;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity /*implements LoaderManager.LoaderCallbacks<ArrayList<DataMovies>>*/ {
    EditText inputjdl;
    ImageView gbposter;
    Button btnSearch;
    /*ListView listView;
    AdapterFilm adapter;*/
    ArrayList<DataMovies> data;
    Async async;
    //Context context;
    //AdapterView.OnClickListener listener;
    String judul_film ="";
    RecyclerView Recycler;
    RvAdapter rvAdapter;
    private RecyclerView.Adapter rv;
    private RecyclerView.LayoutManager layout;
    //private RvAdapter.OnItemClickListener listener;
    static final String EXTRAS_FILM = "EXTRAS_FILM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Recycler = (RecyclerView) findViewById(R.id.rvFilm);
        inputjdl  = findViewById(R.id.edt_movie_title);
        btnSearch    = findViewById(R.id.btn_search);
        gbposter   = findViewById(R.id.iv_movie);
        btnSearch.setOnClickListener(movieListener);

        Recycler.setHasFixedSize(false);
        layout = new LinearLayoutManager(this);
        Recycler.setLayoutManager(layout);

        rvAdapter = new RvAdapter(data,this);
        rvAdapter.notifyDataSetChanged();
        Recycler.setAdapter(rvAdapter);
        Recycler.setClickable(true);








        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_FILM, judul_film);

        /*getLoaderManager().initLoader(0, bundle,this);*/
    }

    View.OnClickListener movieListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            judul_film = inputjdl.getText().toString();

            if(TextUtils.isEmpty(judul_film)){
                Toast.makeText(MainActivity.this, "Isikan judul film lebih dulu !",
                        Toast.LENGTH_SHORT).show();
            }else {
                async = new Async(data,"https://api.themoviedb.org/3/search/movie?api_key=31c0a73972ca149daf5656b3830752e8&language=en-US&query="
                        +judul_film);
                data = async.ReturnArrays();
            }


            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_FILM, judul_film);
            /*getLoaderManager().restartLoader(0, bundle, MainActivity.this);*/
        }
    };
}
