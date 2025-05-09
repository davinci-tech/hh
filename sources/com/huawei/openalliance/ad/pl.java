package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.HiAd;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class pl {

    /* renamed from: a, reason: collision with root package name */
    private Context f7439a;
    private ContentRecord b;

    public void a(ContentRecord contentRecord) {
        this.b = contentRecord;
    }

    public void a() {
        if (!HiAd.a(this.f7439a).m()) {
            ho.b("PreloadWebViewProcessor", "app disable preload webView");
            return;
        }
        ContentRecord contentRecord = this.b;
        if (contentRecord == null || contentRecord.h() == null) {
            ho.b("PreloadWebViewProcessor", "contentRecord or meataData is null");
            return;
        }
        final String A = this.b.A();
        int G = this.b.h().G();
        if (G == 0) {
            ho.b("PreloadWebViewProcessor", "not preload url. enablePreload: %s", Integer.valueOf(G));
            return;
        }
        List<Integer> M = this.b.M();
        if (com.huawei.openalliance.ad.utils.bg.a(M) || a(M)) {
            ho.b("PreloadWebViewProcessor", "not preload url. ClickActionList not support");
            return;
        }
        int t = fh.b(this.f7439a).t();
        if (t == 1 || (t == 0 && com.huawei.openalliance.ad.utils.bv.a(this.f7439a))) {
            ho.b("PreloadWebViewProcessor", "preLoad");
            com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.pl.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        com.huawei.openalliance.ad.views.web.b.a(pl.this.f7439a).a(A);
                    } catch (Throwable unused) {
                        ho.c("PreloadWebViewProcessor", "preLoad fail");
                    }
                }
            });
        }
    }

    private boolean a(List<Integer> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(it.next()));
        }
        String u = fh.b(this.f7439a).u();
        if (TextUtils.isEmpty(u)) {
            return true;
        }
        return Collections.disjoint(Arrays.asList(u.split(",")), arrayList);
    }

    public pl(Context context) {
        this.f7439a = context.getApplicationContext();
    }
}
