package com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect;

import android.content.Context;
import android.provider.MediaStore;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel;
import com.huawei.openalliance.ad.constant.MimeType;
import java.util.List;

/* loaded from: classes4.dex */
public class ImageLoad extends AsyncTaskLoader<List<PhotoModel>> {
    private static final String[] b = {"_id", "_data", "mime_type", "width", "height", "_size"};

    /* renamed from: a, reason: collision with root package name */
    private final Loader<List<PhotoModel>>.ForceLoadContentObserver f3627a;
    private List<PhotoModel> d;
    private boolean e;

    public ImageLoad(Context context) {
        super(context);
        this.d = null;
        this.e = false;
        this.f3627a = new Loader.ForceLoadContentObserver();
    }

    @Override // androidx.loader.content.Loader
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void deliverResult(List<PhotoModel> list) {
        if (isReset() || !isStarted()) {
            return;
        }
        super.deliverResult(list);
    }

    @Override // androidx.loader.content.Loader
    public void onStartLoading() {
        List<PhotoModel> list = this.d;
        if (list != null) {
            deliverResult(list);
        }
        if (takeContentChanged() || this.d == null) {
            forceLoad();
        }
        d();
    }

    @Override // androidx.loader.content.Loader
    public void onStopLoading() {
        cancelLoad();
    }

    @Override // androidx.loader.content.Loader
    public void onReset() {
        super.onReset();
        onStopLoading();
        this.d = null;
        c();
    }

    @Override // androidx.loader.content.Loader
    public void onAbandon() {
        super.onAbandon();
        c();
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public List<PhotoModel> loadInBackground() {
        return b();
    }

    private List<PhotoModel> b() {
        List<PhotoModel> e = e();
        this.d = e;
        return e;
    }

    private boolean d(String str) {
        return str != null && (MimeType.GIF.equals(str) || "image/GIF".equals(str));
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x00c3, code lost:
    
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel> e() {
        /*
            r15 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.content.Context r2 = r15.getContext()     // Catch: java.lang.Throwable -> Lc7
            android.content.ContentResolver r3 = r2.getContentResolver()     // Catch: java.lang.Throwable -> Lc7
            android.net.Uri r4 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI     // Catch: java.lang.Throwable -> Lc7
            java.lang.String[] r5 = com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.ImageLoad.b     // Catch: java.lang.Throwable -> Lc7
            r6 = 0
            r7 = 0
            java.lang.String r8 = "_id DESC"
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> Lc7
            if (r1 == 0) goto Lc1
            int r2 = r1.getCount()     // Catch: java.lang.Throwable -> Lc7
            if (r2 > 0) goto L24
            goto Lc1
        L24:
            r1.moveToFirst()     // Catch: java.lang.Throwable -> Lc7
            java.lang.String r2 = "_data"
            int r2 = r1.getColumnIndexOrThrow(r2)     // Catch: java.lang.Throwable -> Lc7
            java.lang.String r3 = "mime_type"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> Lc7
            java.lang.String r4 = "width"
            int r4 = r1.getColumnIndex(r4)     // Catch: java.lang.Throwable -> Lc7
            java.lang.String r5 = "height"
            int r5 = r1.getColumnIndex(r5)     // Catch: java.lang.Throwable -> Lc7
        L3f:
            java.lang.String r6 = r1.getString(r2)     // Catch: java.lang.Throwable -> Lc7
            java.lang.String r7 = r1.getString(r3)     // Catch: java.lang.Throwable -> Lc7
            int r8 = r1.getInt(r4)     // Catch: java.lang.Throwable -> Lc7
            int r9 = r1.getInt(r5)     // Catch: java.lang.Throwable -> Lc7
            boolean r10 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> Lc7
            if (r10 == 0) goto L56
            goto Lb5
        L56:
            java.io.File r10 = new java.io.File     // Catch: java.lang.Throwable -> Lc7
            r10.<init>(r6)     // Catch: java.lang.Throwable -> Lc7
            boolean r10 = r10.exists()     // Catch: java.lang.Throwable -> Lc7
            if (r10 != 0) goto L62
            goto Lb5
        L62:
            boolean r10 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> Lc7
            if (r10 == 0) goto L69
            goto Lb5
        L69:
            int r10 = r8 * 3
            if (r9 > r10) goto Lb5
            int r10 = r9 * 3
            if (r8 <= r10) goto L72
            goto Lb5
        L72:
            boolean r10 = r15.d(r7)     // Catch: java.lang.Throwable -> Lc7
            if (r10 != 0) goto Lb5
            java.util.Locale r10 = java.util.Locale.ENGLISH     // Catch: java.lang.Throwable -> Lc7
            java.lang.String r10 = r6.toLowerCase(r10)     // Catch: java.lang.Throwable -> Lc7
            java.lang.String r11 = ".dng"
            boolean r10 = r10.contains(r11)     // Catch: java.lang.Throwable -> Lc7
            if (r10 == 0) goto L87
            goto Lb5
        L87:
            java.lang.String r10 = "_id"
            int r10 = r1.getColumnIndexOrThrow(r10)     // Catch: java.lang.Throwable -> Lc7
            java.lang.String r11 = "_size"
            int r11 = r1.getColumnIndex(r11)     // Catch: java.lang.Throwable -> Lc7
            long r12 = r1.getLong(r10)     // Catch: java.lang.Throwable -> Lc7
            long r10 = r1.getLong(r11)     // Catch: java.lang.Throwable -> Lc7
            com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel r14 = new com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel     // Catch: java.lang.Throwable -> Lc7
            r14.<init>(r12, r6, r7)     // Catch: java.lang.Throwable -> Lc7
            com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel r6 = r14.setWidth(r8)     // Catch: java.lang.Throwable -> Lc7
            com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel r6 = r6.setHeight(r9)     // Catch: java.lang.Throwable -> Lc7
            com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel r6 = r6.setSize(r10)     // Catch: java.lang.Throwable -> Lc7
            r7 = 0
            com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel r6 = r6.setTime(r7)     // Catch: java.lang.Throwable -> Lc7
            r0.add(r6)     // Catch: java.lang.Throwable -> Lc7
        Lb5:
            boolean r6 = r1.moveToNext()     // Catch: java.lang.Throwable -> Lc7
            if (r6 != 0) goto L3f
            if (r1 == 0) goto Lc0
            r1.close()
        Lc0:
            return r0
        Lc1:
            if (r1 == 0) goto Lc6
            r1.close()
        Lc6:
            return r0
        Lc7:
            r0 = move-exception
            if (r1 == 0) goto Lcd
            r1.close()
        Lcd:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.ImageLoad.e():java.util.List");
    }

    private void d() {
        if (this.e) {
            return;
        }
        getContext().getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, false, this.f3627a);
        this.e = true;
    }

    private void c() {
        if (this.e) {
            this.e = false;
            getContext().getContentResolver().unregisterContentObserver(this.f3627a);
        }
    }
}
