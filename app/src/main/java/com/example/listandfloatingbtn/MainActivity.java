package com.example.listandfloatingbtn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    String s1[], s2[];

    int images[] = {R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img};

    RecyclerView RecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView = (RecyclerView) findViewById(R.id.lista);

        s1 = getResources().getStringArray(R.array.nomes);
        s2 = getResources().getStringArray(R.array.autores);

        Adaptador a = new Adaptador(this, s1,s2, images);
        RecyclerView.setAdapter(a);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TextView txt = (TextView) findViewById(R.id.txt1);
                //txt.setText(txt.getText()+"\nNova receita");
            }
        });
    }
    

    private void c(){

    }
}