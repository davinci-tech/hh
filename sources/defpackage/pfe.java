package defpackage;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.configuredpage.api.ConfiguredPageDataCallback;
import com.huawei.health.marketing.request.ActivityInfoListFactory;
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
import com.huawei.ui.main.stories.configuredpage.views.ConfiguredPageLinearLayout;
import com.huawei.ui.thirdpartservice.syncdata.constants.SyncDataConstant;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class pfe {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f16104a;
    private static Context e;
    private static ConcurrentHashMap<Integer, CopyOnWriteArrayList<cdy>> j = new ConcurrentHashMap<>(16);
    private static ConcurrentHashMap<Integer, ConfiguredPageDataCallback> c = new ConcurrentHashMap<>(16);
    private static ConcurrentHashMap<Integer, CopyOnWriteArrayList<cdy>> d = new ConcurrentHashMap<>(16);
    private static ConcurrentHashMap<Integer, CopyOnWriteArrayList<pff>> b = new ConcurrentHashMap<>(16);
    private static ConcurrentHashMap<String, Map<Integer, WeakReference<ConfiguredPageLinearLayout>>> f = new ConcurrentHashMap<>(16);

    public static void doh_(int i, LinearLayout linearLayout, ConfiguredPageDataCallback configuredPageDataCallback) {
        doi_(i, linearLayout, false, configuredPageDataCallback);
    }

    public static void doi_(final int i, LinearLayout linearLayout, boolean z, ConfiguredPageDataCallback configuredPageDataCallback) {
        ConcurrentHashMap<Integer, ConfiguredPageDataCallback> concurrentHashMap;
        ConfiguredPageDataCallback configuredPageDataCallback2;
        final LinearLayout linearLayout2 = (LinearLayout) new WeakReference(linearLayout).get();
        if (linearLayout2 == null) {
            LogUtil.a("ConfiguredPage_ConfiguredPageInteraction", "layout is null");
            return;
        }
        if (!Utils.o() || LoginInit.getInstance(e).isKidAccount()) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "use marketing 2.0.");
            linearLayout2.setVisibility(8);
            return;
        }
        if (!z && OperationUtils.switchToMarketingTwo()) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "initOperationConfiguredPage, using marketing 2.0");
            linearLayout2.setVisibility(8);
            return;
        }
        e = BaseApplication.getContext();
        linearLayout2.setId(View.generateViewId());
        LogUtil.a("ConfiguredPage_ConfiguredPageInteraction", "initOperationConfiguredPage() pageType = ", Integer.valueOf(i), ", layout = ", Integer.valueOf(linearLayout2.getId()));
        if (configuredPageDataCallback != null) {
            c.put(Integer.valueOf(i), configuredPageDataCallback);
        }
        boolean doj_ = doj_(i, linearLayout2);
        boolean dog_ = dog_(i, linearLayout2);
        if (doj_) {
            if (dog_) {
                j = e(i, j, b);
            }
            doq_(i, linearLayout2);
        } else {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "initOperationConfiguredPage() no memory cached.");
        }
        if (!lop.c(e)) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "initOperationConfiguredPage() network is not connected. return back!");
            if (doj_ || dog_ || (concurrentHashMap = c) == null || concurrentHashMap.size() <= 0 || !c.containsKey(Integer.valueOf(i)) || (configuredPageDataCallback2 = c.get(Integer.valueOf(i))) == null) {
                return;
            }
            configuredPageDataCallback2.getConfiguredDataSize(0);
            configuredPageDataCallback2.onDataResponse(null);
            return;
        }
        a(i, new HttpResCallback() { // from class: pfe.4
            @Override // com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback
            public void onFinished(int i2, String str) {
                if (i2 != 200 || TextUtils.isEmpty(str)) {
                    LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "/getPageModule resCode = ", Integer.valueOf(i2), " or result is empty.");
                    pfe.doq_(i, linearLayout2);
                } else {
                    LogUtil.c("ConfiguredPage_ConfiguredPageInteraction", "getConfiguredPageDataFromCloud() /getPageModule result = ", str);
                    pfe.dof_(str, i, linearLayout2);
                }
            }
        });
    }

    private static void don_(int i, LinearLayout linearLayout) {
        ConcurrentHashMap<Integer, CopyOnWriteArrayList<cdy>> concurrentHashMap = j;
        if (concurrentHashMap == null) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "removePageModuleData() mPageModuleObjectMap is null.");
            return;
        }
        if (koq.c(concurrentHashMap.get(Integer.valueOf(i)))) {
            j.remove(Integer.valueOf(i));
        }
        doq_(i, linearLayout);
    }

    private static void dom_(int i, LinearLayout linearLayout) {
        ConcurrentHashMap<Integer, CopyOnWriteArrayList<cdy>> concurrentHashMap = j;
        if (concurrentHashMap == null) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "removeHuaweiActivityData() mActivityObjectMap is null.");
            return;
        }
        if (concurrentHashMap.containsKey(Integer.valueOf(i))) {
            CopyOnWriteArrayList<cdy> copyOnWriteArrayList = j.get(Integer.valueOf(i));
            if (koq.b(copyOnWriteArrayList)) {
                return;
            }
            CopyOnWriteArrayList<cdy> copyOnWriteArrayList2 = new CopyOnWriteArrayList<>();
            Iterator<cdy> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                cdy next = it.next();
                if (next != null && next.b() != 3) {
                    copyOnWriteArrayList2.add(next);
                }
            }
            j.remove(Integer.valueOf(i));
            j.put(Integer.valueOf(i), copyOnWriteArrayList2);
            doq_(i, linearLayout);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dof_(String str, int i, LinearLayout linearLayout) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("resultCode");
            if (!"0".equals(string)) {
                LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "hasPageModuleJsonData /getPageModule resultCode = ", string, ", resultDesc = ", jSONObject.getString("resultDesc"));
                return;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("pageModuleList");
            int length = jSONArray.length();
            if (length == 0) {
                LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "/getPageModule pageModuleListJsonArray is empty! no pageModuleList!");
                don_(i, linearLayout);
                return;
            }
            ConcurrentHashMap<Integer, CopyOnWriteArrayList<cdy>> concurrentHashMap = j;
            if (concurrentHashMap != null && concurrentHashMap.containsKey(Integer.valueOf(i))) {
                CopyOnWriteArrayList<cdy> copyOnWriteArrayList = j.get(Integer.valueOf(i));
                if (koq.c(copyOnWriteArrayList) && copyOnWriteArrayList.toString().equals(jSONArray.toString())) {
                    dob_(i, linearLayout);
                    return;
                }
            }
            CopyOnWriteArrayList<cdy> copyOnWriteArrayList2 = new CopyOnWriteArrayList<>();
            for (int i2 = 0; i2 < length; i2++) {
                cdy d2 = d(jSONArray.getJSONObject(i2), i);
                if (d2 != null && !copyOnWriteArrayList2.contains(d2)) {
                    copyOnWriteArrayList2.add(d2);
                }
                LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "getPageModuleData() pageModuleObject is null or contains");
            }
            if (koq.b(copyOnWriteArrayList2)) {
                LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "/getPageModule pageModuleObjectList is empty! ");
                don_(i, linearLayout);
                return;
            }
            j.put(Integer.valueOf(i), copyOnWriteArrayList2);
            SharedPreferenceManager.e(e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "configuredPageModuleData" + i, jSONArray.toString(), new StorageParams());
            dob_(i, linearLayout);
        } catch (JSONException e2) {
            LogUtil.b("ConfiguredPage_ConfiguredPageInteraction", "parse page module data meet exception.", e2.getMessage());
        }
    }

    private static void dob_(int i, LinearLayout linearLayout) {
        ConcurrentHashMap<Integer, CopyOnWriteArrayList<cdy>> concurrentHashMap = j;
        if (concurrentHashMap == null || concurrentHashMap.size() <= 0) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "dealPageActivityData() mPageModuleObjectMap is empty.");
            return;
        }
        CopyOnWriteArrayList<cdy> copyOnWriteArrayList = j.get(Integer.valueOf(i));
        if (koq.b(copyOnWriteArrayList)) {
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        Iterator<cdy> it = copyOnWriteArrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            cdy next = it.next();
            if (next != null && next.b() == 3) {
                List<cdu> e2 = next.e();
                if (koq.b(e2)) {
                    return;
                }
                for (cdu cduVar : e2) {
                    if (cduVar == null || cduVar.a() != 1) {
                        i2++;
                    } else {
                        String b2 = cduVar.b();
                        if (TextUtils.isEmpty(b2) || arrayList.contains(b2)) {
                            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "dealPageActivityData() activityDetailId is empty or already exists");
                        } else {
                            arrayList.add(b2);
                        }
                    }
                }
            }
        }
        if (i2 > 0 && koq.b(arrayList)) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "dealPageActivityData() only tripartite activity.");
            return;
        }
        if (i2 == 0 && koq.b(arrayList)) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "dealPageActivityData() huaweiActivityDetailIdList is empty.");
            dom_(i, linearLayout);
        } else {
            LogUtil.a("ConfiguredPage_ConfiguredPageInteraction", "dealPageActivityData() mHuaweiActivityIdList = ", arrayList.toString());
            doc_(i, linearLayout, arrayList);
        }
    }

    private static void doc_(final int i, final LinearLayout linearLayout, List<String> list) {
        c(list, new ResultCallback<String>() { // from class: pfe.2
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                LogUtil.a("ConfiguredPage_ConfiguredPageInteraction", "dealPageActivityData success:", str);
                pfe.doo_(str, i, linearLayout);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.b("ConfiguredPage_ConfiguredPageInteraction", "dealPageActivityData fail:");
                pfe.doq_(i, linearLayout);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void doq_(int i, LinearLayout linearLayout) {
        dor_(i, linearLayout, true);
    }

    private static void dor_(final int i, final LinearLayout linearLayout, final boolean z) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            HandlerExecutor.a(new Runnable() { // from class: pfe.5
                @Override // java.lang.Runnable
                public void run() {
                    pfe.dol_(i, linearLayout, z);
                }
            });
        } else {
            dol_(i, linearLayout, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void doo_(String str, int i, LinearLayout linearLayout) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "/resolveHuaweiActivityJsonData result is empty! ");
            doq_(i, linearLayout);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("resultCode");
            if (!"0".equals(string)) {
                LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "/resolveHuaweiActivityJsonData resultCode = ", string, ", resultDesc = ", jSONObject.getString("resultDesc"));
                doq_(i, linearLayout);
                return;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("pageActivityList");
            int length = jSONArray.length();
            if (length == 0) {
                LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "/resolveHuaweiActivityJsonData no pageActivityList.");
                dom_(i, linearLayout);
                return;
            }
            if (b.containsKey(Integer.valueOf(i))) {
                CopyOnWriteArrayList<pff> copyOnWriteArrayList = b.get(Integer.valueOf(i));
                if (koq.c(copyOnWriteArrayList) && copyOnWriteArrayList.toString().equals(jSONArray.toString())) {
                    doq_(i, linearLayout);
                    return;
                }
            }
            SharedPreferenceManager.e(e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "configuredPageActivitiesData" + i, jSONArray.toString(), new StorageParams());
            b(i, jSONObject);
            CopyOnWriteArrayList<pff> copyOnWriteArrayList2 = new CopyOnWriteArrayList<>();
            for (int i2 = 0; i2 < length; i2++) {
                pff d2 = d(jSONArray.getJSONObject(i2));
                if (d2 != null && !copyOnWriteArrayList2.contains(d2)) {
                    copyOnWriteArrayList2.add(d2);
                }
            }
            if (koq.b(copyOnWriteArrayList2)) {
                dom_(i, linearLayout);
                return;
            }
            b.put(Integer.valueOf(i), copyOnWriteArrayList2);
            j = e(i, j, b);
            doq_(i, linearLayout);
        } catch (JSONException e2) {
            LogUtil.b("ConfiguredPage_ConfiguredPageInteraction", "Json data error! JSONException:", e2.getMessage());
            doq_(i, linearLayout);
        }
    }

    private static void b(int i, JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("currentTime")) {
            String string = jSONObject.getString("currentTime");
            StorageParams storageParams = new StorageParams();
            SharedPreferenceManager.e(e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "configuredPageActivityServerCurrentTime" + i, string, storageParams);
        }
    }

    public static void dok_(int i, LinearLayout linearLayout) {
        dol_(i, linearLayout, false);
    }

    public static void dol_(int i, LinearLayout linearLayout, boolean z) {
        Context context = BaseApplication.getContext();
        e = context;
        if (LoginInit.getInstance(context).isKidAccount()) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "use marketing 2.0. or kidAccount");
            return;
        }
        if (!Utils.o()) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "use marketing 2.0. or kidAccount");
            return;
        }
        if (!z && OperationUtils.switchToMarketingTwo()) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "refreshConfiguredPageUi, using marketing 2.0");
            linearLayout.setVisibility(8);
            return;
        }
        LogUtil.a("ConfiguredPage_ConfiguredPageInteraction", "refreshConfiguredPageUi() ENTER. pageType = ", Integer.valueOf(i));
        if (linearLayout == null) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "refreshConfiguredPageUi() layout is null.");
            return;
        }
        ConcurrentHashMap<Integer, ConfiguredPageDataCallback> concurrentHashMap = c;
        ConfiguredPageDataCallback configuredPageDataCallback = (concurrentHashMap == null || concurrentHashMap.size() <= 0 || !c.containsKey(Integer.valueOf(i))) ? null : c.get(Integer.valueOf(i));
        ConcurrentHashMap<Integer, CopyOnWriteArrayList<cdy>> concurrentHashMap2 = j;
        if (concurrentHashMap2 == null || concurrentHashMap2.size() <= 0) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "refreshConfiguredPageUi() mPageModuleObjectMap is null.");
            if (configuredPageDataCallback != null) {
                configuredPageDataCallback.getConfiguredDataSize(0);
                configuredPageDataCallback.onDataResponse(null);
            }
            linearLayout.removeAllViews();
            return;
        }
        CopyOnWriteArrayList<cdy> copyOnWriteArrayList = j.get(Integer.valueOf(i));
        CopyOnWriteArrayList<cdy> copyOnWriteArrayList2 = d.get(Integer.valueOf(i));
        if (!koq.b(copyOnWriteArrayList2)) {
            if (f16104a) {
                if (koq.b(copyOnWriteArrayList)) {
                    copyOnWriteArrayList = copyOnWriteArrayList2;
                } else {
                    copyOnWriteArrayList.removeAll(copyOnWriteArrayList2);
                    copyOnWriteArrayList.addAll(copyOnWriteArrayList2);
                }
                Collections.sort(copyOnWriteArrayList, new Comparator<cdy>() { // from class: pfe.3
                    @Override // java.util.Comparator
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public int compare(cdy cdyVar, cdy cdyVar2) {
                        return cdyVar2.j() - cdyVar.j();
                    }
                });
            } else if (koq.c(copyOnWriteArrayList)) {
                copyOnWriteArrayList.removeAll(copyOnWriteArrayList2);
            }
        }
        if (koq.b(copyOnWriteArrayList)) {
            linearLayout.removeAllViews();
            return;
        }
        Map<Integer, WeakReference<ConfiguredPageLinearLayout>> map = f.get(dod_(i, linearLayout));
        if (map == null || map.size() <= 0) {
            map = new HashMap<>();
        }
        linearLayout.removeAllViews();
        Iterator<cdy> it = copyOnWriteArrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 = doe_(linearLayout, map, i2, it.next());
        }
        if (i == 16 && map.size() > 0 && !f.contains(map)) {
            f.put(dod_(i, linearLayout), map);
        }
        LogUtil.a("ConfiguredPage_ConfiguredPageInteraction", "refreshConfiguredPageUi() size = ", Integer.valueOf(copyOnWriteArrayList.size()), ", viewInvisibleNumber = ", Integer.valueOf(i2));
        nsy.cMA_(linearLayout, linearLayout.getChildCount() != 0 ? 0 : 8);
        if (configuredPageDataCallback != null) {
            configuredPageDataCallback.getConfiguredDataSize(copyOnWriteArrayList.size() - i2);
            configuredPageDataCallback.onDataResponse(copyOnWriteArrayList);
        }
    }

    private static int doe_(LinearLayout linearLayout, Map<Integer, WeakReference<ConfiguredPageLinearLayout>> map, int i, cdy cdyVar) {
        WeakReference<ConfiguredPageLinearLayout> weakReference;
        ConfiguredPageLinearLayout configuredPageLinearLayout;
        if (cdyVar == null || linearLayout == null) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "getViewInvisibleNumber() pageModuleObject or layout is null.");
            return i;
        }
        if (cdyVar.b() == 5 && CommonUtil.bf()) {
            return i + 1;
        }
        WeakReference<ConfiguredPageLinearLayout> weakReference2 = map.get(Integer.valueOf(cdyVar.f()));
        if (weakReference2 == null) {
            configuredPageLinearLayout = new ConfiguredPageLinearLayout(e);
            configuredPageLinearLayout.setId(cdyVar.f());
            configuredPageLinearLayout.b(cdyVar);
            weakReference = new WeakReference<>(configuredPageLinearLayout);
        } else {
            ConfiguredPageLinearLayout configuredPageLinearLayout2 = weakReference2.get();
            if (configuredPageLinearLayout2 != null) {
                configuredPageLinearLayout2.c(cdyVar);
            }
            weakReference = weakReference2;
            configuredPageLinearLayout = configuredPageLinearLayout2;
        }
        if (configuredPageLinearLayout == null || !configuredPageLinearLayout.c()) {
            return i + 1;
        }
        ViewParent parent = configuredPageLinearLayout.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(configuredPageLinearLayout);
        }
        linearLayout.addView(configuredPageLinearLayout);
        if (map.containsKey(Integer.valueOf(cdyVar.f()))) {
            return i;
        }
        map.put(Integer.valueOf(cdyVar.f()), weakReference);
        return i;
    }

    private static String dod_(int i, LinearLayout linearLayout) {
        if (linearLayout == null) {
            return String.valueOf(i);
        }
        return String.valueOf(i) + linearLayout.getId();
    }

    private static ConcurrentHashMap<Integer, CopyOnWriteArrayList<cdy>> e(int i, ConcurrentHashMap<Integer, CopyOnWriteArrayList<cdy>> concurrentHashMap, ConcurrentHashMap<Integer, CopyOnWriteArrayList<pff>> concurrentHashMap2) {
        if (concurrentHashMap2 != null && concurrentHashMap2.size() > 0) {
            CopyOnWriteArrayList<cdy> copyOnWriteArrayList = concurrentHashMap.get(Integer.valueOf(i));
            if (koq.b(copyOnWriteArrayList)) {
                return concurrentHashMap;
            }
            CopyOnWriteArrayList<pff> copyOnWriteArrayList2 = concurrentHashMap2.get(Integer.valueOf(i));
            if (koq.b(copyOnWriteArrayList2)) {
                return concurrentHashMap;
            }
            Iterator<cdy> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                cdy next = it.next();
                if (next != null && next.b() == 3) {
                    List<cdu> e2 = next.e();
                    if (koq.b(e2)) {
                        LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "combineModuleActivityList() cardItemObjectList is empty.");
                    } else {
                        for (cdu cduVar : e2) {
                            if (cduVar != null && cduVar.a() == 1) {
                                String b2 = cduVar.b();
                                Iterator<pff> it2 = copyOnWriteArrayList2.iterator();
                                while (it2.hasNext()) {
                                    pff next2 = it2.next();
                                    if (next2 != null && b2.equals(next2.e())) {
                                        cduVar.d(next2.a());
                                        cduVar.e(next2.d());
                                        cduVar.e(next2.b());
                                        cduVar.q(next2.c());
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

    private static cdy d(JSONObject jSONObject, int i) {
        int i2;
        if (jSONObject == null) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "resolvePageModuleJsonData() pageModuleJsonObject is null.");
            return null;
        }
        try {
            cdy e2 = e(jSONObject);
            if (e2 == null) {
                LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "resolvePageModuleJsonData() pageModuleObject is null.");
                return null;
            }
            if (jSONObject.isNull("moduleType")) {
                i2 = 0;
            } else {
                i2 = jSONObject.getInt("moduleType");
                e2.d(i2);
            }
            JSONObject jSONObject2 = new JSONObject();
            if (!jSONObject.isNull("pageModuleDetail")) {
                jSONObject2 = jSONObject.getJSONObject("pageModuleDetail");
            }
            String a2 = a(i2);
            if (TextUtils.isEmpty(a2)) {
                LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "resolvePageModuleJsonData() detailJsonKey is empty. moduleType = ", Integer.valueOf(i2));
                return null;
            }
            JSONArray jSONArray = jSONObject2.getJSONArray(a2);
            int length = jSONArray.length();
            if (length == 0) {
                LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "resolvePageModuleJsonData() pageModuleDetailJsonArrayLength == 0.detailJsonKey = ", a2);
                return null;
            }
            for (int i3 = 0; i3 < length; i3++) {
                cdu a3 = a(jSONArray.getJSONObject(i3), i2);
                if (a3 == null) {
                    LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "resolvePageModuleJsonData() cardItemObject is null.");
                } else {
                    a3.n(i);
                    e2.e(a3);
                }
            }
            return e2;
        } catch (JSONException e3) {
            LogUtil.b("ConfiguredPage_ConfiguredPageInteraction", "expoundConfigureModule Json data error! JSONException:", e3.getMessage());
            return null;
        }
    }

    private static pff d(JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "expoundActivityItemObject() jsonActivityObject is null.");
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
            if (!jSONObject.isNull("timeZone")) {
                pffVar.a(jSONObject.getString("timeZone"));
            }
            return pffVar;
        } catch (JSONException e2) {
            LogUtil.b("ConfiguredPage_ConfiguredPageInteraction", "expoundActivityItemObject Json data error! JSONException:", e2.getMessage());
            return null;
        }
    }

    private static cdu a(JSONObject jSONObject, int i) {
        if (jSONObject == null) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "expoundActivityItemObject() jsonObject is null.");
            return null;
        }
        cdu cduVar = new cdu();
        try {
            cduVar.c(pfg.a(jSONObject, "pageModuleId"));
            cduVar.h(pfg.d(jSONObject, "imgUrl"));
            cduVar.i(pfg.d(jSONObject, "ux10ImgUrl"));
            if (!jSONObject.isNull("imgUrlDaxi")) {
                cduVar.j(jSONObject.getString("imgUrlDaxi"));
            } else if (!jSONObject.isNull("imgUrl")) {
                cduVar.j(jSONObject.getString("imgUrl"));
            } else {
                LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "expoundCardItemObject() imageUrlTahiti and imageUrl are empty");
            }
            cduVar.a(pfg.d(jSONObject, "detailUrl"));
            cduVar.f(pfg.a(jSONObject, "weight"));
            if (!jSONObject.isNull("display")) {
                cduVar.d(jSONObject.getInt("display"));
            }
            cduVar.c(pfg.d(jSONObject, "description"));
            if (!jSONObject.isNull("desDisplay")) {
                cduVar.i(jSONObject.getInt("desDisplay"));
            }
            if (!jSONObject.isNull("labelType")) {
                cduVar.m(jSONObject.getInt("labelType"));
            }
            if (!jSONObject.isNull("heatCount")) {
                cduVar.j(jSONObject.getInt("heatCount"));
            }
            cduVar.k(pfg.d(jSONObject, "pageAttribute"));
            cduVar.n(pfg.d(jSONObject, "pageLabel"));
            cduVar.o(pfg.d(jSONObject, "pageAttributeKey"));
            return e(i, cduVar, jSONObject);
        } catch (JSONException e2) {
            LogUtil.b("ConfiguredPage_ConfiguredPageInteraction", "expoundCardItemObject Json data error! JSONException:", e2.getMessage());
            return null;
        }
    }

    private static cdu e(int i, cdu cduVar, JSONObject jSONObject) {
        try {
            if (i == 1) {
                c(cduVar, jSONObject, "serviceId");
                e(cduVar, jSONObject, JsUtil.SERVICE_NAME);
                cduVar.g(pfg.a(jSONObject, "serviceUrlType"));
                b(cduVar, jSONObject);
            } else if (i == 2) {
                c(cduVar, jSONObject, "planId");
                e(cduVar, jSONObject, "planName");
                cduVar.g(pfg.d(jSONObject, "planDco"));
            } else if (i == 3) {
                c(cduVar, jSONObject, "activityId");
                e(cduVar, jSONObject, "activityName");
                cduVar.a(pfg.a(jSONObject, SyncDataConstant.BI_KEY_ACTIVITY_TYPE));
                cduVar.b(pfg.d(jSONObject, "activityDetailId"));
                cduVar.k(pfg.a(jSONObject, "informationId"));
                cduVar.l(pfg.a(jSONObject, "readCount"));
            } else {
                if (i != 4) {
                    if (i == 5) {
                        c(cduVar, jSONObject, "optimizationId");
                        e(cduVar, jSONObject, "optimizationName");
                        d(cduVar, jSONObject);
                    }
                    return cduVar;
                }
                c(cduVar, jSONObject, "infoId");
                e(cduVar, jSONObject, "infoName");
                d(cduVar, jSONObject);
            }
            return cduVar;
        } catch (JSONException e2) {
            LogUtil.b("ConfiguredPage_ConfiguredPageInteraction", "getModuleTypeCard() Json data error! JSONException:", e2.getMessage());
            return null;
        }
    }

    private static void b(cdu cduVar, JSONObject jSONObject) throws JSONException {
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

    private static void d(cdu cduVar, JSONObject jSONObject) throws JSONException {
        if (jSONObject.isNull("imgType")) {
            return;
        }
        cduVar.h(jSONObject.getInt("imgType"));
    }

    private static void e(cdu cduVar, JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject.isNull(str)) {
            return;
        }
        cduVar.f(jSONObject.getString(str));
    }

    private static void c(cdu cduVar, JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject.isNull(str)) {
            return;
        }
        cduVar.b(jSONObject.getInt(str));
    }

    private static cdy e(JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "resolvePageModuleJsonData() pageModuleJsonObject is null.");
            return null;
        }
        cdy cdyVar = new cdy();
        try {
            if (!jSONObject.isNull("pageModuleId")) {
                cdyVar.c(jSONObject.getInt("pageModuleId"));
            }
            if (!jSONObject.isNull(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE)) {
                cdyVar.a(jSONObject.getInt(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE));
            }
            if (!jSONObject.isNull(com.huawei.health.messagecenter.model.CommonUtil.LAYOUT)) {
                cdyVar.b(jSONObject.getInt(com.huawei.health.messagecenter.model.CommonUtil.LAYOUT));
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
        } catch (JSONException e2) {
            LogUtil.b("ConfiguredPage_ConfiguredPageInteraction", "resolvePageModuleBasicInformation json data error! JSONException:", e2.getMessage());
            return null;
        }
    }

    private static boolean doj_(int i, LinearLayout linearLayout) {
        dop_(i, linearLayout);
        if (j.containsKey(Integer.valueOf(i))) {
            return true;
        }
        String b2 = SharedPreferenceManager.b(e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "configuredPageModuleData" + i);
        try {
            if (!TextUtils.isEmpty(b2)) {
                JSONArray jSONArray = new JSONArray(b2);
                int length = jSONArray.length();
                CopyOnWriteArrayList<cdy> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
                for (int i2 = 0; i2 < length; i2++) {
                    cdy d2 = d(jSONArray.getJSONObject(i2), i);
                    if (d2 != null && !copyOnWriteArrayList.contains(d2)) {
                        copyOnWriteArrayList.add(d2);
                    }
                }
                if (koq.c(copyOnWriteArrayList)) {
                    j.put(Integer.valueOf(i), copyOnWriteArrayList);
                    return true;
                }
            } else {
                LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "showCachedData() mPageModuleCached is empty.");
            }
            return false;
        } catch (JSONException unused) {
            LogUtil.b("ConfiguredPage_ConfiguredPageInteraction", "initPageModuleMemoryCached resolve page module data exception.");
            return false;
        }
    }

    private static boolean dog_(int i, LinearLayout linearLayout) {
        dop_(i, linearLayout);
        if (b.containsKey(Integer.valueOf(i))) {
            return true;
        }
        String b2 = SharedPreferenceManager.b(e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "configuredPageActivitiesData" + i);
        try {
            if (!TextUtils.isEmpty(b2)) {
                JSONArray jSONArray = new JSONArray(b2);
                CopyOnWriteArrayList<pff> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
                int length = jSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    pff d2 = d(jSONArray.getJSONObject(i2));
                    if (d2 != null && !copyOnWriteArrayList.contains(d2)) {
                        copyOnWriteArrayList.add(d2);
                    }
                }
                if (koq.c(copyOnWriteArrayList)) {
                    b.put(Integer.valueOf(i), copyOnWriteArrayList);
                    return true;
                }
            } else {
                LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "showCachedData() mPageActivityCached is empty.");
            }
        } catch (JSONException unused) {
            LogUtil.b("ConfiguredPage_ConfiguredPageInteraction", "initActivityMemoryCached resolve page module data exception.");
        }
        return false;
    }

    private static void c(List<String> list, final ResultCallback<String> resultCallback) {
        if (koq.b(list)) {
            LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "requestPageActivityCloudData() huaweiActivityIdList is empty.");
            return;
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        final JSONObject d2 = d(jSONArray);
        GRSManager.a(e).e("activityUrl", new GrsQueryCallback() { // from class: pfe.1
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.a("ConfiguredPage_ConfiguredPageInteraction", "requestPageActivityCloudData() GRSManager onCallBackSuccess ACTIVITY_KEY.");
                jbj.a(str + "/activity/getActivityInfoByIds", d2, resultCallback);
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "requestPageActivityCloudData() GRSManager onCallBackFail() errorCode = ", Integer.valueOf(i));
            }
        });
    }

    private static JSONObject d(JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject(new ActivityInfoListFactory(BaseApplication.getContext()).getBody(null));
        try {
            jSONObject.put("activityIdList", jSONArray);
        } catch (JSONException e2) {
            LogUtil.b("ConfiguredPage_ConfiguredPageInteraction", "getActivityParams ", e2.getMessage());
        }
        LogUtil.a("ConfiguredPage_ConfiguredPageInteraction", "getActivityParams() activityParams = ", jSONObject.toString());
        return jSONObject;
    }

    private static void a(int i, final HttpResCallback httpResCallback) {
        final HashMap<String, String> e2 = e();
        e2.put(com.huawei.health.messagecenter.model.CommonUtil.PAGE_TYPE, String.valueOf(i));
        LogUtil.a("ConfiguredPage_ConfiguredPageInteraction", "requestPageModuleCloudData() params = ", e2.toString());
        final HashMap<String, String> a2 = a();
        GRSManager.a(e).e("messageCenterUrl", new GrsQueryCallback() { // from class: pfe.6
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.a("ConfiguredPage_ConfiguredPageInteraction", "requestPageModuleCloudData() GRSManager onCallBackSuccess URL_PAGE_MODULE.");
                pfe.d(str + "/messageCenter/getPageModule" + com.huawei.health.messagecenter.model.CommonUtil.getUrlSuffix(), e2, a2, httpResCallback);
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i2) {
                LogUtil.h("ConfiguredPage_ConfiguredPageInteraction", "requestPageModuleCloudData() GRSManager onCallBackFail() errorCode = ", Integer.valueOf(i2));
                httpResCallback.onFinished(-1, "");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(final String str, final HashMap<String, String> hashMap, final HashMap<String, String> hashMap2, final HttpResCallback httpResCallback) {
        if (mds.a()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: pfe.7
                @Override // java.lang.Runnable
                public void run() {
                    pfe.d(str, hashMap, hashMap2, httpResCallback);
                }
            });
            return;
        }
        String b2 = jei.b(str, hashMap, hashMap2);
        if (TextUtils.isEmpty(b2)) {
            httpResCallback.onFinished(-1, "");
        } else {
            httpResCallback.onFinished(200, b2);
        }
    }

    public static HashMap<String, String> a() {
        HashMap<String, String> hashMap = new HashMap<>(16);
        if (!LoginInit.getInstance(e).isBrowseMode()) {
            hashMap.put("x-huid", LoginInit.getInstance(e).getAccountInfo(1011));
        }
        hashMap.put("x-version", CommonUtil.c(e));
        return hashMap;
    }

    public static HashMap<String, String> e() {
        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put("deviceType", PhoneInfoUtils.getDeviceModel());
        hashMap.put("phoneType", PhoneInfoUtils.getHuaweiManufaturerOrEmui());
        String deviceId = LoginInit.getInstance(e).getDeviceId();
        if ("".equals(deviceId)) {
            deviceId = "clientnull";
        }
        hashMap.put("deviceId", deviceId);
        hashMap.put("sysVersion", Build.VERSION.RELEASE);
        hashMap.put("bindDeviceType", String.valueOf(CommonUtil.h(e)));
        hashMap.put("wearType", "");
        hashMap.put("appType", String.valueOf(AppTypeUtils.getAppType()));
        hashMap.put("iVersion", String.valueOf(1));
        hashMap.put("countryCode", LoginInit.getInstance(e).getAccountInfo(1010));
        hashMap.put("language", mtj.e(null));
        hashMap.put("ts", String.valueOf(pfh.g()));
        String accountInfo = LoginInit.getInstance(e).getAccountInfo(1008);
        if (!TextUtils.isEmpty(accountInfo)) {
            try {
                hashMap.put("token", URLEncoder.encode(accountInfo, StandardCharsets.UTF_8.name()));
            } catch (UnsupportedEncodingException e2) {
                LogUtil.b("ConfiguredPage_ConfiguredPageInteraction", "token encode Exception ", e2.toString());
            }
        }
        hashMap.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        hashMap.put("upDeviceType", LoginInit.getInstance(e).getDeviceType());
        if (LoginInit.getInstance(e).isLoginedByWear()) {
            hashMap.put("appId", "com.huawei.bone");
        } else {
            hashMap.put("appId", BaseApplication.getAppPackage());
        }
        hashMap.put("siteId", LoginInit.getInstance(e).getAccountInfo(1009));
        hashMap.put("currentManufacturer", Build.MANUFACTURER);
        return hashMap;
    }

    private static void dop_(int i, LinearLayout linearLayout) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        String b2 = SharedPreferenceManager.b(e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "configuredPageLastLoginCountryCode");
        if (TextUtils.isEmpty(b2) || !b2.equals(accountInfo)) {
            SharedPreferenceManager.e(e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "configuredPageLastLoginCountryCode", accountInfo, new StorageParams(1));
            doa_(i, linearLayout);
            return;
        }
        String accountInfo2 = LoginInit.getInstance(e).getAccountInfo(1011);
        String b3 = SharedPreferenceManager.b(e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "configuredPageLastLoginUserId");
        if (TextUtils.isEmpty(b3) || !b3.equals(accountInfo2)) {
            SharedPreferenceManager.e(e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "configuredPageLastLoginUserId", accountInfo2, new StorageParams(1));
            doa_(i, linearLayout);
        }
    }

    private static void doa_(int i, LinearLayout linearLayout) {
        for (int i2 = 1; i2 <= 22; i2++) {
            SharedPreferenceManager.e(e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "configuredPageModuleData" + i2, "", new StorageParams());
            SharedPreferenceManager.e(e, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "configuredPageActivitiesData" + i2, "", new StorageParams());
        }
        ConcurrentHashMap<String, Map<Integer, WeakReference<ConfiguredPageLinearLayout>>> concurrentHashMap = f;
        if (concurrentHashMap != null) {
            Map<Integer, WeakReference<ConfiguredPageLinearLayout>> map = concurrentHashMap.get(dod_(i, linearLayout));
            if (map != null && map.size() > 0) {
                map.clear();
            }
            f.clear();
        }
        ConcurrentHashMap<Integer, CopyOnWriteArrayList<cdy>> concurrentHashMap2 = j;
        if (concurrentHashMap2 != null) {
            concurrentHashMap2.clear();
        }
        ConcurrentHashMap<Integer, CopyOnWriteArrayList<pff>> concurrentHashMap3 = b;
        if (concurrentHashMap3 != null) {
            concurrentHashMap3.clear();
        }
    }

    public static void dos_(int i, LinearLayout linearLayout, CopyOnWriteArrayList<cdy> copyOnWriteArrayList) {
        if (koq.b(copyOnWriteArrayList)) {
            f16104a = false;
            dor_(i, linearLayout, false);
        } else {
            f16104a = true;
            d.clear();
            d.put(Integer.valueOf(i), copyOnWriteArrayList);
            dor_(i, linearLayout, false);
        }
    }

    private static String a(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "" : "pageOptimizationList" : "pageInformationList" : "pageActivityList" : "pagePlanList" : "pageServiceList";
    }
}
