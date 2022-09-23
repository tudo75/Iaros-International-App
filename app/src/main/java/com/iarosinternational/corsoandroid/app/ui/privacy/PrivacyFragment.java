package com.iarosinternational.corsoandroid.app.ui.privacy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.iarosinternational.corsoandroid.app.R;
import com.iarosinternational.corsoandroid.app.databinding.FragmentPrivacyBinding;


public class PrivacyFragment extends Fragment {

    private FragmentPrivacyBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPrivacyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textPrivacy;
        textView.setText(R.string.title_privacy);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}