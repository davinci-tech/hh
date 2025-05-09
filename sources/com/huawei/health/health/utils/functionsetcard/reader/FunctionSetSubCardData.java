package com.huawei.health.health.utils.functionsetcard.reader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.health.utils.functionsetcard.FunctionSetBean;
import com.huawei.health.health.utils.functionsetcard.ICardChangedCallback;
import com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import defpackage.iyb;
import java.util.List;

/* loaded from: classes3.dex */
public interface FunctionSetSubCardData {
    View createCardView();

    CardConfig getCardConfig();

    String getCardId();

    int getCardType();

    RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater, int i);

    boolean getCurrentIsLargeCard();

    int getExtraWidth();

    FunctionSetBean getFunctionSetBean();

    iyb getShowOrClickBiInfo(int i);

    boolean hasCardData();

    boolean hasCardDataLastTime();

    void initCardData();

    boolean isSubscribeType(int i);

    void onBindViewHolder(RecyclerView.ViewHolder viewHolder, FunctionSetSubCardData functionSetSubCardData);

    void onDestroy();

    void onResume();

    void readCardData();

    void refreshRedDotSyncNotify(boolean z);

    void setCardConfig(CardConfig cardConfig);

    void setCardConstructor(CardConstructor cardConstructor);

    void setFunctionSetBean(FunctionSetBean functionSetBean);

    void setNotifyCardChangedCallback(ICardChangedCallback iCardChangedCallback);

    void updateSuccessList(List<Integer> list);
}
