package com.test.cnim_final.days;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.test.cnim_final.R;
import com.test.cnim_final.WeekActivity;


import java.util.ArrayList;

public class Friday extends AppCompatActivity {

    private ListView listView;
    private Button button_arrowb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friday);
        listView=findViewById(R.id.listView);

        button_arrowb = findViewById(R.id.button_arrow);

        button_arrowb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Friday.this, WeekActivity.class);
                startActivity(intent);
            }
        });
        final ArrayList<String> list=new ArrayList<>();
        ArrayAdapter adapter=new ArrayAdapter<String>(this,R.layout.list_item,list);
        listView.setAdapter(adapter);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Clase").child("V").child("Orar").child("Vineri");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                int i=8;
                for(DataSnapshot snapshot :dataSnapshot.getChildren()){
                    list.add(i+":00"+":" +" "+snapshot.getValue().toString());
                    i++;

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}