package com.huawei.phoneservice.feedbackcommon.network;

import android.content.Context;
import com.huawei.hms.framework.network.restclient.hwhttp.Callback;
import com.huawei.hms.framework.network.restclient.hwhttp.Submit;
import com.huawei.phoneservice.faq.base.network.FaqRestClient;
import defpackage.uhy;
import defpackage.uib;

/* loaded from: classes9.dex */
public final class FaqDownloadManager extends FaqRestClient {
    private static volatile FaqDownloadManager b;
    public static final c c = new c(null);
    private static Context e;
    private Context d;

    public final Submit a(String str, String str2, Callback callback) {
        uhy.e((Object) str, "");
        uhy.e((Object) str2, "");
        uhy.e((Object) callback, "");
        FaqRestClient initRestClientAnno = FaqRestClient.Companion.initRestClientAnno(this.d);
        uhy.d(initRestClientAnno);
        return initRestClientAnno.downloadFile(e, str, str2, callback);
    }

    public static final class c {
        public final FaqDownloadManager e(Context context) {
            FaqDownloadManager.e = context != null ? context.getApplicationContext() : null;
            if (FaqDownloadManager.b == null) {
                FaqDownloadManager.b = new FaqDownloadManager(FaqDownloadManager.e);
            }
            return FaqDownloadManager.b;
        }

        public /* synthetic */ c(uib uibVar) {
            this();
        }

        private c() {
        }
    }

    public FaqDownloadManager(Context context) {
        super(context);
        this.d = context;
    }
}
