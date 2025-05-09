package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class qac extends HealthPagerAdapter {
    private ArrayList<View> c;

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public qac(ArrayList<View> arrayList) {
        new ArrayList(10);
        this.c = arrayList;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        if (koq.b(this.c)) {
            return 0;
        }
        return this.c.size();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (koq.d(this.c, i)) {
            viewGroup.removeView(this.c.get(i));
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (!koq.d(this.c, i)) {
            return null;
        }
        View view = this.c.get(i);
        if (view == null) {
            return view;
        }
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(view);
        }
        viewGroup.addView(view);
        return view;
    }
}
