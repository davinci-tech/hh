package defpackage;

import android.content.Context;
import android.view.View;
import com.huawei.health.tradeservice.view.TradeInformationLayout;
import com.huawei.health.tradeservice.view.TradeOrderLayout;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.trade.TradeViewApi;
import com.huawei.trade.datatype.TradeViewInfo;

@ApiDefine(uri = TradeViewApi.class)
/* loaded from: classes4.dex */
public class gkq implements TradeViewApi {
    @Override // com.huawei.trade.TradeViewApi
    public View getTradeView(Context context, String str, int i) {
        if (context == null) {
            LogUtil.h("TradeViewImpl", "context = null");
            return null;
        }
        return new TradeInformationLayout(context, str, i);
    }

    @Override // com.huawei.trade.TradeViewApi
    public View getTradeView(Context context, TradeViewInfo tradeViewInfo) {
        if (context == null) {
            LogUtil.h("TradeViewImpl", "getTradeView context = null");
            return null;
        }
        return new TradeInformationLayout(context, tradeViewInfo);
    }

    @Override // com.huawei.trade.TradeViewApi
    public void cancelView(View view) {
        if (view instanceof TradeInformationLayout) {
            LogUtil.a("TradeViewImpl", "cancelView");
            ((TradeInformationLayout) view).e();
        } else {
            LogUtil.h("TradeViewImpl", "view = null or is not TradeInformationLayout");
        }
    }

    @Override // com.huawei.trade.TradeViewApi
    public View getOrderVeiw(Context context, int i) {
        if (context == null) {
            LogUtil.h("TradeViewImpl", "getOrderVeiw context = null");
            return null;
        }
        return new TradeOrderLayout(context, i);
    }

    @Override // com.huawei.trade.TradeViewApi
    public void refreshView(View view) {
        if (view instanceof TradeInformationLayout) {
            LogUtil.a("TradeViewImpl", "refreshView");
            ((TradeInformationLayout) view).b();
        } else {
            LogUtil.h("TradeViewImpl", "view = null or is not TradeInformationLayout");
        }
    }

    @Override // com.huawei.trade.TradeViewApi
    public void backIntercept(View view, IBaseResponseCallback iBaseResponseCallback) {
        if (view instanceof TradeInformationLayout) {
            LogUtil.a("TradeViewImpl", "enter retain view");
            ((TradeInformationLayout) view).d(iBaseResponseCallback);
        } else {
            LogUtil.a("TradeViewImpl", "direct return for non vip price product");
            iBaseResponseCallback.d(0, null);
        }
    }
}
