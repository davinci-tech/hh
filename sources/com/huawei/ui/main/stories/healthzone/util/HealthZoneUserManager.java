package com.huawei.ui.main.stories.healthzone.util;

import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.pluginhealthzone.FamilyHealthZoneApi;
import com.huawei.health.pluginhealthzone.UserInfoCallback;
import com.huawei.hwcloudmodel.contentcenter.UploadProgressListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.healthzone.util.HealthZoneUserManager;
import defpackage.exh;
import defpackage.izz;
import defpackage.jaj;
import health.compact.a.Services;
import java.util.List;

/* loaded from: classes7.dex */
public class HealthZoneUserManager {
    private static volatile HealthZoneUserManager d;
    private static final Object e = new Object();

    /* loaded from: classes9.dex */
    public interface UploadDataFilesCallback {
        void errorCallback(int i);

        void infoCallback(List<jaj> list);
    }

    private HealthZoneUserManager() {
    }

    public static HealthZoneUserManager d() {
        HealthZoneUserManager healthZoneUserManager;
        synchronized (HealthZoneUserManager.class) {
            if (d == null) {
                synchronized (e) {
                    if (d == null) {
                        d = new HealthZoneUserManager();
                    }
                }
            }
            healthZoneUserManager = d;
        }
        return healthZoneUserManager;
    }

    public void e(int i, String str, UserInfoCallback<exh.b> userInfoCallback) {
        ((FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class)).requestFindUserInfo(i, str, userInfoCallback);
    }

    private void a(List<jaj> list, UploadDataFilesCallback uploadDataFilesCallback) {
        if (uploadDataFilesCallback != null) {
            uploadDataFilesCallback.infoCallback(list);
        }
    }

    public void a(final int i, final List<String> list, final UploadDataFilesCallback uploadDataFilesCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: rbn
            @Override // java.lang.Runnable
            public final void run() {
                HealthZoneUserManager.this.e(list, i, uploadDataFilesCallback);
            }
        });
    }

    public /* synthetic */ void e(List list, int i, final UploadDataFilesCallback uploadDataFilesCallback) {
        a(new izz().c(list, 1, i, new UploadProgressListener() { // from class: com.huawei.ui.main.stories.healthzone.util.HealthZoneUserManager.2
            @Override // com.huawei.hwcloudmodel.contentcenter.UploadProgressListener
            public void onProgress(float f) {
                LogUtil.c("HealthZoneUserManager", "progress:", Float.valueOf(f));
            }

            @Override // com.huawei.hwcloudmodel.contentcenter.UploadProgressListener
            public void onError(int i2, String str) {
                uploadDataFilesCallback.errorCallback(i2);
            }
        }), uploadDataFilesCallback);
    }
}
