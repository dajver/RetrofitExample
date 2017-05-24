package com.project.retrofitexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.retrofitexample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gleb on 5/24/17.
 */

public class MusicRecycleList extends RecyclerView.Adapter<MusicRecycleList.ViewHolder>{

    private List<List<String>> searchModels = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private ArrayList<MusicRecycleList.ViewHolder> viewHolders = new ArrayList<>();
    private Context context;

    public MusicRecycleList(Context context, List<List<String>> searchModels) {
        this.searchModels = searchModels;
        this.context = context;
    }

    @Override
    public MusicRecycleList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, parent, false);
        MusicRecycleList.ViewHolder pvh = new MusicRecycleList.ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final MusicRecycleList.ViewHolder holder, final int position) {
        holder.title.setText(searchModels.get(position).get(4) + " - " + searchModels.get(position).get(3));
    }

    @Override
    public int getItemCount() {
        return searchModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView)
        TextView title;

        ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(String id);
    }
}