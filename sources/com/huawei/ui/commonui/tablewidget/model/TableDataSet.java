package com.huawei.ui.commonui.tablewidget.model;

import com.huawei.ui.commonui.tablewidget.model.ItemData;

/* loaded from: classes6.dex */
public interface TableDataSet<T0 extends ItemData, T1 extends ItemData, T2 extends ItemData, T3 extends ItemData, T4 extends ItemData, T5 extends ItemData> {
    void clearAll();

    T0 getColumnHeaderData(int i, int i2);

    int getColumnsCount();

    T3 getContentData(int i, int i2);

    T2 getRowColumnHeaderData(int i, int i2);

    T1 getRowHeaderData(int i, int i2);

    int getRowsCount();

    T5 getStatisticData(int i, int i2);

    T4 getStatisticHeaderData(int i, int i2);

    void putColumnHeaderData(int i, int i2, T0 t0);

    void putContentData(int i, int i2, T3 t3);

    void putRowColumnHeaderData(int i, int i2, T2 t2);

    void putRowHeaderData(int i, int i2, T1 t1);

    void putStatisticData(int i, int i2, T5 t5);

    void putStatisticHeaderData(int i, int i2, T4 t4);
}
