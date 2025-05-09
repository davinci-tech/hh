package com.huawei.ads.fund.db;

import android.content.ContentValues;
import android.database.Cursor;

/* loaded from: classes2.dex */
public interface TableRecord {
    void toBean(Cursor cursor);

    ContentValues toRecord();
}
