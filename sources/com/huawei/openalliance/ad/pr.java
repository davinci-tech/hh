package com.huawei.openalliance.ad;

import android.content.Context;
import android.util.Log;
import com.huawei.openalliance.ad.beans.metadata.Template;
import com.huawei.openalliance.ad.db.bean.TemplateRecord;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class pr implements qv {

    /* renamed from: a, reason: collision with root package name */
    private static final String f7449a = "pr";
    private gd b;
    private Context c;

    @Override // com.huawei.openalliance.ad.qv
    public void a(List<Template> list) {
        if (list == null || list.isEmpty()) {
            Log.i(f7449a, "templates is empty, don't need to save");
            return;
        }
        Iterator<Template> it = list.iterator();
        while (it.hasNext()) {
            TemplateRecord a2 = pf.a(it.next());
            if (a2 != null) {
                this.b.a(a2);
            }
        }
    }

    public pr(Context context) {
        this.c = context.getApplicationContext();
        this.b = fk.a(context);
    }
}
