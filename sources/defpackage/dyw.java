package defpackage;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.hmf.annotation.ApiDefine;
import health.compact.a.util.LogUtil;

@ApiDefine(uri = HonourDeviceConstantsApi.class)
/* loaded from: classes3.dex */
public class dyw implements HonourDeviceConstantsApi {
    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public boolean getUserSureDownload() {
        LogUtil.d("HonourDeviceConstantsImpl", "getUserSureDownload");
        return cpa.i();
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public boolean isHonourDevice(String str) {
        LogUtil.d("HonourDeviceConstantsImpl", "isHonourDevice");
        return cpa.z(str);
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public boolean isShowWiFiDevice(String str) {
        LogUtil.d("HonourDeviceConstantsImpl", "isShowWiFiDevice");
        return cpa.aj(str);
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public boolean isHuaweiWspScaleProduct(String str) {
        LogUtil.d("HonourDeviceConstantsImpl", "isHuaweiWspScaleProduct");
        return cpa.ae(str);
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public boolean isTlvScaleProduct(String str, String str2) {
        LogUtil.d("HonourDeviceConstantsImpl", "isTlvScaleProduct");
        return cpa.c(str, str2);
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public boolean isDualModeProduct(String str) {
        return cpa.x(str);
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public boolean isSupportWifiDevice() {
        return cpa.f();
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public boolean isCorrectProductId(String str) {
        return cpa.q(str);
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public boolean isHuaweiOrHonourDevice(String str) {
        LogUtil.d("HonourDeviceConstantsImpl", "isHuaweiOrHonourDevice");
        return cpa.ad(str);
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public boolean isHuaweiOrHonourScaleProdId(String str) {
        LogUtil.d("HonourDeviceConstantsImpl", "isHuaweiOrHonourScaleProdId");
        return cpa.ag(str);
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public boolean isHuaweiWspDataType(int i) {
        LogUtil.d("HonourDeviceConstantsImpl", "isHuaweiWspDataType");
        return cpa.e(i);
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public boolean isNoneHonourAm16BondedDevice() {
        LogUtil.d("HonourDeviceConstantsImpl", "isNoneHonourAm16BondedDevice");
        return cpa.g();
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public String getDeviceSerialNumberByUniqueId(String str) {
        LogUtil.d("HonourDeviceConstantsImpl", "getDeviceSerialNumberByUniqueId");
        return cpa.l(str);
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public Bundle getAm16Bundle() {
        LogUtil.d("HonourDeviceConstantsImpl", "getAm16Bundle");
        return cpa.JY_();
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public void setDevicesUseTime(String str) {
        cpa.at(str);
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public boolean isNoneHuaWeiWeightBondedDevice(String str) {
        return cpa.af(str);
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public boolean isHonourNewDevice(String str) {
        return cpa.ac(str);
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public String judgeProductIdByDeviceName(String str) {
        LogUtil.d("HonourDeviceConstantsImpl", "judgeProductIdByDeviceName");
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String str2 = str.contains("CH100") ? "33123f39-7fc1-420b-9882-a4b0d6c61100" : null;
        if (str.contains("CH18")) {
            str2 = "34fa0346-d46c-439d-9cb0-2f696618846b";
        }
        if (str.contains("CH100S")) {
            str2 = "ccd1f0f8-8c57-4bd7-a884-0ef38482f15f";
        }
        return str.contains("AH100") ? "7a1063dd-0e0f-4a72-9939-461476ff0259" : str2;
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public void setUserSureDownload() {
        cpa.k();
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public String getDeviceIdentify(String str) {
        return cpa.f(str);
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public String getDeviceHelpH5Url(String str) {
        return cpa.e(str);
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public boolean isHonourWeightDevice(String str) {
        return cpa.ab(str);
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public String getDeviceNameByProductId(String str) {
        return cpa.m(str);
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public boolean getAm16PermissionTip() {
        return cpa.b();
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public void setAm16PermissionTip() {
        cpa.l();
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public boolean isWiFiDevice(String str) {
        return cpa.au(str);
    }

    @Override // com.huawei.health.device.api.HonourDeviceConstantsApi
    public String getDeviceModel(String str) {
        return cpa.g(str);
    }
}
