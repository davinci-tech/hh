package defpackage;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import com.huawei.health.R;
import com.huawei.health.knit.section.listener.HealthPageResTrigger;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import java.util.List;

/* loaded from: classes6.dex */
public class ppt extends HealthPagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private List<String> f16221a;
    private Context b;
    private List<Integer> c;
    private HealthPageResTrigger d;
    private IBaseResponseCallback e;
    private List<String> f;
    private HealthViewPager g;
    private List<String> h;
    private List<String> i;
    private View j;

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ppt(Context context, List<Integer> list, List<String> list2, List<String> list3) {
        if (koq.b(list) || koq.b(list2) || koq.b(list3) || list.size() != list2.size() || list.size() != list3.size()) {
            throw new IllegalArgumentException("data is illegal!");
        }
        this.b = context;
        this.c = list;
        this.i = list2;
        this.f16221a = list3;
    }

    public ppt(Context context, List<Integer> list, List<String> list2, List<String> list3, List<String> list4) {
        this(context, list, list2, list3);
        if (koq.b(list4) || list4.size() != list.size()) {
            throw new IllegalArgumentException("data is illegal!");
        }
        this.f = list4;
    }

    public void d(List<String> list) {
        this.h = list;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        if (koq.b(this.c)) {
            return 0;
        }
        return this.c.size();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        LogUtil.a("BootPagerAdapter", "instantiateItem, index = " + i);
        if (koq.b(this.c, i)) {
            return new Object();
        }
        if (!(viewGroup instanceof HealthViewPager)) {
            return new Object();
        }
        View drS_ = drS_(i);
        if (drS_ == null) {
            return new Object();
        }
        ((HealthViewPager) viewGroup).addView(drS_);
        return drS_;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        super.destroyItem(viewGroup, i, obj);
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
    }

    public void drX_(View view) {
        this.j = view;
    }

    public void a(HealthViewPager healthViewPager) {
        this.g = healthViewPager;
    }

    private View drS_(final int i) {
        if (!koq.d(this.c, i) || !koq.d(this.i, i) || !koq.d(this.f16221a, i)) {
            return null;
        }
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.sleep_boot_page_view_page, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.page_image_view);
        imageView.setImageDrawable(ResourcesCompat.getDrawable(BaseApplication.getContext().getResources(), this.c.get(i).intValue(), null));
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.page_text);
        healthTextView.setText(this.i.get(i));
        HealthButton healthButton = (HealthButton) inflate.findViewById(R.id.page_button);
        healthButton.setText(this.f16221a.get(i));
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: ppw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ppt.this.drU_(i, view);
            }
        });
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.skip_text);
        if (koq.c(this.f)) {
            healthTextView2.setText(this.f.get(i));
            healthTextView2.setVisibility(0);
            healthTextView2.setOnClickListener(new View.OnClickListener() { // from class: ppu
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ppt.this.drV_(view);
                }
            });
        }
        HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.page_title);
        if (koq.c(this.h)) {
            healthTextView3.setText(this.h.get(i));
            healthTextView3.setVisibility(0);
            healthTextView.setTextSize(0, this.b.getResources().getDimension(R.dimen._2131365062_res_0x7f0a0cc6));
        }
        drT_(inflate, i, healthTextView, imageView);
        return inflate;
    }

    /* synthetic */ void drU_(int i, View view) {
        if (i == this.c.size() - 1) {
            View view2 = this.j;
            if (view2 != null) {
                view2.setVisibility(8);
                HealthPageResTrigger healthPageResTrigger = this.d;
                if (healthPageResTrigger != null) {
                    healthPageResTrigger.setBootAdapter(true);
                }
            }
            IBaseResponseCallback iBaseResponseCallback = this.e;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(0, true);
            }
        } else {
            HealthViewPager healthViewPager = this.g;
            if (healthViewPager != null) {
                healthViewPager.setCurrentItem(i + 1);
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void drV_(View view) {
        View view2 = this.j;
        if (view2 != null) {
            view2.setVisibility(8);
        }
        IBaseResponseCallback iBaseResponseCallback = this.e;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(0, true);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    protected void drT_(View view, int i, HealthTextView healthTextView, ImageView imageView) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.float_content);
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        int dimensionPixelSize = this.b.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
        int dimensionPixelSize2 = this.b.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
        int i2 = this.b.getResources().getDisplayMetrics().widthPixels;
        if (i == 0) {
            if (linearLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.addRule(12, -1);
                layoutParams.setMargins(((Integer) safeRegionWidth.first).intValue() + dimensionPixelSize, 0, ((Integer) safeRegionWidth.second).intValue() + dimensionPixelSize2, qrp.a(this.b, 122.0f));
                linearLayout.setLayoutParams(layoutParams);
            }
            if (healthTextView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) healthTextView.getLayoutParams();
                layoutParams2.topMargin = qrp.a(this.b, 4.0f);
                healthTextView.setLayoutParams(layoutParams2);
            }
            drW_(safeRegionWidth, dimensionPixelSize, dimensionPixelSize2, i2, imageView);
            return;
        }
        if (i == this.c.size() - 1) {
            if (linearLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams3.removeRule(15);
                layoutParams3.addRule(10, -1);
                layoutParams3.setMargins(dimensionPixelSize + ((Integer) safeRegionWidth.first).intValue(), qrp.a(this.b, 35.0f), dimensionPixelSize2 + ((Integer) safeRegionWidth.second).intValue(), 0);
                linearLayout.setLayoutParams(layoutParams3);
            }
            if (imageView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                layoutParams4.height = qrp.a(this.b, 324.0f);
                layoutParams4.width = -2;
                layoutParams4.gravity = GravityCompat.END;
                layoutParams4.setMarginEnd(qrp.a(this.b, -12.0f));
                imageView.setLayoutParams(layoutParams4);
                return;
            }
            return;
        }
        if (linearLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams5.removeRule(12);
            layoutParams5.addRule(15, -1);
            layoutParams5.setMargins(((Integer) safeRegionWidth.first).intValue() + dimensionPixelSize, 0, ((Integer) safeRegionWidth.second).intValue() + dimensionPixelSize2, 0);
            linearLayout.setLayoutParams(layoutParams5);
        }
        drW_(safeRegionWidth, dimensionPixelSize, dimensionPixelSize2, i2, imageView);
    }

    protected void drW_(Pair<Integer, Integer> pair, int i, int i2, int i3, ImageView imageView) {
        if (imageView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            int intValue = (((i3 - i) - i2) - ((Integer) pair.first).intValue()) - ((Integer) pair.second).intValue();
            int a2 = qrp.a(this.b, 336.0f);
            boolean z = nsn.ag(this.b) || nsn.l() || nsn.ae(this.b);
            LogUtil.a("BootPagerAdapter", "isWideScreen: ", Boolean.valueOf(z));
            if (z) {
                if (i3 > a2) {
                    intValue = a2;
                }
                layoutParams.width = intValue;
            } else {
                layoutParams.width = intValue;
            }
            layoutParams.gravity = 1;
            imageView.setLayoutParams(layoutParams);
            LogUtil.a("BootPagerAdapter", "width: ", Integer.valueOf(imageView.getWidth()), ", ", Integer.valueOf(layoutParams.width));
        }
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        this.e = iBaseResponseCallback;
    }

    public void c(HealthPageResTrigger healthPageResTrigger) {
        this.d = healthPageResTrigger;
    }
}
