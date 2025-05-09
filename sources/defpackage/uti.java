package defpackage;

import net.openid.appauth.browser.BrowserMatcher;
import net.openid.appauth.connectivity.ConnectionBuilder;

/* loaded from: classes7.dex */
public class uti {
    public static final uti d = new c().e();
    private final boolean b;
    private final ConnectionBuilder c;
    private final BrowserMatcher e;

    private uti(BrowserMatcher browserMatcher, ConnectionBuilder connectionBuilder, Boolean bool) {
        this.e = browserMatcher;
        this.c = connectionBuilder;
        this.b = bool.booleanValue();
    }

    public BrowserMatcher c() {
        return this.e;
    }

    public ConnectionBuilder e() {
        return this.c;
    }

    public boolean a() {
        return this.b;
    }

    public static class c {
        private boolean b;

        /* renamed from: a, reason: collision with root package name */
        private BrowserMatcher f17545a = uud.d;
        private ConnectionBuilder c = uua.e;

        public c b(BrowserMatcher browserMatcher) {
            utq.d(browserMatcher, "browserMatcher cannot be null");
            this.f17545a = browserMatcher;
            return this;
        }

        public c c(ConnectionBuilder connectionBuilder) {
            utq.d(connectionBuilder, "connectionBuilder cannot be null");
            this.c = connectionBuilder;
            return this;
        }

        public uti e() {
            return new uti(this.f17545a, this.c, Boolean.valueOf(this.b));
        }
    }
}
