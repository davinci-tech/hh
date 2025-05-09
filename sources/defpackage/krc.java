package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwidauth.c.k;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* loaded from: classes5.dex */
public class krc extends k {
    private static String e = "/oauth2/v3/token?";
    private Context g;
    private krd j;

    public krc(Context context, krd krdVar) {
        this.j = krdVar;
        this.g = context;
    }

    @Override // com.huawei.hwidauth.c.k
    public String b() {
        return e + "client_id=" + this.j.a() + "&cVersion=HwID_6.12.0.302&hms_version=" + ksi.c(this.g) + "&sdkVersion6.12.0.302";
    }

    @Override // com.huawei.hwidauth.c.k
    public String a_() {
        ksy.b("GwTokenRequest", "urlencode: enter", true);
        StringBuilder sb = new StringBuilder("client_id=");
        sb.append(this.j.a());
        sb.append("&grant_type=");
        sb.append(this.j.b());
        sb.append("&redirect_uri=");
        sb.append(this.j.m());
        sb.append("&need_code=");
        sb.append(this.j.h());
        sb.append("&need_open_uid=");
        sb.append(this.j.f());
        sb.append("&supportAlg=");
        sb.append(this.j.c());
        try {
            sb.append("&");
            sb.append("code");
            sb.append("=");
            sb.append(URLEncoder.encode(this.j.e(), "UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            ksy.b("GwTokenRequest", "urlencode: " + e2.getClass().getSimpleName(), true);
        }
        if (!TextUtils.isEmpty(this.j.i())) {
            sb.append("&carrier_id=");
            sb.append(this.j.i());
        }
        if (this.j.j() != null) {
            sb.append("&code_type=");
            sb.append(this.j.j());
            if (this.j.j().intValue() == 1) {
                sb.append("&uuid=");
                sb.append(this.j.l());
                sb.append("&device_id=");
                sb.append(this.j.o());
                sb.append("&device_type=");
                sb.append(this.j.n());
                sb.append("&package_name=");
                sb.append(this.j.k());
            }
        }
        if (!TextUtils.isEmpty(this.j.g())) {
            sb.append("&code_verifier=");
            sb.append(this.j.g());
        }
        if (!TextUtils.isEmpty(this.j.d())) {
            sb.append("&client_secret=");
            sb.append(this.j.d());
        }
        String sb2 = sb.toString();
        ksy.b("GwTokenRequest", "urlencode: url = " + sb2, false);
        return sb2;
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
