package defpackage;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HwEncryptUtil;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kxo implements Serializable {
    private static final long serialVersionUID = -5057395571324649699L;

    /* renamed from: a, reason: collision with root package name */
    private String f14686a;
    private String b;
    private int e;

    public kxo(Context context, String str) throws JSONException, NumberFormatException {
        this.e = 0;
        this.f14686a = "";
        this.b = "";
        String b = HwEncryptUtil.c(context).b(str);
        LogUtil.c("RuleAttrInfo", "parseRuleAttr ruleAttr json : ", str, " decrypt ruleAttr json : ", b);
        if (b != null) {
            JSONObject jSONObject = new JSONObject(b);
            if (jSONObject.has("mincode")) {
                this.e = Integer.parseInt(jSONObject.getString("mincode"));
            }
            this.f14686a = jSONObject.optString("forcedupdate");
            this.b = jSONObject.optString("appforcedupdate");
        }
    }

    public int e() {
        return this.e;
    }

    public String d() {
        return this.f14686a;
    }

    public String a() {
        return this.b;
    }
}
