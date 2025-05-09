package com.huawei.wear.wallet.ui.dialog;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;
import java.text.NumberFormat;

/* loaded from: classes9.dex */
public interface HwProgressDialogInterface {
    void cancel();

    void dismiss();

    int getMax();

    int getProgress();

    int getSecondaryProgress();

    Window getWindow();

    void incrementProgressBy(int i);

    void incrementSecondaryProgressBy(int i);

    boolean isShowing();

    boolean onSearchRequested();

    void setCancelable(boolean z);

    void setCanceledOnTouchOutside(boolean z);

    void setIndeterminateDrawable(Drawable drawable);

    void setMax(int i);

    void setMessage(CharSequence charSequence);

    void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener);

    void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener);

    void setOnKeyListener(DialogInterface.OnKeyListener onKeyListener);

    void setOnShowListener(DialogInterface.OnShowListener onShowListener);

    void setProgress(int i);

    void setProgressDrawable(Drawable drawable);

    void setProgressNumberFormat(String str);

    void setProgressPercentFormat(NumberFormat numberFormat);

    void setProgressStyle(int i);

    void setSearchRequestedReturn(boolean z);

    void setSecondaryProgress(int i);

    void setView(View view);

    void show();
}
