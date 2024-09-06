package com.example.mdpproject;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    private static ImageView robot;
    private static Map map;
    private static int rotation = 0;

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
                if (robot.getVisibility() == View.INVISIBLE) {
                    return;
                }
                String instruction = "_";

                //constant expression issue, not sure why
//                switch (view.getId()) {
//                    case R.id.arrowForward:
                          masterRobotMovement(Constants.UP);
//                        instruction = "f";
//                        break;
//                    case R.id.arrowRight:
                          masterRobotMovement(Constants.RIGHT);
//                        instruction = "r";
//                        break;
//                    case R.id.arrowLeft:
                          masterRobotMovement(Constants.LEFT);
//                        instruction = "l";
//                        break;
//                }

                // Send the instruction via Bluetooth

            }
        };

        forwardButton.setOnClickListener(movementOnClickListener);
        rightButton.setOnClickListener(movementOnClickListener);
        leftButton.setOnClickListener(movementOnClickListener);

        //ROBOT settings - KEEP IT INVISIBLE AT FIRST
//        robot = (ImageView) findViewById(R.id.robotcar);
//
//        if (map.getCanDrawRobot()) {
//            robot.setVisibility(View.VISIBLE);
//            rotation = map.convertFacingToRotation(map.getRobotFacing());
//            trackRobot();
//        } else {
//            robot.setVisibility(View.INVISIBLE);
//        }
    }

    private void trackRobot() {
    }


    private void masterRobotMovement(String direction) {
        //various robot movements
    }


}
