package com.example.a1stapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigDecimal;

public class BMI_Calculator extends AppCompatActivity {
    EditText etName, etGender, etAge, etHeight, etWeight;
    String statement1,statement2="";
    Button btnCal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValidation()){
                    String Name, Gender,Age, height,weight;
                    Name=etName.getText().toString().trim();
                    Gender=etGender.getText().toString().trim();
                    Age=etAge.getText().toString().trim();
                    height=etHeight.getText().toString().trim();
                    weight=etWeight.getText().toString().trim();
                    float BMI=calculate_BMI(height, weight);
                    BMI=round(BMI,1);//two decimal digits, //2.35
                    classification(BMI);
                    String result= Float.toString(BMI);

                     Intent intent=new Intent(BMI_Calculator.this, com.example.a1stapp.BMI_Result.class);
                     passData(intent,Name,Gender,Age,height,weight,result,statement1,statement2);
                     startActivity(intent);
                     finish();

                }
            }
        });
    }

    private float round(float d, int decimalPlace) {
        return BigDecimal.valueOf(d).setScale(decimalPlace,BigDecimal.ROUND_FLOOR).floatValue();
    }

    private void passData(Intent intent,String Name, String Gender, String Age, String height, String weight, String result, String statement1, String statement2) {
        intent.putExtra("Name", Name);
        intent.putExtra("Gender", Gender);
        intent.putExtra("Age", Age);
        intent.putExtra("Height", height);
        intent.putExtra("Weight", weight);
        intent.putExtra("result", result);
        intent.putExtra("statement1", statement1);
        intent.putExtra("statement2", statement2);
    }

    private void classification(float bmi) {

        if(bmi<18.5)
        {
         statement1="Body Mass Deficit";
         statement2="Low Body Mass but higher risk of other illness";
        }
        else if(bmi>= 18.5 && bmi<=24.9)
        {
            statement1="Normal Body Mass ";
            statement2="Congratulations!! Your Body Mass is Normal";
        }
        else if(bmi>=25.0 && bmi<=29.9)
        {
            statement1="Excessive Body Mass";
            statement2="Your Body Mass is hightened. It is considered pre-Obesity";
        }
        else if(bmi>=30.0 && bmi<=34.9)
        {
            statement1="Obesity 1st Degree";
            statement2="High Body Mass. Try to exercise more!";
        }
        else if(bmi>=35.0 && bmi<=39.9)
        {
            statement1="Obesity 2nd Degree";
            statement2="Very High Body Mass. Try to exercise more!";
        }
        else if(bmi>=40.0)
        {
            statement1="Obesity 3rd Degree";
            statement2="Extremely High Body Mass. Try to exercise more!";
        }

    }

    private float calculate_BMI(String Height, String Weight) {
        float h= Float.parseFloat(Height);
        float w= Float.parseFloat(Weight);
        h= h*h;
        float BMI= w/h;
        return BMI;
    }

    private boolean checkValidation() {
        boolean valid=true;
        if(etName.getText().toString().isEmpty()) {
            etName.setError("Enter Your Name");
            valid=false;
        }
        if(etGender.getText().toString().isEmpty()){
            etGender.setError("Enter Your Gender");
            valid=false;
        }
        if(etAge.getText().toString().isEmpty()){
            etAge.setError("Enter Your Age");
            valid=false;
        }
        if(etHeight.getText().toString().isEmpty()){
            etHeight.setError("Enter Your Height");
            valid=false;
        }
        if(etWeight.getText().toString().isEmpty()){
            etWeight.setError("Enter Your Weight");
            valid=false;
        }
        return valid;
    }

    private void init() {
        etName=findViewById(R.id.etName);
        etGender=findViewById(R.id.etGender);
        etAge=findViewById(R.id.etAge);
        etHeight=findViewById(R.id.etHeight);
        etWeight=findViewById(R.id.etWeight);
        btnCal=findViewById(R.id.btnCal);
    }

}