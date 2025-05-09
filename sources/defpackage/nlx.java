package defpackage;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.AchieveDialogFactory;
import com.huawei.ui.commonui.dialog.BaseAchieveDialog;
import com.huawei.ui.commonui.dialog.DialogResourcesListener;
import com.huawei.ui.commonui.dialog.ThreeCircleShareCallback;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class nlx extends BaseAchieveDialog {
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public byte generateAllTextViewLayoutFlags() {
        return (byte) 7;
    }

    public nlx(Context context, AchieveDialogFactory.DialogType dialogType, DialogResourcesListener dialogResourcesListener, ThreeCircleShareCallback threeCircleShareCallback) {
        super(context, dialogType, dialogResourcesListener);
        this.mCallback = threeCircleShareCallback;
    }

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public void initCancelImageAndTipText() {
        this.mCancelImg = (ImageView) this.mRootView.findViewById(R.id.cancel_img);
        this.mCancelImg.setVisibility(0);
        this.mCancelImg.setOnClickListener(new View.OnClickListener() { // from class: nlw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                nlx.this.czW_(view);
            }
        });
        this.mGotoDetailText = (HealthTextView) this.mRootView.findViewById(R.id.goto_detail);
        this.mGotoDetailText.setText(R$string.IDS_hw_messagecenter_more_detail);
        this.mGotoDetailText.setVisibility(0);
    }

    /* synthetic */ void czW_(View view) {
        dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public void goToShare() {
        if (this.mCallback == null) {
            ReleaseLogUtil.c("BaseAchieveDialog", "mCallback is null in ThreeCircleAchieveDialog");
        } else {
            this.mCallback.onCallback(true);
        }
    }

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public void onAnimSizeMeasured(int i, int i2) {
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen._2131362945_res_0x7f0a0481);
        int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen._2131363168_res_0x7f0a0560);
        int i3 = R$string.textFontFamilyMedium;
        HealthTextView createTextViewAbove = createTextViewAbove(R.id.three_circle_achieve_anim_walk_id, R.id.achieve_text, ((int) ((i2 * 0.3333333f) / 2.0f)) + dimensionPixelSize, getContext().getColor(R$color.heat_cal_color), this.mHeatText, R.dimen._2131365075_res_0x7f0a0cd3, i3);
        this.d = createTextViewAbove;
        HealthTextView createTextViewAbove2 = createTextViewAbove(R.id.three_circle_achieve_anim_intensity_id, createTextViewAbove.getId(), dimensionPixelSize2, getContext().getColor(R$color.intensity_time_color), this.mIntensityTimeText, R.dimen._2131365075_res_0x7f0a0cd3, i3);
        this.c = createTextViewAbove2;
        this.b = createTextViewAbove(R.id.three_circle_achieve_anim_active_id, createTextViewAbove2.getId(), dimensionPixelSize2, getContext().getColor(R$color.active_time_color), this.mActiveHourText, R.dimen._2131365075_res_0x7f0a0cd3, i3);
    }

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public void doTextAnimation() {
        if (!isAllTextViewsLayout() || hasDoTextAnimation()) {
            return;
        }
        FastOutSlowInInterpolator fastOutSlowInInterpolator = new FastOutSlowInInterpolator();
        HealthTextView healthTextView = this.d;
        if (healthTextView != null) {
            healthTextView.animate().alpha(1.0f).setDuration(this.mAnimationDuration).setInterpolator(fastOutSlowInInterpolator).start();
        }
        HealthTextView healthTextView2 = this.c;
        if (healthTextView2 != null) {
            healthTextView2.animate().alpha(1.0f).setDuration(this.mAnimationDuration).setInterpolator(fastOutSlowInInterpolator).start();
        }
        HealthTextView healthTextView3 = this.b;
        if (healthTextView3 != null) {
            healthTextView3.animate().alpha(1.0f).setDuration(this.mAnimationDuration).setInterpolator(fastOutSlowInInterpolator).start();
        }
        super.doTextAnimation();
    }

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public String toString() {
        return "ThreeCircleAchieveDialog#" + hashCode();
    }
}
