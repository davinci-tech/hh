package com.huawei.basichealthmodel.listener;

import android.util.SparseArray;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import java.util.List;

/* loaded from: classes3.dex */
public interface TaskDataListener {
    void onAllDataChange(int i, SparseArray<List<HealthLifeBean>> sparseArray);

    void onDataChange(int i, SparseArray<HealthLifeBean> sparseArray);
}
