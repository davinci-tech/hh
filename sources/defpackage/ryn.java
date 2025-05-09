package defpackage;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.marketing.request.ActivityInfoListFactory;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.operation.utils.AppTypeUtils;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.operation.utils.PhoneInfoUtils;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class ryn {
    private static volatile ryn b;
    private static final Byte[] d = new Byte[1];
    private Context e;
    private ConcurrentHashMap<Integer, List<cdy>> i = new ConcurrentHashMap<>(16);
    private ConcurrentHashMap<Integer, List<pff>> c = new ConcurrentHashMap<>(16);

    /* renamed from: a, reason: collision with root package name */
    private boolean f16967a = true;
    private Handler g = new ryi();

    private ryn(Context context) {
        this.e = context;
    }

    public static ryn d() {
        if (b == null) {
            synchronized (d) {
                if (b == null) {
                    b = new ryn(BaseApplication.getContext());
                }
            }
        }
        return b;
    }

    public List<cdy> d(int i) {
        return this.i.get(Integer.valueOf(i));
    }

    public void a(final int i) {
        LogUtil.a("TemplatePage_ConfiguredPageInteraction", "initOperationConfiguredPage() pageType = ", Integer.valueOf(i));
        if (LoginInit.getInstance(this.e).isKidAccount() || !Utils.o()) {
            LogUtil.h("TemplatePage_ConfiguredPageInteraction", "initOperationConfiguredPage() isKidAccount no support.");
            return;
        }
        boolean z = h(i) || f(i);
        if (z) {
            this.i = d(i, this.i, this.c);
            g(i);
        } else {
            LogUtil.h("TemplatePage_ConfiguredPageInteraction", "initOperationConfiguredPage() no memory cached.");
        }
        if (!lop.c(this.e)) {
            LogUtil.h("TemplatePage_ConfiguredPageInteraction", "initOperationConfiguredPage() network is not connected. return back!");
            if (z) {
                return;
            }
            g(i);
            return;
        }
        c(i, new HttpResCallback() { // from class: ryn.3
            @Override // com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback
            public void onFinished(int i2, String str) {
                if (i2 != 200 || TextUtils.isEmpty(str)) {
                    LogUtil.h("TemplatePage_ConfiguredPageInteraction", "/getPageModule resCode = ", Integer.valueOf(i2), " or result is empty.");
                    ryn.this.g(i);
                } else {
                    LogUtil.c("TemplatePage_ConfiguredPageInteraction", "getConfiguredPageDataFromCloud() /getPageModule result = ", str);
                    if (ryn.this.d(str, i)) {
                        ryn.this.g(i);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(String str, int i) {
        JSONObject jSONObject;
        String string;
        try {
            jSONObject = new JSONObject(str);
            string = jSONObject.getString("resultCode");
        } catch (JSONException unused) {
            LogUtil.b("TemplatePage_ConfiguredPageInteraction", "parse page module data meet exception.");
        }
        if (!"0".equals(string)) {
            LogUtil.h("TemplatePage_ConfiguredPageInteraction", "hasPageModuleJsonData /getPageModule resultCode = ", string, ", resultDesc = ", jSONObject.getString("resultDesc"));
            return false;
        }
        JSONArray jSONArray = jSONObject.getJSONArray("pageModuleList");
        int length = jSONArray.length();
        if (length == 0) {
            LogUtil.h("TemplatePage_ConfiguredPageInteraction", "/getPageModule pageModuleListJsonArray is empty! no pageModuleList!");
            return false;
        }
        ConcurrentHashMap<Integer, List<cdy>> concurrentHashMap = this.i;
        if (concurrentHashMap != null && concurrentHashMap.containsKey(Integer.valueOf(i))) {
            List<cdy> list = this.i.get(Integer.valueOf(i));
            if (koq.c(list) && list.toString().equals(jSONArray.toString())) {
                c(i);
                return false;
            }
        }
        ArrayList arrayList = new ArrayList(10);
        for (int i2 = 0; i2 < length; i2++) {
            cdy d2 = d(jSONArray.getJSONObject(i2), i);
            if (d2 != null && !arrayList.contains(d2)) {
                arrayList.add(d2);
            }
            LogUtil.h("TemplatePage_ConfiguredPageInteraction", "getPageModuleData() pageModuleObject is null or contains");
        }
        if (koq.b(arrayList)) {
            LogUtil.h("TemplatePage_ConfiguredPageInteraction", "/getPageModule mPageModuleObjectList is empty! ");
            return false;
        }
        this.i.put(Integer.valueOf(i), arrayList);
        SharedPreferenceManager.e(this.e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "templatePageModuleData" + i, jSONArray.toString(), new StorageParams());
        c(i);
        return true;
    }

    private void c(int i) {
        ConcurrentHashMap<Integer, List<cdy>> concurrentHashMap = this.i;
        if (concurrentHashMap == null || concurrentHashMap.size() <= 0) {
            LogUtil.h("TemplatePage_ConfiguredPageInteraction", "dealPageActivityData() mPageModuleObjectMap is empty.");
            return;
        }
        List<cdy> list = this.i.get(Integer.valueOf(i));
        if (koq.b(list)) {
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        for (cdy cdyVar : list) {
            if (cdyVar != null && cdyVar.b() == 3) {
                List<cdu> e = cdyVar.e();
                if (koq.b(e)) {
                    return;
                }
                for (cdu cduVar : e) {
                    if (cduVar != null && cduVar.a() == 1) {
                        String b2 = cduVar.b();
                        if (TextUtils.isEmpty(b2) || arrayList.contains(b2)) {
                            LogUtil.h("TemplatePage_ConfiguredPageInteraction", "dealPageActivityData() activityDetailId is empty or already exists");
                        } else {
                            arrayList.add(b2);
                        }
                    }
                }
            }
        }
        if (koq.b(arrayList)) {
            LogUtil.h("TemplatePage_ConfiguredPageInteraction", "dealPageActivityData() huaweiActivityDetailIdList is empty.");
            g(i);
        } else {
            LogUtil.a("TemplatePage_ConfiguredPageInteraction", "dealPageActivityData() mHuaweiActivityIdList = ", arrayList.toString());
            d(i, arrayList);
        }
    }

    private void d(final int i, List<String> list) {
        d(list, new ResultCallback<String>() { // from class: ryn.2
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                if (!TextUtils.isEmpty(str)) {
                    LogUtil.a("TemplatePage_ConfiguredPageInteraction", "dealPageActivityData success:", str);
                    ryn.this.e(str, i);
                }
                ryn.this.g(i);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.b("TemplatePage_ConfiguredPageInteraction", "dealPageActivityData fail:");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(int i) {
        Message obtainMessage = this.g.obtainMessage();
        obtainMessage.what = 4097;
        obtainMessage.arg1 = i;
        this.g.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("TemplatePage_ConfiguredPageInteraction", "/resolveHuaweiActivityJsonData result is empty! ");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("resultCode");
            if (!"0".equals(string)) {
                LogUtil.h("TemplatePage_ConfiguredPageInteraction", "/resolveHuaweiActivityJsonData resultCode = ", string, ", resultDesc = ", jSONObject.getString("resultDesc"));
                return;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("pageActivityList");
            int length = jSONArray.length();
            if (length == 0) {
                LogUtil.h("TemplatePage_ConfiguredPageInteraction", "/resolveHuaweiActivityJsonData no pageActivityList.");
                return;
            }
            if (this.c.containsKey(Integer.valueOf(i))) {
                List<pff> list = this.c.get(Integer.valueOf(i));
                if (koq.c(list) && list.toString().equals(jSONArray.toString())) {
                    return;
                }
            }
            SharedPreferenceManager.e(this.e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "templatePageActivitiesData" + i, jSONArray.toString(), new StorageParams());
            c(i, jSONObject);
            ArrayList arrayList = new ArrayList(10);
            for (int i2 = 0; i2 < length; i2++) {
                pff d2 = d(jSONArray.getJSONObject(i2));
                if (d2 != null && !arrayList.contains(d2)) {
                    arrayList.add(d2);
                }
            }
            if (koq.b(arrayList)) {
                return;
            }
            this.c.put(Integer.valueOf(i), arrayList);
            this.i = d(i, this.i, this.c);
        } catch (JSONException unused) {
            LogUtil.b("TemplatePage_ConfiguredPageInteraction", "Json data error! JSONException");
        }
    }

    private void c(int i, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("currentTime")) {
            String string = jSONObject.getString("currentTime");
            StorageParams storageParams = new StorageParams();
            SharedPreferenceManager.e(this.e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "configuredPageActivityServerCurrentTime" + i, string, storageParams);
        }
    }

    private ConcurrentHashMap<Integer, List<cdy>> d(int i, ConcurrentHashMap<Integer, List<cdy>> concurrentHashMap, ConcurrentHashMap<Integer, List<pff>> concurrentHashMap2) {
        if (concurrentHashMap2 != null && concurrentHashMap2.size() > 0) {
            List<cdy> list = concurrentHashMap.get(Integer.valueOf(i));
            if (koq.b(list)) {
                return concurrentHashMap;
            }
            List<pff> list2 = concurrentHashMap2.get(Integer.valueOf(i));
            if (koq.b(list2)) {
                return concurrentHashMap;
            }
            for (cdy cdyVar : list) {
                if (cdyVar != null && cdyVar.b() == 3) {
                    List<cdu> e = cdyVar.e();
                    if (koq.b(e)) {
                        LogUtil.h("TemplatePage_ConfiguredPageInteraction", "combineModuleActivityList() cardItemObjectList is empty.");
                    } else {
                        for (cdu cduVar : e) {
                            if (cduVar != null && cduVar.a() == 1) {
                                String b2 = cduVar.b();
                                for (pff pffVar : list2) {
                                    if (pffVar != null && b2.equals(pffVar.e())) {
                                        cduVar.d(pffVar.a());
                                        cduVar.e(pffVar.d());
                                        cduVar.e(pffVar.b());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return concurrentHashMap;
    }

    private cdy d(JSONObject jSONObject, int i) {
        int i2;
        if (jSONObject == null) {
            LogUtil.h("TemplatePage_ConfiguredPageInteraction", "resolvePageModuleJsonData() pageModuleData is null.");
            return null;
        }
        try {
            cdy c = c(jSONObject);
            if (c == null) {
                LogUtil.h("TemplatePage_ConfiguredPageInteraction", "resolvePageModuleJsonData() pageModuleObject is null.");
                return null;
            }
            if (jSONObject.isNull("moduleType")) {
                i2 = 0;
            } else {
                i2 = jSONObject.getInt("moduleType");
                c.d(i2);
            }
            JSONObject jSONObject2 = new JSONObject();
            if (!jSONObject.isNull("pageModuleDetail")) {
                jSONObject2 = jSONObject.getJSONObject("pageModuleDetail");
            }
            String b2 = b(i2);
            if (TextUtils.isEmpty(b2)) {
                LogUtil.h("TemplatePage_ConfiguredPageInteraction", "resolvePageModuleJsonData() detailJsonKey is empty. moduleType = ", Integer.valueOf(i2));
                return null;
            }
            JSONArray jSONArray = jSONObject2.getJSONArray(b2);
            int length = jSONArray.length();
            if (length == 0) {
                LogUtil.h("TemplatePage_ConfiguredPageInteraction", "resolvePageModuleJsonData() pageModuleDetailJsonArrayLength == 0.detailJsonKey = ", b2);
                return null;
            }
            for (int i3 = 0; i3 < length; i3++) {
                cdu b3 = b(jSONArray.getJSONObject(i3), i2);
                if (b3 == null) {
                    LogUtil.h("TemplatePage_ConfiguredPageInteraction", "resolvePageModuleJsonData() cardItemObject is null.");
                } else {
                    b3.n(i);
                    c.e(b3);
                }
            }
            return c;
        } catch (JSONException unused) {
            LogUtil.b("TemplatePage_ConfiguredPageInteraction", "expoundConfigureModule Json data error!");
            return null;
        }
    }

    private pff d(JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("TemplatePage_ConfiguredPageInteraction", "expoundActivityItemObject() jsonActivityObject is null.");
            return null;
        }
        pff pffVar = new pff();
        try {
            if (!jSONObject.isNull("activityId")) {
                pffVar.c(jSONObject.getString("activityId"));
            }
            if (!jSONObject.isNull(ParsedFieldTag.BEGIN_DATE)) {
                pffVar.d(jSONObject.getString(ParsedFieldTag.BEGIN_DATE));
            }
            if (!jSONObject.isNull("endDate")) {
                pffVar.e(jSONObject.getString("endDate"));
            }
            if (!jSONObject.isNull("numberOfPeople")) {
                pffVar.c(jSONObject.getInt("numberOfPeople"));
            }
            return pffVar;
        } catch (JSONException unused) {
            LogUtil.b("TemplatePage_ConfiguredPageInteraction", "expoundActivityItemObject Json data error!");
            return null;
        }
    }

    private cdu b(JSONObject jSONObject, int i) {
        if (jSONObject == null) {
            LogUtil.h("TemplatePage_ConfiguredPageInteraction", "expoundActivityItemObject() jsonObject is null.");
            return null;
        }
        cdu cduVar = new cdu();
        try {
            if (!jSONObject.isNull("pageModuleId")) {
                cduVar.c(jSONObject.getInt("pageModuleId"));
            }
            if (!jSONObject.isNull("imgUrl")) {
                cduVar.h(jSONObject.getString("imgUrl"));
            }
            if (!jSONObject.isNull("imgUrlDaxi")) {
                cduVar.j(jSONObject.getString("imgUrlDaxi"));
            } else if (!jSONObject.isNull("imgUrl")) {
                cduVar.j(jSONObject.getString("imgUrl"));
            } else {
                LogUtil.h("TemplatePage_ConfiguredPageInteraction", "expoundCardItemObject() imageUrlTahiti and imageUrl are empty");
            }
            if (!jSONObject.isNull("detailUrl")) {
                cduVar.a(jSONObject.getString("detailUrl"));
            }
            if (!jSONObject.isNull("weight")) {
                cduVar.f(jSONObject.getInt("weight"));
            }
            if (!jSONObject.isNull("display")) {
                cduVar.d(jSONObject.getInt("display"));
            }
            if (!jSONObject.isNull("description")) {
                cduVar.c(jSONObject.getString("description"));
            }
            if (!jSONObject.isNull("desDisplay")) {
                cduVar.i(jSONObject.getInt("desDisplay"));
            }
            return c(i, cduVar, jSONObject);
        } catch (JSONException unused) {
            LogUtil.b("TemplatePage_ConfiguredPageInteraction", "expoundCardItemObject Json data error!");
            return null;
        }
    }

    private cdu c(int i, cdu cduVar, JSONObject jSONObject) {
        try {
            if (i == 1) {
                d(cduVar, jSONObject, "serviceId");
                a(cduVar, jSONObject, JsUtil.SERVICE_NAME);
                if (!jSONObject.isNull("serviceUrlType")) {
                    cduVar.g(jSONObject.getInt("serviceUrlType"));
                }
                a(cduVar, jSONObject);
            } else if (i == 2) {
                d(cduVar, jSONObject, "planId");
                a(cduVar, jSONObject, "planName");
                if (!jSONObject.isNull("planDco")) {
                    cduVar.g(jSONObject.getString("planDco"));
                }
            } else if (i == 3) {
                d(cduVar, jSONObject, "activityId");
                a(cduVar, jSONObject, "activityName");
                if (!jSONObject.isNull(SyncDataConstant.BI_KEY_ACTIVITY_TYPE)) {
                    cduVar.a(jSONObject.getInt(SyncDataConstant.BI_KEY_ACTIVITY_TYPE));
                }
                if (!jSONObject.isNull("activityDetailId")) {
                    cduVar.b(jSONObject.getString("activityDetailId"));
                }
            } else if (i == 4) {
                d(cduVar, jSONObject, "infoId");
                a(cduVar, jSONObject, "infoName");
                c(cduVar, jSONObject);
            } else if (i == 5) {
                d(cduVar, jSONObject, "optimizationId");
                a(cduVar, jSONObject, "optimizationName");
                c(cduVar, jSONObject);
            }
            return cduVar;
        } catch (JSONException unused) {
            LogUtil.b("TemplatePage_ConfiguredPageInteraction", "getModuleTypeCard() Json data error!");
            return null;
        }
    }

    private void a(cdu cduVar, JSONObject jSONObject) throws JSONException {
        if (!jSONObject.isNull("serviceFlag")) {
            cduVar.m(jSONObject.getString("serviceFlag"));
        }
        if (!jSONObject.isNull("webUrl")) {
            cduVar.p(jSONObject.getString("webUrl"));
        }
        if (jSONObject.isNull("innerPagePath")) {
            return;
        }
        cduVar.l(jSONObject.getString("innerPagePath"));
    }

    private void c(cdu cduVar, JSONObject jSONObject) throws JSONException {
        if (jSONObject.isNull("imgType")) {
            return;
        }
        cduVar.h(jSONObject.getInt("imgType"));
    }

    private void a(cdu cduVar, JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject.isNull(str)) {
            return;
        }
        cduVar.f(jSONObject.getString(str));
    }

    private void d(cdu cduVar, JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject.isNull(str)) {
            return;
        }
        cduVar.b(jSONObject.getInt(str));
    }

    private cdy c(JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("TemplatePage_ConfiguredPageInteraction", "resolvePageModuleJsonData() pageModuleJsonObject is null.");
            return null;
        }
        cdy cdyVar = new cdy();
        try {
            if (!jSONObject.isNull("pageModuleId")) {
                cdyVar.c(jSONObject.getInt("pageModuleId"));
            }
            if (!jSONObject.isNull(CommonUtil.PAGE_TYPE)) {
                cdyVar.a(jSONObject.getInt(CommonUtil.PAGE_TYPE));
            }
            if (!jSONObject.isNull(CommonUtil.LAYOUT)) {
                cdyVar.b(jSONObject.getInt(CommonUtil.LAYOUT));
            }
            if (!jSONObject.isNull("name")) {
                cdyVar.d(jSONObject.getString("name"));
            }
            if (!jSONObject.isNull("weight")) {
                cdyVar.f(jSONObject.getInt("weight"));
            }
            if (!jSONObject.isNull("display")) {
                cdyVar.e(jSONObject.getInt("display"));
            }
            if (!jSONObject.isNull("moreInfoUrl")) {
                cdyVar.c(jSONObject.getString("moreInfoUrl"));
            }
            if (!jSONObject.isNull("extLayout")) {
                cdyVar.h(jSONObject.getInt("extLayout"));
            }
            if (!jSONObject.isNull("textPosition")) {
                cdyVar.j(jSONObject.getInt("textPosition"));
            }
            return cdyVar;
        } catch (JSONException unused) {
            LogUtil.b("TemplatePage_ConfiguredPageInteraction", "resolvePageModuleBasicInformation json data error!");
            return null;
        }
    }

    private boolean h(int i) {
        if (this.i.containsKey(Integer.valueOf(i))) {
            return true;
        }
        i(i);
        String b2 = SharedPreferenceManager.b(this.e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "templatePageModuleData" + i);
        try {
            if (!TextUtils.isEmpty(b2)) {
                JSONArray jSONArray = new JSONArray(b2);
                int length = jSONArray.length();
                ArrayList arrayList = new ArrayList(10);
                for (int i2 = 0; i2 < length; i2++) {
                    cdy d2 = d(jSONArray.getJSONObject(i2), i);
                    if (d2 != null && !arrayList.contains(d2)) {
                        arrayList.add(d2);
                    }
                }
                if (koq.c(arrayList)) {
                    this.i.put(Integer.valueOf(i), arrayList);
                    return true;
                }
            } else {
                LogUtil.h("TemplatePage_ConfiguredPageInteraction", "showCachedData() mPageModuleCached is empty.");
            }
        } catch (JSONException unused) {
            LogUtil.b("TemplatePage_ConfiguredPageInteraction", "initPageModuleMemoryCached resolve page module data exception.");
        }
        return false;
    }

    private boolean f(int i) {
        if (this.c.containsKey(Integer.valueOf(i))) {
            return true;
        }
        i(i);
        String b2 = SharedPreferenceManager.b(this.e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "templatePageActivitiesData" + i);
        try {
            if (!TextUtils.isEmpty(b2)) {
                JSONArray jSONArray = new JSONArray(b2);
                ArrayList arrayList = new ArrayList(10);
                int length = jSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    pff d2 = d(jSONArray.getJSONObject(i2));
                    if (d2 != null && !arrayList.contains(d2)) {
                        arrayList.add(d2);
                    }
                }
                if (koq.c(arrayList)) {
                    this.c.put(Integer.valueOf(i), arrayList);
                    return true;
                }
            } else {
                LogUtil.h("TemplatePage_ConfiguredPageInteraction", "showCachedData() mPageActivityCached is empty.");
            }
        } catch (JSONException unused) {
            LogUtil.b("TemplatePage_ConfiguredPageInteraction", "initActivityMemoryCached resolve page module data exception.");
        }
        return false;
    }

    private void d(List<String> list, final ResultCallback<String> resultCallback) {
        if (koq.b(list)) {
            LogUtil.h("TemplatePage_ConfiguredPageInteraction", "requestPageActivityCloudData() huaweiActivityIdList is empty.");
            return;
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        final JSONObject b2 = b(jSONArray);
        GRSManager.a(this.e).e("activityUrl", new GrsQueryCallback() { // from class: ryn.1
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.a("TemplatePage_ConfiguredPageInteraction", "requestPageActivityCloudData() GRSManager onCallBackSuccess ACTIVITY_KEY.");
                jbj.a(str + "/activity/getActivityInfoByIds", b2, resultCallback);
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h("TemplatePage_ConfiguredPageInteraction", "requestPageActivityCloudData() GRSManager onCallBackFail() errorCode = ", Integer.valueOf(i));
            }
        });
    }

    private JSONObject b(JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject(new ActivityInfoListFactory(BaseApplication.getContext()).getBody(null));
        try {
            jSONObject.put("activityIdList", jSONArray);
        } catch (JSONException unused) {
            LogUtil.b("TemplatePage_ConfiguredPageInteraction", "getActivityParams exception");
        }
        LogUtil.c("TemplatePage_ConfiguredPageInteraction", "getActivityParams() activityParams = ", jSONObject.toString());
        return jSONObject;
    }

    private void c(int i, final HttpResCallback httpResCallback) {
        final HashMap<String, String> e = e();
        e.put(CommonUtil.PAGE_TYPE, String.valueOf(i));
        LogUtil.a("TemplatePage_ConfiguredPageInteraction", "requestPageModuleCloudData() params = ", e.toString());
        final HashMap<String, String> a2 = a();
        GRSManager.a(this.e).e("messageCenterUrl", new GrsQueryCallback() { // from class: ryn.4
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.a("TemplatePage_ConfiguredPageInteraction", "requestPageModuleCloudData() GRSManager onCallBackSuccess URL_PAGE_MODULE.");
                jei.d(str + "/messageCenter/getPageModule" + CommonUtil.getUrlSuffix(), e, a2, httpResCallback);
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i2) {
                LogUtil.h("TemplatePage_ConfiguredPageInteraction", "requestPageModuleCloudData() GRSManager onCallBackFail() errorCode = ", Integer.valueOf(i2));
            }
        });
    }

    private HashMap<String, String> a() {
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put("x-huid", LoginInit.getInstance(this.e).getAccountInfo(1011));
        hashMap.put("x-version", health.compact.a.CommonUtil.c(this.e));
        return hashMap;
    }

    private HashMap<String, String> e() {
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put("deviceType", PhoneInfoUtils.getDeviceModel());
        hashMap.put("phoneType", PhoneInfoUtils.getHuaweiManufaturerOrEmui());
        String deviceId = LoginInit.getInstance(this.e).getDeviceId();
        if ("".equals(deviceId)) {
            deviceId = "clientnull";
        }
        hashMap.put("deviceId", deviceId);
        hashMap.put("sysVersion", Build.VERSION.RELEASE);
        hashMap.put("bindDeviceType", String.valueOf(health.compact.a.CommonUtil.h(this.e)));
        hashMap.put("wearType", "");
        hashMap.put("appType", String.valueOf(AppTypeUtils.getAppType()));
        hashMap.put("iVersion", String.valueOf(1));
        String accountInfo = LoginInit.getInstance(this.e).getAccountInfo(1010);
        hashMap.put("countryCode", accountInfo);
        hashMap.put("language", mtj.e(null));
        hashMap.put("ts", String.valueOf(pfh.g()));
        String accountInfo2 = LoginInit.getInstance(this.e).getAccountInfo(1008);
        if (!TextUtils.isEmpty(accountInfo2)) {
            try {
                hashMap.put("token", URLEncoder.encode(accountInfo2, StandardCharsets.UTF_8.name()));
            } catch (UnsupportedEncodingException e) {
                LogUtil.b("TemplatePage_ConfiguredPageInteraction", "token encode Exception ", e.toString());
            }
        }
        hashMap.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        hashMap.put("upDeviceType", LoginInit.getInstance(this.e).getDeviceType());
        if (LoginInit.getInstance(this.e).isLoginedByWear()) {
            hashMap.put("appId", "com.huawei.bone");
        } else {
            hashMap.put("appId", BaseApplication.getAppPackage());
        }
        boolean isOperation = OperationUtils.isOperation(accountInfo);
        String accountInfo3 = LoginInit.getInstance(this.e).getAccountInfo(1009);
        if (Utils.o() && isOperation) {
            hashMap.put("siteId", accountInfo3);
        }
        hashMap.put("currentManufacturer", Build.MANUFACTURER);
        return hashMap;
    }

    private void i(int i) {
        if (this.f16967a) {
            this.f16967a = false;
            String accountInfo = LoginInit.getInstance(this.e).getAccountInfo(1010);
            String b2 = SharedPreferenceManager.b(this.e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "templatePageLastLoginCountryCode");
            if (TextUtils.isEmpty(b2) || !b2.equals(accountInfo)) {
                SharedPreferenceManager.e(this.e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "templatePageLastLoginCountryCode", accountInfo, new StorageParams(1));
                e(i);
                return;
            }
            String accountInfo2 = LoginInit.getInstance(this.e).getAccountInfo(1011);
            String b3 = SharedPreferenceManager.b(this.e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "templatePageLastLoginUserId");
            if (TextUtils.isEmpty(b3) || !b3.equals(accountInfo2)) {
                SharedPreferenceManager.e(this.e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "templatePageLastLoginUserId", accountInfo2, new StorageParams(1));
                e(i);
            }
        }
    }

    public void e(int i) {
        SharedPreferenceManager.e(this.e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "templatePageModuleData" + i, "", new StorageParams());
        SharedPreferenceManager.e(this.e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "templatePageActivitiesData" + i, "", new StorageParams());
    }

    public void c() {
        ConcurrentHashMap<Integer, List<cdy>> concurrentHashMap = this.i;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
        ConcurrentHashMap<Integer, List<pff>> concurrentHashMap2 = this.c;
        if (concurrentHashMap2 != null) {
            concurrentHashMap2.clear();
        }
    }

    private String b(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "" : "pageOptimizationList" : "pageInformationList" : "pageActivityList" : "pagePlanList" : "pageServiceList";
    }
}
