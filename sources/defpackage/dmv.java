package defpackage;

import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.browse.BrowsingBiEvent;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.featuremarketing.route.IMarketRouteHelper;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.SharedPreferenceManager;
import java.util.Map;

@ApiDefine(uri = MarketRouterApi.class)
/* loaded from: classes3.dex */
public class dmv implements MarketRouterApi {

    /* renamed from: a, reason: collision with root package name */
    private IBaseResponseCallback f11722a;

    @Override // com.huawei.health.marketrouter.api.MarketRouterApi
    public void router(String str, IBaseResponseCallback iBaseResponseCallback) {
        this.f11722a = iBaseResponseCallback;
        router(str);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.huawei.health.marketrouter.api.MarketRouterApi
    public void router(String str) {
        IMarketRouteHelper dncVar;
        String d = nsa.d(str);
        char c = 65535;
        if (TextUtils.isEmpty(d)) {
            LogUtil.h("MarketRouterImpl", "routerUrl url is null");
            IBaseResponseCallback iBaseResponseCallback = this.f11722a;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(-1, null);
                return;
            }
            return;
        }
        if (SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false)) {
            BrowsingBiEvent browsingBiEvent = LoginInit.getBrowsingBiEvent();
            if (browsingBiEvent != null) {
                browsingBiEvent.showFullServiceDialog(BaseApplication.e());
            }
            LogUtil.h("MarketRouterImpl", "basic model, marketingResource can not jump, return");
            return;
        }
        String trim = d.trim();
        String e = nsa.e(trim);
        if (!"3".equals(e) && !"2".equals(e)) {
            dmw.b(trim);
        }
        e.hashCode();
        switch (e.hashCode()) {
            case 49:
                if (e.equals("1")) {
                    c = 0;
                    break;
                }
                break;
            case 50:
                if (e.equals("2")) {
                    c = 1;
                    break;
                }
                break;
            case 51:
                if (e.equals("3")) {
                    c = 2;
                    break;
                }
                break;
            case 52:
                if (e.equals("4")) {
                    c = 3;
                    break;
                }
                break;
        }
        if (c == 0) {
            dncVar = new dnc(trim, this.f11722a);
        } else if (c == 1) {
            dncVar = new dmz(trim, this.f11722a);
        } else if (c == 2) {
            dncVar = new dmx(trim, this.f11722a);
        } else if (c == 3) {
            dncVar = new dmy(trim, this.f11722a);
        } else {
            dncVar = new dnc(trim, this.f11722a);
        }
        dncVar.jumpActivity();
    }

    @Override // com.huawei.health.marketrouter.api.MarketRouterApi
    public Map<String, String> getLastMarketSource() {
        return dmw.e();
    }
}
