package com.huawei.health.suggestion.ui.plan.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder;
import com.huawei.health.suggestion.ui.plan.viewholder.IntPlanListViewHolder;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.pluginfitnessadvice.pricetagbean.PriceTagBean;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.fyw;
import defpackage.koq;
import defpackage.mmw;
import defpackage.mod;
import defpackage.nrf;
import defpackage.nsn;
import java.util.List;

/* loaded from: classes4.dex */
public class IntPlanListViewHolder extends AbsFitnessViewHolder<Object> implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private String f3291a;
    private boolean b;
    private ImageView c;
    private Context d;
    private ImageView e;
    private IntPlan.PlanType f;
    private boolean g;
    private HealthTextView h;
    private String i;
    private HealthSubHeader j;
    private HealthTextView o;

    public IntPlanListViewHolder(View view, String str) {
        super(view);
        this.b = false;
        this.g = false;
        this.d = view.getContext();
        ImageView imageView = (ImageView) view.findViewById(R.id.fit_plan_pkg_bg);
        this.e = imageView;
        imageView.setOnClickListener(this);
        this.o = (HealthTextView) view.findViewById(R.id.fit_plan_name);
        this.h = (HealthTextView) view.findViewById(R.id.fit_plan_description);
        this.c = (ImageView) view.findViewById(R.id.fit_plan_fitness_corner);
        this.f3291a = str;
        this.j = (HealthSubHeader) view.findViewById(R.id.fit_plan_period);
        if (nsn.s()) {
            nsn.b(this.o);
            nsn.b(this.h);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        IntPlan.PlanType planType;
        if (nsn.o()) {
            LogUtil.h("Suggestion_PlanFitnessViewHolder", "click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.fit_plan_pkg_bg && (planType = this.f) != null) {
            if (planType.equals(IntPlan.PlanType.AI_RUN_PLAN) || this.f.equals(IntPlan.PlanType.AI_FITNESS_PLAN)) {
                LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: gbe
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        IntPlanListViewHolder.this.b(i, obj);
                    }
                }, "");
            } else {
                JumpUtil.d(this.f.getType(), this.i, this.f3291a, this.d);
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void b(int i, Object obj) {
        if (i == 0) {
            JumpUtil.d(this.f.getType(), this.i, this.f3291a, this.d);
        } else {
            LogUtil.a("Suggestion_PlanFitnessViewHolder", "LoginInit is not success", Integer.valueOf(i));
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
    public void init(Object obj) {
        String str;
        List<PriceTagBean> list;
        if (obj == null) {
            return;
        }
        if (obj instanceof mmw) {
            mmw mmwVar = (mmw) obj;
            this.o.setText(mmwVar.i());
            this.h.setText(mmwVar.e().trim());
            if (nsn.s()) {
                nsn.b(this.h);
            }
            this.f = IntPlan.PlanType.getPlanType(IntPlan.PlanType.AI_RUN_PLAN.getType());
            this.i = String.valueOf(mmwVar.c());
            str = mmwVar.d();
            list = mmwVar.getPriceTagBeanList();
            LogUtil.a("Suggestion_PlanFitnessViewHolder", "mPlanTempId = ", this.i);
            b();
        } else if (obj instanceof FitnessPackageInfo) {
            FitnessPackageInfo fitnessPackageInfo = (FitnessPackageInfo) obj;
            this.o.setText(fitnessPackageInfo.acquireName());
            this.h.setText(fitnessPackageInfo.acquireDescription());
            this.f = IntPlan.PlanType.getPlanType(fitnessPackageInfo.getPlanType());
            this.i = fitnessPackageInfo.acquirePlanTempId();
            List<PriceTagBean> priceTagBeanList = fitnessPackageInfo.getPriceTagBeanList();
            String acquirePicture = fitnessPackageInfo.acquirePicture();
            a();
            str = acquirePicture;
            list = priceTagBeanList;
        } else {
            LogUtil.h("Suggestion_PlanFitnessViewHolder", "invalid data.");
            return;
        }
        nrf.cIS_(this.e, str, nrf.d, 0, R$color.common_ui_custom_dialog_transparent_bg);
        if (koq.c(list)) {
            this.c.setVisibility(0);
            nrf.cIK_(mod.b(list), this.c, 0.0f, nrf.d, 0.0f, 0.0f);
        } else {
            this.c.setVisibility(8);
        }
    }

    private void a() {
        HealthSubHeader healthSubHeader = this.j;
        if (healthSubHeader == null) {
            LogUtil.h("Suggestion_PlanFitnessViewHolder", "setFitnessPlanSubHeader mPlanPeriod is null.");
            return;
        }
        healthSubHeader.setVisibility(0);
        this.j.setSubHeaderBackgroundColor(ContextCompat.getColor(this.itemView.getContext(), R.color._2131296971_res_0x7f0902cb));
        if (!fyw.d()) {
            this.j.setHeadTitleText(this.d.getString(R.string._2130845663_res_0x7f021fdf));
            c();
        } else {
            this.j.setVisibility(8);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x0091, code lost:
    
        if (r0.equals("3") == false) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b() {
        /*
            r8 = this;
            com.huawei.ui.commonui.subheader.HealthSubHeader r0 = r8.j
            if (r0 != 0) goto L10
            java.lang.String r0 = "setHealthSubHeaderStyle mPlanPeriod is null."
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "Suggestion_PlanFitnessViewHolder"
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)
            return
        L10:
            r1 = 0
            r0.setVisibility(r1)
            com.huawei.ui.commonui.subheader.HealthSubHeader r0 = r8.j
            android.view.View r2 = r8.itemView
            android.content.Context r2 = r2.getContext()
            r3 = 2131296971(0x7f0902cb, float:1.8211874E38)
            int r2 = androidx.core.content.ContextCompat.getColor(r2, r3)
            r0.setSubHeaderBackgroundColor(r2)
            boolean r0 = defpackage.fyw.d()
            r2 = 8
            r3 = 4
            if (r0 != 0) goto L5d
            r8.c()
            boolean r0 = r8.b
            if (r0 == 0) goto L4a
            com.huawei.ui.commonui.subheader.HealthSubHeader r0 = r8.j
            r0.setVisibility(r1)
            com.huawei.ui.commonui.subheader.HealthSubHeader r0 = r8.j
            android.content.Context r1 = r8.d
            r2 = 2130848558(0x7f022b2e, float:1.7302384E38)
            java.lang.String r1 = r1.getString(r2)
            r0.setHeadTitleText(r1)
            goto L5c
        L4a:
            com.huawei.ui.commonui.subheader.HealthSubHeader r0 = r8.j
            android.content.Context r1 = r8.d
            boolean r1 = defpackage.nsn.ag(r1)
            if (r1 == 0) goto L59
            boolean r1 = r8.g
            if (r1 == 0) goto L59
            r2 = r3
        L59:
            r0.setVisibility(r2)
        L5c:
            return
        L5d:
            java.lang.String r0 = r8.i
            r0.hashCode()
            int r4 = r0.hashCode()
            r5 = 51
            r6 = 2
            r7 = 1
            if (r4 == r5) goto L8b
            r1 = 1568(0x620, float:2.197E-42)
            if (r4 == r1) goto L80
            r1 = 1569(0x621, float:2.199E-42)
            if (r4 == r1) goto L75
            goto L93
        L75:
            java.lang.String r1 = "12"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L7e
            goto L93
        L7e:
            r1 = r6
            goto L94
        L80:
            java.lang.String r1 = "11"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L89
            goto L93
        L89:
            r1 = r7
            goto L94
        L8b:
            java.lang.String r4 = "3"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L94
        L93:
            r1 = -1
        L94:
            if (r1 == 0) goto Lcc
            if (r1 == r7) goto Lbd
            if (r1 == r6) goto Lae
            android.content.Context r0 = r8.d
            boolean r0 = defpackage.nsn.ag(r0)
            if (r0 == 0) goto La8
            com.huawei.ui.commonui.subheader.HealthSubHeader r0 = r8.j
            r0.setVisibility(r3)
            goto Lda
        La8:
            com.huawei.ui.commonui.subheader.HealthSubHeader r0 = r8.j
            r0.setVisibility(r2)
            goto Lda
        Lae:
            com.huawei.ui.commonui.subheader.HealthSubHeader r0 = r8.j
            android.content.Context r1 = r8.d
            r2 = 2130850461(0x7f02329d, float:1.7306244E38)
            java.lang.String r1 = r1.getString(r2)
            r0.setHeadTitleText(r1)
            goto Lda
        Lbd:
            com.huawei.ui.commonui.subheader.HealthSubHeader r0 = r8.j
            android.content.Context r1 = r8.d
            r2 = 2130850459(0x7f02329b, float:1.730624E38)
            java.lang.String r1 = r1.getString(r2)
            r0.setHeadTitleText(r1)
            goto Lda
        Lcc:
            com.huawei.ui.commonui.subheader.HealthSubHeader r0 = r8.j
            android.content.Context r1 = r8.d
            r2 = 2130850460(0x7f02329c, float:1.7306242E38)
            java.lang.String r1 = r1.getString(r2)
            r0.setHeadTitleText(r1)
        Lda:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.ui.plan.viewholder.IntPlanListViewHolder.b():void");
    }

    private void c() {
        if (this.j.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.j.getLayoutParams();
            layoutParams.leftMargin = this.d.getResources().getDimensionPixelSize(R.dimen._2131363161_res_0x7f0a0559);
            layoutParams.rightMargin = this.d.getResources().getDimensionPixelSize(R.dimen._2131363161_res_0x7f0a0559);
            layoutParams.topMargin = this.d.getResources().getDimensionPixelSize(R.dimen._2131363161_res_0x7f0a0559);
            this.j.setLayoutParams(layoutParams);
        }
    }

    public void a(boolean z) {
        this.b = z;
    }

    public void b(boolean z) {
        this.g = z;
    }
}
