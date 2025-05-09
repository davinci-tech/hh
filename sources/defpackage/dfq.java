package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes3.dex */
public class dfq {
    public static void c(Context context, String str) {
        if (context == null) {
            LogUtil.a("UiCommonUtil", "toastPrompt context is null");
            return;
        }
        Resources resources = context.getResources();
        if (resources != null) {
            HealthTextView healthTextView = new HealthTextView(context);
            healthTextView.setBackground(resources.getDrawable(R.drawable._2131427857_res_0x7f0b0211));
            healthTextView.setTextColor(resources.getColor(R.color._2131296937_res_0x7f0902a9));
            healthTextView.setTextSize(1, 12.0f);
            healthTextView.setText(str);
            healthTextView.setGravity(17);
            Toast toast = new Toast(context);
            toast.setView(healthTextView);
            toast.setDuration(0);
            toast.show();
            LogUtil.a("UiCommonUtil", "toastPrompt toast is showing");
        }
    }

    public static void e(Context context, int i) {
        if (context == null) {
            LogUtil.a("UiCommonUtil", "toastPrompt context is null");
            return;
        }
        Resources resources = context.getResources();
        if (resources == null) {
            LogUtil.a("UiCommonUtil", "toastPrompt context.getResources() is null");
        } else {
            c(context, resources.getString(i));
        }
    }
}
