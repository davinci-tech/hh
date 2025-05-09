package com.huawei.health.socialshare.model;

import android.graphics.Bitmap;
import android.view.View;
import defpackage.fed;

/* loaded from: classes4.dex */
public abstract class EditShareCommonView {
    public abstract int getBitmap();

    public abstract boolean getIsDefaultSource();

    public abstract boolean getIsNeedHide();

    public abstract String getSourcePath();

    public abstract View getView();

    public abstract int getWatermarkId();

    public abstract void refreshData(fed fedVar);

    public abstract void refreshTopUi(int i);

    public abstract void refreshUi(int i, int i2);

    public abstract void setBitmap(int i);

    public abstract void setIsDefaultSource(boolean z);

    public abstract void setSourcePath(String str);

    public abstract void setUserInfo(Bitmap bitmap, String str);

    public abstract void setWatermarkId(int i);
}
