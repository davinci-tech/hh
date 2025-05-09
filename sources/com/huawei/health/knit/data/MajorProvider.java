package com.huawei.health.knit.data;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class MajorProvider<T> extends BaseKnitDataProvider<T> {
    private static final String TAG = "MajorProvider";
    private T mLastData;
    private List<MinorProvider<T>> mMinorProviderList = new ArrayList();

    public void registerMinorProvider(MinorProvider<T> minorProvider) {
        this.mMinorProviderList.add(minorProvider);
    }

    public void notifyMinorProviders(T t) {
        LogUtil.a(TAG, "notifyMinorProviders");
        for (MinorProvider<T> minorProvider : this.mMinorProviderList) {
            long currentTimeMillis = System.currentTimeMillis();
            minorProvider.onMajorProviderDataArrived(t);
            LogUtil.a(TAG, "mp: ", minorProvider, ", cost time: ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        }
        this.mLastData = t;
    }

    public T getLastData() {
        return this.mLastData;
    }
}
