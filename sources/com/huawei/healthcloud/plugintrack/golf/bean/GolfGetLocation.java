package com.huawei.healthcloud.plugintrack.golf.bean;

import android.location.Location;
import android.os.Handler;
import android.os.Message;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwlocationmgr.model.ILoactionCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gwp;
import defpackage.hjd;
import defpackage.hjr;
import defpackage.ktg;
import health.compact.a.LogAnonymous;
import health.compact.a.Utils;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class GolfGetLocation {
    private static final Object LOCK = new Object();
    private static final int MSG_OVER_TIME = 101;
    private static final int OVER_TIME = 10000;
    private static final String TAG = "Track_GolfH5LogicManager";
    private static volatile GolfGetLocation sGolfLocation;
    private AMapLocationListener mAMapLocationListener;
    private GetLocationCallback mCallback;
    private Handler mHandler;
    private final boolean mIsOversea;
    private ILoactionCallback mLocationCallback;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption = null;

    /* loaded from: classes4.dex */
    public interface GetLocationCallback {
        void onFailure(int i, String str);

        void onSuccess(hjd hjdVar);
    }

    private GolfGetLocation() {
        boolean o = Utils.o();
        this.mIsOversea = o;
        this.mHandler = new Handler() { // from class: com.huawei.healthcloud.plugintrack.golf.bean.GolfGetLocation.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message == null) {
                    LogUtil.b(GolfGetLocation.TAG, "msg == null");
                    return;
                }
                super.handleMessage(message);
                if (101 == message.what) {
                    LogUtil.h(GolfGetLocation.TAG, "get location over time, unRegister");
                    GolfGetLocation.this.unRegisterLocationListener();
                    GolfGetLocation.this.reportLastKnownLocation();
                }
            }
        };
        if (!o) {
            MapsInitializer.updatePrivacyShow(BaseApplication.e(), true, true);
            MapsInitializer.updatePrivacyAgree(BaseApplication.e(), true);
            this.mAMapLocationListener = new AMapLocationListener() { // from class: com.huawei.healthcloud.plugintrack.golf.bean.GolfGetLocation.2
                @Override // com.amap.api.location.AMapLocationListener
                public void onLocationChanged(AMapLocation aMapLocation) {
                    if (aMapLocation == null || GolfGetLocation.this.mCallback == null) {
                        return;
                    }
                    GolfGetLocation.this.mHandler.removeMessages(101);
                    if (GolfGetLocation.this.mLocationClient != null) {
                        GolfGetLocation.this.mLocationClient.stopLocation();
                    } else {
                        LogUtil.h(GolfGetLocation.TAG, "onLocationChanged: location client is null");
                    }
                    if (aMapLocation.getErrorCode() == 0) {
                        GolfGetLocation.this.mCallback.onSuccess(GolfGetLocation.this.aMapToHealth(aMapLocation));
                        return;
                    }
                    LogUtil.b(GolfGetLocation.TAG, "AmapError, location Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation.getErrorInfo());
                    GolfGetLocation.this.mCallback.onFailure(aMapLocation.getErrorCode(), aMapLocation.getErrorInfo());
                }
            };
            return;
        }
        this.mLocationCallback = new ILoactionCallback() { // from class: com.huawei.healthcloud.plugintrack.golf.bean.GolfGetLocation.3
            @Override // com.huawei.hwlocationmgr.model.ILoactionCallback
            public void dispatchLocationChanged(Location location) {
                if (location != null) {
                    GolfGetLocation.this.mHandler.removeMessages(101);
                    ktg.c().a(GolfGetLocation.TAG);
                    if (GolfGetLocation.this.mCallback != null) {
                        GolfGetLocation.this.mCallback.onSuccess(GolfGetLocation.this.locationToHealth(location));
                        return;
                    } else {
                        LogUtil.b(GolfGetLocation.TAG, " mCallback is null");
                        return;
                    }
                }
                LogUtil.b(GolfGetLocation.TAG, " dispatchLocationChanged null");
            }
        };
    }

    public static GolfGetLocation getInstance() {
        if (sGolfLocation == null) {
            synchronized (LOCK) {
                if (sGolfLocation == null) {
                    sGolfLocation = new GolfGetLocation();
                }
            }
        }
        return sGolfLocation;
    }

    public void getLocation(GetLocationCallback getLocationCallback) {
        LogUtil.a(TAG, "getLocation start");
        this.mCallback = getLocationCallback;
        if (this.mIsOversea) {
            ktg.c().e(this.mLocationCallback, TAG);
        } else {
            startAMapLocation();
        }
        this.mHandler.sendEmptyMessageDelayed(101, PreConnectManager.CONNECT_INTERNAL);
    }

    private void startAMapLocation() {
        try {
            this.mLocationClient = new AMapLocationClient(BaseApplication.e());
        } catch (Exception e) {
            LogUtil.h(TAG, "getLocation: ", LogAnonymous.b((Throwable) e));
        }
        if (this.mLocationClient == null) {
            LogUtil.h(TAG, "getLocation: location client is null");
            this.mCallback.onFailure(9, "");
            return;
        }
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        this.mLocationOption = aMapLocationClientOption;
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        this.mLocationOption.setOnceLocation(true);
        this.mLocationOption.setWifiScan(true);
        this.mLocationOption.setLocationCacheEnable(false);
        this.mLocationOption.setNeedAddress(false);
        this.mLocationClient.setLocationOption(this.mLocationOption);
        this.mLocationClient.setLocationListener(this.mAMapLocationListener);
        this.mLocationClient.startLocation();
        gwp.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public hjd aMapToHealth(AMapLocation aMapLocation) {
        return new hjd(aMapLocation.getLatitude(), aMapLocation.getLongitude());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public hjd locationToHealth(Location location) {
        if (location == null) {
            return null;
        }
        return hjr.c(new hjd(location.getLatitude(), location.getLongitude()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportLastKnownLocation() {
        if (this.mCallback == null) {
            LogUtil.b(TAG, "mCallback is null int reportLastKnownLocation");
            return;
        }
        Location lastKnowLocation = getLastKnowLocation();
        if (lastKnowLocation == null) {
            LogUtil.b(TAG, "reportLastKnownLocation: location is null");
            this.mCallback.onFailure(9, "");
        } else {
            this.mCallback.onSuccess(locationToHealth(lastKnowLocation));
        }
    }

    private Location getLastKnowLocation() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(HAWebViewInterface.NETWORK);
        arrayList.add(GeocodeSearch.GPS);
        return ktg.c().bQQ_(arrayList);
    }

    public void unRegisterLocationListener() {
        AMapLocationClient aMapLocationClient = this.mLocationClient;
        if (aMapLocationClient != null) {
            aMapLocationClient.stopLocation();
            this.mLocationClient.unRegisterLocationListener(this.mAMapLocationListener);
            this.mLocationClient.onDestroy();
            this.mLocationClient = null;
        }
        if (this.mIsOversea) {
            ktg.c().a(TAG);
        }
    }
}
