package com.huawei.updatesdk.b.d;

import android.text.TextUtils;
import com.huawei.hms.network.embedded.j2;
import java.io.File;
import java.net.HttpURLConnection;

/* loaded from: classes7.dex */
public class d {
    public HttpURLConnection a(String str) {
        HttpURLConnection a2 = com.huawei.updatesdk.a.a.b.b.a(str, com.huawei.updatesdk.a.b.a.a.c().a());
        a2.setConnectTimeout(7000);
        a2.setReadTimeout(10000);
        a2.setUseCaches(false);
        a2.setDoInput(true);
        a2.setRequestProperty(j2.v, "identity");
        a2.setInstanceFollowRedirects(true);
        return a2;
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final d f10834a = new d();
    }

    public static String b() {
        String b = com.huawei.updatesdk.a.b.a.a.c().b();
        if (TextUtils.isEmpty(b)) {
            return "";
        }
        String str = b + "/updatesdk";
        File file = new File(str);
        return (file.exists() || file.mkdirs()) ? str : "";
    }

    public static d a() {
        return a.f10834a;
    }
}
