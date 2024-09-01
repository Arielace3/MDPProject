package com.example.mdpproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton forwardButton = (ImageButton) findViewById(R.id.arrowForward);
        ImageButton rightButton = (ImageButton) findViewById(R.id.arrowRight);
        ImageButton leftButton = (ImageButton) findViewById(R.id.arrowLeft);


        View.OnClickListener movementOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String instruction = "_";

                //constant expression issue, not sure why
//                switch (view.getId()) {
//                    case R.id.arrowForward:
//                        instruction = "f";
//                        break;
//                    case R.id.arrowRight:
//                        instruction = "r";
//                        break;
//                    case R.id.arrowLeft:
//                        instruction = "l";
//                        break;
//                }

                // Send the instruction via Bluetooth

            }
        };

        forwardButton.setOnClickListener(movementOnClickListener);
        rightButton.setOnClickListener(movementOnClickListener);
        leftButton.setOnClickListener(movementOnClickListener);
    }

}
