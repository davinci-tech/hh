package defpackage;

import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.indicator.HealthGradientIndicator;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class frx {

    /* renamed from: a, reason: collision with root package name */
    private int f12631a;
    private HealthTextView b;
    private HealthTextView c;
    private LinearLayout d;
    private HealthTextView e;
    private HealthGradientIndicator f;
    private LinearLayout g;
    private LinearLayout h;
    private HealthTextView i;
    private HealthTextView j;
    private HealthTextView k;
    private LinearLayout l;
    private LinearLayout m;
    private View n;
    private HealthTextView o;
    private HealthTextView r;
    private LinearLayout s;

    public frx(int i) {
        this.f12631a = i;
        b();
        e();
    }

    private void b() {
        this.n = LayoutInflater.from(arx.b()).inflate(R.layout.layout_equip_sport_data, (ViewGroup) null);
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
        marginLayoutParams.bottomMargin = 0;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(marginLayoutParams);
        layoutParams.addRule(12);
        layoutParams.addRule(14);
        layoutParams.alignWithParent = true;
        layoutParams.bottomMargin = 0;
        layoutParams.width = -1;
        this.n.setLayoutParams(layoutParams);
        this.l = (LinearLayout) this.n.findViewById(R.id.ll_slope);
        this.g = (LinearLayout) this.n.findViewById(R.id.ll_resistance);
        this.m = (LinearLayout) this.n.findViewById(R.id.ll_speed);
        this.s = (LinearLayout) this.n.findViewById(R.id.ll_steps);
        this.h = (LinearLayout) this.n.findViewById(R.id.ll_power);
        this.d = (LinearLayout) this.n.findViewById(R.id.ll_cadence);
        this.c = (HealthTextView) this.n.findViewById(R.id.distance_total);
        this.k = (HealthTextView) this.n.findViewById(R.id.slope);
        this.j = (HealthTextView) this.n.findViewById(R.id.resistance);
        this.o = (HealthTextView) this.n.findViewById(R.id.speed);
        this.r = (HealthTextView) this.n.findViewById(R.id.steps);
        this.i = (HealthTextView) this.n.findViewById(R.id.power);
        this.b = (HealthTextView) this.n.findViewById(R.id.cadence);
        this.f = (HealthGradientIndicator) this.n.findViewById(R.id.gradient_indicator);
        this.e = (HealthTextView) this.n.findViewById(R.id.duration);
        HealthTextView healthTextView = (HealthTextView) this.n.findViewById(R.id.speed_unit);
        HealthTextView healthTextView2 = (HealthTextView) this.n.findViewById(R.id.distance_unit);
        if (UnitUtil.h()) {
            healthTextView.setText(BaseApplication.getContext().getResources().getString(R.string._2130839825_res_0x7f020911));
            healthTextView2.setText(BaseApplication.getContext().getResources().getString(R.string._2130842416_res_0x7f021330));
        }
    }

    private void e() {
        if (this.f12631a == 1) {
            this.l.setVisibility(0);
            this.s.setVisibility(0);
            this.d.setVisibility(0);
            this.g.setVisibility(8);
            this.m.setVisibility(8);
            this.h.setVisibility(8);
        }
    }

    public void e(double d) {
        String quantityString;
        Resources resources = BaseApplication.getContext().getResources();
        if (resources == null) {
            LogUtil.b("Suggestion_EquipmentSportDataViewHolder", "setDistance resources == null");
            return;
        }
        String e = UnitUtil.e(d, 1, 2);
        if (CommonUtil.c(d)) {
            e = resources.getString(R.string._2130849885_res_0x7f02305d);
        }
        if (UnitUtil.h()) {
            quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903302_res_0x7f030106, (int) d, e);
        } else {
            quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903301_res_0x7f030105, (int) d, e);
        }
        String string = resources.getString(R.string._2130841062_res_0x7f020de6, quantityString);
        HealthTextView healthTextView = this.c;
        if (healthTextView != null) {
            healthTextView.setText(string);
        }
    }

    public void a(String str) {
        d(str, this.j);
    }

    public void c(String str) {
        d(str, this.k);
    }

    public void j(String str) {
        d(str, this.o);
    }

    public void d(String str) {
        d(str, this.i);
    }

    public void g(String str) {
        d(str, this.r);
    }

    public void b(String str) {
        d(str, this.b);
    }

    public void e(String str) {
        d(str, this.e);
    }

    private void d(String str, HealthTextView healthTextView) {
        if (TextUtils.isEmpty(str)) {
            healthTextView.setText(BaseApplication.getContext().getResources().getString(R.string._2130849885_res_0x7f02305d));
        } else {
            healthTextView.setText(str);
        }
    }

    public View aFm_() {
        return this.n;
    }

    public void e(float f, int i, String str, String str2, int i2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Suggestion_EquipmentSportDataViewHolder", "name is empty");
            return;
        }
        String e = UnitUtil.e(f, 1, i2);
        if (f < 1.0E-7f) {
            e = BaseApplication.getContext().getResources().getString(R.string._2130849885_res_0x7f02305d);
        }
        this.f.d(f, e).b(i).d(str).b(str2).e();
    }
}
