package com.amap.api.col.p0003sl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import java.io.File;
import java.io.InputStream;

/* loaded from: classes2.dex */
public final class ej extends View {

    /* renamed from: a, reason: collision with root package name */
    private Bitmap f1007a;
    private Bitmap b;
    private Bitmap c;
    private Bitmap d;
    private Bitmap e;
    private Bitmap f;
    private Bitmap g;
    private Paint h;
    private boolean i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private boolean r;
    private boolean s;
    private Context t;
    private boolean u;
    private float v;
    private float w;
    private boolean x;
    private boolean y;

    public final void a() {
        try {
            Bitmap bitmap = this.f1007a;
            if (bitmap != null) {
                dv.a(bitmap);
                this.f1007a = null;
            }
            Bitmap bitmap2 = this.b;
            if (bitmap2 != null) {
                dv.a(bitmap2);
                this.b = null;
            }
            this.f1007a = null;
            this.b = null;
            Bitmap bitmap3 = this.f;
            if (bitmap3 != null) {
                dv.a(bitmap3);
                this.f = null;
            }
            Bitmap bitmap4 = this.g;
            if (bitmap4 != null) {
                dv.a(bitmap4);
                this.g = null;
            }
            Bitmap bitmap5 = this.c;
            if (bitmap5 != null) {
                dv.a(bitmap5);
            }
            this.c = null;
            Bitmap bitmap6 = this.d;
            if (bitmap6 != null) {
                dv.a(bitmap6);
            }
            this.d = null;
            Bitmap bitmap7 = this.e;
            if (bitmap7 != null) {
                bitmap7.recycle();
            }
            this.h = null;
        } catch (Throwable th) {
            iv.c(th, "WaterMarkerView", "destory");
            th.printStackTrace();
        }
    }

    public ej(Context context) {
        super(context);
        InputStream inputStream;
        this.h = new Paint();
        this.i = false;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 10;
        this.n = 0;
        this.o = 0;
        this.p = 10;
        this.q = 8;
        this.r = false;
        this.s = false;
        this.u = true;
        this.v = 0.0f;
        this.w = 0.0f;
        this.x = true;
        this.y = false;
        InputStream inputStream2 = null;
        try {
            this.t = context.getApplicationContext();
            InputStream open = dp.a(context).open("ap.data");
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(open);
                this.f = decodeStream;
                this.f1007a = dv.a(decodeStream, v.f1366a);
                open.close();
                inputStream2 = dp.a(context).open("ap1.data");
                Bitmap decodeStream2 = BitmapFactory.decodeStream(inputStream2);
                this.g = decodeStream2;
                this.b = dv.a(decodeStream2, v.f1366a);
                inputStream2.close();
                this.k = this.b.getWidth();
                this.j = this.b.getHeight();
                this.h.setAntiAlias(true);
                this.h.setColor(-16777216);
                this.h.setStyle(Paint.Style.STROKE);
                AMapEngineUtils.LOGO_CUSTOM_ICON_DAY_NAME = context.getFilesDir() + "/icon_web_day.data";
                AMapEngineUtils.LOGO_CUSTOM_ICON_NIGHT_NAME = context.getFilesDir() + "/icon_web_night.data";
                dt.a().a(new lb() { // from class: com.amap.api.col.3sl.ej.1
                    @Override // com.amap.api.col.p0003sl.lb
                    public final void runTask() {
                        ej.this.a(AMapEngineUtils.LOGO_CUSTOM_ICON_DAY_NAME, 0);
                        ej.this.a(AMapEngineUtils.LOGO_CUSTOM_ICON_NIGHT_NAME, 1);
                        if ("".equals(dl.a(ej.this.t, "amap_web_logo", "md5_day", ""))) {
                            if (ej.this.c == null || ej.this.d == null) {
                                dl.a(ej.this.t, "amap_web_logo", "md5_day", (Object) "0b718b5f291b09d2b62be725dfb977b3");
                                dl.a(ej.this.t, "amap_web_logo", "md5_night", (Object) "4b1405462a5c910de0e0723ffd96c018");
                                return;
                            }
                            dl.a(ej.this.t, "amap_web_logo", "md5_day", (Object) hv.a(AMapEngineUtils.LOGO_CUSTOM_ICON_DAY_NAME));
                            String a2 = hv.a(AMapEngineUtils.LOGO_CUSTOM_ICON_NIGHT_NAME);
                            if (!"".equals(a2)) {
                                dl.a(ej.this.t, "amap_web_logo", "md5_night", (Object) a2);
                            }
                            ej.this.d(true);
                        }
                    }
                });
                if (open != null) {
                    try {
                        open.close();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                inputStream = inputStream2;
                inputStream2 = open;
                try {
                    iv.c(th, "WaterMarkerView", "create");
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (Throwable th4) {
                            th4.printStackTrace();
                        }
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th5) {
                            th5.printStackTrace();
                        }
                    }
                } finally {
                }
            }
        } catch (Throwable th6) {
            th = th6;
            inputStream = null;
        }
    }

    private Bitmap e() {
        Bitmap bitmap;
        Bitmap bitmap2;
        Bitmap bitmap3;
        return (!this.y || (bitmap3 = this.e) == null) ? this.i ? (!this.s || (bitmap2 = this.d) == null) ? this.b : bitmap2 : (!this.s || (bitmap = this.c) == null) ? this.f1007a : bitmap : bitmap3;
    }

    public final void a(boolean z) {
        if (this.u) {
            try {
                this.i = z;
                if (z) {
                    this.h.setColor(-1);
                } else {
                    this.h.setColor(-16777216);
                }
            } catch (Throwable th) {
                iv.c(th, "WaterMarkerView", "changeBitmap");
                th.printStackTrace();
            }
        }
    }

    public final Point b() {
        return new Point(this.m, this.n - 2);
    }

    public final void a(int i) {
        this.o = 0;
        this.l = i;
        c();
    }

    public final void b(int i) {
        this.o = 1;
        this.q = i;
        c();
    }

    public final void c(int i) {
        this.o = 1;
        this.p = i;
        c();
    }

    public final float d(int i) {
        float f;
        if (!this.u) {
            return 0.0f;
        }
        if (i == 0) {
            return this.v;
        }
        if (i == 1) {
            f = this.v;
        } else {
            if (i != 2) {
                return 0.0f;
            }
            f = this.w;
        }
        return 1.0f - f;
    }

    public final void a(int i, float f) {
        if (this.u) {
            this.o = 2;
            float max = Math.max(0.0f, Math.min(f, 1.0f));
            if (i == 0) {
                this.v = max;
                this.x = true;
            } else if (i == 1) {
                this.v = 1.0f - max;
                this.x = false;
            } else if (i == 2) {
                this.w = 1.0f - max;
            }
            c();
        }
    }

    public final void c() {
        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        f();
        postInvalidate();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        try {
            if (!this.u || getWidth() == 0 || getHeight() == 0 || this.b == null) {
                return;
            }
            if (!this.r) {
                f();
                this.r = true;
            }
            canvas.drawBitmap(e(), this.m, this.n, this.h);
        } catch (Throwable th) {
            iv.c(th, "WaterMarkerView", "onDraw");
            th.printStackTrace();
        }
    }

    private void f() {
        int i = this.o;
        if (i == 0) {
            h();
        } else if (i == 2) {
            g();
        }
        this.m = this.p;
        int height = (getHeight() - this.q) - this.j;
        this.n = height;
        if (this.m < 0) {
            this.m = 0;
        }
        if (height < 0) {
            this.n = 0;
        }
    }

    private void g() {
        if (this.x) {
            this.p = (int) (getWidth() * this.v);
        } else {
            this.p = (int) ((getWidth() * this.v) - this.k);
        }
        this.q = (int) (getHeight() * this.w);
    }

    private void h() {
        int i = this.l;
        if (i == 1) {
            this.p = (getWidth() - this.k) / 2;
        } else if (i == 2) {
            this.p = (getWidth() - this.k) - 10;
        } else {
            this.p = 10;
        }
        this.q = 8;
    }

    public final void a(String str, int i) {
        try {
            if (this.u && new File(str).exists()) {
                if (i == 0) {
                    Bitmap bitmap = this.c;
                    Bitmap decodeFile = BitmapFactory.decodeFile(str);
                    this.f = decodeFile;
                    this.c = dv.a(decodeFile, v.f1366a);
                    if (bitmap == null || bitmap.isRecycled()) {
                        return;
                    }
                    dv.a(bitmap);
                    return;
                }
                if (i == 1) {
                    Bitmap bitmap2 = this.d;
                    Bitmap decodeFile2 = BitmapFactory.decodeFile(str);
                    this.f = decodeFile2;
                    this.d = dv.a(decodeFile2, v.f1366a);
                    if (bitmap2 == null || bitmap2.isRecycled()) {
                        return;
                    }
                    dv.a(bitmap2);
                }
            }
        } catch (Throwable th) {
            iv.c(th, "WaterMarkerView", "create");
            th.printStackTrace();
        }
    }

    public final void b(boolean z) {
        if (this.u) {
            this.y = z;
            if (z) {
                Bitmap bitmap = this.e;
                if (bitmap != null) {
                    this.k = bitmap.getWidth();
                    this.j = this.e.getHeight();
                    return;
                }
                return;
            }
            this.k = this.f1007a.getWidth();
            this.j = this.f1007a.getHeight();
        }
    }

    public final void c(boolean z) {
        this.u = z;
    }

    public final void d(boolean z) {
        if (this.u && this.s != z) {
            this.s = z;
            if (z) {
                if (this.i) {
                    Bitmap bitmap = this.d;
                    if (bitmap != null) {
                        this.k = bitmap.getWidth();
                        this.j = this.d.getHeight();
                        return;
                    }
                    return;
                }
                Bitmap bitmap2 = this.c;
                if (bitmap2 != null) {
                    this.k = bitmap2.getWidth();
                    this.j = this.c.getHeight();
                    return;
                }
                return;
            }
            this.k = this.f1007a.getWidth();
            this.j = this.f1007a.getHeight();
        }
    }

    public final boolean d() {
        return this.i;
    }
}
