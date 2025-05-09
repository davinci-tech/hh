package com.huawei.hms.hihealth;

import android.content.Intent;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.health.aacp;
import com.huawei.hms.hihealth.data.DataType;
import com.huawei.hms.hihealth.options.DataTypeAddOptions;
import com.huawei.hms.hihealth.result.HealthKitAuthResult;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;

/* loaded from: classes4.dex */
public class SettingController {
    private aabo aab;

    public Task<Boolean> setLinkHealthKitStatus(boolean z) {
        return ((aacp) this.aab).aab(z);
    }

    public Intent requestAuthorizationIntent(String[] strArr, boolean z) {
        return ((aacp) this.aab).aab(strArr, z);
    }

    public Task<DataType> readDataType(String str) {
        return ((aacp) this.aab).aaba(str);
    }

    public HealthKitAuthResult parseHealthKitAuthResultFromIntent(Intent intent) {
        return ((aacp) this.aab).aab(intent);
    }

    public boolean openAuthFromCloud() {
        return ((aacp) this.aab).aabf();
    }

    @Deprecated
    public Boolean isAppInTrustList(String str) {
        return ((aacp) this.aab).aab(str);
    }

    public Task<Boolean> getLinkHealthKitStatus() {
        return ((aacp) this.aab).aabe();
    }

    public Task<Boolean> getHealthAppAuthorization() {
        return ((aacp) this.aab).aabc();
    }

    @Deprecated
    public Task<Boolean> getHealthAppAuthorisation() {
        return getHealthAppAuthorization();
    }

    public Task<String> getAuthUrl() {
        return ((aacp) this.aab).aabb();
    }

    public Task<Void> disableHiHealth() {
        return ((aacp) this.aab).aaba();
    }

    public Task<Void> checkHealthAppAuthorization() {
        return ((aacp) this.aab).aab();
    }

    @Deprecated
    public Task<Void> checkHealthAppAuthorisation() {
        return checkHealthAppAuthorization();
    }

    public Task<DataType> addDataType(DataTypeAddOptions dataTypeAddOptions) {
        return ((aacp) this.aab).aab(dataTypeAddOptions);
    }

    @Deprecated
    SettingController(AuthHuaweiId authHuaweiId) {
        this.aab = null;
        this.aab = aacp.aabg();
    }

    SettingController() {
        this.aab = null;
        this.aab = aacp.aabg();
    }
}
