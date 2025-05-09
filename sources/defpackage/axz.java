package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.basichealthmodel.ui.view.HealthWeekCloverView;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes8.dex */
public class axz extends HealthPagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f278a;
    private List<HealthWeekCloverView> d;
    private LinkedList<View> e = new LinkedList<>();

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public axz(Context context, List<HealthWeekCloverView> list) {
        this.f278a = context;
        this.d = list;
    }

    public LinkedList<View> c() {
        return this.e;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        List<HealthWeekCloverView> list = this.d;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof View) {
            if (obj instanceof HealthWeekCloverView) {
                LogUtil.a("HealthLife_HealthCloverPageAdapter", "destroyItem object instanceof HealthWeekCloverView position = ", Integer.valueOf(i));
                ((HealthWeekCloverView) obj).a();
            }
            View view = (View) obj;
            this.e.add(view);
            viewGroup.removeView(view);
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (koq.b(this.d) || koq.b(this.d, i)) {
            return null;
        }
        HealthWeekCloverView healthWeekCloverView = this.d.get(i);
        if (healthWeekCloverView.getParent() instanceof ViewGroup) {
            LogUtil.h("HealthLife_HealthCloverPageAdapter", "child has a parent must reset child and viewList");
            if (this.f278a == null) {
                this.f278a = BaseApplication.getContext();
            }
            HealthWeekCloverView healthWeekCloverView2 = new HealthWeekCloverView(this.f278a);
            this.d.set(i, healthWeekCloverView2);
            viewGroup.addView(healthWeekCloverView2);
            return healthWeekCloverView2;
        }
        viewGroup.addView(healthWeekCloverView);
        return healthWeekCloverView;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        List<HealthWeekCloverView> list = this.d;
        if (list == null) {
            return -2;
        }
        if (list.contains(obj)) {
            return this.d.indexOf(obj);
        }
        return -1;
    }
}
