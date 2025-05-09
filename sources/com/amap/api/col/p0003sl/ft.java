package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.col.p0003sl.fz;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.ServiceSettings;
import com.amap.api.services.poisearch.PoiSearch;
import com.huawei.health.device.model.RecordAction;
import com.huawei.openalliance.ad.constant.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public final class ft extends fs<String, PoiItem> {
    private PoiSearch.Query k;

    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    public final /* synthetic */ Object a(String str) throws AMapException {
        return d(str);
    }

    public ft(Context context, String str, PoiSearch.Query query) {
        super(context, str);
        this.k = query;
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return fc.a() + "/place/detail?";
    }

    private static PoiItem d(String str) throws AMapException {
        try {
            return a(new JSONObject(str));
        } catch (JSONException e) {
            fd.a(e, "PoiSearchIdHandler", "paseJSONJSONException");
            return null;
        } catch (Exception e2) {
            fd.a(e2, "PoiSearchIdHandler", "paseJSONException");
            return null;
        }
    }

    private static PoiItem a(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject;
        JSONArray optJSONArray = jSONObject.optJSONArray("pois");
        if (optJSONArray == null || optJSONArray.length() <= 0 || (optJSONObject = optJSONArray.optJSONObject(0)) == null) {
            return null;
        }
        return fl.d(optJSONObject);
    }

    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        return f();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private String f() {
        StringBuilder sb = new StringBuilder(RecordAction.ACT_ID_TAG);
        sb.append((String) this.b);
        sb.append("&output=json");
        PoiSearch.Query query = this.k;
        if (query != null && !c(query.getExtensions())) {
            sb.append("&extensions=");
            sb.append(this.k.getExtensions());
        } else {
            sb.append("&extensions=base");
        }
        sb.append("&children=1");
        sb.append("&key=" + hn.f(this.i));
        return sb.toString();
    }

    @Override // com.amap.api.col.p0003sl.ev
    protected final fz.b e() {
        fz.b bVar = new fz.b();
        bVar.f1059a = getURL() + c() + Constants.LANGUAGE_ASSIGN_STR + ServiceSettings.getInstance().getLanguage();
        return bVar;
    }
}
