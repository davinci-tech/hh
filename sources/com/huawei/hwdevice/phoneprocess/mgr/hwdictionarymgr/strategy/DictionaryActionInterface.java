package com.huawei.hwdevice.phoneprocess.mgr.hwdictionarymgr.strategy;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import defpackage.cwe;
import defpackage.kbn;
import defpackage.kbo;
import defpackage.spn;

/* loaded from: classes5.dex */
public interface DictionaryActionInterface {
    spn buildSendMessage(kbo kboVar);

    kbo genCacheDicInfo(int i, long j, long j2);

    void insertToHiHealth(kbn kbnVar, HiDataOperateListener hiDataOperateListener);

    kbn parsePointData(cwe cweVar);

    void readHiHealthData(int i, DeviceInfo deviceInfo, HiDataReadResultListener hiDataReadResultListener);

    boolean syncNextInfo(kbn kbnVar, kbo kboVar);
}
