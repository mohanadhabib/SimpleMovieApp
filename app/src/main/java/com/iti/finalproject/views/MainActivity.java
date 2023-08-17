package com.iti.finalproject.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;

import com.iti.finalproject.controllers.RecyclerAdapterController;
import com.iti.finalproject.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recycler.setAdapter(new RecyclerAdapterController());
        binding.recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}
