package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$styleable;

/* loaded from: classes6.dex */
public class nrb {

    /* renamed from: a, reason: collision with root package name */
    private int f15452a;
    private boolean b;
    private int c;
    private int d;
    private boolean e;
    private int f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int t;

    public nrb(Context context, AttributeSet attributeSet) {
        this.e = false;
        this.b = false;
        this.i = false;
        this.g = true;
        this.j = true;
        this.h = false;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.HealthTableWidget, 0, 0);
        try {
            this.f15452a = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthTableWidget_cellMargin, 0);
            this.d = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthTableWidget_cellHorizontalMargin, this.f15452a);
            this.c = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthTableWidget_cellVerticalMargin, this.f15452a);
            this.e = obtainStyledAttributes.getBoolean(R$styleable.HealthTableWidget_columnHeaderFixed, false);
            this.b = obtainStyledAttributes.getBoolean(R$styleable.HealthTableWidget_rowHeaderFixed, false);
            this.i = obtainStyledAttributes.getBoolean(R$styleable.HealthTableWidget_statisticFixed, false);
            this.j = obtainStyledAttributes.getBoolean(R$styleable.HealthTableWidget_isShowLeftRightGrid, false);
            this.g = obtainStyledAttributes.getBoolean(R$styleable.HealthTableWidget_isShowTopBottomGrid, false);
            this.h = obtainStyledAttributes.getBoolean(R$styleable.HealthTableWidget_isShowShadow, false);
            this.m = obtainStyledAttributes.getDimensionPixelSize(R$styleable.HealthTableWidget_shadowThick, 20);
            this.t = obtainStyledAttributes.getResourceId(R$styleable.HealthTableWidget_shadowTop, R$drawable.common_table_shadow_top);
            this.k = obtainStyledAttributes.getResourceId(R$styleable.HealthTableWidget_shadowBottom, R$drawable.common_table_shadow_bottom);
            this.n = obtainStyledAttributes.getResourceId(R$styleable.HealthTableWidget_shadowLeft, R$drawable.common_table_shadow_left);
            this.o = obtainStyledAttributes.getResourceId(R$styleable.HealthTableWidget_shadowRight, R$drawable.common_table_shadow_right);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public int c() {
        return this.l;
    }

    public nrb e(int i) {
        this.l = i;
        return this;
    }

    public int e() {
        return this.f;
    }

    public nrb b(int i) {
        this.f = i;
        return this;
    }

    public boolean j() {
        return this.e;
    }

    public boolean o() {
        return this.b;
    }

    public boolean n() {
        return this.i;
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.d;
    }

    public boolean l() {
        return this.g;
    }

    public boolean k() {
        return this.j;
    }

    public boolean m() {
        return this.h;
    }

    public int g() {
        return this.m;
    }

    public int h() {
        return this.t;
    }

    public int d() {
        return this.k;
    }

    public int i() {
        return this.n;
    }

    public int f() {
        return this.o;
    }
}
