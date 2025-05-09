package com.huawei.health.suggestion.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.huawei.health.R;
import com.huawei.health.suggestion.common.SearchShowMode;
import com.huawei.health.suggestion.ui.fitness.helper.FitnessSearchAllHelper;
import com.huawei.health.suggestion.ui.fragment.FitSearchFragmentFlowLayout;
import com.huawei.health.suggestion.ui.fragment.FitSearchFragmentRecyclerView;
import com.huawei.health.suggestion.ui.fragment.FitnessActionSearchFragmentRecycleView;
import com.huawei.health.suggestion.ui.fragment.FitnessSearchFragmentBar;
import com.huawei.health.suggestion.ui.view.FlowLayout;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import defpackage.gge;
import defpackage.ghu;
import defpackage.koq;
import java.util.List;

/* loaded from: classes8.dex */
public abstract class BaseFitnessSearchActivity extends BaseStateActivity implements SearchViewInterface {
    private static final String TAG = "Suggestion_BaseFitnessSearchActivity";
    protected int mCurrentWorkoutType;
    protected FitnessSearchAllHelper mFitSearchAllHelper;
    protected FitnessActionSearchFragmentRecycleView mFitnessActionSearchRecycleView;
    private FitSearchFragmentFlowLayout mFitnessSearchFlowLayout;
    private FitSearchFragmentRecyclerView mFitnessSearchRecyclerView;
    private FragmentManager mFragmentManager;
    private View mNormalModeContentLayout;
    private View mSearchContentLayout;
    private FitnessSearchFragmentBar mSearchFragmentBar;
    private View mSearchLoadingView;
    private View mSearchNoShow;
    private e mSearchOnClick;
    public SearchShowMode mShowModeStatus = SearchShowMode.NORMAL;
    protected String mCurrentPageSportType = "";

    protected abstract void initNormalModeLayout();

    protected abstract void initNormalViewController();

    protected abstract void initTitleBarSearchController();

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        initSearchLayout();
        initNormalModeLayout();
    }

    private void initSearchLayout() {
        this.mSearchContentLayout = findViewById(R.id.search_content_layout);
        FragmentManager fragmentManager = getFragmentManager();
        this.mFragmentManager = fragmentManager;
        if (fragmentManager.findFragmentById(R.id.fragment_FitSearchFragmentBar) instanceof FitnessSearchFragmentBar) {
            this.mSearchFragmentBar = (FitnessSearchFragmentBar) this.mFragmentManager.findFragmentById(R.id.fragment_FitSearchFragmentBar);
        }
        if (this.mFragmentManager.findFragmentById(R.id.fragment_FitSearchFragmentFlowLayout) instanceof FitSearchFragmentFlowLayout) {
            this.mFitnessSearchFlowLayout = (FitSearchFragmentFlowLayout) this.mFragmentManager.findFragmentById(R.id.fragment_FitSearchFragmentFlowLayout);
        }
        if (this.mFragmentManager.findFragmentById(R.id.fragment_FitSearchFragmentRecyclerView) instanceof FitSearchFragmentRecyclerView) {
            this.mFitnessSearchRecyclerView = (FitSearchFragmentRecyclerView) this.mFragmentManager.findFragmentById(R.id.fragment_FitSearchFragmentRecyclerView);
        }
        Fragment findFragmentById = this.mFragmentManager.findFragmentById(R.id.fragment_FitnessActionSearchFragmentFlowLayout);
        if (findFragmentById instanceof FitnessActionSearchFragmentRecycleView) {
            this.mFitnessActionSearchRecycleView = (FitnessActionSearchFragmentRecycleView) findFragmentById;
        }
        this.mSearchNoShow = findViewById(R.id.search_no_show);
        View findViewById = findViewById(R.id.sug_search_loading);
        this.mSearchLoadingView = findViewById;
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
        this.mNormalModeContentLayout = findViewById(R.id.normal_content_layout);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
        initTitleBarSearchController();
        initSearchViewController();
        initNormalViewController();
    }

    public void initSearchViewController() {
        FitnessSearchFragmentBar fitnessSearchFragmentBar = this.mSearchFragmentBar;
        if (fitnessSearchFragmentBar == null) {
            LogUtil.h(TAG, "mSearchFragmentBar == null");
            return;
        }
        fitnessSearchFragmentBar.aFV_(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.BaseFitnessSearchActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseFitnessSearchActivity.this.handleSearchBarClick();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
        beginTransaction.hide(this.mSearchFragmentBar);
        beginTransaction.commit();
        e eVar = new e(this);
        this.mSearchOnClick = eVar;
        this.mSearchFragmentBar.e(eVar);
        FitSearchFragmentRecyclerView fitSearchFragmentRecyclerView = this.mFitnessSearchRecyclerView;
        if (fitSearchFragmentRecyclerView != null) {
            fitSearchFragmentRecyclerView.d(this.mSearchOnClick);
        } else {
            this.mFitnessActionSearchRecycleView.b(this.mSearchOnClick);
            this.mSearchFragmentBar.e(getResources().getString(R.string._2130848468_res_0x7f022ad4));
        }
        this.mFitnessSearchFlowLayout.d(this.mSearchOnClick);
        if (this.mCurrentWorkoutType == 3) {
            this.mFitSearchAllHelper = new FitnessSearchAllHelper(this, 3);
        } else {
            this.mFitSearchAllHelper = new FitnessSearchAllHelper(this, 2);
        }
        this.mFitSearchAllHelper.b(this.mCurrentPageSportType);
        setOnQueryTextListener(this.mFitSearchAllHelper);
        setOnQueryTextListener(this.mFitSearchAllHelper);
        setLoadMoreListener(this.mFitSearchAllHelper);
        createFlowAdapter();
    }

    private void createFlowAdapter() {
        if (this.mFitnessSearchRecyclerView != null) {
            setFlowAdapter(new FitnessSearchAllHelper.SearchAllFowAdapter(getApplicationContext()));
        } else if (this.mCurrentWorkoutType == 3) {
            setFlowAdapter(new FitnessSearchAllHelper.SearchAllFowAdapter(getApplicationContext(), 3));
        } else {
            setFlowAdapter(new FitnessSearchAllHelper.SearchAllFowAdapter(getApplicationContext(), 2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSearchBarClick() {
        if (SearchShowMode.SEARCH.equals(this.mShowModeStatus)) {
            Object systemService = this.mSearchFragmentBar.getActivity().getApplicationContext().getSystemService("input_method");
            if (systemService instanceof InputMethodManager) {
                ((InputMethodManager) systemService).hideSoftInputFromWindow(this.mSearchFragmentBar.getActivity().getWindow().getDecorView().getWindowToken(), 0);
            }
            switchToNormalMode();
        }
    }

    public void switchToSearchMode() {
        this.mShowModeStatus = SearchShowMode.SEARCH;
        if (!isCheckLayoutValid()) {
            LogUtil.h(TAG, "layout view is invalid to switch to search mode.");
        }
        this.mTitleBar.setVisibility(4);
        FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
        beginTransaction.setCustomAnimations(R.animator._2131034181_res_0x7f050045, R.animator._2131034180_res_0x7f050044);
        beginTransaction.show(this.mSearchFragmentBar);
        beginTransaction.commit();
        this.mNormalModeContentLayout.startAnimation(ghu.aMV_());
        this.mNormalModeContentLayout.setVisibility(4);
        new Handler().postDelayed(new Runnable() { // from class: com.huawei.health.suggestion.ui.BaseFitnessSearchActivity.5
            @Override // java.lang.Runnable
            public void run() {
                BaseFitnessSearchActivity.this.mSearchContentLayout.startAnimation(ghu.aMU_());
                BaseFitnessSearchActivity.this.mSearchContentLayout.setVisibility(0);
                Object systemService = BaseFitnessSearchActivity.this.getApplicationContext().getSystemService("input_method");
                if (systemService instanceof InputMethodManager) {
                    ((InputMethodManager) systemService).toggleSoftInput(0, 2);
                }
                BaseFitnessSearchActivity.this.mSearchFragmentBar.a();
            }
        }, 50L);
    }

    protected void switchToNormalMode() {
        this.mShowModeStatus = SearchShowMode.NORMAL;
        if (!isCheckLayoutValid()) {
            LogUtil.h(TAG, "layout view is invalid to switch to normal mode.");
        }
        this.mTitleBar.setVisibility(0);
        FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
        beginTransaction.setCustomAnimations(ghu.d(), ghu.a());
        beginTransaction.hide(this.mSearchFragmentBar);
        beginTransaction.commit();
        this.mSearchContentLayout.setAnimation(ghu.aMT_());
        this.mSearchContentLayout.setVisibility(4);
        new Handler().postDelayed(new Runnable() { // from class: com.huawei.health.suggestion.ui.BaseFitnessSearchActivity.1
            @Override // java.lang.Runnable
            public void run() {
                BaseFitnessSearchActivity.this.mNormalModeContentLayout.setAnimation(ghu.aMS_());
                BaseFitnessSearchActivity.this.mNormalModeContentLayout.setVisibility(0);
            }
        }, 50L);
    }

    public void showSearchKey() {
        clear();
        FitSearchFragmentFlowLayout fitSearchFragmentFlowLayout = this.mFitnessSearchFlowLayout;
        fitSearchFragmentFlowLayout.aFR_(fitSearchFragmentFlowLayout);
        this.mSearchNoShow.setVisibility(8);
    }

    @Override // com.huawei.health.suggestion.ui.SearchViewInterface
    public void clear() {
        FitSearchFragmentRecyclerView fitSearchFragmentRecyclerView = this.mFitnessSearchRecyclerView;
        if (fitSearchFragmentRecyclerView != null) {
            fitSearchFragmentRecyclerView.d();
        } else {
            this.mFitnessActionSearchRecycleView.e();
        }
    }

    static class e implements FitSearchFragmentRecyclerView.LoadMoreListener, FlowLayout.OnTextClickListener, FitnessSearchFragmentBar.OnQueryTextListener {

        /* renamed from: a, reason: collision with root package name */
        private FitSearchFragmentRecyclerView.LoadMoreListener f3045a;
        private FitnessSearchFragmentBar.OnQueryTextListener b;
        private BaseFitnessSearchActivity e;

        e(BaseFitnessSearchActivity baseFitnessSearchActivity) {
            this.e = baseFitnessSearchActivity;
        }

        public void e(FitnessSearchFragmentBar.OnQueryTextListener onQueryTextListener) {
            this.b = onQueryTextListener;
        }

        public void e(FitSearchFragmentRecyclerView.LoadMoreListener loadMoreListener) {
            this.f3045a = loadMoreListener;
        }

        @Override // com.huawei.health.suggestion.ui.view.FlowLayout.OnTextClickListener
        public void onClick(String str) {
            LogUtil.a(BaseFitnessSearchActivity.TAG, "onClick text=", str);
            this.e.mSearchFragmentBar.d(str);
        }

        @Override // com.huawei.health.suggestion.ui.fragment.FitnessSearchFragmentBar.OnQueryTextListener
        public boolean onQueryTextSubmit(String str) {
            this.e.mFitnessSearchFlowLayout.d(str);
            if (this.b == null) {
                return false;
            }
            this.e.showLoadView();
            this.e.dismissSearchNoShowView();
            this.b.onQueryTextSubmit(str);
            BaseFitnessSearchActivity baseFitnessSearchActivity = this.e;
            if (baseFitnessSearchActivity == null || baseFitnessSearchActivity.mFitSearchAllHelper == null) {
                return false;
            }
            this.e.mFitSearchAllHelper.b(this.e.mCurrentPageSportType);
            return false;
        }

        @Override // com.huawei.health.suggestion.ui.fragment.FitnessSearchFragmentBar.OnQueryTextListener
        public boolean onQueryTextChange(String str) {
            if (TextUtils.isEmpty(str)) {
                this.e.showSearchKey();
            }
            if (this.b == null) {
                return false;
            }
            gge.e("1130036");
            this.b.onQueryTextChange(str);
            return false;
        }

        @Override // com.huawei.health.suggestion.ui.fragment.FitSearchFragmentRecyclerView.LoadMoreListener
        public void loadMore() {
            FitSearchFragmentRecyclerView.LoadMoreListener loadMoreListener = this.f3045a;
            if (loadMoreListener != null) {
                loadMoreListener.loadMore();
            }
        }
    }

    @Override // com.huawei.health.suggestion.ui.SearchViewInterface
    public void hideLoadMore() {
        FitSearchFragmentRecyclerView fitSearchFragmentRecyclerView = this.mFitnessSearchRecyclerView;
        if (fitSearchFragmentRecyclerView != null) {
            fitSearchFragmentRecyclerView.b();
        } else {
            this.mFitnessActionSearchRecycleView.a();
        }
    }

    @Override // com.huawei.health.suggestion.ui.SearchViewInterface
    public void showLoadMore() {
        FitSearchFragmentRecyclerView fitSearchFragmentRecyclerView = this.mFitnessSearchRecyclerView;
        if (fitSearchFragmentRecyclerView != null) {
            fitSearchFragmentRecyclerView.e();
        } else {
            this.mFitnessActionSearchRecycleView.d();
        }
    }

    @Override // com.huawei.health.suggestion.ui.SearchViewInterface
    public void showSearchNoShow() {
        View view = this.mSearchNoShow;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    public void setOnQueryTextListener(FitnessSearchFragmentBar.OnQueryTextListener onQueryTextListener) {
        this.mSearchOnClick.e(onQueryTextListener);
    }

    public void setLoadMoreListener(FitSearchFragmentRecyclerView.LoadMoreListener loadMoreListener) {
        this.mSearchOnClick.e(loadMoreListener);
    }

    public void setFlowAdapter(FitSearchFragmentFlowLayout.FowAdapter fowAdapter) {
        this.mFitnessSearchFlowLayout.e(fowAdapter);
    }

    @Override // com.huawei.health.suggestion.ui.SearchViewInterface
    public void addData(List<FitWorkout> list) {
        if (koq.c(list)) {
            FitSearchFragmentFlowLayout fitSearchFragmentFlowLayout = this.mFitnessSearchFlowLayout;
            fitSearchFragmentFlowLayout.aFQ_(fitSearchFragmentFlowLayout);
            this.mSearchNoShow.setVisibility(8);
        } else if (this.mFitnessSearchRecyclerView.a() == 0) {
            this.mSearchNoShow.setVisibility(0);
        }
        this.mFitnessSearchRecyclerView.b(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLoadView() {
        View view = this.mSearchLoadingView;
        if (view == null || view.getVisibility() != 8) {
            return;
        }
        this.mSearchLoadingView.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissSearchNoShowView() {
        View view = this.mSearchNoShow;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        this.mSearchNoShow.setVisibility(8);
    }

    @Override // com.huawei.health.suggestion.ui.SearchViewInterface
    public void hideLoadView() {
        View view = this.mSearchLoadingView;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        this.mSearchLoadingView.setVisibility(8);
    }

    public void resetData(List<AtomicAction> list) {
        hideLoadView();
        if (this.mFitnessActionSearchRecycleView == null) {
            LogUtil.h(TAG, "setData mFitnessActionSearchRecycleView can not null");
            return;
        }
        if (this.mFitnessSearchFlowLayout != null && koq.c(list)) {
            FitSearchFragmentFlowLayout fitSearchFragmentFlowLayout = this.mFitnessSearchFlowLayout;
            fitSearchFragmentFlowLayout.aFQ_(fitSearchFragmentFlowLayout);
            this.mSearchNoShow.setVisibility(8);
        } else if (this.mFitnessActionSearchRecycleView.c() == 0) {
            this.mSearchNoShow.setVisibility(0);
        }
        this.mFitnessActionSearchRecycleView.c(list);
    }

    public void updateViewState() {
        this.mSearchNoShow.setVisibility(8);
        FitnessActionSearchFragmentRecycleView fitnessActionSearchFragmentRecycleView = this.mFitnessActionSearchRecycleView;
        if (fitnessActionSearchFragmentRecycleView == null) {
            LogUtil.h(TAG, "updateViewState mFitnessActionSearchRecycleView can not null");
        } else {
            fitnessActionSearchFragmentRecycleView.c((List<AtomicAction>) null);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (SearchShowMode.SEARCH.equals(this.mShowModeStatus)) {
            switchToNormalMode();
        } else {
            super.onBackPressed();
        }
    }

    private boolean isCheckLayoutValid() {
        return (this.mSearchFragmentBar == null || (this.mNormalModeContentLayout == null || this.mSearchContentLayout == null)) ? false : true;
    }
}
