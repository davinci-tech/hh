package defpackage;

import com.huawei.hwcloudmodel.model.unite.RouteData;
import com.huawei.route.TrackInfoModel;

/* loaded from: classes3.dex */
public class btq {

    /* renamed from: a, reason: collision with root package name */
    private static RouteData f504a;
    private static TrackInfoModel c;

    public static RouteData e() {
        return f504a;
    }

    public static void c(RouteData routeData) {
        f504a = routeData;
    }

    public static TrackInfoModel c() {
        return c;
    }

    public static void d(TrackInfoModel trackInfoModel) {
        c = trackInfoModel;
    }

    public static void a() {
        if (f504a != null) {
            f504a = null;
        }
    }

    public static void d() {
        if (c != null) {
            c = null;
        }
    }
}
