package defpackage;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import health.compact.a.UnitUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class fsg {

    /* renamed from: a, reason: collision with root package name */
    private String f12632a;
    private HealthProgressBar b;
    private HealthTextView c;
    private View d;

    public fsg() {
        e();
    }

    private void e() {
        View inflate = View.inflate(BaseApplication.getContext(), R.layout.layout_progress_time, null);
        this.d = inflate;
        this.c = (HealthTextView) inflate.findViewById(R.id.tv_time);
        this.b = (HealthProgressBar) this.d.findViewById(R.id.time_progress_bar);
        this.d.setVisibility(8);
    }

    public void c(int i) {
        LogUtil.a("Suggestion_ProgressTimeViewHolder", "setTotalTime totalTime = ", Integer.valueOf(i));
        this.b.setMax(i);
        this.f12632a = UnitUtil.a(i / 1000);
    }

    public void e(int i) {
        this.d.setVisibility(0);
        this.b.setSecondaryProgress(i);
        String e = e(BaseApplication.getContext(), R$string.IDS_current_total_time, UnitUtil.a(i / 1000), this.f12632a);
        int b = b(e);
        int length = e.length();
        SpannableString spannableString = new SpannableString(e);
        nsi.cKG_(spannableString, b, length, new ForegroundColorSpan(ContextCompat.getColor(BaseApplication.getContext(), R$color.textColorSecondaryInverse)));
        nsi.cKG_(spannableString, b, length, new TypefaceSpan(BaseApplication.getContext().getString(R$string.textFontFamilyRegular)));
        nsi.cKG_(spannableString, b, length, new AbsoluteSizeSpan(BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131365063_res_0x7f0a0cc7)));
        this.c.setText(spannableString);
    }

    private String e(Context context, int i, Object... objArr) {
        if (context == null) {
            LogUtil.h("Suggestion_ProgressTimeViewHolder", "getString context == null");
            return "";
        }
        return String.format(context.getApplicationContext().getResources().getString(i), objArr);
    }

    private int b(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        Matcher matcher = Pattern.compile("/").matcher(str);
        if (matcher.find()) {
            return matcher.start();
        }
        return -1;
    }

    public void c() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(new ViewGroup.MarginLayoutParams(-2, -2));
        layoutParams.addRule(13);
        this.d.setLayoutParams(layoutParams);
    }

    public void d(String str) {
        if ("NORMAL_MODE".equals(str)) {
            this.d.setBackgroundResource(R$drawable.progress_time_bg);
        } else {
            this.d.setBackgroundResource(R$drawable.progress_time_bg_whitle);
        }
    }

    public View aFH_() {
        return this.d;
    }

    /* synthetic */ void a() {
        this.d.setVisibility(8);
    }

    public void b() {
        this.d.postDelayed(new Runnable() { // from class: fsh
            @Override // java.lang.Runnable
            public final void run() {
                fsg.this.a();
            }
        }, 100L);
    }
}
