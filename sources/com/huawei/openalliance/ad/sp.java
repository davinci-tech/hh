package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.fastengine.fastview.FastSDKEngine;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.tf;

/* loaded from: classes5.dex */
public class sp extends ta {
    private qq c;

    @Override // com.huawei.openalliance.ad.ta
    public boolean a() {
        ho.b("FastAppSdkOpenAction", "handle fast app sdk action.");
        if (d()) {
            return true;
        }
        return b();
    }

    private boolean d() {
        if (!com.huawei.openalliance.ad.utils.ck.a("com.huawei.fastengine.fastview.FastSDKEngine")) {
            ho.c("FastAppSdkOpenAction", "fast sdk not available.");
            return false;
        }
        if (this.f7529a == null || this.b == null) {
            ho.c("FastAppSdkOpenAction", "open fast app param error");
            return false;
        }
        String c = com.huawei.openalliance.ad.utils.cz.c(this.b.G());
        if (TextUtils.isEmpty(c)) {
            ho.c("FastAppSdkOpenAction", "intentUri empty");
            return false;
        }
        FastSDKEngine.LaunchOption launchOption = new FastSDKEngine.LaunchOption();
        launchOption.setShortCutStrategy(0);
        int launchFastAppByDeeplink = FastSDKEngine.launchFastAppByDeeplink(this.f7529a, c, launchOption);
        ho.b("FastAppSdkOpenAction", "launch ret: %s", Integer.valueOf(launchFastAppByDeeplink));
        tf.a aVar = new tf.a();
        aVar.a(this.b).c(Constants.FAST_APP_PKG);
        if (launchFastAppByDeeplink == 0) {
            te.a(this.f7529a, aVar.a());
            this.c.a(EventType.INTENTSUCCESS, (Integer) 2, (Integer) null);
            return true;
        }
        ho.c("FastAppSdkOpenAction", "launchFastAppByDeeplink error");
        new com.huawei.openalliance.ad.analysis.c(this.f7529a).a(aVar.a(), String.valueOf(launchFastAppByDeeplink));
        return false;
    }

    public sp(Context context, ContentRecord contentRecord) {
        super(context, contentRecord);
        ou ouVar = new ou(context, sc.a(context, contentRecord.e()));
        this.c = ouVar;
        ouVar.a(contentRecord);
    }
}
