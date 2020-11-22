package com.example.listandfloatingbtn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adaptador extends RecyclerView.Adapter<Adaptador.Holder> {
    String data1[], data2[];
    int images[];
    Context context;

    public Adaptador( Context context, String[] data1, String[] data2, int[] images) {
        this.data1 = data1;
        this.data2 = data2;
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public Adaptador.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(context);
        View view = li.inflate(R.layout.card, parent, false);
        return new Adaptador.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.Holder holder, int position) {
        holder.txttitle.setText(data1[position]);
        holder.txtauthor.setText(data2[position]);
        holder.img.setImageResource(images[position]);

    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView txttitle, txtauthor;
        ImageView img;
        public Holder(@NonNull View itemView) {
            super(itemView);
            txttitle = itemView.findViewById(R.id.title);
            txtauthor = itemView.findViewById(R.id.author);
            img = itemView.findViewById(R.id.img);
        }

    }
}
