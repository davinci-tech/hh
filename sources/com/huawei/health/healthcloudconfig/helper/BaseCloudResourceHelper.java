package com.huawei.health.healthcloudconfig.helper;

import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthcloudconfig.helper.BaseCloudResourceHelper;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.dqh;
import defpackage.dqi;
import defpackage.dql;
import defpackage.dqs;
import defpackage.dqw;
import defpackage.drd;
import defpackage.drm;
import health.compact.a.CommonUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class BaseCloudResourceHelper {
    private static final String RELEASE_TAG = "R_BaseCloudResourceHelper";
    private static final String TAG = "BaseCloudResourceHelper";
    private String mCloudResourceConfigName;
    private volatile boolean mIsUpdating;
    private int mUpdateDayInterval;

    protected abstract List<dqs> getLocalFileData(File[] fileArr);

    protected abstract void handleResponse(dqi dqiVar, String str, List<String> list, CloudResourceCallback cloudResourceCallback);

    protected BaseCloudResourceHelper(String str) {
        this.mUpdateDayInterval = 0;
        this.mIsUpdating = false;
        this.mCloudResourceConfigName = str;
    }

    public BaseCloudResourceHelper(String str, int i) {
        this.mUpdateDayInterval = 0;
        this.mIsUpdating = false;
        this.mCloudResourceConfigName = str;
        this.mUpdateDayInterval = i;
    }

    public void checkUpdate(final String str, final Map<String, String> map, final CheckResourceUpdateCallback checkResourceUpdateCallback) {
        LogUtil.a(TAG, "isUpdating = ", Boolean.valueOf(this.mIsUpdating));
        if (this.mIsUpdating) {
            onFailureForUpdate(TAG, "is updating, return", checkResourceUpdateCallback);
            this.mIsUpdating = true;
            return;
        }
        this.mIsUpdating = true;
        if (TextUtils.isEmpty(str) || map.isEmpty()) {
            onFailureForUpdate(TAG, "checkUpdate necessary params is null", checkResourceUpdateCallback);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: dqt
                @Override // java.lang.Runnable
                public final void run() {
                    BaseCloudResourceHelper.this.m420x6a74118e(str, map, checkResourceUpdateCallback);
                }
            });
        }
    }

    /* renamed from: lambda$checkUpdate$0$com-huawei-health-healthcloudconfig-helper-BaseCloudResourceHelper, reason: not valid java name */
    public /* synthetic */ void m420x6a74118e(String str, Map map, CheckResourceUpdateCallback checkResourceUpdateCallback) {
        dqi c = drd.c(TAG, new dql(str, map), this.mUpdateDayInterval, str);
        if (c != null) {
            List<dqh> e = c.e();
            if (e.size() == 0) {
                onFailureForUpdate(TAG, "getResponse from cloud fileList size = 0", checkResourceUpdateCallback);
                return;
            }
            ArrayList arrayList = new ArrayList();
            List<dqs> localFile = getLocalFile(str);
            if (localFile == null || localFile.isEmpty()) {
                LogUtil.a(TAG, "download first time, skip check");
                if (checkResourceUpdateCallback != null) {
                    checkResourceUpdateCallback.onSuccess(TAG, true);
                    return;
                }
                return;
            }
            boolean checkUpdateStatus = checkUpdateStatus(localFile, e, arrayList);
            if (checkResourceUpdateCallback != null) {
                checkResourceUpdateCallback.onSuccess(TAG, checkUpdateStatus);
                return;
            }
            return;
        }
        ReleaseLogUtil.d(RELEASE_TAG, "response is null, skip download");
        this.mIsUpdating = false;
    }

    private List<dqs> getLocalFile(String str) {
        File[] listFiles = new File(drd.d(str, (String) null, (String) null)).listFiles();
        if (listFiles == null) {
            ReleaseLogUtil.e(RELEASE_TAG, "download first time, skip check");
            return Collections.emptyList();
        }
        return getLocalFileData(listFiles);
    }

    private boolean checkUpdateStatus(List<dqs> list, List<dqh> list2, List<drm> list3) {
        ArrayList arrayList = new ArrayList();
        Iterator<dqs> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().c());
        }
        LinkedList<dqw> linkedList = new LinkedList();
        boolean z = false;
        for (dqh dqhVar : list2) {
            dqw dqwVar = new dqw(dqhVar);
            String a2 = dqhVar.a();
            int h = CommonUtils.h(dqhVar.c());
            if (!arrayList.contains(a2)) {
                dqwVar.a(true);
                linkedList.add(dqwVar);
                z = true;
            } else {
                for (dqs dqsVar : list) {
                    String c = dqsVar.c();
                    int e = dqsVar.e();
                    if (TextUtils.isEmpty(c) || TextUtils.isEmpty(a2)) {
                        ReleaseLogUtil.d(RELEASE_TAG, "fileName or fileIdFromCloud is empty");
                    } else {
                        if (c.equals(a2) && h > e) {
                            dqwVar.a(true);
                            z = true;
                        }
                        linkedList.add(dqwVar);
                    }
                }
            }
        }
        this.mIsUpdating = z;
        for (dqw dqwVar2 : linkedList) {
            if (dqwVar2.b()) {
                dqh a3 = dqwVar2.a();
                drm drmVar = new drm();
                drmVar.d(a3.a());
                drmVar.e(a3.d());
                drmVar.e(CommonUtils.h(a3.c()));
                drmVar.c(getFileSizeFromNet(a3.d()));
                list3.add(drmVar);
            }
        }
        LogUtil.a(TAG, "cloudResourceConfigList = ", list3);
        return z;
    }

    public void downloadFiles(final String str, final List<String> list, Map<String, String> map, int i, final CloudResourceCallback cloudResourceCallback) {
        if (TextUtils.isEmpty(str) || list.isEmpty() || map.isEmpty()) {
            onFailureForDownload(RELEASE_TAG, "downloadFiles necessary params is null", cloudResourceCallback);
        } else {
            drd.a(new dql(str, map), TAG, list, i, new DownloadCallback<dqi>() { // from class: com.huawei.health.healthcloudconfig.helper.BaseCloudResourceHelper.5
                @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onFinish(dqi dqiVar) {
                    ReleaseLogUtil.e(BaseCloudResourceHelper.RELEASE_TAG, "downloadFiles onFinish, data is: ", dqiVar);
                    BaseCloudResourceHelper.this.handleResponse(dqiVar, str, list, cloudResourceCallback);
                    BaseCloudResourceHelper.this.mIsUpdating = false;
                }

                @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
                public void onProgress(long j, long j2, boolean z, String str2) {
                    cloudResourceCallback.onProgress(j, j2, z, str2);
                }

                @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
                public void onFail(int i2, Throwable th) {
                    BaseCloudResourceHelper.this.onFailureForDownload(BaseCloudResourceHelper.RELEASE_TAG, "errCode = " + i2 + ", errInfo = " + th.toString(), cloudResourceCallback);
                }
            });
        }
    }

    public void onFailureForUpdate(String str, String str2, CheckResourceUpdateCallback checkResourceUpdateCallback) {
        ReleaseLogUtil.c(RELEASE_TAG, "tag = ", str, " errorInfo = ", str2);
        if (checkResourceUpdateCallback != null) {
            checkResourceUpdateCallback.onFailure(str, str2);
        }
        this.mIsUpdating = false;
    }

    protected void onFailureForDownload(String str, String str2, CloudResourceCallback cloudResourceCallback) {
        ReleaseLogUtil.c(RELEASE_TAG, "tag = ", str, " errorInfo = ", str2);
        if (cloudResourceCallback != null) {
            cloudResourceCallback.onFailure(str, str2);
        }
        this.mIsUpdating = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int getFileSizeFromNet(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 0
            java.net.URL r1 = new java.net.URL     // Catch: java.lang.Throwable -> L21 java.io.IOException -> L23
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L21 java.io.IOException -> L23
            java.net.URLConnection r6 = r1.openConnection()     // Catch: java.lang.Throwable -> L21 java.io.IOException -> L23
            java.net.URLConnection r6 = com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation.openConnection(r6)     // Catch: java.lang.Throwable -> L21 java.io.IOException -> L23
            java.net.HttpURLConnection r6 = (java.net.HttpURLConnection) r6     // Catch: java.lang.Throwable -> L21 java.io.IOException -> L23
            int r0 = r6.getContentLength()     // Catch: java.lang.Throwable -> L1a java.io.IOException -> L1c
            if (r6 == 0) goto L3e
            r6.disconnect()
            goto L3e
        L1a:
            r0 = move-exception
            goto L43
        L1c:
            r0 = move-exception
            r4 = r0
            r0 = r6
            r6 = r4
            goto L24
        L21:
            r6 = move-exception
            goto L46
        L23:
            r6 = move-exception
        L24:
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L3f
            java.lang.String r2 = "getFileSizeFromNet IOException = "
            r3 = 0
            r1[r3] = r2     // Catch: java.lang.Throwable -> L3f
            java.lang.String r6 = r6.getMessage()     // Catch: java.lang.Throwable -> L3f
            r2 = 1
            r1[r2] = r6     // Catch: java.lang.Throwable -> L3f
            java.lang.String r6 = "R_BaseCloudResourceHelper"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r6, r1)     // Catch: java.lang.Throwable -> L3f
            if (r0 == 0) goto L3d
            r0.disconnect()
        L3d:
            r0 = r3
        L3e:
            return r0
        L3f:
            r6 = move-exception
            r4 = r0
            r0 = r6
            r6 = r4
        L43:
            r4 = r0
            r0 = r6
            r6 = r4
        L46:
            if (r0 == 0) goto L4b
            r0.disconnect()
        L4b:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.healthcloudconfig.helper.BaseCloudResourceHelper.getFileSizeFromNet(java.lang.String):int");
    }
}
