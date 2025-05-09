package defpackage;

import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.health.devicemgr.api.outofprocess.SendSampleCommandApi;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.deviceside.CustomConfigWatchFaceService;
import health.compact.a.ProcessUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class cuk extends AppBundlePluginProxy<SendSampleCommandApi> implements SendSampleCommandApi {
    private static int b;
    private static volatile cuk d;
    private SendSampleCommandApi c;

    private cuk() {
        super("SendSampleCommandProxy", "SendSampleCommandProxy", "com.huawei.hwdevice.outofprocess.impl.SendSampleCommandImpl");
        this.c = createPluginApi();
    }

    public static cuk b() {
        cuk cukVar;
        if (d == null) {
            synchronized (cuk.class) {
                if (d == null) {
                    d = new cuk();
                }
                cukVar = d;
            }
            return cukVar;
        }
        return d;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return d != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void initialize(SendSampleCommandApi sendSampleCommandApi) {
        this.c = sendSampleCommandApi;
    }

    @Override // com.huawei.health.devicemgr.api.outofprocess.SendSampleCommandApi
    public boolean sendSampleConfigCommand(DeviceInfo deviceInfo, cvq cvqVar, String str) {
        SendSampleCommandApi sendSampleCommandApi = this.c;
        if (sendSampleCommandApi != null) {
            return sendSampleCommandApi.sendSampleConfigCommand(deviceInfo, cvqVar, str);
        }
        return false;
    }

    @Override // com.huawei.health.devicemgr.api.outofprocess.SendSampleCommandApi
    public boolean sendSampleEventCommand(DeviceInfo deviceInfo, cvp cvpVar, String str) {
        SendSampleCommandApi sendSampleCommandApi = this.c;
        if (sendSampleCommandApi != null) {
            return sendSampleCommandApi.sendSampleEventCommand(deviceInfo, cvpVar, str);
        }
        return false;
    }

    @Override // com.huawei.health.devicemgr.api.outofprocess.SendSampleCommandApi
    public boolean sendSamplePointCommand(DeviceInfo deviceInfo, cvu cvuVar, String str) {
        SendSampleCommandApi sendSampleCommandApi = this.c;
        if (sendSampleCommandApi != null) {
            return sendSampleCommandApi.sendSamplePointCommand(deviceInfo, cvuVar, str);
        }
        return false;
    }

    @Override // com.huawei.health.devicemgr.api.outofprocess.SendSampleCommandApi
    public boolean sendDictionaryPointInfoCommand(DeviceInfo deviceInfo, cvi cviVar, String str) {
        SendSampleCommandApi sendSampleCommandApi = this.c;
        if (sendSampleCommandApi != null) {
            return sendSampleCommandApi.sendDictionaryPointInfoCommand(deviceInfo, cviVar, str);
        }
        return false;
    }

    @Override // com.huawei.health.devicemgr.api.outofprocess.SendSampleCommandApi
    public void registerDeviceSampleListener(String str, DataReceiveCallback dataReceiveCallback) {
        b(str);
        SendSampleCommandApi sendSampleCommandApi = this.c;
        if (sendSampleCommandApi != null) {
            sendSampleCommandApi.registerDeviceSampleListener(str, dataReceiveCallback);
        }
    }

    @Override // com.huawei.health.devicemgr.api.outofprocess.SendSampleCommandApi
    public void unRegisterDeviceSampleListener(String str) {
        SendSampleCommandApi sendSampleCommandApi = this.c;
        if (sendSampleCommandApi != null) {
            sendSampleCommandApi.unRegisterDeviceSampleListener(str);
        }
    }

    private static void b(String str) {
        if (b < 5 && !ProcessUtil.d() && CustomConfigWatchFaceService.CUSTOM_CONFIG_APP_PACKAGE_NAME.equals(str)) {
            String d2 = DfxUtils.d(Thread.currentThread(), null);
            ReleaseLogUtil.e("R_SendSampleCommandProxy", "callPidError stackTraceInfo= ", d2);
            sqo.an("callPidError stackTraceInfo: " + d2);
            b = b + 1;
        }
    }
}
