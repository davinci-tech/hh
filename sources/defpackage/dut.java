package defpackage;

import android.content.ContentValues;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.linkage.sportlinkage.AppLinkageApi;
import com.huawei.linkage.sportlinkage.DataListener;
import com.huawei.linkage.sportlinkage.LinkagePlatformApi;
import org.json.JSONObject;

@ApiDefine(uri = LinkagePlatformApi.class)
@Singleton
/* loaded from: classes3.dex */
public class dut implements LinkagePlatformApi {
    @Override // com.huawei.linkage.sportlinkage.LinkagePlatformApi
    public void initReverseLinkage(AppLinkageApi appLinkageApi) {
        duu.a().e(appLinkageApi);
    }

    @Override // com.huawei.linkage.sportlinkage.LinkagePlatformApi
    public void handleRemoteException(ContentValues contentValues) {
        LogUtil.h("LINKAGE_ReverseLinkageManager", "registerRealDataListener handleRemoteException, content ", contentValues.toString());
        duu.a().aal_(contentValues);
    }

    @Override // com.huawei.linkage.sportlinkage.LinkagePlatformApi
    public void registerRealDataListener(DataListener dataListener) {
        LogUtil.h("LINKAGE_ReverseLinkageManager", "registerRealDataListener linkageDataListener: ", dataListener);
        duu.a().c(dataListener);
    }

    @Override // com.huawei.linkage.sportlinkage.LinkagePlatformApi
    public void unRegisterRealDataListener(DataListener dataListener) {
        duu.a().d(dataListener);
    }

    @Override // com.huawei.linkage.sportlinkage.LinkagePlatformApi
    public void unRegisterAllRealDataListener() {
        duu.a().h();
    }

    @Override // com.huawei.linkage.sportlinkage.LinkagePlatformApi, com.huawei.linkage.sportlinkage.LinkageApi
    public void stopLinkage() {
        LogUtil.h("LINKAGE_ReverseLinkageManager", "ReverseLinkageApiImpl stopLinkage");
        duu.a().c();
    }

    @Override // com.huawei.linkage.sportlinkage.LinkagePlatformApi, com.huawei.linkage.sportlinkage.LinkageApi
    public void resume() {
        LogUtil.h("LINKAGE_ReverseLinkageManager", "ReverseLinkageApiImpl resume");
        duu.a().d();
    }

    @Override // com.huawei.linkage.sportlinkage.LinkagePlatformApi, com.huawei.linkage.sportlinkage.LinkageApi
    public void pause() {
        LogUtil.h("LINKAGE_ReverseLinkageManager", "ReverseLinkageApiImpl pause");
        duu.a().b();
    }

    @Override // com.huawei.linkage.sportlinkage.LinkagePlatformApi, com.huawei.linkage.sportlinkage.LinkageApi
    public void stop() {
        LogUtil.h("LINKAGE_ReverseLinkageManager", "ReverseLinkageApiImpl stop");
        duu.a().e();
    }

    @Override // com.huawei.linkage.sportlinkage.LinkagePlatformApi
    public void replyDevice(int i, String str) {
        LogUtil.h("LINKAGE_ReverseLinkageManager", "ReverseLinkageApiImpl replyDevice");
        duu.a().c(i, str);
    }

    @Override // com.huawei.linkage.sportlinkage.LinkagePlatformApi
    public void sendDataToDevice(JSONObject jSONObject, DeviceInfo deviceInfo) {
        duu.a().a(jSONObject, deviceInfo);
    }
}
