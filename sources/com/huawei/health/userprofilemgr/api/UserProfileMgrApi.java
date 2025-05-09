package com.huawei.health.userprofilemgr.api;

import android.content.Context;
import android.graphics.Bitmap;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.health.userprofilemgr.model.HealthRecordCbk;
import com.huawei.health.userprofilemgr.model.RouteResultCallBack;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.RouteData;
import com.huawei.route.TrackInfoModel;
import com.huawei.up.callback.CommonCallback;
import com.huawei.up.model.UserInfomation;
import defpackage.gmt;
import defpackage.gmu;
import defpackage.gmx;
import defpackage.gnb;
import defpackage.gnc;
import java.util.List;

/* loaded from: classes.dex */
public interface UserProfileMgrApi {
    void checkUserInfo(Context context, IBaseResponseCallback iBaseResponseCallback);

    void clearRouteData();

    void deleteRoutes(List<Long> list, RouteResultCallBack<CloudCommonReponse> routeResultCallBack);

    Class<?> getActiveTargetService();

    void getActiveTargetStatus(IBaseResponseCallback iBaseResponseCallback);

    List<gmx> getEmergencyContact();

    gmu getEmergencyInfo();

    List<String> getHarmonyEmergencyContactsUri();

    Bitmap getHeadBitmap(Context context);

    Class<?> getHealthRecordsJsApi();

    UserInfomation getLocalUserInfo();

    gnb getPopOutWindowInfo(Context context, String str);

    RouteData getRouteData();

    UserInfomation getUserInfo();

    void getUserInfo(BaseResponseCallback<UserInfomation> baseResponseCallback);

    void gotoH5ActiveTarget(Context context);

    void gotoH5HealthRecord(Context context, String str, String str2);

    boolean hasEmergencyInfoProvider();

    void hideTargetRed();

    void init(gnc gncVar);

    boolean isActiveTargetSwitchOpen();

    boolean isTargetRedNeedShow();

    boolean isUserProfileMgrExist(Context context);

    void notifyCallback();

    void queryHealthRecords(int i, int i2, HealthRecordCbk<List<gmt>> healthRecordCbk);

    void registerContactChangeObserver();

    void registerModifyCallback(IBaseResponseCallback iBaseResponseCallback);

    boolean saveAppEmergencyContacts(gmx gmxVar);

    void setPopOutWindowInfo(Context context, String str);

    void setRouteData(RouteData routeData);

    void setTrackInfoModel(TrackInfoModel trackInfoModel);

    void setUserInfo(UserInfomation userInfomation, ICloudOperationResult<Boolean> iCloudOperationResult);

    void sync(CommonCallback commonCallback);

    void unRegisterContactChangeObserver();

    void unRegisterModifyCallback(IBaseResponseCallback iBaseResponseCallback);

    boolean waitInit();
}
