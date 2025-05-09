package defpackage;

import net.openid.appauth.Clock;

/* loaded from: classes7.dex */
public class utw implements Clock {
    public static final utw d = new utw();

    private utw() {
    }

    @Override // net.openid.appauth.Clock
    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
