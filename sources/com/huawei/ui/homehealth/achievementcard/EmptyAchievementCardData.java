package com.huawei.ui.homehealth.achievementcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData;

/* loaded from: classes6.dex */
public abstract class EmptyAchievementCardData extends AbstractBaseCardData {
    private static final String TAG = "EmptyAchievementCardData";
    protected Context mContext;

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void refreshCardData() {
    }

    public EmptyAchievementCardData(Context context) {
        this.mContext = context;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        if (layoutInflater == null) {
            return null;
        }
        return new AchievementCardViewHolder(layoutInflater.inflate(R.layout.home_item_layout_achievement, viewGroup, false), this.mContext, false);
    }
}
