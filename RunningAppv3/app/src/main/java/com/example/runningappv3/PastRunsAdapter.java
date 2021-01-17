package com.example.runningappv3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PastRunsAdapter extends RecyclerView.Adapter<PastRunsAdapter.PastRunsHolder> {

    class PastRunsHolder extends RecyclerView.ViewHolder {
        private final TextView pastRunID;
        private final TextView pastRunDistance;
        private final TextView pastRunTime;
        private final TextView pastRunSpeed;

        private PastRunsHolder(View itemView) {
            super(itemView);
            pastRunID = itemView.findViewById(R.id.ID);
            pastRunDistance = itemView.findViewById(R.id.Distance);
            pastRunTime = itemView.findViewById(R.id.Time);
            pastRunSpeed = itemView.findViewById(R.id.Speed);
        }
    }

    private final LayoutInflater mInflater;
    private List<Run> mRuns; // Cached copy of runs

    PastRunsAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public PastRunsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.past_run_view, parent, false);
        return new PastRunsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PastRunsHolder holder, int position) {
        Run current = mRuns.get(position);
        holder.pastRunID.setText(Long.toString(current.getId()));
        holder.pastRunDistance.setText(String.format("%.2f", current.getDistance()) + " KM");
        holder.pastRunTime.setText(String.format("%.2f", current.getTime()/(1000*60.0)) + " minutes");
        holder.pastRunSpeed.setText(String.format("%.2f", current.getSpeed()) + " min/KM");
    }

    void setRuns(List<Run> runs){
        mRuns = runs;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mRuns != null)
            return mRuns.size();
        else return 0;
    }
}