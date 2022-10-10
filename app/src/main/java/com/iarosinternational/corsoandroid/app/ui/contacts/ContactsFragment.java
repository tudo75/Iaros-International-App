package com.iarosinternational.corsoandroid.app.ui.contacts;

import android.content.Context;
import android.content.Intent;
import android.icu.text.IDNA;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ActionMenuView;
import androidx.fragment.app.Fragment;

import com.iarosinternational.corsoandroid.app.R;
import com.iarosinternational.corsoandroid.app.databinding.FragmentContactsBinding;

public class ContactsFragment extends Fragment {

    private FragmentContactsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentContactsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView contatti_pagina = binding.contattiPagina;
        final TableRow row_contatti_pagina = binding.rowContattiPagina;
        row_contatti_pagina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), getText(R.string.contatti_pagina).toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getString(R.string.contatti_pagina_url)));
                startActivity(intent);
            }
        });

        //final TextView contatti_address = binding.contattiAddress;
        final TableRow row_contatti_address = binding.rowContattiAddress;
        row_contatti_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getString(R.string.contatti_address_url)));
                startActivity(intent);
                //Toast.makeText(getContext(), getText(R.string.contatti_address).toString(), Toast.LENGTH_SHORT).show();

            }
        });

        final ImageView contatti_social = binding.contattiSocial;
        contatti_social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getContext(), getText(R.string.contatti_instagram).toString(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getString(R.string.contatti_instagram)));
                startActivity(intent);
            }
        });

        final ImageView contatti_face = binding.contattiFace;
        contatti_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), getText(R.string.contatti_face).toString(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getString(R.string.contatti_facebook)));
                startActivity(intent);
            }
        });

        //final TextView contatti_phone = binding.contattiPhone;
        final TableRow row_contatti_phone = binding.rowContattiPhone;
        row_contatti_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + getString(R.string.contatti_phone)));
                startActivity(intent);
            }
        });

        //final TextView contatti_email = binding.contattiemail;
        final TableRow row_contatti_email = binding.rowContattiEmail;
        row_contatti_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, getString(R.string.contatti_email));
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.contatti_email_subject));
                intent.setData(Uri.parse("mailto:" + getString(R.string.contatti_email)));
                startActivity(intent);
            }
        });

        /*
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), getText(R.string.contatti_sito).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), getText(R.string.contatti_social2).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), getText(R.string.contatti_social).toString(), Toast.LENGTH_SHORT).show();
            }
        */

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}