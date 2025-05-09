package com.huawei.haf.mediaprocess;

import android.graphics.Bitmap;
import android.media.MediaExtractor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.LogUtil;
import java.io.IOException;

/* loaded from: classes.dex */
public class VideoUtil {
    private VideoUtil() {
    }

    public static int zh_(MediaExtractor mediaExtractor, boolean z) {
        int trackCount = mediaExtractor.getTrackCount();
        for (int i = 0; i < trackCount; i++) {
            String string = mediaExtractor.getTrackFormat(i).getString("mime");
            if (TextUtils.isEmpty(string)) {
                return -5;
            }
            if (z && string.startsWith("audio/")) {
                return i;
            }
            if (!z && string.startsWith("video/")) {
                return i;
            }
        }
        return -5;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.lang.Object, java.lang.String] */
    public static Bitmap zg_(Uri uri) {
        Bitmap bitmap;
        String str = "loadFirst release exception:";
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                mediaMetadataRetriever.setDataSource(BaseApplication.e(), uri);
                bitmap = mediaMetadataRetriever.getFrameAtTime();
            } finally {
                try {
                    mediaMetadataRetriever.release();
                } catch (IOException e) {
                    LogUtil.e("VideoUtil", str, ExceptionUtils.d(e));
                }
            }
        } catch (IllegalArgumentException e2) {
            LogUtil.e("VideoUtil", ExceptionUtils.d(e2));
            try {
                mediaMetadataRetriever.release();
            } catch (IOException e3) {
                LogUtil.e("VideoUtil", "loadFirst release exception:", ExceptionUtils.d(e3));
            }
            bitmap = null;
            str = str;
            mediaMetadataRetriever = mediaMetadataRetriever;
        }
        if (bitmap != null) {
            LogUtil.c("VideoUtil", "loadFirst:", Integer.valueOf(bitmap.getHeight()));
        }
        return bitmap;
    }
}
