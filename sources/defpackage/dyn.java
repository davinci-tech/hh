package defpackage;

import android.view.View;
import com.huawei.health.device.api.DeviceInfoUtilsApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hmf.annotation.ApiDefine;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

@ApiDefine(uri = DeviceInfoUtilsApi.class)
/* loaded from: classes3.dex */
public class dyn implements DeviceInfoUtilsApi {
    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public boolean isSupportUdsByProductId() {
        LogUtil.d("DeviceInfoUtilsImpl", "isSupportUds");
        return cpl.c().i();
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public boolean isDeviceBand(int i) {
        LogUtil.d("DeviceInfoUtilsImpl", "isDeviceBand");
        return cpl.c().g(i);
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public DeviceInfo getConnectionDevice() {
        LogUtil.d("DeviceInfoUtilsImpl", "getConnectionDevice");
        return cpl.c().d();
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public boolean isWearInfoListSize() {
        LogUtil.d("DeviceInfoUtilsImpl", "getWearInfoListSize");
        return cpl.c().j() != null && cpl.c().j().size() > 0;
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public void setCharacteristicNotifyByUds(String str, String str2, String str3, boolean z) {
        LogUtil.d("DeviceInfoUtilsImpl", "setCharacteristicNotifyByUds");
        cpl.c().e(str, str2, str3, z);
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public <T> void putUdsClassMap(String str, T t) {
        LogUtil.d("DeviceInfoUtilsImpl", "putUdsClassMap");
        cpl.c().a(str, (String) t);
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public void clearUdsClassMap() {
        LogUtil.d("DeviceInfoUtilsImpl", "clearUdsClassMap");
        cpl.c().a();
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public boolean isBindingHeartRateDeviceWear(List<String> list) {
        ArrayList<cpm> a2 = cpl.c().a(list);
        return (a2 == null || a2.isEmpty()) ? false : true;
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public String getBondedDeviceAddress(String str, String str2) {
        return cpl.e(str, str2);
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public ArrayList<cpm> getWearInfo() {
        return cpl.c().j();
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public void resetUpdate() {
        cpl.c().f();
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public List<DeviceInfo> getWearDeviceList() {
        return cpl.c().h();
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public DeviceInfo getCurrentDevice() {
        return cpl.c().d();
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public void setBattery(int i) {
        cpl.c().f(i);
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public boolean isPluginDownloadByType(int i) {
        return cpl.c().j(i);
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public String getUUIDForPlugin(int i) {
        return cpl.c().a(i);
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public boolean idCurrentDeviceActive(String str) {
        return cpl.c().b(str);
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public List<DeviceInfo> getCurrentDeviceList() {
        return cpl.c().e();
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public boolean isWiFiDevice(String str) {
        return cue.a(str);
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public int getBtType(int i) {
        return cpl.c().c(i);
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public void sendDeraultSwitch() {
        cpl.c().g();
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public ArrayList<cpm> getWearInfoHeartRate(List<String> list) {
        return cpl.c().a(list);
    }

    @Override // com.huawei.health.device.api.DeviceInfoUtilsApi
    public View checkProductType(int i) {
        return cpl.c().Kj_(i);
    }
}
