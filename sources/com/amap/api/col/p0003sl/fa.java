package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.services.cloud.CloudItemDetail;
import com.amap.api.services.core.AMapException;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.util.Hashtable;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public final class fa extends ez<fw, CloudItemDetail> {
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        return null;
    }

    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    public final /* synthetic */ Object a(String str) throws AMapException {
        return c(str);
    }

    public fa(Context context, fw fwVar) {
        super(context, fwVar);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return fc.e() + "/datasearch/id";
    }

    private static CloudItemDetail c(String str) throws AMapException {
        if (str != null && !str.equals("")) {
            try {
                return d(new JSONObject(str));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    private static CloudItemDetail d(JSONObject jSONObject) throws JSONException {
        JSONArray a2 = a(jSONObject);
        if (a2 == null || a2.length() <= 0) {
            return null;
        }
        JSONObject jSONObject2 = a2.getJSONObject(0);
        CloudItemDetail c = c(jSONObject2);
        a(c, jSONObject2);
        return c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev, com.amap.api.col.p0003sl.ka
    public final Map<String, String> getParams() {
        Hashtable hashtable = new Hashtable(16);
        hashtable.put(MedalConstants.EVENT_KEY, hn.f(this.i));
        hashtable.put("layerId", ((fw) this.b).f1054a);
        hashtable.put("output", "json");
        hashtable.put("id", ((fw) this.b).b);
        String a2 = hq.a();
        String a3 = hq.a(this.i, a2, ia.b(hashtable));
        hashtable.put("ts", a2);
        hashtable.put("scode", a3);
        return hashtable;
    }
}
