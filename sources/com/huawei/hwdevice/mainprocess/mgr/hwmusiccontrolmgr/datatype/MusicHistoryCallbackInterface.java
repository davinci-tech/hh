package com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype;

import defpackage.jka;
import java.util.List;

/* loaded from: classes5.dex */
public interface MusicHistoryCallbackInterface {
    void getHistoryRecordsBusy(int i, String str, int i2);

    void onGetHistoryRecords(int i, String str, List<jka> list);
}
