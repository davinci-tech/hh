package defpackage;

import android.content.Context;
import com.alipay.sdk.m.p.e;
import com.huawei.hms.network.base.common.trans.FileBinary;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class lr extends e {
    @Override // com.alipay.sdk.m.p.e
    public String a(lv lvVar, String str, JSONObject jSONObject) {
        return str;
    }

    @Override // com.alipay.sdk.m.p.e
    public Map<String, String> a(boolean z, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(e.c, String.valueOf(z));
        hashMap.put(e.f, FileBinary.HEAD_VALUE_CONTENT_TYPE_OCTET_STREAM);
        hashMap.put(e.i, "CBC");
        return hashMap;
    }

    @Override // com.alipay.sdk.m.p.e
    public JSONObject a() throws JSONException {
        return null;
    }

    @Override // com.alipay.sdk.m.p.e
    public boolean c() {
        return false;
    }

    @Override // com.alipay.sdk.m.p.e
    public String a(lv lvVar) throws JSONException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("api_name", "/sdk/log");
        hashMap.put(e.l, "1.0.0");
        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("log_v", "1.0");
        return a(lvVar, hashMap, hashMap2);
    }

    @Override // com.alipay.sdk.m.p.e
    public lh a(lv lvVar, Context context, String str) throws Throwable {
        return a(lvVar, context, str, "https://mcgw.alipay.com/sdklog.do", true);
    }
}
