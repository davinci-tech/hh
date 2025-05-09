package com.huawei.health.health.utils.functionsetcard;

import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.hwcommonmodel.fitnessdatatype.ResultUtils;

/* loaded from: classes3.dex */
public abstract class AbstractBaseCardData implements Comparable<AbstractBaseCardData> {
    protected int mCardID;
    private boolean mIfTop;
    protected INotifyCardChangeListener mNotifyCardManager = null;
    protected int mPositionTag = -1;

    public abstract String getCardName();

    public abstract RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater);

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onCreate() {
    }

    public void onDestroy() {
    }

    public void onResume() {
    }

    public abstract void refreshCardData();

    public void updateCardDetailData() {
    }

    public void setNotifyCardChangeListener(INotifyCardChangeListener iNotifyCardChangeListener) {
        this.mNotifyCardManager = iNotifyCardChangeListener;
    }

    public int getCardID() {
        return this.mCardID;
    }

    public void setCardID(int i) {
        this.mCardID = i;
    }

    public boolean getIfTop() {
        return this.mIfTop;
    }

    public void setIfTop(boolean z) {
        this.mIfTop = z;
    }

    public void setCardToTop(int i) {
        INotifyCardChangeListener iNotifyCardChangeListener = this.mNotifyCardManager;
        if (iNotifyCardChangeListener != null) {
            iNotifyCardChangeListener.setCardToTop(i);
        }
    }

    public void cancelCardToTop() {
        INotifyCardChangeListener iNotifyCardChangeListener = this.mNotifyCardManager;
        if (iNotifyCardChangeListener != null) {
            iNotifyCardChangeListener.cancelCardToTop();
        }
    }

    public int getCardPositionTag() {
        return this.mPositionTag;
    }

    public void setCardPositionTag(int i) {
        this.mPositionTag = ((Integer) ResultUtils.commonFunc(Integer.valueOf(i))).intValue();
    }

    @Override // java.lang.Comparable
    public int compareTo(AbstractBaseCardData abstractBaseCardData) {
        return getCardPositionTag() >= abstractBaseCardData.getCardPositionTag() ? 0 : -1;
    }
}
