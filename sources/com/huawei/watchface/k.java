package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import com.huawei.watchface.api.HwWatchFaceManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.DownloadQueryBean;
import com.huawei.watchface.mvp.model.datatype.InstallWatchFaceBean;
import com.huawei.watchface.mvp.model.datatype.WatchFaceDownloadQueryFreeResp;
import com.huawei.watchface.mvp.model.datatype.WatchFaceDownloadQueryResp;
import com.huawei.watchface.mvp.model.datatype.WatchResourcesInfo;
import com.huawei.watchface.utils.BackgroundTaskUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.callback.OperateWatchFaceCallback;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class k {

    /* renamed from: a, reason: collision with root package name */
    private static k f11052a;
    private OperateWatchFaceCallback b;
    private final ConcurrentHashMap<String, LinkedHashMap<String, String>> d = new ConcurrentHashMap<>();
    private HwWatchFaceManager c = HwWatchFaceManager.getInstance(Environment.getApplicationContext());

    public static k a() {
        if (f11052a == null) {
            synchronized (k.class) {
                if (f11052a == null) {
                    f11052a = new k();
                }
            }
        }
        return f11052a;
    }

    public void b() {
        c();
    }

    private static void c() {
        synchronized (k.class) {
            if (f11052a != null) {
                HwLog.i("HwDownloadUrlManager", "destroyInstance() enter.");
                f11052a = null;
            }
        }
    }

    private k() {
    }

    public void a(OperateWatchFaceCallback operateWatchFaceCallback) {
        this.b = operateWatchFaceCallback;
    }

    public String a(final DownloadQueryBean downloadQueryBean) {
        HwLog.i("HwDownloadUrlManager", "downloadInstallWatchFace enter");
        if (downloadQueryBean == null) {
            return "1";
        }
        BackgroundTaskUtils.submit(new Runnable() { // from class: com.huawei.watchface.k.1
            @Override // java.lang.Runnable
            public void run() {
                final InstallWatchFaceBean installWatchFaceBean = new InstallWatchFaceBean();
                installWatchFaceBean.setInstallationType(2);
                int a2 = k.this.a(downloadQueryBean, installWatchFaceBean);
                if (a2 != 1) {
                    HwLog.e("HwDownloadUrlManager", "downloadInstallWatchFace -- return a non-zero value, resultCode==" + a2);
                    return;
                }
                BackgroundTaskUtils.postInMainThread(new Runnable() { // from class: com.huawei.watchface.k.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        HwLog.i("HwDownloadUrlManager", "downloadInstallWatchFace -- start install WatchFace");
                        k.this.c.installWatchFace(installWatchFaceBean);
                    }
                });
            }
        });
        return "0";
    }

    public int a(DownloadQueryBean downloadQueryBean, InstallWatchFaceBean installWatchFaceBean) {
        int i;
        String productId = downloadQueryBean.getProductId();
        String hitopId = downloadQueryBean.getHitopId();
        String dialType = downloadQueryBean.getDialType();
        String version = downloadQueryBean.getVersion();
        boolean isVipFreeWatchFace = downloadQueryBean.isVipFreeWatchFace();
        int type = downloadQueryBean.getType();
        if ("1".equals(dialType)) {
            i = a(productId, hitopId, type, installWatchFaceBean);
        } else if ("2".equals(dialType)) {
            i = a(productId, hitopId, version, type, installWatchFaceBean, isVipFreeWatchFace);
        } else {
            HwLog.e("HwDownloadUrlManager", "requestPaidAndFreeResourceInfo -- dialType==" + dialType);
            i = 0;
        }
        a(i, hitopId);
        String fileUrl = installWatchFaceBean.getFileUrl();
        String hashCode = installWatchFaceBean.getHashCode();
        installWatchFaceBean.setResourceType(dialType);
        installWatchFaceBean.setWatchFaceHiTopId(hitopId);
        installWatchFaceBean.setVersion(version);
        installWatchFaceBean.setFileSize(downloadQueryBean.getFileSize());
        installWatchFaceBean.setWatchScreen(downloadQueryBean.getScreen());
        installWatchFaceBean.setProductId(productId);
        installWatchFaceBean.setUpdate(downloadQueryBean.isUpdate());
        installWatchFaceBean.setZip(downloadQueryBean.isZip());
        installWatchFaceBean.setOneClick(downloadQueryBean.isOneClick());
        installWatchFaceBean.setTitle(downloadQueryBean.getTitle());
        installWatchFaceBean.setCnTitle(downloadQueryBean.getCnTitle());
        if (i == 1) {
            a(hitopId, version, fileUrl, hashCode);
        }
        return i;
    }

    private int a(String str, String str2, String str3, int i, InstallWatchFaceBean installWatchFaceBean, boolean z) {
        HwLog.i("HwDownloadUrlManager", "requestPaidUrlInfo enter");
        if (!CommonUtils.f()) {
            HwLog.e("HwDownloadUrlManager", "requestPaidUrlInfo -- productId is empty or net is error");
            return -5;
        }
        if (installWatchFaceBean == null) {
            HwLog.e("HwDownloadUrlManager", "requestPaidUrlInfo -- installBean is null");
            return -4;
        }
        bw bwVar = new bw(str, str2, i);
        WatchFaceDownloadQueryResp c = bwVar.c(bwVar.c());
        Integer a2 = a(c);
        if (a2 != null) {
            return a2.intValue();
        }
        try {
            g.a(Environment.getApplicationContext(), c.getLicenseResp());
            installWatchFaceBean.setFileUrl(c.getDownUrl());
            installWatchFaceBean.setHashCode(c.getHashCode());
            if (!z || !CommonUtils.B()) {
                return 1;
            }
            a(str2, str3, installWatchFaceBean, c);
            return 1;
        } catch (HwDrmException e) {
            HwLog.e("HwDownloadUrlManager", "requestPaidUrlInfo -- HwDrmException：" + HwLog.printException((Exception) e));
            return -6;
        }
    }

    private void a(String str, String str2, InstallWatchFaceBean installWatchFaceBean, WatchFaceDownloadQueryResp watchFaceDownloadQueryResp) {
        String isOrdered = watchFaceDownloadQueryResp.getIsOrdered();
        if (!TextUtils.isEmpty(isOrdered) && isOrdered.equals(w.NOT_ORDERED.getValue())) {
            WatchResourcesInfo watchResourcesInfo = new WatchResourcesInfo();
            watchResourcesInfo.setWatchInfoId(str);
            watchResourcesInfo.setWatchInfoVersion(str2);
            watchResourcesInfo.setVipFreeWatchFace(true);
            this.c.addVipFreeWatchFace(str, watchResourcesInfo);
        }
        installWatchFaceBean.setVipFreeWatchFace(isOrdered.equals(w.NOT_ORDERED.getValue()));
        HwLog.i("HwDownloadUrlManager", "requestPaidUrlInfo -- is paid vip free: " + installWatchFaceBean.isVipFreeWatchFace());
        if (TextUtils.isEmpty(isOrdered) || !isOrdered.equals(w.NOT_ORDERED.getValue())) {
            return;
        }
        ac.a().b(str, str2);
    }

    private Integer a(WatchFaceDownloadQueryResp watchFaceDownloadQueryResp) {
        if (watchFaceDownloadQueryResp == null) {
            HwLog.e("HwDownloadUrlManager", "requestPaidUrlInfo -- watchFaceDownloadQueryResp is null");
            return -3;
        }
        int intValue = watchFaceDownloadQueryResp.getResultcode().intValue();
        HwLog.e("HwDownloadUrlManager", "requestPaidUrlInfo -- watchFaceDownloadQueryResp resultCode==" + intValue);
        if (WatchFaceHttpUtil.a(intValue)) {
            return Integer.valueOf(intValue);
        }
        if (intValue != 0) {
            HwLog.e("HwDownloadUrlManager", "requestPaidUrlInfo -- watchFaceDownloadQueryResp is not success, ret==" + intValue);
            return -1;
        }
        if (!TextUtils.isEmpty(watchFaceDownloadQueryResp.getDownUrl())) {
            return null;
        }
        HwLog.e("HwDownloadUrlManager", "requestPaidUrlInfo -- downUrl is empty");
        return -2;
    }

    private int a(String str, String str2, int i, InstallWatchFaceBean installWatchFaceBean) {
        HwLog.i("HwDownloadUrlManager", "requestFreeUrlInfo enter");
        if (!CommonUtils.f()) {
            HwLog.e("HwDownloadUrlManager", "requestFreeUrlInfo -- productId is empty or net is error");
            return -5;
        }
        if (installWatchFaceBean == null) {
            HwLog.e("HwDownloadUrlManager", "requestFreeUrlInfo -- installBean is null");
            return -4;
        }
        bv bvVar = new bv(str, str2, i);
        WatchFaceDownloadQueryFreeResp c = bvVar.c(bvVar.c());
        Integer a2 = a(c);
        if (a2 != null) {
            return a2.intValue();
        }
        try {
            g.a(Environment.getApplicationContext(), c.getLicenseResp());
            installWatchFaceBean.setFileUrl(c.getDownUrl());
            installWatchFaceBean.setHashCode(c.getHashCode());
            return 1;
        } catch (HwDrmException e) {
            HwLog.e("HwDownloadUrlManager", "requestFreeUrlInfo -- HwDrmException：" + HwLog.printException((Exception) e));
            return -6;
        }
    }

    private Integer a(WatchFaceDownloadQueryFreeResp watchFaceDownloadQueryFreeResp) {
        if (watchFaceDownloadQueryFreeResp == null) {
            HwLog.e("HwDownloadUrlManager", "requestFreeUrlInfo -- watchFaceDownloadQueryResp is null");
            return -3;
        }
        int intValue = watchFaceDownloadQueryFreeResp.getResultcode().intValue();
        if (WatchFaceHttpUtil.a(intValue)) {
            return Integer.valueOf(intValue);
        }
        if (intValue != 0) {
            HwLog.e("HwDownloadUrlManager", "requestFreeUrlInfo -- WatchFaceDownloadQueryFreeResp is not success, ret==" + intValue);
            return -1;
        }
        if (!TextUtils.isEmpty(watchFaceDownloadQueryFreeResp.getDownUrl())) {
            return null;
        }
        HwLog.e("HwDownloadUrlManager", "requestFreeUrlInfo -- WatchFaceDownloadQueryFreeResp downUrl is empty");
        return -2;
    }

    private void a(String str, String str2, String str3, String str4) {
        String str5 = str + "_" + str2;
        HwLog.i("HwDownloadUrlManager", "putDownUrlHashcodeToMap -- hitopIdKey :" + str5);
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(WatchFaceConstant.HASHCODE, str4);
        linkedHashMap.put(RecommendConstants.DOWNLOAD_URL, str3);
        this.d.put(str5, linkedHashMap);
    }

    public HashMap<String, String> a(String str, String str2) {
        String str3 = str + "_" + str2;
        HwLog.i("HwDownloadUrlManager", "getInstllDownUrlHashcode -- hitopIdKey :" + str3);
        return this.d.get(str3);
    }

    public void b(String str, String str2) {
        HwWatchFaceManager hwWatchFaceManager;
        HwLog.i("HwDownloadUrlManager", "--enter clearCacheWatchFaceInfo--");
        if (this.d.containsKey(str + "_" + str2) || (hwWatchFaceManager = this.c) == null || hwWatchFaceManager.isVipFreeWatchFaceContain(str)) {
            String str3 = str + "_" + str2;
            HwLog.i("HwDownloadUrlManager", "enter clearCacheWatchFaceInfo mDownUrlHashCodeMap--:" + str3);
            this.d.remove(str3);
        }
    }

    private void a(int i, String str) {
        OperateWatchFaceCallback operateWatchFaceCallback = this.b;
        if (operateWatchFaceCallback == null) {
            return;
        }
        operateWatchFaceCallback.transmitDownloadUrl(i, str);
    }
}
