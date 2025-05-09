package defpackage;

import android.view.View;
import android.view.ViewGroup;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nqj extends HealthPagerAdapter {
    private final List<View> b = new ArrayList();

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public void a(List<View> list) {
        this.b.clear();
        this.b.addAll(list);
        notifyDataSetChanged();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        return this.b.size();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = this.b.get(i);
        viewGroup.addView(view);
        return view;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
    }
}
