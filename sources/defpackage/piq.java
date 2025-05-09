package defpackage;

import android.view.View;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;

/* loaded from: classes6.dex */
public class piq extends HealthPagerAdapter {
    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        return 3;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return false;
    }
}
