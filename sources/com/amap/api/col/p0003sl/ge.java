package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;

/* loaded from: classes8.dex */
public final class ge extends ew<RouteSearch.RideRouteQuery, RideRouteResult> {
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final /* synthetic */ Object a(String str) throws AMapException {
        return c(str);
    }

    public ge(Context context, RouteSearch.RideRouteQuery rideRouteQuery) {
        super(context, rideRouteQuery);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        StringBuffer stringBuffer = new StringBuffer("key=");
        stringBuffer.append(hn.f(this.i));
        stringBuffer.append("&origin=").append(fd.a(((RouteSearch.RideRouteQuery) this.b).getFromAndTo().getFrom()));
        stringBuffer.append("&destination=").append(fd.a(((RouteSearch.RideRouteQuery) this.b).getFromAndTo().getTo()));
        stringBuffer.append("&output=json&geometry=false");
        if (!TextUtils.isEmpty(((RouteSearch.RideRouteQuery) this.b).getExtensions())) {
            stringBuffer.append("&extensions=").append(((RouteSearch.RideRouteQuery) this.b).getExtensions());
        } else {
            stringBuffer.append("&extensions=base");
        }
        return stringBuffer.toString();
    }

    private static RideRouteResult c(String str) throws AMapException {
        return fl.h(str);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return fc.b() + "/direction/bicycling?";
    }
}
