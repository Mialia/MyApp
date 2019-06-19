package cn.edu.swufe.myapp;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;

public class SurfaceViewImpl extends android.view.SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder mHolder;
    private Canvas mCanvas;

    private Thread mDrawThread;//用于绘制的线程
    private boolean isRunning;//作为子线程运行的控制开关

    public SurfaceViewImpl(Context context) {
        this(context, null);
    }

    public SurfaceViewImpl(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHolder = getHolder();
        mHolder.addCallback(this);

        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning = true;
        mDrawThread = new Thread(this);
        mDrawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;
    }

    @Override
    public void run() {
        //不断进行绘制
        while (isRunning) {
            draw();
        }
    }

    private void draw() {
        /**
         * try-catch与判空的原因:
         * 当SurfaceView在主界面时，如果点击home或者back键，都会使得Surface销毁，
         * 但是在销毁之后，有可能已经进入该方法执行相应的逻辑了，因此需要对mCanvas进行判空，
         * 另外，由于Surface被销毁，但是线程却不是那么容易被关闭，继续执行draw something的操作，
         * 此时就有可能会抛出某些异常
         */
        try {
            //首先拿到Canvas用于绘制
            mCanvas = mHolder.lockCanvas();
            if (mCanvas != null) {
                //TODO draw something
            }
        } catch (Exception e) {
        } finally {
            if (mCanvas != null)
                mHolder.unlockCanvasAndPost(mCanvas);
        }
    }
}

