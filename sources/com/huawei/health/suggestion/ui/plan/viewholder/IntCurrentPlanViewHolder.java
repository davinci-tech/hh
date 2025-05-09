package com.huawei.health.suggestion.ui.plan.viewholder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.StatInfo;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder;
import com.huawei.health.suggestion.ui.plan.viewholder.IntCurrentPlanViewHolder;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.ase;
import defpackage.ffy;
import defpackage.ffz;
import defpackage.gge;
import defpackage.ggu;
import defpackage.grz;
import defpackage.ixx;
import defpackage.moe;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class IntCurrentPlanViewHolder extends AbsFitnessViewHolder<Object> {

    /* renamed from: a, reason: collision with root package name */
    private Context f3288a;
    private HealthTextView b;
    private RelativeLayout c;
    private RelativeLayout d;
    private IntPlan e;
    private ImageView f;
    private HealthButton g;
    private HealthProgressBar h;
    private HealthTextView i;
    private HealthTextView j;
    private LinearLayout k;
    private RelativeLayout l;
    private RelativeLayout o;

    public IntCurrentPlanViewHolder(final View view, int i) {
        super(view);
        this.f3288a = view.getContext();
        this.j = (HealthTextView) view.findViewById(R.id.plan_name);
        this.g = (HealthButton) view.findViewById(R.id.plan_report_button);
        this.b = (HealthTextView) view.findViewById(R.id.plan_completed_days_and_rate);
        this.h = (HealthProgressBar) view.findViewById(R.id.sug_current_plan_progress_item_horizontal);
        this.i = (HealthTextView) view.findViewById(R.id.plan_goal_content);
        this.d = (RelativeLayout) view.findViewById(R.id.goal_line);
        this.f = (ImageView) view.findViewById(R.id.report_dot);
        this.k = (LinearLayout) view.findViewById(R.id.statistic_data_layout);
        this.c = (RelativeLayout) view.findViewById(R.id.statistic_first_data);
        this.o = (RelativeLayout) view.findViewById(R.id.statistic_second_data);
        this.l = (RelativeLayout) view.findViewById(R.id.statistic_third_data);
        if (i != 0) {
            view.setOnClickListener(new View.OnClickListener() { // from class: gay
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    IntCurrentPlanViewHolder.this.aJH_(view, view2);
                }
            });
        }
    }

    public /* synthetic */ void aJH_(View view, View view2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        gge.e(AnalyticsValue.HEALTH_WEIGHT_MY_TRAINING_PROGRAM_PIC_2030055.value(), hashMap);
        ase.d(this.e, "", 1, 1, 1);
        JumpUtil.c(view.getContext());
        ViewClickInstrumentation.clickOnView(view2);
    }

    private void c(IntPlan intPlan) {
        RelativeLayout relativeLayout = this.c;
        if (relativeLayout == null) {
            LogUtil.h("Suggestion_IntCurrentPlanViewHolder", "mFirstStatisticLayout null.");
            return;
        }
        HealthTextView healthTextView = (HealthTextView) relativeLayout.findViewById(R.id.statistic_data_name);
        HealthTextView healthTextView2 = (HealthTextView) this.c.findViewById(R.id.statistic_data_unit);
        HealthTextView healthTextView3 = (HealthTextView) this.c.findViewById(R.id.statistic_data_value);
        if (ase.l(intPlan)) {
            healthTextView.setText(this.f3288a.getString(R.string._2130844187_res_0x7f021a1b));
            b(intPlan, healthTextView2, healthTextView3);
        } else {
            if (ase.f(intPlan)) {
                healthTextView.setText(this.f3288a.getString(R.string._2130848530_res_0x7f022b12));
                StatInfo stat = intPlan.getStat(StatInfo.STAT_TYPE_PUNCH_DAY);
                int intValue = (stat == null || !(stat.getValue() instanceof Integer)) ? 0 : ((Integer) stat.getValue()).intValue();
                healthTextView2.setText(this.f3288a.getResources().getQuantityString(R.plurals._2130903243_res_0x7f0300cb, intValue));
                healthTextView3.setText(UnitUtil.e(intValue, 1, 0));
                return;
            }
            this.k.setVisibility(8);
        }
    }

    private void b(IntPlan intPlan, HealthTextView healthTextView, HealthTextView healthTextView2) {
        StatInfo stat = intPlan.getStat("distance");
        if (stat != null) {
            LogUtil.a("Suggestion_IntCurrentPlanViewHolder", "ai run plan distance:", stat.getValue());
            double floatValue = ((Float) stat.getValue()).floatValue() / 1000.0d;
            healthTextView.setText(this.f3288a.getString(R.string._2130841382_res_0x7f020f26));
            if (UnitUtil.h()) {
                healthTextView.setText(this.f3288a.getString(R.string._2130841383_res_0x7f020f27));
                floatValue = UnitUtil.e(floatValue, 3);
            }
            healthTextView2.setText(UnitUtil.e(floatValue, 1, 2));
            return;
        }
        LogUtil.b("Suggestion_IntCurrentPlanViewHolder", "can not get run distance.");
        healthTextView.setText(ffy.b(ffz.c(), 0, ""));
        healthTextView2.setText(UnitUtil.e(0.0d, 1, 0));
    }

    private void b(IntPlan intPlan) {
        RelativeLayout relativeLayout = this.o;
        if (relativeLayout == null) {
            LogUtil.h("Suggestion_IntCurrentPlanViewHolder", "mSecondStatisticLayout null.");
            return;
        }
        LinearLayout linearLayout = (LinearLayout) relativeLayout.findViewById(R.id.static_content_layout);
        if (linearLayout != null && (linearLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.addRule(14);
            linearLayout.setLayoutParams(layoutParams);
        }
        HealthTextView healthTextView = (HealthTextView) this.o.findViewById(R.id.statistic_data_name);
        HealthTextView healthTextView2 = (HealthTextView) this.o.findViewById(R.id.statistic_data_unit);
        HealthTextView healthTextView3 = (HealthTextView) this.o.findViewById(R.id.statistic_data_value);
        if (intPlan.getPlanType().getType() == IntPlan.PlanType.AI_RUN_PLAN.getType() || intPlan.getPlanType().getType() == IntPlan.PlanType.FIT_PLAN.getType()) {
            healthTextView.setText(this.f3288a.getString(R.string._2130845994_res_0x7f02212a));
            healthTextView2.setText(this.f3288a.getResources().getQuantityString(R.plurals._2130903233_res_0x7f0300c1, 1));
            StatInfo stat = intPlan.getStat("duration");
            if (stat != null && (stat.getValue() instanceof Float)) {
                LogUtil.a("Suggestion_IntCurrentPlanViewHolder", "plan duration:", stat.getValue());
                healthTextView3.setText(moe.g(((Float) stat.getValue()).floatValue()));
                return;
            } else {
                LogUtil.b("Suggestion_IntCurrentPlanViewHolder", "can not get duration.");
                healthTextView3.setText(UnitUtil.e(0.0d, 1, 0));
                return;
            }
        }
        this.k.setVisibility(8);
    }

    private void a(IntPlan intPlan) {
        RelativeLayout relativeLayout = this.l;
        if (relativeLayout == null) {
            LogUtil.h("Suggestion_IntCurrentPlanViewHolder", "mThirdStatisticLayout null.");
            return;
        }
        LinearLayout linearLayout = (LinearLayout) relativeLayout.findViewById(R.id.static_content_layout);
        if (linearLayout != null && (linearLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.addRule(21);
            linearLayout.setLayoutParams(layoutParams);
        }
        HealthTextView healthTextView = (HealthTextView) this.l.findViewById(R.id.statistic_data_name);
        HealthTextView healthTextView2 = (HealthTextView) this.l.findViewById(R.id.statistic_data_unit);
        HealthTextView healthTextView3 = (HealthTextView) this.l.findViewById(R.id.statistic_data_value);
        if (intPlan.getPlanType().getType() == IntPlan.PlanType.AI_RUN_PLAN.getType() || intPlan.getPlanType().getType() == IntPlan.PlanType.FIT_PLAN.getType()) {
            healthTextView.setText(this.f3288a.getString(R.string._2130847444_res_0x7f0226d4));
            healthTextView2.setText(this.f3288a.getString(R.string._2130848385_res_0x7f022a81));
            StatInfo stat = intPlan.getStat("calorie");
            if (stat != null && (stat.getValue() instanceof Float)) {
                LogUtil.a("Suggestion_IntCurrentPlanViewHolder", "plan calories:", stat.getValue());
                healthTextView3.setText(UnitUtil.e(moe.b(((Float) stat.getValue()).floatValue()), 1, 0));
                return;
            } else {
                LogUtil.b("Suggestion_IntCurrentPlanViewHolder", "can not get calories.");
                healthTextView3.setText(UnitUtil.e(0.0d, 1, 0));
                return;
            }
        }
        this.k.setVisibility(8);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.viewholder.AbsFitnessViewHolder
    public void init(Object obj) {
        LogUtil.a("Suggestion_IntCurrentPlanViewHolder", "init IntCurrentPlanViewHolder.");
        if (obj instanceof IntPlan) {
            IntPlan intPlan = (IntPlan) obj;
            this.e = intPlan;
            d();
            c(this.e);
            b(this.e);
            a(this.e);
            ggu.aMv_(intPlan, this.g, this.f);
        }
    }

    private void d() {
        float floatValue = ((Float) this.e.getStat("progress").getValue()).floatValue();
        String d = ffy.d(this.f3288a, R.string._2130844992_res_0x7f021d40, UnitUtil.e(floatValue, 2, 1));
        if (LanguageUtil.e(this.f3288a)) {
            d = d.replace("%", " %");
        }
        this.j.setText(this.e.getMetaInfo().getName());
        c(d);
        if (floatValue > 0.0f && floatValue <= 6.0f) {
            this.h.setProgress(6);
        } else {
            this.h.setProgress(Math.round(floatValue));
        }
        if (this.e.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
            this.d.setVisibility(0);
            a();
        }
    }

    private void a() {
        if (this.e.getGoal("weight") == null) {
            LogUtil.h("Suggestion_IntCurrentPlanViewHolder", "StatInfo.STAT_TYPE_WEIGHT null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: gax
                @Override // java.lang.Runnable
                public final void run() {
                    IntCurrentPlanViewHolder.this.e();
                }
            });
        }
    }

    /* renamed from: com.huawei.health.suggestion.ui.plan.viewholder.IntCurrentPlanViewHolder$2, reason: invalid class name */
    public class AnonymousClass2 extends UiCallback<Float> {
        AnonymousClass2() {
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h("Suggestion_IntCurrentPlanViewHolder", "getGoalData errorInfo:", str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final Float f) {
            HandlerExecutor.e(new Runnable() { // from class: gaz
                @Override // java.lang.Runnable
                public final void run() {
                    IntCurrentPlanViewHolder.AnonymousClass2.this.d(f);
                }
            });
        }

        public /* synthetic */ void d(Float f) {
            IntCurrentPlanViewHolder.this.c(f.floatValue());
        }
    }

    public /* synthetic */ void e() {
        grz.c(new AnonymousClass2());
    }

    private void c(String str) {
        this.b.setText(BaseApplication.getContext().getResources().getString(R.string._2130848769_res_0x7f022c01, ggu.d(System.currentTimeMillis(), this.e), str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(float f) {
        float floatValue = ((Float) this.e.getGoal("weight").getGoalDst()).floatValue();
        Drawable drawable = ContextCompat.getDrawable(this.f3288a, R.drawable._2131429973_res_0x7f0b0a55);
        if (LanguageUtil.bc(this.f3288a)) {
            drawable = ContextCompat.getDrawable(this.f3288a, R.drawable._2131427841_res_0x7f0b0201);
        }
        if (f > floatValue) {
            String e = ggu.e(f, floatValue);
            String string = this.f3288a.getResources().getString(R.string._2130848659_res_0x7f022b93, e);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append((CharSequence) string).append((CharSequence) " ");
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(BaseApplication.getContext().getColor(R.color._2131296651_res_0x7f09018b));
            int length = this.f3288a.getResources().getString(R.string._2130848659_res_0x7f022b93, "").length();
            spannableStringBuilder.setSpan(foregroundColorSpan, length, e.length() + length, 33);
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                spannableStringBuilder.setSpan(new a(drawable), e.length() + length, length + e.length() + 1, 1);
            }
            this.i.setText(spannableStringBuilder);
        } else {
            SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder();
            String d = ffy.d(this.f3288a, R.string._2130848660_res_0x7f022b94, new Object[0]);
            spannableStringBuilder2.append((CharSequence) d);
            spannableStringBuilder2.append((CharSequence) " ");
            spannableStringBuilder2.setSpan(new ForegroundColorSpan(BaseApplication.getContext().getColor(R.color._2131296651_res_0x7f09018b)), 0, d.length(), 33);
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                spannableStringBuilder2.setSpan(new a(drawable), d.length(), d.length() + 1, 1);
            }
            this.i.setText(spannableStringBuilder2);
        }
        this.i.setVisibility(0);
        c();
    }

    private void c() {
        this.i.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.plan.viewholder.IntCurrentPlanViewHolder.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HashMap hashMap = new HashMap(1);
                hashMap.put("click", 1);
                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.INT_PLAN_1120033.value(), hashMap, 0);
                JumpUtil.a(IntCurrentPlanViewHolder.this.f3288a, "8");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public View aJG_() {
        return this.f;
    }

    static class a extends ImageSpan {
        public a(Drawable drawable) {
            super(drawable);
        }

        @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
        public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
            Drawable drawable = getDrawable();
            if (drawable != null) {
                Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
                int i6 = (((fontMetricsInt.descent + i4) + i4) + fontMetricsInt.ascent) / 2;
                int i7 = drawable.getBounds().bottom / 2;
                canvas.save();
                canvas.translate(f, i6 - i7);
                drawable.draw(canvas);
                canvas.restore();
            }
        }
    }
}
