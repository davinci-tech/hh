package com.huawei.health.marketing.views;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.marketing.datatype.templates.FamousTeacherSeriesRecTemplate;
import com.huawei.health.marketing.datatype.templates.SingleFamousTeacherContent;
import com.huawei.health.marketing.datatype.templates.SingleFamousTeacherStandardContent;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.marketing.views.FamousTeacherAvatarAdapter;
import com.huawei.health.marketing.views.FamousTeacherLayout;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.sport.utils.ResultUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ehy;
import defpackage.eie;
import defpackage.eiv;
import defpackage.ffl;
import defpackage.ffy;
import defpackage.gic;
import defpackage.ixx;
import defpackage.jcf;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class FamousTeacherLayout extends LinearLayout implements View.OnClickListener, ResourceBriefInfoOwnable {

    /* renamed from: a, reason: collision with root package name */
    private FamousTeacherClassAdapter f2869a;
    private HealthRecycleView b;
    private HealthSubHeader c;
    private FamousTeacherAvatarAdapter d;
    private LinearLayout e;
    private int f;
    private String g;
    private Pair<Integer, Integer> h;
    private ResourceBriefInfo i;
    private long j;
    private ImageView k;
    private HealthTextView l;
    private HealthCardView m;
    private HealthRecycleView n;
    private HealthTextView o;
    private b p;
    private FamousTeacherSeriesRecTemplate q;
    private a r;
    private SingleFamousTeacherStandardContent t;

    public FamousTeacherLayout(Context context) {
        super(context);
        this.h = BaseActivity.getSafeRegionWidth();
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public boolean isOwnedByBriefInfo(ResourceBriefInfo resourceBriefInfo) {
        return (resourceBriefInfo == null || this.i == null || !resourceBriefInfo.getResourceId().equals(this.i.getResourceId())) ? false : true;
    }

    @Override // com.huawei.health.marketing.views.ResourceBriefInfoOwnable
    public int getResourceBriefPriority() {
        ResourceBriefInfo resourceBriefInfo = this.i;
        if (resourceBriefInfo != null) {
            return resourceBriefInfo.getPriority();
        }
        return 0;
    }

    static class d extends BaseHandler<FamousTeacherLayout> {
        d(FamousTeacherLayout famousTeacherLayout) {
            super(famousTeacherLayout);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: apk_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(FamousTeacherLayout famousTeacherLayout, Message message) {
            if (message.what == 1000) {
                if (famousTeacherLayout.t == null || famousTeacherLayout.f2869a == null) {
                    return;
                }
                famousTeacherLayout.f2869a.c(famousTeacherLayout.t);
                return;
            }
            LogUtil.h("FamousTeacherLayout", "handleMessageWhenReferenceNotNull not match ");
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null || nsn.o()) {
            LogUtil.h("FamousTeacherLayout", "view = null or isFastClick");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.hwsubheader_more_container) {
            if (TextUtils.isEmpty(this.g)) {
                LogUtil.h("FamousTeacherLayout", "mMoreUrl is empty");
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else {
                if (!eie.b(this.g)) {
                    a(this.g);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: ejx
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        FamousTeacherLayout.this.c(i, obj);
                    }
                }, AnalyticsValue.MARKETING_RESOURCE.value());
            }
        } else {
            LogUtil.h("FamousTeacherLayout", "click not match");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void c(int i, Object obj) {
        if (i == 0) {
            a(this.g);
        } else {
            LogUtil.h("FamousTeacherLayout", "onClick errorCode = ", Integer.valueOf(i));
        }
    }

    private void b(String str, int i) {
        LogUtil.a("FamousTeacherLayout", "goToDetail");
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            marketRouterApi.router(str);
        }
        d(2, i);
    }

    private void a(String str) {
        LogUtil.a("FamousTeacherLayout", "goToMore ");
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            marketRouterApi.router(str);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("event", 1);
        hashMap.put("moreEntryName", this.q.getMoreMenuTitle());
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.MARKETING_FAMOUS_TEACHER.value(), hashMap, 0);
    }

    public void b(int i, ResourceBriefInfo resourceBriefInfo, FamousTeacherSeriesRecTemplate famousTeacherSeriesRecTemplate) {
        if (famousTeacherSeriesRecTemplate == null) {
            LogUtil.h("FamousTeacherLayout", "template = null");
            return;
        }
        this.f = i;
        this.q = famousTeacherSeriesRecTemplate;
        this.i = resourceBriefInfo;
        LogUtil.a("FamousTeacherLayout", "template:", famousTeacherSeriesRecTemplate.toString());
        e();
        e(famousTeacherSeriesRecTemplate.getGridContents().get(0), 0);
        if (this.r == null) {
            this.r = new a(this);
        }
        nsy.cMb_(this.e, this.r);
    }

    private void e() {
        View inflate = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.layout_famous_teacher, this);
        this.e = (LinearLayout) inflate.findViewById(R.id.layout_famous_view);
        this.c = (HealthSubHeader) inflate.findViewById(R.id.marketing_teacher_sub_header);
        this.n = (HealthRecycleView) inflate.findViewById(R.id.marketing_teacher_recycler_view);
        this.b = (HealthRecycleView) inflate.findViewById(R.id.marketing_class_recycler_view);
        this.m = (HealthCardView) inflate.findViewById(R.id.teacher_item);
        this.o = (HealthTextView) inflate.findViewById(R.id.tv_teacher_name);
        this.l = (HealthTextView) inflate.findViewById(R.id.tv_teacher_describe);
        this.k = (ImageView) inflate.findViewById(R.id.img_teacher_background);
        this.d = new FamousTeacherAvatarAdapter(BaseApplication.getContext(), new ArrayList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager.setOrientation(0);
        this.n.setLayoutManager(linearLayoutManager);
        this.n.setAdapter(this.d);
        this.f2869a = new FamousTeacherClassAdapter(BaseApplication.getContext(), this.f, this.i, new ArrayList(), this.q);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(BaseApplication.getContext());
        linearLayoutManager2.setOrientation(0);
        this.b.setLayoutManager(linearLayoutManager2);
        this.b.setAdapter(this.f2869a);
        this.b.setFocusableInTouchMode(false);
        this.b.requestFocus();
        this.b.setItemAnimator(null);
        this.b.setNestedScrollingEnabled(false);
        this.d.d(this.q.getGridContents());
        setSubHeaderUi(this.q);
        e(this.n);
        if (!eie.e(this.f)) {
            inflate.setPadding(((Integer) this.h.first).intValue(), 0, ((Integer) this.h.second).intValue(), 0);
        }
        this.p = new b(this);
        this.d.e(new FamousTeacherAvatarAdapter.onItemClickListener() { // from class: ejs
            @Override // com.huawei.health.marketing.views.FamousTeacherAvatarAdapter.onItemClickListener
            public final void onItemClick(FamousTeacherAvatarAdapter.ViewHolder viewHolder, SingleFamousTeacherStandardContent singleFamousTeacherStandardContent, int i, int i2) {
                FamousTeacherLayout.this.a(viewHolder, singleFamousTeacherStandardContent, i, i2);
            }
        });
    }

    public /* synthetic */ void a(FamousTeacherAvatarAdapter.ViewHolder viewHolder, SingleFamousTeacherStandardContent singleFamousTeacherStandardContent, int i, int i2) {
        this.d.notifyItemChanged(i);
        this.d.notifyItemChanged(i2);
        e(singleFamousTeacherStandardContent, i2);
        d(1, i2);
        nsy.cMa_(this.n, this.p);
    }

    private void setSubHeaderUi(FamousTeacherSeriesRecTemplate famousTeacherSeriesRecTemplate) {
        if (famousTeacherSeriesRecTemplate == null) {
            return;
        }
        boolean isNameVisibility = famousTeacherSeriesRecTemplate.isNameVisibility();
        String name = famousTeacherSeriesRecTemplate.getName();
        eiv.e(isNameVisibility && !TextUtils.isEmpty(name), this.c);
        this.c.setHeadTitleText(name);
        String linkValue = famousTeacherSeriesRecTemplate.getLinkValue();
        this.g = linkValue;
        if (!TextUtils.isEmpty(linkValue)) {
            this.c.setMoreLayoutVisibility(0);
            if (!TextUtils.isEmpty(famousTeacherSeriesRecTemplate.getMoreMenuTitle())) {
                this.c.setMoreTextMaxLines(2);
                this.c.setMoreTextAlignment(6);
                this.c.setMoreText(famousTeacherSeriesRecTemplate.getMoreMenuTitle());
                this.c.setMoreTextVisibility(0);
            } else {
                this.c.setMoreTextVisibility(4);
            }
            this.c.setMoreViewClickListener(this);
        } else {
            this.c.setMoreLayoutVisibility(8);
        }
        this.c.setSubHeaderBackgroundColor(0);
    }

    private void e(HealthRecycleView healthRecycleView) {
        healthRecycleView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.huawei.health.marketing.views.FamousTeacherLayout.1
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
                int dimensionPixelSize = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362922_res_0x7f0a046a);
                int dimensionPixelSize2 = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362922_res_0x7f0a046a);
                int dimensionPixelSize3 = BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362860_res_0x7f0a042c);
                int size = FamousTeacherLayout.this.q.getGridContents().size() - 1;
                if (childAdapterPosition == size) {
                    rect.right = ((Integer) BaseActivity.getSafeRegionWidth().second).intValue() + dimensionPixelSize2;
                    rect.left = dimensionPixelSize3 / 2;
                }
                if (childAdapterPosition == 0) {
                    rect.left = ((Integer) BaseActivity.getSafeRegionWidth().first).intValue() + dimensionPixelSize;
                    rect.right = dimensionPixelSize3 / 2;
                }
                if (childAdapterPosition <= 0 || childAdapterPosition >= size) {
                    return;
                }
                int i = dimensionPixelSize3 / 2;
                rect.left = i;
                rect.right = i;
            }
        });
    }

    private void e(SingleFamousTeacherStandardContent singleFamousTeacherStandardContent, int i) {
        this.t = singleFamousTeacherStandardContent;
        a(singleFamousTeacherStandardContent);
        eiv.d(this.o, singleFamousTeacherStandardContent.getTheme(), singleFamousTeacherStandardContent.isThemeVisibility());
        eiv.d(this.l, singleFamousTeacherStandardContent.getDescription(), singleFamousTeacherStandardContent.isDescriptionVisibility());
        if (TextUtils.equals(this.i.getCategory(), SingleDailyMomentContent.COURSE_TYPE)) {
            b(singleFamousTeacherStandardContent.getSubContents());
        } else {
            this.f2869a.c(singleFamousTeacherStandardContent);
        }
        this.j = System.currentTimeMillis();
        a(singleFamousTeacherStandardContent, i);
    }

    private void a(SingleFamousTeacherStandardContent singleFamousTeacherStandardContent, final int i) {
        final String linkValue = singleFamousTeacherStandardContent.getLinkValue();
        this.m.setOnClickListener(new View.OnClickListener() { // from class: ejz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FamousTeacherLayout.this.apj_(linkValue, i, view);
            }
        });
    }

    public /* synthetic */ void apj_(final String str, final int i, View view) {
        if (TextUtils.isEmpty(str) || nsn.cLk_(view)) {
            LogUtil.h("FamousTeacherLayout", "click too fast or url is empty");
            ViewClickInstrumentation.clickOnView(view);
        } else if (!eie.b(str)) {
            b(str, i);
            ViewClickInstrumentation.clickOnView(view);
        } else {
            LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: ejy
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    FamousTeacherLayout.this.c(str, i, i2, obj);
                }
            }, AnalyticsValue.MARKETING_RESOURCE.value());
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void c(String str, int i, int i2, Object obj) {
        if (i2 == 0) {
            b(str, i);
        } else {
            LogUtil.h("FamousTeacherLayout", "onClick errorCode = ", Integer.valueOf(i2));
        }
    }

    private void b(List<SingleFamousTeacherContent> list) {
        if (koq.b(list)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (SingleFamousTeacherContent singleFamousTeacherContent : list) {
            if (singleFamousTeacherContent != null && !TextUtils.isEmpty(singleFamousTeacherContent.getDynamicDataId())) {
                arrayList.add(new ffl.d(singleFamousTeacherContent.getDynamicDataId()).b());
            }
        }
        e(arrayList, false, new c(this, list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        synchronized (this) {
            new d(this).sendEmptyMessage(1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void e(final List<ffl> list, final boolean z, final UiCallback<List<FitWorkout>> uiCallback) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ejw
                @Override // java.lang.Runnable
                public final void run() {
                    FamousTeacherLayout.this.e(list, z, uiCallback);
                }
            });
            return;
        }
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("FamousTeacherLayout", "getCourseByIds, courseApi is null.");
            uiCallback.onFailure(ResultUtil.ResultCode.PARAMETER_INVALID, "CourseApi is null");
        } else {
            courseApi.getCourseByIds(list, z, uiCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<SingleFamousTeacherContent> list, List<FitWorkout> list2) {
        for (SingleFamousTeacherContent singleFamousTeacherContent : list) {
            if (singleFamousTeacherContent != null) {
                String dynamicDataId = singleFamousTeacherContent.getDynamicDataId();
                if (!TextUtils.isEmpty(dynamicDataId)) {
                    Iterator<FitWorkout> it = list2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        FitWorkout next = it.next();
                        if (next != null && TextUtils.equals(dynamicDataId, next.acquireId())) {
                            LogUtil.a("FamousTeacherLayout", "fitWorkout:", next.toString());
                            singleFamousTeacherContent.setDescription(ehy.d(next.acquireDifficulty()) + "·" + ffy.d(BaseApplication.getContext(), R$string.IDS_min_unit_no_space, gic.e(next.acquireDuration())));
                            singleFamousTeacherContent.setDescriptionVisibility(true);
                            singleFamousTeacherContent.setTheme(next.acquireName());
                            singleFamousTeacherContent.setThemeVisibility(true);
                            LogUtil.a("FamousTeacherLayout", "SingleFamousTeacherContent:", singleFamousTeacherContent.toString());
                            break;
                        }
                    }
                }
            }
        }
    }

    private void a(SingleFamousTeacherStandardContent singleFamousTeacherStandardContent) {
        String picture = singleFamousTeacherStandardContent.getPicture();
        String tahitiPicture = singleFamousTeacherStandardContent.getTahitiPicture();
        if (nsn.ag(BaseApplication.getActivity()) && !TextUtils.isEmpty(tahitiPicture)) {
            picture = tahitiPicture;
        }
        nrf.cIS_(this.k, picture, nrf.c, 3, 0);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        LogUtil.a("FamousTeacherLayout", "onConfigurationChanged: ", Integer.valueOf(configuration.orientation));
        a(this.t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, int i2) {
        if (this.i == null) {
            LogUtil.a("FamousTeacherLayout", "resourceBriefInfo is null. return.");
            return;
        }
        String str = this.i.getResourceName() + " " + this.t.getTheme();
        LogUtil.a("FamousTeacherLayout", "set click event Result. PositionId: ", Integer.valueOf(this.f), "; resourceName: ", str);
        HashMap hashMap = new HashMap();
        hashMap.put("resourceName", str);
        hashMap.put("itemCardName", this.t.getTabName());
        hashMap.put("itemId", "");
        hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - this.j));
        hashMap.put("pullOrder", 0);
        FamousTeacherSeriesRecTemplate famousTeacherSeriesRecTemplate = this.q;
        if (famousTeacherSeriesRecTemplate != null) {
            hashMap.put("moreEntryName", famousTeacherSeriesRecTemplate.getMoreMenuTitle());
        }
        MarketingBiUtils.d(i, this.f, this.i, hashMap);
    }

    static class c extends UiCallback<List<FitWorkout>> {
        private List<SingleFamousTeacherContent> b;
        private WeakReference<FamousTeacherLayout> e;

        c(FamousTeacherLayout famousTeacherLayout, List<SingleFamousTeacherContent> list) {
            this.e = new WeakReference<>(famousTeacherLayout);
            this.b = list;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            FamousTeacherLayout famousTeacherLayout = this.e.get();
            if (famousTeacherLayout == null) {
                LogUtil.h("FamousTeacherLayout", "getCourseByIds onFailure layout is null");
            } else {
                LogUtil.h("FamousTeacherLayout", "getCourseByIds onFailure, ", str);
                famousTeacherLayout.a();
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<FitWorkout> list) {
            FamousTeacherLayout famousTeacherLayout = this.e.get();
            if (famousTeacherLayout == null) {
                LogUtil.h("FamousTeacherLayout", "getCourseByIds onSuccess layout is null");
                return;
            }
            LogUtil.a("FamousTeacherLayout", "getCourseByIds onSuccess, ");
            if (koq.c(list)) {
                famousTeacherLayout.d(this.b, list);
            }
            famousTeacherLayout.a();
        }
    }

    static class a implements ViewTreeObserver.OnScrollChangedListener {
        private final WeakReference<FamousTeacherLayout> b;

        public a(FamousTeacherLayout famousTeacherLayout) {
            this.b = new WeakReference<>(famousTeacherLayout);
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            FamousTeacherLayout famousTeacherLayout = this.b.get();
            if (famousTeacherLayout != null) {
                if (MarketingBiUtils.alP_(famousTeacherLayout.e)) {
                    famousTeacherLayout.d(1, 0);
                    nsy.cMg_(famousTeacherLayout.e, this);
                    return;
                }
                return;
            }
            LogUtil.h("FamousTeacherLayout", "onScrollChanged layout is null");
        }
    }

    static class b implements ViewTreeObserver.OnGlobalLayoutListener {
        private final WeakReference<FamousTeacherLayout> d;

        b(FamousTeacherLayout famousTeacherLayout) {
            this.d = new WeakReference<>(famousTeacherLayout);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            FamousTeacherLayout famousTeacherLayout = this.d.get();
            if (famousTeacherLayout != null) {
                FamousTeacherAvatarAdapter famousTeacherAvatarAdapter = famousTeacherLayout.d;
                if (famousTeacherAvatarAdapter == null) {
                    ReleaseLogUtil.a("FamousTeacherLayout", "TeacherRecycleViewOnGlobalLayoutListener onGlobalLayout adapter is null");
                    return;
                }
                FamousTeacherAvatarAdapter.ViewHolder d = famousTeacherAvatarAdapter.d();
                if (d == null) {
                    ReleaseLogUtil.a("FamousTeacherLayout", "TeacherRecycleViewOnGlobalLayoutListener onGlobalLayout holder is null");
                    return;
                }
                LogUtil.a("FamousTeacherLayout", "TeacherRecycleViewOnGlobalLayoutListener onGlobalLayout holder ", d);
                famousTeacherLayout.b();
                jcf.bEv_(d.itemView, 8);
                return;
            }
            ReleaseLogUtil.a("FamousTeacherLayout", "TeacherRecycleViewOnGlobalLayoutListener onGlobalLayout layout is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        b bVar = this.p;
        if (bVar == null) {
            ReleaseLogUtil.a("FamousTeacherLayout", "removeOnGlobalLayoutListener mTeacherRecycleViewOnGlobalLayoutListener is null");
        } else {
            nsy.cMf_(this.n, bVar);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ReleaseLogUtil.b("FamousTeacherLayout", "onDetachedFromWindow");
        b();
        a aVar = this.r;
        if (aVar != null) {
            nsy.cMg_(this.e, aVar);
        }
    }
}
