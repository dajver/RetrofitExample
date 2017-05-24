package com.project.retrofitexample.adapter;

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

    // наш аррей с списком песен которые нам вернутся с апи
    private List<List<String>> searchModels = new ArrayList<>();
    //колбек который по клику будет что то делать, сами решайте что делать с ним
    private OnItemClickListener onItemClickListener;

    // сетим в конструкторе наш список песен
    public MusicRecycleList(List<List<String>> searchModels) {
        this.searchModels = searchModels;
    }

    // сетим вьюху и втю холдер
    @Override
    public MusicRecycleList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, parent, false);
        MusicRecycleList.ViewHolder pvh = new MusicRecycleList.ViewHolder(v);
        return pvh;
    }

    // биндим данные в вьюху, в нашем случае в textview
    @Override
    public void onBindViewHolder(final MusicRecycleList.ViewHolder holder, final int position) {
        holder.title.setText(searchModels.get(position).get(4) + " - " + searchModels.get(position).get(3));
    }

    @Override
    public int getItemCount() {
        return searchModels.size();
    }

    // холдер с вьюхой
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView)
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            // указываем баттернайфу что мы получаем айдишники из item_music
            ButterKnife.bind(this, itemView);
            //отлавливаем клик по айтему
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // отправляем колбек по клику на айтем, отлавливаем его в MainActivity
                    int position = MusicRecycleList.ViewHolder.super.getAdapterPosition();
                    onItemClickListener.onItemClick(position);
                }
            });
        }
    }

    // сеттер для колбека
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    // интерфейс колбек
    public interface OnItemClickListener {
        void onItemClick(int id);
    }
}