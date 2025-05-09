package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.DeletedContentRecord;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class ew extends ep {
    private static ew c;
    private static final byte[] d = new byte[0];

    public void b(DeletedContentRecord deletedContentRecord) {
        if (deletedContentRecord == null) {
            return;
        }
        synchronized (d) {
            String a2 = deletedContentRecord.a();
            if (a(a2) != null) {
                a(DeletedContentRecord.class, deletedContentRecord.d(this.f6846a), fi.DELETED_CONTENT_BY_ID_WHERE, new String[]{a2});
            } else {
                a(deletedContentRecord);
            }
        }
    }

    public void b() {
        long c2 = com.huawei.openalliance.ad.utils.ao.c() - 604800000;
        ho.a("DeletedContentRecordDao", "deleteExpireIds where updateTime before %s", Long.valueOf(c2));
        a(DeletedContentRecord.class, fi.DELETED_CONTENT_EXPIRE_WHERE, new String[]{String.valueOf(c2)});
    }

    public void a(DeletedContentRecord deletedContentRecord) {
        a(DeletedContentRecord.class, deletedContentRecord.d(this.f6846a));
    }

    public List<String> a() {
        return a(a(DeletedContentRecord.class, new String[]{"contentId"}, fi.DELETED_CONTENT_IDS_WHERE, new String[]{String.valueOf(com.huawei.openalliance.ad.utils.ao.c() - 604800000)}, null, null));
    }

    public DeletedContentRecord a(String str) {
        List a2 = a(DeletedContentRecord.class, new String[]{"contentId"}, fi.DELETED_CONTENT_BY_ID_WHERE, new String[]{str}, null, null);
        if (a2.isEmpty()) {
            return null;
        }
        return (DeletedContentRecord) a2.get(0);
    }

    private List<String> a(List<DeletedContentRecord> list) {
        if (list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<DeletedContentRecord> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().a());
        }
        return arrayList;
    }

    public static ew a(Context context) {
        ew ewVar;
        synchronized (d) {
            if (c == null) {
                c = new ew(context);
            }
            ewVar = c;
        }
        return ewVar;
    }

    protected ew(Context context) {
        super(context);
    }
}
