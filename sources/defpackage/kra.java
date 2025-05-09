package defpackage;

import android.content.Context;
import com.huawei.hwidauth.c.k;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* loaded from: classes9.dex */
public class kra extends k {
    private String e = "/oauth2/v3/revoke?";
    private Context h;
    private String j;

    public kra(Context context, String str) {
        this.j = str;
        this.h = context;
    }

    @Override // com.huawei.hwidauth.c.k
    public String a_() {
        StringBuilder sb = new StringBuilder();
        try {
            String encode = URLEncoder.encode(this.j, "UTF-8");
            sb.append("token");
            sb.append("=");
            sb.append(encode);
        } catch (UnsupportedEncodingException unused) {
            ksy.b("revokeRequest", "UnsupportedEncodingException", true);
        }
        return sb.toString();
    }

    @Override // com.huawei.hwidauth.c.k
    public String b() {
        return this.e + "hms_version=" + ksi.c(this.h) + "&sdkVersion=6.12.0.302";
    }

    @Override // com.huawei.hwidauth.c.k
    public String c() {
        return "";
    }

    @Override // com.huawei.hwidauth.c.k
    public String a() throws IllegalArgumentException, IllegalStateException, IOException {
        return "";
    }
}
