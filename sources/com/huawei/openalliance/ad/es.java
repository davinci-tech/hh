package com.huawei.openalliance.ad;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.v3.CachedContent;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.Scheme;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class es extends ep implements fs {
    private static es c;
    private static final byte[] d = new byte[0];
    private static final byte[] e = new byte[0];

    @Override // com.huawei.openalliance.ad.fs
    public ContentRecord a(String str, String str2, String str3, int i, long j) {
        return null;
    }

    @Override // com.huawei.openalliance.ad.fs
    public void a(String str, String str2, String str3, String str4) {
    }

    @Override // com.huawei.openalliance.ad.ep
    public void e() {
        super.e();
        a(a(), (fi) null, (String[]) null);
    }

    public List<CachedContent> e(String str) {
        return d(f(str));
    }

    @Override // com.huawei.openalliance.ad.fs
    public long d(String str) {
        if (str == null) {
            return 0L;
        }
        List a2 = a(a(), new String[]{ContentRecord.LAST_SHOW_TIME}, fi.CONTENT_BY_TASKID_WHERE, new String[]{str}, "lastShowTime desc", null);
        if (a2.isEmpty()) {
            return 0L;
        }
        return ((ContentRecord) a2.get(0)).p();
    }

    @Override // com.huawei.openalliance.ad.fs
    public void c(String str) {
        ho.a("ContentRecordDao", "deleteContentById id: %s", str);
        a(a(), fi.CONTENT_BY_ID_WHERE, new String[]{str});
    }

    @Override // com.huawei.openalliance.ad.fs
    public void c(ContentRecord contentRecord) {
        if (contentRecord == null) {
            return;
        }
        synchronized (d) {
            if (a(contentRecord.m(), contentRecord.aN(), contentRecord.l()) != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add("_id");
                arrayList.add(ContentRecord.DISPLAY_COUNT);
                arrayList.add(ContentRecord.DISPLAY_DATE);
                arrayList.add(ContentRecord.SPLASH_MEDIA_PATH);
                arrayList.add(ContentRecord.LAST_SHOW_TIME);
                arrayList.add(ContentRecord.FC_CTRL_DATE);
                b(contentRecord, arrayList);
            } else {
                a(contentRecord);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.fs
    public List<ContentRecord> c() {
        return a(a(), new String[]{"contentId", "slotId", ContentRecord.SPLASH_MEDIA_PATH}, fi.CONTENT_EXPIRE_WHERE, new String[]{String.valueOf(com.huawei.openalliance.ad.utils.ao.c()), String.valueOf(0)}, null, null);
    }

    @Override // com.huawei.openalliance.ad.fs
    public void b(String str, String str2) {
        ho.a("ContentRecordDao", "deleteContentById id: %s %s", str, str2);
        a(a(), fi.CONTENT_BY_IDS_WHERE, new String[]{str, str2});
    }

    @Override // com.huawei.openalliance.ad.fs
    public void b(ContentRecord contentRecord, List<String> list, String str) {
        ho.a("ContentRecordDao", "updateContentExcludeById. ");
        if (contentRecord == null) {
            return;
        }
        ContentValues d2 = contentRecord.d(this.f6846a);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            d2.remove(it.next());
        }
        a(a(), d2, fi.CONTENT_BY_ID_WHERE, new String[]{str});
    }

    @Override // com.huawei.openalliance.ad.fs
    public void b(ContentRecord contentRecord, List<String> list) {
        if (contentRecord == null) {
            return;
        }
        ContentValues d2 = contentRecord.d(this.f6846a);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            d2.remove(it.next());
        }
        a(a(), d2, fi.CONTENT_BY_IDS_WHERE, new String[]{contentRecord.m(), contentRecord.l()});
    }

    @Override // com.huawei.openalliance.ad.fs
    public void b(ContentRecord contentRecord) {
        if (contentRecord == null) {
            return;
        }
        synchronized (d) {
            String m = contentRecord.m();
            String l = contentRecord.l();
            if (a(m, l) != null) {
                a(a(), contentRecord.d(this.f6846a), fi.CONTENT_BY_IDS_WHERE, new String[]{m, l});
            } else {
                a(contentRecord);
            }
        }
    }

    @Override // com.huawei.openalliance.ad.fs
    public void b() {
        ContentValues contentValues = new ContentValues();
        String b = com.huawei.openalliance.ad.utils.ao.b("yyyy-MM-dd");
        contentValues.put(ContentRecord.DISPLAY_DATE, b);
        contentValues.put(ContentRecord.DISPLAY_COUNT, (Integer) 0);
        a(a(), contentValues, fi.CONTENT_BY_DATE_NOT_EQUAL_WHERE, new String[]{b});
    }

    @Override // com.huawei.openalliance.ad.fs
    public List<ContentRecord> b(String str, int i, long j, int i2) {
        ArrayList arrayList = new ArrayList();
        fi fiVar = 1 == i ? fi.CONTENT_PORTRAIT_CACHE_SHOW_WHERE : fi.CONTENT_LANDSCAPE_CACHE_SHOW_WHERE;
        long c2 = com.huawei.openalliance.ad.utils.ao.c();
        for (ContentRecord contentRecord : a(a(), null, fiVar, new String[]{String.valueOf(c2), String.valueOf(c2), str, String.valueOf(c2 - j), com.huawei.openalliance.ad.utils.ao.b("yyyy-MM-dd"), String.valueOf(i2)}, "priority asc, displayCount asc, updateTime desc", String.valueOf(4))) {
            if (com.huawei.openalliance.ad.utils.ae.c(this.f6846a, contentRecord.y(), "normal") || com.huawei.openalliance.ad.utils.c.a(this.f6846a, contentRecord, com.huawei.openalliance.ad.utils.c.a(this.f6846a, contentRecord))) {
                arrayList.add(contentRecord);
            } else {
                b(contentRecord.m(), contentRecord.l());
            }
        }
        return arrayList;
    }

    @Override // com.huawei.openalliance.ad.fs
    public List<ContentRecord> b(String str) {
        return a(a(), null, fi.CONTENT_BY_ID_WHERE, new String[]{str}, null, null);
    }

    @Override // com.huawei.openalliance.ad.fs
    public void a(List<ContentRecord> list) {
        long currentTimeMillis = System.currentTimeMillis();
        if (com.huawei.openalliance.ad.utils.bg.a(list) || this.f6846a == null) {
            return;
        }
        synchronized (d) {
            ArrayList arrayList = new ArrayList();
            String simpleName = a().getSimpleName();
            String a2 = fi.CONTENT_BY_IDS_WHERE.a();
            String a3 = fi.CONTENT_BY_IDS_WHERE.a();
            for (ContentRecord contentRecord : list) {
                String m = contentRecord.m();
                String l = contentRecord.l();
                String[] strArr = {m, l};
                String[] strArr2 = {m, l};
                arrayList.add(new de(simpleName, a2, strArr, a3, strArr2, contentRecord.d(this.f6846a)));
                simpleName = simpleName;
            }
            c(arrayList);
        }
        ho.a("ContentRecordDao", "insertOrUpdateContents duration: %s ms", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    @Override // com.huawei.openalliance.ad.fs
    public void a(String str, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContentRecord.LAST_SHOW_TIME, Long.valueOf(j));
        a(a(), contentValues, fi.CONTENT_BY_TASKID_WHERE, new String[]{str});
    }

    @Override // com.huawei.openalliance.ad.fs
    public void a(ContentRecord contentRecord, List<String> list, String str) {
        ho.a("ContentRecordDao", "updateContentIncludeById. ");
        if (contentRecord == null) {
            return;
        }
        List<String> bo = contentRecord.bo();
        ContentValues d2 = contentRecord.d(this.f6846a);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            bo.remove(it.next());
        }
        Iterator<String> it2 = bo.iterator();
        while (it2.hasNext()) {
            d2.remove(it2.next());
        }
        a(a(), d2, fi.CONTENT_BY_ID_WHERE, new String[]{str});
    }

    @Override // com.huawei.openalliance.ad.fs
    public void a(ContentRecord contentRecord, List<String> list) {
        ho.a("ContentRecordDao", "updateContentIncludeById. ");
        if (contentRecord == null) {
            return;
        }
        List<String> bo = contentRecord.bo();
        ContentValues d2 = contentRecord.d(this.f6846a);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            bo.remove(it.next());
        }
        Iterator<String> it2 = bo.iterator();
        while (it2.hasNext()) {
            d2.remove(it2.next());
        }
        a(a(), d2, fi.CONTENT_BY_IDS_WHERE, new String[]{contentRecord.m(), contentRecord.l()});
    }

    @Override // com.huawei.openalliance.ad.fs
    public void a(ContentRecord contentRecord) {
        ho.a("ContentRecordDao", "insertContent. ");
        if (contentRecord == null) {
            return;
        }
        a(a(), contentRecord.d(this.f6846a));
    }

    public List<String> a(int i, String str) {
        return b(a(a(), new String[]{"contentId"}, fi.CONTENT_BY_ADTYPE_AND_WHERE_SLOTID, new String[]{String.valueOf(i), str}, null, null));
    }

    @Override // com.huawei.openalliance.ad.fs
    public List<ContentRecord> a(int i) {
        return a(a(), null, fi.CONTENT_BY_ADTYPE_WHERE, new String[]{String.valueOf(i)}, null, null);
    }

    protected Class<? extends com.huawei.openalliance.ad.db.bean.a> a() {
        return ContentRecord.class;
    }

    @Override // com.huawei.openalliance.ad.fs
    public ContentRecord a(String str, String str2, String str3) {
        List a2 = a(a(), null, fi.CONTENT_RECORD_BY_IDS, new String[]{str, str2, str3}, null, null);
        if (a2.isEmpty()) {
            return null;
        }
        return (ContentRecord) a2.get(0);
    }

    public ContentRecord a(String str, String str2, int i, long j) {
        return a(1 == i ? fi.CONTENT_PORTRAIT_REAL_SHOW_WHERE : fi.CONTENT_LANDSCAPE_REAL_SHOW_WHERE, new String[]{str, str2, String.valueOf(j), String.valueOf(j)});
    }

    @Override // com.huawei.openalliance.ad.fs
    public ContentRecord a(String str, String str2) {
        List a2 = a(a(), null, fi.CONTENT_BY_IDS_WHERE, new String[]{str, str2}, null, null);
        if (a2.isEmpty()) {
            return null;
        }
        return (ContentRecord) a2.get(0);
    }

    @Override // com.huawei.openalliance.ad.fs
    public ContentRecord a(String str, int i, long j, int i2) {
        fi fiVar = 1 == i ? fi.CONTENT_PORTRAIT_CACHE_SHOW_WHERE : fi.CONTENT_LANDSCAPE_CACHE_SHOW_WHERE;
        long c2 = com.huawei.openalliance.ad.utils.ao.c();
        ContentRecord a2 = a(fiVar, new String[]{String.valueOf(c2), String.valueOf(c2), str, String.valueOf(c2 - j), com.huawei.openalliance.ad.utils.ao.b("yyyy-MM-dd"), String.valueOf(i2)});
        ho.b("ContentRecordDao", "queryCacheShowContent " + com.huawei.openalliance.ad.utils.cz.b(a2));
        return a2;
    }

    @Override // com.huawei.openalliance.ad.fs
    public ContentRecord a(String str) {
        List a2 = a(a(), null, fi.CONTENT_BY_ID_WHERE, new String[]{str}, "updateTime desc", String.valueOf(4));
        if (a2.isEmpty()) {
            return null;
        }
        return (ContentRecord) a2.get(0);
    }

    private List<ContentRecord> f(String str) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            Iterator it = a(a(), new String[]{"contentId", "templateId"}, fi.CONTENT_BY_SLOTID, new String[]{str}, null, null).iterator();
            while (it.hasNext()) {
                arrayList.add((ContentRecord) it.next());
            }
        }
        return arrayList;
    }

    private List<CachedContent> d(List<ContentRecord> list) {
        ArrayList arrayList = new ArrayList();
        if (!list.isEmpty()) {
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            for (ContentRecord contentRecord : list) {
                if (!com.huawei.openalliance.ad.utils.cz.b(contentRecord.m()) && !com.huawei.openalliance.ad.utils.cz.b(contentRecord.aN())) {
                    List list2 = (List) concurrentHashMap.get(contentRecord.m());
                    if (com.huawei.openalliance.ad.utils.bg.a(list2)) {
                        list2 = new ArrayList();
                    }
                    list2.add(contentRecord.aN());
                    concurrentHashMap.put(contentRecord.m(), list2);
                }
            }
            for (Map.Entry entry : concurrentHashMap.entrySet()) {
                if (entry != null) {
                    arrayList.add(new CachedContent((String) entry.getKey(), 3, (List) entry.getValue()));
                }
            }
        }
        return arrayList;
    }

    private List<String> b(List<ContentRecord> list) {
        if (list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<ContentRecord> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().m());
        }
        return arrayList;
    }

    public static es a(Context context) {
        es esVar;
        synchronized (e) {
            if (c == null) {
                c = new es(context);
            }
            esVar = c;
        }
        return esVar;
    }

    private ContentRecord a(fi fiVar, String[] strArr) {
        for (ContentRecord contentRecord : a(a(), null, fiVar, strArr, "priority asc, displayCount asc, updateTime desc", String.valueOf(4))) {
            String y = contentRecord.y();
            if (!TextUtils.isEmpty(y) && y.startsWith(Scheme.CONTENT.toString())) {
                ho.a("ContentRecordDao", "queryShowContent valid, uri.");
                return contentRecord;
            }
            if (com.huawei.openalliance.ad.utils.ae.c(this.f6846a, y, "normal") || com.huawei.openalliance.ad.utils.ae.c(this.f6846a, y, Constants.TPLATE_CACHE)) {
                ho.a("ContentRecordDao", "queryShowContent valid: %s", com.huawei.openalliance.ad.utils.cz.b(contentRecord));
                return contentRecord;
            }
            ho.a("ContentRecordDao", "queryShowContent is not valid: %s", com.huawei.openalliance.ad.utils.cz.b(contentRecord));
            b(contentRecord.m(), contentRecord.l());
        }
        return null;
    }

    protected es(Context context) {
        super(context);
    }
}
