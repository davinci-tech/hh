package defpackage;

import android.os.Looper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.dqk;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class hpp {
    public static void c() {
        ReleaseLogUtil.e("R_SportRecordImageDownloadUtil", "enter downloadSportRecordImage");
        ThreadPoolManager.d().execute(new Runnable() { // from class: hpm
            @Override // java.lang.Runnable
            public final void run() {
                hpp.a();
            }
        });
    }

    public static void d() {
        ReleaseLogUtil.e("R_SportRecordImageDownloadUtil", "enter downloadSportRecordImage");
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: hpl
                @Override // java.lang.Runnable
                public final void run() {
                    hpp.d();
                }
            });
        } else if (b(86400000L)) {
            a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a() {
        HashMap hashMap = new HashMap(10);
        hashMap.put("configType", "SportRecord");
        if (CommonUtil.bv()) {
            hashMap.put("configVersion", "Release_001");
        } else {
            hashMap.put("configVersion", "Beta_001");
        }
        dql dqlVar = new dql("com.huawei.health_Sport_Common", hashMap);
        drd.d(new dqk.e().e(dqlVar).d("sport_record_image").e(drd.d("com.huawei.health_Sport_Common", "sport_record_image", ".zip")).d(true).a(new DownloadCallback<dqi>() { // from class: hpp.2
            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onProgress(long j, long j2, boolean z, String str) {
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onFinish(dqi dqiVar) {
                ReleaseLogUtil.e("R_SportRecordImageDownloadUtil", "downloadSportRecordImage finish isFromCloud = ", Boolean.valueOf(dqiVar.c()));
                if (dqiVar.c()) {
                    hpp.c(drd.d("com.huawei.health_Sport_Common", "sport_record_image", ".zip"), drd.d("com.huawei.health_Sport_Common", "", (String) null));
                    drd.a(System.currentTimeMillis(), "com.huawei.health_Sport_Common_sport_record_image");
                }
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFail(int i, Throwable th) {
                ReleaseLogUtil.d("R_SportRecordImageDownloadUtil", "downloadSportRecordImage fail errCode = ", Integer.valueOf(i));
            }
        }).a());
    }

    private static boolean b(long j) {
        long currentTimeMillis = System.currentTimeMillis() - drd.b("com.huawei.health_Sport_Common_sport_record_image");
        if (currentTimeMillis >= j || currentTimeMillis <= 0) {
            return true;
        }
        ReleaseLogUtil.e("R_SportRecordImageDownloadUtil", "isLastUpdateExpire = false");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean c(String str, String str2) {
        if (dro.e(str, str2) != -1) {
            LogUtil.a("SportRecordImageDownloadUtil", "unzipFile success");
            b(str);
            return true;
        }
        LogUtil.h("SportRecordImageDownloadUtil", "unzipFile fail");
        return false;
    }

    private static boolean b(String str) {
        File file = new File(str);
        if (!file.exists()) {
            LogUtil.h("SportRecordImageDownloadUtil", "removeFile file is not exist");
            return false;
        }
        if (file.isDirectory()) {
            LogUtil.h("SportRecordImageDownloadUtil", "removeFile file is directory");
            return false;
        }
        return file.delete();
    }
}
