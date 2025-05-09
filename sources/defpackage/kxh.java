package defpackage;

import android.content.Context;
import android.content.Intent;
import com.huawei.health.versionmgr.api.VersionMgrApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwversionmgr.manager.HwVersionManager;

@ApiDefine(uri = VersionMgrApi.class)
/* loaded from: classes5.dex */
public class kxh implements VersionMgrApi {
    @Override // com.huawei.health.versionmgr.api.VersionMgrApi
    public boolean getBandOtaStatus(Context context) {
        return kxz.i(context);
    }

    @Override // com.huawei.health.versionmgr.api.VersionMgrApi
    public void autoCheckAppNewVersionService() {
        kxk.d().a();
    }

    @Override // com.huawei.health.versionmgr.api.VersionMgrApi
    public void showNewVersionDialog(Context context, Intent intent) {
        kxk.d().bSt_(context, intent);
    }

    @Override // com.huawei.health.versionmgr.api.VersionMgrApi
    public void doManualCheckAppNewVersion() {
        kxk.d().g();
    }

    @Override // com.huawei.health.versionmgr.api.VersionMgrApi
    public void makeMessage(Context context) {
        kxk.d().c(context);
    }

    @Override // com.huawei.health.versionmgr.api.VersionMgrApi
    public void deleteMessage() {
        kxk.d().e();
    }

    @Override // com.huawei.health.versionmgr.api.VersionMgrApi
    public boolean haveNewAppVersion(Context context) {
        return HwVersionManager.c(context).j();
    }

    @Override // com.huawei.health.versionmgr.api.VersionMgrApi
    public boolean isDeviceOtaUpdating(String str) {
        return HwVersionManager.c(BaseApplication.getContext()).o(str);
    }

    @Override // com.huawei.health.versionmgr.api.VersionMgrApi
    public boolean isOtherDeviceOtaUpdating(String str) {
        return HwVersionManager.c(BaseApplication.getContext()).k(str);
    }

    @Override // com.huawei.health.versionmgr.api.VersionMgrApi
    public void resetBandUpdate(String str, String str2) {
        kxp.c().c(str, str2);
    }

    @Override // com.huawei.health.versionmgr.api.VersionMgrApi
    public void resetBandUpdate(String str) {
        HwVersionManager.c(BaseApplication.getContext()).l(str);
    }

    @Override // com.huawei.health.versionmgr.api.VersionMgrApi
    public void checkScaleNewVersionService(String str, String str2, String str3, boolean z, String str4) {
        kxp.c().a(str, str2, str3, Boolean.valueOf(z), str4);
    }

    @Override // com.huawei.health.versionmgr.api.VersionMgrApi
    public String getUpdateUrl(boolean z) {
        return kxu.e(false);
    }
}
