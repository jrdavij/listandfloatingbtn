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

        titulo = findViewById(R.id.vertitulo);
        autor = findViewById(R.id.verautor);
        receita = findViewById(R.id.verreceita);

        titulo.setText(getIntent().getStringExtra("titulo"));
        autor.setText(getIntent().getStringExtra("autor"));
        receita.setText(getIntent().getStringExtra("receita"));
    }
}