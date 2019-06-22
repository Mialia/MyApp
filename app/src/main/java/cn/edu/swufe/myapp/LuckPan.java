package cn.edu.swufe.myapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.Random;

public class LuckPan extends View {
    private final String TAG = "LuckPan";

    private Paint mPaintArc;//转盘扇形画笔
    private Paint mPaintItemStr;//转盘文字画笔
    private float mRadius;//圆盘的半径
    private RectF rectFPan;//构建转盘的矩形
    private RectF rectFStr;//构建文字圆盘的矩形
    private String[] mItemStrs = {""};
    private ArrayList<Path> mArcPaths;
    private float mItemAnge;
    private int mRepeatCount = 4;//转几圈
    private int mLuckNum = 2;//最终停止的位置
    private float mStartAngle = 0;//存储圆盘开始的位置
    private float mOffsetAngle = 0;//圆盘偏移角度（当Item数量为4的倍数的时候）
    private float mTextSize = 20;//文字大小
    private ObjectAnimator objectAnimator;
    private LuckPanAnimEndCallBack luckPanAnimEndCallBack;
    public String result="叮";
    public Helper helper;


    public LuckPanAnimEndCallBack getLuckPanAnimEndCallBack() {
        return luckPanAnimEndCallBack;
    }

    public void setLuckPanAnimEndCallBack(LuckPanAnimEndCallBack luckPanAnimEndCallBack) {
        this.luckPanAnimEndCallBack = luckPanAnimEndCallBack;
    }
    public LuckPan(Context context) {
        this(context,null);
    }

    public LuckPan(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LuckPan(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //helper=new Helper();
        init();
    }

    private void init() {
        mPaintArc = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintArc.setStyle(Paint.Style.FILL);
        mPaintItemStr = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintItemStr.setColor(Color.parseColor("#757575"));
        mPaintItemStr.setStrokeWidth(3);
        mPaintItemStr.setTextAlign(Paint.Align.CENTER);
        mArcPaths = new ArrayList<>();
    }

    /**
     * 设置转盘数据
     * @param items
     */
    public void setItems(String[] items){
        mItemStrs = items;
        mOffsetAngle=0;
        mStartAngle=0;
        mOffsetAngle = 360/items.length/2;
        Log.i(TAG, "setItems: "+items);
        invalidate();
    }

    /**
     * 设置转盘数据
     */
    public void setLuckNumber(int luckNumber){
        mLuckNum = luckNumber;
    }
    public int getmLuckNum(){
        return mLuckNum;
    }
    @Override

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);
        mRadius = Math.min(w,h)/2*0.9f;
        //这里是将（0，0）点作为圆心
        rectFPan = new RectF(-mRadius,-mRadius,mRadius,mRadius);
        rectFStr = new RectF(-mRadius/7*5,-mRadius/7*5,mRadius/7*5,mRadius/7*5);
        //每一个Item的角度
        mItemAnge = 360 / mItemStrs.length;
        mTextSize = mRadius/9;
        mPaintItemStr.setTextSize(mTextSize);
        //数据初始化
        mOffsetAngle=0;
        mStartAngle=0;
        mOffsetAngle = mItemAnge/2;
    }
    public void startAnim(){
        Random random=new Random(2);
        mLuckNum = random.nextInt(mItemStrs.length);//随机生成结束位置
        //mLuckNum =2;
        if(objectAnimator!=null){
            objectAnimator.cancel();
        }
        result=mItemStrs[mLuckNum];
        helper=new Helper();
        helper.setResult(result);
        Log.i(TAG, "startAnim: setResult"+result);
        setResult(result);
        float v = mItemAnge*mLuckNum+mStartAngle%360;//如果转过一次了那下次旋转的角度就需要减去上一次多出的，否则结束的位置会不断增加的

        objectAnimator = ObjectAnimator.ofFloat(this, "rotation", mStartAngle, (mStartAngle-mRepeatCount*360-v));

        objectAnimator.setDuration(4000);

        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if(luckPanAnimEndCallBack!=null){
                    result=mItemStrs[mLuckNum];
                    Log.i(TAG, "onAnimationEnd: rrr里面"+result);
                    luckPanAnimEndCallBack.onAnimEnd(result);
                }
            }
        });
        objectAnimator.start();
        mStartAngle -= mRepeatCount*360+v;
        Log.i(TAG, "onAnimationEnd: rrr外面一层"+result);


    }

    @Override

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/2,getHeight()/2);//画布中心点设置为（0，0）
        canvas.rotate(-90-mOffsetAngle);
        drawPanItem(canvas);
        drawText(canvas);
    }
    //画文字
    private void drawText(Canvas canvas) {
        for(int x = 0;x<mItemStrs.length;x++){
            Path path = mArcPaths.get(x);
            canvas.drawTextOnPath(mItemStrs[x],path,0,0,mPaintItemStr);
        }
    }
    //扇形画图
    private void drawPanItem(Canvas canvas) {
        float startAng = 0;//扇形开始的角度
        for (int x = 1;x<= mItemStrs.length;x++){
            if(x%6 == 1){

                mPaintArc.setColor(Color.parseColor("#ffb74d"));
            }
            else if(x%6 == 2){

                mPaintArc.setColor(Color.parseColor("#dce775"));
            }
            else if(x%6 == 3){

                mPaintArc.setColor(Color.parseColor("#fff176"));
            }
            else if(x%6 == 4){

                mPaintArc.setColor(Color.parseColor("#81c784"));
            }
            else if(x%6 == 5){

                mPaintArc.setColor(Color.parseColor("#ffd54f"));
            }
            else {
                //偶数
                mPaintArc.setColor(Color.parseColor("#aed581"));
            }
            Path path = new Path();
            path.addArc(rectFStr,startAng,mItemAnge);//文字的路径圆形比盘的小
            mArcPaths.add(path);
            canvas.drawArc(rectFPan,startAng,mItemAnge,true,mPaintArc);
            startAng+=mItemAnge;

        }

    }
    public void setResult(String str){
        Log.i(TAG, "startAnim: setResult(pan)"+result);
        result=str;
    }

    public String getResult() {
        Log.i(TAG, "startAnim: getResult(pan)"+result);
        return result;
    }
}