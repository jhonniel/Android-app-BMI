package com.example.labexam1;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class BMIresulActivity extends AppCompatActivity {
    TextView tvname, tvage, tvweight, tvheight, tvbmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiresult);

        tvname=findViewById(R.id.tvname);
        tvage=findViewById(R.id.tvage);
        tvweight=findViewById(R.id.tvweight);
        tvheight=findViewById(R.id.tvheight);
        tvbmi=findViewById(R.id.tvbmi);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        tvname.setText("Hi! "+name);
        String age = intent.getStringExtra("age");
        tvage.setText(age);

        String weight = intent.getStringExtra("weight");
        String height = intent.getStringExtra("height");

        final DecimalFormat df2 = new DecimalFormat(".##");
        double w = Double.parseDouble(weight);
        double h= Double.parseDouble(height);
        double hh = h*h;
        double bmi = w/hh;
        double pound = w * 2.2046;
        double cm = h * 100;

        tvweight.setText(df2.format(pound)+" lb");
        tvheight.setText(df2.format(cm) +" cm");
        tvbmi.setText("Your BMI result is "+ df2.format(bmi));
    }
}