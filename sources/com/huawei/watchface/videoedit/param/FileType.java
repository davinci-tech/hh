package com.huawei.watchface.videoedit.param;

import com.huawei.openalliance.ad.constant.MimeType;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.videoedit.sysc.Optional;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes9.dex */
public final class FileType {
    public static final int FILE_TYPE_3GPP = 23;
    public static final int FILE_TYPE_3GPP2 = 24;
    public static final int FILE_TYPE_AAC = 8;
    public static final int FILE_TYPE_AIF = 13;
    public static final int FILE_TYPE_AMR = 4;
    public static final int FILE_TYPE_APE = 14;
    public static final int FILE_TYPE_ARW = 304;
    public static final int FILE_TYPE_ASF = 26;
    public static final int FILE_TYPE_AU = 12;
    public static final int FILE_TYPE_AVI = 29;
    public static final int FILE_TYPE_AWB = 5;
    public static final int FILE_TYPE_BMP = 37;
    public static final int FILE_TYPE_CR2 = 301;
    public static final int FILE_TYPE_DNG = 300;
    public static final int FILE_TYPE_FLAC = 10;
    public static final int FILE_TYPE_GIF = 35;
    public static final int FILE_TYPE_HEIC = 39;
    public static final int FILE_TYPE_ISMA = 15;
    public static final int FILE_TYPE_ISMV = 18;
    public static final int FILE_TYPE_JPEG = 34;
    public static final int FILE_TYPE_M4A = 2;
    public static final int FILE_TYPE_M4V = 22;
    public static final int FILE_TYPE_MKA = 9;
    public static final int FILE_TYPE_MKV = 27;
    public static final int FILE_TYPE_MP2TS = 28;
    public static final int FILE_TYPE_MP3 = 1;
    public static final int FILE_TYPE_MP4 = 21;
    public static final int FILE_TYPE_NEF = 302;
    public static final int FILE_TYPE_NRW = 303;
    public static final int FILE_TYPE_OGG = 7;
    public static final int FILE_TYPE_ORF = 306;
    public static final int FILE_TYPE_PEF = 308;
    public static final int FILE_TYPE_PNG = 36;
    public static final int FILE_TYPE_RA = 11;
    public static final int FILE_TYPE_RAF = 307;
    public static final int FILE_TYPE_RM = 30;
    public static final int FILE_TYPE_RMHD = 19;
    public static final int FILE_TYPE_RMVB = 20;
    public static final int FILE_TYPE_RV = 31;
    public static final int FILE_TYPE_RW2 = 305;
    public static final int FILE_TYPE_SRW = 309;
    public static final int FILE_TYPE_WAV = 3;
    public static final int FILE_TYPE_WBMP = 38;
    public static final int FILE_TYPE_WEBM = 32;
    public static final int FILE_TYPE_WEBP = 40;
    public static final int FILE_TYPE_WMA = 6;
    public static final int FILE_TYPE_WMV = 25;
    public static final int FILE_TYPE_WPNG = 310;
    private static final int FIRST_AUDIO_FILE_TYPE = 1;
    private static final int FIRST_IMAGE_FILE_TYPE = 34;
    private static final int FIRST_RAW_IMAGE_FILE_TYPE = 300;
    private static final int FIRST_VIDEO_FILE_TYPE = 18;
    private static final int LAST_AUDIO_FILE_TYPE = 15;
    private static final int LAST_IMAGE_FILE_TYPE = 40;
    private static final int LAST_RAW_IMAGE_FILE_TYPE = 310;
    private static final int LAST_VIDEO_FILE_TYPE = 32;
    private static final HashMap<String, Integer> FILE_TYPE_MAP = new HashMap<>();
    private static final HashMap<String, Integer> MIME_TYPE_MAP = new HashMap<>();

    public static boolean isAudioFileType(int i) {
        return i >= 1 && i <= 15;
    }

    public static boolean isImageFileType(int i) {
        return (i >= 34 && i <= 40) || (i >= 300 && i <= 310);
    }

    public static boolean isVideoFileType(int i) {
        return i >= 18 && i <= 32;
    }

    static {
        addFileType("MPGA", 1, "audio/mpeg");
        addFileType("MP3", 1, "audio/mpeg");
        addFileType("WAV", 3, "audio/x-wav");
        addFileType("AMR", 4, "audio/amr");
        addFileType("M4A", 2, "audio/mp4");
        addFileType("AWB", 5, "audio/amr-wb");
        addFileType("OGG", 7, "audio/ogg");
        addFileType("OGG", 7, "application/ogg");
        addFileType("OGA", 7, "application/ogg");
        addFileType("AAC", 8, "audio/aac");
        addFileType("AAC", 8, "audio/aac-adts");
        addFileType("AAC", 8, "audio/ffmpeg");
        addFileType("AU", 12, "audio/basic");
        addFileType("AIF", 13, "audio/x-aiff");
        addFileType("APE", 14, "audio/ffmpeg");
        addFileType("RA", 11, "audio/x-pn-realaudio");
        addFileType("3G2", 5, "audio/3gpp2");
        addFileType("3GP", 5, "audio/3gpp");
        addFileType("AC3", 5, "audio/ac3");
        addFileType("ASF", 5, "audio/x-ms-asf");
        addFileType("AVI", 5, "audio/avi");
        addFileType("F4V", 5, "audio/mp4");
        addFileType("FLV", 5, "audio/x-flv");
        addFileType("M2TS", 5, "audio/x-mpegts");
        addFileType("MKA", 5, "audio/x-matroska");
        addFileType("MKV", 5, "audio/x-matroska");
        addFileType("MMF", 5, "audio/x-skt-lbs");
        addFileType("MP4", 5, "audio/mp4");
        addFileType("MOV", 5, "audio/quicktime");
        addFileType("OGG", 5, "audio/ogg");
        addFileType("RA", 5, "audio/x-pn-realaudio");
        addFileType("RM", 30, "audio/x-pn-realaudio");
        addFileType("RM", 5, "audio/x-pn-realaudio");
        addFileType("RMVB", 5, "audio/x-pn-realaudio");
        addFileType("RMHD", 19, "audio/x-pn-realaudio");
        addFileType("TS", 5, "audio/x-mpegts");
        addFileType("WEBM", 5, "audio/x-matroska");
        addFileType("MKA", 9, "audio/x-matroska");
        addFileType("MPEG", 21, "video/mpeg");
        addFileType("MPG", 21, "video/mpeg");
        addFileType("M4V", 22, "video/mp4");
        addFileType("MP4", 21, "video/mp4");
        addFileType("3GPP", 23, "video/3gpp");
        addFileType("3G2", 24, "video/3gpp2");
        addFileType("3GP", 23, "video/3gpp");
        addFileType("3GPP2", 24, "video/3gpp2");
        addFileType("WEBM", 32, "video/webm");
        addFileType("MKV", 27, "video/x-matroska");
        addFileType("TS", 28, "video/x-mpegts");
        addFileType("AVI", 29, "video/avi");
        addFileType("F4V", 28, "video/mp4");
        addFileType("FLV", 28, "video/x-flv");
        addFileType("M2TS", 28, "video/x-mpegts");
        addFileType("MOV", 28, "video/quicktime");
        addFileType("DIVX", 28, "video/quicktime");
        addFileType("RM", 28, "video/x-pn-realvideo");
        addFileType("RMVB", 28, "video/x-pn-realvideo");
        addFileType("WMV", 25, "video/x-ms-wmv");
        addFileType("ASF", 26, "video/x-ms-asf");
        addFileType("RM", 30, "video/x-pn-realvideo");
        addFileType("RV", 30, "video/x-pn-realvideo");
        addFileType("RMVB", 20, "video/x-pn-realvideo");
        addFileType("RMHD", 19, "video/x-pn-realvideo");
        addFileType("JPG", 34, MimeType.JPEG);
        addFileType("JPE", 34, MimeType.JPEG);
        addFileType("GIF", 35, MimeType.GIF);
        addFileType("JPEG", 34, MimeType.JPEG);
        addFileType("BMP", 37, "image/x-ms-bmp");
        addFileType("WBMP", 38, "image/vnd.wap.wbmp");
        addFileType("PNG", 36, "image/png");
        addFileType("HEIC", 39, "image/heic");
        addFileType("WEBP", 40, "image/webp");
        addFileType("HEIF", 39, "image/heic");
        addFileType("DNG", 300, "image/x-adobe-dng");
        addFileType("CR2", 301, "image/x-canon-cr2");
        addFileType("ARW", 304, "image/x-sony-arw");
        addFileType("NEF", 302, "image/x-nikon-nef");
        addFileType("NRW", 303, "image/x-nikon-nrw");
        addFileType("RW2", 305, "image/x-panasonic-rw2");
        addFileType("ORF", 306, "image/x-olympus-orf");
        addFileType("RAF", 307, "image/x-fuji-raf");
        addFileType("PEF", 308, "image/x-pentax-pef");
        addFileType("SRW", 309, "image/x-samsung-srw");
        addFileType("WPNG", 310, "image/x-up-wpng");
        addFileType("FLAC", 10, "audio/flac");
        addFileType("ISMA", 15, "audio/mp4");
        addFileType("ISMV", 18, "video/mp4");
    }

    private FileType() {
    }

    static void addFileType(String str, int i, String str2) {
        FILE_TYPE_MAP.put(str, Integer.valueOf(i));
        MIME_TYPE_MAP.put(str2, Integer.valueOf(i));
    }

    public static Optional<Integer> getFileType(String str) {
        if (str == null) {
            HwLog.e("FileType", "path is null");
            return Optional.empty();
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf < 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(FILE_TYPE_MAP.get(SafeString.substring(str, lastIndexOf + 1).toUpperCase(Locale.US)));
    }
}
