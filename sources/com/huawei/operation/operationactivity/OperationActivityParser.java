package com.huawei.operation.operationactivity;

import android.text.TextUtils;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import defpackage.koq;
import defpackage.kvm;
import defpackage.kvp;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class OperationActivityParser {
    private static final String TAG = "OperationActivity_OperationActivityParser";

    private OperationActivityParser() {
    }

    public static kvp analyzeOperationActivityResponse(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "analyzeOperationActivityResponse json is empty.");
        } else {
            kvp kvpVar = new kvp();
            try {
                JSONObject jSONObject = new JSONObject(str);
                kvpVar.e(getStringValue(jSONObject, "resultCode"));
                kvpVar.b(getStringValue(jSONObject, "resultDesc"));
                kvpVar.d(getStringValue(jSONObject, "currentTime"));
                kvpVar.e(getIntValue(jSONObject, "hasMore"));
                kvpVar.a(getIntValue(jSONObject, "pageNo"));
                kvpVar.b(getIntValue(jSONObject, IAchieveDBMgr.PARAM_PAGE_SIZE));
                List<kvm> analyzeActivities = analyzeActivities(jSONObject);
                if (!koq.c(analyzeActivities)) {
                    return kvpVar;
                }
                kvpVar.c(analyzeActivities);
                return kvpVar;
            } catch (JSONException e) {
                LogUtil.b(TAG, "getActivities Json data error! JSONException:", e.getMessage());
            }
        }
        return null;
    }

    public static List<kvm> analyzeActivities(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList(10);
        if (jSONObject == null) {
            LogUtil.h(TAG, "analyzeActivities() jsonObject is null.");
            return arrayList;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("activities");
        if (optJSONArray == null) {
            LogUtil.h(TAG, "analyzeActivities() activityJsonArray is null.");
            return arrayList;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            try {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                if (jSONObject2 == null) {
                    LogUtil.h(TAG, "analyzeActivities() jsonObjectActivity is null.");
                } else {
                    kvm analyzeActivitySimpleObject = analyzeActivitySimpleObject(jSONObject2);
                    if (analyzeActivitySimpleObject == null) {
                        LogUtil.h(TAG, "analyzeActivities() activitySimple is null.");
                    } else if (!arrayList.contains(analyzeActivitySimpleObject)) {
                        arrayList.add(analyzeActivitySimpleObject);
                    }
                }
            } catch (JSONException e) {
                LogUtil.b(TAG, "analyzeActivities meet json exception: ", e.getMessage());
            }
        }
        return arrayList;
    }

    private static kvm analyzeActivitySimpleObject(JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h(TAG, "analyzeActivitySimpleObject() jsonObject is null.");
        } else {
            kvm kvmVar = new kvm();
            try {
                kvmVar.b(getStringValue(jSONObject, "activityId"));
                kvmVar.d(getStringValue(jSONObject, "activityName"));
                kvmVar.j(getStringValue(jSONObject, "description"));
                kvmVar.k(getStringValue(jSONObject, "imgUrl"));
                kvmVar.i(getStringValue(jSONObject, ParsedFieldTag.BEGIN_DATE));
                analyzeCommonFields(jSONObject, kvmVar);
                analyzeOtherFields(jSONObject, kvmVar);
                return kvmVar;
            } catch (JSONException e) {
                LogUtil.b(TAG, "analyzeActivitySimpleObject meet json exception: ", e.getMessage());
            }
        }
        return null;
    }

    private static void analyzeCommonFields(JSONObject jSONObject, kvm kvmVar) throws JSONException {
        if (jSONObject == null || kvmVar == null) {
            return;
        }
        kvmVar.f(getStringValue(jSONObject, "endDate"));
        kvmVar.i(getIntValue(jSONObject, "numberOfPeople"));
        kvmVar.d(getIntValue(jSONObject, "activityStatus"));
        kvmVar.b(getIntValue(jSONObject, SyncDataConstant.BI_KEY_ACTIVITY_TYPE));
        kvmVar.o(getIntValue(jSONObject, BleConstants.SPORT_TYPE));
        kvmVar.k(getIntValue(jSONObject, "templateType"));
        kvmVar.q(getStringValue(jSONObject, "wordDesc"));
        kvmVar.c(getIntValue(jSONObject, "activityBrand"));
        kvmVar.e(getIntValue(jSONObject, "activitySubdivided"));
        kvmVar.e(getStringValue(jSONObject, "activityLink"));
        kvmVar.o(getStringValue(jSONObject, "lastModifyTime"));
        kvmVar.a(getIntValue(jSONObject, "activityPosition"));
        kvmVar.m(getIntValue(jSONObject, "rotinePosition"));
        kvmVar.c(getStringValue(jSONObject, "appVersion"));
    }

    private static void analyzeOtherFields(JSONObject jSONObject, kvm kvmVar) throws JSONException {
        if (jSONObject == null || kvmVar == null) {
            return;
        }
        kvmVar.a(getStringValue(jSONObject, "activityContext"));
        kvmVar.l(getStringValue(jSONObject, "matchBeginDate"));
        kvmVar.j(getIntValue(jSONObject, "isMedal"));
        if (!jSONObject.isNull("attendDate")) {
            kvmVar.b(jSONObject.getLong("attendDate"));
        }
        kvmVar.g(getStringValue(jSONObject, "attendTimeZone"));
        kvmVar.s(getStringValue(jSONObject, "workoutUserLable"));
        kvmVar.t(getStringValue(jSONObject, CommonUtil.PAGE_TYPE));
        kvmVar.f(getIntValue(jSONObject, CommonUtil.IMAGE_TEXT_SEPARATE_SWITCH));
        kvmVar.r(getStringValue(jSONObject, "ux10ImgUrl"));
        kvmVar.p(getStringValue(jSONObject, "timeZone"));
        kvmVar.g(getIntValue(jSONObject, "isFree"));
        kvmVar.h(getIntValue(jSONObject, "isMultiLevelFlag"));
        kvmVar.m(getStringValue(jSONObject, "joinDeadLine"));
        kvmVar.n(getStringValue(jSONObject, "lotteryTime"));
        kvmVar.h(getStringValue(jSONObject, "countryCodes"));
    }

    private static String getStringValue(JSONObject jSONObject, String str) {
        if (jSONObject != null && !jSONObject.isNull(str)) {
            try {
                return !jSONObject.isNull(str) ? jSONObject.getString(str) : "";
            } catch (JSONException e) {
                LogUtil.b(TAG, "getJsonValue meet json exception: ", e.getMessage());
            }
        }
        return "";
    }

    private static int getIntValue(JSONObject jSONObject, String str) {
        if (jSONObject != null && !jSONObject.isNull(str)) {
            try {
                if (jSONObject.isNull(str)) {
                    return 0;
                }
                return jSONObject.getInt(str);
            } catch (JSONException e) {
                LogUtil.b(TAG, "getJsonValue meet json exception: ", e.getMessage());
            }
        }
        return 0;
    }
}
