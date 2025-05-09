package com.huawei.openalliance.ad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.huawei.openalliance.ad.db.bean.AppDataCollectionRecord;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class em extends ep {
    private static em c;
    private static final byte[] d = new byte[0];
    private static final byte[] e = new byte[0];

    public Map<String, Long> d() {
        Object th;
        dd ddVar;
        HashMap hashMap = new HashMap();
        Cursor cursor = null;
        try {
            ddVar = dd.a(this.f6846a);
            try {
                cursor = ddVar.a("SELECT eventType, COUNT(*) as cnt FROM " + a().getSimpleName() + " GROUP BY eventType", (String[]) null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        int columnIndex = cursor.getColumnIndex("eventType");
                        String string = columnIndex != -1 ? cursor.getString(columnIndex) : "";
                        int columnIndex2 = cursor.getColumnIndex("cnt");
                        long j = columnIndex2 != -1 ? cursor.getLong(columnIndex2) : 0L;
                        if (!com.huawei.openalliance.ad.utils.cz.b(string) && j > 0) {
                            hashMap.put(string, Long.valueOf(j));
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    ho.c("AppDataCollectionRecordDao", "query cached app data exception: %s", th.getClass().getSimpleName());
                    return hashMap;
                } finally {
                    a(cursor);
                    a(ddVar);
                }
            }
        } catch (Throwable th3) {
            th = th3;
            ddVar = null;
        }
        return hashMap;
    }

    public long c() {
        return a(a());
    }

    public void b() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(5, -15);
        ho.b("AppDataCollectionRecordDao", "delete expire records over 15 days, expire times: %s", calendar.getTime());
        a(a(), fi.APP_DATA_COLLECTION_DELETE_EXPIRE_WHERE, new String[]{String.valueOf(calendar.getTime().getTime())});
    }

    public int b(List<String> list) {
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            ho.b("AppDataCollectionRecordDao", "deleteRecords with empty id list");
            return 0;
        }
        ho.a("AppDataCollectionRecordDao", "deleteRecords size is : %s", Integer.valueOf(list.size()));
        return a(a(), fi.APP_DATA_COLLECTION_DELETE_WHERE, list);
    }

    public List<AppDataCollectionRecord> a(int i) {
        if (i <= 0) {
            i = 50;
        }
        return a(a(), null, null, null, "reportTimestamp DESC", String.valueOf(i));
    }

    protected Class<? extends com.huawei.openalliance.ad.db.bean.a> a() {
        return AppDataCollectionRecord.class;
    }

    public long a(List<AppDataCollectionRecord> list) {
        long a2;
        long currentTimeMillis = System.currentTimeMillis();
        if (com.huawei.openalliance.ad.utils.bg.a(list) || this.f6846a == null) {
            return -1L;
        }
        synchronized (d) {
            ArrayList arrayList = new ArrayList();
            Iterator<AppDataCollectionRecord> it = list.iterator();
            while (it.hasNext()) {
                try {
                    ContentValues d2 = it.next().d(this.f6846a);
                    if (d2 != null) {
                        arrayList.add(d2);
                    }
                } catch (Exception unused) {
                    ho.c("AppDataCollectionRecordDao", "convert to record error");
                }
            }
            a2 = a(a(), arrayList);
        }
        ho.a("AppDataCollectionRecordDao", "insertAppDataRecords duration: %s ms", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return a2;
    }

    public static em a(Context context) {
        em emVar;
        synchronized (e) {
            if (c == null) {
                c = new em(context);
            }
            emVar = c;
        }
        return emVar;
    }

    public em(Context context) {
        super(context);
    }
}
