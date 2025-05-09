package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.route.DriveRouteResultV2;
import com.amap.api.services.route.RouteSearchV2;

/* loaded from: classes8.dex */
public final class fi extends ew<RouteSearchV2.DriveRouteQuery, DriveRouteResultV2> {
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final /* synthetic */ Object a(String str) throws AMapException {
        return c(str);
    }

    public fi(Context context, RouteSearchV2.DriveRouteQuery driveRouteQuery) {
        super(context, driveRouteQuery);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(hn.f(this.i));
        if (((RouteSearchV2.DriveRouteQuery) this.b).getFromAndTo() != null) {
            stringBuffer.append("&origin=").append(fd.a(((RouteSearchV2.DriveRouteQuery) this.b).getFromAndTo().getFrom()));
            if (!fl.g(((RouteSearchV2.DriveRouteQuery) this.b).getFromAndTo().getStartPoiID())) {
                stringBuffer.append("&origin_id=").append(((RouteSearchV2.DriveRouteQuery) this.b).getFromAndTo().getStartPoiID());
            }
            stringBuffer.append("&destination=").append(fd.a(((RouteSearchV2.DriveRouteQuery) this.b).getFromAndTo().getTo()));
            if (!fl.g(((RouteSearchV2.DriveRouteQuery) this.b).getFromAndTo().getDestinationPoiID())) {
                stringBuffer.append("&destination_id=").append(((RouteSearchV2.DriveRouteQuery) this.b).getFromAndTo().getDestinationPoiID());
            }
            if (!fl.g(((RouteSearchV2.DriveRouteQuery) this.b).getFromAndTo().getOriginType())) {
                stringBuffer.append("&origin_type=").append(((RouteSearchV2.DriveRouteQuery) this.b).getFromAndTo().getOriginType());
            }
            if (!fl.g(((RouteSearchV2.DriveRouteQuery) this.b).getFromAndTo().getPlateNumber())) {
                stringBuffer.append("&plate=").append(((RouteSearchV2.DriveRouteQuery) this.b).getFromAndTo().getPlateNumber());
            }
        }
        StringBuffer append = stringBuffer.append("&strategy=");
        StringBuilder sb = new StringBuilder();
        sb.append(((RouteSearchV2.DriveRouteQuery) this.b).getMode());
        append.append(sb.toString());
        int showFields = ((RouteSearchV2.DriveRouteQuery) this.b).getShowFields();
        stringBuffer.append("&show_fields=");
        if ((showFields & 1) != 0) {
            stringBuffer.append("cost,");
        }
        if ((showFields & 2) != 0) {
            stringBuffer.append("tmcs,");
        }
        if ((showFields & 4) != 0) {
            stringBuffer.append("navi,");
        }
        if ((showFields & 8) != 0) {
            stringBuffer.append("cities,");
        }
        if ((showFields & 16) != 0) {
            stringBuffer.append("polyline,");
        }
        if ((showFields & 32) != 0) {
            stringBuffer.append("elec_consume_info,");
        }
        if ((showFields & 64) != 0) {
            stringBuffer.append("charge_station_info,");
        }
        stringBuffer.replace(stringBuffer.length() - 1, stringBuffer.length(), "");
        RouteSearchV2.NewEnergy newEnergy = ((RouteSearchV2.DriveRouteQuery) this.b).getNewEnergy();
        if (newEnergy != null) {
            stringBuffer.append(newEnergy.buildParam());
            stringBuffer.append("&force_new_version=true");
        }
        stringBuffer.append("&ferry=").append(!((RouteSearchV2.DriveRouteQuery) this.b).isUseFerry() ? 1 : 0);
        StringBuffer append2 = stringBuffer.append("&cartype=");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(((RouteSearchV2.DriveRouteQuery) this.b).getCarType());
        append2.append(sb2.toString());
        if (((RouteSearchV2.DriveRouteQuery) this.b).hasPassPoint()) {
            stringBuffer.append("&waypoints=").append(((RouteSearchV2.DriveRouteQuery) this.b).getPassedPointStr());
        }
        if (((RouteSearchV2.DriveRouteQuery) this.b).hasAvoidpolygons()) {
            stringBuffer.append("&avoidpolygons=").append(((RouteSearchV2.DriveRouteQuery) this.b).getAvoidpolygonsStr());
        }
        if (((RouteSearchV2.DriveRouteQuery) this.b).hasAvoidRoad()) {
            stringBuffer.append("&avoidroad=").append(b(((RouteSearchV2.DriveRouteQuery) this.b).getAvoidRoad()));
        }
        stringBuffer.append("&output=json");
        stringBuffer.append("&geometry=false");
        if (((RouteSearchV2.DriveRouteQuery) this.b).getExclude() != null) {
            stringBuffer.append("&exclude=").append(((RouteSearchV2.DriveRouteQuery) this.b).getExclude());
        }
        return stringBuffer.toString();
    }

    private static DriveRouteResultV2 c(String str) throws AMapException {
        return fl.c(str);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return fc.c() + "/direction/driving?";
    }
}
