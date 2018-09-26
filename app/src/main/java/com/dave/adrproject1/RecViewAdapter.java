package com.dave.adrproject1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.ViewHolder>{

    ArrayList<StoredConversion> conversions;

    public RecViewAdapter(ArrayList<StoredConversion> convs){
        conversions = convs;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.history_view,parent,false));
    }

    public void onBindViewHolder(ViewHolder holder, int pos){
        StoredConversion sc = conversions.get(pos);
        holder.date.setText(sc.getDate().toString());
        holder.type.setText(sc.getType());
        holder.origMsg.setText(sc.getOriginalMessage());
        holder.convMsg.setText(sc.getConvertedMessage());
    }

    public int getItemCount(){
        return conversions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView type;
        TextView origMsg;
        TextView convMsg;
        public ViewHolder(View item){
            super(item);
            date = (TextView) item.findViewById(R.id.date);
            type = (TextView) item.findViewById(R.id.type);
            origMsg = (TextView) item.findViewById(R.id.origMsg);
            convMsg = (TextView) item.findViewById(R.id.msgConv);
        }
    }

}