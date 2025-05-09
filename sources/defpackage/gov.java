package defpackage;

import android.os.Looper;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiSampleConfigKey;
import com.huawei.hihealth.HiSampleConfigProcessOption;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class gov {
    public static void i() {
        ReleaseLogUtil.b("SystemInfoToCloudUtil", "updateSystemInfoToCloud enter");
        ThreadPoolManager.d().execute(new Runnable() { // from class: goz
            @Override // java.lang.Runnable
            public final void run() {
                gov.f();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f() {
        cbb h = h();
        if (h == null) {
            h = new cbb();
        }
        LogUtil.c("SystemInfoToCloudUtil", "updateSystemInfoToCloud sampleConfig's language = ", h.e(), ", timeZone = ", h.d());
        String d = HiDateUtil.d((String) null);
        String e = LanguageUtil.e();
        LogUtil.c("SystemInfoToCloudUtil", "updateSystemInfoToCloud device's language = ", e, ", timeZone = ", d);
        if (e.equals(h.e()) && d.equals(h.d())) {
            ReleaseLogUtil.b("SystemInfoToCloudUtil", "updateSystemInfoToCloud sampleConfig's systemInfo is same as device");
            return;
        }
        h.d(e);
        h.e(d);
        a(h);
    }

    public static void d() {
        ReleaseLogUtil.b("SystemInfoToCloudUtil", "saveLanguageToCloud enter");
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ReleaseLogUtil.b("SystemInfoToCloudUtil", "saveLanguageToCloud switch thread");
            ThreadPoolManager.d().execute(new Runnable() { // from class: gpb
                @Override // java.lang.Runnable
                public final void run() {
                    gov.d();
                }
            });
            return;
        }
        cbb h = h();
        if (h == null) {
            h = new cbb();
        }
        LogUtil.c("SystemInfoToCloudUtil", "saveLanguageToCloud sampleConfig's language = ", h.e());
        String e = LanguageUtil.e();
        LogUtil.c("SystemInfoToCloudUtil", "saveLanguageToCloud device's language = ", e);
        if (e.equals(h.e())) {
            ReleaseLogUtil.b("SystemInfoToCloudUtil", "saveLocaleToCloud sampleConfig's language is same as devices");
        } else {
            h.d(e);
            a(h);
        }
    }

    public static void c() {
        ReleaseLogUtil.b("SystemInfoToCloudUtil", "saveTimeZoneToCloud enter");
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ReleaseLogUtil.b("SystemInfoToCloudUtil", "saveTimeZoneToCloud switch thread");
            ThreadPoolManager.d().execute(new Runnable() { // from class: gos
                @Override // java.lang.Runnable
                public final void run() {
                    gov.c();
                }
            });
            return;
        }
        cbb h = h();
        if (h == null) {
            h = new cbb();
        }
        LogUtil.c("SystemInfoToCloudUtil", "saveTimeZoneToCloud sampleConfig's timeZone = ", h.d());
        String d = HiDateUtil.d((String) null);
        LogUtil.c("SystemInfoToCloudUtil", "saveTimeZoneToCloud device's timeZone = ", d);
        if (d.equals(h)) {
            ReleaseLogUtil.b("SystemInfoToCloudUtil", "saveLocaleToCloud sampleConfig's timeZone is same as devices");
        } else {
            h.e(d);
            a(h);
        }
    }

    private static cbb h() {
        ReleaseLogUtil.b("SystemInfoToCloudUtil", "getSystemInfoFromSampleConfig enter");
        ArrayList arrayList = new ArrayList();
        HiSampleConfigKey.Builder builder = new HiSampleConfigKey.Builder();
        builder.a(String.valueOf(0));
        builder.e(String.valueOf(0));
        builder.b("9001");
        builder.d("900100017");
        arrayList.add(builder.d());
        HiSampleConfigProcessOption hiSampleConfigProcessOption = new HiSampleConfigProcessOption();
        hiSampleConfigProcessOption.setProcessType(2);
        hiSampleConfigProcessOption.setDeviceUniqueId(FoundationCommonUtil.getAndroidId(BaseApplication.e()));
        hiSampleConfigProcessOption.setSampleConfigKeyList(arrayList);
        final cbb[] cbbVarArr = new cbb[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        HiHealthManager.d(BaseApplication.e()).getSampleConfig(hiSampleConfigProcessOption, new HiDataReadResultListener() { // from class: gov.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                ReleaseLogUtil.b("SystemInfoToCloudUtil", "getSystemInfoFromSampleConfig end errorCode = ", Integer.valueOf(i), ", data = ", obj);
                if (koq.e(obj, HiSampleConfig.class)) {
                    List list = (List) obj;
                    cbbVarArr[0] = koq.b(list) ? null : (cbb) HiJsonUtil.e(((HiSampleConfig) list.get(0)).getConfigData(), cbb.class);
                }
                countDownLatch.countDown();
            }
        });
        try {
            ReleaseLogUtil.b("SystemInfoToCloudUtil", "getSystemInfoFromSampleConfig isCountDownZero = ", Boolean.valueOf(countDownLatch.await(5000L, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException unused) {
            ReleaseLogUtil.a("SystemInfoToCloudUtil", "getSystemInfoFromSampleConfig exception");
        }
        return cbbVarArr[0];
    }

    private static void a(cbb cbbVar) {
        c(HiJsonUtil.e(cbbVar), new HiDataOperateListener() { // from class: gox
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i, Object obj) {
                gov.a(i, obj);
            }
        });
    }

    static /* synthetic */ void a(int i, Object obj) {
        ReleaseLogUtil.b("SystemInfoToCloudUtil", "saveSystemInfoToSampleConfig end errorCode = ", Integer.valueOf(i));
        if (i == 0) {
            j();
        }
    }

    private static void c(String str, HiDataOperateListener hiDataOperateListener) {
        ReleaseLogUtil.b("SystemInfoToCloudUtil", "saveSystemInfoToSampleConfig enter");
        HiSampleConfig.Builder builder = new HiSampleConfig.Builder();
        builder.c(System.currentTimeMillis());
        builder.g(String.valueOf(0));
        builder.c(FoundationCommonUtil.getAndroidId(BaseApplication.e()));
        builder.h(String.valueOf(0));
        builder.j("9001");
        builder.d("900100017");
        builder.b(str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new HiSampleConfig(builder));
        HiHealthManager.d(BaseApplication.e()).setSampleConfig(arrayList, hiDataOperateListener);
    }

    private static void j() {
        ReleaseLogUtil.b("SystemInfoToCloudUtil", "syncSampleConfig enter");
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(900000000);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(BaseApplication.e()).synCloud(hiSyncOption, null);
    }
}
