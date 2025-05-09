package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.RouteSearch;

/* loaded from: classes8.dex */
public final class ex extends ew<RouteSearch.BusRouteQuery, BusRouteResult> {
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final /* synthetic */ Object a(String str) throws AMapException {
        return c(str);
    }

    public ex(Context context, RouteSearch.BusRouteQuery busRouteQuery) {
        super(context, busRouteQuery);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        StringBuffer stringBuffer = new StringBuffer("key=");
        stringBuffer.append(hn.f(this.i));
        stringBuffer.append("&origin=").append(fd.a(((RouteSearch.BusRouteQuery) this.b).getFromAndTo().getFrom()));
        stringBuffer.append("&destination=").append(fd.a(((RouteSearch.BusRouteQuery) this.b).getFromAndTo().getTo()));
        String city = ((RouteSearch.BusRouteQuery) this.b).getCity();
        if (!fl.g(city)) {
            city = b(city);
            stringBuffer.append("&city=").append(city);
        }
        if (!fl.g(((RouteSearch.BusRouteQuery) this.b).getCity())) {
            stringBuffer.append("&cityd=").append(b(city));
        }
        StringBuffer append = stringBuffer.append("&strategy=");
        StringBuilder sb = new StringBuilder();
        sb.append(((RouteSearch.BusRouteQuery) this.b).getMode());
        append.append(sb.toString());
        stringBuffer.append("&nightflag=").append(((RouteSearch.BusRouteQuery) this.b).getNightFlag());
        if (!TextUtils.isEmpty(((RouteSearch.BusRouteQuery) this.b).getExtensions())) {
            stringBuffer.append("&extensions=").append(((RouteSearch.BusRouteQuery) this.b).getExtensions());
        } else {
            stringBuffer.append("&extensions=base");
        }
        stringBuffer.append("&output=json");
        return stringBuffer.toString();
    }

    private static BusRouteResult c(String str) throws AMapException {
        return fl.a(str);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return fc.a() + "/direction/transit/integrated?";
    }
}
