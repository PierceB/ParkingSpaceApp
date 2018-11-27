package com.wits.witsparkingspace;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class LineView extends View {

    private Paint paint = new Paint();

    public LineView(Context context) {
        super(context);
        /*BitmapDrawable bg = new BitmapDrawable(BitmapFactory.decodeResource(getResources(), R.drawable.car_lot));
        bg.setGravity(Gravity.CENTER);
        setBackgroundDrawable(bg);*/
        setBackgroundResource(R.drawable.cell_crop);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (Pop.is_open == 0){
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        paint.setAlpha(80);}

        /*Point point1 = Pop.coordinateList_1.get(0);
        Point point2 = Pop.coordinateList_1.get(1);
        Point point3 = Pop.coordinateList_1.get(2);
        Point point4 = Pop.coordinateList_1.get(3);

        int x1 = point1.x;
        int y1 = point1.y;
        int x2 = point2.x;
        int y2 = point2.y;
        int x3 = point3.x;
        int y3 = point3.y;
        int x4 = point4.x;
        int y4 = point4.y;*/


        drawRhombus(canvas, paint, 90,114,93,138,150,132,146,109);

        paint.setAntiAlias(true);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(2);
        paint.setStrokeCap(Paint.Cap.ROUND);




        drawRhombus(canvas, paint, 90,114,93,138,150,132,146,109);



    }

    public void drawRhombus(Canvas canvas, Paint paint, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        //int halfWidth = width / 2;
        //int Wfactor = 1;
        //int Hfactor = 2;

        Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3,y3);
        path.lineTo(x4, y4);
        path.lineTo(x1, y1);
        path.close();

        canvas.drawPath(path, paint);


    }
}
