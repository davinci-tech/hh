package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.metadata.MediaFile;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.beans.metadata.Precontent;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.db.bean.PlacementRecord;
import com.huawei.openalliance.ad.inter.data.IPlacementAd;
import com.huawei.openalliance.ad.utils.m;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class op {

    /* renamed from: a, reason: collision with root package name */
    fy f7381a;
    Context b;
    qx c;
    private AdContentRsp d;

    protected abstract void b(AdContentRsp adContentRsp);

    protected Map<String, List<IPlacementAd>> b(List<Precontent> list) {
        HashMap hashMap = new HashMap(4);
        if (list == null) {
            return hashMap;
        }
        byte[] b = com.huawei.openalliance.ad.utils.cp.b(this.b);
        for (final Precontent precontent : list) {
            if (precontent != null) {
                com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.op.2
                    @Override // java.lang.Runnable
                    public void run() {
                        op.this.f7381a.b(pf.b(precontent.a(), precontent, 60));
                    }
                });
                String a2 = precontent.a();
                if (!hashMap.containsKey(a2)) {
                    hashMap.put(a2, new ArrayList(4));
                }
                List list2 = (List) hashMap.get(a2);
                Content content = new Content(precontent);
                if (content.c() != null) {
                    com.huawei.openalliance.ad.inter.data.g a3 = a(a2, content, b);
                    a3.i(this.d.k());
                    a3.c(this.d.n());
                    a3.n(this.d.p());
                    a3.o(this.d.q());
                    list2.add(a3);
                }
            }
        }
        return hashMap;
    }

    protected void a(final List<String> list) {
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            return;
        }
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.op.1
            @Override // java.lang.Runnable
            public void run() {
                MetaData metaData;
                for (String str : list) {
                    PlacementRecord a2 = op.this.f7381a.a(str);
                    if (a2 != null && (metaData = (MetaData) com.huawei.openalliance.ad.utils.be.b(a2.b(), MetaData.class, new Class[0])) != null) {
                        op.this.a(metaData.u());
                        List<MediaFile> w = metaData.w();
                        if (!com.huawei.openalliance.ad.utils.bg.a(w)) {
                            Iterator<MediaFile> it = w.iterator();
                            while (it.hasNext()) {
                                op.this.a(it.next());
                            }
                        }
                    }
                    op.this.f7381a.b(str);
                }
            }
        }, m.a.IO, false);
    }

    public void a(AdContentRsp adContentRsp) {
        if (adContentRsp == null) {
            this.c.a(800);
            return;
        }
        this.d = adContentRsp;
        a(adContentRsp.d());
        b(adContentRsp);
    }

    com.huawei.openalliance.ad.inter.data.g a(String str, Content content, byte[] bArr) {
        return pi.a(str, content, bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MediaFile mediaFile) {
        if (mediaFile == null) {
            return;
        }
        dh.a(this.b, "normal").j(dk.d(mediaFile.e()));
    }

    public static op a(Context context, qx qxVar, boolean z) {
        return z ? new pk(context, qxVar) : new pj(context, qxVar);
    }

    protected op(Context context, qx qxVar) {
        this.b = context;
        this.f7381a = fc.a(context);
        this.c = qxVar;
    }
}
