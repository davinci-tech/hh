package defpackage;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes4.dex */
public class frv {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f12630a;
    private HealthTextView b;
    private ImageView c;
    private HealthTextView d;
    private HealthTextView e;
    private HealthTextView f;
    private ImageView g;
    private ImageView h;
    private HealthTextView i;
    private View j;
    private LinearLayout m;

    public frv() {
        d();
    }

    private void d() {
        View inflate = LayoutInflater.from(arx.b()).inflate(R.layout.layout_count_timer_white, (ViewGroup) null);
        this.j = inflate;
        this.m = (LinearLayout) inflate.findViewById(R.id.timer_container);
        this.e = (HealthTextView) this.j.findViewById(R.id.minute_timed_white);
        this.g = (ImageView) this.j.findViewById(R.id.minute_unit_white);
        this.f = (HealthTextView) this.j.findViewById(R.id.second_timed_white);
        this.h = (ImageView) this.j.findViewById(R.id.second_timed_unit_white);
        this.d = (HealthTextView) this.j.findViewById(R.id.goal_time_minute_white);
        this.c = (ImageView) this.j.findViewById(R.id.goal_time_minute_unit_white);
        this.b = (HealthTextView) this.j.findViewById(R.id.goal_second_time_white);
        this.f12630a = (ImageView) this.j.findViewById(R.id.goal_second_time_unit_white);
        this.i = (HealthTextView) this.j.findViewById(R.id.motion_name_white);
    }

    public View aFl_() {
        return this.j;
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.i.setText(str);
    }

    public void a(int i, int i2, boolean z) {
        this.m.setVisibility(0);
        boolean z2 = i >= 60;
        boolean z3 = i2 >= 60;
        if (z) {
            this.f12630a.setVisibility(0);
            this.h.setVisibility(0);
            if (z2) {
                this.e.setVisibility(0);
                this.e.setText(String.valueOf(i / 60));
                this.g.setVisibility(0);
                this.f.setText(String.valueOf(i % 60));
            } else {
                this.e.setVisibility(8);
                this.g.setVisibility(8);
                this.f.setText(String.valueOf(i));
            }
            if (i2 == -1) {
                return;
            }
            if (z3) {
                this.d.setVisibility(0);
                this.d.setText(String.valueOf(i2 / 60));
                this.c.setVisibility(0);
                this.b.setText(String.valueOf(i2 % 60));
                return;
            }
            this.d.setVisibility(8);
            this.c.setVisibility(8);
            this.b.setText(String.valueOf(i2));
            return;
        }
        this.e.setVisibility(8);
        this.g.setVisibility(8);
        this.h.setVisibility(8);
        this.d.setVisibility(8);
        this.c.setVisibility(8);
        this.f12630a.setVisibility(8);
        this.f.setText(String.valueOf(i));
        if (i2 != -1) {
            this.b.setText(String.valueOf(i2));
        }
    }
}
