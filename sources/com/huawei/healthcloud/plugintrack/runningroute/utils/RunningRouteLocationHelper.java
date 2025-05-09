package com.huawei.healthcloud.plugintrack.runningroute.utils;

import android.app.Activity;
import android.content.Context;
import com.amap.api.services.core.LatLonPoint;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.motiontrack.api.LocationApi;
import com.huawei.healthcloud.plugintrack.golf.bean.GolfGetLocation;
import com.huawei.ui.commonui.model.ICityLatLonCallBack;
import defpackage.emc;
import defpackage.emq;
import defpackage.gza;
import defpackage.hjd;
import defpackage.npq;
import defpackage.npv;
import health.compact.a.Utils;
import health.compact.a.util.LogUtil;

/* loaded from: classes4.dex */
public class RunningRouteLocationHelper implements LocationApi {
    public static final int ERROR_CODE_GOOGLE_LOCATION = -1;
    public static final int ERROR_CODE_SDK_LOCATION = -2;
    private static final String TAG = "Sport_RunningPathLocationHelper";
    private gza mGeoLocationManager = gza.b();
    private boolean mIsLocationSuccess;

    public boolean isLocationFailed(int i) {
        return i == -1 || i == -2;
    }

    @Override // com.huawei.health.motiontrack.api.LocationApi
    public void getLocation(Context context, final ICityLatLonCallBack iCityLatLonCallBack, final UiCallback uiCallback) {
        this.mIsLocationSuccess = false;
        long currentTimeMillis = System.currentTimeMillis();
        hjd a2 = RunningRouteUtils.a(new GolfGetLocation.GetLocationCallback() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteLocationHelper.1
            @Override // com.huawei.healthcloud.plugintrack.golf.bean.GolfGetLocation.GetLocationCallback
            public void onSuccess(hjd hjdVar) {
                if (RunningRouteLocationHelper.this.mIsLocationSuccess) {
                    return;
                }
                RunningRouteLocationHelper.this.mIsLocationSuccess = true;
                if (hjdVar != null) {
                    RunningRouteLocationHelper.this.getCityCenterPoint(hjdVar, iCityLatLonCallBack, uiCallback);
                } else {
                    LogUtil.d(RunningRouteLocationHelper.TAG, "get real location onSuccess null");
                    uiCallback.onFailure(-1, BaseApplication.e().getResources().getString(R.string._2130840075_res_0x7f020a0b));
                }
            }

            @Override // com.huawei.healthcloud.plugintrack.golf.bean.GolfGetLocation.GetLocationCallback
            public void onFailure(int i, String str) {
                if (RunningRouteLocationHelper.this.mIsLocationSuccess) {
                    return;
                }
                LogUtil.d(RunningRouteLocationHelper.TAG, "get real location null");
                uiCallback.onFailure(-1, BaseApplication.e().getResources().getString(R.string._2130840075_res_0x7f020a0b));
            }
        });
        if (this.mIsLocationSuccess || a2 == null || Math.abs(a2.b) <= 1.0E-6d || Math.abs(a2.d) <= 1.0E-6d) {
            return;
        }
        this.mIsLocationSuccess = true;
        LogUtil.d(TAG, "get real location cost:", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        getCityCenterPoint(a2, iCityLatLonCallBack, uiCallback);
    }

    @Override // com.huawei.health.motiontrack.api.LocationApi
    public npq getUserLocation(Activity activity) {
        hjd a2 = RunningRouteUtils.a();
        if (a2 != null) {
            return new npq(a2.b, a2.d);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getCityCenterPoint(hjd hjdVar, final ICityLatLonCallBack iCityLatLonCallBack, UiCallback uiCallback) {
        final long currentTimeMillis = System.currentTimeMillis();
        LatLonPoint latLonPoint = new LatLonPoint(hjdVar.b, hjdVar.d);
        if (!Utils.o()) {
            this.mGeoLocationManager.b(latLonPoint, new ICityLatLonCallBack() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteLocationHelper.2
                @Override // com.huawei.ui.commonui.model.ICityLatLonCallBack
                public void onPointBack(npv npvVar) {
                    LogUtil.d(RunningRouteLocationHelper.TAG, "getCityCenterPoint cost:", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    ICityLatLonCallBack iCityLatLonCallBack2 = iCityLatLonCallBack;
                    if (iCityLatLonCallBack2 != null) {
                        iCityLatLonCallBack2.onPointBack(npvVar);
                    }
                }
            });
        } else if (iCityLatLonCallBack != null) {
            iCityLatLonCallBack.onPointBack(null);
        }
        emq b = new emq.d().e(hjdVar.b).b(hjdVar.d).b();
        LogUtil.d(TAG, "GetCityInfoWithGpsReq: ", b);
        emc.d().getCityInfoWithGps(b, uiCallback);
    }
}
