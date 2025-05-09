package com.huawei.ads.fund.db;

/* loaded from: classes2.dex */
public interface Table {
    String[] getExpireCleanWhereArgs();

    String getExpireCleanWhereClause();

    long getMaxStoreTime();

    String getTableName();

    String getTableScheme();
}
