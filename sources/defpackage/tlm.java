package defpackage;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.huawei.wear.wallet.ui.dialog.HwProgressDialogInterface;

/* loaded from: classes9.dex */
public class tlm {
    public static void fbd_(int i, Handler handler) {
        if (i != 1 || handler == null || handler.hasMessages(0)) {
            return;
        }
        handler.sendEmptyMessage(0);
    }

    public static void fbf_(LinearLayout linearLayout, LinearLayout linearLayout2, View view) {
        linearLayout.setPadding(0, 0, 0, 0);
        if (linearLayout2 != null) {
            linearLayout2.setPadding(0, 0, 0, 0);
            linearLayout2.removeAllViews();
            linearLayout2.addView(view);
            linearLayout2.setVisibility(0);
        }
    }

    public static void b(HwProgressDialogInterface hwProgressDialogInterface, int i, int i2, int i3, int i4) {
        if (i > 0) {
            hwProgressDialogInterface.setMax(i);
        }
        if (i2 > 0) {
            hwProgressDialogInterface.setProgress(i2);
        }
        if (i3 > 0) {
            hwProgressDialogInterface.setSecondaryProgress(i3);
        }
        if (i4 > 0) {
            hwProgressDialogInterface.incrementProgressBy(i4);
        }
    }

    public static void fbe_(HwProgressDialogInterface hwProgressDialogInterface, int i, Drawable drawable, Drawable drawable2, CharSequence charSequence) {
        if (i > 0) {
            hwProgressDialogInterface.incrementSecondaryProgressBy(i);
        }
        if (drawable != null) {
            hwProgressDialogInterface.setProgressDrawable(drawable);
        }
        if (drawable2 != null) {
            hwProgressDialogInterface.setIndeterminateDrawable(drawable2);
        }
        if (charSequence != null) {
            hwProgressDialogInterface.setMessage(charSequence);
        }
    }

    public static void faZ_(TextView textView, CharSequence charSequence) {
        textView.setVisibility(0);
        textView.setText(charSequence);
    }

    public static int fbb_(ProgressBar progressBar, int i) {
        return progressBar != null ? progressBar.getProgress() : i;
    }

    public static int fbc_(ProgressBar progressBar, int i) {
        return progressBar != null ? progressBar.getSecondaryProgress() : i;
    }

    public static int fba_(ProgressBar progressBar, int i) {
        return progressBar != null ? progressBar.getMax() : i;
    }
}
