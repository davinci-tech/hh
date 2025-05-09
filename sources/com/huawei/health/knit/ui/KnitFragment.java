package com.huawei.health.knit.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.core.util.Consumer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.design.pattern.ObserveLabels;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.common.NoAnimationsLinearLayoutManager;
import com.huawei.health.knit.data.MajorProvider;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.model.KnitFragmentModel;
import com.huawei.health.knit.section.listener.IPageResTrigger;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.knit.section.view.BarChartCustomSection;
import com.huawei.health.knit.section.view.BaseSection;
import com.huawei.health.knit.section.view.CollapsibleSectionGroup;
import com.huawei.health.knit.section.view.SectionExpandReport;
import com.huawei.health.knit.section.view.SectionMapMaskView;
import com.huawei.health.knit.section.view.SectionScoring;
import com.huawei.health.knit.section.view.SectionShuteyeShare;
import com.huawei.health.knit.section.view.SectionTabSwitch;
import com.huawei.health.marketing.utils.ColumnLayoutItemDecoration;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.scrollview.HealthNestedScrollView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.scrollview.ScrollViewListener;
import com.huawei.ui.commonui.utils.ScrollUtil;
import defpackage.efb;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsw;
import defpackage.nsy;
import defpackage.ntd;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.utils.StringUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes3.dex */
public class KnitFragment extends BaseFragment {
    private static final int CACHED_VIEWS_SIZE = 9;
    private static final long DEFAULT_LOAD_TIME_OUT_DELAY = 1500;
    private static final String FRAGMENT_BUNDLE_KEY = "isHomeFragment";
    public static final String HEALTH_DETAIL_CONFIG_NAME = "HealthDetailSectionsConfig.json";
    public static final String KEY_BOOT_DRAWING_SECTIONS_COUNT = "bootDrawingSectionsCount";
    public static final String KEY_EXTRA = "extra";
    public static final String KEY_EXTRA_WRAP_CONTENT = "FRAGMENT_WRAP_CONTEXT";
    public static final String KEY_LOADING_TIME_OUT_DELAY = "loadingTimeOutDelay";
    public static final String KEY_PAGE_DATA = "pageData";
    private static final String KEY_PAGE_RES_TRIGGER = "pageResTrigger";
    private static final int MSG_LOAD_ONLINE_TIMEOUT = 100;
    private static final int NETWORK_CONNECT_ERROR = 2;
    private static final int NETWORK_CONNECT_LESS = 1;
    private static final String TAG = "KnitFragment";
    private Observer loginStatusChangeObserver;
    private KnitSectionRecyclerAdapter mAdapter;
    private ColumnLayoutItemDecoration mBottomPaddingDecoration;
    private FrameLayout mBottomPaddingLayout;
    private View mContentView;
    private c mHandler;
    private HealthScrollView mHealthScrollView;
    private IBaseResponseCallback mHideLoadingCallback;
    private androidx.lifecycle.Observer<SectionBean> mIndividualObserver;
    private boolean mIsForeverObserver;
    private boolean mIsLazyLoad;
    private boolean mIsSetRecyclerViewBgColor;
    private boolean mIsSetUserVisibleLastTime;
    private boolean mIsVisibleToUser;
    private SectionMapMaskView mKnitMapMask;
    private RelativeLayout mLoadingLayout;
    private KnitFragment mMajorKnitFragment;
    private RelativeLayout mMapLayout;
    private HealthTextView mMapLogo;
    private ViewGroup mNoNetworkLayout;
    private OnActivityResultListener mOnActivityResultListener;
    private Handler mOutHandler;
    private androidx.lifecycle.Observer<List<SectionBean>> mOverAllObserver;
    private String mPageData;
    private IPageResTrigger mPageResTrigger;
    private KnitFragmentModel mPageViewModel;
    private PuppetsViewCallBack mPuppetsViewCallBack;
    private RecyclerView.OnScrollListener mRecycleViewListener;
    private HealthRecycleView mRecyclerView;
    private int mRecyclerViewBgColor;
    private ScrollViewListener mScrollViewListener;
    private HealthScrollView.ScrollChangeToBoundaryListener mScrollViewTopBoundaryListener;
    private boolean mFirstTimeVisible = true;
    private boolean mIsAllowResumeRefresh = true;
    private boolean mIsBottomPadding = true;
    private boolean mIsLogin = false;
    private boolean mIsNeedLoadDataOnResume = false;
    private boolean mIsOfflineOverAllChanged = true;
    private boolean mIsPageResTriggerEnabled = true;
    private boolean mIsHideLoadingOnTimeout = true;
    private int mBootDrawingSectionsCount = 0;
    private long mLoadingTimeOutDelay = 1500;
    private boolean mIsShowLoading = true;

    public interface OnActivityResultListener {
        void onActivityResult(int i, int i2, Intent intent);
    }

    public interface PuppetsViewCallBack {
        void setPuppetsView(int[] iArr);
    }

    public static KnitFragment newInstance(String str, IPageResTrigger iPageResTrigger, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString(KEY_PAGE_DATA, str);
        bundle2.putParcelable(KEY_PAGE_RES_TRIGGER, iPageResTrigger);
        bundle2.putBundle(KEY_EXTRA, bundle);
        KnitFragment knitFragment = new KnitFragment();
        knitFragment.setArguments(bundle2);
        return knitFragment;
    }

    public KnitSectionRecyclerAdapter getAdapter() {
        return this.mAdapter;
    }

    public static KnitFragment newInstance(String str, IPageResTrigger iPageResTrigger) {
        return newInstance(str, iPageResTrigger, null);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a(TAG, "onCreate");
        if (this.loginStatusChangeObserver == null) {
            this.loginStatusChangeObserver = new a(this);
        }
        if (this.mOverAllObserver == null) {
            this.mOverAllObserver = new e(this);
        }
        if (this.mIndividualObserver == null) {
            this.mIndividualObserver = new b(this);
        }
        this.mHandler = new c(this);
        ObserverManagerUtil.d(this.loginStatusChangeObserver, "com.huawei.plugin.account.login");
        ObserverManagerUtil.d(this.loginStatusChangeObserver, "com.huawei.plugin.account.logout");
        Bundle arguments = getArguments();
        if (arguments == null) {
            LogUtil.h(TAG, "bundle is null");
            return;
        }
        this.mPageResTrigger = (IPageResTrigger) arguments.getParcelable(KEY_PAGE_RES_TRIGGER);
        this.mPageData = arguments.getString(KEY_PAGE_DATA);
        Bundle bundle2 = arguments.getBundle(KEY_EXTRA);
        if (bundle2 != null) {
            this.mBootDrawingSectionsCount = bundle2.getInt(KEY_BOOT_DRAWING_SECTIONS_COUNT, 0);
            this.mLoadingTimeOutDelay = bundle2.getLong(KEY_LOADING_TIME_OUT_DELAY, 1500L);
        }
        retriggerSetUserVisibleHint();
        initViewModel();
        KnitFragmentModel knitFragmentModel = this.mPageViewModel;
        if (knitFragmentModel != null) {
            knitFragmentModel.e();
        }
        IPageResTrigger iPageResTrigger = this.mPageResTrigger;
        if (iPageResTrigger != null) {
            iPageResTrigger.onCreate();
        }
    }

    public void initViewModel() {
        LogUtil.a(TAG, "initViewModel");
        this.mPageViewModel = (KnitFragmentModel) new ViewModelProvider(this).get(KnitFragmentModel.class);
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "onCreateView");
        if (this.mContentView == null) {
            try {
                AppBundle.b().loadResources(getContext());
                if (nsw.cLT_(R.layout.knit_fragment_layout) != null) {
                    this.mContentView = layoutInflater.inflate((XmlPullParser) nsw.cLT_(R.layout.knit_fragment_layout), viewGroup, false);
                } else {
                    this.mContentView = layoutInflater.inflate(R.layout.knit_fragment_layout, viewGroup, false);
                }
                adjustFragmentHeight();
                if (isSportCategory() && LanguageUtil.bc(getContext())) {
                    this.mContentView.setRotationY(180.0f);
                }
                initView();
            } catch (Resources.NotFoundException unused) {
                LogUtil.b(TAG, "inflate failed!");
                return this.mContentView;
            }
        }
        return this.mContentView;
    }

    private void adjustFragmentHeight() {
        IPageResTrigger iPageResTrigger = this.mPageResTrigger;
        if (iPageResTrigger == null || iPageResTrigger.getExtra() == null || this.mContentView == null || !this.mPageResTrigger.getExtra().getBoolean(KEY_EXTRA_WRAP_CONTENT, false)) {
            return;
        }
        LogUtil.a(TAG, "knitFragment height wrap content");
        ViewGroup.LayoutParams layoutParams = this.mContentView.getLayoutParams();
        layoutParams.height = -2;
        this.mContentView.setLayoutParams(layoutParams);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        LogUtil.a(TAG, "onActivityCreated");
        super.onActivityCreated(bundle);
        if (getActivity() instanceof KnitHealthDetailActivity) {
            ((KnitHealthDetailActivity) getActivity()).updateFragmentFloatingBoxStatus();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        LogUtil.a(TAG, "onDestroyView");
        super.onDestroyView();
        KnitFragmentModel knitFragmentModel = this.mPageViewModel;
        if (knitFragmentModel != null) {
            knitFragmentModel.a(getViewLifecycleOwner(), (androidx.lifecycle.Observer<SectionBean>) null);
        }
    }

    public void initView() {
        LogUtil.a(TAG, "initView");
        this.mLoadingLayout = (RelativeLayout) this.mContentView.findViewById(R.id.loading_layout);
        this.mHealthScrollView = (HealthScrollView) this.mContentView.findViewById(R.id.fitness_scroll_view);
        this.mKnitMapMask = (SectionMapMaskView) this.mContentView.findViewById(R.id.knit_map_mask);
        this.mMapLayout = (RelativeLayout) this.mContentView.findViewById(R.id.map_layout);
        this.mMapLogo = (HealthTextView) this.mContentView.findViewById(R.id.map_logo);
        ViewGroup.LayoutParams layoutParams = this.mKnitMapMask.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = getResources().getDimensionPixelSize(R.dimen._2131363005_res_0x7f0a04bd);
        this.mKnitMapMask.setLayoutParams(layoutParams);
        this.mHealthScrollView.setScrollViewVerticalDirectionEvent(true);
        ((HealthNestedScrollView) this.mContentView.findViewById(R.id.fitness_main_ns)).setNestedScrollingEnabled(false);
        HealthRecycleView healthRecycleView = (HealthRecycleView) this.mContentView.findViewById(R.id.fitness_main_rv);
        this.mRecyclerView = healthRecycleView;
        healthRecycleView.setFocusableInTouchMode(false);
        this.mRecyclerView.requestFocus();
        if (this.mIsSetRecyclerViewBgColor) {
            this.mRecyclerView.setBackgroundColor(this.mRecyclerViewBgColor);
        }
        FrameLayout frameLayout = (FrameLayout) this.mContentView.findViewById(R.id.bottom_padding_layout);
        this.mBottomPaddingLayout = frameLayout;
        nsy.cMA_(frameLayout, this.mIsBottomPadding ? 0 : 8);
        KnitSectionRecyclerAdapter knitSectionRecyclerAdapter = new KnitSectionRecyclerAdapter(this);
        this.mAdapter = knitSectionRecyclerAdapter;
        knitSectionRecyclerAdapter.setHasStableIds(true);
        this.mRecyclerView.setLayoutManager(new NoAnimationsLinearLayoutManager(getContext()));
        this.mRecyclerView.setItemViewCacheSize(9);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setItemAnimator(null);
        this.mRecyclerView.setNestedScrollingEnabled(false);
    }

    private boolean isSportCategory() {
        IPageResTrigger iPageResTrigger = this.mPageResTrigger;
        return iPageResTrigger != null && iPageResTrigger.getPageCategory() == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isMemberIntroCategory() {
        IPageResTrigger iPageResTrigger = this.mPageResTrigger;
        return iPageResTrigger != null && iPageResTrigger.getPageCategory() == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isTemperatureNoDataCategory() {
        IPageResTrigger iPageResTrigger = this.mPageResTrigger;
        return iPageResTrigger != null && iPageResTrigger.getResPosId() == 4035;
    }

    public View getContentView() {
        return this.mContentView;
    }

    private void setRecycleViewScrollListener() {
        Bundle extra = this.mPageResTrigger.getExtra();
        if (extra != null && extra.getBoolean(FRAGMENT_BUNDLE_KEY)) {
            ScrollUtil.cKx_(this.mRecyclerView, getActivity().getWindow().getDecorView(), 3001);
        }
        this.mRecyclerView.setOnScrollListener(this.mRecycleViewListener);
    }

    private void setScrollListener() {
        this.mHealthScrollView.e(true);
        this.mHealthScrollView.setScrollViewVerticalDirectionEvent(true);
        this.mHealthScrollView.d(new HealthScrollView.ScrollChangeToBoundaryListener() { // from class: com.huawei.health.knit.ui.KnitFragment.3
            @Override // com.huawei.ui.commonui.scrollview.HealthScrollView.ScrollChangeToBoundaryListener
            public void onScrollToChangeAlpha(float f) {
            }

            @Override // com.huawei.ui.commonui.scrollview.HealthScrollView.ScrollChangeToBoundaryListener
            public void onScrolledToBottom() {
                if (KnitFragment.this.mScrollViewTopBoundaryListener != null) {
                    KnitFragment.this.mScrollViewTopBoundaryListener.onScrolledToBottom();
                }
                if ((KnitFragment.this.isMemberIntroCategory() || KnitFragment.this.isTemperatureNoDataCategory()) && KnitFragment.this.mBottomPaddingDecoration == null) {
                    LogUtil.a(KnitFragment.TAG, "member intro or temperature category");
                    KnitFragment.this.mBottomPaddingDecoration = new ColumnLayoutItemDecoration(0, 0, 1, new int[]{0, 0, 0, KnitFragment.this.getResources().getDimensionPixelSize(KnitFragment.this.isTemperatureNoDataCategory() ? R.dimen._2131363067_res_0x7f0a04fb : R.dimen._2131363129_res_0x7f0a0539)});
                    KnitFragment.this.mRecyclerView.addItemDecoration(KnitFragment.this.mBottomPaddingDecoration);
                }
            }

            @Override // com.huawei.ui.commonui.scrollview.HealthScrollView.ScrollChangeToBoundaryListener
            public void onScrolledTop() {
                if (KnitFragment.this.mScrollViewTopBoundaryListener != null) {
                    KnitFragment.this.mScrollViewTopBoundaryListener.onScrolledTop();
                }
            }

            @Override // com.huawei.ui.commonui.scrollview.HealthScrollView.ScrollChangeToBoundaryListener
            public void onScrollStateChange(int i) {
                if (KnitFragment.this.mScrollViewTopBoundaryListener != null) {
                    KnitFragment.this.mScrollViewTopBoundaryListener.onScrollStateChange(i);
                }
            }
        });
        if (this.mScrollViewListener != null) {
            this.mHealthScrollView.setIsForceScrollListener(true);
            this.mHealthScrollView.setScrollViewListener(this.mScrollViewListener);
        }
        this.mNoNetworkLayout = (ViewGroup) this.mContentView.findViewById(R.id.no_network_layout);
        initNetWorkLayout();
    }

    private void configView() {
        this.mHealthScrollView.setVisibility(4);
        this.mLoadingLayout.setVisibility(0);
        this.mMapLayout.setVisibility(0);
        this.mMapLogo.setText("Map Logo");
        setScrollListener();
        if (efb.a()) {
            setRecycleViewScrollListener();
        }
    }

    public void initData() {
        LogUtil.a(TAG, "initData");
        if (this.mContentView == null) {
            return;
        }
        this.mAdapter.c();
        configView();
        if (this.mIsPageResTriggerEnabled) {
            this.mPageResTrigger.onPageCreated(getActivity(), this.mHealthScrollView);
        }
        KnitFragmentModel knitFragmentModel = this.mPageViewModel;
        if (knitFragmentModel == null) {
            LogUtil.b(TAG, "initData failed cause mPageViewModel is null!");
            return;
        }
        if (koq.b(knitFragmentModel.a())) {
            this.mPageViewModel.a(this, this.mIndividualObserver);
            this.mPageViewModel.b(this, this.mOverAllObserver);
            if (this.mIsForeverObserver) {
                this.mPageViewModel.b(this.mIndividualObserver);
            } else {
                this.mPageViewModel.d(this, this.mIndividualObserver);
            }
            boolean aa = CommonUtil.aa(getContext());
            List<SectionBean> onPageLoadOfflineSections = this.mPageResTrigger.onPageLoadOfflineSections(this.mPageData, aa);
            bindMinorKnitFragment();
            this.mPageViewModel.d(onPageLoadOfflineSections);
            this.mOverAllObserver.onChanged(this.mPageViewModel.b());
            if (!koq.b(onPageLoadOfflineSections)) {
                if (isSportCategory() || !this.mIsShowLoading) {
                    hideLoading();
                }
                loadOnLineSections();
                return;
            }
            setNetWorkLayoutStatus(aa);
            if (aa) {
                loadOnLineSections();
                return;
            }
            return;
        }
        LogUtil.h(TAG, "initData sectionBeansList is empty. ");
    }

    public void registerMajorKnitFragment(KnitFragment knitFragment) {
        this.mMajorKnitFragment = knitFragment;
    }

    public HealthScrollView getHealthScrollView() {
        return this.mHealthScrollView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadOnLineSections() {
        LogUtil.a(TAG, "loadNetData");
        this.mPageResTrigger.onPageLoadOnlineSections(new d(this), getChildFragmentManager());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateOnLineSections(final List<SectionBean> list) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.health.knit.ui.KnitFragment.5
                @Override // java.lang.Runnable
                public void run() {
                    KnitFragment.this.updateOnLineSections(list);
                }
            });
            return;
        }
        if (koq.b(list)) {
            LogUtil.a(TAG, "onlineSection is empty");
            showNetWorkLayout(2);
        }
        this.mPageViewModel.d(list);
        this.mOverAllObserver.onChanged(this.mPageViewModel.b());
    }

    private void bindMinorKnitFragment() {
        KnitFragment knitFragment;
        IPageResTrigger iPageResTrigger;
        if (this.mPageResTrigger == null || (knitFragment = this.mMajorKnitFragment) == null || (iPageResTrigger = knitFragment.mPageResTrigger) == null) {
            return;
        }
        MajorProvider majorProvider = iPageResTrigger.getMajorProvider();
        List<MinorProvider> minorProviderList = this.mPageResTrigger.getMinorProviderList();
        if (majorProvider == null || koq.b(minorProviderList)) {
            return;
        }
        for (MinorProvider minorProvider : minorProviderList) {
            majorProvider.registerMinorProvider(minorProvider);
            minorProvider.onMajorProviderDataArrived(majorProvider.getLastData());
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LogUtil.a(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(configuration);
        KnitFragmentModel knitFragmentModel = this.mPageViewModel;
        if (knitFragmentModel != null) {
            knitFragmentModel.c();
        }
        IPageResTrigger iPageResTrigger = this.mPageResTrigger;
        if (iPageResTrigger != null) {
            iPageResTrigger.onConfigurationChanged();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onMultiWindowModeChanged(boolean z) {
        LogUtil.a(TAG, "onMultiWindowModeChanged");
        IPageResTrigger iPageResTrigger = this.mPageResTrigger;
        if (iPageResTrigger != null) {
            iPageResTrigger.onMultiWindowModeChanged();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        LogUtil.a(TAG, "onStart");
        super.onStart();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        LogUtil.a(TAG, "onResume");
        if (this.mContentView == null) {
            return;
        }
        super.onResume();
        ntd.b().cME_(this, this.mContentView);
        this.mIsNeedLoadDataOnResume = true;
        this.mIsLogin = LoginInit.getInstance(getContext()).getIsLogined();
        if (this.mPageViewModel == null) {
            LogUtil.a(TAG, "mPageViewModel is null");
            return;
        }
        LogUtil.a(TAG, "userVisibleHint: " + getUserVisibleHint() + ", mFirstTimeVisible: " + this.mFirstTimeVisible);
        if ((getUserVisibleHint() && this.mPageResTrigger != null && this.mFirstTimeVisible && this.mContentView != null) || this.mIsLazyLoad) {
            LogUtil.a(TAG, "isLazyInitData");
            initData();
            this.mFirstTimeVisible = false;
            this.mIsLazyLoad = false;
        } else {
            LogUtil.a(TAG, "LazyResume");
            doLazyResume();
        }
        IPageResTrigger iPageResTrigger = this.mPageResTrigger;
        if (iPageResTrigger != null) {
            iPageResTrigger.onResume();
        }
    }

    private void retriggerSetUserVisibleHint() {
        IPageResTrigger iPageResTrigger = this.mPageResTrigger;
        if (iPageResTrigger == null || !this.mIsPageResTriggerEnabled) {
            return;
        }
        iPageResTrigger.onPageVisibilityChanged(getActivity(), this.mIsVisibleToUser, this.mHealthScrollView);
    }

    private void doLazyResume() {
        Object[] objArr = new Object[3];
        boolean z = false;
        objArr[0] = "doLazyResume for resPosId = ";
        IPageResTrigger iPageResTrigger = this.mPageResTrigger;
        objArr[1] = Integer.valueOf(iPageResTrigger != null ? iPageResTrigger.getResPosId() : 0);
        objArr[2] = "mIsAllowResumeRefresh: " + this.mIsAllowResumeRefresh;
        LogUtil.a(TAG, objArr);
        if (!this.mIsNeedLoadDataOnResume || !getUserVisibleHint()) {
            LogUtil.a(TAG, "not need doLazyResume, mIsNeedLoadDataOnResume: " + this.mIsNeedLoadDataOnResume);
            return;
        }
        if (this.mIsAllowResumeRefresh) {
            this.mPageViewModel.e(getActivity(), SectionBean.LoadReason.FORCE);
            z = true;
        }
        KnitFragmentModel knitFragmentModel = this.mPageViewModel;
        if (knitFragmentModel != null) {
            knitFragmentModel.j();
        }
        this.mIsNeedLoadDataOnResume = !z;
    }

    public void onSelfShow() {
        KnitFragmentModel knitFragmentModel = this.mPageViewModel;
        if (knitFragmentModel != null) {
            knitFragmentModel.a(getActivity());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideLoading() {
        hideLoading(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideLoading(final int i) {
        LogUtil.a(TAG, "hideLoading");
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.health.knit.ui.KnitFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    KnitFragment.this.hideLoading(i);
                }
            });
            return;
        }
        this.mHealthScrollView.setVisibility(0);
        this.mLoadingLayout.setVisibility(8);
        IBaseResponseCallback iBaseResponseCallback = this.mHideLoadingCallback;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(i, null);
        }
        ObserverManagerUtil.c(ObserveLabels.KNIT_FRAGMENT_LOADING_FINISH, new Object[0]);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        LogUtil.a(TAG, "onPause");
        super.onPause();
        KnitFragmentModel knitFragmentModel = this.mPageViewModel;
        if (knitFragmentModel != null) {
            knitFragmentModel.d();
        }
        IPageResTrigger iPageResTrigger = this.mPageResTrigger;
        if (iPageResTrigger != null) {
            iPageResTrigger.onPause();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        LogUtil.a(TAG, "onStop");
        super.onStop();
        KnitFragmentModel knitFragmentModel = this.mPageViewModel;
        if (knitFragmentModel != null) {
            knitFragmentModel.i();
        }
        IPageResTrigger iPageResTrigger = this.mPageResTrigger;
        if (iPageResTrigger != null) {
            iPageResTrigger.onStop();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        LogUtil.a(TAG, "onDestroy");
        clearView();
        KnitFragmentModel knitFragmentModel = this.mPageViewModel;
        if (knitFragmentModel != null) {
            knitFragmentModel.c(this, this.mIndividualObserver);
        }
        this.mFirstTimeVisible = true;
        IPageResTrigger iPageResTrigger = this.mPageResTrigger;
        if (iPageResTrigger != null) {
            iPageResTrigger.onDestroy(getActivity());
            this.mPageResTrigger.onDestroy();
        }
        ObserverManagerUtil.e(this.loginStatusChangeObserver, "com.huawei.plugin.account.login");
        ObserverManagerUtil.e(this.loginStatusChangeObserver, "com.huawei.plugin.account.logout");
        HealthScrollView healthScrollView = this.mHealthScrollView;
        if (healthScrollView != null) {
            healthScrollView.c();
        }
        super.onDestroy();
    }

    private void clearView() {
        KnitSectionRecyclerAdapter knitSectionRecyclerAdapter = this.mAdapter;
        if (knitSectionRecyclerAdapter != null) {
            knitSectionRecyclerAdapter.h();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        LogUtil.a(TAG, "setUserVisibleHint, isVisibleToUser: ", Boolean.valueOf(z));
        this.mIsVisibleToUser = z;
        super.setUserVisibleHint(z);
        if (getActivity() == null) {
            return;
        }
        boolean z2 = z && this.mPageResTrigger != null && this.mFirstTimeVisible;
        if (z2) {
            initData();
            this.mFirstTimeVisible = false;
        }
        IPageResTrigger iPageResTrigger = this.mPageResTrigger;
        if (iPageResTrigger != null && this.mIsPageResTriggerEnabled) {
            iPageResTrigger.onPageVisibilityChanged(getActivity(), z, this.mHealthScrollView);
        }
        KnitFragmentModel knitFragmentModel = this.mPageViewModel;
        if (knitFragmentModel != null) {
            knitFragmentModel.c(z);
        }
        IPageResTrigger iPageResTrigger2 = this.mPageResTrigger;
        if (iPageResTrigger2 != null) {
            iPageResTrigger2.setUserVisibleHint(z);
        }
        if (z2) {
            return;
        }
        doLazyResume();
    }

    public int getResPosId() {
        IPageResTrigger iPageResTrigger = (IPageResTrigger) getArguments().getParcelable(KEY_PAGE_RES_TRIGGER);
        if (iPageResTrigger != null) {
            return iPageResTrigger.getResPosId();
        }
        return 0;
    }

    public void refreshAllSections() {
        LogUtil.a(TAG, "refreshAllSections");
        KnitFragmentModel knitFragmentModel = this.mPageViewModel;
        if (knitFragmentModel != null) {
            knitFragmentModel.e(getActivity(), SectionBean.LoadReason.FORCE);
        }
    }

    public void setOnActivityResultListener(OnActivityResultListener onActivityResultListener) {
        this.mOnActivityResultListener = onActivityResultListener;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a(TAG, "onActivityResult");
        super.onActivityResult(i, i2, intent);
        OnActivityResultListener onActivityResultListener = this.mOnActivityResultListener;
        if (onActivityResultListener != null) {
            onActivityResultListener.onActivityResult(i, i2, intent);
        }
        IPageResTrigger iPageResTrigger = this.mPageResTrigger;
        if (iPageResTrigger != null) {
            iPageResTrigger.onActivityResult(i, i2, intent);
        }
    }

    public void setIsAllowResumeRefresh(boolean z) {
        this.mIsAllowResumeRefresh = z;
    }

    public boolean getIsAllowResumeRefresh() {
        return this.mIsAllowResumeRefresh;
    }

    private void initNetWorkLayout() {
        if (this.mPageResTrigger.isNeedToLoadEmptyLayout()) {
            this.mNoNetworkLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.knit.ui.KnitFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    KnitFragment.this.showLoadingLayout();
                    if (CommonUtil.aa((Context) null)) {
                        KnitFragment.this.loadOnLineSections();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            this.mPageResTrigger.setOnFailureListener(new OnFailureListener() { // from class: com.huawei.health.knit.ui.KnitFragment.4
                @Override // com.huawei.hmf.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    KnitFragment.this.showNetWorkLayout(2);
                }
            });
        }
    }

    private void setNetWorkLayoutStatus(boolean z) {
        if (!z) {
            showNetWorkLayout(1);
        } else {
            showLoadingLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNetWorkLayout(final int i) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.health.knit.ui.KnitFragment.7
                @Override // java.lang.Runnable
                public void run() {
                    KnitFragment.this.showNetWorkLayout(i);
                }
            });
            return;
        }
        if (this.mPageResTrigger.isNeedToLoadEmptyLayout()) {
            this.mNoNetworkLayout.setVisibility(0);
            this.mLoadingLayout.setVisibility(8);
            this.mHealthScrollView.setVisibility(8);
            HealthTextView healthTextView = (HealthTextView) this.mNoNetworkLayout.findViewById(R.id.tips_no_net_work);
            if (i == 1) {
                nsy.cMq_(healthTextView, R$string.IDS_hwh_home_group_network_disconnection);
            } else {
                if (i != 2) {
                    return;
                }
                nsy.cMq_(healthTextView, R$string.IDS_heart_rate_measuring_status_data_fail);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLoadingLayout() {
        LogUtil.a(TAG, "showLoadingLayout");
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.health.knit.ui.KnitFragment.9
                @Override // java.lang.Runnable
                public void run() {
                    KnitFragment.this.showLoadingLayout();
                }
            });
            return;
        }
        this.mNoNetworkLayout.setVisibility(8);
        this.mLoadingLayout.setVisibility(0);
        this.mHealthScrollView.setVisibility(0);
    }

    public void setIsRegisterForeverObserver(boolean z) {
        this.mIsForeverObserver = z;
    }

    public void setLoadingCallback(IBaseResponseCallback iBaseResponseCallback) {
        this.mHideLoadingCallback = iBaseResponseCallback;
    }

    public void setMapMaskViewBackground(Drawable drawable) {
        SectionMapMaskView sectionMapMaskView = this.mKnitMapMask;
        if (sectionMapMaskView != null) {
            if (drawable instanceof BitmapDrawable) {
                nrf.cIp_(drawable, sectionMapMaskView);
            } else {
                sectionMapMaskView.setImageDrawable(null);
            }
        }
    }

    public void setScrollViewTopBoundaryListener(HealthScrollView.ScrollChangeToBoundaryListener scrollChangeToBoundaryListener) {
        this.mScrollViewTopBoundaryListener = scrollChangeToBoundaryListener;
    }

    public void informGpsSignal(int i) {
        KnitFragmentModel knitFragmentModel = this.mPageViewModel;
        if (knitFragmentModel != null) {
            knitFragmentModel.e(i);
        }
    }

    public String getPageData() {
        return this.mPageData;
    }

    public List<Object> getItemViews() {
        ArrayList arrayList = new ArrayList();
        List<BaseSection> e2 = this.mAdapter.e();
        for (int i = 0; i < e2.size(); i++) {
            BaseSection baseSection = e2.get(i);
            if (baseSection instanceof SectionShuteyeShare) {
                baseSection.setViewSHow(0);
            }
            arrayList.add(baseSection);
        }
        return arrayList;
    }

    public List<Bitmap> getShareBitmap() {
        return this.mAdapter.a();
    }

    public String getCurrentDayMsg() {
        List<BaseSection> e2 = this.mAdapter.e();
        for (int i = 0; i < e2.size(); i++) {
            BaseSection baseSection = e2.get(i);
            if (baseSection instanceof BarChartCustomSection) {
                return ((BarChartCustomSection) baseSection).getCurrentDayMsg();
            }
        }
        return "";
    }

    public Bundle getSleepScoringData() {
        KnitFragment c2;
        Bundle bundle = new Bundle();
        for (BaseSection baseSection : this.mAdapter.e()) {
            if (baseSection instanceof CollapsibleSectionGroup) {
                for (BaseSection baseSection2 : ((CollapsibleSectionGroup) baseSection).getSectionList()) {
                    if (baseSection2 instanceof SectionScoring) {
                        SectionScoring sectionScoring = (SectionScoring) baseSection2;
                        String sleepStatus = sectionScoring.getSleepStatus();
                        String sleepScoring = sectionScoring.getSleepScoring();
                        String scoringDesc = sectionScoring.getScoringDesc();
                        String commonSleepText = sectionScoring.getCommonSleepText();
                        bundle.putString(KnitHealthDetailActivity.KEY_SLEEP_STATUS, sleepStatus);
                        bundle.putString(KnitHealthDetailActivity.KEY_SLEEP_SCORING, sleepScoring);
                        bundle.putString(KnitHealthDetailActivity.KEY_SLEEP_SCORING_DESC, scoringDesc);
                        bundle.putString(KnitHealthDetailActivity.KEY_SLEEP_COMMON_SLEEP, commonSleepText);
                        return bundle;
                    }
                }
            } else if ((baseSection instanceof SectionTabSwitch) && (c2 = ((SectionTabSwitch) baseSection).c(0)) != null) {
                return c2.getSleepScoringData();
            }
        }
        return bundle;
    }

    public Bundle getSleepEfficientDesc() {
        Bundle bundle = new Bundle();
        for (BaseSection baseSection : this.mAdapter.e()) {
            if (baseSection instanceof SectionTabSwitch) {
                KnitFragment c2 = ((SectionTabSwitch) baseSection).c(0);
                if (c2 != null) {
                    return c2.getSleepEfficientDesc();
                }
            } else if (baseSection instanceof CollapsibleSectionGroup) {
                getSleepEfficient(bundle, (CollapsibleSectionGroup) baseSection);
            }
        }
        return bundle;
    }

    private void getSleepEfficient(Bundle bundle, CollapsibleSectionGroup collapsibleSectionGroup) {
        for (BaseSection baseSection : collapsibleSectionGroup.getSectionList()) {
            if (baseSection instanceof SectionExpandReport) {
                bundle.putString(KnitHealthDetailActivity.KEY_SLEEP_EFFICIENT, ((SectionExpandReport) baseSection).getEfficientDesc());
            }
        }
    }

    public int getDeviceId() {
        List<BaseSection> e2 = this.mAdapter.e();
        for (int i = 0; i < e2.size(); i++) {
            BaseSection baseSection = e2.get(i);
            if (baseSection instanceof BarChartCustomSection) {
                return ((BarChartCustomSection) baseSection).getmDeviceType();
            }
        }
        return 0;
    }

    public boolean getIsVisibleToUser() {
        return this.mIsVisibleToUser;
    }

    public boolean getIsSetUserVisibleLastTime() {
        return this.mIsSetUserVisibleLastTime;
    }

    public void setIsSetUserVisibleLastTime(boolean z) {
        this.mIsSetUserVisibleLastTime = z;
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.mScrollViewListener = scrollViewListener;
        HealthScrollView healthScrollView = this.mHealthScrollView;
        if (healthScrollView != null) {
            healthScrollView.setIsForceScrollListener(true);
            this.mHealthScrollView.setScrollViewListener(scrollViewListener);
        }
    }

    public int getScrollViewRange() {
        View childAt;
        HealthScrollView healthScrollView = this.mHealthScrollView;
        if (healthScrollView == null || (childAt = healthScrollView.getChildAt(0)) == null) {
            return 0;
        }
        return childAt.getHeight();
    }

    public int getScrollViewHeight() {
        HealthScrollView healthScrollView = this.mHealthScrollView;
        if (healthScrollView != null) {
            return healthScrollView.getHeight();
        }
        return 0;
    }

    public void setRecycleViewListener(RecyclerView.OnScrollListener onScrollListener) {
        this.mRecycleViewListener = onScrollListener;
        HealthRecycleView healthRecycleView = this.mRecyclerView;
        if (healthRecycleView != null) {
            healthRecycleView.setOnScrollListener(onScrollListener);
        }
    }

    public void setRecyclerViewBgColor(int i) {
        this.mRecyclerViewBgColor = i;
        this.mIsSetRecyclerViewBgColor = true;
        nsy.cMk_(this.mRecyclerView, i);
    }

    static class a implements Observer {
        private WeakReference<KnitFragment> e;

        public a(KnitFragment knitFragment) {
            this.e = new WeakReference<>(knitFragment);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            KnitFragment knitFragment = this.e.get();
            if (knitFragment == null) {
                LogUtil.b(KnitFragment.TAG, "LoginStatusChangeObserver notify knitFragment null");
                return;
            }
            if (StringUtils.g(str) || knitFragment.mPageViewModel == null || knitFragment.mIsLogin) {
                return;
            }
            if ("com.huawei.plugin.account.login".equals(str) || "com.huawei.plugin.account.logout".equals(str)) {
                knitFragment.mPageViewModel.e(knitFragment.getActivity(), SectionBean.LoadReason.FORCE);
            }
        }
    }

    private boolean containsNewAchievementArea(List<SectionBean> list) {
        Iterator<SectionBean> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().o() == 61) {
                return true;
            }
        }
        return false;
    }

    public void setPageResTriggerEnabled(boolean z) {
        this.mIsPageResTriggerEnabled = z;
    }

    public void setPuppetsView(int[] iArr) {
        PuppetsViewCallBack puppetsViewCallBack = this.mPuppetsViewCallBack;
        if (puppetsViewCallBack != null) {
            puppetsViewCallBack.setPuppetsView(iArr);
        }
    }

    public void setPuppetsViewCallBack(PuppetsViewCallBack puppetsViewCallBack) {
        this.mPuppetsViewCallBack = puppetsViewCallBack;
    }

    public void setIsHideLoadingOnTimeout(boolean z) {
        this.mIsHideLoadingOnTimeout = z;
    }

    public void setRecyclerViewDescendantFocusability(int i) {
        HealthRecycleView healthRecycleView = this.mRecyclerView;
        if (healthRecycleView == null) {
            LogUtil.a(TAG, "mRecyclerView is null");
        } else {
            healthRecycleView.setDescendantFocusability(i);
        }
    }

    public void refreshRecyclerView() {
        if (this.mRecyclerView != null) {
            LogUtil.a(TAG, "refreshRecyclerView");
            this.mRecyclerView.requestLayout();
        }
    }

    public void setIsBottomPadding(boolean z) {
        this.mIsBottomPadding = z;
    }

    public void setIsLazyLoad(boolean z) {
        this.mIsLazyLoad = z;
    }

    public void setIsShowLoading(boolean z) {
        this.mIsShowLoading = z;
    }

    public void setOutHandler(Handler handler) {
        this.mOutHandler = handler;
    }

    public Handler getOutHandler() {
        return this.mOutHandler;
    }

    public IPageResTrigger getPageResTrigger() {
        return this.mPageResTrigger;
    }

    static class e implements androidx.lifecycle.Observer<List<SectionBean>> {
        private WeakReference<KnitFragment> d;

        public e(KnitFragment knitFragment) {
            this.d = new WeakReference<>(knitFragment);
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onChanged(List<SectionBean> list) {
            KnitFragment knitFragment = this.d.get();
            if (knitFragment == null) {
                LogUtil.b(KnitFragment.TAG, "OverAllObserver onChanged knitFragment null");
                return;
            }
            LogUtil.a(KnitFragment.TAG, "OverAllObserver onChanged");
            if (!koq.b(list)) {
                if (!knitFragment.mAdapter.d()) {
                    knitFragment.mRecyclerView.removeAllViews();
                    LogUtil.a(KnitFragment.TAG, "onOverAllSet");
                    knitFragment.mAdapter.e(list);
                } else {
                    LogUtil.a(KnitFragment.TAG, "onOverAllUpdate");
                    knitFragment.mAdapter.a(list, d(knitFragment, list));
                }
                boolean aa = CommonUtil.aa(knitFragment.getContext());
                boolean z = knitFragment.mPageResTrigger != null && knitFragment.mPageResTrigger.isUsingCacheBeanList();
                if (!aa || (knitFragment.mBootDrawingSectionsCount <= 0 && (!knitFragment.mIsOfflineOverAllChanged || knitFragment.getResPosId() == 4040 || z))) {
                    LogUtil.a(KnitFragment.TAG, "load online finish, hide loading now, isNetworkConnected = ", Boolean.valueOf(aa));
                    knitFragment.mHandler.removeMessages(100);
                    knitFragment.hideLoading(!aa ? 100009 : 0);
                }
                if (knitFragment.mPageViewModel != null) {
                    knitFragment.mPageViewModel.e(knitFragment.getActivity(), SectionBean.LoadReason.LIST_READY);
                }
                if (knitFragment.mIsOfflineOverAllChanged) {
                    if (knitFragment.mIsHideLoadingOnTimeout) {
                        knitFragment.mHandler.sendEmptyMessageDelayed(100, knitFragment.mLoadingTimeOutDelay);
                    }
                    knitFragment.mIsOfflineOverAllChanged = false;
                    return;
                }
                return;
            }
            LogUtil.b(KnitFragment.TAG, "onCardDataLoaded cardList is empty!");
        }

        private Consumer<Boolean> d(final KnitFragment knitFragment, List<SectionBean> list) {
            if (knitFragment.mBootDrawingSectionsCount <= 0) {
                return null;
            }
            for (int i = 0; i < knitFragment.mBootDrawingSectionsCount; i++) {
                list.get(i).c(true);
            }
            return new Consumer<Boolean>() { // from class: com.huawei.health.knit.ui.KnitFragment.e.3
                @Override // androidx.core.util.Consumer
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void accept(Boolean bool) {
                    knitFragment.mHandler.removeMessages(100);
                    knitFragment.hideLoading();
                }
            };
        }
    }

    static class b implements androidx.lifecycle.Observer<SectionBean> {
        private WeakReference<KnitFragment> d;

        public b(KnitFragment knitFragment) {
            this.d = new WeakReference<>(knitFragment);
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onChanged(final SectionBean sectionBean) {
            KnitFragment knitFragment = this.d.get();
            if (knitFragment != null) {
                boolean isComputingLayout = knitFragment.mRecyclerView.isComputingLayout();
                boolean z = knitFragment.mRecyclerView.getScrollState() == 0;
                if (isComputingLayout || !z) {
                    LogUtil.a(KnitFragment.TAG, "IndividualObserver onChanged isComputingLayout = ", Boolean.valueOf(isComputingLayout), ", isInIdleState = ", Boolean.valueOf(z), ", post it!");
                    HandlerExecutor.a(new Runnable() { // from class: com.huawei.health.knit.ui.KnitFragment.b.5
                        @Override // java.lang.Runnable
                        public void run() {
                            b.this.onChanged(sectionBean);
                        }
                    });
                    return;
                }
                LogUtil.a(KnitFragment.TAG, "IndividualObserver onChanged");
                knitFragment.mAdapter.c(sectionBean);
                if (sectionBean.o() == 61) {
                    knitFragment.hideLoading();
                    return;
                }
                return;
            }
            LogUtil.b(KnitFragment.TAG, "IndividualObserver onChanged knitFragment null");
        }
    }

    static class c extends BaseHandler<KnitFragment> {
        public c(KnitFragment knitFragment) {
            super(knitFragment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: ajR_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(KnitFragment knitFragment, Message message) {
            if (message.what != 100) {
                return;
            }
            LogUtil.h(KnitFragment.TAG, "load online timeout, hide loading now");
            knitFragment.hideLoading(100009);
        }
    }

    static class d implements Consumer<List<SectionBean>> {
        private WeakReference<KnitFragment> c;

        public d(KnitFragment knitFragment) {
            this.c = new WeakReference<>(knitFragment);
        }

        @Override // androidx.core.util.Consumer
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void accept(List<SectionBean> list) {
            KnitFragment knitFragment = this.c.get();
            if (knitFragment == null) {
                return;
            }
            knitFragment.updateOnLineSections(list);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public String toString() {
        return super.toString() + ", resPosId = " + getResPosId() + ", context = " + getContext();
    }
}
