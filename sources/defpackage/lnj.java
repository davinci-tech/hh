package defpackage;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class lnj {
    private static final String d = "MultiSIMServiceInfo";

    /* renamed from: a, reason: collision with root package name */
    ArrayList<lnm> f14779a = new ArrayList<>();
    lnn b;

    public void d(String str) {
        JSONObject jSONObject;
        try {
            if (str == null) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(str);
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("PairedDeviceList");
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    lnm lnmVar = new lnm();
                    lnmVar.c(optJSONArray.get(i).toString());
                    this.f14779a.add(lnmVar);
                }
            }
            lnn lnnVar = new lnn();
            JSONObject optJSONObject = jSONObject.optJSONObject("PrimaryDevice");
            if (optJSONObject != null) {
                lnnVar.b(optJSONObject.toString());
            }
            this.b = lnnVar;
            if (loq.b.booleanValue()) {
                loq.c(d, "PrimaryDevice to json string = " + jSONObject.toString());
            }
        } catch (JSONException unused) {
            loq.c(d, "Parse response information occured JSONException");
        }
    }

    public ArrayList<lnm> a() {
        return this.f14779a;
    }

    public lnn d() {
        return this.b;
    }
}
