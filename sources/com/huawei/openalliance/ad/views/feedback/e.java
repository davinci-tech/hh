package com.huawei.openalliance.ad.views.feedback;

import android.view.View;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.bg;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class e {
    private int b;

    /* renamed from: a, reason: collision with root package name */
    private final List<View> f8072a = new ArrayList();
    private int c = 0;

    public void a(View view) {
        this.f8072a.add(view);
        if (this.b < view.getMeasuredHeight()) {
            this.b = view.getMeasuredHeight();
        }
    }

    public void a(int i, int i2, int i3, int i4, int i5, int i6) {
        if (bg.a(this.f8072a)) {
            return;
        }
        if (i == -1) {
            int i7 = i3 + (i5 - this.c) + i2 + i6;
            for (int size = this.f8072a.size() - 1; size >= 0; size--) {
                this.f8072a.get(size).layout(i7, i4, this.f8072a.get(size).getMeasuredWidth() + i7, this.f8072a.get(size).getMeasuredHeight() + i4);
                i7 += this.f8072a.get(size).getMeasuredWidth() + i6;
            }
            return;
        }
        if (i == 0) {
            int size2 = ((((this.f8072a.size() - 1) * i6) + i5) - this.c) / (this.f8072a.size() + 1);
            for (View view : this.f8072a) {
                int i8 = i3 + size2;
                view.layout(i8, i4, view.getMeasuredWidth() + i8, view.getMeasuredHeight() + i4);
                i3 = i8 + view.getMeasuredWidth();
            }
            return;
        }
        if (i != 1) {
            ho.b("FlowLayoutLine", "lineMode error");
            return;
        }
        for (View view2 : this.f8072a) {
            view2.layout(i3, i4, view2.getMeasuredWidth() + i3, view2.getMeasuredHeight() + i4);
            i3 += view2.getMeasuredWidth() + i6;
        }
    }

    public void a(int i) {
        this.c = i;
    }

    public int a() {
        return this.b;
    }
}
