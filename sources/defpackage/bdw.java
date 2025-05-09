package defpackage;

import com.huawei.hihealth.HiUserInfo;
import com.huawei.up.model.UserInfomation;

/* loaded from: classes3.dex */
public class bdw {

    /* renamed from: a, reason: collision with root package name */
    private float f340a;
    private int b;
    private int c;
    private int d;
    private float e;

    private float c(float f) {
        if (f <= 0.0f) {
            return 60.0f;
        }
        if (f < 30.0f) {
            return 30.0f;
        }
        if (f > 250.0f) {
            return 250.0f;
        }
        return f;
    }

    private float e(float f) {
        if (f <= 0.0f) {
            return 1.7f;
        }
        if (f > 0.0f && f < 0.5f) {
            return 0.5f;
        }
        if (f > 2.5f) {
            return 2.5f;
        }
        return f;
    }

    private int e(int i) {
        if (i == 1) {
            return 1;
        }
        if (i == 0) {
            return 0;
        }
        return (i == -1 || i == 2) ? -1 : 1;
    }

    public bdw(e eVar) {
        this.d = a(eVar.e);
        this.f340a = e(eVar.f341a);
        this.e = c(eVar.c);
        this.b = e(eVar.b);
        this.c = eVar.d;
    }

    /* loaded from: classes.dex */
    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private float f341a;
        private int b;
        private float c;
        private int d;
        private int e;

        public e d(int i) {
            this.e = i;
            return this;
        }

        public e b(float f) {
            this.c = f;
            return this;
        }

        public e c(float f) {
            this.f341a = f;
            return this;
        }

        public e c(int i) {
            this.b = i;
            return this;
        }

        public bdw a() {
            return new bdw(this);
        }
    }

    public int c() {
        return a(this.d);
    }

    public float a() {
        return c(this.e);
    }

    public float e() {
        return e(this.f340a);
    }

    public int b() {
        return e(this.b);
    }

    public int d() {
        return this.c;
    }

    public static bdw d(UserInfomation userInfomation) {
        e eVar = new e();
        if (userInfomation != null) {
            int i = 1;
            if (userInfomation.getGender() != 0) {
                i = userInfomation.getGender() == 1 ? 0 : userInfomation.getGender();
            }
            eVar.d(userInfomation.getAge()).c(userInfomation.getHeight() / 100.0f).b(userInfomation.getWeight()).c(i);
        }
        return eVar.a();
    }

    private int a(int i) {
        if (i <= 0) {
            return new HiUserInfo().getAgeOrDefaultValue();
        }
        if (i < 16) {
            return 16;
        }
        return Math.min(i, 80);
    }
}
