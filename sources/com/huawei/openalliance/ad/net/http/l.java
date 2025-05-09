package com.huawei.openalliance.ad.net.http;

import android.content.Context;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.v;
import java.net.InetAddress;
import java.util.List;
import okhttp3.Dns;

/* loaded from: classes5.dex */
public class l implements Dns {

    /* renamed from: a, reason: collision with root package name */
    private static final Dns f7309a = Dns.SYSTEM;
    private boolean b;
    private Context c;

    @Override // okhttp3.Dns
    public List<InetAddress> lookup(String str) {
        ho.b("OkHttpDNS", "lookup for :" + cz.f(str) + ",useHuaweiDNS:" + this.b);
        if (this.b && v.a()) {
            List<InetAddress> a2 = v.a(this.c, str);
            if (!a2.isEmpty()) {
                return a2;
            }
        }
        return f7309a.lookup(str);
    }

    public l(Context context, boolean z) {
        this.c = context;
        this.b = z;
    }
}
