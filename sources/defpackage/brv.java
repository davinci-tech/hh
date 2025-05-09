package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import com.huawei.featureuserprofile.healthrecord.js.HealthRecordsJsApi;
import com.huawei.featureuserprofile.sos.manager.EmergencyInfoManager;
import com.huawei.featureuserprofile.target.cloud.ActiveTargetManager;
import com.huawei.featureuserprofile.target.js.ActiveTargetService;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.health.userprofilemgr.model.HealthRecordCbk;
import com.huawei.health.userprofilemgr.model.RouteResultCallBack;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.RouteData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.route.TrackInfoModel;
import com.huawei.up.callback.CommonCallback;
import com.huawei.up.model.UserInfomation;
import java.util.List;

@ApiDefine(uri = UserProfileMgrApi.class)
@Singleton
/* loaded from: classes3.dex */
public class brv implements UserProfileMgrApi {
    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void init(gnc gncVar) {
        bqi.c(arx.b()).a(gncVar);
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public UserInfomation getUserInfo() {
        return bqi.c(arx.b()).e();
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void getUserInfo(BaseResponseCallback<UserInfomation> baseResponseCallback) {
        bqi.c(arx.b()).d(baseResponseCallback);
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void setPopOutWindowInfo(Context context, String str) {
        bwc.b().e(context, str);
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public gnb getPopOutWindowInfo(Context context, String str) {
        return bwc.b().b(context, str);
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public UserInfomation getLocalUserInfo() {
        return bqi.d();
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void checkUserInfo(Context context, IBaseResponseCallback iBaseResponseCallback) {
        bwa.a(context, iBaseResponseCallback);
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public Bitmap getHeadBitmap(Context context) {
        return bqi.tA_(context);
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public boolean isUserProfileMgrExist(Context context) {
        return bqi.c(context) != null;
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public boolean waitInit() {
        return bqi.c(BaseApplication.getContext()).o();
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void setUserInfo(UserInfomation userInfomation, ICloudOperationResult<Boolean> iCloudOperationResult) {
        bqi.c(BaseApplication.getContext()).c(userInfomation, iCloudOperationResult);
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void sync(CommonCallback commonCallback) {
        bqi.c(BaseApplication.getContext()).a(commonCallback);
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void registerModifyCallback(IBaseResponseCallback iBaseResponseCallback) {
        bqi.c(BaseApplication.getContext()).b(iBaseResponseCallback);
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void unRegisterModifyCallback(IBaseResponseCallback iBaseResponseCallback) {
        bqi.c(BaseApplication.getContext()).e(iBaseResponseCallback);
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public gmu getEmergencyInfo() {
        return bqi.c(BaseApplication.getContext()).b();
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void registerContactChangeObserver() {
        EmergencyInfoManager.c().m();
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void unRegisterContactChangeObserver() {
        EmergencyInfoManager.c().k();
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public boolean hasEmergencyInfoProvider() {
        return EmergencyInfoManager.c().f();
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void notifyCallback() {
        bqi.c(BaseApplication.getContext()).g();
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public boolean saveAppEmergencyContacts(gmx gmxVar) {
        return EmergencyInfoManager.c().c(gmxVar);
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public List<String> getHarmonyEmergencyContactsUri() {
        return EmergencyInfoManager.c().j();
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public List<gmx> getEmergencyContact() {
        return EmergencyInfoManager.c().e();
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void deleteRoutes(List<Long> list, RouteResultCallBack<CloudCommonReponse> routeResultCallBack) {
        bto.a(list, routeResultCallBack);
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void setRouteData(RouteData routeData) {
        btq.c(routeData);
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public RouteData getRouteData() {
        return btq.e();
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void setTrackInfoModel(TrackInfoModel trackInfoModel) {
        btq.d(trackInfoModel);
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void clearRouteData() {
        btq.a();
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public boolean isActiveTargetSwitchOpen() {
        return ActiveTargetManager.e().b();
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public boolean isTargetRedNeedShow() {
        return ActiveTargetManager.e().d();
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void hideTargetRed() {
        ActiveTargetManager.e().c();
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void gotoH5ActiveTarget(Context context) {
        ActiveTargetManager.e().b(context);
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void getActiveTargetStatus(IBaseResponseCallback iBaseResponseCallback) {
        ActiveTargetManager.e().c(iBaseResponseCallback);
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public Class<?> getActiveTargetService() {
        return ActiveTargetService.class;
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void gotoH5HealthRecord(Context context, String str, String str2) {
        brg.b(context, str, str2);
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public void queryHealthRecords(int i, int i2, HealthRecordCbk<List<gmt>> healthRecordCbk) {
        HealthRecordsJsApi.queryRecords(i, i2 + 1, healthRecordCbk);
    }

    @Override // com.huawei.health.userprofilemgr.api.UserProfileMgrApi
    public Class<?> getHealthRecordsJsApi() {
        return HealthRecordsJsApi.class;
    }
}
