package defpackage;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes4.dex */
public class frt {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f12629a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private Context e;
    private View f;
    private ViewGroup g;
    private HealthTextView h;

    public frt(View view) {
        if (view == null) {
            return;
        }
        this.f = view;
        this.e = view.getContext();
        this.f12629a = (HealthTextView) view.findViewById(R.id.sug_coach_duration);
        this.h = (HealthTextView) view.findViewById(R.id.sug_up_level);
        this.d = (HealthTextView) view.findViewById(R.id.sug_fitness_level);
        this.g = (ViewGroup) view.findViewById(R.id.kcals_layout);
        this.c = (HealthTextView) view.findViewById(R.id.sug_fitness_kcals);
        this.b = (HealthTextView) view.findViewById(R.id.sug_fitness_times);
    }

    public void d(FitWorkout fitWorkout) {
        if (this.e == null || fitWorkout == null) {
            return;
        }
        e(fitWorkout);
        b(fitWorkout);
        c(fitWorkout);
    }

    private void b(FitWorkout fitWorkout) {
        if (fitWorkout.acquireMeasurementType() == 0) {
            e(this.f12629a, this.e.getString(R.string._2130844082_res_0x7f0219b2));
            e(this.b, ggk.aMr_(this.e.getApplicationContext(), fitWorkout.acquireDistance()));
        } else {
            e(this.f12629a, this.e.getString(R.string._2130837546_res_0x7f02002a));
            e(this.b, ggk.aMs_(this.e.getApplicationContext(), fitWorkout.acquireDuration()));
        }
    }

    private void c(FitWorkout fitWorkout) {
        float e = fnz.e();
        e(this.c, ffy.awT_(this.e, "\\d|[/]", ffy.b(R.plurals._2130903474_res_0x7f0301b2, (int) (fitWorkout.acquireCalorie() * e), moe.i(moe.b(fitWorkout.acquireCalorie() * e))), R.style.sug_detail_bigsize, R.style.sug_detail_smasize));
        if (fitWorkout.isLongExplainVideoCourse()) {
            aFk_(this.g, 8);
        } else {
            aFk_(this.g, 0);
        }
    }

    private void e(FitWorkout fitWorkout) {
        if (fitWorkout.isLongExplainVideoCourse()) {
            e(this.h, this.e.getString(R.string._2130848744_res_0x7f022be8));
            String b = ffy.b(R.plurals._2130903486_res_0x7f0301be, fitWorkout.acquireUsers(), Integer.valueOf(fitWorkout.acquireUsers()));
            int a2 = ggk.a(b);
            int length = b.length() - ggk.a(new StringBuffer(b).reverse().toString());
            SpannableString spannableString = new SpannableString(b);
            nsi.cKG_(spannableString, a2, length, new ForegroundColorSpan(ContextCompat.getColor(this.e, R.color._2131299238_res_0x7f090ba6)));
            nsi.cKG_(spannableString, a2, length, new TypefaceSpan(this.e.getString(R.string._2130851582_res_0x7f0236fe)));
            nsi.cKG_(spannableString, a2, length, new AbsoluteSizeSpan(this.e.getResources().getDimensionPixelSize(R.dimen._2131365079_res_0x7f0a0cd7)));
            e(this.d, spannableString);
            return;
        }
        e(this.d, ffy.awT_(this.e, "L[1-4]{1}", ggm.d(fitWorkout.acquireDifficulty()), R.style.sug_detail_bigsize, R.style.sug_detail_smasize));
        e(this.h, this.e.getText(R.string._2130848436_res_0x7f022ab4));
    }

    public void a(int i) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.d.getLayoutParams();
        int i2 = -i;
        layoutParams.topMargin = i2;
        layoutParams.bottomMargin = i2;
        this.d.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.b.getLayoutParams();
        layoutParams2.topMargin = i2;
        layoutParams2.bottomMargin = i2;
        this.b.setLayoutParams(layoutParams2);
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.c.getLayoutParams();
        layoutParams3.topMargin = i2;
        layoutParams3.bottomMargin = i2;
        this.c.setLayoutParams(layoutParams3);
    }

    public void b(int i) {
        aFk_(this.f, i);
    }

    public void e() {
        Context context = this.e;
        if (context != null) {
            e(this.d, context.getString(R.string._2130851561_res_0x7f0236e9));
            e(this.c, this.e.getString(R.string._2130851561_res_0x7f0236e9));
            e(this.b, this.e.getString(R.string._2130851561_res_0x7f0236e9));
        }
    }

    private void e(HealthTextView healthTextView, CharSequence charSequence) {
        if (healthTextView != null) {
            healthTextView.setText(charSequence);
        }
    }

    private void aFk_(View view, int i) {
        if (view != null) {
            view.setVisibility(i);
        }
    }
}
