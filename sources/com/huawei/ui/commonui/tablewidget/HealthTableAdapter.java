package com.huawei.ui.commonui.tablewidget;

import android.view.ViewGroup;
import com.huawei.ui.commonui.tablewidget.HealthTableWidget;
import com.huawei.ui.commonui.tablewidget.ViewHolder;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public interface HealthTableAdapter<T extends ViewHolder> extends HealthTableDataSetObserver {
    int getColumnCount();

    int getColumnHeaderNum();

    int getColumnWidth(int i);

    int getColumnWidthValueType();

    HealthTableWidget.OnItemClickListener getOnItemClickListener();

    HealthTableWidget.OnItemLongClickListener getOnItemLongClickListener();

    int getRowCount();

    int getRowHeaderNum();

    int getRowHeight(int i);

    int getRowHeightValueType();

    int getStatisticNum();

    boolean isRtl();

    void onBindColumnHeaderViewHolder(T t, int i, int i2);

    void onBindContentViewHolder(T t, int i, int i2);

    void onBindRowColumnHeaderViewHolder(T t, int i, int i2);

    void onBindRowHeaderViewHolder(T t, int i, int i2);

    void onBindStatisticHeaderViewHolder(T t, int i, int i2);

    void onBindStatisticViewHolder(T t, int i, int i2);

    T onCreateColumnHeaderViewHolder(ViewGroup viewGroup);

    T onCreateItemViewHolder(ViewGroup viewGroup);

    T onCreateRowColumnHeaderViewHolder(ViewGroup viewGroup);

    T onCreateRowHeaderViewHolder(ViewGroup viewGroup);

    T onCreateStatisticHeaderViewHolder(ViewGroup viewGroup);

    T onCreateStatisticViewHolder(ViewGroup viewGroup);

    void onViewHolderRecycled(T t);

    void registerDataSetObserver(HealthTableDataSetObserver healthTableDataSetObserver);

    void setOnItemClickListener(HealthTableWidget.OnItemClickListener onItemClickListener);

    void setOnItemLongClickListener(HealthTableWidget.OnItemLongClickListener onItemLongClickListener);

    void setRtl(boolean z);

    void unregisterDataSetObserver(HealthTableDataSetObserver healthTableDataSetObserver);
}
