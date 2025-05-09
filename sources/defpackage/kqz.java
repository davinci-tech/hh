package defpackage;

import android.content.Context;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwidauth.c.k;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* loaded from: classes5.dex */
public class kqz extends k {
    private String f;
    private String g;
    private String i;
    private String j;
    private Context n;
    private String e = "/oauth2/v3/silent_code?";
    private String h = Constants.PARAM_ACCESS_TOKEN;

    @Override // com.huawei.hwidauth.c.k
    public String a() throws IllegalArgumentException, IllegalStateException, IOException {
        return null;
    }

    public kqz(Context context, String str, String str2, String str3, String str4) {
        this.n = context;
        this.g = str;
        this.i = str2;
        this.j = str3;
        this.f = str4;
    }

    @Override // com.huawei.hwidauth.c.k
    public String a_() {
        StringBuilder sb = new StringBuilder();
        try {
            String encode = URLEncoder.encode(this.j, "UTF-8");
            if (!encode.contains("openid")) {
                encode = "openid " + encode;
            }
            String encode2 = URLEncoder.encode(this.f, "UTF-8");
            String encode3 = URLEncoder.encode(this.i, "UTF-8");
            sb.append("grant_type");
            sb.append("=");
            sb.append(this.h);
            sb.append("&");
            sb.append(Constants.PARAM_ACCESS_TOKEN);
            sb.append("=");
            sb.append(encode3);
            sb.append("&");
            sb.append("scope");
            sb.append("=");
            sb.append(encode);
            sb.append("&");
            sb.append(CommonConstant.ReqAccessTokenParam.REDIRECT_URI);
            sb.append("=");
            sb.append(encode2);
            sb.append("&");
            sb.append("need_show_page");
            sb.append("=");
            sb.append("true");
            sb.append("&");
            sb.append(Constants.NONCE);
            sb.append("=");
            sb.append(d());
            sb.append("&");
            sb.append("include_granted_scopes");
            sb.append("=");
            sb.append("true");
            sb.append("&");
            sb.append("access_type");
            sb.append("=");
            sb.append("offline");
            sb.append("&");
            sb.append("state");
            sb.append("=");
            sb.append(d());
            sb.append("&");
            sb.append("uuid");
            sb.append(kti.b(this.n));
        } catch (UnsupportedEncodingException unused) {
            ksy.b("GwSilentCodeHttpRequest", "UnsupportedEncodingException", true);
        }
        return sb.toString();
    }

    private String d() {
        try {
            return URLEncoder.encode(ktc.c(), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            ksy.c("GwSilentCodeHttpRequest", "UnsupportedEncodingException", true);
            return "";
        }
    }

    @Override // com.huawei.hwidauth.c.k
    public String b() {
        return this.e + "client_id=" + this.g + "&hms_version=" + ksi.c(this.n) + "&sdkVersion=6.12.0.302";
    }

    @Override // com.huawei.hwidauth.c.k
    public String c() {
        return "";
    }
}
