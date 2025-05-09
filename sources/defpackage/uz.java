package defpackage;

import com.huawei.openalliance.ad.constant.AssetAlias;
import com.huawei.openalliance.ad.db.bean.ContentTemplateRecord;
import com.huawei.openplatform.abl.log.HiAdLog;
import com.huawei.watchface.videoedit.gles.Constant;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class uz {
    public static vq e(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray(ContentTemplateRecord.ASSETS);
        if (optJSONArray == null) {
            return null;
        }
        vq vqVar = new vq();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            String optString = optJSONObject.optString("alias");
            if (optString.startsWith(AssetAlias.VIDEO)) {
                d(optJSONObject, vqVar);
            } else if (optString.startsWith(AssetAlias.IMAGE)) {
                e(optJSONObject, vqVar);
            }
        }
        return vqVar;
    }

    private static void d(JSONObject jSONObject, vq vqVar) {
        JSONObject optJSONObject = jSONObject.optJSONObject("video");
        if (optJSONObject == null) {
            return;
        }
        vqVar.b(optJSONObject.optInt("W"));
        vqVar.e(optJSONObject.optInt("H"));
    }

    public static String a(JSONObject jSONObject) {
        JSONArray optJSONArray;
        JSONObject jSONObject2;
        if (jSONObject != null && (optJSONArray = jSONObject.optJSONArray(ContentTemplateRecord.ASSETS)) != null && optJSONArray.length() != 0) {
            String str = "";
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    jSONObject2 = (JSONObject) optJSONArray.get(i);
                } catch (JSONException unused) {
                    HiAdLog.e("AdContentParserV3", "get title error");
                    str = "";
                }
                if ("ad_title".equals(jSONObject2.optString("alias"))) {
                    JSONObject optJSONObject = jSONObject2.optJSONObject("title");
                    if (optJSONObject != null) {
                        str = optJSONObject.optString(Constant.TEXT);
                        break;
                    }
                    return str;
                }
                continue;
            }
            return str;
        }
        return "";
    }

    private static void e(JSONObject jSONObject, vq vqVar) {
        JSONObject optJSONObject = jSONObject.optJSONObject("img");
        if (optJSONObject == null) {
            return;
        }
        vqVar.b(optJSONObject.optInt("W"));
        vqVar.e(optJSONObject.optInt("H"));
        vqVar.e(optJSONObject.optString("url"));
    }

    public static String d(JSONObject jSONObject) {
        JSONArray optJSONArray;
        JSONObject jSONObject2;
        if (jSONObject != null && (optJSONArray = jSONObject.optJSONArray(ContentTemplateRecord.ASSETS)) != null && optJSONArray.length() != 0) {
            String str = "";
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    jSONObject2 = (JSONObject) optJSONArray.get(i);
                } catch (JSONException unused) {
                    HiAdLog.e("AdContentParserV3", "get adBrandInfo error");
                    str = "";
                }
                if (AssetAlias.AD_LABEL.equals(jSONObject2.optString("alias"))) {
                    JSONObject optJSONObject = jSONObject2.optJSONObject("data");
                    if (optJSONObject != null) {
                        str = optJSONObject.optString("value");
                        break;
                    }
                    return str;
                }
                continue;
            }
            return str;
        }
        return "";
    }
}
