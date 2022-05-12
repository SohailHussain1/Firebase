package com.example.blooddoner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EntryDetail extends AppCompatActivity {

    EditText Name,City,Contact;
    Button submit;
    //Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_detail);

        
        Name=findViewById(R.id.editTextTextPersonName);
        City=findViewById(R.id.editTextTextPersonName2);
        submit=findViewById(R.id.solo);
        Contact=findViewById(R.id.editTextTextPersonName3);


        Spinner bloodgrp=findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.BloodTypes, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        bloodgrp.setAdapter(adapter);

        FirebaseDatabase database=FirebaseDatabase.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("Donor");
                DatabaseReference usersRef = ref.child("users");
                HashMap<String, Object>m=new HashMap<String,Object>();
                m.put("Name", Name.getText().toString());
                m.put("City", City.getText().toString());
                m.put("Contact", Contact.getText().toString());
                m.put("blood group", bloodgrp.getSelectedItem().toString());
                usersRef.child("User").push().setValue(m);
            }
        });

    }


}