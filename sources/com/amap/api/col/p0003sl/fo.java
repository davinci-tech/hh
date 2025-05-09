package com.amap.api.col.p0003sl;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.services.busline.BusLineResult;
import com.amap.api.services.busline.BusLineSearch;
import com.amap.api.services.busline.BusStationResult;
import com.amap.api.services.busline.BusStationSearch;
import com.amap.api.services.cloud.CloudItemDetail;
import com.amap.api.services.cloud.CloudResult;
import com.amap.api.services.cloud.CloudSearch;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearch;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.nearby.NearbySearch;
import com.amap.api.services.nearby.NearbySearchResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DistanceResult;
import com.amap.api.services.route.DistanceSearch;
import com.amap.api.services.route.DriveRoutePlanResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveRouteResultV2;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.RouteSearchV2;
import com.amap.api.services.route.TruckRouteRestult;
import com.amap.api.services.route.WalkRouteResult;
import com.amap.api.services.routepoisearch.RoutePOISearch;
import com.amap.api.services.routepoisearch.RoutePOISearchResult;
import com.amap.api.services.share.ShareSearch;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.huawei.hms.framework.network.download.internal.constants.ExceptionCode;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public final class fo extends Handler {

    /* renamed from: a, reason: collision with root package name */
    private static fo f1040a;

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public BusLineResult f1041a;
        public BusLineSearch.OnBusLineSearchListener b;
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        public BusStationResult f1042a;
        public BusStationSearch.OnBusStationSearchListener b;
    }

    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        public CloudItemDetail f1043a;
        public CloudSearch.OnCloudSearchListener b;
    }

    public static final class d {

        /* renamed from: a, reason: collision with root package name */
        public CloudResult f1044a;
        public CloudSearch.OnCloudSearchListener b;
    }

    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        public GeocodeResult f1045a;
        public GeocodeSearch.OnGeocodeSearchListener b;
    }

    public static final class f {

        /* renamed from: a, reason: collision with root package name */
        public List<NearbySearch.NearbyListener> f1046a;
        public NearbySearchResult b;
    }

    public static final class g {

        /* renamed from: a, reason: collision with root package name */
        public PoiItem f1047a;
        public PoiSearch.OnPoiSearchListener b;
    }

    public static final class h {

        /* renamed from: a, reason: collision with root package name */
        public PoiResult f1048a;
        public PoiSearch.OnPoiSearchListener b;
    }

    public static final class i {

        /* renamed from: a, reason: collision with root package name */
        public RegeocodeResult f1049a;
        public GeocodeSearch.OnGeocodeSearchListener b;
    }

    public static final class j {

        /* renamed from: a, reason: collision with root package name */
        public RoutePOISearchResult f1050a;
        public RoutePOISearch.OnRoutePOISearchListener b;
    }

    public static final class k {

        /* renamed from: a, reason: collision with root package name */
        public LocalWeatherForecastResult f1051a;
        public WeatherSearch.OnWeatherSearchListener b;
    }

    public static final class l {

        /* renamed from: a, reason: collision with root package name */
        public LocalWeatherLiveResult f1052a;
        public WeatherSearch.OnWeatherSearchListener b;
    }

    public static fo a() {
        fo foVar;
        synchronized (fo.class) {
            if (f1040a == null) {
                if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
                    f1040a = new fo();
                }
                f1040a = new fo(Looper.getMainLooper());
            }
            foVar = f1040a;
        }
        return foVar;
    }

    fo() {
    }

    private fo(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        try {
            int i2 = message.arg1;
            if (i2 != 101) {
                switch (i2) {
                    case 1:
                        k(message);
                        break;
                    case 2:
                        h(message);
                        break;
                    case 3:
                        j(message);
                        break;
                    case 4:
                        i(message);
                        break;
                    case 5:
                        g(message);
                        break;
                    case 6:
                        f(message);
                        break;
                    case 7:
                        e(message);
                        break;
                    case 8:
                        d(message);
                        break;
                    case 9:
                        c(message);
                        break;
                    case 10:
                        b(message);
                        break;
                    case 11:
                        a(message);
                        break;
                    case 12:
                        o(message);
                        break;
                    case 13:
                        p(message);
                        break;
                    case 14:
                        q(message);
                        break;
                    default:
                        switch (i2) {
                            case 16:
                                r(message);
                                break;
                            case 17:
                                m(message);
                                break;
                            case 18:
                                n(message);
                                break;
                        }
                }
            }
            l(message);
        } catch (Throwable th) {
            fd.a(th, "MessageHandler", "handleMessage");
        }
    }

    private static void a(Message message) {
        int i2 = message.arg2;
        ShareSearch.OnShareSearchListener onShareSearchListener = (ShareSearch.OnShareSearchListener) message.obj;
        String string = message.getData().getString("shareurlkey");
        if (onShareSearchListener == null) {
        }
        switch (message.what) {
            case 1100:
                onShareSearchListener.onPoiShareUrlSearched(string, i2);
                break;
            case 1101:
                onShareSearchListener.onLocationShareUrlSearched(string, i2);
                break;
            case 1102:
                onShareSearchListener.onNaviShareUrlSearched(string, i2);
                break;
            case 1103:
                onShareSearchListener.onBusRouteShareUrlSearched(string, i2);
                break;
            case ExceptionCode.CHECK_FILE_HASH_FAILED /* 1104 */:
                onShareSearchListener.onDrivingRouteShareUrlSearched(string, i2);
                break;
            case ExceptionCode.CHECK_FILE_SIZE_FAILED /* 1105 */:
                onShareSearchListener.onWalkRouteShareUrlSearched(string, i2);
                break;
        }
    }

    private static void b(Message message) {
        List list = (List) message.obj;
        if (list == null || list.size() == 0) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((NearbySearch.NearbyListener) it.next()).onNearbyInfoUploaded(message.what);
        }
    }

    private static void c(Message message) {
        List<NearbySearch.NearbyListener> list;
        f fVar = (f) message.obj;
        if (fVar == null || (list = fVar.f1046a) == null || list.size() == 0) {
            return;
        }
        NearbySearchResult nearbySearchResult = message.what == 1000 ? fVar.b : null;
        Iterator<NearbySearch.NearbyListener> it = list.iterator();
        while (it.hasNext()) {
            it.next().onNearbyInfoSearched(nearbySearchResult, message.what);
        }
    }

    private static void d(Message message) {
        List list = (List) message.obj;
        if (list == null || list.size() == 0) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((NearbySearch.NearbyListener) it.next()).onUserInfoCleared(message.what);
        }
    }

    private static void e(Message message) {
        BusStationSearch.OnBusStationSearchListener onBusStationSearchListener;
        b bVar = (b) message.obj;
        if (bVar == null || (onBusStationSearchListener = bVar.b) == null) {
            return;
        }
        onBusStationSearchListener.onBusStationSearched(message.what == 1000 ? bVar.f1042a : null, message.what);
    }

    private static void f(Message message) {
        g gVar;
        PoiSearch.OnPoiSearchListener onPoiSearchListener;
        Bundle data;
        if (message.what == 600) {
            h hVar = (h) message.obj;
            if (hVar == null || (onPoiSearchListener = hVar.b) == null || (data = message.getData()) == null) {
                return;
            }
            onPoiSearchListener.onPoiSearched(hVar.f1048a, data.getInt("errorCode"));
            return;
        }
        if (message.what != 602 || (gVar = (g) message.obj) == null) {
            return;
        }
        PoiSearch.OnPoiSearchListener onPoiSearchListener2 = gVar.b;
        Bundle data2 = message.getData();
        if (data2 != null) {
            onPoiSearchListener2.onPoiItemSearched(gVar.f1047a, data2.getInt("errorCode"));
        }
    }

    private static void g(Message message) {
        Inputtips.InputtipsListener inputtipsListener = (Inputtips.InputtipsListener) message.obj;
        if (inputtipsListener == null) {
            return;
        }
        inputtipsListener.onGetInputtips(message.what == 1000 ? message.getData().getParcelableArrayList("result") : null, message.what);
    }

    private static void h(Message message) {
        e eVar;
        GeocodeSearch.OnGeocodeSearchListener onGeocodeSearchListener;
        GeocodeSearch.OnGeocodeSearchListener onGeocodeSearchListener2;
        if (message.what == 201) {
            i iVar = (i) message.obj;
            if (iVar == null || (onGeocodeSearchListener2 = iVar.b) == null) {
                return;
            }
            onGeocodeSearchListener2.onRegeocodeSearched(iVar.f1049a, message.arg2);
            return;
        }
        if (message.what != 200 || (eVar = (e) message.obj) == null || (onGeocodeSearchListener = eVar.b) == null) {
            return;
        }
        onGeocodeSearchListener.onGeocodeSearched(eVar.f1045a, message.arg2);
    }

    private static void i(Message message) {
        DistrictSearch.OnDistrictSearchListener onDistrictSearchListener = (DistrictSearch.OnDistrictSearchListener) message.obj;
        if (onDistrictSearchListener == null) {
            return;
        }
        onDistrictSearchListener.onDistrictSearched((DistrictResult) message.getData().getParcelable("result"));
    }

    private static void j(Message message) {
        BusLineSearch.OnBusLineSearchListener onBusLineSearchListener;
        a aVar = (a) message.obj;
        if (aVar == null || (onBusLineSearchListener = aVar.b) == null) {
            return;
        }
        onBusLineSearchListener.onBusLineSearched(message.what == 1000 ? aVar.f1041a : null, message.what);
    }

    private static void k(Message message) {
        Bundle data;
        RouteSearch.OnRouteSearchListener onRouteSearchListener = (RouteSearch.OnRouteSearchListener) message.obj;
        if (onRouteSearchListener == null) {
            return;
        }
        if (message.what == 100) {
            Bundle data2 = message.getData();
            if (data2 != null) {
                onRouteSearchListener.onBusRouteSearched((BusRouteResult) message.getData().getParcelable("result"), data2.getInt("errorCode"));
                return;
            }
            return;
        }
        if (message.what == 101) {
            Bundle data3 = message.getData();
            if (data3 != null) {
                onRouteSearchListener.onDriveRouteSearched((DriveRouteResult) message.getData().getParcelable("result"), data3.getInt("errorCode"));
                return;
            }
            return;
        }
        if (message.what == 102) {
            Bundle data4 = message.getData();
            if (data4 != null) {
                onRouteSearchListener.onWalkRouteSearched((WalkRouteResult) message.getData().getParcelable("result"), data4.getInt("errorCode"));
                return;
            }
            return;
        }
        if (message.what == 103) {
            Bundle data5 = message.getData();
            if (data5 != null) {
                onRouteSearchListener.onRideRouteSearched((RideRouteResult) message.getData().getParcelable("result"), data5.getInt("errorCode"));
                return;
            }
            return;
        }
        if (message.what != 104 || (data = message.getData()) == null) {
            return;
        }
        onRouteSearchListener.onRideRouteSearched((RideRouteResult) message.getData().getParcelable("result"), data.getInt("errorCode"));
    }

    private static void l(Message message) {
        Bundle data;
        RouteSearchV2.OnRouteSearchListener onRouteSearchListener = (RouteSearchV2.OnRouteSearchListener) message.obj;
        if (onRouteSearchListener == null || message.what != 101 || (data = message.getData()) == null) {
            return;
        }
        onRouteSearchListener.onDriveRouteSearched((DriveRouteResultV2) message.getData().getParcelable("result"), data.getInt("errorCode"));
    }

    private static void m(Message message) {
        Bundle data;
        RouteSearch.OnTruckRouteSearchListener onTruckRouteSearchListener = (RouteSearch.OnTruckRouteSearchListener) message.obj;
        if (onTruckRouteSearchListener == null || message.what != 104 || (data = message.getData()) == null) {
            return;
        }
        onTruckRouteSearchListener.onTruckRouteSearched((TruckRouteRestult) message.getData().getParcelable("result"), data.getInt("errorCode"));
    }

    private static void n(Message message) {
        Bundle data;
        RouteSearch.OnRoutePlanSearchListener onRoutePlanSearchListener = (RouteSearch.OnRoutePlanSearchListener) message.obj;
        if (onRoutePlanSearchListener == null || message.what != 105 || (data = message.getData()) == null) {
            return;
        }
        int i2 = data.getInt("errorCode");
        DriveRoutePlanResult driveRoutePlanResult = (DriveRoutePlanResult) message.getData().getParcelable("result");
        if (onRoutePlanSearchListener != null) {
            onRoutePlanSearchListener.onDriveRoutePlanSearched(driveRoutePlanResult, i2);
        }
    }

    private static void o(Message message) {
        c cVar;
        if (message.what == 700) {
            d dVar = (d) message.obj;
            if (dVar == null) {
                return;
            }
            dVar.b.onCloudSearched(dVar.f1044a, message.arg2);
            return;
        }
        if (message.what != 701 || (cVar = (c) message.obj) == null) {
            return;
        }
        cVar.b.onCloudItemDetailSearched(cVar.f1043a, message.arg2);
    }

    private static void p(Message message) {
        k kVar;
        WeatherSearch.OnWeatherSearchListener onWeatherSearchListener;
        Bundle data;
        WeatherSearch.OnWeatherSearchListener onWeatherSearchListener2;
        Bundle data2;
        if (message.what == 1301) {
            l lVar = (l) message.obj;
            if (lVar == null || (onWeatherSearchListener2 = lVar.b) == null || (data2 = message.getData()) == null) {
                return;
            }
            onWeatherSearchListener2.onWeatherLiveSearched(lVar.f1052a, data2.getInt("errorCode"));
            return;
        }
        if (message.what != 1302 || (kVar = (k) message.obj) == null || (onWeatherSearchListener = kVar.b) == null || (data = message.getData()) == null) {
            return;
        }
        onWeatherSearchListener.onWeatherForecastSearched(kVar.f1051a, data.getInt("errorCode"));
    }

    private static void q(Message message) {
        RoutePOISearch.OnRoutePOISearchListener onRoutePOISearchListener;
        Bundle data;
        j jVar = (j) message.obj;
        if (jVar == null || (onRoutePOISearchListener = jVar.b) == null || (data = message.getData()) == null) {
            return;
        }
        onRoutePOISearchListener.onRoutePoiSearched(jVar.f1050a, data.getInt("errorCode"));
    }

    private static void r(Message message) {
        Bundle data;
        DistanceSearch.OnDistanceSearchListener onDistanceSearchListener = (DistanceSearch.OnDistanceSearchListener) message.obj;
        if (onDistanceSearchListener == null || message.what != 400 || (data = message.getData()) == null) {
            return;
        }
        onDistanceSearchListener.onDistanceSearched((DistanceResult) message.getData().getParcelable("result"), data.getInt("errorCode"));
    }
}
