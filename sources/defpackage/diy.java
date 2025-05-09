package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.datatype.IntermitentJumpData;
import com.huawei.health.device.datatype.SkippingTargetMode;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.webview.WebViewActivity;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class diy {
    public static void c(BaseFragment baseFragment, String str, String str2, dcz dczVar) {
        Intent intent = new Intent();
        intent.setPackage(cez.w);
        intent.setClassName(cez.w, "com.huawei.operation.activity.WebViewActivity");
        if (!TextUtils.isEmpty(dczVar.r()) && !TextUtils.isEmpty(dczVar.b())) {
            intent.putExtra("url", dcq.b().c(str) + "#/type=2/sn=" + str2);
        } else {
            intent.putExtra("url", dcq.b().c(str));
        }
        intent.putExtra("productId", str);
        dcz d = ResourceManager.e().d(str);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", str2);
        if (d != null) {
            contentValues.put("name", dks.e(str, d.n().b()));
            contentValues.put("deviceType", d.l().name());
        }
        intent.putExtra("commonDeviceInfo", contentValues);
        baseFragment.startActivity(intent);
    }

    public static void Vt_(Activity activity, String str, int i) {
        LogUtil.a("JumpHelper", "showPrivacyActivity");
        Intent intent = new Intent();
        intent.setPackage(cez.w);
        intent.setClassName(cez.w, "com.huawei.health.device.ui.privacy.HonorDeviceShowPrivacyActivity");
        if (str != null) {
            LogUtil.a("JumpHelper", "showPrivacyActivity mPrivacyImagePath not null path = ", str);
            intent.putExtra("image_path", str);
        } else {
            LogUtil.h("JumpHelper", "showPrivacyActivity mPrivacyImagePath is null");
        }
        activity.startActivityForResult(intent, i);
    }

    public static void c(BaseFragment baseFragment, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("JumpHelper", "startUricAcidPage productId or uniqueId isEmpty ");
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(cez.w);
        intent.setClassName(cez.w, "com.huawei.operation.activity.WebViewActivity");
        intent.putExtra("url", dcq.b().c(str) + "#/type=10");
        intent.putExtra("productId", str);
        dcz d = ResourceManager.e().d(str);
        intent.putExtra("uniqueId", str2);
        if (d != null) {
            intent.putExtra("name", dks.e(str, d.n().b()));
            intent.putExtra("deviceType", d.l().name());
        }
        baseFragment.startActivity(intent);
    }

    /* renamed from: diy$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[HealthDevice.HealthDeviceKind.values().length];
            e = iArr;
            try {
                iArr[HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[HealthDevice.HealthDeviceKind.HDK_BLOOD_OXYGEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                e[HealthDevice.HealthDeviceKind.HDK_BODY_TEMPERATURE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                e[HealthDevice.HealthDeviceKind.HDK_WEIGHT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static void a(BaseFragment baseFragment, HealthDevice.HealthDeviceKind healthDeviceKind) {
        Intent intent = new Intent();
        if (AnonymousClass4.e[healthDeviceKind.ordinal()] != 1) {
            return;
        }
        intent.setPackage(cez.w);
        intent.setClassName(cez.w, "com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarHistoryActivity");
        baseFragment.startActivity(intent);
    }

    public static void b(Context context, HealthDevice.HealthDeviceKind healthDeviceKind) {
        if (context == null) {
            LogUtil.h("JumpHelper", "jumpToHomePageActivity, context is null");
            return;
        }
        int i = AnonymousClass4.e[healthDeviceKind.ordinal()];
        if (i != 1) {
            if (i != 3) {
                return;
            }
            d(context);
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(cez.w);
        intent.setClassName(cez.w, "com.huawei.ui.main.stories.template.health.module.HealthDataDetailActivity");
        intent.putExtra("extra_service_id", "BloodSugarCardConstructor");
        intent.putExtra("extra_time_stamp", SharedPreferenceManager.b("blood_sugar_module_id", "latest_one_day_time", 0L));
        intent.putExtra("extra_page_type", 8);
        jdw.bGh_(intent, context);
    }

    public static void e(Context context) {
        AppRouter.b("/home/device").e(Constants.HOME_TAB_NAME, "DEVICE").b(AppRouterExtras.COLDSTART).c(context);
    }

    public static void a(Context context) {
        if (context == null) {
            LogUtil.h("JumpHelper", "jumpToNetWorkSetting, context is null");
        } else {
            context.startActivity(new Intent("android.settings.SETTINGS"));
        }
    }

    public static void b(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("JumpHelper", "jumpToBloodSugarHistory productId or uniqueId isEmpty ");
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", str);
        contentValues.put("uniqueId", str2);
        Intent intent = new Intent();
        intent.putExtra("commonDeviceInfo", contentValues);
        intent.setPackage(cez.w);
        intent.setClassName(cez.w, "com.huawei.health.ecologydevice.ui.healthdata.activity.BloodSugarHistoryActivity");
        jdw.bGh_(intent, context);
    }

    public static void d(Context context) {
        Intent intent = new Intent();
        intent.setPackage(cez.w);
        intent.setClassName(cez.w, "com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity");
        gnm.aPB_(context, intent);
    }

    public static void Vr_(final Activity activity, final String str) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: diw
            @Override // java.lang.Runnable
            public final void run() {
                diy.Vs_(activity, str);
            }
        });
    }

    static /* synthetic */ void Vs_(final Activity activity, final String str) {
        String countryCode = LoginInit.getInstance(BaseApplication.getContext()).getCountryCode(null);
        if (TextUtils.isEmpty(countryCode)) {
            countryCode = "SG";
        }
        final String noCheckUrl = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl("domainTipsResDbankcdn", countryCode);
        if (activity == null) {
            LogUtil.h("JumpHelper", "openDeviceHelpActivity mainActivity is null");
        } else if (TextUtils.isEmpty(noCheckUrl)) {
            LogUtil.h("JumpHelper", "openDeviceHelpActivity url is empty");
        } else {
            LogUtil.c("JumpHelper", "openUserGuideActivity url", noCheckUrl, str);
            activity.runOnUiThread(new Runnable() { // from class: diy.5
                @Override // java.lang.Runnable
                public void run() {
                    Intent intent = new Intent(activity, (Class<?>) WebViewActivity.class);
                    intent.putExtra("WebViewActivity.REQUEST_URL_KEY", noCheckUrl + str);
                    intent.putExtra(Constants.JUMP_MODE_KEY, 8);
                    activity.startActivity(intent);
                }
            });
        }
    }

    public static void e(Context context, String str, knu knuVar) {
        if (knuVar == null) {
            LogUtil.h("JumpHelper", "jumpToTrackDetailActivity pathData is null");
            return;
        }
        gso.e().init(context);
        Bundle bundle = new Bundle();
        bundle.putSerializable("simplifyDatakey", knuVar.b());
        bundle.putString("contentpath", knuVar.d());
        bundle.putString("entrance", str);
        bundle.putBoolean("isAfterSport", false);
        bundle.putBoolean("isNotNeedDeleteFile", false);
        Intent intent = new Intent(context, (Class<?>) TrackDetailActivity.class);
        intent.putExtras(bundle);
        intent.addFlags(268435456);
        gnm.aPB_(context, intent);
    }

    public static void c(Context context, boolean z) {
        if (context == null) {
            health.compact.a.util.LogUtil.c("JumpHelper", "startOneKeyActivity, context is null");
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setClassName(context, "com.huawei.ui.device.activity.adddevice.OneKeyScanActivity");
            intent.putExtra("is_go_rope_jump", z);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            health.compact.a.util.LogUtil.e("JumpHelper", "ActivityNotFoundException e:", e.getMessage());
        }
    }

    public static void b(Context context, int i) {
        if (context == null) {
            LogUtil.h("JumpHelper", "gotoSportMusic context is null");
            return;
        }
        HashMap hashMap = new HashMap(5);
        e(context, i);
        c(context, hashMap, i);
        ixx.d().d(context, AnalyticsValue.MOTION_TRACK_1040014.value(), hashMap, 0);
    }

    private static void c(Context context, Map<String, Object> map, int i) {
        try {
            Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");
            intent.setFlags(268435456);
            if (gvv.a() && CommonUtil.v(gwh.s)) {
                intent.setPackage(gwh.s);
                if (gwg.i(context)) {
                    LogUtil.a("JumpHelper", "gotoSportMusic MUSIC_PLAYER");
                    SportMusicController.a().d(0, i, false);
                    map.put("sportMusicType", 0);
                    map.put("click", 1);
                    map.put(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE, 0);
                    map.put("musicType", Integer.valueOf(gwg.i(context) ? 4 : 0));
                    map.put(BleConstants.SPORT_TYPE, Integer.valueOf(i));
                    return;
                }
                nsn.cLM_(intent, gwh.s, context, nsf.h(R.string._2130842049_res_0x7f0211c1));
            }
        } catch (ActivityNotFoundException e) {
            LogUtil.h("JumpHelper", "Not found this activity MUSIC_PLAYER ", e.getMessage());
        }
        map.put("sportMusicType", 0);
    }

    public static void b(Context context, int i, int i2) {
        if (context == null) {
            LogUtil.h("JumpHelper", "gotoWarmUp context is null");
            return;
        }
        ArrayList<WorkoutRecord> a2 = a();
        mmp mmpVar = new mmp("Y044");
        mmpVar.b(283);
        mmpVar.a(i);
        mmpVar.a(i2);
        mmpVar.j(true);
        mmpVar.a(false);
        mmpVar.e(1);
        mmpVar.c(R.anim._2130772059_res_0x7f01005b);
        mmpVar.d(R.anim._2130771981_res_0x7f01000d);
        mod.c(context, mmpVar, a2);
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("type", 0);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.MOTION_TRACK_1040021.value(), hashMap, 0);
    }

    private static ArrayList<WorkoutRecord> a() {
        WorkoutRecord workoutRecord = new WorkoutRecord();
        workoutRecord.saveVersion(null);
        workoutRecord.saveExerciseTime(new Date().getTime());
        workoutRecord.saveWorkoutId("Y044");
        workoutRecord.savePlanId("");
        ArrayList<WorkoutRecord> arrayList = new ArrayList<>(1);
        arrayList.add(workoutRecord);
        return arrayList;
    }

    public static void d(Context context, int i) {
        if (context == null) {
            LogUtil.b("JumpHelper", "gotoSportHistoryActivity context is null");
            return;
        }
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setPackage(cez.w);
        intent.setClassName(cez.w, Constants.SPORT_HISTORY);
        intent.putExtra(BleConstants.SPORT_TYPE, i);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.h("JumpHelper", "gotoSportHistoryActivity() Not found this activity  ", e.getMessage());
        }
    }

    public static void a(Context context, int i, int i2, IntermitentJumpData intermitentJumpData, String str) {
        if (context == null) {
            LogUtil.b("JumpHelper", "gotoSportHistoryActivity context is null");
            return;
        }
        dds.c().c(new SkippingTargetMode(i, i2, intermitentJumpData));
        SportLaunchParams sportLaunchParams = new SportLaunchParams();
        sportLaunchParams.setSportType(283);
        sportLaunchParams.setSportTarget(i);
        sportLaunchParams.setTrackType(i);
        sportLaunchParams.setTargetValue(i2);
        sportLaunchParams.addExtra("productId", str);
        sportLaunchParams.addExtra("type_intermittent_jump_mode_data", intermitentJumpData);
        Intent intent = new Intent();
        intent.putExtra("bundle_key_sport_launch_paras", sportLaunchParams);
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.indoorequip.activity.IndoorEquipDisplayActivity");
        fgb.awY_(context, intent);
    }

    public static void e(Context context, int i) {
        SharedPreferenceManager.e(context, Integer.toString(20002), "map_tracking_sport_type", Integer.toString(i), new StorageParams());
    }

    public static void e(Context context, String str, String str2) {
        String str3;
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("JumpHelper", "jumpToBloodPressureFaqsH5Page context is null or productId is empty");
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(cez.w);
        intent.setClassName(cez.w, "com.huawei.operation.activity.WebViewActivity");
        String str4 = nrt.a(context) ? "&dark=1" : "&dark=0";
        String str5 = "&lang=" + LanguageUtil.e();
        str2.hashCode();
        if (str2.equals("feature=voice")) {
            str3 = "?feature=voice";
        } else {
            if (!str2.equals("feature=connect")) {
                LogUtil.a("JumpHelper", "jumpToBloodPressureFaqsH5Page pageType is other type");
                return;
            }
            str3 = "?feature=connect";
        }
        intent.putExtra("url", dcq.b().c(str) + str3 + str4 + str5);
        gnm.aPB_(context, intent);
    }

    public static void c(Context context, String str, String str2) {
        Intent intent = new Intent();
        intent.setPackage(cez.w);
        intent.setClassName(cez.w, "com.huawei.ui.device.activity.adddevice.OneKeyScanActivity");
        intent.putExtra("fittings_host_uuid", str);
        intent.putExtra("fittings_host_sn", str2);
        intent.putExtra("isJumpFromFittings", true);
        gnm.aPB_(context, intent);
    }
}
