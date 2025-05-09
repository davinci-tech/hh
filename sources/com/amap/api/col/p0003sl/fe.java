package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DistanceResult;
import com.amap.api.services.route.DistanceSearch;
import java.util.List;

/* loaded from: classes8.dex */
public final class fe extends ew<DistanceSearch.DistanceQuery, DistanceResult> {
    private final String k;
    private final String l;
    private final String m;

    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final /* synthetic */ Object a(String str) throws AMapException {
        return c(str);
    }

    public fe(Context context, DistanceSearch.DistanceQuery distanceQuery) {
        super(context, distanceQuery);
        this.k = "/distance?";
        this.l = "|";
        this.m = ",";
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        StringBuffer stringBuffer = new StringBuffer("key=");
        stringBuffer.append(hn.f(this.i));
        List<LatLonPoint> origins = ((DistanceSearch.DistanceQuery) this.b).getOrigins();
        if (origins != null && origins.size() > 0) {
            stringBuffer.append("&origins=");
            int size = origins.size();
            for (int i = 0; i < size; i++) {
                LatLonPoint latLonPoint = origins.get(i);
                if (latLonPoint != null) {
                    double a2 = fd.a(latLonPoint.getLatitude());
                    stringBuffer.append(fd.a(latLonPoint.getLongitude()));
                    stringBuffer.append(",");
                    stringBuffer.append(a2);
                    if (i < size) {
                        stringBuffer.append("|");
                    }
                }
            }
        }
        LatLonPoint destination = ((DistanceSearch.DistanceQuery) this.b).getDestination();
        if (destination != null) {
            double a3 = fd.a(destination.getLatitude());
            double a4 = fd.a(destination.getLongitude());
            stringBuffer.append("&destination=");
            stringBuffer.append(a4);
            stringBuffer.append(",");
            stringBuffer.append(a3);
        }
        stringBuffer.append("&type=").append(((DistanceSearch.DistanceQuery) this.b).getType());
        if (!TextUtils.isEmpty(((DistanceSearch.DistanceQuery) this.b).getExtensions())) {
            stringBuffer.append("&extensions=").append(((DistanceSearch.DistanceQuery) this.b).getExtensions());
        } else {
            stringBuffer.append("&extensions=base");
        }
        stringBuffer.append("&output=json");
        if (((DistanceSearch.DistanceQuery) this.b).getType() == 1) {
            stringBuffer.append("&strategy=").append(((DistanceSearch.DistanceQuery) this.b).getMode());
        }
        return stringBuffer.toString();
    }

    private static DistanceResult c(String str) throws AMapException {
        return fl.i(str);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return fc.a() + "/distance?";
    }
}
