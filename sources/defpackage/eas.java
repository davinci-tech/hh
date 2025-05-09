package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import java.util.List;

/* loaded from: classes3.dex */
public class eas extends HealthPagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f11932a;
    private List<Object> d;

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public eas(HealthViewPager healthViewPager, Context context) {
        this.f11932a = context;
    }

    public void c(List<Object> list) {
        this.d = list;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        List<Object> list = this.d;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (koq.b(this.d, i)) {
            LogUtil.h("Section16_9Card_01Adapter", "isOutOfBounds");
            return LayoutInflater.from(this.f11932a).inflate(R.layout.section16_9card_01_banner_item, (ViewGroup) null);
        }
        Object obj = this.d.get(i);
        if (!(obj instanceof edu)) {
            return null;
        }
        View acg_ = acg_((edu) obj);
        if (viewGroup instanceof HealthViewPager) {
            viewGroup.addView(acg_);
        }
        return acg_;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (!(obj instanceof View) || koq.b(this.d, i)) {
            return;
        }
        ((HealthViewPager) viewGroup).removeView((View) obj);
    }

    private View acg_(edu eduVar) {
        View inflate = LayoutInflater.from(this.f11932a).inflate(R.layout.section16_9card_01_banner_item, (ViewGroup) null);
        nrf.cIU_(eduVar.b(), (ImageView) inflate.findViewById(R.id.item_background), 0);
        ((HealthTextView) inflate.findViewById(R.id.item_title)).setText(eduVar.d());
        ((HealthTextView) inflate.findViewById(R.id.item_desc)).setText(eduVar.a());
        eem i = eduVar.i();
        if (i != null) {
            HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.item_top_right_text);
            healthTextView.setVisibility(i.b());
            healthTextView.setText(i.a());
            healthTextView.setBackground(i.agU_());
        }
        String c = eduVar.c();
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.item_bottom_right_text);
        if (!TextUtils.isEmpty(c)) {
            healthTextView2.setText(c);
            healthTextView2.setVisibility(0);
        }
        inflate.setOnClickListener(eduVar.agI_());
        return inflate;
    }
}
