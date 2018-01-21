package com.micai.fox.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.micai.fox.R;

/**
 * Created by yangjinling on 15/12/27.
 */
public class CyDownProgressView extends View {
    private static final int DEFAULT_MIN_WIDTH = 400; //View默认最小宽度
    private static final int MIN_ALPHA = 30; //最小不透明度
    private static final int MAX_ALPHA = 255; //最大不透明度
    private static final float doughnutRaduisPercent = 0.65f; //圆环外圆半径占View最大半径的百分比
    private static final float doughnutWidthPercent = 0.12f; //圆环宽度占View最大半径的百分比


    //圆环颜色
//    rgba(236,124,100,1)
    private static int[] doughnutColors = new int[]{
            Color.argb(MIN_ALPHA,255,201,0),
            Color.argb(MIN_ALPHA,255,201,0),
            Color.argb(MAX_ALPHA,255,201,0)};
   /* private  int[] doughnutColors = new int[]{
            Color.argb(MIN_ALPHA,124,0,100),
            Color.argb(MIN_ALPHA,124,0,100),
            Color.argb(MAX_ALPHA,124,0,100)};*/
    private Paint paint = new Paint(); //画笔
    private float width; //自定义view的宽度
    private float height; //自定义view的高度
    private float currentAngle = 0f; //当前旋转角度
    private float raduis; //自定义view的最大半径

    private Thread thread = new Thread(){
        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                postInvalidate();
            }
        }
    };
    public CyDownProgressView(Context context) {
        super(context);
        init();
    }
    public CyDownProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public CyDownProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        thread.start();
    }
    private void resetParams() {
        width = getWidth();
        height = getHeight();
        raduis = Math.min(width, height)/2;
    }

    private void initPaint() {
        paint.reset();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        resetParams();

        //将画布中心设为原点(0,0), 方便后面计算坐标
        canvas.translate(width / 2, height / 2);
        //转起来
        canvas.rotate(currentAngle, 0, 0);
        if (currentAngle >= 360f){
            currentAngle = currentAngle - 360f;
        } else{
            currentAngle = currentAngle + 10f;
        }
        //画渐变圆环
        float doughnutWidth = raduis * doughnutWidthPercent;//圆环宽度
        //圆环外接矩形
        RectF rectF = new RectF(-raduis * doughnutRaduisPercent, -raduis * doughnutRaduisPercent, raduis * doughnutRaduisPercent, raduis * doughnutRaduisPercent);
        initPaint();
        paint.setStrokeWidth(doughnutWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setShader(new SweepGradient(0, 0, doughnutColors, null));
        canvas.drawArc(rectF, 0, 360, false, paint);
        //画旋转头部圆
        initPaint();
        //    rgba(236,124,100,1)
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.argb(MAX_ALPHA,255,201,0)/*getResources().getColor(R.color.red)*/);
        canvas.drawCircle(raduis * doughnutRaduisPercent, 0, doughnutWidth / 2, paint);

    }

    /**
     * 当布局为wrap_content时设置默认长宽
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int origin) {
        int result = DEFAULT_MIN_WIDTH;
        int specMode = MeasureSpec.getMode(origin);
        int specSize = MeasureSpec.getSize(origin);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}
