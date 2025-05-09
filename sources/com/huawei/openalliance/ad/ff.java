package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.SdkCfgSha256Record;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class ff extends ep {
    private static ff c;
    private static final byte[] d = new byte[0];
    private static final byte[] e = new byte[0];

    public void a(SdkCfgSha256Record sdkCfgSha256Record) {
        long currentTimeMillis = System.currentTimeMillis();
        if (sdkCfgSha256Record == null || this.f6846a == null) {
            return;
        }
        synchronized (e) {
            ArrayList arrayList = new ArrayList();
            String simpleName = a().getSimpleName();
            String a2 = sdkCfgSha256Record.a();
            arrayList.add(new de(simpleName, fi.SDKCFG_SHA256_BY_PKG.a(), new String[]{a2}, fi.SDKCFG_SHA256_BY_PKG.a(), new String[]{a2}, sdkCfgSha256Record.d(this.f6846a)));
            c(arrayList);
        }
        ho.a("SdkCfgSha256RecordDao", "insertOrUpdateContents duration: %s ms", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    public String a(String str) {
        SdkCfgSha256Record b = b(str);
        if (b != null) {
            return b.b();
        }
        return null;
    }

    protected Class<? extends SdkCfgSha256Record> a() {
        return SdkCfgSha256Record.class;
    }

    private SdkCfgSha256Record b(String str) {
        List a2 = a(SdkCfgSha256Record.class, null, fi.SDKCFG_SHA256_BY_PKG, new String[]{str}, null, null);
        if (!com.huawei.openalliance.ad.utils.bg.a(a2)) {
            return (SdkCfgSha256Record) a2.get(0);
        }
        ho.a("SdkCfgSha256RecordDao", "no match sha256");
        return null;
    }

    public static ff a(Context context) {
        ff ffVar;
        synchronized (d) {
            if (c == null) {
                c = new ff(context);
            }
            ffVar = c;
        }
        return ffVar;
    }

    private ff(Context context) {
        super(context);
    }
}
