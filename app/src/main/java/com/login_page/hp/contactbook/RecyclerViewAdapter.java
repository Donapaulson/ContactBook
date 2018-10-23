package com.login_page.hp.contactbook;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.RecyclerViewHolder>{

    ArrayList<String> name;
    Context context;

    public RecyclerViewAdapter(ArrayList<String> name, Context context){
        this.name = name;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.tv.setText(name.get(position));
        String a = (String) holder.tv.getText();
//        Log.e("tv text",""+a);
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.contactName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
                Intent intent = new Intent(context,ContactDetails.class);
//                Log.e("click",""+tv.getText());
                intent.putExtra("Person",tv.getText());
                context.startActivity(intent);
        }
    }
}
