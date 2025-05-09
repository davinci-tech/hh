package defpackage;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class frs {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f12628a;
    private HealthTextView b;
    private ImageView c;
    private HealthTextView d;
    private ImageView e;
    private HealthTextView f;
    private HealthTextView g;
    private ImageView h;
    private View i;
    private ImageView j;
    private LinearLayout l;
    private HealthProgressBar m;
    private HealthTextView n;

    public frs() {
        d();
    }

    private void d() {
        this.i = LayoutInflater.from(arx.b()).inflate(R.layout.layout_count_timer_black, (ViewGroup) null);
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
        marginLayoutParams.bottomMargin = 0;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(marginLayoutParams);
        layoutParams.addRule(12);
        layoutParams.alignWithParent = true;
        layoutParams.bottomMargin = 0;
        this.i.setLayoutParams(layoutParams);
        this.l = (LinearLayout) this.i.findViewById(R.id.timer_container);
        this.b = (HealthTextView) this.i.findViewById(R.id.minute_timed_black);
        this.j = (ImageView) this.i.findViewById(R.id.minute_unit_black);
        this.f = (HealthTextView) this.i.findViewById(R.id.second_timed_black);
        this.h = (ImageView) this.i.findViewById(R.id.second_timed_unit_black);
        this.d = (HealthTextView) this.i.findViewById(R.id.goal_time_minute_black);
        this.c = (ImageView) this.i.findViewById(R.id.goal_time_minute_unit_black);
        this.f12628a = (HealthTextView) this.i.findViewById(R.id.goal_second_time_black);
        this.e = (ImageView) this.i.findViewById(R.id.goal_second_time_unit_black);
        this.n = (HealthTextView) this.i.findViewById(R.id.time_split);
        this.g = (HealthTextView) this.i.findViewById(R.id.motion_name_black);
        this.m = (HealthProgressBar) this.i.findViewById(R.id.timer_progressbar);
    }

    public void c(int i, int i2) {
        View view = this.i;
        if (view == null) {
            LogUtil.h("Suggestion_CountTimerViewHolder", "setMarginStart, mRootView is null");
            return;
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.timer_parent);
        if (!(linearLayout.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LogUtil.h("Suggestion_CountTimerViewHolder", "setMarginStart, timerParent.getLayoutParams() not LinearLayout.LayoutParams");
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        layoutParams.setMarginStart(i + nsn.c(BaseApplication.getContext(), BaseApplication.getContext().getResources().getDimension(R.dimen._2131364736_res_0x7f0a0b80)));
        layoutParams.bottomMargin = i2;
        linearLayout.setLayoutParams(layoutParams);
    }

    public View aFb_() {
        return this.i;
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.g.setText(str);
    }

    public void c(float f, float f2) {
        this.m.setVisibility(0);
        gdn.aLx_(this.m, f, f2, 300);
    }

    public void e() {
        this.m.setVisibility(4);
    }

    public void b(int i) {
        this.m.setProgress(i, true);
    }

    public void e(int i) {
        this.m.setMax(i);
    }

    public int c() {
        return this.m.getMax();
    }

    public void a(int i, int i2, boolean z) {
        this.l.setVisibility(0);
        if (i == -1) {
            this.d.setVisibility(8);
            this.c.setVisibility(8);
            this.f12628a.setVisibility(8);
            this.e.setVisibility(8);
            this.n.setVisibility(8);
            i = i2;
            i2 = -1;
        }
        if (z) {
            this.h.setVisibility(0);
            if (i >= 60) {
                this.b.setVisibility(0);
                d(this.b, i / 60);
                this.j.setVisibility(0);
                d(this.f, i % 60);
            } else {
                this.b.setVisibility(8);
                this.j.setVisibility(8);
                d(this.f, i);
            }
            if (i2 == -1) {
                return;
            }
            this.e.setVisibility(0);
            this.f12628a.setVisibility(0);
            this.n.setVisibility(0);
            if (i2 >= 60) {
                this.d.setVisibility(0);
                d(this.d, i2 / 60);
                this.c.setVisibility(0);
                d(this.f12628a, i2 % 60);
                return;
            }
            this.d.setVisibility(8);
            this.c.setVisibility(8);
            d(this.f12628a, i2);
            return;
        }
        d(i, i2);
    }

    private void d(HealthTextView healthTextView, int i) {
        healthTextView.setText(UnitUtil.e(i, 1, 0));
    }

    private void d(int i, int i2) {
        this.b.setVisibility(8);
        this.j.setVisibility(8);
        this.h.setVisibility(8);
        this.d.setVisibility(8);
        this.c.setVisibility(8);
        this.e.setVisibility(8);
        d(this.f, i);
        if (i2 != -1) {
            this.n.setVisibility(0);
            this.f12628a.setVisibility(0);
            d(this.f12628a, i2);
        }
    }
}
