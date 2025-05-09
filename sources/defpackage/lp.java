package defpackage;

import com.alipay.sdk.m.p.e;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class lp extends e {
    @Override // com.alipay.sdk.m.p.e
    public JSONObject a() throws JSONException {
        return e.a("sdkConfig", "obtain");
    }

    @Override // com.alipay.sdk.m.p.e
    public boolean c() {
        return true;
    }

    @Override // com.alipay.sdk.m.p.e
    public String a(lv lvVar, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) throws JSONException {
        if (hashMap2 == null) {
            hashMap2 = new HashMap<>();
        }
        hashMap2.putAll(lt.b(lvVar));
        ma.d("mspl", "cf " + hashMap2);
        return super.a(lvVar, hashMap, hashMap2);
    }

    @Override // com.alipay.sdk.m.p.e
    public String b() {
        return "5.0.0";
    }
}
