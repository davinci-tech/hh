package defpackage;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.health.R;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.suggestion.ui.callback.UiPagingCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class gdr {
    public static int a(int i, int i2) {
        return i == 1 ? i2 : i2 + 3;
    }

    public static int b(float f) {
        if (f > 90.0f) {
            return 5;
        }
        if (f > 80.0f) {
            return 4;
        }
        if (f > 60.0f) {
            return 3;
        }
        if (f > 30.0f) {
            return 2;
        }
        return f > 0.0f ? 1 : 0;
    }

    public static int b(int i) {
        return i;
    }

    public static float c(float f, int i) {
        float f2 = (f * 100.0f) / i;
        if (f2 > 100.0f) {
            return 100.0f;
        }
        return f2;
    }

    public static int c(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 0;
        }
        return i;
    }

    public static int e(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return str.contains("_") ? 3 : 0;
    }

    public static float c(float f) {
        if (f <= 100.0f) {
            return f;
        }
        LogUtil.b("Suggestion_PlanUtil", "truncateOverOneHundred target = ", Float.valueOf(f));
        return 100.0f;
    }

    public static List<String> d(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(moi.e(context, R$string.sug_defalut_5km_plan_name, new Object[0]).replace("5", UnitUtil.e(5.0d, 1, 0)));
        arrayList.add(moi.e(context, R.string._2130848369_res_0x7f022a71, new Object[0]).replace("10", UnitUtil.e(10.0d, 1, 0)));
        arrayList.add(moi.e(context, R$string.sug_defalut_half_marathon_plan_name, new Object[0]));
        arrayList.add(moi.e(context, R$string.sug_defalut_marathon_plan_name, new Object[0]));
        return arrayList;
    }

    public static List<String> c(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(moi.e(context, R.string._2130848370_res_0x7f022a72, new Object[0]));
        arrayList.add(moi.e(context, R.string._2130848371_res_0x7f022a73, new Object[0]));
        arrayList.add(moi.e(context, R.string._2130848372_res_0x7f022a74, new Object[0]));
        arrayList.add(moi.e(context, R.string._2130848373_res_0x7f022a75, new Object[0]));
        arrayList.add(moi.e(context, R.string._2130848374_res_0x7f022a76, new Object[0]));
        arrayList.add(moi.e(context, R.string._2130848375_res_0x7f022a77, new Object[0]));
        return arrayList;
    }

    public static Plan e() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_PlanUtil", "getCurrentPlan : planApi is null.");
            return null;
        }
        final Plan[] planArr = {null};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        planApi.getCurrentIntPlan(new UiPagingCallback<IntPlan>() { // from class: gdr.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccessFirst(IntPlan intPlan) {
                if (intPlan != null) {
                    planArr[0] = intPlan.getCompatiblePlan();
                }
                countDownLatch.countDown();
            }

            @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
            public void onFailureFirst(int i, String str) {
                countDownLatch.countDown();
            }
        });
        try {
            if (!countDownLatch.await(5000L, TimeUnit.MILLISECONDS)) {
                LogUtil.h("Suggestion_PlanUtil", "getIntelligentRunPlan wait timeout");
            }
        } catch (InterruptedException unused) {
            LogUtil.b("Suggestion_PlanUtil", "interrupted while waiting for getDietRecordList");
        }
        return planArr[0];
    }

    public static int e(Plan plan, long j) {
        mnu d;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int d2 = gib.d(calendar.get(7));
        int c = ase.c(plan, j);
        LogUtil.a("Suggestion_PlanUtil", "getAiCoachType weekNumber:", Integer.valueOf(c), " planDayIndex:", Integer.valueOf(d2));
        mnu d3 = ase.d(plan, d2, c);
        if (koq.b(d3.a())) {
            return 1;
        }
        if (d3.e()) {
            return 6;
        }
        if (d2 == 1) {
            d = ase.d(plan, 7, c - 1);
        } else {
            d = ase.d(plan, d2 - 1, c);
        }
        if (koq.c(d.a())) {
            return d.e() ? 0 : 2;
        }
        int b = b(plan, c);
        if (b == 1) {
            return 3;
        }
        if (b != 2) {
            return b != 3 ? 0 : 5;
        }
        return 4;
    }

    private static int b(Plan plan, int i) {
        int i2 = 0;
        while (i > 1) {
            i--;
            if (!ase.e(plan, i)) {
                break;
            }
            i2++;
        }
        return i2;
    }

    public static void aLy_(Context context, int i, final HealthProgressBar healthProgressBar, ImageView imageView) {
        String string;
        Resources resources = context.getResources();
        if (nrt.a(context)) {
            string = resources.getString(R.string._2130851479_res_0x7f023697);
        } else {
            string = resources.getString(R.string._2130851480_res_0x7f023698);
        }
        nrf.cIw_(string, new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE), imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        if (LanguageUtil.bc(context)) {
            imageView.setScaleX(-1.0f);
        }
        healthProgressBar.setVisibility(0);
        ValueAnimator duration = ValueAnimator.ofInt(0, i).setDuration(100L);
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: gdr.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                HealthProgressBar.this.setProgress(((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        });
        duration.start();
    }
}
