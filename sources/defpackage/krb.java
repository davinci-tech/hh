package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwidauth.c.k;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* loaded from: classes5.dex */
public class krb extends k {
    private static String e = "/oauth2/v3/silent_token?";
    private Context g;
    private kre h;

    public krb(Context context, kre kreVar) {
        this.h = kreVar;
        this.g = context;
    }

    @Override // com.huawei.hwidauth.c.k
    public String b() {
        return e + "client_id=" + this.h.c() + "&cVersion=HwID_6.12.0.302&hms_version=" + ksi.c(this.g) + "&sdkVersion6.12.0.302";
    }

    @Override // com.huawei.hwidauth.c.k
    public String a_() {
        StringBuilder sb = new StringBuilder("client_id=");
        sb.append(this.h.c());
        sb.append("&grant_type=");
        sb.append(this.h.a());
        sb.append("&device_type=");
        sb.append(this.h.g());
        sb.append("&package_name=");
        sb.append(this.h.h());
        sb.append("&device_id=");
        sb.append(this.h.l());
        sb.append("&need_code=");
        sb.append(this.h.o());
        sb.append("&token_type=");
        sb.append(this.h.n());
        sb.append("&supportAlg=");
        sb.append(this.h.d());
        if ("service_token".equals(this.h.a())) {
            sb.append("&service_token=");
            sb.append(this.h.j());
            sb.append("&siteId=");
            sb.append(this.h.i());
        } else if (Constants.PARAM_ACCESS_TOKEN.equals(this.h.a())) {
            sb.append("&uuid=");
            sb.append(this.h.e());
            try {
                sb.append("&");
                sb.append(Constants.PARAM_ACCESS_TOKEN);
                sb.append("=");
                sb.append(URLEncoder.encode(this.h.b(), "UTF-8"));
            } catch (UnsupportedEncodingException unused) {
                ksy.b("SilentTokenRequest", "urlencode: UnsupportedEncodingException", true);
            }
        }
        if (!TextUtils.isEmpty(this.h.f())) {
            sb.append("&scope=");
            sb.append(this.h.f());
        }
        if (!TextUtils.isEmpty(this.h.k())) {
            sb.append("&carrier_id=");
            sb.append(this.h.k());
        }
        String sb2 = sb.toString();
        ksy.b("SilentTokenRequest", "urlencode: url = " + sb2, false);
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
