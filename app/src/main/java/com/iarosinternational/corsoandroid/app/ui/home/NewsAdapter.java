package com.iarosinternational.corsoandroid.app.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iarosinternational.corsoandroid.app.MainActivity;

import com.iarosinternational.corsoandroid.app.databinding.ListaNewsBinding;
import com.iarosinternational.corsoandroid.app.R;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ListItemHolder> {

    private ArrayList<News> listNews;
    private ListaNewsBinding binding;

    public NewsAdapter(ArrayList<News> arrayListNews){
        listNews = arrayListNews;
        Log.e("Breakpoint Iaros App", "NewsAdapter constructor");
    }

    @NonNull
    @Override
    public NewsAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_news, parent, false);

        binding = ListaNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        Log.e("Breakpoint Iaros App", "NewsAdapter.ListItemHolder onCreateViewHolder");
        return new ListItemHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        News tempNews = listNews.get(position);

        holder.tViewTitolo.setText(tempNews.getTitolo());

        Log.e("Breakpoint Iaros App", "NewsAdapter onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }


    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tViewTitolo;
        TextView tViewCorpo;
        TextView tViewData;

        public ListItemHolder(View view) {

            super(view);
            tViewTitolo = view.findViewById(R.id.textView_titolo);
            tViewCorpo = view.findViewById(R.id.textView_corpo);
            tViewData = view.findViewById(R.id.textView_data);

            Log.e("Breakpoint Iaros App", "ListItemHolder constructor");
        }

        @Override
        public void onClick(View view) {

        }

    }
}
