package com.test.cnim_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainExam extends AppCompatActivity
{
    FloatingActionButton mAddFab;
    public static void setOnClickListener(View.OnClickListener onClickListener)
    {
    }

    //geeksforgeeks
    private ListView coursesLV;
    ArrayList<String> coursesArrayList;
    DatabaseReference reference;


    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_exam);

        mAddFab = findViewById(R.id.addExamButton);
        mAddFab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openAddExam();
            }
        });

        textView = findViewById(R.id.textView);
        String str = getIntent().getStringExtra("text");
        textView.setText(str);

        coursesLV = findViewById(R.id.idLVCourses);
        coursesArrayList = new ArrayList<String>();
        initializeListView();

        /* /Read DataBase ?????
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        }); */

    }

    public void openAddExam()
    {
        Intent intent = new Intent(this, AddExam.class);
        startActivity(intent);
    }

    //DataBase READING
    private void initializeListView()
    {
        // creating a new array adapter for our list view.
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, coursesArrayList);

        // below line is used for getting reference
        // of our Firebase Database.
        reference = FirebaseDatabase.getInstance().getReference("Elevi").child("alexandruvasile").child("Teza");

        // in below line we are calling method for add child event
        // listener to get the child of our database.
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // this method is called when new child is added to
                // our data base and after adding new child
                // we are adding that item inside our array list and
                // notifying our adapter that the data in adapter is changed.
                coursesArrayList.add(snapshot.getKey());
                coursesArrayList.add(snapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // this method is called when the new child is added.
                // when the new child is added to our list we will be
                // notifying our adapter that data has changed.
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                // below method is called when we remove a child from our database.
                // inside this method we are removing the child from our array list
                // by comparing with it's value.
                // after removing the data we are notifying our adapter that the
                // data has been changed.
                coursesArrayList.remove(snapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // this method is called when we move our
                // child in our database.
                // in our code we are note moving any child.
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // this method is called when we get any
                // error from Firebase with error.
            }
        });
        // below line is used for setting
        // an adapter to our list view.
        coursesLV.setAdapter(adapter);
    }

}
  /*  @Override
    private Button veziprogramari;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_exam);

        //click settings
        veziprogramari = (Button) findViewById(R.id.veziprogramari);
        veziprogramari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTeze_Programate();
            }
        });
    }

    //call-uri
    public void openTeze_Programate() {
        Intent intent1 = new Intent(this, SetExams.class);
        startActivity(intent1);
    }*/
