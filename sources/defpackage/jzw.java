package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.db.RunOnTransaction;
import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.db.SecurityContactsDbHelper;
import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.ContactsDatabaseUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.zetetic.database.sqlcipher.SQLiteDatabase;

/* loaded from: classes5.dex */
public class jzw {
    private static final Object c = new Object();
    private static volatile jzw e;

    /* renamed from: a, reason: collision with root package name */
    private final SecurityContactsDbHelper f14231a;

    private jzw(Context context) {
        this.f14231a = SecurityContactsDbHelper.c(context);
    }

    public static jzw b(Context context) {
        jzw jzwVar;
        if (context == null) {
            context = BaseApplication.getContext();
        }
        synchronized (c) {
            if (e == null) {
                e = new jzw(context);
            }
            jzwVar = e;
        }
        return jzwVar;
    }

    public void a(String str) {
        if (e(str)) {
            return;
        }
        this.f14231a.c(str);
    }

    public void a(final List<jzt> list, final String str) {
        LogUtil.a("SimpleContactsDao", "insert: ");
        if (list == null || list.isEmpty() || TextUtils.isEmpty(str)) {
            LogUtil.h("SimpleContactsDao", "insert: failure, list/tableName is null or empty.");
            return;
        }
        a(str);
        final SQLiteDatabase e2 = this.f14231a.e();
        new RunOnTransaction(e2) { // from class: jzw.3
            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.db.RunOnTransaction
            public void run() {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    jzw.this.a((jzt) it.next(), str, e2);
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(jzt jztVar, String str, SQLiteDatabase sQLiteDatabase) {
        long j;
        if (jztVar == null || TextUtils.isEmpty(str) || sQLiteDatabase == null) {
            LogUtil.h("SimpleContactsDao", "insert: failure, input parameters contain null.");
            return;
        }
        try {
            j = sQLiteDatabase.insert(str, (String) null, bMg_(jztVar));
        } catch (SQLException | IllegalStateException unused) {
            LogUtil.b("SimpleContactsDao", "insert: occurred IllegalStateException or SQLException.");
            j = -1;
        }
        LogUtil.a("SimpleContactsDao", "insert: result: ", Long.valueOf(j));
    }

    public void c(final List<String> list, final String str) {
        LogUtil.a("SimpleContactsDao", "delete: ");
        if (list == null || list.isEmpty() || TextUtils.isEmpty(str)) {
            LogUtil.h("SimpleContactsDao", "delete: rawContactUidList/tableName is null or empty.");
            return;
        }
        a(str);
        final SQLiteDatabase e2 = this.f14231a.e();
        new RunOnTransaction(e2) { // from class: jzw.2
            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.db.RunOnTransaction
            public void run() {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    jzw.this.e((String) it.next(), str, e2);
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, String str2, SQLiteDatabase sQLiteDatabase) {
        int i;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || sQLiteDatabase == null) {
            LogUtil.h("SimpleContactsDao", "delete: delete failure. input parameters contain null.");
            return;
        }
        try {
            i = sQLiteDatabase.delete(str2, "raw_contact_uid = ?", new String[]{str});
        } catch (SQLException | IllegalStateException unused) {
            LogUtil.b("SimpleContactsDao", "delete: occurred IllegalStateException or SQLException.");
            i = -1;
        }
        LogUtil.a("SimpleContactsDao", "delete: result: ", Integer.valueOf(i));
    }

    private void b(jzt jztVar, String str, SQLiteDatabase sQLiteDatabase) {
        int i;
        if (jztVar == null || TextUtils.isEmpty(str) || sQLiteDatabase == null) {
            LogUtil.h("SimpleContactsDao", "update: failure, input parameters contains null.");
            return;
        }
        try {
            i = sQLiteDatabase.update(str, bMg_(jztVar), "raw_contact_uid = ?", new String[]{jztVar.c()});
        } catch (SQLException | IllegalStateException unused) {
            LogUtil.b("SimpleContactsDao", "update: occurred IllegalStateException or SQLException.");
            i = -1;
        }
        LogUtil.a("SimpleContactsDao", "update: result: ", Integer.valueOf(i));
    }

    public void e(final List<jzt> list, final String str) {
        LogUtil.a("SimpleContactsDao", "updateOrInsert: ");
        if (list != null && !list.isEmpty() && !TextUtils.isEmpty(str)) {
            LogUtil.a("SimpleContactsDao", "updateOrInsert beans size: ", Integer.valueOf(list.size()));
            a(str);
            final SQLiteDatabase e2 = this.f14231a.e();
            new RunOnTransaction(e2) { // from class: jzw.1
                @Override // com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.db.RunOnTransaction
                public void run() {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        jzw.this.d((jzt) it.next(), str, e2);
                    }
                }
            }.start();
            return;
        }
        LogUtil.h("SimpleContactsDao", "updateOrInsert: failure, bean/tableName is null.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(jzt jztVar, String str, SQLiteDatabase sQLiteDatabase) {
        if (jztVar == null || TextUtils.isEmpty(str) || sQLiteDatabase == null) {
            LogUtil.h("SimpleContactsDao", "updateOrInsert: failure, input parameters contain null.");
        } else if (b(jztVar.c(), str, sQLiteDatabase).isEmpty()) {
            a(jztVar, str, sQLiteDatabase);
        } else {
            b(jztVar, str, sQLiteDatabase);
        }
    }

    private List<jzt> b(String str, String str2, SQLiteDatabase sQLiteDatabase) {
        Cursor query;
        ArrayList arrayList = new ArrayList(0);
        try {
            query = sQLiteDatabase.query(str2, null, "raw_contact_uid = ?", new String[]{str}, null, null, null);
        } catch (SQLException | IllegalStateException unused) {
        }
        try {
            ArrayList arrayList2 = new ArrayList(bMh_(query));
            if (query != null) {
                try {
                    query.close();
                } catch (SQLException | IllegalStateException unused2) {
                    arrayList = arrayList2;
                    LogUtil.b("SimpleContactsDao", "query: occurred IllegalStateException or SQLException.");
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

    public List<jzt> b(String str) {
        Cursor query;
        LogUtil.a("SimpleContactsDao", "queryAll:");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SimpleContactsDao", "queryAll: failure, tableName is null or empty");
            return new ArrayList(0);
        }
        a(str);
        SQLiteDatabase e2 = this.f14231a.e();
        ArrayList arrayList = new ArrayList(0);
        try {
            query = e2.query(str, null, null, null, null, null, null);
        } catch (SQLException | IllegalStateException unused) {
        }
        try {
            ArrayList arrayList2 = new ArrayList(bMh_(query));
            if (query != null) {
                try {
                    query.close();
                } catch (SQLException | IllegalStateException unused2) {
                    arrayList = arrayList2;
                    LogUtil.b("SimpleContactsDao", "queryAll: occurred IllegalStateException or SQLException.");
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
            LogUtil.h("SimpleContactsDao", "hasTable: deviceId is null or empty.");
            return false;
        }
        return this.f14231a.a(this.f14231a.e(), str);
    }

    private List<jzt> bMh_(Cursor cursor) {
        if (ContactsDatabaseUtils.bMi_(cursor)) {
            LogUtil.h("SimpleContactsDao", "getSimpleContactBeansFromCursor: cursor is null or cannot move.");
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(cursor.getCount());
        do {
            arrayList.add(new jzt(cursor.getInt(cursor.getColumnIndex("raw_contact_id")), cursor.getString(cursor.getColumnIndex("raw_contact_uid")), cursor.getString(cursor.getColumnIndex("raw_contact_feature"))));
        } while (cursor.moveToNext());
        return arrayList;
    }

    private ContentValues bMg_(jzt jztVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("raw_contact_id", Integer.valueOf(jztVar.a()));
        contentValues.put("raw_contact_uid", jztVar.c());
        contentValues.put("raw_contact_feature", jztVar.e());
        return contentValues;
    }
}
