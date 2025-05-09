package defpackage;

import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.device.ui.measure.view.holder.HealthViewPagerHolder;
import com.huawei.health.device.ui.measure.view.holder.HealthViewPagerHolderCreator;
import com.huawei.uikit.hwviewpager.widget.HwPagerAdapter;
import java.util.List;

/* loaded from: classes3.dex */
public class cmr<T> extends HwPagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private List<T> f789a;
    private HealthViewPagerHolderCreator d;

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public cmr(List<T> list, HealthViewPagerHolderCreator healthViewPagerHolderCreator) {
        this.f789a = list;
        this.d = healthViewPagerHolderCreator;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        List<T> list = this.f789a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View Iq_ = Iq_(i, viewGroup);
        viewGroup.addView(Iq_);
        return Iq_;
    }

    private View Iq_(int i, ViewGroup viewGroup) {
        HealthViewPagerHolder createViewHolder = this.d.createViewHolder();
        View createView = createViewHolder.createView(viewGroup.getContext());
        if (createView != null) {
            createView.setTag(Integer.valueOf(i));
        }
        List<T> list = this.f789a;
        if (list != null && list.size() > 0) {
            createViewHolder.onBind(viewGroup.getContext(), i, this.f789a.get(i));
        }
        return createView;
    }
}
