package com.huawei.watchface.utils;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import com.huawei.operation.utils.Constants;
import com.huawei.watchface.api.WebViewActivity;
import com.huawei.watchface.de;
import com.huawei.watchface.dh;
import com.huawei.watchface.flavor.FlavorConfig;
import com.huawei.watchface.mvp.base.BaseActivity;
import com.huawei.watchface.mvp.model.latona.LatonaWatchFaceProvider;
import com.huawei.watchface.mvp.ui.activity.ClipImageActivity;
import com.huawei.watchface.mvp.ui.activity.ScanImageActivity;
import com.huawei.watchface.utils.permission.PermissionUtil;
import com.huawei.watchface.videoedit.gles.Constant;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import defpackage.mcf;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes7.dex */
public class ChoosePicUtil {
    public static final String APP_PATH_SD_CARD = "/watchface/.";
    public static final String APP_PATH_SD_CARD_ROOT = "/huawei/";
    public static final int CHOOSE_PHOTO_REQUEST_CODE = 0;
    public static final int GO_CAMERA_REQUEST_CODE = 1;
    public static final int GO_VIDEO_EDIT_REQUEST_CODE = 2;
    private static boolean b = false;
    public static Uri sImageUri;
    public static File sOutputImageFile;

    /* renamed from: a, reason: collision with root package name */
    private Context f11189a;
    protected File mClippedFile = null;

    public ChoosePicUtil(Context context) {
        this.f11189a = context;
    }

    public static void a(Activity activity, int i) {
        if (activity == null) {
            HwLog.i("ChoosePicUtil", "goCamera() currentActivity is null");
            return;
        }
        HwLog.i("ChoosePicUtil", "goCamera() enter. chooseToClipResourceStyle: " + i);
        try {
            sOutputImageFile = a(activity);
        } catch (IOException unused) {
            HwLog.e("ChoosePicUtil", "goCamera() IOException occured.");
        }
        if (sOutputImageFile != null) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.addFlags(3);
            Uri uriForFile = FileProvider.getUriForFile(activity, CommonUtils.getAuthority(), sOutputImageFile);
            sImageUri = uriForFile;
            intent.putExtra("output", uriForFile);
            PackageManager packageManager = activity.getPackageManager();
            if (packageManager != null) {
                ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 1048576);
                if (resolveActivity == null || resolveActivity.activityInfo == null) {
                    HwLog.w("ChoosePicUtil", "goCamera() resolveInfo or resolveInfo.activityInfo is null");
                    return;
                }
                HwLog.i("ChoosePicUtil", "componentName packageName:" + resolveActivity.activityInfo.packageName);
                intent.setPackage(resolveActivity.activityInfo.packageName);
                mcf.cfK_(activity, intent, 1);
                HwLog.i("ChoosePicUtil", "goCamera() enter. startActivityForResult ");
            } else {
                HwLog.i("ChoosePicUtil", "packageManager == null");
            }
            b(activity);
            return;
        }
        HwLog.i("ChoosePicUtil", "goCamera() mOutputImageFile == null");
    }

    public static void a() {
        HwLog.i("ChoosePicUtil", "deleteImageFile enter");
        File file = sOutputImageFile;
        if (file == null) {
            HwLog.e("ChoosePicUtil", "sOutputImageFile is null");
        } else {
            FileHelper.a(file.getParentFile());
        }
    }

    private static void b(Activity activity) {
        if (activity == null || !(activity instanceof WebViewActivity)) {
            return;
        }
        ((WebViewActivity) activity).notifyH5ClickSelectPhoto();
    }

    public static File a(Activity activity) throws IOException {
        File file;
        String str = "JPEG_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_";
        if (PermissionUtil.isAndroidQ()) {
            file = activity.getExternalFilesDir(APP_PATH_SD_CARD + Environment.DIRECTORY_PICTURES);
        } else {
            file = null;
        }
        if (file == null) {
            file = Environment.getExternalStoragePublicDirectory("/huawei/" + activity.getPackageName() + APP_PATH_SD_CARD + Environment.DIRECTORY_PICTURES);
        }
        if (!file.exists() && !file.mkdirs()) {
            HwLog.d("ChoosePicUtil", "storageDir.mkdirs failure");
        }
        return File.createTempFile(str, ".jpg", file);
    }

    public String getFileSavedPath() {
        try {
            File file = sOutputImageFile;
            if (file == null) {
                return null;
            }
            return file.getCanonicalPath();
        } catch (IOException unused) {
            HwLog.e("ChoosePicUtil", "Get output Image path error");
            return null;
        }
    }

    public Uri getFileSavedUri() {
        return sImageUri;
    }

    public static void b(Activity activity, int i) {
        if (activity == null) {
            HwLog.e("ChoosePicUtil", "choosePhotoOrVideo() activity is null.");
            return;
        }
        HwLog.i("ChoosePicUtil", "choosePhotoOrVideo() choosePicUtil choose pic to clip, chooseToClipResourceStyle: " + i);
        HwLog.i("ChoosePicUtil", "choosePhotoOrVideo() to image select");
        Intent intent = new Intent("android.intent.action.PICK", (Uri) null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, Constants.IMAGE_TYPE);
        if (!BaseActivity.isFlyme() && i != 4) {
            intent = Intent.createChooser(intent, "");
        }
        if (CommonUtils.b(activity, intent)) {
            CommonUtils.startActivityForResult(activity, intent, 0);
        }
        b(activity);
    }

    protected void initWatchFaceBgSavedFile() {
        String latonaBackgroundSavedPath = LatonaWatchFaceProvider.getInstance(this.f11189a).getLatonaBackgroundSavedPath();
        if (!TextUtils.isEmpty(latonaBackgroundSavedPath)) {
            this.mClippedFile = new File(latonaBackgroundSavedPath);
        } else {
            HwLog.i("ChoosePicUtil", "clippedFile is not exist");
        }
    }

    public void a(Activity activity, String str, Uri uri, boolean z) {
        clipPhotoBySelf(activity, str, uri, z, false);
    }

    public void b(Activity activity, String str, Uri uri, boolean z) {
        scanPhotoBySelf(activity, str, uri, z, false);
    }

    public void clipPhotoBySelf(Activity activity, String str, Uri uri, boolean z, boolean z2) {
        if (activity == null) {
            HwLog.e("ChoosePicUtil", "clipPhotoBySelf() activity is null.");
            return;
        }
        HwLog.i("ChoosePicUtil", "clipPhotoBySelf() enter.");
        initWatchFaceBgSavedFile();
        Intent intent = new Intent(activity, (Class<?>) ClipImageActivity.class);
        intent.putExtra("sourceImagePath", str);
        File file = this.mClippedFile;
        if (file != null && file.exists()) {
            intent.putExtra(ClipImageActivity.CLIP_RESULT_PATH, this.mClippedFile.getPath());
        }
        if (uri == null) {
            if (de.b(this.f11189a, sImageUri.toString(), sImageUri)) {
                HwLog.i("ChoosePicUtil", "clipPhotoBySelf imageUriIsEmpty true");
                return;
            }
            intent.putExtra("sourceImageUri", sImageUri.toString());
        } else {
            intent.putExtra("sourceImageUri", uri.toString());
        }
        int watchFaceWidth = LatonaWatchFaceProvider.getInstance(this.f11189a).getWatchFaceWidth();
        int watchFaceHeight = LatonaWatchFaceProvider.getInstance(this.f11189a).getWatchFaceHeight();
        if (watchFaceWidth > 0 && watchFaceHeight > 0) {
            if (watchFaceWidth != watchFaceHeight && z2) {
                intent.putExtra(ClipImageActivity.CLIP_TARGET_WIDTH, Math.min(watchFaceWidth, watchFaceHeight));
                intent.putExtra(ClipImageActivity.CLIP_TARGET_HEIGHT, Math.min(watchFaceWidth, watchFaceHeight));
            } else {
                intent.putExtra(ClipImageActivity.CLIP_TARGET_WIDTH, watchFaceWidth);
                intent.putExtra(ClipImageActivity.CLIP_TARGET_HEIGHT, watchFaceHeight);
            }
        }
        intent.putExtra(ClipImageActivity.CLIP_TARGET_SHAPE, z);
        mcf.cfK_(activity, intent, 3);
    }

    public void scanPhotoBySelf(Activity activity, String str, Uri uri, boolean z, boolean z2) {
        if (activity == null) {
            HwLog.e("ChoosePicUtil", "clipPhotoBySelf() activity is null.");
            return;
        }
        HwLog.i("ChoosePicUtil", "clipPhotoBySelf() enter.");
        Intent intent = new Intent(activity, (Class<?>) ScanImageActivity.class);
        intent.putExtra("sourceImagePath", str);
        if (uri == null) {
            if (de.b(this.f11189a, sImageUri.toString(), sImageUri)) {
                HwLog.i("ChoosePicUtil", "scanPhotoBySelf imageUriIsEmpty true");
                return;
            }
            intent.putExtra("sourceImageUri", sImageUri.toString());
        } else {
            intent.putExtra("sourceImageUri", uri.toString());
        }
        mcf.cfJ_(activity, intent);
    }

    public String getPicPathByUri(Context context, Uri uri) {
        String str;
        if (context == null) {
            return "";
        }
        if (DocumentsContract.isDocumentUri(context, uri)) {
            HwLog.i("ChoosePicUtil", "getPicPathByUri() isDocumentUri.");
            if (b(uri)) {
                HwLog.i("ChoosePicUtil", "getPicPathByUri() isExternalStorageFile.");
                return a(uri);
            }
            if (c(uri)) {
                FlavorConfig.safeHwLog("ChoosePicUtil", "getPicPathByUri picUri:" + uri);
                String documentId = DocumentsContract.getDocumentId(uri);
                if (documentId != null) {
                    if (documentId.startsWith("raw:")) {
                        documentId = documentId.replaceFirst("raw:", "");
                    }
                    HwLog.i("ChoosePicUtil", "getPicPathByUri initdocumentId ==" + documentId);
                    str = documentId.replaceAll("[^\\d.]", "");
                } else {
                    str = "";
                }
                HwLog.i("ChoosePicUtil", "getPicPathByUri() documentId: " + str);
                if (!TextUtils.isEmpty(str)) {
                    long a2 = dh.a(str, 0L);
                    HwLog.i("ChoosePicUtil", "getPicPathByUri documentIdLong =" + a2);
                    return a2 == 0 ? documentId : a(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), a2), (String) null, (String[]) null);
                }
            } else {
                if (!d(uri)) {
                    return "";
                }
                HwLog.i("ChoosePicUtil", "getPicPathByUri() isMediaFile.");
                return a(context, uri);
            }
        } else {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                HwLog.i("ChoosePicUtil", "getPicPathByUri() URI_SCHEME_CONTENT.");
                return a(context, uri, (String) null, (String[]) null);
            }
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                HwLog.i("ChoosePicUtil", "getPicPathByUri() URI_SCHEME_FILE.");
                return uri.getPath();
            }
        }
        return "";
    }

    protected String a(Context context, Uri uri) {
        String[] e = e(uri);
        if (e.length < 2) {
            HwLog.i("ChoosePicUtil", "getPicPathByUri() docIds is null or not enough length");
            return "";
        }
        Uri a2 = a(e[0]);
        return a2 == null ? "" : a(context, a2, "_id=?", new String[]{e[1]});
    }

    protected String a(Uri uri) {
        String[] e = e(uri);
        if (e.length < 2) {
            HwLog.e("ChoosePicUtil", "getExternalStorageFilePath() docIds is null or not enough length");
            return "";
        }
        if ("primary".equalsIgnoreCase(e[0])) {
            return CommonUtils.b(this.f11189a) + File.separator + e[1];
        }
        HwLog.e("ChoosePicUtil", "getExternalStorageFilePath() return empty.");
        return "";
    }

    private Uri a(String str) {
        if (Constant.TYPE_PHOTO.equals(str)) {
            return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        if ("video".equals(str)) {
            return MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        }
        if (PresenterUtils.AUDIO.equals(str)) {
            return MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        }
        return null;
    }

    private String[] e(Uri uri) {
        String documentId = DocumentsContract.getDocumentId(uri);
        if (TextUtils.isEmpty(documentId) || !documentId.contains(File.pathSeparator)) {
            HwLog.i("ChoosePicUtil", "splitDocumentId() documentId is empty.");
            return new String[0];
        }
        return documentId.split(File.pathSeparator);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0030, code lost:
    
        if (r9 != null) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x005c, code lost:
    
        if (r9 != null) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.lang.String a(android.content.Context r11, android.net.Uri r12, java.lang.String r13, java.lang.String[] r14) {
        /*
            r10 = this;
            java.lang.String r0 = "ChoosePicUtil"
            java.lang.String r1 = "_data"
            java.lang.String[] r4 = new java.lang.String[]{r1}
            java.lang.String r8 = ""
            if (r12 != 0) goto Ld
            return r8
        Ld:
            r9 = 0
            android.content.ContentResolver r2 = r11.getContentResolver()     // Catch: java.lang.Throwable -> L33 java.lang.SecurityException -> L35 java.lang.IllegalArgumentException -> L3d
            r7 = 0
            r3 = r12
            r5 = r13
            r6 = r14
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L33 java.lang.SecurityException -> L35 java.lang.IllegalArgumentException -> L3d
            if (r9 == 0) goto L30
            boolean r11 = r9.moveToFirst()     // Catch: java.lang.Throwable -> L33 java.lang.SecurityException -> L35 java.lang.IllegalArgumentException -> L3d
            if (r11 == 0) goto L30
            int r11 = r9.getColumnIndexOrThrow(r1)     // Catch: java.lang.Throwable -> L33 java.lang.SecurityException -> L35 java.lang.IllegalArgumentException -> L3d
            java.lang.String r11 = r9.getString(r11)     // Catch: java.lang.Throwable -> L33 java.lang.SecurityException -> L35 java.lang.IllegalArgumentException -> L3d
            if (r9 == 0) goto L2f
            r9.close()
        L2f:
            return r11
        L30:
            if (r9 == 0) goto L61
            goto L5e
        L33:
            r11 = move-exception
            goto L62
        L35:
            java.lang.String r11 = "getDataColumnByUri() SecurityException."
            com.huawei.watchface.utils.HwLog.e(r0, r11)     // Catch: java.lang.Throwable -> L33
            if (r9 == 0) goto L61
            goto L5e
        L3d:
            java.lang.String r11 = "getDataColumnByUri() IllegalArgumentException."
            com.huawei.watchface.utils.HwLog.e(r0, r11)     // Catch: java.lang.Throwable -> L33
            java.lang.String r11 = r12.getPath()     // Catch: java.lang.Throwable -> L33
            boolean r11 = android.text.TextUtils.isEmpty(r11)     // Catch: java.lang.Throwable -> L33
            if (r11 != 0) goto L5c
            java.lang.String r11 = r12.getPath()     // Catch: java.lang.Throwable -> L33
            java.lang.String r12 = "/root"
            java.lang.String r11 = com.huawei.secure.android.common.util.SafeString.replace(r11, r12, r8)     // Catch: java.lang.Throwable -> L33
            if (r9 == 0) goto L5b
            r9.close()
        L5b:
            return r11
        L5c:
            if (r9 == 0) goto L61
        L5e:
            r9.close()
        L61:
            return r8
        L62:
            if (r9 == 0) goto L67
            r9.close()
        L67:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.watchface.utils.ChoosePicUtil.a(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    protected boolean b(Uri uri) {
        return "com.android.externalstorage.documents".equals(CommonUtils.getAuthorityByUri(uri));
    }

    protected boolean c(Uri uri) {
        return "com.android.providers.downloads.documents".equals(CommonUtils.getAuthorityByUri(uri));
    }

    protected boolean d(Uri uri) {
        return "com.android.providers.media.documents".equals(CommonUtils.getAuthorityByUri(uri));
    }
}
