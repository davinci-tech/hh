package com.huawei.pluginachievement.ui.kakatask;

import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.mfm;
import defpackage.mkg;
import defpackage.mkj;
import defpackage.mle;
import defpackage.nrt;
import defpackage.nsn;
import health.compact.a.UnitUtil;
import org.slf4j.Marker;

/* loaded from: classes8.dex */
public class AchieveKaKaTaskContentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f8440a;
    private boolean b;
    private RelativeLayout c;
    private AchieveKaKaTaskClickListener d;
    private LinearLayout e;
    private int f;
    private HealthTextView g;
    private ImageView h;
    private ImageView i;
    private int j;
    private ImageView k;
    private int l;
    private String m;
    private int n;
    private String o;
    private ImageView p;
    private HealthButton q;
    private LinearLayout r;
    private View s;
    private LinearLayout t;
    private HealthButton u;
    private String v;
    private ImageView w;
    private HealthTextView x;
    private HealthTextView y;

    AchieveKaKaTaskContentHolder(View view, AchieveKaKaTaskClickListener achieveKaKaTaskClickListener) {
        super(view);
        this.o = "1";
        this.v = "";
        this.m = "0";
        this.l = 0;
        this.r = (LinearLayout) mfm.cgM_(view, R.id.achieve_task_kaka_content_task_ll);
        this.x = (HealthTextView) mfm.cgM_(view, R.id.achieve_task_kaka_content_task_text);
        this.w = (ImageView) mfm.cgM_(view, R.id.achieve_task_kaka_content_task_tips_icon);
        this.g = (HealthTextView) mfm.cgM_(view, R.id.kaka_task_finish_tips);
        this.c = (RelativeLayout) mfm.cgM_(view, R.id.content_layout);
        this.k = (ImageView) mfm.cgM_(view, R.id.achieve_task_kaka_content_buy_tips_icon);
        this.t = (LinearLayout) mfm.cgM_(view, R.id.achieve_task_kaka_content_task_kaka_desc);
        this.y = (HealthTextView) mfm.cgM_(view, R.id.achieve_task_kaka_content_task_kaka_value);
        this.p = (ImageView) mfm.cgM_(view, R.id.achieve_task_kaka_content_finished_iv);
        this.s = mfm.cgM_(view, R.id.achieve_task_kaka_content_line);
        this.i = (ImageView) mfm.cgM_(view, R.id.kaka_task_arrow);
        this.h = (ImageView) mfm.cgM_(view, R.id.kaka_new_task_arrow);
        this.e = (LinearLayout) mfm.cgM_(view, R.id.back_kaka_mid_layout);
        this.f8440a = (LinearLayout) mfm.cgM_(view, R.id.arrow_layout);
        HealthButton healthButton = (HealthButton) mfm.cgM_(view, R.id.achieve_task_kaka_content_tag_button);
        this.q = healthButton;
        healthButton.setText(BaseApplication.getContext().getResources().getString(R.string._2130840766_res_0x7f020cbe));
        this.q.setOnClickListener(this);
        HealthButton healthButton2 = (HealthButton) mfm.cgM_(view, R.id.achieve_task_kaka_content_tag_emphasize_button);
        this.u = healthButton2;
        healthButton2.setText(BaseApplication.getContext().getResources().getString(R.string._2130840767_res_0x7f020cbf));
        this.p.setImageResource(nrt.a(BaseApplication.getContext()) ? R.drawable._2131427478_res_0x7f0b0096 : R.drawable._2131427477_res_0x7f0b0095);
        this.u.setOnClickListener(this);
        this.f8440a.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.d = achieveKaKaTaskClickListener;
    }

    private void e(mkj mkjVar) {
        this.v = mkjVar.e();
        String c = mkjVar.c();
        this.m = mkjVar.f();
        if (TextUtils.isEmpty(c)) {
            this.w.setVisibility(8);
        } else {
            this.w.setVisibility(0);
            this.w.setOnClickListener(this);
        }
        if (mle.d(mkjVar.n())) {
            this.o = "0";
        }
        if (a(mkjVar)) {
            this.k.setVisibility(0);
        } else {
            this.k.setVisibility(8);
        }
        this.r.setVisibility(0);
        this.x.setVisibility(0);
        this.s.setVisibility(0);
        this.x.setText(mkjVar.a());
        this.q.setText(mkjVar.g());
        this.u.setText(mkjVar.h());
        this.y.setVisibility(0);
        int b = mkjVar.b();
        this.y.setTypeface(Typeface.createFromAsset(BaseApplication.getContext().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf"));
        String e = UnitUtil.e(b, 1, 0);
        this.y.setText(Marker.ANY_NON_NULL_MARKER + e);
        if ("0".equals(this.m)) {
            this.p.setVisibility(8);
            if (mkjVar.n() != 10001) {
                this.q.setVisibility(0);
                this.u.setVisibility(8);
                return;
            }
            return;
        }
        if ("1".equals(this.m)) {
            this.p.setVisibility(8);
            this.u.setVisibility(0);
            this.q.setVisibility(8);
        } else {
            LogUtil.c("AchieveKaKaTaskContentHolder", "status:", this.m);
            this.p.setVisibility(0);
            this.q.setVisibility(8);
            this.u.setVisibility(8);
        }
    }

    private boolean a(mkj mkjVar) {
        int n = mkjVar.n();
        return n == 20009 || n == 30016 || n == 30017 || mkjVar.k() == 1;
    }

    public void e(mkg mkgVar, int i, int i2, int i3, boolean z) {
        if (mkgVar == null) {
            return;
        }
        this.b = z;
        this.j = nrt.a(BaseApplication.getContext()) ? R.mipmap._2131821342_res_0x7f11031e : R.mipmap._2131821341_res_0x7f11031d;
        this.f = nrt.a(BaseApplication.getContext()) ? R.mipmap._2131821339_res_0x7f11031b : R.mipmap._2131821338_res_0x7f11031a;
        mkj b = mkgVar.b();
        if (b != null) {
            this.l = i;
            e(b);
            e(b.n(), mkgVar.c(), i2, i3);
        } else {
            this.r.setVisibility(8);
            this.p.setVisibility(8);
            this.q.setVisibility(8);
            this.u.setVisibility(8);
            this.s.setVisibility(8);
        }
        if (mkgVar.j()) {
            this.e.setBackgroundResource(R.drawable.kaka_white_background_up);
        }
        if (mkgVar.c()) {
            this.e.setBackgroundResource(R.drawable.kaka_white_background_bottom);
        }
        if (mkgVar.j() && mkgVar.c()) {
            this.e.setBackgroundResource(R.drawable.kaka_task_middle_background);
            c(b);
        }
        if (mle.h(this.v)) {
            this.t.setVisibility(8);
            this.q.setText(BaseApplication.getContext().getResources().getString(R.string._2130840768_res_0x7f020cc0));
        }
    }

    private void c(mkj mkjVar) {
        if (mkjVar != null && mle.d(mkjVar.n()) && this.n == 1) {
            this.f8440a.setVisibility(8);
        }
    }

    private void e(int i, boolean z, int i2, int i3) {
        if (z) {
            if (mle.d(i)) {
                this.h.setVisibility(0);
                if (i2 == 1) {
                    this.h.setImageResource(this.j);
                    this.h.setTag(Integer.valueOf(this.j));
                    return;
                } else {
                    this.h.setImageResource(this.f);
                    this.h.setTag(Integer.valueOf(this.f));
                    return;
                }
            }
            this.i.setVisibility(0);
            if (i3 == 1) {
                this.i.setImageResource(this.j);
                this.i.setTag(Integer.valueOf(this.j));
                if (this.b) {
                    this.g.setVisibility(8);
                    this.c.setVisibility(0);
                    return;
                }
                return;
            }
            this.i.setImageResource(this.f);
            this.i.setTag(Integer.valueOf(this.f));
            if (this.b) {
                this.g.setVisibility(0);
                this.c.setVisibility(8);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        HealthButton healthButton = this.q;
        if (view == healthButton || view == this.u) {
            c(this.m, this.v, this.l);
        } else if (view == this.w) {
            c("3", this.v, this.l);
        } else if (view == this.f8440a || view == this.i || view == this.h) {
            d();
        } else {
            LogUtil.c("AchieveKaKaTaskContentHolder", "status:", healthButton);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        if ("0".equals(this.o)) {
            ciV_(this.h);
        } else {
            ciV_(this.i);
        }
    }

    private void ciV_(ImageView imageView) {
        if (imageView.getTag() instanceof Integer) {
            if (((Integer) imageView.getTag()).intValue() == this.f) {
                this.d.onTaskClick("4", this.o, 1);
            } else {
                this.d.onTaskClick("4", this.o, 0);
            }
        }
    }

    private void c(String str, String str2, int i) {
        if (nsn.a(1000)) {
            LogUtil.h("AchieveKaKaTaskContentHolder", "button click too fast");
        } else {
            this.d.onTaskClick(str, str2, i);
        }
    }

    public void a(int i) {
        this.n = i;
    }
}
