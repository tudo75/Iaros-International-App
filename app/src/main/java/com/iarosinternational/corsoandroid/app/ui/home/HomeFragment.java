package com.iarosinternational.corsoandroid.app.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iarosinternational.corsoandroid.app.JsonTask;
import com.iarosinternational.corsoandroid.app.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements JsonTask.AsyncResponse {

    private FragmentHomeBinding binding;
    private ArrayList<News> newsList;
    private final static String JSON_URL_NEWS = "https://raw.githubusercontent.com/tudo75/Iaros-International-App/main/resources/news_iaros.json";
    private RecyclerView contenitore_news;
    private NewsAdapter news_adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        newsList = new ArrayList<News>();

        new JsonTask(this).execute(JSON_URL_NEWS);

        /*
         1) recupero recyclerview inizializzo
         2) Passareal recyclerview la lista popolo news
         */
        contenitore_news = binding.contenitoreNews;
        news_adapter = new NewsAdapter(newsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        contenitore_news.setLayoutManager(layoutManager);
        contenitore_news.setItemAnimator(new DefaultItemAnimator());
        //contenitore_news.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        contenitore_news.setAdapter(news_adapter);

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

            //Ciclo l'array json news
            for(int i=0; i<news.length(); i++) {
                //per ogni elemento dell'array inizializzo un oggetto News e setto tutti i suoi attributi
                News tempNews = new News();
                JSONObject news_json = (JSONObject) news.get(i);
                String titolo_news = news_json.getString("titolo");
                tempNews.setTitolo(titolo_news);
                String corpo_mnews = news_json.getString("corpo");
                tempNews.setCorpo(corpo_mnews);
                String data_news = news_json.getString("data");
                tempNews.setData(data_news);

                //alla fine dell'inizializzazione inserisco l'oggetto News in un ArrayList
                newsList.add(tempNews);
            }

            news_adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            Log.e("JSON error", e.getMessage());
        }
    }
}
