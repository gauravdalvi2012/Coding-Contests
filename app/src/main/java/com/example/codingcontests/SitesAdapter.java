package com.example.codingcontests;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SitesAdapter extends RecyclerView.Adapter<SitesAdapter.ViewHolder> {

    ArrayList<SitesModel> sites;
    Context context;

    public SitesAdapter(ArrayList<SitesModel> sites, Context context) {
        this.sites = sites;
        this.context = context;
    }

    @NonNull
    @Override
    public SitesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sites_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SitesAdapter.ViewHolder holder, int position) {
        holder.siteLogo.setImageResource(sites.get(position).getImageId());
        holder.siteName.setText(sites.get(position).getSite());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ContestsActivity.class);
                intent.putExtra("site", sites.get(position).getSite());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sites.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView siteLogo;
        TextView siteName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            siteLogo = itemView.findViewById(R.id.siteLogo);
            siteName = itemView.findViewById(R.id.siteName);
        }
    }
}
