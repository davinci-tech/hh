package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.fz;
import com.amap.api.col.p0003sl.gb;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.ServiceSettings;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.huawei.openalliance.ad.constant.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class gd extends ew<RegeocodeQuery, RegeocodeAddress> {
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final /* synthetic */ Object a(String str) throws AMapException {
        return c(str);
    }

    public gd(Context context, RegeocodeQuery regeocodeQuery) {
        super(context, regeocodeQuery);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return fc.a() + "/geocode/regeo?";
    }

    private static RegeocodeAddress c(String str) throws AMapException {
        JSONObject optJSONObject;
        RegeocodeAddress regeocodeAddress = new RegeocodeAddress();
        try {
            optJSONObject = new JSONObject(str).optJSONObject("regeocode");
        } catch (JSONException e) {
            fd.a(e, "ReverseGeocodingHandler", "paseJSON");
        }
        if (optJSONObject == null) {
            return regeocodeAddress;
        }
        regeocodeAddress.setFormatAddress(fl.a(optJSONObject, "formatted_address"));
        JSONObject optJSONObject2 = optJSONObject.optJSONObject("addressComponent");
        if (optJSONObject2 != null) {
            fl.a(optJSONObject2, regeocodeAddress);
        }
        regeocodeAddress.setPois(fl.c(optJSONObject));
        JSONArray optJSONArray = optJSONObject.optJSONArray("roads");
        if (optJSONArray != null) {
            fl.b(optJSONArray, regeocodeAddress);
        }
        JSONArray optJSONArray2 = optJSONObject.optJSONArray("roadinters");
        if (optJSONArray2 != null) {
            fl.a(optJSONArray2, regeocodeAddress);
        }
        JSONArray optJSONArray3 = optJSONObject.optJSONArray("aois");
        if (optJSONArray3 != null) {
            fl.c(optJSONArray3, regeocodeAddress);
        }
        return regeocodeAddress;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private String a(boolean z) {
        StringBuilder sb = new StringBuilder("output=json&location=");
        if (z) {
            sb.append(fd.a(((RegeocodeQuery) this.b).getPoint().getLongitude()));
            sb.append(",");
            sb.append(fd.a(((RegeocodeQuery) this.b).getPoint().getLatitude()));
        }
        if (!TextUtils.isEmpty(((RegeocodeQuery) this.b).getPoiType())) {
            sb.append("&poitype=");
            sb.append(((RegeocodeQuery) this.b).getPoiType());
        }
        if (!TextUtils.isEmpty(((RegeocodeQuery) this.b).getMode())) {
            sb.append("&mode=");
            sb.append(((RegeocodeQuery) this.b).getMode());
        }
        if (!TextUtils.isEmpty(((RegeocodeQuery) this.b).getExtensions())) {
            sb.append("&extensions=");
            sb.append(((RegeocodeQuery) this.b).getExtensions());
        } else {
            sb.append("&extensions=base");
        }
        sb.append("&radius=");
        sb.append((int) ((RegeocodeQuery) this.b).getRadius());
        sb.append("&coordsys=");
        sb.append(((RegeocodeQuery) this.b).getLatLonType());
        sb.append("&key=");
        sb.append(hn.f(this.i));
        return sb.toString();
    }

    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        return a(true);
    }

    private static gb f() {
        ga a2 = fz.a().a("regeo");
        if (a2 == null) {
            return null;
        }
        return (gb) a2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ev
    protected final fz.b e() {
        gb f = f();
        double a2 = f != null ? f.a() : 0.0d;
        fz.b bVar = new fz.b();
        bVar.f1059a = getURL() + a(false) + Constants.LANGUAGE_ASSIGN_STR + ServiceSettings.getInstance().getLanguage();
        if (this.b != 0 && ((RegeocodeQuery) this.b).getPoint() != null) {
            bVar.b = new gb.a(((RegeocodeQuery) this.b).getPoint().getLatitude(), ((RegeocodeQuery) this.b).getPoint().getLongitude(), a2);
        }
        return bVar;
    }
}
