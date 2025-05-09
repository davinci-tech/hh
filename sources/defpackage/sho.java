package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.healthdatacloud.model.GetBindDeviceReq;
import com.huawei.hwcloudmodel.model.userprofile.GetBindDeviceRsp;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HEXUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes8.dex */
public class sho {
    public void c(DeviceInfo deviceInfo, ICloudOperationResult<String> iCloudOperationResult) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceName())) {
            iCloudOperationResult.operationResult("", "device info is null", false);
            return;
        }
        ReleaseLogUtil.e("R_ProfileBindDeviceService", "deviceInfo", deviceInfo.getDeviceName(), ",prodid:", deviceInfo.getHiLinkDeviceId(), ",uniqueId:", CommonUtil.l(HEXUtils.d(deviceInfo.getUuid()) + "#ANDROID21"));
        GetBindDeviceReq getBindDeviceReq = new GetBindDeviceReq();
        getBindDeviceReq.setDeviceCode(0L);
        GetBindDeviceRsp c = jbs.a(BaseApplication.e()).c(getBindDeviceReq);
        if (c == null || koq.b(c.getDeviceInfos())) {
            iCloudOperationResult.operationResult("", "getBindDeviceRsp has no data", false);
            return;
        }
        for (com.huawei.hwcloudmodel.model.userprofile.DeviceInfo deviceInfo2 : c.getDeviceInfos()) {
            LogUtil.c("ProfileBindDeviceService", "info ", deviceInfo2.getDeviceCode(), ":", deviceInfo2.getName());
            if (sii.d(deviceInfo, deviceInfo2)) {
                iCloudOperationResult.operationResult(String.valueOf(deviceInfo2.getDeviceCode()), c.toString(), deviceInfo2.getDeviceCode().longValue() != 0);
                return;
            }
        }
        iCloudOperationResult.operationResult("", "device code not found", false);
    }
}
