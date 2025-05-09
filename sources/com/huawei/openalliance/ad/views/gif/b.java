package com.huawei.openalliance.ad.views.gif;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Gravity;
import com.huawei.openalliance.ad.constant.Scheme;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.al;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.utils.dl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes5.dex */
public class b extends Drawable implements Animatable, Drawable.Callback {
    private Paint f;
    private String i;
    private int l;
    private int m;
    private a o;
    private Context p;
    private c r;
    private boolean s;
    private al t;
    private d v;

    /* renamed from: a, reason: collision with root package name */
    private final String f8077a = "render_frame" + hashCode();
    private Canvas b = new Canvas();
    private Rect c = new Rect();
    private Rect d = new Rect();
    private Rect e = new Rect();
    private boolean g = false;
    private int h = 0;
    private Queue<c> j = new ConcurrentLinkedQueue();
    private Queue<Bitmap> k = new ConcurrentLinkedQueue();
    private boolean n = false;
    private long q = 0;
    private final WeakHashMap<Drawable.Callback, Void> u = new WeakHashMap<>();

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        for (Drawable.Callback callback : this.u.keySet()) {
            if (callback != null) {
                callback.unscheduleDrawable(drawable, runnable);
            }
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        ho.b(o(), "stop");
        this.g = false;
        b();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        ho.b(o(), "start");
        this.g = true;
        a();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        ho.b(o(), "setVisible " + z);
        if (!z) {
            stop();
        } else if (!this.g) {
            start();
        }
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        d().setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        d().setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        for (Drawable.Callback callback : this.u.keySet()) {
            if (callback != null) {
                callback.scheduleDrawable(drawable, runnable, j);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.s = true;
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.g;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        for (Drawable.Callback callback : this.u.keySet()) {
            if (callback != null) {
                callback.invalidateDrawable(drawable);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        c cVar = this.r;
        return cVar != null ? cVar.b.getWidth() : super.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        c cVar = this.r;
        return cVar != null ? cVar.b.getHeight() : super.getIntrinsicHeight();
    }

    protected void finalize() {
        super.finalize();
        this.t.b();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        c cVar = this.r;
        if (cVar == null || cVar.b == null) {
            return;
        }
        if (ho.a()) {
            ho.a(o(), "draw frame: %d", Integer.valueOf(this.r.f8086a));
        }
        if (this.s) {
            Gravity.apply(119, getIntrinsicWidth(), getIntrinsicHeight(), getBounds(), this.c);
            this.s = false;
        }
        canvas.drawBitmap(this.r.b, (Rect) null, this.c, d());
    }

    public int c() {
        int size = (this.k.size() + this.j.size()) * getIntrinsicWidth() * getIntrinsicHeight() * 4;
        if (size > 0) {
            return size;
        }
        return 1;
    }

    public void b() {
        ho.b(o(), "stop play " + dl.a(this.i));
        dj.a(this.f8077a);
        a(true);
        this.j.clear();
        this.t.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.gif.b.1
            @Override // java.lang.Runnable
            public void run() {
                b.this.g();
            }
        });
    }

    public void a(d dVar) {
        this.v = dVar;
    }

    public void a() {
        if (TextUtils.isEmpty(this.i)) {
            return;
        }
        ho.b(o(), "play " + dl.a(this.i));
        b();
        f();
        a(this.i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String o() {
        return "GifDrawable_" + hashCode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        ho.b(o(), "on play end");
        m();
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.gif.b.8
            @Override // java.lang.Runnable
            public void run() {
                if (b.this.v != null) {
                    b.this.v.c();
                }
            }
        });
    }

    private void m() {
        this.k.clear();
    }

    private void l() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.gif.b.6
            @Override // java.lang.Runnable
            public void run() {
                if (b.this.v != null) {
                    b.this.v.a();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.gif.b.5
            @Override // java.lang.Runnable
            public void run() {
                b.i(b.this);
                if (b.this.h != 0 && b.this.l >= b.this.h) {
                    b.this.b();
                    b.this.n();
                } else {
                    b.this.e();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        final a aVar;
        if (h() || (aVar = this.o) == null || aVar.c()) {
            return;
        }
        this.t.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.gif.b.4
            @Override // java.lang.Runnable
            public void run() {
                ho.b(b.this.o(), "fetch next");
                long currentTimeMillis = System.currentTimeMillis();
                c a2 = aVar.a();
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                ho.a(b.this.o(), "frame fetch - decoding duration: %d gif: %s", Long.valueOf(currentTimeMillis2), a2);
                b bVar = b.this;
                if (a2 == null) {
                    c cVar = (c) bVar.j.poll();
                    if (cVar == null) {
                        long currentTimeMillis3 = System.currentTimeMillis() - b.this.q;
                        if (currentTimeMillis3 < b.this.m) {
                            try {
                                Thread.sleep(b.this.m - currentTimeMillis3);
                            } catch (InterruptedException unused) {
                                ho.a(b.this.o(), "InterruptedException");
                            }
                        }
                        b.this.k();
                        return;
                    }
                    b.this.a(cVar);
                    return;
                }
                boolean a3 = bVar.a(a2, currentTimeMillis2);
                ho.a(b.this.o(), "need reduce size: %s", Boolean.valueOf(a3));
                c a4 = a2.a();
                a4.b = b.this.a(a2.b, a3);
                if (!b.this.j.offer(a4)) {
                    ho.c(b.this.o(), "fail to add frame to cache");
                }
                if (currentTimeMillis2 <= a4.c) {
                    ho.b(b.this.o(), "send to render directly");
                } else {
                    int i = (int) ((currentTimeMillis2 * 1.0d) / a4.c);
                    if (i > 5) {
                        i = 5;
                    }
                    ho.a(b.this.o(), "preferred cached frame num: %d", Integer.valueOf(i));
                    if (b.this.j.size() < i) {
                        b.this.j();
                        return;
                    }
                }
                b bVar2 = b.this;
                bVar2.a((c) bVar2.j.poll());
            }
        });
    }

    private void i() {
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.gif.b.3
            @Override // java.lang.Runnable
            public void run() {
                if (b.this.v != null) {
                    b.this.v.b();
                }
                b.this.b();
            }
        });
    }

    static /* synthetic */ int i(b bVar) {
        int i = bVar.l;
        bVar.l = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean h() {
        boolean z;
        synchronized (this) {
            z = this.n;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        a aVar = this.o;
        if (aVar != null) {
            aVar.b();
            this.o = null;
        }
    }

    private void f() {
        a(false);
        this.l = 0;
        this.j.clear();
    }

    private InputStream f(String str) {
        try {
            return this.p.getAssets().open(str.substring(Scheme.ASSET.toString().length()));
        } catch (IOException e) {
            ho.c(o(), "loadFile " + e.getClass().getSimpleName());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        ho.b(o(), "replay " + dl.a(this.i));
        a(this.i);
    }

    private InputStream e(String str) {
        String o;
        StringBuilder sb;
        try {
            return this.p.getResources().openRawResource(Integer.parseInt(str.substring(Scheme.RES.toString().length())));
        } catch (Resources.NotFoundException e) {
            e = e;
            o = o();
            sb = new StringBuilder("loadFile ");
            sb.append(e.getClass().getSimpleName());
            ho.c(o, sb.toString());
            return null;
        } catch (NumberFormatException e2) {
            e = e2;
            o = o();
            sb = new StringBuilder("loadFile ");
            sb.append(e.getClass().getSimpleName());
            ho.c(o, sb.toString());
            return null;
        }
    }

    private InputStream d(String str) {
        try {
            return new FileInputStream(new File(str));
        } catch (FileNotFoundException e) {
            ho.c(o(), "loadFile " + e.getClass().getSimpleName());
            return null;
        }
    }

    private Paint d() {
        if (this.f == null) {
            this.f = new Paint(2);
        }
        return this.f;
    }

    private void c(c cVar) {
        if (cVar == null || this.k.size() >= 2) {
            ho.b(o(), "drop frame");
        } else {
            if (this.k.contains(cVar.b) || this.k.offer(cVar.b)) {
                return;
            }
            ho.c(o(), "fail to release frame to pool");
        }
    }

    private InputStream c(String str) {
        try {
            return this.p.getContentResolver().openInputStream(Uri.parse(str));
        } catch (FileNotFoundException e) {
            ho.c(o(), "oPIs " + e.getClass().getSimpleName());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        g();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        InputStream f = str.startsWith(Scheme.ASSET.toString()) ? f(str) : str.startsWith(Scheme.RES.toString()) ? e(str) : str.startsWith(Scheme.CONTENT.toString()) ? c(str) : d(str);
        if (f != null) {
            try {
                this.o = new a(f, 100);
                j();
            } catch (Exception unused) {
                ho.c(o(), "exception in creating gif decoder");
                i();
            }
        }
    }

    private void b(c cVar) {
        c(this.r);
        this.r = cVar;
        this.m = cVar.c;
        dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.gif.b.7
            @Override // java.lang.Runnable
            public void run() {
                if (!b.this.h()) {
                    b.this.invalidateSelf();
                    b.this.j();
                } else {
                    b.this.r = null;
                }
            }
        }, this.f8077a, 0L);
        this.q = System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(c cVar, long j) {
        int i;
        int i2 = cVar.b.getConfig() == Bitmap.Config.RGB_565 ? 2 : 4;
        long width = cVar.b.getWidth();
        long height = cVar.b.getHeight();
        long j2 = i2;
        if (j > cVar.c) {
            i = (int) Math.ceil((j * 1.0d) / cVar.c);
            if (i > 5) {
                i = 5;
            }
        } else {
            i = 1;
        }
        long max = width * height * j2 * Math.max(i, this.j.size());
        long b = ao.b();
        if (ho.a()) {
            ho.a(o(), "max frame mem: %d unused memory: %d", Long.valueOf(max), Long.valueOf(b));
        }
        return max >= b;
    }

    private void a(boolean z) {
        synchronized (this) {
            this.n = z;
        }
    }

    private void a(final String str) {
        this.t.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.gif.b.2
            @Override // java.lang.Runnable
            public void run() {
                b.this.b(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(c cVar) {
        if (cVar == null) {
            ho.b(o(), "invalid frame.");
            return;
        }
        ho.b(o(), "onFrameDecoded index: %d isstop: %s", Integer.valueOf(cVar.f8086a), Boolean.valueOf(h()));
        if (h()) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.q;
        if (ho.a()) {
            ho.a(o(), "onFrameDecoded decodeInterval: %d currentFrameDuration: %d", Long.valueOf(currentTimeMillis), Integer.valueOf(this.m));
        }
        if (cVar.f8086a == 1) {
            l();
        } else {
            long j = this.m;
            if (currentTimeMillis < j) {
                try {
                    Thread.sleep(j - currentTimeMillis);
                } catch (InterruptedException unused) {
                    ho.a(o(), "sleep InterruptedException");
                }
            }
        }
        b(cVar);
    }

    private void a(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap2 != null) {
            this.b.setBitmap(bitmap2);
            this.b.drawColor(0, PorterDuff.Mode.CLEAR);
            this.d.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            this.e.set(0, 0, bitmap2.getWidth(), bitmap2.getHeight());
            this.b.drawBitmap(bitmap, this.d, this.e, (Paint) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x003e, code lost:
    
        if (r8 > 640) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0046, code lost:
    
        r1 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0047, code lost:
    
        r2 = (int) (((r1 * r0) * 1.0f) / r8);
        com.huawei.openalliance.ad.ho.b(o(), "reduce image size to w: %d, h: %d src w: %d, h: %d", java.lang.Integer.valueOf(r1), java.lang.Integer.valueOf(r2), java.lang.Integer.valueOf(r8), java.lang.Integer.valueOf(r0));
        r0 = android.graphics.Bitmap.createBitmap(r1, r2, android.graphics.Bitmap.Config.RGB_565);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0043, code lost:
    
        if (r8 > 960) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.graphics.Bitmap a(android.graphics.Bitmap r7, boolean r8) {
        /*
            r6 = this;
            boolean r0 = com.huawei.openalliance.ad.ho.a()
            if (r0 == 0) goto L1d
            java.lang.String r0 = r6.o()
            java.util.Queue<android.graphics.Bitmap> r1 = r6.k
            int r1 = r1.size()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            java.lang.String r2 = "image pool size: %d"
            com.huawei.openalliance.ad.ho.a(r0, r2, r1)
        L1d:
            java.util.Queue<android.graphics.Bitmap> r0 = r6.k
            java.lang.Object r0 = r0.poll()
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0
            if (r0 != 0) goto L7e
            java.lang.String r0 = r6.o()
            java.lang.String r1 = "cache bitmap null"
            com.huawei.openalliance.ad.ho.b(r0, r1)
            if (r8 == 0) goto L74
            int r8 = r7.getWidth()
            int r0 = r7.getHeight()
            if (r8 >= r0) goto L41
            r1 = 640(0x280, float:8.97E-43)
            if (r8 <= r1) goto L46
            goto L47
        L41:
            r1 = 960(0x3c0, float:1.345E-42)
            if (r8 <= r1) goto L46
            goto L47
        L46:
            r1 = r8
        L47:
            int r2 = r1 * r0
            float r2 = (float) r2
            r3 = 1065353216(0x3f800000, float:1.0)
            float r2 = r2 * r3
            float r3 = (float) r8
            float r2 = r2 / r3
            int r2 = (int) r2
            java.lang.String r3 = r6.o()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r1)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r2)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Object[] r8 = new java.lang.Object[]{r4, r5, r8, r0}
            java.lang.String r0 = "reduce image size to w: %d, h: %d src w: %d, h: %d"
            com.huawei.openalliance.ad.ho.b(r3, r0, r8)
            android.graphics.Bitmap$Config r8 = android.graphics.Bitmap.Config.RGB_565
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r1, r2, r8)
            goto L7e
        L74:
            android.graphics.Bitmap$Config r8 = r7.getConfig()
            r0 = 1
            android.graphics.Bitmap r7 = r7.copy(r8, r0)
            return r7
        L7e:
            r6.a(r7, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.views.gif.b.a(android.graphics.Bitmap, boolean):android.graphics.Bitmap");
    }

    public b(Context context, String str) {
        this.p = context.getApplicationContext();
        this.i = str;
        al alVar = new al("gif-thread");
        this.t = alVar;
        alVar.a();
        setCallback(this);
    }
}
