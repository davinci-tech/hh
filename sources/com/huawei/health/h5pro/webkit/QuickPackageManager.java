package com.huawei.health.h5pro.webkit;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.health.h5pro.ext.H5ProResidentExtManager;
import com.huawei.health.h5pro.ext.interceptor.H5ProInterceptor;
import com.huawei.health.h5pro.ext.version.H5ProVersionUpgradeExtApi;
import com.huawei.health.h5pro.utils.AppInfoUtil;
import com.huawei.health.h5pro.utils.EnvironmentHelper;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5AppLockUtil;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.h5pro.vengine.H5ProAppLoader;
import com.huawei.health.h5pro.vengine.H5ProPackageManager;
import com.huawei.health.h5pro.vengine.H5ProVersionMeta;
import com.huawei.health.h5pro.vengine.load.H5ProInstanceStatus;
import com.huawei.health.h5pro.vengine.load.H5ProInstanceStatusListener;
import com.huawei.operation.OpAnalyticsConstants;
import com.tencent.connect.common.Constants;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class QuickPackageManager implements H5ProPackageManager {

    /* renamed from: a, reason: collision with root package name */
    public H5ProInstallDirCleanTask f2468a;
    public final Object b = new Object();
    public volatile H5ProAppInfo c;
    public final String d;
    public WeakReference<H5ProInstanceStatusListener> e;
    public volatile String f;
    public WeakReference<Context> g;

    public interface DownloadCbk {
        void onFailure(int i, String str);

        void onNewVersion();

        void onSuccess();
    }

    private boolean j(String str) {
        return false;
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProPackageManager
    public void setInstanceStatusListener(H5ProInstanceStatusListener h5ProInstanceStatusListener) {
        this.e = new WeakReference<>(h5ProInstanceStatusListener);
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProPackageManager
    public String readPreRequestsJson(String str) {
        String r;
        synchronized (this.b) {
            try {
                try {
                    LogUtil.i(getTag(str), "readPreRequestsJson enter");
                    r = r(t(str));
                } catch (FileNotFoundException unused) {
                    return "";
                } catch (IOException e) {
                    LogUtil.e(getTag(str), "readPreRequestsJson IOException: " + e.getMessage());
                    return "";
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return r;
    }

    public String readManifestJson(String str) {
        String str2;
        synchronized (this.b) {
            if (TextUtils.isEmpty(this.f)) {
                this.f = r(o(str));
            }
            str2 = this.f;
        }
        return str2;
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProPackageManager
    public boolean preInstallHpk(final String str) {
        final boolean[] zArr = {false};
        if (H5AppLockUtil.lock(str, 0L)) {
            try {
                try {
                    b(str, new H5ProAppLoader.H5ProPreloadCbk() { // from class: com.huawei.health.h5pro.webkit.QuickPackageManager.4
                        @Override // com.huawei.health.h5pro.vengine.H5ProAppLoader.H5ProPreloadCbk
                        public void onSuccess() {
                            zArr[0] = true;
                            LogUtil.i(QuickPackageManager.getTag(str), "preInstallHpk: onSuccess");
                        }

                        @Override // com.huawei.health.h5pro.vengine.H5ProAppLoader.H5ProPreloadCbk
                        public void onFailure(int i, String str2) {
                            LogUtil.e(QuickPackageManager.getTag(str), "preInstallHpk: onFailure -> " + str2);
                        }
                    });
                } catch (IOException e) {
                    LogUtil.e(getTag(str), "preInstallHpk: exception -> " + e.getMessage());
                }
            } finally {
                H5AppLockUtil.unLock(str);
            }
        }
        return zArr[0];
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProPackageManager
    public String joinUrl(String str) {
        StringBuilder sb = new StringBuilder();
        if (this.c == null) {
            this.c = getAppInfo(str);
        }
        if (TextUtils.isEmpty(this.c.getBaseUrl())) {
            this.c.setPkgName(str);
            this.c.setBaseUrl(AppInfoUtil.getInstance().getBaseUrl(str));
        }
        sb.append(this.c.getBaseUrl());
        sb.append("/h5pro/");
        sb.append(str);
        sb.append("/");
        sb.append(b(str));
        sb.append("/entry/index.html");
        return sb.toString();
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProPackageManager
    public void installApp(final String str, final H5ProAppLoader.H5ProPreloadCbk h5ProPreloadCbk) {
        new Thread(new Runnable() { // from class: com.huawei.health.h5pro.webkit.QuickPackageManager.1
            @Override // java.lang.Runnable
            public void run() {
                if (!H5AppLockUtil.lock(str, OpAnalyticsConstants.H5_LOADING_DELAY)) {
                    LogUtil.e(QuickPackageManager.getTag(str), "installApp: can not get lock");
                    h5ProPreloadCbk.onFailure(-1, "Installation failed: can not get lock, appName=" + str);
                    return;
                }
                LogUtil.i(QuickPackageManager.getTag(str), "installApp: got a lock");
                try {
                    try {
                        QuickPackageManager.this.b(str, h5ProPreloadCbk);
                    } catch (IOException e) {
                        LogUtil.e(QuickPackageManager.getTag(str), "installApp: exception -> ", e.getMessage());
                        h5ProPreloadCbk.onFailure(-1, e.getMessage());
                    }
                } finally {
                    H5AppLockUtil.unLock(str);
                }
            }
        }, "thread-H5ProAppInstall").start();
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProPackageManager
    public H5ProAppInfo getAppInfo(String str) {
        if (this.c != null) {
            return this.c;
        }
        H5ProAppInfo h5ProAppInfo = new H5ProAppInfo();
        h5ProAppInfo.setPkgName(str);
        h5ProAppInfo.setH5ProAppType(H5ProAppInfo.H5ProAppType.H5_MINI_PROGRAM);
        try {
            JSONObject jSONObject = new JSONObject(readManifestJson(str));
            h5ProAppInfo.setAppId(jSONObject.optString("app_id"));
            h5ProAppInfo.setCertPrint(jSONObject.optString("cert_print"));
            h5ProAppInfo.setAppName(jSONObject.optString("app_name"));
            h5ProAppInfo.setPkgName(jSONObject.optString(Constants.PARAM_PKG_NAME));
            h5ProAppInfo.setVersion(jSONObject.optString("version"));
            h5ProAppInfo.setBaseUrl(AppInfoUtil.getInstance().getBaseUrl(str));
            this.c = h5ProAppInfo;
            return this.c;
        } catch (IOException e) {
            LogUtil.e(getTag(str), "getAppInfo==>readFileContent error", e.getMessage());
            return new H5ProAppInfo();
        } catch (JSONException e2) {
            LogUtil.e(getTag(str), "getAppInfo==>json parse error", e2.getMessage());
            return new H5ProAppInfo();
        }
    }

    private String r(String str) {
        FileInputStream fileInputStream = new FileInputStream(str);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return sb.toString();
                }
                sb.append(readLine);
            }
        } finally {
            fileInputStream.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(H5ProInstanceStatus h5ProInstanceStatus) {
        H5ProInstanceStatusListener h5ProInstanceStatusListener;
        WeakReference<H5ProInstanceStatusListener> weakReference = this.e;
        if (weakReference == null || (h5ProInstanceStatusListener = weakReference.get()) == null) {
            return;
        }
        h5ProInstanceStatusListener.onStatusChanged(h5ProInstanceStatus);
    }

    private String t(String str) {
        return n(str) + "/preRequests.json";
    }

    private String m(String str) {
        return EnvironmentHelper.getInstance().getUrl() + str + "/" + str + "-version.json";
    }

    private String k(String str) {
        return this.d + "/" + str + "/" + str + "-version.json";
    }

    private String o(String str) {
        return n(str) + "/manifest.json";
    }

    private String l(String str) {
        return this.d + "/" + str + "/" + str + ".installed";
    }

    private String n(String str) {
        return this.d + "/" + str + "/" + b(str);
    }

    private String i(String str) {
        return g(str) + "/init.em";
    }

    private String e(String str, String str2) {
        return EnvironmentHelper.getInstance().getUrl() + str + "/" + str + com.huawei.openalliance.ad.constant.Constants.LINK + str2 + ".hpk";
    }

    private String f(String str) {
        return this.d + "/" + str + "/" + str + ".hpk";
    }

    private String g(String str) {
        return this.d + "/" + str;
    }

    private boolean a(String str, String str2) {
        if (new File(o(str)).exists()) {
            try {
                return new Version(str2).compareTo(new Version(new JSONObject(readManifestJson(str)).optString("version"))) != 0;
            } catch (IOException | JSONException e) {
                LogUtil.e(getTag(str), "parse manifest.json err: " + e.getMessage());
            }
        }
        return true;
    }

    private boolean h(String str) {
        if (!new File(l(str)).exists()) {
            return false;
        }
        try {
            return new File(n(str)).exists();
        } catch (FileNotFoundException e) {
            LogUtil.e(getTag(str), "isInstalled: exception -> " + e.getMessage());
            return false;
        }
    }

    private boolean b(H5ProVersionMeta h5ProVersionMeta, String str) {
        H5ProVersionUpgradeExtApi versionUpgradeExtApi = H5ProResidentExtManager.getVersionUpgradeExtApi();
        if (versionUpgradeExtApi == null) {
            return true;
        }
        boolean isSupportUpgrade = versionUpgradeExtApi.isSupportUpgrade(h5ProVersionMeta);
        if (isSupportUpgrade) {
            return isSupportUpgrade;
        }
        LogUtil.i(getTag(str), "[RELEASE]isHostAppSupportUpgrade: false, minAppVersion=" + h5ProVersionMeta.getMinAppVersion());
        return isSupportUpgrade;
    }

    private boolean e(String str) {
        long j;
        InstalledMeta installedMeta = new InstalledMeta(r(l(str)));
        try {
            j = new Date().getTime() - installedMeta.getInstallTime();
        } catch (NumberFormatException unused) {
            LogUtil.e(getTag(str), "lastInstallTime: NumberFormatException");
            j = 0;
        }
        EnvironmentHelper.BuildType buildType = EnvironmentHelper.getInstance().getBuildType();
        long j2 = (buildType == EnvironmentHelper.BuildType.RELEASE || buildType == EnvironmentHelper.BuildType.BETA) ? 1800000L : 60000L;
        if (j <= 0 || j > j2 || buildType != installedMeta.getBuildType()) {
            LogUtil.i(getTag(str), "force update h5 app.");
            HpkManager.b.updateInstalledFile(this.g.get(), str);
            return true;
        }
        LogUtil.i(getTag(str), "lastInstalledMeta: " + installedMeta.toJson());
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final String str, final H5ProAppLoader.H5ProPreloadCbk h5ProPreloadCbk) {
        d(str);
        if (h(str)) {
            b(H5ProInstanceStatus.CONFIG_PREPARED);
            H5ProResidentExtManager.getVersionInterceptor().intercept(str, new H5ProInterceptor.InterceptCallback<String>() { // from class: com.huawei.health.h5pro.webkit.QuickPackageManager.2
                @Override // com.huawei.health.h5pro.ext.interceptor.H5ProInterceptor.InterceptCallback
                public void onNotIntercepted() {
                    LogUtil.i(QuickPackageManager.getTag(str), "installHpk: onNotIntercepted");
                    try {
                        QuickPackageManager.this.a(str, h5ProPreloadCbk);
                    } catch (IOException e) {
                        LogUtil.e(QuickPackageManager.getTag(str), "installHpk: exception -> " + e.getMessage());
                        h5ProPreloadCbk.onFailure(-1, e.getMessage());
                    }
                }

                @Override // com.huawei.health.h5pro.ext.interceptor.H5ProInterceptor.InterceptCallback
                public void onIntercepted(String str2) {
                    LogUtil.i(QuickPackageManager.getTag(str), "installHpk: onIntercepted -> " + str2);
                    if (TextUtils.isEmpty(str2) || !HpkManager.b.versionMatches(str2) || QuickPackageManager.this.c(str, str2)) {
                        onNotIntercepted();
                    } else {
                        h5ProPreloadCbk.onSuccess();
                    }
                }
            });
            return;
        }
        if (j(str)) {
            h5ProPreloadCbk.onSuccess();
            return;
        }
        LogUtil.i(getTag(str), "installHpk: first");
        this.f2468a.execute(str);
        if (HpkManager.b.isDownloadedHpk(this.g.get(), str)) {
            LogUtil.i(getTag(str), "installHpk: downloaded, just begin to unzip.");
            if (c(str)) {
                h5ProPreloadCbk.onSuccess();
                return;
            }
        }
        d(str, h5ProPreloadCbk, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(String str) {
        boolean installDownloadedHpk;
        synchronized (this.b) {
            LogUtil.i(getTag(str), "installDownloadedHpk");
            installDownloadedHpk = HpkManager.b.installDownloadedHpk(this.g.get(), str);
        }
        return installDownloadedHpk;
    }

    public static String getTag(String str) {
        return LogUtil.getTag(str, "PkgMgr");
    }

    private String a(String str) {
        File file = new File(i(str));
        if (file.exists()) {
            return String.valueOf(file.lastModified());
        }
        if (file.getParentFile().exists()) {
            FileDownloadManager.deleteFiles(g(str), true);
        }
        LogUtil.e(getTag(str), "init.em not created!");
        throw new FileNotFoundException("init.em not created");
    }

    private String b(String str) {
        return HpkManager.b.getInstallDirNamePrefix(a(str)) + EnvironmentHelper.getInstance().getUserFlag();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0093  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(java.lang.String r9, com.huawei.health.h5pro.webkit.QuickPackageManager.DownloadCbk r10) {
        /*
            r8 = this;
            java.lang.String r0 = getTag(r9)
            java.lang.String r1 = "downloadHpk: start"
            java.lang.String[] r1 = new java.lang.String[]{r1}
            com.huawei.health.h5pro.utils.LogUtil.i(r0, r1)
            r0 = 0
            r1 = 0
            boolean r2 = r8.h(r9)     // Catch: com.huawei.health.h5pro.webkit.HpkVersionException -> L62 org.json.JSONException -> L64 java.io.IOException -> L66
            java.lang.String r3 = r8.m(r9)     // Catch: com.huawei.health.h5pro.webkit.HpkVersionException -> L5c org.json.JSONException -> L5e java.io.IOException -> L60
            java.lang.String r4 = r8.k(r9)     // Catch: com.huawei.health.h5pro.webkit.HpkVersionException -> L5c org.json.JSONException -> L5e java.io.IOException -> L60
            long r3 = r8.e(r3, r4, r9)     // Catch: com.huawei.health.h5pro.webkit.HpkVersionException -> L5c org.json.JSONException -> L5e java.io.IOException -> L60
            com.huawei.health.h5pro.vengine.H5ProVersionMeta r5 = new com.huawei.health.h5pro.vengine.H5ProVersionMeta     // Catch: com.huawei.health.h5pro.webkit.HpkVersionException -> L5c org.json.JSONException -> L5e java.io.IOException -> L60
            java.lang.String r6 = r8.k(r9)     // Catch: com.huawei.health.h5pro.webkit.HpkVersionException -> L5c org.json.JSONException -> L5e java.io.IOException -> L60
            java.lang.String r6 = r8.r(r6)     // Catch: com.huawei.health.h5pro.webkit.HpkVersionException -> L5c org.json.JSONException -> L5e java.io.IOException -> L60
            r5.<init>(r6)     // Catch: com.huawei.health.h5pro.webkit.HpkVersionException -> L5c org.json.JSONException -> L5e java.io.IOException -> L60
            if (r2 == 0) goto L42
            java.lang.String r6 = r5.getLatestVersion()     // Catch: com.huawei.health.h5pro.webkit.HpkVersionException -> L5c org.json.JSONException -> L5e java.io.IOException -> L60
            boolean r6 = r8.a(r9, r6)     // Catch: com.huawei.health.h5pro.webkit.HpkVersionException -> L5c org.json.JSONException -> L5e java.io.IOException -> L60
            if (r6 == 0) goto L3e
            boolean r6 = r8.b(r5, r9)     // Catch: com.huawei.health.h5pro.webkit.HpkVersionException -> L5c org.json.JSONException -> L5e java.io.IOException -> L60
            if (r6 != 0) goto L42
        L3e:
            r10.onSuccess()     // Catch: com.huawei.health.h5pro.webkit.HpkVersionException -> L5c org.json.JSONException -> L5e java.io.IOException -> L60
            return
        L42:
            java.lang.String r5 = r5.getLatestVersion()     // Catch: com.huawei.health.h5pro.webkit.HpkVersionException -> L5c org.json.JSONException -> L5e java.io.IOException -> L60
            java.lang.String r1 = r8.f(r9)     // Catch: com.huawei.health.h5pro.webkit.HpkVersionException -> L5c org.json.JSONException -> L5e java.io.IOException -> L60
            java.lang.String r5 = r8.e(r9, r5)     // Catch: com.huawei.health.h5pro.webkit.HpkVersionException -> L5c org.json.JSONException -> L5e java.io.IOException -> L60
            long r5 = r8.e(r5, r1, r9)     // Catch: com.huawei.health.h5pro.webkit.HpkVersionException -> L5c org.json.JSONException -> L5e java.io.IOException -> L60
            com.huawei.health.h5pro.webkit.H5ProLoadingRecordManager r7 = com.huawei.health.h5pro.webkit.H5ProLoadingRecordManager.e     // Catch: com.huawei.health.h5pro.webkit.HpkVersionException -> L5c org.json.JSONException -> L5e java.io.IOException -> L60
            long r3 = r3 + r5
            r7.recordDownloadTime(r9, r3)     // Catch: com.huawei.health.h5pro.webkit.HpkVersionException -> L5c org.json.JSONException -> L5e java.io.IOException -> L60
            r10.onNewVersion()     // Catch: com.huawei.health.h5pro.webkit.HpkVersionException -> L5c org.json.JSONException -> L5e java.io.IOException -> L60
            goto L9b
        L5c:
            r3 = move-exception
            goto L69
        L5e:
            r3 = move-exception
            goto L69
        L60:
            r3 = move-exception
            goto L69
        L62:
            r2 = move-exception
            goto L67
        L64:
            r2 = move-exception
            goto L67
        L66:
            r2 = move-exception
        L67:
            r3 = r2
            r2 = r0
        L69:
            java.lang.String r4 = getTag(r9)
            java.util.Locale r5 = java.util.Locale.ENGLISH
            java.lang.String r6 = r3.getMessage()
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            java.lang.String r7 = "downloadHpk Exception: %s"
            java.lang.String r5 = java.lang.String.format(r5, r7, r6)
            java.lang.String[] r5 = new java.lang.String[]{r5}
            com.huawei.health.h5pro.utils.LogUtil.e(r4, r5)
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            if (r4 != 0) goto L8d
            com.huawei.health.h5pro.webkit.FileDownloadManager.deleteFiles(r1, r0)
        L8d:
            if (r2 == 0) goto L93
            r10.onSuccess()
            return
        L93:
            r0 = -1
            java.lang.String r1 = r3.getMessage()
            r10.onFailure(r0, r1)
        L9b:
            java.lang.String r9 = getTag(r9)
            java.lang.String r10 = "downloadHpk: end"
            java.lang.String[] r10 = new java.lang.String[]{r10}
            com.huawei.health.h5pro.utils.LogUtil.i(r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.h5pro.webkit.QuickPackageManager.d(java.lang.String, com.huawei.health.h5pro.webkit.QuickPackageManager$DownloadCbk):void");
    }

    private long e(String str, String str2, String str3) {
        long currentTimeMillis = System.currentTimeMillis();
        String tag = getTag(str3);
        boolean endsWith = str.endsWith("hpk");
        LogUtil.i(tag, String.format(Locale.ENGLISH, "download(isHpk->%b): start", Boolean.valueOf(endsWith)));
        LogUtil.d(tag, String.format(Locale.ENGLISH, "download %s to %s", str, str2));
        HttpURLConnection httpURLConnection = (HttpURLConnection) URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
        httpURLConnection.setConnectTimeout(2000);
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Charset", StandardCharsets.UTF_8.name());
        LogUtil.i(tag, String.format(Locale.ENGLISH, "download(isHpk->%b): connect", Boolean.valueOf(endsWith)));
        httpURLConnection.connect();
        int responseCode = httpURLConnection.getResponseCode();
        LogUtil.i(tag, String.format(Locale.ENGLISH, "download(isHpk->%b): responseCode: %d", Boolean.valueOf(endsWith), Integer.valueOf(responseCode)));
        if (responseCode != 200) {
            throw new IOException("error response code: " + responseCode);
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            try {
                byte[] bArr = new byte[1048576];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        fileOutputStream.close();
                        bufferedInputStream.close();
                        LogUtil.i(tag, String.format(Locale.ENGLISH, "download(isHpk->%b): end", Boolean.valueOf(endsWith)));
                        return System.currentTimeMillis() - currentTimeMillis;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                bufferedInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private void d(final String str, final H5ProAppLoader.H5ProPreloadCbk h5ProPreloadCbk, final boolean z) {
        d(str, new DownloadCbk() { // from class: com.huawei.health.h5pro.webkit.QuickPackageManager.3
            @Override // com.huawei.health.h5pro.webkit.QuickPackageManager.DownloadCbk
            public void onSuccess() {
                LogUtil.i(QuickPackageManager.getTag(str), "downloadHpk: onSuccess");
                h5ProPreloadCbk.onSuccess();
            }

            @Override // com.huawei.health.h5pro.webkit.QuickPackageManager.DownloadCbk
            public void onNewVersion() {
                LogUtil.i(QuickPackageManager.getTag(str), "downloadHpk: onNewVersion -> " + z);
                if (!QuickPackageManager.this.c(str)) {
                    LogUtil.i(QuickPackageManager.getTag(str), "downloadHpk: onNewVersion -> hpk installation failed");
                    h5ProPreloadCbk.onFailure(-1, "hpk installation failed");
                } else {
                    if (!z) {
                        QuickPackageManager.this.b(H5ProInstanceStatus.CONFIG_PREPARED);
                    }
                    h5ProPreloadCbk.onSuccess();
                }
            }

            @Override // com.huawei.health.h5pro.webkit.QuickPackageManager.DownloadCbk
            public void onFailure(int i, String str2) {
                LogUtil.e(QuickPackageManager.getTag(str), "downLoadHpk: onFailure -> errCode: " + i + ", errMsg: " + str2);
                if (!z) {
                    h5ProPreloadCbk.onFailure(i, str2);
                } else {
                    LogUtil.w(QuickPackageManager.getTag(str), "downloadHpk: Forced update failed");
                    h5ProPreloadCbk.onSuccess();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0021, code lost:
    
        if (c(r3) != false) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.lang.String r3, com.huawei.health.h5pro.vengine.H5ProAppLoader.H5ProPreloadCbk r4) {
        /*
            r2 = this;
            com.huawei.health.h5pro.webkit.HpkManager r0 = com.huawei.health.h5pro.webkit.HpkManager.b
            java.lang.ref.WeakReference<android.content.Context> r1 = r2.g
            java.lang.Object r1 = r1.get()
            android.content.Context r1 = (android.content.Context) r1
            boolean r0 = r0.isDownloadedHpk(r1, r3)
            if (r0 == 0) goto L24
            java.lang.String r0 = getTag(r3)
            java.lang.String r1 = "doIfInstalled: downloaded, just begin to unzip."
            java.lang.String[] r1 = new java.lang.String[]{r1}
            com.huawei.health.h5pro.utils.LogUtil.i(r0, r1)
            boolean r0 = r2.c(r3)
            if (r0 == 0) goto L37
            goto L3c
        L24:
            boolean r0 = r2.e(r3)
            if (r0 == 0) goto L3c
            java.lang.String r0 = getTag(r3)
            java.lang.String r1 = "doIfInstalled: forceUpdate"
            java.lang.String[] r1 = new java.lang.String[]{r1}
            com.huawei.health.h5pro.utils.LogUtil.i(r0, r1)
        L37:
            r0 = 1
            r2.d(r3, r4, r0)
            goto L3f
        L3c:
            r4.onSuccess()
        L3f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.h5pro.webkit.QuickPackageManager.a(java.lang.String, com.huawei.health.h5pro.vengine.H5ProAppLoader$H5ProPreloadCbk):void");
    }

    private void d(String str) {
        File file = new File(i(str));
        LogUtil.i(getTag(str), "createIfNotExistInitFile -> " + file.exists());
        if (file.exists()) {
            return;
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(String str, String str2) {
        try {
            String readManifestJson = readManifestJson(str);
            if (!TextUtils.isEmpty(readManifestJson)) {
                try {
                    try {
                        return new Version(str2).compareTo(new Version(new JSONObject(readManifestJson).optString("version"))) != 0;
                    } catch (HpkVersionException e) {
                        LogUtil.e(getTag(str), "checkNewVersion HpkVersionException: " + e.getMessage());
                        return true;
                    }
                } catch (JSONException e2) {
                    LogUtil.e(getTag(str), "parse manifest.json err: " + e2.getMessage());
                }
            }
            return true;
        } catch (IOException e3) {
            LogUtil.e(getTag(str), "checkNewVersion manifestPath IOException: " + e3.getMessage());
            return true;
        }
    }

    public static class Version implements Comparable<Version> {
        public final String b;

        public int hashCode() {
            return Objects.hash(this.b);
        }

        public final String get() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && Version.class == obj.getClass() && compareTo((Version) obj) == 0;
        }

        @Override // java.lang.Comparable
        public int compareTo(Version version) {
            if (version == null) {
                return 1;
            }
            String[] split = get().split("\\.");
            String[] split2 = version.get().split("\\.");
            int max = Math.max(split.length, split2.length);
            int i = 0;
            while (i < max) {
                int parseInt = i < split.length ? Integer.parseInt(split[i]) : 0;
                int parseInt2 = i < split2.length ? Integer.parseInt(split2[i]) : 0;
                if (parseInt < parseInt2) {
                    return -1;
                }
                if (parseInt > parseInt2) {
                    return 1;
                }
                i++;
            }
            return 0;
        }

        public Version(String str) {
            if (!HpkManager.b.versionMatches(str)) {
                throw new HpkVersionException(str);
            }
            this.b = str;
        }
    }

    public QuickPackageManager(Context context) {
        this.g = new WeakReference<>(context);
        File file = new File(WebKitUtil.isStrictMode() ? context.getFilesDir() : context.getExternalFilesDir(""), "h5pro");
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            LogUtil.i(getTag("QuickPackageManager"), "isMkdirs -> " + mkdirs);
        }
        this.d = file.getAbsolutePath();
        this.f2468a = new H5ProInstallDirCleanTask(context);
    }
}
