package com.example.manga;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Radapter extends RecyclerView.Adapter<Radapter.MyViewHolder> {

    private static RviewInterface rviewInterface;
    Context context;
    ArrayList<Model> models;
    public Radapter(Context context, ArrayList<Model> models,RviewInterface rviewInterface)
    {
        Radapter.rviewInterface = rviewInterface;
        this.context = context;
        this.models = models;
    }


    @NonNull
    @Override
    public Radapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_view_row,parent,false);
        return new MyViewHolder(view,rviewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Radapter.MyViewHolder holder, int position) {
        holder.textView.setText(models.get(position).getName());
        holder.imageView.setImageResource(models.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textView;
        public MyViewHolder(@NonNull View itemView,RviewInterface rviewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(rviewInterface != null)
                    {
                        int getPos = getAdapterPosition();

                        if(getPos != RecyclerView.NO_POSITION)
                        {
                            rviewInterface.onItemClick(getPos);
                        }
                    }
                }
            });
        }
    }
}
