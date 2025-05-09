package com.huawei.ads.fund.db;

import android.text.TextUtils;
import com.huawei.openplatform.abl.log.HiAdLog;
import defpackage.wj;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes2.dex */
public abstract class BaseDbUpdateHelper {
    protected final BaseDbHelper mDbHelper;
    public final List<Table> oldTableList = new ArrayList(4);
    public final List<Table> tableList = new ArrayList(4);

    protected void createTriggers() {
    }

    protected abstract String getTag();

    void c() {
        for (Table table : this.tableList) {
            String tableName = table.getTableName();
            if (this.mDbHelper.e(tableName)) {
                this.mDbHelper.f(tableName);
                HiAdLog.i(getTag(), "%s exist modify table successfully.", tableName);
                try {
                    this.mDbHelper.executeSQL(table.getTableScheme());
                    a(tableName);
                    HiAdLog.i(getTag(), "insert data to %s successfully.", tableName);
                    this.mDbHelper.c(tableName);
                    HiAdLog.i(getTag(), "drop table temp table successfully.");
                } catch (wj unused) {
                    throw new wj(String.format(Locale.ENGLISH, "table exist, init table tableName: %s failed", tableName.trim()));
                }
            } else {
                try {
                    this.mDbHelper.executeSQL(table.getTableScheme());
                } catch (wj unused2) {
                    throw new wj(String.format(Locale.ENGLISH, "table is not exist, init table tableName: %s failed", tableName.trim()));
                }
            }
        }
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String replaceAll = str.replaceAll("_temp_", "");
        Iterator<Table> it = this.tableList.iterator();
        while (it.hasNext()) {
            if (replaceAll.equals(it.next().getTableName())) {
                return true;
            }
        }
        Iterator<Table> it2 = this.oldTableList.iterator();
        while (it2.hasNext()) {
            if (replaceAll.equals(it2.next().getTableName())) {
                return true;
            }
        }
        return false;
    }

    void b() {
        Iterator<Table> it = this.oldTableList.iterator();
        while (it.hasNext()) {
            String tableName = it.next().getTableName();
            try {
                if (this.mDbHelper.e(tableName)) {
                    this.mDbHelper.b(tableName);
                }
            } catch (wj unused) {
                HiAdLog.w(getTag(), "delete table fail");
            }
        }
        for (Table table : this.tableList) {
            try {
                this.mDbHelper.executeSQL(table.getTableScheme());
            } catch (wj unused2) {
                HiAdLog.w(getTag(), "create table %s failed", table.getTableName());
            }
        }
    }

    void a() {
        for (Table table : this.tableList) {
            this.mDbHelper.delete(table.getTableName(), table.getExpireCleanWhereClause(), table.getExpireCleanWhereArgs());
        }
    }

    private void a(String str) {
        StringBuilder sb = new StringBuilder(" INSERT INTO ");
        sb.append(str);
        sb.append(" SELECT ");
        try {
            try {
                String a2 = a(this.mDbHelper.d(str), this.mDbHelper.d("_temp_" + str));
                if (a2 == null) {
                    throw new wj("insert data sql is null");
                }
                sb.append(a2);
                sb.append(" FROM _temp_");
                sb.append(str);
                try {
                    this.mDbHelper.executeSQL(sb.toString());
                } catch (wj unused) {
                    throw new wj(getTag() + " insertData mDbHelper.executeSQL error");
                }
            } catch (wj unused2) {
                throw new wj(String.format(Locale.ENGLISH, "get temp table %s column names failed", str.trim()));
            }
        } catch (wj unused3) {
            throw new wj(String.format(Locale.ENGLISH, "get table %s column names failed", str.trim()));
        }
    }

    private String a(String[] strArr, String[] strArr2) {
        StringBuilder sb = new StringBuilder();
        List arrayList = new ArrayList(4);
        if (strArr2 != null) {
            arrayList = Arrays.asList(strArr2);
        }
        if (strArr == null || strArr.length <= 0 || strArr2 == null) {
            return null;
        }
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (!arrayList.contains(str)) {
                str = "\"\"";
            }
            sb.append(str);
            if (i != strArr.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public BaseDbUpdateHelper(BaseDbHelper baseDbHelper) {
        this.mDbHelper = baseDbHelper;
    }
}
