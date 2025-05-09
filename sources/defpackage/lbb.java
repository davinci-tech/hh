package defpackage;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class lbb implements HealthViewPager.OnPageChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private int f14740a;
    private LinearLayout b;
    private List<ImageView> d;
    private Context e;

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    public lbb(Context context, LinearLayout linearLayout, int i) {
        this.e = context;
        this.b = linearLayout;
        this.f14740a = i;
        a();
    }

    private void a() {
        this.d = new ArrayList(0);
        for (int i = 0; i < this.f14740a; i++) {
            ImageView imageView = new ImageView(this.e);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(-2, -2));
            layoutParams.leftMargin = 10;
            layoutParams.rightMargin = 10;
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable._2131427596_res_0x7f0b010c);
            } else {
                imageView.setBackgroundResource(R.drawable._2131427595_res_0x7f0b010b);
            }
            this.b.addView(imageView, layoutParams);
            this.d.add(imageView);
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        int i2 = 0;
        while (true) {
            int i3 = this.f14740a;
            if (i2 >= i3) {
                return;
            }
            if (i % i3 == i2) {
                this.d.get(i2).setBackgroundResource(R.drawable._2131427596_res_0x7f0b010c);
            } else {
                this.d.get(i2).setBackgroundResource(R.drawable._2131427595_res_0x7f0b010b);
            }
            i2++;
        }
    }
}
