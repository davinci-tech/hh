package com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.unitedevice.hwcommonfilemgr.entity.RequestFileInfo;
import defpackage.kbs;
import defpackage.kbu;

/* loaded from: classes5.dex */
public interface DictionarySequenceInterface {
    RequestFileInfo buildSendMessage(kbs kbsVar);

    kbs genCacheDicInfo(int i, int i2, int i3);

    void insertToHiHealth(kbu kbuVar, HiDataOperateListener hiDataOperateListener);

    kbu parseSequenceData(String str);

    void readHiHealthData(int i, DeviceInfo deviceInfo, HiDataReadResultListener hiDataReadResultListener);

    boolean syncNextInfo(kbu kbuVar, kbs kbsVar);
}
