package com.huawei.openalliance.ad.views.linkscroll;

import android.view.View;
import android.view.ViewParent;

/* loaded from: classes5.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private final View f8114a;
    private ViewParent b;
    private boolean c;
    private int[] d;

    public void c() {
        ViewParent viewParent = this.b;
        if (viewParent != null) {
            d.a(viewParent, this.f8114a);
            this.b = null;
        }
    }

    public boolean b() {
        return this.b != null;
    }

    public boolean a(int i, int i2, int[] iArr, int[] iArr2) {
        int i3;
        int i4;
        if (!a() || this.b == null) {
            return false;
        }
        if (i == 0 && i2 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        if (iArr2 != null) {
            this.f8114a.getLocationInWindow(iArr2);
            i3 = iArr2[0];
            i4 = iArr2[1];
        } else {
            i3 = 0;
            i4 = 0;
        }
        if (iArr == null) {
            if (this.d == null) {
                this.d = new int[2];
            }
            iArr = this.d;
        }
        iArr[0] = 0;
        iArr[1] = 0;
        d.a(this.b, this.f8114a, i, i2, iArr);
        if (iArr2 != null) {
            this.f8114a.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i3;
            iArr2[1] = iArr2[1] - i4;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public boolean a(int i, int i2, int i3, int i4, int[] iArr) {
        int i5;
        int i6;
        if (a() && this.b != null) {
            if (i != 0 || i2 != 0 || i3 != 0 || i4 != 0) {
                if (iArr != null) {
                    this.f8114a.getLocationInWindow(iArr);
                    i5 = iArr[0];
                    i6 = iArr[1];
                } else {
                    i5 = 0;
                    i6 = 0;
                }
                d.a(this.b, this.f8114a, i, i2, i3, i4);
                if (iArr != null) {
                    this.f8114a.getLocationInWindow(iArr);
                    iArr[0] = iArr[0] - i5;
                    iArr[1] = iArr[1] - i6;
                }
                return true;
            }
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
        }
        return false;
    }

    public boolean a(int i) {
        if (b()) {
            return true;
        }
        if (!a()) {
            return false;
        }
        View view = this.f8114a;
        for (ViewParent parent = this.f8114a.getParent(); parent != null; parent = parent.getParent()) {
            if (d.a(parent, view, this.f8114a, i)) {
                this.b = parent;
                d.b(parent, view, this.f8114a, i);
                return true;
            }
            if (parent instanceof View) {
                view = (View) parent;
            }
        }
        return false;
    }

    public boolean a(float f, float f2) {
        ViewParent viewParent;
        if (!a() || (viewParent = this.b) == null) {
            return false;
        }
        return d.a(viewParent, this.f8114a, f, f2);
    }

    public boolean a() {
        return this.c;
    }

    public void a(boolean z) {
        if (this.c) {
            a(this.f8114a);
        }
        this.c = z;
    }

    public void a(View view) {
        if (view instanceof a) {
            ((e) view).a();
        }
    }

    public c(View view) {
        this.f8114a = view;
    }
}
