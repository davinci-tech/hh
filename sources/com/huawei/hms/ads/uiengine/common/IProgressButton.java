package com.huawei.hms.ads.uiengine.common;

import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.huawei.openalliance.ad.download.app.AppStatus;

/* loaded from: classes4.dex */
public interface IProgressButton {

    public interface ProgressButtonResetListener {
        void onSizeReset(int i, int i2);
    }

    int getCancelBtnHeight(int i);

    int getProgress();

    View getProgressBtn();

    Drawable getProgressDrawable();

    Rect getPromptRect();

    AppStatus getStatus();

    CharSequence getText();

    void incrementProgressBy(int i);

    boolean isFastClick();

    void setCancelBtnHeight(int i);

    void setFixedWidth(boolean z);

    void setFontFamily(String str);

    void setMax(int i);

    void setMaxWidth(int i);

    void setMinWidth(int i);

    void setPaintTypeface(Typeface typeface);

    void setPaintTypeface(Typeface typeface, int i);

    void setProgress(int i);

    void setProgressDrawable(Drawable drawable);

    void setProgressDrawable(Drawable drawable, int i);

    void setResetListener(ProgressButtonResetListener progressButtonResetListener);

    void setResetWidth(boolean z);

    void setShowCancelBtn(boolean z);

    void setText(CharSequence charSequence);

    void setTextColor(int i);

    void setTextInner(CharSequence charSequence, boolean z);

    void setTextSize(float f);

    void updateLayoutHeight();
}
