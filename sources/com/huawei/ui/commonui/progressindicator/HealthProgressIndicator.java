package com.huawei.ui.commonui.progressindicator;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.uikit.phone.hwprogressindicator.widget.HwProgressIndicator;
import defpackage.npz;
import health.compact.a.LanguageUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class HealthProgressIndicator extends HwProgressIndicator {

    /* renamed from: a, reason: collision with root package name */
    private int f8922a;
    private int b;
    private int c;
    private int d;
    private int e;
    private float f;
    private int g;
    private int h;
    private float i;
    private int j;
    private Map<Integer, npz> l;
    private double n;

    public HealthProgressIndicator(Context context) {
        super(context);
        this.e = -1;
        this.c = -1;
        this.g = 3;
        this.f = 0.0f;
        cEE_(getResources());
    }

    public HealthProgressIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = -1;
        this.c = -1;
        this.g = 3;
        this.f = 0.0f;
        cEE_(getResources());
    }

    public HealthProgressIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = -1;
        this.c = -1;
        this.g = 3;
        this.f = 0.0f;
        cEE_(getResources());
    }

    @Override // com.huawei.uikit.hwprogressindicator.widget.HwProgressIndicator
    public void setProgress(int i) {
        if (this.h == 0) {
            LogUtil.c("HealthProgressIndicator", "onIndicatorWidthChanged again");
            b();
        }
        super.setProgress(i);
        this.j = i;
        d();
        if (!this.l.containsKey(Integer.valueOf(i))) {
            int sin = (this.e / 2) + ((int) (this.h * Math.sin(this.n)));
            int i2 = this.c / 2;
            int cos = (int) (this.h * Math.cos(this.n));
            if (LanguageUtil.bc(getContext())) {
                sin = (this.e / 2) - ((int) (this.h * Math.sin(this.n)));
            }
            this.l.put(Integer.valueOf(i), new npz(sin, i2 - cos));
        }
        this.d = this.l.get(Integer.valueOf(i)).c();
        int e = this.l.get(Integer.valueOf(i)).e();
        this.b = e;
        if (this.d == e) {
            this.l.remove(Integer.valueOf(i));
        }
    }

    public npz getPosition() {
        return new npz(this.d, this.b);
    }

    private void d() {
        float f = this.j / 100.0f;
        this.f = f;
        float f2 = f * 360.0f;
        this.i = f2;
        this.n = Math.toRadians(f2);
    }

    private void cEE_(Resources resources) {
        int i = this.g;
        if (i == 0) {
            this.c = resources.getDimensionPixelSize(R.dimen._2131364295_res_0x7f0a09c7);
            this.e = resources.getDimensionPixelSize(R.dimen._2131364297_res_0x7f0a09c9);
        } else if (i == 3) {
            this.c = resources.getDimensionPixelSize(R.dimen._2131364302_res_0x7f0a09ce);
            this.e = resources.getDimensionPixelSize(R.dimen._2131364303_res_0x7f0a09cf);
        } else if (i == 1) {
            this.c = resources.getDimensionPixelSize(R.dimen._2131364299_res_0x7f0a09cb);
            this.e = resources.getDimensionPixelSize(R.dimen._2131364301_res_0x7f0a09cd);
        } else {
            this.c = resources.getDimensionPixelSize(R.dimen._2131364305_res_0x7f0a09d1);
            this.e = resources.getDimensionPixelSize(R.dimen._2131364307_res_0x7f0a09d3);
        }
        if (this.f8922a == -1) {
            this.f8922a = cEC_(resources);
        }
        this.l = new HashMap();
        cEF_(resources);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        b();
    }

    private void b() {
        this.h = Math.min((getHeight() - getPaddingBottom()) - getPaddingTop(), (getWidth() - getPaddingLeft()) - getPaddingRight()) / 2;
    }

    private int cEC_(Resources resources) {
        int i = this.g;
        if (i == 1) {
            return resources.getDimensionPixelSize(R.dimen._2131364298_res_0x7f0a09ca);
        }
        if (i == 2) {
            return resources.getDimensionPixelSize(R.dimen._2131364304_res_0x7f0a09d0);
        }
        return resources.getDimensionPixelSize(R.dimen._2131364292_res_0x7f0a09c4);
    }

    private int cED_(Resources resources) {
        int i = this.g;
        if (i == 1) {
            return resources.getDimensionPixelSize(R.dimen._2131364300_res_0x7f0a09cc);
        }
        if (i == 2) {
            return resources.getDimensionPixelSize(R.dimen._2131364306_res_0x7f0a09d2);
        }
        return resources.getDimensionPixelSize(R.dimen._2131364296_res_0x7f0a09c8);
    }

    private void cEF_(Resources resources) {
        int cEC_ = cEC_(resources);
        if (cEC_ == 0) {
            return;
        }
        int cED_ = (int) ((this.f8922a * cED_(resources)) / cEC_);
        setPadding(cED_, cED_, cED_, cED_);
    }
}
