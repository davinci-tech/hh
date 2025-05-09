package com.amap.api.col.p0003sl;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.route.DriveRoutePlanResult;
import com.amap.api.services.route.RouteSearch;

/* loaded from: classes8.dex */
public final class fg extends ew<RouteSearch.DrivePlanQuery, DriveRoutePlanResult> {
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final /* synthetic */ Object a(String str) throws AMapException {
        return c(str);
    }

    public fg(Context context, RouteSearch.DrivePlanQuery drivePlanQuery) {
        super(context, drivePlanQuery);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        StringBuffer stringBuffer = new StringBuffer("key=");
        stringBuffer.append(hn.f(this.i));
        if (((RouteSearch.DrivePlanQuery) this.b).getFromAndTo() != null) {
            stringBuffer.append("&origin=").append(fd.a(((RouteSearch.DrivePlanQuery) this.b).getFromAndTo().getFrom()));
            if (!fl.g(((RouteSearch.DrivePlanQuery) this.b).getFromAndTo().getStartPoiID())) {
                stringBuffer.append("&originid=").append(((RouteSearch.DrivePlanQuery) this.b).getFromAndTo().getStartPoiID());
            }
            stringBuffer.append("&destination=").append(fd.a(((RouteSearch.DrivePlanQuery) this.b).getFromAndTo().getTo()));
            if (!fl.g(((RouteSearch.DrivePlanQuery) this.b).getFromAndTo().getDestinationPoiID())) {
                stringBuffer.append("&destinationid=").append(((RouteSearch.DrivePlanQuery) this.b).getFromAndTo().getDestinationPoiID());
            }
            if (!fl.g(((RouteSearch.DrivePlanQuery) this.b).getFromAndTo().getOriginType())) {
                stringBuffer.append("&origintype=").append(((RouteSearch.DrivePlanQuery) this.b).getFromAndTo().getOriginType());
            }
            if (!fl.g(((RouteSearch.DrivePlanQuery) this.b).getFromAndTo().getDestinationType())) {
                stringBuffer.append("&destinationtype=").append(((RouteSearch.DrivePlanQuery) this.b).getFromAndTo().getDestinationType());
            }
            if (!fl.g(((RouteSearch.DrivePlanQuery) this.b).getFromAndTo().getPlateProvince())) {
                stringBuffer.append("&province=").append(((RouteSearch.DrivePlanQuery) this.b).getFromAndTo().getPlateProvince());
            }
            if (!fl.g(((RouteSearch.DrivePlanQuery) this.b).getFromAndTo().getPlateNumber())) {
                stringBuffer.append("&number=").append(((RouteSearch.DrivePlanQuery) this.b).getFromAndTo().getPlateNumber());
            }
        }
        if (((RouteSearch.DrivePlanQuery) this.b).getDestParentPoiID() != null) {
            stringBuffer.append("&parentid=").append(((RouteSearch.DrivePlanQuery) this.b).getDestParentPoiID());
        }
        StringBuffer append = stringBuffer.append("&strategy=");
        StringBuilder sb = new StringBuilder();
        sb.append(((RouteSearch.DrivePlanQuery) this.b).getMode());
        append.append(sb.toString());
        StringBuffer append2 = stringBuffer.append("&cartype=");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(((RouteSearch.DrivePlanQuery) this.b).getCarType());
        append2.append(sb2.toString());
        StringBuffer append3 = stringBuffer.append("&firsttime=");
        StringBuilder sb3 = new StringBuilder();
        sb3.append(((RouteSearch.DrivePlanQuery) this.b).getFirstTime());
        append3.append(sb3.toString());
        StringBuffer append4 = stringBuffer.append("&interval=");
        StringBuilder sb4 = new StringBuilder();
        sb4.append(((RouteSearch.DrivePlanQuery) this.b).getInterval());
        append4.append(sb4.toString());
        StringBuffer append5 = stringBuffer.append("&count=");
        StringBuilder sb5 = new StringBuilder();
        sb5.append(((RouteSearch.DrivePlanQuery) this.b).getCount());
        append5.append(sb5.toString());
        return stringBuffer.toString();
    }

    private static DriveRoutePlanResult c(String str) throws AMapException {
        return fl.k(str);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return fc.b() + "/etd/driving?";
    }
}
