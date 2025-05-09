package defpackage;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.healthlinkage.api.HWhealthLinkageApi;
import com.huawei.hmf.annotation.ApiDefine;
import java.util.List;

@ApiDefine(uri = HWhealthLinkageApi.class)
/* loaded from: classes3.dex */
public class dua implements HWhealthLinkageApi {
    @Override // com.huawei.health.healthlinkage.api.HWhealthLinkageApi
    public boolean isMediatorExist() {
        return dum.d() == null;
    }

    @Override // com.huawei.health.healthlinkage.api.HWhealthLinkageApi
    public boolean isMediatorInit() {
        dum d = dum.d();
        if (d == null) {
            return false;
        }
        return d.e();
    }

    @Override // com.huawei.health.healthlinkage.api.HWhealthLinkageApi
    public List<DeviceInfo> getLinkedDevice(int i) {
        return dui.a(i);
    }
}
