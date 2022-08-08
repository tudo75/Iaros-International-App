package com.iarosinternational.corsoandroid.app;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonTask extends AsyncTask<String, Void, String> {

    public interface AsyncResponse {
        void processFinish(String output);
    }
    public AsyncResponse delegate;

    public JsonTask(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(@NonNull String... urls) {

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(urls[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();


            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuilder buffer = new StringBuilder();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
                Log.d("Response: ", "> " + line);
            }

            return buffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}
