package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import com.huawei.ui.main.R$string;
import health.compact.a.util.LogUtil;

/* loaded from: classes8.dex */
public class qgd extends HealthPagerAdapter {
    private final Context e = BaseApplication.getContext();

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        return 4;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View inflate = View.inflate(this.e, R.layout.light_fasting_status_item_view, null);
        dCB_(i, inflate);
        viewGroup.addView(inflate);
        return inflate;
    }

    private void dCB_(int i, View view) {
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.light_fasting_status_title);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.light_fasting_status_time);
        HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.light_fasting_current_phase);
        HealthTextView healthTextView4 = (HealthTextView) view.findViewById(R.id.light_fasting_status_desc);
        ImageView imageView = (ImageView) view.findViewById(R.id.light_fasting_state_bg);
        if (i == 0) {
            healthTextView.setText(R$string.IDS_fasting_status_increased);
            healthTextView2.setText(this.e.getString(R$string.IDS_light_fasting_time, 0, 4));
            healthTextView4.setText(this.e.getString(R$string.IDS_wl_food_b_g_increased_d, 10, 20, 4));
            imageView.setImageResource(R.mipmap._2131821220_res_0x7f1102a4);
        } else if (i == 1) {
            healthTextView.setText(R$string.IDS_fasting_status_decreased);
            healthTextView2.setText(this.e.getString(R$string.IDS_light_fasting_time, 4, 8));
            healthTextView4.setText(R$string.IDS_wl_food_b_g_decreased_d);
            imageView.setImageResource(R.mipmap._2131821222_res_0x7f1102a6);
        } else if (i == 2) {
            healthTextView.setText(R$string.IDS_fasting_status_maintenance);
            healthTextView2.setText(this.e.getString(R$string.IDS_light_fasting_time, 8, 12));
            healthTextView4.setText(R$string.IDS_wl_food_b_g_maintenance_d);
            imageView.setImageResource(R.mipmap._2131821221_res_0x7f1102a5);
        } else if (i == 3) {
            healthTextView.setText(R$string.IDS_fasting_status_phase);
            healthTextView2.setText(this.e.getString(R$string.IDS_light_fasting_phase_time, 12));
            healthTextView4.setText(R$string.IDS_wl_food_f_b_phase_d);
            imageView.setImageResource(R.mipmap._2131821219_res_0x7f1102a3);
        }
        c(healthTextView3, i + 1);
    }

    private void c(HealthTextView healthTextView, int i) {
        if (a() == i) {
            healthTextView.setVisibility(0);
        } else {
            healthTextView.setVisibility(8);
        }
    }

    public int a() {
        qvf a2 = qlc.b().a();
        if (a2 == null) {
            LogUtil.c("FastingLiteStatusAdapter", "currentTask is null");
            return 0;
        }
        long currentTimeMillis = System.currentTimeMillis() - a2.b();
        return (currentTimeMillis >= 14400000 ? currentTimeMillis < 28800000 ? 1 : currentTimeMillis < 43200000 ? 2 : 3 : 0) + 1;
    }
}
