package com.huawei.health.h5pro.webkit;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.bumptech.glide.Glide;
import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProAppInfo;
import com.huawei.health.h5pro.webkit.trustlist.H5ProTrustListChecker;
import com.huawei.hms.network.base.common.trans.FileBinary;
import com.huawei.hms.network.embedded.y5;
import com.huawei.openalliance.ad.constant.MetaCreativeType;
import com.huawei.openalliance.ad.constant.MimeType;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import com.huawei.watchface.videoedit.gles.Constant;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

/* loaded from: classes3.dex */
public class ResourceUtil {
    public static WebResourceResponse nativeGet(WebResourceRequest webResourceRequest, String str) {
        String uri = webResourceRequest.getUrl().toString();
        try {
            URL url = new URL(uri);
            if (TextUtils.isEmpty(str)) {
                str = getMimeType(uri);
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) URLConnectionInstrumentation.openConnection(url.openConnection());
            httpURLConnection.setConnectTimeout(y5.h);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            WebResourceResponse webResourceResponse = new WebResourceResponse(str, "UTF-8", httpURLConnection.getInputStream());
            HashMap hashMap = new HashMap();
            hashMap.put("Access-Control-Allow-Origin", "*");
            webResourceResponse.setResponseHeaders(hashMap);
            return webResourceResponse;
        } catch (IOException e) {
            LogUtil.e("H5PRO_ResourceUtil", "native get fail:", e.getMessage());
            return null;
        }
    }

    public static WebResourceResponse nativeGet(WebResourceRequest webResourceRequest) {
        return nativeGet(webResourceRequest, "");
    }

    public static WebResourceResponse loadImage(String str, WebView webView) {
        try {
            WebResourceResponse webResourceResponse = new WebResourceResponse(getMimeType(str), "UTF-8", new FileInputStream(Glide.with(webView).asFile().load(str).submit().get()));
            HashMap hashMap = new HashMap();
            hashMap.put("Access-Control-Allow-Origin", "*");
            webResourceResponse.setResponseHeaders(hashMap);
            return webResourceResponse;
        } catch (FileNotFoundException e) {
            LogUtil.e("H5PRO_ResourceUtil", "loadImage: FileNotFoundException->" + e.getMessage());
            return null;
        } catch (InterruptedException e2) {
            LogUtil.e("H5PRO_ResourceUtil", "loadImage: InterruptedException->" + e2.getMessage());
            return null;
        } catch (ExecutionException e3) {
            LogUtil.e("H5PRO_ResourceUtil", "loadImage: ExecutionException->" + e3.getMessage());
            return null;
        }
    }

    public static boolean isVideoUrl(String str) {
        return GeneralUtil.isSafePath(str) && str.startsWith(ResourceType.VIDEO.urlPrefix);
    }

    public static boolean isNeedNativeGet(WebResourceRequest webResourceRequest) {
        String uri = webResourceRequest.getUrl().toString();
        if (TextUtils.isEmpty(uri)) {
            return false;
        }
        String str = uri.split("\\?")[0];
        if (uri.contains(FeedbackWebConstants.INVALID_FILE_NAME_PRE) || !uri.startsWith("http")) {
            return false;
        }
        return (str.endsWith("png") || str.endsWith("jpg") || str.endsWith("webp") || str.endsWith(MetaCreativeType.GIF)) && webResourceRequest.getMethod().equals("GET") && !uri.contains("h5.health.huawei.com/h5pro/com.huawei.health.h5");
    }

    public static boolean isNeedImageCache(WebResourceRequest webResourceRequest) {
        String uri = webResourceRequest.getUrl().toString();
        return !TextUtils.isEmpty(uri) && uri.contains("needCache=true");
    }

    public static boolean isLocalResource(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("h5proresource://");
    }

    public static boolean isImageUrl(String str) {
        return GeneralUtil.isSafePath(str) && str.startsWith(ResourceType.IMAGE.urlPrefix);
    }

    public static boolean isFontsUrl(String str) {
        return GeneralUtil.isSafePath(str) && str.startsWith(ResourceType.FONTS.urlPrefix);
    }

    public static boolean isDisableImageProxy(WebResourceRequest webResourceRequest) {
        String uri = webResourceRequest.getUrl().toString();
        return !TextUtils.isEmpty(uri) && uri.contains("needProxy=false");
    }

    public static boolean isAudioUrl(String str) {
        return GeneralUtil.isSafePath(str) && str.startsWith(ResourceType.AUDIO.urlPrefix);
    }

    public enum ResourceType {
        IMAGE(Constant.TYPE_PHOTO, "image/png"),
        VIDEO("video", "video/mp4"),
        AUDIO(PresenterUtils.AUDIO, "audio/mpeg"),
        FONTS("fonts", FileBinary.HEAD_VALUE_CONTENT_TYPE_OCTET_STREAM);

        public final String defMimeType;
        public final String urlPrefix;

        ResourceType(String str, String str2) {
            this.urlPrefix = "h5proresource://" + str + File.separator;
            this.defMimeType = str2;
        }
    }

    public static WebResourceResponse getWebResourceResponse(String str, ResourceType resourceType, Context context) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.w("H5PRO_ResourceUtil", "getWebResourceResponse: url is empty");
            return null;
        }
        String str2 = "";
        String replace = str.replace(resourceType.urlPrefix, "");
        if (!GeneralUtil.isSafePath(replace)) {
            LogUtil.w("H5PRO_ResourceUtil", "getWebResourceResponse: invalid path");
            return null;
        }
        if (resourceType == ResourceType.FONTS) {
            replace = getFontFilePath(replace);
        }
        if (replace.contains("#")) {
            replace = replace.replace("#", "%23");
        }
        try {
            str2 = URLConnection.getFileNameMap().getContentTypeFor(replace);
        } catch (StringIndexOutOfBoundsException unused) {
            LogUtil.e("H5PRO_ResourceUtil", "StringIndexOutOfBoundsException: " + replace);
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = resourceType.defMimeType;
        }
        try {
            WebResourceResponse webResourceResponse = new WebResourceResponse(str2, "UTF-8", replace.startsWith("content://") ? context.getContentResolver().openInputStream(Uri.parse(replace)) : new FileInputStream(replace));
            HashMap hashMap = new HashMap();
            hashMap.put("Cache-Control", "no-cache");
            hashMap.put("Access-Control-Allow-Origin", "*");
            webResourceResponse.setResponseHeaders(hashMap);
            return webResourceResponse;
        } catch (FileNotFoundException e) {
            LogUtil.w("H5PRO_ResourceUtil", "getWebResourceResponse: " + e.getMessage());
            return null;
        }
    }

    public static String getMimeType(String str) {
        String str2 = str.split("\\?")[0];
        return str2.endsWith("png") ? "image/png" : str2.endsWith("jpg") ? MimeType.JPG : str2.endsWith("webp") ? "image/webp" : "";
    }

    public static WebResourceResponse getLocalResource(H5ProAppInfo h5ProAppInfo, String str, String str2, Context context) {
        ResourceType resourceType;
        if (!H5ProTrustListChecker.isTrusted(h5ProAppInfo, str)) {
            return null;
        }
        if (isFontsUrl(str2)) {
            resourceType = ResourceType.FONTS;
        } else if (isVideoUrl(str2)) {
            resourceType = ResourceType.VIDEO;
        } else if (isImageUrl(str2)) {
            resourceType = ResourceType.IMAGE;
        } else {
            if (!isAudioUrl(str2)) {
                return null;
            }
            resourceType = ResourceType.AUDIO;
        }
        return getWebResourceResponse(str2, resourceType, context);
    }

    public static String getFontFilePath(String str) {
        File[] listFiles;
        File file = new File("/system/fonts");
        if (TextUtils.isEmpty(str) || !file.exists() || (listFiles = file.listFiles()) == null || listFiles.length == 0) {
            return "";
        }
        for (File file2 : listFiles) {
            if (file2 != null && str.equalsIgnoreCase(file2.getName())) {
                return file2.getAbsolutePath();
            }
        }
        return "";
    }
}
