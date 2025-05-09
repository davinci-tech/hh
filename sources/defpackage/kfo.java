package defpackage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.healthdatacloud.model.WechatDeviceRegistReq;
import com.huawei.hwcloudmodel.healthdatacloud.model.WechatDeviceSignReq;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes5.dex */
public class kfo implements DataReceiveCallback {

    /* renamed from: a, reason: collision with root package name */
    private static volatile kfo f14350a;
    private final jbs b;

    public kfo() {
        LogUtil.a("HWWechatDeviceMgr", "HWWechatDeviceMgr(). ");
        this.b = jbs.a(BaseApplication.getContext());
    }

    public static kfo a() {
        if (f14350a == null) {
            synchronized (kfo.class) {
                if (f14350a == null) {
                    f14350a = new kfo();
                }
            }
        }
        return f14350a;
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        if (deviceInfo == null) {
            LogUtil.h("HWWechatDeviceMgr", "onDataReceived deviceInfo is null");
            return;
        }
        if (cvrVar instanceof cvq) {
            LogUtil.a("HWWechatDeviceMgr", "onDataReceived sampleBase instanceof SampleConfig");
            List<cvn> configInfoList = ((cvq) cvrVar).getConfigInfoList();
            if (configInfoList == null || configInfoList.size() <= 0) {
                LogUtil.h("HWWechatDeviceMgr", "onDataReceived sampleConfigInfos is null or empty");
            } else {
                b(configInfoList);
            }
        }
    }

    private void b(List<cvn> list) {
        LogUtil.a("HWWechatDeviceMgr", "configDeviceDbInfo in. ");
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HWWechatDeviceMgr");
        if (deviceList == null || deviceList.size() == 0) {
            LogUtil.h("HWWechatDeviceMgr", "configDeviceDbInfo main device is null or empty");
            return;
        }
        for (cvn cvnVar : list) {
            String e = cvx.e(cvx.d(cvnVar.b()));
            LogUtil.a("HWWechatDeviceMgr", "configDeviceDbInfo configData = ", e);
            final DeviceInfo deviceInfo = deviceList.get(0);
            if (cvnVar.e() == 3) {
                final WechatDeviceSignReq e2 = e(e);
                String e3 = this.b.e(e2);
                b(e3, deviceInfo);
                LogUtil.a("HWWechatDeviceMgr", "signRsp: ", e3);
                final WechatDeviceRegistReq wechatDeviceRegistReq = new WechatDeviceRegistReq();
                ThreadPoolManager.d().d("HWWechatDeviceMgr", new Runnable() { // from class: kfn
                    @Override // java.lang.Runnable
                    public final void run() {
                        kfo.this.e(deviceInfo, wechatDeviceRegistReq, e2);
                    }
                });
            } else {
                LogUtil.h("HWWechatDeviceMgr", "configDeviceDbInfo configAction is not match");
            }
        }
    }

    /* synthetic */ void e(DeviceInfo deviceInfo, final WechatDeviceRegistReq wechatDeviceRegistReq, final WechatDeviceSignReq wechatDeviceSignReq) {
        kgb.a(deviceInfo, new ICloudOperationResult() { // from class: kfq
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                kfo.this.e(wechatDeviceRegistReq, wechatDeviceSignReq, (String) obj, str, z);
            }
        });
    }

    /* synthetic */ void e(WechatDeviceRegistReq wechatDeviceRegistReq, WechatDeviceSignReq wechatDeviceSignReq, String str, String str2, boolean z) {
        ReleaseLogUtil.e("R_HWWechatDeviceMgr", "configDeviceDbInfo getDeviceCodeAsync deviceCode: ", CommonUtil.l(str), ",resp: ", CommonUtil.l(str2), ",isSuccess: ", Boolean.valueOf(z));
        wechatDeviceRegistReq.setDeviceList(Arrays.asList(new WechatDeviceRegistReq.WechatDevice(wechatDeviceSignReq.getDeviceSn(), str)));
        wechatDeviceRegistReq.setProductId(wechatDeviceSignReq.getProductId());
        LogUtil.a("HWWechatDeviceMgr", "registerRsp: ", this.b.a(wechatDeviceRegistReq));
    }

    private WechatDeviceSignReq e(String str) {
        LogUtil.a("HWWechatDeviceMgr", "parseJson: ", str);
        WechatDeviceSignReq wechatDeviceSignReq = new WechatDeviceSignReq();
        try {
            return (WechatDeviceSignReq) new Gson().fromJson(str, new TypeToken<WechatDeviceSignReq>() { // from class: kfo.5
            }.getType());
        } catch (JsonSyntaxException unused) {
            LogUtil.b("HWWechatDeviceMgr", "savedMessage jsonSyntaxException ");
            return wechatDeviceSignReq;
        } catch (NumberFormatException unused2) {
            LogUtil.b("HWWechatDeviceMgr", "savedMessage NumberFormatException");
            return wechatDeviceSignReq;
        }
    }

    private void b(String str, DeviceInfo deviceInfo) {
        LogUtil.a("HWWechatDeviceMgr", "sendCommand: ", deviceInfo.toString());
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("hw.unitedevice.wechatDevice");
        cvqVar.setWearPkgName("com.tencent.wechatrtos");
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(c(str));
        cvqVar.setConfigInfoList(arrayList);
        cuk.b().sendSampleConfigCommand(deviceInfo, cvqVar, "HWWechatDeviceMgr");
    }

    private cvn c(String str) {
        cvn cvnVar = new cvn();
        cvnVar.e(900100009L);
        cvnVar.d(1);
        cvnVar.c(cvx.a(cvx.c(str)));
        LogUtil.a("HWWechatDeviceMgr", "constructSampleConfigInfo: ", cvnVar);
        return cvnVar;
    }

    public void e() {
        cuk.b().registerDeviceSampleListener("hw.unitedevice.wechatDevice", this);
    }
}
