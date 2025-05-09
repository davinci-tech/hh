package defpackage;

import android.view.View;
import android.view.ViewGroup;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import java.util.List;

/* loaded from: classes7.dex */
public class rvl extends HealthPagerAdapter {
    private List<View> e;

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public rvl(List<View> list) {
        this.e = list;
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
        View view = this.e.get(i);
        if (viewGroup != null) {
            viewGroup.addView(view, 0);
        }
        return view;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        List<View> list = this.e;
        if (list == null || viewGroup == null) {
            return;
        }
        viewGroup.removeView(list.get(i));
    }
}
