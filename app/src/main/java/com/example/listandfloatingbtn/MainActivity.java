package com.example.listandfloatingbtn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DocSnippets";


    String s1[], s2[];
    int images[] = {R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img,R.drawable.img};
    RecyclerView RecyclerView;

    Dialog dialog;
    AlertDialog.Builder dialogBuilder;
    EditText titulo, autor, receita;
    Button btcriar;
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
        final FirebaseFirestore db = FirebaseFirestore.getInstance();;




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo();
                btcriar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //firebase thing
                        Map<String, Object> recipe = new HashMap<>();
                        recipe.put("titulo", "chocolate quente");
                        recipe.put("autor", "copenhague");
                        recipe.put("receita","pegue o pó de chocolate, leite e mistura tudo");

                        db.collection("Receitas")
                                .add(recipe)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error adding document", e);
                                    }
                                });

                        dialog.dismiss();
                    }
                });
            }
        });
    }
    

    private void c(){

    }
}