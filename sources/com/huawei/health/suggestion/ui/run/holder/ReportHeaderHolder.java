package com.huawei.health.suggestion.ui.run.holder;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanRecord;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ffy;
import defpackage.ffz;
import defpackage.fis;
import defpackage.gdr;
import defpackage.ggl;
import defpackage.moe;
import defpackage.nrf;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes4.dex */
public class ReportHeaderHolder extends BaseReportHolder<c> {

    /* renamed from: a, reason: collision with root package name */
    private final HealthTextView f3371a;
    private final ImageView b;
    private final RatingBar c;
    private final HealthTextView d;
    private final HealthTextView e;
    private final HealthTextView f;
    private final HealthTextView g;
    private final HealthTextView h;
    private final HealthTextView j;

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        CharSequence f3372a;
        CharSequence b;
        CharSequence c;
        CharSequence d;
        CharSequence e;
        CharSequence g;
        CharSequence h;
        int i;
        Uri j;
    }

    public ReportHeaderHolder(View view) {
        super(view);
        this.b = (ImageView) view.findViewById(R.id.sug_iv_user_pic);
        this.c = (RatingBar) view.findViewById(R.id.sug_coach_rating);
        this.g = (HealthTextView) view.findViewById(R.id.sug_train_times);
        this.f = (HealthTextView) view.findViewById(R.id.sug_tv_his_name1);
        this.j = (HealthTextView) view.findViewById(R.id.sug_tv_his_plan_trains);
        this.f3371a = (HealthTextView) view.findViewById(R.id.sug_tv_his_name2);
        this.h = (HealthTextView) view.findViewById(R.id.sug_tv_his_plan_calorie);
        this.d = (HealthTextView) view.findViewById(R.id.sug_tv_his_name3);
        this.e = (HealthTextView) view.findViewById(R.id.sug_tv_his_plan_rate);
    }

    @Override // com.huawei.health.suggestion.ui.run.holder.BaseReportHolder
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void bindDataToView(c cVar) {
        if (cVar == null) {
            LogUtil.h("Suggestion_ReportHeaderHolder", "bindDataToView data is null! ");
            return;
        }
        this.c.setRating(cVar.i);
        this.g.setText(cVar.h);
        this.f.setText(cVar.g);
        this.j.setText(cVar.b);
        this.f3371a.setText(cVar.f3372a);
        this.h.setText(cVar.c);
        this.d.setText(cVar.e);
        this.e.setText(cVar.d);
        if (cVar.j != null) {
            nrf.cJF_(cVar.j, this.b);
        } else {
            this.b.setImageResource(R.mipmap._2131821050_res_0x7f1101fa);
            LogUtil.h("Suggestion_ReportHeaderHolder", "handleWhenGetUserInfoSuccess()! headImgPath is null! ");
        }
    }

    public static c c(Context context, Plan plan, PlanRecord planRecord) {
        if (context == null || plan == null || planRecord == null) {
            LogUtil.h("Suggestion_ReportHeaderHolder", "context == null || plan == null || planRecord == null");
            return null;
        }
        float acquireFinishRate = planRecord.acquireFinishRate();
        float acquireActualCalorie = planRecord.acquireActualCalorie();
        float j = CommonUtil.j(moe.d(planRecord.acquireActualDistance()));
        int acquireWorkoutDays = planRecord.acquireWorkoutDays();
        c cVar = new c();
        cVar.j = aLw_();
        cVar.i = gdr.b(acquireFinishRate);
        cVar.h = b(context, plan);
        cVar.g = context.getString(R.string._2130848411_res_0x7f022a9b);
        cVar.b = ffy.awT_(context, "\\d", ffy.b(R.plurals._2130903469_res_0x7f0301ad, acquireWorkoutDays, UnitUtil.e(acquireWorkoutDays, 1, 0)), R.style.sug_text_myplan_k, R.style.sug_text_myplan_n);
        if (plan.acquireType() == 0) {
            d(context, acquireActualCalorie, j, cVar);
        } else {
            b(context, acquireActualCalorie, acquireFinishRate, cVar);
        }
        return cVar;
    }

    private static void d(Context context, float f, float f2, c cVar) {
        cVar.f3372a = context.getString(R.string._2130848437_res_0x7f022ab5);
        cVar.c = ffy.awT_(context, "\\d+.\\d+|\\d+", ffy.b(ffz.c(), (int) f2, moe.j(moe.g(f2))), R.style.sug_text_myplan_k, R.style.sug_text_myplan_n);
        cVar.e = context.getString(R.string._2130847440_res_0x7f0226d0);
        cVar.d = ffy.awT_(context, "\\d+.\\d+|\\d+", ffy.d(context, R.string._2130837535_res_0x7f02001f, moe.d(f)), R.style.sug_text_myplan_k, R.style.sug_text_myplan_n);
    }

    private static void b(Context context, float f, float f2, c cVar) {
        cVar.f3372a = context.getString(R.string._2130847440_res_0x7f0226d0);
        cVar.c = ffy.awT_(context, "\\d+.\\d+|\\d+", ffy.d(context, R.string._2130837535_res_0x7f02001f, moe.i(f)), R.style.sug_text_myplan_k, R.style.sug_text_myplan_n);
        cVar.e = context.getString(R.string._2130848395_res_0x7f022a8b);
        cVar.d = ffy.awT_(context, "\\d+.\\d+|\\d+", UnitUtil.e(f2, 2, 1), R.style.sug_repirt_big, R.style.sug_report_sma);
    }

    private static Uri aLw_() {
        String a2 = fis.d().a();
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return Uri.parse(a2);
    }

    private static String b(Context context, Plan plan) {
        Date e = ggl.e(plan.acquireStartDate(), "yyyy-MM-dd");
        Date e2 = ggl.e(plan.getEndDate(), "yyyy-MM-dd");
        String format = e != null ? SimpleDateFormat.getDateInstance(3).format(e) : "yyyy-MM-dd";
        String format2 = e2 != null ? SimpleDateFormat.getDateInstance(3).format(e2) : "yyyy-MM-dd";
        if (LanguageUtil.bc(context)) {
            return context.getString(R.string._2130845600_res_0x7f021fa0, format2, format);
        }
        return context.getString(R.string._2130845600_res_0x7f021fa0, format, format2);
    }
}
