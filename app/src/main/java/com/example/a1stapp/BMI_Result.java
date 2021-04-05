package com.example.a1stapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class BMI_Result extends AppCompatActivity {
    TextView tvResult, tvStat1, tvStat2;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m_i__result);
        final String File_name = "BMI_Result.txt";
        init();
        String data = getData();


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeToFile(data, File_name);
                readFromFile(File_name);
                Intent intent = new Intent(BMI_Result.this, BMI_Calculator.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private String getData() {
        String Name= getIntent().getStringExtra("Name");
        String Gender= getIntent().getStringExtra("Gender");
        String Age= getIntent().getStringExtra("Age");
        String Height= getIntent().getStringExtra("Height");
        String Weight= getIntent().getStringExtra("Weight");
        String Statement1= getIntent().getStringExtra("statement1");
        String Statement2= getIntent().getStringExtra("statement2");
        String Result=getIntent().getStringExtra("result");
        tvResult.setText(Result);
        tvStat1.setText(Statement1);
        tvStat2.setText(Statement2);
        String data=toString(Name,Gender,Age,Height,Weight,Result, Statement1,Statement2);
        return data;
    }

    private String toString(String name, String gender, String age, String height, String weight, String result, String statement1, String statement2) {
    return ("Name= "+ name+ "\n"+ "Gender= "+ gender+ "\n"+ "Age= "+ age+ "\n"+ "Height= "+ height+ "\n"+
            "Weight= "+ weight+ "\n"+ "BMI= "+result +"\n"+ statement1+ "\n"+ statement2+ "\n");
    }

    private void writeToFile(String data, String File_name) {
        try {
            FileOutputStream fos= openFileOutput(File_name,MODE_APPEND);
            fos.write(data.getBytes());
            fos.close();
            Toast.makeText(getApplicationContext(),"Saved to File",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void readFromFile(String File_name){
        try {
            String text = "";
            FileInputStream fis = openFileInput(File_name);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void init() {
        tvResult=findViewById(R.id.tvResult);
        tvStat1=findViewById(R.id.tvStat1);
        tvStat2=findViewById(R.id.tvStat2);
        btnBack=findViewById(R.id.btnBack);
    }
}