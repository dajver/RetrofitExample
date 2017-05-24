package com.project.retrofitexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.project.retrofitexample.adapter.MusicRecycleList;
import com.project.retrofitexample.api.RestClient;
import com.project.retrofitexample.api.model.SearchModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements TextWatcher, Callback<SearchModel> {

    @BindView(R.id.searchView)
    EditText searchView;

    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    MusicRecycleList musicRecycleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recycleViewSetup(recycleView);
        searchView.addTextChangedListener(this);
    }

    public void recycleViewSetup(RecyclerView recyclerView) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        RestClient.instance().searchAudio(charSequence.toString(), RestClient.API_KEY).enqueue(this);
    }

    @Override
    public void afterTextChanged(Editable editable) { }

    @Override
    public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
        musicRecycleList = new MusicRecycleList(this, response.body().getList());
        recycleView.setAdapter(musicRecycleList);
    }

    @Override
    public void onFailure(Call<SearchModel> call, Throwable t) {
        t.printStackTrace();
    }
}
