package com.test.cnim_final;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


public class AddExam extends AppCompatActivity
{
    private Spinner spinner1;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button addExamen;
    private Object String;

    Button btn_addEx;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);



         //spinner
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddExam.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.materii));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        //calendar
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

        //addExamen
        btn_addEx = findViewById(R.id.addExamen);
        btn_addEx.setOnClickListener(new View.OnClickListener()

       {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AddExam.this, com.test.cnim_final.MainExam.class);
                String str = dateButton.getText().toString();
                String text = mySpinner.getSelectedItem().toString();
                Log.e("afisare str", str);
                Log.e("afisare text", text);
                intent.putExtra("text",str);
                startActivity(intent);

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Elevi").child("alexandruvasile").child("Teza").child(text);

                reference.setValue(str);

            }
        });

    }

    //CALENDAR
    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        Context context;
        datePickerDialog = new DatePickerDialog( this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year)
    {
        String new1 = new String (getMonthFormat(month) + " " + day + " " + year);
        java.lang.String new2 = new1;
        return new2;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "IAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAI";
        if(month == 6)
            return "IUN";
        if(month == 7)
            return "IUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOI";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "IAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

}