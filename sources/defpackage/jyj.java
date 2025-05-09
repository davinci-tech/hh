package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.CalendarSyncStateBean;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.RunOnTransaction;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.SecurityCalendarDbHelper;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* loaded from: classes5.dex */
public class jyj {
    private static volatile jyj c;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private final SecurityCalendarDbHelper f14197a;

    private jyj(Context context) {
        this.f14197a = SecurityCalendarDbHelper.d(context);
        b();
    }

    public static jyj e(Context context) {
        jyj jyjVar;
        if (context == null) {
            context = BaseApplication.getContext();
        }
        synchronized (d) {
            if (c == null) {
                c = new jyj(context);
            }
            jyjVar = c;
        }
        return jyjVar;
    }

    private void b() {
        if (a()) {
            return;
        }
        LogUtil.a("DeviceSyncStateDao", "createSyncStateTable: has no table.");
        this.f14197a.d();
    }

    public void e(final List<CalendarSyncStateBean> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("DeviceSyncStateDao", "insert: insert failure. list is null or empty.");
        } else if (!a()) {
            LogUtil.a("DeviceSyncStateDao", "insert: device state table do not existed.");
        } else {
            final SQLiteDatabase c2 = this.f14197a.c();
            new RunOnTransaction(c2) { // from class: jyj.3
                @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.RunOnTransaction
                public void run() {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        jyj.this.b((CalendarSyncStateBean) it.next(), c2);
                    }
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(CalendarSyncStateBean calendarSyncStateBean, SQLiteDatabase sQLiteDatabase) {
        long j;
        if (calendarSyncStateBean == null) {
            LogUtil.h("DeviceSyncStateDao", "insert: insert failure. bean is null.");
            return;
        }
        if (sQLiteDatabase == null) {
            LogUtil.h("DeviceSyncStateDao", "insert: insert failure. database is null.");
            return;
        }
        try {
            j = sQLiteDatabase.insert("calendar_sync_state", (String) null, bKR_(calendarSyncStateBean));
        } catch (SQLiteException | IllegalStateException unused) {
            LogUtil.b("DeviceSyncStateDao", "insert: occurred IllegalStateException or SQLException.");
            j = 0;
        }
        if (j == -1) {
            LogUtil.h("DeviceSyncStateDao", "insert: insert failure.");
        }
    }

    public void a(final List<String> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("DeviceSyncStateDao", "delete: delete failure. deviceIdList is null or empty.");
        } else if (!a()) {
            LogUtil.h("DeviceSyncStateDao", "delete: device state table do not existed.");
        } else {
            final SQLiteDatabase c2 = this.f14197a.c();
            new RunOnTransaction(c2) { // from class: jyj.5
                @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.RunOnTransaction
                public void run() {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        jyj.this.e((String) it.next(), c2);
                    }
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, SQLiteDatabase sQLiteDatabase) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceSyncStateDao", "delete: delete failure. deviceId is null.");
            return;
        }
        if (sQLiteDatabase == null) {
            LogUtil.h("DeviceSyncStateDao", "delete: delete failure. database is null.");
            return;
        }
        try {
            if (sQLiteDatabase.delete("calendar_sync_state", "device_id = ?", new String[]{str}) != 0) {
                return;
            }
        } catch (SQLiteException | IllegalStateException unused) {
            LogUtil.b("DeviceSyncStateDao", "delete: occurred IllegalStateException or SQLException.");
        }
        LogUtil.h("DeviceSyncStateDao", "delete: delete failure.");
    }

    public void c(final List<CalendarSyncStateBean> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("DeviceSyncStateDao", "update: update failure. beanList is null or empty.");
        } else if (!a()) {
            LogUtil.h("DeviceSyncStateDao", "update: device state table do not existed.");
        } else {
            final SQLiteDatabase c2 = this.f14197a.c();
            new RunOnTransaction(c2) { // from class: jyj.4
                @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.RunOnTransaction
                public void run() {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        jyj.this.e((CalendarSyncStateBean) it.next(), c2);
                    }
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(CalendarSyncStateBean calendarSyncStateBean, SQLiteDatabase sQLiteDatabase) {
        if (calendarSyncStateBean == null) {
            LogUtil.h("DeviceSyncStateDao", "update: update failure. bean is null");
            return;
        }
        if (sQLiteDatabase == null) {
            LogUtil.h("DeviceSyncStateDao", "update: update failure. database is null.");
            return;
        }
        try {
            if (sQLiteDatabase.update("calendar_sync_state", bKR_(calendarSyncStateBean), "device_id = ?", new String[]{calendarSyncStateBean.getDeviceIdentify()}) != 0) {
                return;
            }
        } catch (SQLiteException | IllegalStateException unused) {
            LogUtil.b("DeviceSyncStateDao", "update: occurred IllegalStateException or SQLException.");
        }
        LogUtil.h("DeviceSyncStateDao", "update: update failure.");
    }

    public List<CalendarSyncStateBean> d(List<String> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("DeviceSyncStateDao", "query: failed, deviceIdList is null or empty.");
            return Collections.emptyList();
        }
        if (!a()) {
            LogUtil.h("DeviceSyncStateDao", "query: device state table do not existed.");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        SQLiteDatabase c2 = this.f14197a.c();
        if (c2 == null) {
            LogUtil.h("DeviceSyncStateDao", "query: query failure. database is null.");
            return Collections.emptyList();
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.addAll(c(it.next(), c2));
        }
        return arrayList;
    }

    private List<CalendarSyncStateBean> c(String str, SQLiteDatabase sQLiteDatabase) {
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList(0);
        try {
            Cursor query = sQLiteDatabase.query("calendar_sync_state", null, "device_id = ?", new String[]{str}, null, null, null);
            try {
                arrayList = new ArrayList(bKS_(query));
                if (query != null) {
                    try {
                        query.close();
                    } catch (SQLiteException | IllegalStateException unused) {
                        arrayList2 = arrayList;
                        LogUtil.b("DeviceSyncStateDao", "query: occurred IllegalStateException or SQLException.");
                        arrayList = arrayList2;
                        kao.a(arrayList);
                        return arrayList;
                    }
                }
            } finally {
            }
        } catch (SQLiteException | IllegalStateException unused2) {
        }
        kao.a(arrayList);
        return arrayList;
    }

    public List<String> d() {
        if (!a()) {
            LogUtil.h("DeviceSyncStateDao", "queryAllDeviceId: device state table do not existed.");
            return new ArrayList(0);
        }
        SQLiteDatabase c2 = this.f14197a.c();
        if (c2 == null) {
            LogUtil.h("DeviceSyncStateDao", "queryAllDeviceId: query failure. database is null.");
            return new ArrayList(0);
        }
        String[] strArr = {"device_id"};
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = c2.query("calendar_sync_state", strArr, null, null, null, null, null);
            try {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(query.getString(query.getColumnIndex("device_id")));
                    } while (query.moveToNext());
                }
                if (query != null) {
                    query.close();
                }
            } catch (Throwable th) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (SQLiteException | IllegalArgumentException unused) {
            LogUtil.b("DeviceSyncStateDao", "queryAllDeviceId: occurred Exception.");
        }
        kao.a(arrayList);
        return arrayList;
    }

    private boolean a() {
        return this.f14197a.a(this.f14197a.c(), "calendar_sync_state");
    }

    private ContentValues bKR_(CalendarSyncStateBean calendarSyncStateBean) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("device_id", calendarSyncStateBean.getDeviceIdentify());
        contentValues.put("device_table_name", calendarSyncStateBean.getDeviceTableName());
        contentValues.put("device_sync_flag", Integer.valueOf(calendarSyncStateBean.getSyncAllFlag()));
        contentValues.put("minor", Long.valueOf(calendarSyncStateBean.getMinor()));
        return contentValues;
    }

    private List<CalendarSyncStateBean> bKS_(Cursor cursor) {
        if (cursor == null || !cursor.moveToFirst()) {
            Object[] objArr = new Object[2];
            objArr[0] = "getDeviceSyncStateBeansFromCursor: invalid cursor. ";
            objArr[1] = cursor == null ? Constants.NULL : Integer.valueOf(cursor.getCount());
            LogUtil.h("DeviceSyncStateDao", objArr);
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(cursor.getCount());
        do {
            arrayList.add(new CalendarSyncStateBean(cursor.getString(cursor.getColumnIndex("device_id")), cursor.getString(cursor.getColumnIndex("device_table_name")), cursor.getInt(cursor.getColumnIndex("device_sync_flag")), cursor.getLong(cursor.getColumnIndex("minor"))));
        } while (cursor.moveToNext());
        return arrayList;
    }
}
