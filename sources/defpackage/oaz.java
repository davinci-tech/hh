package defpackage;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class oaz {
    public static void b(final String str) {
        LogUtil.a("HealthCloudWearDeviceUtils", "checkUpdate ruleValue: ", str);
        ThreadPoolManager.d().execute(new Runnable() { // from class: oaz.4
            @Override // java.lang.Runnable
            public void run() {
                dqi d = drd.d("f69b5b90-83db-4cd1-a5c8-19708adaf3f3_new_v1.1", oaz.i(str));
                if (d == null) {
                    LogUtil.h("HealthCloudWearDeviceUtils", "checkUpdate response is null.");
                    return;
                }
                List<dqh> e = d.e();
                if (e != null && e.size() != 0) {
                    int g = oaz.g(str);
                    for (dqh dqhVar : e) {
                        String a2 = dqhVar.a();
                        int parseInt = Integer.parseInt(dqhVar.c());
                        if ("f69b5b90-83db-4cd1-a5c8-19708adaf3f3_new_v1.1".equalsIgnoreCase(a2) && parseInt > g) {
                            oaz.a(str, parseInt);
                        }
                    }
                    return;
                }
                LogUtil.h("HealthCloudWearDeviceUtils", "checkUpdate fileList is empty.");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str, int i) {
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "com.huawei.health_wearDevice_config" + str, String.valueOf(i), (StorageParams) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int g(String str) {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "com.huawei.health_wearDevice_config" + str);
        int parseInt = !TextUtils.isEmpty(b) ? Integer.parseInt(b) : 0;
        LogUtil.a("HealthCloudWearDeviceUtils", "getResourceVersion fileVersion: ", Integer.valueOf(parseInt));
        return parseInt;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(final String str, final int i) {
        LogUtil.a("HealthCloudWearDeviceUtils", "Enter downloadResource.");
        drd.a(i(str), "f69b5b90-83db-4cd1-a5c8-19708adaf3f3_new_v1.1", new DownloadCallback<File>() { // from class: oaz.2
            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onProgress(long j, long j2, boolean z, String str2) {
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onFinish(File file) {
                LogUtil.a("HealthCloudWearDeviceUtils", "downloadResource onFinish.");
                if ("autoSwitch".equalsIgnoreCase(str)) {
                    oaz.e();
                }
                oaz.b(str, i);
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFail(int i2, Throwable th) {
                LogUtil.a("HealthCloudWearDeviceUtils", "downloadResource onFail errCode: ", Integer.valueOf(i2));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e() {
        String d = drd.d("com.huawei.health_wearDevice_config", "f69b5b90-83db-4cd1-a5c8-19708adaf3f3_new_v1.1", "zip");
        String str = drd.d("com.huawei.health_wearDevice_config", "f69b5b90-83db-4cd1-a5c8-19708adaf3f3_new_v1.1", (String) null) + File.separator;
        dro.e(d, str);
        if (dro.e(d, str) != -1) {
            LogUtil.a("HealthCloudWearDeviceUtils", "unzipFile success.");
            FileUtils.d(new File(d));
        } else {
            LogUtil.a("HealthCloudWearDeviceUtils", "unzip failure.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static dql i(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("configType", str);
        return new dql("com.huawei.health_wearDevice_config", hashMap);
    }

    public static boolean d(String str) {
        String str2;
        LogUtil.a("HealthCloudWearDeviceUtils", "isExistResource ruleValue: ", str);
        if ("autoSwitch".equalsIgnoreCase(str)) {
            str2 = drd.d("com.huawei.health_wearDevice_config", "f69b5b90-83db-4cd1-a5c8-19708adaf3f3_new_v1.1", (String) null) + File.separator + "f69b5b90-83db-4cd1-a5c8-19708adaf3f3_new_v1.1" + File.separator + "f69b5b90-83db-4cd1-a5c8-19708adaf3f3" + File.separator + "img";
        } else {
            str2 = "";
        }
        LogUtil.a("HealthCloudWearDeviceUtils", "isExistResource filePath: ", str2);
        return new File(str2).exists();
    }

    public static ArrayList<Bitmap> e(String str) {
        LogUtil.a("HealthCloudWearDeviceUtils", "getBitmapList ruleValue: ", str);
        ArrayList<Bitmap> arrayList = new ArrayList<>();
        File[] listFiles = new File("autoSwitch".equalsIgnoreCase(str) ? drd.d("com.huawei.health_wearDevice_config", "f69b5b90-83db-4cd1-a5c8-19708adaf3f3_new_v1.1", (String) null) + File.separator + "f69b5b90-83db-4cd1-a5c8-19708adaf3f3_new_v1.1" + File.separator + "f69b5b90-83db-4cd1-a5c8-19708adaf3f3" + File.separator + "img" + File.separator : "").listFiles();
        if (listFiles == null || listFiles.length == 0) {
            LogUtil.h("HealthCloudWearDeviceUtils", "getBitmapList imageFiles is empty.");
            return arrayList;
        }
        Arrays.sort(listFiles, new Comparator<File>() { // from class: oaz.1
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(File file, File file2) {
                return file.getName().compareTo(file2.getName());
            }
        });
        LogUtil.a("HealthCloudWearDeviceUtils", "getBitmapList imageFiles size: ", Integer.valueOf(listFiles.length));
        for (File file : listFiles) {
            try {
                String canonicalPath = file.getCanonicalPath();
                LogUtil.c("HealthCloudWearDeviceUtils", " filename: ", file.getName());
                arrayList.add(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImagePath(canonicalPath));
            } catch (IOException unused) {
                LogUtil.h("HealthCloudWearDeviceUtils", "getBitmapList occur IOException.");
            }
        }
        return arrayList;
    }
}
