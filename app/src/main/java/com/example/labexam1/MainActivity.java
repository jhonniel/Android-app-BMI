package com.example.labexam1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.*;
import android.widget.*;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import android.content.Intent;
import java.time.Year;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btncalc;
    EditText txtname, txtbdate, txtweight, txtheight;
    TextView tvage;
    DatePickerDialog picker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btncalc=findViewById(R.id.btncalc);
        txtname=findViewById(R.id.txtname);
        txtbdate=findViewById(R.id.txtdate);
        txtweight=findViewById(R.id.txtweight);
        txtheight=findViewById(R.id.txtheight);
        tvage=findViewById(R.id.tvage);

        BIRTH();

    }

    public void BIRTH() {
        //datepicker pops when DOB input is clicked
        txtbdate.setInputType(InputType.TYPE_NULL);
        txtbdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            //fills the birthdate textview from the selected value on the date dialog
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String date = (dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                txtbdate.setText(date);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        btncalc.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if (txtname.getText().toString().equals("") || txtbdate.getText().toString().equals("") ||
                        txtweight.getText().toString().equals("") || txtheight.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Please fill all blanks to calculate.",
                            Toast.LENGTH_SHORT).show();

                } else {

                    //Get current date
                    Calendar calendar = Calendar.getInstance();
                    int year2 = calendar.get(Calendar.YEAR);
                    int month2 = calendar.get(Calendar.MONTH);
                    int day2 = calendar.get(Calendar.DAY_OF_MONTH);

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String cDate = simpleDateFormat.format(Calendar.getInstance().getTime());

                    String bDate = txtbdate.getText().toString();
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date date1 = simpleDateFormat1.parse(bDate);
                        Date date2 = simpleDateFormat1.parse(cDate);

                        long startDate = date1.getTime();
                        long endDate = date2.getTime();

                        //Getting age
                        if (startDate <= endDate) {

                            Period period = new Period(startDate, endDate, PeriodType.yearMonthDay());
                            int years = period.getYears();
                            String age = String.valueOf(years + " Year old");
                            String name = txtname.getText().toString();
                            String weight = txtweight.getText().toString();
                            String height = txtheight.getText().toString();
                            Intent intent = new Intent(MainActivity.this, BMIresulActivity.class);
                            intent.putExtra("name", name);
                            intent.putExtra("age", age);
                            intent.putExtra("weight", weight);
                            intent.putExtra("height", height);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getApplicationContext(), "Birth Date error.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                }
            }

            });

        }

    }




