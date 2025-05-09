package defpackage;

import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.api.phoneprocess.PhoneServiceMgrApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import java.util.List;

/* loaded from: classes.dex */
public class cuo extends AppBundlePluginProxy<PhoneServiceMgrApi> implements PhoneServiceMgrApi {
    private static volatile cuo e;
    private PhoneServiceMgrApi b;

    private cuo() {
        super("PhoneServiceMgrProxy", "PhoneServiceMgr", "com.huawei.hwdevice.phoneprocess.impl.PhoneServiceMgrImpl");
        this.b = createPluginApi();
    }

    public static cuo d() {
        cuo cuoVar;
        if (e == null) {
            synchronized (cuo.class) {
                if (e == null) {
                    e = new cuo();
                }
                cuoVar = e;
            }
            return cuoVar;
        }
        return e;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.b != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void initialize(PhoneServiceMgrApi phoneServiceMgrApi) {
        this.b = phoneServiceMgrApi;
    }

    @Override // com.huawei.health.devicemgr.api.phoneprocess.PhoneServiceMgrApi
    public List<DeviceInfo> getDeviceList(HwGetDevicesMode hwGetDevicesMode, HwGetDevicesParameter hwGetDevicesParameter, String str) {
        PhoneServiceMgrApi phoneServiceMgrApi = this.b;
        if (phoneServiceMgrApi != null) {
            return phoneServiceMgrApi.getDeviceList(hwGetDevicesMode, hwGetDevicesParameter, str);
        }
        return null;
    }

    @Override // com.huawei.health.devicemgr.api.phoneprocess.PhoneServiceMgrApi
    public String getDeviceRelation(String str) {
        PhoneServiceMgrApi phoneServiceMgrApi = this.b;
        return phoneServiceMgrApi != null ? phoneServiceMgrApi.getDeviceRelation(str) : "";
    }
}
