package com.huawei.openalliance.ad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.huawei.openalliance.ad.db.bean.AnalysisEventRecord;
import com.huawei.openalliance.ad.db.bean.ClickEventRecord;
import com.huawei.openalliance.ad.db.bean.EventMonitorRecord;
import com.huawei.openalliance.ad.db.bean.EventRecord;
import com.huawei.openalliance.ad.db.bean.ImpEventRecord;
import com.huawei.openalliance.ad.db.bean.ThirdPartyEventRecord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class ex extends ep implements fv {
    private static ex c;
    private static final byte[] d = new byte[0];

    @Override // com.huawei.openalliance.ad.ep
    public void e() {
        super.e();
        a(EventRecord.class, (fi) null, (String[]) null);
        a(ImpEventRecord.class, (fi) null, (String[]) null);
        a(ClickEventRecord.class, (fi) null, (String[]) null);
        a(AnalysisEventRecord.class, (fi) null, (String[]) null);
        a(ThirdPartyEventRecord.class, (fi) null, (String[]) null);
    }

    @Override // com.huawei.openalliance.ad.fv
    public void b(Class<? extends EventRecord> cls, List<String> list) {
        a(cls, fi.EVENT_DELETE_BY_ID_WHERE, list);
    }

    @Override // com.huawei.openalliance.ad.fv
    public void b(long j, List<String> list) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("lastReportTime", Long.valueOf(j));
        a(ThirdPartyEventRecord.class, contentValues, fi.THIRD_PARTY_EVENT_UPDATE_BY_ID_WHERE, list);
    }

    @Override // com.huawei.openalliance.ad.fv
    public List<ThirdPartyEventRecord> b(long j, int i) {
        return a(ThirdPartyEventRecord.class, null, fi.THIER_PARTY_EVENT_UPLOAD_ITEMS_WHERE, new String[]{String.valueOf(com.huawei.openalliance.ad.utils.ao.c() - j)}, "lastReportTime asc", String.valueOf(i));
    }

    @Override // com.huawei.openalliance.ad.fv
    public void a(List<String> list) {
        a(ThirdPartyEventRecord.class, fi.THIRD_PARTY_EVENT_DELETE_BY_ID_WHERE, list);
    }

    @Override // com.huawei.openalliance.ad.fv
    public void a(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("requestId", str2);
        a(ThirdPartyEventRecord.class, contentValues, fi.THIRD_PARTY_EVENT_UPDATE_BY_ID_WHERE, new String[]{str});
    }

    public void a(String str, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventMonitorRecord.ADD_TIME, Long.valueOf(j));
        a(EventMonitorRecord.class, contentValues, fi.EVENT_MONITOR_RECORD_UPDATE_BY_EVENT_ID_WHERE, new String[]{str});
    }

    @Override // com.huawei.openalliance.ad.fv
    public void a(Class<? extends EventRecord> cls, String str, String str2, long j, List<String> list) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("lastReportTime", str);
        contentValues.put(EventRecord.LAST_FAIL_REASON, str2);
        contentValues.put(EventRecord.REPEATED_COUNT, Long.valueOf(j));
        a(cls, contentValues, fi.EVENT_REPORT_FAILED_UPDATE_BY_ID_WHERE, list);
    }

    @Override // com.huawei.openalliance.ad.fv
    public void a(ThirdPartyEventRecord thirdPartyEventRecord) {
        a(ThirdPartyEventRecord.class, thirdPartyEventRecord.d(this.f6846a));
    }

    public void a(EventMonitorRecord eventMonitorRecord, int i) {
        a(EventMonitorRecord.class, eventMonitorRecord.d(this.f6846a));
        a(i);
    }

    @Override // com.huawei.openalliance.ad.fv
    public void a(long j, List<String> list) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ThirdPartyEventRecord.LOCK_TIME, Long.valueOf(j));
        a(ThirdPartyEventRecord.class, contentValues, fi.THIRD_PARTY_EVENT_UPDATE_BY_ID_WHERE, list);
    }

    @Override // com.huawei.openalliance.ad.fv
    public void a(long j, int i) {
        a(EventRecord.class, fi.EVENT_DELETE_EXPIRE_WHERE, new String[]{String.valueOf(j), String.valueOf(i)});
        a(ImpEventRecord.class, fi.EVENT_DELETE_EXPIRE_WHERE, new String[]{String.valueOf(j), String.valueOf(i)});
        a(ClickEventRecord.class, fi.EVENT_DELETE_EXPIRE_WHERE, new String[]{String.valueOf(j), String.valueOf(i)});
        a(AnalysisEventRecord.class, fi.EVENT_DELETE_EXPIRE_WHERE, new String[]{String.valueOf(j), String.valueOf(i)});
        a(ThirdPartyEventRecord.class, fi.THIRD_PARTY_EVENT_DELETE_EXPIRE_WHERE, new String[]{String.valueOf(j), String.valueOf(i)});
        a(EventMonitorRecord.class, fi.EVENT_MONITOR_RECORD_DELETE_EXPIRE_WHERE, new String[]{String.valueOf(j), String.valueOf(i)});
    }

    protected Map<String, EventRecord> a(Class<? extends EventRecord> cls, String[] strArr, String str, String str2) {
        dd ddVar;
        StringBuilder sb;
        HashMap hashMap = new HashMap();
        Cursor cursor = null;
        try {
            ddVar = dd.a(this.f6846a);
            try {
                Cursor a2 = ddVar.a(cls.getSimpleName(), strArr, null, null, str, str2);
                if (a2 != null) {
                    while (a2.moveToNext()) {
                        try {
                            try {
                                EventRecord newInstance = cls.newInstance();
                                newInstance.a(a2);
                                hashMap.put(newInstance.h(), newInstance);
                            } catch (RuntimeException e) {
                                sb = new StringBuilder();
                                sb.append("query RuntimeException:");
                                sb.append(e.getClass().getSimpleName());
                                ho.d("EventDao", sb.toString());
                            } catch (Exception e2) {
                                sb = new StringBuilder();
                                sb.append("query exception:");
                                sb.append(e2.getClass().getSimpleName());
                                ho.d("EventDao", sb.toString());
                            }
                        } catch (Throwable th) {
                            th = th;
                            cursor = a2;
                            a(cursor);
                            a(ddVar);
                            throw th;
                        }
                    }
                }
                a(a2);
                a(ddVar);
                return hashMap;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            ddVar = null;
        }
    }

    @Override // com.huawei.openalliance.ad.fv
    public Map<String, EventRecord> a(Class<? extends EventRecord> cls, int i) {
        return a(cls, (String[]) null, "_id desc", String.valueOf(i));
    }

    public EventMonitorRecord a(String str) {
        List a2 = a(EventMonitorRecord.class, null, fi.EVENT_MONITOR_RECORD_BY_EVENT_ID_WHERE, new String[]{str}, null, null);
        if (com.huawei.openalliance.ad.utils.bg.a(a2)) {
            return null;
        }
        return (EventMonitorRecord) a2.get(0);
    }

    @Override // com.huawei.openalliance.ad.fv
    public long a(Class<? extends EventRecord> cls, EventRecord eventRecord) {
        return a(cls, eventRecord.d(this.f6846a));
    }

    private void a(int i) {
        List<String> a2 = a();
        ho.a("EventDao", "delete over: size:%s, limit:%s", Integer.valueOf(a2.size()), Integer.valueOf(i));
        int size = a2.size() - i;
        if (size <= 0) {
            return;
        }
        a(EventMonitorRecord.class, fi.EVENT_MONITOR_RECORD_UPDATE_BY_EVENT_ID_WHERE, a2.subList(0, size));
    }

    private List<String> a() {
        ArrayList arrayList = new ArrayList();
        List a2 = a(EventMonitorRecord.class, new String[]{EventMonitorRecord.EVENT_ID}, null, null, "addTime asc", null);
        if (!com.huawei.openalliance.ad.utils.bg.a(a2)) {
            Iterator it = a2.iterator();
            while (it.hasNext()) {
                arrayList.add(((EventMonitorRecord) it.next()).a());
            }
        }
        return arrayList;
    }

    public static ex a(Context context) {
        ex exVar;
        synchronized (d) {
            if (c == null) {
                c = new ex(context);
            }
            exVar = c;
        }
        return exVar;
    }

    protected ex(Context context) {
        super(context);
    }
}
