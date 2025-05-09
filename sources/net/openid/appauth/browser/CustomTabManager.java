package net.openid.appauth.browser;

import android.content.Context;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import net.openid.appauth.internal.Logger;

/* loaded from: classes7.dex */
public class CustomTabManager {

    /* renamed from: a, reason: collision with root package name */
    private final WeakReference<Context> f15280a;
    private final AtomicReference<CustomTabsClient> b = new AtomicReference<>();
    private final CountDownLatch d = new CountDownLatch(1);
    private CustomTabsServiceConnection e;

    public CustomTabManager(Context context) {
        this.f15280a = new WeakReference<>(context);
    }

    public void e() {
        synchronized (this) {
            if (this.e == null) {
                return;
            }
            Context context = this.f15280a.get();
            if (context != null) {
                context.unbindService(this.e);
            }
            this.b.set(null);
            Logger.b("CustomTabsService is disconnected", new Object[0]);
        }
    }
}
