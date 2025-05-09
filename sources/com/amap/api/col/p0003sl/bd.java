package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.col.p0003sl.ho;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class bd extends bu<String, List<OfflineMapProvince>> {
    private Context d;

    public bd(Context context, String str) {
        super(context, str);
    }

    public final void a(Context context) {
        this.d = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.amap.api.col.p0003sl.bu
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public List<OfflineMapProvince> a(JSONObject jSONObject) throws AMapException {
        try {
            if (this.d != null) {
                bt.c(jSONObject.toString(), this.d);
            }
        } catch (Throwable th) {
            iv.c(th, "OfflineUpdateCityHandlerAbstract", "loadData jsonInit");
            th.printStackTrace();
        }
        try {
            Context context = this.d;
            if (context != null) {
                return bt.a(jSONObject, context);
            }
        } catch (JSONException e) {
            iv.c(e, "OfflineUpdateCityHandlerAbstract", "loadData parseJson");
            e.printStackTrace();
        }
        return null;
    }

    @Override // com.amap.api.col.p0003sl.bu
    protected final JSONObject a(ho.b bVar) {
        if (bVar == null || bVar.f == null) {
            return null;
        }
        JSONObject optJSONObject = bVar.f.optJSONObject("015");
        if (!optJSONObject.has("result")) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("result", new JSONObject().put("offlinemap_with_province_vfour", optJSONObject));
                return jSONObject;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return optJSONObject;
    }

    @Override // com.amap.api.col.p0003sl.bu
    protected final Map<String, String> b() {
        Hashtable hashtable = new Hashtable(16);
        hashtable.put("mapver", this.f932a);
        return hashtable;
    }

    @Override // com.amap.api.col.p0003sl.bu
    protected final String a() {
        return "015";
    }
}
