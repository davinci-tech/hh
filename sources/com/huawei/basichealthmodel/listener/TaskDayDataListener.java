package com.huawei.basichealthmodel.listener;

import com.huawei.health.healthmodel.bean.HealthLifeBean;
import java.util.List;

/* loaded from: classes3.dex */
public interface TaskDayDataListener {
    void onAllDataChange(int i, List<HealthLifeBean> list);

    void onDataChange(int i, HealthLifeBean healthLifeBean);
}
