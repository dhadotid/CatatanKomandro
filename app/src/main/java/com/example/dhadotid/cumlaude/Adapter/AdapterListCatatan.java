package com.example.dhadotid.cumlaude.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dhadotid.cumlaude.Adapter.ViewHolder.CatatanViewHolder;
import com.example.dhadotid.cumlaude.Model.CatatanModel;
import com.example.dhadotid.cumlaude.R;

import java.util.ArrayList;

/**
 * Created by dhadotid on 03/09/2017.
 */

public class AdapterListCatatan extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<CatatanModel> data;

    public AdapterListCatatan(Context context, ArrayList<CatatanModel> data){
        this.context = context;
        this.data = data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_catatan_layout, null);

        return new CatatanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CatatanViewHolder){
            CatatanModel catatanModel = data.get(position);

            ((CatatanViewHolder) holder).setupUI(catatanModel);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
