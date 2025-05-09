package com.huawei.ui.homehealth.functionsetcard.manager.strategy;

import android.content.Context;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class CardUpgradeStrategy {
    protected static final String INDEX_EIGHT = "8";
    protected static final String INDEX_FIVE = "5";
    protected static final String INDEX_FOUR = "4";
    protected static final String INDEX_ONE = "1";
    protected static final String INDEX_SEVEN = "7";
    protected static final String INDEX_SIX = "6";
    protected static final String INDEX_THREE = "3";
    protected static final String INDEX_TWO = "2";
    protected static final String INDEX_ZERO = "0";
    protected static final int NOT_SHOW_ITEM_INDEX = 1000;
    protected static final String TRUE = Boolean.TRUE.toString();
    protected static final String WITHOUT_INIT_VALUE = "";
    protected Context mContext;
    protected String mCardPosition = "";
    protected String mDefaultCardPosition = "";
    protected String mShowCard = "";

    public abstract String getCardId();

    protected abstract boolean isEditedPosition(List<Boolean> list);

    public abstract void setCardPosition();

    public abstract void setCardShowOptions();

    public CardUpgradeStrategy(Context context) {
        this.mContext = context;
        setCardPosition();
        setCardShowOptions();
    }

    public boolean isEditedCard(List<Boolean> list) {
        if (list != null && list.size() >= 9) {
            boolean isEditedPosition = isEditedPosition(list);
            boolean isEditedShowStatus = isEditedShowStatus();
            if (isEditedPosition || isEditedShowStatus) {
                return true;
            }
        }
        return false;
    }

    public int getShowFlag(List<Boolean> list) {
        if (list == null || list.size() < 9) {
            return 0;
        }
        if (list.get(5).booleanValue()) {
            return (!isEmptyShowCard() && TRUE.equals(this.mShowCard)) ? 1 : 2;
        }
        if (list.get(6).booleanValue() || list.get(7).booleanValue() || list.get(8).booleanValue()) {
            return isEmptyPosition() ? 2 : 1;
        }
        return 0;
    }

    public boolean isEmptyPosition() {
        return "".equals(this.mCardPosition);
    }

    public boolean isEmptyShowCard() {
        return "".equals(this.mShowCard);
    }

    public String getCardPosition() {
        return this.mCardPosition;
    }

    public String getShowCard() {
        return this.mShowCard;
    }

    protected boolean isEditedShowStatus() {
        return (isEmptyShowCard() || TRUE.equals(this.mShowCard)) ? false : true;
    }

    protected boolean judgeDefaultPosition() {
        return !this.mDefaultCardPosition.equals(this.mCardPosition);
    }
}
