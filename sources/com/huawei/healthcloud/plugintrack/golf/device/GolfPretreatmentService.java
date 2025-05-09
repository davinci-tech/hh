package com.huawei.healthcloud.plugintrack.golf.device;

import android.content.Context;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cun;

/* loaded from: classes.dex */
public class GolfPretreatmentService implements PretreatmentService {
    private static final int MAX_RETRY_COUNT = 2;
    private static final String TAG = "GolfPretreatmentService";
    private static final int WAITING_SLOT = 2000;

    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        LogUtil.c(TAG, "enter golf pretreatment");
        initDevice();
        return false;
    }

    private void initDevice() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.golf.device.GolfPretreatmentService.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a(GolfPretreatmentService.TAG, "get GolfDeviceProxy instance in new thread");
                boolean z = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, GolfPretreatmentService.TAG) != null;
                int i = 2;
                while (!z && i > 0) {
                    try {
                        Thread.sleep(2000L);
                        z = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, GolfPretreatmentService.TAG) != null;
                        i--;
                    } catch (InterruptedException unused) {
                        LogUtil.b(GolfPretreatmentService.TAG, "thread sleep error");
                    }
                }
                if (!z) {
                    LogUtil.h("register fail", new Object[0]);
                    return;
                }
                GolfDeviceProxy.getInstance();
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException unused2) {
                    LogUtil.b(GolfPretreatmentService.TAG, "thread sleep error");
                }
                GolfDeviceProxy.getInstance().sendDevicePullSuccessMsg();
            }
        });
    }
}
