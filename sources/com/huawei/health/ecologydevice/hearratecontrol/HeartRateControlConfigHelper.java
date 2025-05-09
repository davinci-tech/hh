package com.huawei.health.ecologydevice.hearratecontrol;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.ecologydevice.hearratecontrol.HeartRateControlConfigHelper;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ProgressListener;
import defpackage.czf;
import defpackage.dqh;
import defpackage.dqi;
import defpackage.dql;
import defpackage.drd;
import defpackage.dro;
import defpackage.dsw;
import defpackage.dsy;
import defpackage.koq;
import defpackage.lqi;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

/* loaded from: classes3.dex */
public class HeartRateControlConfigHelper {

    /* renamed from: a, reason: collision with root package name */
    private List<DownloadCallBack<dsw>> f2291a;
    private List<DownloadCallBack<dsy>> d;
    private Handler e;

    public interface DownloadCallBack<T> {
        void onDownloadFail(int i, String str);

        void onDownloadFinish(T t);
    }

    private HeartRateControlConfigHelper() {
        this.d = new Vector();
        this.f2291a = new Vector();
        this.e = new Handler(Looper.getMainLooper());
    }

    static class d {

        /* renamed from: a, reason: collision with root package name */
        private static final HeartRateControlConfigHelper f2293a = new HeartRateControlConfigHelper();
    }

    public void a(DownloadCallBack<dsw> downloadCallBack) {
        this.f2291a.add(downloadCallBack);
    }

    public void e(DownloadCallBack<dsy> downloadCallBack) {
        this.d.add(downloadCallBack);
    }

    public static HeartRateControlConfigHelper c() {
        return d.f2293a;
    }

    public void c(final int i) {
        LogUtil.a("HeartRateControlConfigHelper", "getHeartRateConfig");
        final String b = b(i);
        if (TextUtils.isEmpty("heart_rate_control_configuration") || TextUtils.isEmpty(b)) {
            LogUtil.h("HeartRateControlConfigHelper", "downloadFile, fileId or configId is empty");
            d(1, " fileId or configId is empty");
        } else {
            final dql a2 = a(i);
            final String str = "heart_rate_control_configuration";
            ThreadPoolManager.d().execute(new Runnable() { // from class: czh
                @Override // java.lang.Runnable
                public final void run() {
                    HeartRateControlConfigHelper.this.d(i, b, str, a2);
                }
            });
        }
    }

    public /* synthetic */ void d(int i, String str, String str2, dql dqlVar) {
        dsw b = czf.b(i, str, str2);
        dsy e = czf.e(i, str, str2);
        if (b(b, e, i)) {
            LogUtil.a("HeartRateControlConfigHelper", "enter downloadFile");
            Objects.requireNonNull(str2);
            Objects.requireNonNull(str);
            List<dqh> d2 = d(str2, dqlVar);
            if (koq.b(d2)) {
                LogUtil.h("HeartRateControlConfigHelper", "downloadFile, fileListSingles is null");
                e(b, e);
                return;
            }
            String c = c(d2, str2);
            LogUtil.a("HeartRateControlConfigHelper", "version = ", c);
            String b2 = b(d2, str2);
            if (TextUtils.isEmpty(c) || TextUtils.isEmpty(b2)) {
                LogUtil.h("HeartRateControlConfigHelper", "downloadFile, version or downloadUrl is null");
                e(b, e);
            } else if (b == null || b.getVersion() < nsn.h(c)) {
                c(i, str, str2, b2);
            } else {
                drd.a(System.currentTimeMillis(), h(i));
                e(b, e);
            }
        }
    }

    private dql a(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("deviceType", "heartRateControlMonitor");
        return new dql(b(i), hashMap);
    }

    private boolean b(dsw dswVar, dsy dsyVar, int i) {
        if (dswVar == null || dsyVar == null) {
            return true;
        }
        long b = drd.b(h(i));
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("HeartRateControlConfigHelper", "lastUpdateTime ", Long.valueOf(b), " currentTime ", Long.valueOf(currentTimeMillis));
        long j = currentTimeMillis - b;
        if (j >= 86400000 || j <= 0) {
            return true;
        }
        LogUtil.a("HeartRateControlConfigHelper", "downloadFiles, no need to update.");
        e(dswVar, dsyVar);
        return false;
    }

    private void e(final dsw dswVar, final dsy dsyVar) {
        this.e.post(new Runnable() { // from class: czi
            @Override // java.lang.Runnable
            public final void run() {
                HeartRateControlConfigHelper.this.d(dsyVar, dswVar);
            }
        });
    }

    public /* synthetic */ void d(dsy dsyVar, dsw dswVar) {
        Iterator<DownloadCallBack<dsy>> it = this.d.iterator();
        while (it.hasNext()) {
            it.next().onDownloadFinish(dsyVar);
            it.remove();
        }
        Iterator<DownloadCallBack<dsw>> it2 = this.f2291a.iterator();
        while (it2.hasNext()) {
            it2.next().onDownloadFinish(dswVar);
            it2.remove();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final int i, final String str) {
        this.e.post(new Runnable() { // from class: czg
            @Override // java.lang.Runnable
            public final void run() {
                HeartRateControlConfigHelper.this.b(i, str);
            }
        });
    }

    public /* synthetic */ void b(int i, String str) {
        Iterator<DownloadCallBack<dsy>> it = this.d.iterator();
        while (it.hasNext()) {
            it.next().onDownloadFail(i, str);
            it.remove();
        }
        Iterator<DownloadCallBack<dsw>> it2 = this.f2291a.iterator();
        while (it2.hasNext()) {
            it2.next().onDownloadFail(i, str);
            it2.remove();
        }
    }

    private static String c(List<dqh> list, String str) {
        LogUtil.a("HeartRateControlConfigHelper", "enter getVersion");
        for (dqh dqhVar : list) {
            if (str.equals(dqhVar.a())) {
                return dqhVar.c();
            }
        }
        return null;
    }

    private static String b(List<dqh> list, String str) {
        LogUtil.a("HeartRateControlConfigHelper", "enter getDownloadUrl");
        for (dqh dqhVar : list) {
            if (str.equals(dqhVar.a())) {
                return dqhVar.d();
            }
        }
        return null;
    }

    private static List<dqh> d(String str, dql dqlVar) {
        LogUtil.a("HeartRateControlConfigHelper", "enter getResponse");
        String b = b();
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("HeartRateControlConfigHelper", "getResponse getUrl is null");
            return null;
        }
        dqi dqiVar = (dqi) lqi.d().d(b, a(dqlVar.a() + str), HiJsonUtil.e(dqlVar), dqi.class);
        if (dqiVar == null) {
            LogUtil.h("HeartRateControlConfigHelper", "response is null");
            return null;
        }
        return dqiVar.e();
    }

    private static Map<String, String> a(String str) {
        HashMap hashMap = new HashMap(1);
        hashMap.put("x-version", CommonUtil.c(BaseApplication.getContext()));
        hashMap.put("Content-Type", "application/json; charset=utf-8");
        hashMap.put("traceId", str);
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (!TextUtils.isEmpty(accountInfo)) {
            hashMap.put("x-huid", accountInfo);
        }
        return hashMap;
    }

    private static String b() {
        LogUtil.a("HeartRateControlConfigHelper", "enter getUrl");
        String noCheckUrl = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl("domainHealthCloudCommon", GRSManager.a(BaseApplication.getContext()).getCommonCountryCode());
        if (TextUtils.isEmpty(noCheckUrl)) {
            LogUtil.b("HeartRateControlConfigHelper", "getUrl is empty or null.");
            return null;
        }
        return noCheckUrl + "/commonAbility/configCenter/getConfigInfo";
    }

    private void c(final int i, final String str, final String str2, String str3) {
        LogUtil.a("HeartRateControlConfigHelper", "downLoadZip");
        drd.a(str3, drd.d(str, str2, str3), new ProgressListener<File>() { // from class: com.huawei.health.ecologydevice.hearratecontrol.HeartRateControlConfigHelper.3
            @Override // com.huawei.networkclient.ProgressListener
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onFinish(File file) {
                LogUtil.a("HeartRateControlConfigHelper", "onFinish, data = ", file);
                HeartRateControlConfigHelper.this.a(i, str, str2, drd.d(str, str2, "zip"));
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onProgress(long j, long j2, boolean z) {
                LogUtil.c("HeartRateControlConfigHelper", "read = ", Long.valueOf(j), " total = ", Long.valueOf(j2), " isDone = ", Boolean.valueOf(z));
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onFail(Throwable th) {
                LogUtil.b("HeartRateControlConfigHelper", "downLoadZip on Fail: ", th.getMessage());
                HeartRateControlConfigHelper.this.b(drd.d(str, str2, "zip"));
                HeartRateControlConfigHelper.this.d(2, th.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str, String str2, String str3) {
        String str4 = drd.d(str, str2, (String) null) + File.separator;
        LogUtil.a("HeartRateControlConfigHelper", "filePostProcess");
        if (dro.e(str3, str4) != -1) {
            LogUtil.a("HeartRateControlConfigHelper", "filePostProcess unzipFile is suc");
            b(str3);
            dsw b = czf.b(i, str, str2);
            dsy e = czf.e(i, str, str2);
            if (b == null || e == null) {
                LogUtil.h("HeartRateControlConfigHelper", "rateControlInfo or speedControlConfigInfo is null");
                d(3, "rateControlInfo or speedControlConfigInfo is null");
                return;
            } else {
                drd.a(System.currentTimeMillis(), h(i));
                e(b, e);
                return;
            }
        }
        LogUtil.h("HeartRateControlConfigHelper", "filePostProcess unzipFile is fail");
        d(4, " unzipFile is fail");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(String str) {
        File file = new File(str);
        if (!file.exists() && !file.isDirectory()) {
            LogUtil.h("HeartRateControlConfigHelper", "this file is not exists");
            return false;
        }
        return e(file);
    }

    private boolean e(File file) {
        if (file == null || !file.exists()) {
            LogUtil.a("HeartRateControlConfigHelper", "deleteFiles, root == null or not exist");
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file2 : listFiles) {
                    e(file2);
                }
            }
        } else {
            LogUtil.h("HeartRateControlConfigHelper", "root not deleteFile or directory");
        }
        boolean delete = file.delete();
        LogUtil.a("HeartRateControlConfigHelper", "delete result= ", Boolean.valueOf(delete));
        return delete;
    }

    public String b(int i) {
        return i != 264 ? i != 265 ? "" : CommonUtil.ag(BaseApplication.getContext()) ? "com.huawei.health_ExerciseBikeTwo_deviceConfig" : "com.huawei.health_ExerciseBikeTwo_deviceConfigBeta" : CommonUtil.ag(BaseApplication.getContext()) ? "com.huawei.health_TreadmillFour_deviceConfig" : "com.huawei.health_TreadmillFour_deviceConfigBeta";
    }

    public String d(int i) {
        return i != 264 ? i != 265 ? "" : "INDOOR_BIKE_RESISTANCE_MAP_KEY" : "TREADMILL_SPEED_MAP_KEY";
    }

    public String e(int i) {
        return i != 264 ? i != 265 ? "" : "INDOOR_BIKE_COURSE_MAP_KEY" : "TREADMILL_COURSE_MAP_KEY";
    }

    private String h(int i) {
        return i != 264 ? i != 265 ? "" : "TREADMILL_CONFIG_RESOURCE" : "INDOOR_BIKE_CONFIG_RESOURCE";
    }
}
