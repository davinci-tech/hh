package com.huawei.hianalytics.framework.data;

import com.huawei.hianalytics.framework.data.WorkKeyHandler;

/* loaded from: classes4.dex */
public interface IAesKeyGetter {

    public interface Callback {
        void onResult(WorkKeyHandler.WorkKeyBean workKeyBean);
    }

    WorkKeyHandler.WorkKeyBean getWorkKeyBeanFromDisk();

    void requestAesKey(String str, Callback callback);

    void saveWorkKeyBeanToDisk(WorkKeyHandler.WorkKeyBean workKeyBean);
}
