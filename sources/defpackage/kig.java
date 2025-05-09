package defpackage;

import android.content.Intent;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwUpdateService;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import java.util.concurrent.Semaphore;

/* loaded from: classes5.dex */
public class kig {
    private Semaphore b = new Semaphore(1);
    private ThreadPoolManager e = ThreadPoolManager.e(1, 1, "OtaHandleDeviceRequest");

    public void b(DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            LogUtil.h("OtaHandleDeviceRequest", "handleOtaReceivedData dataInfos is invalid");
            return;
        }
        if (bArr[0] != 9) {
            LogUtil.c("OtaHandleDeviceRequest", "handleOtaReceivedData service id is not 9");
            return;
        }
        byte b = bArr[1];
        if (b == 15) {
            d(deviceInfo, bArr);
            return;
        }
        if (b == 16) {
            a(deviceInfo, bArr);
        } else if (b == 17) {
            e(deviceInfo, bArr);
        } else {
            LogUtil.c("OtaHandleDeviceRequest", "handleOtaReceivedData other command");
        }
    }

    private void d(final DeviceInfo deviceInfo, final byte[] bArr) {
        jrb.b("OtaHandleDeviceRequest", 9, 15);
        this.e.execute(new Runnable() { // from class: kig.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    LogUtil.a("OtaHandleDeviceRequest", "semaphore, semaphore.availablePermits().", Integer.valueOf(kig.this.b.availablePermits()));
                    kig.this.b.acquire();
                    jrj.d(OpAnalyticsConstants.H5_LOADING_DELAY, "user-checkFile");
                    jrs.d(BaseApplication.getContext());
                    if (!kid.e(deviceInfo, bArr)) {
                        LogUtil.h("OtaHandleDeviceRequest", "parserDeviceRequestCheck isRequestNewVersionCheck is false");
                        jrs.b();
                        jrj.b("user-checkFile");
                        kig.this.b.release();
                        return;
                    }
                    Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) HwUpdateService.class);
                    intent.putExtra("extra_band_imei", deviceInfo.getDeviceIdentify());
                    intent.putExtra("key_device_request_check_new_version", true);
                    intent.setAction("action_band_manual_update_new_version");
                    BaseApplication.getContext().startService(intent);
                    Thread.sleep(PreConnectManager.CONNECT_INTERNAL);
                    jrs.b();
                    jrj.b("user-checkFile");
                    kig.this.b.release();
                } catch (cwg | IllegalStateException | InterruptedException | SecurityException e) {
                    LogUtil.b("OtaHandleDeviceRequest", "parserDeviceRequestCheck Exception", ExceptionUtils.d(e));
                }
            }
        });
    }

    private void a(DeviceInfo deviceInfo, byte[] bArr) {
        jrb.b("OtaHandleDeviceRequest", 9, 16);
        try {
            int d = kid.d(deviceInfo, bArr);
            Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) HwUpdateService.class);
            intent.putExtra("extra_band_imei", deviceInfo.getDeviceIdentify());
            intent.putExtra("key_auto_install_status", d);
            intent.setAction("action_device_request_download_ota");
            BaseApplication.getContext().startService(intent);
        } catch (cwg | IllegalStateException | SecurityException e) {
            LogUtil.b("OtaHandleDeviceRequest", "deviceRequestDownloadOta Exception", ExceptionUtils.d(e));
        }
    }

    private void e(DeviceInfo deviceInfo, byte[] bArr) {
        jrb.b("OtaHandleDeviceRequest", 9, 17);
        try {
            if (kid.a(deviceInfo, bArr)) {
                Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) HwUpdateService.class);
                intent.putExtra("extra_band_imei", deviceInfo.getDeviceIdentify());
                intent.putExtra("key_is_user_agree_download", true);
                intent.setAction("action_device_request_download_ota");
                BaseApplication.getContext().startService(intent);
            } else {
                LogUtil.h("OtaHandleDeviceRequest", "userConfirmIsDownload user not agree download");
            }
        } catch (cwg | IllegalStateException | SecurityException e) {
            LogUtil.b("OtaHandleDeviceRequest", "userConfirmIsDownload Exception", ExceptionUtils.d(e));
        }
    }
}
