package com.huawei.multisimsdk.multidevicemanager.model;

import android.content.Context;
import android.os.Message;
import android.util.Base64;
import com.huawei.multisimsdk.multidevicemanager.common.AuthParam;
import defpackage.lnc;
import defpackage.lop;
import defpackage.loq;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class BaseAuthenAbs {
    private static final String TAG = "BaseAuthenAbs";
    public AuthParam mAuthParam;
    public Context mContext;
    public Message mMessage;

    public abstract void startAuthLogin();

    public BaseAuthenAbs(Context context, AuthParam authParam, Message message) {
        this.mContext = context;
        this.mAuthParam = authParam;
        this.mMessage = message;
    }

    protected String getAuthenParam(Context context, AuthParam authParam) {
        int c = lop.c();
        try {
            loq.c(TAG, "Build BaseAuthenAbs JsonObj start");
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ReqSN", c);
            jSONObject.put("ReqName", "DevAuth");
            jSONObject.put("AuthType", "GBA");
            if (context != null && authParam != null) {
                String d = lnc.d(context, authParam.getSlotId());
                jSONObject.put("Identity", Base64.encodeToString(String.format(Locale.ROOT, "0%1$s@nai.epc.mnc%2$s.mcc%3$s.3gppnetwork.org", d, lop.b(d), lop.a(d)).getBytes(), 0).trim());
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("IMEI", lnc.a(context, authParam.getSlotId()));
                jSONObject.put("DeviceID", jSONObject2);
            }
            jSONArray.put(jSONObject);
            if (loq.b.booleanValue()) {
                loq.c(TAG, "Build BaseAuthenAbs JsonObj result:" + jSONArray.toString());
            }
            return jSONArray.toString();
        } catch (JSONException unused) {
            loq.b(TAG, "Build BaseAuthenAbs JsonObj occured JSONException");
            return null;
        }
    }
}
