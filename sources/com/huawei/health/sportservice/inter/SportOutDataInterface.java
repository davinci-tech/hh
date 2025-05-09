package com.huawei.health.sportservice.inter;

import com.huawei.health.sportservice.SportDataNotify;
import defpackage.fgm;

/* loaded from: classes4.dex */
public interface SportOutDataInterface {
    Object getData(String str);

    int getSportType();

    int getTargetType();

    float getTargetValue();

    boolean isToSave();

    void stagingData(String str, Object obj);

    boolean subscribeNotify(fgm fgmVar, SportDataNotify sportDataNotify);
}
