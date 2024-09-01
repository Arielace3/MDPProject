package com.example.mdpproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Map extends View {

    private static final String TAG = "Map";
    private Paint paint;

    public Map(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED); // Set paint color to red
        paint.setStyle(Paint.Style.FILL); // Fill the shape
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Get the width and height of the view
        int width = getWidth();
        int height = getHeight();

//         Log width and height (for debugging purposes)
         Log.d(TAG, "Width: " + width + ", Height: " + height);

        canvas.drawColor(Color.YELLOW);
        // Draw a red rectangle that occupies a quarter of the view's width and height
//        canvas.drawRect(
//                width / 4, // left
//                height / 4, // top
//                (width / 4) * 3, // right
//                (height / 4) * 3, // bottom
//                paint
//        );
    }
}
