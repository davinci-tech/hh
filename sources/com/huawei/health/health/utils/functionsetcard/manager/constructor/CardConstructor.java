package com.huawei.health.health.utils.functionsetcard.manager.constructor;

import android.content.Context;
import android.view.View;
import com.huawei.health.health.utils.functionsetcard.manager.OnCardChangedListener;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;

/* loaded from: classes3.dex */
public interface CardConstructor {
    FunctionSetSubCardData createCardReader(Context context);

    String getCardId();

    int getIsMessageLargeCard();

    View getMarketingView();

    int getShowFlag(Object obj);

    void init();

    boolean isSupport(boolean z, int i);

    void release();

    void setIsMessageLargeCard(int i);

    void setMarketingView(View view);

    void setOnChangedListener(OnCardChangedListener onCardChangedListener);

    void subscribeDataChange();

    void unSubscribeDataChange();
}
