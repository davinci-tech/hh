package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.col.p0003sl.ho;
import com.amap.api.maps.AMapException;
import java.util.Hashtable;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class ay extends bu<String, ax> {
    @Override // com.amap.api.col.p0003sl.bu
    protected final /* synthetic */ ax a(JSONObject jSONObject) throws AMapException {
        return b(jSONObject);
    }

    public ay(Context context, String str) {
        super(context, str);
    }

    @Override // com.amap.api.col.p0003sl.bu
    protected final JSONObject a(ho.b bVar) {
        if (bVar == null || bVar.f == null) {
            return null;
        }
        return bVar.f.optJSONObject("016");
    }

    @Override // com.amap.api.col.p0003sl.bu
    protected final Map<String, String> b() {
        Hashtable hashtable = new Hashtable(16);
        hashtable.put("mapver", this.f932a);
        return hashtable;
    }

    private static ax b(JSONObject jSONObject) throws AMapException {
        ax axVar = new ax();
        try {
            String optString = jSONObject.optString("update", "");
            if (optString.equals("0")) {
                axVar.a(false);
            } else if (optString.equals("1")) {
                axVar.a(true);
            }
            axVar.a(jSONObject.optString("version", ""));
        } catch (Throwable th) {
            iv.c(th, "OfflineInitHandlerAbstract", "loadData parseJson");
        }
        return axVar;
    }

    @Override // com.amap.api.col.p0003sl.bu
    protected final String a() {
        return "016";
    }
}
