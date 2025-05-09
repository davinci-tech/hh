package defpackage;

import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import com.huawei.ui.commonui.subtab.HealthSubTabListener;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nqt extends HealthPagerAdapter implements HealthSubTabListener, HealthViewPager.OnPageChangeListener {
    private final List<View> c = new ArrayList(2);
    private final HealthSubTabWidget d;
    private final HealthViewPager e;

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // com.huawei.ui.commonui.subtab.HealthSubTabListener, com.huawei.uikit.hwsubtab.widget.HwSubTabListener
    public void onSubTabReselected(smy smyVar, FragmentTransaction fragmentTransaction) {
    }

    @Override // com.huawei.ui.commonui.subtab.HealthSubTabListener, com.huawei.uikit.hwsubtab.widget.HwSubTabListener
    public void onSubTabUnselected(smy smyVar, FragmentTransaction fragmentTransaction) {
    }

    public nqt(HealthViewPager healthViewPager, HealthSubTabWidget healthSubTabWidget) {
        this.d = healthSubTabWidget;
        this.e = healthViewPager;
        healthViewPager.setAdapter(this);
        healthViewPager.addOnPageChangeListener(this);
    }

    public void cGt_(smy smyVar, View view, boolean z) {
        if (smyVar == null || view == null) {
            LogUtil.c("HealthSubTabViewAdapter", "Parameter subTab and view of method add SubTab should not be null.");
            return;
        }
        if (smyVar.a() == null) {
            smyVar.d(this);
        }
        smyVar.e(view);
        this.c.add(view);
        notifyDataSetChanged();
        this.d.a(smyVar, z);
    }

    public void b() {
        this.c.clear();
        this.d.h();
        notifyDataSetChanged();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        return this.c.size();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (koq.b(this.c, i)) {
            return null;
        }
        View view = this.c.get(i);
        viewGroup.addView(view);
        return view;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        this.d.setSubTabScrollingOffsets(i, f);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        this.d.setSubTabSelected(i);
    }

    @Override // com.huawei.ui.commonui.subtab.HealthSubTabListener, com.huawei.uikit.hwsubtab.widget.HwSubTabListener
    public void onSubTabSelected(smy smyVar, FragmentTransaction fragmentTransaction) {
        Object j = smyVar.j();
        if (j instanceof View) {
            View view = (View) j;
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                if (this.c.get(i) == view) {
                    this.e.setCurrentItem(i);
                    return;
                }
            }
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        for (int i = 0; i < this.c.size(); i++) {
            if (obj.equals(this.c.get(i))) {
                return i;
            }
        }
        return -2;
    }
}
