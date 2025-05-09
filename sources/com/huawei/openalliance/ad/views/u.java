package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.constant.SkipPosition;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.jb;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dd;
import java.util.IllegalFormatException;
import java.util.Locale;

/* loaded from: classes9.dex */
public class u extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8154a = "u";
    private static int b = 16;
    private static int c = 16;
    private static int d = 4;
    private static int e = 16;
    private static int f = 16;
    private static int g = 24;
    private static int h = 24;
    private Context i;
    private String j;
    private String k;
    private int l;
    private int m;
    private int n;
    private final String o;
    private jb p;
    private boolean q;
    private Resources r;
    private TextView s;
    private boolean t;
    private int u;
    private float v;
    private int w;
    private boolean x;
    private boolean y;
    private boolean z;

    private int getSkipAdLeftMarginPx() {
        return 0;
    }

    public void setShowLeftTime(boolean z) {
        this.t = z;
    }

    public void setLinkedOnTouchListener(View.OnTouchListener onTouchListener) {
        setOnTouchListener(onTouchListener);
    }

    public void setAdMediator(jb jbVar) {
        this.p = jbVar;
    }

    public void a(int i) {
        if (this.t && !TextUtils.isEmpty(this.k)) {
            try {
                String format = String.format(Locale.getDefault(), this.k, Integer.valueOf(i));
                ho.a(f8154a, "updateLeftTime : %s", format);
                this.s.setText(format);
                return;
            } catch (IllegalFormatException unused) {
                ho.d(f8154a, "updateLeftTime IllegalFormatException");
            }
        }
        this.s.setText(this.j);
    }

    private int getVerticalSidePaddingDp() {
        return Math.min(a(false), this.n);
    }

    private int getVerticalSideMarginDp() {
        int a2 = a(false);
        int i = this.n;
        if (a2 < i) {
            return 0;
        }
        return a2 - i;
    }

    private int getVerticalSideBottomMarginDp() {
        int a2 = a(true);
        int i = this.n;
        if (a2 < i) {
            return 0;
        }
        return a2 - i;
    }

    private int getTopPaddingDp() {
        return Math.min(5 == this.m ? f : c, this.n);
    }

    private int getSkipAdTopPaddingPx() {
        Context context;
        int topPaddingDp;
        if (SkipPosition.LOWER_RIGHT.equals(this.o)) {
            context = this.i;
            topPaddingDp = this.n;
        } else {
            context = this.i;
            topPaddingDp = getTopPaddingDp();
        }
        return ao.a(context, topPaddingDp);
    }

    private int getSkipAdTopMarginPx() {
        if (SkipPosition.LOWER_RIGHT.equals(this.o)) {
            return 0;
        }
        return ao.a(this.i, getVerticalSideMarginDp());
    }

    private int getSkipAdRightPaddingPx() {
        return ao.a(this.i, getHorizontalSidePaddingDp());
    }

    private int getSkipAdRightMarginPx() {
        return ao.a(this.i, getHorizontalSideMarginDp());
    }

    private int getSkipAdPaddingPx() {
        return this.r.getDimensionPixelOffset(R.dimen._2131363366_res_0x7f0a0626);
    }

    private int getSkipAdLeftPaddingPx() {
        return this.r.getDimensionPixelOffset(R.dimen._2131363367_res_0x7f0a0627);
    }

    private RelativeLayout.LayoutParams getSkipAdLayoutParams() {
        int a2;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(SkipPosition.LOWER_RIGHT.equals(this.o) ? 12 : 10);
        layoutParams.addRule(21);
        int skipAdLeftMarginPx = getSkipAdLeftMarginPx();
        int skipAdTopMarginPx = getSkipAdTopMarginPx();
        int skipAdRightMarginPx = getSkipAdRightMarginPx();
        int skipAdBottomMarginPx = getSkipAdBottomMarginPx();
        if (1 != this.l) {
            if (!this.x) {
                skipAdRightMarginPx += this.u;
            }
            skipAdRightMarginPx = this.y ? skipAdRightMarginPx + dd.f(this.i) : dd.f(this.i);
            if ("tr".equals(this.o)) {
                a2 = ao.a(this.i, 12.0f);
                skipAdTopMarginPx += a2;
            }
        } else if ("tr".equals(this.o)) {
            a2 = this.u;
            skipAdTopMarginPx += a2;
        }
        layoutParams.setMargins(skipAdLeftMarginPx, skipAdTopMarginPx, skipAdRightMarginPx, skipAdBottomMarginPx);
        layoutParams.setMarginEnd(skipAdRightMarginPx);
        return layoutParams;
    }

    private int getSkipAdBottomPaddingPx() {
        Context context;
        int i;
        if (SkipPosition.LOWER_RIGHT.equals(this.o)) {
            context = this.i;
            i = getVerticalSidePaddingDp();
        } else {
            context = this.i;
            i = this.n;
        }
        return ao.a(context, i);
    }

    private int getSkipAdBottomMarginPx() {
        if (!SkipPosition.LOWER_RIGHT.equals(this.o)) {
            return 0;
        }
        int f2 = (this.l != 0 || 5 == this.m || com.huawei.openalliance.ad.utils.x.q(this.i) || com.huawei.openalliance.ad.utils.x.n(this.i)) ? this.q ? 0 : dd.f(this.i) : 0;
        if (!this.q && ho.a()) {
            ho.a(f8154a, "navigation bar h: %d", Integer.valueOf(f2));
        }
        return f2 + ao.a(this.i, getVerticalSideBottomMarginDp());
    }

    private int getHorizontalSidePaddingDp() {
        return Math.min(getHorizontalSideGapDpSize(), this.n);
    }

    private int getHorizontalSideMarginDp() {
        int horizontalSideGapDpSize = getHorizontalSideGapDpSize();
        int i = this.n;
        if (horizontalSideGapDpSize < i) {
            return 0;
        }
        return horizontalSideGapDpSize - i;
    }

    private int getHorizontalSideGapDpSize() {
        int i = b;
        if (5 == this.m) {
            i = e;
        }
        return !this.y ? d : i;
    }

    private void d() {
        setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.openalliance.ad.views.u.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float rawX = motionEvent.getRawX();
                float rawY = motionEvent.getRawY();
                if (motionEvent.getAction() == 0) {
                    if (ho.a()) {
                        ho.a(u.f8154a, "touch down skipAdButton x=%f, y=%f", Float.valueOf(rawX), Float.valueOf(rawY));
                    }
                    if (!u.this.z && u.this.p != null) {
                        u.this.z = true;
                        u.this.p.a((int) rawX, (int) rawY);
                    }
                }
                return true;
            }
        });
    }

    private void c() {
        inflate(getContext(), R.layout.hiad_view_skip_button, this);
        TextView textView = (TextView) findViewById(R.id.hiad_skip_text);
        this.s = textView;
        textView.setText(this.j);
        if (this.v > 0.0f) {
            if (ao.n(this.i)) {
                this.s.setTextSize(1, 24.0f);
                if (this.w > 0) {
                    this.s.setHeight(ao.a(this.i, 48.0f));
                }
            } else {
                this.s.setTextSize(2, this.v);
                int i = this.w;
                if (i > 0) {
                    this.s.setHeight(ao.c(this.i, i));
                }
            }
        }
        this.s.setPadding(getSkipAdPaddingPx(), 0, getSkipAdPaddingPx(), 0);
        setPaddingRelative(getSkipAdLeftPaddingPx(), getSkipAdTopPaddingPx(), getSkipAdRightPaddingPx(), getSkipAdBottomPaddingPx());
        setClickable(true);
        setLayoutParams(getSkipAdLayoutParams());
    }

    private void b() {
        Context context;
        Resources resources = this.r;
        if (resources == null || (context = this.i) == null) {
            return;
        }
        b = ao.b(context, resources.getDimension(R.dimen._2131363381_res_0x7f0a0635));
        c = ao.b(this.i, this.r.getDimension(R.dimen._2131363383_res_0x7f0a0637));
        d = ao.b(this.i, this.r.getDimension(R.dimen._2131363387_res_0x7f0a063b));
        e = ao.b(this.i, this.r.getDimension(R.dimen._2131363384_res_0x7f0a0638));
        f = ao.b(this.i, this.r.getDimension(R.dimen._2131363386_res_0x7f0a063a));
        g = ao.b(this.i, this.r.getDimension(R.dimen._2131363382_res_0x7f0a0636));
        h = ao.b(this.i, this.r.getDimension(R.dimen._2131363385_res_0x7f0a0639));
    }

    private String a(String str) {
        String c2 = cz.c(str);
        return cz.b(c2) ? this.i.getString(R.string._2130851065_res_0x7f0234f9) : c2;
    }

    private int a(boolean z) {
        return 5 == this.m ? z ? h : f : z ? g : c;
    }

    public u(Context context, String str, int i, int i2, int i3, String str2, boolean z, int i4, float f2, int i5, boolean z2) {
        super(context);
        this.n = 0;
        this.t = false;
        this.x = false;
        this.y = true;
        this.z = false;
        this.i = context;
        this.r = context.getResources();
        b();
        this.l = i;
        this.m = i2;
        this.n = i3;
        this.o = str2 == null ? "tr" : str2;
        this.j = context.getString(R.string._2130851064_res_0x7f0234f8);
        this.k = a(str);
        this.q = z;
        this.u = i4;
        this.v = f2;
        this.w = i5;
        this.x = z2;
        this.y = bz.b(context);
        c();
        this.z = false;
        d();
    }
}
