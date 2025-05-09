package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.col.p0003sl.fz;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.ServiceSettings;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class fj extends ew<GeocodeQuery, ArrayList<GeocodeAddress>> {
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final /* synthetic */ Object a(String str) throws AMapException {
        return c(str);
    }

    public fj(Context context, GeocodeQuery geocodeQuery) {
        super(context, geocodeQuery);
    }

    private static ArrayList<GeocodeAddress> c(String str) throws AMapException {
        ArrayList<GeocodeAddress> arrayList = new ArrayList<>();
        try {
            JSONObject jSONObject = new JSONObject(str);
            return (jSONObject.has("count") && jSONObject.getInt("count") > 0) ? fl.g(jSONObject) : arrayList;
        } catch (JSONException e) {
            fd.a(e, "GeocodingHandler", "paseJSONJSONException");
            return arrayList;
        } catch (Exception e2) {
            fd.a(e2, "GeocodingHandler", "paseJSONException");
            return arrayList;
        }
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return fc.a() + "/geocode/geo?";
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        StringBuffer stringBuffer = new StringBuffer("output=json&address=");
        stringBuffer.append(b(((GeocodeQuery) this.b).getLocationName()));
        String city = ((GeocodeQuery) this.b).getCity();
        if (!fl.g(city)) {
            stringBuffer.append("&city=").append(b(city));
        }
        if (!fl.g(((GeocodeQuery) this.b).getCountry())) {
            stringBuffer.append("&country=").append(b(((GeocodeQuery) this.b).getCountry()));
        }
        stringBuffer.append("&key=" + hn.f(this.i));
        return stringBuffer.toString();
    }

    @Override // com.amap.api.col.p0003sl.ev
    protected final fz.b e() {
        fz.b bVar = new fz.b();
        bVar.f1059a = getURL() + c() + Constants.LANGUAGE_ASSIGN_STR + ServiceSettings.getInstance().getLanguage();
        return bVar;
    }
}
