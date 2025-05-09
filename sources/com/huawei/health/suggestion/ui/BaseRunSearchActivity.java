package com.huawei.health.suggestion.ui;

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
import com.huawei.health.suggestion.ui.fragment.FitnessSearchFragmentBar;
import com.huawei.health.suggestion.ui.view.FlowLayout;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import defpackage.gds;
import defpackage.gge;
import defpackage.ghu;
import defpackage.koq;
import java.util.List;

/* loaded from: classes8.dex */
public abstract class BaseRunSearchActivity extends BaseStateActivity implements SearchViewInterface {
    private static final String TAG = "Suggestion_BaseRunSearchActivity";
    protected int mCurrentWorkoutType;
    private FitnessSearchAllHelper mFitSearchAllHelper;
    private FitSearchFragmentFlowLayout mFitnessSearchFlowLayout;
    private FragmentManager mFragmentManager;
    private View mNormalModeContentLayout;
    private View mSearchContentLayout;
    private FitnessSearchFragmentBar mSearchFragmentBar;
    private View mSearchNoShow;
    private b mSearchOnClick;
    private FitSearchFragmentRecyclerView mSearchRunCourseFragment;
    protected SearchShowMode mShowModeStatus = SearchShowMode.NORMAL;

    protected abstract void initNormalViewController();

    protected abstract void initTitleBarSearchController();

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        initSearchLayout();
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
            this.mSearchRunCourseFragment = (FitSearchFragmentRecyclerView) this.mFragmentManager.findFragmentById(R.id.fragment_FitSearchFragmentRecyclerView);
        }
        this.mSearchNoShow = findViewById(R.id.search_no_show);
        this.mNormalModeContentLayout = findViewById(R.id.normal_content_layout);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
        initTitleBarSearchController();
        initSearchViewController();
        initNormalViewController();
    }

    protected void initSearchViewController() {
        FitnessSearchFragmentBar fitnessSearchFragmentBar = this.mSearchFragmentBar;
        if (fitnessSearchFragmentBar == null) {
            return;
        }
        fitnessSearchFragmentBar.aFV_(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.BaseRunSearchActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SearchShowMode.SEARCH.equals(BaseRunSearchActivity.this.mShowModeStatus)) {
                    Object systemService = BaseRunSearchActivity.this.mSearchFragmentBar.getActivity().getApplicationContext().getSystemService("input_method");
                    if (systemService instanceof InputMethodManager) {
                        ((InputMethodManager) systemService).hideSoftInputFromWindow(BaseRunSearchActivity.this.mSearchFragmentBar.getActivity().getWindow().getDecorView().getWindowToken(), 0);
                    }
                    BaseRunSearchActivity.this.switchToNormalMode();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
        beginTransaction.hide(this.mSearchFragmentBar);
        beginTransaction.commit();
        b bVar = new b(this);
        this.mSearchOnClick = bVar;
        this.mSearchFragmentBar.e(bVar);
        this.mFitnessSearchFlowLayout.d(this.mSearchOnClick);
        this.mSearchRunCourseFragment.d(this.mSearchOnClick);
        if (this.mCurrentWorkoutType == 3) {
            this.mFitSearchAllHelper = new FitnessSearchAllHelper(this, 3);
        } else {
            this.mFitSearchAllHelper = new FitnessSearchAllHelper(this, 1);
        }
        setOnQueryTextListener(this.mFitSearchAllHelper);
        setRunLoadMoreListener(this.mFitSearchAllHelper);
        if (this.mCurrentWorkoutType == 3) {
            setFlowAdapter(new FitnessSearchAllHelper.SearchAllFowAdapter(getApplicationContext(), 3));
        } else {
            setFlowAdapter(new FitnessSearchAllHelper.SearchAllFowAdapter(getApplicationContext(), 1));
        }
    }

    protected void switchToSearchMode() {
        this.mShowModeStatus = SearchShowMode.SEARCH;
        if (!isCheckLayoutValid()) {
            LogUtil.h(TAG, "layout view is invalid to switch to search mode.");
        }
        FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
        beginTransaction.setCustomAnimations(R.animator._2131034181_res_0x7f050045, R.animator._2131034180_res_0x7f050044);
        beginTransaction.show(this.mSearchFragmentBar);
        beginTransaction.commit();
        this.mNormalModeContentLayout.startAnimation(ghu.aMV_());
        this.mNormalModeContentLayout.setVisibility(4);
        new Handler().postDelayed(new Runnable() { // from class: com.huawei.health.suggestion.ui.BaseRunSearchActivity.2
            @Override // java.lang.Runnable
            public void run() {
                BaseRunSearchActivity.this.mSearchContentLayout.startAnimation(ghu.aMU_());
                BaseRunSearchActivity.this.mSearchContentLayout.setVisibility(0);
                Object systemService = BaseRunSearchActivity.this.getApplicationContext().getSystemService("input_method");
                if (systemService instanceof InputMethodManager) {
                    ((InputMethodManager) systemService).toggleSoftInput(0, 2);
                }
                BaseRunSearchActivity.this.mSearchFragmentBar.a();
            }
        }, 50L);
    }

    protected void switchToNormalMode() {
        this.mShowModeStatus = SearchShowMode.NORMAL;
        if (!isCheckLayoutValid()) {
            LogUtil.h(TAG, "layout view is invalid to switch to normal mode.");
        }
        FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
        beginTransaction.setCustomAnimations(ghu.d(), ghu.a());
        beginTransaction.hide(this.mSearchFragmentBar);
        beginTransaction.commit();
        this.mSearchContentLayout.setAnimation(ghu.aMT_());
        this.mSearchContentLayout.setVisibility(4);
        new Handler().postDelayed(new Runnable() { // from class: com.huawei.health.suggestion.ui.BaseRunSearchActivity.4
            @Override // java.lang.Runnable
            public void run() {
                BaseRunSearchActivity.this.mNormalModeContentLayout.setAnimation(ghu.aMS_());
                BaseRunSearchActivity.this.mNormalModeContentLayout.setVisibility(0);
            }
        }, 50L);
    }

    public void showSearchKey() {
        clear();
        this.mFitnessSearchFlowLayout.aFR_(this.mSearchRunCourseFragment);
        this.mSearchNoShow.setVisibility(8);
    }

    @Override // com.huawei.health.suggestion.ui.SearchViewInterface
    public void clear() {
        this.mSearchRunCourseFragment.d();
    }

    static class b implements FlowLayout.OnTextClickListener, FitnessSearchFragmentBar.OnQueryTextListener, FitSearchFragmentRecyclerView.LoadMoreListener {
        private BaseRunSearchActivity b;
        private FitSearchFragmentRecyclerView.LoadMoreListener c;
        private FitnessSearchFragmentBar.OnQueryTextListener d;

        b(BaseRunSearchActivity baseRunSearchActivity) {
            this.b = baseRunSearchActivity;
        }

        public void a(FitnessSearchFragmentBar.OnQueryTextListener onQueryTextListener) {
            this.d = onQueryTextListener;
        }

        public void d(FitSearchFragmentRecyclerView.LoadMoreListener loadMoreListener) {
            this.c = loadMoreListener;
        }

        @Override // com.huawei.health.suggestion.ui.view.FlowLayout.OnTextClickListener
        public void onClick(String str) {
            LogUtil.a(BaseRunSearchActivity.TAG, "onClick text=", str);
            this.b.mSearchFragmentBar.d(str);
        }

        @Override // com.huawei.health.suggestion.ui.fragment.FitnessSearchFragmentBar.OnQueryTextListener
        public boolean onQueryTextSubmit(String str) {
            this.b.mFitnessSearchFlowLayout.d(str);
            if (this.d == null) {
                return false;
            }
            gge.e("1130037");
            this.d.onQueryTextSubmit(str);
            return false;
        }

        @Override // com.huawei.health.suggestion.ui.fragment.FitnessSearchFragmentBar.OnQueryTextListener
        public boolean onQueryTextChange(String str) {
            if (TextUtils.isEmpty(str)) {
                this.b.showSearchKey();
            }
            if (this.d == null) {
                return false;
            }
            gge.e("1130037");
            this.d.onQueryTextChange(str);
            return false;
        }

        @Override // com.huawei.health.suggestion.ui.fragment.FitSearchFragmentRecyclerView.LoadMoreListener
        public void loadMore() {
            FitSearchFragmentRecyclerView.LoadMoreListener loadMoreListener = this.c;
            if (loadMoreListener != null) {
                loadMoreListener.loadMore();
            }
        }
    }

    @Override // com.huawei.health.suggestion.ui.SearchViewInterface
    public void hideLoadMore() {
        this.mSearchRunCourseFragment.b();
    }

    @Override // com.huawei.health.suggestion.ui.SearchViewInterface
    public void showLoadMore() {
        this.mSearchRunCourseFragment.e();
    }

    public void setOnQueryTextListener(FitnessSearchFragmentBar.OnQueryTextListener onQueryTextListener) {
        this.mSearchOnClick.a(onQueryTextListener);
    }

    public void setRunLoadMoreListener(FitSearchFragmentRecyclerView.LoadMoreListener loadMoreListener) {
        this.mSearchOnClick.d(loadMoreListener);
    }

    public void setFlowAdapter(FitSearchFragmentFlowLayout.FowAdapter fowAdapter) {
        this.mFitnessSearchFlowLayout.e(fowAdapter);
    }

    @Override // com.huawei.health.suggestion.ui.SearchViewInterface
    public void addData(List<FitWorkout> list) {
        if (koq.c(list)) {
            this.mFitnessSearchFlowLayout.aFQ_(this.mSearchRunCourseFragment);
            this.mSearchNoShow.setVisibility(8);
        } else if (this.mSearchRunCourseFragment.a() == 0) {
            this.mSearchNoShow.setVisibility(0);
        }
        this.mSearchRunCourseFragment.b(gds.b(list));
    }

    @Override // com.huawei.health.suggestion.ui.SearchViewInterface
    public void showSearchNoShow() {
        View view = this.mSearchNoShow;
        if (view != null) {
            view.setVisibility(0);
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
