package defpackage;

import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.huawei.ui.commonui.viewpager.HealthFragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class byu extends HealthFragmentStatePagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private final List<Fragment> f547a;
    private int d;

    @Override // com.huawei.uikit.hwviewpager.widget.HwFragmentStatePagerAdapter, com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Parcelable saveState() {
        return null;
    }

    public byu(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.f547a = new ArrayList();
        this.d = 0;
        c(list);
    }

    public void c(List<Fragment> list) {
        this.f547a.clear();
        if (list != null) {
            this.f547a.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwFragmentStatePagerAdapter
    public Fragment getItem(int i) {
        if (i >= this.f547a.size()) {
            return null;
        }
        return this.f547a.get(i);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        return this.f547a.size();
    }

    public void b(int i) {
        if (i >= 0 && i < this.f547a.size()) {
            this.f547a.get(i).onHiddenChanged(false);
        }
        if (this.d >= 0) {
            int size = this.f547a.size();
            int i2 = this.d;
            if (size > i2) {
                this.f547a.get(i2).onHiddenChanged(true);
            }
        }
        this.d = i;
    }
}
