package defpackage;

import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.uikit.hwsubtab.widget.HwSubTabWidget;

/* loaded from: classes6.dex */
public class nqw implements HealthViewPager.OnPageChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private HwSubTabWidget f15448a;

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    public nqw(HwSubTabWidget hwSubTabWidget) {
        this.f15448a = hwSubTabWidget;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        this.f15448a.setSubTabScrollingOffsets(i, f);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        this.f15448a.setSubTabSelected(i);
    }
}
