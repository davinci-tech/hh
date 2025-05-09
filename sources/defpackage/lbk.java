package defpackage;

import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.huawei.ui.commonui.viewpager.HealthFragmentStatePagerAdapter;
import java.util.List;

/* loaded from: classes5.dex */
public class lbk extends HealthFragmentStatePagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private List<Fragment> f14744a;

    @Override // com.huawei.uikit.hwviewpager.widget.HwFragmentStatePagerAdapter, com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Parcelable saveState() {
        return null;
    }

    public lbk(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.f14744a = list;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwFragmentStatePagerAdapter
    public Fragment getItem(int i) {
        List<Fragment> list = this.f14744a;
        if (list == null || i < 0 || i >= list.size()) {
            return null;
        }
        return this.f14744a.get(i);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        return this.f14744a.size();
    }
}
