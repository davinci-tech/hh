package com.huawei.openalliance.ad;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.huawei.openalliance.ad.activity.SafeIntent;
import com.huawei.openalliance.ad.beans.metadata.ApkInfo;
import com.huawei.openalliance.ad.beans.metadata.MetaData;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.AppInfo;
import com.huawei.openalliance.ad.tf;

/* loaded from: classes5.dex */
public class sm extends ta {
    private qq c;

    @Override // com.huawei.openalliance.ad.ta
    public boolean a() {
        String str;
        AppInfo ae;
        String packageName;
        String G;
        tf.a aVar;
        boolean z;
        Intent b;
        ho.b("AppAction", "handle app action");
        try {
            ae = this.b.ae();
            packageName = ae == null ? null : ae.getPackageName();
            G = this.b.G();
            aVar = new tf.a();
            aVar.a(ae).a(this.b);
            tf a2 = aVar.a();
            String G2 = this.b.G();
            Intent b2 = com.huawei.openalliance.ad.utils.i.b(this.f7529a, G2, packageName, a2);
            if (TextUtils.isEmpty(G2) || b2 == null || !"hwpps".equals(b2.getScheme())) {
                z = true;
            } else {
                G2 = G2 + "&PPSFromIntent=hwpps";
                z = false;
            }
            b = com.huawei.openalliance.ad.utils.i.b(this.f7529a, G2, packageName, a2);
        } catch (ActivityNotFoundException unused) {
            str = "activity not exist";
            ho.c("AppAction", str);
            d();
            return b();
        } catch (Exception unused2) {
            str = "handle intent url fail";
            ho.c("AppAction", str);
            d();
            return b();
        }
        if (b == null) {
            ho.c("AppAction", "cannot find target activity");
            d();
            return b();
        }
        b.addFlags(268435456);
        a(b, G);
        a(b);
        if (ae != null) {
            aVar.a(ae);
        }
        aVar.a(b);
        com.huawei.openalliance.ad.utils.i.a(this.f7529a, b, aVar.a());
        b(com.huawei.openalliance.ad.utils.j.a(packageName, ae));
        com.huawei.openalliance.ad.download.app.l.a(this.f7529a, this.b.ae());
        if (z) {
            this.c.a(EventType.INTENTSUCCESS, Integer.valueOf(com.huawei.openalliance.ad.utils.j.b(packageName, ae)), (Integer) null);
        }
        return true;
    }

    private void d() {
        String str;
        ApkInfo r;
        try {
            MetaData metaData = (MetaData) com.huawei.openalliance.ad.utils.be.b(this.b.g(), MetaData.class, new Class[0]);
            this.c.a(EventType.INTENTFAIL, (Integer) 1, Integer.valueOf((metaData == null || (r = metaData.r()) == null || com.huawei.openalliance.ad.utils.i.b(this.f7529a, r.a()) == null) ? 1 : 2));
        } catch (IllegalStateException unused) {
            str = "recordOpenFailEvent IllegalStateException";
            ho.c("AppAction", str);
        } catch (Exception e) {
            str = "recordOpenFailEvent " + e.getClass().getSimpleName();
            ho.c("AppAction", str);
        }
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (URLUtil.isNetworkUrl(str)) {
            ho.b("AppAction", "uri is http or https url.");
            return false;
        }
        try {
            Uri parse = Uri.parse(str);
            String host = parse.getHost();
            if (TextUtils.equals("hwpps", parse.getScheme())) {
                return TextUtils.equals(Constants.SCHEME_HOST, host);
            }
            return false;
        } catch (Throwable th) {
            ho.c("AppAction", "isHwPPSUri exception." + th.getClass().getSimpleName());
            return false;
        }
    }

    private void a(Intent intent, String str) {
        if (intent == null || TextUtils.isEmpty(str) || str.indexOf("hwpps") <= 0) {
            return;
        }
        intent.addFlags(268435456);
    }

    private void a(Intent intent) {
        SafeIntent safeIntent = new SafeIntent(intent);
        String c = com.huawei.openalliance.ad.inter.a.a().c();
        ho.b("AppAction", "at is null ? " + TextUtils.isEmpty(c));
        if (TextUtils.isEmpty(c)) {
            return;
        }
        if (!a(safeIntent.getDataString())) {
            ho.b("AppAction", "isHwPPSUri false.");
        } else if (com.huawei.openalliance.ad.utils.i.c(this.f7529a)) {
            safeIntent.putExtra("accessToken", c);
        } else {
            ho.b("AppAction", "isHMSInstalled false.");
        }
    }

    public sm(Context context, ContentRecord contentRecord) {
        super(context, contentRecord);
        ou ouVar = new ou(context, sc.a(context, contentRecord.e()));
        this.c = ouVar;
        ouVar.a(contentRecord);
    }
}
