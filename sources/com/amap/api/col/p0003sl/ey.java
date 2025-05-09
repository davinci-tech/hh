package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.services.busline.BusLineQuery;
import com.amap.api.services.busline.BusLineResult;
import com.amap.api.services.busline.BusStationQuery;
import com.amap.api.services.busline.BusStationResult;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.SuggestionCity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public final class ey<T> extends ew<T, Object> {
    private int k;
    private List<String> l;
    private List<SuggestionCity> m;

    public ey(Context context, T t) {
        super(context, t);
        this.k = 0;
        this.l = new ArrayList();
        this.m = new ArrayList();
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        String str;
        if (!(this.b instanceof BusLineQuery)) {
            str = "stopname";
        } else if (((BusLineQuery) this.b).getCategory() == BusLineQuery.SearchType.BY_LINE_ID) {
            str = "lineid";
        } else {
            str = ((BusLineQuery) this.b).getCategory() == BusLineQuery.SearchType.BY_LINE_NAME ? "linename" : "";
        }
        return fc.a() + "/bus/" + str + "?";
    }

    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final Object a(String str) throws AMapException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONObject("suggestion");
            if (optJSONObject != null) {
                this.m = fl.a(optJSONObject);
                this.l = fl.b(optJSONObject);
            }
            this.k = jSONObject.optInt("count");
            if (this.b instanceof BusLineQuery) {
                return BusLineResult.createPagedResult((BusLineQuery) this.b, this.k, this.m, this.l, fl.f(jSONObject));
            }
            return BusStationResult.createPagedResult((BusStationQuery) this.b, this.k, this.m, this.l, fl.e(jSONObject));
        } catch (Exception e) {
            fd.a(e, "BusSearchServerHandler", "paseJSON");
            return null;
        }
    }

    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        StringBuilder sb = new StringBuilder("output=json");
        if (this.b instanceof BusLineQuery) {
            BusLineQuery busLineQuery = (BusLineQuery) this.b;
            if (!TextUtils.isEmpty(busLineQuery.getExtensions())) {
                sb.append("&extensions=");
                sb.append(busLineQuery.getExtensions());
            } else {
                sb.append("&extensions=base");
            }
            if (busLineQuery.getCategory() == BusLineQuery.SearchType.BY_LINE_ID) {
                sb.append("&id=");
                sb.append(b(((BusLineQuery) this.b).getQueryString()));
            } else {
                String city = busLineQuery.getCity();
                if (!fl.g(city)) {
                    String b = b(city);
                    sb.append("&city=");
                    sb.append(b);
                }
                sb.append("&keywords=" + b(busLineQuery.getQueryString()));
                sb.append("&offset=" + busLineQuery.getPageSize());
                sb.append("&page=" + busLineQuery.getPageNumber());
            }
        } else {
            BusStationQuery busStationQuery = (BusStationQuery) this.b;
            String city2 = busStationQuery.getCity();
            if (!fl.g(city2)) {
                String b2 = b(city2);
                sb.append("&city=");
                sb.append(b2);
            }
            sb.append("&keywords=" + b(busStationQuery.getQueryString()));
            sb.append("&offset=" + busStationQuery.getPageSize());
            sb.append("&page=" + busStationQuery.getPageNumber());
        }
        sb.append("&key=" + hn.f(this.i));
        return sb.toString();
    }
}
