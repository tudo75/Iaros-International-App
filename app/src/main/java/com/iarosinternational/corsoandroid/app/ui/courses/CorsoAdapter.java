package com.iarosinternational.corsoandroid.app.ui.courses;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iarosinternational.corsoandroid.app.R;
import com.iarosinternational.corsoandroid.app.databinding.RecyclerItemCorsoBinding;

import java.util.ArrayList;

public class CorsoAdapter extends RecyclerView.Adapter<CorsoAdapter.ListItemHolder> {

    private ArrayList<Corso> mCorsoList;
    private CoursesFragment mCoursesFragment;

    private RecyclerItemCorsoBinding binding;

    private Context adapter_context;

    public CorsoAdapter (CoursesFragment coursesFragment, ArrayList<Corso> corsoList) {
        mCoursesFragment = coursesFragment;
        mCorsoList = corsoList;
    }

    @NonNull
    @Override
    public CorsoAdapter.ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_corso, parent, false);

            adapter_context = parent.getContext();

            return new ListItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CorsoAdapter.ListItemHolder holder, int position) {

        Corso corso = mCorsoList.get(position);
        holder.mTitolo.setText(corso.getTitolo());
        holder.mDataConsegnaModuli.setText(corso.getDataConsegnaModuli());
        holder.mSchedaCorso.setText(corso.getSchedaCorso());
        holder.mSchedaBando.setText(corso.getSchedaBando());
        holder.mSchedaIscrizione.setText(corso.getSchedaIscrizione());

        if (corso.getSchedaCorsoUrl().equals("")) {
            holder.mSchedaCorso.setVisibility(View.GONE);
        } else {
            holder.mSchedaCorso.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(corso.getSchedaCorsoUrl()));
                    adapter_context.startActivity(i);
                }
            });
        }

        if (corso.getSchedaBandoUrl().equals("")) {
            holder.mSchedaBando.setVisibility(View.GONE);
        } else {
            holder.mSchedaBando.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(corso.getSchedaBandoUrl()));
                    adapter_context.startActivity(i);
                }
            });
        }

        if (corso.getSchedaIscrizioneUrl().equals("")) {
            holder.mSchedaIscrizione.setVisibility(View.GONE);
        } else {
            holder.mSchedaIscrizione.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(corso.getSchedaIscrizioneUrl()));
                    adapter_context.startActivity(i);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mCorsoList.size();
    }

    public void setFilter (ArrayList<Corso> searchResultList) {
        mCorsoList.clear();
        mCorsoList.addAll(searchResultList);
        notifyDataSetChanged();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTitolo;
        TextView mDataConsegnaModuli;
        Button mSchedaCorso;
        Button mSchedaBando;
        Button mSchedaIscrizione;

        public ListItemHolder(View view) {
            super(view);
            mTitolo = view.findViewById(R.id.coursesTitoloItemRW);
            mDataConsegnaModuli = view.findViewById(R.id.coursesDataItemRW);
            mSchedaCorso = view.findViewById(R.id.coursesSchedaCorsoItemRW);
            mSchedaBando = view.findViewById(R.id.coursesSchedaBandoItemRW);
            mSchedaIscrizione = view.findViewById(R.id.coursesSchedaIscrizioneItemRW);

            //view.setClickable(true);
            //view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //mCoursesFragment.showCorso(getAdapterPosition());
            Toast.makeText(this.itemView.getContext(), mTitolo.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}

