package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.ApkInfo;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import java.util.List;

/* loaded from: classes5.dex */
public class qa implements pz {

    /* renamed from: a, reason: collision with root package name */
    private Context f7461a;

    @Override // com.huawei.openalliance.ad.pz
    public int b() {
        return 3;
    }

    @Override // com.huawei.openalliance.ad.pz
    public boolean a(final String str, final int i, final int i2, final Content content) {
        if (content == null) {
            return false;
        }
        if (ho.a()) {
            ho.a("PreCheckFilter", "filterContents adType: %d contentid: %s", Integer.valueOf(i), content.g());
        }
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.qa.1
            @Override // java.lang.Runnable
            public void run() {
                qa.this.b(str, i, i2, content);
            }
        });
        return false;
    }

    public int a(MetaData metaData) {
        if (metaData == null) {
            return -1;
        }
        String n = metaData.n();
        return !TextUtils.isEmpty(n) ? a(metaData, n) : c(metaData);
    }

    private int d(MetaData metaData) {
        ApkInfo r;
        if (metaData == null || (r = metaData.r()) == null || TextUtils.isEmpty(r.a())) {
            return 3;
        }
        boolean a2 = com.huawei.openalliance.ad.utils.i.a(this.f7461a, r.a());
        if (!TextUtils.isEmpty(r.J())) {
            a(r.J(), a2);
        }
        return a2 ? 1 : 2;
    }

    private ou c(String str, int i, int i2, Content content) {
        ContentRecord a2 = pf.a(str, content, i, null);
        if (a2 == null) {
            ho.b("PreCheckFilter", "contentRecord is null");
            return null;
        }
        a2.o(i2);
        Context context = this.f7461a;
        ou ouVar = new ou(context, sc.a(context, i));
        ouVar.a(a2);
        return ouVar;
    }

    private int c(MetaData metaData) {
        int d = d(metaData);
        if (d != 1) {
            return d != 2 ? 9 : 6;
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, int i, int i2, Content content) {
        ou c;
        MetaData c2 = content.c();
        if (c2 == null || (c = c(str, i, i2, content)) == null) {
            return;
        }
        c.a(a(c2), b(c2));
    }

    private String b(MetaData metaData) {
        ApkInfo r = metaData.r();
        return r != null ? com.huawei.openalliance.ad.utils.d.a(this.f7461a, r.a()) : "";
    }

    private void a(String str, boolean z) {
        List<String> w = com.huawei.openalliance.ad.utils.d.w(this.f7461a);
        if (z) {
            if (w.contains(str)) {
                return;
            }
            w.add(str);
            com.huawei.openalliance.ad.utils.d.a(this.f7461a, w);
            ho.a("PreCheckFilter", "add app to insApp file ,PkgNameEncoded: %s", str);
            return;
        }
        if (w.contains(str)) {
            w.remove(str);
            com.huawei.openalliance.ad.utils.d.a(this.f7461a, w);
            ho.a("PreCheckFilter", "remove app to insApp file ,PkgNameEncoded: %s", str);
        }
    }

    private int a(MetaData metaData, String str) {
        ApkInfo r;
        int d = d(metaData);
        if (com.huawei.openalliance.ad.utils.i.b(this.f7461a, str, (metaData == null || (r = metaData.r()) == null) ? null : r.a(), null) == null) {
            if (d != 1) {
                return d != 2 ? 7 : 2;
            }
            return 3;
        }
        if (d != 1) {
            return d != 2 ? 8 : 5;
        }
        return 4;
    }

    qa(Context context) {
        this.f7461a = context.getApplicationContext();
    }
}
