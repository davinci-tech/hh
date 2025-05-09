package com.huawei.uikit.hwviewpager.widget;

import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.amap.api.col.p0003sl.it;
import java.util.ArrayList;
import java.util.Set;

/* loaded from: classes7.dex */
public abstract class HwFragmentStatePagerAdapter extends HwPagerAdapter {
    private static final String i = "HwFragmentStatePagerAdapt";
    private static final boolean j = false;
    private static final int k = -1;
    private final FragmentManager d;
    private FragmentTransaction e = null;
    private ArrayList<Fragment.SavedState> f = new ArrayList<>();
    private ArrayList<Fragment> g = new ArrayList<>();
    private Fragment h = null;

    public HwFragmentStatePagerAdapter(FragmentManager fragmentManager) {
        this.d = fragmentManager;
    }

    private void a(Parcelable[] parcelableArr) {
        for (Parcelable parcelable : parcelableArr) {
            if (parcelable instanceof Fragment.SavedState) {
                this.f.add((Fragment.SavedState) parcelable);
            }
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
        if (obj instanceof Fragment) {
            Fragment fragment = (Fragment) obj;
            if (this.e == null) {
                this.e = this.d.beginTransaction();
            }
            while (this.f.size() <= i2) {
                this.f.add(null);
            }
            this.f.set(i2, fragment.isAdded() ? this.d.saveFragmentInstanceState(fragment) : null);
            this.g.set(i2, null);
            this.e.remove(fragment);
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

    public abstract Fragment getItem(int i2);

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i2) {
        Fragment.SavedState savedState;
        Fragment fragment;
        if (this.g.size() > i2 && (fragment = this.g.get(i2)) != null) {
            return fragment;
        }
        if (this.e == null) {
            this.e = this.d.beginTransaction();
        }
        Fragment item = getItem(i2);
        if (this.f.size() > i2 && (savedState = this.f.get(i2)) != null) {
            item.setInitialSavedState(savedState);
        }
        while (this.g.size() <= i2) {
            this.g.add(null);
        }
        item.setMenuVisibility(false);
        item.setUserVisibleHint(false);
        this.g.set(i2, item);
        this.e.add(viewGroup.getId(), item);
        return item;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return (obj instanceof Fragment) && ((Fragment) obj).getView() == view;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        Parcelable[] parcelableArr;
        int i2;
        if (parcelable != null && (parcelable instanceof Bundle)) {
            Bundle bundle = (Bundle) parcelable;
            bundle.setClassLoader(classLoader);
            Set<String> set = null;
            try {
                parcelableArr = bundle.getParcelableArray("states");
            } catch (BadParcelableException unused) {
                Log.e(i, "restoreState: get fragmentSavedState failed");
                parcelableArr = null;
            }
            this.f.clear();
            this.g.clear();
            if (parcelableArr != null) {
                a(parcelableArr);
            }
            try {
                set = bundle.keySet();
            } catch (BadParcelableException unused2) {
                Log.e(i, "restoreState: get keys failed");
            }
            if (set == null) {
                return;
            }
            for (String str : set) {
                if (str.startsWith(it.i)) {
                    try {
                        i2 = Integer.parseInt(str.substring(1));
                    } catch (NumberFormatException unused3) {
                        Log.e(i, "restoreState: get Index failed");
                        i2 = -1;
                    }
                    if (i2 != -1) {
                        a(str, i2, this.d.getFragment(bundle, str));
                    }
                }
            }
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Parcelable saveState() {
        Bundle bundle;
        if (this.f.size() > 0) {
            bundle = new Bundle();
            Fragment.SavedState[] savedStateArr = new Fragment.SavedState[this.f.size()];
            this.f.toArray(savedStateArr);
            bundle.putParcelableArray("states", savedStateArr);
        } else {
            bundle = null;
        }
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            Fragment fragment = this.g.get(i2);
            if (fragment != null && fragment.isAdded()) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                this.d.putFragment(bundle, it.i + i2, fragment);
            }
        }
        return bundle;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void setPrimaryItem(ViewGroup viewGroup, int i2, Object obj) {
        Fragment fragment;
        Fragment fragment2;
        if (!(obj instanceof Fragment) || (fragment = (Fragment) obj) == (fragment2 = this.h)) {
            return;
        }
        if (fragment2 != null) {
            fragment2.setMenuVisibility(false);
            this.h.setUserVisibleHint(false);
        }
        fragment.setMenuVisibility(true);
        fragment.setUserVisibleHint(true);
        this.h = fragment;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void startUpdate(ViewGroup viewGroup) {
        if (viewGroup.getId() != -1) {
            return;
        }
        throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
    }

    private void a(String str, int i2, Fragment fragment) {
        if (fragment != null) {
            while (this.g.size() <= i2) {
                this.g.add(null);
            }
            fragment.setMenuVisibility(false);
            this.g.set(i2, fragment);
            return;
        }
        Log.w(i, "Bad fragment at key " + str);
    }
}
