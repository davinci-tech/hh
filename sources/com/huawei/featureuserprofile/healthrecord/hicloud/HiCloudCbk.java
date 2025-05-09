package com.huawei.featureuserprofile.healthrecord.hicloud;

/* loaded from: classes8.dex */
public interface HiCloudCbk<T> {
    void onFailure(int i, String str);

    void onSuccess(T t);
}
