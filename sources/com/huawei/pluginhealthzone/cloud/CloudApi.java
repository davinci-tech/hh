package com.huawei.pluginhealthzone.cloud;

import defpackage.exf;
import defpackage.mqp;
import java.util.Collection;
import java.util.List;

/* loaded from: classes6.dex */
public interface CloudApi {
    void getCommonUsedDevices(String str, HttpDataCallback httpDataCallback);

    void getMyFollowRelations(Collection<Long> collection, HttpDataCallback httpDataCallback);

    void getPositionPushInfo(String str, int i, HttpDataCallback httpDataCallback);

    void getPushInformationByNotifyTime(long j, int i, HttpDataCallback httpDataCallback);

    void getVerifyCode(HttpDataCallback httpDataCallback);

    void notifyMemberToCheckHealth(String str, HttpDataCallback httpDataCallback);

    void setCommonUsedDevices(List<exf> list, HttpDataCallback httpDataCallback);

    void setLocationPermission(String str, int i, HttpDataCallback httpDataCallback);

    void uploadPosition(String str, mqp mqpVar, int i, HttpDataCallback httpDataCallback);
}
