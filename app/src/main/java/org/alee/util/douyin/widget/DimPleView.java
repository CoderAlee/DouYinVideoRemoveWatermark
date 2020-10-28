package org.alee.util.douyin.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**********************************************************
 *
 * @author: MY.Liu
 * @date: 2020/10/26
 * @description: xxxx
 *
 *********************************************************/
public class DimPleView extends View {
    private final List<Particle> mParticleList = new ArrayList<>();
    /**
     * 路径，用于测量扩散圆某一处的X,Y值
     */
    private final PathMeasure mPathMeasure = new PathMeasure();
    /**
     * 扩散圆上某一点的x,y
     */
    private final float[] mCoordinate = new float[2];
    /**
     * 扩散圆上某一点切线
     */
    private final float[] mTan = new float[2];
    private final Random mRandom = new Random();
    /**
     * 粒子数量
     */
    private final long mParticleNumber = 2500;
    /**
     * 粒子半径
     */
    private final float mParticleRadius = 2.2f;
    /**
     * 扩散圆半径
     */
    private final float mDiffusionRadius = 268f;
    private float mWidth;
    private float mHeight;
    private ValueAnimator mAnimator;
    private Paint mPaint;
    private Paint mInnerCirclePaint;
    private Paint mMiddleCircle;
    private Path mPath;

    public DimPleView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mAnimator = ValueAnimator.ofFloat(0f, 1f);
        mAnimator.setDuration(2 * 1000);
        mAnimator.setRepeatCount(-1);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(animation -> {
            updateParticle();
            invalidate();
        });
        mInnerCirclePaint = new Paint();
        mInnerCirclePaint.setColor(Color.WHITE);
        mInnerCirclePaint.setAntiAlias(true);
        mMiddleCircle = new Paint();
        mMiddleCircle.setColor(0xBBDEFF);
        mMiddleCircle.setAntiAlias(true);
        mPaint = new Paint();
        mPaint.setColor(0x86DDFF);
        mPaint.setAntiAlias(true);
        mPath = new Path();
    }

    private void updateParticle() {
        for (Particle particle : mParticleList) {
            if (particle.getMOffSet() > particle.getMMaxOffSet()) {
                particle.setMOffSet(0f);
                particle.setMSpeed(mRandom.nextInt(3) + 1.5f);
                particle.setMMaxOffSet(mRandom.nextInt(250));
            }
            particle.setMX((float) (mWidth / 2 + Math.cos(particle.getMAngle()) * (mDiffusionRadius + particle.getMOffSet())) + particle.getMOffSetX() * particle.getMDirection());
            float y = particle.getMY() > mHeight / 2 ? (float) (Math.sin(particle.getMAngle()) * (mDiffusionRadius + particle.getMOffSet()) + mHeight / 2) : (float) (mHeight / 2 - Math.sin(particle.getMAngle()) * (mDiffusionRadius + particle.getMOffSet()));
            particle.setMY(y);
            particle.setMOffSet(particle.getMSpeed() + particle.getMOffSet());
        }
    }

    public DimPleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DimPleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = (float) w;
        mHeight = (float) h;
        mParticleList.clear();
        mPath.addCircle(mWidth / 2, mHeight / 2, mDiffusionRadius, Path.Direction.CCW);
        mPathMeasure.setPath(mPath, false);
        for (int i = 0; i < mParticleNumber; i++) {
            mPathMeasure.getPosTan(i / (float) mParticleNumber * mPathMeasure.getLength(), mCoordinate, mTan);
            Particle particle = new Particle();
            particle.setMOffSet(mRandom.nextInt(200));
            particle.setMSpeed(mRandom.nextInt(2) + 0.5f);
            particle.setMX(mCoordinate[0] + mRandom.nextInt(6) - 3f);
            particle.setMY(mCoordinate[1] + mRandom.nextInt(6) - 3f);
            particle.setMRadius(mParticleRadius);
            particle.setMOffSetX(mRandom.nextInt(3));
            particle.setMDirection(mRandom.nextInt(3) - 1.5f);
            particle.setMAngle(Math.acos(((mCoordinate[0] - mWidth / 2) / mDiffusionRadius)));
            particle.setMMaxOffSet(mRandom.nextInt(250) + 0f);
            mParticleList.add(particle);
        }
        mAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Particle particle : mParticleList) {
            int alpha = 5f < particle.getMOffSet() ? (int) ((1f - particle.getMOffSet() / particle.getMMaxOffSet()) * 0.8 * 255f) : 225;
            float distance = (float) Math.sqrt((mWidth / 2 - particle.getMX()) * (mWidth / 2 - particle.getMX()) + (mHeight / 2 - particle.getMY()) * (mHeight / 2 - particle.getMY())) - mDiffusionRadius;
            distance = Math.abs(distance);
            Paint paint;
            if (30 >= distance) {
                paint = mInnerCirclePaint;
            } else if (65 >= distance) {
                paint = mMiddleCircle;
            } else {
                paint = mPaint;
            }
            paint.setAlpha(alpha);
            canvas.drawCircle(particle.getMX(), particle.getMY(), particle.getMRadius(), paint);
        }
    }
}
