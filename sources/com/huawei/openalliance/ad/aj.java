package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.hms.ads.jsb.inner.data.AppDownloadInfo;
import com.huawei.openalliance.ad.an;
import com.huawei.openalliance.ad.ipc.RemoteCallResultCallback;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
public class aj extends ag {

    static class a implements com.huawei.openalliance.ad.download.k {

        /* renamed from: a, reason: collision with root package name */
        private static final byte[] f6620a = new byte[0];
        private static a b;
        private String c;
        private Map<String, RemoteCallResultCallback<String>> d = Collections.synchronizedMap(new an.e(5));

        @Override // com.huawei.openalliance.ad.download.k
        public void a(String str, int i) {
            Map<String, RemoteCallResultCallback<String>> map = this.d;
            if (map == null || map.size() <= 0) {
                return;
            }
            Iterator<RemoteCallResultCallback<String>> it = this.d.values().iterator();
            while (it.hasNext()) {
                j.a(it.next(), this.c, 1000, com.huawei.openalliance.ad.utils.be.b(new AppDownloadInfo(str, i)), false);
            }
        }

        public void a(RemoteCallResultCallback<String> remoteCallResultCallback, String str, String str2) {
            this.d.put(str2, remoteCallResultCallback);
            this.c = str;
        }

        public static a a(Context context) {
            a aVar;
            synchronized (f6620a) {
                if (b == null) {
                    b = new a(context);
                }
                aVar = b;
            }
            return aVar;
        }

        private a(Context context) {
            ho.b("JsbOnAgReserveStatusChange", "listener init");
            com.huawei.openalliance.ad.download.ag.e.a(context).a(this);
        }
    }

    @Override // com.huawei.openalliance.ad.j, com.huawei.openalliance.ad.g
    public void a(Context context, String str, RemoteCallResultCallback<String> remoteCallResultCallback) {
        a.a(context).a(remoteCallResultCallback, this.f7108a, this.c);
    }

    public aj() {
        super("pps.listener.appreservestatus");
    }
}
