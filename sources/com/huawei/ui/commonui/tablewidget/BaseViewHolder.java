package com.huawei.ui.commonui.tablewidget;

import android.view.View;
import java.util.Objects;

/* loaded from: classes6.dex */
public abstract class BaseViewHolder implements ViewHolder {
    private int mColumnIndex;
    private int mItemType;
    private final View mItemView;
    private int mRowIndex;

    public BaseViewHolder(View view) {
        this.mItemView = view;
    }

    @Override // com.huawei.ui.commonui.tablewidget.ViewHolder
    public View getItemView() {
        return this.mItemView;
    }

    @Override // com.huawei.ui.commonui.tablewidget.ViewHolder
    public int getRowIndex() {
        return this.mRowIndex;
    }

    @Override // com.huawei.ui.commonui.tablewidget.ViewHolder
    public void setRowIndex(int i) {
        this.mRowIndex = i;
    }

    @Override // com.huawei.ui.commonui.tablewidget.ViewHolder
    public int getColumnIndex() {
        return this.mColumnIndex;
    }

    @Override // com.huawei.ui.commonui.tablewidget.ViewHolder
    public void setColumnIndex(int i) {
        this.mColumnIndex = i;
    }

    @Override // com.huawei.ui.commonui.tablewidget.ViewHolder
    public int getItemType() {
        return this.mItemType;
    }

    @Override // com.huawei.ui.commonui.tablewidget.ViewHolder
    public void setItemType(int i) {
        this.mItemType = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BaseViewHolder baseViewHolder = (BaseViewHolder) obj;
        return this.mRowIndex == baseViewHolder.mRowIndex && this.mColumnIndex == baseViewHolder.mColumnIndex && this.mItemType == baseViewHolder.mItemType && this.mItemView.equals(baseViewHolder.mItemView);
    }

    public int hashCode() {
        return Objects.hash(this.mItemView, Integer.valueOf(this.mRowIndex), Integer.valueOf(this.mColumnIndex), Integer.valueOf(this.mItemType));
    }
}
