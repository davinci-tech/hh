package defpackage;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.uikit.hwviewpager.widget.HwPagerAdapter;
import com.huawei.uikit.hwviewpager.widget.HwViewPager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class qbh extends HwPagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private List<View> f16376a;

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }

    public qbh(List<View> list) {
        this.f16376a = new ArrayList();
        if (koq.b(list)) {
            return;
        }
        this.f16376a = list;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (!(viewGroup instanceof HwViewPager) || i < 0 || i >= this.f16376a.size()) {
            return;
        }
        ((HwViewPager) viewGroup).removeView(this.f16376a.get(i));
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void finishUpdate(ViewGroup viewGroup) {
        super.finishUpdate(viewGroup);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        List<View> list = this.f16376a;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (!(viewGroup instanceof HwViewPager) || !koq.d(this.f16376a, i)) {
            return null;
        }
        ((HwViewPager) viewGroup).addView(this.f16376a.get(i), 0);
        return this.f16376a.get(i);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void startUpdate(ViewGroup viewGroup) {
        super.startUpdate(viewGroup);
    }
}
