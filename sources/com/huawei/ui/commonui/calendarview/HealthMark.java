package com.huawei.ui.commonui.calendarview;

import android.graphics.drawable.Drawable;
import android.view.View;
import java.io.Serializable;

/* loaded from: classes6.dex */
public class HealthMark implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private String f8778a;
    private boolean b;
    private transient Drawable c;
    private int d;
    private int e;
    private transient View f;
    private MarkType i;
    private int j;

    public enum MarkType {
        TEXT,
        COLOR,
        DRAWABLE,
        TEXT_DRAWABLE,
        VIEW
    }

    public HealthMark() {
        this.i = MarkType.DRAWABLE;
    }

    public HealthMark(MarkType markType) {
        MarkType markType2 = MarkType.DRAWABLE;
        this.i = markType;
    }

    public HealthMark(Drawable drawable, String str, int i) {
        this.i = MarkType.DRAWABLE;
        this.c = drawable;
        this.f8778a = str;
        this.d = i;
        this.i = MarkType.DRAWABLE;
        this.b = true;
    }

    public boolean j() {
        return this.b;
    }

    public MarkType f() {
        return this.i;
    }

    public void e(MarkType markType) {
        this.i = markType;
    }

    public int d() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
        this.b = true;
        this.i = MarkType.COLOR;
    }

    public void cxz_(Drawable drawable) {
        this.c = drawable;
        this.i = MarkType.DRAWABLE;
    }

    public String e() {
        return this.f8778a;
    }

    public View cxy_() {
        return this.f;
    }

    public Drawable cxx_() {
        return this.c;
    }

    public void cxA_(View view) {
        this.f = view;
        this.i = MarkType.VIEW;
    }

    public void e(int i) {
        View view = this.f;
        if (view == null || view.getMeasuredHeight() > 0) {
            return;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i, 1073741824);
        this.f.measure(makeMeasureSpec, makeMeasureSpec);
        this.f.layout(0, 0, this.f.getMeasuredWidth(), this.f.getMeasuredHeight());
    }

    public int c() {
        return this.j;
    }

    public void d(int i) {
        this.j = i;
    }

    public int b() {
        return this.e;
    }
}
