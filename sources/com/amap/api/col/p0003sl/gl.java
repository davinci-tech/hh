package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;

/* loaded from: classes8.dex */
public final class gl extends ew<RouteSearch.WalkRouteQuery, WalkRouteResult> {
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final /* synthetic */ Object a(String str) throws AMapException {
        return c(str);
    }

    public gl(Context context, RouteSearch.WalkRouteQuery walkRouteQuery) {
        super(context, walkRouteQuery);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amap.api.col.p0003sl.ew, com.amap.api.col.p0003sl.ev
    protected final String c() {
        StringBuffer stringBuffer = new StringBuffer("key=");
        stringBuffer.append(hn.f(this.i));
        stringBuffer.append("&origin=").append(fd.a(((RouteSearch.WalkRouteQuery) this.b).getFromAndTo().getFrom()));
        stringBuffer.append("&destination=").append(fd.a(((RouteSearch.WalkRouteQuery) this.b).getFromAndTo().getTo()));
        stringBuffer.append("&multipath=0&output=json&geometry=false");
        if (!TextUtils.isEmpty(((RouteSearch.WalkRouteQuery) this.b).getExtensions())) {
            stringBuffer.append("&extensions=").append(((RouteSearch.WalkRouteQuery) this.b).getExtensions());
        } else {
            stringBuffer.append("&extensions=base");
        }
        return stringBuffer.toString();
    }

    private static WalkRouteResult c(String str) throws AMapException {
        return fl.d(str);
    }

    @Override // com.amap.api.col.p0003sl.ka
    public final String getURL() {
        return fc.a() + "/direction/walking?";
    }
}
