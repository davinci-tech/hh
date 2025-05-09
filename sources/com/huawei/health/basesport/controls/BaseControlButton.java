package com.huawei.health.basesport.controls;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.basesport.controls.BaseControlButton;
import com.huawei.health.basesport.controls.CircleProgressButton;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.mld;
import health.compact.a.LanguageUtil;

/* loaded from: classes3.dex */
public abstract class BaseControlButton extends RelativeLayout {
    private static final int ANIMATION_RTL_TIME = 200;
    private static final int ANIMATION_RTL_TIME_ZERO = 0;
    private static final String PROPERTY_NAME = "translationX";
    private static final String TAG = "Track_BaseControlButton";
    protected Context mContext;
    protected Handler mHandler;
    protected int mMoveLength;
    protected SportControlButton mPauseButton;
    protected SportControlButton mPlayButton;
    protected CircleProgressButton mStopButton;
    protected ImageView mVoiceIcon;
    protected int mVoiceIconMoveLength;

    protected abstract void onPauseClickEvents();

    protected abstract void onPlayClickEvents();

    protected abstract void onStopClickEvents();

    protected abstract void setMoveLength();

    public BaseControlButton(Context context) {
        super(context);
        this.mHandler = new e(Looper.getMainLooper(), this);
        initView(context);
    }

    public BaseControlButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHandler = new e(Looper.getMainLooper(), this);
        initView(context);
    }

    public BaseControlButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHandler = new e(Looper.getMainLooper(), this);
        initView(context);
    }

    public BaseControlButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mHandler = new e(Looper.getMainLooper(), this);
    }

    private void initView(Context context) {
        if (context == null) {
            LogUtil.b(TAG, "context is null");
            return;
        }
        this.mContext = context;
        View.inflate(context, R.layout.control_button_layout, this);
        this.mStopButton = (CircleProgressButton) findViewById(R.id.track_main_page_btn_stop_indoor);
        SportControlButton sportControlButton = (SportControlButton) findViewById(R.id.track_main_page_btn_pause);
        this.mPauseButton = sportControlButton;
        sportControlButton.setOnClickListener(new View.OnClickListener() { // from class: bzx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseControlButton.this.m132xb9c555ee(view);
            }
        });
        SportControlButton sportControlButton2 = (SportControlButton) findViewById(R.id.track_main_page_btn_play);
        this.mPlayButton = sportControlButton2;
        sportControlButton2.setOnClickListener(new View.OnClickListener() { // from class: bzy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BaseControlButton.this.m133xbafba8cd(view);
            }
        });
        this.mStopButton.e(new CircleProgressButton.CircleProcessListener() { // from class: com.huawei.health.basesport.controls.BaseControlButton.3
            @Override // com.huawei.health.basesport.controls.CircleProgressButton.CircleProcessListener
            public void onCancel() {
            }

            @Override // com.huawei.health.basesport.controls.CircleProgressButton.CircleProcessListener
            public void onStarted() {
            }

            @Override // com.huawei.health.basesport.controls.CircleProgressButton.CircleProcessListener
            public void onFinished() {
                BaseControlButton.this.onStopClickEvents();
            }
        });
        this.mPauseButton.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.basesport.controls.BaseControlButton.4
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                BaseControlButton.this.initMoveLength();
                BaseControlButton.this.setMoveLength();
            }
        });
    }

    /* renamed from: lambda$initView$0$com-huawei-health-basesport-controls-BaseControlButton, reason: not valid java name */
    public /* synthetic */ void m132xb9c555ee(View view) {
        onPauseClickEvents();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$initView$1$com-huawei-health-basesport-controls-BaseControlButton, reason: not valid java name */
    public /* synthetic */ void m133xbafba8cd(View view) {
        onPlayClickEvents();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initMoveLength() {
        this.mMoveLength = mld.d(this.mContext, 8.0f) + (this.mPauseButton.getWidth() / 2);
    }

    public void showBtnStopAndPlay() {
        this.mHandler.sendEmptyMessage(101);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hidePauseAndShowPlayBtn(int i) {
        this.mPauseButton.setVisibility(4);
        this.mPlayButton.setVisibility(0);
        this.mStopButton.setVisibility(0);
        LogUtil.a(TAG, "onGlobalLayout");
        if (LanguageUtil.bc(this.mContext)) {
            slideView(0, 0 - this.mMoveLength, i, this.mStopButton, false);
            slideView(0, this.mMoveLength, i, this.mPlayButton, false);
            moveVoiceIcon(0, 0 - this.mVoiceIconMoveLength, i);
        } else {
            slideView(0, this.mMoveLength, i, this.mStopButton, false);
            slideView(0, 0 - this.mMoveLength, i, this.mPlayButton, false);
            moveVoiceIcon(0, this.mVoiceIconMoveLength, i);
        }
    }

    public void foldBtnStopAndPlay() {
        this.mHandler.sendEmptyMessage(100);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onlyShowStopBtn() {
        this.mPauseButton.setVisibility(8);
        this.mPlayButton.setVisibility(8);
        this.mStopButton.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hidePlayAndShowPauseBtn() {
        this.mStopButton.setProgressZero();
        this.mStopButton.e(true);
        if (LanguageUtil.bc(this.mContext)) {
            slideView(this.mMoveLength, 0, 200, this.mPlayButton, true);
            slideView(0 - this.mMoveLength, 0, 200, this.mStopButton, true);
            moveVoiceIcon(0 - this.mVoiceIconMoveLength, 0, 200);
        } else {
            slideView(this.mMoveLength, 0, 200, this.mStopButton, true);
            slideView(0 - this.mMoveLength, 0, 200, this.mPlayButton, true);
            moveVoiceIcon(this.mVoiceIconMoveLength, 0, 200);
        }
    }

    private void moveVoiceIcon(int i, int i2, int i3) {
        ImageView imageView;
        if (this.mVoiceIconMoveLength <= 0 || (imageView = this.mVoiceIcon) == null) {
            return;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, "translationX", i, i2);
        ofFloat.setDuration(i3);
        ofFloat.start();
    }

    public void slideView(int i, int i2, int i3, final View view, final boolean z) {
        if (view == null) {
            LogUtil.b(TAG, "slideView view is null");
            return;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationX", i, i2);
        ofFloat.setDuration(i3);
        ofFloat.addListener(new Animator.AnimatorListener() { // from class: com.huawei.health.basesport.controls.BaseControlButton.5
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BaseControlButton.this.slideViewEnd(z, view);
            }
        });
        ofFloat.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void slideViewEnd(boolean z, View view) {
        if (z) {
            view.setVisibility(8);
            if (view.getId() == R.id.track_main_page_btn_stop_indoor) {
                this.mPauseButton.setVisibility(0);
                return;
            }
            return;
        }
        view.setVisibility(0);
        this.mPauseButton.setVisibility(8);
        if (view.getId() == R.id.track_main_page_btn_stop_indoor) {
            this.mStopButton.e(false);
        }
    }

    /* loaded from: classes8.dex */
    static class e extends BaseHandler<BaseControlButton> {
        e(Looper looper, BaseControlButton baseControlButton) {
            super(looper, baseControlButton);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: Cr_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(BaseControlButton baseControlButton, Message message) {
            if (message != null) {
                switch (message.what) {
                    case 100:
                        baseControlButton.hidePlayAndShowPauseBtn();
                        break;
                    case 101:
                        baseControlButton.hidePauseAndShowPlayBtn(200);
                        break;
                    case 103:
                        baseControlButton.onlyShowStopBtn();
                        break;
                    case 104:
                        baseControlButton.hidePauseAndShowPlayBtn(0);
                        break;
                }
            }
            LogUtil.h(BaseControlButton.TAG, "handleMessage message is null");
        }
    }
}
