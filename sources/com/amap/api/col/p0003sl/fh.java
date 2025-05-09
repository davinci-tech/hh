package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RouteSearch;

/* loaded from: classes8.dex */
public final class fh extends ew<RouteSearch.DriveRouteQuery, DriveRouteResult> {
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final /* synthetic */ Object a(String str) throws AMapException {
        return c(str);
    }

    public fh(Context context, RouteSearch.DriveRouteQuery driveRouteQuery) {
        super(context, driveRouteQuery);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        StringBuffer stringBuffer = new StringBuffer("key=");
        stringBuffer.append(hn.f(this.i));
        if (((RouteSearch.DriveRouteQuery) this.b).getFromAndTo() != null) {
            stringBuffer.append("&origin=").append(fd.a(((RouteSearch.DriveRouteQuery) this.b).getFromAndTo().getFrom()));
            if (!fl.g(((RouteSearch.DriveRouteQuery) this.b).getFromAndTo().getStartPoiID())) {
                stringBuffer.append("&originid=").append(((RouteSearch.DriveRouteQuery) this.b).getFromAndTo().getStartPoiID());
            }
            stringBuffer.append("&destination=").append(fd.a(((RouteSearch.DriveRouteQuery) this.b).getFromAndTo().getTo()));
            if (!fl.g(((RouteSearch.DriveRouteQuery) this.b).getFromAndTo().getDestinationPoiID())) {
                stringBuffer.append("&destinationid=").append(((RouteSearch.DriveRouteQuery) this.b).getFromAndTo().getDestinationPoiID());
            }
            if (!fl.g(((RouteSearch.DriveRouteQuery) this.b).getFromAndTo().getOriginType())) {
                stringBuffer.append("&origintype=").append(((RouteSearch.DriveRouteQuery) this.b).getFromAndTo().getOriginType());
            }
            if (!fl.g(((RouteSearch.DriveRouteQuery) this.b).getFromAndTo().getDestinationType())) {
                stringBuffer.append("&destinationtype=").append(((RouteSearch.DriveRouteQuery) this.b).getFromAndTo().getDestinationType());
            }
            if (!fl.g(((RouteSearch.DriveRouteQuery) this.b).getFromAndTo().getPlateProvince())) {
                stringBuffer.append("&province=").append(((RouteSearch.DriveRouteQuery) this.b).getFromAndTo().getPlateProvince());
            }
            if (!fl.g(((RouteSearch.DriveRouteQuery) this.b).getFromAndTo().getPlateNumber())) {
                stringBuffer.append("&number=").append(((RouteSearch.DriveRouteQuery) this.b).getFromAndTo().getPlateNumber());
            }
        }
        StringBuffer append = stringBuffer.append("&strategy=");
        StringBuilder sb = new StringBuilder();
        sb.append(((RouteSearch.DriveRouteQuery) this.b).getMode());
        append.append(sb.toString());
        if (!TextUtils.isEmpty(((RouteSearch.DriveRouteQuery) this.b).getExtensions())) {
            stringBuffer.append("&extensions=").append(((RouteSearch.DriveRouteQuery) this.b).getExtensions());
        } else {
            stringBuffer.append("&extensions=base");
        }
        stringBuffer.append("&ferry=").append(!((RouteSearch.DriveRouteQuery) this.b).isUseFerry() ? 1 : 0);
        StringBuffer append2 = stringBuffer.append("&cartype=");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(((RouteSearch.DriveRouteQuery) this.b).getCarType());
        append2.append(sb2.toString());
        if (((RouteSearch.DriveRouteQuery) this.b).hasPassPoint()) {
            stringBuffer.append("&waypoints=").append(((RouteSearch.DriveRouteQuery) this.b).getPassedPointStr());
        }
        if (((RouteSearch.DriveRouteQuery) this.b).hasAvoidpolygons()) {
            stringBuffer.append("&avoidpolygons=").append(((RouteSearch.DriveRouteQuery) this.b).getAvoidpolygonsStr());
        }
        if (((RouteSearch.DriveRouteQuery) this.b).hasAvoidRoad()) {
            stringBuffer.append("&avoidroad=").append(b(((RouteSearch.DriveRouteQuery) this.b).getAvoidRoad()));
        }
        stringBuffer.append("&output=json&geometry=false");
        if (((RouteSearch.DriveRouteQuery) this.b).getExclude() != null) {
            stringBuffer.append("&exclude=").append(((RouteSearch.DriveRouteQuery) this.b).getExclude());
        }
        return stringBuffer.toString();
    }

    private static DriveRouteResult c(String str) throws AMapException {
        return fl.b(str);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return fc.a() + "/direction/driving?";
    }
}
