package defpackage;

import com.huawei.ads.fund.util.JsonUtil;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class uv {
    private static vq c(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("videoInfo");
        if (optJSONObject == null) {
            return null;
        }
        vq vqVar = new vq();
        double optDouble = optJSONObject.optDouble("videoRatio");
        vqVar.e(1000);
        vqVar.b((int) (optDouble * 1000.0d));
        return vqVar;
    }

    public static vq a(JSONObject jSONObject) {
        JSONObject createJsonObj = JsonUtil.createJsonObj(jSONObject.optString("metaData"));
        if (createJsonObj == null) {
            return null;
        }
        return createJsonObj.has("videoInfo") ? c(createJsonObj) : d(createJsonObj);
    }

    private static vq d(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("imageInfo");
        if (optJSONArray == null) {
            return null;
        }
        vq vqVar = new vq();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                vqVar.b(optJSONObject.optInt("width"));
                vqVar.e(optJSONObject.optInt("height"));
                vqVar.e(optJSONObject.optString("url"));
            }
        }
        return vqVar;
    }
}
