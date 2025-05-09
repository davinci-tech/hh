package com.huawei.healthcloud.plugintrack.runningroute.utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.viewpager.widget.PagerAdapter;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class CoordinatorAdapter extends PagerAdapter {
    private List<View> b;
    private List<String> c;

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public CoordinatorAdapter(ArrayList<View> arrayList, List<String> list) {
        this.b = arrayList;
        this.c = list;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (koq.b(this.b, i)) {
            return;
        }
        viewGroup.removeView(this.b.get(i));
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        List<View> list = this.b;
        int size = list != null ? list.size() : 0;
        LogUtil.a("CoordinatorAdapter", "count: ", Integer.valueOf(size));
        return size;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (koq.b(this.b, i)) {
            return null;
        }
        View view = this.b.get(i);
        ViewParent parent = view.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(view);
        }
        viewGroup.addView(view);
        return view;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int i) {
        return this.c.get(i);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        for (int i = 0; i < this.b.size(); i++) {
            if (obj.equals(this.b.get(i))) {
                return i;
            }
        }
        return -2;
    }
}
