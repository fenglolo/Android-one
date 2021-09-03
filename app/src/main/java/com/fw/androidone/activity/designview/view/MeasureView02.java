package com.fw.androidone.activity.designview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.fw.androidone.R;

/**
 * description :Canvas和Paint
 * <p>
 * author : apple
 * date : 2021/9/2 16:15
 */
public class MeasureView02 extends View {

    //    Canvas常用的几个绘制方法:
//    drawRect()	画矩形
//    drawCircle()	画圆
//    drawArc()	画圆弧
//    drawRoundRect()	画圆角矩形
//    drawBitmap()	画一个Bitmap
//    drawOval()	画椭圆
//    drawText()	画文字
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint textPaint = new Paint();

    public MeasureView02(Context context) {
        super(context);
        initPaint();
    }

    public MeasureView02(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public MeasureView02(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint.setColor(Color.parseColor("#FF4081"));
        paint2.setColor(Color.parseColor("#334023"));

        textPaint.setColor(Color.parseColor("#FF4081"));
        textPaint.setTextSize(90f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //drawRect() 绘制矩形
//        float left = getLeft();
//        float right = getRight();
//        float top = getTop();
//        float bottom = getBottom();
//        canvas.drawRect(left, top, right, bottom, paint);
//        Rect rect = new Rect(100,100,200,200);
//        canvas.drawRect(rect,paint);
//        RectF rect2 = new RectF(100.5f,100.5f,200.5f,200.5f);
//        canvas.drawRect(rect2,paint);

        //drawCricle() 绘制圆形
//        float width = getWidth();
//        float height = getHeight();
//        float radius = Math.min(width,height)/2;
//        canvas.drawCircle(width/2,height/2,radius,paint);

        //drawArc() 绘制扇形
//        RectF rect = new RectF(0f,0f,500f,500f);
//        canvas.drawArc(rect,0,60,true,paint);
//        canvas.drawArc(rect,60,30,false,paint2);

        //drawBitmap() 绘制Bitmap
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_menu);
//        float width = (getWidth() - bitmap.getWidth()) / 2;
//        float height = (getHeight() - bitmap.getHeight()) / 2;
//        canvas.drawBitmap(bitmap, width, height, paint);

        //drawText()绘制文字
//        canvas.drawText("HelloWorld", 100, 100, textPaint);

        //drawPath() 绘制路径
        Path p = new Path();
        p.moveTo(100, 100);
        p.lineTo(200, 50);
        p.lineTo(300, 100);
        p.lineTo(200, 400);
        canvas.drawPath(p, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measuredHeight(heightMeasureSpec));
    }

//    EXACTLY 精确模式，两种情况
//    控件的layout_width，layout_height
//    -指定数值时，例如layout_width="100dp"
//    -指定为match_parent

//    AT_MOST 最大值模式
//    -控件的layout_width，layout_height指定为wrap_content

    /**
     * 测量宽
     *
     * @param widthMeasureSpec
     */
    private int measureWidth(int widthMeasureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * 测量高
     *
     * @param heightMeasureSpec
     */
    private int measuredHeight(int heightMeasureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}
