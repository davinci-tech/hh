package com.huawei.healthcloud.plugintrack.ui.viewholder;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.basesport.sportui.OnEndCountdownListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.gso;
import defpackage.gww;
import defpackage.gxd;
import defpackage.gxi;
import defpackage.mxb;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class CountdownDialog {
    public static final int COUNT_DOWN_NUMBER_FOR_OUT_GOING = 3;
    public static final int COUNT_DOWN_NUMBER_FOR_ROPE = 5;
    private static final int DELAY_TIME = 150;
    private static final int[] ICONS_FROM_FIVE = {R.drawable._2131431813_res_0x7f0b1185, R.drawable._2131431814_res_0x7f0b1186, R.drawable._2131431815_res_0x7f0b1187, R.drawable._2131431816_res_0x7f0b1188, R.drawable._2131431817_res_0x7f0b1189, R.drawable._2131431818_res_0x7f0b118a};
    private static final int MAX_COUNTDOWN_NUMBER = 6;
    private static final String TAG = "Track_CountdownDialog";
    private AnimatorSet mAnimatorSet;
    private Context mContext;
    private boolean mIsNotArabicNum;
    private String mModuleID;
    private ImageView mNum;
    private HealthTextView mNumText;
    private View mRootView;
    private ImageView mRound;
    private StorageParams mStorageParams;
    private gww mTrackSharedPreferenceUtil;
    private OnEndCountdownListener mEndListener = null;
    private int mCountdown = 5;
    private boolean mIsGoEnable = false;

    static /* synthetic */ int access$006(CountdownDialog countdownDialog) {
        int i = countdownDialog.mCountdown - 1;
        countdownDialog.mCountdown = i;
        return i;
    }

    public CountdownDialog(Context context, View view) {
        if (context == null || view == null) {
            throw new RuntimeException("Invalid param");
        }
        this.mContext = context;
        this.mRootView = view;
        this.mStorageParams = new StorageParams();
        this.mModuleID = Integer.toString(20002);
        this.mTrackSharedPreferenceUtil = new gww(BaseApplication.getContext(), this.mStorageParams, this.mModuleID);
        this.mIsNotArabicNum = LanguageUtil.ax(this.mContext);
        initView();
    }

    public void addEndCountdown(OnEndCountdownListener onEndCountdownListener) {
        this.mEndListener = onEndCountdownListener;
    }

    private void initView() {
        this.mNum = (ImageView) this.mRootView.findViewById(R.id.track_conut_down_num);
        this.mRound = (ImageView) this.mRootView.findViewById(R.id.track_conut_down_num_round);
        this.mNumText = (HealthTextView) this.mRootView.findViewById(R.id.track_conut_down_num_text);
        Animator loadAnimator = AnimatorInflater.loadAnimator(this.mContext, R.animator._2131034184_res_0x7f050048);
        Animator loadAnimator2 = AnimatorInflater.loadAnimator(this.mContext, R.animator._2131034185_res_0x7f050049);
        if (this.mIsNotArabicNum) {
            this.mNumText.setVisibility(0);
            loadAnimator.setTarget(this.mNumText);
        } else {
            this.mNum.setVisibility(0);
            loadAnimator.setTarget(this.mNum);
        }
        loadAnimator2.setTarget(this.mRound);
        AnimatorSet animatorSet = new AnimatorSet();
        this.mAnimatorSet = animatorSet;
        animatorSet.playTogether(loadAnimator, loadAnimator2);
        this.mAnimatorSet.addListener(new Animator.AnimatorListener() { // from class: com.huawei.healthcloud.plugintrack.ui.viewholder.CountdownDialog.1
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
                CountdownDialog countdownDialog = CountdownDialog.this;
                countdownDialog.countdown(CountdownDialog.access$006(countdownDialog));
            }
        });
    }

    public void setTimeStart(int i) {
        this.mCountdown = i;
    }

    public void enableGo() {
        this.mIsGoEnable = true;
    }

    public void startCountdown() {
        this.mRootView.postDelayed(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.viewholder.CountdownDialog.4
            @Override // java.lang.Runnable
            public void run() {
                CountdownDialog.this.mRound.setVisibility(0);
                CountdownDialog countdownDialog = CountdownDialog.this;
                countdownDialog.countdown(countdownDialog.mCountdown);
            }
        }, 150L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void countdown(int i) {
        LogUtil.a(TAG, "countdown() :", Integer.valueOf(i));
        if (i < ((this.mIsNotArabicNum || !this.mIsGoEnable) ? 1 : 0)) {
            if (this.mEndListener != null) {
                gxd.a().b(false);
                this.mEndListener.endCountdown();
                return;
            }
            return;
        }
        sendBroadcastToVoiceService(i);
        if (this.mIsNotArabicNum) {
            this.mNumText.setText(UnitUtil.e(i, 1, 0));
        } else {
            this.mNum.setImageResource(ICONS_FROM_FIVE[i]);
        }
        this.mAnimatorSet.start();
    }

    private void sendBroadcastToVoiceService(int i) {
        if (i > 6 || i < 0) {
            return;
        }
        gxd.a().b(true);
        if (isVoiceValid(gso.e().b()) && isNeedVoice()) {
            Bundle bundle = new Bundle();
            bundle.putString("voiceType", "intelligentVoice");
            bundle.putInt("intelligentVoice", i);
            gxd.a().d(new gxi(20, bundle));
        }
    }

    private boolean isNeedVoice() {
        return mxb.a().c(this.mContext) || (LanguageUtil.j(this.mContext) && !UnitUtil.h());
    }

    private boolean isVoiceValid(int i) {
        if (i == 2) {
            return true;
        }
        if (CommonUtil.h(SharedPreferenceManager.b(this.mContext, Integer.toString(20002), "map_tracking_sport_type")) == 283) {
            return gww.d();
        }
        return gww.e() == 1;
    }
}
