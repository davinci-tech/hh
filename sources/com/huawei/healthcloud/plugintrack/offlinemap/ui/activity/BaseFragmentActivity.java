package com.huawei.healthcloud.plugintrack.offlinemap.ui.activity;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public abstract class BaseFragmentActivity extends BaseUiActivity {
    private static final int TAB_START = 0;
    private static final String TAG = "common.ui.BaseFragmentActivity";
    private ViewPager mListViewPager;
    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private List<View> mTab = null;
    private List<Fragment> mFragmentList = null;
    private int mTabCount = 0;

    public interface FragmentActivityModel {
        Fragment getFragment();

        List<TabFragmentModel> getTabList();

        int getType();

        int getViewPageId();
    }

    public interface TabFragmentModel {
        Fragment getFragment();

        int getTabNameStringId();

        int getTabTextViewId();

        int getTabViewId();
    }

    public abstract FragmentActivityModel getFragmentStructure();

    @Override // com.huawei.healthcloud.plugintrack.offlinemap.ui.activity.BaseUiActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initializeActivity();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mListViewPager = null;
        this.mFragmentList = null;
    }

    public void setTab(int i) {
        LogUtil.c(TAG, "setTab() enter ");
        if (this.mTab != null) {
            LogUtil.c(TAG, "setTab() mTab != null ");
            for (int i2 = 0; i2 < this.mTab.size(); i2++) {
                if (i2 == i) {
                    this.mTab.get(i2).setEnabled(false);
                } else {
                    this.mTab.get(i2).setEnabled(true);
                }
            }
        }
        if (this.mListViewPager != null) {
            LogUtil.c(TAG, "setTab() seleted : ", Integer.valueOf(i));
            this.mListViewPager.setCurrentItem(i);
        } else {
            LogUtil.h(TAG, "setTab() mListViewPager is null");
        }
    }

    private void initializeActivity() {
        LogUtil.a(TAG, "initializeActivity() ");
        FragmentActivityModel fragmentStructure = getFragmentStructure();
        if (fragmentStructure == null) {
            LogUtil.h(TAG, "initializeActivity model is null");
            finish();
            return;
        }
        int type = fragmentStructure.getType();
        if (type == 0) {
            if (fragmentStructure.getFragment() == null) {
                LogUtil.h(TAG, "initializeActivity model.getFragment() is null");
                finish();
                return;
            } else {
                initFragment(fragmentStructure.getFragment(), fragmentStructure.getViewPageId());
                return;
            }
        }
        if (type == 1) {
            if (fragmentStructure.getTabList() == null) {
                LogUtil.h(TAG, "initializeActivity model.getTabList() is null");
                finish();
                return;
            } else {
                initTabFragment(fragmentStructure.getViewPageId(), fragmentStructure.getTabList());
                return;
            }
        }
        LogUtil.h(TAG, "initializeActivity invalid type");
        finish();
    }

    private void initFragment(Fragment fragment, int i) {
        FragmentManager fragmentManager = this.mFragmentManager;
        if (fragmentManager == null) {
            return;
        }
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(i, fragment);
        beginTransaction.commit();
    }

    private void initTabFragment(int i, List<TabFragmentModel> list) {
        LogUtil.c(TAG, "initTabFragment modelList : ", list);
        if (list != null) {
            int size = list.size();
            this.mTabCount = size;
            if (size > 0) {
                this.mTab = new ArrayList(10);
                initTab(list);
                initTabText(list);
                initViewPager(i, list);
                LogUtil.c(TAG, "initTabFragment end");
                return;
            }
            LogUtil.h(TAG, "initTabFragment modelList.size() is 0");
            return;
        }
        LogUtil.h(TAG, "initTabFragment modelList is null");
    }

    private void initViewPager(int i, List<TabFragmentModel> list) {
        View findViewById = findViewById(i);
        if (findViewById instanceof ViewPager) {
            this.mListViewPager = (ViewPager) findViewById;
        }
        ViewPager viewPager = this.mListViewPager;
        if (viewPager != null) {
            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.ui.activity.BaseFragmentActivity.4
                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i2) {
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageScrolled(int i2, float f, int i3) {
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageSelected(int i2) {
                    BaseFragmentActivity.this.setTab(i2);
                }
            });
            this.mFragmentList = new ArrayList(10);
            Iterator<TabFragmentModel> it = list.iterator();
            while (it.hasNext()) {
                this.mFragmentList.add(it.next().getFragment());
            }
            this.mListViewPager.setAdapter(new e(getSupportFragmentManager(), this.mFragmentList));
            this.mListViewPager.setOffscreenPageLimit(this.mTabCount);
            setTab(0);
            return;
        }
        LogUtil.h(TAG, "initTabFragment mListViewPager is null");
        throw new ClassCastException(toString() + " mListViewPager is null!");
    }

    private void initTabText(List<TabFragmentModel> list) {
        HealthTextView healthTextView = null;
        for (TabFragmentModel tabFragmentModel : list) {
            View findViewById = findViewById(tabFragmentModel.getTabTextViewId());
            if (findViewById instanceof HealthTextView) {
                healthTextView = (HealthTextView) findViewById;
            }
            if (healthTextView != null) {
                healthTextView.setText(tabFragmentModel.getTabNameStringId());
            }
        }
    }

    private void initTab(List<TabFragmentModel> list) {
        for (int i = 0; i < this.mTabCount; i++) {
            View findViewById = findViewById(list.get(i).getTabViewId());
            if (findViewById != null) {
                findViewById.setVisibility(0);
                findViewById.setTag(Integer.valueOf(i));
                findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.offlinemap.ui.activity.BaseFragmentActivity.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (view != null) {
                            BaseFragmentActivity.this.setTab(((Integer) view.getTag()).intValue());
                        }
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
                this.mTab.add(findViewById);
            }
        }
    }

    static class e extends FragmentPagerAdapter {
        private List<Fragment> d;

        e(FragmentManager fragmentManager, List<Fragment> list) {
            super(fragmentManager);
            this.d = list;
        }

        @Override // androidx.fragment.app.FragmentPagerAdapter
        public Fragment getItem(int i) {
            if (koq.b(this.d, i)) {
                return null;
            }
            Fragment fragment = koq.b(this.d) ? null : this.d.get(i);
            LogUtil.a(BaseFragmentActivity.TAG, "getItem() position : ", Integer.valueOf(i), "    fragment : ", fragment);
            return fragment;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            List<Fragment> list = this.d;
            if (list == null) {
                return 0;
            }
            return list.size();
        }
    }
}
