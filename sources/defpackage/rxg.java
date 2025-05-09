package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.health.adapterhealthmgr.api.AdapterHealthMgrApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.utils.ActivityHtmlPathApi;
import com.huawei.operation.utils.AppTypeUtils;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.operation.utils.OperationUtilsApi;
import com.huawei.operation.utils.OperationWebActivityIntentBuilderApi;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import com.huawei.ui.main.stories.soical.interactor.OperationDetailActivityStartCallback;
import com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi;
import com.huawei.ui.main.stories.soical.operationactivity.ActivityInfoFactory;
import com.huawei.ui.main.stories.soical.operationactivity.UserActivityInfoReq;
import com.huawei.ui.main.stories.soical.operationactivity.UserActivityInfoRsp;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ApiDefine(uri = OperationInteractorsApi.class)
@Singleton
/* loaded from: classes7.dex */
public class rxg implements OperationInteractorsApi {
    private final OperationWebActivityIntentBuilderApi b = (OperationWebActivityIntentBuilderApi) Services.a("PluginOperation", OperationWebActivityIntentBuilderApi.class);
    private final ActivityHtmlPathApi e = (ActivityHtmlPathApi) Services.a("PluginOperation", ActivityHtmlPathApi.class);
    private final AdapterHealthMgrApi d = (AdapterHealthMgrApi) Services.a("HwAdpaterHealthMgr", AdapterHealthMgrApi.class);
    private final OperationUtilsApi c = (OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class);

    @Override // com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi
    public void getOperationList(Context context, int i, List<Integer> list, final HttpResCallback httpResCallback) {
        if (CommonUtil.bu()) {
            LogUtil.a("UIDV_OperationInteractors", "isStoreDemoVersion getOperationList return.");
            return;
        }
        final HashMap<String, String> d = d(i, list, context);
        final HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008))) {
            hashMap.put("x-huid", LoginInit.getInstance(context).getAccountInfo(1011));
        }
        hashMap.put("x-version", CommonUtil.c(context));
        GRSManager.a(context).e("activityUrl", new GrsQueryCallback() { // from class: rxg.5
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.c("UIDV_OperationInteractors", "GRS url = ", str, " params:", d.toString(), " headers:", hashMap.toString());
                jei.d(str + "/activity/getActivities", d, hashMap, httpResCallback);
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i2) {
                LogUtil.c("UIDV_OperationInteractors", "GRSManager onCallBackFail ACTIVITY_KEY code = ", Integer.valueOf(i2));
            }
        });
    }

    private HashMap<String, String> d(int i, List<Integer> list, Context context) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (i >= 0) {
            hashMap.put("joinStatus", String.valueOf(i));
        }
        if (koq.c(list)) {
            hashMap.put(SyncDataConstant.BI_KEY_ACTIVITY_TYPE, String.valueOf(list));
        }
        hashMap.put("pageNo", "0");
        hashMap.put(IAchieveDBMgr.PARAM_PAGE_SIZE, "500");
        if (CommonUtil.as() || CommonUtil.cc()) {
            hashMap.put("isBeta", "1");
        }
        hashMap.put("deviceType", PhoneInfoUtils.getDeviceModel());
        hashMap.put("phoneType", PhoneInfoUtils.getPhoneType());
        String deviceId = LoginInit.getInstance(context).getDeviceId();
        if ("".equals(deviceId)) {
            deviceId = "clientnull";
        }
        hashMap.put("deviceId", deviceId);
        hashMap.put("sysVersion", Build.VERSION.RELEASE);
        hashMap.put("bindDeviceType", String.valueOf(CommonUtil.h(context)));
        hashMap.put("wearType", "");
        hashMap.put("appType", String.valueOf(AppTypeUtils.getAppType()));
        hashMap.put("iVersion", String.valueOf(1));
        hashMap.put("language", mtj.e(null));
        hashMap.put("ts", String.valueOf(jec.h()));
        hashMap.put("token", a());
        hashMap.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        hashMap.put("upDeviceType", LoginInit.getInstance(context).getDeviceType());
        if (LoginInit.getInstance(context).isLoginedByWear()) {
            hashMap.put("appId", "com.huawei.bone");
        } else {
            hashMap.put("appId", BaseApplication.getAppPackage());
        }
        hashMap.put("countryCode", LoginInit.getInstance(context).getAccountInfo(1010));
        hashMap.put("siteId", LoginInit.getInstance(context).getAccountInfo(1009));
        hashMap.put("currentManufacturer", Build.MANUFACTURER);
        hashMap.put(CloudParamKeys.CLIENT_TYPE, eil.a());
        return hashMap;
    }

    @Override // com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi
    public ceb expoundOperationActivity(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        ceb cebVar = new ceb();
        try {
            if (!jSONObject.isNull("activityName")) {
                cebVar.c(jSONObject.getString("activityName"));
            }
            if (!jSONObject.isNull("activityId")) {
                cebVar.b(jSONObject.getString("activityId"));
            }
            if (!jSONObject.isNull("imgUrl")) {
                cebVar.e(jSONObject.getString("imgUrl"));
            }
            if (!jSONObject.isNull(ParsedFieldTag.BEGIN_DATE)) {
                cebVar.i(jSONObject.getString(ParsedFieldTag.BEGIN_DATE));
            }
            if (!jSONObject.isNull("endDate")) {
                cebVar.f(jSONObject.getString("endDate"));
            }
            if (!jSONObject.isNull("numberOfPeople")) {
                cebVar.j(jSONObject.getString("numberOfPeople"));
            }
            if (!jSONObject.isNull("activityStatus")) {
                cebVar.c(jSONObject.getInt("activityStatus"));
            }
            if (!jSONObject.isNull(SyncDataConstant.BI_KEY_ACTIVITY_TYPE)) {
                cebVar.b(jSONObject.getInt(SyncDataConstant.BI_KEY_ACTIVITY_TYPE));
            }
            if (!jSONObject.isNull("teamFlag")) {
                cebVar.l(jSONObject.getInt("teamFlag"));
            }
            if (!jSONObject.isNull("timeZone")) {
                cebVar.k(jSONObject.getString("timeZone"));
            }
            b(jSONObject, cebVar);
            return cebVar;
        } catch (JSONException e) {
            LogUtil.b("UIDV_OperationInteractors", "expoundOperationActivity parse json meet exception: ", e.getMessage());
            return null;
        }
    }

    private static void b(JSONObject jSONObject, ceb cebVar) throws JSONException {
        if (jSONObject == null || cebVar == null) {
            return;
        }
        if (!jSONObject.isNull("templateType")) {
            cebVar.h(jSONObject.getInt("templateType"));
        }
        if (!jSONObject.isNull("activityLink")) {
            cebVar.a(jSONObject.getString("activityLink"));
        }
        if (!jSONObject.isNull("rotinePosition")) {
            cebVar.i(jSONObject.getInt("rotinePosition"));
        }
        if (!jSONObject.isNull("activityPosition")) {
            cebVar.d(jSONObject.getInt("activityPosition"));
        }
        if (!jSONObject.isNull("matchBeginDate")) {
            cebVar.h(jSONObject.getString("matchBeginDate"));
        }
        if (!jSONObject.isNull("activityContext")) {
            cebVar.d(jSONObject.getString("activityContext"));
        }
        if (!jSONObject.isNull("haveHistoryTodo")) {
            cebVar.g(jSONObject.getInt("haveHistoryTodo"));
        }
        if (!jSONObject.isNull("activityTargetTodo")) {
            cebVar.a(jSONObject.getInt("activityTargetTodo"));
        }
        if (!jSONObject.isNull("continuityTodo")) {
            cebVar.j(jSONObject.getInt("continuityTodo"));
        }
        if (!jSONObject.isNull("targetDaysTodo")) {
            cebVar.f(jSONObject.getInt("targetDaysTodo"));
        }
        if (!jSONObject.isNull("completeFlagTodo")) {
            cebVar.e(jSONObject.getInt("completeFlagTodo"));
        }
        if (!jSONObject.isNull("appVersion")) {
            cebVar.g(jSONObject.getString("appVersion"));
        }
        if (!jSONObject.isNull(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE)) {
            cebVar.n(jSONObject.getString(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE));
        }
        if (!jSONObject.isNull("workoutUserLable ")) {
            cebVar.o(jSONObject.getString("workoutUserLable "));
        }
        if (jSONObject.isNull(com.huawei.health.messagecenter.model.CommonUtil.IMAGE_TEXT_SEPARATE_SWITCH)) {
            return;
        }
        cebVar.o(jSONObject.getInt(com.huawei.health.messagecenter.model.CommonUtil.IMAGE_TEXT_SEPARATE_SWITCH));
    }

    @Override // com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi
    public JSONObject activityBeanToJSONObject(ceb cebVar) {
        JSONObject jSONObject = new JSONObject();
        if (cebVar != null) {
            try {
                jSONObject.put("activityName", cebVar.b());
                jSONObject.put("activityId", cebVar.c());
                jSONObject.put("imgUrl", cebVar.d());
                jSONObject.put(ParsedFieldTag.BEGIN_DATE, cebVar.t());
                jSONObject.put("endDate", cebVar.n());
                jSONObject.put("timeZone", cebVar.v());
                jSONObject.put("numberOfPeople", cebVar.o());
                jSONObject.put("activityStatus", cebVar.h());
                jSONObject.put(SyncDataConstant.BI_KEY_ACTIVITY_TYPE, cebVar.f());
                jSONObject.put("templateType", cebVar.p());
                jSONObject.put("activityLink", cebVar.a());
                jSONObject.put("rotinePosition", cebVar.q());
                jSONObject.put("activityPosition", cebVar.j());
                jSONObject.put("matchBeginDate", cebVar.s());
                jSONObject.put("activityContext", cebVar.e());
                jSONObject.put("haveHistoryTodo", cebVar.l());
                jSONObject.put("activityTargetTodo", cebVar.g());
                jSONObject.put("continuityTodo", cebVar.m());
                jSONObject.put("targetDaysTodo", cebVar.r());
                jSONObject.put("completeFlagTodo", cebVar.k());
                return jSONObject;
            } catch (JSONException e) {
                LogUtil.b("UIDV_OperationInteractors", "Json data error! JSONException:", e.getMessage());
                return null;
            }
        }
        LogUtil.h("UIDV_OperationInteractors", "activityBeanToJSONObject:activity == null");
        return jSONObject;
    }

    @Override // com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi
    public String getGMTtoLocal(String str) {
        LogUtil.c("UIDV_OperationInteractors", "getGMTtoLocal");
        String str2 = null;
        if (str == null) {
            LogUtil.a("UIDV_OperationInteractors", "date is null");
            return null;
        }
        LogUtil.a("UIDV_OperationInteractors", "before GMTtoLocal ", str);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            str2 = UnitUtil.a(simpleDateFormat.parse(str), 65552);
            LogUtil.a("UIDV_OperationInteractors", "after GMTtoLocal ", str2);
            return str2;
        } catch (ParseException e) {
            LogUtil.b("UIDV_OperationInteractors", "getGMTtoLocal(),Exception exception = ", e.getMessage());
            return str2;
        }
    }

    @Override // com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi
    public ArrayList getActivityPageType(ceb cebVar) {
        String u = cebVar.u();
        if (TextUtils.isEmpty(u)) {
            return null;
        }
        String[] split = u.split(",");
        ArrayList arrayList = new ArrayList(2);
        for (String str : split) {
            arrayList.add(Integer.valueOf(nsn.e(str)));
        }
        return arrayList;
    }

    @Override // com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi
    public boolean isMatchPage(int i, ArrayList<Integer> arrayList) {
        return koq.c(arrayList) && arrayList.contains(Integer.valueOf(i));
    }

    @Override // com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi
    public boolean isUserLabelActivity(ceb cebVar, List<String> list) {
        if (cebVar == null || koq.b(list)) {
            LogUtil.h("UIDV_OperationInteractors", "isUserLableActivity is null or userLables is empty.");
            return true;
        }
        String x = cebVar.x();
        if (TextUtils.isEmpty(x)) {
            return true;
        }
        try {
            JSONObject jSONObject = new JSONObject(x);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (!TextUtils.isEmpty(next)) {
                    JSONArray jSONArray = jSONObject.getJSONArray(next);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        if (list.contains((String) jSONArray.get(i))) {
                            break;
                        }
                    }
                }
                return false;
            }
            return true;
        } catch (JSONException unused) {
            LogUtil.b("UIDV_OperationInteractors", "isUserLableActivity JSONException");
            return true;
        }
    }

    @Override // com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi
    public int getActivityStatus(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            LogUtil.h("UIDV_OperationInteractors", "curDate or startDate or endDate is empty");
            return 2;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = simpleDateFormat.parse(str);
            Date parse2 = simpleDateFormat.parse(str3);
            Date parse3 = simpleDateFormat.parse(str2);
            if (parse.getTime() < parse3.getTime()) {
                return 0;
            }
            if (parse3.getTime() > parse.getTime() || parse.getTime() > parse2.getTime()) {
                if (parse2.getTime() < parse.getTime()) {
                    return -1;
                }
                LogUtil.h("UIDV_OperationInteractors", "no branch!");
            }
            return 1;
        } catch (ParseException e) {
            LogUtil.b("UIDV_OperationInteractors", "getGMTtoLocal(),Exception exception = ", e.getMessage());
            return 2;
        }
    }

    @Override // com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi
    public boolean isHuaweiNoTemplateActivity(ceb cebVar) {
        return cebVar.f() == 400;
    }

    private boolean c(ceb cebVar) {
        OperationUtilsApi operationUtilsApi = this.c;
        if (operationUtilsApi == null) {
            return false;
        }
        return operationUtilsApi.isChallengeActivity(cebVar.f());
    }

    private String d(String str, ceb cebVar) {
        String str2;
        String str3 = str + this.e.getActivityHtmlPath(cebVar);
        if (cebVar.h() == -1) {
            str2 = str3 + "activityShare";
        } else if (c(cebVar)) {
            str2 = str3 + "rankList";
        } else if (cebVar.ac()) {
            str2 = str3 + "groupChallenge/dist/index";
        } else {
            str2 = str3 + "calendar";
        }
        if (cebVar.p() == 5 && !cebVar.ac()) {
            str2 = str2 + "New";
        }
        String str4 = str2 + ".html?activityId=" + cebVar.c();
        if (cebVar.ac()) {
            str4 = str4 + "#/score";
        }
        if (cebVar.h() == -1 || cebVar.ac()) {
            return str4;
        }
        return str4 + "&activityName=" + cebVar.b();
    }

    @Override // com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi
    public String getOperationActivityUrl(String str, ceb cebVar) {
        LogUtil.c("UIDV_OperationInteractors", "getOperationActivityUrl activityUrl = ", str);
        if (cebVar.p() == 4) {
            LogUtil.a("UIDV_OperationInteractors", "Enter ACTIVITY_TEMPLATETYPE_NO_DETAIL");
            return cebVar.a();
        }
        return d(str, cebVar);
    }

    @Override // com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi
    public String getMaxActivityId(JSONArray jSONArray) {
        String str = "";
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i3);
                if (jSONObject != null) {
                    ceb expoundOperationActivity = expoundOperationActivity(jSONObject);
                    if (expoundOperationActivity != null && expoundOperationActivity.c() != null && !"".equals(expoundOperationActivity.c())) {
                        try {
                            i = Integer.parseInt(expoundOperationActivity.c());
                        } catch (NumberFormatException e) {
                            LogUtil.b("UIDV_OperationInteractors", "NumberFormatException exception = ", e.getMessage());
                        }
                    }
                    if (i > i2) {
                        if (expoundOperationActivity != null) {
                            str = expoundOperationActivity.c();
                        }
                        i2 = i;
                    }
                }
            } catch (JSONException e2) {
                LogUtil.b("UIDV_OperationInteractors", "Json data error! JSONException:", e2.getMessage());
            }
        }
        return str;
    }

    @Override // com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi
    public boolean judgeVersionSupport(String str) {
        LogUtil.a("UIDV_OperationInteractors", "judgeVersionSupport enter!");
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        Map<String, String> appData = this.d.getAppData(new String[]{"getAppInfo"});
        LogUtil.c("UIDV_OperationInteractors", "mMapInfo ", appData.toString());
        String str2 = appData.get("version");
        if (TextUtils.isEmpty(str2)) {
            return true;
        }
        LogUtil.c("UIDV_OperationInteractors", "version from app = ", str2, " cloud = ", str);
        int indexOf = str2.indexOf(Constants.LINK);
        LogUtil.c("UIDV_OperationInteractors", "index: ", Integer.valueOf(indexOf));
        if (indexOf >= 0) {
            str2 = str2.substring(0, indexOf);
        }
        while (!"".equals(str2)) {
            int indexOf2 = str2.indexOf(".");
            int e = nsn.e(str2.substring(0, indexOf2 > -1 ? indexOf2 : str2.length()));
            if (indexOf2 < 0) {
                str2 = "";
            } else {
                str2 = str2.substring(indexOf2 + 1);
            }
            int indexOf3 = str.indexOf(".");
            int e2 = nsn.e(str.substring(0, indexOf3 > -1 ? indexOf3 : str.length()));
            str = str.substring(indexOf3 + 1);
            if (e > e2) {
                return true;
            }
            if (e < e2) {
                return false;
            }
            LogUtil.h("UIDV_OperationInteractors", "no branch!");
        }
        return true;
    }

    @Override // com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi
    public void setNeedUpdateActivity(Context context, boolean z) {
        SharedPreferenceManager.e(context, Integer.toString(10011), "SOCIAL_FRAGMENT_IS_NEED_UPDATE_ACTIVITY", z ? "TRUE_VALUE" : "FALSE_VALUE", new StorageParams());
    }

    @Override // com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi
    public boolean getNeedUpdateActivity(Context context) {
        return "TRUE_VALUE".equals(SharedPreferenceManager.b(context, Integer.toString(10011), "SOCIAL_FRAGMENT_IS_NEED_UPDATE_ACTIVITY"));
    }

    private String a() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008);
        if (TextUtils.isEmpty(accountInfo)) {
            return accountInfo;
        }
        try {
            return URLEncoder.encode(accountInfo, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            LogUtil.b("UIDV_OperationInteractors", "token encode Exception ", e.toString());
            return accountInfo;
        }
    }

    @Override // com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi
    public void gotoOperationActivityDetail(Context context, String str, ceb cebVar, OperationDetailActivityStartCallback operationDetailActivityStartCallback) {
        String operationActivityUrl = getOperationActivityUrl(str, cebVar);
        setNeedUpdateActivity(context, true);
        operationDetailActivityStartCallback.beforeDetailStart(cebVar);
        try {
            context.startActivity(this.b.builder(context, operationActivityUrl).build());
        } catch (ActivityNotFoundException e) {
            LogUtil.b("UIDV_OperationInteractors", "operationWebActivity", e.getMessage());
        }
        operationDetailActivityStartCallback.afterDetailStart(cebVar);
    }

    @Override // com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi
    public void getUserActivityInfo(int i, String str, List<String> list, IBaseResponseCallback iBaseResponseCallback) {
        UserActivityInfoReq userActivityInfoReq = new UserActivityInfoReq();
        if (i == 1) {
            if (koq.b(list)) {
                return;
            }
            userActivityInfoReq.setQueryType(1);
            userActivityInfoReq.setActivityList(list);
        } else {
            userActivityInfoReq.setQueryType(0);
            userActivityInfoReq.setActivityId(str);
        }
        ActivityInfoFactory activityInfoFactory = new ActivityInfoFactory(BaseApplication.getContext());
        UserActivityInfoRsp userActivityInfoRsp = (UserActivityInfoRsp) lqi.d().d(userActivityInfoReq.getUrl(), activityInfoFactory.getHeaders(), lql.b(activityInfoFactory.getBody(userActivityInfoReq)), UserActivityInfoRsp.class);
        if (userActivityInfoRsp == null) {
            LogUtil.h("UIDV_OperationInteractors", "getUserActivityInfo, userActivityInfoRsp is null");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        String resultCode = userActivityInfoRsp.getResultCode();
        LogUtil.a("UIDV_OperationInteractors", "getUserActivityInfo, resultCode = ", resultCode);
        List<rwz> userActivityInfoList = userActivityInfoRsp.getUserActivityInfoList();
        if (userActivityInfoList == null) {
            LogUtil.h("UIDV_OperationInteractors", "getUserActivityInfo, infoList is null");
            iBaseResponseCallback.d(-1, null);
        } else {
            iBaseResponseCallback.d(CommonUtil.m(BaseApplication.getContext(), resultCode), userActivityInfoList);
        }
    }
}
