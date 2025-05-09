package com.huawei.indoorequip.util;

import android.app.Activity;
import android.text.TextUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cxs;
import defpackage.dql;
import defpackage.drd;
import defpackage.dro;
import defpackage.koq;
import defpackage.msa;
import defpackage.msb;
import defpackage.msn;
import health.compact.a.IoUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class DownloadUtil {
    private static volatile DownloadUtil b;
    private static final Object c = new Object();
    private DownloadListener d;

    public interface DownloadListener {
        void onDownloadFinish(boolean z);
    }

    private DownloadUtil() {
        this(null);
    }

    private DownloadUtil(DownloadListener downloadListener) {
        this.d = downloadListener;
    }

    public static DownloadUtil b(DownloadListener downloadListener) {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new DownloadUtil(downloadListener);
                }
            }
        } else {
            b.e(downloadListener);
        }
        return b;
    }

    private boolean e(File file) {
        if (file == null || !file.exists()) {
            LogUtil.a("DownloadUtil", "deleteFiles, root == null or not exist");
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
            LogUtil.h("DownloadUtil", "root not file or directory");
        }
        LogUtil.a("DownloadUtil", "delete result= ", Boolean.valueOf(file.delete()));
        return false;
    }

    private dql a() {
        HashMap hashMap = new HashMap();
        hashMap.put("resources", "north_device_img");
        return new dql("com.huawei.health_ecologydevice_config", hashMap);
    }

    private void e() {
        drd.a(a(), "north_device_img_index", new DownloadCallback<File>() { // from class: com.huawei.indoorequip.util.DownloadUtil.1
            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onProgress(long j, long j2, boolean z, String str) {
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onFinish(File file) {
                LogUtil.a("DownloadUtil", "updatePpgConfig onFinish, data is: ", file.toString());
                String d = drd.d("com.huawei.health_ecologydevice_config", "north_device_img_index", "json");
                if (TextUtils.isEmpty(d)) {
                    return;
                }
                File file2 = new File(d);
                if (file2.exists()) {
                    DownloadUtil.this.b(msb.c(DownloadUtil.d(file2)).d());
                }
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFail(int i, Throwable th) {
                LogUtil.h("DownloadUtil", "updatePpgConfig on Fail: ", th.getMessage());
                if (DownloadUtil.this.d != null) {
                    DownloadUtil.this.d.onDownloadFinish(false);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<msa> list) {
        msa msaVar;
        if (koq.b(list) || (msaVar = list.get(0)) == null) {
            return;
        }
        int e = msaVar.e();
        String b2 = msaVar.b();
        final String d = msaVar.d();
        if (cxs.e() > e) {
            drd.a(a(), b2, new DownloadCallback<File>() { // from class: com.huawei.indoorequip.util.DownloadUtil.5
                @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
                public void onProgress(long j, long j2, boolean z, String str) {
                }

                @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onFinish(File file) {
                    LogUtil.a("DownloadUtil", "updatePpgConfig onFinish, data is: ", file.toString());
                    String d2 = drd.d("com.huawei.health_ecologydevice_config", "north_device_img_resource", "zip");
                    if (!msn.c(d, d2)) {
                        if (DownloadUtil.this.d != null) {
                            DownloadUtil.this.d.onDownloadFinish(false);
                            return;
                        }
                        return;
                    }
                    DownloadUtil.this.a(d2);
                }

                @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
                public void onFail(int i, Throwable th) {
                    LogUtil.h("DownloadUtil", "updatePpgConfig on Fail: ", th.getMessage());
                    if (DownloadUtil.this.d != null) {
                        DownloadUtil.this.d.onDownloadFinish(false);
                    }
                    DownloadUtil.this.c(drd.d("com.huawei.health_ecologydevice_config", "north_device_img_resource", "zip"));
                }
            });
        } else {
            LogUtil.h("DownloadUtil", "this torage is not enough");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(String str) {
        File file = new File(str);
        if (!file.exists() && !file.isDirectory()) {
            LogUtil.h("DownloadUtil", "this file is not exists");
            return false;
        }
        return e(file);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String d(File file) {
        if (file == null) {
            LogUtil.h("DownloadUtil", "readFileToData, file == null");
            return "";
        }
        LogUtil.a("DownloadUtil", "enter readFileToData: fileName = ", file.getName());
        if (!file.exists()) {
            LogUtil.h("DownloadUtil", "readFileToData, file not exist");
            return "";
        }
        try {
            return FileUtils.b(file, 5242880L);
        } catch (IOException e) {
            LogUtil.h("DownloadUtil", "readFileToData, ex=", health.compact.a.LogUtil.a(e));
            return "";
        }
    }

    public void bVQ_(Activity activity, boolean z) {
        if (activity == null) {
            LogUtil.b("DownloadUtil", "startDownload activity is null.");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis() - drd.b("north_device_img_resources");
        if (currentTimeMillis < 604800000 && currentTimeMillis > 0) {
            LogUtil.a("DownloadUtil", "downloadFiles, no need to update.");
            DownloadListener downloadListener = this.d;
            if (downloadListener != null) {
                downloadListener.onDownloadFinish(true);
                return;
            }
            return;
        }
        e();
    }

    public void e(DownloadListener downloadListener) {
        this.d = downloadListener;
    }

    private void b() {
        FileOutputStream fileOutputStream;
        String valueOf;
        LogUtil.a("DownloadUtil", "createDoneMarkFile enter create done file");
        StringBuilder sb = new StringBuilder();
        FileOutputStream fileOutputStream2 = null;
        sb.append(drd.d("com.huawei.health_ecologydevice_config", "north_device_img_resource", (String) null));
        sb.append(File.separator);
        sb.append("done");
        String sb2 = sb.toString();
        LogUtil.a("DownloadUtil", "donePath is ", sb2);
        try {
            try {
                File file = new File(sb2);
                if (!file.getParentFile().exists()) {
                    LogUtil.a("DownloadUtil", "createDoneMarkFile isDirMade is ", Boolean.valueOf(file.mkdir()));
                }
                if (!file.exists()) {
                    LogUtil.a("DownloadUtil", "createDoneMarkFile isNewFileCreated is ", Boolean.valueOf(file.createNewFile()));
                }
                valueOf = String.valueOf(new Date().getTime() / 1000);
                LogUtil.a("DownloadUtil", "createDoneMarkFile timeStamp is =", valueOf);
                fileOutputStream = new FileOutputStream(file);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
        } catch (IOException unused) {
        }
        try {
            byte[] bytes = valueOf.getBytes("UTF-8");
            fileOutputStream.write(bytes, 0, bytes.length);
            IoUtils.e(fileOutputStream);
        } catch (IOException unused2) {
            fileOutputStream2 = fileOutputStream;
            LogUtil.b("DownloadUtil", "createDoneMarkFile createDoneFile IOException");
            IoUtils.e(fileOutputStream2);
        } catch (Throwable th2) {
            th = th2;
            IoUtils.e(fileOutputStream);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str) {
        if (dro.e(str, drd.d("com.huawei.health_ecologydevice_config", "north_device_img_resource", (String) null) + File.separator) != -1) {
            LogUtil.a("DownloadUtil", "filePostProcess unzipFile is suc");
            b();
            c(str);
            drd.a(System.currentTimeMillis(), "north_device_img_resources");
            DownloadListener downloadListener = this.d;
            if (downloadListener != null) {
                downloadListener.onDownloadFinish(true);
            }
            return true;
        }
        LogUtil.h("DownloadUtil", "filePostProcess unzipFile is fail");
        return false;
    }
}
