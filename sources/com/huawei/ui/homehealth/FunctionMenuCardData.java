package com.huawei.ui.homehealth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.RcmItem;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.GridTemplate;
import com.huawei.health.marketing.views.ColumnLayoutAdapter;
import com.huawei.health.marketing.views.ColumnLinearLayout;
import com.huawei.health.vip.datatypes.BenefitStatusInfo;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.RepeatResourceBenefitInfo;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.homehealth.FunctionMenuCardData;
import com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback;
import defpackage.drx;
import defpackage.dsl;
import defpackage.dso;
import defpackage.efb;
import defpackage.ixu;
import defpackage.jae;
import defpackage.jdx;
import defpackage.koq;
import defpackage.nrr;
import defpackage.nrv;
import defpackage.nsy;
import defpackage.pug;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class FunctionMenuCardData extends AbstractBaseCardData {
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private ColumnLinearLayout f9343a;
    private String g;
    private RecyclerView.ViewHolder n;
    private b p;
    private int u;
    private Timer v;
    private int h = 0;
    private FunctionMenuViewHolder j = null;
    private GridTemplate k = null;
    private GridTemplate m = null;
    private String i = "";
    private String l = "";
    private boolean o = false;
    private boolean c = true;
    private ResourceResultInfo r = new ResourceResultInfo.Builder().build();
    private ResourceBriefInfo t = new ResourceBriefInfo.Builder().setContentType(47).build();
    private volatile boolean b = true;
    private volatile boolean d = true;
    private int w = 0;
    private ViewTreeObserver.OnGlobalLayoutListener s = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.homehealth.FunctionMenuCardData.1
        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            FunctionMenuCardData functionMenuCardData = FunctionMenuCardData.this;
            functionMenuCardData.u = functionMenuCardData.n.itemView.getTop();
        }
    };
    private BroadcastReceiver q = new d(this);
    private Context f = BaseApplication.getContext();

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void refreshCardData() {
    }

    static class d extends BroadcastReceiver {
        private WeakReference<FunctionMenuCardData> b;

        d(FunctionMenuCardData functionMenuCardData) {
            this.b = new WeakReference<>(functionMenuCardData);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.c("FunctionMenuCardData", "mSwitchAccountReceiver  onReceive");
            if (intent == null) {
                LogUtil.a("FunctionMenuCardData", "login receive.intent null.");
                return;
            }
            FunctionMenuCardData functionMenuCardData = this.b.get();
            if (functionMenuCardData == null) {
                LogUtil.a("FunctionMenuCardData", "login receive.menuCardData is null.");
                return;
            }
            if ("com.huawei.plugin.account.login".equals(intent.getAction())) {
                functionMenuCardData.b(false);
            }
            if ("com.huawei.plugin.account.logout".equals(intent.getAction())) {
                functionMenuCardData.w();
                functionMenuCardData.b(false);
            }
        }
    }

    public FunctionMenuCardData() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ocq
            @Override // java.lang.Runnable
            public final void run() {
                FunctionMenuCardData.this.s();
            }
        });
        b bVar = new b(this);
        this.p = bVar;
        HandlerExecutor.d(bVar, PreConnectManager.CONNECT_INTERNAL);
        k();
        this.v = new Timer();
    }

    static class b implements Runnable {
        private final WeakReference<FunctionMenuCardData> c;

        public b(FunctionMenuCardData functionMenuCardData) {
            this.c = new WeakReference<>(functionMenuCardData);
        }

        @Override // java.lang.Runnable
        public void run() {
            FunctionMenuCardData functionMenuCardData = this.c.get();
            if (functionMenuCardData != null) {
                functionMenuCardData.o();
            } else {
                LogUtil.a("FunctionMenuCardData", "LastTimeCallback activity is null");
            }
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        this.j = new FunctionMenuViewHolder(LayoutInflater.from(this.f).inflate(R.layout.function_menu_card, viewGroup, false));
        this.f9343a = new ColumnLinearLayout(BaseApplication.getContext());
        n();
        return this.j;
    }

    public void a(RecyclerView.ViewHolder viewHolder, int i) {
        this.n = viewHolder;
        viewHolder.itemView.getViewTreeObserver().addOnGlobalLayoutListener(this.s);
        l();
        b(true);
    }

    private void l() {
        LogUtil.c("FunctionMenuCardData", "refreshBySp");
        if (this.d) {
            if (this.t == null || this.k == null) {
                LogUtil.c("FunctionMenuCardData", "async restoreCacheData failed");
                if (!s()) {
                    LogUtil.a("FunctionMenuCardData", "no marketing sp data");
                }
            }
            if (this.k == null || this.t == null) {
                LogUtil.a("FunctionMenuCardData", "no marketing sp data");
                return;
            }
            n();
            d(true, false, false, "", null);
            this.m = this.k.m426clone();
            this.d = false;
            LogUtil.c("FunctionMenuCardData", "refreshBySp completed");
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        ColumnLinearLayout columnLinearLayout = this.f9343a;
        if (columnLinearLayout != null) {
            columnLinearLayout.onResume();
        }
        ReleaseLogUtil.e("FunctionMenuCardData", "main card onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void j() {
        if (this.k != null) {
            LogUtil.c("FunctionMenuCardData", "updateFunctionMenu");
            PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
            LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
            if (payApi != null && loginInit.getIsLogined()) {
                LogUtil.c("FunctionMenuCardData", "queryBenefitInfo");
                payApi.queryBenefitInfo(9, "", new IBaseResponseCallback() { // from class: ocr
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        FunctionMenuCardData.this.c(i, obj);
                    }
                });
            } else {
                r();
            }
        }
    }

    public /* synthetic */ void c(int i, Object obj) {
        LogUtil.c("FunctionMenuCardData", "errorCode " + i);
        if (i == 0 && (obj instanceof RepeatResourceBenefitInfo) && c((RepeatResourceBenefitInfo) obj)) {
            LogUtil.c("FunctionMenuCardData", "objData instanceof RepeatResourceBenefitInfo");
            this.i = "hasEquity";
            int h = h();
            if (h == -1) {
                LogUtil.c("FunctionMenuCardData", "healthManageIndex is -1");
                w();
                return;
            } else {
                c(h);
                return;
            }
        }
        r();
    }

    private void r() {
        this.i = "";
        if (!TextUtils.isEmpty(this.g)) {
            synchronized (e) {
                this.k = (GridTemplate) nrv.b(this.g, GridTemplate.class);
            }
        }
        w();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z, boolean z2, boolean z3, String str, List<RcmItem> list) {
        HandlerExecutor.d(new c(this, z, z2, z3, str, list), 2000L);
    }

    static class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final String f9347a;
        private final boolean b;
        private final boolean c;
        private final boolean d;
        private final List<RcmItem> e;
        private final WeakReference<FunctionMenuCardData> h;

        public c(FunctionMenuCardData functionMenuCardData, boolean z, boolean z2, boolean z3, String str, List<RcmItem> list) {
            this.h = new WeakReference<>(functionMenuCardData);
            this.b = z;
            this.c = z2;
            this.d = z3;
            this.f9347a = str;
            this.e = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.h.get() == null) {
                LogUtil.a("FunctionMenuCardData", "LastTimeCallback activity is null");
            } else {
                ObserverManagerUtil.c("marketing_response_arrived", Boolean.valueOf(this.b), Boolean.valueOf(this.c), Boolean.valueOf(this.d), this.f9347a, this.e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            ReleaseLogUtil.d("R_FunctionMenuCardData", "isNetworkConnected: false");
            FunctionMenuViewHolder functionMenuViewHolder = this.j;
            if (functionMenuViewHolder != null) {
                functionMenuViewHolder.c(0);
            }
            m();
            w();
            d(false, false, false, "", null);
            return;
        }
        if (z && !this.d) {
            this.v.schedule(new TimerTask() { // from class: com.huawei.ui.homehealth.FunctionMenuCardData.4
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    FunctionMenuCardData.this.t();
                }
            }, 2000L);
        } else {
            t();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        ReleaseLogUtil.d("R_FunctionMenuCardData", "request resource");
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(4043);
        resourceResultInfo.addOnSuccessListener(new e(this, marketingApi));
        resourceResultInfo.addOnFailureListener(new a(this));
    }

    static class e implements OnSuccessListener<Map<Integer, ResourceResultInfo>> {

        /* renamed from: a, reason: collision with root package name */
        private MarketingApi f9348a;
        private WeakReference<FunctionMenuCardData> d;

        e(FunctionMenuCardData functionMenuCardData, MarketingApi marketingApi) {
            this.d = new WeakReference<>(functionMenuCardData);
            this.f9348a = marketingApi;
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Map<Integer, ResourceResultInfo> map) {
            FunctionMenuCardData functionMenuCardData = this.d.get();
            if (functionMenuCardData == null) {
                ReleaseLogUtil.d("R_FunctionMenuCardData", "cardData is null");
                return;
            }
            LogUtil.c("FunctionMenuCardData", "marketingResponse: ", map);
            if (map == null || map.isEmpty()) {
                ReleaseLogUtil.d("R_FunctionMenuCardData", "marketingResponse is invalid");
                functionMenuCardData.d(true, true, false, "", null);
                functionMenuCardData.m();
                functionMenuCardData.w();
                return;
            }
            Map<Integer, ResourceResultInfo> filterMarketingRules = this.f9348a.filterMarketingRules(map);
            if (functionMenuCardData.j != null) {
                functionMenuCardData.j.c(0);
            }
            if (filterMarketingRules == null || filterMarketingRules.isEmpty()) {
                ReleaseLogUtil.d("R_FunctionMenuCardData", "filterResultInfoMap is invalid");
                functionMenuCardData.d(true, true, false, "", null);
                functionMenuCardData.m();
                functionMenuCardData.w();
                return;
            }
            if (filterMarketingRules.get(4043) != null) {
                ReleaseLogUtil.e("R_FunctionMenuCardData", "getOperationService has health operation marketing resource");
                functionMenuCardData.r = filterMarketingRules.get(4043);
                functionMenuCardData.i();
            }
        }
    }

    static class a implements OnFailureListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<FunctionMenuCardData> f9346a;

        a(FunctionMenuCardData functionMenuCardData) {
            this.f9346a = new WeakReference<>(functionMenuCardData);
        }

        @Override // com.huawei.hmf.tasks.OnFailureListener
        public void onFailure(Exception exc) {
            FunctionMenuCardData functionMenuCardData = this.f9346a.get();
            if (functionMenuCardData != null) {
                functionMenuCardData.d(true, true, false, "", null);
                functionMenuCardData.m();
                functionMenuCardData.w();
                ReleaseLogUtil.c("R_FunctionMenuCardData", "requestMarketResource onFailure");
                return;
            }
            ReleaseLogUtil.d("R_FunctionMenuCardData", "cardData is null");
        }
    }

    private void e(boolean z) {
        SharedPreferenceManager.e("showLocalResources", "FUNCTION_MENU_SHOW_LOCAL_RESOURCES", z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        FunctionMenuViewHolder functionMenuViewHolder;
        if ("1".equals(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1004)) && (functionMenuViewHolder = this.j) != null) {
            functionMenuViewHolder.c(8);
            return;
        }
        if (BaseApplication.getContext() == null) {
            ReleaseLogUtil.c("FunctionMenuCardData", "loadFunctionMenuSetConfig failed with null context");
            return;
        }
        if (s()) {
            LogUtil.c("FunctionMenuCardData", "restoreCacheData success, use cache data");
            e(false);
            return;
        }
        if (Utils.o()) {
            return;
        }
        InputStream inputStream = null;
        try {
            try {
                inputStream = BaseApplication.getContext().getAssets().open("FunctionMenuConfig.json");
                if (inputStream != null) {
                    synchronized (e) {
                        this.k = (GridTemplate) ixu.d(inputStream, GridTemplate.class);
                        this.t = new ResourceBriefInfo.Builder().setContentType(47).build();
                    }
                    e(true);
                }
            } catch (IOException e2) {
                ReleaseLogUtil.c("FunctionMenuCardData", "loadFunctionMenuSetCofig failed, ", LogAnonymous.b((Throwable) e2));
            }
        } finally {
            IoUtils.e((Closeable) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        FunctionMenuViewHolder functionMenuViewHolder = this.j;
        if (functionMenuViewHolder == null || this.r == null) {
            d(true, true, false, "", null);
            return;
        }
        functionMenuViewHolder.c(0);
        List<ResourceBriefInfo> resources = this.r.getResources();
        if (resources == null) {
            return;
        }
        LogUtil.c("FunctionMenuCardData", "size: ", Integer.valueOf(resources.size()), ", List<ResourceBriefInfo> : ", resources);
        for (int size = resources.size() - 1; size >= 0; size--) {
            ResourceBriefInfo resourceBriefInfo = resources.get(size);
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis > resourceBriefInfo.getEffectiveTime() && currentTimeMillis < resourceBriefInfo.getExpirationTime() && resourceBriefInfo.getContentType() == 47) {
                this.g = resourceBriefInfo.getContent().getContent();
                LogUtil.c("FunctionMenuCardData", "content  " + this.g);
                if (!TextUtils.isEmpty(this.g)) {
                    a(resourceBriefInfo);
                    return;
                }
            }
        }
    }

    private void a(ResourceBriefInfo resourceBriefInfo) {
        String str;
        GridTemplate gridTemplate;
        boolean equals = "intelligent_alpha".equals(resourceBriefInfo.getSortingRules());
        List<String> strategyIds = resourceBriefInfo.getStrategyIds();
        List<String> bucketIds = resourceBriefInfo.getBucketIds();
        List<RcmItem> recommendList = resourceBriefInfo.getRecommendList();
        LogUtil.c("FunctionMenuCardData", "strategyIds: ", strategyIds, ", bucketIds: ", bucketIds);
        if (strategyIds == null || bucketIds == null) {
            str = "";
        } else {
            str = nrv.a(new jae.d(strategyIds, bucketIds));
            LogUtil.c("FunctionMenuCardData", "abTestInfo: ", str);
        }
        d(true, true, equals, str, recommendList);
        e(false);
        synchronized (e) {
            this.t = resourceBriefInfo;
            gridTemplate = (GridTemplate) nrv.b(this.g, GridTemplate.class);
            this.k = gridTemplate;
        }
        if (gridTemplate == null) {
            LogUtil.a("FunctionMenuCardData", "handleValidBriefInfo mGridTemplate is null");
        } else if (h() == -1) {
            LogUtil.c("FunctionMenuCardData", "healthManage not found");
            w();
        } else {
            j();
        }
    }

    private boolean c(RepeatResourceBenefitInfo repeatResourceBenefitInfo) {
        return repeatResourceBenefitInfo.getExpireTime().longValue() >= b(repeatResourceBenefitInfo);
    }

    private long b(RepeatResourceBenefitInfo repeatResourceBenefitInfo) {
        return repeatResourceBenefitInfo.getNowTime() != 0 ? repeatResourceBenefitInfo.getNowTime() : System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ocn
                @Override // java.lang.Runnable
                public final void run() {
                    FunctionMenuCardData.this.p();
                }
            });
            return;
        }
        if (this.k == null || this.t == null) {
            return;
        }
        LogUtil.c("FunctionMenuCardData", "saveCacheData");
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences("function_menu_file", 0);
        if (sharedPreferences == null) {
            LogUtil.e("FunctionMenuCardData", "saveCacheData failed, sharedPreference is null");
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (edit == null) {
            LogUtil.e("FunctionMenuCardData", "saveCacheData failed, editor is null");
            return;
        }
        synchronized (e) {
            String e2 = nrv.e(this.k, new TypeToken<GridTemplate>() { // from class: com.huawei.ui.homehealth.FunctionMenuCardData.3
            }.getType());
            int contentType = this.t.getContentType();
            String resourceId = this.t.getResourceId();
            String resourceName = this.t.getResourceName();
            String category = this.t.getCategory();
            edit.putString("function_menu_json", e2);
            edit.putInt("content_type", contentType);
            edit.putString("resource_id", resourceId);
            edit.putString("resource_name", resourceName);
            edit.putString("category", category);
            edit.putString("country", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
            edit.putString("health_manage_status", this.i);
            edit.apply();
            LogUtil.c("FunctionMenuCardData", "saveCacheData functionMenuJson: ", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: oco
                @Override // java.lang.Runnable
                public final void run() {
                    FunctionMenuCardData.this.g();
                }
            });
            return;
        }
        LogUtil.c("FunctionMenuCardData", "clearCacheData");
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences("function_menu_file", 0);
        if (sharedPreferences == null) {
            LogUtil.e("FunctionMenuCardData", "clearCacheData failed, sharedPreference is null");
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (edit == null) {
            LogUtil.e("FunctionMenuCardData", "clearCacheData failed, editor is null");
            return;
        }
        synchronized (e) {
            edit.putString("function_menu_json", null);
            edit.putInt("content_type", 0);
            edit.putString("resource_id", null);
            edit.putString("resource_name", null);
            edit.putString("category", null);
            edit.putString("country", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
            edit.apply();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        if (this.f9343a == null) {
            LogUtil.a("FunctionMenuCardData", "mColumnLinearLayout is null");
            return;
        }
        synchronized (e) {
            GridTemplate gridTemplate = this.k;
            if (gridTemplate != null && !koq.b(gridTemplate.getGridContents())) {
                LogUtil.c("FunctionMenuCardData", "updateColumnLinearLayout");
                efb.c(this.k.getGridContents());
                GridTemplate m426clone = this.k.m426clone();
                q();
                LogUtil.c("FunctionMenuCardData", "mGridTemplate.size" + this.k.getGridContents().size());
                GridTemplate gridTemplate2 = this.m;
                if (gridTemplate2 != null && gridTemplate2.equals(m426clone) && this.i.equals(this.l)) {
                    LogUtil.a("FunctionMenuCardData", "no data update, not need refresh");
                    return;
                }
                p();
                if (this.b || this.m == null) {
                    HandlerExecutor.a(new Runnable() { // from class: oct
                        @Override // java.lang.Runnable
                        public final void run() {
                            FunctionMenuCardData.this.n();
                        }
                    });
                } else {
                    HandlerExecutor.a(new Runnable() { // from class: ocw
                        @Override // java.lang.Runnable
                        public final void run() {
                            FunctionMenuCardData.this.e();
                        }
                    });
                    this.l = this.i;
                }
                this.m = m426clone;
            }
        }
    }

    public /* synthetic */ void e() {
        b(this.i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        LogUtil.c("FunctionMenuCardData", "initViewHolder, mCurStatus:", this.i);
        this.f9343a.b(this.i);
        this.f9343a.e(4043, this.t, this.k);
        this.j.c(this.f9343a);
        this.b = false;
    }

    private void b(String str) {
        LogUtil.c("FunctionMenuCardData", "updateView");
        this.f9343a.d(this.t, this.k, str);
    }

    private void q() {
        String sortingRules = this.t.getSortingRules();
        List<SingleGridContent> gridContents = this.k.getGridContents();
        if (koq.b(gridContents)) {
            return;
        }
        Iterator<SingleGridContent> it = gridContents.iterator();
        while (it.hasNext()) {
            it.next().setInfoType(sortingRules);
        }
    }

    private void c(int i) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        b(i, countDownLatch);
        a(i, countDownLatch);
        c(countDownLatch);
        try {
            LogUtil.c("FunctionMenuCardData", "getHealthManageInfo result：", Boolean.valueOf(countDownLatch.await(PreConnectManager.CONNECT_INTERNAL, TimeUnit.MILLISECONDS)));
        } catch (InterruptedException unused) {
            LogUtil.e("FunctionMenuCardData", "getHealthManageInfo InterruptedException");
        }
        if (this.o) {
            this.i = "hasInquiring";
        }
        w();
    }

    private int h() {
        int d2;
        synchronized (e) {
            List<SingleGridContent> gridContents = this.k.getGridContents();
            d2 = d(gridContents, "healthManage");
            if (d2 == -1) {
                d2 = a(gridContents);
            }
        }
        LogUtil.c("FunctionMenuCardData", "healthManageIndex  " + d2);
        return d2;
    }

    private int a(List<SingleGridContent> list) {
        if (koq.b(list)) {
            return -1;
        }
        for (SingleGridContent singleGridContent : list) {
            if (singleGridContent != null && BaseApplication.getContext().getResources().getString(R.string._2130845573_res_0x7f021f85).equals(singleGridContent.getTheme())) {
                return list.indexOf(singleGridContent);
            }
        }
        return -1;
    }

    private void b(int i, final CountDownLatch countDownLatch) {
        pug.a().getDoctorBasicInfo(new DataCallback() { // from class: com.huawei.ui.homehealth.FunctionMenuCardData.5
            @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback
            public void onFailure(int i2, String str) {
                LogUtil.e("FunctionMenuCardData", "getDoctorBasicInfo errorCode " + i2 + ", errorInfo " + str);
                FunctionMenuCardData.this.d(countDownLatch);
            }

            @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.c("FunctionMenuCardData", "getDoctorBasicInfo  " + jSONObject.toString());
                try {
                    if (jSONObject.getInt("resultCode") != 0) {
                        LogUtil.e("FunctionMenuCardData", "resultCode: ", Integer.valueOf(jSONObject.getInt("resultCode")));
                        FunctionMenuCardData.this.d(countDownLatch);
                        return;
                    }
                    String optString = jSONObject.optString("doctorId");
                    if (!TextUtils.isEmpty(optString) && !"0".equals(optString)) {
                        FunctionMenuCardData.this.i = "hasSigned";
                        FunctionMenuCardData.this.e(countDownLatch);
                        return;
                    }
                    LogUtil.c("FunctionMenuCardData", "keySet DOCTOR_HEAD_ID is null");
                    FunctionMenuCardData.this.d(countDownLatch);
                } catch (JSONException unused) {
                    LogUtil.e("FunctionMenuCardData", "parse doctor basic info json fail");
                    FunctionMenuCardData.this.d(countDownLatch);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(CountDownLatch countDownLatch) {
        countDownLatch.countDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final CountDownLatch countDownLatch) {
        ReleaseLogUtil.e("R_FunctionMenuCardData", "getHealthLifeChallenge");
        dsl.b("FunctionMenuCardData", (ResponseCallback<drx>) new ResponseCallback() { // from class: ocv
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                FunctionMenuCardData.this.a(countDownLatch, i, (drx) obj);
            }
        });
    }

    public /* synthetic */ void a(CountDownLatch countDownLatch, int i, drx drxVar) {
        ReleaseLogUtil.e("R_FunctionMenuCardData", "getHealthLifeChallenge resultCode ", Integer.valueOf(i));
        if (drxVar != null) {
            ReleaseLogUtil.e("R_FunctionMenuCardData", "getHealthLifeChallenge data ", drxVar.toString());
            this.i = "hasPlan";
            d(countDownLatch);
            return;
        }
        b(countDownLatch);
    }

    private void b(final CountDownLatch countDownLatch) {
        ReleaseLogUtil.e("R_FunctionMenuCardData", "queryDoctorPlanInfo");
        dsl.c("FunctionMenuCardData", (ResponseCallback<dso>) new ResponseCallback() { // from class: ocu
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                FunctionMenuCardData.this.a(countDownLatch, i, (dso) obj);
            }
        });
    }

    public /* synthetic */ void a(CountDownLatch countDownLatch, int i, dso dsoVar) {
        ReleaseLogUtil.e("R_FunctionMenuCardData", "queryDoctorPlanInfo resultCode ", Integer.valueOf(i));
        if (dsoVar != null && dsoVar.e()) {
            ReleaseLogUtil.e("R_FunctionMenuCardData", "queryDoctorPlanInfo data ", dsoVar.toString());
            this.i = "hasPlan";
        }
        d(countDownLatch);
    }

    private void a(final int i, final CountDownLatch countDownLatch) {
        pug.a().getDoctorImInfo(new DataCallback() { // from class: com.huawei.ui.homehealth.FunctionMenuCardData.2
            @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback
            public void onFailure(int i2, String str) {
                LogUtil.e("FunctionMenuCardData", "getDoctorImInfo errorCode " + i2 + ", errorInfo " + str);
                FunctionMenuCardData.this.d(countDownLatch);
            }

            @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.c("FunctionMenuCardData", "getDoctorImInfo  " + jSONObject.toString());
                try {
                    if (jSONObject.getInt("resultCode") != 0) {
                        FunctionMenuCardData.this.d(countDownLatch);
                        return;
                    }
                    ArraySet arraySet = new ArraySet();
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        arraySet.add(keys.next());
                    }
                    if (!arraySet.contains("unReadMsgCount")) {
                        FunctionMenuCardData.this.d(countDownLatch);
                        return;
                    }
                    FunctionMenuCardData.this.w = jSONObject.getInt("unReadMsgCount");
                    LogUtil.c("FunctionMenuCardData", "unReadMsgCount count  " + FunctionMenuCardData.this.w);
                    synchronized (FunctionMenuCardData.e) {
                        List<SingleGridContent> gridContents = FunctionMenuCardData.this.k.getGridContents();
                        if (i < gridContents.size()) {
                            gridContents.get(i).setMessageCount(FunctionMenuCardData.this.w);
                        }
                    }
                    FunctionMenuCardData.this.d(countDownLatch);
                } catch (JSONException unused) {
                    LogUtil.e("FunctionMenuCardData", "parse doctor im info json fail");
                    FunctionMenuCardData.this.d(countDownLatch);
                }
            }
        });
    }

    private void c(final CountDownLatch countDownLatch) {
        this.o = false;
        HashMap hashMap = new HashMap(1);
        hashMap.put("type", 1);
        pug.a().queryExclusiveGuardianStatus(hashMap, new DataCallback() { // from class: com.huawei.ui.homehealth.FunctionMenuCardData.8
            @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback
            public void onFailure(int i, String str) {
                LogUtil.e("FunctionMenuCardData", "queryExclusiveGuardianStatus errorCode " + i + ", errorInfo " + str);
                FunctionMenuCardData.this.d(countDownLatch);
            }

            @Override // com.huawei.ui.main.stories.fitness.activity.pressuremeasure.cloud.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.c("FunctionMenuCardData", "queryExclusiveGuardianStatus  data:", jSONObject);
                if (jSONObject != null) {
                    try {
                        if (jSONObject.getInt("resultCode") == 0 && jSONObject.has(CloudParamKeys.INFO)) {
                            String string = jSONObject.getString(CloudParamKeys.INFO);
                            if (TextUtils.isEmpty(string)) {
                                FunctionMenuCardData.this.d(countDownLatch);
                                return;
                            }
                            BenefitStatusInfo benefitStatusInfo = (BenefitStatusInfo) nrv.b(string, BenefitStatusInfo.class);
                            if (benefitStatusInfo != null && benefitStatusInfo.getStatus() == 0) {
                                FunctionMenuCardData.this.o = true;
                            }
                            FunctionMenuCardData.this.d(countDownLatch);
                            return;
                        }
                    } catch (JSONException e2) {
                        LogUtil.e("FunctionMenuCardData", "queryExclusiveGuardianStatus json error， ", ExceptionUtils.d(e2));
                        FunctionMenuCardData.this.d(countDownLatch);
                        return;
                    }
                }
                FunctionMenuCardData.this.d(countDownLatch);
            }
        });
    }

    private int d(List<SingleGridContent> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(list.get(i).getRedDotFlag())) {
                return i;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean s() {
        String str;
        String str2;
        String str3;
        String str4;
        int i;
        LogUtil.c("FunctionMenuCardData", "getInitData");
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences("function_menu_file", 0);
        String str5 = "";
        if (sharedPreferences != null) {
            str5 = sharedPreferences.getString("function_menu_json", "");
            i = sharedPreferences.getInt("content_type", 0);
            str4 = sharedPreferences.getString("resource_id", "");
            str3 = sharedPreferences.getString("resource_name", "");
            str2 = sharedPreferences.getString("category", "");
            str = sharedPreferences.getString("country", "");
            this.i = sharedPreferences.getString("health_manage_status", "");
        } else {
            str = "";
            str2 = "";
            str3 = "";
            str4 = "";
            i = 0;
        }
        if (!str.equals(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010))) {
            LogUtil.c("FunctionMenuCardData", "country code not equal with last, clear cache");
            g();
            return false;
        }
        if (TextUtils.isEmpty(str5) || i == 0 || "".equals(str4) || "".equals(str3) || "".equals(str2)) {
            return false;
        }
        synchronized (e) {
            this.t = new ResourceBriefInfo.Builder().setContentType(i).setResourceId(str4).setResourceName(str3).setCategory(str2).build();
            this.k = (GridTemplate) nrv.b(str5, GridTemplate.class);
        }
        return true;
    }

    private void k() {
        if (this.c) {
            this.c = false;
            ObserverManagerUtil.d(new AnonymousClass10(), "REFRESH_HEALTH_HEADLINES_SHOW_STATUS");
        }
    }

    /* renamed from: com.huawei.ui.homehealth.FunctionMenuCardData$10, reason: invalid class name */
    public class AnonymousClass10 implements Observer {
        AnonymousClass10() {
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            if (!koq.e(objArr, 0)) {
                LogUtil.e("FunctionMenuCardData", "null args!");
                return;
            }
            Object obj = objArr[0];
            if (obj instanceof Boolean) {
                if (((Boolean) obj).booleanValue()) {
                    ObserverManagerUtil.c("SHOW_HEALTH_HEADLINES_TIPS", -1L, -1L);
                } else {
                    jdx.b(new Runnable() { // from class: ocs
                        @Override // java.lang.Runnable
                        public final void run() {
                            FunctionMenuCardData.AnonymousClass10.this.a();
                        }
                    });
                }
            }
        }

        public /* synthetic */ void a() {
            int d = FunctionMenuCardData.this.f9343a.getmColumnLayoutAdapter().d();
            int g = FunctionMenuCardData.this.f9343a.getmColumnLayoutAdapter().g();
            if (FunctionMenuCardData.this.f9343a.getHealthSubHeader() != null && FunctionMenuCardData.this.f9343a.getHealthSubHeader().getVisibility() == 0) {
                g += FunctionMenuCardData.this.f9343a.getHealthSubHeader().getHeight() - nrr.e(BaseApplication.getContext(), 12.0f);
            }
            ObserverManagerUtil.c("SHOW_HEALTH_HEADLINES_TIPS", Integer.valueOf(d), Integer.valueOf(g));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        LogUtil.c("FunctionMenuCardData", "registerSwitchAccountReceiver enter");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.plugin.account.login");
        intentFilter.addAction("com.huawei.plugin.account.logout");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).registerReceiver(this.q, intentFilter);
        LogUtil.c("FunctionMenuCardData", "registerSwitchAccountReceiver end");
    }

    private void u() {
        try {
            LogUtil.c("FunctionMenuCardData", "unregisterSwitchAccountReceiver mSwitchAccountReceiver != null");
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.q);
        } catch (IllegalArgumentException unused) {
            LogUtil.e("FunctionMenuCardData", "unregisterSwitchAccountReceiver IllegalArgumentException");
        }
    }

    public static class FunctionMenuViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        View f9345a;
        LinearLayout c;
        LinearLayout e;

        public FunctionMenuViewHolder(View view) {
            super(view);
            this.f9345a = view;
            this.c = (LinearLayout) view.findViewById(R.id.item);
            Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
            this.c.setPadding(((Integer) safeRegionWidth.first).intValue(), 0, ((Integer) safeRegionWidth.second).intValue(), (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131362886_res_0x7f0a0446));
            this.e = (LinearLayout) view.findViewById(R.id.operation_service_layout);
        }

        public void c(ColumnLinearLayout columnLinearLayout) {
            this.e.removeAllViews();
            this.e.addView(columnLinearLayout);
        }

        public void c(int i) {
            ViewGroup.LayoutParams layoutParams = this.f9345a.getLayoutParams();
            if (layoutParams instanceof RecyclerView.LayoutParams) {
                RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
                if (i == 0) {
                    layoutParams2.height = -2;
                    layoutParams2.width = -1;
                } else {
                    layoutParams2.height = 0;
                    layoutParams2.width = 0;
                }
                nsy.cMA_(this.f9345a, i);
                this.f9345a.setLayoutParams(layoutParams2);
            }
        }
    }

    public void a() {
        ColumnLayoutAdapter columnLayoutAdapter;
        ColumnLinearLayout columnLinearLayout = this.f9343a;
        if (columnLinearLayout == null || (columnLayoutAdapter = columnLinearLayout.getmColumnLayoutAdapter()) == null) {
            return;
        }
        columnLayoutAdapter.h();
    }

    public void c() {
        ColumnLayoutAdapter columnLayoutAdapter;
        ColumnLinearLayout columnLinearLayout = this.f9343a;
        if (columnLinearLayout == null || (columnLayoutAdapter = columnLinearLayout.getmColumnLayoutAdapter()) == null) {
            return;
        }
        columnLayoutAdapter.e();
    }

    public void b() {
        ColumnLayoutAdapter columnLayoutAdapter;
        ColumnLinearLayout columnLinearLayout = this.f9343a;
        if (columnLinearLayout == null || (columnLayoutAdapter = columnLinearLayout.getmColumnLayoutAdapter()) == null) {
            return;
        }
        columnLayoutAdapter.j();
    }

    public void f() {
        ColumnLayoutAdapter columnLayoutAdapter;
        ColumnLinearLayout columnLinearLayout = this.f9343a;
        if (columnLinearLayout == null || (columnLayoutAdapter = columnLinearLayout.getmColumnLayoutAdapter()) == null) {
            return;
        }
        columnLayoutAdapter.f();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onDestroy() {
        super.onDestroy();
        u();
        ObserverManagerUtil.c("marketing_response_arrived", -1);
        HandlerExecutor.yF_().removeCallbacks(this.p);
        RecyclerView.ViewHolder viewHolder = this.n;
        if (viewHolder != null && viewHolder.itemView != null) {
            this.n.itemView.getViewTreeObserver().removeOnGlobalLayoutListener(this.s);
        }
        this.v.cancel();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public String getCardName() {
        return "FunctionMenuCardData";
    }
}
