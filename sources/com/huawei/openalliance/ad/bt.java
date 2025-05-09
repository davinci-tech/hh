package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.hms.ads.uiengine.b;
import com.huawei.openalliance.ad.media.IMultiMediaPlayingManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class bt extends b.a {

    /* renamed from: a, reason: collision with root package name */
    private static bt f6652a;
    private static final byte[] b = new byte[0];
    private Context c;
    private IMultiMediaPlayingManager d;
    private final Map<Long, bs> e = new HashMap();

    @Override // com.huawei.hms.ads.uiengine.b
    public void d(String str, com.huawei.hms.ads.uiengine.a aVar) {
        ho.b("MultiMPlayingManagerPro", "pause %s", c(aVar));
        IMultiMediaPlayingManager iMultiMediaPlayingManager = this.d;
        if (iMultiMediaPlayingManager != null) {
            iMultiMediaPlayingManager.pause(str, d(aVar));
        }
    }

    @Override // com.huawei.hms.ads.uiengine.b
    public void c(String str, com.huawei.hms.ads.uiengine.a aVar) {
        ho.b("MultiMPlayingManagerPro", "stop %s", c(aVar));
        IMultiMediaPlayingManager iMultiMediaPlayingManager = this.d;
        if (iMultiMediaPlayingManager != null) {
            iMultiMediaPlayingManager.stop(str, d(aVar));
        }
    }

    @Override // com.huawei.hms.ads.uiengine.b
    public void b(String str, com.huawei.hms.ads.uiengine.a aVar) {
        ho.b("MultiMPlayingManagerPro", "manualPlay %s", c(aVar));
        IMultiMediaPlayingManager iMultiMediaPlayingManager = this.d;
        if (iMultiMediaPlayingManager != null) {
            iMultiMediaPlayingManager.manualPlay(str, d(aVar));
        }
    }

    @Override // com.huawei.hms.ads.uiengine.b
    public void b(com.huawei.hms.ads.uiengine.a aVar) {
        ho.b("MultiMPlayingManagerPro", "removeListeners %s", c(aVar));
        bs d = d(aVar);
        IMultiMediaPlayingManager iMultiMediaPlayingManager = this.d;
        if (iMultiMediaPlayingManager != null) {
            iMultiMediaPlayingManager.removeListenersForMediaPlayerAgent(d);
        }
    }

    @Override // com.huawei.hms.ads.uiengine.b
    public void a(String str, com.huawei.hms.ads.uiengine.a aVar) {
        ho.b("MultiMPlayingManagerPro", "autoPlay %s", c(aVar));
        IMultiMediaPlayingManager iMultiMediaPlayingManager = this.d;
        if (iMultiMediaPlayingManager != null) {
            iMultiMediaPlayingManager.autoPlay(str, d(aVar));
        }
    }

    public void a(IMultiMediaPlayingManager iMultiMediaPlayingManager) {
        this.d = iMultiMediaPlayingManager;
    }

    @Override // com.huawei.hms.ads.uiengine.b
    public void a(com.huawei.hms.ads.uiengine.a aVar) {
        Long c = c(aVar);
        ho.b("MultiMPlayingManagerPro", "removeAgent %s", c);
        IMultiMediaPlayingManager iMultiMediaPlayingManager = this.d;
        if (iMultiMediaPlayingManager != null) {
            iMultiMediaPlayingManager.removeMediaPlayerAgent(d(aVar));
        }
        if (c != null) {
            this.e.remove(c);
        }
    }

    private bs d(com.huawei.hms.ads.uiengine.a aVar) {
        bs bsVar;
        try {
            long a2 = aVar.a();
            if (this.e.containsKey(Long.valueOf(a2))) {
                bsVar = this.e.get(Long.valueOf(a2));
            } else {
                bs bsVar2 = new bs(this.c, aVar);
                this.e.put(Long.valueOf(a2), bsVar2);
                bsVar = bsVar2;
            }
            if (ho.a()) {
                ho.a("MultiMPlayingManagerPro", "getProxy = %s, proxy = %s", Long.valueOf(a2), bsVar);
            }
            return bsVar;
        } catch (Throwable th) {
            ho.b("MultiMPlayingManagerPro", "getProxy err: %s", th.getClass().getSimpleName());
            return null;
        }
    }

    private Long c(com.huawei.hms.ads.uiengine.a aVar) {
        if (aVar == null) {
            return null;
        }
        try {
            return Long.valueOf(aVar.a());
        } catch (Throwable th) {
            ho.b("MultiMPlayingManagerPro", "get id err: %s", th.getClass().getSimpleName());
            return null;
        }
    }

    private static bt b(Context context) {
        bt btVar;
        synchronized (b) {
            if (f6652a == null) {
                f6652a = new bt(context);
            }
            btVar = f6652a;
        }
        return btVar;
    }

    public static bt a(Context context) {
        return b(context);
    }

    private bt(Context context) {
        this.c = context;
    }
}
