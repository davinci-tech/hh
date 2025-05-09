package defpackage;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.viewpager.HealthFragmentPagerAdapter;
import java.util.List;

/* loaded from: classes6.dex */
public class pft extends HealthFragmentPagerAdapter {
    private List<Fragment> b;
    private final String[] c;

    public pft(FragmentManager fragmentManager, List<Fragment> list, String[] strArr) {
        super(fragmentManager);
        this.b = list;
        if (strArr != null) {
            String[] strArr2 = new String[strArr.length];
            this.c = strArr2;
            System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
            return;
        }
        this.c = null;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public CharSequence getPageTitle(int i) {
        String[] strArr = this.c;
        return (strArr == null || i < 0 || i >= strArr.length) ? "" : strArr[i];
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwFragmentPagerAdapter
    public Fragment getItem(int i) {
        if (koq.b(this.b, i)) {
            LogUtil.h("DiscoverViewpagerAdapter", "mFragments is out of bounds.");
            return null;
        }
        return this.b.get(i);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        return this.b.size();
    }
}
