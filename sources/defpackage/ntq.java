package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class ntq<T extends View> extends HealthPagerAdapter {
    private List<T> e;

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ntq(ArrayList<T> arrayList) {
        new ArrayList();
        this.e = arrayList;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        return this.e.size();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (koq.b(this.e, i)) {
            return null;
        }
        T t = this.e.get(i);
        ViewParent parent = t.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(t);
        }
        viewGroup.addView(t);
        return t;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        for (int i = 0; i < this.e.size(); i++) {
            if (obj.equals(this.e.get(i))) {
                return i;
            }
        }
        return -2;
    }
}
