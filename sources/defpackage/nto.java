package defpackage;

import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.huawei.ui.commonui.viewpager.HealthFragmentStatePagerAdapter;
import java.util.List;

/* loaded from: classes6.dex */
public class nto extends HealthFragmentStatePagerAdapter {
    private List<Fragment> b;

    @Override // com.huawei.uikit.hwviewpager.widget.HwFragmentStatePagerAdapter, com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Parcelable saveState() {
        return null;
    }

    public nto(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.b = list;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwFragmentStatePagerAdapter
    public Fragment getItem(int i) {
        return nsn.c(this.b, i, "HealthFragmentStatePagerSimpleAdapter");
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        return this.b.size();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        for (int i = 0; i < this.b.size(); i++) {
            if (obj.equals(this.b.get(i))) {
                return i;
            }
        }
        return -2;
    }
}
