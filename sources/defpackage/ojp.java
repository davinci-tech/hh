package defpackage;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.health.constants.ObserveLabels;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBean;
import com.huawei.health.health.utils.functionsetcard.FunctionSetType;
import com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.health.servicesui.R$string;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.R$anim;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.view.threeCircle.ThreeCircleView;
import com.huawei.ui.homehealth.threecirclecard.model.StepsViewModel;
import com.huawei.ui.main.stories.fitness.activity.active.KnitActiveRecordActivity;
import defpackage.ojp;
import health.compact.a.HiDateUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class ojp extends FunctionSetBeanReader {

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f15738a;
    private String b;
    private Bitmap c;
    private StepsViewModel d;
    private int e;
    private b f;
    private int g;
    private FunctionSetBean h;
    private int i;
    private Observer<HashMap<String, Integer>> j;
    private boolean k;
    private d l;
    private a m;
    private BroadcastReceiver n;
    private boolean o;
    private int p;
    private Resources q;
    private int r;
    private Observer<Bundle> s;
    private int t;
    private ArrayList<Integer> u;
    private List<Integer> v;
    private View w;
    private ThreeCircleView x;
    private List<Integer> y;

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public float getChartBottomMargin() {
        return -4.0f;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public float getChartTopMargin() {
        return 8.0f;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public boolean isMessageDefaultLargeCard() {
        return true;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public boolean isOverseaDefaultLargeCard() {
        return true;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public boolean isSubscribeType(int i) {
        return false;
    }

    static class b extends HandleCacheDataRunnable {

        /* renamed from: a, reason: collision with root package name */
        private List<Integer> f15739a;
        private final WeakReference<ojp> b;
        private HiHealthData e;

        b(ojp ojpVar) {
            super("FunctionSetThreeCircleCardReader", null);
            this.b = new WeakReference<>(ojpVar);
        }

        void a(List<Integer> list) {
            this.f15739a = list;
            if (list != null) {
                e(true).putString("_", marshallListToString(list));
            }
        }

        void d(long j, int i, int i2) {
            HiHealthData e = e(true);
            e.putLong("_t", j);
            e.putInt("COMPLETE_GOALS", i);
            e.putInt("_u", i2);
        }

        void b(boolean z) {
            HiHealthData hiHealthData;
            LogUtil.a("FunctionSetThreeCircleCardReader", "saveHealthData isSave", Boolean.valueOf(z));
            if (z) {
                ojp.setHasCardData(this.b, true);
                hiHealthData = e(false);
            } else {
                ojp.setHasCardData(this.b, false);
                this.f15739a = null;
                hiHealthData = null;
            }
            this.e = null;
            ojp.saveDataFromHealthApi("FunctionSetThreeCircleCardReader", this.b, hiHealthData);
        }

        @Override // com.huawei.health.health.utils.functionsetcard.HandleCacheDataRunnable
        public void handleCacheData(HiHealthData hiHealthData, boolean z) {
            LogUtil.a("FunctionSetThreeCircleCardReader", "handleCacheData threeCircleValueList data ", hiHealthData);
            ojp ojpVar = this.b.get();
            if (ojpVar == null) {
                LogUtil.h("FunctionSetThreeCircleCardReader", "handleCacheData threeCirCleCardReader is null");
                return;
            }
            if (hiHealthData == null) {
                return;
            }
            List list = this.f15739a;
            if (!z || list == null) {
                list = new ArrayList();
                unmarshallListFromString(hiHealthData.getString("_"), list);
            } else {
                this.f15739a = null;
            }
            if (!koq.b(list)) {
                ojpVar.a((List<Integer>) list);
                ojpVar.o = true;
                ojpVar.d(hiHealthData.getLong("_t"), hiHealthData.getInt("COMPLETE_GOALS"), hiHealthData.getInt("_u"), z);
            } else {
                LogUtil.h("FunctionSetThreeCircleCardReader", "handleCacheData threeCircleValueList is empty");
                if (z) {
                    return;
                }
                ojpVar.refreshCardBySp(ojpVar.buildEmptyCardBean());
            }
        }

        private HiHealthData e(boolean z) {
            if (z && this.e == null) {
                this.e = new HiHealthData();
            }
            return this.e;
        }
    }

    public ojp(Context context, CardConfig cardConfig) {
        super(context, "FunctionSetThreeCircleCardReader", cardConfig);
        this.o = false;
        this.k = true;
        this.g = 0;
        this.i = 0;
        this.t = 0;
        this.r = 10000;
        this.y = new ArrayList(Collections.nCopies(6, 0));
        this.p = 0;
        this.f15738a = new ArrayList(Collections.nCopies(6, 0));
        this.n = new e(this);
        this.s = new Observer() { // from class: com.huawei.ui.homehealth.functionsetcard.reader.FunctionSetThreeCircleCardReader$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ojp.this.dbt_((Bundle) obj);
            }
        };
        this.j = new Observer() { // from class: com.huawei.ui.homehealth.functionsetcard.reader.FunctionSetThreeCircleCardReader$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ojp.this.a((HashMap<String, Integer>) obj);
            }
        };
        LogUtil.a("FunctionSetThreeCircleCardReader", "FunctionSetThreeCircleCardReader context ", context);
        this.m = new a(this);
        this.f = new b(this);
        d();
        d dVar = new d(this);
        this.l = dVar;
        ObserverManagerUtil.d(dVar, ObserveLabels.SUMMARY_DATA_INIT);
    }

    static class d implements com.huawei.haf.design.pattern.Observer {
        private final WeakReference<ojp> e;

        d(ojp ojpVar) {
            this.e = new WeakReference<>(ojpVar);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            ojp ojpVar = this.e.get();
            if (ojpVar == null || objArr == null || !ObserveLabels.SUMMARY_DATA_INIT.equals(str)) {
                ReleaseLogUtil.d("FunctionSetThreeCircleCardReader", "InnerObserver notify functionSetThreeCircleCardReader ", ojpVar, " objects ", objArr, " label ", str);
                return;
            }
            Object obj = objArr[0];
            if (!(obj instanceof Integer) || !(objArr[1] instanceof HashMap)) {
                ReleaseLogUtil.d("FunctionSetThreeCircleCardReader", "InnerObserver notify is not legal");
                return;
            }
            int intValue = ((Integer) obj).intValue();
            HashMap hashMap = (HashMap) objArr[1];
            LogUtil.a("FunctionSetThreeCircleCardReader", "initSummary errorCode ", Integer.valueOf(intValue));
            Message obtainMessage = ojpVar.m.obtainMessage();
            obtainMessage.what = 100;
            obtainMessage.obj = hashMap.get("activityRings");
            ojpVar.m.sendMessage(obtainMessage);
        }
    }

    private void d() {
        this.q = this.mContext.getResources();
        i();
        f();
        m();
        b();
    }

    private void f() {
        if (koq.d(this.y, 5)) {
            this.y.set(1, 270000);
            this.y.set(3, 25);
            this.y.set(5, 12);
        }
    }

    private void m() {
        ArrayList<Integer> arrayList = new ArrayList<>(16);
        this.u = arrayList;
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA.value()));
        HiHealthNativeApi.a(this.mContext).subscribeHiHealthData(this.u, new FunctionSetBeanReader.c("FunctionSetThreeCircleCardReader", this));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void onCardViewClickListener(FunctionSetBean.ViewType viewType) {
        LogUtil.a("FunctionSetThreeCircleCardReader", "go to THREE CIRCLE CARD record");
        if (nsn.a(500)) {
            return;
        }
        if (LoginInit.getInstance(BaseApplication.e()).isBrowseMode()) {
            LoginInit.getInstance(BaseApplication.e()).browsingToLogin(new IBaseResponseCallback() { // from class: ojl
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    ojp.this.c(i, obj);
                }
            }, "");
        } else {
            j();
        }
    }

    /* synthetic */ void c(int i, Object obj) {
        if (i == 0) {
            j();
        }
    }

    private void j() {
        Intent intent = new Intent();
        intent.setClass(this.mContext, KnitActiveRecordActivity.class);
        intent.setFlags(268435456);
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("from", 2);
        c((Map<String, Object>) hashMap);
        intent.putExtra("activeRecordClickFrom", 2);
        boolean j = nhj.j();
        LogUtil.a("FunctionSetThreeCircleCardReader", "jumpActiveRecordActivity isOpenActiveRecord ", Boolean.valueOf(j), " mCardId ", Integer.valueOf(this.e));
        if (!j) {
            intent.putExtra("card_id", this.e);
        }
        if (this.mContext != null) {
            dbr_(intent);
            ixx.d().d(this.mContext, AnalyticsValue.HEALTH_HOME_CIRCLE_RING_1040091.value(), hashMap, 0);
            gnm.aPB_(this.mContext, intent);
            h();
        }
    }

    private void c(Map<String, Object> map) {
        String[] strArr = {"calorie", "MVPA", "activity"};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 6; i += 2) {
            int i2 = i + 1;
            if (koq.b(this.y, i2)) {
                break;
            }
            if (this.y.get(i).intValue() >= this.y.get(i2).intValue() && this.y.get(i2).intValue() > 0) {
                arrayList.add(strArr[i / 2]);
            }
        }
        if (arrayList.size() == 3) {
            arrayList.add("perfect");
        }
        map.put("status", arrayList);
    }

    private void h() {
        Activity wa_ = BaseApplication.wa_();
        if (wa_ == null) {
            LogUtil.h("FunctionSetThreeCircleCardReader", "activity is null when setShortTransAnimation.");
        } else {
            wa_.overridePendingTransition(R$anim.left_enter_short_for_sleep, R$anim.activity_no_animation_short);
        }
    }

    private void dbr_(Intent intent) {
        if (koq.b(this.y, 5)) {
            LogUtil.h("FunctionSetThreeCircleCardReader", "list is out bound not init");
            return;
        }
        intent.putExtra("current_total_calorie", this.y.get(0));
        intent.putExtra("cur_calorie_goal", this.y.get(1));
        intent.putExtra("current_total_intensity", this.y.get(2));
        intent.putExtra("cur_intensity_goal", this.y.get(3));
        intent.putExtra("current_total_active", this.y.get(4));
        intent.putExtra("cur_active_goal", this.y.get(5));
        intent.putExtra("current_total_step", this.t);
        intent.putExtra("cur_step_goal", this.r);
        intent.putExtra("current_total_distance", this.g);
        intent.putExtra("current_total_floor", this.i);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, FunctionSetSubCardData functionSetSubCardData) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onBindViewHolder(viewHolder, functionSetSubCardData);
        ReleaseLogUtil.e("TimeEat_FunctionSetThreeCircleCardReader", "onBindViewHolder finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(long j, int i, int i2, boolean z) {
        setHasCardData(true);
        FunctionSetBean a2 = a(j, i);
        a2.c(i2);
        setFunctionSetBean(a2);
        boolean j2 = BaseApplication.j();
        if (z && j2) {
            Message obtainMessage = this.m.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.obj = a2;
            this.m.sendMessage(obtainMessage);
            return;
        }
        refreshCardBySp(a2);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public View createCardView() {
        LogUtil.a("FunctionSetThreeCircleCardReader", "createCardView ");
        HealthImageView healthImageView = new HealthImageView(this.mContext);
        if (this.o || this.c == null) {
            LogUtil.a("FunctionSetThreeCircleCardReader", "mIsDataUpdated ");
            View view = this.w;
            if (view == null || (view instanceof HealthImageView)) {
                try {
                    View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.card_three_circle_view, (ViewGroup) null);
                    this.w = inflate;
                    this.x = (ThreeCircleView) inflate.findViewById(R.id.circleProgressBar);
                } catch (InflateException e2) {
                    LogUtil.h("FunctionSetThreeCircleCardReader", e2.getClass().getSimpleName() + "inflate mThreeCircleCardView fail!");
                }
            }
            a(this.y);
            if (this.o) {
                this.o = false;
            }
            this.c = dbs_(this.w);
        }
        healthImageView.setImageBitmap(this.c);
        return healthImageView;
    }

    private Bitmap dbs_(View view) {
        if (view == null) {
            LogUtil.h("FunctionSetThreeCircleCardReader", "getThreeCircleBitmap view == null");
            return null;
        }
        try {
            view.measure(View.MeasureSpec.makeMeasureSpec(nsn.n(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(nsn.j(), Integer.MIN_VALUE));
        } catch (IllegalStateException e2) {
            LogUtil.h("FunctionSetThreeCircleCardReader", e2.getClass().getSimpleName() + "getThreeCircleBitmap measure fail!");
        }
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<Integer> list) {
        this.y = list;
        LogUtil.a("FunctionSetThreeCircleCardReader", "initThreeCircle.");
        if (this.w == null || this.x == null || this.mContext == null) {
            LogUtil.b("FunctionSetThreeCircleCardReader", "initThreeCircle has null object.");
        } else {
            b(list);
        }
    }

    private FunctionSetBean a(long j, int i) {
        LogUtil.a("FunctionSetThreeCircleCardReader", "createFunctionSetBean completedGoals ", Integer.valueOf(i));
        setBiHasData(j);
        FunctionSetBean c = new FunctionSetBean.a(this.q.getString(R$string.IDS_three_circle_title)).d(this.mContext).a(FunctionSetType.THREE_CIRCLE_CARD).b(FunctionSetBean.ViewType.DATA_VIEW).a(this.mContext.getResources().getString(R.string._2130845622_res_0x7f021fb6, UnitUtil.e(i, 1, 0), UnitUtil.e(3.0d, 1, 0))).b(nsf.h(R.string._2130846074_res_0x7f02217a)).c();
        boolean j2 = nhj.j();
        LogUtil.a("FunctionSetThreeCircleCardReader", "createFunctionSetBean isOpenActiveRecord ", Boolean.valueOf(j2), " mActiveRecordSummaryText ", this.b);
        if (TextUtils.isEmpty(this.b) || j2) {
            c.c((CharSequence) owm.b(j));
        } else {
            c.a(this.b);
        }
        return c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogUtil.a("FunctionSetThreeCircleCardReader", "readSportGoalAchievementData");
        StepsViewModel stepsViewModel = this.d;
        if (stepsViewModel != null) {
            stepsViewModel.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final HashMap<String, Integer> hashMap) {
        LogUtil.a("FunctionSetThreeCircleCardReader", "processGoalData goal value is ", hashMap);
        HandlerExecutor.e(new Runnable() { // from class: ojn
            @Override // java.lang.Runnable
            public final void run() {
                ojp.this.d(hashMap);
            }
        });
    }

    /* synthetic */ void d(HashMap hashMap) {
        c((HashMap<String, Integer>) hashMap);
        e(this.y.get(0).intValue(), this.y.get(2).intValue(), this.y.get(4).intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dbt_(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        LogUtil.a("FunctionSetThreeCircleCardReader", "report step=", Integer.valueOf(bundle.getInt("step", -1)), ":calories=", Integer.valueOf(bundle.getInt("carior", -1)), ":floor=", Integer.valueOf(bundle.getInt("floor", -1)), ":distance=", Integer.valueOf(bundle.getInt("distance", -1)), " mTimes ", Integer.valueOf(bundle.getInt("intensityTime", -1)), " mActiveHours ", Integer.valueOf(bundle.getInt("activeCount", -1)));
        LogUtil.a("FunctionSetThreeCircleCardReader", "mCompleteGoals ", this.f15738a, "mThreeCircleList", this.y);
        if (koq.b(this.y, 5)) {
            LogUtil.h("FunctionSetThreeCircleCardReader", "list is out bound or not init");
            return;
        }
        final int i = bundle.getInt("carior", -1);
        final int i2 = bundle.getInt("intensityTime", -1);
        final int i3 = bundle.getInt("activeCount", -1);
        this.t = bundle.getInt("step", -1);
        this.i = bundle.getInt("floor", -1);
        this.g = bundle.getInt("distance", -1);
        HandlerExecutor.e(new Runnable() { // from class: ojm
            @Override // java.lang.Runnable
            public final void run() {
                ojp.this.e(i, i2, i3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void e(int i, int i2, int i3) {
        boolean c = c(i, i2, i3);
        int a2 = a();
        LogUtil.a("FunctionSetThreeCircleCardReader", "report isUpdated ", Boolean.valueOf(c), " completeGoals ", Integer.valueOf(a2), "mCompleteGoals ", this.f15738a, "mThreeCircleList", this.y);
        if (this.m != null && this.k && i == 0 && i3 == 0 && i2 == 0) {
            c(a2);
            this.k = false;
        }
        if (this.m == null || !c) {
            return;
        }
        this.o = true;
        c(a2);
    }

    private void c(int i) {
        this.f.d(HiDateUtil.t(System.currentTimeMillis()), i, (this.y.toString() + i).hashCode());
        this.f.a(this.y);
        this.f.b(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        Bitmap dbs_;
        LogUtil.a("FunctionSetThreeCircleCardReader", "refreshCardView");
        if (koq.b(this.y)) {
            LogUtil.b("FunctionSetThreeCircleCardReader", "mThreeCircleList ", this.y);
            return;
        }
        HealthImageView healthImageView = new HealthImageView(this.mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        healthImageView.setLayoutParams(layoutParams);
        if (z || this.c == null) {
            View view = this.w;
            if (view == null || (view instanceof HealthImageView)) {
                try {
                    View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.card_three_circle_view, (ViewGroup) null);
                    this.w = inflate;
                    this.x = (ThreeCircleView) inflate.findViewById(R.id.circleProgressBar);
                } catch (InflateException e2) {
                    LogUtil.h("FunctionSetThreeCircleCardReader", e2.getClass().getSimpleName() + "inflate mThreeCircleCardView fail!");
                }
            }
            a(this.y);
            dbs_ = dbs_(this.w);
            this.c = dbs_;
        } else {
            LogUtil.a("FunctionSetThreeCircleCardReader", "use mBitmap");
            dbs_ = this.c;
        }
        healthImageView.setImageBitmap(dbs_);
        this.w = healthImageView;
    }

    private boolean c(int i, int i2, int i3) {
        LogUtil.a("FunctionSetThreeCircleCardReader", "updateThreeCircleData");
        if (!koq.b(this.y, 5)) {
            return b(0, i) || b(2, i2) || b(4, i3);
        }
        LogUtil.h("FunctionSetThreeCircleCardReader", "list is out bound or not init");
        return false;
    }

    private boolean b(int i, int i2) {
        boolean z;
        LogUtil.a("FunctionSetThreeCircleCardReader", "updateThreeCircleDataImpl index=", Integer.valueOf(i), " sportData=", Integer.valueOf(i2));
        if (koq.b(this.y, i) || koq.b(this.f15738a, i)) {
            LogUtil.h("FunctionSetThreeCircleCardReader", "list is out bound or not init");
            return false;
        }
        int i3 = i + 1;
        if (!koq.d(this.y, i3) || this.y.get(i3).intValue() == 0) {
            z = false;
        } else if (i2 >= this.y.get(i3).intValue()) {
            z = this.f15738a.get(i).intValue() != 1;
            this.f15738a.set(i, 1);
        } else {
            z = this.f15738a.get(i).intValue() != 0;
            this.f15738a.set(i, 0);
        }
        boolean z2 = this.y.get(i).intValue() != i2 || z;
        this.y.set(i, Integer.valueOf(i2));
        return z2;
    }

    private void b() {
        if (this.d == null) {
            this.d = (StepsViewModel) new ViewModelProvider((ViewModelStoreOwner) this.mContext).get(StepsViewModel.class);
        }
        LogUtil.a("FunctionSetThreeCircleCardReader", "initViewModel");
        this.d.e((LifecycleOwner) this.mContext, this.s);
        this.d.d((LifecycleOwner) this.mContext, this.j);
    }

    private void c(HashMap<String, Integer> hashMap) {
        int e2 = nip.e(hashMap, "900200007", 270000);
        if (e2 != 0) {
            this.y.set(1, Integer.valueOf(e2));
        }
        int e3 = nip.e(hashMap, "900200008", 25);
        if (e3 != 0) {
            this.y.set(3, Integer.valueOf(e3));
        }
        int e4 = nip.e(hashMap, "900200009", 12);
        if (e4 != 0) {
            this.y.set(5, Integer.valueOf(e4));
        }
        int e5 = nip.e(hashMap, "900200006", 10000);
        if (e5 != 0) {
            this.r = e5;
        }
    }

    private void b(List<Integer> list) {
        if (this.mContext == null || this.x == null || koq.b(list)) {
            return;
        }
        if (list.size() > 1) {
            this.x.c("firstCircle", list.get(0).intValue(), list.get(1).intValue());
        }
        if (list.size() > 2) {
            this.x.c("secondCircle", list.get(2).intValue(), list.get(3).intValue());
        }
        if (list.size() > 5) {
            this.x.c("thirdCircle", list.get(4).intValue(), list.get(5).intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        b(true);
        FunctionSetBean a2 = a(System.currentTimeMillis(), a());
        a2.YB_(this.w);
        notifyItemChanged(a2);
    }

    private void i() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.hihealth.action_sync_total_sport_data");
        intentFilter.addAction("com.huawei.hihealth.action_sync");
        LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this.n, intentFilter);
        LogUtil.c("FunctionSetThreeCircleCardReader", "mHiBroadcastReceiver register");
    }

    private void n() {
        if (this.n == null) {
            return;
        }
        try {
            LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this.n);
            LogUtil.c("FunctionSetThreeCircleCardReader", "mHiBroadcastReceiver unregister");
        } catch (IllegalArgumentException unused) {
            LogUtil.c("FunctionSetThreeCircleCardReader", "IllegalArgumentException mHiBroadcastReceiver unregister");
        }
    }

    static class e extends BroadcastReceiver {
        private WeakReference<ojp> e;

        e(ojp ojpVar) {
            this.e = new WeakReference<>(ojpVar);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                ReleaseLogUtil.d("FunctionSetThreeCircleCardReader", "threeCircleCard onReceive intent=null");
                return;
            }
            ojp ojpVar = this.e.get();
            if (ojpVar == null) {
                ReleaseLogUtil.d("FunctionSetThreeCircleCardReader", "threeCircleCardReader is null");
            } else if ("com.huawei.hihealth.action_sync".equals(intent.getAction()) && intent.getIntExtra("com.huawei.hihealth.action_sync_status", 6) == 2) {
                LogUtil.a("FunctionSetThreeCircleCardReader", "handleActionSyncByStatus");
                ojpVar.g();
            }
        }
    }

    static class a extends BaseHandler<ojp> {
        private WeakReference<ojp> d;

        a(ojp ojpVar) {
            super(Looper.getMainLooper(), ojpVar);
            this.d = new WeakReference<>(ojpVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dbu_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ojp ojpVar, Message message) {
            if (message == null) {
                LogUtil.b("FunctionSetThreeCircleCardReader", "msg is null");
                return;
            }
            int i = message.what;
            if (i == 1) {
                LogUtil.a("FunctionSetThreeCircleCardReader", "MSG_UPDATE_THREE_CIRCLE");
                int a2 = ojpVar.a();
                if (a2 <= 0) {
                    ojpVar.b(ojpVar.o);
                    if (message.obj instanceof FunctionSetBean) {
                        ojpVar.notifyItemChanged((FunctionSetBean) message.obj);
                    }
                } else {
                    LogUtil.a("FunctionSetThreeCircleCardReader", "show new data, completeGoals=", Integer.valueOf(a2));
                    ojpVar.e();
                }
                ojpVar.o = false;
                return;
            }
            if (i == 2) {
                LogUtil.a("FunctionSetThreeCircleCardReader", "MSG_NOT_HAVE_THREE_CIRCLE_DATA");
                ojp.setHasCardData(this.d, false);
                ojpVar.notifyItemChanged(ojpVar.buildEmptyCardBean());
                return;
            }
            if (i == 100) {
                boolean j = nhj.j();
                LogUtil.a("FunctionSetThreeCircleCardReader", "handleMessageWhenReferenceNotNull isOpenActiveRecord ", Boolean.valueOf(j));
                if (j) {
                    return;
                }
                Object obj = message.obj;
                if (obj instanceof Pair) {
                    Pair pair = (Pair) obj;
                    if (!(pair.first instanceof Integer) || !(pair.second instanceof String)) {
                        LogUtil.h("FunctionSetThreeCircleCardReader", "handleMessageWhenReferenceNotNull activeRecordText is error");
                        return;
                    }
                    int intValue = ((Integer) pair.first).intValue();
                    String str = (String) pair.second;
                    LogUtil.a("FunctionSetThreeCircleCardReader", "cardId is：", Integer.valueOf(intValue), "activeRecordText is：", str);
                    if (!TextUtils.isEmpty(str)) {
                        ojpVar.b = str;
                        FunctionSetBean functionSetBean = ojpVar.getFunctionSetBean();
                        if (functionSetBean == null) {
                            ReleaseLogUtil.d("FunctionSetThreeCircleCardReader", "handleMessageWhenReferenceNotNull functionSetBean is null");
                            return;
                        }
                        functionSetBean.c((CharSequence) "");
                        functionSetBean.a(str);
                        ojpVar.notifyItemChanged(functionSetBean);
                        ojpVar.e = intValue;
                        return;
                    }
                    LogUtil.h("FunctionSetThreeCircleCardReader", "handleMessageWhenReferenceNotNull activeRecordText is empty");
                    return;
                }
                return;
            }
            LogUtil.h("FunctionSetThreeCircleCardReader", "handler message default");
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void readCardData() {
        super.readCardData();
        g();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void initCardData() {
        LogUtil.a("FunctionSetThreeCircleCardReader", "initCardData");
        readCardData();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public FunctionSetBean buildEmptyCardBean() {
        LogUtil.a("FunctionSetThreeCircleCardReader", "setEmptyFunctionSetBean");
        FunctionSetBean c = new FunctionSetBean.a(this.q.getString(R$string.IDS_three_circle_title)).e(this.q.getString(R$string.IDS_three_circle_subtitle)).a(FunctionSetType.THREE_CIRCLE_CARD).b(FunctionSetBean.ViewType.EMPTY_VIEW).d(this.mContext).d(false).c();
        this.h = c;
        return c;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        readCardData();
        e();
        ReleaseLogUtil.e("TimeEat_FunctionSetThreeCircleCardReader", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onDestroy() {
        super.onDestroy();
        c();
        n();
        a aVar = this.m;
        if (aVar != null) {
            aVar.removeCallbacksAndMessages(null);
        }
        ObserverManagerUtil.c(this.l);
        this.d.a(this.s);
        this.d.c(this.j);
    }

    public void c() {
        LogUtil.a("FunctionSetThreeCircleCardReader", "unSubscribeThreeCircleData");
        List<Integer> list = this.v;
        if (list == null || list.isEmpty()) {
            return;
        }
        HiHealthNativeApi.a(this.mContext).unSubscribeHiHealthData(this.v, new FunctionSetBeanReader.b("FunctionSetThreeCircleCardReader", "unSubscribeThreeCircleData, isSuccess:"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a() {
        Iterator<Integer> it = this.f15738a.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().intValue();
        }
        if (i > 3) {
            return 3;
        }
        return i;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void updateSuccessList(List<Integer> list) {
        LogUtil.a("FunctionSetThreeCircleCardReader", "updateSuccessList, onResult");
        if (list == null || list.isEmpty()) {
            return;
        }
        LogUtil.a("FunctionSetThreeCircleCardReader", "registerThreeCircleListener success");
        this.v = list;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getDefaultImage(boolean z) {
        if (z) {
            return R$drawable.three_circle_default_img;
        }
        return R$drawable.ic_three_circle;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonTextId() {
        return com.huawei.ui.commonui.R$string.IDS_three_circle_button;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getDefaultLargeCardImageRes() {
        return Integer.toString(R.drawable.three_circle_default_img);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public HandleCacheDataRunnable getRunnable() {
        return this.f;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getChildTag() {
        return "FunctionSetThreeCircleCardReader";
    }
}
