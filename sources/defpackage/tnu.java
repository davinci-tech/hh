package defpackage;

import android.content.Context;
import com.huawei.wearengine.client.ServiceConnectionListener;

/* loaded from: classes7.dex */
public final class tnu {
    public static tpc c(Context context) {
        top.a(context, "Context must not be null!");
        trr.e(context);
        return tpc.d();
    }

    public static tpx b(Context context) {
        top.a(context, "Context must not be null!");
        trr.e(context);
        return tpx.a();
    }

    public static toa e(Context context) {
        top.a(context, "Context must not be null!");
        trr.e(context);
        return toa.d();
    }

    public static tph a(Context context) {
        top.a(context, "Context must not be null!");
        trr.e(context);
        return tph.c();
    }

    public static tpu d(Context context) {
        top.a(context, "Context must not be null!");
        trr.e(context);
        return tpu.e();
    }

    public static toi b(Context context, ServiceConnectionListener serviceConnectionListener) {
        top.a(context, "Context must not be null!");
        top.a(serviceConnectionListener, "Listener must not be null!");
        trr.e(context);
        return toi.b(serviceConnectionListener);
    }
}
