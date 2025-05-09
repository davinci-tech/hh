package com.huawei.ui.commonui.view.threeCircle;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.ui.commonui.progressbar.CircleProgressBar;
import defpackage.ntp;
import health.compact.a.util.LogUtil;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class ThreeCircleView extends FrameLayout {
    private LinkedHashMap<String, ntp> f;
    private CircleProgressBar h;
    private Map<String, CircleProgressBar> i;
    private CircleProgressBar j;
    private boolean k;
    private boolean l;
    private Map<String, Object> m;
    private Context n;
    private CircleProgressBar o;
    private Map<String, Object> p;
    private Map<String, Object> q;
    private boolean r;
    private ImageView s;
    private boolean t;
    private int v;
    private float x;
    private static final int[] e = {BaseApplication.e().getColor(R$color.new_cal_circle_bg_start_color), BaseApplication.e().getColor(R$color.new_cal_circle_bg_end_color)};
    private static final int[] d = {BaseApplication.e().getColor(R$color.new_time_circle_bg_start_color), BaseApplication.e().getColor(R$color.new_time_circle_bg_end_color)};
    private static final int[] b = {BaseApplication.e().getColor(R$color.new_activity_circle_bg_start_color), BaseApplication.e().getColor(R$color.new_activity_circle_bg_end_color)};
    private static final int[] c = {BaseApplication.e().getColor(R$color.new_cal_circle_process_start_color), BaseApplication.e().getColor(R$color.new_cal_circle_process_second_color), BaseApplication.e().getColor(R$color.new_cal_circle_process_third_color), BaseApplication.e().getColor(R$color.new_cal_circle_process_end_color)};

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f8980a = {BaseApplication.e().getColor(R$color.new_time_circle_process_start_color), BaseApplication.e().getColor(R$color.new_time_circle_process_second_color), BaseApplication.e().getColor(R$color.new_time_circle_process_third_color), BaseApplication.e().getColor(R$color.new_time_circle_process_end_color)};
    private static final int[] g = {BaseApplication.e().getColor(R$color.new_activity_circle_process_start_color), BaseApplication.e().getColor(R$color.new_activity_circle_process_second_color), BaseApplication.e().getColor(R$color.new_activity_circle_process_third_color), BaseApplication.e().getColor(R$color.new_activity_circle_process_end_color)};

    private int a(float f) {
        return (int) (((((f % 200.0f) / 200.0f) * 2.0f) + 1.0f) * 400.0f);
    }

    public ThreeCircleView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ThreeCircleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ThreeCircleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = new HashMap();
        this.f = new LinkedHashMap<>();
        this.m = new HashMap();
        this.p = new HashMap();
        this.q = new HashMap();
        this.k = true;
        this.n = context.getApplicationContext();
        cNe_(attributeSet, i);
        d();
    }

    public ThreeCircleView(Context context, int i) {
        super(context);
        this.i = new HashMap();
        this.f = new LinkedHashMap<>();
        this.m = new HashMap();
        this.p = new HashMap();
        this.q = new HashMap();
        this.k = true;
        this.n = context.getApplicationContext();
        this.v = i;
        if (i == 1) {
            a();
        } else {
            new ThreeCircleView(context);
        }
    }

    public void setIsAnimator(boolean z) {
        this.l = z;
    }

    public void setIsShowOverlap(boolean z) {
        this.r = z;
    }

    public void setIsIconVisible(boolean z) {
        this.k = z;
    }

    public void setIsShowTriangle(boolean z) {
        this.t = z;
    }

    public void a(LinkedHashMap<String, ntp> linkedHashMap) {
        if (linkedHashMap == null || linkedHashMap.size() == 0) {
            LogUtil.c("ThreeCircleView", "circleInfos is null.");
            return;
        }
        this.f = linkedHashMap;
        c("firstCircle");
        c("secondCircle");
        c("thirdCircle");
    }

    private void c() {
        this.m.put("circleColor", c);
        this.m.put("circleBackgroundColor", e);
        this.m.put("icon", Integer.valueOf(R$drawable.ic_heat_xxxhdpi_df));
        this.m.put("colorDiff", Integer.valueOf((int) TypedValue.applyDimension(1, 3.0f, BaseApplication.e().getResources().getDisplayMetrics())));
        this.m.put("isComplete", false);
        this.p.put("circleColor", f8980a);
        this.p.put("circleBackgroundColor", d);
        this.p.put("icon", Integer.valueOf(R$drawable.ic_time_xxxhdpi));
        this.p.put("colorDiff", Integer.valueOf((int) TypedValue.applyDimension(1, 5.0f, BaseApplication.e().getResources().getDisplayMetrics())));
        this.p.put("isComplete", false);
        this.q.put("circleColor", g);
        this.q.put("circleBackgroundColor", b);
        this.q.put("icon", Integer.valueOf(R$drawable.ic_excise_xxxhdpi));
        this.q.put("colorDiff", Integer.valueOf((int) TypedValue.applyDimension(1, 8.0f, BaseApplication.e().getResources().getDisplayMetrics())));
        this.q.put("isComplete", false);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        float size = View.MeasureSpec.getSize(i2) / TypedValue.applyDimension(1, 158.0f, BaseApplication.e().getResources().getDisplayMetrics());
        this.x = size;
        this.j.setCircleScale(size);
        this.h.setCircleScale(this.x);
        this.o.setCircleScale(this.x);
    }

    private void d() {
        LogUtil.d("ThreeCircleView", "initView");
        View inflate = LayoutInflater.from(this.n).inflate(R.layout.three_circle_view_layout, this);
        this.j = (CircleProgressBar) inflate.findViewById(R.id.circleProgressBar1);
        this.h = (CircleProgressBar) inflate.findViewById(R.id.circleProgressBar2);
        this.o = (CircleProgressBar) inflate.findViewById(R.id.circleProgressBar3);
        this.s = (ImageView) inflate.findViewById(R.id.circle_triangle);
        e();
    }

    private void a() {
        LogUtil.d("ThreeCircleView", "initView");
        View inflate = LayoutInflater.from(this.n).inflate(R.layout.three_circle_view_layout_icon, this);
        this.j = (CircleProgressBar) inflate.findViewById(R.id.icon_circleProgressBar1);
        this.h = (CircleProgressBar) inflate.findViewById(R.id.icon_circleProgressBar2);
        this.o = (CircleProgressBar) inflate.findViewById(R.id.icon_circleProgressBar3);
        this.s = (ImageView) inflate.findViewById(R.id.icon_circle_triangle);
        e();
    }

    private void e() {
        this.i.put("firstCircle", this.j);
        this.i.put("secondCircle", this.h);
        this.i.put("thirdCircle", this.o);
        c();
        c("firstCircle");
        c("secondCircle");
        c("thirdCircle");
    }

    private void cNe_(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.n.getTheme().obtainStyledAttributes(attributeSet, R$styleable.ThreeCircleView, i, 0);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == R$styleable.ThreeCircleView_isAnimator) {
                this.l = obtainStyledAttributes.getBoolean(index, false);
            } else if (index == R$styleable.ThreeCircleView_isIconVisible) {
                this.k = obtainStyledAttributes.getBoolean(index, true);
            } else if (index == R$styleable.ThreeCircleView_isShowOverlap) {
                this.r = obtainStyledAttributes.getBoolean(index, false);
            } else if (index == R$styleable.ThreeCircleView_isShowTriangle) {
                this.t = obtainStyledAttributes.getBoolean(index, false);
            } else {
                LogUtil.c("ThreeCircleView", "wrong attr. attr = ", Integer.valueOf(index));
            }
        }
        obtainStyledAttributes.recycle();
    }

    private void c(String str) {
        CircleProgressBar circleProgressBar = this.i.get(str);
        circleProgressBar.setCircleAngle(135.0f, 135.0f, 270.0f);
        circleProgressBar.setCircleWidth(17.0f);
        float applyDimension = TypedValue.applyDimension(1, 9.0f, BaseApplication.e().getResources().getDisplayMetrics());
        circleProgressBar.setStartIconRes(b(str), 16.0f, 16.0f, applyDimension, applyDimension);
        circleProgressBar.setBottomCircleGradientColor(e(str, "circleBackgroundColor"), null, ((Integer) d(str).get("colorDiff")).intValue());
        circleProgressBar.setCircleOverlap(this.r);
        circleProgressBar.setIsIconVisible(this.k);
        circleProgressBar.a();
    }

    public void c(String str, int i, int i2) {
        LogUtil.d("ThreeCircleView", "updateProgress, progress: ", Integer.valueOf(i), ", target: ", Integer.valueOf(i2), ", isAnimation: ", Boolean.valueOf(this.l));
        CircleProgressBar circleProgressBar = this.i.get(str);
        if (i2 == 0) {
            LogUtil.d("ThreeCircleView", "NOT draw progress.");
            circleProgressBar.a();
            return;
        }
        circleProgressBar.setCircleOverlap(this.r);
        circleProgressBar.setProgressGradientColor(e(str, "circleColor"), null, ((Integer) d(str).get("colorDiff")).intValue());
        float max = Math.max(((i * 1.0f) / i2) * 100.0f, 0.3f);
        if (this.l) {
            circleProgressBar.cEn_(max, new AccelerateDecelerateInterpolator(), a(max));
        } else {
            circleProgressBar.b(max);
        }
        e(str, i, i2);
    }

    private boolean d(int[] iArr) {
        return iArr == null || iArr.length != 2;
    }

    private int[] e(String str, String str2) {
        ntp ntpVar = this.f.get(str);
        if (ntpVar == null || d(ntpVar.e())) {
            return (int[]) d(str).get(str2);
        }
        if (TextUtils.equals(str2, "circleBackgroundColor")) {
            return ntpVar.d();
        }
        return ntpVar.e();
    }

    private int b(String str) {
        ntp ntpVar = this.f.get(str);
        if (ntpVar == null || ntpVar.a() == 0) {
            return ((Integer) d(str).get("icon")).intValue();
        }
        return ntpVar.a();
    }

    private Map<String, Object> d(String str) {
        char c2;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -1501317216) {
            if (str.equals("firstCircle")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode != -1172519324) {
            if (hashCode == -368098025 && str.equals("thirdCircle")) {
                c2 = 2;
            }
            c2 = 65535;
        } else {
            if (str.equals("secondCircle")) {
                c2 = 1;
            }
            c2 = 65535;
        }
        if (c2 == 0) {
            return this.m;
        }
        if (c2 == 1) {
            return this.p;
        }
        if (c2 == 2) {
            return this.q;
        }
        LogUtil.c("ThreeCircleView", "wrong circleIndex.");
        return this.m;
    }

    private void e(String str, int i, int i2) {
        if (this.t) {
            if (i < i2) {
                LogUtil.d("ThreeCircleView", "didn't complete. target = ", Integer.valueOf(i2), "; progress = ", Integer.valueOf(i));
                return;
            }
            d(str).put("isComplete", Boolean.valueOf(i >= i2));
            if (((Boolean) this.m.get("isComplete")).booleanValue() && ((Boolean) this.p.get("isComplete")).booleanValue() && ((Boolean) this.q.get("isComplete")).booleanValue()) {
                this.s.setVisibility(0);
                LogUtil.d("ThreeCircleView", "showTriangle");
            }
        }
    }
}
