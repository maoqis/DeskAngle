package com.example.anglelib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.maoqis.desk.javaanglelib.AngleOffsetBean;
import com.maoqis.desk.javaanglelib.AngleUtil;

import java.util.ArrayList;
import java.util.List;

public class DeskView extends View {

    public static final int MODE_All_ANGLE = 0;
    public static final int MODE_ZONE_ANGLE = 1;
    public static final int MODE_SINGLE_ANGLE = 2;
    public int mode = 2;
    protected boolean isClearLast = true;
    public int zone = -1;
    private static final String TAG = "DeskView";
    double w;
    double h;

    public int colors[] = {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK};
    Paint paint = new Paint();
    private double mSingleAngle;

    public DeskView(Context context) {
        super(context);
    }

    public DeskView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DeskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSingleAngle(double singleAngle) {
        this.mSingleAngle = singleAngle;
    }

    public double getSingleAngle() {
        return mSingleAngle;
    }

    public boolean isClearLast() {
        return isClearLast;
    }

    public void setClearLast(boolean clearLast) {
        isClearLast = clearLast;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        w = getMeasuredWidth();
        h = getMeasuredHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawPaint(paint);
        drawBg(canvas);

        if (mode == MODE_All_ANGLE) {
            drawAllAngle(canvas);
        } else if (mode == MODE_SINGLE_ANGLE) {
            double singleAngle = this.mSingleAngle;
            AngleOffsetBean angleOffsetBean = drawAngle(canvas, singleAngle);
            String text = angleOffsetBean.angle + "度" + " 类型" + angleOffsetBean.getZone() + " 偏移:" + angleOffsetBean.getOffset();
            Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
        } else if (mode == MODE_ZONE_ANGLE) {
            drawZone(canvas);
        }


    }

    private void drawZone(Canvas canvas) {
        List<Double> angles = new ArrayList<>();
        switch (zone) {
            case 1:
                for (int i = 0; i <= 7; i++) {
                    angles.add((double) i);
                }

                break;
            case 2:
                for (int i = 7; i <= 14; i++) {
                    angles.add((double) i);
                }
                break;
            case 3:
                for (int i = 14; i <= 20; i++) {
                    angles.add((double) i);
                }
//                    angles.add(20.5);
                break;
            case 4:
                angles.add(20.5);
                for (int i = 21; i <= 26; i++) {
                    angles.add((double) i);
                }
//                    angles.add(26.5);
                break;
            case 5:
                for (int i = 27; i <= 30; i++) {
                    angles.add((double) i);
                }
//                    angles.add(33.7);
                break;
            case 6:
                for (int i = 30; i <= 34; i++) {
                    angles.add((double) i);
                }
//                    angles.add(33.7);
                break;
            case 7:
                for (int i = 34; i <= 39; i++) {
                    angles.add((double) i);
                }
//                    angles.add(33.7);
                break;
            case 8:
                for (int i = 39; i <= 45; i++) {
                    angles.add((double) i);
                }
//                    angles.add(33.7);
                break;
            case 9:
                for (int i = 45; i <= 53; i++) {
                    angles.add((double) i);
                }
//                    angles.add(33.7);
                break;
            case 10:
                for (int i = 53; i <= 64; i++) {
                    angles.add((double) i);
                }
//                    angles.add(33.7);
                break;
            case 11:
                for (int i = 64; i <= 76; i++) {
                    angles.add((double) i);
                }
//                    angles.add(33.7);
                break;
            case 12:
                for (int i = 76; i <= 90; i++) {
                    angles.add((double) i);
                }
                break;

        }
        drawAngle(canvas, angles);
    }

    public void setMode(int mode) {
        this.mode = mode;
        invalidate();
    }

    private void drawAllAngle(Canvas canvas) {

        for (int i = 0; i <= 90; i++) {
            drawAngle(canvas, i);
        }

    }

    private void drawAngle(Canvas canvas, List<Double> angles) {
        for (Double angle : angles) {
            drawAngle(canvas, angle);
        }

    }

    private AngleOffsetBean drawAngle(Canvas canvas, double angle) {
        int pos = ((int) angle) % colors.length;
        int color = colors[pos];
        if (mSingleAngle == angle) {
            color = Color.RED;
        }
        paint.setColor(color);



        Log.d(TAG, "onDraw: w h = " + w + " , " + h);
        AngleOffsetBean zoneOffset = AngleUtil.getZoneOffset(angle);
        Log.d(TAG, "onDraw: zoneOffset=" + zoneOffset);
        PointF pointF = getPointF(zoneOffset);
        Log.d(TAG, "onDraw: point=" + pointF);

        canvas.drawLine(0, 0, pointF.x, (float) pointF.y, paint);
        return zoneOffset;
    }

    private void drawBg(Canvas canvas) {
        canvas.drawColor(Color.WHITE);                  //设置背景颜色
        paint.setColor(Color.BLACK);                    //设置画笔颜色
        paint.setAntiAlias(true);
        paint.setStrokeWidth((float) 1.0);              //设置线宽
        canvas.drawLine(0, 0, 0, (float) h, paint);        //绘制直线
        canvas.drawLine(0, (float) h - 1, (float) w, (float) h - 1, paint);         //绘制直线
        canvas.drawLine((float) w - 1, (float) h - 1, (float) w - 1, (float) 0, paint);        //绘制直线
        canvas.drawLine((float) w, (float) 0, (float) 0, (float) 0, paint);        //绘制直线

        //w
        for (int i = 1; i <= 4; i++) {
            canvas.drawLine((float) w * i / 4f, (float) h, (float) w * i / 4f, (float) h - 15, paint);
        }
        //h
        for (int i = 1; i <= 8; i++) {
            canvas.drawLine((float) w, (float) h * i / 8f, (float) w - 15, (float) h * i / 8f, paint);
        }


    }

    @NonNull
    private PointF getPointF(AngleOffsetBean zoneOffset) {
        PointF pointF = new PointF();
        if (zoneOffset.getZone() == 1) {
            pointF.x = (float) (zoneOffset.offset * w / 400f);
            pointF.y = (float) h;
        } else if (zoneOffset.getZone() == 2) {
            pointF.x = (float) w;
            pointF.y = (float) (h - zoneOffset.getOffset() * w / 400f);
        } else if (zoneOffset.getZone() == 3) {
            pointF.x = (float) w;
            pointF.y = (float) (w - zoneOffset.getOffset() * w / 400f);
        }
        return pointF;
    }
}
