package com.amap.api.col.p0003sl;

import android.content.Context;
import com.huawei.hms.network.embedded.j2;
import com.huawei.hms.network.embedded.x2;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes2.dex */
public final class cp extends hf<String, a> {
    private String j;
    private String k;
    private String l;
    private final String m;
    private boolean n;
    private String o;

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public byte[] f945a;
        public int b = -1;
        public String c = null;
        public boolean d = false;
    }

    @Override // com.amap.api.col.p0003sl.hf
    protected final /* bridge */ /* synthetic */ a a(String str) throws he {
        return null;
    }

    @Override // com.amap.api.col.p0003sl.hf
    protected final String c() {
        return null;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final boolean isSupportIPV6() {
        return true;
    }

    public cp(Context context, String str) {
        super(context, str);
        this.k = "1.0";
        this.l = "0";
        this.m = "lastModified";
        this.n = false;
        this.o = null;
        this.h = "/map/styles";
        this.i = true;
    }

    public cp(Context context, String str, boolean z) {
        super(context, str);
        this.k = "1.0";
        this.l = "0";
        this.m = "lastModified";
        this.o = null;
        this.n = z;
        if (z) {
            this.h = "/sdk/map/styles";
            this.isPostFlag = false;
        } else {
            this.h = "/map/styles";
        }
        this.i = true;
    }

    public final void b(String str) {
        this.o = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.amap.api.col.p0003sl.hf
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public a a(kb kbVar) throws he {
        List<String> list;
        if (kbVar == null) {
            return null;
        }
        a a2 = a(kbVar.f1250a);
        a2.d = a2.f945a != null;
        if (kbVar.b == null || !kbVar.b.containsKey("lastModified") || (list = kbVar.b.get("lastModified")) == null || list.size() <= 0) {
            return a2;
        }
        a2.c = list.get(0);
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.amap.api.col.p0003sl.hf
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public a a(byte[] bArr) throws he {
        a aVar = new a();
        aVar.f945a = bArr;
        if (this.n && bArr != null) {
            if (bArr.length == 0) {
                aVar.f945a = null;
            } else if (aVar.f945a.length <= 1024) {
                try {
                    if (new String(bArr, "utf-8").contains("errcode")) {
                        aVar.f945a = null;
                    }
                } catch (Exception e) {
                    iv.c(e, "CustomStyleRequest", "loadData");
                }
            }
        }
        return aVar;
    }

    @Override // com.amap.api.col.p0003sl.hf, com.amap.api.col.p0003sl.ka
    public final Map<String, String> getRequestHead() {
        hz a2 = dv.a();
        String b = a2 != null ? a2.b() : null;
        Hashtable hashtable = new Hashtable(16);
        hashtable.put("User-Agent", v.c);
        hashtable.put(j2.v, Constants.GZIP);
        hashtable.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", b, "3dmap"));
        hashtable.put("x-INFO", hq.a(this.g));
        hashtable.put(MedalConstants.EVENT_KEY, hn.f(this.g));
        hashtable.put("logversion", "2.1");
        return hashtable;
    }

    @Override // com.amap.api.col.p0003sl.cz, com.amap.api.col.p0003sl.ka
    public final Map<String, String> getParams() {
        Hashtable hashtable = new Hashtable(16);
        hashtable.put(MedalConstants.EVENT_KEY, hn.f(this.g));
        if (!this.n) {
            hashtable.put("output", "bin");
        } else {
            hashtable.put("sdkType", this.o);
        }
        hashtable.put("styleid", this.j);
        hashtable.put(x2.PROTOCOL, this.k);
        hashtable.put("ispublic", "1");
        hashtable.put("lastModified", this.l);
        String a2 = hq.a();
        String a3 = hq.a(this.g, a2, ia.b(hashtable));
        hashtable.put("ts", a2);
        hashtable.put("scode", a3);
        return hashtable;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return "http://restsdk.amap.com/v4" + this.h;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getIPV6URL() {
        return dv.a(getURL());
    }

    public final void c(String str) {
        this.j = str;
    }

    public final void d(String str) {
        this.l = str;
    }
}
