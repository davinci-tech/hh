package okhttp3.internal.connection;

import com.huawei.hms.network.embedded.y;
import defpackage.uhy;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import okhttp3.Route;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0005J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u0005R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokhttp3/internal/connection/RouteDatabase;", "", "()V", "failedRoutes", "", "Lokhttp3/Route;", "connected", "", "route", "failed", "failedRoute", "shouldPostpone", "", y.b.j}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RouteDatabase {
    private final Set<Route> failedRoutes = new LinkedHashSet();

    public final void failed(Route failedRoute) {
        synchronized (this) {
            uhy.e((Object) failedRoute, "");
            this.failedRoutes.add(failedRoute);
        }
    }

    public final void connected(Route route) {
        synchronized (this) {
            uhy.e((Object) route, "");
            this.failedRoutes.remove(route);
        }
    }

    public final boolean shouldPostpone(Route route) {
        boolean contains;
        synchronized (this) {
            uhy.e((Object) route, "");
            contains = this.failedRoutes.contains(route);
        }
        return contains;
    }
}
