package androidx.constraintlayout.core.motion.utils;

/* loaded from: classes8.dex */
public class SpringStopEngine implements StopEngine {
    private static final double UNSET = Double.MAX_VALUE;
    private float mLastTime;
    private double mLastVelocity;
    private float mMass;
    private float mPos;
    private double mStiffness;
    private float mStopThreshold;
    private double mTargetPos;
    private float mV;
    double mDamping = 0.5d;
    private boolean mInitialized = false;
    private int mBoundaryMode = 0;

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public String debug(String str, float f) {
        return null;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getVelocity() {
        return 0.0f;
    }

    void log(String str) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        String str2 = ".(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName() + "() ";
        System.out.println(str2 + str);
    }

    public void springConfig(float f, float f2, float f3, float f4, float f5, float f6, float f7, int i) {
        this.mTargetPos = f2;
        this.mDamping = f6;
        this.mInitialized = false;
        this.mPos = f;
        this.mLastVelocity = f3;
        this.mStiffness = f5;
        this.mMass = f4;
        this.mStopThreshold = f7;
        this.mBoundaryMode = i;
        this.mLastTime = 0.0f;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getVelocity(float f) {
        return this.mV;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public float getInterpolation(float f) {
        compute(f - this.mLastTime);
        this.mLastTime = f;
        return this.mPos;
    }

    public float getAcceleration() {
        return ((float) (((-this.mStiffness) * (this.mPos - this.mTargetPos)) - (this.mDamping * this.mV))) / this.mMass;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public boolean isStopped() {
        double d = this.mPos - this.mTargetPos;
        double d2 = this.mStiffness;
        double d3 = this.mV;
        return Math.sqrt((((d3 * d3) * ((double) this.mMass)) + ((d2 * d) * d)) / d2) <= ((double) this.mStopThreshold);
    }

    private void compute(double d) {
        double d2 = this.mStiffness;
        double d3 = this.mDamping;
        int sqrt = (int) ((9.0d / ((Math.sqrt(d2 / this.mMass) * d) * 4.0d)) + 1.0d);
        double d4 = d / sqrt;
        int i = 0;
        while (i < sqrt) {
            double d5 = this.mPos;
            double d6 = this.mTargetPos;
            double d7 = -d2;
            double d8 = d2;
            double d9 = this.mV;
            double d10 = (d7 * (d5 - d6)) - (d9 * d3);
            double d11 = d3;
            double d12 = this.mMass;
            double d13 = (((d10 / d12) * d4) / 2.0d) + d9;
            double d14 = ((((-((d5 + ((d4 * d13) / 2.0d)) - d6)) * d8) - (d13 * d11)) / d12) * d4;
            double d15 = d14 / 2.0d;
            float f = (float) (d14 + d9);
            this.mV = f;
            float f2 = (float) (d5 + ((d9 + d15) * d4));
            this.mPos = f2;
            int i2 = this.mBoundaryMode;
            if (i2 > 0) {
                if (f2 < 0.0f && (i2 & 1) == 1) {
                    this.mPos = -f2;
                    this.mV = -f;
                }
                float f3 = this.mPos;
                if (f3 > 1.0f && (i2 & 2) == 2) {
                    this.mPos = 2.0f - f3;
                    this.mV = -this.mV;
                }
            }
            i++;
            d2 = d8;
            d3 = d11;
        }
    }
}
