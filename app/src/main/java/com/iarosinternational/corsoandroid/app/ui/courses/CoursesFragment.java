package com.iarosinternational.corsoandroid.app.ui.courses;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.iarosinternational.corsoandroid.app.JsonTask;
import com.iarosinternational.corsoandroid.app.R;
import com.iarosinternational.corsoandroid.app.databinding.FragmentCoursesBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoursesFragment extends Fragment implements JsonTask.AsyncResponse, SwipeRefreshLayout.OnRefreshListener, MenuProvider {

    private ArrayList<Corso> corsoList;
    private RecyclerView recyclerView;
    private CorsoAdapter mAdapter;
    private ProgressBar spinner;

    private FragmentCoursesBinding binding;

    private final static String JSON_URL_CORSI = "https://raw.githubusercontent.com/tudo75/Iaros-International-App/main/resources/corsi_iaros.json";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCoursesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.getActivity().addMenuProvider(this, getViewLifecycleOwner());

        corsoList = new ArrayList<Corso>();

        //final TextView textView = binding.textNotifications;

        //textView.setText("avvio recupero lista");

        new JsonTask(this).execute(JSON_URL_CORSI);

        //textView.setText(R.string.title_courses);

        binding.coursesSwipeRefresh.setOnRefreshListener(CoursesFragment.this);

        recyclerView = binding.coursesRecyclerView;
        mAdapter = new CorsoAdapter(this, corsoList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(mAdapter);
        recyclerView.setVisibility(View.GONE);

        spinner = binding.coursesSpinner;

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
        // inizializzo la lista di corsi vuota

        try {
            JSONArray courses = new JSONArray(result);

            corsoList.clear();
            // ciclo il jsonarray e per ogni ciclo del for mi creo un oggetto corso

            for (int i= 0; i < courses.length(); i++) {
                Corso corso = new Corso();

                JSONObject corso_json = (JSONObject) courses.get(i);
                String titolo_corso = corso_json.getString("titolo");
                corso.setTitolo(titolo_corso);
                String data_corso = corso_json.getString("data_consegna_moduli");
                corso.setDataConsegnaModuli(data_corso);
                String scheda_corso = corso_json.getJSONObject("scheda_corso").getString("titolo_url");
                corso.setSchedaCorso(scheda_corso);
                String scheda_corso_url = corso_json.getJSONObject("scheda_corso").getString("url");
                corso.setSchedaCorsoUrl(scheda_corso_url);
                String scheda_bando = corso_json.getJSONObject("scheda_bando").getString("titolo_url");
                corso.setSchedaBando(scheda_bando);
                String scheda_bando_url = corso_json.getJSONObject("scheda_bando").getString("url");
                corso.setSchedaBandoUrl(scheda_bando_url);
                String scheda_iscrizione = corso_json.getJSONObject("scheda_iscrizione").getString("titolo_url");
                corso.setSchedaIscrizione(scheda_iscrizione);
                String scheda_iscrizione_url = corso_json.getJSONObject("scheda_iscrizione").getString("url");
                corso.setSchedaIscrizioneUrl(scheda_iscrizione_url);

                // alla fine del singolo ciclo prendo l'oggetto
                // corso appena creato e lo aggiungo alla lista
                corsoList.add(corso);
            }

            mAdapter.notifyDataSetChanged();
            binding.coursesSwipeRefresh.setRefreshing(false);
            // una volta uscito dal ciclo di lettura del jsonarray devo passare
            // la lista al recyclerview
            // -> lo faccio nel corpo del createView dopo la chiamata di questo metodo

            spinner.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            //binding.textNotifications.setText(result);
            //binding.textNotifications.setText("array json recuperato");
        } catch (JSONException e) {
            Log.e("JSON error", e.getMessage());
        }
    }

    @Override
    public void onRefresh() {
        new JsonTask(this).execute(JSON_URL_CORSI);
    }

    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_search, menu);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_search) {
            SearchView searchView = (SearchView) menuItem.getActionView();
            searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    if (!newText.equals("")) {
                        ArrayList<Corso> searchList = new ArrayList<Corso>();
                        for (int i = 0; i < corsoList.size(); i++) {
                            Corso tempCorso = corsoList.get(i);
                            if (tempCorso.getTitolo().toLowerCase().contains(newText.toLowerCase())) {
                                searchList.add(tempCorso);
                            }


                        }
                        mAdapter.setFilter(searchList);
                    } else {
                        //mAdapter.setFilter(corsoList);
                    }
                    //Toast.makeText(getContext(), newText, Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

            return true;
        }


        return false;
    }
}