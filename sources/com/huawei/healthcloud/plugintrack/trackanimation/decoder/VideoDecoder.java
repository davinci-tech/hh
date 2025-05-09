package com.huawei.healthcloud.plugintrack.trackanimation.decoder;

import android.content.ContentValues;
import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMetadataRetriever;
import android.media.MediaMuxer;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;

/* loaded from: classes4.dex */
public class VideoDecoder {

    /* renamed from: a, reason: collision with root package name */
    private VideoDecoderCallback f3582a;
    private Context e;
    private String g;
    private MediaExtractor h;
    private MediaMuxer i;
    private Uri o;
    private int n = -1;
    private int j = 0;
    private int b = -1;
    private int c = 0;
    private int f = 0;
    private int d = 0;
    private long k = 0;

    public VideoDecoder(Context context) {
        this.e = context;
    }

    public void a(VideoDecoderCallback videoDecoderCallback) {
        this.f3582a = videoDecoderCallback;
    }

    public void e(String str, int i, int i2) {
        e(str, i * 1000000, i2 * 1000000);
    }

    public void e(String str, long j, long j2) {
        if (j > j2) {
            e(false);
            LogUtil.h("Suggestion_VideoDecoder", "splitVideo: error start time > end time");
            return;
        }
        d();
        if (!d(str, j, j2)) {
            e(false);
            LogUtil.h("Suggestion_VideoDecoder", "splitVideo: init Decoder fail");
            return;
        }
        if (this.h == null || this.i == null) {
            LogUtil.h("Suggestion_VideoDecoder", "splitVideo: extractor or muxer is null");
            e(false);
            return;
        }
        ByteBuffer allocate = ByteBuffer.allocate(this.f);
        this.i.start();
        b(j, j2, allocate);
        a(j, j2, allocate);
        try {
            this.h.release();
            this.i.stop();
            this.i.release();
            this.i = null;
            this.h = null;
            e(true);
        } catch (IllegalStateException e) {
            LogUtil.h("Suggestion_VideoDecoder", "splitVideo: error ", LogAnonymous.b((Throwable) e));
            e(false);
        }
        this.h = null;
    }

    public void b() {
        try {
            this.e = null;
            MediaExtractor mediaExtractor = this.h;
            if (mediaExtractor != null) {
                mediaExtractor.release();
                this.h = null;
            }
            MediaMuxer mediaMuxer = this.i;
            if (mediaMuxer != null) {
                mediaMuxer.stop();
                this.i.release();
                this.i = null;
            }
        } catch (IllegalStateException e) {
            LogUtil.h("Suggestion_VideoDecoder", "release: error ", LogAnonymous.b((Throwable) e));
        }
    }

    private void d() {
        this.i = null;
        this.h = null;
        this.g = "";
        this.o = null;
        this.n = -1;
        this.b = -1;
        this.j = 0;
        this.c = 0;
    }

    private boolean d(String str, long j, long j2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Suggestion_VideoDecoder", "initDecoder: video path is empty");
            return false;
        }
        MediaExtractor mediaExtractor = new MediaExtractor();
        this.h = mediaExtractor;
        try {
            mediaExtractor.setDataSource(str);
            b(str);
        } catch (IOException e) {
            LogUtil.h("Suggestion_VideoDecoder", "splitVideo: ", LogAnonymous.b((Throwable) e));
        }
        if (this.i == null) {
            return false;
        }
        d(str);
        for (int i = 0; i < this.h.getTrackCount(); i++) {
            MediaFormat trackFormat = this.h.getTrackFormat(i);
            String string = trackFormat.getString("mime");
            if (!TextUtils.isEmpty(string) && aXM_(j, j2, i, trackFormat, string)) {
                return false;
            }
        }
        return true;
    }

    private boolean aXM_(long j, long j2, int i, MediaFormat mediaFormat, String str) {
        if (str.startsWith("video/")) {
            this.j = i;
            this.f = mediaFormat.getInteger("max-input-size");
            long j3 = mediaFormat.getLong("durationUs");
            this.k = j3;
            if (j > j3) {
                LogUtil.h("Suggestion_VideoDecoder", "splitVideo: start time > video duration");
                return true;
            }
            if (j + j2 > j3) {
                LogUtil.h("Suggestion_VideoDecoder", "splitVideo: end time > video duration");
                return true;
            }
            LogUtil.c("Suggestion_VideoDecoder", "splitVideo: video info : width = ", Integer.valueOf(mediaFormat.getInteger("width")), " height = ", Integer.valueOf(mediaFormat.getInteger("height")), " maxInputSize ", Integer.valueOf(this.f), " videoDuration = ", Long.valueOf(this.k));
            this.n = this.i.addTrack(mediaFormat);
        }
        if (!str.startsWith("audio/")) {
            return false;
        }
        this.c = i;
        this.d = mediaFormat.getInteger("max-input-size");
        this.b = this.i.addTrack(mediaFormat);
        return false;
    }

    private void d(String str) {
        if (this.i == null) {
            LogUtil.h("Suggestion_VideoDecoder", "setMuxerRotation: muxer is null");
            return;
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(str);
        int e = CommonUtil.e(mediaMetadataRetriever.extractMetadata(24), -1);
        if (e >= 0) {
            this.i.setOrientationHint(e);
        }
    }

    private void a(long j, long j2, ByteBuffer byteBuffer) {
        this.h.selectTrack(this.c);
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        bufferInfo.presentationTimeUs = 0L;
        this.h.readSampleData(byteBuffer, 0);
        if (this.h.getSampleTime() == 0) {
            this.h.advance();
        }
        this.h.seekTo(j, 0);
        while (true) {
            int readSampleData = this.h.readSampleData(byteBuffer, 0);
            if (readSampleData < 0) {
                this.h.unselectTrack(this.c);
                return;
            }
            long sampleTime = this.h.getSampleTime();
            if (j2 != 0 && sampleTime > j + j2) {
                this.h.unselectTrack(this.c);
                return;
            }
            bufferInfo.offset = 0;
            bufferInfo.size = readSampleData;
            bufferInfo.flags = this.h.getSampleFlags();
            bufferInfo.presentationTimeUs = sampleTime;
            this.i.writeSampleData(this.b, byteBuffer, bufferInfo);
            this.h.advance();
        }
    }

    private void b(long j, long j2, ByteBuffer byteBuffer) {
        this.h.selectTrack(this.j);
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        bufferInfo.presentationTimeUs = 0L;
        this.h.readSampleData(byteBuffer, 0);
        this.h.seekTo(j, 0);
        while (true) {
            int readSampleData = this.h.readSampleData(byteBuffer, 0);
            if (readSampleData < 0) {
                this.h.unselectTrack(this.j);
                return;
            }
            long sampleTime = this.h.getSampleTime();
            if (j2 != 0 && sampleTime > j + j2) {
                this.h.unselectTrack(this.j);
                return;
            }
            bufferInfo.flags = this.h.getSampleFlags();
            bufferInfo.offset = 0;
            bufferInfo.size = readSampleData;
            bufferInfo.presentationTimeUs = sampleTime;
            this.i.writeSampleData(this.n, byteBuffer, bufferInfo);
            this.h.advance();
        }
    }

    private void b(String str) {
        if (this.e == null) {
            LogUtil.h("Suggestion_VideoDecoder", "initMuxer: context is null");
            e(false);
            return;
        }
        if (PermissionUtil.c()) {
            c(str);
        } else {
            c();
        }
        try {
            if (!TextUtils.isEmpty(this.g)) {
                this.i = new MediaMuxer(this.g, 0);
            } else if (this.o != null && PermissionUtil.c()) {
                this.i = new MediaMuxer(this.e.getContentResolver().openFileDescriptor(this.o, "rw").getFileDescriptor(), 0);
            } else {
                LogUtil.h("Suggestion_VideoDecoder", "initMuxer: path and uri is empty");
            }
        } catch (IOException e) {
            LogUtil.h("Suggestion_VideoDecoder", "initMuxer: error ", LogAnonymous.b((Throwable) e));
            e(false);
        }
    }

    private void c() {
        String str = CommonUtil.j(BaseApplication.getContext()) + File.separator + "DCIM/HuaweiHealth/";
        if (!e(str)) {
            LogUtil.h("Suggestion_VideoDecoder", "folder create fail ", str);
            e(false);
            return;
        }
        try {
            this.g = str + new SimpleDateFormat(HwDrmConstant.TIME_FORMAT).format(Long.valueOf(System.currentTimeMillis())) + "_cut.mp4";
        } catch (IllegalArgumentException e) {
            LogUtil.h("Suggestion_VideoDecoder", "initMuxer: error ", LogAnonymous.b((Throwable) e));
            e(false);
        }
    }

    private boolean e(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }

    private void c(String str) {
        String substring = str.contains(File.separator) ? str.substring(str.lastIndexOf(File.separator)) : null;
        if (!TextUtils.isEmpty(substring) && substring.contains(".")) {
            substring = substring.substring(0, substring.lastIndexOf("."));
        }
        if (!TextUtils.isEmpty(substring)) {
            substring = substring + "_cut.mp4";
        }
        long currentTimeMillis = System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", substring);
        contentValues.put("_display_name", substring);
        contentValues.put("mime_type", "video/mp4");
        long j = currentTimeMillis / 1000;
        contentValues.put("date_added", Long.valueOf(j));
        contentValues.put("date_modified", Long.valueOf(j));
        contentValues.put("date_expires", Long.valueOf((currentTimeMillis + 3600000) / 1000));
        contentValues.put("is_pending", (Integer) 1);
        contentValues.put("relative_path", "DCIM/HuaweiHealth/");
        try {
            this.o = BaseApplication.getContext().getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
        } catch (IllegalArgumentException e) {
            LogUtil.h("Suggestion_VideoDecoder", "initVideoName: ", LogAnonymous.b((Throwable) e));
        }
    }

    private void e(boolean z) {
        if (this.f3582a == null) {
            LogUtil.h("Suggestion_VideoDecoder", "notifyResult: callback is empty");
            return;
        }
        if (this.o != null) {
            a(z);
        }
        if (TextUtils.isEmpty(this.g)) {
            this.f3582a.onResult(false, "");
            LogUtil.h("Suggestion_VideoDecoder", "notifyResult: path is empty");
            return;
        }
        this.f3582a.onResult(z, this.g);
        if (z) {
            return;
        }
        File file = new File(this.g);
        if (file.exists()) {
            FileUtils.i(file);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x003a, code lost:
    
        if (r12 != null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0059, code lost:
    
        return r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0056, code lost:
    
        r12.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0054, code lost:
    
        if (r12 == null) goto L22;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005f  */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String aXN_(android.net.Uri r12) {
        /*
            r11 = this;
            java.lang.String r0 = "Suggestion_VideoDecoder"
            java.lang.String r1 = "_data"
            java.lang.String[] r1 = new java.lang.String[]{r1}
            r8 = 1
            r9 = 0
            r10 = 0
            android.content.Context r2 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Throwable -> L3f java.lang.IllegalArgumentException -> L41
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.lang.Throwable -> L3f java.lang.IllegalArgumentException -> L41
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r12
            r4 = r1
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L3f java.lang.IllegalArgumentException -> L41
            if (r12 != 0) goto L2d
            java.lang.Object[] r1 = new java.lang.Object[r8]     // Catch: java.lang.IllegalArgumentException -> L3d java.lang.Throwable -> L5a
            java.lang.String r2 = "getFilePathFromContentUri: cursor is null"
            r1[r9] = r2     // Catch: java.lang.IllegalArgumentException -> L3d java.lang.Throwable -> L5a
            com.huawei.hwlogsmodel.LogUtil.h(r0, r1)     // Catch: java.lang.IllegalArgumentException -> L3d java.lang.Throwable -> L5a
            if (r12 == 0) goto L2c
            r12.close()
        L2c:
            return r10
        L2d:
            r12.moveToFirst()     // Catch: java.lang.IllegalArgumentException -> L3d java.lang.Throwable -> L5a
            r1 = r1[r9]     // Catch: java.lang.IllegalArgumentException -> L3d java.lang.Throwable -> L5a
            int r1 = r12.getColumnIndex(r1)     // Catch: java.lang.IllegalArgumentException -> L3d java.lang.Throwable -> L5a
            java.lang.String r10 = r12.getString(r1)     // Catch: java.lang.IllegalArgumentException -> L3d java.lang.Throwable -> L5a
            if (r12 == 0) goto L59
            goto L56
        L3d:
            r1 = move-exception
            goto L44
        L3f:
            r12 = move-exception
            goto L5d
        L41:
            r12 = move-exception
            r1 = r12
            r12 = r10
        L44:
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L5a
            java.lang.String r3 = "getFilePathFromContentUri: "
            r2[r9] = r3     // Catch: java.lang.Throwable -> L5a
            java.lang.String r1 = health.compact.a.LogAnonymous.b(r1)     // Catch: java.lang.Throwable -> L5a
            r2[r8] = r1     // Catch: java.lang.Throwable -> L5a
            com.huawei.hwlogsmodel.LogUtil.h(r0, r2)     // Catch: java.lang.Throwable -> L5a
            if (r12 == 0) goto L59
        L56:
            r12.close()
        L59:
            return r10
        L5a:
            r0 = move-exception
            r10 = r12
            r12 = r0
        L5d:
            if (r10 == 0) goto L62
            r10.close()
        L62:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.healthcloud.plugintrack.trackanimation.decoder.VideoDecoder.aXN_(android.net.Uri):java.lang.String");
    }

    private void a(boolean z) {
        if (this.o == null) {
            LogUtil.h("Suggestion_VideoDecoder", "onVideoSaveSuccess: uri is null");
            return;
        }
        try {
            if (z) {
                ContentValues contentValues = new ContentValues();
                contentValues.putNull("date_expires");
                contentValues.put("is_pending", (Integer) 0);
                BaseApplication.getContext().getContentResolver().update(this.o, contentValues, null, null);
                this.g = aXN_(this.o);
            } else {
                BaseApplication.getContext().getContentResolver().delete(this.o, null, null);
                this.o = null;
            }
        } catch (IllegalArgumentException e) {
            LogUtil.h("Suggestion_VideoDecoder", "onVideoSaveSuccess: resolver update error ", LogAnonymous.b((Throwable) e));
        }
    }
}
