package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.viewmodel.IndoorEquipViewModel;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import health.compact.a.UnitUtil;
import java.util.Map;

/* loaded from: classes5.dex */
public class lce {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f14761a;
    private ViewGroup b;
    private HealthTextView c;
    private boolean d;
    private HealthTextView e;
    private final int f;
    private HealthTextView g;
    private HealthProgressBar h;
    private HealthTextView i;
    private final int j;
    private final float l;
    private HealthTextView m;
    private HealthTextView n;
    private HealthTextView o;

    public lce(View view, IndoorEquipViewModel indoorEquipViewModel) {
        this.f = indoorEquipViewModel.getSportTarget();
        this.l = indoorEquipViewModel.getTargetValue();
        this.j = indoorEquipViewModel.getSportType();
        bVT_(view);
    }

    private void bVT_(View view) {
        this.g = (HealthTextView) view.findViewById(R.id.target_real_time_value);
        this.n = (HealthTextView) view.findViewById(R.id.target_type);
        this.h = (HealthProgressBar) view.findViewById(R.id.progressbar_target);
        this.m = (HealthTextView) view.findViewById(R.id.target_value);
        this.o = (HealthTextView) view.findViewById(R.id.target_unit);
        this.i = (HealthTextView) view.findViewById(R.id.sport_type);
        this.b = (ViewGroup) view.findViewById(R.id.ll_heart_rate_container);
        this.f14761a = (HealthTextView) view.findViewById(R.id.center_heart_rate_value);
        this.e = (HealthTextView) view.findViewById(R.id.heart_rate_des);
        this.c = (HealthTextView) view.findViewById(R.id.heart_rate_des_unit);
    }

    public void b(boolean z) {
        this.d = z;
        LogUtil.a("TargetModeWithProgressViewHolder", "init: mTargetType = ", Integer.valueOf(this.f), ", mTargetValue = ", Float.valueOf(this.l), ", mSportType = ", Integer.valueOf(this.j), ", mHasHeartRateDevice = ", Boolean.valueOf(this.d));
        e();
        b();
        a();
    }

    private void e() {
        int i = this.j;
        if (i == 264) {
            this.i.setText(R.string.IDS_start_track_sport_type_run);
        } else {
            if (i != 265) {
                return;
            }
            this.i.setText(R.string._2130842145_res_0x7f021221);
        }
    }

    private void b() {
        double b;
        int i = this.f;
        if (i == 0) {
            this.n.setText(R.string.ie_device_connect_cost);
            this.o.setText(R.string._2130839756_res_0x7f0208cc);
            this.m.setText(UnitUtil.e(this.l / 60.0d, 1, 0));
            return;
        }
        if (i != 1) {
            if (i != 2) {
                return;
            }
            this.n.setText(R.string._2130847442_res_0x7f0226d2);
            this.o.setText(R.string._2130841384_res_0x7f020f28);
            this.m.setText(UnitUtil.e(this.l, 1, 0));
            return;
        }
        boolean h = UnitUtil.h();
        HealthTextView healthTextView = this.n;
        int i2 = R.string._2130844082_res_0x7f0219b2;
        healthTextView.setText(h ? R.string._2130844081_res_0x7f0219b1 : R.string._2130844082_res_0x7f0219b2);
        HealthTextView healthTextView2 = this.o;
        if (h) {
            i2 = R.string._2130844081_res_0x7f0219b1;
        }
        healthTextView2.setText(i2);
        int i3 = (int) this.l;
        if (h) {
            b = jed.c(i3);
        } else {
            b = jed.b(i3);
        }
        this.m.setText(UnitUtil.e(b, 1, 2));
    }

    private void a() {
        if (this.d) {
            this.b.setVisibility(0);
        }
    }

    public void a(int i) {
        this.h.setProgress(i);
    }

    public void d(Map<Integer, Object> map) {
        double b;
        int i = this.f;
        if (i == 0) {
            this.g.setText(UnitUtil.d(a(map, 2)));
        } else if (i == 1) {
            int a2 = a(map, 1);
            if (UnitUtil.h()) {
                b = jed.c(a2);
            } else {
                b = jed.b(a2);
            }
            this.g.setText(UnitUtil.e(b, 1, 2));
        } else if (i == 2) {
            this.g.setText(UnitUtil.e(a(map, 6), 1, 0));
        }
        if (this.d) {
            e(a(map, 20004));
        }
    }

    private void e(int i) {
        if (i <= 0 || i == 255) {
            this.f14761a.setText(R.string._2130851303_res_0x7f0235e7);
        } else {
            this.f14761a.setText(String.valueOf(i));
        }
    }

    private int a(Map<Integer, Object> map, int i) {
        Object obj = map.get(Integer.valueOf(i));
        if (obj == null) {
            return 0;
        }
        return ((Integer) obj).intValue();
    }

    public void e(float f) {
        HealthTextView healthTextView = this.g;
        healthTextView.setTextSize(0, healthTextView.getTextSize() * f);
        HealthTextView healthTextView2 = this.n;
        healthTextView2.setTextSize(0, healthTextView2.getTextSize() * f);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.h.getLayoutParams();
        layoutParams.height = (int) (layoutParams.height * f);
        layoutParams.width = (int) (layoutParams.width * f);
        LogUtil.a("TargetModeWithProgressViewHolder", "scale is ", Float.valueOf(f), "height is ", Integer.valueOf(layoutParams.height), "width is ", Integer.valueOf(layoutParams.width));
        this.h.setLayoutParams(layoutParams);
        HealthTextView healthTextView3 = this.m;
        healthTextView3.setTextSize(0, healthTextView3.getTextSize() * f);
        HealthTextView healthTextView4 = this.o;
        healthTextView4.setTextSize(0, healthTextView4.getTextSize() * f);
        HealthTextView healthTextView5 = this.i;
        healthTextView5.setTextSize(0, healthTextView5.getTextSize() * f);
        HealthTextView healthTextView6 = this.e;
        healthTextView6.setTextSize(0, healthTextView6.getTextSize() * f);
        HealthTextView healthTextView7 = this.f14761a;
        healthTextView7.setTextSize(0, healthTextView7.getTextSize() * f);
        HealthTextView healthTextView8 = this.c;
        healthTextView8.setTextSize(0, f * healthTextView8.getTextSize());
    }
}
