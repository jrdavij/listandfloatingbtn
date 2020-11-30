package com.example.listandfloatingbtn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    String s1[], s2[];
    int images[] = {R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img};
    RecyclerView RecyclerView;

    Dialog dialog;
    AlertDialog.Builder dialogBuilder;
    EditText titulo, autor, receita;
    Button btcriar, btcancelar;
    public void dialogo(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View criar = getLayoutInflater().inflate(R.layout.novo, null);
        titulo = criar.findViewById(R.id.txttitulo);
        autor = criar.findViewById(R.id.txtautor);
        receita = criar.findViewById(R.id.txtreceita);

        btcriar = criar.findViewById(R.id.btcriar);

        dialogBuilder.setView(criar);
        dialog = dialogBuilder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView = findViewById(R.id.lista);

        s1 = getResources().getStringArray(R.array.nomes);
        s2 = getResources().getStringArray(R.array.autores);

        Adaptador a = new Adaptador(this, s1,s2, images);
        RecyclerView.setAdapter(a);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.add);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo();
            }
        });
    }
    

    private void c(){

    }
}