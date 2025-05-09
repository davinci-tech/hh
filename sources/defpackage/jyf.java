package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.huawei.agconnect.apms.collect.model.HeaderType;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.CalendarDbBean;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.RunOnTransaction;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.SecurityCalendarDbHelper;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* loaded from: classes5.dex */
public class jyf {
    private static final Object b = new Object();
    private static volatile jyf d;

    /* renamed from: a, reason: collision with root package name */
    private final SecurityCalendarDbHelper f14191a;

    private jyf(Context context) {
        this.f14191a = SecurityCalendarDbHelper.d(context);
    }

    public static jyf d(Context context) {
        jyf jyfVar;
        if (context == null) {
            context = BaseApplication.getContext();
        }
        synchronized (b) {
            if (d == null) {
                d = new jyf(context);
            }
            jyfVar = d;
        }
        return jyfVar;
    }

    public void a(String str) {
        if (e(str)) {
            return;
        }
        this.f14191a.b(str);
    }

    public void d(final List<CalendarDbBean> list, final String str) {
        LogUtil.a("SimpleCalendarDao", "insert: ");
        if (list == null || list.isEmpty() || TextUtils.isEmpty(str)) {
            LogUtil.h("SimpleCalendarDao", "insert: failure, list/tableName is null or empty.");
            return;
        }
        a(str);
        final SQLiteDatabase c = this.f14191a.c();
        new RunOnTransaction(c) { // from class: jyf.2
            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.RunOnTransaction
            public void run() {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    jyf.this.a((CalendarDbBean) it.next(), str, c);
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CalendarDbBean calendarDbBean, String str, SQLiteDatabase sQLiteDatabase) {
        long j;
        if (calendarDbBean == null || TextUtils.isEmpty(str) || sQLiteDatabase == null) {
            LogUtil.h("SimpleCalendarDao", "insert: failure, input parameters contain null.");
            return;
        }
        try {
            j = sQLiteDatabase.insert(str, (String) null, bKU_(calendarDbBean));
        } catch (SQLiteException | IllegalStateException unused) {
            LogUtil.b("SimpleCalendarDao", "insert: occurred IllegalStateException or SQLiteException.");
            j = -1;
        }
        LogUtil.a("SimpleCalendarDao", "insert: result: ", Long.valueOf(j));
    }

    public void c(final String str, final String str2) {
        LogUtil.a("SimpleCalendarDao", "delete: ");
        if (str == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("SimpleCalendarDao", "delete: rawContactUidList/tableName is null or empty.");
            return;
        }
        a(str2);
        final SQLiteDatabase c = this.f14191a.c();
        new RunOnTransaction(c) { // from class: jyf.4
            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.RunOnTransaction
            public void run() {
                jyf.this.c(str, str2, c);
            }
        }.start();
    }

    public void c(String str, String str2, SQLiteDatabase sQLiteDatabase) {
        int i;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || sQLiteDatabase == null) {
            LogUtil.h("SimpleCalendarDao", "delete: delete failure. input parameters contain null.");
            return;
        }
        try {
            i = sQLiteDatabase.delete(str2, "event_uuid = ?", new String[]{str});
        } catch (SQLiteException | IllegalStateException unused) {
            LogUtil.b("SimpleCalendarDao", "delete: occurred IllegalStateException or SQLiteException.");
            i = -1;
        }
        LogUtil.a("SimpleCalendarDao", "delete: result: ", Integer.valueOf(i));
    }

    private void c(CalendarDbBean calendarDbBean, String str, SQLiteDatabase sQLiteDatabase) {
        int i;
        if (calendarDbBean == null || TextUtils.isEmpty(str) || sQLiteDatabase == null) {
            LogUtil.h("SimpleCalendarDao", "update: failure, input parameters contains null.");
            return;
        }
        try {
            i = sQLiteDatabase.update(str, bKU_(calendarDbBean), "event_uuid = ?", new String[]{calendarDbBean.getEventUuid()});
        } catch (SQLiteException | IllegalStateException unused) {
            LogUtil.b("SimpleCalendarDao", "update: occurred IllegalStateException or SQLiteException.");
            i = -1;
        }
        LogUtil.a("SimpleCalendarDao", "update: result: ", Integer.valueOf(i));
    }

    public void e(final List<CalendarDbBean> list, final String str) {
        LogUtil.a("SimpleCalendarDao", "updateOrInsert: ");
        if (list != null && !list.isEmpty() && !TextUtils.isEmpty(str)) {
            LogUtil.a("SimpleCalendarDao", "updateOrInsert beans size: ", Integer.valueOf(list.size()));
            a(str);
            final SQLiteDatabase c = this.f14191a.c();
            new RunOnTransaction(c) { // from class: jyf.1
                @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.RunOnTransaction
                public void run() {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        jyf.this.e((CalendarDbBean) it.next(), str, c);
                    }
                }
            }.start();
            return;
        }
        LogUtil.h("SimpleCalendarDao", "updateOrInsert: failure, bean/tableName is null.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(CalendarDbBean calendarDbBean, String str, SQLiteDatabase sQLiteDatabase) {
        if (calendarDbBean == null || TextUtils.isEmpty(str) || sQLiteDatabase == null) {
            LogUtil.h("SimpleCalendarDao", "updateOrInsert: failure, input parameters contain null.");
        } else if (a(calendarDbBean.getEventUuid(), str, sQLiteDatabase).isEmpty()) {
            a(calendarDbBean, str, sQLiteDatabase);
        } else {
            c(calendarDbBean, str, sQLiteDatabase);
        }
    }

    private List<CalendarDbBean> a(String str, String str2, SQLiteDatabase sQLiteDatabase) {
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList(0);
        try {
            Cursor query = sQLiteDatabase.query(str2, null, "event_uuid = ?", new String[]{str}, null, null, null);
            try {
                arrayList = new ArrayList(bKV_(query));
                if (query != null) {
                    try {
                        query.close();
                    } catch (SQLiteException | IllegalStateException unused) {
                        arrayList2 = arrayList;
                        LogUtil.b("SimpleCalendarDao", "query: occurred IllegalStateException or SQLiteException.");
                        arrayList = arrayList2;
                        kao.a(arrayList);
                        return new ArrayList(arrayList);
                    }
                }
            } finally {
            }
        } catch (SQLiteException | IllegalStateException unused2) {
        }
        kao.a(arrayList);
        return new ArrayList(arrayList);
    }

    public List<CalendarDbBean> c(String str) {
        Cursor query;
        LogUtil.a("SimpleCalendarDao", "queryAll:");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SimpleCalendarDao", "queryAll: failure, tableName is null or empty");
            return new ArrayList(0);
        }
        a(str);
        SQLiteDatabase c = this.f14191a.c();
        ArrayList arrayList = new ArrayList(0);
        try {
            query = c.query(str, null, null, null, null, null, null);
        } catch (SQLiteException | IllegalStateException unused) {
        }
        try {
            ArrayList arrayList2 = new ArrayList(bKV_(query));
            if (query != null) {
                try {
                    query.close();
                } catch (SQLiteException | IllegalStateException unused2) {
                    arrayList = arrayList2;
                    LogUtil.b("SimpleCalendarDao", "queryAll: occurred IllegalStateException or SQLiteException.");
                    arrayList2 = arrayList;
                    kao.a(arrayList2);
                    return new ArrayList(arrayList2);
                }
            }
            kao.a(arrayList2);
            return new ArrayList(arrayList2);
        } finally {
        }
    }

    public boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SimpleCalendarDao", "hasTable: deviceId is null or empty.");
            return false;
        }
        return this.f14191a.a(this.f14191a.c(), str);
    }

    private List<CalendarDbBean> bKV_(Cursor cursor) {
        if (bKW_(cursor)) {
            LogUtil.h("SimpleCalendarDao", "getSimpleContactBeansFromCursor: cursor is null or cannot move.");
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(cursor.getCount());
        do {
            arrayList.add(new CalendarDbBean(cursor.getString(cursor.getColumnIndex("event_uuid")), cursor.getInt(cursor.getColumnIndex("operation")), cursor.getString(cursor.getColumnIndex(HeaderType.EVENT_ID)), cursor.getLong(cursor.getColumnIndex("dtstart")), cursor.getLong(cursor.getColumnIndex("dtend")), cursor.getInt(cursor.getColumnIndex("all_day")), cursor.getString(cursor.getColumnIndex("calendarFeature"))));
        } while (cursor.moveToNext());
        return arrayList;
    }

    public static boolean bKW_(Cursor cursor) {
        return cursor == null || !cursor.moveToFirst();
    }

    private ContentValues bKU_(CalendarDbBean calendarDbBean) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("event_uuid", calendarDbBean.getEventUuid());
        contentValues.put("operation", Integer.valueOf(calendarDbBean.getOperation()));
        contentValues.put(HeaderType.EVENT_ID, calendarDbBean.getEventId());
        contentValues.put("dtstart", Long.valueOf(calendarDbBean.getDtstart()));
        contentValues.put("dtend", Long.valueOf(calendarDbBean.getDtend()));
        contentValues.put("all_day", Integer.valueOf(calendarDbBean.getAllDay()));
        contentValues.put("calendarFeature", calendarDbBean.getCalendarFeature());
        return contentValues;
    }
}
