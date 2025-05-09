package com.huawei.health.health.utils.functionsetcard;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public class FunctionSetBean implements Comparable<FunctionSetBean> {

    /* renamed from: a, reason: collision with root package name */
    private CharSequence f2482a;
    private View b;
    private CharSequence c;
    private Context d;
    private int e;
    private boolean f;
    private String g;
    private FunctionSetType h;
    private CharSequence i;
    private String j;
    private Drawable k;
    private String l;
    private CharSequence m;
    private String n;
    private int o;
    private int p;
    private CharSequence s;
    private ViewType t;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private View f2483a;
        private Context b;
        private int c;
        private CharSequence d;
        private CharSequence e;
        private CharSequence f;
        private boolean g;
        private String h;
        private Drawable i;
        private FunctionSetType j;
        private CharSequence k;
        private String l;
        private String m;
        private int n;
        private CharSequence o;
        private ViewType s;

        public a(String str) {
            this.m = str;
        }

        public a d(Context context) {
            this.b = context;
            return this;
        }

        public a c(CharSequence charSequence) {
            this.k = charSequence;
            return this;
        }

        public a c(String str) {
            this.k = str;
            return this;
        }

        public a a(CharSequence charSequence) {
            this.d = charSequence;
            return this;
        }

        public a b(CharSequence charSequence) {
            this.o = charSequence;
            return this;
        }

        public a e(String str) {
            this.h = str;
            return this;
        }

        public a a(FunctionSetType functionSetType) {
            this.j = functionSetType;
            return this;
        }

        public a d(boolean z) {
            this.g = z;
            return this;
        }

        public a e(CharSequence charSequence) {
            this.e = charSequence;
            return this;
        }

        public a d(String str) {
            this.e = str;
            return this;
        }

        public FunctionSetBean c() {
            return new FunctionSetBean(this);
        }

        public a b(ViewType viewType) {
            this.s = viewType;
            return this;
        }

        public a c(List<HiHealthData> list) {
            if (koq.b(list)) {
                return this;
            }
            Iterator<HiHealthData> it = list.iterator();
            int i = 1;
            while (it.hasNext()) {
                i = Objects.hash(Integer.valueOf(it.next().hashCode()), Integer.valueOf(i));
            }
            this.c = i;
            return this;
        }

        public a d(CharSequence charSequence) {
            this.f = charSequence;
            return this;
        }

        public a YF_(Drawable drawable) {
            this.i = drawable;
            return this;
        }
    }

    public enum ViewType {
        EMPTY_VIEW(0),
        DATA_VIEW(1),
        CARD_MANAGER_VIEW(2);

        private int defaultIndex;

        ViewType(int i) {
            this.defaultIndex = i;
        }
    }

    public FunctionSetBean(a aVar) {
        if (aVar != null) {
            this.n = aVar.m;
            this.m = aVar.k;
            this.c = aVar.d;
            this.s = aVar.o;
            this.g = aVar.h;
            this.o = aVar.n;
            this.h = aVar.j;
            this.t = aVar.s;
            this.d = aVar.b;
            this.b = aVar.f2483a;
            this.f = aVar.g;
            this.f2482a = aVar.e;
            this.e = aVar.c;
            this.i = aVar.f;
            this.l = aVar.l;
            this.k = aVar.i;
            return;
        }
        LogUtil.h("FunctionSetBean", "builder is null");
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof FunctionSetBean)) {
            return ((FunctionSetBean) obj).n().equals(n());
        }
        return false;
    }

    public int hashCode() {
        return g();
    }

    @Override // java.lang.Comparable
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(FunctionSetBean functionSetBean) {
        return this.o > functionSetBean.g() ? 0 : -1;
    }

    public String n() {
        return this.n;
    }

    public CharSequence j() {
        return this.m;
    }

    public void c(CharSequence charSequence) {
        this.m = charSequence;
    }

    public CharSequence d() {
        return this.c;
    }

    public void e(CharSequence charSequence) {
        this.c = charSequence;
    }

    public int l() {
        return this.p;
    }

    public void c(int i) {
        this.p = i;
    }

    public int a() {
        return this.e;
    }

    public CharSequence m() {
        return this.s;
    }

    public void b(CharSequence charSequence) {
        this.s = charSequence;
    }

    public String e() {
        return this.g;
    }

    public int g() {
        return this.o;
    }

    public FunctionSetType k() {
        return this.h;
    }

    public ViewType q() {
        return this.t;
    }

    public void b(ViewType viewType) {
        this.t = viewType;
    }

    public void e(boolean z) {
        this.f = z;
    }

    public boolean h() {
        return this.f;
    }

    public void YB_(View view) {
        this.b = view;
    }

    public View Yz_() {
        return this.b;
    }

    public void b(String str) {
        this.f2482a = str;
    }

    public CharSequence c() {
        return this.f2482a;
    }

    public CharSequence i() {
        return this.i;
    }

    public void a(CharSequence charSequence) {
        this.i = charSequence;
    }

    public String o() {
        return this.l;
    }

    public void c(String str) {
        this.l = str;
    }

    public Drawable YA_() {
        return this.k;
    }

    public void YC_(Drawable drawable) {
        this.k = drawable;
    }

    public String toString() {
        return "FunctionSetBean{mTitle='" + this.n + "', mTime='" + ((Object) this.m) + "', mData=" + ((Object) this.c) + ", mUnit='" + ((Object) this.s) + "', mDescription='" + this.g + "', mSortKey=" + this.o + ", mIsShowTag='" + this.j + "', mFunctionSetType=" + this.h + ", mViewType=" + this.t + ", mContext=" + this.d + ", mIsShowRedDot=" + this.f + ", mCardView=" + this.b + ", mDataType='" + ((Object) this.f2482a) + "', mUniqueCode=" + this.p + '}';
    }
}
