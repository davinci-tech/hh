package defpackage;

import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.AchieveDialogFactory;
import com.huawei.ui.commonui.dialog.BaseAchieveDialog;
import com.huawei.ui.commonui.dialog.DialogResourcesListener;
import com.huawei.ui.commonui.dialog.ThreeCircleShareCallback;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class nlt extends BaseAchieveDialog {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f15377a;
    private String b;
    private SpannableString c;
    private HealthTextView e;

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public byte generateAllTextViewLayoutFlags() {
        return (byte) 3;
    }

    public nlt(Context context, AchieveDialogFactory.DialogType dialogType, DialogResourcesListener dialogResourcesListener, ThreeCircleShareCallback threeCircleShareCallback) {
        super(context, dialogType, dialogResourcesListener);
        this.mCallback = threeCircleShareCallback;
    }

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public void initCancelImageAndTipText() {
        this.mCancelImg = (ImageView) this.mRootView.findViewById(R.id.cancel_img);
        this.mCancelImg.setVisibility(0);
        this.mCancelImg.setOnClickListener(new View.OnClickListener() { // from class: nlv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                nlt.this.czU_(view);
            }
        });
        this.mGotoDetailText = (HealthTextView) this.mRootView.findViewById(R.id.goto_detail);
        this.mGotoDetailText.setText(R$string.IDS_achive_ok_check_text);
        this.mGotoDetailText.setVisibility(0);
    }

    /* synthetic */ void czU_(View view) {
        dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    public nlt c(String str) {
        this.b = str;
        return this;
    }

    public nlt czV_(SpannableString spannableString) {
        this.c = spannableString;
        return this;
    }

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public void goToShare() {
        if (this.mCallback == null) {
            ReleaseLogUtil.c("BaseAchieveDialog", "mCallback is null in StepAchieveDialog");
        } else {
            this.mCallback.onCallback(false);
        }
    }

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public void onAnimSizeMeasured(int i, int i2) {
        int i3 = R$string.textFontFamilyMedium;
        HealthTextView createTextViewAbove = createTextViewAbove(R.id.single_circle_target_desc_id, R.id.achieve_text, (((int) ((i2 * 0.3333333f) / 2.0f)) + ((int) (((int) (0.6666667f * r10)) * 0.06666667f))) * 3, getContext().getColor(R$color.textColorPrimaryInverse), "", R.dimen._2131365076_res_0x7f0a0cd4, i3);
        this.f15377a = createTextViewAbove;
        createTextViewAbove.setText(this.c);
        this.e = createTextViewAbove(R.id.single_circle_desc_id, R.id.single_circle_target_desc_id, 0, getContext().getColor(R$color.textColorPrimaryInverse), this.b, R.dimen._2131365076_res_0x7f0a0cd4, i3);
    }

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public void doTextAnimation() {
        if (!isAllTextViewsLayout() || hasDoTextAnimation()) {
            return;
        }
        FastOutSlowInInterpolator fastOutSlowInInterpolator = new FastOutSlowInInterpolator();
        HealthTextView healthTextView = this.e;
        if (healthTextView != null) {
            healthTextView.animate().alpha(1.0f).setDuration(this.mAnimationDuration).setInterpolator(fastOutSlowInInterpolator).start();
        }
        HealthTextView healthTextView2 = this.f15377a;
        if (healthTextView2 != null) {
            healthTextView2.animate().alpha(1.0f).setDuration(this.mAnimationDuration).setInterpolator(fastOutSlowInInterpolator).start();
        }
        super.doTextAnimation();
    }

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public String toString() {
        StringBuilder sb = new StringBuilder("StepAchieveDialog#");
        String str = this.b;
        if (str == null) {
            str = Constants.NULL;
        }
        sb.append(str);
        sb.append("#");
        sb.append(hashCode());
        return sb.toString();
    }
}
