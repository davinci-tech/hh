package com.huawei.hms.mlsdk.asr.engine.cloud.vo;

import com.huawei.hiai.awareness.client.AwarenessRequest;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.watchface.videoedit.gles.Constant;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class e implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private String f5080a;
    private String b;
    private JSONObject c;

    public String a() {
        return this.f5080a;
    }

    public JSONObject b() {
        return this.c;
    }

    public String c() {
        return this.b;
    }

    public static e a(String str) throws JSONException {
        e eVar = new e();
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.has(AwarenessRequest.Field.COMMAND)) {
            eVar.f5080a = jSONObject.getString(AwarenessRequest.Field.COMMAND);
        }
        if (jSONObject.has("retCode")) {
            eVar.b = jSONObject.getString("retCode");
        }
        if (jSONObject.has("retMsg")) {
            jSONObject.getString("retMsg");
        }
        if (JsUtil.ServiceType.DATA.equalsIgnoreCase(eVar.f5080a) || CommonConstant.RETKEY.STATUS.equalsIgnoreCase(eVar.f5080a)) {
            eVar.c = jSONObject.getJSONObject("result");
        }
        return eVar;
    }

    public static c a(JSONObject jSONObject) throws JSONException {
        c cVar = new c();
        if (jSONObject != null) {
            if (jSONObject.has(Constant.TEXT)) {
                cVar.a(jSONObject.getString(Constant.TEXT));
            }
            if (jSONObject.has(JsUtil.SCORE)) {
                jSONObject.getDouble(JsUtil.SCORE);
            }
            if (jSONObject.has("endTimeOffset")) {
                jSONObject.getInt("endTimeOffset");
            }
            if (jSONObject.has("final")) {
                cVar.a(jSONObject.getBoolean("final"));
            }
            if (jSONObject.has("isPartialFinal")) {
                cVar.b(jSONObject.getBoolean("isPartialFinal"));
            }
            if (jSONObject.has("words")) {
                cVar.b(a(jSONObject.getJSONArray("words")));
            }
            if (jSONObject.has("sentences")) {
                cVar.a(a(jSONObject.getJSONArray("sentences")));
            }
        }
        return cVar;
    }

    private static ArrayList<RttSegment> a(JSONArray jSONArray) throws JSONException {
        ArrayList<RttSegment> arrayList = new ArrayList<>();
        if (jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String str = "";
                String string = jSONObject.has("startTime") ? jSONObject.getString("startTime") : "";
                String string2 = jSONObject.has("endTime") ? jSONObject.getString("endTime") : "";
                if (jSONObject.has(Constant.TEXT)) {
                    str = jSONObject.getString(Constant.TEXT);
                }
                arrayList.add(new RttSegment(string, string2, str));
            }
        }
        return arrayList;
    }
}
