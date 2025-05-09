package com.huawei.ui.commonui.tablewidget;

import com.huawei.ui.commonui.tablewidget.HealthTableWidget;
import com.huawei.ui.commonui.tablewidget.ViewHolder;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes6.dex */
public abstract class BaseHealthTableAdapter<T extends ViewHolder> implements HealthTableAdapter<T> {
    private final Set<HealthTableDataSetObserver> mHealthTableDataSetObservers = new HashSet();
    private boolean mIsRtl;
    private HealthTableWidget.OnItemClickListener mOnItemClickListener;
    private HealthTableWidget.OnItemLongClickListener mOnItemLongClickListener;

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public int getColumnCount() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public int getColumnHeaderNum() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public int getColumnWidthValueType() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public int getRowCount() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public int getRowHeaderNum() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public int getRowHeightValueType() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public int getStatisticNum() {
        return 0;
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public void onViewHolderRecycled(T t) {
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public HealthTableWidget.OnItemClickListener getOnItemClickListener() {
        return this.mOnItemClickListener;
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public void setOnItemClickListener(HealthTableWidget.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public HealthTableWidget.OnItemLongClickListener getOnItemLongClickListener() {
        return this.mOnItemLongClickListener;
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public void setOnItemLongClickListener(HealthTableWidget.OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public void registerDataSetObserver(HealthTableDataSetObserver healthTableDataSetObserver) {
        if (healthTableDataSetObserver != null) {
            this.mHealthTableDataSetObservers.add(healthTableDataSetObserver);
        }
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public void unregisterDataSetObserver(HealthTableDataSetObserver healthTableDataSetObserver) {
        this.mHealthTableDataSetObservers.remove(healthTableDataSetObserver);
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableDataSetObserver
    public void notifyDataSetChanged() {
        Iterator<HealthTableDataSetObserver> it = this.mHealthTableDataSetObservers.iterator();
        while (it.hasNext()) {
            it.next().notifyDataSetChanged();
        }
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableDataSetObserver
    public void notifyItemChanged(int i, int i2) {
        Iterator<HealthTableDataSetObserver> it = this.mHealthTableDataSetObservers.iterator();
        while (it.hasNext()) {
            it.next().notifyItemChanged(i, i2);
        }
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableDataSetObserver
    public void notifyRowChanged(int i) {
        Iterator<HealthTableDataSetObserver> it = this.mHealthTableDataSetObservers.iterator();
        while (it.hasNext()) {
            it.next().notifyRowChanged(i);
        }
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableDataSetObserver
    public void notifyColumnChanged(int i) {
        Iterator<HealthTableDataSetObserver> it = this.mHealthTableDataSetObservers.iterator();
        while (it.hasNext()) {
            it.next().notifyColumnChanged(i);
        }
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableDataSetObserver
    public void notifyLayoutChanged() {
        Iterator<HealthTableDataSetObserver> it = this.mHealthTableDataSetObservers.iterator();
        while (it.hasNext()) {
            it.next().notifyLayoutChanged();
        }
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public boolean isRtl() {
        return this.mIsRtl;
    }

    @Override // com.huawei.ui.commonui.tablewidget.HealthTableAdapter
    public void setRtl(boolean z) {
        this.mIsRtl = z;
    }
}
