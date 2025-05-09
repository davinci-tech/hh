package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.district.DistrictSearchQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.callback.HwLocationCallback;
import com.huawei.hwlocationmgr.model.ILoactionCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import health.compact.a.Utils;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Semaphore;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jrc {

    /* renamed from: a, reason: collision with root package name */
    private static jrc f14032a;
    private static final Object d = new Object();
    private Context e;
    private Location j;
    private ktg m;
    private long f = 0;
    private boolean h = false;
    private Semaphore l = new Semaphore(1);
    private Handler o = null;
    private HandlerThread b = null;
    private HwLocationCallback c = null;
    private ILoactionCallback i = new ILoactionCallback() { // from class: jrc.3
        @Override // com.huawei.hwlocationmgr.model.ILoactionCallback
        public void dispatchLocationChanged(Location location) {
            LogUtil.a("HwLocationUtil", "checkNewVersion location dispatchLocationChanged");
            if (jrc.this.o != null) {
                jrc.this.o.removeMessages(40);
            }
            if (jrc.this.m != null) {
                jrc.this.c();
            }
            jrc.this.j = location;
            jrc.this.b();
        }
    };
    private ServiceConnection g = new ServiceConnection() { // from class: jrc.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtil.a("HwLocationUtil", "getFromLocation onServiceConnected");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            jrc.this.h = false;
            LogUtil.a("HwLocationUtil", "getFromLocation onServiceDisconnected");
        }
    };

    private jrc(Context context) {
        this.e = context;
        a();
    }

    public void a(String str, HwLocationCallback hwLocationCallback) {
        try {
            this.l.acquire();
        } catch (InterruptedException e2) {
            LogUtil.b("HwLocationUtil", "initLocation Exception", ExceptionUtils.d(e2));
        }
        this.c = hwLocationCallback;
        if (TextUtils.isEmpty(str)) {
            b("initLocation deviceId is null");
            return;
        }
        if (Math.abs(System.currentTimeMillis() - this.f) < 1800000) {
            b("initLocation is DURING_BINDING_TIME");
            return;
        }
        if (!Utils.o() && d() && kxz.d(str)) {
            LogUtil.a("HwLocationUtil", "can start to initLocation");
            if (this.m == null) {
                this.m = ktg.c();
            }
            ArrayList arrayList = new ArrayList(0);
            arrayList.add(HAWebViewInterface.NETWORK);
            arrayList.add(GeocodeSearch.GPS);
            Location bQQ_ = this.m.bQQ_(arrayList);
            if (bQQ_ == null) {
                bQQ_ = this.m.bQR_();
            }
            arrayList.clear();
            if (bQQ_ != null) {
                LogUtil.a("HwLocationUtil", "get location != null");
                this.j = bQQ_;
                b();
                return;
            } else {
                if (!TextUtils.isEmpty(kxz.h(this.e)) && !kxz.j()) {
                    b("initLocation is existent");
                    return;
                }
                this.m.e(this.i, "wearCheckVersionLocation");
                Handler handler = this.o;
                if (handler != null) {
                    handler.sendEmptyMessageDelayed(40, PreConnectManager.CONNECT_INTERNAL);
                    return;
                }
                return;
            }
        }
        b("checkNewVersion no initLocation");
    }

    private boolean d() {
        Object systemService = BaseApplication.getContext().getSystemService("location");
        LocationManager locationManager = systemService instanceof LocationManager ? (LocationManager) systemService : null;
        if (locationManager != null) {
            return locationManager.isProviderEnabled(GeocodeSearch.GPS) || locationManager.isProviderEnabled(HAWebViewInterface.NETWORK);
        }
        LogUtil.a("HwLocationUtil", "checkLocationServiceStatus() if (locationManager == null)");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("HwLocationUtil", "processLocationForQuery() ENTER");
        kxz.s(this.e);
        ThreadPoolManager.d().execute(new Runnable() { // from class: jrc.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    LogUtil.a("HwLocationUtil", "dispatchLocationChanged location result");
                    double latitude = jrc.this.j.getLatitude();
                    double longitude = jrc.this.j.getLongitude();
                    double doubleValue = new BigDecimal(latitude).setScale(3, 4).doubleValue();
                    double doubleValue2 = new BigDecimal(longitude).setScale(3, 4).doubleValue();
                    if (bky.g()) {
                        jrc.this.o.sendEmptyMessageDelayed(41, 3000L);
                    }
                    if (Build.VERSION.SDK_INT >= 30) {
                        jrc.this.e(doubleValue, doubleValue2);
                        return;
                    }
                    List<Address> fromLocation = new Geocoder(BaseApplication.getContext(), Locale.getDefault()).getFromLocation(doubleValue, doubleValue2, 1);
                    LogUtil.a("HwLocationUtil", "processLocationForQuery getFromLocation end");
                    jrc.this.f();
                    if (jrc.this.o != null) {
                        jrc.this.o.removeMessages(41);
                    }
                    if (fromLocation != null && fromLocation.size() > 0) {
                        if (fromLocation.get(0) instanceof Address) {
                            Address address = fromLocation.get(0);
                            if (address.getSubLocality() == null) {
                                jrc.this.b("location result getSubLocality is null");
                                return;
                            }
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("country", address.getCountryCode());
                            jSONObject.put(DistrictSearchQuery.KEYWORDS_PROVINCE, address.getAdminArea());
                            jSONObject.put(DistrictSearchQuery.KEYWORDS_CITY, address.getLocality());
                            jSONObject.put("area", address.getSubLocality());
                            kxz.x(jrc.this.e, address.getCountryCode() + "|" + address.getAdminArea() + "|" + address.getLocality() + "|" + address.getSubLocality());
                            kxz.y(jrc.this.e, jSONObject.toString());
                            jrc.this.f = System.currentTimeMillis();
                            jrc.this.b("location request success");
                            return;
                        }
                        return;
                    }
                    jrc.this.b("location result is null");
                } catch (IOException | IllegalArgumentException | IllegalStateException | JSONException e2) {
                    jrc.this.b("processLocationForQuery Exception " + ExceptionUtils.d(e2));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(double d2, double d3) {
        try {
            List<Address> fromLocation = new Geocoder(BaseApplication.getContext(), Locale.getDefault()).getFromLocation(d2, d3, 1);
            LogUtil.a("HwLocationUtil", "getFromLocationForRVersion getFromLocation end");
            f();
            Handler handler = this.o;
            if (handler != null) {
                handler.removeMessages(41);
            }
            if (fromLocation != null && fromLocation.size() > 0) {
                if (fromLocation.get(0) instanceof Address) {
                    Address address = fromLocation.get(0);
                    if (address.getSubLocality() == null) {
                        d(d2, d3);
                        return;
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("country", address.getCountryCode());
                    jSONObject.put(DistrictSearchQuery.KEYWORDS_PROVINCE, address.getAdminArea());
                    jSONObject.put(DistrictSearchQuery.KEYWORDS_CITY, address.getLocality());
                    jSONObject.put("area", address.getSubLocality());
                    kxz.x(this.e, address.getCountryCode() + "|" + address.getAdminArea() + "|" + address.getLocality() + "|" + address.getSubLocality());
                    kxz.y(this.e, jSONObject.toString());
                    this.f = System.currentTimeMillis();
                    b("location request success");
                    return;
                }
                d(d2, d3);
                return;
            }
            d(d2, d3);
        } catch (IOException | IllegalArgumentException | IllegalStateException | JSONException e2) {
            LogUtil.h("HwLocationUtil", "getFromLocationForRVersion exception :", ExceptionUtils.d(e2));
            d(d2, d3);
        }
    }

    private void d(double d2, double d3) {
        LogUtil.a("HwLocationUtil", "requestMapsLocation location result");
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("requestMapsLocation", String.valueOf(Build.VERSION.SDK_INT));
        OpAnalyticsUtil.getInstance().setEvent(OperationKey.PSI_GEOCODE_SEARCH_80070008.value(), linkedHashMap);
        MapsInitializer.updatePrivacyShow(BaseApplication.getContext(), true, true);
        MapsInitializer.updatePrivacyAgree(BaseApplication.getContext(), true);
        RegeocodeQuery regeocodeQuery = new RegeocodeQuery(new LatLonPoint(d2, d3), 200.0f, GeocodeSearch.AMAP);
        try {
            GeocodeSearch geocodeSearch = new GeocodeSearch(BaseApplication.getContext());
            geocodeSearch.setOnGeocodeSearchListener(new e());
            geocodeSearch.getFromLocationAsyn(regeocodeQuery);
        } catch (AMapException e2) {
            b("requestMapsLocation AMapException: " + ExceptionUtils.d(e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        HwLocationCallback hwLocationCallback = this.c;
        if (hwLocationCallback != null) {
            hwLocationCallback.onResult(str);
        }
        this.l.release();
    }

    public void c() {
        LogUtil.a("HwLocationUtil", "stopLocationPosition() ENTER");
        ktg ktgVar = this.m;
        if (ktgVar != null) {
            ktgVar.a("wearCheckVersionLocation");
        }
    }

    private void a() {
        HandlerThread handlerThread = new HandlerThread("HwLocationUtil");
        this.b = handlerThread;
        handlerThread.start();
        this.o = new d(this.b.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.g == null || !this.h) {
            return;
        }
        try {
            BaseApplication.getContext().unbindService(this.g);
            this.h = false;
            LogUtil.a("HwLocationUtil", "unBindLBSService");
        } catch (IllegalArgumentException unused) {
            LogUtil.b("HwLocationUtil", "unBindLBSService exception");
        }
    }

    class d extends Handler {
        d(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 40) {
                jrc.this.b("location time out");
                jrc.this.c();
                return;
            }
            if (i == 41) {
                Intent intent = new Intent();
                intent.setClassName("com.huawei.lbs", "com.huawei.lbs.HwLBSService");
                try {
                    jrc.this.h = true;
                    boolean bindService = BaseApplication.getContext().bindService(intent, jrc.this.g, 1);
                    if (!bindService) {
                        jrc.this.h = false;
                    }
                    LogUtil.a("HwLocationUtil", "getFromLocation bindLBSService result is ", Boolean.valueOf(bindService));
                } catch (SecurityException e) {
                    LogUtil.h("HwLocationUtil", "start HwLBSService service SecurityException ,e = ", ExceptionUtils.d(e));
                }
            }
            LogUtil.h("HwLocationUtil", "handleMessage error");
        }
    }

    public static jrc e() {
        jrc jrcVar;
        synchronized (d) {
            if (f14032a == null) {
                f14032a = new jrc(BaseApplication.getContext());
            }
            jrcVar = f14032a;
        }
        return jrcVar;
    }

    class e implements GeocodeSearch.OnGeocodeSearchListener {
        private e() {
        }

        @Override // com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener
        public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
            LogUtil.a("HwLocationUtil", "onRegeocodeSearched code ", Integer.valueOf(i));
            if (regeocodeResult == null || regeocodeResult.getRegeocodeAddress() == null) {
                jrc.this.b("requestMapsLocation regeocodeResult == null");
                return;
            }
            RegeocodeAddress regeocodeAddress = regeocodeResult.getRegeocodeAddress();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("country", regeocodeAddress.getCountry());
                jSONObject.put(DistrictSearchQuery.KEYWORDS_PROVINCE, regeocodeAddress.getProvince());
                jSONObject.put(DistrictSearchQuery.KEYWORDS_CITY, regeocodeAddress.getCity());
                jSONObject.put("area", regeocodeAddress.getDistrict());
                kxz.x(jrc.this.e, regeocodeAddress.getCountry() + "|" + regeocodeAddress.getProvince() + "|" + regeocodeAddress.getCity() + "|" + regeocodeAddress.getDistrict());
                kxz.y(BaseApplication.getContext(), jSONObject.toString());
                jrc.this.f = System.currentTimeMillis();
                jrc.this.b("location request success");
            } catch (JSONException e) {
                jrc.this.b("requestMapsLocation JSONException" + ExceptionUtils.d(e));
            }
        }

        @Override // com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener
        public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
            jrc.this.b("requestMapsLocation onGeocodeSearched code " + i);
        }
    }
}
