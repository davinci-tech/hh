package defpackage;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsocialshare.view.EditShareCustomFragment;
import com.huawei.pluginsocialshare.view.ShareItemFragment;
import com.huawei.ui.commonui.viewpager.HealthFragmentStatePagerAdapter;
import java.util.List;

/* loaded from: classes6.dex */
public class mue extends HealthFragmentStatePagerAdapter {
    private List<fdz> b;
    private Fragment[] e;

    public mue(FragmentManager fragmentManager, List<fdz> list) {
        super(fragmentManager);
        if (!koq.b(list)) {
            this.b = list;
            this.e = new Fragment[list.size()];
        } else {
            LogUtil.h("Share_ShareFragmentPagerAdapter", "ShareFragmentPagerAdapter, shareEditContents is empty");
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        List<fdz> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwFragmentStatePagerAdapter
    public Fragment getItem(int i) {
        if (i < 0 || i >= getCount()) {
            return null;
        }
        Fragment fragment = this.e[i];
        if (fragment == null) {
            if (this.b.get(i).h()) {
                fragment = new EditShareCustomFragment();
            } else {
                fragment = ShareItemFragment.a(i);
            }
            this.e[i] = fragment;
        }
        return fragment;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public CharSequence getPageTitle(int i) {
        if (koq.b(this.b, i)) {
            return null;
        }
        return this.b.get(i).j();
    }
}
