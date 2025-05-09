package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.weight.bean.WeightTargetDifferences;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import defpackage.rag;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public class rag {
    private static final String e = String.valueOf(PrebakedEffectId.RT_HEARTBEAT);

    public static void b(final WeightTargetDifferences weightTargetDifferences, final ResponseCallback<Object> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthWeight_WeightUtils", "saveWeightTargetDifferences callback is null");
        } else {
            sqp.d("900300011", HiJsonUtil.e(weightTargetDifferences), new HiDataOperateListener() { // from class: rae
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public final void onResult(int i, Object obj) {
                    rag.a(WeightTargetDifferences.this, responseCallback, i, obj);
                }
            });
        }
    }

    static /* synthetic */ void a(WeightTargetDifferences weightTargetDifferences, ResponseCallback responseCallback, int i, Object obj) {
        if (i == 0) {
            gsd.b(weightTargetDifferences);
        }
        responseCallback.onResponse(i, weightTargetDifferences);
    }

    public static boolean b(WeightTargetDifferences weightTargetDifferences) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        b(weightTargetDifferences, (ResponseCallback<Object>) new ResponseCallback() { // from class: raq
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                rag.b(atomicBoolean, countDownLatch, i, obj);
            }
        });
        try {
            LogUtil.a("HealthWeight_WeightUtils", "saveWeightTargetDifferences await ", Boolean.valueOf(countDownLatch.await(1000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException e2) {
            LogUtil.b("HealthWeight_WeightUtils", "saveWeightTargetDifferences interruptedException ", LogAnonymous.b((Throwable) e2));
        }
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("custom.weight_goal_daily_diff");
        hiUserPreference.setValue(String.valueOf(UnitUtil.a(weightTargetDifferences.a() / 1000.0d, 2)));
        HiHealthManager.d(BaseApplication.e()).setUserPreference(hiUserPreference);
        return atomicBoolean.get();
    }

    static /* synthetic */ void b(AtomicBoolean atomicBoolean, CountDownLatch countDownLatch, int i, Object obj) {
        atomicBoolean.set(i == 0);
        countDownLatch.countDown();
    }

    public static WeightTargetDifferences b() {
        final AtomicReference atomicReference = new AtomicReference();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        a((ResponseCallback<WeightTargetDifferences>) new ResponseCallback() { // from class: ral
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                rag.b(atomicReference, countDownLatch, i, (WeightTargetDifferences) obj);
            }
        });
        try {
            LogUtil.a("HealthWeight_WeightUtils", "getWeightTargetDifferences await ", Boolean.valueOf(countDownLatch.await(1000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException e2) {
            LogUtil.b("HealthWeight_WeightUtils", "getWeightTargetDifferences interruptedException ", LogAnonymous.b((Throwable) e2));
        }
        return (WeightTargetDifferences) atomicReference.get();
    }

    static /* synthetic */ void b(AtomicReference atomicReference, CountDownLatch countDownLatch, int i, WeightTargetDifferences weightTargetDifferences) {
        if (i == 0) {
            atomicReference.set(weightTargetDifferences);
        } else {
            HiUserPreference userPreference = HiHealthManager.d(BaseApplication.e()).getUserPreference("custom.weight_goal_daily_diff");
            if (userPreference == null || TextUtils.isEmpty(userPreference.getValue())) {
                atomicReference.set(null);
            } else {
                atomicReference.set(new WeightTargetDifferences(CommonUtils.a(userPreference.getValue()) * 1000.0d, 0L, 0L, 0));
            }
        }
        countDownLatch.countDown();
    }

    public static void a(final ResponseCallback<WeightTargetDifferences> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthWeight_WeightUtils", "getWeightTargetDifferences callback is null");
        } else {
            qrp.b("900300011", new HiDataReadResultListener() { // from class: rag.4
                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResult(Object obj, int i, int i2) {
                    if (!koq.e(obj, HiSampleConfig.class)) {
                        ResponseCallback.this.onResponse(-1, null);
                        return;
                    }
                    List list = (List) obj;
                    if (koq.b(list)) {
                        ResponseCallback.this.onResponse(-1, null);
                        return;
                    }
                    HiSampleConfig hiSampleConfig = (HiSampleConfig) list.get(0);
                    if (hiSampleConfig == null) {
                        ResponseCallback.this.onResponse(-1, null);
                        return;
                    }
                    String configData = hiSampleConfig.getConfigData();
                    if (TextUtils.isEmpty(configData)) {
                        ResponseCallback.this.onResponse(-1, null);
                        return;
                    }
                    WeightTargetDifferences weightTargetDifferences = (WeightTargetDifferences) HiJsonUtil.e(configData, WeightTargetDifferences.class);
                    if (weightTargetDifferences == null) {
                        ResponseCallback.this.onResponse(-1, null);
                    } else {
                        gsd.b(weightTargetDifferences);
                        ResponseCallback.this.onResponse(0, weightTargetDifferences);
                    }
                }

                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResultIntent(int i, Object obj, int i2, int i3) {
                    LogUtil.a("HealthWeight_WeightUtils", "getWeightTargetDifferences intentType ", Integer.valueOf(i), " object ", obj, " errorCode ", Integer.valueOf(i2), " anchor ", Integer.valueOf(i3));
                }
            });
        }
    }

    public static WeightTargetDifferences e(WeightTargetDifferences weightTargetDifferences) {
        if (weightTargetDifferences == null) {
            ReleaseLogUtil.a("HealthWeight_WeightUtils", "getWeightTargetDifferences differences is null");
            return null;
        }
        cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
        if (mainUser == null) {
            ReleaseLogUtil.a("HealthWeight_WeightUtils", "getWeightTargetDifferences user is null differences ", weightTargetDifferences);
            return weightTargetDifferences;
        }
        float f = mainUser.f();
        float k = mainUser.k();
        if (f <= 0.0f || k <= 0.0f) {
            ReleaseLogUtil.a("HealthWeight_WeightUtils", "getWeightTargetDifferences startWeight ", Float.valueOf(f), " goalWeight ", Float.valueOf(k));
            return weightTargetDifferences;
        }
        int i = AnonymousClass1.e[weightTargetDifferences.d().ordinal()];
        if (i == 1) {
            LogUtil.a("HealthWeight_WeightUtils", "getWeightTargetDifferences WEIGHT_KEE");
            return weightTargetDifferences;
        }
        if (i != 2) {
            if (i != 3) {
                ReleaseLogUtil.a("HealthWeight_WeightUtils", "getWeightTargetDifferences default differences ", weightTargetDifferences);
            } else if (f < k) {
                LogUtil.a("HealthWeight_WeightUtils", "getWeightTargetDifferences WEIGHT_GAIN");
                return weightTargetDifferences;
            }
        } else if (f > k) {
            LogUtil.a("HealthWeight_WeightUtils", "getWeightTargetDifferences WEIGHT_LOSS");
            return weightTargetDifferences;
        }
        LogUtil.h("HealthWeight_WeightUtils", "getWeightTargetDifferences differences ", weightTargetDifferences, " startWeight ", Float.valueOf(f), " goalWeight ", Float.valueOf(k));
        weightTargetDifferences.d(b(weightTargetDifferences.e(), weightTargetDifferences.b(), f - k));
        b(weightTargetDifferences);
        return weightTargetDifferences;
    }

    /* renamed from: rag$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[WeightTargetDifferences.WeightTargetType.values().length];
            e = iArr;
            try {
                iArr[WeightTargetDifferences.WeightTargetType.WEIGHT_KEE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[WeightTargetDifferences.WeightTargetType.WEIGHT_LOSS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[WeightTargetDifferences.WeightTargetType.WEIGHT_GAIN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static int c(double d, double d2) {
        double doubleValue;
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
        try {
            int a2 = UnitUtil.a();
            if (a2 == 1) {
                doubleValue = numberFormat.parse(UnitUtil.e(BigDecimal.valueOf(UnitUtil.b(d)).subtract(BigDecimal.valueOf((int) UnitUtil.b(d2))).doubleValue(), 1, 1)).doubleValue();
            } else if (a2 == 3) {
                doubleValue = numberFormat.parse(UnitUtil.e(BigDecimal.valueOf(UnitUtil.h(d)).subtract(BigDecimal.valueOf((int) UnitUtil.h(d2))).doubleValue(), 1, 1)).doubleValue();
            } else {
                doubleValue = numberFormat.parse(UnitUtil.e(BigDecimal.valueOf(d).subtract(BigDecimal.valueOf(d2)).doubleValue(), 1, 1)).doubleValue();
            }
            int i = (int) (doubleValue * 10.0d);
            if (i > 5290) {
                return 5290;
            }
            return i;
        } catch (ParseException e2) {
            LogUtil.b("HealthWeight_WeightUtils", "parse weight data exception", e2.getMessage());
            return 0;
        }
    }

    public static int d(double d) {
        return c(d, 10.0d);
    }

    public static HealthDevice a() {
        return new HealthDevice() { // from class: rag.5
            @Override // com.huawei.health.device.model.HealthDevice
            public String getAddress() {
                return null;
            }

            @Override // com.huawei.health.device.model.HealthDevice
            public String getDeviceName() {
                return null;
            }

            @Override // com.huawei.health.device.model.HealthDevice
            public String getUniqueId() {
                return "-1";
            }
        };
    }

    public static double a(int i) {
        return a(i, false);
    }

    public static double a(int i, boolean z) {
        return d(10.0d, i, z);
    }

    public static double d(double d, int i, boolean z) {
        int a2 = UnitUtil.a();
        if (a2 == 1) {
            double doubleValue = new BigDecimal(((int) UnitUtil.b(d)) + (i * 0.1d)).setScale(1, 4).doubleValue();
            return z ? UnitUtil.c(doubleValue) : doubleValue;
        }
        if (a2 == 3) {
            double doubleValue2 = new BigDecimal(((int) UnitUtil.h(d)) + (i * 0.1d)).setScale(1, 4).doubleValue();
            return z ? UnitUtil.i(doubleValue2) : doubleValue2;
        }
        return new BigDecimal(d + (i * 0.1d)).setScale(1, 4).doubleValue();
    }

    public static ArrayList<String> c(int i, int i2) {
        ArrayList<String> arrayList = new ArrayList<>();
        int a2 = UnitUtil.a();
        if (a2 == 1) {
            for (int b = (int) UnitUtil.b(i); b < ((int) UnitUtil.b(i2)); b++) {
                arrayList.add(UnitUtil.e(b, 1, 0));
            }
        } else if (a2 == 3) {
            for (int h = (int) UnitUtil.h(i); h < ((int) UnitUtil.h(i2)); h++) {
                arrayList.add(UnitUtil.e(h, 1, 0));
            }
        } else {
            while (i < i2) {
                arrayList.add(UnitUtil.e(i, 1, 0));
                i++;
            }
        }
        return arrayList;
    }

    public static ArrayList<String> d() {
        return c(10, 251);
    }

    public static long e(double d, double d2, boolean z) {
        double d3 = d / d2;
        return jdl.b(System.currentTimeMillis(), ((int) (z ? d3 * 7.0d : Math.ceil(d3 * 7.0d))) - 1);
    }

    public static double b(long j, long j2, double d) {
        return (d * 1000.0d) / jdl.d(DateFormatUtil.b(j), DateFormatUtil.b(j2));
    }

    public static int d(double d, double d2, double d3) {
        if (d == 0.0d || d2 <= 0.0d || d3 <= 0.0d) {
            return 0;
        }
        return Math.abs((int) Math.ceil(((d3 - d2) * 1000.0d) / d));
    }

    public static int d(WeightTargetDifferences weightTargetDifferences, double d, double d2) {
        if (weightTargetDifferences == null || d <= 0.0d || d2 <= 0.0d) {
            return 0;
        }
        long e2 = weightTargetDifferences.e();
        long b = weightTargetDifferences.b();
        if (CommonUtil.c(e2) || CommonUtil.c(b)) {
            return d(weightTargetDifferences.a(), d, d2);
        }
        return jdl.d(DateFormatUtil.b(e2), DateFormatUtil.b(b));
    }

    private static void h(final ResponseCallback<Double> responseCallback) {
        kot.a().c(new ResponseCallback<Float>() { // from class: rag.2
            @Override // com.huawei.hwbasemgr.ResponseCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onResponse(int i, Float f) {
                LogUtil.h("HealthWeight_WeightUtils", "getWeightGoal data ", f);
                if (f == null) {
                    ResponseCallback.this.onResponse(i, Double.valueOf(0.0d));
                } else {
                    ResponseCallback.this.onResponse(i, Double.valueOf(CommonUtils.a(String.valueOf(f))));
                }
            }
        });
    }

    public static void b(final ResponseCallback<Double> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthWeight_WeightUtils", "getStartWeight callback is null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: rai
                @Override // java.lang.Runnable
                public final void run() {
                    rag.c(ResponseCallback.this);
                }
            });
        }
    }

    static /* synthetic */ void c(ResponseCallback responseCallback) {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.e()).getUserPreference("custom.start_weight_base");
        if (userPreference == null) {
            responseCallback.onResponse(-1, Double.valueOf(0.0d));
        } else {
            responseCallback.onResponse(0, Double.valueOf(CommonUtils.a(userPreference.getValue())));
        }
    }

    public static void e(ResponseCallback<Double> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthWeight_WeightUtils", "getTargetWeight callback is null");
        } else {
            h(responseCallback);
        }
    }

    public static void d(final ResponseCallback<HashMap<String, Double>> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthWeight_WeightUtils", "getStartWeightAndTargetWeight callback is null");
        } else {
            h(new ResponseCallback() { // from class: ram
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    rag.d(ResponseCallback.this, i, (Double) obj);
                }
            });
        }
    }

    static /* synthetic */ void d(ResponseCallback responseCallback, int i, Double d) {
        HashMap hashMap = new HashMap(2);
        double doubleValue = d != null ? d.doubleValue() : 0.0d;
        hashMap.put("targetWeight", Double.valueOf(doubleValue));
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.e()).getUserPreference("custom.start_weight_base");
        double a2 = userPreference != null ? CommonUtils.a(userPreference.getValue()) : 0.0d;
        hashMap.put("startWeight", Double.valueOf(a2));
        cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
        LogUtil.a("HealthWeight_WeightUtils", "getStartWeightAndTargetWeight mainUser ", mainUser, " targetWeight ", Double.valueOf(doubleValue), " startWeight ", Double.valueOf(a2));
        if (mainUser != null) {
            mainUser.d((float) doubleValue);
            mainUser.c((float) a2);
        }
        responseCallback.onResponse(i, hashMap);
    }

    public static float c(float f, double d, int i) {
        float floatValue;
        double d2 = f;
        if (d2 % d == 0.0d) {
            return f + ((float) d);
        }
        if (i == 0) {
            floatValue = (float) Math.ceil(d2);
        } else {
            floatValue = new BigDecimal(d2).setScale(i, 0).floatValue();
        }
        return d(d, floatValue);
    }

    private static float d(double d, float f) {
        for (int i = 0; i < 4; i++) {
            if (d == 4.0d) {
                f += 1.0f;
            }
            if (d == 0.4d) {
                f = Math.round((f + 0.1f) * 10.0f) / 10.0f;
            }
            if (d == 0.04d) {
                f = Math.round((f + 0.01f) * 100.0f) / 100.0f;
            }
            if (b(f, d)) {
                break;
            }
        }
        return f;
    }

    public static float e(float f, double d, int i) {
        float floatValue;
        double d2 = f;
        if (d2 % d == 0.0d) {
            return f - ((float) d);
        }
        if (i == 0) {
            floatValue = (float) Math.floor(d2);
        } else {
            floatValue = new BigDecimal(d2).setScale(i, 1).floatValue();
        }
        return b(d, floatValue);
    }

    private static float b(double d, float f) {
        for (int i = 0; i < 4; i++) {
            if (d == 4.0d) {
                f -= 1.0f;
            }
            if (d == 0.4d) {
                f = Math.round((f - 0.1f) * 10.0f) / 10.0f;
            }
            if (d == 0.04d) {
                f = Math.round((f - 0.01f) * 100.0f) / 100.0f;
            }
            if (b(f, d)) {
                break;
            }
        }
        return f;
    }

    private static boolean b(float f, double d) {
        if (Double.compare(d, 0.4d) == 0) {
            d *= 10.0d;
            f *= 10.0f;
        }
        if (Double.compare(d, 0.04d) == 0) {
            d *= 100.0d;
            f *= 100.0f;
        }
        return ((double) f) % d == 0.0d;
    }

    public static void d(String[] strArr) {
        SharedPreferenceManager.e(e, strArr);
    }

    public static void c(int i, String str, String str2) {
        SharedPreferenceManager.e(BaseApplication.e(), String.valueOf(i), str, str2, new StorageParams());
    }

    public static String a(int i, String str) {
        return SharedPreferenceManager.b(BaseApplication.e(), String.valueOf(i), str);
    }

    public static void c(String str, String str2) {
        SharedPreferenceManager.c(e, str, str2);
    }

    public static String a(String str) {
        return a(str, "");
    }

    public static String a(String str, String str2) {
        return SharedPreferenceManager.e(e, str, str2);
    }

    public static void e(String str, boolean z) {
        SharedPreferenceManager.e(e, str, z);
    }

    public static boolean c(String str) {
        return d(str, false);
    }

    public static boolean d(String str, boolean z) {
        return SharedPreferenceManager.a(e, str, z);
    }

    public static void d(String str, float f) {
        SharedPreferenceManager.e(e, str, f);
    }

    public static float e(String str) {
        return c(str, -1.0f);
    }

    public static float c(String str, float f) {
        return SharedPreferenceManager.b(e, str, f);
    }

    public static boolean e() {
        if (mww.d().getModelType() == 1) {
            return true;
        }
        LogUtil.b("HealthWeight_WeightUtils", "phone model not support ai body shape analysis, return");
        return false;
    }

    public static String d(String str) {
        return (TextUtils.isEmpty(str) || "NULL".equals(str)) ? LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011) : str;
    }

    private static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.a("HealthWeight_WeightUtils", "getUserIdForH5 uid ", str);
            return "";
        }
        cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
        String i = mainUser != null ? mainUser.i() : "";
        LogUtil.a("HealthWeight_WeightUtils", "getUserIdForH5 mainUserId ", i, " uid ", str);
        return str.equals(i) ? "" : str;
    }

    private static void a(String str, int i, boolean z) {
        LogUtil.a("HealthWeight_WeightUtils", "goToWeight path ", str, " flags ", Integer.valueOf(i), " isDeepLink ", Boolean.valueOf(z));
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.a("HealthWeight_WeightUtils", "goToWeight path ", str);
            return;
        }
        Context c = c(BaseApplication.wa_());
        if (z) {
            AppRouter.zi_(Uri.parse("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.weight?h5pro=true&path=" + str)).b(268435456).c(c);
        } else {
            H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
            builder.addPath(str);
            if (i > -1) {
                builder.setActivityStartFlag(i);
            }
            bzs.e().loadH5ProApp(c, "com.huawei.health.h5.weight", builder);
        }
    }

    public static void dJz_(Bundle bundle) {
        long j;
        String str;
        String str2;
        String str3;
        LogUtil.a("HealthWeight_WeightUtils", "goToWeight bundle ", bundle);
        boolean z = false;
        if (bundle != null) {
            str = bundle.containsKey("uid") ? bundle.getString("uid") : "";
            str2 = bundle.containsKey("from") ? bundle.getString("from") : "";
            str3 = bundle.containsKey("entranceType") ? bundle.getString("entranceType") : "";
            j = bundle.containsKey("lastDataTime") ? bundle.getLong("lastDataTime") : 0L;
            r4 = bundle.containsKey(ParamConstants.Param.FLAGS) ? bundle.getInt(ParamConstants.Param.FLAGS) : -1;
            if (bundle.containsKey(FaqConstants.FAQ_IS_DEEPLICK)) {
                z = bundle.getBoolean(FaqConstants.FAQ_IS_DEEPLICK);
            }
        } else {
            j = 0;
            str = "";
            str2 = str;
            str3 = str2;
        }
        String str4 = z ? "" : Constants.H5PRO_PAGE_PREFIX;
        String b = b(str);
        String str5 = str4 + "home?isMainUser=" + TextUtils.isEmpty(b) + "&uid=" + b;
        if (j > 0) {
            str5 = str5 + "&lastDataTime=" + j;
        }
        if (!TextUtils.isEmpty(str2)) {
            str5 = str5 + "&from=" + str2;
        }
        if (!TextUtils.isEmpty(str3)) {
            str5 = str5 + "&entranceType=" + str3;
        }
        a(str5, r4, z);
    }

    public static void dJB_(String str, Bundle bundle) {
        if (bundle != null) {
            bundle.putString("from", str);
        }
        dJz_(bundle);
    }

    public static void dJA_(String str, Intent intent) {
        String str2;
        str2 = "";
        boolean z = false;
        if (intent != null) {
            str2 = intent.hasExtra("weight_user_id") ? intent.getStringExtra("weight_user_id") : "";
            r4 = intent.hasExtra("lastDataTime") ? intent.getLongExtra("lastDataTime", 0L) : 0L;
            r6 = intent.hasExtra(ParamConstants.Param.FLAGS) ? intent.getIntExtra(ParamConstants.Param.FLAGS, -1) : -1;
            if (intent.hasExtra(FaqConstants.FAQ_IS_DEEPLICK)) {
                z = intent.getBooleanExtra(FaqConstants.FAQ_IS_DEEPLICK, false);
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString("uid", str2);
        bundle.putString("from", str);
        bundle.putLong("lastDataTime", r4);
        bundle.putInt(ParamConstants.Param.FLAGS, r6);
        bundle.putBoolean(FaqConstants.FAQ_IS_DEEPLICK, z);
        dJz_(bundle);
    }

    public static void e(Map<String, Object> map) {
        StringBuffer stringBuffer = new StringBuffer("#/DataPage?dimension=0&tabId=bodyFat");
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                stringBuffer.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        a(stringBuffer.toString(), -1, false);
    }

    public static Context c(Context context) {
        if (context instanceof Activity) {
            return context;
        }
        Activity wa_ = BaseApplication.wa_();
        ReleaseLogUtil.a("HealthWeight_WeightUtils", "getActivity topActivity ", wa_);
        return wa_ == null ? BaseApplication.e() : wa_;
    }

    public static void c(final float f, final long j, final ResponseCallback<Object> responseCallback) {
        if (HandlerExecutor.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: raj
                @Override // java.lang.Runnable
                public final void run() {
                    rag.c(f, j, (ResponseCallback<Object>) responseCallback);
                }
            });
            return;
        }
        if (Float.isNaN(f) || Float.compare(f, 0.0f) != 1) {
            ReleaseLogUtil.a("HealthWeight_WeightUtils", "autoUpdateUserWeight weight ", Float.valueOf(f), " measureTime ", Long.valueOf(j), " callback ", responseCallback);
            return;
        }
        final cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        if (currentUser == null) {
            ReleaseLogUtil.a("HealthWeight_WeightUtils", "autoUpdateUserWeight user is null weight ", Float.valueOf(f), " measureTime ", Long.valueOf(j), " callback ", responseCallback);
            return;
        }
        long g = CommonUtils.g(SharedPreferenceManager.b(BaseApplication.e(), String.valueOf(10000), "update_user_weight_time" + currentUser.i()));
        LogUtil.a("HealthWeight_WeightUtils", "autoUpdateUserWeight lastModifyTime ", Long.valueOf(g), " measureTime ", Long.valueOf(j), " weight ", Float.valueOf(f), " user ", currentUser);
        if (g > j) {
            return;
        }
        currentUser.b(f);
        MultiUsersManager.INSTANCE.saveUser(currentUser, new IBaseResponseCallback() { // from class: rak
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                rag.a(ResponseCallback.this, currentUser, i, obj);
            }
        });
    }

    static /* synthetic */ void a(ResponseCallback responseCallback, cfi cfiVar, int i, Object obj) {
        ReleaseLogUtil.a("HealthWeight_WeightUtils", "autoUpdateUserWeight onResponse errorCode ", Integer.valueOf(i), " object ", obj, " callback ", responseCallback);
        if (responseCallback == null) {
            return;
        }
        responseCallback.onResponse(i, cfiVar);
    }

    public static void a(List<HiHealthData> list, ArrayList<cfe> arrayList, cfi cfiVar) {
        if (arrayList == null) {
            ReleaseLogUtil.a("HealthWeight_WeightUtils", "buildWeightListFromHiHealthData weightList is null");
            return;
        }
        if (koq.b(list)) {
            ReleaseLogUtil.a("HealthWeight_WeightUtils", "buildWeightListFromHiHealthData list ", list);
            return;
        }
        boolean o = Utils.o();
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            cfe a2 = a(it.next(), o, cfiVar);
            if (a2 != null && !arrayList.contains(a2)) {
                arrayList.add(a2);
            }
        }
    }

    private static cfe a(HiHealthData hiHealthData, boolean z, cfi cfiVar) {
        if (hiHealthData.getDouble("bodyWeight") <= 0.0d) {
            return null;
        }
        cfe b = b(hiHealthData.getInt("trackdata_deviceType"));
        d(hiHealthData, b, z, cfiVar);
        a(hiHealthData, b);
        b.a(hiHealthData.getDouble("bodyFat"));
        b.y(hiHealthData.getDouble(BleConstants.IMPEDANCE));
        b.aa(hiHealthData.getDouble(BleConstants.MOISTURE));
        b.ai(hiHealthData.getDouble(BleConstants.MOISTURE_RATE));
        b.l(hiHealthData.getDouble(BleConstants.VISCERAL_FAT_LEVEL));
        b.j(hiHealthData.getDouble(BleConstants.BONE_SALT));
        b.d(hiHealthData.getDouble(BleConstants.BASAL_METABOLISM));
        b.q(hiHealthData.getDouble(BleConstants.MUSCLE_MASS));
        b.t(dks.b(hiHealthData));
        b.g(hiHealthData.getDouble(BleConstants.BODY_SCORE));
        b.b(hiHealthData.getDouble(BleConstants.BODY_AGE));
        b.n(hiHealthData.getDouble(IndoorEquipManagerApi.KEY_HEART_RATE));
        b.s(hiHealthData.getDouble("pressure"));
        b.ad(hiHealthData.getDouble("skeletalMusclelMass"));
        b.j(hiHealthData.getInt("pole"));
        b.k(hiHealthData.getDouble("leftArmFatMass"));
        b.r(hiHealthData.getDouble("leftArmMuscleMass"));
        b.o(hiHealthData.getDouble("leftLegFatMass"));
        b.m(hiHealthData.getDouble("leftLegMuscleMass"));
        b.u(hiHealthData.getDouble("rightArmFatMass"));
        b.ab(hiHealthData.getDouble("rightArmMuscleMass"));
        b.w(hiHealthData.getDouble("rightLegFatMass"));
        b.v(hiHealthData.getDouble("rightLegMuscleMass"));
        b.ac(hiHealthData.getDouble("trunkFatMass"));
        b.z(hiHealthData.getDouble("trunkMuscleMass"));
        b.ag(hiHealthData.getDouble("waistHipRatio"));
        b.ah(hiHealthData.getDouble("waistHipRatioUser"));
        b.x(hiHealthData.getDouble("rasm"));
        b.f(hiHealthData.getDouble("bodySize"));
        b.h(hiHealthData.getDouble("bodyShape"));
        b.i(hiHealthData.getDouble("fatBalance"));
        b.p(hiHealthData.getDouble("muscleBalance"));
        b.e(hiHealthData.getString("device_uniquecode"));
        b.b(hiHealthData.getClientId());
        b.e(hiHealthData.getModifiedTime());
        b.b(hiHealthData.getStartTime());
        b.d(hiHealthData.getEndTime());
        return b;
    }

    private static cfe b(int i) {
        cfe qgqVar;
        if (cpa.e(i)) {
            qgqVar = new qgo();
        } else if (i == 133) {
            qgqVar = new qgn();
        } else {
            qgqVar = new qgq();
        }
        qgqVar.a(i);
        return qgqVar;
    }

    public static void d(HiHealthData hiHealthData, cfe cfeVar, boolean z, cfi cfiVar) {
        if (hiHealthData == null || cfeVar == null) {
            ReleaseLogUtil.a("HealthWeight_WeightUtils", "setWeightBeanBaseData data ", hiHealthData, " bean ", cfeVar, " isOversea ", Boolean.valueOf(z));
            return;
        }
        cfeVar.ae(hiHealthData.getDouble("bodyWeight"));
        int i = hiHealthData.getInt("height");
        boolean z2 = cfiVar != null && cfiVar.d() > 0 && cfiVar.a() > 0;
        if (i <= 0) {
            if (z2) {
                cfeVar.c(cfiVar.d());
            } else {
                cfeVar.c(gni.b());
            }
            LogUtil.h("HealthWeight_WeightUtils", "setWeightBeanBaseData height ", Integer.valueOf(i), " bean ", cfeVar);
        } else {
            cfeVar.c(i);
        }
        cfeVar.b(hiHealthData.getClientId());
        cfeVar.e(hiHealthData.getModifiedTime());
        double d = hiHealthData.getDouble(BleConstants.BMI);
        if (!dph.e(d, z)) {
            d = doj.a(cfeVar.ax(), cfeVar.t());
        }
        cfeVar.c(d);
        int i2 = hiHealthData.getInt("age");
        if (i2 <= 0) {
            if (z2) {
                cfeVar.d(cfiVar.a());
            } else {
                cfeVar.d(gni.e());
            }
            LogUtil.h("HealthWeight_WeightUtils", "setWeightBeanBaseData age ", Integer.valueOf(i2), " bean ", cfeVar);
        } else {
            cfeVar.d(i2);
        }
        int i3 = hiHealthData.getInt(CommonConstant.KEY_GENDER);
        if (i3 <= 0 && i2 <= 0 && i <= 0 && (cfeVar instanceof qgq)) {
            if (z2) {
                cfeVar.a(cfiVar.c());
            } else {
                int i4 = gni.a() == 1 ? 0 : 1;
                cfeVar.a((byte) i4);
                i3 = i4;
            }
            LogUtil.h("HealthWeight_WeightUtils", "setWeightBeanBaseData gender ", Integer.valueOf(i3), " bean ", cfeVar);
        } else {
            cfeVar.a((byte) (i3 == 0 ? 0 : 1));
        }
        cfeVar.e(hiHealthData.getDouble(BleConstants.BODY_FAT_RATE));
    }

    private static void a(HiHealthData hiHealthData, cfe cfeVar) {
        String[] strArr = {"lfrfImpedance", "lhrhImpedance", "lhlfImpedance", "lhrfImpedance", "rhlfImpedance", "rhrfImpedance"};
        double[] dArr = new double[6];
        for (int i = 0; i < 6; i++) {
            dArr[i] = hiHealthData.getDouble(strArr[i]);
        }
        cfeVar.c(dArr);
        if (cpa.a(hiHealthData)) {
            String[] strArr2 = {"lfrfHfImpedance", "lhrhHfImpedance", "lhlfHfImpedance", "lhrfHfImpedance", "rhlfHfImpedance", "rhrfHfImpedance"};
            double[] dArr2 = new double[6];
            for (int i2 = 0; i2 < 6; i2++) {
                dArr2[i2] = hiHealthData.getDouble(strArr2[i2]);
            }
            cfeVar.e(dArr2);
            cfeVar.e(2);
        }
    }

    /* renamed from: rag$3, reason: invalid class name */
    class AnonymousClass3 implements HiAggregateListener {
        final /* synthetic */ ResponseCallback e;

        AnonymousClass3(ResponseCallback responseCallback) {
            this.e = responseCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(final List<HiHealthData> list, final int i, int i2) {
            OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_WEIGHT_QUERY_85070010.value(), i);
            if (koq.b(list)) {
                this.e.onResponse(i, null);
                return;
            }
            ThreadPoolManager d = ThreadPoolManager.d();
            final ResponseCallback responseCallback = this.e;
            d.execute(new Runnable() { // from class: rao
                @Override // java.lang.Runnable
                public final void run() {
                    rag.AnonymousClass3.b(list, responseCallback, i);
                }
            });
        }

        static /* synthetic */ void b(List list, ResponseCallback responseCallback, int i) {
            ArrayList arrayList = new ArrayList();
            rag.a((List<HiHealthData>) list, (ArrayList<cfe>) arrayList, (cfi) null);
            responseCallback.onResponse(i, arrayList);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            LogUtil.a("HealthWeight_WeightUtils", "onResultIntent intentType ", Integer.valueOf(i), " anchor ", Integer.valueOf(i3), " errorCode ", Integer.valueOf(i2));
            OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_WEIGHT_QUERY_85070010.value(), i2);
            this.e.onResponse(i2, null);
        }
    }

    public static void a(HiAggregateOption hiAggregateOption, ResponseCallback<List<cfe>> responseCallback) {
        if (hiAggregateOption == null || responseCallback == null) {
            ReleaseLogUtil.a("HealthWeight_WeightUtils", "getWeighList option ", hiAggregateOption, " callback ", responseCallback);
        } else {
            HiHealthManager.d(BaseApplication.e()).aggregateHiHealthData(hiAggregateOption, new AnonymousClass3(responseCallback));
        }
    }
}
