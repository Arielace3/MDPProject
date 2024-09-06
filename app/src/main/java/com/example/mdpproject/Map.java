package com.example.mdpproject;

import com.example.mdpproject.Cell;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class Map extends View {

    private static final String TAG = "Map";
    private static Cell[][] cells;
    private static float cellSizeX,cellSizeY;
    private static final int COLS = 15, ROWS = 20;
    private static int[] curCoord = new int[]{1, 1};
    private static ArrayList<int[]> obstacleCoord = new ArrayList<>();
    private Paint background, unexploredPaint, gridNumberPaint, wallPaint;
    private static boolean canDrawRobot = false;
    private static String robotFacing = Constants.NONE;
    private static int robotSize = 3;

    public Map(Context context, AttributeSet attrs) {
        super(context, attrs);
//        init();
        createAllPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw() called");
        super.onDraw(canvas);

        canvas.drawColor(Color.YELLOW);
        // Get the width and height of the view
        int width = getWidth();
        int height = getHeight();
//       Log width and height (for debugging purposes)
         Log.d(TAG, "Width: " + width + ", Height: " + height);
        cellSizeX = (float) 626 / COLS;
        cellSizeY = (float) 732 / ROWS;
        Log.d(TAG, "Cell sizeX: " + String.valueOf(cellSizeX) + ", Cell sizeY: " + String.valueOf(cellSizeY));

        canvas.translate(40, 0);
        createCell();
        drawCell(canvas);
        drawGridNumber(canvas);
        drawRobot(canvas, curCoord);
        drawObstacles(canvas, obstacleCoord);


    }

    private void createAllPaint(){
        //COLOR FOR UNEXPLORED PATH
        unexploredPaint = new Paint();
        unexploredPaint.setColor(Color.parseColor("#0E79E5"));

        gridNumberPaint = new Paint();
        gridNumberPaint.setColor(Color.BLACK);
        gridNumberPaint.setTextSize(12);
        gridNumberPaint.setTypeface(Typeface.DEFAULT_BOLD);

    }

    private void createCell() {
        Log.d(TAG, "createCell() called");
        cells = new Cell[COLS][ROWS];

        for (int x = 0; x < COLS; x++) {
            for (int y = 0; y < ROWS; y++) {
                cells[x][y] = new Cell(x * cellSizeX, y * cellSizeY, (x + 1) * cellSizeX, (y + 1) * cellSizeY, unexploredPaint);
            }
        }

    }

    private void drawCell(Canvas canvas){
        Log.d(TAG, "drawCell() called");
        for (int x = 0; x < COLS; x++) {
            for (int y = 0; y < ROWS; y++) {
                //DRAW EACH INDIVIDUAL CELL
                canvas.drawRect(cells[x][y].startX,cells[x][y].startY,cells[x][y].endX,cells[x][y].endY,cells[x][y].paint);
            }
        }
    }

    //See if better
//    public void drawCell(Canvas canvas) {
//        for (int x = 1; x <= COLS; x++) {
//            for (int y = 0; y < ROWS; y++) {
//                for (int i = 0; i < robotSize; i++) {
//                    Paint textPaint = new Paint();
//                    textPaint.setTextSize(20);
//                    textPaint.setColor(Color.WHITE);
//                    textPaint.setTextAlign(Paint.Align.CENTER);
//                    canvas.drawText(String.valueOf(cells[x][y].getId()),(cells[x][y].startX+cells[x][y].endX)/2, cells[x][y].endY + (cells[x][y].startY-cells[x][y].endY)/4, textPaint);
//                    canvas.drawRect(cells[x][y].startX, cells[x][y].startY, cells[x][y].endX, cells[x][y].endY, cells[x][y].paint);
//                }
//            }
//        }
//    }

    public void drawRobot(Canvas canvas, int[] curCoord) {
        int androidRowCoord = this.convertRow(curCoord[1]);

        // for the shading of square - USED TO BE -1 to + 1
        for (int x = curCoord[0]; x <= curCoord[0] + 2; x++)
            for (int y = androidRowCoord - 2; y <= androidRowCoord; y++)
                cells[x][y].setType("robot");
        Log.d(TAG,"drawRobot()");
    }

    private void drawGridNumber(Canvas canvas) {
        //GRID NUMBER FOR COLUMN
        Log.d(TAG,"drawGridNumber()");

        for (int x = 0; x < 15; x++) {
            if(x > 9){
                Log.d(TAG,"x =" + String.valueOf(x));
//                Log.d(TAG,"cells[x][19].startX: " + String.valueOf(cells[x][19].startX) + "\n" +
//                        "cells[x][19].startX + (cellSizeX / 6): " + (cellSizeX / 6) + String.valueOf(cells[x][19].startX) + (cellSizeX / 6) + "\n" +
//                        "cellSizeX: " + String.valueOf(cellSizeX) + "\n" +
//                        "cells[x][19].endY: " + String.valueOf(cells[x][19].endY) + "\n" +
//                        "cellSizeY: " + String.valueOf(cellSizeY) + "\n" +
//                        "**************************");

                canvas.drawText(Integer.toString(x), cells[x][19].startX + (cellSizeX / 6), cells[x][19].endY + (cellSizeY / 2), gridNumberPaint);
            } else {
                //GRID NUMBER FOR ROW
                canvas.drawText(Integer.toString(x), cells[x][19].startX + (cellSizeX / 4), cells[x][19].endY + (cellSizeY / 2), gridNumberPaint);
            }
        }

        Log.d(TAG,"cellSizeY" + String.valueOf(cellSizeY));
        //GRID NUMBER FOR ROW
        for (int y = 0;y <20;y++) {
            if(y >9){
                canvas.drawText(Integer.toString(19 - y), cells[0][y].startX - (cellSizeX / 2f), cells[0][y].endY - (cellSizeY / 3.5f), gridNumberPaint);
            }
            else {
                canvas.drawText(Integer.toString(19 - y), cells[0][y].startX - (cellSizeX / 1.7f), cells[0][y].endY - (cellSizeY / 3.5f), gridNumberPaint);
            }
        }
    }



    public void drawObstacles(Canvas canvas, ArrayList<int[]> obstacles) {
        // Extract the obstacle coordinates (x and y)
//        int x = obstacles.get(i)[0];
//        int y = obstacles.get(i)[1];
        for (int i = 0; i < obstacles.size(); i++) {
            cells[obstacles.get(i)[0]][obstacles.get(i)[1]].setType("obstacle");
        }
    }

    /**
     * cos row 5 is array[][15]
     * @param row
     * @return
     */
    public int convertRow(int row) {
        return (20 - row);
    }

    public int convertFacingToRotation(String facing) {
        switch (facing) {
            case Constants.NORTH:
                return 0;
            case Constants.EAST:
                return 90;
            case Constants.SOUTH:
                return 180;
            case Constants.WEST:
                return 270;
            default:
                return 0;    // assume
        }
    }

    public boolean getCanDrawRobot() {
        return canDrawRobot;
    }

    public void setCanDrawRobot(boolean isDrawRobot) {
        canDrawRobot = isDrawRobot;
    }

    public String getRobotFacing() {
        return robotFacing;
    }

    public void setRobotFacing(String facing) {
        robotFacing = facing;}

}
