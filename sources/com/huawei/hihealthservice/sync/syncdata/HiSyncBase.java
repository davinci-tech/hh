package com.huawei.hihealthservice.sync.syncdata;

import defpackage.iut;

/* loaded from: classes7.dex */
public interface HiSyncBase {
    void pullDataByTime(long j, long j2) throws iut;

    void pullDataByVersion() throws iut;

    void pushData() throws iut;
}
