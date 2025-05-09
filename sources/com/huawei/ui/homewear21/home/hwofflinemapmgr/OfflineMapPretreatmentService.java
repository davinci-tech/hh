package com.huawei.ui.homewear21.home.hwofflinemapmgr;

import android.content.Context;
import android.net.Uri;
import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.facade.service.PretreatmentService;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.utils.EnvironmentHelper;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.maps.offlinedata.health.init.OfflineMapInitManager;
import com.huawei.maps.offlinedata.health.init.OfflineMapInitOptions;
import com.huawei.maps.offlinedata.health.init.OnCreateResultListener;
import com.huawei.operation.h5pro.H5proUtil;
import com.huawei.ui.homewear21.home.hwofflinemapmgr.OfflineMapPretreatmentService;
import defpackage.cun;
import defpackage.jdx;
import defpackage.oat;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
public class OfflineMapPretreatmentService implements PretreatmentService {
    @Override // com.huawei.haf.router.facade.template.ServiceProvider
    public void init(Context context) {
    }

    @Override // com.huawei.haf.router.facade.service.PretreatmentService
    public boolean onPretreatment(Context context, Guidepost guidepost) {
        LogUtil.a("Track_OfflineMapPretreatmentService", "enter offlineMap pretreatment");
        Uri zN_ = guidepost.zN_();
        if (zN_ != null) {
            LogUtil.a("Track_OfflineMapPretreatmentService", "uri:", zN_.getQuery(), " getPageType ", Integer.valueOf(dmL_(zN_)));
            DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Track_OfflineMapPretreatmentService");
            if (deviceInfo == null) {
                return false;
            }
            if (deviceInfo.getProductType() == 99 && CommonUtil.bv()) {
                return false;
            }
            int dmL_ = dmL_(zN_);
            if (dmL_ == 1) {
                c();
            } else if (dmL_ == 2) {
                DeviceInfo deviceInfo2 = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Track_OfflineMapPretreatmentService");
                if (deviceInfo2 != null) {
                    oat.c().d(BaseApplication.getContext(), deviceInfo2.getDeviceUdid());
                }
                e();
            } else {
                sqo.ad("PretreatmentService:unknown deeplink");
            }
            return false;
        }
        LogUtil.b("Track_OfflineMapPretreatmentService", "uri is null.");
        return false;
    }

    private void e() {
        final DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Track_OfflineMapPretreatmentService");
        boolean z = deviceInfo != null;
        ReleaseLogUtil.e("Track_OfflineMapPretreatmentService", "startMapPage enter isConnected ", Boolean.valueOf(z));
        if (z) {
            jdx.b(new Runnable() { // from class: pek
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineMapPretreatmentService.this.c(deviceInfo);
                }
            });
        } else {
            sqo.ad("startMapPage error because of Connect status");
        }
    }

    public /* synthetic */ void c(DeviceInfo deviceInfo) {
        OnCreateResultListener onCreateResultListener = new OnCreateResultListener() { // from class: com.huawei.ui.homewear21.home.hwofflinemapmgr.OfflineMapPretreatmentService.3
            @Override // com.huawei.maps.offlinedata.health.init.OnCreateResultListener
            public void onCreateResult(boolean z) {
                LogUtil.a("Track_OfflineMapPretreatmentService", "jumpToOfflineMap onCreateResultListener result: ", Boolean.valueOf(z));
            }
        };
        OfflineMapInitOptions offlineMapInitOptions = new OfflineMapInitOptions();
        offlineMapInitOptions.setContext(com.huawei.haf.application.BaseApplication.vZ_());
        offlineMapInitOptions.setActivity(com.huawei.haf.application.BaseApplication.wa_());
        offlineMapInitOptions.setDeviceName(deviceInfo.getDeviceName());
        offlineMapInitOptions.setUniqueDevice(deviceInfo.getDeviceUdid());
        H5proUtil.initH5pro();
        offlineMapInitOptions.setH5proUrl(EnvironmentHelper.getInstance().getUrl());
        boolean createOfflineDataMap = OfflineMapInitManager.getInstance().createOfflineDataMap(offlineMapInitOptions, onCreateResultListener);
        if (!createOfflineDataMap) {
            sqo.ad("open map page error");
        }
        ReleaseLogUtil.e("Track_OfflineMapPretreatmentService", "startMapPage enter ", Boolean.valueOf(createOfflineDataMap));
    }

    public static int dmL_(Uri uri) {
        try {
            int h = CommonUtils.h(uri.getQueryParameter(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE));
            LogUtil.a("Track_OfflineMapPretreatmentService", "type:", Integer.valueOf(h));
            return h;
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            LogUtil.b("Track_OfflineMapPretreatmentService", "get type IllegalArgumentException:", com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE, LogAnonymous.b(e));
            return -1;
        }
    }

    private void c() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homewear21.home.hwofflinemapmgr.OfflineMapPretreatmentService.5
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("Track_OfflineMapPretreatmentService", "get OfflineMapPretreatmentService instance in new thread");
                DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Track_OfflineMapPretreatmentService");
                boolean z = deviceInfo != null;
                int i = 2;
                while (!z && i > 0) {
                    try {
                        Thread.sleep(2000L);
                        deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Track_OfflineMapPretreatmentService");
                        z = deviceInfo != null;
                        i--;
                    } catch (InterruptedException unused) {
                        LogUtil.b("Track_OfflineMapPretreatmentService", "thread sleep error");
                    }
                }
                if (!z) {
                    LogUtil.h("register fail", new Object[0]);
                    sqo.ad("ping error error because of Connect status");
                } else {
                    oat.c().d(BaseApplication.getContext(), deviceInfo.getDeviceUdid());
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException unused2) {
                        LogUtil.b("Track_OfflineMapPretreatmentService", "thread sleep error");
                    }
                    oat.c().d();
                }
            }
        });
    }
}
