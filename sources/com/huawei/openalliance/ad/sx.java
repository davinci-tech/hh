package com.huawei.openalliance.ad;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.openalliance.ad.constant.ClickDestination;
import com.huawei.openalliance.ad.constant.IntentFailError;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.tf;

/* loaded from: classes5.dex */
public class sx extends ta {
    @Override // com.huawei.openalliance.ad.ta
    public boolean a() {
        Context context;
        String str;
        if (this.b == null || !(os.b(this.b.V()) || com.huawei.openalliance.ad.utils.bv.e(this.f7529a))) {
            return b();
        }
        ho.b("OuterWebAction", "handle outer browser action");
        Intent intent = new Intent();
        intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
        String A = this.b.A();
        if (!com.huawei.openalliance.ad.utils.cz.b(A)) {
            intent.setData(Uri.parse(A));
            if (!(this.f7529a instanceof Activity)) {
                intent.addFlags(268435456);
            }
            tf.a aVar = new tf.a();
            aVar.a(this.b).a(intent);
            tf a2 = aVar.a();
            try {
                if (os.c(this.b.V())) {
                    ho.a("OuterWebAction", "handleUri, use default browser");
                    String d = d();
                    if (TextUtils.isEmpty(d)) {
                        ho.c("OuterWebAction", "can not find default browser");
                    } else {
                        intent.setPackage(d);
                    }
                }
                PackageManager packageManager = this.f7529a.getPackageManager();
                if (packageManager == null) {
                    context = this.f7529a;
                    str = IntentFailError.ACTIVITY_NOT_FOUND;
                } else {
                    if (!packageManager.queryIntentActivities(intent, 65536).isEmpty()) {
                        b(ClickDestination.WEB);
                        com.huawei.openalliance.ad.utils.i.a(this.f7529a, intent, a2);
                        return true;
                    }
                    context = this.f7529a;
                    str = IntentFailError.CAN_NOT_GET_PKG_MANAGER;
                }
                com.huawei.openalliance.ad.utils.cl.a(context, a2, str);
            } catch (ActivityNotFoundException unused) {
                com.huawei.openalliance.ad.utils.cl.a(this.f7529a, a2, IntentFailError.ACTIVITY_NOT_FOUND_EXP);
                ho.d("OuterWebAction", "fail to open uri");
            } catch (Throwable th) {
                com.huawei.openalliance.ad.utils.cl.a(this.f7529a, a2, "unknown exception : " + th.getClass().getSimpleName());
                ho.d("OuterWebAction", "handle uri exception: %s", th.getClass().getSimpleName());
            }
        }
        return b();
    }

    private String d() {
        for (String str : fh.b(this.f7529a).bk()) {
            if (com.huawei.openalliance.ad.utils.i.a(this.f7529a, str)) {
                return str;
            }
        }
        return "";
    }

    public sx(Context context, ContentRecord contentRecord) {
        super(context, contentRecord);
    }
}
