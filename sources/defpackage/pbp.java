package defpackage;

import android.view.View;
import android.view.ViewGroup;
import com.huawei.uikit.hwviewpager.widget.HwPagerAdapter;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class pbp extends HwPagerAdapter {
    private List<View> d;

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public pbp(List<View> list) {
        this.d = new ArrayList(0);
        if (list == null) {
            return;
        }
        this.d = list;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (i < 0 || i >= this.d.size() || !(viewGroup instanceof HwViewPager)) {
            return;
        }
        ((HwViewPager) viewGroup).removeView(this.d.get(i));
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void finishUpdate(ViewGroup viewGroup) {
        super.finishUpdate(viewGroup);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        List<View> list = this.d;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (!(viewGroup instanceof HwViewPager) || !koq.d(this.d, i)) {
            return null;
        }
        ((HwViewPager) viewGroup).addView(this.d.get(i), 0);
        return this.d.get(i);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void startUpdate(ViewGroup viewGroup) {
        super.startUpdate(viewGroup);
    }
}
