package com.huawei.uikit.hwsubtab.widget;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import defpackage.smy;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class HwSubTabFragmentPagerAdapter extends FragmentPagerAdapter implements HwSubTabListener, ViewPager.OnPageChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private int f10750a;
    private final ViewPager b;
    private final ArrayList<d> c;
    private boolean d;
    private final HwSubTabWidget e;

    static final class d {
        private Fragment c;
    }

    private void d(smy smyVar) {
        Object j = smyVar.j();
        if (j instanceof d) {
            d dVar = (d) j;
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                if (this.c.get(i) == dVar) {
                    notifyDataSetChanged();
                    this.b.setCurrentItem(i);
                    return;
                }
            }
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.c.size();
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        int size = this.c.size();
        if (i < 0 || i >= size) {
            return null;
        }
        return this.c.get(i).c;
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        if (i == 0) {
            this.e.setIsViewPagerScroll(false);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        if (this.d) {
            this.e.setIsViewPagerScroll(true);
            this.e.setSubTabScrollingOffsets(i, f);
        }
        if (f == 0.0f && this.f10750a == this.b.getCurrentItem()) {
            this.d = true;
            this.e.setIsViewPagerScroll(false);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        this.e.setSubTabSelected(i);
    }

    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabListener
    public void onSubTabReselected(smy smyVar, FragmentTransaction fragmentTransaction) {
    }

    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabListener
    public void onSubTabSelected(smy smyVar, FragmentTransaction fragmentTransaction) {
        if (this.e.getSubTabAppearance() == 1) {
            this.d = false;
            this.f10750a = smyVar.e();
        }
        d(smyVar);
    }

    @Override // com.huawei.uikit.hwsubtab.widget.HwSubTabListener
    public void onSubTabUnselected(smy smyVar, FragmentTransaction fragmentTransaction) {
    }
}
