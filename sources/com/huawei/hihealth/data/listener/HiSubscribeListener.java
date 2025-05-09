package com.huawei.hihealth.data.listener;

import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import java.util.List;

/* loaded from: classes.dex */
public interface HiSubscribeListener {
    void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j);

    void onResult(List<Integer> list, List<Integer> list2);
}
