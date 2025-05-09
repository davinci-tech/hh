package defpackage;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.huawei.ui.commonui.subtab.HealthSubTabListener;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.viewpager.HealthFragmentPagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.uikit.hwsubtab.widget.HwSubTabListener;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nqx extends HealthFragmentPagerAdapter implements HealthSubTabListener {

    /* renamed from: a, reason: collision with root package name */
    private final HealthSubTabWidget f15449a;
    private nqw b;
    private final HealthViewPager d;
    private final List<Fragment> e;

    @Override // com.huawei.ui.commonui.subtab.HealthSubTabListener, com.huawei.uikit.hwsubtab.widget.HwSubTabListener
    public void onSubTabReselected(smy smyVar, FragmentTransaction fragmentTransaction) {
    }

    @Override // com.huawei.ui.commonui.subtab.HealthSubTabListener, com.huawei.uikit.hwsubtab.widget.HwSubTabListener
    public void onSubTabUnselected(smy smyVar, FragmentTransaction fragmentTransaction) {
    }

    public nqx(FragmentActivity fragmentActivity, HealthViewPager healthViewPager, HealthSubTabWidget healthSubTabWidget) {
        super(fragmentActivity.getSupportFragmentManager());
        this.e = new ArrayList(3);
        this.f15449a = healthSubTabWidget;
        this.d = healthViewPager;
        healthViewPager.setAdapter(this);
        nqw nqwVar = new nqw(healthSubTabWidget);
        this.b = nqwVar;
        healthViewPager.addOnPageChangeListener(nqwVar);
    }

    public nqx(FragmentManager fragmentManager, HealthViewPager healthViewPager, HealthSubTabWidget healthSubTabWidget) {
        super(fragmentManager);
        this.e = new ArrayList(3);
        this.f15449a = healthSubTabWidget;
        this.d = healthViewPager;
        healthViewPager.setAdapter(this);
        nqw nqwVar = new nqw(healthSubTabWidget);
        this.b = nqwVar;
        healthViewPager.addOnPageChangeListener(nqwVar);
    }

    public void c(smy smyVar, Fragment fragment, boolean z) {
        if (smyVar == null || fragment == null) {
            LogUtil.c("HealthSubTabAdapter", "Parameter subTab and fragment of method add SubTab should not be null.");
            return;
        }
        if (smyVar.a() == null) {
            smyVar.d(this);
        }
        smyVar.e(fragment);
        this.e.add(fragment);
        notifyDataSetChanged();
        this.f15449a.a(smyVar, z);
    }

    public void a(smy smyVar, int i, Fragment fragment, boolean z) {
        if (smyVar == null || fragment == null) {
            LogUtil.c("HealthSubTabAdapter", "Parameter subTab and fragment of method add SubTab should not be null.");
            return;
        }
        if (smyVar.a() == null) {
            smyVar.d(this);
        }
        smyVar.e(fragment);
        this.e.add(i, fragment);
        this.f15449a.b(smyVar, i, z);
        notifyDataSetChanged();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwFragmentPagerAdapter
    public Fragment getItem(int i) {
        return nsn.c(this.e, i, "HealthSubTabAdapter");
    }

    public void a(int i) {
        if (this.f15449a == null) {
            LogUtil.e("HealthSubTabAdapter", "removeSubTab mSubTabWidget is null");
            return;
        }
        if (koq.d(this.e, i)) {
            smy a2 = this.f15449a.a(i);
            if (a2 != null) {
                a2.e(null);
                a2.d((HwSubTabListener) null);
            }
            this.f15449a.e(i);
            this.e.remove(i);
            notifyDataSetChanged();
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        return this.e.size();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        for (int i = 0; i < this.e.size(); i++) {
            if (obj.equals(this.e.get(i))) {
                return i;
            }
        }
        return -2;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwFragmentPagerAdapter
    public long getItemId(int i) {
        if (koq.b(this.e, i)) {
            return super.getItemId(i);
        }
        if (this.e.get(i) == null) {
            return super.getItemId(i);
        }
        return r0.hashCode();
    }

    @Override // com.huawei.ui.commonui.subtab.HealthSubTabListener, com.huawei.uikit.hwsubtab.widget.HwSubTabListener
    public void onSubTabSelected(smy smyVar, FragmentTransaction fragmentTransaction) {
        Object j = smyVar.j();
        if (j instanceof Fragment) {
            Fragment fragment = (Fragment) j;
            int size = this.e.size();
            for (int i = 0; i < size; i++) {
                if (this.e.get(i) == fragment) {
                    this.d.setCurrentItem(i);
                    return;
                }
            }
        }
    }
}
