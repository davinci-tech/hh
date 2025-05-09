package defpackage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseIntArray;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.huawei.basichealthmodel.R$string;
import com.huawei.basichealthmodel.bean.ConfigDetailBean;
import com.huawei.basichealthmodel.listener.TaskDayDataListener;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.health.healthmodel.bean.HealthLifeTaskBean;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.bcl;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class bcl {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f320a;
    private static final int aa;
    private static final int ab;
    private static final int ac;
    private static final int ad;
    private static final int b;
    private static final int c;
    private static final int d;
    private static final int e = 2131430606;
    private static final int f = 2131430614;
    private static final int g;
    private static final int h;
    private static final int i;
    private static final int j;
    private static final int k;
    private static final int[][] l;
    private static final int m;
    private static final int[] n;
    private static final int o;
    private static final int[][] p;
    private static final int[] q;
    private static final int[] r;
    private static final int s;
    private static final int t;
    private static final int u = 2131430627;
    private static final int v;
    private static final int w;
    private static final int x;
    private static final int y;

    public static int b(int i2, int i3) {
        if (i2 <= 0) {
            return 0;
        }
        if (i3 == 1) {
            return 1;
        }
        return i2 == 3 ? 3 : 200;
    }

    static {
        int i2 = R$string.IDS_health_model_card_dialog_go_exercise;
        i = i2;
        int i3 = R$string.IDS_health_model_card_dialog_to_train;
        ab = i3;
        int i4 = R$string.IDS_health_model_card_dialog_take_a_break;
        x = i4;
        int i5 = R$string.IDS_health_model_card_dialog_punch;
        s = i5;
        int i6 = R$string.IDS_health_model_card_dialog_start_to_sleep;
        ac = i6;
        int i7 = R$string.IDS_health_model_card_dialog_start_to_sleep_check;
        ad = i7;
        int i8 = R$string.IDS_card_to_walk;
        aa = i8;
        w = R$string.IDS_card_running;
        h = R$string.IDS_card_energetic_workout;
        g = com.huawei.ui.commonui.R$string.IDS_indoor_skipper_rope_sport_type;
        int i9 = R$string.IDS_card_start_mindfulness;
        v = i9;
        int i10 = R$string.IDS_card_keep_on_mindfulness;
        j = i10;
        int i11 = R$string.IDS_card_drink_water;
        d = i11;
        int i12 = R$string.IDS_card_medicine;
        k = i12;
        int i13 = R$string.IDS_card_dialog_tool_measurement;
        y = i13;
        int i14 = R$string.IDS_card_dialog_manual_input;
        m = i14;
        int i15 = R$string.IDS_card_dialog_record;
        t = i15;
        int i16 = R$string.IDS_intensity_detail_view;
        b = i16;
        int i17 = R$string.IDS_health_life_view_pressure;
        c = i17;
        int i18 = R$string.IDS_health_model_card_dialog_understood;
        o = i18;
        q = new int[]{-1, i2, i3, i3, -1, -1, i5, -1, -1, -1, -1, -1, -1};
        p = new int[][]{new int[]{i8, -1, -1, i3, -1, -1, i5, i9, -1, -1, i13, i15, i13}, new int[]{i8, -1, -1, i3, -1, -1, i5, i10, -1, -1, i13, i15, i13}};
        r = new int[]{-1, -1, -1, -1, i5, i6, -1, -1, -1, -1, i14, -1, i14};
        l = new int[][]{new int[]{-1, i4, i4, -1, i14, i7, -1, -1, i11, i12, -1, -1, i4}, new int[]{-1, -1, -1, -1, i14, -1, -1, -1, i11, -1, -1, -1, -1}};
        f320a = new int[]{-1, i16, -1, i17, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        n = new int[]{i18, i18, i18, i18, i18, i18, i18, i18, i18, i18, i18, i18, i18};
    }

    private static Drawable nj_(int i2) {
        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(BaseApplication.e().getResources(), nrf.cIj_(nrf.cHR_(R$drawable.health_life_background), new int[]{i2}, true));
        create.setAntiAlias(true);
        create.setDither(true);
        create.setCornerRadius(r0.getDimensionPixelSize(R.dimen._2131362601_res_0x7f0a0329));
        return create;
    }

    private static Drawable nl_() {
        return nj_(ContextCompat.getColor(BaseApplication.e(), R.color._2131297063_res_0x7f090327));
    }

    public static Drawable nk_(Drawable drawable, boolean z) {
        if (!z) {
            return drawable;
        }
        if (!(drawable instanceof RoundedBitmapDrawable)) {
            LogUtil.h("HealthLife_TaskDialogUtils", "getBackground drawable ", drawable);
            return nl_();
        }
        RoundedBitmapDrawable roundedBitmapDrawable = (RoundedBitmapDrawable) drawable;
        Bitmap bitmap = roundedBitmapDrawable.getBitmap();
        if (bitmap == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "getBackground bitmap is null");
            return nl_();
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int cornerRadius = (int) roundedBitmapDrawable.getCornerRadius();
        LogUtil.a("HealthLife_TaskDialogUtils", "getBackground width ", Integer.valueOf(width), " height ", Integer.valueOf(height), " cornerRadius ", Integer.valueOf(cornerRadius));
        if (width <= cornerRadius || height <= cornerRadius) {
            return nl_();
        }
        return nj_(bitmap.getPixel(width - cornerRadius, height - cornerRadius));
    }

    private static int j(HealthLifeBean healthLifeBean) {
        int complete = healthLifeBean.getComplete();
        int id = healthLifeBean.getId();
        if (complete <= 0) {
            return 0;
        }
        int e2 = nsn.e(healthLifeBean.getResult());
        if (complete == 4) {
            if ((id == 6 || id == 7) && !azi.g(healthLifeBean)) {
                return 0;
            }
            if (id == 9 && e2 <= 0) {
                return 0;
            }
        }
        return (id != 11 || e2 >= nsn.e(healthLifeBean.getTarget())) ? 1 : 0;
    }

    public static List<atz> a(HealthLifeBean healthLifeBean) {
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "getEventListForRecycler bean is null");
            return Collections.emptyList();
        }
        if (healthLifeBean.getId() == 3) {
            return b();
        }
        return Collections.emptyList();
    }

    private static List<atz> b() {
        ArrayList arrayList = new ArrayList();
        if (SportSupportUtil.o()) {
            arrayList.add(new atz(1, w, u));
        }
        if (SportSupportUtil.a()) {
            arrayList.add(new atz(2, h, e));
        }
        if (SportSupportUtil.m()) {
            arrayList.add(new atz(3, g, f));
        }
        return arrayList;
    }

    public static String i(HealthLifeBean healthLifeBean) {
        ConfigDetailBean detailEntity;
        String uri;
        String url;
        String h5PackageName;
        int i2;
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "getPositiveText bean is null");
            return "";
        }
        int id = healthLifeBean.getId();
        boolean z = true;
        boolean z2 = id == 5;
        boolean z3 = id == 9;
        if ((z2 || z3) && (detailEntity = azi.d(awq.e(), id).getDetailEntity()) != null) {
            uri = detailEntity.getUri();
            url = detailEntity.getUrl();
            h5PackageName = detailEntity.getH5PackageName();
        } else {
            h5PackageName = "";
            uri = h5PackageName;
            url = uri;
        }
        boolean z4 = SportSupportUtil.o() || !(id == 2 || id == 3);
        if (TextUtils.isEmpty(uri) && TextUtils.isEmpty(url) && TextUtils.isEmpty(h5PackageName) && (z2 || z3)) {
            z = false;
        }
        if (!z4 || !z) {
            return "";
        }
        int q2 = azi.q();
        if (id == 12 && azi.x()) {
            return "";
        }
        if (q2 >= 0 && q2 <= healthLifeBean.getRecordDay()) {
            i2 = p[j(healthLifeBean)][azi.b(id)];
        } else {
            i2 = q[azi.b(id)];
        }
        return i2 <= 0 ? "" : nsf.h(i2);
    }

    public static String b(HealthLifeBean healthLifeBean) {
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "getNeutralText bean is null");
            return "";
        }
        int q2 = azi.q();
        int id = healthLifeBean.getId();
        if ((id == 3 && Utils.o()) || (id == 12 && azi.x())) {
            return "";
        }
        int b2 = azi.b(id);
        if ((id == 6 || id == 7) && (azi.e(healthLifeBean) != 1 || healthLifeBean.getComplete() > 0)) {
            return "";
        }
        int i2 = (q2 < 0 || q2 > healthLifeBean.getRecordDay()) ? -1 : r[b2];
        return i2 <= 0 ? "" : nsf.h(i2);
    }

    public static String e(HealthLifeBean healthLifeBean, int i2) {
        int i3;
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "getNegativeText bean is null");
            return "";
        }
        int complete = healthLifeBean.getComplete();
        int id = healthLifeBean.getId();
        return healthLifeBean.getId() == 12 ? azi.x() ? nsf.h(R$string.IDS_blood_pressure_lookup) : "" : (azi.d(awq.e(), id).getGoalCycleType() != 2 || i2 > 0) ? (id != 6 || TextUtils.isEmpty(healthLifeBean.getResult())) ? (id != 7 || (complete <= 0 && azi.e(healthLifeBean) == 1 && efb.b(BaseApplication.e()))) ? ((id != 10 || nsn.e(healthLifeBean.getResult()) < 6000) && (i3 = l[j(healthLifeBean)][azi.b(id)]) > 0) ? nsf.h(i3) : "" : "" : "" : "";
    }

    public static String c(HealthLifeBean healthLifeBean) {
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "getDetailText bean is null");
            return "";
        }
        int i2 = f320a[azi.b(healthLifeBean.getId())];
        return i2 <= 0 ? "" : nsf.h(i2);
    }

    public static String e(HealthLifeBean healthLifeBean) {
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "getKnowButtonText bean is null");
            return "";
        }
        int id = healthLifeBean.getId();
        int i2 = n[azi.b(id)];
        int complete = healthLifeBean.getComplete();
        if ((id == 7 && (azi.e(healthLifeBean) != 1 || (complete > 0 && complete != 4))) || (complete == 4 && azi.g(healthLifeBean))) {
            i2 = o;
        }
        return nsf.h(i2);
    }

    public static void b(HealthTextView healthTextView, String str) {
        if (healthTextView == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "setText textView is null");
        } else {
            if (TextUtils.isEmpty(str)) {
                healthTextView.setVisibility(8);
                return;
            }
            healthTextView.setVisibility(0);
            healthTextView.setText(str);
            jcf.bEB_(healthTextView, jcf.a(str), false);
        }
    }

    public static String g(HealthLifeBean healthLifeBean) {
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "getTitle bean is null");
            return "";
        }
        int b2 = b(healthLifeBean.getComplete(), healthLifeBean.getRest());
        if (b2 == 1) {
            return nsf.h(R$string.IDS_health_model_today_rest);
        }
        if (b2 == 3) {
            return nsf.h(R$string.IDS_make_up_success);
        }
        if (b2 == 200) {
            int id = healthLifeBean.getId();
            ArrayList<String> d2 = bcc.d(bcc.c(id).a(), (HashMap<String, String>) null);
            if (koq.c(d2)) {
                return d2.get(nsg.b(d2.size()));
            }
            return nsf.h(dsl.ZJ_().get(id));
        }
        return nsf.h(dsl.ZJ_().get(healthLifeBean.getId()));
    }

    public static String d(HealthLifeBean healthLifeBean) {
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "getContent bean is null");
            return "";
        }
        if (healthLifeBean.getComplete() > 0) {
            return h(healthLifeBean);
        }
        int id = healthLifeBean.getId();
        int e2 = azi.e(healthLifeBean);
        if (e2 == 0) {
            if (id == 6) {
                return nsf.h(R$string.IDS_health_model_wake_up_early);
            }
            if (id == 7) {
                return nsf.h(R$string.IDS_health_model_sleep_early);
            }
            LogUtil.h("HealthLife_TaskDialogUtils", "getContent TIME_LIMIT_BEFORE id ", Integer.valueOf(id));
            return "";
        }
        if (e2 != 2) {
            return e(id, healthLifeBean.getChallengeId(), true);
        }
        if (id == 6) {
            return nsf.h(R$string.IDS_health_model_wake_up_late);
        }
        if (id == 7) {
            return nsf.h(R$string.IDS_health_model_sleep_late);
        }
        LogUtil.h("HealthLife_TaskDialogUtils", "getContent TIME_LIMIT_AFTER id ", Integer.valueOf(id));
        return "";
    }

    private static String h(HealthLifeBean healthLifeBean) {
        int id = healthLifeBean.getId();
        if (id != 10) {
            return "";
        }
        int h2 = CommonUtils.h(healthLifeBean.getResult());
        if (h2 >= 6000) {
            ArrayList<String> d2 = bcc.d(bcc.d(BleConstants.LIMIT, id).a(), (HashMap<String, String>) null);
            if (koq.c(d2)) {
                return d2.get(nsg.b(d2.size()));
            }
        } else if (h2 > CommonUtils.h(healthLifeBean.getTarget())) {
            ArrayList<String> d3 = bcc.d(bcc.d("exceed_target", id).a(), (HashMap<String, String>) null);
            if (koq.c(d3)) {
                return d3.get(nsg.b(d3.size()));
            }
        } else {
            LogUtil.c("HealthLife_TaskDialogUtils", "getSemanticForDrinkWater bean ", healthLifeBean);
        }
        return "";
    }

    public static String e(int i2, int i3, boolean z) {
        if (i3 <= 0 || !bat.a().contains(Integer.valueOf(i3))) {
            i3 = bah.e();
        }
        if (i3 == 200003 && i2 == 9) {
            String[] i4 = nsf.i(R.array._2130968628_res_0x7f040034);
            String str = i4[nsg.b(i4.length)];
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        ArrayList<String> d2 = bcc.d(bcc.e(i2, i3).a(), (HashMap<String, String>) null);
        if (z && koq.b(d2)) {
            d2 = bcc.d(bcc.d("", i2).a(), (HashMap<String, String>) null);
        }
        return koq.c(d2) ? d2.get(nsg.b(d2.size())) : "";
    }

    public static String f(HealthLifeBean healthLifeBean) {
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "getRule bean is null");
            return "";
        }
        if (healthLifeBean.getId() != 5) {
            return "";
        }
        Context e2 = BaseApplication.e();
        return e2.getResources().getString(R$string.IDS_breath_achieved_dialog, 1, nsj.c(e2, azi.e(18, 0), 1), 60);
    }

    public static void c(HealthLifeBean healthLifeBean, Context context) {
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "setAboutEvent bean is null");
        } else {
            d(healthLifeBean.getId(), context);
        }
    }

    public static void d(int i2, Context context) {
        if (context == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "setAboutEvent bean activity is null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("targetId", i2);
        AppRouter.b("/PluginHealthModel/HealthModelMissionDetails").b(268435456).zF_(bundle).c(context);
    }

    public static void b(HealthLifeBean healthLifeBean, Context context) {
        ConfigDetailBean detailEntity;
        String uri;
        String h5PackageName;
        String str;
        if (healthLifeBean == null || context == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "setPositiveEvent bean ", healthLifeBean, " activity ", context);
            return;
        }
        int id = healthLifeBean.getId();
        boolean z = true;
        boolean z2 = id == 5;
        boolean z3 = id == 9;
        if ((!z2 && !z3) || (detailEntity = azi.d(awq.e(), id).getDetailEntity()) == null) {
            str = "";
            uri = "";
            h5PackageName = uri;
        } else {
            uri = detailEntity.getUri();
            String url = detailEntity.getUrl();
            h5PackageName = detailEntity.getH5PackageName();
            str = url;
        }
        boolean z4 = SportSupportUtil.o() || !(id == 2 || id == 3);
        if (TextUtils.isEmpty(uri) && TextUtils.isEmpty(str) && TextUtils.isEmpty(h5PackageName) && (z2 || z3)) {
            z = false;
        }
        if (z4 && z) {
            if (id == 3) {
                a(context);
                return;
            }
            if (id != 5) {
                if (id == 13) {
                    b(context);
                    return;
                } else if (id == 8) {
                    d(healthLifeBean, context);
                    return;
                } else if (id != 9) {
                    LogUtil.h("HealthLife_TaskDialogUtils", "setPositiveEvent bean ", healthLifeBean);
                    return;
                }
            }
            b(uri, str, h5PackageName, context);
        }
    }

    public static void a(Context context) {
        if (context == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "jumpRunning activity is null");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.e(), "com.huawei.health.MainActivity");
        intent.setFlags(131072);
        intent.putExtra("mLaunchSource", 6);
        intent.putExtra(BleConstants.SPORT_TYPE, 258);
        intent.putExtra("isToSportTab", true);
        gnm.aPB_(context, intent);
    }

    public static void d(Context context) {
        if (context == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "jumpAllCourse activity is null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("SKIP_ALL_COURSE_KEY", "");
        AppRouter.b("/PluginFitnessAdvice/SportAllCourseActivity").b(268435456).zF_(bundle).c(context);
    }

    public static void c(Context context) {
        if (context == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "jumpRope activity is null");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.e(), "com.huawei.health.MainActivity");
        intent.setFlags(131072);
        intent.putExtra("mLaunchSource", 6);
        intent.putExtra(BleConstants.SPORT_TYPE, 283);
        intent.putExtra("isToSportTab", true);
        gnm.aPB_(context, intent);
    }

    private static void b(String str, String str2, String str3, Context context) {
        if (!TextUtils.isEmpty(str)) {
            gnm.aPB_(context, new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str)));
            return;
        }
        if (!TextUtils.isEmpty(str3)) {
            bzs.e().loadH5ProApp(context, str3, null);
            return;
        }
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("OperationBundle", MarketRouterApi.class);
        if (marketRouterApi == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "setPositiveEventForLink api is null url ", str2);
        } else {
            marketRouterApi.router(str2, new IBaseResponseCallback() { // from class: bco
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    LogUtil.a("HealthLife_TaskDialogUtils", "setPositiveEventForLink errorCode ", Integer.valueOf(i2));
                }
            });
        }
    }

    private static void d(HealthLifeBean healthLifeBean, final Context context) {
        Intent intent = new Intent();
        intent.putExtra("taskRecord", healthLifeBean);
        intent.putExtra("moduleName", "PluginHiAiEngine");
        AppBundle.e().launchActivity(context, intent, new AppBundleLauncher.InstallCallback() { // from class: com.huawei.basichealthmodel.util.TaskDialogUtils$$ExternalSyntheticLambda1
            @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
            public final boolean call(Context context2, Intent intent2) {
                return bcl.np_(context, context2, intent2);
            }
        });
    }

    public static /* synthetic */ boolean np_(Context context, Context context2, Intent intent) {
        AppRouter.b("/PluginHealthModel/DailySmileActivity").b(268435456).zF_(new Bundle()).c(context);
        return false;
    }

    private static void b(Context context) {
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath("#/diet_recording_tool?from=5");
        bzs.e().loadH5ProApp(context, "com.huawei.health.h5.diet-diary", builder);
    }

    public static void e(HealthLifeBean healthLifeBean, Context context, String str) {
        if (healthLifeBean == null || context == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "setNeutralEvent bean ", healthLifeBean, " activity ", context);
            return;
        }
        int id = healthLifeBean.getId();
        if (id == 3) {
            d(context);
            return;
        }
        if (id == 12) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("isShowInput", true);
            bundle.putBoolean("isStartNewActivity", true);
            bundle.putString("from", str);
            AppRouter.b("/Main/InputBloodPressureActivity").b(268435456).zF_(bundle).c(context);
            return;
        }
        LogUtil.h("HealthLife_TaskDialogUtils", "setNeutralEvent bean ", healthLifeBean);
    }

    public static void b(final HealthLifeTaskBean healthLifeTaskBean, Context context, final ResponseCallback<HealthLifeTaskBean> responseCallback) {
        if (healthLifeTaskBean == null || context == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "setNegativeEvent bean ", healthLifeTaskBean, " activity ", context);
            return;
        }
        int id = healthLifeTaskBean.getId();
        awq e2 = awq.e();
        if (azi.d(e2, id).getGoalCycleType() == 2) {
            e2.c(3, String.valueOf(0), healthLifeTaskBean.getHealthLifeBean(), new TaskDayDataListener() { // from class: bcl.3
                @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
                public void onAllDataChange(int i2, List<HealthLifeBean> list) {
                }

                @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
                public void onDataChange(int i2, HealthLifeBean healthLifeBean) {
                    LogUtil.a("HealthLife_TaskDialogUtils", "setNegativeEvent onOneTaskDataChange result ", Integer.valueOf(i2), " lifeBean ", healthLifeBean);
                    if (ResponseCallback.this == null) {
                        return;
                    }
                    if (healthLifeBean != null) {
                        healthLifeTaskBean.setHealthLifeBean(healthLifeBean);
                    }
                    ResponseCallback.this.onResponse(i2 == 0 ? 3 : -1, healthLifeTaskBean);
                }
            });
        }
        if (id == 6) {
            azi.a(context, 0L, 0L);
        } else if (id == 12) {
            AppRouter.b("/Main/KnitBloodPressureActivity").b(268435456).c(context);
        } else {
            LogUtil.h("HealthLife_TaskDialogUtils", "setNegativeEvent bean ", healthLifeTaskBean);
        }
    }

    public static void e(HealthLifeBean healthLifeBean, Context context) {
        if (healthLifeBean == null || context == null) {
            LogUtil.h("HealthLife_TaskDialogUtils", "setDetailEvent bean ", healthLifeBean, " activity ", context);
            return;
        }
        int id = healthLifeBean.getId();
        if (id == 3) {
            AppRouter.b("/Main/FitnessSportIntensityDetailActivity").b(268435456).c("today_current_middle_and_high_total", CommonUtil.h(healthLifeBean.getResult())).c(context);
        } else {
            if (id == 5) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", 2);
                eyv.atT_(context, bundle);
                return;
            }
            LogUtil.h("HealthLife_TaskDialogUtils", "setDetailEvent bean ", healthLifeBean);
        }
    }

    public static SparseIntArray no_(SparseArray<List<HealthLifeBean>> sparseArray, int i2) {
        if (sparseArray == null || sparseArray.size() <= 0) {
            LogUtil.h("HealthLife_TaskDialogUtils", "getWeeklyTaskStatus sparseArray ", sparseArray);
            return new SparseIntArray();
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < sparseArray.size(); i3++) {
            arrayList.add(Integer.valueOf(sparseArray.keyAt(i3)));
        }
        if (koq.b(arrayList, 0)) {
            return new SparseIntArray();
        }
        Collections.sort(arrayList);
        List<Integer> f2 = azi.f(sparseArray.keyAt(0));
        SparseIntArray sparseIntArray = new SparseIntArray();
        Iterator<Integer> it = f2.iterator();
        while (it.hasNext()) {
            sparseIntArray.append(it.next().intValue(), 0);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            int intValue = ((Integer) it2.next()).intValue();
            Iterator it3 = new ArrayList(sparseArray.get(intValue)).iterator();
            while (it3.hasNext()) {
                HealthLifeBean healthLifeBean = (HealthLifeBean) it3.next();
                if (healthLifeBean != null && healthLifeBean.getId() == i2) {
                    sparseIntArray.append(intValue, b(healthLifeBean.getComplete(), healthLifeBean.getRest()));
                }
            }
        }
        return sparseIntArray;
    }

    public static ArrayList<Integer> nn_(SparseArray<List<HealthLifeBean>> sparseArray, int i2) {
        if (sparseArray == null || sparseArray.size() <= 0) {
            LogUtil.h("HealthLife_TaskDialogUtils", "getWeeklyTaskRecords sparseArray ", sparseArray);
            return new ArrayList<>();
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i3 = 0; i3 < sparseArray.size(); i3++) {
            int keyAt = sparseArray.keyAt(i3);
            List<HealthLifeBean> list = sparseArray.get(keyAt);
            if (!koq.b(list)) {
                for (HealthLifeBean healthLifeBean : list) {
                    if (healthLifeBean != null && healthLifeBean.getId() == i2 && healthLifeBean.getComplete() > 0) {
                        arrayList.add(Integer.valueOf(keyAt));
                        LogUtil.c("HealthLife_TaskDialogUtils", "getWeeklyTaskRecords recordDay ", Integer.valueOf(keyAt));
                    }
                }
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static int nm_(SparseArray<List<HealthLifeBean>> sparseArray, int i2) {
        SparseIntArray no_ = no_(sparseArray, i2);
        int size = no_.size();
        int nS_ = bdh.nS_(i2, azi.lO_(i2, sparseArray));
        for (int i3 = 0; i3 < size; i3++) {
            if (no_.get(no_.keyAt(i3)) == 1) {
                nS_--;
            }
        }
        return Math.max(nS_, 0);
    }
}
