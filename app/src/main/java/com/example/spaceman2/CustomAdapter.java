package com.example.spaceman2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    ArrayList  IME, MAX;


    CustomAdapter(Context context, ArrayList IME, ArrayList MAX){
                this.context = context;
                this.IME = IME;
                this.MAX = MAX;

    }
    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
                holder.IME.setText(String.valueOf(IME.get(position)));
                holder.MAX.setText(String.valueOf(MAX.get(position)));
    }

    @Override
    public int getItemCount() {
        return IME.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView  IME, MAX;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            IME = itemView.findViewById(R.id.IME);
            MAX = itemView.findViewById(R.id.MAX);
        }
    }
}
