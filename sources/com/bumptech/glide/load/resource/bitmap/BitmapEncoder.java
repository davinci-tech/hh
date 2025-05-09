package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;

/* loaded from: classes2.dex */
public class BitmapEncoder implements ResourceEncoder<Bitmap> {
    private static final String TAG = "BitmapEncoder";
    private final ArrayPool arrayPool;
    public static final Option<Integer> COMPRESSION_QUALITY = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", 90);
    public static final Option<Bitmap.CompressFormat> COMPRESSION_FORMAT = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat");

    public BitmapEncoder(ArrayPool arrayPool) {
        this.arrayPool = arrayPool;
    }

    @Deprecated
    public BitmapEncoder() {
        this.arrayPool = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0071 A[Catch: all -> 0x00bf, TRY_LEAVE, TryCatch #7 {all -> 0x00bf, blocks: (B:3:0x0021, B:15:0x004b, B:18:0x006b, B:20:0x0071, B:45:0x00bb, B:43:0x00be, B:38:0x0066), top: B:2:0x0021 }] */
    @Override // com.bumptech.glide.load.Encoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean encode(com.bumptech.glide.load.engine.Resource<android.graphics.Bitmap> r8, java.io.File r9, com.bumptech.glide.load.Options r10) {
        /*
            r7 = this;
            java.lang.String r0 = "BitmapEncoder"
            java.lang.Object r8 = r8.get()
            android.graphics.Bitmap r8 = (android.graphics.Bitmap) r8
            android.graphics.Bitmap$CompressFormat r1 = r7.getFormat(r8, r10)
            int r2 = r8.getWidth()
            int r3 = r8.getHeight()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.String r4 = "encode: [%dx%d] %s"
            com.bumptech.glide.util.pool.GlideTrace.beginSectionFormat(r4, r2, r3, r1)
            long r2 = com.bumptech.glide.util.LogTime.getLogTime()     // Catch: java.lang.Throwable -> Lbf
            com.bumptech.glide.load.Option<java.lang.Integer> r4 = com.bumptech.glide.load.resource.bitmap.BitmapEncoder.COMPRESSION_QUALITY     // Catch: java.lang.Throwable -> Lbf
            java.lang.Object r4 = r10.get(r4)     // Catch: java.lang.Throwable -> Lbf
            java.lang.Integer r4 = (java.lang.Integer) r4     // Catch: java.lang.Throwable -> Lbf
            int r4 = r4.intValue()     // Catch: java.lang.Throwable -> Lbf
            r5 = 0
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r6.<init>(r9)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r9 = r7.arrayPool     // Catch: java.lang.Throwable -> L50 java.io.IOException -> L52
            if (r9 == 0) goto L44
            com.bumptech.glide.load.data.BufferedOutputStream r9 = new com.bumptech.glide.load.data.BufferedOutputStream     // Catch: java.lang.Throwable -> L50 java.io.IOException -> L52
            com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool r5 = r7.arrayPool     // Catch: java.lang.Throwable -> L50 java.io.IOException -> L52
            r9.<init>(r6, r5)     // Catch: java.lang.Throwable -> L50 java.io.IOException -> L52
            r5 = r9
            goto L45
        L44:
            r5 = r6
        L45:
            r8.compress(r1, r4, r5)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r5.close()     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L57
            r5.close()     // Catch: java.io.IOException -> L4e java.lang.Throwable -> Lbf
        L4e:
            r9 = 1
            goto L6a
        L50:
            r8 = move-exception
            goto Lb9
        L52:
            r9 = move-exception
            r5 = r6
            goto L58
        L55:
            r8 = move-exception
            goto Lb8
        L57:
            r9 = move-exception
        L58:
            r4 = 3
            boolean r4 = android.util.Log.isLoggable(r0, r4)     // Catch: java.lang.Throwable -> L55
            if (r4 == 0) goto L64
            java.lang.String r4 = "Failed to encode Bitmap"
            android.util.Log.d(r0, r4, r9)     // Catch: java.lang.Throwable -> L55
        L64:
            if (r5 == 0) goto L69
            r5.close()     // Catch: java.io.IOException -> L69 java.lang.Throwable -> Lbf
        L69:
            r9 = 0
        L6a:
            r4 = 2
            boolean r4 = android.util.Log.isLoggable(r0, r4)     // Catch: java.lang.Throwable -> Lbf
            if (r4 == 0) goto Lb4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r5 = "Compressed with type: "
            r4.<init>(r5)     // Catch: java.lang.Throwable -> Lbf
            r4.append(r1)     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r1 = " of size "
            r4.append(r1)     // Catch: java.lang.Throwable -> Lbf
            int r1 = com.bumptech.glide.util.Util.getBitmapByteSize(r8)     // Catch: java.lang.Throwable -> Lbf
            r4.append(r1)     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r1 = " in "
            r4.append(r1)     // Catch: java.lang.Throwable -> Lbf
            double r1 = com.bumptech.glide.util.LogTime.getElapsedMillis(r2)     // Catch: java.lang.Throwable -> Lbf
            r4.append(r1)     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r1 = ", options format: "
            r4.append(r1)     // Catch: java.lang.Throwable -> Lbf
            com.bumptech.glide.load.Option<android.graphics.Bitmap$CompressFormat> r1 = com.bumptech.glide.load.resource.bitmap.BitmapEncoder.COMPRESSION_FORMAT     // Catch: java.lang.Throwable -> Lbf
            java.lang.Object r10 = r10.get(r1)     // Catch: java.lang.Throwable -> Lbf
            r4.append(r10)     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r10 = ", hasAlpha: "
            r4.append(r10)     // Catch: java.lang.Throwable -> Lbf
            boolean r8 = r8.hasAlpha()     // Catch: java.lang.Throwable -> Lbf
            r4.append(r8)     // Catch: java.lang.Throwable -> Lbf
            java.lang.String r8 = r4.toString()     // Catch: java.lang.Throwable -> Lbf
            android.util.Log.v(r0, r8)     // Catch: java.lang.Throwable -> Lbf
        Lb4:
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            return r9
        Lb8:
            r6 = r5
        Lb9:
            if (r6 == 0) goto Lbe
            r6.close()     // Catch: java.io.IOException -> Lbe java.lang.Throwable -> Lbf
        Lbe:
            throw r8     // Catch: java.lang.Throwable -> Lbf
        Lbf:
            r8 = move-exception
            com.bumptech.glide.util.pool.GlideTrace.endSection()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.BitmapEncoder.encode(com.bumptech.glide.load.engine.Resource, java.io.File, com.bumptech.glide.load.Options):boolean");
    }

    private Bitmap.CompressFormat getFormat(Bitmap bitmap, Options options) {
        Bitmap.CompressFormat compressFormat = (Bitmap.CompressFormat) options.get(COMPRESSION_FORMAT);
        if (compressFormat != null) {
            return compressFormat;
        }
        if (bitmap.hasAlpha()) {
            return Bitmap.CompressFormat.PNG;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    @Override // com.bumptech.glide.load.ResourceEncoder
    public EncodeStrategy getEncodeStrategy(Options options) {
        return EncodeStrategy.TRANSFORMED;
    }
}
