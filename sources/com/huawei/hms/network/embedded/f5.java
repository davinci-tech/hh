package com.huawei.hms.network.embedded;

/* loaded from: classes9.dex */
public class f5 implements g5 {

    /* renamed from: a, reason: collision with root package name */
    public boolean f5243a;
    public boolean b;
    public boolean c;
    public int d;
    public int e;
    public long f;
    public int g = 0;
    public int h = 0;
    public int i = 0;
    public boolean j;

    public String toString() {
        return "SystemControlImpl{isDozeIdleMode=" + this.f5243a + ", isAppIdleMode=" + this.b + ", isAllowList=" + this.c + ", isPowerSaverMode=" + this.d + ", isDataSaverMode=" + this.e + ", sysControlTimeStamp=" + this.f + ", sysControlMode=" + this.g + ", controlPolicyMode=" + this.h + ", hwControlMode=" + this.i + ", isFreeze=" + this.j + '}';
    }

    public void d(boolean z) {
        this.j = z;
    }

    @Override // com.huawei.hms.network.embedded.g5
    public long d() {
        return this.f;
    }

    public void c(boolean z) {
        this.f5243a = z;
    }

    @Override // com.huawei.hms.network.embedded.g5
    public int c() {
        int i;
        if (this.c) {
            boolean z = this.f5243a;
            if (z && this.b) {
                this.g = 1;
            }
            if (z && !this.b) {
                this.g = 2;
            }
            if (!z && this.b) {
                this.g = 3;
            }
            if (!z && !this.b) {
                i = 4;
                this.g = i;
            }
        } else {
            boolean z2 = this.f5243a;
            if (z2 && this.b) {
                this.g = 5;
            }
            if (z2 && !this.b) {
                this.g = 6;
            }
            if (!z2 && this.b) {
                this.g = 7;
            }
            if (!z2 && !this.b) {
                i = 8;
                this.g = i;
            }
        }
        return this.g;
    }

    public void b(boolean z) {
        this.b = z;
    }

    public void b(int i) {
        this.d = i;
    }

    @Override // com.huawei.hms.network.embedded.g5
    public int b() {
        int i;
        int i2 = this.d;
        if (i2 == 1) {
            int i3 = this.e;
            if (i3 == 1) {
                this.h = 1;
            } else if (i3 == 2) {
                this.h = 2;
            } else if (i3 == 3) {
                this.h = 3;
            } else if (i3 == 0) {
                i = 10;
                this.h = i;
            }
        } else if (i2 == 4) {
            int i4 = this.e;
            if (i4 == 1) {
                this.h = 4;
            } else if (i4 == 2) {
                this.h = 5;
            } else {
                if (i4 == 3) {
                    i = 6;
                } else if (i4 == 0) {
                    i = 11;
                }
                this.h = i;
            }
        } else if (i2 == 5) {
            int i5 = this.e;
            if (i5 == 1) {
                i = 7;
            } else if (i5 == 2) {
                i = 8;
            } else if (i5 == 3) {
                i = 9;
            } else if (i5 == 0) {
                i = 12;
            }
            this.h = i;
        } else if (i2 == 0) {
            int i6 = this.e;
            if (i6 == 1) {
                i = 13;
            } else if (i6 == 2) {
                i = 14;
            } else if (i6 == 3) {
                i = 15;
            } else if (i6 == 0) {
                i = 16;
            }
            this.h = i;
        }
        return this.h;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public void a(long j) {
        this.f = j;
    }

    public void a(int i) {
        this.e = i;
    }

    @Override // com.huawei.hms.network.embedded.g5
    public int a() {
        if (this.j) {
            this.i = 1;
        }
        return this.i;
    }
}
