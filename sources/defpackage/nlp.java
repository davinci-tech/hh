package defpackage;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.google.flatbuffers.reflection.BaseType;
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
public class nlp extends BaseAchieveDialog {

    /* renamed from: a, reason: collision with root package name */
    private String f15375a;
    private HealthTextView b;
    private String c;
    private String d;
    private int e;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private String j;

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public byte generateAllTextViewLayoutFlags() {
        return BaseType.Obj;
    }

    public nlp(Context context, AchieveDialogFactory.DialogType dialogType, DialogResourcesListener dialogResourcesListener, ThreeCircleShareCallback threeCircleShareCallback) {
        super(context, dialogType, dialogResourcesListener);
        this.mCallback = threeCircleShareCallback;
    }

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public void initCancelImageAndTipText() {
        this.mCancelImg = (ImageView) this.mRootView.findViewById(R.id.cancel_img);
        this.mCancelImg.setVisibility(0);
        this.mCancelImg.setOnClickListener(new View.OnClickListener() { // from class: nlq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                nlp.this.czT_(view);
            }
        });
        this.mGotoDetailText = (HealthTextView) this.mRootView.findViewById(R.id.goto_detail);
        this.mGotoDetailText.setText(R$string.IDS_hw_messagecenter_more_detail);
        this.mGotoDetailText.setVisibility(0);
    }

    /* synthetic */ void czT_(View view) {
        dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    public nlp e(String str) {
        this.j = str;
        return this;
    }

    public nlp d(String str) {
        this.d = str;
        return this;
    }

    public nlp c(String str) {
        this.f15375a = str;
        return this;
    }

    public nlp b(String str) {
        this.c = str;
        return this;
    }

    public nlp c(int i) {
        this.e = i;
        return this;
    }

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public void goToShare() {
        if (this.mCallback == null) {
            ReleaseLogUtil.c("BaseAchieveDialog", "mCallback is null in SingleCircleAchieveDialog");
        } else {
            this.mCallback.onCallback(false);
        }
    }

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public void onAnimSizeMeasured(int i, int i2) {
        float f = i2;
        int i3 = (int) ((f * 0.3333333f) / 2.0f);
        float f2 = (int) (0.6666667f * f);
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen._2131363063_res_0x7f0a04f7);
        int i4 = R$string.textFontFamilyRegular;
        int i5 = R$string.textFontFamilyMedium;
        this.h = createTextViewAbove(R.id.single_circle_target_desc_id, R.id.achieve_text, i3 + ((int) (0.06666667f * f2)), getContext().getColor(R$color.textColorPrimaryInverse), this.c, R.dimen._2131365080_res_0x7f0a0cd8, i4);
        this.b = createTextViewAbove(R.id.single_circle_desc_id, R.id.single_circle_target_desc_id, 0, getContext().getColor(R$color.textColorPrimaryInverse), this.f15375a, R.dimen._2131365080_res_0x7f0a0cd8, i4);
        this.g = createTextViewBelow(R.id.single_circle_title_id, 0, i3 + ((int) (f2 * this.mRingExtraTopMarginRatio)), getContext().getColor(R$color.textColorPrimaryInverse), this.j, R.dimen._2131365076_res_0x7f0a0cd4, i5);
        HealthTextView createTextViewBelow = createTextViewBelow(R.id.single_circle_target_id, R.id.single_circle_title_id, dimensionPixelSize, getContext().getColor(this.e), this.d, R.dimen._2131365072_res_0x7f0a0cd0, i5);
        this.i = createTextViewBelow;
        createTextViewBelow.setScaleX(0.5f);
        this.i.setScaleY(0.5f);
        this.i.setTypeface(Typeface.createFromAsset(getContext().getResources().getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf"));
    }

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public void doTextAnimation() {
        if (!isAllTextViewsLayout() || hasDoTextAnimation()) {
            return;
        }
        FastOutSlowInInterpolator fastOutSlowInInterpolator = new FastOutSlowInInterpolator();
        HealthTextView healthTextView = this.g;
        if (healthTextView != null) {
            healthTextView.animate().alpha(1.0f).setDuration(this.mAnimationDuration).setInterpolator(fastOutSlowInInterpolator).start();
        }
        HealthTextView healthTextView2 = this.i;
        if (healthTextView2 != null) {
            healthTextView2.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(this.mAnimationDuration).setInterpolator(fastOutSlowInInterpolator).start();
        }
        HealthTextView healthTextView3 = this.h;
        if (healthTextView3 != null) {
            healthTextView3.animate().alpha(1.0f).setDuration(this.mAnimationDuration).setInterpolator(fastOutSlowInInterpolator).start();
        }
        HealthTextView healthTextView4 = this.b;
        if (healthTextView4 != null) {
            healthTextView4.animate().alpha(1.0f).setDuration(this.mAnimationDuration).setInterpolator(fastOutSlowInInterpolator).start();
        }
        super.doTextAnimation();
    }

    @Override // com.huawei.ui.commonui.dialog.BaseAchieveDialog
    public String toString() {
        StringBuilder sb = new StringBuilder("SingleCircleAchieveDialog#");
        String str = this.j;
        if (str == null) {
            str = Constants.NULL;
        }
        sb.append(str);
        sb.append("#");
        sb.append(hashCode());
        return sb.toString();
    }
}
