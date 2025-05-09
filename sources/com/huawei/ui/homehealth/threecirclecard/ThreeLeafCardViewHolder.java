package com.huawei.ui.homehealth.threecirclecard;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.view.Clover;
import com.huawei.ui.homehealth.threecirclecard.ThreeLeafCardViewHolder;
import defpackage.dsl;
import defpackage.dsm;
import defpackage.jfa;
import defpackage.nkx;
import defpackage.nrt;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsk;
import defpackage.nsn;
import defpackage.oun;
import defpackage.owb;
import defpackage.owc;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class ThreeLeafCardViewHolder extends ThreeCircleCardViewHolder {
    private HealthTextView ab;
    private boolean ad;
    private LinearLayout ae;
    private LinearLayout af;
    private HealthTextView ag;
    private ImageView ah;
    private HealthTextView ai;
    private boolean aj;
    private boolean ak;
    private Clover al;
    private LinearLayout am;
    private boolean an;
    private owb ao;
    private HealthTextView ap;
    private HealthTextView aq;
    private ImageView ar;
    private HealthTextView as;
    private HealthTextView at;
    private LinearLayout au;
    private LinearLayout av;
    private HealthTextView aw;
    private HealthTextView ax;

    @Override // com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardViewHolder
    protected void dip_(View view) {
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardViewHolder, com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void setThreeCircleDataListener(int i, View.OnClickListener onClickListener) {
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardViewHolder, com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void setThreeCircleLayoutListener(View.OnClickListener onClickListener) {
    }

    public ThreeLeafCardViewHolder(View view, Context context, boolean z) {
        super(view, context, z);
        View findViewById;
        this.ad = true;
        ViewStub viewStub = (ViewStub) view.findViewById(R.id.three_leaf_layout);
        if (viewStub.getParent() != null) {
            findViewById = viewStub.inflate();
        } else {
            findViewById = view.findViewById(R.id.three_leaf_layout_inflated);
        }
        if (findViewById instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) findViewById;
            frameLayout.setVisibility(0);
            this.al = (Clover) frameLayout.findViewById(R.id.view_task_status_clover);
        }
        this.ab = (HealthTextView) view.findViewById(R.id.activity_hours);
        this.q.setBackground(getContext().getResources().getDrawable(R.drawable._2131430055_res_0x7f0b0aa7));
        this.ao = new owb(this.e, this.f9626a, this.k, this.o, this.ak);
    }

    public void e(int i, String str, int i2) {
        this.p.setVisibility(0);
        this.w.setVisibility(8);
        this.q.setBackground(getContext().getResources().getDrawable(R.drawable._2131430055_res_0x7f0b0aa7));
        this.r.setText(getContext().getResources().getString(i));
        this.t.setText(str);
        this.s.setText(getContext().getResources().getString(i2));
        n();
    }

    public void c(int i, int i2, boolean z) {
        Drawable drawable;
        this.p.setVisibility(8);
        this.w.setVisibility(0);
        this.r.setText(getContext().getResources().getString(i));
        this.aw.setText(getContext().getResources().getString(i2));
        if (z) {
            this.ar.setVisibility(8);
            if (nrt.a(getContext())) {
                if (LanguageUtil.bc(getContext())) {
                    drawable = nrz.cKn_(getContext(), R.drawable._2131430340_res_0x7f0b0bc4);
                } else {
                    drawable = getContext().getResources().getDrawable(R.drawable._2131430340_res_0x7f0b0bc4);
                }
            } else if (LanguageUtil.bc(getContext())) {
                drawable = nrz.cKn_(getContext(), R.drawable._2131430339_res_0x7f0b0bc3);
            } else {
                drawable = getContext().getResources().getDrawable(R.drawable._2131430339_res_0x7f0b0bc3);
            }
            drawable.setBounds(0, 0, getContext().getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532), getContext().getResources().getDimensionPixelSize(R.dimen._2131362922_res_0x7f0a046a));
            this.aw.setCompoundDrawablesRelative(null, null, drawable, null);
            return;
        }
        this.ar.setVisibility(8);
    }

    public void d(int i, int i2, int i3) {
        this.v.setBackground(getContext().getResources().getDrawable(R.drawable._2131430047_res_0x7f0b0a9f));
        this.y.setText(getContext().getResources().getString(i));
        getTimeText().setText(UnitUtil.e(i2, 1, 0));
        String a2 = nsf.a(i3, i2, "");
        this.aa.setText(a2.startsWith(" ") ? a2.substring(1) : a2);
        LogUtil.a(this.c, "bindThreeCircleSportLayout value: ", Integer.valueOf(i2), a2);
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardViewHolder, com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void setFitnessDataOriginIcon2Visible(int i) {
        this.l.setVisibility(i);
    }

    public void a(int i) {
        this.b.setBackground(getContext().getResources().getDrawable(R.drawable._2131430046_res_0x7f0b0a9e));
        f().setText(getContext().getResources().getString(i));
    }

    public void d(int i, int i2, String str, String str2, int i3) {
        this.b.setBackground(getContext().getResources().getDrawable(R.drawable._2131430046_res_0x7f0b0a9e));
        f().setText(getContext().getResources().getString(i));
        this.am.setVisibility(0);
        this.af.setVisibility(8);
        this.ae.setVisibility(8);
        this.ap.setText(getContext().getResources().getString(i2));
        this.aq.setText(str);
        this.as.setText(str2);
        this.as.setTextColor(i3);
        n();
    }

    public void d(int i, String str) {
        this.am.setVisibility(8);
        this.af.setVisibility(0);
        this.ae.setVisibility(8);
        this.m.setText(UnitUtil.e(i, 1, 0));
        this.ai.setText(str);
        n();
    }

    public void b(int i) {
        Drawable drawable;
        this.am.setVisibility(8);
        this.af.setVisibility(8);
        this.ae.setVisibility(0);
        if (nrt.a(getContext())) {
            if (LanguageUtil.bc(getContext())) {
                drawable = nrz.cKn_(getContext(), R.drawable._2131430340_res_0x7f0b0bc4);
            } else {
                drawable = getContext().getResources().getDrawable(R.drawable._2131430340_res_0x7f0b0bc4);
            }
        } else if (LanguageUtil.bc(getContext())) {
            drawable = nrz.cKn_(getContext(), R.drawable._2131430339_res_0x7f0b0bc3);
        } else {
            drawable = getContext().getResources().getDrawable(R.drawable._2131430339_res_0x7f0b0bc3);
        }
        this.ag.setText(getContext().getResources().getString(i));
        drawable.setBounds(0, 0, getContext().getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532), getContext().getResources().getDimensionPixelSize(R.dimen._2131362922_res_0x7f0a046a));
        this.ag.setCompoundDrawablesRelative(null, null, drawable, null);
    }

    public void g() {
        this.av.setVisibility(8);
        this.au.setVisibility(0);
        String h = nsf.h(R.string._2130846519_res_0x7f022337);
        String b = nsf.b(R.string._2130846280_res_0x7f022248, h);
        int indexOf = b.indexOf(h);
        if (indexOf <= -1) {
            this.at.setText(b);
            return;
        }
        SpannableString spannableString = new SpannableString(b);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(BaseApplication.e(), R.color._2131296927_res_0x7f09029f)), indexOf, h.length() + indexOf, 17);
        this.at.setText(spannableString);
    }

    public void c(int i) {
        if (i == 0) {
            this.av.setVisibility(0);
            this.o.setVisibility(0);
            this.au.setVisibility(8);
        } else {
            this.av.setVisibility(8);
            this.o.setVisibility(8);
            this.au.setVisibility(0);
            g();
            jfa.b(dsm.c, "perfect_three_leaf", 0L);
        }
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardViewHolder
    protected void dio_(LinearLayout linearLayout) {
        this.av = linearLayout;
        ViewStub viewStub = (ViewStub) this.itemView.findViewById(R.id.three_leaf_default_layout);
        if (viewStub.getParent() != null) {
            this.au = (LinearLayout) viewStub.inflate();
        } else {
            this.au = (LinearLayout) this.itemView.findViewById(R.id.three_leaf_default_layout_inflated);
        }
        this.au.setVisibility(8);
        this.ax = (HealthTextView) this.au.findViewById(R.id.default_health_model_title);
        this.at = (HealthTextView) this.au.findViewById(R.id.default_health_model_des);
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardViewHolder
    protected void din_(LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4) {
        linearLayout.setVisibility(8);
        linearLayout2.setVisibility(8);
        linearLayout3.setVisibility(8);
        linearLayout4.setVisibility(8);
        this.am = linearLayout2;
        this.af = linearLayout3;
        this.ae = linearLayout4;
        o();
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardViewHolder
    protected void b() {
        this.aw = (HealthTextView) this.w.findViewById(R.id.sleep_text);
        this.ar = (ImageView) this.w.findViewById(R.id.sleep_array);
        a(this.aw);
    }

    private void a(HealthTextView healthTextView) {
        if (nsn.r()) {
            healthTextView.setTextSize(1, 21.0f);
        } else {
            healthTextView.setTextSize(2, 12.0f);
        }
    }

    private void o() {
        Typeface cKN_ = nsk.cKN_();
        this.ap = (HealthTextView) this.am.findViewById(R.id.pressure_title);
        HealthTextView healthTextView = (HealthTextView) this.am.findViewById(R.id.pressure_value);
        this.aq = healthTextView;
        healthTextView.setTypeface(cKN_);
        this.as = (HealthTextView) this.am.findViewById(R.id.pressure_des);
        this.m = (HealthTextView) this.af.findViewById(R.id.breath_count_value);
        this.m.setTypeface(cKN_);
        this.ai = (HealthTextView) this.af.findViewById(R.id.breath_count_des);
        this.ag = (HealthTextView) this.ae.findViewById(R.id.breath_train);
        this.ah = (ImageView) this.ae.findViewById(R.id.breath_array);
        a(this.ag);
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardViewHolder
    protected void h() {
        dij_(this.am, this.as, false);
        dij_(this.af, this.ai, false);
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardViewHolder, com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public ImageView getFitnessDataOriginIcon() {
        return this.n;
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardViewHolder, com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public LinearLayout getFitnessDataOriginIconLayout() {
        return this.l;
    }

    public Clover l() {
        return this.al;
    }

    public HealthTextView f() {
        return this.ab;
    }

    public LinearLayout dim_() {
        return this.au;
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardViewHolder
    public void a() {
        this.h.setText(getContext().getString(R.string._2130846475_res_0x7f02230b));
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable._2131430064_res_0x7f0b0ab0);
        if (LanguageUtil.bc(getContext())) {
            BitmapDrawable cKm_ = nrz.cKm_(getContext(), drawable);
            this.g.setBackground(cKm_);
            this.k.diH_(cKm_);
        } else {
            this.g.setBackground(drawable);
            this.k.diH_(drawable);
        }
        setBottomClickListener(nkx.cwZ_(new View.OnClickListener() { // from class: ove
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ThreeLeafCardViewHolder.this.diq_(view);
            }
        }, (BaseActivity) getContext(), true, ""));
        this.k.d(getContext().getString(R.string._2130846475_res_0x7f02230b));
        this.k.d(R.dimen._2131365063_res_0x7f0a0cc7);
    }

    public /* synthetic */ void diq_(View view) {
        dsl.ZK_(getContext(), Uri.parse("").buildUpon().appendQueryParameter("from", "cloverLife").build());
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardViewHolder, com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void updateProgress(int i, int i2, int i3, boolean z) {
        LogUtil.a(this.c, "updateProgress not need refresh ui");
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardViewHolder, com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void setTextByPosition(int i, int i2, int i3) {
        LogUtil.a(this.c, "setTextByPosition not need refresh ui");
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardViewHolder, com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void updateBottomData(int i, int i2) {
        LogUtil.a(this.c, "updateBottomData curValue=", Integer.valueOf(i), "  goal=", Integer.valueOf(i2));
        String e = UnitUtil.e(i2, 1, 0);
        String e2 = UnitUtil.e(i, 1, 0);
        SpannableString dii_ = dii_(nsf.b(R.string._2130846476_res_0x7f02230c, e2, e), e2);
        this.k.a(dii_);
        owb owbVar = this.ao;
        if (owbVar != null && !owbVar.a()) {
            LogUtil.h(this.c, "has showing message animate");
            return;
        }
        owb owbVar2 = this.ao;
        LinearLayout diK_ = owbVar2 == null ? this.e : owbVar2.diK_();
        HealthTextView healthTextView = (HealthTextView) diK_.findViewById(R.id.bottom_step_value);
        if (healthTextView == null) {
            LogUtil.h(this.c, "updateBottomData bottomValue is null, return");
            return;
        }
        healthTextView.setText(dii_);
        healthTextView.setVisibility(0);
        boolean dic_ = dic_(diK_);
        this.ak = dic_;
        owb owbVar3 = this.ao;
        if (owbVar3 != null) {
            owbVar3.b(dic_);
        }
    }

    public void b(List<owc> list) {
        if (this.an) {
            LogUtil.h(this.c, "setMessageList page is destroyed");
        } else {
            this.ao.c(list, this.aj && "threeLeafCard".equals(oun.a()));
        }
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardViewHolder, com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void setBottomClickListener(View.OnClickListener onClickListener) {
        this.e.setOnClickListener(onClickListener);
        this.k.diG_(onClickListener);
    }

    @Override // com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder
    public void updateLeafCardShowStatus(boolean z) {
        super.updateLeafCardShowStatus(z);
        this.aj = z;
        if (!"threeLeafCard".equals(oun.a())) {
            LogUtil.h(this.c, "updateLeafCardShowStatus  Three leaf card not show");
        } else {
            if (this.an) {
                return;
            }
            if (z) {
                this.ao.b();
            } else {
                this.ao.c();
            }
        }
    }

    public void d(boolean z) {
        if (z) {
            this.ao.b();
        } else {
            this.ao.c();
        }
    }

    public void m() {
        this.an = true;
        this.ao.d();
    }

    private void n() {
        if (nsn.p() || !LanguageUtil.h(getContext())) {
            LogUtil.a(this.c, "wrapLineForThreeLeaf");
            this.x.setOrientation(1);
            this.z.setOrientation(1);
            this.am.setOrientation(1);
            this.af.setOrientation(1);
            this.p.setOrientation(1);
        }
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardViewHolder
    protected String d() {
        return "ThreeLeafCardViewHolder";
    }
}
