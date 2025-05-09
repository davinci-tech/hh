package defpackage;

import android.app.Activity;
import android.content.Context;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import health.compact.a.MagicBuild;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class gow {
    private Context b;
    private LinearLayout c;

    public gow(Context context, ViewStub viewStub) {
        this.b = context;
        aRf_(context, viewStub);
    }

    private void aRf_(Context context, ViewStub viewStub) {
        if (viewStub == null) {
            LogUtil.a("StartPageHelper", "init startPage failed, viewstub is null");
            return;
        }
        LinearLayout linearLayout = (LinearLayout) viewStub.inflate();
        this.c = linearLayout;
        HealthTextView healthTextView = (HealthTextView) linearLayout.findViewById(R.id.hw_copyrights);
        healthTextView.setText(sds.a());
        HealthTextView healthTextView2 = (HealthTextView) this.c.findViewById(R.id.slogan);
        HealthTextView healthTextView3 = (HealthTextView) this.c.findViewById(R.id.appName);
        if (MagicBuild.f13130a) {
            LogUtil.c("StartPageHelper", "magic system detected");
            healthTextView.setVisibility(8);
            healthTextView3.setText(R.string.IDS_hw_app_name);
        }
        Activity activity = (Activity) context;
        aRe_(activity);
        e();
        if (nsn.r()) {
            healthTextView2.setTextSize(1, 28.0f);
            healthTextView3.setTextSize(1, 42.0f);
            healthTextView.setTextSize(1, 21.0f);
        }
        if (activity.isInMultiWindowMode() && (healthTextView2.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            ((ConstraintLayout.LayoutParams) healthTextView2.getLayoutParams()).bottomMargin = context.getResources().getDimensionPixelOffset(R.dimen._2131363052_res_0x7f0a04ec);
        }
    }

    public void a(int i) {
        ReleaseLogUtil.e("R_StartPageHelper", "changeVisibility, visibility:", Integer.valueOf(i));
        if (i == 0) {
            LogUtil.c("StartPageHelper", "show start page");
        } else {
            LogUtil.c("StartPageHelper", "dismiss start page");
        }
        LinearLayout linearLayout = this.c;
        if (linearLayout == null) {
            LogUtil.a("StartPageHelper", "warning: view not inited");
        } else {
            linearLayout.setVisibility(i);
        }
    }

    private void aRe_(Activity activity) {
        if (CommonUtil.as() && CommonUtil.y(activity)) {
            ImageView imageView = (ImageView) activity.findViewById(R.id.betaDemoView);
            imageView.setVisibility(0);
            imageView.setImageResource(R.mipmap._2131820819_res_0x7f110113);
            LogUtil.c("StartPageHelper", "beta version and china rom");
            return;
        }
        if (CommonUtil.bu()) {
            ImageView imageView2 = (ImageView) activity.findViewById(R.id.betaDemoView);
            imageView2.setVisibility(0);
            imageView2.setImageResource(R.mipmap._2131820879_res_0x7f11014f);
            LogUtil.c("StartPageHelper", "store demo version");
        }
    }

    public void e() {
        LinearLayout linearLayout = this.c;
        if (linearLayout == null || linearLayout.getVisibility() != 0) {
            return;
        }
        this.c.findViewById(R.id.hw_health_start_page).setBackgroundResource(R.drawable._2131431547_res_0x7f0b107b);
    }

    public void d() {
        LinearLayout linearLayout = this.c;
        if (linearLayout != null) {
            HealthTextView healthTextView = (HealthTextView) linearLayout.findViewById(R.id.slogan);
            if (((Activity) this.b).isInMultiWindowMode() && (healthTextView.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
                LogUtil.c("StartPageHelper", "multi window activated, adjust slogan...");
                ((ConstraintLayout.LayoutParams) healthTextView.getLayoutParams()).bottomMargin = this.b.getResources().getDimensionPixelOffset(R.dimen._2131363052_res_0x7f0a04ec);
            }
        }
    }
}
