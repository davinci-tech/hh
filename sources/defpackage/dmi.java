package defpackage;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.marketing.datatype.MarketingOption;
import com.huawei.health.marketing.datatype.PositionIdSet;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.utils.OperationUtilsApi;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dmi {
    public static void e(MarketingOption marketingOption) {
        int pageId = marketingOption.getPageId();
        ArrayList<Integer> positionIdList = PositionIdSet.getPositionIdList(pageId);
        String extendParam = marketingOption.getExtendParam();
        if (positionIdList.isEmpty()) {
            LogUtil.e("R_MarketingDataProvider", "requestByMarketingOption failed, positionId is empty, pageID: " + pageId);
        } else {
            Task<Map<Integer, ResourceResultInfo>> d = d(positionIdList, extendParam);
            dms.e().b(String.valueOf(pageId), d);
            d.addOnSuccessListener(new AnonymousClass3(marketingOption, pageId));
        }
    }

    /* renamed from: dmi$3, reason: invalid class name */
    class AnonymousClass3 implements OnSuccessListener<Map<Integer, ResourceResultInfo>> {
        final /* synthetic */ MarketingOption c;
        final /* synthetic */ int e;

        AnonymousClass3(MarketingOption marketingOption, int i) {
            this.c = marketingOption;
            this.e = i;
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final Map<Integer, ResourceResultInfo> map) {
            if (map == null) {
                LogUtil.d("R_MarketingDataProvider", "ResultInfoMap is null.");
            } else {
                if (this.c.getContext() == null) {
                    LogUtil.e("R_MarketingDataProvider", "caller is dead, pageID: " + this.e);
                    return;
                }
                ThreadPoolManager.d().execute(new Runnable() { // from class: dmi.3.3
                    @Override // java.lang.Runnable
                    public void run() {
                        final int i = 1;
                        final Map<Integer, ResourceResultInfo> d = dmm.d(map, null, 1);
                        HandlerExecutor.e(new Runnable() { // from class: dmi.3.3.5
                            @Override // java.lang.Runnable
                            public void run() {
                                new dml(AnonymousClass3.this.c).c(i, d, (Map<String, ?>) null);
                            }
                        });
                    }
                });
            }
        }
    }

    public static Task<Map<Integer, ResourceResultInfo>> a(List<Integer> list, String str) {
        LogUtil.c("R_MarketingDataProvider", "requestByPositionIdList..." + list);
        return d(list, str);
    }

    /* renamed from: dmi$2, reason: invalid class name */
    class AnonymousClass2 implements Callable<Map<Integer, ResourceResultInfo>> {
        final /* synthetic */ String b;
        final /* synthetic */ List c;

        AnonymousClass2(List list, String str) {
            this.c = list;
            this.b = str;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Map<Integer, ResourceResultInfo> call() throws Exception {
            final Map<Integer, ResourceResultInfo> map = null;
            if (koq.b(this.c)) {
                LogUtil.e("R_MarketingDataProvider", "doRequest failed, positionIdList is null.");
                return null;
            }
            if (LoginInit.getInstance(BaseApplication.e()).isKidAccount()) {
                LogUtil.a("R_MarketingDataProvider", "doRequest abort, Kid Account detected");
                return null;
            }
            if (Utils.l()) {
                LogUtil.a("R_MarketingDataProvider", "doRequest abort, no cloud area detected");
                return null;
            }
            if (Utils.o()) {
                String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1010);
                if (!((OperationUtilsApi) Services.c("PluginOperation", OperationUtilsApi.class)).isOperation(accountInfo)) {
                    LogUtil.a("R_MarketingDataProvider", "doRequest abort, this country is not in operation, code:" + accountInfo);
                    return null;
                }
                if (!bzs.e().switchToMarketingTwo()) {
                    LogUtil.a("R_MarketingDataProvider", "doRequest abort, do not use marketing 2.0");
                    return null;
                }
            }
            dmm dmmVar = new dmm(1);
            if (CommonUtil.aa(BaseApplication.e())) {
                LogUtil.c("R_MarketingDataProvider", "network ok, do requesting... " + this.c);
                map = dmi.c(this.c, this.b);
                if (map != null && !map.isEmpty()) {
                    HandlerCenter.d().e(new Runnable() { // from class: dmn
                        @Override // java.lang.Runnable
                        public final void run() {
                            dmk.d((Map<Integer, ResourceResultInfo>) map);
                        }
                    }, OpAnalyticsConstants.H5_LOADING_DELAY);
                } else {
                    LogUtil.a("R_MarketingDataProvider", "resultInfoMap from cloud is null....");
                }
            } else {
                LogUtil.c("R_MarketingDataProvider", "network NOT ok, cancel requesting...");
            }
            if (map == null || map.isEmpty()) {
                LogUtil.c("R_MarketingDataProvider", "resultInfoMap is empty, read from cache...");
                map = dmk.b(this.c);
            }
            return dmmVar.e(map);
        }
    }

    private static Task<Map<Integer, ResourceResultInfo>> d(List<Integer> list, String str) {
        Task<Map<Integer, ResourceResultInfo>> callInBackground = Tasks.callInBackground(new AnonymousClass2(list, str));
        callInBackground.addOnFailureListener(new OnFailureListener() { // from class: dmj
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                LogUtil.a("R_MarketingDataProvider", "doRequest failed!!! e:" + exc.toString());
            }
        });
        return callInBackground;
    }

    public static Map<Integer, ResourceResultInfo> e(JSONObject jSONObject, List<Integer> list) {
        final Map<Integer, ResourceResultInfo> c;
        dmm dmmVar = new dmm(1);
        if (jSONObject == null) {
            LogUtil.a("R_MarketingDataProvider", "parseFromJsonObject failed, JsonObject is null, requesting from DB...");
            return a(list);
        }
        if (list.size() == 1) {
            int intValue = list.get(0).intValue();
            LogUtil.c("R_MarketingDataProvider", "parseFromJsonObject... parse single position res");
            ResourceResultInfo d = dmg.d(jSONObject);
            c = new HashMap<>();
            c.put(Integer.valueOf(intValue), d);
        } else {
            LogUtil.c("R_MarketingDataProvider", "parseFromJsonObject... parse multi position res");
            c = dmg.c(jSONObject, null, false);
        }
        if (c == null || c.isEmpty()) {
            LogUtil.a("R_MarketingDataProvider", "parseFromJsonObject failed, parse failed, requesting from DB...");
            return a(list);
        }
        HandlerCenter.d().e(new Runnable() { // from class: dmp
            @Override // java.lang.Runnable
            public final void run() {
                dmk.d((Map<Integer, ResourceResultInfo>) c);
            }
        }, OpAnalyticsConstants.H5_LOADING_DELAY);
        Map<Integer, ResourceResultInfo> e = dmmVar.e(c);
        LogUtil.c("R_MarketingDataProvider", "parseFromJsonObject success, content:" + e);
        return e;
    }

    public static Map<Integer, ResourceResultInfo> a(List<Integer> list) {
        return new dmm(1).e(dmk.b(list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<Integer, ResourceResultInfo> c(List<Integer> list, String str) {
        LogUtil.c("R_MarketingDataProvider", "start requesting marketing resource, positionIds: ", list);
        String a2 = dmo.a(list, str, 1);
        if (TextUtils.isEmpty(a2)) {
            LogUtil.a("R_MarketingDataProvider", "startReq result is invaild.");
            return new HashMap();
        }
        if (!a2.contains("resultInfo")) {
            LogUtil.a("R_MarketingDataProvider", "no positionId configured. ", list.toString());
            return new HashMap();
        }
        LogUtil.c("R_MarketingDataProvider", "requesting marketing resource success, start processing...");
        if (list.size() == 1) {
            return dmg.e(list.get(0).intValue(), str, a2);
        }
        return dmg.c(a2, str);
    }
}
