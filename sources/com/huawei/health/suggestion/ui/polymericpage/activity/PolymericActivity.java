package com.huawei.health.suggestion.ui.polymericpage.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.google.gson.reflect.TypeToken;
import com.huawei.basefitnessadvice.model.FilterCondition;
import com.huawei.basefitnessadvice.model.Navigation;
import com.huawei.basefitnessadvice.model.NavigationFilter;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.InputBoxTemplate;
import com.huawei.health.suggestion.ui.BaseActivity;
import com.huawei.health.suggestion.ui.BaseStateActivity;
import com.huawei.health.suggestion.ui.polymericpage.activity.PolymericActivity;
import com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericCategoryAdapter;
import com.huawei.health.suggestion.ui.polymericpage.model.PolymericCustomer;
import com.huawei.health.suggestion.ui.polymericpage.model.PolymericDataViewModel;
import com.huawei.health.suggestion.ui.polymericpage.view.PolymericDataFragment;
import com.huawei.health.suggestion.ui.polymericpage.view.PolymericFilterRecyclerHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.dpf;
import defpackage.eil;
import defpackage.gge;
import defpackage.ggr;
import defpackage.jcf;
import defpackage.koq;
import defpackage.moj;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class PolymericActivity extends BaseStateActivity {
    private static final String TAG = "Suggestion_PolymericActivity";
    private LinearLayout mAllCourseFilterBar;
    private FrameLayout mContentLayout;
    protected Context mContext;
    private int mCurNavigationId;
    private int mCurrentResourceId;
    private PolymericDataViewModel mDataViewModel;
    private HealthButton mGoToSetNetwork;
    private Handler mHandler;
    private a mInnerOnGlobalLayoutListener;
    private PolymericCategoryAdapter mNavigationAdapter;
    private HealthRecycleView mNavigationListView;
    private View mNoNetWork;
    protected String mPageType;
    protected PolymericCustomer mPolymericCustomer;
    private PolymericDataFragment mPolymericDataFragment;
    private PolymericFilterRecyclerHelper mPolymericFilterRecyclerHelper;
    private LinearLayout mRecommendView;
    private long mStartTime;
    private HealthSubHeader mSubNavigationHeader;
    public InputBoxTemplate mInputBoxTemplate = new InputBoxTemplate();
    private Observer<NavigationFilter> mNavigationObserver = new Observer() { // from class: com.huawei.health.suggestion.ui.polymericpage.activity.PolymericActivity$$ExternalSyntheticLambda1
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            PolymericActivity.this.notifyNavigationData((NavigationFilter) obj);
        }
    };

    public abstract PolymericCustomer createPolymericCustomer(Intent intent);

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a(TAG, "onCreate.");
        nsn.cLf_(this, bundle);
        this.mContext = this;
        this.mPolymericCustomer = createPolymericCustomer(getIntent());
        this.mStartTime = System.currentTimeMillis();
        d dVar = new d(this);
        this.mHandler = dVar;
        dpf.Yn_(dVar, 4036);
        super.onCreate(bundle);
        getWindow().setFlags(16777216, 16777216);
        initViewModelAndGetNavData();
        LogUtil.a(TAG, "onCreate finish.");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        LogUtil.a(TAG, "initViewTahiti");
        getWindow().setFlags(16777216, 16777216);
        this.mStartTime = System.currentTimeMillis();
        this.mContentLayout.invalidate();
        PolymericDataViewModel polymericDataViewModel = this.mDataViewModel;
        Navigation c = polymericDataViewModel.c(polymericDataViewModel.c());
        if (!this.mPolymericCustomer.isSupportFilter() || c == null) {
            return;
        }
        this.mPolymericFilterRecyclerHelper.a(c.getFilterConditions(), c.getName());
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        setContentView(R.layout.sug_activity_sport_all_course_activity);
        this.mContext = this;
        Intent intent = getIntent();
        if (intent != null) {
            this.mPolymericCustomer.getPolymericIntentData(intent);
        }
        this.mContentLayout = (FrameLayout) findViewById(R.id.sug_march_frame);
        this.mLoadingView = findViewById(R.id.sug_loading);
        this.mNoNetWork = findViewById(R.id.sug_action_no_data_set_network);
        this.mGoToSetNetwork = (HealthButton) findViewById(R.id.btn_no_net_work);
        BaseActivity.cancelLayoutById(this.mContentLayout);
        BaseActivity.setViewSafeRegion(false, this.mContentLayout);
        cancelAdaptRingRegion();
        initNormalModeLayout();
        this.mPolymericDataFragment = this.mPolymericCustomer.createPolymericDataFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.sug_course_list_content, this.mPolymericDataFragment).commitAllowingStateLoss();
        setNoNetworkState(true);
    }

    protected void initNormalModeLayout() {
        LogUtil.a(TAG, "initNormalModeLayout.");
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.sug_course_category_list);
        this.mNavigationListView = healthRecycleView;
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this.mContext));
        if (this.mNavigationListView.getItemAnimator() instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) this.mNavigationListView.getItemAnimator()).setSupportsChangeAnimations(false);
        }
        PolymericCategoryAdapter polymericCategoryAdapter = new PolymericCategoryAdapter(Collections.EMPTY_LIST);
        this.mNavigationAdapter = polymericCategoryAdapter;
        this.mNavigationListView.setAdapter(polymericCategoryAdapter);
        categoryListViewSetScrollListener(this.mNavigationListView);
        this.mNavigationAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() { // from class: gbt
            @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
            public final void onItemClicked(RecyclerHolder recyclerHolder, int i, Object obj) {
                PolymericActivity.this.m511xe7bd212d(recyclerHolder, i, obj);
            }
        });
        a aVar = new a(this);
        this.mInnerOnGlobalLayoutListener = aVar;
        nsy.cMa_(this.mNavigationListView, aVar);
    }

    /* renamed from: lambda$initNormalModeLayout$0$com-huawei-health-suggestion-ui-polymericpage-activity-PolymericActivity, reason: not valid java name */
    public /* synthetic */ void m511xe7bd212d(RecyclerHolder recyclerHolder, int i, Object obj) {
        if (i == this.mNavigationAdapter.d() || !(obj instanceof Navigation)) {
            LogUtil.h(TAG, "setOnItemClickListener, position not change or itemData not instanceof ClassifyInfo.");
        } else {
            this.mNavigationAdapter.a(i);
            onNavigationClick((Navigation) obj);
        }
    }

    private void categoryListViewSetScrollListener(HealthRecycleView healthRecycleView) {
        healthRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.health.suggestion.ui.polymericpage.activity.PolymericActivity.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (recyclerView.canScrollVertically(1)) {
                    return;
                }
                PolymericActivity.this.slideLabelBottom();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void slideLabelBottom() {
        PolymericDataViewModel polymericDataViewModel = this.mDataViewModel;
        Navigation c = polymericDataViewModel.c(polymericDataViewModel.c());
        String name = c != null ? c.getName() : "";
        if ("HEALTH_COURSE".equals(this.mPageType)) {
            ggr.d(name);
        } else {
            ggr.d(gge.c(this.mPageType));
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
        initTitleBarSearchController();
        initNormalViewController();
    }

    public void initNormalViewController() {
        initTopView();
        if (this.mTitleBar == null) {
            LogUtil.h(TAG, "initNormalViewController, failed with null mTitleBar.");
            return;
        }
        this.mTitleBar.setTitleText(this.mPolymericCustomer.getTitleText(this));
        this.mTitleBar.setDoubleClickEnable(true);
        this.mTitleBar.setBackToTopListener(new CustomTitleBar.BackToTopListener() { // from class: gbs
            @Override // com.huawei.ui.commonui.titlebar.CustomTitleBar.BackToTopListener
            public final void backToTop() {
                PolymericActivity.this.onBackToTop();
            }
        });
    }

    protected void initTitleBarSearchController() {
        if (this.mTitleBar == null) {
            LogUtil.h(TAG, "initTitleBarSearchController, failed with null mTitleBar.");
            return;
        }
        Drawable rightButtonDrawable = getRightButtonDrawable();
        if (rightButtonDrawable == null) {
            LogUtil.a(TAG, "initTitleBarSearchController, rightButtonDrawable is null.");
            this.mTitleBar.setRightButtonVisibility(8);
        } else {
            this.mTitleBar.setRightButtonDrawable(rightButtonDrawable, nsf.h(R.string._2130847322_res_0x7f02265a));
            this.mTitleBar.setRightButtonVisibility(0);
            this.mTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: gbp
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PolymericActivity.this.m512x9335f141(view);
                }
            });
        }
    }

    /* renamed from: lambda$initTitleBarSearchController$1$com-huawei-health-suggestion-ui-polymericpage-activity-PolymericActivity, reason: not valid java name */
    public /* synthetic */ void m512x9335f141(View view) {
        rightButtonClick();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a(TAG, "onActivityResult.", Integer.valueOf(i), " resultCode:", Integer.valueOf(i2));
        if (i == 123) {
            if (i2 != -1 || intent == null) {
                LogUtil.h(TAG, "onActivityResult() resultCode is no OK or data is null");
                return;
            }
            this.mPolymericDataFragment.refreshCurrentPage(0);
            String stringExtra = intent.getStringExtra("MORE_FILTER_SELECTED_DATA");
            if (TextUtils.isEmpty(stringExtra)) {
                LogUtil.a(TAG, "selectedString empty.");
                return;
            }
            LogUtil.a(TAG, "selectedString.", stringExtra);
            Map<Long, List<Long>> map = (Map) moj.b(stringExtra, new TypeToken<Map<Long, List<Long>>>() { // from class: com.huawei.health.suggestion.ui.polymericpage.activity.PolymericActivity.1
            }.getType());
            if (this.mPolymericCustomer.isSupportFilter()) {
                this.mPolymericFilterRecyclerHelper.c(map);
            }
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a(TAG, "onDestroy mInnerOnGlobalLayoutListener ", this.mInnerOnGlobalLayoutListener);
        a aVar = this.mInnerOnGlobalLayoutListener;
        if (aVar != null) {
            nsy.cMf_(this.mNavigationListView, aVar);
        }
        this.mDataViewModel.b(this.mNavigationObserver);
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra("entrance");
            if (TextUtils.isEmpty(stringExtra)) {
                stringExtra = "otherModel";
            }
            HashMap hashMap = new HashMap(2);
            hashMap.put("entrance", stringExtra);
            hashMap.put("stay_time", Long.valueOf(System.currentTimeMillis() - this.mStartTime));
            gge.e("1130016", hashMap);
        }
    }

    public int getIndexByNavigation(Navigation navigation, List<Navigation> list) {
        if (koq.b(list)) {
            return 0;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getValue() == navigation.getValue()) {
                return i;
            }
        }
        return 0;
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initData() {
        LogUtil.a(TAG, "initData");
    }

    private void initViewModelAndGetNavData() {
        if (this.mDataViewModel == null) {
            this.mDataViewModel = (PolymericDataViewModel) new ViewModelProvider(this).get(PolymericDataViewModel.class);
        }
        this.mDataViewModel.a(this.mPageType, this.mPolymericCustomer.acquireFragmentParam());
        this.mDataViewModel.c(this, this.mNavigationObserver);
        showLoading();
        this.mDataViewModel.b();
    }

    private void initTopView() {
        if (this.mRecommendView == null) {
            this.mRecommendView = (LinearLayout) ((ViewStub) findViewById(R.id.sug_course_recommend_bar)).inflate().findViewById(R.id.sug_all_course_recommend_more_layout);
        }
        if (this.mPolymericFilterRecyclerHelper == null) {
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.sug_view_container);
            relativeLayout.removeAllViews();
            relativeLayout.addView(LayoutInflater.from(this.mContext).inflate(R.layout.include_sport_all_course_recyclerview, (ViewGroup) null));
            this.mAllCourseFilterBar = (LinearLayout) relativeLayout.findViewById(R.id.sug_course_screen_layout);
            HealthSubHeader healthSubHeader = (HealthSubHeader) relativeLayout.findViewById(R.id.sug_category_name);
            this.mSubNavigationHeader = healthSubHeader;
            healthSubHeader.setMoreTextVisibility(4);
            this.mSubNavigationHeader.setRightArrayVisibility(4);
            if (this.mPolymericCustomer.isSupportFilter()) {
                PolymericFilterRecyclerHelper polymericFilterRecyclerHelper = new PolymericFilterRecyclerHelper(this.mContext, this.mAllCourseFilterBar);
                this.mPolymericFilterRecyclerHelper = polymericFilterRecyclerHelper;
                polymericFilterRecyclerHelper.c(new PolymericFilterRecyclerHelper.OnCourseFilterListener() { // from class: gbk
                    @Override // com.huawei.health.suggestion.ui.polymericpage.view.PolymericFilterRecyclerHelper.OnCourseFilterListener
                    public final void onToFilterCallBack(List list) {
                        PolymericActivity.this.m513xe735cbbf(list);
                    }
                });
                return;
            }
            ((HealthRecycleView) this.mAllCourseFilterBar.findViewById(R.id.recycleview_filter)).setVisibility(8);
        }
    }

    /* renamed from: lambda$initTopView$2$com-huawei-health-suggestion-ui-polymericpage-activity-PolymericActivity, reason: not valid java name */
    public /* synthetic */ void m513xe735cbbf(List list) {
        LogUtil.a(TAG, "OnFilter.", moj.e(list));
        this.mPolymericDataFragment.getPolymericData(this.mDataViewModel.c(this.mCurNavigationId), list, true);
    }

    public PolymericCustomer getPolymericCustomer() {
        return this.mPolymericCustomer;
    }

    protected void getResourceView(Navigation navigation) {
        if (navigation == null) {
            LogUtil.h(TAG, "getResourceView() mPrimaryClassify is null or out of bounds.");
            return;
        }
        if (this.mRecommendView == null) {
            LogUtil.h(TAG, "getResourceView() classifyInfo or mRecommendView is null");
            return;
        }
        int positionId = navigation.getPositionId();
        LogUtil.a(TAG, "notifyFilterData, getResourceView = ", Integer.valueOf(positionId));
        if (positionId == 0) {
            LogUtil.h(TAG, "getResourceView() resourceId == 0");
            this.mRecommendView.setVisibility(8);
            return;
        }
        if (positionId != this.mCurrentResourceId) {
            this.mRecommendView.removeAllViews();
            eil.alQ_(this.mContext, this.mRecommendView, positionId);
            this.mCurrentResourceId = positionId;
        }
        this.mRecommendView.setVisibility(0);
    }

    public void onNavigationClick(Navigation navigation) {
        this.mCurNavigationId = navigation.getValue();
        LogUtil.a(TAG, "onNavigationClick.", moj.e(navigation));
        HealthSubHeader healthSubHeader = this.mSubNavigationHeader;
        if (healthSubHeader != null) {
            healthSubHeader.setHeadTitleText(navigation.getName());
        }
        ArrayList arrayList = new ArrayList();
        if (this.mPolymericCustomer.isSupportFilter()) {
            this.mPolymericFilterRecyclerHelper.a(navigation.getFilterConditions(), navigation.getName());
        }
        this.mPolymericDataFragment.getPolymericData(navigation, arrayList, true);
        ggr.b(navigation.getName());
        getResourceView(navigation);
    }

    public void rightButtonClick() {
        LogUtil.a(TAG, "rightButtonClick.");
        gge.b("1", 7);
        if (TextUtils.equals(this.mPageType, "FITNESS_COURSE")) {
            this.mInputBoxTemplate.setLessonSubCategory("fitnesslesson");
        } else if (!TextUtils.equals(this.mPageType, "RUNNING_COURSE")) {
            LogUtil.b(TAG, "rightButtonClick mPageType: ", this.mPageType);
        } else {
            this.mInputBoxTemplate.setLessonSubCategory("runninglesson");
        }
        dpf.a(this.mContext, this.mInputBoxTemplate);
    }

    /* loaded from: classes4.dex */
    static class a implements ViewTreeObserver.OnGlobalLayoutListener {
        private final WeakReference<PolymericActivity> b;

        a(PolymericActivity polymericActivity) {
            this.b = new WeakReference<>(polymericActivity);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            PolymericActivity polymericActivity = this.b.get();
            if (polymericActivity != null && !polymericActivity.isFinishing() && !polymericActivity.isDestroyed()) {
                PolymericCategoryAdapter polymericCategoryAdapter = polymericActivity.mNavigationAdapter;
                if (polymericCategoryAdapter == null) {
                    ReleaseLogUtil.a(PolymericActivity.TAG, "InnerOnGlobalLayoutListener onGlobalLayout adapter is null");
                    return;
                }
                RecyclerHolder a2 = polymericCategoryAdapter.a();
                if (a2 == null) {
                    ReleaseLogUtil.a(PolymericActivity.TAG, "InnerOnGlobalLayoutListener onGlobalLayout holder is null");
                    return;
                } else {
                    jcf.bEv_(a2.itemView, 8);
                    return;
                }
            }
            ReleaseLogUtil.a(PolymericActivity.TAG, "InnerOnGlobalLayoutListener onGlobalLayout activity ", polymericActivity);
        }
    }

    /* loaded from: classes4.dex */
    static class d extends BaseHandler<PolymericActivity> {
        d(PolymericActivity polymericActivity) {
            super(Looper.getMainLooper(), polymericActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aJP_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(PolymericActivity polymericActivity, Message message) {
            if (message == null) {
                LogUtil.b(PolymericActivity.TAG, "msg is null");
                return;
            }
            LogUtil.a(PolymericActivity.TAG, "msg.what:", Integer.valueOf(message.what));
            if (message.what != 42) {
                return;
            }
            Object obj = message.obj;
            if (obj instanceof InputBoxTemplate) {
                polymericActivity.mInputBoxTemplate = (InputBoxTemplate) obj;
            }
        }
    }

    protected Drawable getRightButtonDrawable() {
        if (LanguageUtil.bc(this.mContext)) {
            return nrz.cKn_(this.mContext, R.drawable._2131431370_res_0x7f0b0fca);
        }
        return ContextCompat.getDrawable(this.mContext, R.drawable._2131431370_res_0x7f0b0fca);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBackToTop() {
        PolymericDataFragment polymericDataFragment = this.mPolymericDataFragment;
        if (polymericDataFragment == null) {
            LogUtil.h(TAG, "onBackToTop, mRecordsFragment null.");
        } else {
            polymericDataFragment.smoothScrollToPosition(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyNavigationData(NavigationFilter navigationFilter) {
        LogUtil.a(TAG, "notifyNavigationData");
        finishLoading();
        if (navigationFilter != null && !koq.b(navigationFilter.getLeftNavigations())) {
            LogUtil.a(TAG, "notifyNavigationData size:", Integer.valueOf(navigationFilter.getLeftNavigations().size()));
            if (!TextUtils.isEmpty(navigationFilter.getPageName())) {
                this.mTitleBar.setTitleText(navigationFilter.getPageName());
            }
            this.mNavigationAdapter.refreshDataChange(navigationFilter.getLeftNavigations());
            Navigation initActiveNavigation = this.mPolymericCustomer.initActiveNavigation(navigationFilter.getLeftNavigations());
            if (initActiveNavigation != null) {
                LogUtil.a(TAG, "selectNavigation.", moj.e(initActiveNavigation));
                this.mCurNavigationId = initActiveNavigation.getValue();
                HealthSubHeader healthSubHeader = this.mSubNavigationHeader;
                if (healthSubHeader != null) {
                    healthSubHeader.setHeadTitleText(initActiveNavigation.getName());
                }
                this.mNavigationAdapter.a(getIndexByNavigation(initActiveNavigation, navigationFilter.getLeftNavigations()));
                List<FilterCondition> initFilterConditions = this.mPolymericCustomer.initFilterConditions(initActiveNavigation);
                this.mPolymericDataFragment.getPolymericData(initActiveNavigation, initFilterConditions, true);
                getResourceView(initActiveNavigation);
                if (this.mPolymericCustomer.isSupportFilter()) {
                    this.mPolymericFilterRecyclerHelper.a(initActiveNavigation.getFilterConditions(), initActiveNavigation.getName());
                    this.mPolymericFilterRecyclerHelper.a(initFilterConditions);
                    return;
                }
                return;
            }
            LogUtil.a(TAG, "no select navigation.");
            return;
        }
        LogUtil.b(TAG, "notifyNavigationData size empty.");
        dealNoNavigationData();
    }

    private void dealNoNavigationData() {
        if (!NetworkUtil.i()) {
            setNoNetworkState(false);
            return;
        }
        this.mPolymericDataFragment.getPolymericData(new Navigation.Builder().name(this.mContext.getString(R.string._2130848510_res_0x7f022afe)).categoryId(0).value(0).build(), new ArrayList(), true);
        ((HealthRecycleView) findViewById(R.id.sug_course_category_list)).setVisibility(8);
        ((RelativeLayout) findViewById(R.id.sug_view_container)).setVisibility(8);
    }

    private void setNoNetworkState(boolean z) {
        if (z) {
            this.mNoNetWork.setVisibility(8);
        } else {
            this.mNoNetWork.setVisibility(0);
            this.mGoToSetNetwork.setOnClickListener(new View.OnClickListener() { // from class: gbq
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PolymericActivity.this.m514xe4caf7a2(view);
                }
            });
        }
    }

    /* renamed from: lambda$setNoNetworkState$3$com-huawei-health-suggestion-ui-polymericpage-activity-PolymericActivity, reason: not valid java name */
    public /* synthetic */ void m514xe4caf7a2(View view) {
        CommonUtil.q(this.mContext);
        ViewClickInstrumentation.clickOnView(view);
    }
}
