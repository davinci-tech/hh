package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.db.RunOnTransaction;
import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.db.SecurityContactsDbHelper;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* loaded from: classes5.dex */
public class jzu {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14227a = new Object();
    private static volatile jzu d;
    private final SecurityContactsDbHelper b;

    private jzu(Context context) {
        this.b = SecurityContactsDbHelper.c(context);
        a();
    }

    public static jzu d(Context context) {
        jzu jzuVar;
        if (context == null) {
            context = BaseApplication.getContext();
        }
        synchronized (f14227a) {
            if (d == null) {
                d = new jzu(context);
            }
            jzuVar = d;
        }
        return jzuVar;
    }

    private void a() {
        if (e()) {
            return;
        }
        LogUtil.a("DeviceSyncStateDao", "createSyncStateTable: has no table.");
        this.b.c();
    }

    public void b(final List<jzv> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("DeviceSyncStateDao", "insert: insert failure. list is null or empty.");
        } else if (!e()) {
            LogUtil.a("DeviceSyncStateDao", "insert: device state table do not existed.");
        } else {
            final SQLiteDatabase e = this.b.e();
            new RunOnTransaction(e) { // from class: jzu.1
                @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.db.RunOnTransaction
                public void run() {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        jzu.this.c((jzv) it.next(), e);
                    }
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(jzv jzvVar, SQLiteDatabase sQLiteDatabase) {
        long j;
        if (jzvVar == null) {
            LogUtil.h("DeviceSyncStateDao", "insert: insert failure. bean is null.");
            return;
        }
        if (sQLiteDatabase == null) {
            LogUtil.h("DeviceSyncStateDao", "insert: insert failure. database is null.");
            return;
        }
        try {
            j = sQLiteDatabase.insert("contact_sync_state", (String) null, bMd_(jzvVar));
        } catch (SQLException | IllegalStateException unused) {
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
        } else if (!e()) {
            LogUtil.h("DeviceSyncStateDao", "delete: device state table do not existed.");
        } else {
            final SQLiteDatabase e = this.b.e();
            new RunOnTransaction(e) { // from class: jzu.2
                @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.db.RunOnTransaction
                public void run() {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        jzu.this.b((String) it.next(), e);
                    }
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, SQLiteDatabase sQLiteDatabase) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceSyncStateDao", "delete: delete failure. deviceId is null.");
            return;
        }
        if (sQLiteDatabase == null) {
            LogUtil.h("DeviceSyncStateDao", "delete: delete failure. database is null.");
            return;
        }
        try {
            if (sQLiteDatabase.delete("contact_sync_state", "device_id = ?", new String[]{str}) != 0) {
                return;
            }
        } catch (SQLException | IllegalStateException unused) {
            LogUtil.b("DeviceSyncStateDao", "delete: occurred IllegalStateException or SQLException.");
        }
        LogUtil.h("DeviceSyncStateDao", "delete: delete failure.");
    }

    public void c(final List<jzv> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("DeviceSyncStateDao", "update: update failure. beanList is null or empty.");
        } else if (!e()) {
            LogUtil.h("DeviceSyncStateDao", "update: device state table do not existed.");
        } else {
            final SQLiteDatabase e = this.b.e();
            new RunOnTransaction(e) { // from class: jzu.3
                @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.db.RunOnTransaction
                public void run() {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        jzu.this.a((jzv) it.next(), e);
                    }
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(jzv jzvVar, SQLiteDatabase sQLiteDatabase) {
        if (jzvVar == null) {
            LogUtil.h("DeviceSyncStateDao", "update: update failure. bean is null");
            return;
        }
        if (sQLiteDatabase == null) {
            LogUtil.h("DeviceSyncStateDao", "update: update failure. database is null.");
            return;
        }
        try {
            if (sQLiteDatabase.update("contact_sync_state", bMd_(jzvVar), "device_id = ?", new String[]{jzvVar.d()}) != 0) {
                return;
            }
        } catch (SQLException | IllegalStateException unused) {
            LogUtil.b("DeviceSyncStateDao", "update: occurred IllegalStateException or SQLException.");
        }
        LogUtil.h("DeviceSyncStateDao", "update: update failure.");
    }

    public void d(final List<jzv> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("DeviceSyncStateDao", "updateOrInsert: updateOrInsert failure. beanList is null or empty.");
        } else if (!e()) {
            LogUtil.h("DeviceSyncStateDao", "updateOrInsert: device state table do not existed.");
        } else {
            final SQLiteDatabase e = this.b.e();
            new RunOnTransaction(e) { // from class: jzu.5
                @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.db.RunOnTransaction
                public void run() {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        jzu.this.d((jzv) it.next(), e);
                    }
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(jzv jzvVar, SQLiteDatabase sQLiteDatabase) {
        if (jzvVar == null) {
            LogUtil.h("DeviceSyncStateDao", "updateOrInsert: failed, bean is null");
            return;
        }
        if (sQLiteDatabase == null) {
            LogUtil.h("DeviceSyncStateDao", "updateOrInsert: updateOrInsert failure. database is null.");
        } else if (c(jzvVar.d(), sQLiteDatabase).isEmpty()) {
            c(jzvVar, sQLiteDatabase);
        } else {
            a(jzvVar, sQLiteDatabase);
        }
    }

    public List<jzv> e(List<String> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.h("DeviceSyncStateDao", "query: failed, deviceIdList is null or empty.");
            return Collections.emptyList();
        }
        if (!e()) {
            LogUtil.h("DeviceSyncStateDao", "query: device state table do not existed.");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        SQLiteDatabase e = this.b.e();
        if (e == null) {
            LogUtil.h("DeviceSyncStateDao", "query: query failure. database is null.");
            return Collections.emptyList();
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.addAll(c(it.next(), e));
        }
        return arrayList;
    }

    private List<jzv> c(String str, SQLiteDatabase sQLiteDatabase) {
        Cursor query;
        ArrayList arrayList = new ArrayList(0);
        try {
            query = sQLiteDatabase.query("contact_sync_state", null, "device_id = ?", new String[]{str}, null, null, null);
        } catch (SQLException | IllegalStateException unused) {
        }
        try {
            ArrayList arrayList2 = new ArrayList(bMe_(query));
            if (query != null) {
                try {
                    query.close();
                } catch (SQLException | IllegalStateException unused2) {
                    arrayList = arrayList2;
                    LogUtil.b("DeviceSyncStateDao", "query: occurred IllegalStateException or SQLException.");
                    arrayList2 = arrayList;
                    kao.a(arrayList2);
                    return arrayList2;
                }
            }
            kao.a(arrayList2);
            return arrayList2;
        } finally {
        }
    }

    public List<String> c() {
        if (!e()) {
            LogUtil.h("DeviceSyncStateDao", "queryAllDeviceId: device state table do not existed.");
            return new ArrayList(0);
        }
        SQLiteDatabase e = this.b.e();
        if (e == null) {
            LogUtil.h("DeviceSyncStateDao", "queryAllDeviceId: query failure. database is null.");
            return new ArrayList(0);
        }
        String[] strArr = {"device_id"};
        ArrayList arrayList = new ArrayList(16);
        try {
            Cursor query = e.query("contact_sync_state", strArr, null, null, null, null, null);
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
        } catch (SQLException | IllegalArgumentException unused) {
            LogUtil.b("DeviceSyncStateDao", "queryAllDeviceId: occurred Exception.");
        }
        kao.a(arrayList);
        return arrayList;
    }

    private boolean e() {
        return this.b.a(this.b.e(), "contact_sync_state");
    }

    private ContentValues bMd_(jzv jzvVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("device_id", jzvVar.d());
        contentValues.put("device_table_name", jzvVar.b());
        contentValues.put("device_sync_flag", Integer.valueOf(jzvVar.a()));
        contentValues.put("device_sync_state", Integer.valueOf(jzvVar.e()));
        contentValues.put("device_last_sync_time", Long.valueOf(jzvVar.c()));
        return contentValues;
    }

    private List<jzv> bMe_(Cursor cursor) {
        if (cursor == null || !cursor.moveToFirst()) {
            Object[] objArr = new Object[2];
            objArr[0] = "getDeviceSyncStateBeansFromCursor: invalid cursor. ";
            objArr[1] = cursor == null ? Constants.NULL : Integer.valueOf(cursor.getCount());
            LogUtil.h("DeviceSyncStateDao", objArr);
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(cursor.getCount());
        do {
            arrayList.add(new jzv(cursor.getString(cursor.getColumnIndex("device_id")), cursor.getString(cursor.getColumnIndex("device_table_name")), cursor.getInt(cursor.getColumnIndex("device_sync_flag")), cursor.getInt(cursor.getColumnIndex("device_sync_state")), cursor.getLong(cursor.getColumnIndex("device_last_sync_time"))));
        } while (cursor.moveToNext());
        return arrayList;
    }
}
