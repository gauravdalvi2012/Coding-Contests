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

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ContestsAdapter extends RecyclerView.Adapter<ContestsAdapter.ViewHolder> {

    ArrayList<ContestsModel> contests;
    Context context;

    public ContestsAdapter(ArrayList<ContestsModel> contests, Context context) {
        this.contests = contests;
        this.context = context;
    }

    @NonNull
    @Override
    public ContestsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contests_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContestsAdapter.ViewHolder holder, int position) {
        holder.contestName.setText(contests.get(position).getName());

        holder.contestStartTime.setText(contests.get(position).getStart_time());
        holder.contestEndTime.setText(contests.get(position).getEnd_time());

        holder.contestDuration.setText(contests.get(position).getDuration());

        holder.contestIn24Hours.setText(contests.get(position).getIn_24_hours());
        holder.contestStatus.setText(contests.get(position).getStatus().equals("BEFORE") ? "UPCOMING" : "RUNNING");

        holder.contestLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(contests.get(position).getUrl()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView contestName;
        TextView contestStartTime;
        TextView contestEndTime;
        TextView contestDuration;
        TextView contestIn24Hours;
        TextView contestStatus;
        ImageView contestLink;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            contestName = itemView.findViewById(R.id.contestName);
            contestStartTime = itemView.findViewById(R.id.contestStartTime);
            contestEndTime = itemView.findViewById(R.id.contestEndTime);
            contestDuration = itemView.findViewById(R.id.contestDuration);
            contestIn24Hours = itemView.findViewById(R.id.contestIn24Hours);
            contestStatus = itemView.findViewById(R.id.contestStatus);
            contestLink = itemView.findViewById(R.id.contestLink);
        }
    }
}
