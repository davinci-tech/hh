package defpackage;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.featuremarketing.route.IMarketRouteHelper;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class dmx implements IMarketRouteHelper {

    /* renamed from: a, reason: collision with root package name */
    private IBaseResponseCallback f11723a;
    private String c;

    public dmx(String str, IBaseResponseCallback iBaseResponseCallback) {
        this.c = str;
        this.f11723a = iBaseResponseCallback;
    }

    @Override // com.huawei.health.featuremarketing.route.IMarketRouteHelper
    public void jumpActivity() {
        if (TextUtils.isEmpty(this.c)) {
            LogUtil.h("QuickAppRouterHelper", "mLinkUrl is null");
            IBaseResponseCallback iBaseResponseCallback = this.f11723a;
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(-1, null);
                return;
            }
            return;
        }
        mxv.d(BaseApplication.e(), Uri.parse(nsa.g(this.c)).getQueryParameter("pName"));
        IBaseResponseCallback iBaseResponseCallback2 = this.f11723a;
        if (iBaseResponseCallback2 != null) {
            iBaseResponseCallback2.d(0, null);
        }
    }
}
