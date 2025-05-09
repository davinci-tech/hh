package defpackage;

import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jkz {
    private String b = "";
    private int c = 0;

    public String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TrackConstants$Opers.RESPONSE, this.b);
            jSONObject.put("errCode", this.c);
        } catch (JSONException unused) {
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.toString();
    }

    public String toString() {
        return "NfcCommandResponse{response='" + this.b + "', errCode='" + this.c + '}';
    }

    public void e(String str) {
        this.b = str;
    }

    public void c(int i) {
        this.c = i;
    }
}
