package defpackage;

import android.text.TextUtils;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.pluginhealthzone.cloud.CloudApi;
import com.huawei.pluginhealthzone.cloud.HttpDataCallback;
import health.compact.a.LogAnonymous;
import java.util.Collection;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mpn implements CloudApi {
    @Override // com.huawei.pluginhealthzone.cloud.CloudApi
    public void getVerifyCode(HttpDataCallback httpDataCallback) {
        if (httpDataCallback == null) {
            LogUtil.h("CloudImpl", "getShareTypes input params error, callback is null!");
        } else {
            mpo.a("healthCloudUrl", "/healthCare/user/getVerifyCode", new JSONObject(), null, httpDataCallback);
        }
    }

    @Override // com.huawei.pluginhealthzone.cloud.CloudApi
    public void getMyFollowRelations(Collection<Long> collection, HttpDataCallback httpDataCallback) {
        if (httpDataCallback == null) {
            LogUtil.h("CloudImpl", "getMyFollowRelations input params error, callback is null!");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            if (koq.c(collection)) {
                jSONObject.put("authUser", collection.toArray()[0]);
            }
            mpo.a("healthCloudUrl", "/healthCare/user/getUserListByFollowUser", jSONObject, null, httpDataCallback);
        } catch (JSONException e) {
            LogUtil.b("CloudImpl", LogAnonymous.b((Throwable) e));
            httpDataCallback.onFailure(AwarenessConstants.ERROR_UNKNOWN_CODE, mpu.b(AwarenessConstants.ERROR_UNKNOWN_CODE));
        }
    }

    @Override // com.huawei.pluginhealthzone.cloud.CloudApi
    public void getPushInformationByNotifyTime(long j, int i, HttpDataCallback httpDataCallback) {
        if (httpDataCallback == null) {
            LogUtil.b("CloudImpl", "getPushInformationByPushId input params error, callback is null!");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("notifyTime", j);
            jSONObject.put("pushType", i);
            mpo.a("healthCloudUrl", "/healthCare/pushdata/getPushInfoByNotifytime", jSONObject, null, httpDataCallback);
        } catch (JSONException e) {
            LogUtil.h("CloudImpl", LogAnonymous.b((Throwable) e));
            httpDataCallback.onFailure(AwarenessConstants.ERROR_UNKNOWN_CODE, mpu.b(AwarenessConstants.ERROR_UNKNOWN_CODE));
        }
    }

    @Override // com.huawei.pluginhealthzone.cloud.CloudApi
    public void uploadPosition(String str, mqp mqpVar, int i, HttpDataCallback httpDataCallback) {
        if (d(str, httpDataCallback)) {
            LogUtil.h("CloudImpl", "uploadPosition input params is null");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("coordinate", mqpVar.b());
            jSONObject.put(JsbMapKeyNames.H5_LOC_LON, mqpVar.a());
            jSONObject.put(JsbMapKeyNames.H5_LOC_LAT, mqpVar.e());
            jSONObject.put("altitude", mqpVar.c());
            jSONObject.put("clientTime", mqpVar.d());
            jSONObject.put("type", mqpVar.j());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("udid", str);
            jSONObject2.put("locationDataJson", jSONObject);
            jSONObject2.put("positionResultCode", i);
            mpo.a("healthCloudUrl", "/healthCare/position/uploadPosition", jSONObject2, null, httpDataCallback);
        } catch (JSONException e) {
            LogUtil.h("CloudImpl", LogAnonymous.b((Throwable) e));
            httpDataCallback.onFailure(AwarenessConstants.ERROR_UNKNOWN_CODE, mpu.b(AwarenessConstants.ERROR_UNKNOWN_CODE));
        }
    }

    @Override // com.huawei.pluginhealthzone.cloud.CloudApi
    public void getPositionPushInfo(String str, int i, HttpDataCallback httpDataCallback) {
        if (d(str, httpDataCallback)) {
            LogUtil.h("CloudImpl", "getPositionPushInfo input params is null");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("pushId", str);
            jSONObject.put("headFlag", i);
            mpo.a("healthCloudUrl", "/healthCare/position/getPositionPushInfo", jSONObject, null, httpDataCallback);
        } catch (JSONException e) {
            LogUtil.h("CloudImpl", LogAnonymous.b((Throwable) e));
            httpDataCallback.onFailure(AwarenessConstants.ERROR_UNKNOWN_CODE, mpu.b(AwarenessConstants.ERROR_UNKNOWN_CODE));
        }
    }

    @Override // com.huawei.pluginhealthzone.cloud.CloudApi
    public void setLocationPermission(String str, int i, HttpDataCallback httpDataCallback) {
        if (d(str, httpDataCallback)) {
            LogUtil.h("CloudImpl", "setLocationPermission input params is null");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("memberHuid", str);
            jSONObject.put("permission", i);
            mpo.a("healthCloudUrl", "/healthCare/position/setLocationPermission", jSONObject, null, httpDataCallback);
        } catch (JSONException e) {
            LogUtil.b("CloudImpl", LogAnonymous.b((Throwable) e));
            httpDataCallback.onFailure(AwarenessConstants.ERROR_UNKNOWN_CODE, mpu.b(AwarenessConstants.ERROR_UNKNOWN_CODE));
        }
    }

    @Override // com.huawei.pluginhealthzone.cloud.CloudApi
    public void getCommonUsedDevices(String str, HttpDataCallback httpDataCallback) {
        if (d(str, httpDataCallback)) {
            LogUtil.h("CloudImpl", "getCommonUsedDevices input params is null");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("commonUse", str);
            mpo.a("healthCloudUrl", "/healthCare/position/getCommonUsedDevices", jSONObject, null, httpDataCallback);
        } catch (JSONException e) {
            LogUtil.b("CloudImpl", LogAnonymous.b((Throwable) e));
            httpDataCallback.onFailure(AwarenessConstants.ERROR_UNKNOWN_CODE, mpu.b(AwarenessConstants.ERROR_UNKNOWN_CODE));
        }
    }

    @Override // com.huawei.pluginhealthzone.cloud.CloudApi
    public void notifyMemberToCheckHealth(String str, HttpDataCallback httpDataCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("memberHuidHash", str);
            mpo.a("healthCloudUrl", "/healthCare/user/notifyMemberToMedical", jSONObject, null, httpDataCallback);
        } catch (JSONException e) {
            LogUtil.b("CloudImpl", LogAnonymous.b((Throwable) e));
            httpDataCallback.onFailure(AwarenessConstants.ERROR_UNKNOWN_CODE, mpu.b(AwarenessConstants.ERROR_UNKNOWN_CODE));
        }
    }

    @Override // com.huawei.pluginhealthzone.cloud.CloudApi
    public void setCommonUsedDevices(List<exf> list, HttpDataCallback httpDataCallback) {
        if (httpDataCallback == null) {
            LogUtil.h("CloudImpl", "setCommonUsedDevices callback is null!");
            return;
        }
        if (koq.b(list)) {
            LogUtil.h("CloudImpl", "setCommonUsedDevices settings is empty!");
            httpDataCallback.onFailure(AwarenessConstants.ERROR_UNKNOWN_CODE, mpu.b(AwarenessConstants.ERROR_UNKNOWN_CODE));
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("deviceListData", rbu.e(list, exf.class));
            mpo.a("healthCloudUrl", "/healthCare/position/setCommonUsedDevices", jSONObject, null, httpDataCallback);
        } catch (JSONException e) {
            LogUtil.b("CloudImpl", LogAnonymous.b((Throwable) e));
            httpDataCallback.onFailure(AwarenessConstants.ERROR_UNKNOWN_CODE, mpu.b(AwarenessConstants.ERROR_UNKNOWN_CODE));
        }
    }

    private boolean d(String str, HttpDataCallback httpDataCallback) {
        if (httpDataCallback == null) {
            LogUtil.h("CloudImpl", "isParamsNull callback is null!");
            return true;
        }
        if (!TextUtils.isEmpty(str)) {
            return false;
        }
        LogUtil.h("CloudImpl", "isParamsNull udId is null!");
        httpDataCallback.onFailure(AwarenessConstants.ERROR_UNKNOWN_CODE, mpu.b(AwarenessConstants.ERROR_UNKNOWN_CODE));
        return true;
    }
}
