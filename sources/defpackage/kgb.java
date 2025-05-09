package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudGzipFactory;
import com.huawei.hwcloudmodel.healthdatacloud.model.BindDeviceReq;
import com.huawei.hwcloudmodel.model.userprofile.BindDeviceRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.GRSManager;
import health.compact.a.Utils;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class kgb {
    public static void a(DeviceInfo deviceInfo, ICloudOperationResult<String> iCloudOperationResult) {
        if (deviceInfo == null) {
            LogUtil.b("HwCloudDeviceMgr", "bindDevice return for device info null");
            iCloudOperationResult.operationResult("", "device info is null", false);
            return;
        }
        int b = juu.a(deviceInfo.getProductType()).b();
        if (!TextUtils.isEmpty(deviceInfo.getDeviceModel()) && deviceInfo.getDeviceModel().toUpperCase(Locale.ENGLISH).contains("HECTOR")) {
            b = 112;
        }
        int b2 = b(deviceInfo.getHiLinkDeviceId());
        if (b2 != -1) {
            LogUtil.a("UIDV_FitnessCloud", "startDeviceLinkage getDeviceProductIdByHiLinId is ", Integer.valueOf(b2));
            b = b2;
        }
        BindDeviceReq bindDeviceReq = new BindDeviceReq();
        bindDeviceReq.setProductId(Integer.valueOf(b));
        bindDeviceReq.setUniqueId(d(deviceInfo));
        bindDeviceReq.setName(deviceInfo.getDeviceName());
        String str = GRSManager.a(BaseApplication.getContext()).getUrl("healthCloudUrl") + "/profile/device/bindDevice";
        HealthDataCloudGzipFactory healthDataCloudGzipFactory = new HealthDataCloudGzipFactory(BaseApplication.getContext());
        BindDeviceRsp bindDeviceRsp = (BindDeviceRsp) lqi.d().d(str, healthDataCloudGzipFactory.getHeaders(), lql.b(healthDataCloudGzipFactory.getBody(bindDeviceReq)), BindDeviceRsp.class);
        if (bindDeviceRsp == null || iCloudOperationResult == null) {
            return;
        }
        Long deviceCode = bindDeviceRsp.getDeviceCode();
        iCloudOperationResult.operationResult(String.valueOf(deviceCode), bindDeviceRsp.toString(), deviceCode.longValue() != 0);
    }

    private static String d(DeviceInfo deviceInfo) {
        if (deviceInfo.getProductType() >= 34 && !Utils.o()) {
            return deviceInfo.getSecurityUuid() + "#ANDROID21";
        }
        return deviceInfo.getUuid();
    }

    private static int b(String str) {
        try {
            JSONObject d = cwc.d(str);
            if (d == null) {
                LogUtil.h("HwCloudDeviceMgr", "getDeviceProductIdByHiLinId aiTipsJson is null");
                return -1;
            }
            if (d.has("health_cloud_productId")) {
                int i = d.getInt("health_cloud_productId");
                LogUtil.a("HwCloudDeviceMgr", "healthCloudId is ", Integer.valueOf(i));
                return i;
            }
            LogUtil.h("HwCloudDeviceMgr", "getDeviceProductIdByHiLinId aiTipsJson no health_cloud_productId key");
            return -1;
        } catch (JSONException unused) {
            LogUtil.b("HwCloudDeviceMgr", "getDeviceProductIdByHiLinId JSONException");
            return -1;
        }
    }
}
