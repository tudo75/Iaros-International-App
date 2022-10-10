package com.iarosinternational.corsoandroid.app.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iarosinternational.corsoandroid.app.databinding.ListaNewsBinding;
import com.iarosinternational.corsoandroid.app.R;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ListItemHolder> {

    private ArrayList<News> listNews;
    private ListaNewsBinding binding;

    public NewsAdapter(ArrayList<News> arrayListNews){
        listNews = arrayListNews;
    }

    @NonNull
    @Override
    public NewsAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = ListaNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ListItemHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {

        News tempNews = listNews.get(position);

        holder.homeNewsTitolo.setText(tempNews.getTitolo());
        holder.homeNewsCorpo.setText(tempNews.getCorpo());
        holder.homeNewsData.setText(tempNews.getData());
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }


    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView homeNewsTitolo;
        TextView homeNewsCorpo;
        TextView homeNewsData;

        public ListItemHolder(View view) {

            super(view);
            homeNewsTitolo = view.findViewById(R.id.homeNewsTitolo);
            homeNewsCorpo = view.findViewById(R.id.homeNewsCorpo);
            homeNewsData = view.findViewById(R.id.homeNewsData);
        }

        @Override
        public void onClick(View view) {}
    }
}
