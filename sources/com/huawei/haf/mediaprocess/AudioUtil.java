package com.huawei.haf.mediaprocess;

import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import health.compact.a.LogUtil;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class AudioUtil {
    protected static final Map<Integer, Integer> c;

    static {
        HashMap hashMap = new HashMap();
        c = hashMap;
        hashMap.put(96000, 0);
        hashMap.put(88200, 1);
        hashMap.put(64000, 2);
        hashMap.put(48000, 3);
        hashMap.put(44100, 4);
        hashMap.put(32000, 5);
        hashMap.put(24000, 6);
        hashMap.put(22050, 7);
        hashMap.put(16000, 8);
        hashMap.put(12000, 9);
        hashMap.put(11025, 10);
        hashMap.put(8000, 11);
        hashMap.put(7350, 12);
    }

    private AudioUtil() {
    }

    public static long zb_(MediaExtractor mediaExtractor, MediaMuxer mediaMuxer, int i, VideoProgressListener videoProgressListener) {
        int zh_ = VideoUtil.zh_(mediaExtractor, true);
        mediaExtractor.selectTrack(zh_);
        mediaExtractor.seekTo(0L, 2);
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        long j = 0;
        while (true) {
            long sampleTime = mediaExtractor.getSampleTime();
            if (sampleTime == -1) {
                break;
            }
            MediaFormat trackFormat = mediaExtractor.getTrackFormat(zh_);
            long j2 = trackFormat.getLong("durationUs");
            if (videoProgressListener != null && j2 != 0) {
                videoProgressListener.onProgress(Math.min(1.0f, Math.max(0.0f, sampleTime / j2)));
            }
            bufferInfo.presentationTimeUs = sampleTime;
            bufferInfo.flags = mediaExtractor.getSampleFlags();
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(za_(trackFormat));
            bufferInfo.size = mediaExtractor.readSampleData(allocateDirect, 0);
            if (bufferInfo.size < 0) {
                break;
            }
            LogUtil.c("AudioUtil", "writeAudioSampleData,time:", Float.valueOf(new BigDecimal(bufferInfo.presentationTimeUs).divide(new BigDecimal(1000), 2, 4).floatValue()));
            mediaMuxer.writeSampleData(i, allocateDirect, bufferInfo);
            long j3 = bufferInfo.presentationTimeUs;
            mediaExtractor.advance();
            j = j3;
        }
        return j;
    }

    public static int za_(MediaFormat mediaFormat) {
        if (mediaFormat.containsKey("max-input-size")) {
            return mediaFormat.getInteger("max-input-size");
        }
        return 100000;
    }
}
