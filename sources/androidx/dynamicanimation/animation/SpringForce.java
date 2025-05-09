package androidx.dynamicanimation.animation;

import androidx.dynamicanimation.animation.DynamicAnimation;

/* loaded from: classes2.dex */
public final class SpringForce implements Force {
    public static final float DAMPING_RATIO_HIGH_BOUNCY = 0.2f;
    public static final float DAMPING_RATIO_LOW_BOUNCY = 0.75f;
    public static final float DAMPING_RATIO_MEDIUM_BOUNCY = 0.5f;
    public static final float DAMPING_RATIO_NO_BOUNCY = 1.0f;
    public static final float STIFFNESS_HIGH = 10000.0f;
    public static final float STIFFNESS_LOW = 200.0f;
    public static final float STIFFNESS_MEDIUM = 1500.0f;
    public static final float STIFFNESS_VERY_LOW = 50.0f;
    private static final double UNSET = Double.MAX_VALUE;
    private static final double VELOCITY_THRESHOLD_MULTIPLIER = 62.5d;
    private double mDampedFreq;
    double mDampingRatio;
    private double mFinalPosition;
    private double mGammaMinus;
    private double mGammaPlus;
    private boolean mInitialized;
    private final DynamicAnimation.MassState mMassState;
    double mNaturalFreq;
    private double mValueThreshold;
    private double mVelocityThreshold;

    public SpringForce() {
        this.mNaturalFreq = Math.sqrt(1500.0d);
        this.mDampingRatio = 0.5d;
        this.mInitialized = false;
        this.mFinalPosition = UNSET;
        this.mMassState = new DynamicAnimation.MassState();
    }

    public SpringForce(float f) {
        this.mNaturalFreq = Math.sqrt(1500.0d);
        this.mDampingRatio = 0.5d;
        this.mInitialized = false;
        this.mFinalPosition = UNSET;
        this.mMassState = new DynamicAnimation.MassState();
        this.mFinalPosition = f;
    }

    public SpringForce setStiffness(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Spring stiffness constant must be positive.");
        }
        this.mNaturalFreq = Math.sqrt(f);
        this.mInitialized = false;
        return this;
    }

    public float getStiffness() {
        double d = this.mNaturalFreq;
        return (float) (d * d);
    }

    public SpringForce setDampingRatio(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("Damping ratio must be non-negative");
        }
        this.mDampingRatio = f;
        this.mInitialized = false;
        return this;
    }

    public float getDampingRatio() {
        return (float) this.mDampingRatio;
    }

    public SpringForce setFinalPosition(float f) {
        this.mFinalPosition = f;
        return this;
    }

    public float getFinalPosition() {
        return (float) this.mFinalPosition;
    }

    @Override // androidx.dynamicanimation.animation.Force
    public float getAcceleration(float f, float f2) {
        float finalPosition = getFinalPosition();
        double d = this.mNaturalFreq;
        return (float) (((-(d * d)) * (f - finalPosition)) - (((d * 2.0d) * this.mDampingRatio) * f2));
    }

    @Override // androidx.dynamicanimation.animation.Force
    public boolean isAtEquilibrium(float f, float f2) {
        return ((double) Math.abs(f2)) < this.mVelocityThreshold && ((double) Math.abs(f - getFinalPosition())) < this.mValueThreshold;
    }

    private void init() {
        if (this.mInitialized) {
            return;
        }
        if (this.mFinalPosition == UNSET) {
            throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
        }
        double d = this.mDampingRatio;
        if (d > 1.0d) {
            double d2 = this.mNaturalFreq;
            this.mGammaPlus = ((-d) * d2) + (d2 * Math.sqrt((d * d) - 1.0d));
            double d3 = this.mDampingRatio;
            double d4 = this.mNaturalFreq;
            this.mGammaMinus = ((-d3) * d4) - (d4 * Math.sqrt((d3 * d3) - 1.0d));
        } else if (d >= 0.0d && d < 1.0d) {
            this.mDampedFreq = this.mNaturalFreq * Math.sqrt(1.0d - (d * d));
        }
        this.mInitialized = true;
    }

    DynamicAnimation.MassState updateValues(double d, double d2, long j) {
        double pow;
        double cos;
        init();
        double d3 = j / 1000.0d;
        double d4 = d - this.mFinalPosition;
        double d5 = this.mDampingRatio;
        if (d5 > 1.0d) {
            double d6 = this.mGammaMinus;
            double d7 = ((d6 * d4) - d2) / (d6 - this.mGammaPlus);
            double d8 = d4 - d7;
            pow = (Math.pow(2.718281828459045d, d6 * d3) * d8) + (Math.pow(2.718281828459045d, this.mGammaPlus * d3) * d7);
            double d9 = this.mGammaMinus;
            double pow2 = Math.pow(2.718281828459045d, d9 * d3);
            double d10 = this.mGammaPlus;
            cos = (d8 * d9 * pow2) + (d7 * d10 * Math.pow(2.718281828459045d, d10 * d3));
        } else if (d5 == 1.0d) {
            double d11 = this.mNaturalFreq;
            double d12 = d2 + (d11 * d4);
            double d13 = d4 + (d12 * d3);
            pow = Math.pow(2.718281828459045d, (-d11) * d3) * d13;
            double pow3 = Math.pow(2.718281828459045d, (-this.mNaturalFreq) * d3);
            double d14 = -this.mNaturalFreq;
            cos = (d13 * pow3 * d14) + (d12 * Math.pow(2.718281828459045d, d3 * d14));
        } else {
            double d15 = 1.0d / this.mDampedFreq;
            double d16 = this.mNaturalFreq;
            double d17 = d15 * ((d5 * d16 * d4) + d2);
            pow = Math.pow(2.718281828459045d, (-d5) * d16 * d3) * ((Math.cos(this.mDampedFreq * d3) * d4) + (Math.sin(this.mDampedFreq * d3) * d17));
            double d18 = this.mNaturalFreq;
            double d19 = -d18;
            double d20 = this.mDampingRatio;
            double pow4 = Math.pow(2.718281828459045d, (-d20) * d18 * d3);
            double d21 = this.mDampedFreq;
            double d22 = -d21;
            double sin = Math.sin(d21 * d3);
            double d23 = this.mDampedFreq;
            cos = (d19 * pow * d20) + (((d22 * d4 * sin) + (d17 * d23 * Math.cos(d23 * d3))) * pow4);
        }
        this.mMassState.mValue = (float) (pow + this.mFinalPosition);
        this.mMassState.mVelocity = (float) cos;
        return this.mMassState;
    }

    void setValueThreshold(double d) {
        double abs = Math.abs(d);
        this.mValueThreshold = abs;
        this.mVelocityThreshold = abs * VELOCITY_THRESHOLD_MULTIPLIER;
    }
}
