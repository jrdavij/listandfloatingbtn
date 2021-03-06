package com.example.listandfloatingbtn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView RecyclerView;
    Adaptador a;
    String data1[], data2[], data3[];

    Dialog dialog;
    AlertDialog.Builder dialogBuilder;
    EditText titulo, autor, receita;
    Button btcriar;

    public void dialogo(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View criar = getLayoutInflater().inflate(R.layout.novo, null);
        titulo = criar.findViewById(R.id.novotitulo);
        autor = criar.findViewById(R.id.novoautor);
        receita = criar.findViewById(R.id.novareceita);

        btcriar = criar.findViewById(R.id.btcriar);

        dialogBuilder.setView(criar);
        dialog = dialogBuilder.create();
        dialog.show();

    }
    public void load(){
        final Context context = this;
        db.collection("Receitas").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    data1 = new String[task.getResult().size()];
                    data2 = new String[task.getResult().size()];
                    data3 = new String[task.getResult().size()];

                    for (int i = 0; i< data1.length ; i++){
                        data1[i] = (String) task.getResult().getDocuments().get(i).get("titulo");
                        data2[i] = (String) task.getResult().getDocuments().get(i).get("autor");
                        data3[i] = (String) task.getResult().getDocuments().get(i).get("receita");
                    }



                    a = new Adaptador(context, data1, data2, data3);
                    RecyclerView.setAdapter(a);
                    Log.d("Receitas obtidas: ", String.valueOf(data1.length));
                }

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView = findViewById(R.id.lista);
        RecyclerView.setAdapter(a);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        load();


        Button btload = findViewById(R.id.btupdate);
        btload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load();
            }
        });

        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo();
                btcriar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String t, a, r;
                        t = titulo.getText().toString();
                        a = autor.getText().toString();
                        r = receita.getText().toString();
                        if (!t.isEmpty() && !a.isEmpty() && !r.isEmpty()){
                            //firebase thing
                            Map<String, Object> recipe = new HashMap<>();
                            recipe.put("titulo", t);
                            recipe.put("autor", a);
                            recipe.put("receita", r);

                            db.collection("Receitas")
                                    .add(recipe)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Log.d("db log: ", "DocumentSnapshot added with ID: " + documentReference.getId());
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w("db log: ", "Error adding document", e);
                                        }
                                    });
                            dialog.dismiss();
                        }
                        else{
                            //vazio
                        }

                    }
                });
            }
        });
    }
    

}