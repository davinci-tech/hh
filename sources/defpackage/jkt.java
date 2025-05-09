package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jkt {

    /* renamed from: a, reason: collision with root package name */
    private int f13920a = 0;
    private int e = 20;
    private int d = 2;
    private int b = 0;

    public String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("lowPowerNumber", this.f13920a);
            jSONObject.put("intervalTime", this.e);
            jSONObject.put("protocolWaitingTime", this.d);
            jSONObject.put("errCode", this.b);
        } catch (JSONException unused) {
            LogUtil.h("AccessConfigInfo", "getConfigJson JSONException");
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.toString();
    }

    public void e(int i) {
        this.f13920a = i;
    }

    public void a(int i) {
        this.e = i;
    }

    public void c(int i) {
        this.d = i;
    }

    public void d(int i) {
        this.b = i;
    }

    public String toString() {
        return "AccessConfigInfo{lowPowerNumber=" + this.f13920a + ", intervalTime=" + this.e + ", protocolWaitingTime=" + this.d + ", errorCode=" + this.b + "}";
    }
}
