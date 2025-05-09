package com.huawei.health.basesport.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LanguageUtil;

/* loaded from: classes3.dex */
public class ControlButtonLayout extends BaseControlButton {
    private IBaseResponseCallback c;
    private ImageView d;

    public ControlButtonLayout(Context context) {
        super(context);
    }

    public ControlButtonLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.huawei.health.basesport.controls.BaseControlButton
    protected void onPauseClickEvents() {
        IBaseResponseCallback iBaseResponseCallback = this.c;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(101, 101);
        }
    }

    @Override // com.huawei.health.basesport.controls.BaseControlButton
    protected void onPlayClickEvents() {
        IBaseResponseCallback iBaseResponseCallback = this.c;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(100, 100);
        }
    }

    @Override // com.huawei.health.basesport.controls.BaseControlButton
    protected void onStopClickEvents() {
        IBaseResponseCallback iBaseResponseCallback = this.c;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(102, 102);
        }
    }

    public void setClickCallback(IBaseResponseCallback iBaseResponseCallback) {
        this.c = iBaseResponseCallback;
    }

    public void setScreenSwitchLength(ImageView imageView, ImageView imageView2) {
        this.d = imageView;
        this.mVoiceIcon = imageView2;
    }

    @Override // com.huawei.health.basesport.controls.BaseControlButton
    public void setMoveLength() {
        int left;
        int width;
        if (this.d == null || this.mVoiceIcon == null) {
            LogUtil.b("Track_ControlButtonLayout", "mScreenSwitch mVoiceIcon view is null");
            return;
        }
        if (LanguageUtil.bc(this.mContext)) {
            left = this.d.getLeft() - this.mVoiceIcon.getRight();
            width = this.mPauseButton.getWidth();
        } else {
            left = this.mVoiceIcon.getLeft() - this.d.getRight();
            width = this.mPauseButton.getWidth();
        }
        this.mMoveLength = ((left - (width * 2)) / 6) + (this.mPauseButton.getWidth() / 2);
    }

    public void a() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.basesport.controls.ControlButtonLayout.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ControlButtonLayout.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ControlButtonLayout.this.showBtnStopAndPlay();
            }
        });
    }

    public void e() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.basesport.controls.ControlButtonLayout.5
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ControlButtonLayout.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ControlButtonLayout.this.foldBtnStopAndPlay();
            }
        });
    }

    public void setHidePauseAndStopButton() {
        this.mHandler.sendEmptyMessage(103);
    }

    public void d() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.basesport.controls.ControlButtonLayout.4
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ControlButtonLayout.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ControlButtonLayout.this.mHandler.sendEmptyMessage(104);
            }
        });
    }

    public CircleProgressButton getStopButton() {
        return this.mStopButton;
    }

    public void setPauseButtonPading() {
        this.mPauseButton.setPadding(0, 0, 0, 0);
    }

    public void setIsNeedMoveVoiceIcon(int i) {
        this.mVoiceIconMoveLength = i;
    }
}
