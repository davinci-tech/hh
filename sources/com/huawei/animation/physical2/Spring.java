package com.huawei.animation.physical2;

/* loaded from: classes8.dex */
public class Spring {
    private float c = 0.0f;
    private float g = 0.0f;
    private float b = 1.0f;
    private float j = 0.001f;
    private float f = 228.0f;
    private float e = 30.0f;

    /* renamed from: a, reason: collision with root package name */
    private float f1850a = 1.0f;
    private float h = 0.001f;
    private CalcSpring d = new e();
    private float i = this.j * 62.5f;

    interface CalcSpring {
        float getValue(float f);

        float getVelocity(float f);
    }

    public boolean e(float f, float f2) {
        return ((double) Math.abs(f2)) < ((double) this.i) && ((double) Math.abs(f - this.b)) < ((double) this.j);
    }

    public Spring e() {
        this.d = b();
        return this;
    }

    public float e(long j) {
        return this.d.getValue(j / 1000.0f) + this.b;
    }

    public float d(long j) {
        return this.d.getVelocity(j / 1000.0f);
    }

    private CalcSpring b() {
        float f = this.f1850a;
        if (f == 0.0f) {
            return this.d;
        }
        float f2 = this.c - this.b;
        float f3 = this.e;
        float f4 = (f3 * f3) - ((4.0f * f) * this.f);
        if (f4 == 0.0f) {
            float f5 = (-f3) / (f * 2.0f);
            return new b(f2, this.g - (f5 * f2), f5);
        }
        if (f4 > 0.0f) {
            double d2 = -f3;
            double d3 = f4;
            float sqrt = (float) ((d2 - Math.sqrt(d3)) / (this.f1850a * 2.0f));
            float sqrt2 = (float) (((-this.e) + Math.sqrt(d3)) / (this.f1850a * 2.0f));
            float f6 = sqrt2 - sqrt;
            if (Math.abs(f6) < 1.0E-6f) {
                return this.d;
            }
            float f7 = (this.g - (sqrt * f2)) / f6;
            return new d(f2 - f7, f7, sqrt, sqrt2);
        }
        double sqrt3 = Math.sqrt(-f4);
        float f8 = this.f1850a * 2.0f;
        float f9 = (float) (sqrt3 / f8);
        float f10 = (-this.e) / f8;
        return new a(f2, (this.g - (f10 * f2)) / f9, f9, f10);
    }

    public Spring e(float f) {
        this.c = f;
        return this;
    }

    public Spring c(float f) {
        this.g = f;
        return this;
    }

    public float a() {
        return this.b;
    }

    public Spring a(float f) {
        this.b = f;
        return this;
    }

    public Spring f(float f) {
        this.j = f;
        this.i = f * 62.5f;
        return this;
    }

    public Spring d(float f) {
        this.f = f;
        return this;
    }

    public Spring b(float f) {
        this.e = f;
        return this;
    }

    static class b implements CalcSpring {
        float b;
        float c;
        float d;

        b(float f, float f2, float f3) {
            this.d = f;
            this.b = f2;
            this.c = f3;
        }

        @Override // com.huawei.animation.physical2.Spring.CalcSpring
        public float getValue(float f) {
            return (float) ((this.d + (this.b * f)) * Math.pow(2.718281828459045d, this.c * f));
        }

        @Override // com.huawei.animation.physical2.Spring.CalcSpring
        public float getVelocity(float f) {
            float pow = (float) Math.pow(2.718281828459045d, this.c * f);
            float f2 = this.c;
            float f3 = this.d;
            float f4 = this.b;
            return (f2 * (f3 + (f * f4)) * pow) + (f4 * pow);
        }
    }

    static class d implements CalcSpring {
        float b;
        float c;
        float d;
        float e;

        d(float f, float f2, float f3, float f4) {
            this.d = f;
            this.c = f2;
            this.b = f3;
            this.e = f4;
        }

        @Override // com.huawei.animation.physical2.Spring.CalcSpring
        public float getValue(float f) {
            return (this.d * ((float) Math.pow(2.718281828459045d, this.b * f))) + (this.c * ((float) Math.pow(2.718281828459045d, this.e * f)));
        }

        @Override // com.huawei.animation.physical2.Spring.CalcSpring
        public float getVelocity(float f) {
            float f2 = this.d;
            float f3 = this.b;
            return (f2 * f3 * ((float) Math.pow(2.718281828459045d, f3 * f))) + (this.c * this.e * ((float) Math.pow(2.718281828459045d, f * r6)));
        }
    }

    static class a implements CalcSpring {

        /* renamed from: a, reason: collision with root package name */
        float f1851a;
        float b;
        float c;
        float e;

        a(float f, float f2, float f3, float f4) {
            this.e = f;
            this.f1851a = f2;
            this.b = f3;
            this.c = f4;
        }

        @Override // com.huawei.animation.physical2.Spring.CalcSpring
        public float getValue(float f) {
            return ((float) Math.pow(2.718281828459045d, this.c * f)) * ((this.e * ((float) Math.cos(this.b * f))) + (this.f1851a * ((float) Math.sin(this.b * f))));
        }

        @Override // com.huawei.animation.physical2.Spring.CalcSpring
        public float getVelocity(float f) {
            float pow = (float) Math.pow(2.718281828459045d, this.c * f);
            float cos = (float) Math.cos(this.b * f);
            float sin = (float) Math.sin(this.b * f);
            float f2 = this.f1851a;
            float f3 = this.b;
            float f4 = this.e;
            return ((((f2 * f3) * cos) - ((f3 * f4) * sin)) * pow) + (this.c * pow * ((f2 * sin) + (f4 * cos)));
        }
    }

    static class e implements CalcSpring {
        @Override // com.huawei.animation.physical2.Spring.CalcSpring
        public float getValue(float f) {
            return 0.0f;
        }

        @Override // com.huawei.animation.physical2.Spring.CalcSpring
        public float getVelocity(float f) {
            return 0.0f;
        }

        e() {
        }
    }

    public String toString() {
        return "Spring{startValue=" + this.c + ", startVelocity=" + this.g + ", endValue=" + this.b + ", valueAccuracy=" + this.j + ", stiffness=" + this.f + ", damping=" + this.e + ", mass=" + this.f1850a + ", timeEstimateSpan=" + this.h + ", calcSpring=" + this.d + ", velocityAccuracy=" + this.i + '}';
    }
}
