package com.huawei.health.suggestion.ui.polymericpage.adapter;

import android.text.TextUtils;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.adapter.LoadMoreRecyclerViewAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.arx;
import defpackage.fnz;
import defpackage.nsn;
import health.compact.a.Utils;
import health.compact.a.utils.StringUtils;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class PolymericDataAdapter extends LoadMoreRecyclerViewAdapter<Workout> {
    protected static final float FITNESS_LARGE_FONT_SCALE_MODE_MIN = 1.3f;
    private static final String TAG = "Suggestion_PolymericDataAdapter";
    protected boolean mIsShowSquare;
    protected int mNavigationId;
    protected float mWeight;

    public abstract void onItemClick(Workout workout, int i);

    public abstract void setCardBackground(ImageView imageView, Workout workout, int i);

    public abstract void setNewPic(ImageView imageView, Workout workout);

    public abstract void setRecycleHolder(RecyclerHolder recyclerHolder, Workout workout, int i);

    public PolymericDataAdapter(List<Workout> list, RecyclerView recyclerView, int i) {
        super(list, recyclerView, i);
        this.mIsShowSquare = false;
        this.mWeight = fnz.e();
    }

    @Override // com.huawei.ui.commonui.adapter.LoadMoreRecyclerViewAdapter
    public void convert(RecyclerHolder recyclerHolder, int i, Workout workout) {
        if (workout == null) {
            LogUtil.h(TAG, "convert, workout is null.");
            return;
        }
        LogUtil.a(TAG, "convert, holder.itemView.getWidth() = ", Integer.valueOf(recyclerHolder.itemView.getWidth()));
        HealthTextView healthTextView = (HealthTextView) recyclerHolder.cwK_(R.id.tv_fe_name);
        if (!Utils.o()) {
            healthTextView.setMaxLines(1);
            healthTextView.setSingleLine(true);
            healthTextView.setEllipsize(TextUtils.TruncateAt.END);
        }
        healthTextView.setText(StringUtils.c((Object) workout.acquireName()));
        recyclerHolder.a(R.id.tv_plan_peoples_num, isShowPeoplesNum() ? 0 : 8);
        setNewPic((ImageView) recyclerHolder.cwK_(R.id.new_imageView), workout);
        if (!this.mIsShowSquare) {
            setCardBackground((ImageView) recyclerHolder.cwK_(R.id.sug_img_item_pic), workout, i);
        }
        setRecycleHolder(recyclerHolder, workout, i);
    }

    public void setNavigationId(int i) {
        this.mNavigationId = i;
    }

    public void setIsShowSquare(boolean z) {
        this.mIsShowSquare = z;
    }

    protected boolean isShowPeoplesNum() {
        if (nsn.e(1.3f)) {
            return false;
        }
        return Utils.j();
    }

    protected void setLevelAndParameterNumAndCalorieState(HealthTextView healthTextView, HealthTextView healthTextView2, HealthTextView healthTextView3, int i, int i2) {
        healthTextView.setVisibility(i);
        healthTextView2.setVisibility(i);
        if (nsn.e(1.3f)) {
            healthTextView.setTextSize(getTextSize(R.dimen._2131363095_res_0x7f0a0517));
            healthTextView3.setTextSize(getTextSize(R.dimen._2131363095_res_0x7f0a0517));
            healthTextView2.setTextSize(getTextSize(R.dimen._2131363095_res_0x7f0a0517));
        }
        if (healthTextView3.getLayoutParams() instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) healthTextView3.getLayoutParams();
            layoutParams.setMarginStart(i2);
            layoutParams.baselineToBaseline = -1;
        }
    }

    protected float getTextSize(int i) {
        return arx.b().getResources().getDimensionPixelSize(i) / nsn.g(arx.b());
    }

    protected int getTextColor(int i) {
        return ContextCompat.getColor(arx.b(), i);
    }
}
