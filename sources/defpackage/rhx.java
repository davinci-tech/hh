package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes8.dex */
public class rhx {
    public static Drawable dOE_(Context context, HiAppInfo hiAppInfo) {
        if (hiAppInfo.getAppName() != null && hiAppInfo.getAppName().startsWith("QuickApp_")) {
            return dOD_(context, hiAppInfo.getPackageName());
        }
        return dOC_(context, hiAppInfo.getPackageName());
    }

    public static Drawable dOC_(Context context, String str) {
        try {
            return context.getPackageManager().getApplicationIcon(str);
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.h("ThirdPartAuthUtil", "NameNotFoundException e = ", e.getMessage());
            return null;
        }
    }

    public static Drawable dOD_(Context context, String str) {
        Bitmap dOB_ = dOB_(context, str);
        if (dOB_ == null) {
            LogUtil.a("ThirdPartAuthUtil", "setQuickAppIcon, Icon not found");
            return dOC_(context, Constants.FAST_APP_PKG);
        }
        return new BitmapDrawable(dOB_);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0068, code lost:
    
        if (r1 != null) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0091, code lost:
    
        if (r1 == null) goto L40;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:48:0x009b  */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap dOB_(android.content.Context r9, java.lang.String r10) {
        /*
            java.lang.String r0 = "ThirdPartAuthUtil"
            android.content.Context r9 = r9.getApplicationContext()
            android.content.ContentResolver r1 = r9.getContentResolver()
            r9 = 1
            r7 = 0
            r8 = 0
            java.lang.String r2 = "content://com.huawei.fastapp.provider.open/packageRecordList"
            android.net.Uri r2 = android.net.Uri.parse(r2)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d java.lang.IllegalArgumentException -> L7a android.os.OperationCanceledException -> L87
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L6d java.lang.IllegalArgumentException -> L7a android.os.OperationCanceledException -> L87
            if (r1 != 0) goto L2c
            java.lang.Object[] r10 = new java.lang.Object[r9]     // Catch: java.lang.Exception -> L6e java.lang.IllegalArgumentException -> L7b android.os.OperationCanceledException -> L88 java.lang.Throwable -> L97
            java.lang.String r2 = "getQuickAppIcon， null cursor"
            r10[r7] = r2     // Catch: java.lang.Exception -> L6e java.lang.IllegalArgumentException -> L7b android.os.OperationCanceledException -> L88 java.lang.Throwable -> L97
            com.huawei.hwlogsmodel.LogUtil.b(r0, r10)     // Catch: java.lang.Exception -> L6e java.lang.IllegalArgumentException -> L7b android.os.OperationCanceledException -> L88 java.lang.Throwable -> L97
            if (r1 == 0) goto L2b
            r1.close()
        L2b:
            return r8
        L2c:
            boolean r2 = r1.moveToNext()     // Catch: java.lang.Exception -> L6e java.lang.IllegalArgumentException -> L7b android.os.OperationCanceledException -> L88 java.lang.Throwable -> L97
            if (r2 == 0) goto L68
            java.lang.String r2 = "app_package_name"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Exception -> L6e java.lang.IllegalArgumentException -> L7b android.os.OperationCanceledException -> L88 java.lang.Throwable -> L97
            java.lang.String r3 = "app_icon_process"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Exception -> L6e java.lang.IllegalArgumentException -> L7b android.os.OperationCanceledException -> L88 java.lang.Throwable -> L97
            java.lang.String r2 = r1.getString(r2)     // Catch: java.lang.Exception -> L6e java.lang.IllegalArgumentException -> L7b android.os.OperationCanceledException -> L88 java.lang.Throwable -> L97
            if (r2 != 0) goto L45
            goto L68
        L45:
            boolean r2 = r2.equals(r10)     // Catch: java.lang.Exception -> L6e java.lang.IllegalArgumentException -> L7b android.os.OperationCanceledException -> L88 java.lang.Throwable -> L97
            if (r2 == 0) goto L2c
            java.lang.String r10 = r1.getString(r3)     // Catch: java.lang.Exception -> L6e java.lang.IllegalArgumentException -> L7b android.os.OperationCanceledException -> L88 java.lang.Throwable -> L97
            if (r10 != 0) goto L5b
            java.lang.Object[] r10 = new java.lang.Object[r9]     // Catch: java.lang.Exception -> L6e java.lang.IllegalArgumentException -> L7b android.os.OperationCanceledException -> L88 java.lang.Throwable -> L97
            java.lang.String r2 = "getQuickAppIcon icon Content is null"
            r10[r7] = r2     // Catch: java.lang.Exception -> L6e java.lang.IllegalArgumentException -> L7b android.os.OperationCanceledException -> L88 java.lang.Throwable -> L97
            com.huawei.hwlogsmodel.LogUtil.b(r0, r10)     // Catch: java.lang.Exception -> L6e java.lang.IllegalArgumentException -> L7b android.os.OperationCanceledException -> L88 java.lang.Throwable -> L97
            goto L68
        L5b:
            byte[] r10 = android.util.Base64.decode(r10, r7)     // Catch: java.lang.Exception -> L6e java.lang.IllegalArgumentException -> L7b android.os.OperationCanceledException -> L88 java.lang.Throwable -> L97
            if (r10 != 0) goto L62
            goto L68
        L62:
            int r2 = r10.length     // Catch: java.lang.Exception -> L6e java.lang.IllegalArgumentException -> L7b android.os.OperationCanceledException -> L88 java.lang.Throwable -> L97
            android.graphics.Bitmap r9 = android.graphics.BitmapFactory.decodeByteArray(r10, r7, r2)     // Catch: java.lang.Exception -> L6e java.lang.IllegalArgumentException -> L7b android.os.OperationCanceledException -> L88 java.lang.Throwable -> L97
            r8 = r9
        L68:
            if (r1 == 0) goto L96
            goto L93
        L6b:
            r9 = move-exception
            goto L99
        L6d:
            r1 = r8
        L6e:
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> L97
            java.lang.String r10 = "getQuickAppIcon unknown exception"
            r9[r7] = r10     // Catch: java.lang.Throwable -> L97
            com.huawei.hwlogsmodel.LogUtil.b(r0, r9)     // Catch: java.lang.Throwable -> L97
            if (r1 == 0) goto L96
            goto L93
        L7a:
            r1 = r8
        L7b:
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> L97
            java.lang.String r10 = "getQuickAppIcon decode icon exception"
            r9[r7] = r10     // Catch: java.lang.Throwable -> L97
            com.huawei.hwlogsmodel.LogUtil.b(r0, r9)     // Catch: java.lang.Throwable -> L97
            if (r1 == 0) goto L96
            goto L93
        L87:
            r1 = r8
        L88:
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> L97
            java.lang.String r10 = "getQuickAppIcon, query exception"
            r9[r7] = r10     // Catch: java.lang.Throwable -> L97
            com.huawei.hwlogsmodel.LogUtil.b(r0, r9)     // Catch: java.lang.Throwable -> L97
            if (r1 == 0) goto L96
        L93:
            r1.close()
        L96:
            return r8
        L97:
            r9 = move-exception
            r8 = r1
        L99:
            if (r8 == 0) goto L9e
            r8.close()
        L9e:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rhx.dOB_(android.content.Context, java.lang.String):android.graphics.Bitmap");
    }
}
