package com.huawei.watchface.videoedit.ui;

import android.R;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.videoedit.utils.UiType;
import com.huawei.watchface.videoedit.utils.Utils;

/* loaded from: classes9.dex */
public class BaseDefaultContentDialog extends BaseDialogFragment {
    private static final float FADE_IN_ALPHA_BEGIN = 0.1f;
    private static final long FADE_IN_DURATION_MS = 100;
    private static final long FADE_IN_START_DELAY_MS = 200;
    private static final int ORIENTATION_0 = 0;
    private static final int ORIENTATION_180 = 180;
    private static final int ORIENTATION_270 = 270;
    private static final int ORIENTATION_360 = 360;
    private static final int ORIENTATION_90 = 90;
    private static final int PRIVATE_FLAG_NO_MOVE_ANIMATION = 64;
    private static final float RATIO_HALF = 0.5f;
    private static final String TAG = "BDCDialog";
    private int dialogPadding;
    private ValueAnimator fadeInAnimator;
    private int initContentViewHeight;
    private int initContentViewWidth;
    private int initDialogHeight;
    private int initDialogWidth;
    private int initGravity;
    private boolean isFirstCreateDialog = true;
    private DialogListenerInterface mCancelListener;
    private View mContent;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getDialog().setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.huawei.watchface.videoedit.ui.BaseDefaultContentDialog$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                BaseDefaultContentDialog.this.m964x9c2656a2(dialogInterface);
            }
        });
    }

    /* renamed from: lambda$onActivityCreated$0$com-huawei-watchface-videoedit-ui-BaseDefaultContentDialog, reason: not valid java name */
    /* synthetic */ void m964x9c2656a2(DialogInterface dialogInterface) {
        if (this.isFirstCreateDialog) {
            initParams(getDialog());
            this.isFirstCreateDialog = false;
        }
        setRotation(this.mRotation);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        DialogListenerInterface dialogListenerInterface = this.mCancelListener;
        if (dialogListenerInterface != null) {
            dialogListenerInterface.dialogListener();
        }
    }

    public void setOnCancelListener(DialogListenerInterface dialogListenerInterface) {
        this.mCancelListener = dialogListenerInterface;
    }

    private void initParams(Dialog dialog) {
        Window window;
        if (dialog == null || !dialog.isShowing() || (window = dialog.getWindow()) == null || window.getDecorView().getWidth() == 0) {
            return;
        }
        this.mContent = window.findViewById(R.id.content);
        this.initDialogWidth = window.getDecorView().getWidth();
        this.initDialogHeight = window.getDecorView().getHeight();
        this.dialogPadding = window.getDecorView().getPaddingStart() + window.getDecorView().getPaddingEnd();
        View view = this.mContent;
        if (view == null) {
            return;
        }
        this.initContentViewWidth = view.getWidth();
        this.initContentViewHeight = this.mContent.getHeight();
    }

    @Override // com.huawei.watchface.videoedit.ui.BaseDialogFragment
    public void setRotation(int i) {
        if (getDialog() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        if (window == null) {
            HwLog.e("TAG", "setRotation: window = null");
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        try {
            Object obj = attributes.getClass().getField("privateFlags").get(attributes);
            if (obj instanceof Integer) {
                attributes.getClass().getField("privateFlags").set(attributes, Integer.valueOf(((Integer) obj).intValue() | 64));
            }
        } catch (IllegalAccessException | NoSuchFieldException unused) {
            HwLog.e(TAG, "window Layout field get error");
        }
        window.setAttributes(attributes);
        setWindowAlpha(getDialog(), 0.0f);
        ValueAnimator valueAnimator = this.fadeInAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator duration = ValueAnimator.ofFloat(0.1f, 1.0f).setDuration(100L);
        this.fadeInAnimator = duration;
        duration.setStartDelay(200L);
        this.fadeInAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.huawei.watchface.videoedit.ui.BaseDefaultContentDialog$$ExternalSyntheticLambda1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                BaseDefaultContentDialog.this.m965x4adc487b(valueAnimator2);
            }
        });
        this.fadeInAnimator.start();
        setRotation(i, window);
    }

    /* renamed from: lambda$setRotation$1$com-huawei-watchface-videoedit-ui-BaseDefaultContentDialog, reason: not valid java name */
    /* synthetic */ void m965x4adc487b(ValueAnimator valueAnimator) {
        setWindowAlpha(getDialog(), ((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    private void setRotation(final int i, final Window window) {
        int i2;
        int i3;
        int i4;
        View view = this.mContent;
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (i % 180 == 90) {
            int i5 = this.initDialogWidth;
            if (i5 == 0 || (i4 = this.initDialogHeight) == 0 || this.initContentViewWidth == 0 || this.initContentViewHeight == 0) {
                return;
            }
            window.setLayout(Math.min(i4 + this.dialogPadding, i5), this.initDialogWidth - this.dialogPadding);
            layoutParams.width = this.initContentViewWidth;
            layoutParams.height = Math.min(this.initContentViewHeight, this.initContentViewWidth);
            i2 = (int) ((layoutParams.width - layoutParams.height) * 0.5f);
            if (!Utils.isRtl()) {
                i3 = -i2;
                this.mContent.setRotation(i);
                this.mContent.setLayoutParams(layoutParams);
                this.mContent.setTranslationX(i3);
                this.mContent.setTranslationY(i2);
                m966x50e013da(i, window);
                this.mContent.post(new Runnable() { // from class: com.huawei.watchface.videoedit.ui.BaseDefaultContentDialog$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseDefaultContentDialog.this.m966x50e013da(i, window);
                    }
                });
            }
        } else {
            window.setLayout(-2, -2);
            layoutParams.width = -1;
            layoutParams.height = -1;
            i2 = 0;
        }
        i3 = i2;
        this.mContent.setRotation(i);
        this.mContent.setLayoutParams(layoutParams);
        this.mContent.setTranslationX(i3);
        this.mContent.setTranslationY(i2);
        m966x50e013da(i, window);
        this.mContent.post(new Runnable() { // from class: com.huawei.watchface.videoedit.ui.BaseDefaultContentDialog$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BaseDefaultContentDialog.this.m966x50e013da(i, window);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateGravity, reason: merged with bridge method [inline-methods] */
    public void m966x50e013da(int i, Window window) {
        int i2 = i % 360;
        if (window.getAttributes().gravity != 17) {
            this.initGravity = window.getAttributes().gravity;
        }
        if (this.initGravity != 0) {
            if (i2 % 180 == 90 || Utils.getUiType(getActivity().getApplicationContext()) == UiType.TAH_FULL) {
                window.setGravity(17);
            } else if (i2 == 0) {
                window.setGravity(80);
            } else if (i2 == 180) {
                window.setGravity(48);
            } else {
                HwLog.e(TAG, "invalid rotation");
            }
            window.setAttributes(window.getAttributes());
        }
    }

    private void setWindowAlpha(Dialog dialog, float f) {
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            window.getAttributes().alpha = f;
            window.setAttributes(window.getAttributes());
        }
    }
}
