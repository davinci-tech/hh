package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.huawei.haf.application.BaseApplication;
import com.huawei.healthcloud.plugintrack.runningroute.manager.IGetPoiByLocationName;
import com.huawei.ui.commonui.model.ICityLatLonCallBack;
import com.huawei.ui.commonui.model.ITwoPointDistanceCallBack;
import health.compact.a.LogAnonymous;
import health.compact.a.LogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class gza implements RouteSearch.OnRouteSearchListener, PoiSearch.OnPoiSearchListener {
    private static volatile gza c;

    /* renamed from: a, reason: collision with root package name */
    private IGetPoiByLocationName f13010a;
    private ITwoPointDistanceCallBack b;
    private GeocodeSearch d;

    @Override // com.amap.api.services.route.RouteSearch.OnRouteSearchListener
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {
    }

    @Override // com.amap.api.services.route.RouteSearch.OnRouteSearchListener
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {
    }

    @Override // com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener
    public void onPoiItemSearched(PoiItem poiItem, int i) {
    }

    @Override // com.amap.api.services.route.RouteSearch.OnRouteSearchListener
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {
    }

    private gza() {
        MapsInitializer.updatePrivacyShow(BaseApplication.e(), true, true);
        MapsInitializer.updatePrivacyAgree(BaseApplication.e(), true);
    }

    public static gza b() {
        if (c == null) {
            synchronized (gza.class) {
                if (c == null) {
                    c = new gza();
                }
            }
        }
        return c;
    }

    public void b(LatLonPoint latLonPoint, ICityLatLonCallBack iCityLatLonCallBack) {
        if (latLonPoint != null) {
            LogUtil.c("GeoLocationManager", "getCityCenterPoint, lat: ", Double.valueOf(latLonPoint.getLatitude()), "lng: ", Double.valueOf(latLonPoint.getLongitude()));
        }
        RegeocodeQuery regeocodeQuery = new RegeocodeQuery(latLonPoint, 200.0f, GeocodeSearch.AMAP);
        try {
            GeocodeSearch geocodeSearch = new GeocodeSearch(BaseApplication.e());
            this.d = geocodeSearch;
            geocodeSearch.setOnGeocodeSearchListener(new e(this, iCityLatLonCallBack, 0));
            this.d.getFromLocationAsyn(regeocodeQuery);
            gwp.d(1001);
        } catch (AMapException e2) {
            LogUtil.a("GeoLocationManager", "getCityCenterPoint: ", LogAnonymous.b((Throwable) e2));
        }
    }

    public void a(LatLonPoint latLonPoint, ICityLatLonCallBack iCityLatLonCallBack) {
        if (latLonPoint != null) {
            LogUtil.c("GeoLocationManager", "getCityDetailInfoByLatLng, lat: ", Double.valueOf(latLonPoint.getLatitude()), "lng: ", Double.valueOf(latLonPoint.getLongitude()));
        }
        RegeocodeQuery regeocodeQuery = new RegeocodeQuery(latLonPoint, 200.0f, GeocodeSearch.AMAP);
        try {
            GeocodeSearch geocodeSearch = new GeocodeSearch(BaseApplication.e());
            this.d = geocodeSearch;
            geocodeSearch.setOnGeocodeSearchListener(new e(this, iCityLatLonCallBack, 1));
            this.d.getFromLocationAsyn(regeocodeQuery);
            gwp.d(1002);
        } catch (AMapException e2) {
            LogUtil.a("GeoLocationManager", "getCityDetailInfoByLatLng: ", LogAnonymous.b((Throwable) e2));
        }
    }

    public void d(Context context, String str, String str2, IGetPoiByLocationName iGetPoiByLocationName) {
        this.f13010a = iGetPoiByLocationName;
        PoiSearch.Query query = new PoiSearch.Query(str, "", str2);
        query.setPageSize(10);
        try {
            PoiSearch poiSearch = new PoiSearch(context, query);
            poiSearch.setOnPoiSearchListener(this);
            poiSearch.searchPOIAsyn();
            gwp.d(1003);
        } catch (AMapException e2) {
            LogUtil.a("GeoLocationManager", "getPoiByLocationName: ", LogAnonymous.b((Throwable) e2));
        }
    }

    @Override // com.amap.api.services.route.RouteSearch.OnRouteSearchListener
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {
        Iterator<WalkPath> it = walkRouteResult.getPaths().iterator();
        double d = 0.0d;
        while (it.hasNext()) {
            while (it.next().getSteps().iterator().hasNext()) {
                d += r6.next().getDistance();
            }
        }
        ITwoPointDistanceCallBack iTwoPointDistanceCallBack = this.b;
        if (iTwoPointDistanceCallBack != null) {
            iTwoPointDistanceCallBack.onDistanceBack(d);
        }
    }

    @Override // com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener
    public void onPoiSearched(PoiResult poiResult, int i) {
        ArrayList<PoiItem> pois = poiResult.getPois();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator<PoiItem> it = pois.iterator();
        while (it.hasNext()) {
            PoiItem next = it.next();
            LatLonPoint latLonPoint = next.getLatLonPoint();
            arrayList.add(next.getCityName());
            arrayList2.add(new hjd(latLonPoint.getLatitude(), latLonPoint.getLongitude()));
            arrayList3.add(next.getTitle());
        }
        IGetPoiByLocationName iGetPoiByLocationName = this.f13010a;
        if (iGetPoiByLocationName != null) {
            iGetPoiByLocationName.onPoiListBack(arrayList, arrayList2, arrayList3);
        }
    }

    static class e implements GeocodeSearch.OnGeocodeSearchListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<gza> f13011a;
        private ICityLatLonCallBack b;
        private int d;
        private npv e = new npv();

        public e(gza gzaVar, ICityLatLonCallBack iCityLatLonCallBack, int i) {
            this.f13011a = new WeakReference<>(gzaVar);
            this.b = iCityLatLonCallBack;
            this.d = i;
        }

        @Override // com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener
        public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
            WeakReference<gza> weakReference = this.f13011a;
            if (weakReference == null || weakReference.get() == null) {
                LogUtil.c("GeoLocationManager", "onRegeocodeSearched: mWeakReference is empty");
                return;
            }
            gza gzaVar = this.f13011a.get();
            Object[] objArr = new Object[4];
            objArr[0] = "onRegeocodeSearched, regeocodeResult is null: ";
            objArr[1] = Boolean.valueOf(regeocodeResult == null);
            objArr[2] = ", code: ";
            objArr[3] = Integer.valueOf(i);
            LogUtil.c("GeoLocationManager", objArr);
            if (regeocodeResult != null) {
                if (gzaVar.d == null || regeocodeResult.getRegeocodeAddress().getCity() == null) {
                    return;
                }
                RegeocodeAddress regeocodeAddress = regeocodeResult.getRegeocodeAddress();
                String city = regeocodeAddress.getCity();
                this.e.c(city);
                StringBuilder sb = new StringBuilder();
                sb.append(regeocodeAddress.getCity());
                sb.append(regeocodeAddress.getDistrict());
                sb.append(regeocodeAddress.getTownship());
                if (koq.c(regeocodeAddress.getRoads()) && regeocodeAddress.getRoads().get(0) != null) {
                    sb.append(regeocodeAddress.getRoads().get(0).getName());
                }
                this.e.d(sb.toString());
                LogUtil.c("GeoLocationManager", "city: ", city);
                int i2 = this.d;
                if (i2 == 0) {
                    gzaVar.d.getFromLocationNameAsyn(new GeocodeQuery(city, city));
                    gwp.d(1004);
                    return;
                } else if (i2 == 1) {
                    this.b.onPointBack(this.e);
                    return;
                } else {
                    LogUtil.c("GeoLocationManager", "mRegeocodeQueryType invalid");
                    return;
                }
            }
            this.b.onPointBack(null);
        }

        @Override // com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener
        public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
            GeocodeAddress geocodeAddress;
            WeakReference<gza> weakReference = this.f13011a;
            if (weakReference == null || weakReference.get() == null) {
                LogUtil.c("GeoLocationManager", "onGeocodeSearched: mWeakReference is empty");
                return;
            }
            Object[] objArr = new Object[4];
            objArr[0] = "onGeocodeSearched, geocodeResult is null: ";
            objArr[1] = Boolean.valueOf(geocodeResult == null);
            objArr[2] = ", code: ";
            objArr[3] = Integer.valueOf(i);
            LogUtil.c("GeoLocationManager", objArr);
            if (geocodeResult == null) {
                this.b.onPointBack(null);
                return;
            }
            if (geocodeResult.getGeocodeAddressList() == null || geocodeResult.getGeocodeAddressList().size() <= 0 || (geocodeAddress = geocodeResult.getGeocodeAddressList().get(0)) == null || this.b == null) {
                return;
            }
            LatLonPoint latLonPoint = geocodeAddress.getLatLonPoint();
            this.e.a(new npq(latLonPoint.getLatitude(), latLonPoint.getLongitude()));
            if (!TextUtils.isEmpty(geocodeAddress.getCity())) {
                this.e.c(geocodeAddress.getCity());
            }
            this.b.onPointBack(this.e);
        }
    }
}
