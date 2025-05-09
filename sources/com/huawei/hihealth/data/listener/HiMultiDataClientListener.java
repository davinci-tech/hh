package com.huawei.hihealth.data.listener;

import com.huawei.hihealth.HiHealthClient;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public interface HiMultiDataClientListener {
    void onMergeTypeResult(List<HiHealthClient> list);

    void onMultiTypeResult(Map<Integer, List<HiHealthClient>> map);
}
