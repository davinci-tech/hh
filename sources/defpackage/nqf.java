package defpackage;

import android.app.Activity;
import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;

/* loaded from: classes6.dex */
public class nqf {
    private static int[] e = {0, 0, 0, 0, 0};
    private static float[] c = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f};

    public static void c() {
        LogUtil.a("ScrollUtils", "resetData");
        int i = 0;
        while (true) {
            int[] iArr = e;
            if (i >= iArr.length) {
                return;
            }
            iArr[i] = 0;
            c[i] = 0.0f;
            i++;
        }
    }

    public static void d(Context context) {
        if (context instanceof Activity) {
            int identifier = context.getResources().getIdentifier("androidhwext:style/Theme.Emui.NoActionBar", null, null);
            Activity activity = (Activity) context;
            if (identifier == 0 && BaseActivity.isMiui()) {
                BaseActivity.setMiuiStatusBarMode(false, activity);
                return;
            }
            if (identifier == 0 && BaseActivity.isFlyme()) {
                BaseActivity.setMeizuStatusBarDarkIcon(activity.getWindow(), false);
            } else if (identifier == 0) {
                activity.getWindow().getDecorView().setSystemUiVisibility(1024);
            } else {
                activity.getWindow().setStatusBarColor(0);
            }
        }
    }
}
