package com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.pattern.UpdateListener;
import com.huawei.animationkit.computationalwallpaper.pattern.arranger.AbstractArranger;
import com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorAnimation;
import com.huawei.animationkit.computationalwallpaper.pattern.variable.VariableType;
import com.huawei.animationkit.computationalwallpaper.vectordrawable.VectorDrawableCompat;
import defpackage.abv;
import defpackage.adl;
import defpackage.aeb;
import defpackage.aen;
import defpackage.aep;
import defpackage.aeq;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Properties;

/* loaded from: classes8.dex */
public class VectorArranger extends AbstractArranger {
    public static final Parcelable.Creator<VectorArranger> CREATOR = new Parcelable.Creator<VectorArranger>() { // from class: com.huawei.animationkit.computationalwallpaper.pattern.arranger.impl.VectorArranger.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: ge_, reason: merged with bridge method [inline-methods] */
        public VectorArranger createFromParcel(Parcel parcel) {
            return new VectorArranger(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public VectorArranger[] newArray(int i) {
            return new VectorArranger[i];
        }
    };
    private static final float NO_ROTATE = 0.0f;
    private static final float NO_SCALE = 1.0f;
    private static final float NO_TRANSLATE = 0.0f;
    private static final String RESOURCE = "resource";
    private static final String ROTATE = "vector_rotate";
    private static final String SCALE = "vector_scale";
    private static final String TAG = "VectorArranger";
    private static final String TRANSLATE_X = "vector_translate_x";
    private static final String TRANSLATE_Y = "vector_translate_y";
    private AnimatorSet mAnimatorSet;
    private Bitmap mBitmap;
    private boolean mCanTranslate;
    private float mInitRotate;
    private float mInitScale;
    private float mInitTranslateX;
    private float mInitTranslateY;
    private final Paint mPaint;
    private int mRotateInterval;
    private float mRotateValue;
    private float mScaleMax;
    private float mScaleMin;
    private float mScaleValue;
    private float mTranslateX;
    private float mTranslateY;
    private UpdateListener mUpdateListener;
    private List<VectorAnimation> mVectorAnimations;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public VectorArranger() {
        this.mTranslateX = 0.0f;
        this.mTranslateY = 0.0f;
        this.mScaleValue = 1.0f;
        this.mRotateValue = 0.0f;
        this.mScaleMin = 1.0f;
        this.mScaleMax = 1.0f;
        this.mRotateInterval = -1;
        this.mCanTranslate = false;
        this.mInitScale = -1.0f;
        this.mInitRotate = 0.0f;
        this.mInitTranslateX = 0.0f;
        this.mInitTranslateY = 0.0f;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
    }

    private VectorArranger(Parcel parcel) {
        this.mTranslateX = 0.0f;
        this.mTranslateY = 0.0f;
        this.mScaleValue = 1.0f;
        this.mRotateValue = 0.0f;
        this.mScaleMin = 1.0f;
        this.mScaleMax = 1.0f;
        this.mRotateInterval = -1;
        this.mCanTranslate = false;
        this.mInitScale = -1.0f;
        this.mInitRotate = 0.0f;
        this.mInitTranslateX = 0.0f;
        this.mInitTranslateY = 0.0f;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        setColor((ColorResult) parcel.readParcelable(ColorResult.class.getClassLoader()));
        setResource(parcel.readString());
        this.mInitScale = parcel.readFloat();
        this.mInitRotate = parcel.readFloat();
        this.mInitTranslateX = parcel.readFloat();
        this.mInitTranslateY = parcel.readFloat();
    }

    public void setAnimation(List<VectorAnimation> list) {
        this.mVectorAnimations = list;
        ArrayList arrayList = new ArrayList();
        Iterator<VectorAnimation> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(buildAnimator(it.next()));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        this.mAnimatorSet = animatorSet;
        animatorSet.playTogether(arrayList);
        initVectorState(list.get(list.size() - 1));
    }

    public void setTransform(float f, float f2, int i, boolean z) {
        this.mScaleMin = f;
        this.mScaleMax = f2;
        this.mRotateInterval = i;
        this.mCanTranslate = z;
    }

    private void updateInitTransform() {
        aeb a2 = aeb.a();
        this.mInitScale = a2.nextFloat(VariableType.Random.RANDOM.getKey(), this.mScaleMin, this.mScaleMax);
        if (this.mRotateInterval > 0) {
            this.mInitRotate = a2.nextInt(VariableType.Random.RANDOM.getKey(), 0, (360 / this.mRotateInterval) - 1) * this.mRotateInterval;
        }
        if (this.mCanTranslate) {
            float maxTranslate = getMaxTranslate();
            float f = -maxTranslate;
            this.mInitTranslateX = a2.nextFloat(VariableType.Random.RANDOM.getKey(), f, maxTranslate);
            this.mInitTranslateY = a2.nextFloat(VariableType.Random.RANDOM.getKey(), f, maxTranslate);
        }
    }

    private float getMaxTranslate() {
        float sin;
        if (this.mInitScale < 1.0f / ((float) Math.sin(0.7853981633974483d))) {
            return 0.0f;
        }
        if (Float.compare(this.mInitRotate, 0.0f) == 0) {
            sin = this.mInitScale / 2.0f;
        } else {
            sin = (this.mInitScale / 2.0f) * ((float) Math.sin(0.7853981633974483d));
        }
        return sin - 0.5f;
    }

    private void initVectorState(VectorAnimation vectorAnimation) {
        this.mScaleValue = 1.0f;
        this.mRotateValue = 0.0f;
        this.mTranslateX = 0.0f;
        this.mTranslateY = 0.0f;
        List<VectorAnimation.b> scaleKeyFrames = vectorAnimation.getScaleKeyFrames();
        if (scaleKeyFrames != null && !scaleKeyFrames.isEmpty()) {
            this.mScaleValue = scaleKeyFrames.get(scaleKeyFrames.size() - 1).c();
        }
        List<VectorAnimation.b> rotateKeyFrame = vectorAnimation.getRotateKeyFrame();
        if (rotateKeyFrame != null && !rotateKeyFrame.isEmpty()) {
            this.mRotateValue = rotateKeyFrame.get(rotateKeyFrame.size() - 1).c();
        }
        List<VectorAnimation.b> translateXKeyFrames = vectorAnimation.getTranslateXKeyFrames();
        if (translateXKeyFrames != null && !translateXKeyFrames.isEmpty()) {
            this.mTranslateX = translateXKeyFrames.get(translateXKeyFrames.size() - 1).c();
        }
        List<VectorAnimation.b> translateYKeyFrames = vectorAnimation.getTranslateYKeyFrames();
        if (translateYKeyFrames == null || translateYKeyFrames.isEmpty()) {
            return;
        }
        this.mTranslateY = translateYKeyFrames.get(translateYKeyFrames.size() - 1).c();
    }

    private void initFirst() {
        VectorAnimation vectorAnimation = this.mVectorAnimations.get(0);
        this.mScaleValue = 1.0f;
        this.mRotateValue = 0.0f;
        this.mTranslateX = 0.0f;
        this.mTranslateY = 0.0f;
        List<VectorAnimation.b> scaleKeyFrames = vectorAnimation.getScaleKeyFrames();
        if (scaleKeyFrames != null && !scaleKeyFrames.isEmpty()) {
            this.mScaleValue = scaleKeyFrames.get(0).c();
        }
        List<VectorAnimation.b> rotateKeyFrame = vectorAnimation.getRotateKeyFrame();
        if (rotateKeyFrame != null && !rotateKeyFrame.isEmpty()) {
            this.mRotateValue = rotateKeyFrame.get(0).c();
        }
        List<VectorAnimation.b> translateXKeyFrames = vectorAnimation.getTranslateXKeyFrames();
        if (translateXKeyFrames != null && !translateXKeyFrames.isEmpty()) {
            this.mTranslateX = translateXKeyFrames.get(0).c();
        }
        List<VectorAnimation.b> translateYKeyFrames = vectorAnimation.getTranslateYKeyFrames();
        if (translateYKeyFrames == null || translateYKeyFrames.isEmpty()) {
            return;
        }
        this.mTranslateY = translateYKeyFrames.get(0).c();
    }

    private Animator buildAnimator(VectorAnimation vectorAnimation) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(vectorAnimation.getInterpolator());
        valueAnimator.setDuration(vectorAnimation.getDuration());
        valueAnimator.setStartDelay(vectorAnimation.getStartDelay());
        valueAnimator.setFloatValues(0.0f, 1.0f);
        valueAnimator.addUpdateListener(new e(vectorAnimation));
        return valueAnimator;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void arrange(Canvas canvas) {
        try {
            initBitmap();
            draw(canvas);
        } catch (abv unused) {
            Log.e(TAG, "init bitmap failed");
        }
    }

    private void draw(Canvas canvas) {
        float centerX = getBound().centerX();
        float centerY = getBound().centerY();
        Matrix matrix = new Matrix();
        float max = Math.max(getBound().height(), getBound().width());
        matrix.postScale(max / this.mBitmap.getWidth(), max / this.mBitmap.getHeight());
        matrix.postTranslate((getBound().width() - max) / 2.0f, (getBound().height() - max) / 2.0f);
        float f = this.mScaleValue;
        matrix.postScale(f, f, centerX, centerY);
        matrix.postRotate(this.mRotateValue, centerX, centerY);
        matrix.postTranslate(this.mTranslateX * getBound().width(), this.mTranslateY * getBound().height());
        canvas.drawBitmap(this.mBitmap, matrix, this.mPaint);
    }

    private void initBitmap() throws abv {
        if (this.mBitmap != null) {
            return;
        }
        this.mBitmap = getResourceBitmap(getTransform());
    }

    public Bitmap toBitmap() {
        Bitmap createBitmap = Bitmap.createBitmap(getBound().width(), getBound().height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Matrix matrix = new Matrix();
        int max = Math.max(getBound().width(), getBound().height());
        matrix.postTranslate((getBound().width() - max) / 2.0f, (getBound().height() - max) / 2.0f);
        try {
            canvas.drawBitmap(getPreviewBitmap(getTransform()), matrix, this.mPaint);
        } catch (abv unused) {
            Log.e(TAG, "Failed to obtain the preview bitmap");
        }
        return createBitmap;
    }

    private aen getTransform() {
        if (this.mInitScale < 1.0f) {
            updateInitTransform();
        }
        return new aen(this.mInitScale, this.mInitRotate, this.mInitTranslateX, this.mInitTranslateY);
    }

    private Bitmap getPreviewBitmap(aen aenVar) throws abv {
        int max = Math.max(getBound().width(), getBound().height());
        VectorDrawableCompat a2 = aeq.a(getResource(), aenVar, max, max);
        aep.a(a2, getColor());
        return aeq.gJ_(a2, max, max);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void releaseBitmap() {
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.mBitmap = null;
        }
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void startAnimation() {
        cancelAnimation();
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null) {
            animatorSet.start();
        }
        Log.i(TAG, String.format(Locale.ENGLISH, "start animation, initScale=%.2f, initRotate=%.2f, initTranslateX=%.2f, intiTranslateY=%.2f", Float.valueOf(this.mInitScale), Float.valueOf(this.mInitRotate), Float.valueOf(this.mInitTranslateX), Float.valueOf(this.mInitTranslateY)));
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void cancelAnimation() {
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet == null || !animatorSet.isRunning()) {
            return;
        }
        this.mAnimatorSet.cancel();
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void goFirstFrame() {
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.mAnimatorSet.cancel();
        }
        initFirst();
        Optional.ofNullable(this.mUpdateListener).ifPresent(new adl());
        Log.i(TAG, "goFirstFrame");
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void setUpdateListener(UpdateListener updateListener) {
        this.mUpdateListener = updateListener;
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void save(File file, Properties properties) throws abv {
        getColor().save(properties);
        properties.put(RESOURCE, getResource());
        properties.put(SCALE, String.valueOf(this.mInitScale));
        properties.put(ROTATE, String.valueOf(this.mInitRotate));
        properties.put(TRANSLATE_X, String.valueOf(this.mInitTranslateX));
        properties.put(TRANSLATE_Y, String.valueOf(this.mInitTranslateY));
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger
    public void load(File file, Properties properties, ColorResult colorResult) throws abv {
        setColor(colorResult);
        setResource(properties.getProperty(RESOURCE));
        try {
            this.mInitScale = Float.parseFloat(properties.getProperty(SCALE, "1"));
            this.mInitRotate = Float.parseFloat(properties.getProperty(ROTATE, "0"));
            this.mInitTranslateX = Float.parseFloat(properties.getProperty(TRANSLATE_X, "0"));
            this.mInitTranslateY = Float.parseFloat(properties.getProperty(TRANSLATE_Y, "0"));
        } catch (NumberFormatException e2) {
            Log.e(TAG, "load vector arranger failed.", e2);
            throw new abv(e2);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(getColor(), i);
        parcel.writeString(getResource());
        parcel.writeFloat(this.mInitScale);
        parcel.writeFloat(this.mInitRotate);
        parcel.writeFloat(this.mInitTranslateX);
        parcel.writeFloat(this.mInitTranslateY);
    }

    class e implements ValueAnimator.AnimatorUpdateListener {
        private final VectorAnimation d;

        private e(VectorAnimation vectorAnimation) {
            this.d = vectorAnimation;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            List<VectorAnimation.b> scaleKeyFrames = this.d.getScaleKeyFrames();
            if (scaleKeyFrames != null && !scaleKeyFrames.isEmpty()) {
                VectorArranger.this.mScaleValue = a(scaleKeyFrames, animatedFraction);
                Log.d(VectorArranger.TAG, "animatedValue:" + animatedFraction + ",scale:" + VectorArranger.this.mScaleValue);
            }
            List<VectorAnimation.b> rotateKeyFrame = this.d.getRotateKeyFrame();
            if (rotateKeyFrame != null && !rotateKeyFrame.isEmpty()) {
                VectorArranger.this.mRotateValue = a(rotateKeyFrame, animatedFraction);
                Log.d(VectorArranger.TAG, "animatedValue:" + animatedFraction + ",rotate:" + VectorArranger.this.mRotateValue);
            }
            List<VectorAnimation.b> translateXKeyFrames = this.d.getTranslateXKeyFrames();
            if (translateXKeyFrames != null && !translateXKeyFrames.isEmpty()) {
                VectorArranger.this.mTranslateX = a(translateXKeyFrames, animatedFraction);
                Log.d(VectorArranger.TAG, "animatedValue:" + animatedFraction + ",translateX:" + VectorArranger.this.mTranslateX);
            }
            List<VectorAnimation.b> translateYKeyFrames = this.d.getTranslateYKeyFrames();
            if (translateYKeyFrames != null && !translateYKeyFrames.isEmpty()) {
                VectorArranger.this.mTranslateY = a(translateYKeyFrames, animatedFraction);
                Log.d(VectorArranger.TAG, "animatedValue:" + animatedFraction + ",translateY:" + VectorArranger.this.mTranslateY);
            }
            Optional.ofNullable(VectorArranger.this.mUpdateListener).ifPresent(new adl());
        }

        private float a(List<VectorAnimation.b> list, float f) {
            int i = 1;
            while (i < list.size() - 1 && list.get(i).a() < f) {
                i++;
            }
            VectorAnimation.b bVar = list.get(i - 1);
            VectorAnimation.b bVar2 = list.get(i);
            return (((f - bVar.a()) / (bVar2.a() - bVar.a())) * (bVar2.c() - bVar.c())) + bVar.c();
        }
    }
}
