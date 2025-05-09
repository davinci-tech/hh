package com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.provider.MediaStore;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.VideoModel;
import health.compact.a.CommonUtils;
import java.util.List;

/* loaded from: classes4.dex */
public class VideoLoad extends AsyncTaskLoader<List<VideoModel>> {
    private static final String[] d = {"_id", "_data", "_size"};

    /* renamed from: a, reason: collision with root package name */
    private boolean f3628a;
    private List<VideoModel> c;
    private final Loader<List<VideoModel>>.ForceLoadContentObserver e;

    public VideoLoad(Context context) {
        super(context);
        this.c = null;
        this.f3628a = false;
        this.e = new Loader.ForceLoadContentObserver();
    }

    @Override // androidx.loader.content.Loader
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void deliverResult(List<VideoModel> list) {
        if (isReset() || !isStarted()) {
            return;
        }
        super.deliverResult(list);
    }

    @Override // androidx.loader.content.Loader
    public void onStartLoading() {
        List<VideoModel> list = this.c;
        if (list != null) {
            deliverResult(list);
        }
        if (takeContentChanged() || this.c == null) {
            forceLoad();
        }
        c();
    }

    @Override // androidx.loader.content.Loader
    public void onStopLoading() {
        cancelLoad();
    }

    @Override // androidx.loader.content.Loader
    public void onReset() {
        super.onReset();
        onStopLoading();
        this.c = null;
        d();
    }

    @Override // androidx.loader.content.Loader
    public void onAbandon() {
        super.onAbandon();
        d();
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public List<VideoModel> loadInBackground() {
        return a();
    }

    private List<VideoModel> a() {
        List<VideoModel> b = b();
        this.c = b;
        return b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0085, code lost:
    
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.VideoModel> b() {
        /*
            r12 = this;
            java.lang.String r0 = "_data"
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            android.content.Context r3 = r12.getContext()     // Catch: java.lang.Throwable -> L89
            android.content.ContentResolver r4 = r3.getContentResolver()     // Catch: java.lang.Throwable -> L89
            android.net.Uri r5 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI     // Catch: java.lang.Throwable -> L89
            java.lang.String[] r6 = com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.VideoLoad.d     // Catch: java.lang.Throwable -> L89
            r7 = 0
            r8 = 0
            java.lang.String r9 = "_id DESC"
            android.database.Cursor r2 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L89
            if (r2 == 0) goto L83
            int r3 = r2.getCount()     // Catch: java.lang.Throwable -> L89
            if (r3 > 0) goto L25
            goto L83
        L25:
            r2.moveToFirst()     // Catch: java.lang.Throwable -> L89
            int r3 = r2.getColumnIndexOrThrow(r0)     // Catch: java.lang.Throwable -> L89
            int r0 = r2.getColumnIndexOrThrow(r0)     // Catch: java.lang.Throwable -> L89
        L30:
            java.lang.String r4 = r2.getString(r3)     // Catch: java.lang.Throwable -> L89
            java.lang.String r5 = r2.getString(r0)     // Catch: java.lang.Throwable -> L89
            boolean r6 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L89
            if (r6 == 0) goto L3f
            goto L77
        L3f:
            java.io.File r6 = new java.io.File     // Catch: java.lang.Throwable -> L89
            r6.<init>(r4)     // Catch: java.lang.Throwable -> L89
            boolean r6 = r6.exists()     // Catch: java.lang.Throwable -> L89
            if (r6 != 0) goto L4b
            goto L77
        L4b:
            java.lang.String r6 = "_id"
            int r6 = r2.getColumnIndexOrThrow(r6)     // Catch: java.lang.Throwable -> L89
            java.lang.String r7 = "_size"
            int r7 = r2.getColumnIndex(r7)     // Catch: java.lang.Throwable -> L89
            long r8 = r2.getLong(r6)     // Catch: java.lang.Throwable -> L89
            long r6 = r2.getLong(r7)     // Catch: java.lang.Throwable -> L89
            int r10 = r12.a(r4)     // Catch: java.lang.Throwable -> L89
            com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.VideoModel r11 = new com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.VideoModel     // Catch: java.lang.Throwable -> L89
            r11.<init>(r8, r4)     // Catch: java.lang.Throwable -> L89
            com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.VideoModel r4 = r11.setThumbnailPath(r5)     // Catch: java.lang.Throwable -> L89
            com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.VideoModel r4 = r4.setSize(r6)     // Catch: java.lang.Throwable -> L89
            com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.VideoModel r4 = r4.setDuration(r10)     // Catch: java.lang.Throwable -> L89
            r1.add(r4)     // Catch: java.lang.Throwable -> L89
        L77:
            boolean r4 = r2.moveToNext()     // Catch: java.lang.Throwable -> L89
            if (r4 != 0) goto L30
            if (r2 == 0) goto L82
            r2.close()
        L82:
            return r1
        L83:
            if (r2 == 0) goto L88
            r2.close()
        L88:
            return r1
        L89:
            r0 = move-exception
            if (r2 == 0) goto L8f
            r2.close()
        L8f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.VideoLoad.b():java.util.List");
    }

    private int a(String str) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(str);
        return CommonUtils.h(mediaMetadataRetriever.extractMetadata(9));
    }

    private void c() {
        if (this.f3628a) {
            return;
        }
        getContext().getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, false, this.e);
        this.f3628a = true;
    }

    private void d() {
        if (this.f3628a) {
            this.f3628a = false;
            getContext().getContentResolver().unregisterContentObserver(this.e);
        }
    }
}
