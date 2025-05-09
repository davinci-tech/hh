package com.huawei.agconnect.config.impl;

import android.content.Context;
import android.util.Log;
import com.huawei.agconnect.config.AesDecrypt;
import com.huawei.agconnect.config.IDecrypt;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class h implements AesDecrypt {

    /* renamed from: a, reason: collision with root package name */
    private final Context f1710a;
    private final String b;
    private IDecrypt c;

    @Override // com.huawei.agconnect.config.AesDecrypt
    public IDecrypt decryptComponent() {
        String a2 = l.a(this.f1710a, this.b, "agc_plugin_", "crypto_component");
        if (a2 == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(new String(Hex.decodeHexString(a2), "utf-8"));
            return new f(new d(jSONObject.getString("rx"), jSONObject.getString("ry"), jSONObject.getString("rz"), jSONObject.getString("salt"), jSONObject.getString("algorithm"), jSONObject.getInt("iterationCount")));
        } catch (UnsupportedEncodingException | IllegalArgumentException | JSONException e) {
            Log.e("AGC_FlexibleDecrypt", "FlexibleDecrypt exception: " + e.getMessage());
            return null;
        }
    }

    @Override // com.huawei.agconnect.config.AesDecrypt
    public String decrypt(String str, String str2) {
        if (this.c == null) {
            this.c = decryptComponent();
        }
        if (this.c == null) {
            Log.w("AGC_FlexibleDecrypt", "decrypt Flexible Decrypt error, use old instead");
            this.c = new g(this.f1710a, this.b).decryptComponent();
        }
        return this.c.decrypt(l.a(this.f1710a, this.b, "agc_plugin_", str), str2);
    }

    public h(Context context, String str) {
        Log.d("AGC_FlexibleDecrypt", "init");
        this.f1710a = context;
        this.b = str;
    }
}
