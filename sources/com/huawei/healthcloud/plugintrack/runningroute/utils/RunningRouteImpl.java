package com.huawei.healthcloud.plugintrack.runningroute.utils;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.core.app.ActivityCompat;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.motiontrack.api.ILocationChangeListener;
import com.huawei.health.motiontrack.api.RunningRouteApi;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Opers;
import defpackage.emb;
import defpackage.emc;
import defpackage.emh;
import defpackage.emi;
import defpackage.emk;
import defpackage.emm;
import defpackage.emn;
import defpackage.emo;
import defpackage.emp;
import defpackage.emq;
import defpackage.emr;
import defpackage.ems;
import defpackage.emt;
import defpackage.emu;
import defpackage.emv;
import defpackage.emw;
import defpackage.emx;
import defpackage.emy;
import defpackage.emz;
import defpackage.enb;
import defpackage.enc;
import defpackage.ene;
import defpackage.enf;
import defpackage.gtl;
import defpackage.gzk;
import defpackage.hjd;
import defpackage.nrv;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class RunningRouteImpl implements RunningRouteApi {
    private static final int LOCATION_UPDATE_TIME = 30000;
    private static final String TAG = "RunningRouteImpl";
    private ILocationChangeListener mLocationChangeListener;
    private Handler mUiHandler = new Handler(Looper.getMainLooper());
    private LocationListener mLocationListener = new LocationListener() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.1
        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            SharedPreferenceManager.e(BaseApplication.e(), "USER_LOCATION_LAT_LNG", "USER_LOCATION_LAT_LNG", nrv.a(new hjd(location.getLatitude(), location.getLongitude())), (StorageParams) null);
            gtl gtlVar = new gtl(BaseApplication.e(), 0);
            if (RunningRouteImpl.this.mLocationChangeListener != null) {
                RunningRouteImpl.this.mLocationChangeListener.onLocationChange(gtlVar.aTB_(location));
            }
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
            LogUtil.a(RunningRouteImpl.TAG, "sLocationListener onProviderDisabled ", str);
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
            LogUtil.a(RunningRouteImpl.TAG, "sLocationListener onProviderEnabled ", str);
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
            LogUtil.a(RunningRouteImpl.TAG, "sLocationListener onStatusChanged ", Integer.valueOf(i));
        }
    };

    @Override // com.huawei.health.motiontrack.api.RunningRouteApi
    public void getCityInfoList(final emk emkVar, final UiCallback<emt> uiCallback) {
        health.compact.a.util.LogUtil.d(TAG, "queryCityInfoList: Request Received, processing.....");
        gzk.e(emkVar, new ResultCallback<emt>() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.9
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(final emt emtVar) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.9.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Map hashMap;
                        try {
                            hashMap = (Map) nrv.c(SharedPreferenceManager.b(BaseApplication.e(), "ROUTE_CITY_INFO_LIST", "ROUTE_CITY_INFO_LIST"), new TypeToken<Map<String, emt>>() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.9.3.5
                            }.getType());
                        } catch (JsonSyntaxException unused) {
                            health.compact.a.util.LogUtil.e(RunningRouteImpl.TAG, "JsonSyntaxException");
                            hashMap = new HashMap();
                        }
                        if (hashMap == null) {
                            hashMap = new HashMap();
                        }
                        hashMap.put(emkVar.c(), emtVar);
                        SharedPreferenceManager.e(BaseApplication.e(), "ROUTE_CITY_INFO_LIST", "ROUTE_CITY_INFO_LIST", nrv.a(hashMap), new StorageParams());
                    }
                });
                uiCallback.onSuccess(RunningRouteImpl.this.mUiHandler, emtVar);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                uiCallback.onFailure(RunningRouteImpl.this.mUiHandler, -1, th.getMessage());
            }
        });
    }

    @Override // com.huawei.health.motiontrack.api.RunningRouteApi
    public void getAllCityInfoList(emm emmVar, final UiCallback<emo> uiCallback) {
        health.compact.a.util.LogUtil.d(TAG, "queryAllCityInfoList: Request Received, processing.....");
        gzk.d(emmVar, new ResultCallback<emo>() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.11
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(emo emoVar) {
                uiCallback.onSuccess(RunningRouteImpl.this.mUiHandler, emoVar);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                uiCallback.onFailure(RunningRouteImpl.this.mUiHandler, -1, th.getMessage());
            }
        });
    }

    @Override // com.huawei.health.motiontrack.api.RunningRouteApi
    public void getCityInfoWithGps(emq emqVar, final UiCallback<emp> uiCallback) {
        health.compact.a.util.LogUtil.d(TAG, "queryCityInfoWithGps: Request Received, processing.....");
        gzk.e(emqVar, new ResultCallback<emp>() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.13
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(emp empVar) {
                uiCallback.onSuccess(RunningRouteImpl.this.mUiHandler, empVar);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                uiCallback.onFailure(RunningRouteImpl.this.mUiHandler, -1, th.getMessage());
            }
        });
    }

    @Override // com.huawei.health.motiontrack.api.RunningRouteApi
    public void feedbackHotPathInfo(emn emnVar, final UiCallback<CloudCommonReponse> uiCallback) {
        health.compact.a.util.LogUtil.d(TAG, "feedbackHotPathInfo: Request Received, processing.....");
        gzk.c(emnVar, new ResultCallback<CloudCommonReponse>() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.12
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                uiCallback.onSuccess(RunningRouteImpl.this.mUiHandler, cloudCommonReponse);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                uiCallback.onFailure(RunningRouteImpl.this.mUiHandler, -1, th.getMessage());
            }
        });
    }

    @Override // com.huawei.health.motiontrack.api.RunningRouteApi
    public void getFeedbackInfos(emr emrVar, final UiCallback<ems> uiCallback) {
        health.compact.a.util.LogUtil.d(TAG, "getFeedbackInfos: Request Received, processing.....");
        gzk.e(emrVar, new ResultCallback<ems>() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.15
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(ems emsVar) {
                uiCallback.onSuccess(RunningRouteImpl.this.mUiHandler, emsVar);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                uiCallback.onFailure(RunningRouteImpl.this.mUiHandler, -1, th.getMessage());
            }
        });
    }

    @Override // com.huawei.health.motiontrack.api.RunningRouteApi
    public void deleteFeedbackInfo(emi emiVar, final UiCallback<CloudCommonReponse> uiCallback) {
        health.compact.a.util.LogUtil.d(TAG, "deleteFeedbackInfo: Request Received, processing.....");
        gzk.d(emiVar, new ResultCallback<CloudCommonReponse>() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.14
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                uiCallback.onSuccess(RunningRouteImpl.this.mUiHandler, cloudCommonReponse);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                uiCallback.onFailure(RunningRouteImpl.this.mUiHandler, -1, th.getMessage());
            }
        });
    }

    @Override // com.huawei.health.motiontrack.api.RunningRouteApi
    public void deleteFeedbackByPathId(emh emhVar, final UiCallback<CloudCommonReponse> uiCallback) {
        health.compact.a.util.LogUtil.d(TAG, "deleteFeedbackByPathId: Request Received, processing.....");
        gzk.d(emhVar, new ResultCallback<CloudCommonReponse>() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.20
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                uiCallback.onSuccess(RunningRouteImpl.this.mUiHandler, cloudCommonReponse);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                uiCallback.onFailure(RunningRouteImpl.this.mUiHandler, -1, th.getMessage());
            }
        });
    }

    @Override // com.huawei.health.motiontrack.api.RunningRouteApi
    public void addHotPathParticipateInfo(emb embVar, final UiCallback<CloudCommonReponse> uiCallback) {
        health.compact.a.util.LogUtil.d(TAG, "addHotPathParticipateInfo: Request Received, processing.....");
        gzk.a(embVar, new ResultCallback<CloudCommonReponse>() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.18
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                uiCallback.onSuccess(RunningRouteImpl.this.mUiHandler, cloudCommonReponse);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                uiCallback.onFailure(RunningRouteImpl.this.mUiHandler, -1, th.getMessage());
            }
        });
    }

    @Override // com.huawei.health.motiontrack.api.RunningRouteApi
    public void getHotPathParticipateInfos(emx emxVar, final UiCallback<enb> uiCallback) {
        health.compact.a.util.LogUtil.d(TAG, "getHotPathParticipateInfos: Request Received, processing.....");
        gzk.b(emxVar, new ResultCallback<enb>() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.3
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(enb enbVar) {
                uiCallback.onSuccess(RunningRouteImpl.this.mUiHandler, enbVar);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                uiCallback.onFailure(RunningRouteImpl.this.mUiHandler, -1, th.getMessage());
            }
        });
    }

    @Override // com.huawei.health.motiontrack.api.RunningRouteApi
    public void getHotPathParticipateInfo(emy emyVar, final UiCallback<emw> uiCallback) {
        health.compact.a.util.LogUtil.d(TAG, "GetHotPathParticipateInfoRsp: request");
        RunningRouteUtils.a(BaseApplication.e()).e(emyVar, new ResultCallback<emw>() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.2
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(emw emwVar) {
                uiCallback.onSuccess(RunningRouteImpl.this.mUiHandler, emwVar);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                uiCallback.onFailure(RunningRouteImpl.this.mUiHandler, -1, th.getMessage());
            }
        });
    }

    @Override // com.huawei.health.motiontrack.api.RunningRouteApi
    public void getHotPaths(ene eneVar, final UiCallback<emz> uiCallback) {
        health.compact.a.util.LogUtil.d(TAG, "getHotPaths: Request Received, processing.....");
        gzk.c(eneVar, new ResultCallback<emz>() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.4
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(emz emzVar) {
                health.compact.a.util.LogUtil.d(RunningRouteImpl.TAG, TrackConstants$Opers.RESPONSE);
                if (emzVar != null) {
                    for (enf enfVar : emzVar.e()) {
                        if (enfVar != null) {
                            health.compact.a.util.LogUtil.d(RunningRouteImpl.TAG, "operationInfo: ", enfVar.toString());
                        }
                    }
                }
                RunningRouteImpl.this.saveHotPathOperationInfos(emzVar);
                uiCallback.onSuccess(RunningRouteImpl.this.mUiHandler, emzVar);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                uiCallback.onFailure(RunningRouteImpl.this.mUiHandler, -1, th.getMessage());
            }
        });
    }

    @Override // com.huawei.health.motiontrack.api.RunningRouteApi
    public void getHotPathDetail(emu emuVar, final UiCallback<emv> uiCallback) {
        health.compact.a.util.LogUtil.d(TAG, "getHotPathDetail: Request Received, processing.....");
        gzk.b(emuVar, new ResultCallback<emv>() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.5
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(emv emvVar) {
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder("response: ");
                sb.append(emvVar == null ? Constants.NULL : emvVar.toString());
                objArr[0] = sb.toString();
                health.compact.a.util.LogUtil.d(RunningRouteImpl.TAG, objArr);
                uiCallback.onSuccess(RunningRouteImpl.this.mUiHandler, emvVar);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                uiCallback.onFailure(RunningRouteImpl.this.mUiHandler, -1, th.getMessage());
            }
        });
    }

    @Override // com.huawei.health.motiontrack.api.RunningRouteApi
    public void getHotPathDetail(final String str, final UiCallback<enc> uiCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.7
            @Override // java.lang.Runnable
            public void run() {
                RunningRouteImpl.this.getHotPathDetailFromSpOrCloud(str, uiCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveHotPathOperationInfos(final emz emzVar) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.8
            @Override // java.lang.Runnable
            public void run() {
                String b = SharedPreferenceManager.b(BaseApplication.e(), "ROUTE_HOT_PATHS_LIST", "ROUTE_HOT_PATHS_LIST");
                Map hashMap = new HashMap();
                if (b != null) {
                    try {
                        try {
                            hashMap = (Map) nrv.c(b, new TypeToken<Map<String, enf>>() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.8.5
                            }.getType());
                        } catch (JsonSyntaxException unused) {
                            health.compact.a.util.LogUtil.e(RunningRouteImpl.TAG, "JsonSyntaxException");
                            hashMap = new HashMap();
                        }
                    } catch (IllegalStateException unused2) {
                        health.compact.a.util.LogUtil.d(RunningRouteImpl.TAG, "saveHotPathOperationInfos IllegalStateException");
                        return;
                    }
                }
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                if (emzVar.e() == null || b == null) {
                    return;
                }
                for (enf enfVar : emzVar.e()) {
                    if (enfVar.i() != null) {
                        hashMap.put(enfVar.i(), enfVar);
                    }
                }
                SharedPreferenceManager.e(BaseApplication.e(), "ROUTE_HOT_PATHS_LIST", "ROUTE_HOT_PATHS_LIST", nrv.a(hashMap), new StorageParams());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getHotPathDetailFromSpOrCloud(final String str, final UiCallback<enc> uiCallback) {
        final Map hashMap;
        try {
            hashMap = (Map) nrv.c(SharedPreferenceManager.b(BaseApplication.e(), "RUNNING_ROUTE_PATH_DETAIL_LIST", "RUNNING_ROUTE_PATH_DETAIL_LIST"), new TypeToken<Map<String, emv>>() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.6
            }.getType());
        } catch (JsonSyntaxException unused) {
            health.compact.a.util.LogUtil.e(TAG, "JsonSyntaxException");
            hashMap = new HashMap();
        }
        if (hashMap != null && hashMap.containsKey(str) && hashMap.get(str) != null) {
            uiCallback.onSuccess(this.mUiHandler, ((emv) hashMap.get(str)).b());
        }
        if (hashMap == null) {
            hashMap = new HashMap();
        }
        health.compact.a.util.LogUtil.e(TAG, "pathId: ", str, " request from cloud");
        emc.d().getHotPathDetail(new emu.b().d(str).b(), new UiCallback<emv>() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.10
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(final emv emvVar) {
                if (emvVar == null || emvVar.b() == null) {
                    uiCallback.onSuccess(RunningRouteImpl.this.mUiHandler, null);
                } else {
                    final enc b = emvVar.b();
                    ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteImpl.10.3
                        @Override // java.lang.Runnable
                        public void run() {
                            if (hashMap.containsKey(str) && nrv.a(hashMap.get(str)).equals(nrv.a(emvVar))) {
                                health.compact.a.util.LogUtil.d(RunningRouteImpl.TAG, "data in sp and cloud is consistent");
                                return;
                            }
                            hashMap.put(str, emvVar);
                            SharedPreferenceManager.e(BaseApplication.e(), "RUNNING_ROUTE_PATH_DETAIL_LIST", "RUNNING_ROUTE_PATH_DETAIL_LIST", nrv.a(hashMap), new StorageParams());
                            health.compact.a.util.LogUtil.d(RunningRouteImpl.TAG, "data in sp and cloud is not consistent");
                            uiCallback.onSuccess(RunningRouteImpl.this.mUiHandler, b);
                        }
                    });
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str2) {
                health.compact.a.util.LogUtil.e(RunningRouteImpl.TAG, "no match pathId in sp or cloud");
                if (!hashMap.containsKey(str) || hashMap.get(str) == null) {
                    uiCallback.onFailure(RunningRouteImpl.this.mUiHandler, i, str2);
                }
            }
        });
    }

    @Override // com.huawei.health.motiontrack.api.RunningRouteApi
    public void requestLocationUpdates(ILocationChangeListener iLocationChangeListener) {
        LocationManager locationManager = (LocationManager) BaseApplication.e().getSystemService("location");
        this.mLocationChangeListener = iLocationChangeListener;
        if (ActivityCompat.checkSelfPermission(BaseApplication.e(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(BaseApplication.e(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            try {
                locationManager.requestLocationUpdates(GeocodeSearch.GPS, OpAnalyticsConstants.H5_LOADING_DELAY, 0.0f, this.mLocationListener);
            } catch (IllegalArgumentException unused) {
                health.compact.a.util.LogUtil.e(TAG, "Exception: provider doesn't exist: GPS");
            }
            try {
                locationManager.requestLocationUpdates(HAWebViewInterface.NETWORK, OpAnalyticsConstants.H5_LOADING_DELAY, 0.0f, this.mLocationListener);
            } catch (IllegalArgumentException unused2) {
                health.compact.a.util.LogUtil.e(TAG, "Exception: provider doesn't exist: NETWORK");
            }
        }
    }

    @Override // com.huawei.health.motiontrack.api.RunningRouteApi
    public void removeLocationUpdates() {
        LocationManager locationManager = (LocationManager) BaseApplication.e().getSystemService("location");
        LocationListener locationListener = this.mLocationListener;
        if (locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }
    }
}
