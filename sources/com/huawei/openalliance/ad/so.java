package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.ApkInfo;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.tf;

/* loaded from: classes5.dex */
public class so extends ta {
    private qq c;

    @Override // com.huawei.openalliance.ad.ta
    public boolean a() {
        ApkInfo r;
        ho.b("AppEnterAction", "handle app enter action");
        MetaData metaData = (MetaData) com.huawei.openalliance.ad.utils.be.b(this.b.g(), MetaData.class, new Class[0]);
        if (metaData != null && (r = metaData.r()) != null) {
            String a2 = r.a();
            tf.a aVar = new tf.a();
            aVar.a(r).a(this.b);
            if (com.huawei.openalliance.ad.utils.i.a(this.f7529a, a2, aVar.a())) {
                b("app");
                com.huawei.openalliance.ad.download.app.l.a(this.f7529a, this.b.ae());
                this.c.a((Integer) 1);
                return true;
            }
        }
        return b();
    }

    public so(Context context, ContentRecord contentRecord) {
        super(context, contentRecord);
        ou ouVar = new ou(context, sc.a(context, contentRecord.e()));
        this.c = ouVar;
        ouVar.a(contentRecord);
    }
}
