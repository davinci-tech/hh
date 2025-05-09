package com.huawei.ui.commonui.viewpager.carouselsviewpager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class CarouselsPagerAdapter extends PagerAdapter {
    private ArrayList<View> d = new ArrayList<>();
    private int e;

    public interface IViewGenerator {
        View inflate(Context context, String str);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public CarouselsPagerAdapter(Context context, ArrayList<String> arrayList, IViewGenerator iViewGenerator) {
        this.e = arrayList.size();
        int i = 0;
        while (true) {
            int i2 = this.e;
            if (i >= i2 * 3) {
                return;
            }
            this.d.add(iViewGenerator.inflate(context, arrayList.get(i % i2)));
            i++;
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.d.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    /* renamed from: cNj_, reason: merged with bridge method [inline-methods] */
    public View instantiateItem(ViewGroup viewGroup, int i) {
        if (koq.b(this.d, i)) {
            LogUtil.a("CarouselsPagerAdapter", "position is invalid");
            return null;
        }
        View view = this.d.get(i);
        viewGroup.addView(view);
        return view;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
    }
}
