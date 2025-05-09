package defpackage;

import android.text.TextUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ProgressListener;
import com.huawei.operation.ble.BleConstants;
import defpackage.drf;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes.dex */
public class drd {
    private static String e() {
        LogUtil.a("HealthCloudConfigUtils", "enter getUrl");
        String noCheckUrl = GRSManager.a(BaseApplication.getContext()).getNoCheckUrl("domainHealthCloudCommon", GRSManager.a(BaseApplication.getContext()).getCommonCountryCode());
        if (TextUtils.isEmpty(noCheckUrl) || noCheckUrl == null) {
            LogUtil.b("HealthCloudConfigUtils", "getUrl is empty or null.");
            return "";
        }
        return noCheckUrl + "/commonAbility/configCenter/getConfigInfo";
    }

    private static Map<String, String> a(String str) {
        LogUtil.a("HealthCloudConfigUtils", "enter getHeader");
        HashMap hashMap = new HashMap();
        hashMap.put("x-version", CommonUtil.c(BaseApplication.getContext()));
        hashMap.put("Content-Type", "application/json; charset=utf-8");
        hashMap.put("traceId", str);
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (!TextUtils.isEmpty(accountInfo)) {
            hashMap.put("x-huid", accountInfo);
        }
        return hashMap;
    }

    private static String d(String str) {
        if (str == null) {
            return "";
        }
        LogUtil.a("HealthCloudConfigUtils", "enter getFileSuffix");
        return "." + str.split("\\.")[r2.length - 1].split("\"")[0];
    }

    public static void a(long j, String str) {
        LogUtil.a("HealthCloudConfigUtils", "enter setLatestDownloadTime");
        SharedPreferenceManager.e(String.valueOf(20006), str + "_latestDownloadTime", j);
    }

    public static long b(String str) {
        LogUtil.a("HealthCloudConfigUtils", "enter getLatestDownloadTime.");
        long b = SharedPreferenceManager.b(String.valueOf(20006), str + "_latestDownloadTime", 0L);
        if (b != 0) {
            return b;
        }
        LogUtil.h("HealthCloudConfigUtils", "latestDownloadTime is null or 0.");
        return 0L;
    }

    private static dqi e(String str, dql dqlVar, DownloadCallback downloadCallback) {
        LogUtil.a("HealthCloudConfigUtils", "enter getResponse");
        String e = e();
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("HealthCloudConfigUtils", "getResponse getUrl is null");
            downloadCallback.onFail(1003, new UnknownHostException("HealthCloudConfigUtils getResponse, getUrl is null"));
            return null;
        }
        Map<String, String> a2 = a(dqlVar.a() + str);
        LogUtil.a("HealthCloudConfigUtils", "Request Information, fileId is: ", str, ", url is: ", e, ", header is: ", a2, ", body is: ", HiJsonUtil.e(dqlVar));
        dqi dqiVar = (dqi) lqi.d().d(e, a2, HiJsonUtil.e(dqlVar), dqi.class);
        if (dqiVar == null) {
            LogUtil.h("HealthCloudConfigUtils", "failed to get response");
            downloadCallback.onFail(1004, new IOException("HealthCloudConfigUtils getResponse, response is null"));
            return null;
        }
        if (dqiVar.d() == 0) {
            return dqiVar;
        }
        LogUtil.b("HealthCloudConfigUtils", "response not success, code is: ", Integer.valueOf(dqiVar.d()));
        downloadCallback.onFail(1005, new IOException("response not success, code is: " + dqiVar.d()));
        return null;
    }

    public static dqi d(String str, dql dqlVar) {
        LogUtil.a("HealthCloudConfigUtils", "enter public getResponse.");
        if (TextUtils.isEmpty(str) || dqlVar == null) {
            LogUtil.h("HealthCloudConfigUtils", "public getResponse, fileId or body is null or empty");
            return null;
        }
        String e = e();
        Map<String, String> a2 = a(dqlVar.a() + str);
        LogUtil.a("HealthCloudConfigUtils", "Request Information, fileId is: ", str, ", url is: ", e, ", header is: ", a2, ", body is: ", HiJsonUtil.e(dqlVar));
        dqi dqiVar = (dqi) lqi.d().d(e, a2, HiJsonUtil.e(dqlVar), dqi.class);
        if (dqiVar == null) {
            LogUtil.h("HealthCloudConfigUtils", "failed to get response");
            return null;
        }
        if (dqiVar.d() == 0) {
            return dqiVar;
        }
        LogUtil.b("HealthCloudConfigUtils", "response not success, resultCode is: ", Integer.valueOf(dqiVar.d()));
        return null;
    }

    public static dqi c(String str, dql dqlVar, int i, String str2) {
        LogUtil.a("HealthCloudConfigUtils", "enter public getResponse by updateDays: ", Integer.valueOf(i));
        long e = e(str2);
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("HealthCloudConfigUtils", "get from sp: lastCheckVersionTime = ", Long.valueOf(e), ", currentCheckVersionTime = ", Long.valueOf(currentTimeMillis));
        long j = currentTimeMillis - e;
        if (j < i * 86400000 && j > 0) {
            LogUtil.a("HealthCloudConfigUtils", "no need to check version");
            return null;
        }
        if (TextUtils.isEmpty(str) || dqlVar == null) {
            LogUtil.h("HealthCloudConfigUtils", "public getResponse, fileId or body is null or empty");
            return null;
        }
        String e2 = e();
        Map<String, String> a2 = a(dqlVar.a() + str);
        LogUtil.a("HealthCloudConfigUtils", "Request Information, fileId is: ", str, ", url is: ", e2, ", header is: ", a2, ", body is: ", HiJsonUtil.e(dqlVar));
        dqi dqiVar = (dqi) lqi.d().d(e2, a2, HiJsonUtil.e(dqlVar), dqi.class);
        if (dqiVar == null) {
            LogUtil.h("HealthCloudConfigUtils", "failed to get response");
            return null;
        }
        if (dqiVar.d() != 0) {
            LogUtil.b("HealthCloudConfigUtils", "response not success, resultCode is: ", Integer.valueOf(dqiVar.d()));
            return null;
        }
        a(str2, System.currentTimeMillis());
        return dqiVar;
    }

    private static String d(dqi dqiVar, String str) {
        String str2;
        LogUtil.a("HealthCloudConfigUtils", "enter getDownloadUrl");
        List<dqh> e = dqiVar.e();
        int i = 0;
        while (true) {
            if (i >= e.size()) {
                str2 = null;
                break;
            }
            if (str.equals(e.get(i).a())) {
                str2 = e.get(i).d();
                break;
            }
            i++;
        }
        if (str2 != null && !TextUtils.isEmpty(str2)) {
            return str2;
        }
        LogUtil.h("HealthCloudConfigUtils", "downloadUrl is null or empty.");
        return null;
    }

    private static void c(File file) {
        if (file.getParentFile() == null || file.getParentFile().exists()) {
            return;
        }
        LogUtil.a("HealthCloudConfigUtils", "initFolder isMkdirFile = ", Boolean.valueOf(file.getParentFile().mkdirs()));
    }

    private static void a(File file) {
        if (file == null) {
            LogUtil.h("HealthCloudConfigUtils", "initTempFile, tempFile is null");
        } else {
            if (file.getParentFile() == null || file.getParentFile().exists()) {
                return;
            }
            LogUtil.a("HealthCloudConfigUtils", "initTempFile isMkdirFile = ", Boolean.valueOf(file.getParentFile().mkdirs()));
        }
    }

    private static void c(String str, Map<String, String> map, DownloadCallback downloadCallback) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();
            if (TextUtils.isEmpty(value)) {
                LogUtil.h("HealthCloudConfigUtils", "downloadBatch, downloadUrl is null or empty.");
                downloadCallback.onFail(1007, new NullPointerException("HealthCloudConfigUtils downloadBatch, downloadUrl is null"));
                return;
            }
            String key = entry.getKey();
            String d = d(str, key, value);
            if (d == null) {
                LogUtil.h("HealthCloudConfigUtils", "downloadBatch, filePath is null");
                downloadCallback.onFail(1006, new IOException("HealthCloudConfigUtils downloadBatch, filePath is null"));
                return;
            }
            File file = new File(d);
            File e = e(key, value);
            dqy dqyVar = new dqy(str, key, value, file, downloadCallback);
            c(file);
            a(e);
            lqi.d().d(new lqg(value, null, file, e, dqyVar));
        }
    }

    private static String b(String str, String str2) {
        File[] listFiles;
        try {
            String str3 = BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + "healthcloud" + File.separator + str;
            if (TextUtils.isEmpty(str3)) {
                LogUtil.h("HealthCloudConfigUtils", "getLocalFile filePath is empty.");
                return null;
            }
            File file = new File(str3);
            if ((!file.exists() && !file.isDirectory()) || (listFiles = file.listFiles()) == null) {
                return null;
            }
            for (int i = 0; i < listFiles.length && listFiles[i].isFile(); i++) {
                String name = listFiles[i].getName();
                if (TextUtils.isEmpty(name)) {
                    LogUtil.h("HealthCloudConfigUtils", "checkLocalFile, fileName is empty");
                    return null;
                }
                int lastIndexOf = name.lastIndexOf(46);
                if (lastIndexOf == -1) {
                    LogUtil.h("HealthCloudConfigUtils", "checkLocalFile, index is -1.");
                    return null;
                }
                if (name.substring(0, lastIndexOf).equals(str2)) {
                    try {
                        return listFiles[i].getCanonicalPath();
                    } catch (IOException e) {
                        LogUtil.h("HealthCloudConfigUtils", "getLocalFile, exception is: ", e.getMessage());
                        return null;
                    }
                }
            }
            return null;
        } catch (IOException e2) {
            LogUtil.b("HealthCloudConfigUtils", "IOException e", e2.getMessage());
            return null;
        }
    }

    private static List<File> c(String str) {
        try {
            String str2 = BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + "healthcloud" + File.separator + str;
            if (TextUtils.isEmpty(str2)) {
                LogUtil.h("HealthCloudConfigUtils", "getLocalFile filePath is empty.");
                return null;
            }
            File file = new File(str2);
            if (!file.exists() && !file.isDirectory()) {
                return null;
            }
            if (!sqd.b(file)) {
                LogUtil.h("HealthCloudConfigUtils", "path_crossing checkLocalFile file is invalid,filePath=", str2);
                return null;
            }
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList(10);
            for (File file2 : listFiles) {
                arrayList.add(file2);
            }
            return arrayList;
        } catch (IOException e) {
            LogUtil.b("HealthCloudConfigUtils", "IOException e", e.getMessage());
            return null;
        }
    }

    private static File e(String str, String str2) {
        try {
            String str3 = BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + "healthcloud" + File.separator + BleConstants.TEMPERATURE + File.separator + str + d(str2);
            if (TextUtils.isEmpty(str3)) {
                LogUtil.h("HealthCloudConfigUtils", "getTempFile filePath is empty.");
                return null;
            }
            File file = new File(str3);
            if (sqd.b(file)) {
                return file;
            }
            LogUtil.h("HealthCloudConfigUtils", "path_crossing getTempFile file is invalid,filePath=", str3);
            return null;
        } catch (IOException e) {
            LogUtil.b("HealthCloudConfigUtils", "IOException e", e.getMessage());
            return null;
        }
    }

    public static String d(String str, String str2, String str3) {
        LogUtil.a("HealthCloudConfigUtils", "enter getFilePath");
        Objects.requireNonNull(str);
        if (str2 == null) {
            str2 = "";
        }
        try {
            String str4 = BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + "healthcloud" + File.separator + str + File.separator + str2 + d(str3);
            if (!TextUtils.isEmpty(str4)) {
                return str4;
            }
            LogUtil.h("HealthCloudConfigUtils", "getFilePath filePath is empty.");
            return null;
        } catch (IOException e) {
            LogUtil.b("HealthCloudConfigUtils", "IOException e", e.getMessage());
            return null;
        }
    }

    public static void a(final dql dqlVar, final String str, final DownloadCallback<File> downloadCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: dre
            @Override // java.lang.Runnable
            public final void run() {
                drd.b(str, dqlVar, downloadCallback);
            }
        });
    }

    static /* synthetic */ void b(String str, dql dqlVar, DownloadCallback downloadCallback) {
        LogUtil.a("HealthCloudConfigUtils", "enter downloadFile");
        Objects.requireNonNull(str);
        Objects.requireNonNull(dqlVar);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(dqlVar.a())) {
            LogUtil.h("HealthCloudConfigUtils", "downloadFile, fileId or configId is empty");
            downloadCallback.onFail(1002, new IllegalArgumentException("fileId or configId is null or empty"));
            return;
        }
        dqi e = e(str, dqlVar, downloadCallback);
        if (e == null) {
            LogUtil.h("HealthCloudConfigUtils", "downloadFile, response is null");
            return;
        }
        String a2 = dqlVar.a();
        String d = d(a2, str, d(e, str));
        if (d == null) {
            LogUtil.h("HealthCloudConfigUtils", "downloadFile, filePath is null");
            downloadCallback.onFail(1006, new IOException("HealthCloudConfigUtils downloadFile, filePath is null"));
            return;
        }
        String d2 = d(e, str);
        if (d2 == null) {
            LogUtil.h("HealthCloudConfigUtils", "downloadFile, downloadUrl is null");
            downloadCallback.onFail(1007, new NullPointerException("HealthCloudConfigUtils downloadFile, downloadUrl is null"));
        } else {
            d(d, d2, str, new dqy(a2, str, d2, new File(d), downloadCallback), downloadCallback);
        }
    }

    public static void e(final dql dqlVar, final String str, final int i, final DownloadCallback<File> downloadCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: drg
            @Override // java.lang.Runnable
            public final void run() {
                drd.d(str, dqlVar, downloadCallback, i);
            }
        });
    }

    static /* synthetic */ void d(String str, dql dqlVar, DownloadCallback downloadCallback, int i) {
        LogUtil.a("HealthCloudConfigUtils", "enter downloadFiles");
        Objects.requireNonNull(str);
        Objects.requireNonNull(dqlVar);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(dqlVar.a())) {
            LogUtil.h("HealthCloudConfigUtils", "getResponse, fileId or configId is empty");
            downloadCallback.onFail(1002, new IllegalArgumentException("fileId or configId is null or empty"));
            return;
        }
        String a2 = dqlVar.a();
        long b = b(a2 + "_" + str);
        long currentTimeMillis = System.currentTimeMillis();
        String b2 = b(a2, str);
        long j = currentTimeMillis - b;
        if (j < i * 86400000 && j > 0 && b2 != null) {
            LogUtil.a("HealthCloudConfigUtils", "downloadFiles, no need to update.");
            downloadCallback.onFinish(new File(b2));
            return;
        }
        LogUtil.a("HealthCloudConfigUtils", "downloadFiles, needs to update.");
        dqi e = e(str, dqlVar, downloadCallback);
        if (e == null) {
            LogUtil.h("HealthCloudConfigUtils", "downloadFiles, response is null");
            return;
        }
        String d = d(a2, str, d(e, str));
        if (d == null) {
            LogUtil.h("HealthCloudConfigUtils", "downloadFiles, filePath is null");
            downloadCallback.onFail(1006, new IOException("HealthCloudConfigUtils downloadFiles, filePath is null"));
            return;
        }
        String d2 = d(e, str);
        if (d2 == null) {
            LogUtil.h("HealthCloudConfigUtils", "downloadFiles, downloadUrl is null");
            downloadCallback.onFail(1007, new NullPointerException("HealthCloudConfigUtils downloadFiles, downloadUrl is null"));
        } else {
            d(d, d2, str, new drc(a2, str, d2, new File(d), downloadCallback), downloadCallback);
        }
    }

    private static void d(String str, String str2, String str3, ProgressListener progressListener, DownloadCallback downloadCallback) {
        File file = new File(str);
        File e = e(str3, str2);
        c(file);
        a(e);
        lqi.d().d(new lqg(str2, null, file, e, progressListener));
    }

    public static void d(final dql dqlVar, final String str, final List<String> list, final DownloadCallback<List<File>> downloadCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: drp
            @Override // java.lang.Runnable
            public final void run() {
                drd.e(dql.this, str, downloadCallback, list);
            }
        });
    }

    static /* synthetic */ void e(dql dqlVar, String str, DownloadCallback downloadCallback, List list) {
        LogUtil.a("HealthCloudConfigUtils", "enter downloadBatchList");
        Objects.requireNonNull(dqlVar);
        Objects.requireNonNull(str);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(dqlVar.a())) {
            LogUtil.h("HealthCloudConfigUtils", "getResponse, tag or configId is empty");
            downloadCallback.onFail(1002, new IllegalArgumentException("tag or configId is null or empty"));
            return;
        }
        if (list == null || list.isEmpty()) {
            LogUtil.h("HealthCloudConfigUtils", "downloadBatchList, list is null or empty.");
            downloadCallback.onFail(1009, new NullPointerException("HealthCloudConfigUtils downloadBatchList, list is null or empty"));
            return;
        }
        dqi e = e(str, dqlVar, downloadCallback);
        if (e == null) {
            LogUtil.h("HealthCloudConfigUtils", "downloadBatchList, response is null");
            return;
        }
        String a2 = dqlVar.a();
        Map<String, String> b = b(e, (List<String>) list);
        if (b.isEmpty()) {
            LogUtil.h("HealthCloudConfigUtils", "downloadBatchList, map is null or empty.");
            downloadCallback.onFail(1008, new NullPointerException("downloadBatchList, map is null or empty."));
        } else {
            c(a2, b, new dqz(a2, str, b.size(), downloadCallback));
        }
    }

    public static void c(final dql dqlVar, final String str, final List<String> list, final DownloadCallback<dqi> downloadCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: drk
            @Override // java.lang.Runnable
            public final void run() {
                drd.c(dql.this, str, downloadCallback, list);
            }
        });
    }

    static /* synthetic */ void c(dql dqlVar, String str, DownloadCallback downloadCallback, List list) {
        LogUtil.a("HealthCloudConfigUtils", "enter downloadBatchListResponse");
        Objects.requireNonNull(dqlVar);
        Objects.requireNonNull(str);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(dqlVar.a())) {
            LogUtil.h("HealthCloudConfigUtils", "getResponse, tag or configId is empty");
            downloadCallback.onFail(1002, new IllegalArgumentException("tag or configId is null or empty"));
            return;
        }
        if (list == null || list.isEmpty()) {
            LogUtil.h("HealthCloudConfigUtils", "downloadBatchList, list is null or empty.");
            downloadCallback.onFail(1009, new NullPointerException("HealthCloudConfigUtils downloadBatchList, list is null or empty"));
            return;
        }
        dqi e = e(str, dqlVar, downloadCallback);
        if (e == null) {
            LogUtil.h("HealthCloudConfigUtils", "downloadBatchList, response is null");
            return;
        }
        String a2 = dqlVar.a();
        Map<String, String> b = b(e, (List<String>) list);
        if (b.isEmpty()) {
            LogUtil.h("HealthCloudConfigUtils", "downloadBatchList, map is null or empty.");
            downloadCallback.onFail(1008, new NullPointerException("downloadBatchList, map is null or empty."));
        } else {
            c(a2, b, new dqx(a2, str, b.size(), e, downloadCallback));
        }
    }

    public static void a(final dql dqlVar, final String str, final List<String> list, final int i, final DownloadCallback<dqi> downloadCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: drj
            @Override // java.lang.Runnable
            public final void run() {
                drd.d(dql.this, str, downloadCallback, list, i);
            }
        });
    }

    static /* synthetic */ void d(dql dqlVar, String str, DownloadCallback downloadCallback, List list, int i) {
        LogUtil.a("HealthCloudConfigUtils", "enter downloadBatchListResponse with updateDays");
        Objects.requireNonNull(dqlVar);
        Objects.requireNonNull(str);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(dqlVar.a())) {
            LogUtil.h("HealthCloudConfigUtils", "getResponse, tag or configId is empty");
            downloadCallback.onFail(1002, new IllegalArgumentException("tag or configId is null or empty"));
            return;
        }
        if (list == null || list.isEmpty()) {
            LogUtil.h("HealthCloudConfigUtils", "downloadBatchList, list is null or empty.");
            downloadCallback.onFail(1009, new NullPointerException("HealthCloudConfigUtils downloadBatchList, list is null or empty"));
            return;
        }
        String a2 = dqlVar.a();
        long b = b(d(a2, str, true));
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.a("HealthCloudConfigUtils", "get from sp: lastUpdateTime = ", Long.valueOf(b), ", currentTime = ", Long.valueOf(currentTimeMillis));
        List<File> c = c(a2);
        long j = currentTimeMillis - b;
        if (j < i * 86400000 && j > 0 && c != null) {
            LogUtil.a("HealthCloudConfigUtils", "downloadFiles, no need to update.");
            dqi dqiVar = new dqi();
            dqiVar.b(false);
            downloadCallback.onFinish(dqiVar);
            return;
        }
        LogUtil.a("HealthCloudConfigUtils", "downloadBatchLists, needs to update.");
        dqi e = e(str, dqlVar, downloadCallback);
        if (e == null) {
            LogUtil.h("HealthCloudConfigUtils", "downloadBatchList, response is null");
            return;
        }
        Map<String, String> b2 = b(e, (List<String>) list);
        if (b2.isEmpty()) {
            LogUtil.h("HealthCloudConfigUtils", "downloadBatchList, map is null or empty.");
            downloadCallback.onFail(1008, new NullPointerException("downloadBatchList, map is null or empty."));
        } else {
            c(a2, b2, new dqx(a2, str, b2.size(), e, str, downloadCallback));
        }
    }

    public static String d(String str, String str2, boolean z) {
        StringBuilder sb;
        if (z) {
            sb = new StringBuilder();
            sb.append(str);
            sb.append("_");
            sb.append(str2);
            str2 = "_batch";
        } else {
            sb = new StringBuilder();
            sb.append(str);
            sb.append("_");
        }
        sb.append(str2);
        return sb.toString();
    }

    private static Map<String, String> b(dqi dqiVar, List<String> list) {
        int size = dqiVar.e().size();
        List<dqh> e = dqiVar.e();
        HashMap hashMap = new HashMap();
        for (int i = 0; i < size; i++) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (e.get(i).a().equals(list.get(i2))) {
                    hashMap.put(list.get(i2), e.get(i).d());
                }
            }
        }
        return hashMap;
    }

    @Deprecated
    public static void e(final dql dqlVar, final String str, final int i, final List<String> list, final DownloadCallback<List<File>> downloadCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: drh
            @Override // java.lang.Runnable
            public final void run() {
                drd.c(dql.this, str, downloadCallback, list, i);
            }
        });
    }

    static /* synthetic */ void c(dql dqlVar, String str, DownloadCallback downloadCallback, List list, int i) {
        LogUtil.a("HealthCloudConfigUtils", "enter downloadBatchLists");
        Objects.requireNonNull(dqlVar);
        Objects.requireNonNull(str);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(dqlVar.a())) {
            LogUtil.h("HealthCloudConfigUtils", "getResponse, tag or configId is empty");
            downloadCallback.onFail(1002, new IllegalArgumentException("tag or configId is null or empty"));
            return;
        }
        if (list == null || list.isEmpty()) {
            LogUtil.h("HealthCloudConfigUtils", "downloadBatchLists，list is null or empty.");
            downloadCallback.onFail(1009, new NullPointerException("HealthCloudConfigUtils downloadBatchLists, list is null"));
            return;
        }
        String a2 = dqlVar.a();
        long b = b(d(a2, str, false));
        long currentTimeMillis = System.currentTimeMillis();
        List<File> c = c(a2);
        long j = currentTimeMillis - b;
        if (j < i * 86400000 && j > 0 && c != null) {
            LogUtil.a("HealthCloudConfigUtils", "downloadFiles, no need to update.");
            downloadCallback.onFinish(c);
            return;
        }
        LogUtil.a("HealthCloudConfigUtils", "downloadBatchLists, needs to update.");
        dqi e = e(str, dqlVar, downloadCallback);
        if (e == null) {
            LogUtil.h("HealthCloudConfigUtils", "downloadBatchLists, response is null");
            return;
        }
        Map<String, String> b2 = b(e, (List<String>) list);
        if (b2.isEmpty()) {
            LogUtil.h("HealthCloudConfigUtils", "map is null or empty.");
            downloadCallback.onFail(1008, new NullPointerException("map is null or empty."));
        } else {
            c(a2, b2, new dra(a2, str, b2.size(), downloadCallback));
        }
    }

    public static void d(final dqk dqkVar) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: dri
            @Override // java.lang.Runnable
            public final void run() {
                drd.b(dqk.this);
            }
        });
    }

    static /* synthetic */ void b(dqk dqkVar) {
        LogUtil.a("HealthCloudConfigUtils", "enter downloadFileResponse");
        if (dqkVar.a() == null) {
            LogUtil.h("HealthCloudConfigUtils", "listener is null");
            return;
        }
        DownloadCallback a2 = dqkVar.a();
        if (dqkVar.c() == null || TextUtils.isEmpty(dqkVar.c().a()) || TextUtils.isEmpty(dqkVar.e()) || TextUtils.isEmpty(dqkVar.b())) {
            LogUtil.h("HealthCloudConfigUtils", "fileId or configId or body is empty");
            a2.onFail(1002, new IllegalArgumentException("fileId or configId is null or empty"));
            return;
        }
        String e = dqkVar.e();
        dql c = dqkVar.c();
        boolean d = dqkVar.d();
        String b = dqkVar.b();
        dqi e2 = e(e, c, a2);
        if (e2 == null) {
            LogUtil.h("HealthCloudConfigUtils", "downloadFile, response is null");
            return;
        }
        String a3 = c.a();
        String e3 = e(e2, e);
        if (TextUtils.isEmpty(e3)) {
            LogUtil.h("HealthCloudConfigUtils", "cloudVer is null or empty, no download");
            return;
        }
        if (d) {
            if (e3.compareTo(h(a3 + e)) <= 0 && FileUtils.j(new File(dqkVar.b())) != 0) {
                LogUtil.a("HealthCloudConfigUtils", "localVer equals with cloudVer, no need to update.");
                e2.b(false);
                a2.onFinish(e2);
                return;
            }
        } else {
            e3 = "-1";
        }
        e2.b(true);
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("HealthCloudConfigUtils", "downloadFile, filePath is null");
            a2.onFail(1006, new IOException("HealthCloudConfigUtils downloadFile, filePath is null"));
            return;
        }
        String c2 = CommonUtil.c(b);
        String d2 = d(e2, e);
        if (d2 == null) {
            LogUtil.h("HealthCloudConfigUtils", "downloadFile, downloadUrl is null");
            a2.onFail(1007, new NullPointerException("HealthCloudConfigUtils downloadFile, downloadUrl is null"));
        } else {
            d(c2, d2, e, new drf.a().a(a3).e(e).b(d2).e(new File(c2)).a(a2).c(e3).d(e2).e(), a2);
        }
    }

    public static void a(String str, String str2, ProgressListener<File> progressListener) {
        LogUtil.a("HealthCloudConfigUtils", "enter downloadFileUrl.");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthCloudConfigUtils", "url is empty");
            return;
        }
        File file = new File(str2);
        String[] split = file.getName().split("\\.");
        File e = e(split[0], split[1]);
        c(file);
        a(e);
        lqi.d().d(new lqg(str, null, file, e, progressListener));
    }

    public static void d(String str, String str2) {
        LogUtil.a("HealthCloudConfigUtils", "enter setLocalFileVer.");
        SharedPreferenceManager.c(String.valueOf(20006), str + "_localVer", str2);
    }

    private static String h(String str) {
        LogUtil.a("HealthCloudConfigUtils", "enter getLocalFileVer.");
        return SharedPreferenceManager.e(String.valueOf(20006), str + "_localVer", String.valueOf(0L));
    }

    private static String e(dqi dqiVar, String str) {
        String str2;
        LogUtil.a("HealthCloudConfigUtils", "enter getDownloadUrl");
        List<dqh> e = dqiVar.e();
        int i = 0;
        while (true) {
            if (i >= e.size()) {
                str2 = null;
                break;
            }
            if (str.equals(e.get(i).a())) {
                str2 = e.get(i).c();
                break;
            }
            i++;
        }
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        LogUtil.h("HealthCloudConfigUtils", "ver is null or empty.");
        return null;
    }

    private static long e(String str) {
        LogUtil.a("HealthCloudConfigUtils", "enter getLatestCheckVersionTime");
        long b = SharedPreferenceManager.b(String.valueOf(20006), str + "_latestCheckVersionTime", 0L);
        if (b != 0) {
            return b;
        }
        LogUtil.h("HealthCloudConfigUtils", "latestCheckVersion is null or 0");
        return 0L;
    }

    private static void a(String str, long j) {
        LogUtil.a("HealthCloudConfigUtils", "enter setLatestCheckVersionTime");
        SharedPreferenceManager.e(String.valueOf(20006), str + "_latestCheckVersionTime", j);
    }
}
