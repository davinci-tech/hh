package com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.automatchphoto;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.MimeType;
import defpackage.hcf;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class TrackPhotosDownload implements LoaderManager.LoaderCallbacks<Cursor> {

    /* renamed from: a, reason: collision with root package name */
    private long f3625a;
    private volatile boolean b;
    private volatile boolean c;
    private Context d;
    private c e;
    private MotionPath f;
    private HashMap<Integer, List<PhotoModel>> g;
    private List<PhotoModel> h;
    private String[] i;
    private MotionPathSimplify j;
    private int k;
    private long o;

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    static class c implements Runnable {
        WeakReference<Cursor> c;
        WeakReference<TrackPhotosDownload> e;

        public c(TrackPhotosDownload trackPhotosDownload, Cursor cursor) {
            this.e = new WeakReference<>(trackPhotosDownload);
            this.c = new WeakReference<>(cursor);
        }

        @Override // java.lang.Runnable
        public void run() {
            TrackPhotosDownload trackPhotosDownload = this.e.get();
            Cursor cursor = this.c.get();
            if (trackPhotosDownload != null) {
                if (cursor != null && trackPhotosDownload.b) {
                    trackPhotosDownload.aZR_(cursor);
                    hcf hcfVar = new hcf(trackPhotosDownload.h, trackPhotosDownload.f, trackPhotosDownload.j, trackPhotosDownload.k);
                    LogUtil.a("TrackPhotosDownload", "mPhotosList:" + trackPhotosDownload.h.size());
                    trackPhotosDownload.g = hcfVar.e();
                    trackPhotosDownload.b = false;
                }
                trackPhotosDownload.c = true;
                return;
            }
            LogUtil.b("TrackPhotosDownload", "MatchPhotoRunnable run() trackPhotosDownload is Null");
        }
    }

    public boolean d() {
        return this.c;
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        LogUtil.a("TrackPhotosDownload", "onCreateLoader");
        String[] strArr = {String.valueOf(this.o / 1000), String.valueOf(this.f3625a / 1000)};
        return new CursorLoader(this.d, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, this.i, "((date_added >= ?) AND (date_added <= ?))", strArr, this.i[0] + " ASC");
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    /* renamed from: aZS_, reason: merged with bridge method [inline-methods] */
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor != null) {
            LogUtil.a("TrackPhotosDownload", "TrackPhotosDownload#onLoadFinished cursor is closed =", Boolean.valueOf(cursor.isClosed()));
        }
        this.e = new c(this, cursor);
        ThreadPoolManager.d().execute(this.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00e0 A[LOOP:0: B:16:0x0052->B:34:0x00e0, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00dc A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void aZR_(android.database.Cursor r18) {
        /*
            r17 = this;
            r1 = r17
            r2 = r18
            java.lang.String r0 = "TrackPhotosDownload"
            if (r2 != 0) goto L12
            java.lang.String r2 = "fillingLoadResult cursor is null"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)
            return
        L12:
            boolean r3 = r18.isClosed()
            if (r3 == 0) goto L22
            java.lang.String r2 = "fillingLoadResult cursor isClosed"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)
            return
        L22:
            int r3 = r18.getCount()
            if (r3 > 0) goto L32
            java.lang.String r2 = "fillingLoadResult cursor Count <= 0"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r2)
            return
        L32:
            java.util.List<com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel> r0 = r1.h     // Catch: java.lang.Throwable -> Le6
            r0.clear()     // Catch: java.lang.Throwable -> Le6
            r18.moveToFirst()     // Catch: java.lang.Throwable -> Le6
            java.lang.String r0 = "_data"
            int r0 = r2.getColumnIndexOrThrow(r0)     // Catch: java.lang.Throwable -> Le6
            java.lang.String r3 = "mime_type"
            int r3 = r2.getColumnIndex(r3)     // Catch: java.lang.Throwable -> Le6
            java.lang.String r4 = "width"
            int r4 = r2.getColumnIndex(r4)     // Catch: java.lang.Throwable -> Le6
            java.lang.String r5 = "height"
            int r5 = r2.getColumnIndex(r5)     // Catch: java.lang.Throwable -> Le6
        L52:
            java.lang.String r6 = r2.getString(r3)     // Catch: java.lang.Throwable -> Le6
            int r7 = r2.getInt(r4)     // Catch: java.lang.Throwable -> Le6
            int r8 = r2.getInt(r5)     // Catch: java.lang.Throwable -> Le6
            java.lang.String r9 = r2.getString(r0)     // Catch: java.lang.Throwable -> Le6
            java.util.Locale r10 = java.util.Locale.ENGLISH     // Catch: java.lang.Throwable -> Le6
            java.lang.String r10 = r9.toLowerCase(r10)     // Catch: java.lang.Throwable -> Le6
            java.lang.String r11 = "camera"
            boolean r10 = r10.contains(r11)     // Catch: java.lang.Throwable -> Le6
            if (r10 == 0) goto Ld4
            java.util.Locale r10 = java.util.Locale.ENGLISH     // Catch: java.lang.Throwable -> Le6
            java.lang.String r10 = r9.toLowerCase(r10)     // Catch: java.lang.Throwable -> Le6
            java.lang.String r11 = ".dng"
            boolean r10 = r10.contains(r11)     // Catch: java.lang.Throwable -> Le6
            if (r10 == 0) goto L7f
            goto Ld4
        L7f:
            boolean r10 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Throwable -> Le6
            if (r10 != 0) goto Ld4
            boolean r10 = r1.c(r6)     // Catch: java.lang.Throwable -> Le6
            if (r10 == 0) goto L8c
            goto Ld4
        L8c:
            int r10 = r7 * 3
            if (r8 > r10) goto Ld4
            int r10 = r8 * 3
            if (r7 <= r10) goto L95
            goto Ld4
        L95:
            java.lang.String r10 = "_id"
            int r10 = r2.getColumnIndexOrThrow(r10)     // Catch: java.lang.Throwable -> Le6
            long r10 = r2.getLong(r10)     // Catch: java.lang.Throwable -> Le6
            java.lang.String r12 = "_size"
            int r12 = r2.getColumnIndex(r12)     // Catch: java.lang.Throwable -> Le6
            long r12 = r2.getLong(r12)     // Catch: java.lang.Throwable -> Le6
            java.lang.String r14 = "date_added"
            int r14 = r2.getColumnIndex(r14)     // Catch: java.lang.Throwable -> Le6
            int r14 = r2.getInt(r14)     // Catch: java.lang.Throwable -> Le6
            long r14 = (long) r14     // Catch: java.lang.Throwable -> Le6
            r16 = r0
            java.util.List<com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel> r0 = r1.h     // Catch: java.lang.Throwable -> Le6
            com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel r1 = new com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel     // Catch: java.lang.Throwable -> Le6
            r1.<init>(r10, r9, r6)     // Catch: java.lang.Throwable -> Le6
            com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel r1 = r1.setWidth(r7)     // Catch: java.lang.Throwable -> Le6
            com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel r1 = r1.setHeight(r8)     // Catch: java.lang.Throwable -> Le6
            com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel r1 = r1.setSize(r12)     // Catch: java.lang.Throwable -> Le6
            r6 = 1000(0x3e8, double:4.94E-321)
            long r14 = r14 * r6
            com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel r1 = r1.setTime(r14)     // Catch: java.lang.Throwable -> Le6
            r0.add(r1)     // Catch: java.lang.Throwable -> Le6
            goto Ld6
        Ld4:
            r16 = r0
        Ld6:
            boolean r0 = r18.moveToNext()     // Catch: java.lang.Throwable -> Le6
            if (r0 != 0) goto Le0
            r18.close()
            return
        Le0:
            r1 = r17
            r0 = r16
            goto L52
        Le6:
            r0 = move-exception
            r18.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.automatchphoto.TrackPhotosDownload.aZR_(android.database.Cursor):void");
    }

    private boolean c(String str) {
        return str != null && str.equals(MimeType.GIF);
    }

    public HashMap<Integer, List<PhotoModel>> e() {
        return this.g;
    }

    public void b() {
        LogUtil.a("TrackPhotosDownload", "TrackPhotosDownload destroy()");
        if (this.e != null) {
            ThreadPoolManager.d().a(this.e);
        }
    }
}
