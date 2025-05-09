package com.huawei.linkage.sportlinkage;

import android.content.ContentValues;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public interface LinkagePlatformApi extends LinkageApi {
    void handleRemoteException(ContentValues contentValues);

    void initReverseLinkage(AppLinkageApi appLinkageApi);

    @Override // com.huawei.linkage.sportlinkage.LinkageApi
    void pause();

    void registerRealDataListener(DataListener dataListener);

    void replyDevice(int i, String str);

    @Override // com.huawei.linkage.sportlinkage.LinkageApi
    void resume();

    void sendDataToDevice(JSONObject jSONObject, DeviceInfo deviceInfo);

    @Override // com.huawei.linkage.sportlinkage.LinkageApi
    void stop();

    @Override // com.huawei.linkage.sportlinkage.LinkageApi
    void stopLinkage();

    void unRegisterAllRealDataListener();

    void unRegisterRealDataListener(DataListener dataListener);
}
