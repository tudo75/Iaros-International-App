package com.iarosinternational.corsoandroid.app.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.iarosinternational.corsoandroid.app.JsonTask;
import com.iarosinternational.corsoandroid.app.R;
import com.iarosinternational.corsoandroid.app.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;

public class HomeFragment extends Fragment implements JsonTask.AsyncResponse {

    private FragmentHomeBinding binding;

    private final static String JSON_URL_NEWS = "https://raw.githubusercontent.com/tudo75/Iaros-International-App/main/resources/news_iaros.json";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        new JsonTask(this).execute(JSON_URL_NEWS);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * Metodo che restituisce il contenuto del file rihiessto attraverso JSONTask sotto forma di
     * stringa
     *
     * @param result contenuto del file sotto forma di stringa di testo
     */
    @Override
    public void processFinish(String result) {
        try {
            JSONArray news = new JSONArray(result);
        } catch (JSONException e) {
            Log.e("JSON error", e.getMessage());
        }
    }
}