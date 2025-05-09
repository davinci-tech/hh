package defpackage;

import com.huawei.health.sportservice.download.cloud.VoiceDataApi;

/* loaded from: classes7.dex */
public class fgz {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f12503a = new Object();
    private static volatile VoiceDataApi b;

    public static VoiceDataApi a() {
        if (b == null) {
            synchronized (f12503a) {
                if (b == null) {
                    b = new fgr();
                }
            }
        }
        return b;
    }
}
