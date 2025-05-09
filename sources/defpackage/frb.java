package defpackage;

import android.app.Activity;
import android.content.Intent;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import androidx.collection.ArrayMap;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.callback.JudgeCallback;
import com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract;
import com.huawei.health.suggestion.util.ResourceJudgeUtil;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.pluginfitnessadvice.BelongInfo;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.RecommendationWorkout;
import com.huawei.trade.PayApi;
import com.huawei.trade.TradeViewApi;
import com.huawei.trade.datatype.ProductSummary;
import com.huawei.trade.datatype.TradeViewInfo;
import com.huawei.ui.commonui.webview.WebViewActivity;
import defpackage.ffl;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class frb implements TrainDetailContract.Ipresenter {

    /* renamed from: a, reason: collision with root package name */
    private int f12617a;
    private int b;
    private int c;
    private BelongInfo d;
    private CourseApi e;
    private ProductSummary f;
    private boolean g = false;
    private FitWorkout h;
    private String i;
    private ProductSummary j;
    private fqy k;
    private long l;
    private TradeViewApi m;
    private TrainDetailContract.Iview n;
    private View o;
    private VipApi p;

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Ipresenter
    public boolean isCoursePaid(int i) {
        return i == 1;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Ipresenter
    public boolean isFreeCourses(int i) {
        return i == 0;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Ipresenter
    public boolean isPayCourses(int i) {
        return i == 1;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Ipresenter
    public boolean isVipCourses(int i) {
        return i == 2;
    }

    public frb(TrainDetailContract.Iview iview, fqy fqyVar) {
        this.n = iview;
        this.k = fqyVar;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Ipresenter
    public void initTradeView(FitWorkout fitWorkout) {
        this.h = fitWorkout;
        this.l = System.currentTimeMillis();
        this.b = fitWorkout.acquireAuthResult();
        int c = c(fitWorkout);
        this.f12617a = c;
        this.d = fitWorkout.getCourseBelongInfoByType(c);
        this.c = fpq.b(this.h);
        if (koq.c(fitWorkout.getBelongInfoList())) {
            this.i = fitWorkout.getBelongInfoList().get(0).getName();
        } else {
            this.i = fitWorkout.acquireName();
        }
        ReleaseLogUtil.e("Suggestion_TrainDetailPresenter", "initTradeView priceType= ", Integer.valueOf(this.c), " authResult=", Integer.valueOf(this.b), " belongType=", Integer.valueOf(this.f12617a));
        if (isFreeCourses(this.c)) {
            this.g = false;
            TrainDetailContract.Iview iview = this.n;
            if (iview != null) {
                iview.showDownloadView();
                return;
            }
            return;
        }
        TrainDetailContract.Iview iview2 = this.n;
        if (iview2 != null) {
            iview2.hideDownloadView();
        }
        if (isPayCourses(this.c)) {
            if (isCoursePaid(this.b)) {
                a(true);
                m();
                return;
            } else {
                a(false);
                a();
                e();
                return;
            }
        }
        if (isVipCourses(this.c)) {
            l();
        }
    }

    private void a(boolean z) {
        TrainDetailContract.Iview iview = this.n;
        if (iview == null) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "changBuyState mView == null");
        } else {
            iview.changBuyState(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        TrainDetailContract.Iview iview = this.n;
        if (iview == null) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "changVipState mView == null");
        } else {
            iview.changeVipState(z);
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Ipresenter
    public void onResume() {
        LogUtil.a("Suggestion_TrainDetailPresenter", "presenter 》onResume 》mIsNeedRefreshTradeState", Boolean.valueOf(this.g));
        if (this.g) {
            judgeVipState();
            l();
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Ipresenter
    public void judgeVipState() {
        VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
        if (vipApi == null) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "refreshVipStateData VipApi is null");
        } else {
            vipApi.getVipInfo(new VipCallback() { // from class: frb.3
                @Override // com.huawei.health.vip.api.VipCallback
                public void onSuccess(Object obj) {
                    ReleaseLogUtil.e("Suggestion_TrainDetailPresenter", "getVipInfo success.");
                    if (obj == null) {
                        LogUtil.a("Suggestion_TrainDetailPresenter", "getVipInfo onSuccess result is null");
                        return;
                    }
                    UserMemberInfo userMemberInfo = obj instanceof UserMemberInfo ? (UserMemberInfo) obj : null;
                    if (userMemberInfo != null && userMemberInfo.getMemberFlag() == 1 && !gpn.d(userMemberInfo)) {
                        frb.this.d(true);
                    } else {
                        ReleaseLogUtil.d("Suggestion_TrainDetailPresenter", "TradeViewApi memberInfo == null or != VipConstants.MEMBER_FLAG_VIP");
                        frb.this.d(false);
                    }
                }

                @Override // com.huawei.health.vip.api.VipCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.d("Suggestion_TrainDetailPresenter", "getVipInfo errorCode=", Integer.valueOf(i), " errMsg=", str);
                }
            });
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Ipresenter
    public void onDestroy() {
        if (isVipCourses(this.c)) {
            k();
        } else if (isPayCourses(this.c)) {
            m();
        }
        TrainDetailContract.Iview iview = this.n;
        if (iview != null) {
            iview.hideDownloadView();
        }
        this.n = null;
        if (this.e != null) {
            this.e = null;
        }
        if (this.p != null) {
            this.p = null;
        }
        o();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Ipresenter
    public void initNoticeContent(String str, String str2) {
        TrainDetailContract.Iview iview = this.n;
        if (iview == null) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "mView == null");
            return;
        }
        WeakReference<Activity> acquireActivity = iview.acquireActivity();
        if (acquireActivity == null) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "initNoticeContent>activity==null");
        } else {
            aEK_(acquireActivity.get(), str, str2);
        }
    }

    private void aEK_(final Activity activity, final String str, final String str2) {
        if (activity == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "initSpannableString input params is invalid.");
            return;
        }
        String string = activity.getResources().getString(R.string._2130849773_res_0x7f022fed, str);
        int length = string.length();
        int indexOf = string.indexOf(str);
        if (indexOf < 0 || indexOf > length) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "noticeTitleStartIndex = ", Integer.valueOf(indexOf), "..lenght=", Integer.valueOf(length), "notice = ", string);
            return;
        }
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new ForegroundColorSpan(activity.getResources().getColor(R.color._2131299177_res_0x7f090b69)), indexOf, length, 33);
        spannableString.setSpan(new ClickableSpan() { // from class: frb.1
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                frb.this.aEL_(activity, str2, str);
                ViewClickInstrumentation.clickOnView(view);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(activity.getResources().getColor(R.color._2131299177_res_0x7f090b69));
                textPaint.setUnderlineText(false);
            }
        }, indexOf, length, 33);
        this.n.setNoticeContent(spannableString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aEL_(Activity activity, String str, String str2) {
        if (activity == null) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "activity == null");
            return;
        }
        Intent intent = new Intent(activity, (Class<?>) WebViewActivity.class);
        intent.putExtra("WebViewActivity.REQUEST_URL_KEY", str);
        intent.putExtra("WebViewActivity.TITLE", str2);
        gnm.aPB_(BaseApplication.getContext(), intent);
        if (this.n == null) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "mView == null");
            return;
        }
        HashMap hashMap = new HashMap(10);
        hashMap.put("resource_name", this.n.acquireWorkoutName());
        hashMap.put("resource_id", g());
        gge.e(AnalyticsValue.TRADE_DETAIL_VIEW_NOTICE.value(), hashMap);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Ipresenter
    public void getRecommendationList(List<RecommendationWorkout> list) {
        if (this.e == null) {
            this.e = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        }
        if (this.e == null) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "CourseApi is null");
            return;
        }
        if (koq.b(list)) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "recommendationList is empty");
            return;
        }
        final ArrayMap arrayMap = new ArrayMap(16);
        ArrayList arrayList = new ArrayList(list.size());
        for (RecommendationWorkout recommendationWorkout : list) {
            if (recommendationWorkout != null && !TextUtils.isEmpty(recommendationWorkout.getWorkoutId())) {
                arrayList.add(new ffl.d(recommendationWorkout.getWorkoutId()).b());
                arrayMap.put(recommendationWorkout.getWorkoutId(), recommendationWorkout.getTopicListName());
            }
        }
        this.e.getCourseByIds(arrayList, false, new UiCallback<List<FitWorkout>>() { // from class: frb.2
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.h("Suggestion_TrainDetailPresenter", "getCourseByIds onFailure ", str, "errorCode is ", Integer.valueOf(i));
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<FitWorkout> list2) {
                LogUtil.a("Suggestion_TrainDetailPresenter", "getCourseByIds onSuccess ");
                if (frb.this.n != null) {
                    frb.this.n.bindRecommendationData(list2, arrayMap);
                }
            }
        });
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Ipresenter
    public void onActivityResult(int i, int i2, Intent intent) {
        ReleaseLogUtil.e("Suggestion_TrainDetailPresenter", "requestCode:", Integer.valueOf(i), "resultCode:", Integer.valueOf(i2));
        VipApi vipApi = this.p;
        if (vipApi != null) {
            vipApi.notifyActivityResult(this.o, i, i2, intent);
        }
        TrainDetailContract.Iview iview = this.n;
        if (iview != null) {
            iview.notifyActivityResult(i, i2, intent);
        }
    }

    private void e() {
        this.g = true;
        TrainDetailContract.Iview iview = this.n;
        if (iview == null) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "addTradeView>mView==null");
            return;
        }
        WeakReference<Activity> acquireActivity = iview.acquireActivity();
        if (acquireActivity == null || acquireActivity.get() == null) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "addTradeView>activity==null");
            return;
        }
        TradeViewApi tradeViewApi = (TradeViewApi) Services.a("TradeService", TradeViewApi.class);
        this.m = tradeViewApi;
        if (tradeViewApi == null) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "TradeViewApi is null");
            return;
        }
        if (this.o != null) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "addTradeView>mTradeView != null");
            this.m.refreshView(this.o);
            return;
        }
        TradeViewInfo h = h();
        View tradeView = this.m.getTradeView(acquireActivity.get(), h);
        this.o = tradeView;
        if (tradeView != null) {
            ReleaseLogUtil.e("Suggestion_TrainDetailPresenter", "addTradeView.");
            this.n.addTradeView(this.o);
        } else {
            ReleaseLogUtil.d("Suggestion_TrainDetailPresenter", "addTradeView>mTradeView>is_null,mWorkId=", g());
        }
    }

    private TradeViewInfo h() {
        TradeViewInfo tradeViewInfo = new TradeViewInfo(g(), f());
        int i = this.f12617a;
        if (i == 1) {
            tradeViewInfo.setButtonName(BaseApplication.getContext().getString(R.string._2130845470_res_0x7f021f1e));
        } else if (i == 0) {
            tradeViewInfo.setButtonName(BaseApplication.getContext().getString(R.string._2130845347_res_0x7f021ea3));
        } else {
            LogUtil.a("Suggestion_TrainDetailPresenter", "is single course.");
        }
        if (!TextUtils.isEmpty(this.i)) {
            tradeViewInfo.setTrigResName(this.i);
        }
        if (this.k != null) {
            tradeViewInfo.setBiParams(j());
        }
        return tradeViewInfo;
    }

    private Map<String, String> j() {
        HashMap hashMap = new HashMap(16);
        hashMap.put(WebViewHelp.BI_KEY_PULL_FROM, this.k.k());
        hashMap.put("resourceId", this.k.o());
        hashMap.put("resourceName", this.k.r());
        hashMap.put("pullOrder", this.k.n());
        hashMap.put("buyStage", "2");
        hashMap.put("payResourceType", c());
        return hashMap;
    }

    private String c() {
        return "1".equals(this.k.i()) ? "8" : "2";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (!HandlerExecutor.c()) {
            HandlerExecutor.e(new Runnable() { // from class: fri
                @Override // java.lang.Runnable
                public final void run() {
                    frb.this.m();
                }
            });
            return;
        }
        TrainDetailContract.Iview iview = this.n;
        if (iview == null) {
            return;
        }
        View view = this.o;
        if (view == null) {
            iview.showDownloadView();
            return;
        }
        iview.recycleTradeView(view);
        TradeViewApi tradeViewApi = this.m;
        if (tradeViewApi != null) {
            tradeViewApi.cancelView(this.o);
        }
        this.o = null;
        this.g = false;
    }

    private void d() {
        this.g = true;
        TrainDetailContract.Iview iview = this.n;
        if (iview == null) {
            LogUtil.h("Suggestion_TrainDetailPresenter", " addVipView mView == null");
            return;
        }
        if (this.o != null) {
            LogUtil.h("Suggestion_TrainDetailPresenter", " addVipView mTradeView != null");
            return;
        }
        WeakReference<Activity> acquireActivity = iview.acquireActivity();
        if (acquireActivity == null || acquireActivity.get() == null) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "TradeViewApi>addVipView>activity == null");
            return;
        }
        final Activity activity = acquireActivity.get();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: fre
            @Override // java.lang.Runnable
            public final void run() {
                frb.this.aEM_(activity);
            }
        });
    }

    /* synthetic */ void aEM_(Activity activity) {
        String acquireWorkoutName;
        if (this.p == null) {
            this.p = (VipApi) Services.a("vip", VipApi.class);
        }
        if (this.p == null) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "addVipView >VipApi is null");
            return;
        }
        if (this.n != null) {
            String c = c();
            if ("1".equals(this.k.i())) {
                acquireWorkoutName = this.i;
            } else {
                acquireWorkoutName = this.n.acquireWorkoutName();
            }
            this.n.bindProductCourseSummary(this.f, this.j, h());
            View vipPayView = this.p.getVipPayView(activity, "", "#/PayPopup?benefitName=" + acquireWorkoutName + "&payResourceType=" + c, i());
            this.o = vipPayView;
            if (vipPayView != null) {
                ReleaseLogUtil.d("Suggestion_TrainDetailPresenter", "addVipView >  mView.addTradeView");
                this.n.addTradeView(this.o);
            }
        }
    }

    private Map<String, Object> i() {
        HashMap hashMap = new HashMap(5);
        hashMap.put(WebViewHelp.BI_KEY_PULL_FROM, this.k.k());
        hashMap.put("resourceId", this.k.o());
        hashMap.put("resourceName", this.k.r());
        hashMap.put("pullOrder", this.k.n());
        hashMap.put("workout_id", this.k.w());
        hashMap.put("algId", this.k.b());
        return hashMap;
    }

    private void k() {
        TrainDetailContract.Iview iview = this.n;
        if (iview == null) {
            return;
        }
        WeakReference<Activity> acquireActivity = iview.acquireActivity();
        if (acquireActivity == null || acquireActivity.get() == null) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "TradeViewApi>recycleVipView>activity == null");
            return;
        }
        Activity activity = acquireActivity.get();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: frc
            @Override // java.lang.Runnable
            public final void run() {
                frb.this.b();
            }
        });
    }

    /* synthetic */ void b() {
        TrainDetailContract.Iview iview = this.n;
        if (iview == null) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "recycleVipView>mView==null");
            return;
        }
        View view = this.o;
        if (view == null) {
            iview.showDownloadView();
            return;
        }
        iview.recycleTradeView(view);
        VipApi vipApi = this.p;
        if (vipApi != null) {
            vipApi.destoryVipPayView(this.o);
        }
        this.g = false;
        this.o = null;
    }

    private void l() {
        if (this.n == null) {
            return;
        }
        ResourceJudgeUtil.c(f(), g(), isVipCourses(this.c) ? 3 : 2, new JudgeCallback() { // from class: frg
            @Override // com.huawei.health.suggestion.callback.JudgeCallback
            public final void onJudgeCallback(Object obj) {
                frb.this.d((Integer) obj);
            }
        });
    }

    /* synthetic */ void d(Integer num) {
        if (num.intValue() == 1 && isPayCourses(this.c)) {
            a(true);
        }
        if (num.intValue() == 3) {
            d();
            d(false);
            return;
        }
        if (num.intValue() == 2) {
            if (isVipCourses(this.c)) {
                d();
                d(false);
                return;
            } else {
                e();
                return;
            }
        }
        if (num.intValue() == 1 && isVipCourses(this.c)) {
            d(true);
        }
        n();
    }

    private void n() {
        if (isVipCourses(this.c)) {
            k();
        } else {
            m();
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.mvp.TrainDetailContract.Ipresenter
    public void refreshCourseData() {
        if (this.g && this.n != null) {
            if (this.e == null) {
                this.e = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
            }
            CourseApi courseApi = this.e;
            if (courseApi == null) {
                LogUtil.h("Suggestion_TrainDetailPresenter", "TradeViewApi is null");
            } else {
                this.g = false;
                courseApi.getCourseById(new ffl.d(this.k.w()).d(this.k.y()).b(), new fnk(this));
            }
        }
    }

    private void o() {
        long currentTimeMillis = System.currentTimeMillis();
        HashMap hashMap = new HashMap(10);
        hashMap.put("enter_time", Long.valueOf(this.l));
        hashMap.put("leave_time", Long.valueOf(currentTimeMillis));
        hashMap.put("stay_time", Long.valueOf(currentTimeMillis - this.l));
        hashMap.put("payType", Integer.valueOf(this.c));
        hashMap.put("resourceType", Integer.valueOf(f()));
        FitWorkout fitWorkout = this.h;
        if (fitWorkout != null) {
            hashMap.put("resourceName", fitWorkout.acquireName());
        }
        gge.e(AnalyticsValue.TRADE_DETAIL_DESTROY.value(), hashMap);
    }

    private String g() {
        if (this.n == null) {
            return "";
        }
        int i = this.f12617a;
        if (i == 1) {
            return this.k.g();
        }
        if (i == 0) {
            if (TextUtils.isEmpty(this.k.x())) {
                return this.d.getId();
            }
            return this.k.x();
        }
        return this.k.w();
    }

    private int f() {
        int i = this.f12617a;
        if (i == 1) {
            return CommonUtil.e(this.k.p(), 2);
        }
        return i == 0 ? 10 : 1;
    }

    private int c(FitWorkout fitWorkout) {
        if (TextUtils.isEmpty(this.k.g())) {
            return (TextUtils.isEmpty(this.k.x()) && fitWorkout.getCourseBelongInfoByType(0) == null) ? -1 : 0;
        }
        return 1;
    }

    private void a() {
        ((PayApi) Services.c("TradeService", PayApi.class)).getProductSummaryInfo(g(), f(), new IBaseResponseCallback() { // from class: fra
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                frb.this.c(i, obj);
            }
        });
    }

    /* synthetic */ void c(int i, Object obj) {
        if (i == 0 && (obj instanceof List)) {
            LogUtil.a("Suggestion_TrainDetailPresenter", "getProductSummaryInfo success");
            c((List<ProductSummary>) obj);
        } else {
            LogUtil.h("Suggestion_TrainDetailPresenter", "getProductSummaryInfo fail errorCode = ", Integer.valueOf(i));
        }
    }

    private void c(List<ProductSummary> list) {
        if (koq.b(list)) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "productSummaries is empty");
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (ProductSummary productSummary : list) {
            if (productSummary.isMemberFlag()) {
                arrayList.add(productSummary);
            } else {
                arrayList2.add(productSummary);
            }
        }
        if (koq.b(arrayList2)) {
            LogUtil.h("Suggestion_TrainDetailPresenter", "normalProductSummaries is empty");
            return;
        }
        e(arrayList2);
        ProductSummary productSummary2 = arrayList2.get(0);
        this.f = productSummary2;
        TrainDetailContract.Iview iview = this.n;
        if (iview == null) {
            ReleaseLogUtil.d("Suggestion_TrainDetailPresenter", "dealProductSummaries mView == null");
            return;
        }
        iview.bindProductCourseSummary(productSummary2, this.j, h());
        if (koq.b(arrayList)) {
            LogUtil.a("Suggestion_TrainDetailPresenter", "not have memberPrice");
            return;
        }
        e(arrayList);
        ProductSummary productSummary3 = arrayList.get(0);
        this.j = productSummary3;
        this.n.bindProductCourseSummary(this.f, productSummary3, h());
    }

    private void e(List<ProductSummary> list) {
        Collections.sort(list, new Comparator() { // from class: frd
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Integer.compare(((ProductSummary) obj2).getPriority(), ((ProductSummary) obj).getPriority());
                return compare;
            }
        });
    }
}
