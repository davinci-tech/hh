package com.huawei.basichealthmodel.listener;

import android.util.SparseArray;
import com.huawei.health.healthmodel.bean.HealthLifeBean;

/* loaded from: classes3.dex */
public interface TaskInterface {
    void getRecord(SparseArray<HealthLifeBean> sparseArray, TaskDataListener taskDataListener);

    void getRecord(HealthLifeBean healthLifeBean, TaskDayDataListener taskDayDataListener);

    void saveRecord(int i, String str, HealthLifeBean healthLifeBean, TaskDayDataListener taskDayDataListener);
}
