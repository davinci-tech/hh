package com.huawei.hihealth.data.listener;

import com.huawei.hihealth.model.HealthTrendReport;
import java.util.List;

/* loaded from: classes.dex */
public interface HealthTrendListener {
    void onResult(int i, List<HealthTrendReport> list);
}
