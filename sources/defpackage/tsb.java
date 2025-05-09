package defpackage;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class tsb {
    public static String c(tpy tpyVar) {
        if (tpyVar == null) {
            tov.d("P2pJsonUtil", "buildMessageExJson message is null");
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("message_enable_encrypt", tpyVar.e());
        } catch (JSONException unused) {
            tov.d("P2pJsonUtil", "buildMessageExJson JSONException");
        }
        return jSONObject.toString();
    }
}
