package defpackage;

import android.view.View;
import com.huawei.ui.commonui.viewpager.HealthViewPager;

/* loaded from: classes6.dex */
public class mwb implements HealthViewPager.PageTransformer {
    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.PageTransformer
    public void transformPage(View view, float f) {
        view.setAlpha(1.0f - (Math.abs(f) * 0.0f));
    }
}
