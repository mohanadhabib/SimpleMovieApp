package com.iti.finalproject.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.iti.finalproject.databinding.DetailsPageBinding;
import com.squareup.picasso.Picasso;

public class PageOfDetails extends AppCompatActivity {
    DetailsPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailsPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Picasso.get().load(bundle.getString("img")).into(binding.img);
        Picasso.get().load(bundle.getString("imgTwo")).into(binding.imgTwo);
        binding.title.setText(bundle.getString("title"));
        binding.date.setText(bundle.getString("date"));
        binding.info.setText(bundle.getString("info"));
        binding.originalTitle.setText("Original Title : "+bundle.getString("originalTitle"));
        binding.language.setText("Original Language : "+bundle.getString("language"));
    }
}