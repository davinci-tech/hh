package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class eaq extends HealthPagerAdapter {
    private ArrayList<View> e;

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public eaq(ArrayList<View> arrayList) {
        new ArrayList(10);
        this.e = arrayList;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        if (koq.b(this.e)) {
            return 0;
        }
        return this.e.size();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (koq.d(this.e, i)) {
            viewGroup.removeView(this.e.get(i));
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (!koq.d(this.e, i)) {
            return null;
        }
        View view = this.e.get(i);
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
