package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IRouteSearchV2;
import com.amap.api.services.route.DriveRouteResultV2;
import com.amap.api.services.route.RouteSearchV2;

/* loaded from: classes8.dex */
public final class hb implements IRouteSearchV2 {

    /* renamed from: a, reason: collision with root package name */
    private RouteSearchV2.OnRouteSearchListener f1108a;
    private Context b;
    private Handler c;

    public hb(Context context) throws AMapException {
        hx a2 = hw.a(context, fc.a(false));
        if (a2.f1161a != hw.c.SuccessCode) {
            throw new AMapException(a2.b, 1, a2.b, a2.f1161a.a());
        }
        this.b = context.getApplicationContext();
        this.c = fo.a();
    }

    private static boolean a(RouteSearchV2.FromAndTo fromAndTo) {
        return (fromAndTo == null || fromAndTo.getFrom() == null || fromAndTo.getTo() == null) ? false : true;
    }

    @Override // com.amap.api.services.interfaces.IRouteSearchV2
    public final void setRouteSearchListener(RouteSearchV2.OnRouteSearchListener onRouteSearchListener) {
        this.f1108a = onRouteSearchListener;
    }

    @Override // com.amap.api.services.interfaces.IRouteSearchV2
    public final DriveRouteResultV2 calculateDriveRoute(RouteSearchV2.DriveRouteQuery driveRouteQuery) throws AMapException {
        try {
            fm.a(this.b);
            if (driveRouteQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            if (!a(driveRouteQuery.getFromAndTo())) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            gc.a().a(driveRouteQuery.getPassedByPoints());
            gc.a().b(driveRouteQuery.getAvoidpolygons());
            RouteSearchV2.DriveRouteQuery m105clone = driveRouteQuery.m105clone();
            DriveRouteResultV2 d = new fi(this.b, m105clone).d();
            if (d != null) {
                d.setDriveQuery(m105clone);
            }
            return d;
        } catch (AMapException e) {
            fd.a(e, "RouteSearch", "calculateDriveRoute");
            throw e;
        }
    }

    @Override // com.amap.api.services.interfaces.IRouteSearchV2
    public final void calculateDriveRouteAsyn(final RouteSearchV2.DriveRouteQuery driveRouteQuery) {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.hb.1
                @Override // java.lang.Runnable
                public final void run() {
                    Message obtainMessage = fo.a().obtainMessage();
                    obtainMessage.what = 101;
                    obtainMessage.arg1 = 101;
                    Bundle bundle = new Bundle();
                    DriveRouteResultV2 driveRouteResultV2 = null;
                    try {
                        try {
                            driveRouteResultV2 = hb.this.calculateDriveRoute(driveRouteQuery);
                            bundle.putInt("errorCode", 1000);
                        } catch (AMapException e) {
                            bundle.putInt("errorCode", e.getErrorCode());
                        }
                    } finally {
                        obtainMessage.obj = hb.this.f1108a;
                        bundle.putParcelable("result", driveRouteResultV2);
                        obtainMessage.setData(bundle);
                        hb.this.c.sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            fd.a(th, "RouteSearch", "calculateDriveRouteAsyn");
        }
    }
}
