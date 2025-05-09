package com.huawei.ui.homehealth.refreshcard;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.homehealth.RecyclerviewSlideMenu;

/* loaded from: classes6.dex */
public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String TAG = "CardViewHolder";
    private AbstractBaseCardData mBaseCardData;
    private HealthTextView mCancelToptv;
    private LinearLayout mCardItemLayout;
    private RelativeLayout mCardMenuLayout;
    private Context mContext;
    private boolean mIsNeedMenu;
    private HealthTextView mToptv;

    public CardViewHolder(View view, Context context, boolean z) {
        super(view);
        this.mBaseCardData = null;
        this.mContext = context;
        this.mIsNeedMenu = z;
        if (!(view instanceof LinearLayout)) {
            Log.d(TAG, "ERROR itemView!");
            return;
        }
        if (z) {
            this.mCardItemLayout = (LinearLayout) view;
            this.mCardItemLayout.addView(new RecyclerviewSlideMenu(getContext()), new RelativeLayout.LayoutParams((int) ((context.getResources().getDisplayMetrics().density * 67.0f) + 0.5f), -1));
            this.mToptv = (HealthTextView) view.findViewById(R.id.card_top_tv);
            this.mCancelToptv = (HealthTextView) view.findViewById(R.id.card_cancel_top_tv);
            this.mCardMenuLayout = (RelativeLayout) view.findViewById(R.id.cardsedit_layout);
            this.mToptv.setOnClickListener(this);
            this.mCancelToptv.setOnClickListener(this);
            this.mCardMenuLayout.setOnClickListener(this);
        }
    }

    public <T extends AbstractBaseCardData> void bindViewHolder(T t) {
        this.mBaseCardData = t;
        if (this.mIsNeedMenu) {
            if (t.getIfTop()) {
                this.mToptv.setVisibility(8);
                this.mCancelToptv.setVisibility(0);
            } else {
                this.mToptv.setVisibility(0);
                this.mCancelToptv.setVisibility(8);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.cardsedit_layout) {
            LogUtil.c(TAG, "cardsedit_layout");
            if (this.mBaseCardData == null) {
                LogUtil.b(TAG, "======onClick ERROR=====cardsedit_layout");
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else if (this.mToptv.getVisibility() == 0) {
                this.mToptv.performClick();
            } else {
                this.mCancelToptv.performClick();
            }
        } else if (id == R.id.card_top_tv) {
            LogUtil.c(TAG, "card_top_tv");
            AbstractBaseCardData abstractBaseCardData = this.mBaseCardData;
            if (abstractBaseCardData == null) {
                LogUtil.b(TAG, "======onClick ERROR=====card_top_tv");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            abstractBaseCardData.setCardToTop(getLayoutPosition());
        } else if (id == R.id.card_cancel_top_tv) {
            LogUtil.c(TAG, "card_cancel_top_tv");
            AbstractBaseCardData abstractBaseCardData2 = this.mBaseCardData;
            if (abstractBaseCardData2 == null) {
                LogUtil.b(TAG, "======onClick ERROR=====card_cancel_top_tv");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            abstractBaseCardData2.cancelCardToTop();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public Context getContext() {
        return this.mContext;
    }
}
