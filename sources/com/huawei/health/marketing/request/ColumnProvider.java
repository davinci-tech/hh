package com.huawei.health.marketing.request;

import android.content.Context;
import java.util.List;

/* loaded from: classes3.dex */
public class ColumnProvider {
    private static volatile ColumnProvider mColumnProvider;
    private List<CustomConfigValue> mFavoriteList;

    private ColumnProvider(Context context) {
    }

    public static ColumnProvider getInstance(Context context) {
        if (mColumnProvider == null) {
            synchronized (ColumnProvider.class) {
                if (mColumnProvider == null) {
                    mColumnProvider = new ColumnProvider(context);
                }
            }
        }
        return mColumnProvider;
    }

    public List<CustomConfigValue> getFavoriteList() {
        return this.mFavoriteList;
    }

    public void setFavoriteList(List<CustomConfigValue> list) {
        this.mFavoriteList = list;
    }
}
