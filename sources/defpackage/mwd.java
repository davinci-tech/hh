package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.TypefaceSpan;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.socialshare.model.SaveBitampCallback;
import com.huawei.health.socialshare.model.socialsharebean.ShareDataInfo;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.up.model.UserInfomation;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;

/* loaded from: classes6.dex */
public class mwd {

    /* renamed from: a, reason: collision with root package name */
    private static int f15214a;
    private static int e;
    private static String c = d(Locale.getDefault());
    private static float b = 1.0f;

    public static boolean d() {
        return true;
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str + System.currentTimeMillis() + "";
    }

    public static String cqA_(Context context, Bitmap bitmap) {
        String str = "";
        if (context == null || bitmap == null) {
            LogUtil.a("Share_ShareUtil", "saveBmpToFile() error: context/screenCut is null");
            return "";
        }
        if (context.getExternalCacheDir() != null) {
            try {
                str = context.getExternalCacheDir().getCanonicalPath() + File.separator + System.currentTimeMillis() + e + "_share_tmp.jpg";
                e++;
            } catch (IOException unused) {
                LogUtil.a("Share_ShareUtil", "getCanonicalPath fail:IOException");
            }
        }
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            FileOutputStream openOutputStream = FileUtils.openOutputStream(new File(str));
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, openOutputStream);
                openOutputStream.flush();
                if (openOutputStream != null) {
                    openOutputStream.close();
                }
            } catch (Throwable th) {
                if (openOutputStream != null) {
                    try {
                        openOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException e2) {
            LogUtil.h("Share_ShareUtil", "saveBmpToFile:IOException exception: ", e2.getMessage());
        } catch (IllegalArgumentException e3) {
            e = e3;
            LogUtil.h("Share_ShareUtil", "saveBmpToFile fail: IllegalArgumentException|IllegalStateException exception: ", e.getMessage());
        } catch (IllegalStateException e4) {
            e = e4;
            LogUtil.h("Share_ShareUtil", "saveBmpToFile fail: IllegalArgumentException|IllegalStateException exception: ", e.getMessage());
        }
        return str;
    }

    public static Bitmap cqs_(Bitmap bitmap, int i) {
        LogUtil.a("Share_ShareUtil", "ensureMaxHeight start");
        if (bitmap == null || i <= 0) {
            LogUtil.b("Share_ShareUtil", "ensureMaxHeight fail:params error, ", bitmap, Integer.valueOf(i));
            return null;
        }
        int height = bitmap.getHeight();
        if (height <= i) {
            LogUtil.a("Share_ShareUtil", "ensureMaxHeight pass: height not exceed");
            return bitmap;
        }
        LogUtil.a("Share_ShareUtil", "ensureMaxHeight height exceeds, rescale it");
        Matrix matrix = new Matrix();
        float f = i / height;
        if (!d()) {
            matrix.postScale(1.0f, f);
        } else {
            matrix.setScale(f, f);
        }
        try {
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), height, matrix, true);
        } catch (IllegalArgumentException e2) {
            LogUtil.a("Share_ShareUtil", "ensureMaxHeight:IllegalArgumentException exception: ", e2.getMessage());
            return null;
        } catch (OutOfMemoryError unused) {
            LogUtil.a("Share_ShareUtil", "ensureMaxHeight:OutOfMemoryError");
            return null;
        }
    }

    private static String f() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US);
        Date date = new Date(System.currentTimeMillis());
        Locale locale = Locale.ENGLISH;
        int i = e;
        e = i + 1;
        return String.format(locale, "sporthealth-%d-%s.jpg", Integer.valueOf(i), simpleDateFormat.format(date));
    }

    private static Uri cqy_(Context context, Bitmap bitmap) {
        Uri uri = Uri.EMPTY;
        String cqA_ = cqA_(context, bitmap);
        if (TextUtils.isEmpty(cqA_)) {
            LogUtil.h("Share_ShareUtil", "insertBitmapToGallery filePath is null");
            return uri;
        }
        try {
            String insertImage = MediaStore.Images.Media.insertImage(context.getContentResolver(), cqA_, f(), (String) null);
            return !TextUtils.isEmpty(insertImage) ? Uri.parse(insertImage) : uri;
        } catch (FileNotFoundException | IllegalArgumentException e2) {
            LogUtil.b("Share_ShareUtil", "insertBitmapToGallery:exception", ExceptionUtils.d(e2));
            return uri;
        }
    }

    public static void cqD_(Context context, Bitmap bitmap) {
        cqE_(context, bitmap, null);
    }

    public static void cqE_(Context context, Bitmap bitmap, SaveBitampCallback saveBitampCallback) {
        if (context == null || bitmap == null) {
            LogUtil.a("Share_ShareUtil", "saveBmpToFile() error: context/screenCut is null");
            return;
        }
        Uri uri = Uri.EMPTY;
        String string = context.getString(R.string._2130846047_res_0x7f02215f);
        if (!PermissionUtil.c()) {
            String cqC_ = cqC_(context, bitmap, f());
            if (!TextUtils.isEmpty(cqC_)) {
                uri = Uri.fromFile(new File(cqC_));
            }
        } else {
            uri = cqy_(context, bitmap);
        }
        if (uri != Uri.EMPTY) {
            if (saveBitampCallback != null) {
                saveBitampCallback.onSuccess(uri);
            }
            context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", uri));
            Toast.makeText(context, string, 0).show();
            return;
        }
        if (saveBitampCallback != null) {
            saveBitampCallback.onFail(1);
        }
        Toast.makeText(context, R.string._2130842375_res_0x7f021307, 0).show();
    }

    private static Uri cqp_(Context context, Bitmap bitmap) {
        Uri uri = Uri.EMPTY;
        if (!PermissionUtil.c()) {
            String cqC_ = cqC_(context, bitmap, f());
            return !TextUtils.isEmpty(cqC_) ? Uri.fromFile(new File(cqC_)) : uri;
        }
        return cqy_(context, bitmap);
    }

    private static void cqz_(Context context, Uri uri) {
        context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", uri));
        try {
            Thread.sleep(10L);
        } catch (InterruptedException unused) {
            LogUtil.h("Share_ShareUtil", "refreshImageByUri delay: InterruptedException");
        }
    }

    private static String cqC_(Context context, Bitmap bitmap, String str) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), b(context));
        String str2 = "";
        if (!file.exists() && !file.mkdir()) {
            LogUtil.b("Share_ShareUtil", "saveToHealthGallery: make gallery dir fail");
            return "";
        }
        File file2 = new File(file, str);
        if (file2.isFile() && file2.exists() && !file2.delete()) {
            LogUtil.b("Share_ShareUtil", "delete file fail:", str);
        }
        try {
            FileOutputStream openOutputStream = FileUtils.openOutputStream(file2);
            try {
                boolean compress = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, openOutputStream);
                openOutputStream.flush();
                if (compress) {
                    str2 = file2.getCanonicalPath();
                    LogUtil.a("Share_ShareUtil", "saveToLocalGallery success");
                } else {
                    LogUtil.b("Share_ShareUtil", "saveToLocalGallery fail");
                }
                if (openOutputStream != null) {
                    openOutputStream.close();
                }
            } finally {
            }
        } catch (IOException unused) {
            LogUtil.b("Share_ShareUtil", "saveToLocalGallery fail");
        }
        return str2;
    }

    public static void cqr_(Context context, Uri uri) {
        if (context != null) {
            LogUtil.a("Share_ShareUtil", "delete system gallery image count: ", Integer.valueOf(context.getContentResolver().delete(uri, null, null)));
        }
    }

    public static void d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("Share_ShareUtil", "delete file fail: path is empty");
        }
        File file = new File(str);
        if (file.isFile() && file.exists() && !file.delete()) {
            LogUtil.b("Share_ShareUtil", "delete file fail");
        }
    }

    private static void cqq_(Context context, Uri uri) {
        if (context == null || uri == null) {
            LogUtil.b("Share_ShareUtil", "deleteFileByFileUri fail: context/uri is null");
            return;
        }
        if (uri.toString().startsWith("content://")) {
            context.getContentResolver().delete(uri, null, null);
            return;
        }
        String cqt_ = cqt_(context, uri);
        if (TextUtils.isEmpty(cqt_)) {
            return;
        }
        File file = new File(cqt_);
        if (file.exists() && file.isFile() && !file.delete()) {
            LogUtil.b("Share_ShareUtil", "delete file fail");
        }
    }

    private static String cqt_(Context context, Uri uri) {
        int columnIndex;
        String str = null;
        if (uri == null) {
            LogUtil.b("Share_ShareUtil", "getFilePathByUri: uri is null");
            return null;
        }
        String scheme = uri.getScheme();
        if (scheme == null) {
            str = uri.getPath();
        } else if ("file".equals(scheme)) {
            str = uri.getPath();
        } else if ("content".equals(scheme)) {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst() && (columnIndex = query.getColumnIndex("_data")) > -1) {
                        str = query.getString(columnIndex);
                    }
                } catch (Throwable th) {
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
        } else {
            LogUtil.h("Share_ShareUtil", "getFilePathByUri fail: scheme unknown");
        }
        return CommonUtil.c(str);
    }

    public static String b(Context context) {
        if (context == null) {
            LogUtil.b("Share_ShareUtil", "getAppName: context is null");
            return "";
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            String str = packageManager.getApplicationLabel(applicationInfo) instanceof String ? (String) packageManager.getApplicationLabel(applicationInfo) : "";
            LogUtil.a("Share_ShareUtil", "getAppName() applicationName=", str);
            return str;
        } catch (PackageManager.NameNotFoundException e2) {
            LogUtil.b("Share_ShareUtil", e2.getMessage());
            return "";
        }
    }

    public static Bitmap cqu_(fdu fduVar) {
        LogUtil.a("Share_ShareUtil", "sharePicture()");
        if (fduVar == null) {
            LogUtil.b("Share_ShareUtil", "ERROR shareContent in sharePicture()");
            return null;
        }
        int u = fduVar.u();
        if (u == 1 || u == 7 || u == 10) {
            LogUtil.a("Share_ShareUtil", "SHARE_WAY_PIC");
            if (fduVar.awm_() == null || fduVar.awm_().isRecycled()) {
                LogUtil.b("Share_ShareUtil", "ERROR getSharePicContent in getShareContentBitmap()");
                return null;
            }
            return fduVar.awm_();
        }
        if (u == 4 || u == 5) {
            LogUtil.a("Share_ShareUtil", "SHARE_WAY_IMG_PATH");
            String i = fduVar.i();
            if (TextUtils.isEmpty(i)) {
                LogUtil.b("Share_ShareUtil", "ERROR getImagePath in getShareContentBitmap()");
                return null;
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            return nrf.cHC_(i, options);
        }
        LogUtil.b("Share_ShareUtil", "not a image share!");
        return null;
    }

    public static ArrayList<Uri> a(Context context, fdu fduVar, int i) {
        if (context == null || fduVar == null) {
            LogUtil.b("Share_ShareUtil", "getDividedImageUriList: context/shareContent is null");
            return null;
        }
        Iterator<Uri> it = fduVar.f().iterator();
        while (it.hasNext()) {
            cqq_(context, it.next());
        }
        ArrayList<Uri> c2 = c(context, fduVar.i(), fduVar.c(), i);
        fduVar.c(c2);
        return c2;
    }

    private static ArrayList<Uri> c(Context context, String str, int i, int i2) {
        ArrayList<Uri> arrayList = new ArrayList<>(9);
        if (i <= 0) {
            LogUtil.b("Share_ShareUtil", "divideImageToPathList: imageNum <= 0");
            return arrayList;
        }
        Bitmap cHB_ = nrf.cHB_(str);
        if (cHB_ == null) {
            LogUtil.b("Share_ShareUtil", "decodeFile from orgFilePath return null");
            return arrayList;
        }
        int height = cHB_.getHeight() / i;
        int width = cHB_.getWidth();
        while (true) {
            i--;
            if (i >= 0) {
                try {
                    Bitmap createBitmap = Bitmap.createBitmap(cHB_, 0, height * i, width, height);
                    Uri cqo_ = cqo_(context, createBitmap, i2, i);
                    arrayList.add(cqo_);
                    createBitmap.recycle();
                    if (e(i2, i)) {
                        cqz_(context, cqo_);
                    }
                } catch (IllegalArgumentException | OutOfMemoryError e2) {
                    LogUtil.b("Share_ShareUtil", "cache image fail:", e2.getMessage());
                }
            } else {
                Collections.reverse(arrayList);
                return arrayList;
            }
        }
    }

    private static Uri cqo_(Context context, Bitmap bitmap, int i, int i2) {
        if (i == 3) {
            String c2 = CommonUtil.c(CommonUtil.j(context) + "/Huawei/Health/ShareTmp/multi_share_" + i2);
            if (TextUtils.isEmpty(c2)) {
                return Uri.EMPTY;
            }
            nrf.cJt_(bitmap, c2);
            return cqv_(context, new File(c2));
        }
        return cqp_(context, bitmap);
    }

    public static Uri cqv_(Context context, File file) {
        if (d()) {
            try {
                return FileProvider.getUriForFile(context, mus.d, file);
            } catch (IllegalArgumentException e2) {
                ReleaseLogUtil.c("Share_ShareUtil", "getFileUri IllegalArgumentException:", e2.getMessage());
                return null;
            }
        }
        return Uri.fromFile(file);
    }

    private static boolean e(int i, int i2) {
        if (i != 0) {
            if (i == 1) {
                return true;
            }
            if (i != 2) {
                if (i != 3) {
                    LogUtil.b("Share_ShareUtil", "refresh type unknow");
                }
            } else if (i2 != 0) {
                return true;
            }
        }
        return false;
    }

    public static void b(HealthTextView healthTextView, CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            healthTextView.setVisibility(8);
        } else {
            healthTextView.setVisibility(0);
            healthTextView.setText(charSequence);
        }
    }

    public static void c(HealthTextView healthTextView, CharSequence charSequence) {
        b(healthTextView, charSequence);
        if (e(charSequence.toString())) {
            return;
        }
        healthTextView.setTypeface(Typeface.create(BaseApplication.getContext().getString(R.string._2130851582_res_0x7f0236fe), 3));
    }

    public static boolean b(String str) {
        return BaseApplication.getContext().getString(R.string._2130850262_res_0x7f0231d6).equals(str);
    }

    public static void d(HealthTextView healthTextView, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Share_ShareUtil", "setFront value is null");
            return;
        }
        if (str.contains("(E)")) {
            if (Build.VERSION.SDK_INT >= 28) {
                healthTextView.setText(cqF_(str, "(E)"));
                return;
            } else {
                healthTextView.setTypeface(Typeface.create(BaseApplication.getContext().getString(R.string._2130851582_res_0x7f0236fe), 3));
                healthTextView.setText(str);
                return;
            }
        }
        b(healthTextView, str);
    }

    public static SpannableStringBuilder cqF_(String str, String str2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new TypefaceSpan(Typeface.create(BaseApplication.getContext().getString(R.string._2130851582_res_0x7f0236fe), 3)), str.indexOf(str2), str.indexOf(str2) + str2.length(), 33);
        return spannableStringBuilder;
    }

    public static SpannableString cqH_(String str, String str2, int i) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new AbsoluteSizeSpan(i), str.indexOf(str2), str.indexOf(str2) + str2.length(), 33);
        return spannableString;
    }

    public static void b(List<ShareDataInfo> list) {
        if (list != null) {
            Collections.sort(list, new Comparator() { // from class: mwi
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return mwd.e((ShareDataInfo) obj, (ShareDataInfo) obj2);
                }
            });
        }
    }

    static /* synthetic */ int e(ShareDataInfo shareDataInfo, ShareDataInfo shareDataInfo2) {
        if (shareDataInfo2.getWeight() > shareDataInfo.getWeight()) {
            return 1;
        }
        if (shareDataInfo.getWeight() == shareDataInfo2.getWeight()) {
            return shareDataInfo2.getId() - shareDataInfo.getId();
        }
        return -1;
    }

    public static List<ShareDataInfo> e(List<ShareDataInfo> list) {
        ShareDataInfo next;
        if (koq.b(list)) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<ShareDataInfo> it = list.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            long effectiveTime = next.getEffectiveTime();
            long deadTime = next.getDeadTime();
            if (next.getValidityType() == 0) {
                if (currentTimeMillis >= effectiveTime && currentTimeMillis <= deadTime) {
                    arrayList.add(next);
                }
            } else if (currentTimeMillis >= effectiveTime) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public static String b() {
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo != null && !TextUtils.isEmpty(userInfo.getName())) {
            return userInfo.getName();
        }
        return BaseApplication.getContext().getString(R.string._2130847106_res_0x7f022582);
    }

    public static Bitmap cqw_(Context context) {
        if (context == null) {
            LogUtil.b("Share_ShareUtil", "getUserHeader context null.");
            return null;
        }
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        String picPath = userInfo != null ? userInfo.getPicPath() : null;
        if (TextUtils.isEmpty(picPath)) {
            return null;
        }
        return nrf.cIe_(context, picPath);
    }

    public static boolean a() {
        UserMemberInfo a2 = gpn.a();
        return (a2 == null || gpn.d(a2) || a2.getMemberFlag() != 1) ? false : true;
    }

    public static void a(HealthTextView healthTextView) {
        int c2 = nsn.c(BaseApplication.getContext(), 16.0f);
        Drawable drawable = BaseApplication.getContext().getResources().getDrawable(R.drawable._2131430190_res_0x7f0b0b2e);
        drawable.setBounds(0, 0, c2, c2);
        healthTextView.setCompoundDrawables(null, null, drawable, null);
    }

    public static String a(String str) {
        try {
            long parseLong = Long.parseLong(str);
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb, Locale.getDefault());
            int i = (int) (parseLong / 86400000);
            int i2 = (int) ((parseLong - (86400000 * i)) / 3600000);
            int ceil = (int) Math.ceil((r0 - (3600000 * i2)) / 60000.0d);
            sb.setLength(0);
            return formatter.format("%02d:%02d:%02d", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(ceil)).toString();
        } catch (NumberFormatException unused) {
            LogUtil.h("Share_ShareUtil", "generateTime is illegal");
            return str;
        }
    }

    public static void c() {
        c = d(Locale.getDefault());
    }

    public static boolean g() {
        String d = d(Locale.getDefault());
        LogUtil.a("Share_ShareUtil", "isLanguageChanged lastLocalStr = ", c, ", currentLocale = ", d);
        if (d.equals(c)) {
            return false;
        }
        c = d;
        return true;
    }

    private static String d(Locale locale) {
        return locale == null ? "" : locale.toString();
    }

    public static boolean j() {
        return LanguageUtil.m(BaseApplication.getContext()) && !Utils.o();
    }

    public static String cqB_(Context context, String str, Bitmap bitmap) {
        LogUtil.a("Share_ShareUtil", "saveImage");
        File file = new File(context.getFilesDir() + File.separator + "photos" + File.separator + MessageConstant.GROUP_MEDAL_TYPE);
        String str2 = "";
        if (!file.exists() && !file.mkdirs()) {
            LogUtil.b("Share_ShareUtil", "create file error");
            return "";
        }
        if (bitmap == null || bitmap.isRecycled()) {
            LogUtil.b("Share_ShareUtil", "bitmap isRecycled");
            return "";
        }
        try {
            str2 = file.getCanonicalPath() + File.separator + str;
        } catch (IOException unused) {
            LogUtil.b("Share_ShareUtil", "IOException");
        }
        File file2 = new File(str2);
        if (file2.exists() && !file2.delete()) {
            LogUtil.b("Share_ShareUtil", "delete old file error");
        }
        return cqL_(bitmap, str2, file2);
    }

    private static String cqL_(Bitmap bitmap, String str, File file) {
        FileOutputStream fileOutputStream;
        boolean compress;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
        } catch (FileNotFoundException unused) {
        } catch (IOException unused2) {
        }
        if (!file.createNewFile()) {
            LogUtil.b("Share_ShareUtil", "saveImage createNewFile error");
            c((FileOutputStream) null);
            return "";
        }
        fileOutputStream = new FileOutputStream(file);
        try {
            int cqn_ = cqn_(bitmap);
            LogUtil.a("Share_ShareUtil", "bitmap size:" + cqn_);
            if (cqn_ > 1048576) {
                compress = bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fileOutputStream);
            } else {
                compress = bitmap.compress(Bitmap.CompressFormat.WEBP, 50, fileOutputStream);
            }
            LogUtil.a("Share_ShareUtil", "writeFile successJpeg: ", Boolean.valueOf(compress));
            c(fileOutputStream);
        } catch (FileNotFoundException unused3) {
            fileOutputStream2 = fileOutputStream;
            LogUtil.b("Share_ShareUtil", "saveImage FileNotFoundException");
            c(fileOutputStream2);
            return str;
        } catch (IOException unused4) {
            fileOutputStream2 = fileOutputStream;
            LogUtil.b("Share_ShareUtil", "saveImage IOException");
            c(fileOutputStream2);
            return str;
        } catch (Throwable th2) {
            th = th2;
            c(fileOutputStream);
            throw th;
        }
        return str;
    }

    public static void b(fdu fduVar) {
        if (fduVar.awm_() != null || TextUtils.isEmpty(fduVar.i())) {
            return;
        }
        fduVar.awp_(BitmapFactory.decodeFile(fduVar.i(), new BitmapFactory.Options()));
    }

    private static void c(FileOutputStream fileOutputStream) {
        try {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                } catch (IOException unused) {
                    LogUtil.b("Share_ShareUtil", "saveImage finally flush IOException");
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                            return;
                        } catch (IOException unused2) {
                            LogUtil.b("Share_ShareUtil", "saveImage finally close IOException");
                            return;
                        }
                    }
                    return;
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused3) {
                    LogUtil.b("Share_ShareUtil", "saveImage finally close IOException");
                }
            }
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused4) {
                    LogUtil.b("Share_ShareUtil", "saveImage finally close IOException");
                }
            }
            throw th;
        }
    }

    public static void cqK_(View view, Context context, boolean z) {
        if (context == null || view == null) {
            return;
        }
        int i = context.getResources().getDisplayMetrics().widthPixels;
        RelativeLayout.LayoutParams layoutParams = view.getLayoutParams() instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) view.getLayoutParams() : null;
        if (layoutParams == null) {
            LogUtil.h("Share_ShareUtil", "shareView context or params is null");
            return;
        }
        boolean z2 = EnvironmentInfo.k() && nsn.ac(context);
        if (nsn.ag(context) || z2) {
            int c2 = nsn.c(context, 108.0f);
            if (nsn.ae(context) && !z2) {
                c2 = nsn.c(context, 150.0f);
            }
            int i2 = (i - (c2 * 3)) / 2;
            int i3 = i - (i2 * 2);
            layoutParams.width = i3;
            layoutParams.height = i3;
            layoutParams.leftMargin = i2;
            layoutParams.rightMargin = i2;
        }
        if (!nsn.ag(context) && z && !z2) {
            layoutParams.width = -1;
            layoutParams.height = -2;
            layoutParams.setMargins(nsn.c(context, 32.0f), nsn.c(context, 16.0f), nsn.c(context, 32.0f), nsn.c(context, 132.0f));
        }
        if (z2 && (view instanceof HealthCardView)) {
            ((HealthCardView) view).setCardBackgroundColor(context.getColor(R.color._2131297799_res_0x7f090607));
        }
        view.setLayoutParams(layoutParams);
    }

    public static Resources cqG_(Resources resources) {
        if (resources != null && resources.getConfiguration() != null) {
            Configuration configuration = resources.getConfiguration();
            if (configuration.fontScale != 1.0f) {
                b = configuration.fontScale;
            }
            configuration.fontScale = 1.0f;
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
        return resources;
    }

    public static void cqI_(Resources resources) {
        if (resources == null || resources.getConfiguration() == null) {
            return;
        }
        Configuration configuration = resources.getConfiguration();
        configuration.fontScale = b;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    public static int cqn_(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        return bitmap.getAllocationByteCount();
    }

    public static Bitmap cqx_(View view) {
        view.setVisibility(0);
        LogUtil.a("Share_ShareUtil", "getWatermarkBitmap mWaterMarkLayout");
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
            canvas.save();
            canvas.restore();
        } catch (IllegalArgumentException | IllegalStateException | OutOfMemoryError e2) {
            LogUtil.b("Share_ShareUtil", "createBitmap failed! exception: ", e2.getMessage());
        }
        view.setVisibility(4);
        return bitmap;
    }

    public static int e() {
        return f15214a;
    }

    public static void a(int i) {
        f15214a = i;
    }

    public static void e(final boolean z) {
        LogUtil.a("Share_ShareUtil", "setIsShowUserInfo:", Boolean.valueOf(z));
        ThreadPoolManager.d().execute(new Runnable() { // from class: mvz
            @Override // java.lang.Runnable
            public final void run() {
                gmz.d().c(701, z, (String) null, (IBaseResponseCallback) null);
            }
        });
    }

    public static boolean i() {
        String c2 = gmz.d().c(701);
        LogUtil.a("Share_ShareUtil", "showStatus:", c2);
        return TextUtils.isEmpty(c2) || "true".equalsIgnoreCase(c2);
    }

    public static void cqJ_(Context context, int i, int i2, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(context).b(i).d(i2).cyR_(R.string._2130841130_res_0x7f020e2a, onClickListener2).cyU_(R.string._2130841131_res_0x7f020e2b, onClickListener).a();
        a2.setCancelable(false);
        a2.show();
    }

    private static boolean e(String str) {
        return Pattern.compile("[一-龥|.*\\d.*]").matcher(str).find();
    }
}
