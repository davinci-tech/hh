package com.huawei.dynamicanimation;

import android.os.SystemClock;
import java.math.BigDecimal;

/* loaded from: classes3.dex */
public class SpringModelBase extends PhysicalModelBase {
    public static final float c = new BigDecimal("1").divide(new BigDecimal("1000")).floatValue();

    /* renamed from: a, reason: collision with root package name */
    private float f1964a;
    private float b;
    private Solution d;
    private float e;

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(float f, float f2, float f3) {
        return f > f2 - f3;
    }

    private boolean b(float f, float f2, float f3) {
        return f > f2 - f3 && f < f2 + f3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(float f, float f2, float f3) {
        return f < f2 - f3;
    }

    public SpringModelBase(float f, float f2, float f3) {
        this.f1964a = 1.0f;
        this.e = 800.0f;
        this.b = 15.0f;
        super.setValueThreshold(f3);
        this.f1964a = 1.0f;
        this.e = Math.min(Math.max(1.0f, f), 999.0f);
        this.b = Math.min(Math.max(1.0f, f2), 99.0f);
        this.d = null;
        this.mStartPosition = 0.0f;
        this.mEndPosition = 0.0f;
        this.mStartVelocity = 0.0f;
        this.mStartTime = 0L;
    }

    abstract class Solution {
        private float[] mPositionDist = new float[17];
        protected float mPosition = 0.0f;
        protected float mVelocity = 0.0f;
        protected float mAcceleration = 0.0f;
        protected float mDuration = 0.0f;

        private float getIterate(float f, float f2) {
            if (f <= 999.0f) {
                return f2;
            }
            return -1.0f;
        }

        protected abstract void doEstimateDuration();

        protected abstract float estimateDuration();

        protected abstract float getAcceleration(float f);

        protected abstract float getFirstExtremumX();

        protected abstract float getMaxAbsX();

        protected abstract float getPosition(float f);

        protected abstract float getVelocity(float f);

        protected Solution() {
        }

        protected void estimateDuration(float f, float f2) {
            int i = 0;
            if (f2 >= 0.0f && !Float.isInfinite(f2) && !Float.isNaN(f2)) {
                float position = getPosition(f2);
                int i2 = 0;
                while (SpringModelBase.this.c(Math.abs(position), SpringModelBase.this.mValueThreshold, 0.0f)) {
                    i2++;
                    if (i2 > 999.0f) {
                        break;
                    }
                    f2 = (f2 + f) / 2.0f;
                    position = getPosition(f2);
                }
                if (i2 > 999.0f) {
                    this.mDuration = f2;
                    return;
                }
                f = f2;
            }
            float position2 = getPosition(f);
            float velocity = getVelocity(f);
            while (SpringModelBase.this.a(Math.abs(position2), SpringModelBase.this.mValueThreshold, 0.0f)) {
                i++;
                if (i > 999.0f) {
                    break;
                }
                f -= position2 / velocity;
                if (f < 0.0f || Float.isNaN(f) || Float.isInfinite(f)) {
                    this.mDuration = 0.0f;
                    return;
                } else {
                    position2 = getPosition(f);
                    velocity = getVelocity(f);
                }
            }
            if (i > 999.0f) {
                this.mDuration = -1.0f;
            } else {
                this.mDuration = f;
            }
        }

        private float getStartTimeForX(float f, float f2, float f3) {
            float f4 = (f3 - f2) / 16.0f;
            boolean z = getVelocity(new BigDecimal((double) (f3 + f2)).divide(new BigDecimal("2")).floatValue()) > 0.0f;
            for (int i = 1; i < 17; i++) {
                float[] fArr = this.mPositionDist;
                float f5 = fArr[i];
                int i2 = i - 1;
                float f6 = fArr[i2];
                float f7 = f5 - f6;
                if (z && f5 >= f) {
                    return f7 == 0.0f ? f2 + (i2 * f4) : f2 + ((i2 + ((f - f6) / f7)) * f4);
                }
                if (!z && f5 <= f) {
                    return f7 == 0.0f ? f2 + (i2 * f4) : f2 + ((i - ((f5 - f) / f7)) * f4);
                }
            }
            return f3;
        }

        protected float doIterate(float f, float f2) {
            float f3 = (f2 - f) / 16.0f;
            float f4 = SpringModelBase.this.mValueThreshold;
            for (int i = 0; i < 17; i++) {
                this.mPositionDist[i] = getPosition((i * f3) + f);
            }
            boolean z = true;
            int i2 = 1;
            while (true) {
                if (i2 >= 17) {
                    z = false;
                    break;
                }
                int i3 = i2 - 1;
                if ((this.mPositionDist[i3] - SpringModelBase.this.mValueThreshold) * (this.mPositionDist[i2] - SpringModelBase.this.mValueThreshold) < 0.0f) {
                    f4 = SpringModelBase.this.mValueThreshold;
                    break;
                }
                if ((this.mPositionDist[i3] + SpringModelBase.this.mValueThreshold) * (this.mPositionDist[i2] + SpringModelBase.this.mValueThreshold) < 0.0f) {
                    f4 = -SpringModelBase.this.mValueThreshold;
                    break;
                }
                i2++;
            }
            if (!z) {
                return f;
            }
            float startTimeForX = getStartTimeForX(f4, f, f2);
            while (Math.abs(getPosition(startTimeForX)) < SpringModelBase.this.mValueThreshold && f2 - startTimeForX >= 0.0625f) {
                float f5 = (startTimeForX - f) / 16.0f;
                for (int i4 = 0; i4 < 17; i4++) {
                    this.mPositionDist[i4] = getPosition((i4 * f5) + f);
                }
                float f6 = startTimeForX;
                startTimeForX = getStartTimeForX(f4, f, startTimeForX);
                f2 = f6;
            }
            float position = getPosition(startTimeForX);
            float velocity = getVelocity(startTimeForX);
            float f7 = 0.0f;
            while (SpringModelBase.this.a(Math.abs(position), SpringModelBase.this.mValueThreshold, 0.0f)) {
                float f8 = 1.0f + f7;
                if (f7 >= 999.0f || velocity == 0.0f) {
                    f7 = f8;
                    break;
                }
                startTimeForX -= position / velocity;
                position = getPosition(startTimeForX);
                velocity = getVelocity(startTimeForX);
                f7 = f8;
            }
            return getIterate(f7, startTimeForX);
        }
    }

    class d extends Solution {
        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        protected void doEstimateDuration() {
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        protected float estimateDuration() {
            return 0.0f;
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        protected float getFirstExtremumX() {
            return 0.0f;
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        protected float getMaxAbsX() {
            return 0.0f;
        }

        private d() {
            super();
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        public float getPosition(float f) {
            return this.mPosition;
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        public float getVelocity(float f) {
            return this.mVelocity;
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        public float getAcceleration(float f) {
            return this.mAcceleration;
        }
    }

    class a extends Solution {

        /* renamed from: a, reason: collision with root package name */
        float f1965a;
        float c;
        float d;

        a(float f, float f2, float f3) {
            super();
            this.f1965a = f;
            this.c = f2;
            this.d = f3;
            doEstimateDuration();
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        public float getPosition(float f) {
            this.mPosition = (float) ((this.f1965a + (this.c * f)) * Math.pow(2.718281828459045d, this.d * f));
            return this.mPosition;
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        public float getVelocity(float f) {
            float pow = (float) Math.pow(2.718281828459045d, this.d * f);
            float f2 = this.d;
            float f3 = this.f1965a;
            float f4 = this.c;
            this.mVelocity = (f2 * (f3 + (f * f4)) * pow) + (f4 * pow);
            return this.mVelocity;
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        public float getAcceleration(float f) {
            float pow = (float) Math.pow(2.718281828459045d, this.d * f);
            float f2 = this.d;
            float f3 = this.f1965a;
            float f4 = this.c;
            this.mAcceleration = (f2 * f2 * (f3 + (f * f4)) * pow) + (f4 * 2.0f * f2 * pow);
            return this.mAcceleration;
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        protected final void doEstimateDuration() {
            float f = this.c;
            if (f != 0.0f) {
                float f2 = this.d;
                if (f2 == 0.0f) {
                    return;
                }
                estimateDuration(0.0f, (-(((2.0f * f) / f2) + this.f1965a)) / f);
            }
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        protected float estimateDuration() {
            return this.mDuration;
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        protected float getMaxAbsX() {
            return Math.abs(getFirstExtremumX());
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        protected float getFirstExtremumX() {
            float f = this.c;
            float f2 = 0.0f;
            if (f != 0.0f) {
                float f3 = this.d;
                if (f3 != 0.0f) {
                    float f4 = (-((f / f3) + this.f1965a)) / f;
                    if (f4 >= 0.0f && !Float.isInfinite(f4)) {
                        f2 = f4;
                    }
                    return getPosition(f2);
                }
            }
            return 0.0f;
        }
    }

    class b extends Solution {

        /* renamed from: a, reason: collision with root package name */
        float f1966a;
        float c;
        float d;
        float e;

        b(float f, float f2, float f3, float f4) {
            super();
            this.f1966a = f;
            this.c = f2;
            this.d = f3;
            this.e = f4;
            doEstimateDuration();
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        public float getPosition(float f) {
            this.mPosition = (this.f1966a * ((float) Math.pow(2.718281828459045d, this.d * f))) + (this.c * ((float) Math.pow(2.718281828459045d, this.e * f)));
            return this.mPosition;
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        public float getVelocity(float f) {
            float f2 = this.f1966a;
            float f3 = this.d;
            this.mVelocity = (f2 * f3 * ((float) Math.pow(2.718281828459045d, f3 * f))) + (this.c * this.e * ((float) Math.pow(2.718281828459045d, r6 * f)));
            return this.mVelocity;
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        public float getAcceleration(float f) {
            float f2 = this.f1966a;
            float f3 = this.d;
            float pow = (float) Math.pow(2.718281828459045d, f3 * f);
            float f4 = this.c;
            float f5 = this.e;
            this.mAcceleration = (f2 * f3 * f3 * pow) + (f4 * f5 * f5 * ((float) Math.pow(2.718281828459045d, f * f5)));
            return this.mAcceleration;
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        protected final void doEstimateDuration() {
            if (Math.abs(this.d - this.e) < 1.0E-6f) {
                return;
            }
            float f = this.f1966a;
            float f2 = this.d;
            float log = (float) Math.log(Math.abs(f * f2 * f2));
            float f3 = -this.c;
            float f4 = this.e;
            estimateDuration(0.0f, (log - ((float) Math.log(Math.abs((f3 * f4) * f4)))) / (this.e - this.d));
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        protected float estimateDuration() {
            return this.mDuration;
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        protected float getMaxAbsX() {
            return Math.abs(getFirstExtremumX());
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        protected float getFirstExtremumX() {
            float f = 0.0f;
            if (Math.abs(this.d - this.e) < 1.0E-6f) {
                return 0.0f;
            }
            float log = (((float) Math.log(Math.abs(this.f1966a * this.d))) - ((float) Math.log(Math.abs((-this.c) * this.e)))) / (this.e - this.d);
            if (log >= 0.0f && !Float.isInfinite(log)) {
                f = log;
            }
            return getPosition(f);
        }
    }

    class e extends Solution {

        /* renamed from: a, reason: collision with root package name */
        float f1967a;
        float c;
        float d;
        float e;

        e(float f, float f2, float f3, float f4) {
            super();
            this.f1967a = f;
            this.e = f2;
            this.c = f3;
            this.d = f4;
            doEstimateDuration();
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        public float getPosition(float f) {
            this.mPosition = ((float) Math.pow(2.718281828459045d, this.d * f)) * ((this.f1967a * ((float) Math.cos(this.c * f))) + (this.e * ((float) Math.sin(this.c * f))));
            return this.mPosition;
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        public float getVelocity(float f) {
            float pow = (float) Math.pow(2.718281828459045d, this.d * f);
            float cos = (float) Math.cos(this.c * f);
            float sin = (float) Math.sin(this.c * f);
            float f2 = this.e;
            float f3 = this.c;
            float f4 = this.f1967a;
            this.mVelocity = ((((f2 * f3) * cos) - ((f3 * f4) * sin)) * pow) + (this.d * pow * ((f2 * sin) + (f4 * cos)));
            return this.mVelocity;
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        public float getAcceleration(float f) {
            float pow = (float) Math.pow(2.718281828459045d, this.d * f);
            float cos = (float) Math.cos(this.c * f);
            float sin = (float) Math.sin(this.c * f);
            float f2 = this.d;
            float f3 = this.e;
            float f4 = this.c;
            float f5 = this.f1967a;
            float f6 = f5 * f4;
            float f7 = f2 * pow * (((f3 * f4) * cos) - (f6 * sin));
            this.mAcceleration = ((((((-f3) * f4) * f4) * sin) - ((f6 * f4) * cos)) * pow) + f7 + (f2 * f2 * pow * ((f3 * sin) + (f5 * cos))) + f7;
            return this.mAcceleration;
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        protected final void doEstimateDuration() {
            float sqrt = (float) Math.sqrt((SpringModelBase.this.b * SpringModelBase.this.b) / ((SpringModelBase.this.f1964a * 4.0f) * SpringModelBase.this.e));
            float sqrt2 = ((float) Math.sqrt(1.0f - (sqrt * sqrt))) * ((float) Math.sqrt(SpringModelBase.this.e / SpringModelBase.this.f1964a));
            float atan = (float) Math.atan(this.e / this.f1967a);
            if (Float.isNaN(atan)) {
                this.mDuration = 0.0f;
                return;
            }
            float acos = ((((float) Math.acos(0.0d)) + atan) % 3.1415927f) / this.c;
            float velocity = getVelocity(acos);
            float acos2 = (((((float) Math.acos(0.0d)) + ((float) Math.atan(sqrt2 / (sqrt * r1)))) + atan) % 3.1415927f) / sqrt2;
            float f = (6.2831855f / sqrt2) / 2.0f;
            int i = 0;
            float f2 = 0.0f;
            while (true) {
                if (!SpringModelBase.this.a(Math.abs(velocity), SpringModelBase.this.mVelocityThreshold, 0.0f)) {
                    break;
                }
                int i2 = i + 1;
                if (i >= 999.0f) {
                    i = i2;
                    break;
                }
                acos += f;
                velocity = getVelocity(acos);
                f2 += f;
                acos2 += f;
                i = i2;
            }
            float f3 = -1.0f;
            if (i >= 999.0f) {
                this.mDuration = -1.0f;
                return;
            }
            if ((f2 <= acos2 && acos2 < acos) || Math.abs(f2 - acos) < 1.0E-6f) {
                f3 = doIterate(acos2, f + acos2);
            } else if (f2 < acos && acos < acos2) {
                f3 = doIterate(Math.max(0.0f, acos2 - f), acos2);
            }
            this.mDuration = f3;
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        protected float estimateDuration() {
            return this.mDuration;
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        protected float getMaxAbsX() {
            float sqrt = (float) Math.sqrt((SpringModelBase.this.b * SpringModelBase.this.b) / ((SpringModelBase.this.f1964a * 4.0f) * SpringModelBase.this.e));
            float sqrt2 = (float) (((float) Math.sqrt(SpringModelBase.this.e / SpringModelBase.this.f1964a)) * Math.sqrt(1.0f - (sqrt * sqrt)));
            float acos = (float) (((Math.acos(0.0d) + ((float) Math.atan(sqrt2 / (sqrt * r1)))) + ((float) Math.atan(this.e / this.f1967a))) % 3.141592653589793d);
            float abs = Math.abs(getPosition(acos / sqrt2));
            int i = 0;
            do {
                float f = (float) (acos + ((i * 3.141592653589793d) / sqrt2));
                float abs2 = Math.abs(getPosition(f));
                if (abs < abs2) {
                    abs = abs2;
                }
                if (f >= estimateDuration()) {
                    break;
                }
                i++;
            } while (i < 999.0f);
            if (i >= 999.0f) {
                return -1.0f;
            }
            return abs;
        }

        @Override // com.huawei.dynamicanimation.SpringModelBase.Solution
        protected float getFirstExtremumX() {
            if (SpringModelBase.this.b == 0.0f || SpringModelBase.this.f1964a == 0.0f || SpringModelBase.this.e == 0.0f || this.f1967a == 0.0f) {
                return 0.0f;
            }
            float sqrt = (float) Math.sqrt((SpringModelBase.this.b * SpringModelBase.this.b) / ((SpringModelBase.this.f1964a * 4.0f) * SpringModelBase.this.e));
            float sqrt2 = (float) (((float) Math.sqrt(SpringModelBase.this.e / SpringModelBase.this.f1964a)) * Math.sqrt(1.0f - (sqrt * sqrt)));
            float atan = (float) Math.atan(this.e / this.f1967a);
            float atan2 = (float) Math.atan(sqrt2 / (sqrt * r2));
            if (sqrt2 == 0.0f) {
                return 0.0f;
            }
            return getPosition((float) ((((Math.acos(0.0d) + atan2) + atan) % 3.141592653589793d) / sqrt2));
        }
    }

    private boolean a(float f, float f2) {
        return b(f, 0.0f, f2);
    }

    public Solution e(float f, float f2) {
        float f3 = this.b;
        float f4 = this.f1964a;
        float f5 = f3 * f3;
        float f6 = 4.0f * f4 * this.e;
        int compare = Float.compare(f5, f6);
        if (compare == 0) {
            float f7 = (-f3) / (f4 * 2.0f);
            return new a(f, f2 - (f7 * f), f7);
        }
        if (compare > 0) {
            double d2 = -f3;
            double d3 = f5 - f6;
            double d4 = f4 * 2.0f;
            float sqrt = (float) ((d2 - Math.sqrt(d3)) / d4);
            float sqrt2 = (float) ((d2 + Math.sqrt(d3)) / d4);
            float f8 = (f2 - (sqrt * f)) / (sqrt2 - sqrt);
            return new b(f - f8, f8, sqrt, sqrt2);
        }
        float f9 = f4 * 2.0f;
        float sqrt3 = (float) (Math.sqrt(f6 - f5) / f9);
        float f10 = (-f3) / f9;
        return new e(f, (f2 - (f10 * f)) / sqrt3, sqrt3, f10);
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public float getPosition(float f) {
        if (f < 0.0f) {
            f = (float) ((SystemClock.elapsedRealtime() - this.mStartTime) / 1000.0d);
        }
        if (this.d != null) {
            return this.mEndPosition + this.d.getPosition(f);
        }
        return 0.0f;
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public float getPosition() {
        return getPosition(-1.0f);
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public float getVelocity(float f) {
        if (f < 0.0f) {
            f = (float) ((SystemClock.elapsedRealtime() - this.mStartTime) / 1000.0d);
        }
        Solution solution = this.d;
        if (solution != null) {
            return solution.getVelocity(f);
        }
        return 0.0f;
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public float getVelocity() {
        return getVelocity(-1.0f);
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public float getAcceleration(float f) {
        if (f < 0.0f) {
            f = (float) ((SystemClock.elapsedRealtime() - this.mStartTime) / 1000.0d);
        }
        Solution solution = this.d;
        if (solution != null) {
            return solution.getAcceleration(f);
        }
        return 0.0f;
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public float getAcceleration() {
        return getAcceleration(-1.0f);
    }

    public SpringModelBase c(float f, float f2, long j) {
        float min = Math.min(99999.0f, Math.max(-99999.0f, f));
        float min2 = Math.min(99999.0f, Math.max(-99999.0f, f2));
        if (j <= 0) {
            j = SystemClock.elapsedRealtime();
        }
        if (min == this.mEndPosition && a(min2, this.mValueThreshold)) {
            return this;
        }
        float f3 = this.mEndPosition;
        if (this.d != null) {
            if (a(min2, this.mValueThreshold)) {
                min2 = this.d.getVelocity((j - this.mStartTime) / 1000.0f);
            }
            float position = this.d.getPosition((j - this.mStartTime) / 1000.0f);
            if (a(min2, this.mValueThreshold)) {
                min2 = 0.0f;
            }
            if (a(position, this.mValueThreshold)) {
                position = 0.0f;
            }
            f3 = position + this.mEndPosition;
            if (a(f3 - min, this.mValueThreshold) && a(min2, this.mValueThreshold)) {
                return this;
            }
        }
        this.mEndPosition = min;
        this.mStartPosition = f3;
        this.mStartVelocity = min2;
        this.d = e(f3 - this.mEndPosition, min2);
        this.mStartTime = j;
        return this;
    }

    public SpringModelBase b(float f, float f2) {
        if (f == this.mEndPosition && a(f2, this.mValueThreshold)) {
            return this;
        }
        this.mStartTime = SystemClock.elapsedRealtime();
        this.mStartPosition = 0.0f;
        this.mEndPosition = f;
        this.mStartVelocity = f2;
        this.d = e(this.mStartPosition - this.mEndPosition, f2);
        return this;
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public boolean isAtEquilibrium(float f) {
        if (f < 0.0f) {
            f = SystemClock.elapsedRealtime() - (getStartTime() / 1000.0f);
        }
        return b(getPosition(f), this.mEndPosition, this.mValueThreshold) && a(getVelocity(f), this.mValueThreshold);
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public boolean isAtEquilibrium() {
        return isAtEquilibrium(-1.0f);
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public boolean isAtEquilibrium(float f, float f2) {
        return Math.abs(f2) < this.mVelocityThreshold && Math.abs(f - this.mEndPosition) < this.mValueThreshold;
    }

    public SpringModelBase e(float f) {
        float min = Math.min(0.0f, Math.max(0.0f, f));
        this.mStartTime = SystemClock.elapsedRealtime();
        this.mStartPosition = 0.0f;
        this.mEndPosition = min;
        this.mStartVelocity = 0.0f;
        this.d = new d();
        return this;
    }

    public SpringModelBase d(float f, float f2, float f3, float f4) {
        super.setValueThreshold(f4);
        this.f1964a = Math.min(Math.max(1.0f, f), 1.0f);
        this.e = Math.min(Math.max(1.0f, f2), 999.0f);
        this.b = Math.min(Math.max(1.0f, f3), 99.0f);
        this.mStartPosition = getPosition(-1.0f);
        this.mStartVelocity = getVelocity(-1.0f);
        this.d = e(this.mStartPosition - this.mEndPosition, this.mStartVelocity);
        this.mStartTime = SystemClock.elapsedRealtime();
        return this;
    }

    public SpringModelBase b(float f) {
        return d(this.f1964a, f, this.b, this.mValueThreshold);
    }

    public SpringModelBase a(float f) {
        return d(this.f1964a, this.e, f, this.mValueThreshold);
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public SpringModelBase setValueThreshold(float f) {
        return d(this.f1964a, this.e, this.b, f);
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public float getEstimatedDuration() {
        float estimateDuration = this.d.estimateDuration();
        if (Float.compare(estimateDuration, -1.0f) == 0) {
            return 500.0f;
        }
        return estimateDuration * 1000.0f;
    }

    @Override // com.huawei.dynamicanimation.PhysicalModelBase, com.huawei.dynamicanimation.PhysicalModel
    public float getMaxAbsX() {
        Solution solution = this.d;
        if (solution != null) {
            return solution.getMaxAbsX();
        }
        return 0.0f;
    }

    public float a() {
        Solution solution = this.d;
        if (solution != null) {
            return solution.getFirstExtremumX();
        }
        return 0.0f;
    }
}
