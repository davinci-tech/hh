package com.huawei.updatesdk.b.e;

import android.content.Context;
import android.text.TextUtils;
import java.io.InputStream;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes7.dex */
class d {
    public static String a(Context context, String str, String str2) {
        String str3 = "";
        try {
            JSONArray jSONArray = new JSONObject(a(context, str)).getJSONArray("services");
            JSONArray jSONArray2 = jSONArray.getJSONObject(0).getJSONArray("servings");
            for (int i = 0; i < jSONArray2.length(); i++) {
                JSONObject jSONObject = jSONArray2.getJSONObject(i);
                if (TextUtils.equals(str2, jSONObject.getString("countryOrAreaGroup"))) {
                    str3 = jSONObject.getJSONObject("addresses").getString("ROOT");
                }
            }
        } catch (Exception unused) {
            com.huawei.updatesdk.a.a.a.a("JsonPareUrl", "Failed to obtain the default url.");
        }
        return str3;
    }

    private static String a(Context context, String str) {
        InputStream open = context.getAssets().open(str);
        byte[] bArr = new byte[open.available()];
        com.huawei.updatesdk.a.a.a.b("JsonPareUrl", "loadJSONFromAsset code: " + open.read(bArr));
        open.close();
        return new String(bArr, "UTF-8");
    }
}
