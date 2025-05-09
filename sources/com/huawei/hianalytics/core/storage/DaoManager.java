package com.huawei.hianalytics.core.storage;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.huawei.hianalytics.core.c;
import com.huawei.hianalytics.core.d;
import com.huawei.hianalytics.core.e;
import com.huawei.hianalytics.core.log.HiLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class DaoManager {
    public static final String TAG = "DaoManager";
    public c eventDao;
    public com.huawei.hianalytics.core.b headerDao;
    public boolean initialized;
    public d mcInfoDao;
    public e mcTagDao;

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public static final DaoManager f3842a = new DaoManager();
    }

    public static DaoManager getInstance() {
        return b.f3842a;
    }

    public long countBySubCount(String str, String str2, String str3) {
        long j = 0;
        if (!this.initialized) {
            HiLog.w(TAG, "DaoManager not initialized, return");
            return 0L;
        }
        c cVar = this.eventDao;
        if (!cVar.a()) {
            try {
                Cursor rawQuery = cVar.b.rawQuery(c.g, new String[]{str, str2, str3, ""});
                try {
                    if (rawQuery.moveToFirst()) {
                        long j2 = rawQuery.getLong(0);
                        rawQuery.close();
                        j = j2;
                    } else {
                        HiLog.e("EventDao", "cursor is empty");
                        rawQuery.close();
                    }
                } finally {
                }
            } catch (Exception unused) {
                HiLog.e("EventDao", "query sub count error");
            }
        }
        return j;
    }

    public void createEventTable(SQLiteDatabase sQLiteDatabase) {
        DBUtil.exec(sQLiteDatabase, c.f);
    }

    public void createHeaderTable(SQLiteDatabase sQLiteDatabase) {
        DBUtil.exec(sQLiteDatabase, com.huawei.hianalytics.core.b.e);
        HiLog.i("CommonHeaderExDao", "createTable");
    }

    public void createMcInfoTable(SQLiteDatabase sQLiteDatabase) {
        DBUtil.exec(sQLiteDatabase, d.h);
        HiLog.i("MetricMcInfoDao", "createTable");
    }

    public void createMcTagTable(SQLiteDatabase sQLiteDatabase) {
        DBUtil.exec(sQLiteDatabase, e.k);
        HiLog.i("MetricMcTagDao", "createTable");
    }

    public void deleteEvents(String str, String str2) {
        String str3;
        if (!this.initialized) {
            HiLog.w(TAG, "DaoManager not initialized, return");
            return;
        }
        c cVar = this.eventDao;
        cVar.getClass();
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            str3 = null;
        } else {
            arrayList.add(str);
            str3 = Event.COLUMN_SERVICE_TAG.columnName + "=?";
        }
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            str3 = str3 + " AND " + Event.COLUMN_EVT_TYPE.columnName + "=?";
        }
        if (str3 == null) {
            return;
        }
        cVar.b(str3, (String[]) arrayList.toArray(new String[0]));
    }

    public int deleteMcInfo(List<String> list) {
        d dVar = this.mcInfoDao;
        dVar.getClass();
        if (list == null || list.isEmpty()) {
            return -1;
        }
        StringBuilder sb = new StringBuilder();
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = list.get(i);
            sb.append("?");
            if (i != list.size() - 1) {
                sb.append(",");
            }
        }
        return dVar.b(d.e.columnName + " in( " + ((Object) sb) + " )", strArr);
    }

    public int deleteMcTag(List<String> list) {
        e eVar = this.mcTagDao;
        eVar.getClass();
        return eVar.a(list, e.e.columnName);
    }

    public void insertEvents(List<Event> list) {
        if (this.initialized) {
            this.eventDao.a((List) list, false);
        } else {
            HiLog.w(TAG, "DaoManager not initialized, return");
        }
    }

    public long insertHeaders(List<CommonHeaderEx> list) {
        if (this.initialized) {
            return this.headerDao.a((List) list, true);
        }
        HiLog.w(TAG, "DaoManager not initialized, return");
        return 0L;
    }

    public long insertMcInfoList(List<String> list) {
        return this.mcInfoDao.a((List) list, false);
    }

    public List<String> queryMcInfo() {
        d dVar = this.mcInfoDao;
        dVar.getClass();
        return dVar.a(new String[]{d.e.columnName, d.f.columnName, d.g.columnName}, null, null, null, null, d.g.columnName + " DESC", null);
    }

    public void deleteEvents(List<Event> list) {
        if (!this.initialized) {
            HiLog.w(TAG, "DaoManager not initialized, return");
            return;
        }
        c cVar = this.eventDao;
        cVar.getClass();
        String[] strArr = new String[list.size()];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = String.valueOf(list.get(i).getId());
            sb.append("?");
            if (i != list.size() - 1) {
                sb.append(", ");
            }
        }
        cVar.b(Event.COLUMN_ID.columnName + " in( " + ((Object) sb) + " )", strArr);
    }

    public long insert(CommonHeaderEx commonHeaderEx) {
        if (!this.initialized) {
            HiLog.w(TAG, "DaoManager not initialized, return");
            return 0L;
        }
        com.huawei.hianalytics.core.b bVar = this.headerDao;
        bVar.getClass();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(commonHeaderEx);
        return bVar.a((List) arrayList, true);
    }

    public long insertMcTagList(List<String> list) {
        e eVar = this.mcTagDao;
        eVar.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            try {
                arrayList.add(new JSONObject(it.next()).optString(e.f.columnName, ""));
            } catch (JSONException e) {
                HiLog.w("MetricMcTagDao", e.getMessage());
            }
        }
        eVar.a(arrayList, e.f.columnName);
        return eVar.a((List) list, false);
    }

    public List<String> queryMcTag(String str) {
        e eVar = this.mcTagDao;
        eVar.getClass();
        return eVar.a(new String[]{e.e.columnName, e.f.columnName, e.g.columnName, e.h.columnName, e.i.columnName, e.j.columnName}, e.i.columnName + " =?", new String[]{str}, null, null, null, "1000");
    }

    public List<Event> readEvents(String str, String str2, String str3, long j) {
        String[] strArr;
        if (!this.initialized) {
            HiLog.w(TAG, "DaoManager not initialized, return");
            return new ArrayList();
        }
        c cVar = this.eventDao;
        cVar.getClass();
        String[] strArr2 = {Event.COLUMN_ID.columnName, Event.COLUMN_IS_ENCRYPTED.columnName, Event.COLUMN_SESSION_ID.columnName, Event.COLUMN_CONTENT.columnName, Event.COLUMN_EVT_ID.columnName, Event.COLUMN_PROCESS_NAME.columnName, Event.COLUMN_EVT_EX_HASH_CODE.columnName, Event.COLUMN_SESSION_NAME.columnName, Event.COLUMN_SERVICE_TAG.columnName, Event.COLUMN_EVT_TIME.columnName, Event.COLUMN_EVT_TYPE.columnName, Event.COLUMN_SUB_COUNT.columnName};
        String str4 = "";
        if (j > 0) {
            String str5 = Event.COLUMN_EVT_TIME.columnName + "<? AND ";
            strArr = new String[]{String.valueOf(j), str, str2, str3, ""};
            str4 = str5;
        } else {
            strArr = new String[]{str, str2, str3, ""};
        }
        return cVar.a(strArr2, str4 + Event.COLUMN_SERVICE_TAG.columnName + "=? AND " + Event.COLUMN_EVT_TYPE.columnName + "=? AND (" + Event.COLUMN_PROCESS_NAME.columnName + "=? or " + Event.COLUMN_PROCESS_NAME.columnName + "=?)", strArr, null, null, null, "1000");
    }

    public CommonHeaderEx readHeader(String str) {
        if (!this.initialized) {
            HiLog.w(TAG, "DaoManager not initialized, return");
            return new CommonHeaderEx();
        }
        com.huawei.hianalytics.core.b bVar = this.headerDao;
        bVar.getClass();
        ArrayList arrayList = (ArrayList) bVar.a(new String[]{CommonHeaderEx.COLUMN_HASHCODE.columnName, CommonHeaderEx.COLUMN_HEADER.columnName}, CommonHeaderEx.COLUMN_HASHCODE.columnName + " =?", new String[]{str}, null, null, null, "1000");
        return !arrayList.isEmpty() ? (CommonHeaderEx) arrayList.get(0) : new CommonHeaderEx();
    }

    public long insert(Event event) {
        if (!this.initialized) {
            HiLog.w(TAG, "DaoManager not initialized, return");
            return 0L;
        }
        c cVar = this.eventDao;
        cVar.getClass();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(event);
        return cVar.a((List) arrayList, false);
    }

    public long insertMcInfo(String str) {
        return insertMcInfoList(Collections.singletonList(str));
    }

    public void init(SQLiteDatabase sQLiteDatabase) {
        this.eventDao = new c(sQLiteDatabase);
        this.headerDao = new com.huawei.hianalytics.core.b(sQLiteDatabase);
        this.mcTagDao = new e(sQLiteDatabase);
        this.mcInfoDao = new d(sQLiteDatabase);
        this.initialized = true;
    }

    public void dropAllTable(SQLiteDatabase sQLiteDatabase) {
        c.a(sQLiteDatabase);
        com.huawei.hianalytics.core.b.a(sQLiteDatabase);
        e.a(sQLiteDatabase);
        d.a(sQLiteDatabase);
        HiLog.i(TAG, "dropAllTable");
    }

    public void deleteAllInfo() {
        if (this.initialized) {
            this.mcInfoDao.c();
        } else {
            HiLog.w(TAG, "DaoManager not initialized, return");
        }
    }

    public void deleteAllHeaders() {
        if (this.initialized) {
            this.headerDao.c();
        } else {
            HiLog.w(TAG, "DaoManager not initialized, return");
        }
    }

    public void deleteAllEvents() {
        if (this.initialized) {
            this.eventDao.c();
        } else {
            HiLog.w(TAG, "DaoManager not initialized, return");
        }
    }

    public long countEvents() {
        if (this.initialized) {
            return this.eventDao.b();
        }
        HiLog.w(TAG, "DaoManager not initialized, return");
        return 0L;
    }

    public boolean checkAndClearTable() {
        if (this.mcTagDao.b() > 500) {
            this.mcTagDao.c();
        }
        if (this.mcInfoDao.b() < 500) {
            return false;
        }
        this.mcInfoDao.c();
        return true;
    }

    public DaoManager() {
    }
}
