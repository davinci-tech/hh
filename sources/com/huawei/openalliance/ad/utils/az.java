package com.huawei.openalliance.ad.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.bumptech.glide.Registry;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.Scheme;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.fb;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.rt;
import com.huawei.openalliance.ad.ru;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class az {

    /* renamed from: a, reason: collision with root package name */
    private static final String f7621a = "az";
    private static String b;

    public interface a {
        void a();

        void a(Drawable drawable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r9v5, types: [java.io.Closeable] */
    private static Pair<Drawable, String> c(BitmapFactory.Options options, String str, Context context) {
        InputStream inputStream;
        InputStream inputStream2;
        String str2;
        String str3;
        String str4;
        Uri fromFile;
        Pair<Drawable, String> pair;
        ContentResolver contentResolver;
        try {
            try {
                File b2 = ae.b(context, str, "normal");
                if (b2 == null || !ae.d(b2)) {
                    b2 = ae.b(context, str, Constants.TPLATE_CACHE);
                }
                try {
                    if (str.startsWith(Scheme.CONTENT.toString())) {
                        fromFile = Uri.parse(str);
                    } else {
                        if (b2 == null) {
                            pair = new Pair<>(null, "File is null");
                            cy.a((Closeable) null);
                            cy.a((Closeable) null);
                            return pair;
                        }
                        fromFile = Uri.fromFile(b2);
                    }
                    if (a(inputStream2) == 4) {
                        Pair<Drawable, String> pair2 = new Pair<>(str.startsWith(Scheme.CONTENT.toString()) ? new com.huawei.openalliance.ad.views.gif.b(context, str) : b2 != null ? new com.huawei.openalliance.ad.views.gif.b(context, b2.getCanonicalPath()) : null, null);
                        cy.a((Closeable) inputStream2);
                        pair = pair2;
                        cy.a((Closeable) null);
                        return pair;
                    }
                    inputStream = contentResolver.openInputStream(fromFile);
                    try {
                        Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
                        if (decodeStream == null) {
                            ho.b(f7621a, "Image decode fail");
                            cg.a(context).b((Integer) 0);
                        }
                        return new Pair<>(new BitmapDrawable(context.getResources(), decodeStream), null);
                    } catch (FileNotFoundException e) {
                        e = e;
                        str3 = f7621a;
                        str4 = "lfP " + e.getClass().getSimpleName();
                        str2 = "loadDiskImg FileNotFoundException";
                        str = inputStream;
                        ho.c(str3, str4);
                        cy.a((Closeable) inputStream2);
                        cy.a((Closeable) str);
                        return new Pair<>(null, str2);
                    } catch (Throwable th) {
                        th = th;
                        str2 = "loadDiskImg " + th.getClass().getSimpleName();
                        str3 = f7621a;
                        str4 = "lfP " + th.getClass().getSimpleName();
                        str = inputStream;
                        ho.c(str3, str4);
                        cy.a((Closeable) inputStream2);
                        cy.a((Closeable) str);
                        return new Pair<>(null, str2);
                    }
                } catch (FileNotFoundException e2) {
                    e = e2;
                    inputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = null;
                }
                contentResolver = context.getContentResolver();
                inputStream2 = contentResolver.openInputStream(fromFile);
            } finally {
                cy.a((Closeable) inputStream2);
                cy.a((Closeable) str);
            }
        } catch (FileNotFoundException e3) {
            e = e3;
            inputStream = null;
            inputStream2 = null;
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
            inputStream2 = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Drawable c(String str) {
        return ay.a().a(cu.a(str));
    }

    public static String b(Context context, String str) {
        if (TextUtils.isEmpty(str) || !str.contains("__source__")) {
            ho.b(f7621a, "do not need dealUri");
            return str;
        }
        Integer ab = cg.a(context).ab();
        String str2 = (Build.VERSION.SDK_INT < 28 || (ab != null ? ab.intValue() : 1) != 1) ? "webp" : "heif";
        b = str2;
        if (TextUtils.isEmpty(str2)) {
            ho.b(f7621a, "replacedImageType is null, return original url");
            return str;
        }
        ho.b(f7621a, "dealUri replaceAll by replacedImageType %s", b);
        return str.replaceAll("__source__", b);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v2 */
    private static Pair<Drawable, String> b(BitmapFactory.Options options, String str, Context context) {
        InputStream inputStream;
        String str2;
        String str3;
        String str4;
        Resources resources = context.getResources();
        ?? r2 = 0;
        try {
            try {
                int parseInt = Integer.parseInt(str.substring(Scheme.RES.toString().length()));
                inputStream = resources.openRawResource(parseInt);
                try {
                    if (a(inputStream) == 4) {
                        Pair<Drawable, String> pair = new Pair<>(new com.huawei.openalliance.ad.views.gif.b(context, str), null);
                        cy.a((Closeable) inputStream);
                        return pair;
                    }
                    Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), parseInt, options);
                    if (decodeResource == null) {
                        ho.b(f7621a, "Image decode fail");
                        cg.a(context).b((Integer) 0);
                    }
                    Pair<Drawable, String> pair2 = new Pair<>(new BitmapDrawable(context.getResources(), decodeResource), null);
                    cy.a((Closeable) inputStream);
                    return pair2;
                } catch (Resources.NotFoundException unused) {
                    str2 = f7621a;
                    str3 = "loadResImg Resources.NotFoundException";
                    str4 = "loadImage Resources.NotFoundException";
                    ho.c(str2, str4);
                    cy.a((Closeable) inputStream);
                    return new Pair<>(null, str3);
                } catch (NumberFormatException unused2) {
                    str2 = f7621a;
                    str3 = "loadResImg NumberFormatException";
                    str4 = "loadImage NumberFormatException";
                    ho.c(str2, str4);
                    cy.a((Closeable) inputStream);
                    return new Pair<>(null, str3);
                }
            } catch (Throwable th) {
                th = th;
                r2 = resources;
                cy.a((Closeable) r2);
                throw th;
            }
        } catch (Resources.NotFoundException unused3) {
            inputStream = null;
        } catch (NumberFormatException unused4) {
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            cy.a((Closeable) r2);
            throw th;
        }
    }

    public static Bitmap b(Drawable drawable) {
        Bitmap a2;
        if (drawable == null || (a2 = a(drawable)) == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(-1.0f, 1.0f);
        return Bitmap.createBitmap(a2, 0, 0, a2.getWidth(), a2.getHeight(), matrix, false);
    }

    private static void a(Drawable drawable, Bitmap bitmap) {
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
    }

    public static void a(final Context context, final String str, final a aVar, final ContentRecord contentRecord) {
        if (context != null) {
            context = context.getApplicationContext();
        }
        m.f(new Runnable() { // from class: com.huawei.openalliance.ad.utils.az.2
            @Override // java.lang.Runnable
            public void run() {
                String str2;
                boolean z;
                if (ho.a()) {
                    ho.a(az.f7621a, "load image, filePath:%s", dl.a(str));
                }
                Drawable c = az.c(str);
                if (c == null) {
                    Pair<Drawable, String> a2 = az.a(context, str);
                    Drawable drawable = (Drawable) a2.first;
                    str2 = (String) a2.second;
                    c = drawable;
                } else {
                    str2 = null;
                }
                a aVar2 = aVar;
                if (aVar2 != null) {
                    if (c != null) {
                        aVar2.a(c);
                        return;
                    }
                    boolean z2 = !cz.b(str) && ae.c(context, str, "normal");
                    if (z2) {
                        z = z2;
                    } else {
                        z = !cz.b(str) && ae.c(context, str, Constants.TPLATE_CACHE);
                    }
                    aVar.a();
                    if (contentRecord != null) {
                        new com.huawei.openalliance.ad.analysis.c(context).a(str, -1, str2, contentRecord, z);
                    }
                }
            }
        });
    }

    public static void a(Context context, String str, a aVar) {
        a(context, str, aVar, null);
    }

    public static void a(final Context context, final rt rtVar, final a aVar) {
        m.b(new Runnable() { // from class: com.huawei.openalliance.ad.utils.az.1
            @Override // java.lang.Runnable
            public void run() {
                ru a2 = fb.a(context.getApplicationContext()).a(rtVar);
                if (a2 != null && !cz.b(a2.a())) {
                    az.a(context, a2.a(), aVar);
                    return;
                }
                a aVar2 = aVar;
                if (aVar2 != null) {
                    aVar2.a();
                }
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v2 */
    private static Pair<Drawable, String> a(BitmapFactory.Options options, String str, Context context) {
        InputStream inputStream;
        String substring = str.substring(Scheme.ASSET.toString().length());
        ?? r2 = 0;
        try {
            try {
                inputStream = context.getAssets().open(substring);
                try {
                    if (a(inputStream) == 4) {
                        Pair<Drawable, String> pair = new Pair<>(new com.huawei.openalliance.ad.views.gif.b(context, str), null);
                        cy.a((Closeable) inputStream);
                        return pair;
                    }
                    Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
                    if (decodeStream == null) {
                        ho.b(f7621a, "Image decode fail");
                        cg.a(context).b((Integer) 0);
                    }
                    Pair<Drawable, String> pair2 = new Pair<>(new BitmapDrawable(context.getResources(), decodeStream), null);
                    cy.a((Closeable) inputStream);
                    return pair2;
                } catch (IOException unused) {
                    ho.c(f7621a, "loadAssetImg IOException");
                    cy.a((Closeable) inputStream);
                    return new Pair<>(null, "loadAssetImg IOException");
                }
            } catch (Throwable th) {
                th = th;
                r2 = substring;
                cy.a((Closeable) r2);
                throw th;
            }
        } catch (IOException unused2) {
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            cy.a((Closeable) r2);
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.util.Pair<android.graphics.drawable.Drawable, java.lang.String> a(android.content.Context r4, java.lang.String r5) {
        /*
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options
            r0.<init>()
            r1 = 0
            r2 = 0
            r0.inJustDecodeBounds = r1     // Catch: java.lang.Throwable -> L46 java.lang.OutOfMemoryError -> L71
            com.huawei.openalliance.ad.constant.Scheme r1 = com.huawei.openalliance.ad.constant.Scheme.RES     // Catch: java.lang.Throwable -> L46 java.lang.OutOfMemoryError -> L71
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L46 java.lang.OutOfMemoryError -> L71
            boolean r1 = r5.startsWith(r1)     // Catch: java.lang.Throwable -> L46 java.lang.OutOfMemoryError -> L71
            if (r1 == 0) goto L25
            android.util.Pair r4 = b(r0, r5, r4)     // Catch: java.lang.Throwable -> L46 java.lang.OutOfMemoryError -> L71
            java.lang.Object r0 = r4.first     // Catch: java.lang.Throwable -> L46 java.lang.OutOfMemoryError -> L71
            android.graphics.drawable.Drawable r0 = (android.graphics.drawable.Drawable) r0     // Catch: java.lang.Throwable -> L46 java.lang.OutOfMemoryError -> L71
        L1d:
            java.lang.Object r4 = r4.second     // Catch: java.lang.Throwable -> L20 java.lang.OutOfMemoryError -> L23
            goto L43
        L20:
            r4 = move-exception
            r2 = r0
            goto L47
        L23:
            r2 = r0
            goto L71
        L25:
            com.huawei.openalliance.ad.constant.Scheme r1 = com.huawei.openalliance.ad.constant.Scheme.ASSET     // Catch: java.lang.Throwable -> L46 java.lang.OutOfMemoryError -> L71
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L46 java.lang.OutOfMemoryError -> L71
            boolean r1 = r5.startsWith(r1)     // Catch: java.lang.Throwable -> L46 java.lang.OutOfMemoryError -> L71
            if (r1 == 0) goto L3a
            android.util.Pair r4 = a(r0, r5, r4)     // Catch: java.lang.Throwable -> L46 java.lang.OutOfMemoryError -> L71
            java.lang.Object r0 = r4.first     // Catch: java.lang.Throwable -> L46 java.lang.OutOfMemoryError -> L71
            android.graphics.drawable.Drawable r0 = (android.graphics.drawable.Drawable) r0     // Catch: java.lang.Throwable -> L46 java.lang.OutOfMemoryError -> L71
            goto L1d
        L3a:
            android.util.Pair r4 = c(r0, r5, r4)     // Catch: java.lang.Throwable -> L46 java.lang.OutOfMemoryError -> L71
            java.lang.Object r0 = r4.first     // Catch: java.lang.Throwable -> L46 java.lang.OutOfMemoryError -> L71
            android.graphics.drawable.Drawable r0 = (android.graphics.drawable.Drawable) r0     // Catch: java.lang.Throwable -> L46 java.lang.OutOfMemoryError -> L71
            goto L1d
        L43:
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.Throwable -> L20 java.lang.OutOfMemoryError -> L23
            goto L7b
        L46:
            r4 = move-exception
        L47:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "loadImageFromDisk "
            r0.<init>(r1)
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = r4.getSimpleName()
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            java.lang.String r0 = com.huawei.openalliance.ad.utils.az.f7621a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "loadImage err: "
            r1.<init>(r3)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            com.huawei.openalliance.ad.ho.c(r0, r1)
            goto L7a
        L71:
            java.lang.String r4 = com.huawei.openalliance.ad.utils.az.f7621a
            java.lang.String r0 = "OutOfMemoryError when read image"
            com.huawei.openalliance.ad.ho.c(r4, r0)
            java.lang.String r4 = "OOM read image"
        L7a:
            r0 = r2
        L7b:
            if (r0 == 0) goto L88
            com.huawei.openalliance.ad.utils.ay r1 = com.huawei.openalliance.ad.utils.ay.a()
            java.lang.String r5 = com.huawei.openalliance.ad.utils.cu.a(r5)
            r1.a(r5, r0)
        L88:
            android.util.Pair r5 = new android.util.Pair
            r5.<init>(r0, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.utils.az.a(android.content.Context, java.lang.String):android.util.Pair");
    }

    public static Rect a(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return new Rect(0, 0, options.outWidth, options.outHeight);
    }

    public static Bitmap a(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            ho.b(f7621a, Registry.BUCKET_BITMAP_DRAWABLE);
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicHeight <= 0) {
            intrinsicHeight = 1;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth > 0 ? intrinsicWidth : 1, intrinsicHeight, Bitmap.Config.ARGB_8888);
        a(drawable, createBitmap);
        return createBitmap;
    }

    private static int a(InputStream inputStream) {
        String a2;
        try {
            a2 = ae.a(inputStream);
        } catch (Resources.NotFoundException unused) {
            ho.d(f7621a, "resId is not found");
        }
        if (Constants.GIF_HEADER_HEX.equals(a2)) {
            return 4;
        }
        return a2 != null ? 2 : 100;
    }
}
