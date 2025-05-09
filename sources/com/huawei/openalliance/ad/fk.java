package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.TemplateRecord;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class fk extends ep implements gd {
    private static fk c;
    private static final byte[] d = new byte[0];
    private static final byte[] e = new byte[0];

    @Override // com.huawei.openalliance.ad.ep
    public void e() {
        super.e();
        a(TemplateRecord.class, (fi) null, (String[]) null);
    }

    @Override // com.huawei.openalliance.ad.gd
    public void a(TemplateRecord templateRecord) {
        synchronized (e) {
            String a2 = templateRecord.a();
            if (a(a2) == null) {
                a(TemplateRecord.class, templateRecord.d(this.f6846a));
            } else {
                a(TemplateRecord.class, templateRecord.d(this.f6846a), fi.TEMPLATE_BY_ID_WHERE, new String[]{a2});
            }
        }
    }

    @Override // com.huawei.openalliance.ad.gd
    public List<String> a() {
        return a(a(TemplateRecord.class, new String[]{"templateId"}, null, null, null, null));
    }

    public TemplateRecord a(String str) {
        List a2 = a(TemplateRecord.class, null, fi.TEMPLATE_BY_ID_WHERE, new String[]{str}, null, null);
        if (a2.isEmpty()) {
            return null;
        }
        return (TemplateRecord) a2.get(0);
    }

    private List<String> a(List<TemplateRecord> list) {
        if (list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<TemplateRecord> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().a());
        }
        return arrayList;
    }

    public static fk a(Context context) {
        fk fkVar;
        synchronized (d) {
            if (c == null) {
                c = new fk(context);
            }
            fkVar = c;
        }
        return fkVar;
    }

    protected fk(Context context) {
        super(context);
    }
}
