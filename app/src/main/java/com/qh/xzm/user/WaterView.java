package com.qh.xzm.user;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

//自定义的水波纹
public class WaterView extends View {

    private Paint mTopPaint;
    private Paint mBottomPaint;
    private Path mPathTop;
    private Path mPathBotom;
    private float φ;

    public WaterView(Context context) {
        this(context, null);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

        int w = context.getResources().getDisplayMetrics().widthPixels;

        //上面的线的画笔
        mTopPaint = new Paint();
        mTopPaint.setColor(Color.WHITE);
        mTopPaint.setAntiAlias(true);

        //下面的线的画笔
        mBottomPaint = new Paint();
        mBottomPaint.setColor(Color.RED);
        mBottomPaint.setAntiAlias(true);
        mBottomPaint.setAlpha(60);
        //上面的线的路径
        mPathTop = new Path();

        //下面的线的路径
        mPathBotom = new Path();
    }

    public WaterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPathTop.reset();
        mPathBotom.reset();
        //开始的位置
        mPathTop.moveTo(getLeft(), getBottom());
        mPathBotom.moveTo(getLeft(), getBottom());
        //获取每个宽度值所占的度数
        double mY = Math.PI * 2 / getWidth();
        φ -= 0.1f;
        //路径移动的坐标
        for (float x = 0; x <= getWidth(); x += 20) {
            mPathTop.lineTo(x, (float) (10 * Math.cos(mY * x + φ) + 15));
            mPathBotom.lineTo(x, (float) (10 * Math.sin(mY * x + φ)));
        }
        int width = getWidth();
        //终止的位置
        mPathTop.lineTo(width, getBottom());
        mPathBotom.lineTo(width, getBottom());
        //绘制两条线的路线
        canvas.drawPath(mPathTop, mTopPaint);
        canvas.drawPath(mPathBotom, mBottomPaint);

        postInvalidateDelayed(20);
    }


    public interface AnimationListener {
        void animation(float y);
    }

    private AnimationListener animationListener;

    public void setAnimationListener(AnimationListener animationListener) {
        this.animationListener = animationListener;
    }


}

