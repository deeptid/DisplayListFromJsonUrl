package com.deepti.saltsideassignment;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by deepti on 06/04/17.
 */
public class DownloadJson extends AsyncTask<String, JsonArray, JsonArray> {
    private static String TAG = DownloadJson.class.getSimpleName();
    @Override
    protected JsonArray doInBackground(String... urls) {
        URL url = null;
        JsonArray rootobj = null;
        try {
            url = new URL(urls[0]);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            rootobj = root.getAsJsonArray();
            publishProgress(rootobj);
            Log.d(TAG,"rootobj :" + rootobj.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootobj;
    }
}
