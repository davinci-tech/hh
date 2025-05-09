package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IRouteSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRoutePlanResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.TruckRouteRestult;
import com.amap.api.services.route.WalkRouteResult;

/* loaded from: classes8.dex */
public final class ha implements IRouteSearch {

    /* renamed from: a, reason: collision with root package name */
    private RouteSearch.OnRouteSearchListener f1101a;
    private RouteSearch.OnTruckRouteSearchListener b;
    private RouteSearch.OnRoutePlanSearchListener c;
    private Context d;
    private Handler e;

    public ha(Context context) throws AMapException {
        hx a2 = hw.a(context, fc.a(false));
        if (a2.f1161a != hw.c.SuccessCode) {
            throw new AMapException(a2.b, 1, a2.b, a2.f1161a.a());
        }
        this.d = context.getApplicationContext();
        this.e = fo.a();
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public final void setRouteSearchListener(RouteSearch.OnRouteSearchListener onRouteSearchListener) {
        this.f1101a = onRouteSearchListener;
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public final void setOnTruckRouteSearchListener(RouteSearch.OnTruckRouteSearchListener onTruckRouteSearchListener) {
        this.b = onTruckRouteSearchListener;
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public final void setOnRoutePlanSearchListener(RouteSearch.OnRoutePlanSearchListener onRoutePlanSearchListener) {
        this.c = onRoutePlanSearchListener;
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public final WalkRouteResult calculateWalkRoute(RouteSearch.WalkRouteQuery walkRouteQuery) throws AMapException {
        try {
            fm.a(this.d);
            if (walkRouteQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            if (!a(walkRouteQuery.getFromAndTo())) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            gc.a().b(walkRouteQuery.getFromAndTo());
            RouteSearch.WalkRouteQuery m104clone = walkRouteQuery.m104clone();
            WalkRouteResult d = new gl(this.d, m104clone).d();
            if (d != null) {
                d.setWalkQuery(m104clone);
            }
            return d;
        } catch (AMapException e) {
            fd.a(e, "RouteSearch", "calculateWalkRoute");
            throw e;
        }
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public final void calculateWalkRouteAsyn(final RouteSearch.WalkRouteQuery walkRouteQuery) {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.ha.1
                @Override // java.lang.Runnable
                public final void run() {
                    Message obtainMessage = fo.a().obtainMessage();
                    obtainMessage.what = 102;
                    obtainMessage.arg1 = 1;
                    Bundle bundle = new Bundle();
                    WalkRouteResult walkRouteResult = null;
                    try {
                        try {
                            walkRouteResult = ha.this.calculateWalkRoute(walkRouteQuery);
                            bundle.putInt("errorCode", 1000);
                        } catch (AMapException e) {
                            bundle.putInt("errorCode", e.getErrorCode());
                        }
                    } finally {
                        obtainMessage.obj = ha.this.f1101a;
                        bundle.putParcelable("result", walkRouteResult);
                        obtainMessage.setData(bundle);
                        ha.this.e.sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            fd.a(th, "RouteSearch", "calculateWalkRouteAsyn");
        }
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public final BusRouteResult calculateBusRoute(RouteSearch.BusRouteQuery busRouteQuery) throws AMapException {
        try {
            fm.a(this.d);
            if (busRouteQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            if (!a(busRouteQuery.getFromAndTo())) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            RouteSearch.BusRouteQuery m98clone = busRouteQuery.m98clone();
            BusRouteResult d = new ex(this.d, m98clone).d();
            if (d != null) {
                d.setBusQuery(m98clone);
            }
            return d;
        } catch (AMapException e) {
            fd.a(e, "RouteSearch", "calculateBusRoute");
            throw e;
        }
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public final void calculateBusRouteAsyn(final RouteSearch.BusRouteQuery busRouteQuery) {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.ha.2
                @Override // java.lang.Runnable
                public final void run() {
                    Message obtainMessage = fo.a().obtainMessage();
                    obtainMessage.what = 100;
                    obtainMessage.arg1 = 1;
                    Bundle bundle = new Bundle();
                    BusRouteResult busRouteResult = null;
                    try {
                        try {
                            busRouteResult = ha.this.calculateBusRoute(busRouteQuery);
                            bundle.putInt("errorCode", 1000);
                        } catch (AMapException e) {
                            bundle.putInt("errorCode", e.getErrorCode());
                        }
                    } finally {
                        obtainMessage.obj = ha.this.f1101a;
                        bundle.putParcelable("result", busRouteResult);
                        obtainMessage.setData(bundle);
                        ha.this.e.sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            fd.a(th, "RouteSearch", "calculateBusRouteAsyn");
        }
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public final DriveRouteResult calculateDriveRoute(RouteSearch.DriveRouteQuery driveRouteQuery) throws AMapException {
        try {
            fm.a(this.d);
            if (driveRouteQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            if (!a(driveRouteQuery.getFromAndTo())) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            gc.a().a(driveRouteQuery.getPassedByPoints());
            gc.a().b(driveRouteQuery.getAvoidpolygons());
            RouteSearch.DriveRouteQuery m100clone = driveRouteQuery.m100clone();
            DriveRouteResult d = new fh(this.d, m100clone).d();
            if (d != null) {
                d.setDriveQuery(m100clone);
            }
            return d;
        } catch (AMapException e) {
            fd.a(e, "RouteSearch", "calculateDriveRoute");
            throw e;
        }
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public final void calculateDriveRouteAsyn(final RouteSearch.DriveRouteQuery driveRouteQuery) {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.ha.3
                @Override // java.lang.Runnable
                public final void run() {
                    Message obtainMessage = fo.a().obtainMessage();
                    obtainMessage.what = 101;
                    obtainMessage.arg1 = 1;
                    Bundle bundle = new Bundle();
                    DriveRouteResult driveRouteResult = null;
                    try {
                        try {
                            driveRouteResult = ha.this.calculateDriveRoute(driveRouteQuery);
                            bundle.putInt("errorCode", 1000);
                        } catch (AMapException e) {
                            bundle.putInt("errorCode", e.getErrorCode());
                        }
                    } finally {
                        obtainMessage.obj = ha.this.f1101a;
                        bundle.putParcelable("result", driveRouteResult);
                        obtainMessage.setData(bundle);
                        ha.this.e.sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            fd.a(th, "RouteSearch", "calculateDriveRouteAsyn");
        }
    }

    private static boolean a(RouteSearch.FromAndTo fromAndTo) {
        return (fromAndTo == null || fromAndTo.getFrom() == null || fromAndTo.getTo() == null) ? false : true;
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public final void calculateRideRouteAsyn(final RouteSearch.RideRouteQuery rideRouteQuery) {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.ha.4
                @Override // java.lang.Runnable
                public final void run() {
                    Message obtainMessage = fo.a().obtainMessage();
                    obtainMessage.what = 103;
                    obtainMessage.arg1 = 1;
                    Bundle bundle = new Bundle();
                    RideRouteResult rideRouteResult = null;
                    try {
                        try {
                            rideRouteResult = ha.this.calculateRideRoute(rideRouteQuery);
                            bundle.putInt("errorCode", 1000);
                        } catch (AMapException e) {
                            bundle.putInt("errorCode", e.getErrorCode());
                        }
                    } finally {
                        obtainMessage.obj = ha.this.f1101a;
                        bundle.putParcelable("result", rideRouteResult);
                        obtainMessage.setData(bundle);
                        ha.this.e.sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            fd.a(th, "RouteSearch", "calculateRideRouteAsyn");
        }
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public final RideRouteResult calculateRideRoute(RouteSearch.RideRouteQuery rideRouteQuery) throws AMapException {
        try {
            fm.a(this.d);
            if (rideRouteQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            if (!a(rideRouteQuery.getFromAndTo())) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            gc.a().a(rideRouteQuery.getFromAndTo());
            RouteSearch.RideRouteQuery m102clone = rideRouteQuery.m102clone();
            RideRouteResult d = new ge(this.d, m102clone).d();
            if (d != null) {
                d.setRideQuery(m102clone);
            }
            return d;
        } catch (AMapException e) {
            fd.a(e, "RouteSearch", "calculaterideRoute");
            throw e;
        }
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public final TruckRouteRestult calculateTruckRoute(RouteSearch.TruckRouteQuery truckRouteQuery) throws AMapException {
        try {
            fm.a(this.d);
            if (truckRouteQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            if (!a(truckRouteQuery.getFromAndTo())) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            gc.a().a(truckRouteQuery.getFromAndTo(), truckRouteQuery.getPassedByPoints());
            gc.a().a(truckRouteQuery.getPassedByPoints());
            RouteSearch.TruckRouteQuery m103clone = truckRouteQuery.m103clone();
            TruckRouteRestult d = new gk(this.d, m103clone).d();
            if (d != null) {
                d.setTruckQuery(m103clone);
            }
            return d;
        } catch (AMapException e) {
            fd.a(e, "RouteSearch", "calculateDriveRoute");
            throw e;
        }
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public final void calculateTruckRouteAsyn(final RouteSearch.TruckRouteQuery truckRouteQuery) {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.ha.5
                @Override // java.lang.Runnable
                public final void run() {
                    Message obtainMessage = fo.a().obtainMessage();
                    obtainMessage.what = 104;
                    obtainMessage.arg1 = 17;
                    Bundle bundle = new Bundle();
                    TruckRouteRestult truckRouteRestult = null;
                    try {
                        try {
                            truckRouteRestult = ha.this.calculateTruckRoute(truckRouteQuery);
                            bundle.putInt("errorCode", 1000);
                        } catch (AMapException e) {
                            bundle.putInt("errorCode", e.getErrorCode());
                        }
                    } finally {
                        obtainMessage.obj = ha.this.b;
                        bundle.putParcelable("result", truckRouteRestult);
                        obtainMessage.setData(bundle);
                        ha.this.e.sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            fd.a(th, "RouteSearch", "calculateTruckRouteAsyn");
        }
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public final DriveRoutePlanResult calculateDrivePlan(RouteSearch.DrivePlanQuery drivePlanQuery) throws AMapException {
        try {
            fm.a(this.d);
            if (drivePlanQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            if (!a(drivePlanQuery.getFromAndTo())) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            DriveRoutePlanResult d = new fg(this.d, drivePlanQuery.m99clone()).d();
            if (d != null) {
                d.setDrivePlanQuery(drivePlanQuery);
            }
            return d;
        } catch (AMapException e) {
            fd.a(e, "RouteSearch", "calculateDrivePlan");
            throw e;
        }
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public final void calculateDrivePlanAsyn(final RouteSearch.DrivePlanQuery drivePlanQuery) {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.ha.6
                @Override // java.lang.Runnable
                public final void run() {
                    Message obtainMessage = fo.a().obtainMessage();
                    obtainMessage.what = 105;
                    obtainMessage.arg1 = 18;
                    Bundle bundle = new Bundle();
                    DriveRoutePlanResult driveRoutePlanResult = null;
                    try {
                        try {
                            driveRoutePlanResult = ha.this.calculateDrivePlan(drivePlanQuery);
                            bundle.putInt("errorCode", 1000);
                        } catch (AMapException e) {
                            bundle.putInt("errorCode", e.getErrorCode());
                        }
                    } finally {
                        obtainMessage.obj = ha.this.c;
                        bundle.putParcelable("result", driveRoutePlanResult);
                        obtainMessage.setData(bundle);
                        ha.this.e.sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            fd.a(th, "RouteSearch", "calculateTruckRouteAsyn");
        }
    }
}
