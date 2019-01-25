package com.raivstudio.katalogfilm;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class AsynctaskLoaderFilm extends AsyncTaskLoader <ArrayList<DataMovies>> {

    private ArrayList<DataMovies> Data;
    private boolean adaResult = false;

    private String Judulfilm;

    public AsynctaskLoaderFilm(final Context context, String _judulfilm) {
        super(context);
        onContentChanged();
        this.Judulfilm = _judulfilm;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (adaResult)
            deliverResult(Data);
    }

    public void deliverResult(ArrayList<DataMovies> data) {
        Data = data;
        adaResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (adaResult) {
            onReleaseResources(Data);
            Data = null;
            adaResult = false;
        }
    }

    private void onReleaseResources(ArrayList<DataMovies> data) {
    }

    @Override
    public ArrayList<DataMovies> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<DataMovies> dataMovies = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key=31c0a73972ca149daf5656b3830752e8&language=en-US&query="
                +Judulfilm;

        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String hasil = new String(responseBody);
                    JSONObject responseObject = new JSONObject(hasil);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0 ; i < list.length() ; i++){
                        JSONObject film = list.getJSONObject(i);
                        DataMovies DataMovies = new DataMovies(film);
                        dataMovies.add(DataMovies);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return dataMovies;
    }
}
