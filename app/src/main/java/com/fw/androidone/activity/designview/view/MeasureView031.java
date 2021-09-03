package com.fw.androidone.activity.designview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * description :Paint绘制文字：设置路径效果
 * <p>
 * author : apple
 * date : 2021/9/2 16:15
 */
public class MeasureView031 extends View {
    private Paint textPaint;
    private Paint pathPaint;
    private Path path;
    private String[] pathEffectName = {"默认", "CornerPathEffect", "DashPathEffect", "PathDashPathEffect",
            "SumPathEffect", "DiscretePathEffect", "ComposePathEffect"};
    private PathEffect[] pathEffects;

    public MeasureView031(Context context) {
        super(context);
        initPaint();
    }

    public MeasureView031(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public MeasureView031(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    /**
     * 初始化画笔设置
     */
    private void initPaint() {
        //文字画笔
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(40f);

        //路径画笔
        pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathPaint.setColor(Color.parseColor("#FF4081"));
        pathPaint.setStrokeWidth(5f);
        pathPaint.setStyle(Paint.Style.STROKE);

        path = new Path();
        //设置起点
        path.moveTo(0, 0);
        //路径链接的点
        for (int i = 1; i < 37; i++) {
            path.lineTo(i * 30, (float) (Math.random() * 100));
        }

        //初始化PathEffect
        pathEffects = new PathEffect[7];
        pathEffects[0] = new PathEffect();
        pathEffects[1] = new CornerPathEffect(10f);
        pathEffects[2] = new DashPathEffect(new float[]{10f, 5f, 20f, 15f}, 10);
        pathEffects[3] = new PathDashPathEffect(new Path(), 10, 10f, PathDashPathEffect.Style.ROTATE);
        pathEffects[4] = new SumPathEffect(pathEffects[1], pathEffects[2]);
        pathEffects[5] = new DiscretePathEffect(5f, 10f);
        pathEffects[6] = new ComposePathEffect(pathEffects[3], pathEffects[5]);
    }

    /**
     * 绘制
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < pathEffects.length; i++) {
            pathPaint.setPathEffect(pathEffects[i]);
            //绘制路径
            canvas.drawPath(path, pathPaint);
            //绘制文字
            canvas.drawText(pathEffectName[i], 0, 130, textPaint);
            //每绘制一条将画布向下平移180个像素
            canvas.translate(0, 180);//控件的高度要足够大才能平移
        }
        invalidate();//绘制刷新
    }

    /**
     * 测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int wSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (wSpecMode == MeasureSpec.AT_MOST && hSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(300, 300);
        } else if (wSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(300, hSpecSize);
        } else if (hSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(wSpecSize, 300);
        }
    }
}