package com.example.dhadotid.cumlaude.Adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dhadotid.cumlaude.Model.CatatanModel;
import com.example.dhadotid.cumlaude.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dhadotid on 03/09/2017.
 */

public class CatatanViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.adapter_titlecatatan_textview)
    TextView titleCatatan;
    @BindView(R.id.adapter_notes_textview)
    TextView notesTextview;
    @BindView(R.id.adapter_time_textview)
    TextView timeTextview;

    public CatatanViewHolder(View v){
        super(v);
        ButterKnife.bind(this, v);
    }

    public void setupUI(CatatanModel data){
        titleCatatan.setText("" + data.getTitle());
        notesTextview.setText("" + data.getCatatan());
        timeTextview.setText("Added on: " + data.getCreated_at());
    }
}
