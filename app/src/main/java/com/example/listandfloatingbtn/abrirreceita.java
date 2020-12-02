package com.example.listandfloatingbtn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class abrirreceita extends AppCompatActivity {
    TextView titulo, autor, receita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abrirreceita);

        titulo = findViewById(R.id.txttitulo);
        autor = findViewById(R.id.txtautor);
        receita = findViewById(R.id.txtreceita);

        titulo.setText(getIntent().getStringExtra("titulo"));
        autor.setText(getIntent().getStringExtra("autor"));
    }
}