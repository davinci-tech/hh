package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.TruckRouteRestult;

/* loaded from: classes8.dex */
public final class gk extends ew<RouteSearch.TruckRouteQuery, TruckRouteRestult> {
    private final String k;
    private final String l;
    private final String m;

    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final /* synthetic */ Object a(String str) throws AMapException {
        return c(str);
    }

    public gk(Context context, RouteSearch.TruckRouteQuery truckRouteQuery) {
        super(context, truckRouteQuery);
        this.k = "/direction/truck?";
        this.l = "|";
        this.m = ",";
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        StringBuffer stringBuffer = new StringBuffer("key=");
        stringBuffer.append(hn.f(this.i));
        if (((RouteSearch.TruckRouteQuery) this.b).getFromAndTo() != null) {
            stringBuffer.append("&origin=").append(fd.a(((RouteSearch.TruckRouteQuery) this.b).getFromAndTo().getFrom()));
            if (!fl.g(((RouteSearch.TruckRouteQuery) this.b).getFromAndTo().getStartPoiID())) {
                stringBuffer.append("&originid=").append(((RouteSearch.TruckRouteQuery) this.b).getFromAndTo().getStartPoiID());
            }
            stringBuffer.append("&destination=").append(fd.a(((RouteSearch.TruckRouteQuery) this.b).getFromAndTo().getTo()));
            if (!fl.g(((RouteSearch.TruckRouteQuery) this.b).getFromAndTo().getDestinationPoiID())) {
                stringBuffer.append("&destinationid=").append(((RouteSearch.TruckRouteQuery) this.b).getFromAndTo().getDestinationPoiID());
            }
            if (!fl.g(((RouteSearch.TruckRouteQuery) this.b).getFromAndTo().getOriginType())) {
                stringBuffer.append("&origintype=").append(((RouteSearch.TruckRouteQuery) this.b).getFromAndTo().getOriginType());
            }
            if (!fl.g(((RouteSearch.TruckRouteQuery) this.b).getFromAndTo().getDestinationType())) {
                stringBuffer.append("&destinationtype=").append(((RouteSearch.TruckRouteQuery) this.b).getFromAndTo().getDestinationType());
            }
            if (!fl.g(((RouteSearch.TruckRouteQuery) this.b).getFromAndTo().getPlateProvince())) {
                stringBuffer.append("&province=").append(((RouteSearch.TruckRouteQuery) this.b).getFromAndTo().getPlateProvince());
            }
            if (!fl.g(((RouteSearch.TruckRouteQuery) this.b).getFromAndTo().getPlateNumber())) {
                stringBuffer.append("&number=").append(((RouteSearch.TruckRouteQuery) this.b).getFromAndTo().getPlateNumber());
            }
        }
        stringBuffer.append("&strategy=").append(((RouteSearch.TruckRouteQuery) this.b).getMode());
        if (((RouteSearch.TruckRouteQuery) this.b).hasPassPoint()) {
            stringBuffer.append("&waypoints=").append(((RouteSearch.TruckRouteQuery) this.b).getPassedPointStr());
        }
        stringBuffer.append("&size=").append(((RouteSearch.TruckRouteQuery) this.b).getTruckSize());
        stringBuffer.append("&height=").append(((RouteSearch.TruckRouteQuery) this.b).getTruckHeight());
        stringBuffer.append("&width=").append(((RouteSearch.TruckRouteQuery) this.b).getTruckWidth());
        stringBuffer.append("&load=").append(((RouteSearch.TruckRouteQuery) this.b).getTruckLoad());
        stringBuffer.append("&weight=").append(((RouteSearch.TruckRouteQuery) this.b).getTruckWeight());
        stringBuffer.append("&axis=").append(((RouteSearch.TruckRouteQuery) this.b).getTruckAxis());
        if (!TextUtils.isEmpty(((RouteSearch.TruckRouteQuery) this.b).getExtensions())) {
            stringBuffer.append("&extensions=").append(((RouteSearch.TruckRouteQuery) this.b).getExtensions());
        } else {
            stringBuffer.append("&extensions=base");
        }
        stringBuffer.append("&output=json");
        return stringBuffer.toString();
    }

    private static TruckRouteRestult c(String str) throws AMapException {
        return fl.j(str);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return fc.b() + "/direction/truck?";
    }
}
