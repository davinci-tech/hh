package com.huawei.trade;

import android.content.Context;
import android.view.View;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.trade.datatype.TradeViewInfo;

/* loaded from: classes6.dex */
public interface TradeViewApi {
    void backIntercept(View view, IBaseResponseCallback iBaseResponseCallback);

    void cancelView(View view);

    View getOrderVeiw(Context context, int i);

    View getTradeView(Context context, TradeViewInfo tradeViewInfo);

    @Deprecated
    View getTradeView(Context context, String str, int i);

    void refreshView(View view);
}
