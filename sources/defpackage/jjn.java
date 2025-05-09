package defpackage;

import android.content.ContentUris;
import android.content.Context;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.NegotiationData;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.IoUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes5.dex */
public class jjn {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13897a = new Object();
    private static jjn d;
    private static final Map<String, Integer> e;
    private ParcelFileDescriptor g;
    private List<NegotiationData.TypeStruct> h = new ArrayList(16);
    private boolean c = false;
    private List<Integer> b = new ArrayList(16);

    static {
        HashMap hashMap = new HashMap(16);
        e = hashMap;
        hashMap.put("MP3", 0);
        hashMap.put("WAV", 1);
        hashMap.put("AAC", 2);
        hashMap.put("SBC", 3);
        hashMap.put("MSBC", 4);
        hashMap.put("HWA", 5);
        hashMap.put("CVSD", 6);
        hashMap.put("PCM", 8);
        hashMap.put("APE", 9);
        hashMap.put("ALAC", 10);
        hashMap.put("M4A", 10);
        hashMap.put("FLAC", 11);
        hashMap.put("OPUS", 12);
        hashMap.put("OGG", 13);
        hashMap.put("BUTT", 14);
        hashMap.put("AMR", 16);
        hashMap.put("IMY", 17);
        d = null;
    }

    public static jjn a() {
        jjn jjnVar;
        synchronized (f13897a) {
            if (d == null) {
                d = new jjn();
            }
            jjnVar = d;
        }
        return jjnVar;
    }

    public void b(List<Integer> list) {
        this.b = list;
    }

    public void e(List<NegotiationData.TypeStruct> list) {
        this.h = list;
    }

    public void b(boolean z) {
        this.c = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0063 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0065 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean d(com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong r4) {
        /*
            r3 = this;
            java.lang.String r0 = "song.getSuffix()"
            java.lang.String r1 = r4.getSuffix()
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
            java.lang.String r1 = "MusicMultiFormatManager"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            java.lang.String r0 = r4.getSuffix()
            r0.hashCode()
            java.lang.String r2 = "aac"
            boolean r2 = r0.equals(r2)
            if (r2 != 0) goto L50
            java.lang.String r2 = "mp3"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L3d
            java.lang.String r0 = "other music format"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)
            boolean r0 = r3.e(r4)
            if (r0 == 0) goto L63
            boolean r4 = r3.c(r4)
            if (r4 == 0) goto L63
            goto L65
        L3d:
            boolean r0 = r3.a(r4)
            if (r0 != 0) goto L65
            boolean r0 = r3.e(r4)
            if (r0 == 0) goto L63
            boolean r4 = r3.c(r4)
            if (r4 == 0) goto L63
            goto L65
        L50:
            boolean r0 = r3.b(r4)
            if (r0 != 0) goto L65
            boolean r0 = r3.e(r4)
            if (r0 == 0) goto L63
            boolean r4 = r3.c(r4)
            if (r4 == 0) goto L63
            goto L65
        L63:
            r4 = 0
            goto L66
        L65:
            r4 = 1
        L66:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jjn.d(com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong):boolean");
    }

    private boolean e(MusicSong musicSong) {
        Integer num = e.get(musicSong.getSuffix().toUpperCase(Locale.ENGLISH));
        int intValue = num == null ? -1 : num.intValue();
        LogUtil.a("MusicMultiFormatManager", "checkMusicBitrate, musicType :", Integer.valueOf(intValue));
        if (this.h.isEmpty()) {
            LogUtil.h("MusicMultiFormatManager", "mTypeStructList mBitRateMap is empty.");
            return true;
        }
        i(musicSong);
        j(musicSong);
        LogUtil.a("MusicMultiFormatManager", "checkMusicBitrate, getSongSize :", Long.valueOf(musicSong.getSongSize()), ", getDuration :", musicSong.getDuration());
        long j = 0;
        try {
            if (musicSong.getDuration().longValue() != 0) {
                j = ((musicSong.getSongSize() * 8) / (musicSong.getDuration().longValue() / 1000000)) / 1024;
            }
        } catch (ArithmeticException unused) {
            LogUtil.b("MusicMultiFormatManager", "checkMusicBitrate, ArithmeticException");
        }
        NegotiationData.TypeStruct a2 = a(intValue);
        if (a2 == null) {
            LogUtil.h("MusicMultiFormatManager", "checkMusicBitrate, struct is null.");
            return true;
        }
        int musicBitrate = a2.getMusicBitrate();
        LogUtil.a("MusicMultiFormatManager", "checkMusicBitrate, deviceBitrate :", Integer.valueOf(musicBitrate), ", bitrate :", Long.valueOf(j));
        if (musicBitrate >= j) {
            return true;
        }
        musicSong.setSyncFailedErrorCode(1);
        return false;
    }

    private boolean c(MusicSong musicSong) {
        Integer num = e.get(musicSong.getSuffix().toUpperCase(Locale.ENGLISH));
        int intValue = num == null ? -1 : num.intValue();
        LogUtil.a("MusicMultiFormatManager", "checkMusicChannelCount, musicType :", Integer.valueOf(intValue));
        if (this.h.isEmpty()) {
            LogUtil.h("MusicMultiFormatManager", "checkMusicChannelCount mTypeStructList is empty.");
            return true;
        }
        i(musicSong);
        j(musicSong);
        NegotiationData.TypeStruct a2 = a(intValue);
        if (a2 == null) {
            LogUtil.h("MusicMultiFormatManager", "checkMusicChannelCount, struct is null.");
            return true;
        }
        int musicChannelCount = a2.getMusicChannelCount();
        LogUtil.a("MusicMultiFormatManager", "checkMusicChannelCount, deviceChannelCount :", Integer.valueOf(musicChannelCount));
        if (musicChannelCount >= musicSong.getChannelCount()) {
            return true;
        }
        musicSong.setSyncFailedErrorCode(1);
        return false;
    }

    private NegotiationData.TypeStruct a(int i) {
        for (NegotiationData.TypeStruct typeStruct : this.h) {
            if (typeStruct.getMusicType() == i) {
                return typeStruct;
            }
        }
        return null;
    }

    private boolean a(MusicSong musicSong) {
        List<Integer> list = this.b;
        if (list != null && !list.isEmpty()) {
            i(musicSong);
            j(musicSong);
            if (this.b.contains(Integer.valueOf(musicSong.getSampleRate())) && !"audio/mp4a-latm".equals(musicSong.getMimeType())) {
                return true;
            }
            musicSong.setSyncFailedErrorCode(1);
            return false;
        }
        LogUtil.h("MusicMultiFormatManager", "mMp3SampleRateList is empty");
        return true;
    }

    private boolean b(MusicSong musicSong) {
        if (!this.c) {
            LogUtil.a("MusicMultiFormatManager", "not limit adts encode");
            return true;
        }
        String i = i(musicSong);
        if (!TextUtils.isEmpty(i)) {
            if ("FFF".equals(i)) {
                LogUtil.a("MusicMultiFormatManager", "ADTS format:", i);
                return true;
            }
            LogUtil.a("MusicMultiFormatManager", "not ADTS format:", i);
            musicSong.setSyncFailedErrorCode(2);
        } else {
            LogUtil.h("MusicMultiFormatManager", "stringHex is empty");
        }
        return false;
    }

    private String i(MusicSong musicSong) {
        String str = "";
        if (!new File(musicSong.getSongFilePath()).exists()) {
            LogUtil.h("MusicMultiFormatManager", "file not exists");
            return "";
        }
        LogUtil.a("MusicMultiFormatManager", "file.exists");
        FileInputStream e2 = e(musicSong.getSongFilePath(), musicSong.getSourceId(), BaseApplication.getContext());
        if (e2 == null) {
            LogUtil.h("MusicMultiFormatManager", "getFileByPath error.");
            IoUtils.e(this.g);
            return "";
        }
        try {
            try {
                byte[] bArr = new byte[2];
                if (e2.read(bArr) > 0) {
                    str = cvx.d(bArr);
                    if (str != null) {
                        str = str.substring(0, 3);
                    }
                    LogUtil.a("MusicMultiFormatManager", "stringHex :", str);
                }
            } catch (IOException unused) {
                LogUtil.b("MusicMultiFormatManager", "requestUtl exception");
            }
            return str;
        } finally {
            IoUtils.e(e2);
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x005b -> B:5:0x005c). Please report as a decompilation issue!!! */
    private FileInputStream e(String str, long j, Context context) {
        FileInputStream fileInputStream;
        try {
            try {
                try {
                    try {
                    } catch (IOException unused) {
                        LogUtil.b("MusicMultiFormatManager", "getFileInputStreamByUriId, IOException");
                    }
                } catch (FileNotFoundException unused2) {
                    LogUtil.b("MusicMultiFormatManager", "FileNotFoundException");
                }
            } catch (SecurityException e2) {
                LogUtil.b("MusicMultiFormatManager", "getFileInputStreamByUriId, SecurityException", ExceptionUtils.d(e2));
            }
            if (j == -1) {
                fileInputStream = new FileInputStream(CommonUtil.c(str));
            } else {
                ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, j), "r");
                this.g = openFileDescriptor;
                if (openFileDescriptor != null) {
                    fileInputStream = new FileInputStream(this.g.getFileDescriptor());
                }
                fileInputStream = null;
            }
            return fileInputStream;
        } finally {
            IoUtils.e(this.g);
        }
    }

    private void j(MusicSong musicSong) {
        if (this.g != null) {
            MediaExtractor mediaExtractor = new MediaExtractor();
            try {
                try {
                    try {
                        try {
                            LogUtil.a("MusicMultiFormatManager", "MediaExtractor start");
                            mediaExtractor.setDataSource(this.g.getFileDescriptor());
                            MediaFormat trackFormat = mediaExtractor.getTrackFormat(0);
                            musicSong.setSampleRate(trackFormat.getInteger("sample-rate"));
                            musicSong.setMimeType(trackFormat.getString("mime"));
                            musicSong.setChannelCount(trackFormat.getInteger("channel-count"));
                            musicSong.setDuration(trackFormat.getLong("durationUs"));
                            LogUtil.a("MusicMultiFormatManager", "sampleRate: ", Integer.valueOf(musicSong.getSampleRate()), ", mimeType: ", musicSong.getMimeType(), ", channelCount:", Integer.valueOf(musicSong.getChannelCount()), ", duration:", musicSong.getDuration());
                        } catch (NullPointerException unused) {
                            LogUtil.b("MusicMultiFormatManager", "MediaExtractor NullPointerException.");
                        }
                    } catch (IOException unused2) {
                        LogUtil.b("MusicMultiFormatManager", "MediaExtractor failed");
                    }
                } catch (IllegalArgumentException unused3) {
                    LogUtil.b("MusicMultiFormatManager", "MediaExtractor IllegalArgumentException.");
                }
                IoUtils.e(this.g);
                mediaExtractor.release();
            } catch (Throwable th) {
                IoUtils.e(this.g);
                throw th;
            }
        }
    }
}
