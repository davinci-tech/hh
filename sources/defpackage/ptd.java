package defpackage;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.huawei.ui.commonui.viewpager.HealthFragmentStatePagerAdapter;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.fragment.QuestionItemFragment;
import java.util.List;

/* loaded from: classes6.dex */
public class ptd extends HealthFragmentStatePagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private final List<QuestionItemFragment> f16255a;

    public ptd(FragmentManager fragmentManager, List<QuestionItemFragment> list) {
        super(fragmentManager);
        this.f16255a = list;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwFragmentStatePagerAdapter
    public Fragment getItem(int i) {
        if (koq.b(this.f16255a, i)) {
            return null;
        }
        return this.f16255a.get(i);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        if (koq.b(this.f16255a)) {
            return 0;
        }
        return this.f16255a.size();
    }
}
