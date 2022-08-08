package com.iarosinternational.corsoandroid.app.ui.mission;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.iarosinternational.corsoandroid.app.R;
import com.iarosinternational.corsoandroid.app.databinding.FragmentMissionBinding;

public class MissionFragment extends Fragment {

    private FragmentMissionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMissionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMission;
        textView.setText(R.string.title_mission);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}