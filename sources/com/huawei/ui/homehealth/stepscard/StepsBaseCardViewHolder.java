package com.huawei.ui.homehealth.stepscard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.knit.section.chart.StepView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.homehealth.refreshcard.CardViewHolder;

/* loaded from: classes6.dex */
public abstract class StepsBaseCardViewHolder extends CardViewHolder {
    public static final int POSITION_CARD_STEP = 3;
    public static final int THREE_CIRCLE_INSIDE = 2;
    public static final int THREE_CIRCLE_MIDDLE = 1;
    public static final int THREE_CIRCLE_OUTSIDE = 0;

    public void bindThreeCircleFloorLayout(int i, int i2) {
    }

    public abstract RelativeLayout getActivityLayout();

    public abstract HealthTextView getBubbleText();

    public abstract View getDivider();

    public abstract ImageView getFitnessDataOriginIcon();

    public abstract LinearLayout getFitnessDataOriginIconLayout();

    public abstract HealthTextView getFitnessDataTextView();

    public abstract View getHomeStepCardLayout();

    public abstract ViewGroup getStepLayout();

    public abstract StepView getStepView();

    public abstract HealthTextView getStepsText();

    public LinearLayout getThreeCycleLayoutContainer() {
        return null;
    }

    public abstract ImageView getThreeLeafIcon();

    public abstract ViewGroup getTimeStrengthLayout();

    public abstract HealthTextView getTimeText();

    public abstract LinearLayout getTwoModelSwitchArea();

    public abstract void refreshDistanceCalFloorMargin(String str, String str2, String str3, boolean z);

    public abstract void refreshDistanceCalFloorParams(int i);

    public abstract void refreshStepStrenActivityWidth();

    public void setBottomClickListener(View.OnClickListener onClickListener) {
    }

    public void setFitnessDataOriginIcon2Visible(int i) {
    }

    public void setMsgRedDotText(String str) {
    }

    public void setTextByPosition(int i, int i2, int i3) {
    }

    public void setThreeCircleDataListener(int i, View.OnClickListener onClickListener) {
    }

    public void setThreeCircleLayoutListener(View.OnClickListener onClickListener) {
    }

    public void updateBottomData(int i, int i2) {
    }

    public abstract boolean updateLayout();

    public void updateLeafCardShowStatus(boolean z) {
    }

    public void updateMsgRedDotVisibility(int i) {
    }

    public void updateProgress(int i, int i2, int i3, boolean z) {
    }

    public StepsBaseCardViewHolder(View view, Context context, boolean z) {
        super(view, context, z);
    }
}
