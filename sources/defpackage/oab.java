package defpackage;

import android.app.Activity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.button.HealthButton;

/* loaded from: classes6.dex */
public class oab {
    private static int b = 15;
    private static int e = 9;

    public static void cTB_(final HealthButton healthButton, final Activity activity) {
        final int[] iArr = {b};
        for (int i = 0; i < b - e; i++) {
            healthButton.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: oab.5
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (HealthButton.this.getLineCount() > 1) {
                        WindowManager windowManager = activity.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR) instanceof WindowManager ? (WindowManager) activity.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR) : null;
                        if (windowManager != null) {
                            int width = windowManager.getDefaultDisplay().getWidth();
                            ViewGroup.LayoutParams layoutParams = HealthButton.this.getLayoutParams();
                            if (layoutParams.width == width) {
                                int[] iArr2 = iArr;
                                int i2 = iArr2[0] - 1;
                                iArr2[0] = i2;
                                HealthButton.this.setTextSize(1, i2);
                                return;
                            }
                            layoutParams.width = width - nrr.e(activity, 64.0f);
                            HealthButton.this.setLayoutParams(layoutParams);
                            return;
                        }
                        return;
                    }
                    HealthButton.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }
}
