package com.huawei.hwcloudjs.service.auth.bean;

import android.text.TextUtils;
import com.huawei.hwcloudjs.d.b.a.c;
import com.huawei.hwcloudjs.f.d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class b extends c {
    private static final String c = "AuthResponseBean";
    private static final String d = "NSP_STATUS";
    private int e = -1;
    private List<String> f;
    private List<String> g;

    public List<String> f() {
        return this.g;
    }

    public int e() {
        return this.e;
    }

    public List<String> d() {
        return this.f;
    }

    public void c(int i) {
        this.e = i;
    }

    public void b(List<String> list) {
        this.g = list;
    }

    @Override // com.huawei.hwcloudjs.d.b.a.c
    public String[] a() {
        return new String[]{d};
    }

    public void a(List<String> list) {
        this.f = list;
    }

    @Override // com.huawei.hwcloudjs.d.b.a.c
    public void a(String str, Map<String, String> map) {
        JSONArray optJSONArray;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String str2 = map.get(d);
            if (!TextUtils.isEmpty(str2) && !"0".equals(str2)) {
                try {
                    c(Integer.parseInt(str2));
                    return;
                } catch (NumberFormatException unused) {
                    c(-1);
                    return;
                }
            }
            c(0);
            JSONArray optJSONArray2 = jSONObject.optJSONArray("scopes");
            if (optJSONArray2 != null) {
                try {
                    ArrayList arrayList = new ArrayList();
                    int length = optJSONArray2.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject optJSONObject = optJSONArray2.optJSONObject(i);
                        if (optJSONObject != null && (optJSONArray = optJSONObject.optJSONArray("permissions")) != null) {
                            int length2 = optJSONArray.length();
                            for (int i2 = 0; i2 < length2; i2++) {
                                arrayList.add(optJSONArray.getString(i2));
                            }
                        }
                    }
                    a(arrayList);
                } catch (JSONException unused2) {
                    d.b(c, "jsonArray JSONException", true);
                    return;
                }
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject("appAttr");
            if (optJSONObject2 != null) {
                String optString = optJSONObject2.optString("securityUrl");
                if (TextUtils.isEmpty(optString)) {
                    return;
                }
                b(Arrays.asList(optString.split("\\,")));
            }
        } catch (JSONException unused3) {
            d.b(c, "resultObj JSONException", true);
        }
    }
}
