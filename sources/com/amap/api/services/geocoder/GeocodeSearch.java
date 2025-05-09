package com.amap.api.services.geocoder;

import android.content.Context;
import com.amap.api.col.p0003sl.gv;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IGeocodeSearch;
import java.util.List;

/* loaded from: classes2.dex */
public final class GeocodeSearch {
    public static final String AMAP = "autonavi";
    public static final String EXTENSIONS_ALL = "all";
    public static final String EXTENSIONS_BASE = "base";
    public static final String GPS = "gps";

    /* renamed from: a, reason: collision with root package name */
    private IGeocodeSearch f1497a;

    public interface OnGeocodeSearchListener {
        void onGeocodeSearched(GeocodeResult geocodeResult, int i);

        void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i);
    }

    public GeocodeSearch(Context context) throws AMapException {
        if (this.f1497a == null) {
            try {
                this.f1497a = new gv(context);
            } catch (Exception e) {
                e.printStackTrace();
                if (e instanceof AMapException) {
                    throw ((AMapException) e);
                }
            }
        }
    }

    public final RegeocodeAddress getFromLocation(RegeocodeQuery regeocodeQuery) throws AMapException {
        IGeocodeSearch iGeocodeSearch = this.f1497a;
        if (iGeocodeSearch != null) {
            return iGeocodeSearch.getFromLocation(regeocodeQuery);
        }
        return null;
    }

    public final List<GeocodeAddress> getFromLocationName(GeocodeQuery geocodeQuery) throws AMapException {
        IGeocodeSearch iGeocodeSearch = this.f1497a;
        if (iGeocodeSearch != null) {
            return iGeocodeSearch.getFromLocationName(geocodeQuery);
        }
        return null;
    }

    public final void setOnGeocodeSearchListener(OnGeocodeSearchListener onGeocodeSearchListener) {
        IGeocodeSearch iGeocodeSearch = this.f1497a;
        if (iGeocodeSearch != null) {
            iGeocodeSearch.setOnGeocodeSearchListener(onGeocodeSearchListener);
        }
    }

    public final void getFromLocationAsyn(RegeocodeQuery regeocodeQuery) {
        IGeocodeSearch iGeocodeSearch = this.f1497a;
        if (iGeocodeSearch != null) {
            iGeocodeSearch.getFromLocationAsyn(regeocodeQuery);
        }
    }

    public final void getFromLocationNameAsyn(GeocodeQuery geocodeQuery) {
        IGeocodeSearch iGeocodeSearch = this.f1497a;
        if (iGeocodeSearch != null) {
            iGeocodeSearch.getFromLocationNameAsyn(geocodeQuery);
        }
    }
}
