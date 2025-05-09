package defpackage;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import defpackage.jae;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class fep {
    private fep() {
    }

    public static boolean a() {
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            return false;
        }
        boolean z = !dzn.i();
        LogUtil.a("Login_PageStartAction", " isNeedShowPrivacy = ", Boolean.valueOf(z));
        return !z;
    }

    public static boolean b() {
        if (!LanguageUtil.m(BaseApplication.getContext())) {
            LogUtil.a("Login_PageStartAction", "not chineseSimplify");
            return false;
        }
        if (Utils.o()) {
            LogUtil.a("Login_PageStartAction", "oversea version, dont collect label");
            return false;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            LogUtil.a("Login_PageStartAction", "browse mode, dont collect label");
            return false;
        }
        if (CommonUtil.aa(BaseApplication.getContext())) {
            return true;
        }
        LogUtil.a("Login_PageStartAction", "no network, dont collect label");
        return false;
    }

    public static void awH_(final Handler handler) {
        if (b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: feo
                @Override // java.lang.Runnable
                public final void run() {
                    fep.awJ_(handler);
                }
            });
        }
    }

    static /* synthetic */ void awJ_(Handler handler) {
        d((jae.d) null);
        awI_(handler);
        e();
        c();
    }

    private static void c() {
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            LogUtil.b("Login_PageStartAction", "marketingApi is null");
            return;
        }
        Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(4043);
        if (resourceResultInfo == null) {
            LogUtil.b("Login_PageStartAction", "marketingTask is null");
        } else {
            resourceResultInfo.addOnSuccessListener(new OnSuccessListener() { // from class: fen
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    fep.d(MarketingApi.this, (Map) obj);
                }
            });
        }
    }

    static /* synthetic */ void d(MarketingApi marketingApi, Map map) {
        if (map == null || map.isEmpty()) {
            LogUtil.b("Login_PageStartAction", "marketingResponse is invalid");
            return;
        }
        if (marketingApi == null) {
            LogUtil.b("Login_PageStartAction", "marketingApi is null");
            return;
        }
        Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules((Map<Integer, ResourceResultInfo>) map);
        if (filterMarketingRules == null || filterMarketingRules.isEmpty()) {
            LogUtil.b("Login_PageStartAction", "filterResultInfoMap is invalid");
        } else if (filterMarketingRules.get(4043) != null) {
            a(filterMarketingRules.get(4043));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(final ResourceResultInfo resourceResultInfo) {
        if (HandlerExecutor.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: fep.5
                @Override // java.lang.Runnable
                public void run() {
                    fep.a(ResourceResultInfo.this);
                }
            });
            return;
        }
        if (resourceResultInfo == null) {
            return;
        }
        List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
        if (resources == null) {
            LogUtil.b("Login_PageStartAction", "resources is null");
            return;
        }
        LogUtil.a("Login_PageStartAction", "size: ", Integer.valueOf(resources.size()), ", List<ResourceBriefInfo> : ", resources);
        for (int size = resources.size() - 1; size >= 0; size--) {
            ResourceBriefInfo resourceBriefInfo = resources.get(size);
            if (resourceBriefInfo != null) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis > resourceBriefInfo.getEffectiveTime() && currentTimeMillis < resourceBriefInfo.getExpirationTime() && resourceBriefInfo.getContentType() == 47) {
                    List<String> strategyIds = resourceBriefInfo.getStrategyIds();
                    List<String> bucketIds = resourceBriefInfo.getBucketIds();
                    LogUtil.a("Login_PageStartAction", "strategyIds: ", strategyIds, ", bucketIds: ", bucketIds);
                    if (strategyIds == null || bucketIds == null) {
                        return;
                    }
                    c(new jae.d(strategyIds, bucketIds));
                    return;
                }
            }
        }
    }

    private static void e() {
        jbs.a(BaseApplication.getContext()).a("function_card", new jaf(), new ResultCallback<jae>() { // from class: fep.3
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(jae jaeVar) {
                if (jaeVar == null) {
                    LogUtil.h("Login_PageStartAction", "rsp is null.");
                    return;
                }
                LogUtil.a("Login_PageStartAction", "rsp: ", jaeVar.toString());
                if (jaeVar.getResultCode().intValue() != 0) {
                    LogUtil.h("Login_PageStartAction", "getStrategy rsp resultCode is ", jaeVar.getResultCode());
                    return;
                }
                if (koq.b(jaeVar.a()) || koq.b(jaeVar.e()) || koq.b(jaeVar.b())) {
                    LogUtil.h("Login_PageStartAction", "getStrategy rsp data empty");
                } else {
                    LogUtil.a("Login_PageStartAction", "json: ", nrv.a(jaeVar));
                    fep.c(new jae.d(jaeVar));
                }
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.b("Login_PageStartAction", "get strategy error:", ExceptionUtils.d(th));
            }
        });
    }

    private static void awI_(Handler handler) {
        jbs.a(BaseApplication.getContext()).d(new jaf(), new e(handler));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(jae.d dVar) {
        jae.d dVar2;
        synchronized (fep.class) {
            String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "AB_TEST_BI_INFO");
            try {
                if (TextUtils.isEmpty(b)) {
                    dVar2 = new jae.d(new ArrayList(), new ArrayList());
                } else {
                    dVar2 = (jae.d) nrv.c(b, new TypeToken<jae.d>() { // from class: fep.1
                    }.getType());
                    if (dVar2 == null) {
                        dVar2 = new jae.d(new ArrayList(), new ArrayList());
                    }
                }
                List<String> a2 = dVar2.a();
                List<String> b2 = dVar2.b();
                List<String> a3 = dVar.a();
                List<String> b3 = dVar.b();
                if (a2 != null && koq.c(a3)) {
                    for (int i = 0; i < a3.size(); i++) {
                        String str = a3.get(i);
                        if (!TextUtils.isEmpty(str) && !a2.contains(str)) {
                            a2.add(str);
                            if (b2 != null && b3 != null && koq.d(b3, i) && b3.get(i) != null) {
                                b2.add(b3.get(i));
                            }
                        }
                    }
                }
                d(dVar2);
            } catch (JsonSyntaxException unused) {
                LogUtil.b("Login_PageStartAction", "JsonSyntaxException");
            }
        }
    }

    public static String d() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "strategyName");
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        LogUtil.h("Login_PageStartAction", " ab strategy null, return");
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(jae.d dVar) {
        String a2 = dVar != null ? nrv.a(dVar) : "";
        LogUtil.a("Login_PageStartAction", "abTestBiInfo: ", a2);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "AB_TEST_BI_INFO", a2, (StorageParams) null);
    }

    static class e implements ResultCallback<jae> {
        WeakReference<Handler> b;

        e(Handler handler) {
            this.b = new WeakReference<>(handler);
        }

        @Override // com.huawei.networkclient.ResultCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(jae jaeVar) {
            Handler handler = this.b.get();
            Message obtain = Message.obtain();
            obtain.what = 18;
            if (handler == null) {
                b();
                LogUtil.b("Login_PageStartAction", "getStrategy handler is null");
                return;
            }
            if (jaeVar.getResultCode().intValue() != 0) {
                b();
                obtain.obj = false;
                handler.sendMessage(obtain);
                LogUtil.h("Login_PageStartAction", "getStrategy rsp resultCode is ", jaeVar.getResultCode());
                return;
            }
            if (koq.b(jaeVar.a()) || koq.b(jaeVar.e()) || koq.b(jaeVar.b())) {
                b();
                obtain.obj = false;
                handler.sendMessage(obtain);
                LogUtil.h("Login_PageStartAction", "getStrategy rsp data empty");
                return;
            }
            LogUtil.a("Login_PageStartAction", "bucketId", ":", jaeVar.a(), " ", "strategyId", ":", jaeVar.e(), " ", "strategyName", ":", jaeVar.b());
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "AB_TEST_STRATEGY_INFO", nrv.a(jaeVar), (StorageParams) null);
            boolean contains = jaeVar.b().contains("[VIP]");
            ObserverManagerUtil.c("AB_TEST_STRATEGY_SET_SP_DONE", Boolean.valueOf(contains));
            obtain.obj = Boolean.valueOf(contains);
            handler.sendMessage(obtain);
            fep.d(new jae.d(jaeVar));
        }

        private void b() {
            SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(10000), "AB_TEST_STRATEGY_INFO");
            SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(10000), "AB_TEST_BI_INFO");
        }

        @Override // com.huawei.networkclient.ResultCallback
        public void onFailure(Throwable th) {
            LogUtil.b("Login_PageStartAction", "get strategy error:", ExceptionUtils.d(th));
        }
    }
}
