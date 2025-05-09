package defpackage;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.SpannableString;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.huawei.basichealthmodel.HealthModelH5ProService;
import com.huawei.basichealthmodel.R$plurals;
import com.huawei.basichealthmodel.R$string;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBean;
import com.huawei.health.health.utils.functionsetcard.FunctionSetType;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.health.healthmodel.bean.HealthLifeTaskBean;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.view.Clover;
import defpackage.ayy;
import health.compact.a.CommonUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes8.dex */
public class ayy extends FunctionSetBeanReader {

    /* renamed from: a, reason: collision with root package name */
    private Clover f288a;
    private final Observer b;
    private AtomicLong c;
    private Map<String, Object> d;
    private Context e;
    private FunctionSetBean f;
    private d g;
    private int h;
    private View i;
    private boolean j;
    private SpannableString k;
    private AtomicLong l;
    private final SparseArray<String> m;
    private final Observer n;
    private String o;
    private final SparseArray<String> p;
    private final SparseIntArray q;
    private AtomicInteger r;
    private AtomicLong t;

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getDefaultLargeCardImageRes() {
        return null;
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

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void updateSuccessList(List<Integer> list) {
    }

    public ayy(Context context, CardConfig cardConfig) {
        super(context, "HealthModel_FunctionSetHealthModelCardReader", cardConfig);
        this.n = new Observer() { // from class: ayy.3
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (BaseApplication.j()) {
                    if ("HEALTH_LIFE_CONS_INFO".equals(str) && objArr != null && objArr.length == 4) {
                        if (!(objArr[0] instanceof HealthLifeTaskBean) || !(objArr[1] instanceof ArrayList) || !(objArr[2] instanceof Integer) || !(objArr[3] instanceof Map)) {
                            return;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("HEALTH_LIFT_TASK_BEAN", (HealthLifeTaskBean) objArr[0]);
                        bundle.putIntegerArrayList("HEALTH_LIFT_TASK_CONS", (ArrayList) objArr[1]);
                        bundle.putInt("HEALTH_LIFT_TASK_CONS_MAX_DAYS", ((Integer) objArr[2]).intValue());
                        ayy.this.d = (Map) objArr[3];
                        azi.ma_(ayy.this.g, 6, bundle);
                    }
                    if ("PluginHealthModel".equals(str) || "HEALTH_LIFE_DAY_RECORD".equals(str)) {
                        azi.lY_(ayy.this.g, 1);
                    }
                }
            }
        };
        this.b = new Observer() { // from class: ayy.1
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (!"HOME_PAGE_SPORT_TASK_UPDATE".equals(str) || objArr == null || objArr.length <= 0) {
                    LogUtil.h("HealthModel_FunctionSetHealthModelCardReader", "mBasicSportDataObserver notify label ", str, " objects ", objArr);
                    return;
                }
                if (BaseApplication.j()) {
                    Object obj = objArr[0];
                    if (!(obj instanceof HealthLifeBean)) {
                        LogUtil.h("HealthModel_FunctionSetHealthModelCardReader", "mBasicSportDataObserver object instanceof HealthLifeBean false");
                        return;
                    }
                    HealthLifeBean healthLifeBean = (HealthLifeBean) obj;
                    int id = healthLifeBean.getId();
                    LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "mBasicSportDataObserver id ", Integer.valueOf(id), " bean ", healthLifeBean);
                    if (id == 2 || id == 3) {
                        if (ayy.this.q.get(id) > 0) {
                            return;
                        }
                        int h = CommonUtils.h((String) ayy.this.p.get(id));
                        if (h <= 0) {
                            azi.lY_(ayy.this.g, 1);
                            return;
                        }
                        float f = h;
                        if (Float.compare(azi.d(CommonUtils.h((String) ayy.this.m.get(id)) / f, false), azi.d(CommonUtils.h(healthLifeBean.getResult()) / f, healthLifeBean.getComplete() > 0)) == 0) {
                            return;
                        }
                        azi.lY_(ayy.this.g, 1);
                        return;
                    }
                    LogUtil.h("HealthModel_FunctionSetHealthModelCardReader", "mBasicSportDataObserver default object ", obj);
                }
            }
        };
        this.p = new SparseArray<>();
        this.m = new SparseArray<>();
        this.q = new SparseIntArray();
        this.d = new HashMap();
        LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "FunctionSetHealthModelCardReader context ", context);
        d dVar = new d(this);
        this.g = dVar;
        dVar.sendEmptyMessageDelayed(100, 6000L);
        if (context instanceof Activity) {
            this.e = context;
        } else {
            this.e = BaseApplication.wa_();
        }
        e();
        j();
    }

    private void j() {
        ObserverManagerUtil.c("HEALTH_MODEL_CARD_KEY_NEW", new Object[0]);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public boolean handleHealthModelSp(String str) {
        LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "handleHealthModelSp");
        if (!azi.aa()) {
            f();
            return false;
        }
        SparseIntArray sparseIntArray = new SparseIntArray();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        List<HealthLifeBean> arrayList = new ArrayList<>();
        try {
            arrayList = (List) HiJsonUtil.b(str, new TypeToken<List<HealthLifeBean>>() { // from class: ayy.4
            }.getType());
        } catch (JsonParseException | IllegalStateException e) {
            LogUtil.b("HealthModel_FunctionSetHealthModelCardReader", "handleHealthModelSp exception ", LogAnonymous.b(e));
        }
        if (lt_(arrayList, sparseIntArray, sparseIntArray2)) {
            setHasCardData(true);
            lw_(sparseIntArray, sparseIntArray2, arrayList, true);
            return true;
        }
        f();
        azi.lY_(this.g, 1);
        return false;
    }

    private void f() {
        setHasCardData(false);
        setFunctionSetBean(buildEmptyCardBean());
        refreshCardBySp(this.f);
    }

    private void e() {
        this.j = azi.ah();
        this.r = new AtomicInteger();
        this.l = new AtomicLong();
        this.t = new AtomicLong();
        this.c = new AtomicLong();
        ObserverManagerUtil.d(this.b, "HOME_PAGE_SPORT_TASK_UPDATE");
        ObserverManagerUtil.d(this.n, "PluginHealthModel");
        ObserverManagerUtil.d(this.n, "HEALTH_LIFE_DAY_RECORD");
        ObserverManagerUtil.d(this.n, "HEALTH_LIFE_CONS_INFO");
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void readCardData() {
        super.readCardData();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        h();
        ReleaseLogUtil.b("TimeEat_HealthModel_FunctionSetHealthModelCardReader", "onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void h() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < jdl.e(currentTimeMillis, 1, 0)) {
            bcm.e(azi.p());
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (Math.abs(elapsedRealtime - this.l.get()) < 1000 || this.j) {
            LogUtil.h("HealthModel_FunctionSetHealthModelCardReader", "refreshData repeated refresh mIsUseNewHealthModel ", Boolean.valueOf(this.j));
            return;
        }
        this.l.set(elapsedRealtime);
        if (this.g != null) {
            baq.b(new ResponseCallback() { // from class: ayv
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    ayy.this.a(i, (aur) obj);
                }
            });
        }
        if (azi.aa()) {
            return;
        }
        azi.b(ThreadPoolManager.d(), "HealthModelUpdateUserProtocolStatus", new Runnable() { // from class: ayx
            @Override // java.lang.Runnable
            public final void run() {
                LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "refreshData isAgreeUserProtocol ", Boolean.valueOf(azi.w()));
            }
        });
    }

    /* synthetic */ void a(int i, aur aurVar) {
        this.j = azi.ah();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "onDestroy");
        d dVar = this.g;
        if (dVar != null) {
            dVar.removeCallbacksAndMessages(null);
        }
        ObserverManagerUtil.e(this.b, "HOME_PAGE_SPORT_TASK_UPDATE");
        ObserverManagerUtil.c(this.n);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader, com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, FunctionSetSubCardData functionSetSubCardData) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onBindViewHolder(viewHolder, functionSetSubCardData);
        FunctionSetBean functionSetBean = functionSetSubCardData.getFunctionSetBean();
        if (functionSetBean == null || !(viewHolder instanceof FunctionSetBeanReader.MyHolder)) {
            LogUtil.h("HealthModel_FunctionSetHealthModelCardReader", "onBindViewHolder functionSetBean is null or holder null");
            return;
        }
        ImageView imageView = (ImageView) ((FunctionSetBeanReader.MyHolder) viewHolder).itemView.findViewById(R.id.function_set_empty_red_dot);
        if (imageView == null) {
            return;
        }
        if (functionSetBean.q() == FunctionSetBean.ViewType.EMPTY_VIEW) {
            nsy.cMA_(imageView, 8);
        } else if (functionSetBean.q() == FunctionSetBean.ViewType.DATA_VIEW) {
            if (functionSetBean.h()) {
                nsy.cMA_(imageView, 0);
            } else {
                nsy.cMA_(imageView, 8);
            }
        }
        ReleaseLogUtil.b("TimeEat_HealthModel_FunctionSetHealthModelCardReader", "onBindViewHolder finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void a(boolean z) {
        if (String.valueOf(true).equals(bao.e("health_model_is_join_future"))) {
            nrh.b(this.mContext, R$string.IDS_effective_tomorrow_life_new);
        } else {
            dsl.ZK_(this.e, Uri.parse("").buildUpon().appendQueryParameter("from", z ? "HealthModel_Cons_FunctionSetHealthModelCardReader" : "HealthModel_FunctionSetHealthModelCardReader").build());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "refreshCard");
        if (this.j) {
            return;
        }
        this.j = azi.ah();
        this.r.set(0);
        if (azi.aa()) {
            d();
        } else {
            azi.lY_(this.g, 4);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData
    public View createCardView() {
        boolean aa = azi.aa();
        LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "createCardView isJoin ", Boolean.valueOf(aa));
        if (aa) {
            b(true);
            return this.i;
        }
        d(buildEmptyCardBean());
        return null;
    }

    private void b(boolean z) {
        if (this.i == null) {
            LogUtil.h("HealthModel_FunctionSetHealthModelCardReader", "initClover mHealthModelLayout is null");
            View cKr_ = nsf.cKr_(this.mContext, R.layout.home_card_view, null);
            this.i = cKr_;
            if (cKr_ == null) {
                return;
            }
        }
        if (this.f288a == null) {
            LogUtil.h("HealthModel_FunctionSetHealthModelCardReader", "initClover mClover is null");
            this.f288a = (Clover) this.i.findViewById(R.id.home_card_clover);
            if (z) {
                LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "initClover0");
                this.f288a.setClover(0.0f, 0.0f, 0.0f);
            }
        }
        this.i.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: ayy.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ayy.this.i.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int min = Math.min(ayy.this.i.getWidth(), ayy.this.i.getHeight());
                ViewGroup.LayoutParams layoutParams = ayy.this.f288a.getLayoutParams();
                layoutParams.width = min;
                layoutParams.height = min;
                LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "getViewTreeObserver cloverSize" + min);
                ayy.this.f288a.setLayoutParams(layoutParams);
            }
        });
    }

    private void d() {
        this.t.set(SystemClock.elapsedRealtime());
        List<HealthLifeBean> d2 = bda.d();
        LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "getHealthModelData list ", d2);
        if (koq.b(d2) || d2.size() < 3) {
            azi.lY_(this.g, 3);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("arrayList", new ArrayList<>(d2));
        azi.ma_(this.g, 2, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ls_(Bundle bundle) {
        int i;
        if (this.f == null) {
            LogUtil.h("HealthModel_FunctionSetHealthModelCardReader", "mFunctionSetBean is null");
            return;
        }
        ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList("HEALTH_LIFT_TASK_CONS");
        if (koq.b(integerArrayList)) {
            return;
        }
        HealthLifeTaskBean healthLifeTaskBean = (HealthLifeTaskBean) bundle.getParcelable("HEALTH_LIFT_TASK_BEAN");
        int i2 = bundle.getInt("HEALTH_LIFT_TASK_CONS_MAX_DAYS");
        if (healthLifeTaskBean != null) {
            HealthLifeBean healthLifeBean = healthLifeTaskBean.getHealthLifeBean();
            i = healthLifeBean != null ? healthLifeBean.getContinueDays() : 0;
            this.h = healthLifeTaskBean.getIconId();
        } else {
            i = 0;
        }
        if (integerArrayList.size() == 1) {
            b(healthLifeTaskBean, i, i2);
        } else {
            c(integerArrayList);
        }
        LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "message is ", this.k.toString());
        this.f.a(this.k);
        this.f.YC_(nsf.cKq_(this.h));
        ixx.d().d(this.mContext, AnalyticsValue.HEALTH_LIFE_TASK_COMPLETE_2119097.value(), this.d, 0);
    }

    private void b(HealthLifeTaskBean healthLifeTaskBean, int i, int i2) {
        int i3;
        int i4 = R$plurals.IDS_multi_task_cons_info_for_other;
        if (LanguageUtil.h(this.mContext)) {
            if (i > i2) {
                i4 = R$plurals.IDS_over_max_cons_complte_info;
                i3 = i2;
            } else {
                i3 = i;
            }
            SpannableString spannableString = new SpannableString(nsf.a(i4, i3, healthLifeTaskBean.getName(), UnitUtil.e(i3, 1, 0)));
            this.k = spannableString;
            lv_(spannableString, healthLifeTaskBean.getName(), healthLifeTaskBean.getColorResourcesId());
            nsi.cKL_(this.k, healthLifeTaskBean.getName(), R$string.textFontFamilyMedium);
        } else {
            if (i > i2) {
                i4 = R$plurals.IDS_over_max_cons_complte_tips;
                i3 = i2;
            } else {
                i3 = i;
            }
            SpannableString spannableString2 = new SpannableString(nsf.a(i4, i3, UnitUtil.e(i3, 1, 0)));
            this.k = spannableString2;
            lv_(spannableString2, spannableString2.toString(), R.color._2131299241_res_0x7f090ba9);
        }
        LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "setMessageWhenSingleCons tempConsDays ", Integer.valueOf(i3), " consDays ", Integer.valueOf(i), " maxDays ", Integer.valueOf(i2));
    }

    private void c(List<Integer> list) {
        SpannableString spannableString = new SpannableString(nsf.a(R$plurals.IDS_multi_tasks_complte, list.size(), UnitUtil.e(list.size(), 1, 0)));
        this.k = spannableString;
        lv_(spannableString, spannableString.toString(), R.color._2131299241_res_0x7f090ba9);
    }

    private void lv_(SpannableString spannableString, String str, int i) {
        nsi.cKI_(spannableString, spannableString.toString(), R.color._2131299241_res_0x7f090ba9);
        nsi.cKI_(spannableString, str, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(FunctionSetBean functionSetBean) {
        LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "notifyDataView functionSetBean ", functionSetBean, " mCardViewHolder ", this.mCardViewHolder);
        if (functionSetBean == null) {
            return;
        }
        notifyItemChanged(functionSetBean);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.r.get() == 1) {
            LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "retry request end");
            if (azi.aa()) {
                return;
            }
            azi.lY_(this.g, 4);
            return;
        }
        this.r.getAndIncrement();
        d();
        LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "retry request retryCount:", Integer.valueOf(this.r.get()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<HealthLifeBean> list) {
        LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "showTaskData");
        SparseIntArray sparseIntArray = new SparseIntArray();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        if (lt_(list, sparseIntArray, sparseIntArray2)) {
            lw_(sparseIntArray, sparseIntArray2, list, false);
        } else {
            azi.lY_(this.g, 3);
        }
    }

    private void lw_(SparseIntArray sparseIntArray, SparseIntArray sparseIntArray2, List<HealthLifeBean> list, boolean z) {
        LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "showDataCard");
        if (sparseIntArray2 == null || sparseIntArray == null) {
            LogUtil.h("HealthModel_FunctionSetHealthModelCardReader", "showDataCard totalTasks ", sparseIntArray2, " completeTasks ", sparseIntArray);
            if (z) {
                refreshCardBySp(buildEmptyCardBean());
                return;
            }
            return;
        }
        String string = this.mContext.getResources().getString(R$string.IDS_current_total_time, azi.c(sparseIntArray.size(), 1, 0), azi.c(sparseIntArray2.size(), 1, 0));
        if (this.j) {
            string = this.mContext.getResources().getString(R$string.IDS_current_total_time, "--", "--");
        }
        this.f = a(list, string);
        b(false);
        if (this.j) {
            this.f288a.setClover(0.0f, 0.0f, 0.0f);
            if (z) {
                refreshCardBySp(this.f);
                return;
            } else {
                d(this.f);
                return;
            }
        }
        lu_(sparseIntArray, sparseIntArray2, list);
        if (z) {
            refreshCardBySp(this.f);
        } else {
            d(this.f);
        }
        if (string.equals(this.o)) {
            return;
        }
        this.o = string;
    }

    private FunctionSetBean a(List<HealthLifeBean> list, String str) {
        String d2;
        if (koq.b(list)) {
            LogUtil.h("HealthModel_FunctionSetHealthModelCardReader", "createFunctionSetBean list ", list);
            d2 = DateFormatUtil.d(System.currentTimeMillis(), DateFormatUtil.DateFormatType.DATE_FORMAT_MD);
        } else {
            HealthLifeBean healthLifeBean = list.get(0);
            if (healthLifeBean == null) {
                LogUtil.h("HealthModel_FunctionSetHealthModelCardReader", "createFunctionSetBean bean is null");
                d2 = DateFormatUtil.d(System.currentTimeMillis(), DateFormatUtil.DateFormatType.DATE_FORMAT_MD);
            } else {
                d2 = DateFormatUtil.d(azi.c(healthLifeBean), DateFormatUtil.DateFormatType.DATE_FORMAT_MD);
            }
        }
        Drawable drawable = this.h != 0 ? ContextCompat.getDrawable(this.mContext, this.h) : null;
        setBiHasData(System.currentTimeMillis());
        return new FunctionSetBean.a(azi.i()).d(this.mContext).a(FunctionSetType.HEALTH_MODEL_CARD).c(d2).b(FunctionSetBean.ViewType.DATA_VIEW).a(str).b(nsf.h(R$string.IDS_card_task_attainment)).d(this.k).YF_(drawable).c();
    }

    private void lu_(SparseIntArray sparseIntArray, SparseIntArray sparseIntArray2, List<HealthLifeBean> list) {
        float b;
        float b2;
        float f;
        boolean z;
        if (this.f288a == null) {
            LogUtil.h("HealthModel_FunctionSetHealthModelCardReader", "setCloverScale mClover is null");
            return;
        }
        if (azi.ae()) {
            int q = azi.q();
            f = azi.e(list, 3, q);
            b = azi.e(list, 1, q);
            b2 = azi.e(list, 2, q);
            if (b < 1.0f || f < 1.0f || b2 < 1.0f) {
                z = false;
            } else {
                z = azi.a(list) > 0 ? azi.i(list) : true;
                avm.a().h();
            }
            this.f288a.setAllFull(z);
        } else {
            b = b(sparseIntArray.get(2) + sparseIntArray.get(3) + sparseIntArray.get(4), sparseIntArray2.get(2) + sparseIntArray2.get(3) + sparseIntArray2.get(4));
            float b3 = b(sparseIntArray.get(5) + sparseIntArray.get(8), sparseIntArray2.get(5) + sparseIntArray2.get(8));
            b2 = b(sparseIntArray.get(6) + sparseIntArray.get(7), sparseIntArray2.get(6) + sparseIntArray2.get(7));
            f = b3;
        }
        LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "setCloverScale top ", Float.valueOf(b), " left ", Float.valueOf(f), " right ", Float.valueOf(b2));
        this.f288a.setClover(b, f, b2);
    }

    private float b(int i, int i2) {
        if (i == 0 || i2 == 0) {
            return 0.0f;
        }
        return azi.a(i / i2);
    }

    private boolean lt_(List<HealthLifeBean> list, SparseIntArray sparseIntArray, SparseIntArray sparseIntArray2) {
        LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "isResolveData list ", list);
        if (koq.b(list)) {
            return false;
        }
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null) {
                int id = healthLifeBean.getId();
                int complete = healthLifeBean.getComplete();
                this.q.append(id, complete);
                this.p.append(id, healthLifeBean.getTarget());
                this.m.append(id, healthLifeBean.getResult());
                sparseIntArray2.put(id, 1);
                if (complete > 0) {
                    sparseIntArray.put(id, 1);
                }
                if (!azi.c(id)) {
                    LogUtil.h("HealthModel_FunctionSetHealthModelCardReader", "isResolveData taskId ", Integer.valueOf(id));
                    return false;
                }
            }
        }
        return true;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getCardTitle() {
        FunctionSetBean functionSetBean = this.f;
        if (functionSetBean == null) {
            LogUtil.h("HealthModel_FunctionSetHealthModelCardReader", "getCardTitle mFunctionSetBean is null");
            return azi.i();
        }
        return functionSetBean.n();
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public FunctionSetBean buildEmptyCardBean() {
        LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "setEmptyFunctionSetBean");
        FunctionSetBean c = new FunctionSetBean.a(azi.i()).e(nsf.h(com.huawei.health.servicesui.R$string.IDS_health_model_home_card_description)).a(FunctionSetType.HEALTH_MODEL_CARD).b(FunctionSetBean.ViewType.EMPTY_VIEW).d(this.mContext).c();
        this.f = c;
        return c;
    }

    private void c() {
        this.h = 0;
        this.k = null;
        FunctionSetBean functionSetBean = this.f;
        if (functionSetBean != null) {
            functionSetBean.YC_(null);
            this.f.a(null);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public void onCardViewClickListener(FunctionSetBean.ViewType viewType) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        LogUtil.a("HealthModel_FunctionSetHealthModelCardReader", "onBindViewHolder onClick elapsedRealtime ", Long.valueOf(elapsedRealtime));
        if (Math.abs(elapsedRealtime - this.c.get()) < 800) {
            LogUtil.h("HealthModel_FunctionSetHealthModelCardReader", "onBindViewHolder onClick fast click");
            return;
        }
        final boolean z = false;
        if (this.h != 0 && this.k != null) {
            ixx.d().d(this.mContext, AnalyticsValue.HEALTH_LIFE_TASK_COMPLETE_2119097.value(), this.d, 0);
            z = true;
        }
        this.c.set(elapsedRealtime);
        c();
        if (LoginInit.getInstance(this.mContext).getIsLogined()) {
            a(z);
        } else {
            LoginInit.getInstance(BaseApplication.e()).browsingToLogin(new IBaseResponseCallback() { // from class: ayw
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    ayy.this.c(z, i, obj);
                }
            }, "");
        }
    }

    /* synthetic */ void c(final boolean z, int i, Object obj) {
        if (i != 0) {
            LogUtil.h("HealthModel_FunctionSetHealthModelCardReader", "onBindViewHolder browsingToLogin fail");
        } else if (azi.aa()) {
            b();
            a(z);
        } else {
            baq.b(new ResponseCallback() { // from class: ayu
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i2, Object obj2) {
                    ayy.this.c(z, i2, (aur) obj2);
                }
            });
        }
    }

    /* synthetic */ void c(boolean z, int i, aur aurVar) {
        b();
        a(z);
    }

    static class d extends BaseHandler<ayy> {
        private WeakReference<ayy> b;

        d(ayy ayyVar) {
            super(Looper.getMainLooper(), ayyVar);
            this.b = new WeakReference<>(ayyVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: lx_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ayy ayyVar, Message message) {
            if (message == null) {
                LogUtil.b("HealthModel_FunctionSetHealthModelCardReader", "msg is null");
                return;
            }
            int i = message.what;
            if (i != 100) {
                switch (i) {
                    case 1:
                        ayyVar.b();
                        break;
                    case 2:
                        Bundle data = message.getData();
                        if (data != null) {
                            ayy.setHasCardData(this.b, true);
                            ayyVar.b(data.getParcelableArrayList("arrayList"));
                            break;
                        } else {
                            LogUtil.b("HealthModel_FunctionSetHealthModelCardReader", "bundle is null");
                            break;
                        }
                    case 3:
                        ayyVar.g();
                        break;
                    case 4:
                        ayy.setHasCardData(this.b, false);
                        ayyVar.d(ayyVar.buildEmptyCardBean());
                        break;
                    case 5:
                        ayy.setHasCardData(this.b, true);
                        ayyVar.d(ayyVar.f);
                        break;
                    case 6:
                        ayy.setHasCardData(this.b, true);
                        ayyVar.ls_(message.getData());
                        ayyVar.d(ayyVar.f);
                        break;
                    default:
                        LogUtil.h("HealthModel_FunctionSetHealthModelCardReader", "handler message default");
                        break;
                }
                return;
            }
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: azc
                @Override // java.lang.Runnable
                public final void run() {
                    ayy.d.e();
                }
            });
        }

        static /* synthetic */ void e() {
            bzs.e().initH5Pro();
            H5ProClient.getServiceManager().registerService(HealthModelH5ProService.class);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getDefaultImage(boolean z) {
        if (z) {
            return R$drawable.helath_life_default_img_big;
        }
        return R$drawable.health_life_default_img_small;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public int getButtonTextId() {
        return com.huawei.ui.commonui.R$string.IDS_function_set_card_button_health_life;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader
    public String getChildTag() {
        return "HealthModel_FunctionSetHealthModelCardReader";
    }
}
