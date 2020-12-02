package com.example.listandfloatingbtn;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class Adaptador extends RecyclerView.Adapter<Adaptador.Holder> {
    String data1[], data2[], data3[];

    Context context;


    public Adaptador(Context context, String[] data1, String[] data2, String[] data3) {
        this.context = context;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
    }

    @NonNull
    @Override
    public Adaptador.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(context);
        View view = li.inflate(R.layout.card, parent, false);


        return new Adaptador.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.Holder holder, final int position) {
        holder.txttitle.setText(data1[position]);
        holder.txtauthor.setText(data2[position]);
        holder.img.setImageResource(R.drawable.img);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, abrirreceita.class);
                intent.putExtra("titulo", data1[position]);
                intent.putExtra("autor", data2[position]);
                intent.putExtra("receita", data3[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView txttitle, txtauthor;
        ImageView img;
        ConstraintLayout card;
        public Holder(@NonNull View itemView) {
            super(itemView);
            txttitle = itemView.findViewById(R.id.title);
            txtauthor = itemView.findViewById(R.id.author);
            img = itemView.findViewById(R.id.img);
            card = itemView.findViewById(R.id.mainlayout);
        }

    }
}
