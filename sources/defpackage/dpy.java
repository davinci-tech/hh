package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import java.util.List;

/* loaded from: classes3.dex */
public class dpy extends HealthPagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private List<String> f11775a;
    private Context b;
    private List<String> c;
    private List<String> d;
    private dqb e;
    private List<String> f;
    private List<Integer> g;
    private HealthViewPager i;
    private View j;

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public dpy(Context context, dol dolVar) {
        this.b = context;
        this.g = dolVar.d();
        this.f = dolVar.e();
        this.f11775a = dolVar.c();
        this.c = dolVar.b();
        this.d = dolVar.a();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        if (koq.b(this.g)) {
            return 0;
        }
        return this.g.size();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        LogUtil.a("BootPagerAdapter", "instantiateItem, index = " + i);
        if (koq.b(this.g, i)) {
            return new Object();
        }
        if (!(viewGroup instanceof HealthViewPager)) {
            return new Object();
        }
        View Zs_ = Zs_(i);
        if (Zs_ == null) {
            return new Object();
        }
        ((HealthViewPager) viewGroup).addView(Zs_);
        return Zs_;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        super.destroyItem(viewGroup, i, obj);
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
    }

    public void a(dqb dqbVar) {
        this.e = dqbVar;
    }

    public void Zt_(View view) {
        this.j = view;
    }

    public void a(HealthViewPager healthViewPager) {
        this.i = healthViewPager;
    }

    private View Zs_(int i) {
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.member_guide_view_page, (ViewGroup) null);
        Zq_(inflate, i);
        Zr_(inflate, i);
        Zo_(inflate, i);
        Zp_(inflate, i);
        Zn_(inflate, i);
        Zm_(inflate);
        return inflate;
    }

    private void Zm_(View view) {
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.member_guide_image_layout);
        ViewGroup.LayoutParams layoutParams = constraintLayout.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            int marginStart = layoutParams2.getMarginStart();
            int marginEnd = layoutParams2.getMarginEnd();
            Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
            layoutParams2.setMargins(marginStart + ((Integer) safeRegionWidth.first).intValue(), 0, marginEnd + ((Integer) safeRegionWidth.second).intValue(), 0);
            constraintLayout.setLayoutParams(layoutParams2);
        }
    }

    private void Zq_(View view, int i) {
        if (koq.b(this.g, i)) {
            return;
        }
        ((ImageView) view.findViewById(R.id.page_image_view)).setImageDrawable(ResourcesCompat.getDrawable(BaseApplication.getContext().getResources(), this.g.get(i).intValue(), null));
    }

    private void Zr_(View view, int i) {
        if (koq.b(this.f, i)) {
            return;
        }
        ((HealthTextView) view.findViewById(R.id.member_guide_title)).setText(this.f.get(i));
    }

    private void Zo_(View view, final int i) {
        if (koq.b(this.f11775a, i)) {
            return;
        }
        HealthButton healthButton = (HealthButton) view.findViewById(R.id.member_guide_button);
        d(healthButton);
        healthButton.setText(this.f11775a.get(i));
        if (this.f11775a.get(i).equals(this.b.getString(R$string.IDS_member_center_start_trip))) {
            healthButton.setTextColor(this.b.getColor(R.color._2131299321_res_0x7f090bf9));
        }
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: dpy.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (i == dpy.this.g.size() - 1) {
                    if (dpy.this.e != null) {
                        dpy.this.e.dismiss();
                    }
                } else if (dpy.this.i != null) {
                    dpy.this.i.setCurrentItem(i + 1);
                }
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
    }

    private void d(HealthButton healthButton) {
        ViewGroup.LayoutParams layoutParams = healthButton.getLayoutParams();
        int b = nrr.b(this.b);
        int c = eie.c();
        int f = new HealthColumnSystem(BaseApplication.getContext()).f();
        int i = (c - ((f + 1) * b)) / f;
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            layoutParams2.width = nsn.ag(this.b) ? (i + b) * 2 : i * 3;
            healthButton.setLayoutParams(layoutParams2);
        }
    }

    private void Zp_(View view, int i) {
        if (koq.b(this.c, i)) {
            return;
        }
        nsy.cMs_((HealthTextView) view.findViewById(R.id.member_guide_description), this.c.get(i), true);
    }

    private void Zn_(View view, int i) {
        if (koq.b(this.d, i) || TextUtils.isEmpty(this.d.get(i))) {
            return;
        }
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.member_guide_attention);
        healthTextView.setText(this.d.get(i));
        healthTextView.setVisibility(0);
    }
}
