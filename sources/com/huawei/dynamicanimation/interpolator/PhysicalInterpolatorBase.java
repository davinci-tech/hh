package com.huawei.dynamicanimation.interpolator;

import android.view.animation.Interpolator;
import com.huawei.dynamicanimation.DynamicAnimation;
import com.huawei.dynamicanimation.FloatPropertyCompat;
import com.huawei.dynamicanimation.PhysicalModelBase;
import com.huawei.dynamicanimation.interpolator.PhysicalInterpolatorBase;
import defpackage.bnj;
import java.math.BigDecimal;

/* loaded from: classes3.dex */
public abstract class PhysicalInterpolatorBase<T extends PhysicalInterpolatorBase<T>> implements Interpolator {
    private static final float CURRENT_TIME = -1.0f;
    private static final long DEFAULT_DURATION = 300;
    public static final float MIN_VISIBLE_CHANGE_PIXELS = 1.0f;
    private static final float ONE_SECOND = 1000.0f;
    protected static final float THRESHOLD_MULTIPLIER = 0.75f;
    private InterpolatorDataUpdateListener mDataUpdateListener;
    private long mDuration;
    private float mMinVisibleChange;
    private PhysicalModelBase mModel;
    final FloatPropertyCompat mProperty;
    public static final float MIN_VISIBLE_CHANGE_ROTATION_DEGREES = new BigDecimal(1.0d).divide(new BigDecimal("10")).floatValue();
    public static final float MIN_VISIBLE_CHANGE_ALPHA = new BigDecimal(1.0d).divide(new BigDecimal("256")).floatValue();
    public static final float MIN_VISIBLE_CHANGE_SCALE = new BigDecimal(1.0d).divide(new BigDecimal("500")).floatValue();
    public static final float MIN_VISIBLE_CHANGE_STANDARD_INTERPOLATE = new BigDecimal(1.0d).divide(new BigDecimal("1000")).floatValue();

    public interface InterpolatorDataUpdateListener {
        void onDataUpdate(float f, float f2, float f3, float f4);
    }

    protected abstract T setValueThreshold(float f);

    public PhysicalInterpolatorBase(final bnj bnjVar, PhysicalModelBase physicalModelBase) {
        this.mDuration = 300L;
        this.mModel = physicalModelBase;
        this.mProperty = new FloatPropertyCompat("FloatValueHolder") { // from class: com.huawei.dynamicanimation.interpolator.PhysicalInterpolatorBase.4
            @Override // com.huawei.dynamicanimation.FloatPropertyCompat
            public float getValue(Object obj) {
                return bnjVar.getValue();
            }

            @Override // com.huawei.dynamicanimation.FloatPropertyCompat
            public void setValue(Object obj, float f) {
                bnjVar.setValue(f);
            }
        };
        this.mMinVisibleChange = MIN_VISIBLE_CHANGE_STANDARD_INTERPOLATE;
    }

    public <K> PhysicalInterpolatorBase(FloatPropertyCompat<K> floatPropertyCompat, PhysicalModelBase physicalModelBase) {
        this.mDuration = 300L;
        this.mModel = physicalModelBase;
        this.mProperty = floatPropertyCompat;
        if (floatPropertyCompat == DynamicAnimation.ROTATION || floatPropertyCompat == DynamicAnimation.ROTATION_X || floatPropertyCompat == DynamicAnimation.ROTATION_Y) {
            this.mMinVisibleChange = MIN_VISIBLE_CHANGE_ROTATION_DEGREES;
            return;
        }
        if (floatPropertyCompat == DynamicAnimation.ALPHA) {
            this.mMinVisibleChange = MIN_VISIBLE_CHANGE_ALPHA;
        } else if (floatPropertyCompat == DynamicAnimation.SCALE_X || floatPropertyCompat == DynamicAnimation.SCALE_Y) {
            this.mMinVisibleChange = MIN_VISIBLE_CHANGE_SCALE;
        } else {
            this.mMinVisibleChange = 1.0f;
        }
    }

    public T setModel(PhysicalModelBase physicalModelBase) {
        this.mModel = physicalModelBase;
        return this;
    }

    /* JADX WARN: Incorrect return type in method signature: <T:Lcom/huawei/dynamicanimation/PhysicalModelBase;>()TT; */
    public final PhysicalModelBase getModel() {
        return this.mModel;
    }

    public T setMinimumVisibleChange(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Minimum visible change must be positive.");
        }
        this.mMinVisibleChange = f;
        setValueThreshold(f);
        return this;
    }

    protected final float getValueThreshold() {
        return this.mMinVisibleChange * 0.75f;
    }

    public T setDuration(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Animators cannot have negative duration: " + j);
        }
        this.mDuration = j;
        return this;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        float duration = (f * getDuration()) / 1000.0f;
        float position = getModel().getPosition(duration);
        if (this.mDataUpdateListener != null) {
            this.mDataUpdateListener.onDataUpdate(duration, position, getModel().getVelocity(duration), getModel().getAcceleration(duration));
        }
        if (getDeltaX() == 0.0f) {
            return 0.0f;
        }
        return position / getDeltaX();
    }

    protected float getDeltaX() {
        return Math.abs(getModel().getEndPosition() - getModel().getStartPosition());
    }

    public float getInterpolation2(float f) {
        if (this.mDuration < 0 || f < this.mModel.getStartTime() || f > this.mModel.getStartTime() + this.mDuration || Float.compare(getDuration(), 0.0f) == 0 || getDuration() == -1.0f) {
            return 0.0f;
        }
        float startTime = (((f - this.mModel.getStartTime()) / this.mDuration) * getDuration()) / 1000.0f;
        float position = getModel().getPosition(startTime);
        this.mDataUpdateListener.onDataUpdate(startTime, position, getModel().getVelocity(startTime), getModel().getAcceleration(startTime));
        return position / Math.abs(getModel().getEndPosition());
    }

    public float getDuration() {
        return getModel().getEstimatedDuration();
    }

    public float getEndOffset() {
        return getModel().getEndPosition();
    }

    public T setDataUpdateListener(InterpolatorDataUpdateListener interpolatorDataUpdateListener) {
        this.mDataUpdateListener = interpolatorDataUpdateListener;
        return this;
    }
}
