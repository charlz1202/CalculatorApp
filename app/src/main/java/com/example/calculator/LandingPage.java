package com.example.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LandingPage extends AppCompatActivity {

    private Spinner group;
    private TextView tvDisplay;
    private Button btCalculator;

    String name = "Charlie Medialdea";
    String stdId = "300374504";
    String reflection = "It was a fun learning activity doing this lab. " +
            "I enjoyed the hands-on experience and found it incredibly engaging " +
            "to apply theoretical concepts to practical scenarios." +
            "Each step of the lab challenged me to think critically and problem-solve, " +
            "which boosted my confidence in my skills. Additionally, I appreciated the " +
            "opportunity to experiment and explore different methods. " +
            "Overall, it was a rewarding and enriching experience.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_landing_page);

        group = findViewById(R.id.spGroup);
        tvDisplay = findViewById(R.id.tvDisplay);
        btCalculator = findViewById(R.id.btCalculator);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tvGroup, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        group.setAdapter(adapter);

        group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();

                if (position == 0) {
                    tvDisplay.setText("");
                } else {

                    switch (selectedOption) {
                        case "Name":
                            tvDisplay.setText(name);
                            break;
                        case "Student ID":
                            tvDisplay.setText(stdId);
                            break;
                        case "Reflection":
                            tvDisplay.setText(reflection);
                            break;
                        default:
                            tvDisplay.setText("");
                            break;
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tvDisplay.setText("");

            }
        });

        btCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandingPage.this, MainActivity.class);
                startActivity(intent);
            }
        });




    }
}