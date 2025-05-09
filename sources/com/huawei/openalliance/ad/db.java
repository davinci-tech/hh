package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.db.bean.ContentRecord;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class db {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f6690a = new byte[0];
    private static db b;
    private volatile Map<String, ContentRecord> c = new ConcurrentHashMap();

    public void b(String str) {
        if (this.c.containsKey(str)) {
            this.c.remove(str);
        }
    }

    public void a(String str, ContentRecord contentRecord) {
        ho.a("AdRecordManager", "task size before: %s", Integer.valueOf(this.c.size()));
        contentRecord.g(System.currentTimeMillis());
        this.c.put(str, contentRecord);
        b();
    }

    public ContentRecord a(String str) {
        return this.c.get(str);
    }

    private void b() {
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.db.1
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry entry : new ConcurrentHashMap(db.this.c).entrySet()) {
                    ho.a("AdRecordManager", "entry key: %s time: %s", entry.getKey(), Long.valueOf(((ContentRecord) entry.getValue()).bd()));
                    if (System.currentTimeMillis() - ((ContentRecord) entry.getValue()).bd() > 86400000) {
                        db.this.c.remove(entry.getKey());
                    }
                }
            }
        });
    }

    public static db a() {
        db dbVar;
        synchronized (f6690a) {
            if (b == null) {
                b = new db();
            }
            dbVar = b;
        }
        return dbVar;
    }

    private db() {
    }
}
