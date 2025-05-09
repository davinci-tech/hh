package defpackage;

import android.content.Context;
import com.alipay.sdk.m.p.e;
import defpackage.lf;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class lo extends e {
    @Override // com.alipay.sdk.m.p.e
    public String a(lv lvVar, String str, JSONObject jSONObject) {
        return str;
    }

    @Override // com.alipay.sdk.m.p.e
    public Map<String, String> a(boolean z, String str) {
        return new HashMap();
    }

    @Override // com.alipay.sdk.m.p.e
    public JSONObject a() {
        return null;
    }

    @Override // com.alipay.sdk.m.p.e
    public boolean c() {
        return false;
    }

    @Override // com.alipay.sdk.m.p.e
    public lh a(lv lvVar, Context context, String str) throws Throwable {
        ma.d("mspl", "mdap post");
        byte[] a2 = kw.a(str.getBytes(Charset.forName("UTF-8")));
        HashMap hashMap = new HashMap();
        hashMap.put("utdId", lw.c().e());
        hashMap.put("logHeader", "RAW");
        hashMap.put("bizCode", "alipaysdk");
        hashMap.put("productId", "alipaysdk_android");
        hashMap.put("Content-Encoding", "Gzip");
        hashMap.put("productVersion", "15.8.14");
        lf.b d = lf.d(context, new lf.a("https://loggw-exsdk.alipay.com/loggw/logUpload.do", hashMap, a2));
        ma.d("mspl", "mdap got " + d);
        if (d == null) {
            throw new RuntimeException("Response is null");
        }
        boolean a3 = e.a(d);
        try {
            byte[] bArr = d.c;
            if (a3) {
                bArr = kw.c(bArr);
            }
            return new lh("", new String(bArr, Charset.forName("UTF-8")));
        } catch (Exception e) {
            ma.c(e);
            return null;
        }
    }
}
