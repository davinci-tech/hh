package com.huawei.health.configresource.downloaddevicemanager;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadDeviceInfoCallBack;
import com.huawei.health.devicemgr.business.entity.downloadresouce.DownloadResultCallBack;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ccp;
import defpackage.ccs;
import defpackage.ccv;
import defpackage.ccx;
import defpackage.cdc;
import defpackage.cdg;
import defpackage.cdk;
import defpackage.cuy;
import defpackage.cvc;
import defpackage.cve;
import defpackage.cvk;
import health.compact.a.GRSManager;
import java.util.List;

@ApiDefine(uri = DownloadManagerApi.class)
/* loaded from: classes3.dex */
public class DownloadManagerImpl implements DownloadManagerApi {
    private static final String TAG = "DownloadManagerImpl";

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public String getPairDescription(String str) {
        return null;
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public List<String> getValueFeature(String str) {
        return null;
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public void downloadIndexAll() {
        LogUtil.a(TAG, "downloadIndexAll");
        ccx.e().d();
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public void addDownloadIndexAllCallBack(DownloadResultCallBack downloadResultCallBack) {
        LogUtil.a(TAG, "addDownloadIndexAllCallBack");
        ccx.e().d(downloadResultCallBack);
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public void onDownloadIndexAllDestroy() {
        ccx.e().a();
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public List<cve> getDeviceList() {
        return ccs.d().e();
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public cuy getIndexBean() {
        return ccs.d().b();
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public List<cve> getDeviceInfoByBluetooth(String str) {
        return ccs.d().d(str);
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public void downloadIndexByUuidList(List<String> list, DownloadDeviceInfoCallBack downloadDeviceInfoCallBack) {
        if (downloadDeviceInfoCallBack == null) {
            if (list == null || list.size() != 1) {
                return;
            }
            ccv.d().e(list.get(0));
            return;
        }
        new cdc(list, downloadDeviceInfoCallBack).a();
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public void downloadIndexByDeviceType(String str) {
        ccv.d().d(str);
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public cvc getPluginInfoByUuid(String str) {
        return cdk.e().b(str);
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public cvc getPluginInfoByDeviceType(int i) {
        return cdk.e().e(i);
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public cvc getPluginInfoByHiType(int i) {
        return cdk.e().b(i);
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public List<cvk> getIndexList() {
        return cdk.e().c();
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public boolean isResourcesAvailable(String str) {
        return cdk.e().h(str);
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public Bitmap loadImageByImageName(cvc cvcVar, String str) {
        return cdk.e().Dg_(cvcVar, str);
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public Bitmap loadImageByImagePath(String str) {
        return cdk.e().Df_(str);
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public void clearPluginInfoList() {
        cdk.d();
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public String getPluginIndexOobe(String str) {
        cvk e = cdk.e().e(str);
        String b = e != null ? e.b() : "";
        return (TextUtils.isEmpty(b) && isSmartWatch(str)) ? "6ed99f86-c6b4-43e2-8339-52b2d72a9168" : b;
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public void resourcePresetHty() {
        cdg.c();
        downloadIndexAll();
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public boolean isHtyVersion() {
        return cdg.e();
    }

    @Override // com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi
    public boolean isSupportPairCheckedOtaAutoUpdateSwitch() {
        List<String> a2 = ccp.a();
        String commonCountryCode = GRSManager.a(BaseApplication.getContext()).getCommonCountryCode();
        LogUtil.a(TAG, "countryCodeList: " + a2 + ", current countryCode: " + commonCountryCode);
        return !a2.contains(commonCountryCode);
    }

    private boolean isSmartWatch(String str) {
        return "69a968a4-5db8-4d1e-a390-762cb8039784".equals(str) || "96cea4f7-9a5e-4db4-9dda-244885c5446f".equals(str) || "c7d28238-fec2-4985-b2a4-f29c96637ac4".equals(str) || "f5195256-bc1c-44b8-a4b7-52866f623d11".equals(str);
    }
}
