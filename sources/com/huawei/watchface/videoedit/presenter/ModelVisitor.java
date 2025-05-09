package com.huawei.watchface.videoedit.presenter;

import com.huawei.watchface.videoedit.param.TemplateModel;

/* loaded from: classes9.dex */
public abstract class ModelVisitor<T> {
    private final int flag;
    protected T selfStorage;

    public abstract void onDataSetChanged(TemplateModel templateModel);

    public ModelVisitor(int i) {
        this.flag = i;
    }

    public T getVisitorModel() {
        return this.selfStorage;
    }

    public int getFlag() {
        return this.flag;
    }
}
