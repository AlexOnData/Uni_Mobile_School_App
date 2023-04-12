package com.test.cnim_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.test.cnim_final.days.Friday;
import com.test.cnim_final.days.Monday;
import com.test.cnim_final.days.Thursday;
import com.test.cnim_final.days.Tuesday;
import com.test.cnim_final.days.Wednesday;

public class WeekActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(WeekActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void Ganesh1(View View)
    {
        String button_text;
        button_text =((Button)View).getText().toString();
        if(button_text.equals("←"))
        {
            Intent ganesh = new Intent(WeekActivity.this, HomeActivity.class);
            startActivity(ganesh);
        }

    }

    public void Ganesh2(View View)
    {
        String button_text;
        button_text =((Button)View).getText().toString();
        if(button_text.equals("Luni"))
        {
            Intent ganesh = new Intent(this, Monday.class);
            startActivity(ganesh);
        }
         if(button_text.equals("Marti"))
    {
        Intent ganesh = new Intent(this, Tuesday.class);
        startActivity(ganesh);
    }
        if(button_text.equals("Miercuri"))
        {
            Intent ganesh = new Intent(this, Wednesday.class);
            startActivity(ganesh);
        }
        if(button_text.equals("Joi"))
        {
            Intent ganesh = new Intent(this, Thursday.class);
            startActivity(ganesh);
        }
        if(button_text.equals("Vineri"))
        {
            Intent ganesh = new Intent(this, Friday.class);
            startActivity(ganesh);
        }

    }
}