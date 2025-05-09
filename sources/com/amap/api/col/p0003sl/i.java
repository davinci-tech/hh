package com.amap.api.col.p0003sl;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.district.DistrictSearchQuery;
import com.autonavi.aps.amapapi.utils.b;
import com.huawei.hianalytics.visual.autocollect.instrument.WebViewInstrumentation;
import com.huawei.maps.offlinedata.DeviceTypeConsts;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.utils.Constants;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class i {
    a c;
    private Context d;
    private WebView f;

    /* renamed from: a, reason: collision with root package name */
    Object f1165a = new Object();
    private AMapLocationClient e = null;
    private String g = "AMap.Geolocation.cbk";
    AMapLocationClientOption b = null;
    private volatile boolean h = false;

    public i(Context context, WebView webView) {
        this.f = null;
        this.c = null;
        this.d = context.getApplicationContext();
        this.f = webView;
        this.c = new a();
    }

    public final void a() {
        if (this.f == null || this.d == null || this.h) {
            return;
        }
        try {
            this.f.getSettings().setJavaScriptEnabled(true);
            this.f.addJavascriptInterface(this, "AMapAndroidLoc");
            if (!TextUtils.isEmpty(this.f.getUrl())) {
                this.f.reload();
            }
            if (this.e == null) {
                AMapLocationClient aMapLocationClient = new AMapLocationClient(this.d);
                this.e = aMapLocationClient;
                aMapLocationClient.setLocationListener(this.c);
            }
            this.h = true;
        } catch (Throwable unused) {
        }
    }

    public final void b() {
        synchronized (this.f1165a) {
            this.h = false;
            AMapLocationClient aMapLocationClient = this.e;
            if (aMapLocationClient != null) {
                aMapLocationClient.unRegisterLocationListener(this.c);
                this.e.stopLocation();
                this.e.onDestroy();
                this.e = null;
            }
            this.b = null;
        }
    }

    private void a(String str) {
        boolean z;
        if (this.b == null) {
            this.b = new AMapLocationClientOption();
        }
        long j = OpAnalyticsConstants.H5_LOADING_DELAY;
        int i = 5;
        try {
            JSONObject jSONObject = new JSONObject(str);
            j = jSONObject.optLong("to", OpAnalyticsConstants.H5_LOADING_DELAY);
            boolean z2 = jSONObject.optInt("useGPS", 1) == 1;
            try {
                r3 = jSONObject.optInt(DeviceTypeConsts.WATCH, 0) == 1;
                i = jSONObject.optInt("interval", 5);
                String optString = jSONObject.optString(ParamConstants.Param.CALLBACK, null);
                if (!TextUtils.isEmpty(optString)) {
                    this.g = optString;
                } else {
                    this.g = "AMap.Geolocation.cbk";
                }
            } catch (Throwable unused) {
            }
            boolean z3 = r3;
            r3 = z2;
            z = z3;
        } catch (Throwable unused2) {
            z = false;
        }
        try {
            this.b.setHttpTimeOut(j);
            if (r3) {
                this.b.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            } else {
                this.b.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
            }
            this.b.setOnceLocation(!z);
            if (z) {
                this.b.setInterval(i * 1000);
            }
        } catch (Throwable unused3) {
        }
    }

    @JavascriptInterface
    public final void getLocation(String str) {
        synchronized (this.f1165a) {
            if (this.h) {
                a(str);
                AMapLocationClient aMapLocationClient = this.e;
                if (aMapLocationClient != null) {
                    aMapLocationClient.setLocationOption(this.b);
                    this.e.stopLocation();
                    this.e.startLocation();
                }
            }
        }
    }

    @JavascriptInterface
    public final void stopLocation() {
        AMapLocationClient aMapLocationClient;
        if (this.h && (aMapLocationClient = this.e) != null) {
            aMapLocationClient.stopLocation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        try {
            WebView webView = this.f;
            if (webView != null) {
                webView.evaluateJavascript(Constants.JAVA_SCRIPT + this.g + Constants.LEFT_BRACKET + str + Constants.RIGHT_BRACKET, new ValueCallback<String>() { // from class: com.amap.api.col.3sl.i.1
                    @Override // android.webkit.ValueCallback
                    public final /* bridge */ /* synthetic */ void onReceiveValue(String str2) {
                    }
                });
            }
        } catch (Throwable th) {
            b.a(th, "H5LocationClient", "callbackJs()");
        }
    }

    /* renamed from: com.amap.api.col.3sl.i$2, reason: invalid class name */
    final class AnonymousClass2 implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f1167a;

        AnonymousClass2(String str) {
            this.f1167a = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            WebView webView = i.this.f;
            String str = Constants.JAVA_SCRIPT + i.this.g + Constants.LEFT_BRACKET + this.f1167a + Constants.RIGHT_BRACKET;
            webView.loadUrl(str);
            WebViewInstrumentation.loadUrl(webView, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(AMapLocation aMapLocation) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (aMapLocation == null) {
                jSONObject.put("errorCode", -1);
                jSONObject.put(MyLocationStyle.ERROR_INFO, "unknownError");
            } else if (aMapLocation.getErrorCode() == 0) {
                jSONObject.put("errorCode", 0);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("x", aMapLocation.getLongitude());
                jSONObject2.put("y", aMapLocation.getLatitude());
                jSONObject2.put("precision", aMapLocation.getAccuracy());
                jSONObject2.put("type", aMapLocation.getLocationType());
                jSONObject2.put("country", aMapLocation.getCountry());
                jSONObject2.put(DistrictSearchQuery.KEYWORDS_PROVINCE, aMapLocation.getProvince());
                jSONObject2.put(DistrictSearchQuery.KEYWORDS_CITY, aMapLocation.getCity());
                jSONObject2.put("cityCode", aMapLocation.getCityCode());
                jSONObject2.put(DistrictSearchQuery.KEYWORDS_DISTRICT, aMapLocation.getDistrict());
                jSONObject2.put("adCode", aMapLocation.getAdCode());
                jSONObject2.put("street", aMapLocation.getStreet());
                jSONObject2.put("streetNum", aMapLocation.getStreetNum());
                jSONObject2.put("floor", aMapLocation.getFloor());
                jSONObject2.put("address", aMapLocation.getAddress());
                jSONObject.put("result", jSONObject2);
            } else {
                jSONObject.put("errorCode", aMapLocation.getErrorCode());
                jSONObject.put(MyLocationStyle.ERROR_INFO, aMapLocation.getErrorInfo());
                jSONObject.put("locationDetail", aMapLocation.getLocationDetail());
            }
        } catch (Throwable unused) {
        }
        return jSONObject.toString();
    }

    final class a implements AMapLocationListener {
        a() {
        }

        @Override // com.amap.api.location.AMapLocationListener
        public final void onLocationChanged(AMapLocation aMapLocation) {
            if (i.this.h) {
                i.this.b(i.b(aMapLocation));
            }
        }
    }
}
