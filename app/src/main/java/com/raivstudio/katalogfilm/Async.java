package com.raivstudio.katalogfilm;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class Async extends AsyncTask<String,Void,String> {
    ArrayList<DataMovies> data = new ArrayList<>();
    RvAdapter adapter ;
    JSONObject jsonPart;
    Context context;
    public Async (ArrayList<DataMovies> data,String urls){
        this.data=data;
        execute(urls);

    }
    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        URL url;
        HttpURLConnection urlConnection= null;
        try{
            url= new URL(urls[0]);
            urlConnection= (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();
            while (data!=-1){
                char current = (char) data;
                result += current;
                data = reader.read();
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONObject jsonObject = new JSONObject(result);
            Iterator<String> info = jsonObject.keys();
            JSONArray arr;


            //String info = jsonObject.getString("production_companies");
            while (info.hasNext()) {
                String key = info.next();
                try {
                    arr = new JSONArray(key);
                    for (int i=0; i<arr.length(); i++){
                        jsonPart = arr.getJSONObject(i);
                        data.add(new DataMovies(jsonPart));
                    }
                    adapter = new RvAdapter(data,context);
                    adapter.setData(data);
                } catch (JSONException e) {
                    // Something went wrong!
                }
            };







        }catch (Exception e){

            }

        }
        public ArrayList<DataMovies> ReturnArrays(){
            return data;
        }
    }


