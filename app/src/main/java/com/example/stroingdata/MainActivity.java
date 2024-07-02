package com.example.stroingdata;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editTextNumber;
    TextView resultText;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextNumber = findViewById(R.id.editTextNumber);
        resultText = findViewById(R.id.resulText);

        sharedPreferences = this.getSharedPreferences("com.example.stroingdata", Context.MODE_PRIVATE);

        //write a stored data on create
        int storedAge = sharedPreferences.getInt("storedAge",0);
        if (storedAge == 0){
            resultText.setText("Your age: ");
        }else {
            resultText.setText("Stored age: "+storedAge);
        }
    }

    //save a stored data for restarting
    public void save(View view){
        if(!editTextNumber.getText().toString().matches("")){
            int userAge = Integer.parseInt(editTextNumber.getText().toString());
            resultText.setText("Your age: "+userAge);
            sharedPreferences.edit().putInt("storedAge",userAge).apply(); //set stored data (putInt("key",assignedValue))
        }else {
            resultText.setText("Enter your age!");
        }
    }

    //remove stored data
    public void delete(View view){
        int storedData = sharedPreferences.getInt("storedAge",0); //get stored data(getInt("key",defaultValue))
            if (storedData != 0){
                sharedPreferences.edit().remove("storedAge").apply();
                resultText.setText("Your age:");
            }
        }
    }


