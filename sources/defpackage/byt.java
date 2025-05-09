package defpackage;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import java.util.List;

/* loaded from: classes3.dex */
public class byt extends HealthPagerAdapter {
    private final List<View> e;

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void finishUpdate(ViewGroup viewGroup) {
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Parcelable saveState() {
        return null;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void startUpdate(ViewGroup viewGroup) {
    }

    public byt(List<View> list) {
        this.e = list;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (!(viewGroup instanceof HealthViewPager) || koq.b(this.e, i)) {
            return;
        }
        ((HealthViewPager) viewGroup).removeView(this.e.get(i));
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        List<View> list = this.e;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (viewGroup instanceof HealthViewPager) {
            viewGroup.addView(this.e.get(i), 0);
        }
        int size = this.e.size() - 1;
        if (i == size) {
            this.e.get(size).setOnClickListener(new View.OnClickListener() { // from class: byx
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        return this.e.get(i);
    }
}
