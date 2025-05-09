package com.huawei.syncmgr.menstrual;

import com.huawei.hihealth.HiHealthData;
import defpackage.cvo;
import java.util.List;

/* loaded from: classes6.dex */
public interface MenstrualSyncApi {
    List<HiHealthData> getMenstrualFlow(long j, long j2, long j3);

    boolean syncMenstrualToDevice(List<cvo> list);
}
