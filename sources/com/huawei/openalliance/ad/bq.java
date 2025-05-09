package com.huawei.openalliance.ad;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.openalliance.ad.beans.metadata.v3.Asset;
import com.huawei.openalliance.ad.beans.metadata.v3.MotionData;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.ContentRecord;

/* loaded from: classes5.dex */
public class bq {
    /* JADX INFO: Access modifiers changed from: private */
    public static rt b(MotionData motionData) {
        if (motionData == null) {
            return null;
        }
        rt rtVar = new rt();
        rtVar.c(motionData.g());
        rtVar.b(motionData.h());
        rtVar.b(true);
        rtVar.c(true);
        rtVar.d(Constants.TPLATE_CACHE);
        return rtVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static rt b(Asset asset) {
        if (asset == null || asset.e() == null) {
            return null;
        }
        rt rtVar = new rt();
        rtVar.c(asset.e().a());
        rtVar.b(asset.e().f());
        rtVar.b(asset.e().h() == 0);
        rtVar.c(true);
        rtVar.a(Constants.TEMPLATE_VIDEO_SUB_DIR);
        rtVar.a(true);
        return rtVar;
    }

    public static boolean a(Context context, ContentRecord contentRecord) {
        String str;
        ho.a("TDownloadUtil", "checkAndDownloadContent");
        if (contentRecord == null) {
            str = "content is null";
        } else {
            if (!com.huawei.openalliance.ad.utils.bg.a(contentRecord.aV())) {
                dk a2 = dh.a(context, Constants.TPLATE_CACHE);
                final fx a3 = fb.a(context);
                boolean z = true;
                for (final Asset asset : contentRecord.aV()) {
                    if (asset != null && asset.e() != null && !com.huawei.openalliance.ad.utils.ae.b(a2.c(a2.e(asset.e().a())))) {
                        com.huawei.openalliance.ad.utils.m.d(new Runnable() { // from class: com.huawei.openalliance.ad.bq.1
                            @Override // java.lang.Runnable
                            public void run() {
                                rt b = bq.b(Asset.this);
                                if (b != null) {
                                    b.c(true);
                                    b.d(Constants.TPLATE_CACHE);
                                    ru a4 = a3.a(b);
                                    if (a4 == null || TextUtils.isEmpty(a4.a())) {
                                        ho.b("TDownloadUtil", "down asset failed");
                                    }
                                }
                            }
                        });
                        z = false;
                    }
                }
                if (contentRecord.aT() != null && !com.huawei.openalliance.ad.utils.bg.a(contentRecord.aT().c())) {
                    for (final MotionData motionData : contentRecord.aT().c()) {
                        if (motionData != null && !com.huawei.openalliance.ad.utils.ae.b(a2.c(a2.e(motionData.g())))) {
                            com.huawei.openalliance.ad.utils.m.d(new Runnable() { // from class: com.huawei.openalliance.ad.bq.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    ru a4 = a3.a(bq.b(MotionData.this));
                                    if (a4 == null || TextUtils.isEmpty(a4.a())) {
                                        ho.b("TDownloadUtil", "down motion failed %s", a4);
                                    }
                                }
                            });
                            z = false;
                        }
                    }
                }
                ho.b("TDownloadUtil", "result: %s", Boolean.valueOf(z));
                return z;
            }
            str = "assets is null";
        }
        ho.b("TDownloadUtil", str);
        return false;
    }
}
