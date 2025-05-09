package com.huawei.health.suggestion.ui.run.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.PlanInfo;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.suggestion.FitnessExternalUtils;
import com.huawei.health.suggestion.callback.JudgeCallback;
import com.huawei.health.suggestion.ui.plan.viewholder.IntPlanListViewHolder;
import com.huawei.health.suggestion.ui.run.activity.RunPlanCreateActivity;
import com.huawei.health.suggestion.ui.run.adapter.PlanInfoAdapter;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.health.suggestion.util.ResourceJudgeUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.pluginfitnessadvice.pricetagbean.PriceTagBean;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.gge;
import defpackage.jdm;
import defpackage.koq;
import defpackage.mmw;
import defpackage.mod;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class PlanInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private boolean f3358a = false;
    private NoTitleCustomAlertDialog b;
    private List<FitnessPackageInfo> c;
    private Context d;
    private List<PlanInfo> e;
    private int g;
    private List<mmw> h;

    static class e extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        ImageView f3359a;
        HealthTextView b;
        ImageView c;
        HealthTextView d;
        View e;

        e(View view) {
            super(view);
            this.f3359a = (ImageView) view.findViewById(R.id.sug_fitness_pkg_bg);
            this.c = (ImageView) view.findViewById(R.id.sug_fitness_corner);
            this.b = (HealthTextView) view.findViewById(R.id.sug_fitness_name);
            this.d = (HealthTextView) view.findViewById(R.id.sug_fitness_description);
            this.e = view.findViewById(R.id.fitness_pkg_interval);
        }
    }

    public PlanInfoAdapter(List<FitnessPackageInfo> list, int i, Context context) {
        this.d = context;
        this.c = list;
        this.g = i;
        c();
    }

    public PlanInfoAdapter(int i, List<mmw> list, Context context) {
        this.d = context;
        this.h = list;
        this.g = i;
        c();
    }

    private void c() {
        d();
    }

    private void d() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.d);
        builder.e(R$string.IDS_sport_notify_update_app);
        builder.czC_(R$string.IDS_update_new_version_to_upgrade_app, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.PlanInfoAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!jdm.b(PlanInfoAdapter.this.d, "com.huawei.appmarket")) {
                    nrh.b(PlanInfoAdapter.this.d, com.huawei.ui.commonui.R$string.IDS_main_sns_app_store_content);
                } else {
                    Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=" + BaseApplication.d()));
                    intent.addFlags(268435456);
                    intent.setPackage("com.huawei.appmarket");
                    nsn.cLM_(intent, "com.huawei.appmarket", PlanInfoAdapter.this.d, nsf.h(com.huawei.ui.commonui.R$string.IDS_device_fragment_application_market));
                }
                PlanInfoAdapter.this.b.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czz_(R$string.IDS_plugin_fitnessadvice_cancal, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.PlanInfoAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.b = builder.e();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.g == 3 && FitnessExternalUtils.a()) {
            return new IntPlanListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sug_layout_intplan_recommend_list_item, viewGroup, false), "fitness");
        }
        return new e(LayoutInflater.from(BaseApplication.e()).inflate(R.layout.sug_plan_type_item, viewGroup, false));
    }

    private void a(final HealthTextView healthTextView) {
        if (healthTextView == null) {
            return;
        }
        healthTextView.post(new Runnable() { // from class: com.huawei.health.suggestion.ui.run.adapter.PlanInfoAdapter.4
            @Override // java.lang.Runnable
            public void run() {
                TextPaint paint = healthTextView.getPaint();
                if (paint == null || healthTextView.getText() == null || paint.measureText(healthTextView.getText().toString()) < healthTextView.getMeasuredWidth() * healthTextView.getMaxLines() * 0.85f) {
                    return;
                }
                if (nsn.r()) {
                    healthTextView.setMaxLines(2);
                    healthTextView.setTextSize(2, 10.0f);
                } else {
                    healthTextView.setMaxLines(3);
                    healthTextView.setTextSize(1, 10.0f);
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof e) {
            e eVar = (e) viewHolder;
            int i2 = this.g;
            if (i2 == 3) {
                if (koq.b(this.c, i)) {
                    return;
                }
                a(eVar, this.c.get(i));
                return;
            } else if (i2 == 0) {
                if (koq.b(this.h, i)) {
                    return;
                }
                a(eVar, this.h.get(i));
                return;
            } else if (koq.b(this.e, i)) {
                LogUtil.h("Suggestion_PlanInfoAdapter", "onBindViewHolder, mPlanInfoList out of bounds. position = ", Integer.valueOf(i));
                return;
            } else {
                d(eVar, i);
                return;
            }
        }
        if (viewHolder instanceof IntPlanListViewHolder) {
            ((IntPlanListViewHolder) viewHolder).a(this.c.get(0));
        }
    }

    private void d(e eVar, int i) {
        if (koq.b(this.e, i)) {
            LogUtil.h("Suggestion_PlanInfoAdapter", "ArrayIndexOutOfBoundsException");
            return;
        }
        PlanInfo planInfo = this.e.get(i);
        int type = planInfo.getType();
        if (type == 0) {
            if (planInfo instanceof mmw) {
                a(eVar, (mmw) planInfo);
            }
        } else {
            if (type == 3) {
                if (planInfo instanceof FitnessPackageInfo) {
                    a(eVar, (FitnessPackageInfo) planInfo);
                    return;
                }
                return;
            }
            LogUtil.h("Suggestion_PlanInfoAdapter", "planType unSupport");
        }
    }

    private void a(e eVar, mmw mmwVar) {
        if (eVar == null || mmwVar == null) {
            LogUtil.h("Suggestion_PlanInfoAdapter", "holder == null || info == null");
            return;
        }
        eVar.e.setBackgroundColor(this.d.getResources().getColor(R$color.common_ui_custom_dialog_transparent_bg));
        eVar.b.setText(mmwVar.i());
        eVar.d.setText(mmwVar.e().trim());
        if (nsn.s()) {
            nsn.b(eVar.d);
        }
        aLb_(eVar.c, mmwVar.getPriceTagBeanList());
        a(eVar.d);
        if (mmwVar.a() != 0) {
            aLc_(eVar.f3359a, mmwVar.a());
        } else {
            nrf.cJh_(mmwVar.d(), eVar.f3359a, nrf.d);
        }
        c(eVar, mmwVar);
    }

    private void aLb_(ImageView imageView, List<PriceTagBean> list) {
        if (imageView == null) {
            return;
        }
        if (koq.c(list) && !TextUtils.isEmpty(mod.b(list))) {
            imageView.setVisibility(0);
            nrf.cIK_(mod.b(list), imageView, 0.0f, nrf.d, 0.0f, 0.0f);
        } else {
            imageView.setVisibility(8);
        }
    }

    private void c(e eVar, final mmw mmwVar) {
        eVar.f3359a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.PlanInfoAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view == null) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                if (nsn.o()) {
                    LogUtil.h("Suggestion_PlanInfoAdapter", "mBackgroundPicture click too fast.");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.a("Suggestion_PlanInfoAdapter", "info.getCommodityFlag()", Integer.valueOf(mmwVar.getCommodityFlag()));
                if (!CommonUtil.aa(PlanInfoAdapter.this.d)) {
                    Toast.makeText(PlanInfoAdapter.this.d, R.string._2130839508_res_0x7f0207d4, 0).show();
                } else if (mmwVar.getCommodityFlag() != 2 && !FitnessExternalUtils.e()) {
                    PlanInfoAdapter.this.c(mmwVar);
                } else if (mmwVar.getCommodityFlag() != 2 || FitnessExternalUtils.e()) {
                    PlanInfoAdapter.this.a();
                } else {
                    PlanInfoAdapter.this.e(mmwVar);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final mmw mmwVar) {
        ResourceJudgeUtil.c(5, String.valueOf(mmwVar.c()), 3, new JudgeCallback() { // from class: gdi
            @Override // com.huawei.health.suggestion.callback.JudgeCallback
            public final void onJudgeCallback(Object obj) {
                PlanInfoAdapter.this.e(mmwVar, (Integer) obj);
            }
        });
    }

    public /* synthetic */ void e(mmw mmwVar, Integer num) {
        if (num.intValue() == 3 || num.intValue() == 2) {
            b();
        } else {
            c(mmwVar);
        }
    }

    private void b() {
        JumpUtil.e(IntPlan.PlanType.AI_RUN_PLAN.getType(), "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_PlanInfoAdapter", "bindRunPlanInfo, getCurrentPlan : planApi is null.");
        } else {
            planApi.setPlanType(0);
            planApi.checkAllowCreateOldPlan(new UiCallback<Boolean>() { // from class: com.huawei.health.suggestion.ui.run.adapter.PlanInfoAdapter.1
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.c("Suggestion_PlanInfoAdapter", "checkAllowCreateOldPlan()  failed errorCode=", Integer.valueOf(i));
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Boolean bool) {
                    if (bool != null) {
                        PlanInfoAdapter.this.i();
                    } else {
                        ReleaseLogUtil.c("Suggestion_PlanInfoAdapter", "checkAllowCreateOldPlan() onSuccess plan is null");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(mmw mmwVar) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null || mmwVar == null) {
            LogUtil.b("Suggestion_PlanInfoAdapter", "bindRunPlanInfo, getCurrentPlan : planApi is null.");
            return;
        }
        ReleaseLogUtil.e("Suggestion_PlanInfoAdapter", "createIntelligentPlan runpaln Name:", mmwVar.i());
        IntPlan currentIntPlan = planApi.getCurrentIntPlan();
        if (currentIntPlan != null) {
            boolean z = (Utils.o() || mmwVar.h() == null || !koq.c(mmwVar.h().acquireIntroductionImgs())) ? false : true;
            if (TextUtils.equals(currentIntPlan.getPlanTempId(), String.valueOf(mmwVar.c()))) {
                JumpUtil.c(this.d);
                return;
            } else {
                if (z) {
                    JumpUtil.c(this.d, String.valueOf(mmwVar.c()), IntPlan.PlanType.AI_RUN_PLAN.getType());
                    return;
                }
                JumpUtil.a(mmwVar, String.valueOf(mmwVar.c()), this.d, true);
                return;
            }
        }
        d(mmwVar);
    }

    private void d(mmw mmwVar) {
        LogUtil.c("Suggestion_PlanInfoAdapter", "isShowPrivacyTipsDialog enter");
        if (!Utils.o()) {
            b(mmwVar);
        } else {
            JumpUtil.d(mmwVar, this.d);
        }
    }

    private void b(mmw mmwVar) {
        HashMap hashMap = new HashMap(5);
        hashMap.put("click", 1);
        hashMap.put("type", 2);
        hashMap.put("planType", 0);
        hashMap.put("planName", mmwVar.i());
        hashMap.put("enter", "0");
        gge.e(AnalyticsValue.HEALTH_HOME_TRAINING_PROGRAM_PIC_2010036.value(), hashMap);
        Intent intent = new Intent(this.d, (Class<?>) RunPlanCreateActivity.class);
        intent.putExtra("runPlanTitle", mmwVar.i());
        intent.putExtra("runPlanType", mmwVar.c());
        intent.setFlags(268435456);
        aLd_(intent);
    }

    private void aLd_(Intent intent) {
        if (intent == null) {
            LogUtil.h("Suggestion_PlanInfoAdapter", "startActivity intent is null.");
            return;
        }
        try {
            this.d.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("Suggestion_PlanInfoAdapter", "startActivity ActivityNotFoundException.");
        }
    }

    private void aLc_(ImageView imageView, int i) {
        if (!LanguageUtil.bc(this.d)) {
            nrf.cIP_(imageView, i, nrf.d, 0, 0);
        } else {
            nrf.cIO_(nrz.cKn_(this.d, i), imageView, nrf.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.b;
        if (noTitleCustomAlertDialog == null) {
            LogUtil.h("Suggestion_PlanInfoAdapter", "showTipsDialog() mCheckAllowCreateOldPlanDialog is null");
        } else {
            noTitleCustomAlertDialog.show();
        }
    }

    private void a(e eVar, final FitnessPackageInfo fitnessPackageInfo) {
        if (eVar == null || fitnessPackageInfo == null) {
            return;
        }
        eVar.e.setBackgroundColor(this.d.getResources().getColor(R$color.common_ui_custom_dialog_transparent_bg));
        nrf.cIS_(eVar.f3359a, fitnessPackageInfo.acquirePicture(), nrf.d, 0, R$color.common_ui_custom_dialog_transparent_bg);
        aLb_(eVar.c, fitnessPackageInfo.getPriceTagBeanList());
        eVar.b.setText(fitnessPackageInfo.acquireName());
        eVar.d.setText(fitnessPackageInfo.acquireDescription());
        eVar.f3359a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.adapter.PlanInfoAdapter.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlanInfoAdapter.this.a(fitnessPackageInfo);
                HashMap hashMap = new HashMap(5);
                hashMap.put("click", 1);
                hashMap.put("type", 2);
                hashMap.put("planType", 1);
                hashMap.put("planName", fitnessPackageInfo.acquireName());
                hashMap.put("enter", "1");
                gge.e(AnalyticsValue.HEALTH_HOME_TRAINING_PROGRAM_PIC_2010036.value(), hashMap);
                if (PlanInfoAdapter.this.e()) {
                    hashMap.clear();
                    hashMap.put("click", 1);
                    gge.e(AnalyticsValue.HEALTH_WEIGHT_TRAINING_PROGRAM_PIC_2030053.value(), hashMap);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(FitnessPackageInfo fitnessPackageInfo) {
        JumpUtil.b(fitnessPackageInfo, fitnessPackageInfo.acquirePlanTempId(), this.d);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = this.g;
        if (i == 3) {
            return this.c.size();
        }
        if (i == 0) {
            return this.h.size();
        }
        if (koq.b(this.e)) {
            return 0;
        }
        return this.e.size();
    }

    public boolean e() {
        return this.f3358a;
    }
}
