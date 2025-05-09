package com.huawei.ui.main.stories.fitness.base;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import com.huawei.health.R;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.nsy;

/* loaded from: classes9.dex */
public abstract class BaseFitnessDetailActivity extends BaseActivity {
    public static final int UPDATE_DAY_UI = 0;
    public static final int UPDATE_MONTH_UI = 2;
    public static final int UPDATE_WEEK_UI = 1;
    public static final int UPDATE_YEAR_UI = 3;
    public static final int VIEW_PAGE_SCROLL_HEIGHT = 200;
    protected View mShareView;
    protected HealthSubTabWidget mSubTabWidget;
    private CustomTitleBar mTitleBar;
    protected HealthViewPager mViewPager = null;

    protected int getContentView() {
        return R.layout.activity_fitness_detail;
    }

    protected abstract void initTitleBar();

    protected abstract void initViewPager();

    public CustomTitleBar getCustomTitleBar() {
        return this.mTitleBar;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getContentView());
        initView();
    }

    protected void initView() {
        this.mSubTabWidget = (HealthSubTabWidget) nsy.cMc_(this, R.id.fitness_detail_sub_tab_layout);
        this.mTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.fitness_detail_titlebar);
        this.mViewPager = (HealthViewPager) nsy.cMc_(this, R.id.fitness_detail_viewpager);
        initTitleBar();
        initViewPager();
        HealthViewPager healthViewPager = this.mViewPager;
        if (healthViewPager != null) {
            healthViewPager.setScrollHeightArea(200);
        }
    }

    protected void initShareView() {
        if (this.mShareView == null) {
            this.mShareView = ((ViewStub) findViewById(R.id.share_view_stub)).inflate();
        }
    }

    public HealthViewPager getViewPager() {
        return this.mViewPager;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }
}
