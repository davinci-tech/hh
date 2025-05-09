package com.huawei.openalliance.ad.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.LruCache;
import com.huawei.openalliance.ad.ho;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class ay {
    private static final String b = "ay";
    private static ay c;
    private static final byte[] d = new byte[0];

    /* renamed from: a, reason: collision with root package name */
    LruCache<String, WeakReference<Drawable>> f7619a;

    public void a(String str, Drawable drawable) {
        try {
            this.f7619a.put(str, new WeakReference<>(drawable));
        } catch (Throwable th) {
            ho.c(b, "put cache encounter: " + th.getClass().getSimpleName());
        }
    }

    public Drawable a(String str) {
        try {
            WeakReference<Drawable> weakReference = this.f7619a.get(str);
            if (weakReference != null) {
                return weakReference.get();
            }
        } catch (Throwable th) {
            ho.c(b, "get cache encounter: " + th.getClass().getSimpleName());
        }
        return null;
    }

    private void b() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        this.f7619a = new LruCache<String, WeakReference<Drawable>>(Math.min(31457280, maxMemory > 0 ? maxMemory / 4 : 31457280)) { // from class: com.huawei.openalliance.ad.utils.ay.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.util.LruCache
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int sizeOf(String str, WeakReference<Drawable> weakReference) {
                Drawable drawable;
                if (weakReference == null || (drawable = weakReference.get()) == null) {
                    return 1;
                }
                if (!(drawable instanceof BitmapDrawable)) {
                    if (drawable instanceof com.huawei.openalliance.ad.views.gif.b) {
                        return ((com.huawei.openalliance.ad.views.gif.b) drawable).c();
                    }
                    return 1;
                }
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                if (bitmap != null) {
                    return bitmap.getByteCount();
                }
                return 1;
            }
        };
    }

    public static ay a() {
        ay ayVar;
        synchronized (d) {
            if (c == null) {
                c = new ay();
            }
            ayVar = c;
        }
        return ayVar;
    }

    private ay() {
        b();
    }
}
