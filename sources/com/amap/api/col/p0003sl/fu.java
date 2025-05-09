package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.col.p0003sl.fz;
import com.amap.api.col.p0003sl.gb;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.ServiceSettings;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiResult;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public final class fu extends fs<fx, PoiResult> {
    private int k;
    private boolean l;
    private List<String> m;
    private List<SuggestionCity> n;

    public fu(Context context, fx fxVar) {
        super(context, fxVar);
        this.k = 0;
        this.l = false;
        this.m = new ArrayList();
        this.n = new ArrayList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        String str = fc.a() + "/place";
        if (((fx) this.b).b == null) {
            return str + "/text?";
        }
        if (((fx) this.b).b.getShape().equals("Bound")) {
            String str2 = str + "/around?";
            this.l = true;
            return str2;
        }
        if (!((fx) this.b).b.getShape().equals("Rectangle") && !((fx) this.b).b.getShape().equals("Polygon")) {
            return str;
        }
        return str + "/polygon?";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public PoiResult a(String str) throws AMapException {
        JSONObject jSONObject;
        ArrayList<PoiItem> arrayList = new ArrayList<>();
        if (str == null) {
            return PoiResult.createPagedResult(((fx) this.b).f1055a, ((fx) this.b).b, this.m, this.n, ((fx) this.b).f1055a.getPageSize(), this.k, arrayList);
        }
        try {
            jSONObject = new JSONObject(str);
            this.k = jSONObject.optInt("count");
            arrayList = fl.c(jSONObject);
        } catch (JSONException e) {
            fd.a(e, "PoiSearchKeywordHandler", "paseJSONJSONException");
        } catch (Exception e2) {
            fd.a(e2, "PoiSearchKeywordHandler", "paseJSONException");
        }
        if (!jSONObject.has("suggestion")) {
            return PoiResult.createPagedResult(((fx) this.b).f1055a, ((fx) this.b).b, this.m, this.n, ((fx) this.b).f1055a.getPageSize(), this.k, arrayList);
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("suggestion");
        if (optJSONObject == null) {
            return PoiResult.createPagedResult(((fx) this.b).f1055a, ((fx) this.b).b, this.m, this.n, ((fx) this.b).f1055a.getPageSize(), this.k, arrayList);
        }
        this.n = fl.a(optJSONObject);
        this.m = fl.b(optJSONObject);
        return PoiResult.createPagedResult(((fx) this.b).f1055a, ((fx) this.b).b, this.m, this.n, ((fx) this.b).f1055a.getPageSize(), this.k, arrayList);
    }

    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        return a(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private String a(boolean z) {
        List<LatLonPoint> polyGonList;
        StringBuilder sb = new StringBuilder("output=json");
        if (((fx) this.b).b != null) {
            if (((fx) this.b).b.getShape().equals("Bound")) {
                if (z) {
                    double a2 = fd.a(((fx) this.b).b.getCenter().getLongitude());
                    double a3 = fd.a(((fx) this.b).b.getCenter().getLatitude());
                    sb.append("&location=");
                    sb.append(a2 + "," + a3);
                }
                sb.append("&radius=");
                sb.append(((fx) this.b).b.getRange());
                sb.append("&sortrule=");
                sb.append(b(((fx) this.b).b.isDistanceSort()));
            } else if (((fx) this.b).b.getShape().equals("Rectangle")) {
                LatLonPoint lowerLeft = ((fx) this.b).b.getLowerLeft();
                LatLonPoint upperRight = ((fx) this.b).b.getUpperRight();
                double a4 = fd.a(lowerLeft.getLatitude());
                double a5 = fd.a(lowerLeft.getLongitude());
                double a6 = fd.a(upperRight.getLatitude());
                sb.append("&polygon=" + a5 + "," + a4 + ";" + fd.a(upperRight.getLongitude()) + "," + a6);
            } else if (((fx) this.b).b.getShape().equals("Polygon") && (polyGonList = ((fx) this.b).b.getPolyGonList()) != null && polyGonList.size() > 0) {
                sb.append("&polygon=" + fd.a(polyGonList));
            }
        }
        String city = ((fx) this.b).f1055a.getCity();
        if (!c(city)) {
            String b = b(city);
            sb.append("&city=");
            sb.append(b);
        }
        String b2 = b(((fx) this.b).f1055a.getQueryString());
        if (!c(b2)) {
            sb.append("&keywords=");
            sb.append(b2);
        }
        sb.append("&offset=");
        sb.append(((fx) this.b).f1055a.getPageSize());
        sb.append("&page=");
        sb.append(((fx) this.b).f1055a.getPageNum());
        String building = ((fx) this.b).f1055a.getBuilding();
        if (building != null && building.trim().length() > 0) {
            sb.append("&building=");
            sb.append(((fx) this.b).f1055a.getBuilding());
        }
        String b3 = b(((fx) this.b).f1055a.getCategory());
        if (!c(b3)) {
            sb.append("&types=");
            sb.append(b3);
        }
        if (!c(((fx) this.b).f1055a.getExtensions())) {
            sb.append("&extensions=");
            sb.append(((fx) this.b).f1055a.getExtensions());
        } else {
            sb.append("&extensions=base");
        }
        sb.append("&key=");
        sb.append(hn.f(this.i));
        if (((fx) this.b).f1055a.getCityLimit()) {
            sb.append("&citylimit=true");
        } else {
            sb.append("&citylimit=false");
        }
        if (((fx) this.b).f1055a.isRequireSubPois()) {
            sb.append("&children=1");
        } else {
            sb.append("&children=0");
        }
        if (this.l) {
            if (((fx) this.b).f1055a.isSpecial()) {
                sb.append("&special=1");
            } else {
                sb.append("&special=0");
            }
        }
        if (((fx) this.b).b == null && ((fx) this.b).f1055a.getLocation() != null) {
            sb.append("&sortrule=");
            sb.append(b(((fx) this.b).f1055a.isDistanceSort()));
            double a7 = fd.a(((fx) this.b).f1055a.getLocation().getLongitude());
            double a8 = fd.a(((fx) this.b).f1055a.getLocation().getLatitude());
            sb.append("&location=");
            sb.append(a7 + "," + a8);
        }
        return sb.toString();
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
        fz.b bVar = new fz.b();
        if (this.l) {
            gb f = f();
            double a2 = f != null ? f.a() : 0.0d;
            bVar.f1059a = getURL() + a(false) + Constants.LANGUAGE_ASSIGN_STR + ServiceSettings.getInstance().getLanguage();
            if (((fx) this.b).b.getShape().equals("Bound")) {
                bVar.b = new gb.a(fd.a(((fx) this.b).b.getCenter().getLatitude()), fd.a(((fx) this.b).b.getCenter().getLongitude()), a2);
            }
        } else {
            bVar.f1059a = getURL() + c() + Constants.LANGUAGE_ASSIGN_STR + ServiceSettings.getInstance().getLanguage();
        }
        return bVar;
    }

    private static String b(boolean z) {
        return z ? "distance" : "weight";
    }
}
