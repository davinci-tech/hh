package com.huawei.openalliance.ad;

import android.content.ContentValues;
import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.v3.Asset;
import com.huawei.openalliance.ad.beans.metadata.v3.TemplateData;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.ContentRecordV3;
import com.huawei.openalliance.ad.db.bean.ContentTemplateRecord;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class et extends es {
    private static et c;
    private static final byte[] d = new byte[0];

    @Override // com.huawei.openalliance.ad.es, com.huawei.openalliance.ad.fs
    public List<ContentRecord> c() {
        return a(a(), new String[]{"contentId", "slotId", "templateId"}, fi.CONTENT_EXPIRE_WHERE, new String[]{String.valueOf(com.huawei.openalliance.ad.utils.ao.c()), String.valueOf(0)}, null, null);
    }

    @Override // com.huawei.openalliance.ad.es, com.huawei.openalliance.ad.fs
    public void b(ContentRecord contentRecord, List<String> list) {
        if (contentRecord == null) {
            return;
        }
        ContentValues d2 = contentRecord.d(this.f6846a);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            d2.remove(it.next());
        }
        a(a(), d2, fi.CONTENT_RECORD_BY_IDS, new String[]{contentRecord.m(), contentRecord.aN(), contentRecord.l()});
    }

    @Override // com.huawei.openalliance.ad.es, com.huawei.openalliance.ad.fs
    public List<ContentRecord> b(String str, int i, long j, int i2) {
        ArrayList arrayList = new ArrayList();
        fi fiVar = 1 == i ? fi.CONTENT_V3_PORTRAIT_CACHE_SHOW_WHERE : fi.CONTENT_V3_LANDSCAPE_CACHE_SHOW_WHERE;
        long c2 = com.huawei.openalliance.ad.utils.ao.c();
        for (ContentRecord contentRecord : a(a(), null, fiVar, new String[]{String.valueOf(c2), String.valueOf(c2), str, String.valueOf(c2 - j), com.huawei.openalliance.ad.utils.ao.b("yyyy-MM-dd")}, "priority asc, displayCount asc, updateTime desc", String.valueOf(4))) {
            if (contentRecord != null) {
                ContentTemplateRecord a2 = ev.a(this.f6846a).a(contentRecord.m(), contentRecord.aN());
                if (a2 != null && !com.huawei.openalliance.ad.utils.bg.a(a2.c())) {
                    boolean z = true;
                    for (Asset asset : a2.c()) {
                        if (asset != null && !com.huawei.openalliance.ad.utils.cz.b(asset.g())) {
                            if (!com.huawei.openalliance.ad.utils.ae.c(this.f6846a, asset.g(), Constants.TPLATE_CACHE)) {
                                z = false;
                            }
                            if (!z) {
                                z = com.huawei.openalliance.ad.utils.c.a(this.f6846a, contentRecord, a(asset), asset);
                            }
                        }
                    }
                    if (z) {
                        contentRecord.l(a2.c());
                        contentRecord.a(new TemplateData(a2.d(), a2.e(), a2.f()));
                        contentRecord.M(fl.a(this.f6846a).a(contentRecord.l(), contentRecord.aN()));
                        arrayList.add(contentRecord);
                    }
                }
                b(contentRecord.m(), contentRecord.aN(), contentRecord.l(), "delete not exists");
            }
        }
        return arrayList;
    }

    @Override // com.huawei.openalliance.ad.es, com.huawei.openalliance.ad.fs
    public void a(String str, String str2, String str3, String str4) {
        b(str, str2, str3, str4);
    }

    @Override // com.huawei.openalliance.ad.es, com.huawei.openalliance.ad.fs
    public List<ContentRecord> a(int i) {
        return a(ContentRecord.class, "SELECT ContentRecordV3.*,ContentTemplateRecord.assets From ContentRecordV3 INNER JOIN ContentTemplateRecord ON ContentRecordV3.adType = " + i + " AND ContentTemplateRecord.contentId = ContentRecordV3.contentId AND ContentTemplateRecord.templateId = ContentRecordV3.templateId;", (String[]) null);
    }

    @Override // com.huawei.openalliance.ad.es
    protected Class<? extends com.huawei.openalliance.ad.db.bean.a> a() {
        return ContentRecordV3.class;
    }

    @Override // com.huawei.openalliance.ad.es, com.huawei.openalliance.ad.fs
    public ContentRecord a(String str, String str2, String str3, int i, long j) {
        return a(fi.CONTENT_V3_REAL_SHOW_WHERE, new String[]{str, str2, str3, String.valueOf(j), String.valueOf(j)});
    }

    private void b(String str, String str2, String str3, String str4) {
        ho.a("ContentRecordV3Dao", "deleteContentByIds, contentId= %s, templateId= %s, slotId= %s, reason: %s", str, str2, str3, str4);
        a(a(), fi.CONTENT_RECORD_BY_IDS, new String[]{str, str2, str3});
        ev.a(this.f6846a).a(str, str2, str4);
    }

    public static et b(Context context) {
        et etVar;
        synchronized (d) {
            if (c == null) {
                c = new et(context);
            }
            etVar = c;
        }
        return etVar;
    }

    private String a(Asset asset) {
        if (asset.d() != null) {
            return asset.d().a();
        }
        if (asset.e() != null) {
            return asset.e().a();
        }
        return null;
    }

    private ContentRecord a(fi fiVar, String[] strArr) {
        List a2 = a(a(), null, fiVar, strArr, "priority asc, displayCount asc, updateTime desc", String.valueOf(4));
        if (com.huawei.openalliance.ad.utils.bg.a(a2)) {
            return null;
        }
        return (ContentRecord) a2.get(0);
    }

    protected et(Context context) {
        super(context);
    }
}
