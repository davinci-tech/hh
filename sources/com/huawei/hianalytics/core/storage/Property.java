package com.huawei.hianalytics.core.storage;

/* loaded from: classes4.dex */
public class Property {
    public final String columnName;
    public int index;
    public final Class<?> type;

    public Property(Class<?> cls, String str, int i) {
        this.type = cls;
        this.columnName = str;
        this.index = i;
    }

    public Property(Class<?> cls, String str) {
        this(cls, str, 0);
    }
}
