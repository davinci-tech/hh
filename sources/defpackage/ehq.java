package defpackage;

import android.content.Context;
import com.huawei.haf.bundle.AppBundlePluginProxy;
import com.huawei.health.market.api.MarketMgrApi;

/* loaded from: classes3.dex */
public class ehq extends AppBundlePluginProxy<MarketMgrApi> implements MarketMgrApi {

    /* renamed from: a, reason: collision with root package name */
    private static volatile ehq f12016a;
    private MarketMgrApi e;

    private ehq() {
        super("MarketProxy", "Market", "com.huawei.health.market.impl.MarketMgrImpl");
        this.e = createPluginApi();
    }

    public static ehq b() {
        ehq ehqVar;
        if (f12016a == null) {
            synchronized (ehq.class) {
                if (f12016a == null) {
                    f12016a = new ehq();
                }
                ehqVar = f12016a;
            }
            return ehqVar;
        }
        return f12016a;
    }

    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    public boolean isPluginAvaiable() {
        return this.e != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.haf.bundle.AppBundlePluginProxy
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void initialize(MarketMgrApi marketMgrApi) {
        this.e = marketMgrApi;
    }

    @Override // com.huawei.health.market.api.MarketMgrApi
    public void showMarketCommentDialog(Context context) {
        if (isPluginAvaiable()) {
            this.e.showMarketCommentDialog(context);
        }
    }
}
