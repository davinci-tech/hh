package com.huawei.android.hicloud.sync.update;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.text.TextUtils;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.android.hicloud.sync.update.ui.activity.UpCloudActivity;
import com.huawei.android.hicloud.sync.util.FileUtil;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.updatesdk.UpdateSdkAPI;
import com.huawei.updatesdk.fileprovider.UpdateSdkFileProvider;
import com.huawei.updatesdk.service.appmgr.bean.ApkUpgradeInfo;
import com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack;
import com.huawei.updatesdk.service.otaupdate.UpdateKey;
import defpackage.abd;
import defpackage.abg;
import defpackage.zs;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.Thread;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes8.dex */
public final class UpdateManager {
    private static UpdateManager d;

    /* renamed from: a, reason: collision with root package name */
    private ApkUpgradeInfo f1838a;
    private UpdateCheckCallback b;
    private int c;
    private boolean e;
    private Context g;
    private List<Handler> h;
    private String[] i = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};

    public interface UpdateCheckCallback {
        void onCheckHiCloudResult(int i);
    }

    class a implements CheckUpdateCallBack {
        a() {
        }

        @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
        public void onMarketInstallInfo(Intent intent) {
            abd.c("UpdateManager", "onMarketInstallInfo ");
            UpdateManager.this.b();
        }

        @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
        public void onMarketStoreError(int i) {
            abd.c("UpdateManager", "onMarketStoreError ");
            UpdateManager updateManager = UpdateManager.this;
            updateManager.d(updateManager.e(i));
            UpdateManager.this.b();
        }

        @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
        public void onUpdateInfo(Intent intent) {
            int i = 4;
            if (intent != null) {
                try {
                    int intExtra = intent.getIntExtra("status", -99);
                    abd.c("UpdateManager", "onUpdateInfo status: " + intExtra);
                    int e = UpdateManager.this.e(intExtra);
                    if (1 == e) {
                        Serializable serializableExtra = intent.getSerializableExtra(UpdateKey.INFO);
                        if (!(serializableExtra instanceof ApkUpgradeInfo) || UpdateManager.this.g == null) {
                            e = 4;
                        } else {
                            UpdateManager.this.f1838a = (ApkUpgradeInfo) serializableExtra;
                            Intent intent2 = new Intent(UpdateManager.this.g, (Class<?>) UpCloudActivity.class);
                            intent2.putExtra("new_version_info", (Serializable) UpdateManager.this.f1838a);
                            UpdateManager.this.g.startActivity(intent2);
                        }
                    }
                    if (1 != e) {
                        abd.c("UpdateManager", "onUpdateInfo status: " + intExtra + ",failCode: " + intent.getIntExtra(UpdateKey.FAIL_CODE, -99) + ",failReason: " + intent.getStringExtra(UpdateKey.FAIL_REASON));
                    }
                    i = e;
                } catch (Exception e2) {
                    abd.b("UpdateManager", "onUpdateInfo err " + e2.getMessage());
                }
            }
            UpdateManager.this.d(i);
            if (1 != i) {
                UpdateManager.this.b();
            }
        }

        @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
        public void onUpdateStoreError(int i) {
            abd.c("UpdateManager", "onUpdateStoreError ");
            UpdateManager updateManager = UpdateManager.this;
            updateManager.d(updateManager.e(i));
            UpdateManager.this.b();
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this) {
                UpdateManager.this.j();
            }
        }
    }

    class c implements Thread.UncaughtExceptionHandler {
        c() {
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            abd.b("UpdateManager", "Exception =" + th.getMessage());
        }
    }

    class d extends Thread {
        public d() {
            super.setName("download_thread");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            UpdateManager updateManager = UpdateManager.this;
            updateManager.c(updateManager.f1838a, false);
        }
    }

    class e extends TimerTask {
        final /* synthetic */ String d;

        e(String str) {
            this.d = str;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            abd.c("UpdateManager", "Timer.downloadApkData deleteFile = " + UpdateManager.c(this.d));
        }
    }

    private UpdateManager() {
    }

    public static UpdateManager a() {
        if (d == null) {
            d = new UpdateManager();
        }
        return d;
    }

    private void a(int i) {
        List<Handler> list = this.h;
        if (list != null) {
            for (Handler handler : list) {
                handler.sendMessage(handler.obtainMessage(i));
            }
        }
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str) || this.g == null) {
            return false;
        }
        File file = new File(str);
        if (!file.exists()) {
            abd.b("UpdateManager", "hicloud apk file not exist");
            return false;
        }
        try {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL);
            intent.addFlags(3);
            intent.setDataAndType(UpdateSdkFileProvider.getUriForFile(this.g, this.g.getPackageName() + ".fileprovider", file), "application/vnd.android.package-archive");
            intent.addFlags(268435456);
            this.g.startActivity(intent);
            return true;
        } catch (Exception unused) {
            abd.b("UpdateManager", "installApk: Exception");
            return true;
        }
    }

    private boolean a(String str, String str2) {
        String fileHashData = FileUtil.getFileHashData(str2, "SHA-256");
        return fileHashData != null && fileHashData.equalsIgnoreCase(str);
    }

    private static long b(String str) {
        StatFs statFs = new StatFs(str);
        return statFs.getAvailableBlocks() * statFs.getBlockSize();
    }

    private boolean b(boolean z, boolean z2, boolean z3) {
        if (this.c == 0) {
            abd.c("UpdateManager", "0 == fileLength");
            a(6);
            d(10);
            return false;
        }
        if (z && !z2) {
            if (this.g == null) {
                return false;
            }
            if (b(this.g.getFilesDir() + File.separator) < this.c) {
                c(this.f1838a, true);
                return false;
            }
        }
        if (!z3 || b(Environment.getExternalStorageDirectory().getPath()) >= this.c) {
            return true;
        }
        a(6);
        d(8);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(ApkUpgradeInfo apkUpgradeInfo, boolean z) {
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        String d2;
        synchronized (this) {
            if (z) {
                if (!e(this.g, this.i[0]) || !e(this.g, this.i[1])) {
                    abd.c("UpdateManager", "download not has permission");
                    a(6);
                    d(9);
                    return;
                }
            }
            boolean equals = Environment.getExternalStorageState().equals("mounted");
            boolean z2 = equals && z;
            String downurl_ = apkUpgradeInfo.getDownurl_();
            abd.c("UpdateManager", "download hasSDCard = " + equals + ", isUseSD = " + z);
            StringBuilder sb = new StringBuilder("download downloadApkURL = ");
            sb.append(downurl_);
            abd.a("UpdateManager", sb.toString());
            String name_ = apkUpgradeInfo.getName_();
            if (!name_.endsWith(".apk")) {
                apkUpgradeInfo.setName_(name_ + ".apk");
            }
            BufferedOutputStream bufferedOutputStream2 = null;
            try {
                try {
                    d2 = d(apkUpgradeInfo.getName_(), z2);
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = bufferedInputStream;
                    bufferedOutputStream2 = bufferedOutputStream2;
                }
            } catch (IOException e2) {
                e = e2;
                bufferedInputStream = null;
            } catch (NumberFormatException e3) {
                e = e3;
                bufferedInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = null;
            }
            if (d2 == null) {
                abd.c("UpdateManager", "download localfile = null");
                a(6);
                d(10);
                e((BufferedOutputStream) null, (BufferedInputStream) null);
                b();
                return;
            }
            c(d2);
            File file = new File(d2);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) URLConnectionInstrumentation.openConnection(new URL(downurl_).openConnection());
            zs.b(this.g, httpsURLConnection);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setRequestProperty("Range", "bytes=0-");
            int responseCode = httpsURLConnection.getResponseCode();
            if (200 != responseCode && 206 != responseCode) {
                if (!downurl_.startsWith("http:")) {
                    abd.a("UpdateManager", "downloadApkURL = " + downurl_);
                    a(6);
                    d(10);
                    e((BufferedOutputStream) null, (BufferedInputStream) null);
                    b();
                    return;
                }
                httpsURLConnection = (HttpsURLConnection) URLConnectionInstrumentation.openConnection(new URL(downurl_.replaceFirst("http:", "https:")).openConnection());
                zs.b(this.g, httpsURLConnection);
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setRequestProperty("Range", "bytes=0-");
                int responseCode2 = httpsURLConnection.getResponseCode();
                if (200 != responseCode2 && 206 != responseCode2) {
                    abd.a("UpdateManager", "responseCode = " + responseCode2);
                    a(6);
                    d(10);
                    e((BufferedOutputStream) null, (BufferedInputStream) null);
                    b();
                    return;
                }
            }
            this.c = Integer.parseInt(httpsURLConnection.getHeaderField("Content-Length"));
            if (!b(equals, z, z2)) {
                e((BufferedOutputStream) null, (BufferedInputStream) null);
                b();
                return;
            }
            bufferedInputStream = new BufferedInputStream(httpsURLConnection.getInputStream());
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, true), 131072);
                try {
                    c(apkUpgradeInfo, bufferedOutputStream, bufferedInputStream, d2, z2);
                } catch (IOException e4) {
                    e = e4;
                    bufferedOutputStream2 = bufferedOutputStream;
                    abd.b("UpdateManager", "IOException e = " + e.getMessage());
                    a(6);
                    d(10);
                    bufferedOutputStream = bufferedOutputStream2;
                    e(bufferedOutputStream, bufferedInputStream);
                    b();
                } catch (NumberFormatException e5) {
                    e = e5;
                    bufferedOutputStream2 = bufferedOutputStream;
                    abd.b("UpdateManager", "NumberFormatException ex = " + e.getMessage());
                    a(6);
                    d(10);
                    bufferedOutputStream = bufferedOutputStream2;
                    e(bufferedOutputStream, bufferedInputStream);
                    b();
                } catch (Throwable th3) {
                    th = th3;
                    bufferedOutputStream2 = bufferedOutputStream;
                    e(bufferedOutputStream2, bufferedInputStream);
                    b();
                    throw th;
                }
            } catch (IOException e6) {
                e = e6;
            } catch (NumberFormatException e7) {
                e = e7;
            } catch (Throwable th4) {
                th = th4;
            }
            e(bufferedOutputStream, bufferedInputStream);
            b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    private boolean c(String str, String str2, boolean z) {
        abd.a("UpdateManager", "check update apk whether exist.");
        if (z) {
            String str3 = Environment.getExternalStorageDirectory().getPath() + "/SyncSDK/" + str;
            if (new File(str3).exists() && a(str2, str3)) {
                return true;
            }
            e(str);
            return false;
        }
        if (this.g == null) {
            return false;
        }
        String str4 = this.g.getFilesDir() + File.separator + str;
        if (new File(str4).exists() && a(str2, str4)) {
            return true;
        }
        e(str);
        return false;
    }

    private String d(String str, boolean z) {
        if (z) {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/SyncSDK/");
            if (file.exists() || file.mkdirs()) {
                return Environment.getExternalStorageDirectory().getPath() + "/SyncSDK/" + str;
            }
        } else if (this.g != null) {
            return this.g.getFilesDir() + File.separator + str;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int e(int i) {
        if (i == 2) {
            return 2;
        }
        if (i == 3) {
            return 3;
        }
        if (i != 7) {
            return i != 8 ? 4 : 5;
        }
        return 1;
    }

    private void e(BufferedOutputStream bufferedOutputStream, BufferedInputStream bufferedInputStream) {
        if (bufferedInputStream != null) {
            try {
                bufferedInputStream.close();
            } catch (IOException e2) {
                abd.b("UpdateManager", "IOException e = " + e2.getMessage());
            }
        }
        if (bufferedOutputStream != null) {
            try {
                bufferedOutputStream.close();
            } catch (IOException e3) {
                abd.b("UpdateManager", "IOException e = " + e3.getMessage());
            }
        }
    }

    private void e(String str) {
        if (str == null || this.g == null) {
            return;
        }
        File file = new File(this.g.getFilesDir() + File.separator + str);
        if (file.exists()) {
            abd.c("UpdateManager", "removeErrorFile fileMem delete:" + file.delete());
        }
        File file2 = new File(Environment.getExternalStorageDirectory().getPath() + "/SyncSDK/" + str);
        if (file2.exists()) {
            abd.c("UpdateManager", "removeErrorFile fileSD delete:" + file2.delete());
        }
    }

    private boolean e(Context context, String str) {
        if (context == null) {
            abd.d("UpdateManager", "hasPermission: mContexts is null");
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            return packageManager.checkPermission(str, context.getPackageName()) == 0;
        }
        abd.d("UpdateManager", "hasPermission: pm is null");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        UpdateSdkAPI.checkTargetAppUpdate(this.g, abg.d(), new a());
    }

    public void b() {
        if (this.f1838a != null) {
            this.f1838a = null;
        }
        if (this.g != null) {
            this.g = null;
        }
        List<Handler> list = this.h;
        if (list != null) {
            list.clear();
            this.h = null;
        }
        UpdateSdkAPI.releaseCallBack();
    }

    public void b(Context context, UpdateCheckCallback updateCheckCallback) {
        this.g = context;
        this.b = updateCheckCallback;
        if (this.h == null) {
            this.h = new ArrayList();
        }
    }

    public void c() {
        new Thread(new b()).start();
    }

    public void d() {
        this.e = true;
    }

    public void d(int i) {
        UpdateCheckCallback updateCheckCallback = this.b;
        if (updateCheckCallback != null) {
            updateCheckCallback.onCheckHiCloudResult(i);
        }
    }

    public void e() {
        if (this.f1838a == null) {
            abd.c("UpdateManager", "update info is null.");
            return;
        }
        abd.c("UpdateManager", "startDownload");
        e(this.f1838a.getName_());
        this.e = false;
        d dVar = new d();
        dVar.setUncaughtExceptionHandler(new c());
        dVar.start();
    }

    public void fl_(Handler handler) {
        List<Handler> list = this.h;
        if (list != null) {
            list.add(handler);
        }
    }

    private void c(ApkUpgradeInfo apkUpgradeInfo, BufferedOutputStream bufferedOutputStream, BufferedInputStream bufferedInputStream, String str, boolean z) throws IOException {
        BufferedOutputStream bufferedOutputStream2;
        synchronized (this) {
            byte[] bArr = new byte[32768];
            long currentTimeMillis = System.currentTimeMillis();
            int i = 0;
            while (true) {
                if (this.e) {
                    break;
                }
                int read = bufferedInputStream.read(bArr);
                i += read;
                int i2 = this.c;
                int i3 = (int) ((i / i2) * 100.0f);
                if (read >= 0) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("DOWNLOADED_SIZE", String.valueOf(i));
                    hashMap.put("TOTAL_SIZE", String.valueOf(this.c));
                    long currentTimeMillis2 = System.currentTimeMillis();
                    if (currentTimeMillis2 - currentTimeMillis > 500) {
                        e(4, i3, 0, hashMap);
                        bufferedOutputStream2 = bufferedOutputStream;
                        currentTimeMillis = currentTimeMillis2;
                    } else {
                        bufferedOutputStream2 = bufferedOutputStream;
                    }
                    bufferedOutputStream2.write(bArr, 0, read);
                    if (this.e) {
                        e(apkUpgradeInfo.getName_());
                    }
                } else if (i2 == i + 1) {
                    bufferedOutputStream.flush();
                    if (c(apkUpgradeInfo.getName_(), apkUpgradeInfo.getSha256_(), z)) {
                        e(5, 0, 0, str);
                        d(6);
                        a(str);
                        new Timer().schedule(new e(str), 300000L);
                    } else {
                        abd.c("UpdateManager", "isUpdateFileExist false");
                        a(6);
                        d(10);
                    }
                } else {
                    abd.a("UpdateManager", "fileLength = " + this.c);
                    a(6);
                    d(10);
                }
            }
        }
    }

    private void e(int i, int i2, int i3, Object obj) {
        List<Handler> list = this.h;
        if (list != null) {
            for (Handler handler : list) {
                handler.sendMessage(handler.obtainMessage(i, i2, i3, obj));
            }
        }
    }
}
