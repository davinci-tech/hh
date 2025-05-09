package com.huawei.phoneservice.feedbackcommon.photolibrary;

import android.content.ContentResolver;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import androidx.collection.ArraySet;
import com.huawei.openalliance.ad.constant.MetaCreativeType;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.faq.base.util.l;
import com.huawei.watchface.videoedit.gles.Constant;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* loaded from: classes5.dex */
public enum MimeType {
    JPEG(com.huawei.openalliance.ad.constant.MimeType.JPEG, a("jpg", "jpeg")),
    PNG("image/png", a("png")),
    GIF(com.huawei.openalliance.ad.constant.MimeType.GIF, a(MetaCreativeType.GIF)),
    BMP("image/x-ms-bmp", a("bmp")),
    WEBP("image/webp", a("webp")),
    MNG("image/x-jng", a("mng")),
    MPEG("video/mpeg", a("mpeg", "mpg", "mpe")),
    MP4("video/mp4", a("mp4", "m4v")),
    QUICKTIME("video/quicktime", a("mov")),
    THREEGPP("video/3gpp", a("3gp", "3gpp")),
    THREEGPP2("video/3gpp2", a("3g2", "3gpp2")),
    MKV("video/x-matroska", a("mkv")),
    WEBM("video/webm", a("webm")),
    TS("video/mp2ts", a("ts")),
    AVI("video/avi", a("avi")),
    ASF("video/x-ms-asf", a("asf")),
    WMV("video/x-ms-wmv", a("wmv", "wmx", "wvx")),
    MOVIE("video/x-sgi-movie", a("movie")),
    RV("video/vnd.rn-realvideo", a("rv"));

    private static final List<String> v = Arrays.asList("bmp", "jpg", "jpeg", "png", "webp", "mng", MetaCreativeType.GIF, "mp4", "3gp", "avi", "3g2", "mpg", "mpe", "mov", "asf", "wmv", "wmx", "wvx", "movie", "ts", "rmvb", "rv", "flv", "mkv");

    /* renamed from: a, reason: collision with root package name */
    private final String f8336a;
    private final Set<String> b;

    @Override // java.lang.Enum
    public String toString() {
        return this.f8336a;
    }

    public boolean checkType(ContentResolver contentResolver, Uri uri) {
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        if (uri == null) {
            return false;
        }
        String extensionFromMimeType = singleton.getExtensionFromMimeType(contentResolver.getType(uri));
        String str = null;
        boolean z = false;
        for (String str2 : this.b) {
            if (str2.equals(extensionFromMimeType)) {
                return true;
            }
            if (!z) {
                str = a(contentResolver, uri);
                if (!TextUtils.isEmpty(str)) {
                    str = str.toLowerCase(Locale.US);
                }
                z = true;
            }
            if (str != null && str.endsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    public static Set<MimeType> ofVideo() {
        return EnumSet.of(MPEG, MP4, QUICKTIME, THREEGPP, THREEGPP2, MKV, WEBM, TS, AVI, ASF, WMV, MOVIE, RV);
    }

    public static Set<MimeType> ofImage() {
        return EnumSet.of(JPEG, PNG, GIF, BMP, WEBP, MNG);
    }

    public static Set<MimeType> ofAll() {
        return EnumSet.allOf(MimeType.class);
    }

    public static boolean isVideoFromUrl(String str) {
        if (l.e(str)) {
            return false;
        }
        return isVideo(getSuffixFromUrl(str));
    }

    public static boolean isVideo(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("video");
    }

    public static boolean isImageFromUrl(String str) {
        if (l.e(str)) {
            return false;
        }
        return c(getSuffixFromUrl(str));
    }

    public static String getSuffixFromUrl(String str) {
        if (l.e(str)) {
            return null;
        }
        return URLConnection.guessContentTypeFromName(str);
    }

    public static String getMimeType(String str) {
        String message;
        if (TextUtils.isEmpty(a(str))) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            if (str != null) {
                try {
                    mediaMetadataRetriever.setDataSource(str);
                    String extractMetadata = mediaMetadataRetriever.extractMetadata(12);
                    if (!TextUtils.isEmpty(extractMetadata)) {
                        if (b(extractMetadata)) {
                            return extractMetadata;
                        }
                    }
                } catch (IllegalArgumentException e) {
                    message = e.getMessage();
                    i.c("MimeType", message);
                    return com.huawei.openalliance.ad.constant.MimeType.JPEG;
                } catch (IllegalStateException e2) {
                    message = e2.getMessage();
                    i.c("MimeType", message);
                    return com.huawei.openalliance.ad.constant.MimeType.JPEG;
                } catch (Exception e3) {
                    message = e3.getMessage();
                    i.c("MimeType", message);
                    return com.huawei.openalliance.ad.constant.MimeType.JPEG;
                }
            }
        } else {
            String a2 = a(str);
            if (!TextUtils.isEmpty(a2) && b(a2)) {
                return a2;
            }
        }
        return com.huawei.openalliance.ad.constant.MimeType.JPEG;
    }

    public static boolean c(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith(Constant.TYPE_PHOTO);
    }

    private static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return v.contains(str.split("/")[r3.length - 1]);
        } catch (Exception unused) {
            return false;
        }
    }

    private static Set<String> a(String... strArr) {
        return new ArraySet(Arrays.asList(strArr));
    }

    public static String a(String str) {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str));
    }

    public static String a(ContentResolver contentResolver, Uri uri) {
        Throwable th;
        Cursor cursor = null;
        if (uri == null) {
            return null;
        }
        if ("content".equals(uri.getScheme())) {
            try {
                Cursor query = contentResolver.query(uri, new String[]{"_data"}, null, null, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            if (query.getColumnIndex("_data") > -1) {
                                String string = query.getString(query.getColumnIndex("_data"));
                                query.close();
                                return string;
                            }
                            query.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = query;
                        if (cursor == null) {
                            throw th;
                        }
                        cursor.close();
                        throw th;
                    }
                }
                if (query != null) {
                    query.close();
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
            }
        }
        return uri.getPath();
    }

    MimeType(String str, Set set) {
        this.f8336a = str;
        this.b = set;
    }
}
