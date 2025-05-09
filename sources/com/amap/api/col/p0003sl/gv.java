package com.amap.api.col.p0003sl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.col.p0003sl.fo;
import com.amap.api.col.p0003sl.hw;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.interfaces.IGeocodeSearch;
import java.util.List;

/* loaded from: classes2.dex */
public final class gv implements IGeocodeSearch {

    /* renamed from: a, reason: collision with root package name */
    private Context f1082a;
    private GeocodeSearch.OnGeocodeSearchListener b;
    private Handler c;

    public gv(Context context) throws AMapException {
        hx a2 = hw.a(context, fc.a(false));
        if (a2.f1161a != hw.c.SuccessCode) {
            throw new AMapException(a2.b, 1, a2.b, a2.f1161a.a());
        }
        this.f1082a = context.getApplicationContext();
        this.c = fo.a();
    }

    @Override // com.amap.api.services.interfaces.IGeocodeSearch
    public final RegeocodeAddress getFromLocation(RegeocodeQuery regeocodeQuery) throws AMapException {
        try {
            fm.a(this.f1082a);
            if (!a(regeocodeQuery)) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            return new gd(this.f1082a, regeocodeQuery).d();
        } catch (AMapException e) {
            fd.a(e, "GeocodeSearch", "getFromLocationAsyn");
            throw e;
        }
    }

    @Override // com.amap.api.services.interfaces.IGeocodeSearch
    public final List<GeocodeAddress> getFromLocationName(GeocodeQuery geocodeQuery) throws AMapException {
        try {
            fm.a(this.f1082a);
            if (geocodeQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            return new fj(this.f1082a, geocodeQuery).d();
        } catch (AMapException e) {
            fd.a(e, "GeocodeSearch", "getFromLocationName");
            throw e;
        }
    }

    @Override // com.amap.api.services.interfaces.IGeocodeSearch
    public final void setOnGeocodeSearchListener(GeocodeSearch.OnGeocodeSearchListener onGeocodeSearchListener) {
        this.b = onGeocodeSearchListener;
    }

    @Override // com.amap.api.services.interfaces.IGeocodeSearch
    public final void getFromLocationAsyn(final RegeocodeQuery regeocodeQuery) {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.gv.1
                @Override // java.lang.Runnable
                public final void run() {
                    Message obtainMessage = fo.a().obtainMessage();
                    try {
                        try {
                            obtainMessage.arg1 = 2;
                            obtainMessage.what = 201;
                            fo.i iVar = new fo.i();
                            iVar.b = gv.this.b;
                            obtainMessage.obj = iVar;
                            iVar.f1049a = new RegeocodeResult(regeocodeQuery, gv.this.getFromLocation(regeocodeQuery));
                            obtainMessage.arg2 = 1000;
                        } catch (AMapException e) {
                            obtainMessage.arg2 = e.getErrorCode();
                        }
                    } finally {
                        gv.this.c.sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            fd.a(th, "GeocodeSearch", "getFromLocationAsyn_threadcreate");
        }
    }

    @Override // com.amap.api.services.interfaces.IGeocodeSearch
    public final void getFromLocationNameAsyn(final GeocodeQuery geocodeQuery) {
        try {
            gj.a().a(new Runnable() { // from class: com.amap.api.col.3sl.gv.2
                @Override // java.lang.Runnable
                public final void run() {
                    Message obtainMessage = fo.a().obtainMessage();
                    try {
                        try {
                            obtainMessage.what = 200;
                            obtainMessage.arg1 = 2;
                            obtainMessage.arg2 = 1000;
                            fo.e eVar = new fo.e();
                            eVar.b = gv.this.b;
                            obtainMessage.obj = eVar;
                            eVar.f1045a = new GeocodeResult(geocodeQuery, gv.this.getFromLocationName(geocodeQuery));
                        } catch (AMapException e) {
                            obtainMessage.arg2 = e.getErrorCode();
                        }
                    } finally {
                        gv.this.c.sendMessage(obtainMessage);
                    }
                }
            });
        } catch (Throwable th) {
            fd.a(th, "GeocodeSearch", "getFromLocationNameAsynThrowable");
        }
    }

    private static boolean a(RegeocodeQuery regeocodeQuery) {
        return (regeocodeQuery == null || regeocodeQuery.getPoint() == null || regeocodeQuery.getLatLonType() == null) ? false : true;
    }
}
