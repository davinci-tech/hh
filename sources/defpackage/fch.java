package defpackage;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.sleep.SleepApi;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/* loaded from: classes3.dex */
public class fch {
    private static int[] c() {
        return new int[]{3, ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL, 1};
    }

    private fch() {
    }

    public static void d(Date date, fda fdaVar) {
        LogUtil.a("SleepDayDataCache", "setSleepDailyResultBeanCache time: ", date);
        String b = nsj.b(date.getTime());
        SharedPreferenceManager.e(BaseApplication.e(), "day_sleep_cache", "CACHE_KEY_MGMT" + b, jdr.b(Collections.singletonList(fdaVar), null), (StorageParams) null);
    }

    public static fda b(Date date) {
        String b = nsj.b(date.getTime());
        ArrayList arrayList = new ArrayList();
        try {
            jdr.d(SharedPreferenceManager.b(BaseApplication.e(), "day_sleep_cache", "CACHE_KEY_MGMT" + b), fch.class.getClassLoader(), null, arrayList);
            if (koq.b(arrayList)) {
                return null;
            }
            return (fda) arrayList.get(0);
        } catch (Exception e) {
            LogUtil.b("SleepDayDataCache", "getSleepDailyResultBean Exception", ExceptionUtils.d(e));
            return null;
        }
    }

    public static <T> void b(Date date, List<T> list) {
        LogUtil.a("SleepDayDataCache", "setSleepDeviceCache time: ", date);
        String b = nsj.b(date.getTime());
        SharedPreferenceManager.e(BaseApplication.e(), "day_sleep_cache", "CACHE_KEY_DEVICE" + b, jdr.b(list, null), (StorageParams) null);
    }

    public static <T> List<T> d(Date date) {
        String b = nsj.b(date.getTime());
        ArrayList arrayList = new ArrayList();
        try {
            jdr.d(SharedPreferenceManager.b(BaseApplication.e(), "day_sleep_cache", "CACHE_KEY_DEVICE" + b), fch.class.getClassLoader(), null, arrayList);
            return arrayList;
        } catch (Exception e) {
            LogUtil.b("SleepDayDataCache", "getSleepDeviceCache Exception", ExceptionUtils.d(e));
            return Collections.EMPTY_LIST;
        }
    }

    public static void a(final Date date) {
        ReleaseLogUtil.e("R_Sleep_SleepDayDataCache", "startSetSleepCache", date);
        int b = DateFormatUtil.b(date.getTime());
        ThreadPoolManager d = ThreadPoolManager.d();
        final String valueOf = String.valueOf(b);
        d.execute(new Runnable() { // from class: fcg
            @Override // java.lang.Runnable
            public final void run() {
                fch.c(date, valueOf);
            }
        });
    }

    static /* synthetic */ void c(Date date, final String str) {
        if (a(BaseApplication.e(), "KnitSleepDetailActivity")) {
            LogUtil.a("SleepDayDataCache", "sleep detail page is running");
            return;
        }
        d(date, new CommonUiBaseResponse() { // from class: fch.3
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public void onResponse(int i, Object obj) {
                LogUtil.a("SleepDayDataCache", "requestGetDayDetailData errorCode = ", Integer.valueOf(i));
                if (i == 0 && obj != null) {
                    SparseArray sparseArray = (SparseArray) obj;
                    ReleaseLogUtil.e("SleepDayDataCache", "request detail data back,  ", sparseArray.toString());
                    fch.awj_(str, sparseArray);
                    return;
                }
                LogUtil.a("SleepDayDataCache", "requestGetDayDetailData else");
            }
        });
        c(date, new CommonUiBaseResponse() { // from class: fch.5
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public void onResponse(int i, Object obj) {
                LogUtil.a("SleepDayDataCache", "requestGetCoreSleepDetailData errorCode = ", Integer.valueOf(i));
                if (i == 0 && obj != null) {
                    List list = (List) obj;
                    LogUtil.a("SleepDayDataCache", "request summary data back,", list.toString());
                    fch.d(str, (List<fdp>) list);
                    return;
                }
                LogUtil.a("SleepDayDataCache", "requestGetCoreSleepDetailData else.");
            }
        });
        SleepApi sleepApi = (SleepApi) Services.a("Main", SleepApi.class);
        if (sleepApi != null) {
            sleepApi.requestSleepDevice(date, new CommonUiBaseResponse() { // from class: fch.1
                @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                public void onResponse(int i, Object obj) {
                    LogUtil.a("SleepDayDataCache", "set device cache ,", obj);
                }
            });
        }
    }

    private static void d(Date date, final CommonUiBaseResponse commonUiBaseResponse) {
        long e = jdl.e(date.getTime(), 20, 0);
        long e2 = jdl.e(jdl.b(date.getTime(), -1), 20, 0);
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(DateFormatUtil.a(e2, DateFormatUtil.DateFormatType.DATE_FORMAT_MILS), DateFormatUtil.a(e - 1, DateFormatUtil.DateFormatType.DATE_FORMAT_MILS), HiDataReadOption.TimeFormatType.DATE_FORMAT_MILLISECONDS);
        hiDataReadOption.setType(new int[]{22100});
        hiDataReadOption.setSortOrder(0);
        HiHealthManager.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: fch.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (!(obj instanceof SparseArray)) {
                    CommonUiBaseResponse.this.onResponse(1, obj);
                }
                CommonUiBaseResponse.this.onResponse(i, obj);
            }
        });
    }

    private static void c(Date date, final CommonUiBaseResponse commonUiBaseResponse) {
        fcd.c().b(date, date, c(), new IBaseResponseCallback() { // from class: fch.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                CommonUiBaseResponse.this.onResponse(i, obj);
            }
        });
    }

    public static void awj_(String str, SparseArray<List<HiHealthData>> sparseArray) {
        LogUtil.a("SleepDayDataCache", "setSleepDetailCache time: ", str);
        Context e = BaseApplication.e();
        b(e, sparseArray.get(22100), "CACHE_KEY_CORE");
        SharedPreferenceManager.e(e, "day_sleep_cache", "CACHE_KEY_TIME", str, (StorageParams) null);
    }

    private static boolean a(Context context, String str) {
        if (context == null || str == null || !(context.getSystemService("activity") instanceof ActivityManager)) {
            return false;
        }
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        ComponentName componentName = koq.c(runningTasks) ? runningTasks.get(0).topActivity : null;
        if (componentName == null) {
            return false;
        }
        return componentName.getClassName().contains(str);
    }

    private static void b(Context context, List<HiHealthData> list, String str) {
        SharedPreferenceManager.e(context, "day_sleep_cache", str, jdr.b(list, null), (StorageParams) null);
    }

    public static SparseArray<List<HiHealthData>> awg_(String str) {
        LogUtil.a("SleepDayDataCache", "getSleepDetailCache time: ", str);
        SparseArray<List<HiHealthData>> sparseArray = new SparseArray<>();
        Context e = BaseApplication.e();
        if (str.equals(SharedPreferenceManager.b(e, "day_sleep_cache", "CACHE_KEY_TIME"))) {
            try {
                awh_(e, "CACHE_KEY_CORE", 22100, sparseArray);
            } catch (Exception unused) {
                LogUtil.c("SleepDayDataCache", "wrong type");
            }
        }
        return sparseArray;
    }

    private static void awh_(Context context, String str, int i, SparseArray<List<HiHealthData>> sparseArray) {
        String b = SharedPreferenceManager.b(context, "day_sleep_cache", str);
        ArrayList arrayList = new ArrayList();
        jdr.d(b, fch.class.getClassLoader(), null, arrayList);
        sparseArray.put(i, arrayList);
    }

    public static boolean awi_(SparseArray<List<HiHealthData>> sparseArray, SparseArray<List<HiHealthData>> sparseArray2) {
        return sparseArray == null || koq.b(sparseArray.get(22100)) || b(sparseArray.get(22100), sparseArray2.get(22100));
    }

    private static boolean b(List<HiHealthData> list, List<HiHealthData> list2) {
        return !jdr.b(list, null).equals(jdr.b(list2, null));
    }

    public static void d(String str, List<fdp> list) {
        LogUtil.a("SleepDayDataCache", "setSleepSummaryCache time: ", str);
        Context e = BaseApplication.e();
        c(e, list, "CACHE_KEY_SUMMARY");
        SharedPreferenceManager.e(e, "day_sleep_summary_cache", "CACHE_KEY_TIME_SUMMARY", str, (StorageParams) null);
    }

    private static void c(Context context, List<fdp> list, String str) {
        SharedPreferenceManager.e(context, "day_sleep_summary_cache", str, jdr.b(list, null), (StorageParams) null);
    }

    public static List<fdp> e(String str) {
        LogUtil.a("SleepDayDataCache", "getSleepSummaryCache: ", str);
        ArrayList arrayList = new ArrayList();
        Context e = BaseApplication.e();
        if (str.equals(SharedPreferenceManager.b(e, "day_sleep_summary_cache", "CACHE_KEY_TIME_SUMMARY"))) {
            e(e, "CACHE_KEY_SUMMARY", arrayList);
        }
        return arrayList;
    }

    private static void e(Context context, String str, List<fdp> list) {
        jdr.d(SharedPreferenceManager.b(context, "day_sleep_summary_cache", str), fch.class.getClassLoader(), null, list);
    }

    public static <T> boolean e(List<T> list, List<T> list2) {
        if (koq.c(list)) {
            return c(list, list2);
        }
        return true;
    }

    private static boolean c(List list, List list2) {
        return !jdr.b(list, null).equals(jdr.b(list2, null));
    }

    public static void d(String str, HiHealthData hiHealthData) {
        LogUtil.a("SleepDayDataCache", "setSleepCardCache time for card: ", str);
        Context e = BaseApplication.e();
        a(e, hiHealthData, "CACHE_KEY_CARD");
        SharedPreferenceManager.e(e, "day_sleep_summary_cache", "CACHE_KEY_TIME_CARD", str, (StorageParams) null);
    }

    private static void a(Context context, HiHealthData hiHealthData, String str) {
        SharedPreferenceManager.e(context, "day_sleep_summary_cache", str, jdr.b(Collections.singletonList(hiHealthData), null), (StorageParams) null);
    }

    public static List<HiHealthData> b(String str) {
        LogUtil.a("SleepDayDataCache", "getSleepCardCache: ", str);
        ArrayList arrayList = new ArrayList();
        Context e = BaseApplication.e();
        if (str.equals(SharedPreferenceManager.b(e, "day_sleep_summary_cache", "CACHE_KEY_TIME_CARD"))) {
            b(e, "CACHE_KEY_CARD", arrayList);
        }
        return arrayList;
    }

    private static void b(Context context, String str, List<HiHealthData> list) {
        jdr.d(SharedPreferenceManager.b(context, "day_sleep_summary_cache", str), fch.class.getClassLoader(), null, list);
    }
}
