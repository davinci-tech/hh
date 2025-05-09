package defpackage;

import android.os.Bundle;
import com.huawei.health.device.kit.devicelogmgr.FilePuller;
import com.huawei.health.device.kit.devicelogmgr.FilePullerCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.unite.DeviceServiceInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceControlDataModelReq;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class cfm implements FilePuller {
    @Override // com.huawei.health.device.kit.devicelogmgr.FilePuller
    public void syncDeviceLog(Bundle bundle, final FilePullerCallback filePullerCallback) {
        if (filePullerCallback == null || bundle == null) {
            LogUtil.h("HwWspFilePuller", "send log fail!");
            return;
        }
        LogUtil.a("HwWspFilePuller", "start sync wifi device log");
        DeviceServiceInfo deviceServiceInfo = new DeviceServiceInfo();
        HashMap hashMap = new HashMap(10);
        hashMap.put("upload_log", "");
        deviceServiceInfo.setData(hashMap);
        deviceServiceInfo.setSid("setDevParam");
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(deviceServiceInfo);
        WifiDeviceControlDataModelReq wifiDeviceControlDataModelReq = new WifiDeviceControlDataModelReq();
        wifiDeviceControlDataModelReq.setDeviceServiceInfo(arrayList);
        wifiDeviceControlDataModelReq.setDevId(bundle.getString("deviceId"));
        jbs.a(BaseApplication.getContext()).d(wifiDeviceControlDataModelReq, new ICloudOperationResult() { // from class: cfm.1
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public void operationResult(Object obj, String str, boolean z) {
                LogUtil.a("HwWspFilePuller", "operation Result isSuccess =", Boolean.valueOf(z));
                if (z) {
                    filePullerCallback.onSuccess(obj);
                } else {
                    filePullerCallback.onFail(-3);
                }
            }
        });
    }
}
