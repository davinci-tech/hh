package com.huawei.healthcloud.plugintrack.ui.viewholder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.view.BloodOxygenRangeView;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.anchor.Layout;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthBaseCombinedChart;
import defpackage.gvv;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/* loaded from: classes4.dex */
public class TrackChartViewHolder extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3821a;
    private Context b;
    private LinearLayout c;
    private HwHealthBaseCombinedChart d;
    private int e;
    private RelativeLayout f;
    private LinearLayout g;
    private ImageView h;
    private int i;
    private boolean j;
    private HealthTextView k;
    private HealthTextView l;
    private HealthTextView m;
    private HealthTextView n;
    private HealthTextView o;
    private HealthTextView q;
    private HealthTextView s;
    private HealthTextView t;

    private boolean b(float f) {
        return f < 1.0f;
    }

    public TrackChartViewHolder(Context context, int i, boolean z, int i2) {
        super(context);
        this.b = null;
        this.e = 0;
        this.j = false;
        this.i = 100;
        this.g = null;
        this.l = null;
        this.t = null;
        this.s = null;
        this.m = null;
        this.k = null;
        this.o = null;
        this.n = null;
        this.q = null;
        this.d = null;
        this.f3821a = null;
        this.c = null;
        this.h = null;
        c(context, i, z, i2);
    }

    public TrackChartViewHolder(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = null;
        this.e = 0;
        this.j = false;
        this.i = 100;
        this.g = null;
        this.l = null;
        this.t = null;
        this.s = null;
        this.m = null;
        this.k = null;
        this.o = null;
        this.n = null;
        this.q = null;
        this.d = null;
        this.f3821a = null;
        this.c = null;
        this.h = null;
        c(context, 100, false, 0);
    }

    private void c(Context context, int i, boolean z, int i2) {
        this.b = context;
        this.i = i;
        this.e = i2;
        this.j = z;
        f();
        if (z) {
            this.f3821a.setVisibility(8);
            Resources resources = getResources();
            if (this.i == 100) {
                int color = resources.getColor(R.color._2131296919_res_0x7f090297);
                int color2 = resources.getColor(R.color._2131296910_res_0x7f09028e);
                this.t.setTextColor(color2);
                this.t.setTypeface(Typeface.create(this.b.getString(R.string._2130851581_res_0x7f0236fd), 0));
                this.s.setTextColor(color2);
                this.m.setTextColor(color2);
                this.k.setTextColor(color);
                this.l.setTextColor(color2);
                this.n.setTextColor(color2);
                this.o.setTextColor(color);
                this.q.setTextColor(color2);
            } else {
                int color3 = resources.getColor(R.color._2131296996_res_0x7f0902e4);
                int color4 = resources.getColor(R.color._2131296871_res_0x7f090267);
                this.t.setTextColor(resources.getColor(R.color._2131299236_res_0x7f090ba4));
                this.s.setTextColor(resources.getColor(R.color._2131299236_res_0x7f090ba4));
                this.m.setTextColor(color4);
                this.k.setTextColor(color3);
                this.l.setTextColor(color4);
                this.n.setTextColor(color4);
                this.o.setTextColor(color3);
                this.q.setTextColor(color4);
            }
            this.n.setVisibility(8);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f.getLayoutParams();
            if (marginLayoutParams != null) {
                marginLayoutParams.bottomMargin = nsn.c(this.b, 3.5f);
                marginLayoutParams.height = -2;
                this.f.setLayoutParams(marginLayoutParams);
            }
            ViewGroup.LayoutParams layoutParams = this.d.getLayoutParams();
            layoutParams.height = nsn.c(this.b, 149.0f);
            this.d.setLayoutParams(layoutParams);
        }
        x();
        l();
    }

    private void f() {
        View inflate = View.inflate(this.b, R.layout.layout_track_chart_viewholder, this);
        this.g = (LinearLayout) inflate.findViewById(R.id.layout_track_detail_curve_max_avg);
        this.l = (HealthTextView) findViewById(R.id.text_track_detail_left_value);
        this.t = (HealthTextView) findViewById(R.id.text_curve_title);
        this.s = (HealthTextView) findViewById(R.id.text_curve_title_unit);
        this.m = (HealthTextView) findViewById(R.id.text_track_detail_left_unit);
        this.k = (HealthTextView) findViewById(R.id.text_track_detail_left_title);
        this.o = (HealthTextView) findViewById(R.id.text_track_detail_right_title);
        this.n = (HealthTextView) findViewById(R.id.text_track_detail_right_unit);
        this.q = (HealthTextView) findViewById(R.id.text_track_detail_right_value);
        this.f3821a = (HealthTextView) findViewById(R.id.text_curve_detail);
        this.h = (ImageView) findViewById(R.id.img_explain_icon);
        HwHealthBaseCombinedChart hwHealthBaseCombinedChart = (HwHealthBaseCombinedChart) findViewById(R.id.combined_chart);
        this.d = hwHealthBaseCombinedChart;
        Layout acquireLayout = hwHealthBaseCombinedChart.acquireLayout();
        if (acquireLayout != null) {
            acquireLayout.c(0.0f, 0.0f);
            acquireLayout.b(0.0f);
        }
        this.f = (RelativeLayout) findViewById(R.id.rl_title_parent);
        this.c = (LinearLayout) findViewById(R.id.track_extended_view);
        blH_(inflate);
    }

    private void blH_(View view) {
        if (nsn.l()) {
            boolean ag = nsn.ag(this.b);
            LogUtil.a("Track_TrackChartViewHolder", "inflateView, isTahitiModel: ", Boolean.valueOf(ag));
            ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.heart_rate_right_data);
            if (viewGroup != null && (viewGroup.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewGroup.getLayoutParams();
                if (ag) {
                    layoutParams.setMarginStart(nsn.c(this.b, 12.0f));
                } else {
                    layoutParams.setMarginStart(nsn.c(this.b, 34.0f));
                }
                viewGroup.setLayoutParams(layoutParams);
                return;
            }
            LogUtil.h("Track_TrackChartViewHolder", "inflateView, viewGroup is null, ", "or layoutParams isn't the instance of LinearLauout.LayoutParams");
        }
    }

    public void b() {
        f();
    }

    public void blJ_(View view) {
        LinearLayout linearLayout;
        if (view == null || (linearLayout = this.c) == null) {
            return;
        }
        linearLayout.addView(view);
        this.c.setVisibility(0);
    }

    public void a() {
        LinearLayout linearLayout = this.c;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
            this.c.setVisibility(8);
        }
    }

    private void x() {
        setBaseChart(this.e);
        setDeviceChart(this.e);
        setBoltChart(this.e);
        setBasketballChart(this.e);
        setSwimmingChart(this.e);
    }

    private void setSwimmingChart(int i) {
        if (i == 4) {
            ae();
        } else if (i == 5) {
            w();
        } else {
            if (i != 6) {
                return;
            }
            ah();
        }
    }

    private void setBasketballChart(int i) {
        if (i == 11) {
            u();
        } else {
            if (i != 12) {
                return;
            }
            y();
        }
    }

    private void setDeviceChart(int i) {
        if (i == 9) {
            ac();
            return;
        }
        if (i == 10) {
            z();
            return;
        }
        if (i != 124) {
            switch (i) {
                case 13:
                    h();
                    return;
                case 14:
                    break;
                case 15:
                    v();
                    return;
                case 16:
                    aa();
                    return;
                case 17:
                    m();
                    return;
                default:
                    switch (i) {
                        case 24:
                            ad();
                            break;
                        case 26:
                            n();
                            break;
                    }
                    return;
            }
            setPaddleFreqChart(i == 25);
            return;
        }
        t();
    }

    private void setBoltChart(int i) {
        if (i == 7) {
            o();
            return;
        }
        if (i != 8) {
            switch (i) {
                case 18:
                    r();
                    break;
                case 19:
                    q();
                    break;
                case 20:
                    j();
                    break;
                case 21:
                    k();
                    break;
                case 22:
                    ai();
                    break;
                case 23:
                    af();
                    break;
            }
        }
        p();
    }

    private void setBaseChart(int i) {
        if (i == 0) {
            i();
            return;
        }
        if (i == 1) {
            s();
            return;
        }
        if (i == 2) {
            ag();
        } else if (i == 3) {
            ab();
        } else {
            if (i != 100) {
                return;
            }
            g();
        }
    }

    private void t() {
        this.t.setText(R.string._2130839938_res_0x7f020982);
        this.s.setText(String.format(Locale.ROOT, this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getString(R.string.IDS_main_watch_heart_rate_unit_string)));
        this.o.setText(getResources().getString(R.string._2130840019_res_0x7f0209d3, getResources().getString(R.string._2130838285_res_0x7f02030d), getResources().getString(R.string._2130838286_res_0x7f02030e)));
        this.n.setVisibility(8);
    }

    private void y() {
        this.t.setText(R.string._2130843145_res_0x7f021609);
        if (UnitUtil.h()) {
            this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getString(R.string._2130841897_res_0x7f021129)));
        } else {
            this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getString(R.string._2130841416_res_0x7f020f48)));
        }
        this.k.setText(R.string._2130843146_res_0x7f02160a);
        this.o.setText(R.string._2130843147_res_0x7f02160b);
        this.m.setVisibility(8);
        this.n.setVisibility(8);
    }

    private void u() {
        this.t.setText(R.string._2130843148_res_0x7f02160c);
        this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getString(R.string._2130842713_res_0x7f021459)));
        this.n.setVisibility(8);
        this.k.setText(R.string._2130843150_res_0x7f02160e);
        this.o.setText(R.string._2130843151_res_0x7f02160f);
        this.m.setVisibility(8);
        this.n.setVisibility(8);
    }

    private void z() {
        this.t.setText(R.string.IDS_hw_health_blood_oxygen);
        this.s.setVisibility(8);
        this.k.setText(R.string._2130843091_res_0x7f0215d3);
        this.o.setText(R.string._2130843092_res_0x7f0215d4);
        this.m.setVisibility(8);
        this.n.setVisibility(8);
        if (this.j) {
            return;
        }
        a();
        blJ_(new BloodOxygenRangeView(getContext()));
    }

    private void ac() {
        String str;
        this.t.setText(R.string._2130844083_res_0x7f0219b3);
        this.k.setText(R.string._2130839844_res_0x7f020924);
        this.o.setText(R.string._2130839911_res_0x7f020967);
        if (UnitUtil.h()) {
            str = "/" + getResources().getString(R.string._2130844081_res_0x7f0219b1);
        } else {
            str = "/" + getResources().getString(R.string._2130844082_res_0x7f0219b2);
        }
        this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), str));
        this.m.setVisibility(8);
        this.n.setVisibility(8);
    }

    private void m() {
        this.t.setText(R.string._2130844083_res_0x7f0219b3);
        this.k.setText(R.string._2130839844_res_0x7f020924);
        this.o.setText(R.string._2130839911_res_0x7f020967);
        if (UnitUtil.h()) {
            this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100)));
        } else {
            this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 500, 500)));
        }
        this.m.setVisibility(8);
        this.n.setVisibility(8);
    }

    private void n() {
        this.t.setText(nsf.h(R.string._2130844083_res_0x7f0219b3));
        this.k.setText(nsf.h(R.string._2130839844_res_0x7f020924));
        this.o.setText(nsf.h(R.string._2130839911_res_0x7f020967));
        this.s.setText(String.format(nsf.h(R.string._2130839866_res_0x7f02093a), nsf.a(R.plurals._2130903225_res_0x7f0300b9, 500, 500)));
        this.m.setVisibility(8);
        this.n.setVisibility(8);
    }

    private void p() {
        this.t.setText(R.string._2130842712_res_0x7f021458);
        this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getString(R.string._2130842716_res_0x7f02145c)));
        this.m.setVisibility(8);
        this.n.setVisibility(8);
        this.k.setText(R.string._2130842891_res_0x7f02150b);
        this.o.setText(R.string._2130842892_res_0x7f02150c);
    }

    private void j() {
        c();
        this.m.setVisibility(8);
        this.n.setVisibility(8);
        this.k.setText(R.string._2130845177_res_0x7f021df9);
        this.o.setText(R.string._2130845179_res_0x7f021dfb);
    }

    private void c() {
        if (LanguageUtil.b(this.b)) {
            HealthTextView healthTextView = this.t;
            Context context = this.b;
            String string = context.getString(R.string._2130845176_res_0x7f021df8);
            Context context2 = this.b;
            healthTextView.setText(context.getString(R.string._2130845986_res_0x7f022122, string, context2.getString(R.string._2130839866_res_0x7f02093a, context2.getString(R.string._2130845180_res_0x7f021dfc))));
            this.s.setText("");
            return;
        }
        this.t.setText(R.string._2130845176_res_0x7f021df8);
        this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getString(R.string._2130845180_res_0x7f021dfc)));
    }

    private void o() {
        this.t.setText(R.string._2130842710_res_0x7f021456);
        this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getString(R.string._2130842713_res_0x7f021459)));
        this.m.setVisibility(8);
        this.n.setVisibility(8);
        this.k.setText(R.string._2130842889_res_0x7f021509);
        this.o.setText(R.string._2130842890_res_0x7f02150a);
    }

    private void r() {
        this.t.setText(R.string._2130843148_res_0x7f02160c);
        this.s.setText(this.b.getString(R.string._2130839866_res_0x7f02093a, getResources().getString(R.string._2130842713_res_0x7f021459)));
        this.m.setVisibility(8);
        this.n.setVisibility(8);
        this.k.setText(R.string._2130843150_res_0x7f02160e);
        this.o.setText(R.string._2130843151_res_0x7f02160f);
    }

    private void k() {
        this.t.setText(R.string._2130845165_res_0x7f021ded);
        this.s.setText(this.b.getString(R.string._2130839866_res_0x7f02093a, Character.valueOf(new DecimalFormatSymbols(Locale.getDefault()).getPercent())));
        this.m.setVisibility(8);
        this.n.setVisibility(8);
        if (LanguageUtil.b(this.b)) {
            this.o.setText(R.string._2130845166_res_0x7f021dee);
            this.k.setText(R.string._2130845167_res_0x7f021def);
        } else {
            this.k.setText(R.string._2130845166_res_0x7f021dee);
            this.o.setText(R.string._2130845167_res_0x7f021def);
        }
    }

    private void ai() {
        this.t.setText(R.string._2130845218_res_0x7f021e22);
        this.s.setText(this.b.getString(R.string._2130839866_res_0x7f02093a, Character.valueOf(new DecimalFormatSymbols(Locale.getDefault()).getPercent())));
        this.m.setVisibility(8);
        this.n.setVisibility(8);
        this.k.setText(R.string._2130845170_res_0x7f021df2);
        this.o.setText(R.string._2130845548_res_0x7f021f6c);
    }

    private void af() {
        String string;
        this.t.setText(R.string._2130845168_res_0x7f021df0);
        if (!UnitUtil.h()) {
            string = this.b.getResources().getString(R.string._2130841416_res_0x7f020f48);
        } else {
            string = this.b.getResources().getString(R.string._2130841897_res_0x7f021129);
        }
        this.s.setText(this.b.getString(R.string._2130839866_res_0x7f02093a, string));
        this.m.setVisibility(8);
        this.n.setVisibility(8);
        this.k.setText(R.string._2130845171_res_0x7f021df3);
        this.o.setText(R.string._2130845172_res_0x7f021df4);
    }

    private void q() {
        this.t.setText(R.string._2130843723_res_0x7f02184b);
        this.s.setVisibility(8);
        this.m.setVisibility(8);
        this.n.setVisibility(8);
        this.k.setText(R.string._2130843726_res_0x7f02184e);
        this.o.setText(R.string._2130843727_res_0x7f02184f);
    }

    private void g() {
        this.t.setText(R.string._2130842548_res_0x7f0213b4);
        this.k.setText(R.string._2130842888_res_0x7f021508);
        this.o.setText(R.string._2130842887_res_0x7f021507);
        if (UnitUtil.h()) {
            this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getString(R.string._2130841417_res_0x7f020f49)));
        } else {
            this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getString(R.string._2130841568_res_0x7f020fe0)));
        }
        this.m.setVisibility(8);
        this.n.setVisibility(8);
    }

    private void ah() {
        this.t.setText(R.string._2130844083_res_0x7f0219b3);
        this.k.setText(R.string._2130839844_res_0x7f020924);
        this.o.setText(R.string._2130839911_res_0x7f020967);
        if (UnitUtil.h()) {
            this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getQuantityString(R.plurals._2130903226_res_0x7f0300ba, 100, 100)));
        } else {
            this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getQuantityString(R.plurals._2130903225_res_0x7f0300b9, 100, 100)));
        }
        this.n.setVisibility(8);
        this.m.setVisibility(8);
    }

    private void w() {
        this.t.setText(R.string._2130844080_res_0x7f0219b0);
        this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getQuantityString(R.plurals._2130903224_res_0x7f0300b8, 0)));
        this.m.setVisibility(8);
        this.n.setVisibility(8);
        this.k.setText(R.string._2130839848_res_0x7f020928);
        this.o.setText(R.string._2130839847_res_0x7f020927);
    }

    private void ae() {
        this.t.setText(R.string._2130844077_res_0x7f0219ad);
        this.s.setVisibility(8);
        this.k.setText(R.string._2130839846_res_0x7f020926);
        this.o.setText(R.string._2130839836_res_0x7f02091c);
        this.m.setVisibility(8);
        this.n.setVisibility(8);
    }

    private void ab() {
        this.t.setText(R.string._2130844076_res_0x7f0219ac);
        this.m.setVisibility(8);
        this.n.setVisibility(8);
        this.k.setText(R.string._2130839845_res_0x7f020925);
        this.o.setText(R.string._2130842157_res_0x7f02122d);
        if (UnitUtil.h()) {
            this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getString(R.string._2130844079_res_0x7f0219af)));
        } else {
            this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getString(R.string._2130844078_res_0x7f0219ae)));
        }
    }

    private void i() {
        this.t.setText(R.string._2130842326_res_0x7f0212d6);
        this.k.setText(R.string._2130842325_res_0x7f0212d5);
        this.o.setText(R.string._2130842545_res_0x7f0213b1);
        if (UnitUtil.h()) {
            this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getString(R.string._2130841417_res_0x7f020f49)));
        } else {
            this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getString(R.string._2130841568_res_0x7f020fe0)));
        }
        this.m.setVisibility(8);
        this.n.setVisibility(8);
    }

    private void ag() {
        this.t.setText(getResources().getString(R.string._2130844075_res_0x7f0219ab));
        this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getQuantityString(R.plurals._2130903288_res_0x7f0300f8, 0)));
        this.k.setText(R.string._2130839753_res_0x7f0208c9);
        this.m.setVisibility(8);
        this.o.setText(R.string._2130839752_res_0x7f0208c8);
        this.n.setVisibility(8);
    }

    private void h() {
        this.t.setText(getResources().getString(R.string._2130843486_res_0x7f02175e));
        this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getString(R.string._2130843487_res_0x7f02175f)));
        this.k.setText(getResources().getString(R.string._2130843488_res_0x7f021760));
        this.m.setVisibility(8);
        this.o.setText(getResources().getString(R.string._2130843490_res_0x7f021762));
        this.n.setVisibility(8);
    }

    private void setPaddleFreqChart(boolean z) {
        this.t.setText(getResources().getString(z ? R.string._2130845951_res_0x7f0220ff : R.string._2130843496_res_0x7f021768));
        this.g.setVisibility(z ? 8 : 0);
        this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getString(R.string._2130843497_res_0x7f021769)));
        this.k.setText(getResources().getString(R.string._2130843498_res_0x7f02176a));
        this.m.setVisibility(8);
        this.o.setText(getResources().getString(R.string._2130843499_res_0x7f02176b));
        this.n.setVisibility(8);
    }

    private void ad() {
        this.t.setText(getResources().getString(R.string._2130845943_res_0x7f0220f7));
        HealthTextView healthTextView = this.s;
        Locale locale = Locale.ROOT;
        String string = this.b.getString(R.string._2130839866_res_0x7f02093a);
        Object[] objArr = new Object[1];
        objArr[0] = getResources().getString(UnitUtil.h() ? R.string._2130841559_res_0x7f020fd7 : R.string._2130845952_res_0x7f022100);
        healthTextView.setText(String.format(locale, string, objArr));
        this.o.setVisibility(8);
        this.n.setVisibility(8);
        this.q.setVisibility(8);
        this.k.setText(getResources().getString(R.string._2130845949_res_0x7f0220fd));
        this.m.setVisibility(8);
    }

    private void aa() {
        this.t.setText(R.string._2130844076_res_0x7f0219ac);
        this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getString(R.string._2130843710_res_0x7f02183e)));
        this.m.setVisibility(8);
        this.n.setVisibility(8);
        this.k.setText(R.string._2130839845_res_0x7f020925);
        this.o.setText(R.string._2130842157_res_0x7f02122d);
    }

    private void v() {
        this.t.setText(getResources().getString(R.string._2130843491_res_0x7f021763));
        this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getString(R.string._2130843492_res_0x7f021764)));
        this.k.setText(getResources().getString(R.string._2130843493_res_0x7f021765));
        this.m.setVisibility(8);
        this.o.setText(getResources().getString(R.string._2130843494_res_0x7f021766));
        this.n.setVisibility(8);
    }

    private void s() {
        this.t.setText(R.string._2130841430_res_0x7f020f56);
        this.s.setText(String.format(this.b.getString(R.string._2130839866_res_0x7f02093a), getResources().getString(R.string.IDS_main_watch_heart_rate_unit_string)));
        this.m.setVisibility(8);
        this.n.setVisibility(8);
        this.k.setText(R.string._2130839500_res_0x7f0207cc);
        this.o.setText(R.string._2130839495_res_0x7f0207c7);
    }

    private void d(float f) {
        if (UnitUtil.h()) {
            double d = f;
            this.l.setText(UnitUtil.e(UnitUtil.e(d, 1), 1, 2));
            this.m.setText(getResources().getQuantityString(R.plurals._2130903238_res_0x7f0300c6, (int) Math.round(UnitUtil.e(d, 1))));
            return;
        }
        this.l.setText(UnitUtil.e(f, 1, 1));
        this.m.setText(getResources().getString(R.string._2130841568_res_0x7f020fe0));
    }

    public void c(float f) {
        setLeftValueSingleNumberType(f);
        int i = this.e;
        if (i == 0) {
            d(f);
            if (b(f)) {
                this.l.setVisibility(4);
                this.m.setVisibility(4);
                this.k.setVisibility(4);
                return;
            }
            return;
        }
        if (i == 3) {
            a(f, this.l, this.m, R.string._2130844079_res_0x7f0219af, R.string._2130844078_res_0x7f0219ae);
            return;
        }
        if (i == 100) {
            d(f);
        } else if (i == 124) {
            setRecoveryHeartRateLeftValue(f);
        } else {
            setTextLeftValue(f);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void setTextLeftValue(float r6) {
        /*
            r5 = this;
            int r0 = r5.e
            r1 = 26
            if (r0 == r1) goto L4e
            r1 = 1
            r2 = 0
            switch(r0) {
                case 6: goto L4e;
                case 7: goto L43;
                case 8: goto L43;
                case 9: goto L4e;
                case 10: goto L25;
                case 11: goto L1a;
                case 12: goto Lf;
                default: goto Lb;
            }
        Lb:
            switch(r0) {
                case 17: goto L4e;
                case 18: goto L43;
                case 19: goto L3b;
                case 20: goto L3b;
                case 21: goto L31;
                case 22: goto L3b;
                case 23: goto L3b;
                default: goto Le;
            }
        Le:
            goto L4d
        Lf:
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r5.l
            double r3 = (double) r6
            java.lang.String r6 = health.compact.a.UnitUtil.e(r3, r1, r2)
            r0.setText(r6)
            goto L4d
        L1a:
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r5.l
            double r3 = (double) r6
            java.lang.String r6 = health.compact.a.UnitUtil.e(r3, r1, r2)
            r0.setText(r6)
            goto L4d
        L25:
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r5.l
            double r3 = (double) r6
            r6 = 2
            java.lang.String r6 = health.compact.a.UnitUtil.e(r3, r6, r2)
            r0.setText(r6)
            goto L4d
        L31:
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r5.l
            r1 = 21
            com.huawei.ui.commonui.healthtextview.HealthTextView r2 = r5.q
            r5.b(r6, r0, r1, r2)
            goto L4d
        L3b:
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r5.l
            r1 = 20
            r5.b(r6, r0, r1, r0)
            goto L4d
        L43:
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r5.l
            double r3 = (double) r6
            java.lang.String r6 = health.compact.a.UnitUtil.e(r3, r1, r2)
            r0.setText(r6)
        L4d:
            return
        L4e:
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r5.l
            java.lang.String r6 = defpackage.gvv.a(r6)
            r0.setText(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.healthcloud.plugintrack.ui.viewholder.TrackChartViewHolder.setTextLeftValue(float):void");
    }

    private void b(float f, HealthTextView healthTextView, int i, HealthTextView healthTextView2) {
        double d = f;
        healthTextView.setText(UnitUtil.e(d, 1, 1));
        if (LanguageUtil.b(getContext()) && i == 21) {
            healthTextView2.setText(UnitUtil.e(d, 1, 1));
        }
    }

    private void a(float f, HealthTextView healthTextView, HealthTextView healthTextView2, int i, int i2) {
        if (UnitUtil.h()) {
            healthTextView.setText(UnitUtil.e(UnitUtil.e(f, 3), 1, 2));
            healthTextView2.setText(getResources().getString(i));
        } else {
            healthTextView.setText(UnitUtil.e(f, 1, 2));
            healthTextView2.setText(getResources().getString(i2));
        }
    }

    private void setLeftValueSingleNumberType(float f) {
        int i = this.e;
        if (i != 1 && i != 2 && i != 4 && i != 5 && i != 24 && i != 25) {
            switch (i) {
            }
        }
        this.l.setText(UnitUtil.e(f, 1, 0));
    }

    private void setRecoveryHeartRateLeftValue(float f) {
        String e = UnitUtil.e((int) Math.floor(Math.abs(f)), 1, 0);
        if (f >= 0.0f) {
            this.k.setText(getResources().getString(R.string._2130840016_res_0x7f0209d0, ""));
        } else {
            this.k.setText(getResources().getString(R.string._2130840015_res_0x7f0209cf, ""));
        }
        this.m.setVisibility(8);
        this.l.setText(e);
    }

    private void a(float f) {
        if (UnitUtil.h()) {
            double d = f;
            this.q.setText(UnitUtil.e(UnitUtil.e(d, 1), 1, 2));
            this.n.setText(getResources().getQuantityString(R.plurals._2130903238_res_0x7f0300c6, (int) Math.round(UnitUtil.e(d, 1))));
            return;
        }
        this.q.setText(UnitUtil.e(f, 1, 1));
        this.n.setText(getResources().getString(R.string._2130841568_res_0x7f020fe0));
    }

    public void e(float f) {
        int i = this.e;
        if (i == 0) {
            a(f);
            if (b(f)) {
                this.q.setVisibility(4);
                this.o.setVisibility(4);
            }
            this.n.setVisibility(8);
            return;
        }
        if (i == 3) {
            a(f, this.q, this.n, R.string._2130844079_res_0x7f0219af, R.string._2130844078_res_0x7f0219ae);
            this.n.setVisibility(8);
        } else if (i == 100) {
            a(f);
            this.n.setVisibility(8);
        } else {
            setRightValueAndUnitVisible(f);
        }
    }

    private void setRightValueAndUnitVisible(float f) {
        switch (this.e) {
            case 1:
            case 2:
            case 4:
            case 5:
            case 13:
            case 14:
            case 15:
            case 16:
            case 25:
                this.q.setText(UnitUtil.e(f, 1, 0));
                this.n.setVisibility(8);
                break;
            case 3:
            case 11:
            case 12:
            case 24:
            default:
                setRightValueAndUnitVisibleOne(f);
                break;
            case 6:
            case 9:
            case 17:
            case 26:
                this.q.setText(gvv.a(f));
                this.n.setVisibility(8);
                break;
            case 7:
            case 8:
            case 18:
                this.q.setText(UnitUtil.e(f, 1, 0));
                this.n.setVisibility(8);
                break;
            case 10:
                this.q.setText(UnitUtil.e(f, 2, 0));
                this.n.setVisibility(8);
                break;
            case 19:
            case 20:
            case 22:
            case 23:
                HealthTextView healthTextView = this.q;
                b(f, healthTextView, 19, healthTextView);
                this.n.setVisibility(8);
                break;
            case 21:
                b(f, this.q, 21, this.l);
                this.n.setVisibility(8);
                break;
        }
    }

    private void setRightValueAndUnitVisibleOne(float f) {
        int i = this.e;
        if (i == 11 || i == 12) {
            this.q.setText(UnitUtil.e(f, 1, 0));
            this.n.setVisibility(8);
        }
    }

    public void a(int i, int i2) {
        if (this.e == 124) {
            this.q.setText(getResources().getString(R.string._2130840019_res_0x7f0209d3, UnitUtil.e(i, 1, 0), UnitUtil.e(i2, 1, 0)));
        }
    }

    public HwHealthBaseCombinedChart getCombinedChart() {
        return this.d;
    }

    public HealthTextView d() {
        return this.f3821a;
    }

    public ImageView blI_() {
        return this.h;
    }

    private void l() {
        this.t.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.healthcloud.plugintrack.ui.viewholder.TrackChartViewHolder.5
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (TrackChartViewHolder.this.s.getWidth() + TrackChartViewHolder.this.t.getWidth() + TrackChartViewHolder.this.h.getWidth() + TrackChartViewHolder.this.f3821a.getWidth() + nsn.c(TrackChartViewHolder.this.b, 18.0f) <= TrackChartViewHolder.this.f.getWidth()) {
                    return;
                }
                if (TrackChartViewHolder.this.j) {
                    TrackChartViewHolder.this.s.setTextSize(0, nsn.c(TrackChartViewHolder.this.b, 12.0f));
                    TrackChartViewHolder.this.t.setTextSize(0, nsn.c(TrackChartViewHolder.this.b, 12.0f));
                } else {
                    float textSize = TrackChartViewHolder.this.s.getTextSize();
                    float textSize2 = TrackChartViewHolder.this.t.getTextSize();
                    TrackChartViewHolder.this.s.setTextSize(0, textSize - 1.0f);
                    TrackChartViewHolder.this.t.setTextSize(0, textSize2 - 1.0f);
                }
            }
        });
    }
}
