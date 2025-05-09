package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.SparseArray;
import android.util.SparseIntArray;
import androidx.core.content.ContextCompat;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.huawei.basichealthmodel.HealthModelH5ProService;
import com.huawei.basichealthmodel.R$plurals;
import com.huawei.basichealthmodel.bean.ConfigBean;
import com.huawei.basichealthmodel.bean.TaskConfigBean;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessCommon;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.openservice.OpenServiceUtil;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import org.apache.commons.io.FileUtils;

/* loaded from: classes.dex */
public class azi {
    private static final int[] h = {R$drawable.health_life_icon_task_step, R$drawable.health_life_icon_task_intensity, R$drawable.health_life_icon_task_workout, R$drawable.health_life_icon_task_breath, R$drawable.health_life_icon_task_wake_up, R$drawable.health_life_icon_task_sleep, R$drawable.health_life_icon_task_smile, R$drawable.health_life_icon_task_mindfulness, R$drawable.health_life_icon_task_drink_water, R$drawable.health_life_icon_task_medicine, R$drawable.health_life_icon_task_blood_pressure, R$drawable.health_life_icon_task_diet, R$drawable.health_life_icon_task_weight};
    private static final int[] b = {R.color._2131297854_res_0x7f09063e, R.color._2131297849_res_0x7f090639, R.color._2131297857_res_0x7f090641, R.color._2131297846_res_0x7f090636, R.color._2131297855_res_0x7f09063f, R.color._2131297852_res_0x7f09063c, R.color._2131297853_res_0x7f09063d, R.color._2131297851_res_0x7f09063b, R.color._2131297848_res_0x7f090638, R.color._2131297850_res_0x7f09063a, R.color._2131297845_res_0x7f090635, R.color._2131297847_res_0x7f090637, R.color._2131297856_res_0x7f090640};
    private static final int[] c = {R.color._2131297841_res_0x7f090631, R.color._2131297836_res_0x7f09062c, R.color._2131297844_res_0x7f090634, R.color._2131297833_res_0x7f090629, R.color._2131297842_res_0x7f090632, R.color._2131297839_res_0x7f09062f, R.color._2131297840_res_0x7f090630, R.color._2131297838_res_0x7f09062e, R.color._2131297835_res_0x7f09062b, R.color._2131297837_res_0x7f09062d, R.color._2131297832_res_0x7f090628, R.color._2131297834_res_0x7f09062a, R.color._2131297843_res_0x7f090633};
    private static final int[] g = {70200, 63000, 63001, 54000, 25200, 82800, 36000, 79200, FitnessCommon.GTM_800_OFFSET_IN_SECOND, 28801, 25201, 30600, 32400};
    private static final int[] e = {47101, 47102, 47103, 47104, 47105, 47107, 47108, 47109};

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f291a = {"TOTAL", OpenServiceUtil.Location.STEP, "RUN", "CYCLE", "FITNESS", "CLIMB", "SWIM", "UNKNOWHIGH"};
    private static final float[] d = {0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f};
    private static SparseIntArray m = new SparseIntArray();
    private static SparseIntArray j = new SparseIntArray();
    private static SparseIntArray f = new SparseIntArray();
    private static SparseIntArray i = new SparseIntArray();

    private static int b(long j2, long j3, long j4) {
        if (j2 < j3) {
            return 0;
        }
        return j2 > j4 ? 2 : 1;
    }

    private static boolean e(int i2, int i3, int i4, int i5) {
        if (i2 < 0 || i3 < 0 || i4 <= 0) {
            return false;
        }
        if (i2 == 0 && i3 == 0) {
            return true;
        }
        if (i2 == 0) {
            return i5 <= 0 && i3 >= i4;
        }
        if (i3 == 0) {
            boolean z = i2 <= i4;
            return i5 <= 0 ? z : z && i2 > i5;
        }
        boolean z2 = i4 >= i2 && i4 <= i3;
        if (i5 <= 0) {
            return z2;
        }
        if (z2) {
            return (i5 < i2 || i5 > i3) && i2 > i5;
        }
        return false;
    }

    public static boolean g(int i2) {
        return i2 == 6 || i2 == 7;
    }

    public static void d(CountDownLatch countDownLatch, String str) {
        if (countDownLatch == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "setCountDownLatchAwait countDownLatch is null");
            return;
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e2) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "setCountDownLatchAwait tag ", str, " interruptedException ", LogAnonymous.b((Throwable) e2));
        }
    }

    public static void d(ThreadPoolManager threadPoolManager, Runnable runnable) {
        if (threadPoolManager == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "restartExecute threadPoolManager is null");
        } else {
            threadPoolManager.a(runnable);
            threadPoolManager.execute(runnable);
        }
    }

    public static void b(ThreadPoolManager threadPoolManager, String str, Runnable runnable) {
        if (threadPoolManager == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "restartTagExecute threadPoolManager is null");
        } else if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "restartTagExecute tag is empty");
            d(threadPoolManager, runnable);
        } else {
            threadPoolManager.e(str, null);
            threadPoolManager.d(str, runnable);
        }
    }

    public static boolean h(String str) {
        File file;
        if (!TextUtils.isEmpty(str) && (file = FileUtils.getFile(str)) != null && file.exists()) {
            try {
                if (!sqd.d(file, new File(mrv.d).getCanonicalPath())) {
                    LogUtil.h("HealthLife_BasicHealthModelUtils", "isInvalidFile isValidFile false path ", str);
                    return true;
                }
                if (!file.isDirectory()) {
                    return file.length() <= 0;
                }
                File[] listFiles = file.listFiles();
                return listFiles == null || listFiles.length <= 0;
            } catch (IOException e2) {
                ReleaseLogUtil.c("HealthLife_BasicHealthModelUtils", "isInvalidFile exception ", ExceptionUtils.d(e2));
            }
        }
        return true;
    }

    public static void lY_(Handler handler, int i2) {
        lZ_(handler, i2, null);
    }

    public static void lZ_(Handler handler, int i2, Object obj) {
        if (handler == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "sendMessage handler is null");
            return;
        }
        Message obtainMessage = handler.obtainMessage();
        obtainMessage.what = i2;
        if (obj != null) {
            obtainMessage.obj = obj;
        }
        handler.sendMessage(obtainMessage);
    }

    public static void ma_(Handler handler, int i2, Bundle bundle) {
        if (handler == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "sendMessageWithBundle handler is null");
            return;
        }
        Message obtainMessage = handler.obtainMessage();
        obtainMessage.what = i2;
        if (bundle != null) {
            obtainMessage.setData(bundle);
        }
        handler.sendMessage(obtainMessage);
    }

    public static void e(HealthTextView healthTextView, int i2, float f2) {
        if (healthTextView == null) {
            ReleaseLogUtil.a("HealthLife_BasicHealthModelUtils", "setTextSize textView is null dimensionResourcesId ", Integer.valueOf(i2), " zoomFactor ", Float.valueOf(f2));
        } else {
            healthTextView.setZoomSize(i2, f2);
        }
    }

    public static boolean ab() {
        long g2 = CommonUtils.g(ao());
        return g2 <= 0 || DateFormatUtil.b(System.currentTimeMillis()) != DateFormatUtil.b(g2);
    }

    private static String ao() {
        String a2 = bao.a(Utils.o() ? "health_model_plugin_time_oversea" : "health_model_plugin_time");
        LogUtil.a("HealthLife_BasicHealthModelUtils", "getPluginTime pluginTime ", a2);
        return a2;
    }

    public static void a(long j2) {
        bao.c(Utils.o() ? "health_model_plugin_time_oversea" : "health_model_plugin_time", String.valueOf(j2));
        LogUtil.a("HealthLife_BasicHealthModelUtils", "setPluginTime time ", Long.valueOf(j2));
    }

    public static boolean ah() {
        return String.valueOf(true).equals(bao.e("is_support_health_model_new"));
    }

    public static boolean ag() {
        HiUserPreference c2 = c("health_model_is_support_health_model_new");
        if (c2 == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "isUseNewHealthModel hiUserPreference is null");
            aj();
            return false;
        }
        String value = c2.getValue();
        boolean z = CommonUtils.h(value) > 30000001;
        bao.e("is_support_health_model_new", String.valueOf(z));
        LogUtil.a("HealthLife_BasicHealthModelUtils", "isUseNewHealthModel value ", value, " HandlerExecutor.isMainTread() ", Boolean.valueOf(HandlerExecutor.c()));
        if (!z) {
            aj();
        }
        return z;
    }

    public static void aj() {
        if (HandlerExecutor.c()) {
            d(ThreadPoolManager.d(), new Runnable() { // from class: azg
                @Override // java.lang.Runnable
                public final void run() {
                    azi.aj();
                }
            });
            return;
        }
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("health_model_is_support_health_model_new");
        hiUserPreference.setValue(String.valueOf(30000001));
        LogUtil.a("HealthLife_BasicHealthModelUtils", "setHealthModelVersion isSuccess ", Boolean.valueOf(HiHealthManager.d(BaseApplication.e()).setUserPreference(hiUserPreference)));
    }

    public static boolean u() {
        String e2 = bao.e("health_model_is_agree_protocol");
        LogUtil.a("HealthLife_BasicHealthModelUtils", "isAgreeProtocolForSharedPreference data ", e2);
        return String.valueOf(true).equals(e2);
    }

    public static HiUserPreference c(String str) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(str);
        List<HiUserPreference> userPreferenceList = HiHealthManager.d(BaseApplication.e()).getUserPreferenceList(arrayList);
        if (koq.b(userPreferenceList)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getHiUserPreference hiUserPreferenceList is empty");
            return null;
        }
        return userPreferenceList.get(0);
    }

    public static void af() {
        if (HandlerExecutor.c()) {
            d(ThreadPoolManager.d(), new Runnable() { // from class: azh
                @Override // java.lang.Runnable
                public final void run() {
                    azi.af();
                }
            });
            return;
        }
        if (w()) {
            return;
        }
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("health_model_is_agree_protocol");
        hiUserPreference.setValue(String.valueOf(true));
        boolean userPreference = HiHealthManager.d(BaseApplication.e()).setUserPreference(hiUserPreference);
        bao.e("health_model_is_agree_protocol", String.valueOf(true));
        LogUtil.a("HealthLife_BasicHealthModelUtils", "setAgreeProtocol isSuccess ", Boolean.valueOf(userPreference));
        an();
    }

    public static boolean w() {
        HiUserPreference c2 = c("health_model_is_agree_protocol");
        if (c2 == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "isAgreeUserProtocol hiUserPreference is null");
            return false;
        }
        String value = c2.getValue();
        boolean z = !TextUtils.isEmpty(value);
        bao.e("health_model_is_agree_protocol", String.valueOf(z));
        LogUtil.a("HealthLife_BasicHealthModelUtils", "isAgreeUserProtocol value ", value);
        return z;
    }

    public static void an() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setForceSync(true);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(10026);
        HiHealthNativeApi.a(BaseApplication.e()).synCloud(hiSyncOption, null);
    }

    @Deprecated
    public static String p() {
        return jfa.d();
    }

    public static boolean j(HealthLifeBean healthLifeBean) {
        return healthLifeBean != null && healthLifeBean.getId() > 0;
    }

    public static boolean aa() {
        int t = t();
        return t > 0 && DateFormatUtil.b(System.currentTimeMillis()) >= t;
    }

    public static String g() {
        String e2 = bao.e("health_model_join_time");
        LogUtil.a("HealthLife_BasicHealthModelUtils", "getJoinTime joinTime ", e2);
        return e2;
    }

    public static boolean ae() {
        int q = q();
        return q > 0 && DateFormatUtil.b(System.currentTimeMillis()) >= q;
    }

    public static void n(int i2) {
        bao.e("dataSource", String.valueOf(i2));
    }

    public static String f() {
        String e2 = bao.e("dataSource");
        LogUtil.a("HealthLife_BasicHealthModelUtils", "getDataSource dataSource ", e2);
        return e2;
    }

    public static boolean ad() {
        return CommonUtils.h(f()) == 1;
    }

    public static int m() {
        if (ad()) {
            return 1;
        }
        return CommonUtils.h(bao.e("health_model_challenge_id"));
    }

    public static boolean z() {
        boolean ad = ad();
        int h2 = CommonUtils.h(bao.e("health_model_challenge_id"));
        boolean z = h2 > 200001;
        LogUtil.a("HealthLife_BasicHealthModelUtils", "isGenerateWeekReport isDoctorPlan=", Boolean.valueOf(ad), ",planId=", Integer.valueOf(h2));
        return ad || z;
    }

    public static void b(boolean z) {
        bao.e("isSubscribeBloodPressure", String.valueOf(z));
    }

    public static boolean ac() {
        return String.valueOf(true).equals(bao.e("isSubscribeBloodPressure"));
    }

    public static int q() {
        return bao.c();
    }

    public static int t() {
        return bao.d();
    }

    private static int c(double d2, int i2) {
        if (i2 > -1) {
            return i2;
        }
        double a2 = UnitUtil.a(d2, 2);
        int i3 = 0;
        if (a2 - UnitUtil.a(d2, 0) != 0.0d) {
            i3 = 1;
            if (a2 - UnitUtil.a(d2, 1) != 0.0d) {
                return 2;
            }
        }
        return i3;
    }

    public static double b(double d2, int i2) {
        return UnitUtil.a(d2, c(d2, i2));
    }

    public static String c(double d2, int i2, int i3) {
        String e2 = UnitUtil.e(b(d2, i3), i2, c(d2, i3));
        return i2 == 2 ? a(e2) : e2;
    }

    public static String a(String str) {
        Context e2 = BaseApplication.e();
        if (!LanguageUtil.y(e2) && !LanguageUtil.bp(e2)) {
            return str;
        }
        return "\u200f" + str;
    }

    public static boolean e(String str, int i2) {
        return c(str, i2, -1);
    }

    public static boolean c(String str, int i2, int i3) {
        int[] g2 = g(str);
        int length = g2.length;
        if (length <= 0) {
            return false;
        }
        if (length == 1) {
            int i4 = g2[0];
            if (i4 < 0) {
                return false;
            }
            return i4 == 0 || i4 == i2;
        }
        return e(g2[0], g2[1], i2, i3);
    }

    private static int[] g(String str) {
        String[] split;
        int length;
        if (!TextUtils.isEmpty(str) && (length = (split = str.split(Constants.LINK)).length) != 0) {
            if (length == 1) {
                return new int[]{j(split[0])};
            }
            return new int[]{j(split[0]), j(split[1])};
        }
        return new int[]{0};
    }

    private static int j(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Math.max(0, Math.abs(Integer.parseInt(str)));
        } catch (NumberFormatException e2) {
            LogUtil.b("HealthLife_BasicHealthModelUtils", "getVersion NumberFormatException ", LogAnonymous.b((Throwable) e2));
            return -1;
        }
    }

    public static ArrayList<Integer> a() {
        return dsl.d(R.array._2130968659_res_0x7f040053);
    }

    public static boolean y() {
        return a().contains(Integer.valueOf(CommonUtil.h(bao.e("health_model_challenge_id"))));
    }

    public static Calendar s() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar;
    }

    public static long e(int i2, int i3) {
        Calendar s = s();
        s.set(11, i2);
        s.set(12, i3);
        return s.getTimeInMillis();
    }

    public static List<Integer> f(int i2) {
        int b2;
        long[] e2 = jdl.e(DateFormatUtil.c(i2), TimeZone.getDefault(), 1, 0);
        long j2 = e2[0];
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(DateFormatUtil.b(j2)));
        int b3 = DateFormatUtil.b(e2[1]);
        for (int i3 = 0; i3 < 5 && (b2 = DateFormatUtil.b((j2 = jdl.y(j2)))) < b3; i3++) {
            arrayList.add(Integer.valueOf(b2));
        }
        arrayList.add(Integer.valueOf(b3));
        return arrayList;
    }

    public static void a(String str, String str2, HiAggregateListener hiAggregateListener) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeInterval(str, str2, HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        hiAggregateOption.setType(e);
        hiAggregateOption.setConstantsKey(f291a);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setAggregateType(1);
        HiHealthNativeApi.a(BaseApplication.e()).aggregateHiHealthData(hiAggregateOption, hiAggregateListener);
    }

    public static int c(HiHealthData hiHealthData) {
        double d2;
        double d3;
        if (hiHealthData == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getIntensityStatus hiHealthData is null");
            return -1;
        }
        int i2 = hiHealthData.getInt("TOTAL");
        if (i2 <= 0) {
            return 0;
        }
        double d4 = hiHealthData.getInt("RUN");
        double d5 = hiHealthData.getInt(OpenServiceUtil.Location.STEP);
        double d6 = hiHealthData.getInt("CYCLE");
        double d7 = hiHealthData.getInt("FITNESS");
        double d8 = hiHealthData.getInt("SWIM") + hiHealthData.getInt("CLIMB") + hiHealthData.getInt("UNKNOWHIGH");
        double d9 = d4 + d5 + d6 + d7 + d8;
        double d10 = i2;
        if (d10 > d9) {
            d2 = d4;
            d3 = d10 - d9;
            d9 = d10;
        } else {
            d2 = d4;
            d3 = 0.0d;
        }
        double c2 = c(d2, d9);
        double c3 = c(d6, d9);
        double c4 = c(d7, d9);
        double c5 = c(d8 + d3, d9);
        double d11 = c2 + c3 + c4 + c5;
        if (d11 > 100.0d) {
            double d12 = d11 - 100.0d;
            if (Double.compare(c2, c3) >= 0 && Double.compare(c2, c4) >= 0 && Double.compare(c2, c5) >= 0) {
                c2 -= d12;
            } else if (Double.compare(c4, c2) >= 0 && Double.compare(c4, c3) >= 0 && Double.compare(c4, c5) >= 0) {
                c4 -= d12;
            } else if (Double.compare(c3, c2) >= 0 && Double.compare(c3, c4) >= 0 && Double.compare(c3, c5) >= 0) {
                c3 -= d12;
            } else if (Double.compare(c5, c2) < 0 || Double.compare(c5, c3) < 0 || Double.compare(c5, c4) < 0) {
                LogUtil.c("HealthLife_BasicHealthModelUtils", "getIntensityStatus run ", Double.valueOf(c2), " cycle ", Double.valueOf(c3), " fitness ", Double.valueOf(c4), " other ", Double.valueOf(c5));
            } else {
                c5 -= d12;
            }
        }
        double round = Math.round((d5 / d9) * 100.0d);
        if (d5 > 0.0d) {
            round = (((100.0d - c2) - c3) - c4) - c5;
        }
        if (round < 0.0d) {
            round = 0.0d;
        }
        return ((int) b(c2, 0)) + ((int) b(round, 0)) >= (((int) b(c3, 0)) + ((int) b(c4, 0))) + ((int) b(c5, 0)) ? 300 : 301;
    }

    private static double c(double d2, double d3) {
        double round = Math.round((d2 / d3) * 100.0d);
        if (round >= 1.0d || d2 <= 0.0d) {
            return round;
        }
        return 1.0d;
    }

    public static boolean x() {
        return d(0L);
    }

    public static boolean d(long j2) {
        String e2 = bao.e("blood_pressure_day_of_week");
        String binaryString = Integer.toBinaryString(CommonUtil.h(e2));
        LogUtil.a("HealthLife_BasicHealthModelUtils", "isBloodPressureRest doyOfWeek ", e2, " binary ", binaryString);
        if (TextUtils.isEmpty(binaryString)) {
            return true;
        }
        Calendar calendar = Calendar.getInstance();
        if (j2 != 0) {
            calendar.setTimeInMillis(j2);
        }
        int i2 = calendar.get(7);
        String format = String.format(Locale.ENGLISH, "%07d", Integer.valueOf(CommonUtil.h(binaryString)));
        if (i2 == 1) {
            i2 = 8;
        }
        int i3 = i2 - 1;
        char charAt = format.charAt(format.length() - i3);
        LogUtil.a("HealthLife_BasicHealthModelUtils", "isBloodPressureRest time ", Long.valueOf(j2), " dayOfWeek ", format, " currentDayOfWeek ", Integer.valueOf(i3), " charAt ", Character.valueOf(charAt));
        return charAt != '1';
    }

    public static void b(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "jumpH5App context ", context, " h5PackageName ", str);
            return;
        }
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.setImmerse().showStatusBar().setForceDarkMode(1).setStatusBarTextBlack(true).addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi"));
        if (!TextUtils.isEmpty(str2)) {
            builder.addPath(str2);
        }
        bzs.e().loadH5ProApp(context, str, builder);
    }

    public static void a(Context context, long j2, long j3) {
        String str;
        if (j2 == 0 || j3 == 0) {
            str = "#/sleepDataInput?pullFrom=2";
        } else {
            str = "#/sleepDataInput?pullFrom=2&bedTime=" + j2 + "&risingTime=" + j3;
        }
        bzs.e().initH5Pro();
        H5ProClient.startH5MiniProgram(context, "com.huawei.health.h5.sleepdetection", new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("SleepDetection", mtp.d().getJsModule()).addPath(str).setImmerse().showStatusBar().setStatusBarTextBlack(true).setForceDarkMode(1).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(final String str, final String str2, final IBaseResponseCallback iBaseResponseCallback) {
        if (HandlerExecutor.c()) {
            d(ThreadPoolManager.d(), new Runnable() { // from class: azn
                @Override // java.lang.Runnable
                public final void run() {
                    azi.a(str, str2, iBaseResponseCallback);
                }
            });
            return;
        }
        if (iBaseResponseCallback == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getUserSampleConfigList callback is null");
        } else if (!CommonUtil.aa(BaseApplication.e())) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getUserSampleConfigList isNetworkConnected false");
            iBaseResponseCallback.d(-1, null);
        } else {
            LogUtil.a("HealthLife_BasicHealthModelUtils", "getUserSampleConfigList type ", str, " id ", str2);
            aug.d().c(str, str2, iBaseResponseCallback);
        }
    }

    private static List<cbk> am() {
        String e2 = bao.e("bloodPressureMeasurePlan");
        LogUtil.a("HealthLife_BasicHealthModelUtils", "getBloodPressureAlarmInfoListForLocal value ", e2);
        if (TextUtils.isEmpty(e2)) {
            List<cbk> ak = ak();
            ArrayList arrayList = new ArrayList();
            if (koq.c(ak)) {
                arrayList.addAll(ak);
                e((cbk) arrayList.get(0));
            }
            if (koq.b(arrayList)) {
                LogUtil.h("HealthLife_BasicHealthModelUtils", "getBloodPressureAlarmInfoListForLocal bloodPressureAlarmInfoList ", arrayList);
                arrayList.addAll(h());
            }
            return arrayList;
        }
        return b(e2);
    }

    public static List<cbk> e() {
        if (!CommonUtil.aa(BaseApplication.e())) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getBloodPressureAlarmInfoList isNetworkConnected false");
            return am();
        }
        final ArrayList arrayList = new ArrayList();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        a("9003", "900300001", new IBaseResponseCallback() { // from class: aze
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                azi.b(arrayList, countDownLatch, i2, obj);
            }
        });
        d(countDownLatch, "getBloodPressureAlarmInfoList");
        return arrayList;
    }

    static /* synthetic */ void b(List list, CountDownLatch countDownLatch, int i2, Object obj) {
        String str;
        LogUtil.a("HealthLife_BasicHealthModelUtils", "getBloodPressureAlarmInfoList errorCode ", Integer.valueOf(i2), " object ", obj);
        if (i2 != 0 || !koq.e(obj, dry.class)) {
            list.addAll(am());
            countDownLatch.countDown();
            return;
        }
        Iterator it = ((List) obj).iterator();
        while (true) {
            if (!it.hasNext()) {
                str = "";
                break;
            }
            dry dryVar = (dry) it.next();
            if (dryVar != null && !TextUtils.isEmpty(dryVar.b())) {
                str = dryVar.b();
                break;
            }
        }
        if (TextUtils.isEmpty(str)) {
            list.addAll(am());
            countDownLatch.countDown();
            return;
        }
        list.addAll(cbi.a((cbj) HiJsonUtil.e(str, cbj.class)));
        if (koq.b(list)) {
            list.addAll(am());
            countDownLatch.countDown();
        } else {
            d((List<cbk>) list);
            e((cbk) list.get(0));
            countDownLatch.countDown();
        }
    }

    public static void e(cbk cbkVar) {
        if (cbkVar == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "setBloodPressureAlarmInfoDaysOfWeek bloodPressureAlarmInfo is null");
        } else {
            bao.e("blood_pressure_day_of_week", String.valueOf(cbkVar.d()));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.util.List] */
    public static List<cbk> b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getBloodPressureAlarmInfoList json ", str);
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        try {
            arrayList = (List) HiJsonUtil.b(str, new TypeToken<List<cbk>>() { // from class: azi.1
            }.getType());
        } catch (JsonParseException e2) {
            LogUtil.b("HealthLife_BasicHealthModelUtils", "getBloodPressureAlarmInfoList JsonParseException ", LogAnonymous.b((Throwable) e2));
        }
        d(arrayList);
        LogUtil.a("HealthLife_BasicHealthModelUtils", "getBloodPressureAlarmInfoList list ", arrayList);
        return arrayList;
    }

    public static void d(List<cbk> list) {
        if (koq.b(list)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "compare list is empty");
        } else {
            Collections.sort(list, new Comparator() { // from class: azj
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return azi.c((cbk) obj, (cbk) obj2);
                }
            });
        }
    }

    static /* synthetic */ int c(cbk cbkVar, cbk cbkVar2) {
        if (cbkVar == null || cbkVar2 == null) {
            return 0;
        }
        int a2 = cbkVar.a();
        int a3 = cbkVar2.a();
        if (a2 == a3) {
            return cbkVar.e() - cbkVar2.e();
        }
        if ((a2 > 2 && a3 > 2) || (a2 < 2 && a3 < 2)) {
            return a2 - a3;
        }
        if (a2 < 2) {
            return 1;
        }
        return a3 < 2 ? -1 : 0;
    }

    private static List<cbk> ak() {
        HiUserPreference c2 = c("BloodPressureMeasurePlan");
        if (c2 == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getBloodPressureAlarmInfoListForHiUserPreference preference is null");
            return new ArrayList();
        }
        return b(c2.getValue());
    }

    public static List<cbk> h() {
        ArrayList arrayList = new ArrayList();
        cbk cbkVar = new cbk(0, 7, 0, 0);
        cbkVar.b(false);
        cbkVar.b(127);
        arrayList.add(cbkVar);
        cbk cbkVar2 = new cbk(9, 22, 0, 9);
        cbkVar2.b(false);
        cbkVar2.b(127);
        arrayList.add(cbkVar2);
        e(cbkVar);
        LogUtil.a("HealthLife_BasicHealthModelUtils", "getDefaultBloodPlan bloodPressureAlarmInfoList ", arrayList);
        return arrayList;
    }

    public static boolean h(int i2) {
        if (Boolean.TRUE.toString().equals(bao.e("is_exist_reward_clover"))) {
            LogUtil.a("HealthLife_BasicHealthModelUtils", "isNeedRewardClover IS_EXIST_REWARD_CLOVER");
            return false;
        }
        if (ad()) {
            LogUtil.a("HealthLife_BasicHealthModelUtils", "isNeedRewardClover isDoctorPlan");
            return false;
        }
        boolean z = CommonUtils.h(bao.e("health_model_challenge_join")) > 0;
        boolean z2 = CommonUtils.h(bao.e("health_model_challenge_id")) >= 200001;
        if (z && z2) {
            LogUtil.a("HealthLife_BasicHealthModelUtils", "isNeedRewardClover isJoinChallenge");
            return false;
        }
        boolean z3 = auz.e(t(), i2, 1) > 0;
        bao.e("is_exist_reward_clover", String.valueOf(z3));
        LogUtil.a("HealthLife_BasicHealthModelUtils", "isNeedRewardClover isExistRewardClover = ", Boolean.valueOf(z3));
        return !z3;
    }

    public static boolean c(int i2) {
        ArrayList<Integer> j2 = dsl.j();
        if (koq.b(j2)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "checkSupportForTaskId taskIdList is empty");
            return false;
        }
        return j2.contains(Integer.valueOf(i2));
    }

    public static long d(long j2, String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getTimeMillisForTimeAndOffsetMinute time is empty");
            return 0L;
        }
        String[] split = str.split(":");
        if (split.length <= 1) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getTimeMillisForTimeAndOffsetMinute length is default min time length");
            return 0L;
        }
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j2);
            calendar.set(11, Integer.parseInt(split[0]));
            calendar.set(12, Integer.parseInt(split[1]) + i2);
            return calendar.getTimeInMillis();
        } catch (NumberFormatException unused) {
            LogUtil.b("HealthLife_BasicHealthModelUtils", "getTimeMillisForTimeAndOffsetMinute NumberFormatException");
            return 0L;
        }
    }

    public static List<Integer> d(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList(2);
        }
        String[] split = str.split(Constants.LINK);
        ArrayList arrayList = new ArrayList(2);
        for (int i2 = 0; i2 < Math.min(2, split.length); i2++) {
            int b2 = CommonUtil.b(BaseApplication.e(), split[i2], -1);
            if (b2 == -1) {
                return new ArrayList(2);
            }
            arrayList.add(Integer.valueOf(b2));
        }
        return arrayList;
    }

    public static Calendar f(String str) {
        Calendar s = s();
        if (TextUtils.isEmpty(str)) {
            return s;
        }
        String[] split = str.split(":");
        if (split.length < 2) {
            return s;
        }
        s.set(11, CommonUtil.h(split[0]));
        s.set(12, CommonUtil.h(split[1]));
        return s;
    }

    public static List<Calendar> e(String str) {
        String[] split;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "convertTimestampInterval timestampInterval is empty");
            return new ArrayList(2);
        }
        ArrayList arrayList = new ArrayList(2);
        if (str.contains(Constants.LINK)) {
            split = str.split(Constants.LINK);
        } else {
            split = str.split(",");
        }
        for (String str2 : split) {
            long h2 = nsn.h(str2);
            if (h2 == 0) {
                LogUtil.h("HealthLife_BasicHealthModelUtils", "convertTimestampInterval timestamp is invalid");
                return new ArrayList(2);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(h2);
            arrayList.add(a(calendar));
        }
        return arrayList;
    }

    public static Calendar a(Calendar calendar) {
        if (calendar == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "setTodayDate calendar is null");
            return s();
        }
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(11, calendar.get(11));
        calendar2.set(12, calendar.get(12));
        calendar2.set(13, calendar.get(13));
        calendar2.set(14, calendar.get(14));
        return calendar2;
    }

    public static void k(int i2) {
        bao.e("health_life_last_sync_date", String.valueOf(i2));
    }

    public static void al() {
        bam.c("HealthLife_BasicHealthModelUtils", new ResponseCallback() { // from class: azk
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                azi.c(i2, (drx) obj);
            }
        });
    }

    static /* synthetic */ void c(int i2, drx drxVar) {
        LogUtil.a("HealthLife_BasicHealthModelUtils", "setChallengeId errorCode ", Integer.valueOf(i2), " object ", drxVar);
        if (drxVar == null) {
            return;
        }
        bao.e("health_model_challenge_id", String.valueOf(drxVar.c()));
        bao.e("health_model_current_challenge_join_time", String.valueOf(drxVar.d()));
    }

    public static ArrayList<Integer> d() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Integer> b2 = b();
        boolean y = y();
        Iterator<Integer> it = b2.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (!y || intValue != 2) {
                if (y || intValue != 3) {
                    arrayList.add(Integer.valueOf(intValue));
                }
            }
        }
        LogUtil.a("HealthLife_BasicHealthModelUtils", "getBasicTaskIdForChallenge source basicIdList ", arrayList.toString());
        return arrayList;
    }

    public static ArrayList<Integer> b() {
        return dsl.d(R.array._2130968657_res_0x7f040051);
    }

    public static String d(HealthLifeBean healthLifeBean) {
        int e2;
        if (healthLifeBean == null) {
            return "";
        }
        int id = healthLifeBean.getId();
        boolean z = id == 6;
        boolean z2 = id == 7;
        if ((!z && !z2) || (e2 = e(healthLifeBean)) == 0 || (e2 == 2 && z2)) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        String valueOf = String.valueOf(calendar.get(11));
        if (!TextUtils.isEmpty(valueOf) && valueOf.length() < 2) {
            valueOf = "0" + valueOf;
        }
        String valueOf2 = String.valueOf(calendar.get(12));
        if (!TextUtils.isEmpty(valueOf2) && valueOf2.length() < 2) {
            valueOf2 = "0" + valueOf2;
        }
        return DateFormatUtil.b(System.currentTimeMillis()) + " " + valueOf + ":" + valueOf2;
    }

    public static int e(HealthLifeBean healthLifeBean) {
        if (healthLifeBean == null) {
            return 1;
        }
        int id = healthLifeBean.getId();
        String target = healthLifeBean.getTarget();
        long currentTimeMillis = System.currentTimeMillis();
        long d2 = d(currentTimeMillis, target, 60);
        if (id == 6) {
            return b(currentTimeMillis, d(currentTimeMillis, target, -60), d2);
        }
        if (id != 7) {
            return 1;
        }
        int recordDay = healthLifeBean.getRecordDay();
        long e2 = jdl.e(currentTimeMillis, bcm.d(recordDay, target) < bcm.b(recordDay, 20, 0) ? 19 : 20, 0);
        long t = jdl.t(currentTimeMillis);
        long e3 = jdl.e(currentTimeMillis);
        long j2 = e3 - t;
        if (d2 - currentTimeMillis > j2) {
            LogUtil.a("HealthLife_BasicHealthModelUtils", "getTimeLimit startTimeOfCurrentDay ", Long.valueOf(t), " endTimeOfCurrentDay ", Long.valueOf(e3), " ondDayTimeMillis ", Long.valueOf(j2), " start ", Long.valueOf(e2), " end ", Long.valueOf(d2), " currentTimeMillis ", Long.valueOf(currentTimeMillis));
            d2 -= j2;
            e2 -= j2;
        }
        return b(currentTimeMillis, e2, d2);
    }

    public static void b(final int i2, final ResponseCallback<dsb> responseCallback) {
        if (HandlerExecutor.c()) {
            d(ThreadPoolManager.d(), new Runnable() { // from class: azl
                @Override // java.lang.Runnable
                public final void run() {
                    azi.b(i2, (ResponseCallback<dsb>) responseCallback);
                }
            });
            return;
        }
        if (responseCallback == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "queryWeekHealthReport callback is null");
            return;
        }
        if (!o(i2)) {
            responseCallback.onResponse(-1, null);
        } else if (!CommonUtil.aa(BaseApplication.e())) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "queryWeekHealthReport isNetworkConnected false");
            responseCallback.onResponse(-1, null);
        } else {
            LogUtil.a("HealthLife_BasicHealthModelUtils", "queryWeekHealthReport startDate ", Integer.valueOf(i2));
            aug.d().d(i2, new IBaseResponseCallback() { // from class: azm
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i3, Object obj) {
                    azi.b(ResponseCallback.this, i3, obj);
                }
            });
        }
    }

    static /* synthetic */ void b(ResponseCallback responseCallback, int i2, Object obj) {
        if (!(obj instanceof dsn)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "queryWeekHealthReport errorCode ", Integer.valueOf(i2), " object ", obj);
            responseCallback.onResponse(i2, null);
            return;
        }
        dsb d2 = ((dsn) obj).d();
        if (d2 == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "queryWeekHealthReport weekReport is null");
            responseCallback.onResponse(i2, null);
        } else {
            responseCallback.onResponse(i2, d2);
        }
    }

    private static boolean o(int i2) {
        int t = t();
        if (t <= 0) {
            return false;
        }
        int b2 = DateFormatUtil.b(jdl.c(System.currentTimeMillis(), 1, 0));
        int b3 = DateFormatUtil.b(jdl.c(DateFormatUtil.c(i2), 1, 0));
        LogUtil.a("HealthLife_BasicHealthModelUtils", "isSupportQueryWeekHealthReport startDate ", Integer.valueOf(i2), " startDateOfWeek ", Integer.valueOf(b3), " joinTime ", Integer.valueOf(t), " currentStartDateOfWeek ", Integer.valueOf(b2));
        if (b3 >= b2) {
            return false;
        }
        return t <= b3 || jdl.e(DateFormatUtil.c(b3), DateFormatUtil.c(t)) <= 3;
    }

    public static float e(List<HealthLifeBean> list, int i2, int i3) {
        if (koq.b(list)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getCloverScale list is empty");
            return 0.0f;
        }
        HealthLifeBean healthLifeBean = list.get(0);
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getCloverScale bean is null");
            return 0.0f;
        }
        if (i3 <= 0 || i3 > healthLifeBean.getRecordDay()) {
            return a(e(list, i2) / a(list, i2));
        }
        return d(h(list, i2), c(list, i2));
    }

    public static boolean c(List<HealthLifeBean> list, int i2) {
        int id;
        if (koq.b(list)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "isCompletedBasic list is empty");
            return false;
        }
        ArrayList<Integer> e2 = e(list);
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null && healthLifeBean.getType() == i2 && (id = healthLifeBean.getId()) != 1 && id != 100001 && c(id) && e2.contains(Integer.valueOf(id))) {
                return healthLifeBean.getComplete() > 0;
            }
        }
        return false;
    }

    public static ArrayList<Integer> e(List<HealthLifeBean> list) {
        ArrayList<Integer> b2 = b();
        ArrayList<Integer> arrayList = new ArrayList<>(3);
        if (koq.c(list)) {
            for (HealthLifeBean healthLifeBean : list) {
                if (healthLifeBean != null && healthLifeBean.getTaskType() == 1) {
                    int id = healthLifeBean.getId();
                    if (b2.contains(Integer.valueOf(id))) {
                        arrayList.add(Integer.valueOf(id));
                    }
                }
            }
        }
        if (arrayList.size() == 3) {
            return arrayList;
        }
        arrayList.clear();
        boolean b3 = b(list);
        Iterator<Integer> it = b2.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (!b3 || intValue != 2) {
                if (b3 || intValue != 3) {
                    arrayList.add(Integer.valueOf(intValue));
                }
            }
        }
        return arrayList;
    }

    public static boolean b(List<HealthLifeBean> list) {
        HealthLifeBean healthLifeBean;
        boolean z = false;
        if (koq.b(list) || (healthLifeBean = list.get(0)) == null) {
            return false;
        }
        int recordDay = healthLifeBean.getRecordDay();
        int h2 = CommonUtils.h(bao.e("health_model_current_challenge_join_time"));
        boolean y = y();
        if (recordDay == DateFormatUtil.b(System.currentTimeMillis())) {
            z = y;
        } else if (y && h2 > 0 && recordDay >= h2) {
            z = true;
        }
        if (z) {
            return true;
        }
        for (HealthLifeBean healthLifeBean2 : list) {
            if (healthLifeBean2 != null && healthLifeBean2.getId() == 2) {
                return z;
            }
        }
        return true;
    }

    public static float d(float f2, boolean z) {
        if (z) {
            return 1.0f;
        }
        return a(f2);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0085 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static float h(java.util.List<com.huawei.health.healthmodel.bean.HealthLifeBean> r8, int r9) {
        /*
            boolean r0 = defpackage.koq.b(r8)
            java.lang.String r1 = "HealthLife_BasicHealthModelUtils"
            r2 = 0
            if (r0 == 0) goto L13
            java.lang.String r8 = "getStepScale list is empty"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r8)
            return r2
        L13:
            java.util.Iterator r0 = r8.iterator()
        L17:
            boolean r3 = r0.hasNext()
            r4 = 1
            if (r3 == 0) goto L41
            java.lang.Object r3 = r0.next()
            com.huawei.health.healthmodel.bean.HealthLifeBean r3 = (com.huawei.health.healthmodel.bean.HealthLifeBean) r3
            if (r3 == 0) goto L17
            int r5 = r3.getType()
            if (r5 != r9) goto L17
            int r5 = r3.getTaskType()
            if (r5 != r4) goto L17
            java.lang.String r0 = "getStepScale isBasicType bean "
            java.lang.String r3 = r3.toString()
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r3}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            r0 = r4
            goto L42
        L41:
            r0 = 0
        L42:
            java.util.Iterator r3 = r8.iterator()
        L46:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto La0
            java.lang.Object r5 = r3.next()
            com.huawei.health.healthmodel.bean.HealthLifeBean r5 = (com.huawei.health.healthmodel.bean.HealthLifeBean) r5
            if (r5 != 0) goto L55
            goto L46
        L55:
            if (r0 == 0) goto L64
            int r6 = r5.getType()
            if (r6 != r9) goto L46
            int r6 = r5.getTaskType()
            if (r6 == r4) goto L72
            goto L46
        L64:
            int r6 = r5.getType()
            if (r6 != r9) goto L46
            int r6 = r5.getId()
            r7 = 2
            if (r6 == r7) goto L72
            goto L46
        L72:
            java.lang.String r8 = "getStepScale bean "
            java.lang.String r9 = r5.toString()
            java.lang.Object[] r8 = new java.lang.Object[]{r8, r9}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r8)
            int r8 = r5.getComplete()
            if (r8 <= 0) goto L88
            r8 = 1065353216(0x3f800000, float:1.0)
            return r8
        L88:
            java.lang.String r8 = r5.getTarget()
            int r8 = defpackage.nsn.e(r8)
            if (r8 > 0) goto L93
            goto L9f
        L93:
            java.lang.String r9 = r5.getResult()
            int r9 = defpackage.nsn.e(r9)
            float r9 = (float) r9
            float r8 = (float) r8
            float r2 = r9 / r8
        L9f:
            return r2
        La0:
            java.lang.String r9 = "getStepScale list "
            java.lang.String r8 = r8.toString()
            java.lang.Object[] r8 = new java.lang.Object[]{r9, r8}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r8)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.azi.h(java.util.List, int):float");
    }

    public static int e(List<HealthLifeBean> list, int i2) {
        int id;
        int i3 = 0;
        if (koq.b(list)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getCompleted list is empty");
            return 0;
        }
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null && healthLifeBean.getType() == i2 && (id = healthLifeBean.getId()) != 1 && id != 100001 && c(id) && healthLifeBean.getComplete() > 0) {
                i3++;
            }
        }
        return i3;
    }

    public static int a(List<HealthLifeBean> list, int i2) {
        int id;
        if (koq.b(list)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getTotal list is empty");
            return 1;
        }
        int i3 = 0;
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null && (id = healthLifeBean.getId()) != 1 && id != 100001 && healthLifeBean.getType() == i2) {
                i3++;
            }
        }
        return Math.max(i3, 1);
    }

    public static float a(float f2) {
        if (f2 <= 0.0f) {
            return 0.0f;
        }
        if (f2 >= 1.0f) {
            return 1.0f;
        }
        return (float) Math.min((Math.floor(f2 * 10.0f) * 0.06000000238418579d) + 0.4000000059604645d, 0.9100000262260437d);
    }

    public static float b(List<HealthLifeBean> list, int i2, int i3) {
        if (koq.b(list)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getCloverScaleToDevice list is empty");
            return 0.0f;
        }
        HealthLifeBean healthLifeBean = list.get(0);
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getCloverScaleToDevice bean is null");
            return 0.0f;
        }
        if (i3 <= 0 || i3 > healthLifeBean.getRecordDay()) {
            return a(e(list, i2) / a(list, i2));
        }
        if (g(list, i2)) {
            return 1.0f;
        }
        return f(list, i2);
    }

    public static boolean g(List<HealthLifeBean> list, int i2) {
        if (koq.b(list)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "isCompletedBasic list is empty");
            return false;
        }
        ArrayList<Integer> e2 = e(list);
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null) {
                int id = healthLifeBean.getId();
                boolean z = healthLifeBean.getTaskType() == 1;
                if (!z) {
                    z = e2.contains(Integer.valueOf(id));
                }
                if (z && healthLifeBean.getType() == i2 && c(id)) {
                    return healthLifeBean.getComplete() > 0;
                }
            }
        }
        return false;
    }

    private static float f(List<HealthLifeBean> list, int i2) {
        if (koq.b(list)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getActiveScale list is empty");
            return 0.0f;
        }
        ArrayList<Integer> e2 = e(list);
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null) {
                boolean z = healthLifeBean.getTaskType() == 1;
                if (!z) {
                    z = e2.contains(Integer.valueOf(healthLifeBean.getId()));
                }
                if (z && healthLifeBean.getType() == i2) {
                    if (healthLifeBean.getComplete() > 0) {
                        return 1.0f;
                    }
                    return d(nsn.e(healthLifeBean.getTarget()), nsn.e(healthLifeBean.getResult()));
                }
            }
        }
        return 0.0f;
    }

    private static float d(int i2, int i3) {
        if (i3 <= 0) {
            return d[0];
        }
        float f2 = i3;
        float f3 = i2 * 0.1f;
        if (f2 <= f3 - 1.0f) {
            return d[1];
        }
        int i4 = 1;
        while (true) {
            float[] fArr = d;
            if (i4 >= fArr.length - 1) {
                return fArr[fArr.length - 1];
            }
            if (f2 >= i4 * f3 && f2 <= ((i4 + 1) * f3) - 1.0f) {
                return fArr[i4];
            }
            i4++;
        }
    }

    public static String a(HealthLifeBean healthLifeBean) {
        int h2;
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getTaskResultString bundle is null");
            return "";
        }
        int complete = healthLifeBean.getComplete();
        if (complete == 3) {
            return nsf.h(R$string.IDS_makeup);
        }
        int id = healthLifeBean.getId();
        int recordDay = healthLifeBean.getRecordDay();
        String target = healthLifeBean.getTarget();
        if (recordDay <= 0 || !c(id) || TextUtils.isEmpty(target)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getTaskResultString recordDay ", Integer.valueOf(recordDay), " id ", Integer.valueOf(id), " targetText ", target);
            return "";
        }
        String result = healthLifeBean.getResult();
        boolean g2 = g(id);
        if (complete > 0 && (!g2 ? (h2 = CommonUtils.h(target)) <= 0 || CommonUtils.h(result) < h2 : !a(recordDay, id, result, target))) {
            if (complete == 4) {
                return nsf.h(com.huawei.basichealthmodel.R$string.IDS_rewards);
            }
            if (healthLifeBean.getRest() == 1) {
                return nsf.h(com.huawei.basichealthmodel.R$string.IDS_relax);
            }
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getTaskResultString other status");
        }
        String a2 = a(g2, result, id);
        if (id == 5 && dsl.b(healthLifeBean)) {
            return h(healthLifeBean);
        }
        String d2 = d(id, target);
        int i2 = com.huawei.basichealthmodel.R$string.IDS_splash_no_space;
        Object[] objArr = new Object[2];
        if ("--".equals(d2)) {
            a2 = "--";
        }
        objArr[0] = a2;
        objArr[1] = d2;
        return nsf.b(i2, objArr);
    }

    public static boolean g(HealthLifeBean healthLifeBean) {
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "hasCompletedForSlumber bean is null");
            return false;
        }
        return a(healthLifeBean.getRecordDay(), healthLifeBean.getId(), healthLifeBean.getResult(), healthLifeBean.getTarget());
    }

    private static boolean a(int i2, int i3, String str, String str2) {
        if (i2 <= 0 || !c(i3) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "hasCompletedForSlumber recordDay ", Integer.valueOf(i2), " id ", Integer.valueOf(i3), " resultText ", str, " targetText ", str2);
            return false;
        }
        if (i3 == 6) {
            int a2 = bcm.a(i2, str);
            int d2 = bcm.d(i2, str2);
            LogUtil.a("HealthLife_BasicHealthModelUtils", "hasCompletedForSlumber ID_TYPE_WAKE_UP result ", Integer.valueOf(a2), " target ", Integer.valueOf(d2));
            return Math.abs(d2 - a2) <= 60;
        }
        if (i3 != 7) {
            return false;
        }
        int a3 = bcm.a(i2, str);
        int d3 = bcm.d(i2, str2);
        LogUtil.a("HealthLife_BasicHealthModelUtils", "hasCompletedForSlumber ID_TYPE_SLEEP result ", Integer.valueOf(a3), " target ", Integer.valueOf(d3));
        return a3 <= d3 + 60;
    }

    private static String a(boolean z, String str, int i2) {
        if (z) {
            String d2 = dsl.d(str);
            return TextUtils.isEmpty(d2) ? "--" : nsj.c(BaseApplication.e(), f(d2).getTimeInMillis(), 1);
        }
        if (i2 == 10) {
            return c(Math.min(CommonUtils.a(str), 6000.0d) / 1000.0d, 1, -1);
        }
        return c(CommonUtils.h(str), 1, -1);
    }

    private static String h(HealthLifeBean healthLifeBean) {
        int a2 = ((eyw) HiJsonUtil.e(healthLifeBean.getExtendInfo(), eyw.class)).a();
        return nsf.b(com.huawei.basichealthmodel.R$string.IDS_health_life_pressure_grade, Integer.valueOf(a2), l(a2));
    }

    private static String l(int i2) {
        if (i2 >= 1 && i2 <= 29) {
            return nsf.h(R$string.IDS_hw_pressure_relaxed);
        }
        if (i2 > 29 && i2 < 60) {
            return nsf.h(R$string.IDS_hw_show_healthdata_bloodsugar_status_overall_normal);
        }
        if (i2 >= 60 && i2 < 80) {
            return nsf.h(com.huawei.basichealthmodel.R$string.IDS_health_life_grade_med);
        }
        return nsf.h(R$string.IDS_details_sleep_grade_high);
    }

    public static String d(int i2, String str) {
        Context e2 = BaseApplication.e();
        switch (i2) {
            case 2:
                int e3 = nsn.e(str);
                return nsf.a(R$plurals.IDS_health_step_local, e3, c(e3, 1, 0));
            case 3:
                int e4 = nsn.e(str);
                return nsf.a(R$plurals.IDS_minute_no_space, e4, Integer.valueOf(e4));
            case 4:
            case 5:
            case 8:
            case 9:
            case 11:
            case 12:
            case 13:
            case 14:
                int e5 = nsn.e(str);
                return e5 <= 0 ? "--" : nsf.a(R$plurals.IDS_time_no_space, e5, Integer.valueOf(e5));
            case 6:
            case 7:
                return nsj.c(e2, f(str).getTimeInMillis(), 1);
            case 10:
                double b2 = b(nsn.j(str) / 1000.0d, -1);
                return nsf.a(R$plurals.IDS_liter_no_space, UnitUtil.e(b2), c(b2, 1, -1));
            default:
                return str;
        }
    }

    public static SparseIntArray lS_() {
        SparseIntArray sparseIntArray = m;
        if (sparseIntArray == null || sparseIntArray.size() <= 0) {
            m = dsl.ZI_(g);
        }
        return m;
    }

    public static int d(List<HealthLifeBean> list, int i2) {
        if (koq.b(list)) {
            return 2;
        }
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null && i2 == healthLifeBean.getType() && healthLifeBean.getTaskType() == 1) {
                return healthLifeBean.getId();
            }
        }
        return bat.c(list) ? 3 : 2;
    }

    private static boolean g(List<HealthLifeBean> list) {
        if (koq.b(list)) {
            ReleaseLogUtil.a("HealthLife_BasicHealthModelUtils", "isPerfectClover list ", list);
            return false;
        }
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null && healthLifeBean.getId() == 1) {
                return healthLifeBean.getComplete() > 0;
            }
        }
        return false;
    }

    public static boolean b(List<HealthLifeBean> list, float f2, float f3, float f4) {
        boolean g2 = g(list);
        if (g2) {
            return true;
        }
        if (f2 < 1.0f || f3 < 1.0f || f4 < 1.0f) {
            return g2;
        }
        if (a(list) > 0) {
            return i(list);
        }
        return true;
    }

    public static int a(List<HealthLifeBean> list) {
        int id;
        int i2 = 0;
        if (koq.b(list)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getOtherTaskCount list is empty");
            return 0;
        }
        ArrayList<Integer> e2 = e(list);
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null && (id = healthLifeBean.getId()) != 1 && id != 100001 && !e2.contains(Integer.valueOf(id))) {
                i2++;
            }
        }
        return i2;
    }

    public static boolean i(List<HealthLifeBean> list) {
        int id;
        if (koq.b(list)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "isCompletedAllOther list is empty");
            return false;
        }
        ArrayList<Integer> e2 = e(list);
        boolean z = false;
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null && (id = healthLifeBean.getId()) != 1 && id != 100001 && c(id) && !e2.contains(Integer.valueOf(id))) {
                if (healthLifeBean.getComplete() <= 0) {
                    return false;
                }
                if (!z) {
                    z = true;
                }
            }
        }
        return z;
    }

    public static int c() {
        int i2;
        String p = p();
        int b2 = aus.b(p);
        if (b2 != 0) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "deleteTaskConfigResult:", Integer.valueOf(b2));
            i2 = 401;
        } else {
            i2 = 0;
        }
        int d2 = auz.d(p);
        if (d2 == 0) {
            return i2;
        }
        LogUtil.h("HealthLife_BasicHealthModelUtils", "deleteTaskResult:", Integer.valueOf(d2));
        return 402;
    }

    public static int d(boolean z) {
        c(z);
        bao.e("health_model_reminder", "");
        Set<String> c2 = jfa.c(bao.f305a);
        ArrayList arrayList = new ArrayList();
        for (String str : c2) {
            if (!TextUtils.isEmpty(str) && !"health_model_show_dialog_time".equals(str) && !"health_model_show_dialog_count".equals(str) && !"health_model_plugin_time".equals(str) && !"health_model_plugin_time_oversea".equals(str) && !"health_model_device_link_version".equals(str) && (z || !knl.e("health_model_is_agree_protocol").equals(str))) {
                arrayList.add(str);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            bao.c((String) it.next(), "");
        }
        bad.b().b("HealthLife_BasicHealthModelUtils");
        return 0;
    }

    private static void c(boolean z) {
        bby.d();
        for (Integer num : bby.c()) {
            bby.c(true, num.intValue(), "");
            bby.c(false, num.intValue(), "");
        }
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("health_model_reminder");
        hiUserPreference.setValue("");
        LogUtil.a("HealthLife_BasicHealthModelUtils", "resetHealthModelHiUserPreference isSuccess ", Boolean.valueOf(HiHealthManager.d(BaseApplication.e()).setUserPreference(hiUserPreference)));
        HashMap<Integer, int[]> a2 = bby.a();
        Iterator<Integer> it = a2.keySet().iterator();
        while (it.hasNext()) {
            int[] iArr = a2.get(it.next());
            if (iArr != null && iArr.length >= 1) {
                jdh.c().a(iArr[1]);
            }
        }
        if (z) {
            hiUserPreference.setKey("health_model_is_agree_protocol");
            hiUserPreference.setValue("");
            LogUtil.a("HealthLife_BasicHealthModelUtils", "resetHealthModelHiUserPreference isSuccess ", Boolean.valueOf(HiHealthManager.d(BaseApplication.e()).setUserPreference(hiUserPreference)));
        }
    }

    public static SparseIntArray lR_() {
        SparseIntArray sparseIntArray = j;
        if (sparseIntArray == null || sparseIntArray.size() <= 0) {
            j = dsl.ZI_(h);
        }
        return j;
    }

    public static SparseIntArray lQ_() {
        SparseIntArray sparseIntArray = f;
        if (sparseIntArray == null || sparseIntArray.size() <= 0) {
            f = dsl.ZI_(b);
        }
        return f;
    }

    public static SparseIntArray lP_() {
        SparseIntArray sparseIntArray = i;
        if (sparseIntArray == null || sparseIntArray.size() <= 0) {
            i = dsl.ZI_(c);
        }
        return i;
    }

    public static boolean b(List<HealthLifeBean> list, int i2) {
        if (koq.b(list)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getCompleted list is empty");
            return false;
        }
        HealthLifeBean healthLifeBean = list.get(0);
        if (healthLifeBean != null && healthLifeBean.getRecordDay() < q()) {
            int a2 = a(list, i2);
            return a2 > 0 && e(list, i2) >= a2;
        }
        return g(list, i2);
    }

    public static int a(boolean z) {
        Context e2 = BaseApplication.e();
        String e3 = bao.e("health_model_days_to_enter");
        String e4 = bao.e("health_model_entry_time_millis");
        long timeInMillis = s().getTimeInMillis();
        int t = t();
        int i2 = 1;
        boolean z2 = t > 0 && t >= DateFormatUtil.b(timeInMillis);
        if (TextUtils.isEmpty(e3) || TextUtils.isEmpty(e4) || z2) {
            e(timeInMillis);
            LogUtil.a("HealthLife_BasicHealthModelUtils", "isContinuousEntry initContinuousEntry");
            return 1;
        }
        int e5 = nsn.e(e3);
        long h2 = nsn.h(e4);
        int e6 = jdl.e(h2, timeInMillis) - 1;
        if (e6 == 1) {
            i2 = e5 + 1;
            bao.e("health_model_days_to_enter", String.valueOf(i2));
            bao.e("health_model_entry_time_millis", String.valueOf(timeInMillis));
        } else if (e6 > 1) {
            if (z || CommonUtil.aa(e2)) {
                e(timeInMillis);
            }
            i2 = e5;
        } else {
            if (e6 >= 0) {
                LogUtil.c("HealthLife_BasicHealthModelUtils", "isContinuousEntry today");
            } else if (z) {
                e(timeInMillis);
            }
            i2 = e5;
        }
        if (e6 != 0) {
            LogUtil.a("HealthLife_BasicHealthModelUtils", "isContinuousEntry daysToEnter ", Integer.valueOf(i2), " entryTimeMillis ", Long.valueOf(h2));
        }
        return i2;
    }

    private static void e(long j2) {
        bao.e("health_model_days_to_enter", String.valueOf(1));
        bao.e("health_model_entry_time_millis", String.valueOf(j2));
    }

    public static void a(boolean z, String str, Object... objArr) {
        if (z) {
            LogUtil.a(str, objArr);
        } else {
            LogUtil.c(str, objArr);
        }
    }

    public static String i() {
        return nsf.h(R$string.IDS_health_clover_title);
    }

    public static TaskConfigBean d(awq awqVar, int i2) {
        TaskConfigBean taskConfigBean = new TaskConfigBean();
        List<TaskConfigBean> taskList = a(awqVar).getTaskList();
        if (taskList == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getTaskConfig list is null");
            return taskConfigBean;
        }
        for (TaskConfigBean taskConfigBean2 : taskList) {
            if (taskConfigBean2 != null && taskConfigBean2.getId() == i2) {
                return taskConfigBean2;
            }
        }
        return taskConfigBean;
    }

    private static ConfigBean a(awq awqVar) {
        ConfigBean configBean = new ConfigBean();
        if (awqVar == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getTaskConfigEntity manager is null");
            return configBean;
        }
        ConfigBean a2 = awqVar.a();
        if (a2 != null) {
            return a2;
        }
        LogUtil.h("HealthLife_BasicHealthModelUtils", "getTaskConfigEntity entity is null");
        return configBean;
    }

    public static int b(int i2) {
        return Math.min(Math.max(i2 - 2, 0), dsl.j().size() - 2);
    }

    public static SparseArray<HealthLifeBean> lO_(int i2, SparseArray<List<HealthLifeBean>> sparseArray) {
        SparseArray<HealthLifeBean> sparseArray2 = new SparseArray<>();
        if (sparseArray == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getTaskArray sparseArray is null");
            return sparseArray2;
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < sparseArray.size(); i3++) {
            arrayList.add(Integer.valueOf(sparseArray.keyAt(i3)));
        }
        Collections.sort(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            Iterator it2 = new ArrayList(sparseArray.get(num.intValue())).iterator();
            while (it2.hasNext()) {
                HealthLifeBean healthLifeBean = (HealthLifeBean) it2.next();
                if (healthLifeBean != null && healthLifeBean.getId() == i2) {
                    sparseArray2.append(num.intValue(), healthLifeBean);
                }
            }
        }
        return sparseArray2;
    }

    public static int v() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(DateFormatUtil.c(awq.e().e(DateFormatUtil.b(System.currentTimeMillis()))));
        return calendar.get(7);
    }

    public static SparseIntArray lM_() {
        try {
            HashMap hashMap = (HashMap) HiJsonUtil.b(bao.g("intensity_week_status"), new TypeToken<HashMap<Integer, Integer>>() { // from class: azi.5
            }.getType());
            if (hashMap == null) {
                return new SparseIntArray();
            }
            SparseIntArray sparseIntArray = new SparseIntArray();
            for (Map.Entry entry : hashMap.entrySet()) {
                if (entry != null) {
                    sparseIntArray.append(((Integer) entry.getKey()).intValue(), ((Integer) entry.getValue()).intValue());
                }
            }
            return sparseIntArray;
        } catch (JsonParseException e2) {
            LogUtil.b("HealthLife_BasicHealthModelUtils", "getIntensityWeekStatus exception ", LogAnonymous.b((Throwable) e2));
            return new SparseIntArray();
        }
    }

    public static Bitmap lH_(int i2, int i3) {
        try {
            return nrf.cIj_(nrf.cHR_(i2), new int[]{ContextCompat.getColor(BaseApplication.e(), i3)}, true);
        } catch (Resources.NotFoundException unused) {
            LogUtil.b("HealthLife_BasicHealthModelUtils", "getBitmap NotFoundException");
            return null;
        }
    }

    public static int c(long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j2);
        return (calendar.get(11) * 3600) + (calendar.get(12) * 60);
    }

    public static boolean ai() {
        return BaseApplication.j();
    }

    public static int c(List<HealthLifeBean> list) {
        int id;
        int i2 = 0;
        if (koq.b(list)) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getRewardsCompleted list is empty");
            return 0;
        }
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null && (id = healthLifeBean.getId()) != 100001 && c(id) && healthLifeBean.getComplete() == 4) {
                i2++;
            }
        }
        return i2;
    }

    public static SparseIntArray lJ_(final int i2, final int i3) {
        int i4;
        if (HandlerExecutor.c()) {
            d(ThreadPoolManager.d(), new Runnable() { // from class: azf
                @Override // java.lang.Runnable
                public final void run() {
                    azi.lJ_(i2, i3);
                }
            });
        }
        int a2 = auz.a(i2, i3, 1);
        int a3 = auz.a(i2, i3) - a2;
        int q = q();
        if (q <= 0 || i3 < q) {
            i4 = a2;
        } else if (i2 >= q) {
            i4 = auz.a(i2, i3, 100001);
            a3 -= i4;
        } else {
            int b2 = DateFormatUtil.b(jdl.v(DateFormatUtil.c(q)));
            int a4 = auz.a(i2, b2, 1);
            int a5 = auz.a(q, i3, 100001);
            LogUtil.a("HealthLife_BasicHealthModelUtils", "getCompleteNumber startDate ", Integer.valueOf(i2), " tempEndDate ", Integer.valueOf(b2), " upgradeTime ", Integer.valueOf(q), " energized ", Integer.valueOf(a4), " newEnergized ", Integer.valueOf(a5), " endDate ", Integer.valueOf(i3), " complete ", Integer.valueOf(a3));
            i4 = a4 + a5;
            a3 -= a5;
        }
        SparseIntArray sparseIntArray = new SparseIntArray(16);
        sparseIntArray.append(1, a2);
        sparseIntArray.append(100001, i4);
        sparseIntArray.append(0, a3);
        return sparseIntArray;
    }

    public static int b(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            ReleaseLogUtil.a("HealthLife_BasicHealthModelUtils", "getDate hiHealthData is null");
            return 0;
        }
        return DateFormatUtil.b(hiHealthData.getStartTime());
    }

    public static long c(HealthLifeBean healthLifeBean) {
        if (healthLifeBean == null) {
            ReleaseLogUtil.a("HealthLife_BasicHealthModelUtils", "getStartTime bean is null");
            return 0L;
        }
        return DateFormatUtil.c(healthLifeBean.getRecordDay());
    }

    public static long lN_(SparseArray<HealthLifeBean> sparseArray) {
        if (sparseArray == null) {
            ReleaseLogUtil.a("HealthLife_BasicHealthModelUtils", "getStartTime sparseArray is null");
            return 0L;
        }
        int keyAt = sparseArray.keyAt(0);
        long c2 = c(sparseArray.get(keyAt));
        return c2 <= 0 ? DateFormatUtil.c(keyAt) : c2;
    }

    public static long b(HealthLifeBean healthLifeBean) {
        if (healthLifeBean == null) {
            ReleaseLogUtil.a("HealthLife_BasicHealthModelUtils", "getEndTime bean is null");
            return 0L;
        }
        return jdl.e(c(healthLifeBean));
    }

    public static long lK_(SparseArray<HealthLifeBean> sparseArray) {
        if (sparseArray == null) {
            ReleaseLogUtil.a("HealthLife_BasicHealthModelUtils", "getEndTime sparseArray is null");
            return 0L;
        }
        int keyAt = sparseArray.keyAt(sparseArray.size() - 1);
        long b2 = b(sparseArray.get(keyAt));
        return b2 <= 0 ? jdl.e(DateFormatUtil.c(keyAt)) : b2;
    }

    public static void e(Context context, int i2) {
        if (context == null) {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "jumpWeekReport context or record is null");
            return;
        }
        StringBuilder sb = new StringBuilder("#/ReportDetail?from=0&startTime=");
        sb.append(i2);
        LogUtil.a("HealthLife_BasicHealthModelUtils", "path ", sb.toString());
        bzs.e().initH5Pro();
        H5ProClient.getServiceManager().registerService(HealthModelH5ProService.class);
        b(context, "com.huawei.health.h5.blood-pressure", sb.toString());
    }

    public static SpannableString lI_(int i2, int i3, int i4) {
        String string;
        String c2 = c(i2, 1, 0);
        Context context = com.huawei.hwcommonmodel.application.BaseApplication.getContext();
        Resources resources = context.getResources();
        String quantityString = resources.getQuantityString(R$plurals.IDS_health_model_mission_complete_number, i2, Integer.valueOf(i2));
        if (i4 == 1) {
            string = resources.getString(com.huawei.basichealthmodel.R$string.IDS_weekly_report_week, quantityString);
        } else if (i4 == 2) {
            string = resources.getString(com.huawei.basichealthmodel.R$string.IDS_weekly_report_week_has_percent, quantityString, c(i3, 2, 0));
        } else if (i4 == 5) {
            c2 = c(i3, 2, 0);
            string = resources.getString(com.huawei.basichealthmodel.R$string.IDS_weekly_report_clover_rank, c2);
        } else if (i4 == 6) {
            string = resources.getString(com.huawei.basichealthmodel.R$string.IDS_weekly_report_no_this_year, quantityString);
        } else {
            LogUtil.h("HealthLife_BasicHealthModelUtils", "getCloversText scene ", Integer.valueOf(i4));
            string = "";
        }
        SpannableString spannableString = new SpannableString(string);
        int indexOf = string.indexOf(c2);
        if (indexOf <= -1) {
            return spannableString;
        }
        int length = c2.length() + indexOf;
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R$color.emuiColor4)), indexOf, length, 33);
        if (i4 == 5) {
            spannableString.setSpan(nsk.cKO_(), indexOf, length, 33);
        } else {
            spannableString.setSpan(new nmj(nsk.cKM_()), indexOf, length, 33);
            if (LanguageUtil.h(context)) {
                spannableString.setSpan(new AbsoluteSizeSpan(38, true), indexOf, length, 33);
            }
        }
        return spannableString;
    }

    public static SparseArray<String> lW_(int i2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        try {
            Date parse = simpleDateFormat.parse(String.valueOf(i2));
            if (parse == null) {
                LogUtil.h("HealthLife_BasicHealthModelUtils", "getWeekDateByInt date is null");
                return new SparseArray<>();
            }
            calendar.setTime(parse);
            SparseArray<String> sparseArray = new SparseArray<>();
            lX_(sparseArray, calendar);
            return sparseArray;
        } catch (ParseException e2) {
            LogUtil.b("HealthLife_BasicHealthModelUtils", "getWeekDateByInt ParseException ", LogAnonymous.b((Throwable) e2));
            return new SparseArray<>();
        }
    }

    private static void lX_(SparseArray<String> sparseArray, Calendar calendar) {
        ArrayList arrayList = new ArrayList();
        calendar.setFirstDayOfWeek(1);
        calendar.add(5, calendar.getFirstDayOfWeek() - calendar.get(7));
        arrayList.add(UnitUtil.a(calendar.getTime(), 22));
        int i2 = 0;
        for (int i3 = 0; i3 < 6; i3++) {
            calendar.add(5, 1);
            arrayList.add(UnitUtil.a(calendar.getTime(), 22));
        }
        while (i2 < arrayList.size()) {
            int i4 = i2 + 1;
            sparseArray.put(i4, (String) arrayList.get(i2));
            i2 = i4;
        }
    }

    public static SparseArray<String> lU_(int i2) {
        SparseArray<String> sparseArray = new SparseArray<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(o());
        calendar.add(5, i2 * (-7));
        lX_(sparseArray, calendar);
        return sparseArray;
    }

    public static Date o() {
        int e2 = awq.e().e(DateFormatUtil.b(System.currentTimeMillis()));
        LogUtil.a("HealthLife_BasicHealthModelUtils", "getRealDate recordDay= ", Integer.valueOf(e2));
        return new Date(DateFormatUtil.c(e2));
    }

    public static SparseIntArray lL_(int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(o());
        calendar.add(5, i2 * (-7));
        return lV_(calendar);
    }

    public static SparseIntArray lT_(int i2) {
        try {
            Date parse = new SimpleDateFormat("yyyyMMdd", Locale.ROOT).parse(String.valueOf(i2));
            if (parse == null) {
                return new SparseIntArray();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parse);
            return lV_(calendar);
        } catch (IllegalArgumentException | ParseException e2) {
            LogUtil.b("HealthLife_BasicHealthModelUtils", "getWeekArrayFromInt exception ", LogAnonymous.b((Object) e2));
            return new SparseIntArray();
        }
    }

    private static SparseIntArray lV_(Calendar calendar) {
        calendar.setFirstDayOfWeek(1);
        calendar.add(5, calendar.getFirstDayOfWeek() - calendar.get(7));
        SparseIntArray sparseIntArray = new SparseIntArray();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ROOT);
            for (int i2 = 1; i2 <= 7; i2++) {
                sparseIntArray.append(CommonUtils.h(simpleDateFormat.format(calendar.getTime())), i2);
                calendar.add(5, 1);
            }
        } catch (IllegalArgumentException e2) {
            LogUtil.b("HealthLife_BasicHealthModelUtils", "getWeekDate exception ", LogAnonymous.b((Throwable) e2));
        }
        return sparseIntArray;
    }

    public static int j(int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(5, i2 * (-7));
        calendar.setFirstDayOfWeek(1);
        int i3 = calendar.get(7);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        calendar.add(5, calendar.getFirstDayOfWeek() - i3);
        try {
            return Integer.parseInt(simpleDateFormat.format(calendar.getTime()));
        } catch (NumberFormatException unused) {
            LogUtil.b("HealthLife_BasicHealthModelUtils", "getWeekMondayFormPageNumber ParseException ");
            return 0;
        }
    }
}
