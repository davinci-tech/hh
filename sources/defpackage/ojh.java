package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBean;
import com.huawei.health.health.utils.functionsetcard.FunctionSetType;
import com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.health.sleep.SleepApi;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.SleepDailyProcessResultCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.R$anim;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import com.huawei.ui.homehealth.functionsetcard.view.SleepThumbnailView;
import com.huawei.ui.main.stories.fitness.activity.coresleep.KnitSleepDetailActivity;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepJsApi;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementCallback;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes9.dex */
public class ojh extends FunctionSetBeanReader {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<Integer, String> f15726a = new HashMap<Integer, String>() { // from class: ojh.3
        {
            put(44102, "core_sleep_deep_key");
            put(44103, "core_sleep_shallow_key");
            put(44101, "core_sleep_wake_dream_key");
            put(44105, "core_sleep_total_sleep_time_key");
            put(44001, "sleep_deep_key");
            put(44002, "sleep_shallow_key");
            put(44108, "core_sleep_day_sleep_time_key");
            put(44104, "core_sleep_wake_key");
            put(44109, "data_session_manual_sleep_bed_time_key");
            put(44110, "sleep_device_category_key");
        }
    };
    private final Observer b;
    private volatile String c;
    private View d;
    private boolean e;
    private int f;
    private ImageView g;
    private volatile String h;
    private final a i;
    private final d j;
    private boolean k;
    private boolean l;
    private final boolean m;
    private volatile boolean n;
    private boolean o;
    private volatile Boolean p;
    private final Observer q;
    private long r;
    private List<Integer> s;
    private SleepThumbnailView t;
    private UserLabelServiceApi w;
    private String x;

    private boolean b(int i, double d2) {
        return i != 0 && d2 == 0.0d;
    }

    private boolean b(int i, int i2) {
        return (i2 == 0 && i != 0) || i < 180;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonTextId() {
        return R.string._2130846212_res_0x7f022204;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getDataTypeTextColor() {
        return R.color._2131299087_res_0x7f090b0f;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getDefaultImage(boolean z) {
        return z ? R.drawable._2131431512_res_0x7f0b1058 : R.drawable.marketing_default_img_sleep;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public boolean isMessageDefaultLargeCard() {
        return true;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public boolean isSubscribeType(int i) {
        return i == 2 || i == 3;
    }

    private boolean c(String str) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(this.h)) {
            return false;
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(this.h)) {
            return true;
        }
        return !str.equals(this.h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(int i) {
        switch (i) {
            case 10:
                if (!"has_core_sleep".equals(this.c)) {
                    break;
                }
                break;
            case 11:
                if (!"has_core_sleep".equals(this.c)) {
                    break;
                }
                break;
        }
        return "";
    }

    static class a extends HandleCacheDataRunnable {
        private final WeakReference<ojh> b;
        private boolean e;

        a(ojh ojhVar) {
            super("FunctionSetSleepCardReader", null);
            this.b = new WeakReference<>(ojhVar);
        }

        @Override // com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable
        public void handleCacheData(HiHealthData hiHealthData, boolean z) {
            ojh ojhVar = this.b.get();
            LogUtil.a("FunctionSetSleepCardReader", "handleCacheData isNewData: ", Boolean.valueOf(z));
            if (ojhVar == null) {
                LogUtil.h("FunctionSetSleepCardReader", "handleCacheData reader is null");
                return;
            }
            if (!z) {
                List<HiHealthData> b = fch.b(String.valueOf(DateFormatUtil.b(System.currentTimeMillis())));
                if (koq.c(b) && !hiHealthData.equals(b.get(0))) {
                    HiHealthData hiHealthData2 = b.get(0);
                    z = ojhVar.d(hiHealthData, hiHealthData2);
                    hiHealthData = hiHealthData2;
                }
                if (z) {
                    ojhVar.d(hiHealthData.getStartTime());
                }
            }
            if (hiHealthData == null && this.e && z) {
                LogUtil.h("FunctionSetSleepCardReader", "handleCacheData data is null, show empty view");
                ojh.setHasCardData(this.b, false);
                ojhVar.c();
            } else if (hiHealthData != null) {
                ojh.setHasCardData(this.b, true);
                ojhVar.b(hiHealthData, z);
                this.e = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
        for (String str : f15726a.values()) {
            if (hiHealthData.getInt(str) != hiHealthData2.getInt(str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(2);
        arrayList.add(3);
        HiHealthNativeApi.a(this.mContext).subscribeHiHealthData(arrayList, new FunctionSetBeanReader.c("FunctionSetSleepCardReader", this));
    }

    public ojh(Context context, CardConfig cardConfig) {
        super(context, "FunctionSetSleepCardReader", cardConfig);
        this.i = new a(this);
        this.x = "FunctionSetSleepCardReader";
        this.s = null;
        this.e = false;
        this.f = 0;
        this.l = false;
        this.o = true;
        this.c = "";
        this.n = false;
        Observer observer = new Observer() { // from class: ojh.5
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                ojh.this.l = true;
                ojh.this.readCardData();
            }
        };
        this.b = observer;
        LogUtil.a("FunctionSetSleepCardReader", "FunctionSetSleepCardReader create");
        this.m = Utils.o();
        this.j = new d();
        this.w = (UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class);
        new IntentFilter("com.huawei.hihealth.action_account_login_datas_switch_finish").addAction("com.huawei.hihealth.action_account_login_datas_switch_finish");
        j();
        Observer observer2 = new Observer() { // from class: ojh.1
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (StringUtils.g(str) || !ojh.this.e) {
                    LogUtil.b("FunctionSetSleepCardReader", "label is null || isRegSuccess");
                } else if ("com.huawei.plugin.account.login".equals(str)) {
                    ojh.this.j();
                }
            }
        };
        this.q = observer2;
        ObserverManagerUtil.d(observer2, "com.huawei.plugin.account.login");
        ObserverManagerUtil.d(observer, "KNIT_SLEEP_ACTIVITY_DESTROY");
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, FunctionSetSubCardData functionSetSubCardData) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onBindViewHolder(viewHolder, functionSetSubCardData);
        ReleaseLogUtil.e("TimeEat_FunctionSetSleepCardReader", "onBindViewHolder finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onDestroy() {
        super.onDestroy();
        if (koq.b(this.s)) {
            LogUtil.h("FunctionSetSleepCardReader", "onDestroy mSleepSuccessList is empty");
        } else {
            HiHealthNativeApi.a(this.mContext).unSubscribeHiHealthData(this.s, new FunctionSetBeanReader.b("FunctionSetSleepCardReader", "unSubscribeSleepData, isSuccess :"));
        }
        ObserverManagerUtil.e(this.q, "com.huawei.plugin.account.login");
        ObserverManagerUtil.e(this.b, "KNIT_SLEEP_ACTIVITY_DESTROY");
        d dVar = this.j;
        if (dVar == null) {
            LogUtil.h("FunctionSetSleepCardReader", "onDestroy mHandler is null");
        } else {
            dVar.removeCallbacksAndMessages(null);
        }
    }

    private void b(boolean z, boolean z2, long j2) {
        Intent intent = new Intent(this.mContext, (Class<?>) KnitSleepDetailActivity.class);
        intent.putExtra(Constants.CORE_SLEEP_TODAY_HAS_DATA, z);
        intent.putExtra(Constants.SLEEP_TYPE_KEY, z2);
        intent.putExtra("from", 0);
        if (this.p != null) {
            intent.putExtra("key_is_open_sleep_management", this.p);
        }
        intent.setFlags(268435456);
        intent.putExtra("key_bundle_health_red_dot", getFunctionSetBean().h());
        intent.putExtra("key_bundle_health_last_data_time", j2);
        gnm.aPB_(this.mContext, intent);
        a();
    }

    private void a() {
        Activity activity = BaseApplication.getActivity();
        if (activity == null) {
            LogUtil.h("FunctionSetSleepCardReader", "context is not Activity");
        } else {
            activity.overridePendingTransition(R$anim.left_enter_short_for_sleep, R$anim.activity_no_animation_short);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public final void readCardData() {
        super.readCardData();
        LogUtil.a("FunctionSetSleepCardReader", "readCardData");
        this.n = false;
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(0L);
        hiAggregateOption.setEndTime(HiDateUtil.f(System.currentTimeMillis()));
        hiAggregateOption.setCount(1);
        hiAggregateOption.setGroupUnitSize(1);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setType(new int[]{44102, 44103, 44101, 44105, 44001, 44002, 44108, 44104, 44109, 44110});
        hiAggregateOption.setConstantsKey(new String[]{"core_sleep_deep_key", "core_sleep_shallow_key", "core_sleep_wake_dream_key", "core_sleep_total_sleep_time_key", "sleep_deep_key", "sleep_shallow_key", "core_sleep_day_sleep_time_key", "core_sleep_wake_key", "data_session_manual_sleep_bed_time_key", "sleep_device_category_key"});
        HiHealthManager.d(this.mContext).aggregateHiHealthData(hiAggregateOption, new c(this));
        if (VersionControlUtil.isSupportSleepManagement()) {
            this.p = pob.a((Boolean) null);
            pob.d(new e(this));
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        ReleaseLogUtil.e("TimeEat_FunctionSetSleepCardReader", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final long j2) {
        LogUtil.a("FunctionSetSleepCardReader", "tryToRequestDailyProcessResult, curTime: ", Long.valueOf(j2));
        if (!VersionControlUtil.isSupportSleepManagement()) {
            LogUtil.h("FunctionSetSleepCardReader", "not support sleep management");
            return;
        }
        if (j2 == 0) {
            LogUtil.h("FunctionSetSleepCardReader", "curTime is 0");
            return;
        }
        LogUtil.a("FunctionSetSleepCardReader", "mIsHideDailyProblem: ", Boolean.valueOf(this.n));
        if (this.n) {
            LogUtil.h("FunctionSetSleepCardReader", "hide sleep problem");
            d("");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ojh.2
                @Override // java.lang.Runnable
                public void run() {
                    ojh.this.d(j2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(long j2) {
        ReleaseLogUtil.e("TimeEat_", "start query daily problem");
        pob.a(j2, new b(this, j2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j2) {
        SleepApi sleepApi = (SleepApi) Services.a("Main", SleepApi.class);
        if (sleepApi != null) {
            sleepApi.requestDailyProcessResult(ggl.g(j2), new j(this));
        } else {
            LogUtil.a("FunctionSetSleepCardReader", "sleepServiceApi is null");
        }
    }

    static class j implements SleepDailyProcessResultCallback {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<ojh> f15730a;

        public j(ojh ojhVar) {
            this.f15730a = new WeakReference<>(ojhVar);
        }

        @Override // com.huawei.hwbasemgr.SleepDailyProcessResultCallback
        public void onResponse(int i, Object obj) {
            ojh ojhVar = this.f15730a.get();
            if (ojhVar == null) {
                LogUtil.b("FunctionSetSleepCardReader", "reader is null");
            } else if (obj instanceof fda) {
                String c = ojhVar.c(((fda) obj).h());
                LogUtil.a("FunctionSetSleepCardReader", "requested dailyProblem: ", c);
                ojhVar.d(c);
            }
        }
    }

    static class b implements SleepManagementCallback<Map<String, Object>> {
        private long b;
        private WeakReference<ojh> e;

        public b(ojh ojhVar, long j) {
            this.e = new WeakReference<>(ojhVar);
            this.b = j;
        }

        @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Map<String, Object> map) {
            ojh ojhVar = this.e.get();
            if (ojhVar == null) {
                LogUtil.b("FunctionSetSleepCardReader", "reader is null");
                return;
            }
            if (map.size() == 0) {
                LogUtil.b("FunctionSetSleepCardReader", "result is null");
                ojhVar.a(this.b);
                return;
            }
            Object obj = map.get("dailySleepProblem");
            if (obj instanceof Integer) {
                String c = ojhVar.c(((Integer) obj).intValue());
                LogUtil.a("FunctionSetSleepCardReader", "query result: ", c);
                ojhVar.d(c);
            } else {
                LogUtil.b("FunctionSetSleepCardReader", "object is null");
                ojhVar.a(this.b);
            }
        }

        @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.SleepManagementCallback
        public void onFailure(int i, String str) {
            LogUtil.b("FunctionSetSleepCardReader", "errCode: ", Integer.valueOf(i), ", errMsg: ", str);
            ojh ojhVar = this.e.get();
            if (ojhVar != null) {
                ojhVar.a(this.b);
            } else {
                LogUtil.b("FunctionSetSleepCardReader", "reader is null");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        if (!c(str)) {
            LogUtil.a("FunctionSetSleepCardReader", "dailyProblem not changed");
            return;
        }
        this.h = str;
        if (getFunctionSetBean() == null) {
            LogUtil.a("FunctionSetSleepCardReader", "functionsetbean not build, wait to build");
        } else {
            getFunctionSetBean().b(this.h);
            c(getFunctionSetBean());
        }
    }

    public static class c implements HiAggregateListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<ojh> f15729a;

        public c(ojh ojhVar) {
            this.f15729a = new WeakReference<>(ojhVar);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            WeakReference<ojh> weakReference = this.f15729a;
            if (weakReference == null) {
                LogUtil.b("FunctionSetSleepCardReader", "mReference is null");
                return;
            }
            ojh ojhVar = weakReference.get();
            if (ojhVar == null) {
                LogUtil.b("FunctionSetSleepCardReader", "reader is null, refresh failed");
                return;
            }
            if (koq.b(list)) {
                LogUtil.h("FunctionSetSleepCardReader", "readCardData onResult dataList is empty");
                ojhVar.saveDataFromHealthApi(ojhVar.x, ojhVar, (HiHealthData) null);
                return;
            }
            LogUtil.a("FunctionSetSleepCardReader", "readCardData success");
            HiHealthData hiHealthData = list.get(0);
            LogUtil.a("FunctionSetSleepCardReader", "last data:", hiHealthData);
            ojhVar.saveDataFromHealthApi(ojhVar.x, ojhVar, hiHealthData);
            if (hiHealthData != null) {
                long startTime = hiHealthData.getStartTime();
                SharedPreferenceManager.c("privacy_center", "sleep", String.valueOf(startTime));
                ojhVar.c(startTime);
                return;
            }
            LogUtil.a("FunctionSetSleepCardReader", "data is null");
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            LogUtil.a("FunctionSetSleepCardReader", "readCardData onResultIntent intentType ", Integer.valueOf(i), " errorCode ", Integer.valueOf(i2));
        }
    }

    static class e implements IBaseResponseCallback {
        private WeakReference<ojh> c;

        public e(ojh ojhVar) {
            this.c = new WeakReference<>(ojhVar);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            ojh ojhVar = this.c.get();
            if (ojhVar == null) {
                LogUtil.b("FunctionSetSleepCardReader", "reader is null, refresh failed");
                return;
            }
            ReleaseLogUtil.e("FunctionSetSleepCardReader", "SleepPlanOpenCallback errorCode:", Integer.valueOf(i), " objData:", obj);
            if (obj instanceof Boolean) {
                ojhVar.p = (Boolean) obj;
            }
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void initCardData() {
        ohy.c().a(new HiDataReadResultListener() { // from class: ojh.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                int[] a2 = ohy.a();
                ArrayList arrayList = new ArrayList();
                SparseArray sparseArray = obj instanceof SparseArray ? (SparseArray) obj : null;
                if (sparseArray != null) {
                    for (int i3 : a2) {
                        arrayList.addAll(kor.bPC_(sparseArray, i3));
                    }
                }
                LogUtil.a("FunctionSetSleepCardReader", "sleep dataList: ", arrayList);
                HiHealthData c2 = ojh.this.c(arrayList);
                LogUtil.a("FunctionSetSleepCardReader", "aggregated data: ", c2);
                if (c2 == null) {
                    LogUtil.h("FunctionSetSleepCardReader", "readCardData onResult dataList is empty");
                    ojh ojhVar = ojh.this;
                    ojhVar.saveDataFromHealthApi(ojhVar.x, ojh.this, (HiHealthData) null);
                } else {
                    ojh ojhVar2 = ojh.this;
                    ojhVar2.saveDataFromHealthApi(ojhVar2.x, ojh.this, c2);
                }
            }
        });
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void saveDataFromHealthApi(String str, FunctionSetBeanReader functionSetBeanReader, HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            if (ArkUIXConstants.DELETE.equals(this.mChangeKey)) {
                ObserverManagerUtil.c("SLEEP_MESSAGE_JUMP_TAG", Long.valueOf(this.r), true);
            }
            super.saveDataFromHealthApi(str, functionSetBeanReader, hiHealthData);
            return;
        }
        int i = hiHealthData.getInt("core_sleep_deep_key");
        int i2 = hiHealthData.getInt("core_sleep_shallow_key");
        if (b(hiHealthData.getInt("core_sleep_wake_key"), i + i2 + hiHealthData.getInt("core_sleep_wake_dream_key") + hiHealthData.getInt("core_sleep_day_sleep_time_key"))) {
            LogUtil.a("FunctionSetSleepCardReader", "saveDataFromHealthApi, is only wake up time");
            this.n = true;
        } else {
            super.saveDataFromHealthApi(str, functionSetBeanReader, hiHealthData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HiHealthData c(List<HiHealthData> list) {
        String str;
        HiHealthData hiHealthData = null;
        if (koq.b(list)) {
            return null;
        }
        long j2 = -1;
        for (HiHealthData hiHealthData2 : list) {
            if (hiHealthData2 != null) {
                if (j2 == -1) {
                    j2 = hiHealthData2.getStartTime();
                    hiHealthData = c(hiHealthData2);
                }
                if (hiHealthData2.getStartTime() == j2 && (str = f15726a.get(Integer.valueOf(hiHealthData2.getType()))) != null) {
                    hiHealthData.putInt(str, hiHealthData2.getInt("point_value"));
                }
            }
        }
        return hiHealthData;
    }

    private HiHealthData c(HiHealthData hiHealthData) {
        Parcel obtain = Parcel.obtain();
        hiHealthData.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        HiHealthData createFromParcel = HiHealthData.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        Iterator<String> it = f15726a.values().iterator();
        while (it.hasNext()) {
            createFromParcel.putInt(it.next(), 0);
        }
        return createFromParcel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.k = false;
        c(buildEmptyCardBean());
    }

    private void c(FunctionSetBean functionSetBean) {
        Message obtainMessage = this.j.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = functionSetBean;
        this.j.sendMessage(obtainMessage);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public FunctionSetBean buildEmptyCardBean() {
        return new FunctionSetBean.a(nsf.h(R.string.IDS_hw_show_main_home_page_sleep)).e(nsf.h(R.string.IDS_hw_show_main_home_page_sleep_description)).a(FunctionSetType.SLEEP_CARD).b(FunctionSetBean.ViewType.EMPTY_VIEW).d(this.mContext).c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: ojh.8
                @Override // java.lang.Runnable
                public void run() {
                    ojh.this.d();
                }
            });
            return;
        }
        if (this.g == null) {
            this.g = new ImageView(this.mContext);
        }
        this.g.setImageResource(R.drawable._2131431507_res_0x7f0b1053);
        this.d = this.g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final int i, final int i2, final int i3, final int i4, final int i5, final int i6, final int i7) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: ojh.10
                @Override // java.lang.Runnable
                public void run() {
                    ojh.this.e(i, i2, i3, i4, i5, i6, i7);
                }
            });
            return;
        }
        if (this.t == null) {
            this.t = new SleepThumbnailView(this.mContext);
        }
        this.t.setSleepData(i, i2, i3, i4, i5, i6, i7);
        this.d = this.t;
    }

    private void c(final boolean z) {
        jdx.b(new Runnable() { // from class: ojh.9
            @Override // java.lang.Runnable
            public void run() {
                if (!z || SharedPreferenceManager.d(ojh.this.mContext)) {
                    return;
                }
                SharedPreferenceManager.c(ojh.this.mContext, true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0198  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(com.huawei.hihealth.HiHealthData r44, boolean r45) {
        /*
            Method dump skipped, instructions count: 661
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ojh.b(com.huawei.hihealth.HiHealthData, boolean):void");
    }

    private String e(HiHealthData hiHealthData, String str) {
        if ("has_common_sleep".equals(str)) {
            this.n = true;
            setBiHasDataType("normal");
            setBiDataSource("wearDevice");
            return "has_common_sleep";
        }
        setBiHasDataType("scientific");
        int i = hiHealthData.getInt("sleep_device_category_key");
        if (i == Integer.parseInt("06D", 16) || i == Integer.parseInt("06E", 16)) {
            setBiDataSource("wearDevice");
            return "has_core_sleep";
        }
        if (i == Integer.parseInt("001", 16)) {
            setBiDataSource("manual");
            return "has_manual_sleep";
        }
        if (!nrq.b(i)) {
            return "";
        }
        setBiDataSource("telephone");
        return "has_phone_sleep";
    }

    private FunctionSetBean c(HiHealthData hiHealthData, double d2) {
        FunctionSetBean c2;
        if (9.999999747378752E-6d + d2 == 0.0d) {
            c2 = buildEmptyCardBean();
            LogUtil.a("FunctionSetSleepCardReader", "getFunctionSetBean buildEmptyCardBean");
        } else {
            long startTime = hiHealthData.getStartTime();
            this.r = startTime;
            setBiHasData(startTime);
            c2 = new FunctionSetBean.a(nsf.h(R.string.IDS_hw_show_main_home_page_sleep)).c(owm.b(this.r)).a(d((int) d2)).b(FunctionSetBean.ViewType.DATA_VIEW).a(FunctionSetType.SLEEP_CARD).d(this.mContext).d(this.h).e(this.c).c();
        }
        c2.c(hiHealthData.toString().hashCode());
        return c2;
    }

    private CharSequence d(int i) {
        BaseApplication.getContext();
        int i2 = i / 60;
        int i3 = i % 60;
        String a2 = nsf.a(R.plurals._2130903200_res_0x7f0300a0, i3, UnitUtil.e(i3, 1, 0));
        if (i2 > 0) {
            a2 = nsf.b(R.string._2130838891_res_0x7f02056b, nsf.a(R.plurals._2130903199_res_0x7f03009f, i2, UnitUtil.e(i2, 1, 0)), a2);
        }
        return owm.e("[\\d\\-]", a2);
    }

    private boolean e(double d2, int i, int i2, int i3) {
        int a2 = SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_COIN_DROP), "ENTRY_ACTIVITY_TIME_SP_KEY", 0);
        int hash = Objects.hash(Integer.valueOf((int) d2), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Long.valueOf(this.r));
        this.f = hash;
        LogUtil.a("FunctionSetSleepCardReader", "mCurrentHash = ", Integer.valueOf(hash), "; hash = ", Integer.valueOf(a2));
        if (!this.l) {
            return a2 != this.f;
        }
        SharedPreferenceManager.b(Integer.toString(PrebakedEffectId.RT_COIN_DROP), "ENTRY_ACTIVITY_TIME_SP_KEY", this.f);
        this.l = false;
        return false;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void updateSuccessList(List<Integer> list) {
        LogUtil.a("FunctionSetSleepCardReader", "updateSuccessList");
        if (koq.b(list)) {
            LogUtil.h("FunctionSetSleepCardReader", "updateSuccessList successList is empty");
            this.e = true;
        } else {
            this.s = list;
        }
    }

    static class d extends BaseHandler<ojh> {
        private d(ojh ojhVar) {
            super(Looper.getMainLooper(), ojhVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dbi_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ojh ojhVar, Message message) {
            if (ojhVar == null || message == null) {
                LogUtil.h("FunctionSetSleepCardReader", "handleMessageWhenReferenceNotNull sleepCardReader or message is null");
                return;
            }
            int i = message.what;
            LogUtil.a("FunctionSetSleepCardReader", "handleMessageWhenReferenceNotNull what ", Integer.valueOf(i));
            if (i == 1) {
                Object obj = message.obj;
                if (obj instanceof FunctionSetBean) {
                    ojhVar.notifyItemChanged((FunctionSetBean) obj);
                } else {
                    LogUtil.h("FunctionSetSleepCardReader", "handleMessageWhenReferenceNotNull object instanceof FunctionSetBean is false");
                }
            }
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public View createCardView() {
        return this.d;
    }

    private Map<String, Object> b() {
        HashMap hashMap = new HashMap(2);
        hashMap.put(FunctionSetBeanReader.BI_HAS_DATA, false);
        if (!(this.mCardViewHolder instanceof FunctionSetBeanReader.MyHolder)) {
            LogUtil.a("FunctionSetSleepCardReader", "mCardViewHolder is not MyHolder instance");
            hashMap.put("guideLanguage", "");
        } else {
            View view = ((FunctionSetBeanReader.MyHolder) this.mCardViewHolder).itemView;
            if (view == null) {
                LogUtil.a("FunctionSetSleepCardReader", "view is null");
                hashMap.put("guideLanguage", "");
            } else {
                HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.function_set_empty_items_description);
                if (healthTextView == null) {
                    LogUtil.a("FunctionSetSleepCardReader", "sleepEmptyDescription is null");
                    hashMap.put("guideLanguage", "");
                } else {
                    hashMap.put("guideLanguage", healthTextView.getText().toString());
                }
            }
        }
        return hashMap;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void onCardViewClickListener(FunctionSetBean.ViewType viewType) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("guideLanguage", "");
        hashMap.put("from", 0);
        if (viewType == FunctionSetBean.ViewType.DATA_VIEW) {
            hashMap.put(FunctionSetBeanReader.BI_HAS_DATA, true);
            gge.e(AnalyticsValue.HEALTH_HOME_SLEEP_CARD_CLICK_21300020.value(), hashMap);
            ixx.d().d(this.mContext.getApplicationContext(), AnalyticsValue.HEALTH_HOME_SLEEP_DETAIL_2010011.value(), hashMap, 0);
            try {
                this.w.clickRecord(Integer.parseInt(AnalyticsValue.HEALTH_HOME_SLEEP_DETAIL_2010011.value()), getHuid());
            } catch (NumberFormatException e2) {
                LogUtil.b("FunctionSetSleepCardReader", "setSleepCardForDataView onClick NumberFormatException ", LogAnonymous.b((Throwable) e2));
            }
            b(true, "has_core_sleep".equals(getFunctionSetBean().e()), this.r);
        } else {
            hashMap.put(FunctionSetBeanReader.BI_HAS_DATA, false);
            gge.e(AnalyticsValue.HEALTH_HOME_SLEEP_CARD_CLICK_21300020.value(), b());
            ixx.d().d(this.mContext.getApplicationContext(), AnalyticsValue.HEALTH_HOME_SLEEP_DETAIL_2010011.value(), hashMap, 0);
            try {
                this.w.clickRecord(Integer.parseInt(AnalyticsValue.HEALTH_HOME_SLEEP_DETAIL_2010011.value()), getHuid());
            } catch (NumberFormatException e3) {
                LogUtil.b("FunctionSetSleepCardReader", "setSleepCardForEmptyView onClick NumberFormatException ", LogAnonymous.b((Throwable) e3));
            }
            e();
        }
        SharedPreferenceManager.b(Integer.toString(PrebakedEffectId.RT_COIN_DROP), "ENTRY_ACTIVITY_TIME_SP_KEY", this.f);
    }

    private void e() {
        if (this.m || nsf.h(R.string.IDS_main_no_device_click).equals(getFunctionSetBean().e()) || this.k) {
            b(false, false, this.r);
            return;
        }
        Intent intent = new Intent(this.mContext, (Class<?>) KnitSleepDetailActivity.class);
        intent.putExtra("from", 0);
        gnm.aPB_(this.mContext, intent);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getDefaultLargeCardImageRes() {
        return Integer.toString(R.drawable.pic_health_sleep);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonColorId() {
        return nrt.a(this.mContext) ? R.color._2131297761_res_0x7f0905e1 : R.color._2131297760_res_0x7f0905e0;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void setClickEventForButton(HealthButton healthButton) {
        if (healthButton == null) {
            return;
        }
        healthButton.setClickable(true);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: ojh.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                H5ProClient.getServiceManager().registerService(SleepJsApi.class);
                bzs.e().loadH5ProApp(ojh.this.mContext, "com.huawei.health.h5.sleepdetection", new H5ProLaunchOption.Builder().addPath("#/sleepDataInput?pullFrom=6&whichDate=" + System.currentTimeMillis()).addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).setForceDarkMode(1).setImmerse().showStatusBar().setStatusBarTextBlack(true));
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        Activity activity = BaseApplication.getActivity();
        if (activity != null) {
            healthButton.setOnClickListener(nkx.cwY_(onClickListener, activity, true, ""));
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public HandleCacheDataRunnable getRunnable() {
        return this.i;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getChildTag() {
        return "FunctionSetSleepCardReader";
    }
}
