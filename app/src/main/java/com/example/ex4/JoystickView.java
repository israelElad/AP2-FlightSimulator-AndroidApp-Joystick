package com.example.ex4;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import static android.view.MotionEvent.ACTION_UP;

public class JoystickView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {
    int width;
    int height;
    int bigR;
    Client client;

    public JoystickView(Context context, Client client) {
        super(context);
        this.client = client;
        getHolder().addCallback(this);
        setOnTouchListener(this);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        drawInXY(this.width / 2, this.height / 2);
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w - getPaddingLeft() - getPaddingRight();
        this.height = h - getPaddingTop() - getPaddingBottom();
        this.bigR = Math.min(this.width, this.height) / 2 - 10;
    }

    public void drawInXY(float x, float y) {
        Canvas canvas = this.getHolder().lockCanvas();
        Paint paint = new Paint();

        // clear the canvas before painting
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        // draw background
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sky), null,
                new RectF(0, 0, this.width, this.height), null);

        // draw oval behind the joystick
        paint.setColor(Color.parseColor("#275CB8"));
        canvas.drawCircle(this.width / 2, this.height / 2, bigR, paint);

        // draw joystick
        paint.setColor(Color.parseColor("#FFFFFF"));
        canvas.drawCircle(x, y, bigR / 3, paint);

        getHolder().unlockCanvasAndPost(canvas);
    }

    public boolean onTouch(View v, MotionEvent e) {
        if ((e.getAction() != MotionEvent.ACTION_UP) && (e.getAction() != MotionEvent.ACTION_CANCEL)) {
            float currentX = e.getX();
            float currentY = e.getY();
            float normalX = (currentX - (this.width / 2)) / (bigR * 2 / 3);
            float normalY = (currentY - (this.height / 2)) / (bigR * 2 / 3);
            if (Math.sqrt(Math.pow(normalX, 2) + Math.pow(normalY, 2)) <= 1) {
                drawInXY(currentX, currentY);
                String aileronSet = "set controls/flight/aileron " + normalX + "\r\n";
                String elevatorSet = "set controls/flight/elevator " + normalY + "\r\n";
            }
        } else {
            drawInXY(this.width / 2, this.height / 2);
            String aileronSet = "set controls/flight/aileron " + "0" + "\r\n";
            String elevatorSet = "set controls/flight/elevator " + "0" + "\r\n";
        }
        return true;
    }
}
