package com.huawei.uikit.hwviewpager.widget;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/* loaded from: classes7.dex */
public abstract class HwFragmentPagerAdapter extends HwPagerAdapter {
    private static final String g = "HwFragmentPagerAdapter";
    private static final boolean h = false;
    private final FragmentManager d;
    private FragmentTransaction e = null;
    private Fragment f = null;

    public HwFragmentPagerAdapter(FragmentManager fragmentManager) {
        this.d = fragmentManager;
    }

    private static String a(int i, long j) {
        return "android:switcher:" + i + ":" + j;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof Fragment) {
            Fragment fragment = (Fragment) obj;
            if (this.e == null) {
                this.e = this.d.beginTransaction();
            }
            this.e.detach(fragment);
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void finishUpdate(ViewGroup viewGroup) {
        FragmentTransaction fragmentTransaction = this.e;
        if (fragmentTransaction != null) {
            fragmentTransaction.commitNowAllowingStateLoss();
            this.e = null;
        }
    }

    public abstract Fragment getItem(int i);

    public long getItemId(int i) {
        return i;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (this.e == null) {
            this.e = this.d.beginTransaction();
        }
        long itemId = getItemId(i);
        Fragment findFragmentByTag = this.d.findFragmentByTag(a(viewGroup.getId(), itemId));
        if (findFragmentByTag != null) {
            this.e.attach(findFragmentByTag);
        } else {
            findFragmentByTag = getItem(i);
            this.e.add(viewGroup.getId(), findFragmentByTag, a(viewGroup.getId(), itemId));
        }
        if (findFragmentByTag != this.f) {
            findFragmentByTag.setMenuVisibility(false);
            findFragmentByTag.setUserVisibleHint(false);
        }
        return findFragmentByTag;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return (obj instanceof Fragment) && ((Fragment) obj).getView() == view;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Parcelable saveState() {
        return null;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        Fragment fragment2 = this.f;
        if (fragment != fragment2) {
            if (fragment2 != null) {
                fragment2.setMenuVisibility(false);
                this.f.setUserVisibleHint(false);
            }
            fragment.setMenuVisibility(true);
            fragment.setUserVisibleHint(true);
            this.f = fragment;
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void startUpdate(ViewGroup viewGroup) {
        if (viewGroup.getId() != -1) {
            return;
        }
        throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
    }
}
