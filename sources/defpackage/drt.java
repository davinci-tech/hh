package defpackage;

import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.health.healthcloudconfig.voiceconfig.VoiceConfigCallback;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HEXUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class drt {
    public static void a(final String str, String str2, final VoiceConfigCallback voiceConfigCallback) {
        dqr d = d(str, str2, voiceConfigCallback);
        if (d == null) {
            c(str, "checkUpdate QueryParams is null", voiceConfigCallback);
            return;
        }
        final String e = d.e();
        if (!e.matches("[-_.A-Za-z0-9]+")) {
            ReleaseLogUtil.c("HiH_VoiceConfigManager", "deviceIdentify = ", str, " errorInfo = ", "checkUpdate invalid char in configId");
            voiceConfigCallback.onFailure(str, "checkUpdate invalid char in configId");
        }
        final List<dqs> d2 = d.d();
        String a2 = d.a();
        String b = d.b();
        if (TextUtils.isEmpty(e) || TextUtils.isEmpty(a2) || TextUtils.isEmpty(b) || d2.size() == 0) {
            c(str, "checkUpdate necessary params is null", voiceConfigCallback);
            return;
        }
        final HashMap hashMap = new HashMap();
        hashMap.put(a2, b);
        ThreadPoolManager.d().execute(new Runnable() { // from class: drq
            @Override // java.lang.Runnable
            public final void run() {
                drt.a(e, hashMap, str, voiceConfigCallback, d2);
            }
        });
    }

    static /* synthetic */ void a(String str, Map map, String str2, VoiceConfigCallback voiceConfigCallback, List list) {
        dqi d = drd.d("HiH_VoiceConfigManager", new dql(str, map));
        if (d != null) {
            List<dqh> e = d.e();
            if (e.size() == 0) {
                c(str2, "getResponse from cloud fileList size = 0", voiceConfigCallback);
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                for (int i2 = 0; i2 < e.size(); i2++) {
                    String c = ((dqs) list.get(i)).c();
                    int e2 = ((dqs) list.get(i)).e();
                    String a2 = e.get(i2).a();
                    int parseInt = Integer.parseInt(e.get(i2).c());
                    if (TextUtils.isEmpty(c) || TextUtils.isEmpty(a2)) {
                        LogUtil.b("HiH_VoiceConfigManager", "fileName or fileIdFromCloud is empty");
                    } else if (c.equals(a2) && parseInt > e2) {
                        drm drmVar = new drm();
                        drmVar.d(c).e(parseInt).e(e.get(i2).d()).c(a(e.get(i2).d()));
                        arrayList.add(drmVar);
                    }
                }
            }
            voiceConfigCallback.onSuccess(str2, arrayList);
        }
    }

    public static void e(final String str, String str2, final VoiceConfigCallback voiceConfigCallback) {
        dqr d = d(str, str2, voiceConfigCallback);
        if (d == null) {
            c(str, "downloadFiles QueryParams is null", voiceConfigCallback);
            return;
        }
        final String e = d.e();
        List<dqs> d2 = d.d();
        String a2 = d.a();
        String b = d.b();
        final ArrayList arrayList = new ArrayList();
        for (int i = 0; i < d2.size(); i++) {
            arrayList.add(d2.get(i).c());
        }
        if (TextUtils.isEmpty(e) || TextUtils.isEmpty(a2) || TextUtils.isEmpty(b) || arrayList.size() == 0) {
            c(str, "downloadFiles necessary params is null", voiceConfigCallback);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(a2, b);
        ReleaseLogUtil.e("HiH_VoiceConfigManager", "VoiceConfigManager downloadBatchList : ", arrayList);
        drd.c(new dql(e, hashMap), "HiH_VoiceConfigManager", arrayList, new DownloadCallback<dqi>() { // from class: drt.4
            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onFinish(dqi dqiVar) {
                LogUtil.a("HiH_VoiceConfigManager", "downloadFiles onFinish, data is: ", HiJsonUtil.e(dqiVar));
                drt.a(dqiVar, e, (List<String>) arrayList, str, voiceConfigCallback);
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onProgress(long j, long j2, boolean z, String str3) {
                voiceConfigCallback.onProgress(j, j2, z, str3);
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFail(int i2, Throwable th) {
                drt.c(str, i2 + th.toString(), voiceConfigCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(dqi dqiVar, String str, List<String> list, String str2, VoiceConfigCallback voiceConfigCallback) {
        ArrayList arrayList = new ArrayList();
        List<dqh> e = dqiVar.e();
        for (int i = 0; i < e.size(); i++) {
            String a2 = e.get(i).a();
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (list.get(i2).equals(a2)) {
                    String c = e.get(i).c();
                    String d = e.get(i).d();
                    String d2 = drd.d(str, a2, d);
                    if (TextUtils.isEmpty(c) || TextUtils.isEmpty(d) || TextUtils.isEmpty(d2)) {
                        LogUtil.b("HiH_VoiceConfigManager", "ver or downloadUrl or filePath isEmpty");
                    } else {
                        File file = new File(d2);
                        File file2 = new File(file.getParent() + File.separator + a2 + "." + c + "." + d2.split("\\.")[r7.length - 1]);
                        boolean renameTo = file.renameTo(file2);
                        drm drmVar = new drm();
                        if (!renameTo) {
                            LogUtil.a("HiH_VoiceConfigManager", "renameFile failed");
                            drmVar.c(file.getPath());
                        } else {
                            drmVar.c(file2.getPath());
                        }
                        drmVar.d(a2);
                        drmVar.e(Integer.parseInt(c));
                        arrayList.add(drmVar);
                    }
                }
            }
        }
        voiceConfigCallback.onSuccess(str2, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str, String str2, VoiceConfigCallback voiceConfigCallback) {
        LogUtil.b("HiH_VoiceConfigManager", "deviceIdentify = ", str, " errorInfo = ", str2);
        voiceConfigCallback.onFailure(str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int a(java.lang.String r5) {
        /*
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L21 java.io.IOException -> L23
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L21 java.io.IOException -> L23
            java.net.URLConnection r5 = r1.openConnection()     // Catch: java.lang.Throwable -> L21 java.io.IOException -> L23
            java.net.URLConnection r5 = com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation.openConnection(r5)     // Catch: java.lang.Throwable -> L21 java.io.IOException -> L23
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch: java.lang.Throwable -> L21 java.io.IOException -> L23
            int r0 = r5.getContentLength()     // Catch: java.lang.Throwable -> L1a java.io.IOException -> L1c
            if (r5 == 0) goto L3e
            r5.disconnect()
            goto L3e
        L1a:
            r0 = move-exception
            goto L43
        L1c:
            r0 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
            goto L24
        L21:
            r5 = move-exception
            goto L46
        L23:
            r5 = move-exception
        L24:
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L3f
            java.lang.String r2 = "getFileSizeFromNet IOException "
            r3 = 0
            r1[r3] = r2     // Catch: java.lang.Throwable -> L3f
            java.lang.String r5 = r5.getMessage()     // Catch: java.lang.Throwable -> L3f
            r2 = 1
            r1[r2] = r5     // Catch: java.lang.Throwable -> L3f
            java.lang.String r5 = "HiH_VoiceConfigManager"
            com.huawei.hwlogsmodel.LogUtil.b(r5, r1)     // Catch: java.lang.Throwable -> L3f
            if (r0 == 0) goto L3d
            r0.disconnect()
        L3d:
            r0 = r3
        L3e:
            return r0
        L3f:
            r5 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
        L43:
            r4 = r0
            r0 = r5
            r5 = r4
        L46:
            if (r0 == 0) goto L4b
            r0.disconnect()
        L4b:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.drt.a(java.lang.String):int");
    }

    private static dqr d(String str, String str2, VoiceConfigCallback voiceConfigCallback) {
        if (voiceConfigCallback == null) {
            LogUtil.b("HiH_VoiceConfigManager", "handleParams voiceConfigCallback is null");
            return null;
        }
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            c("", "handleParams input params contains empty value", voiceConfigCallback);
            return null;
        }
        String d = HEXUtils.d(str2);
        LogUtil.a("HiH_VoiceConfigManager", "handleParams configData = ", d);
        try {
            dqr dqrVar = (dqr) HiJsonUtil.e(d, dqr.class);
            if (dqrVar != null) {
                return dqrVar;
            }
            c(str, "handleParams QueryParams is null", voiceConfigCallback);
            return null;
        } catch (JsonSyntaxException e) {
            c(str, "handleParams Exception: " + e.getMessage(), voiceConfigCallback);
            return null;
        }
    }
}
