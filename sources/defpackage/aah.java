package defpackage;

import android.database.Cursor;
import com.huawei.android.hicloud.sync.c.a.b.e;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public class aah extends e<aac> {
    public String d(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor c = c("select distinct prepareTraceId from pre_records where syncType = ?", new String[]{str});
            if (c != null) {
                try {
                    if (c.moveToFirst()) {
                        do {
                            arrayList.add(c.getString(0));
                        } while (c.moveToNext());
                    }
                } finally {
                }
            }
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            abd.b("PreRecordOperator", "queryPrepareTraceId error: " + e.toString());
        }
        return !arrayList.isEmpty() ? (String) arrayList.get(0) : "";
    }

    public int a(String str) {
        try {
            Cursor c = c("select sum(times) as num from pre_records where syncType = ?", new String[]{str});
            if (c != null && c.moveToFirst()) {
                return c.getInt(0);
            }
        } catch (Exception e) {
            abd.b("PreRecordOperator", "countRecords error " + e.getMessage());
        }
        return 0;
    }

    public void e(String str) {
        abd.c("PreRecordOperator", "clearByType, syncType = " + str);
        try {
            a("DELETE FROM pre_records where syncType = ?", new String[]{str});
        } catch (Exception e) {
            abd.b("PreRecordOperator", "clearByType error " + e.getMessage());
        }
    }

    public long c(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor c = c("select endTime from pre_records where syncType = ? order by endTime desc", new String[]{str});
            if (c != null && c.moveToFirst()) {
                arrayList.add(Long.valueOf(c.getLong(0)));
            }
        } catch (Exception e) {
            abd.b("PreRecordOperator", "getLatestEndTime error " + e.getMessage());
        }
        if (arrayList.isEmpty()) {
            return 0L;
        }
        return ((Long) arrayList.get(0)).longValue();
    }

    public void d() {
        abd.c("PreRecordOperator", "clearAll");
        try {
            a("DELETE FROM pre_records", (String[]) null);
        } catch (Exception e) {
            abd.b("PreRecordOperator", "clearAll error " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.huawei.android.hicloud.sync.c.a.b.e
    /* renamed from: eR_, reason: merged with bridge method [inline-methods] */
    public aac a(Cursor cursor) {
        aac aacVar = new aac();
        aacVar.e(cursor.getString(0));
        aacVar.d(cursor.getString(1));
        aacVar.c(cursor.getInt(2));
        aacVar.a(cursor.getLong(3));
        aacVar.e(cursor.getLong(4));
        aacVar.a(cursor.getString(5));
        aacVar.a(cursor.getInt(6));
        return aacVar;
    }
}
