package com.iti.finalproject.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iti.finalproject.R;
import com.iti.finalproject.models.Movie;
import com.iti.finalproject.databinding.RecyclerItemBinding;
import com.iti.finalproject.network.Api;
import com.iti.finalproject.views.PageOfDetails;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecyclerAdapterController extends RecyclerView.Adapter<RecyclerAdapterController.RecyclerHolder>{
    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        api.getMovies().enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                String path = "https://image.tmdb.org/t/p/w185/"+response.body().getResults().get(holder.getAdapterPosition()).getPosterPath();
                String pathTwo =  "https://image.tmdb.org/t/p/w185/"+response.body().getResults().get(holder.getAdapterPosition()).getBackdropPath();
                Picasso.get().load(path).into(holder.binding.img);
                holder.binding.title.setText(response.body().getResults().get(holder.getAdapterPosition()).getTitle());
                holder.binding.date.setText(response.body().getResults().get(holder.getAdapterPosition()).getReleaseDate());
                holder.binding.tile.setOnClickListener(v -> {
                    Intent intent = new Intent(holder.binding.getRoot().getContext(), PageOfDetails.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("img",path);
                    bundle.putString("imgTwo",pathTwo);
                    bundle.putString("title",response.body().getResults().get(holder.getAdapterPosition()).getTitle());
                    bundle.putString("info",response.body().getResults().get(holder.getAdapterPosition()).getOverview());
                    bundle.putString("date",response.body().getResults().get(holder.getAdapterPosition()).getReleaseDate());
                    bundle.putString("originalTitle",response.body().getResults().get(holder.getAdapterPosition()).getOriginalTitle());
                    bundle.putString("language",response.body().getResults().get(holder.getAdapterPosition()).getOriginalLanguage());
                    intent.putExtras(bundle);
                    holder.binding.getRoot().getContext().startActivity(intent);
                });
            }
            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(holder.binding.getRoot().getContext(),"Error",Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return 20;
    }

    class RecyclerHolder extends RecyclerView.ViewHolder{
        public RecyclerItemBinding binding;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            binding = RecyclerItemBinding.bind(itemView);
        }
    }

}