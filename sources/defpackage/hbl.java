package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.videoedit.utils.Utils;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

/* loaded from: classes4.dex */
public class hbl {
    private String c;
    private a d = new a();
    private String e = null;
    private Uri b = null;

    public String b() {
        return this.e;
    }

    public Uri aYE_() {
        return this.b;
    }

    public void c(boolean z) {
        this.e = null;
        this.b = null;
        long currentTimeMillis = System.currentTimeMillis();
        this.c = this.d.e(Long.valueOf(currentTimeMillis), z);
        if (PermissionUtil.a()) {
            c(currentTimeMillis);
            return;
        }
        this.e = d() + this.c;
    }

    private void c(long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", this.c);
        contentValues.put("_display_name", this.c);
        contentValues.put("mime_type", "video/mp4");
        long j2 = j / 1000;
        contentValues.put("date_added", Long.valueOf(j2));
        contentValues.put("date_modified", Long.valueOf(j2));
        contentValues.put("date_expires", Long.valueOf((j + 3600000) / 1000));
        contentValues.put("is_pending", (Integer) 1);
        contentValues.put("relative_path", "DCIM/HuaweiHealth/");
        try {
            this.b = BaseApplication.getContext().getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
        } catch (IllegalArgumentException | IllegalStateException e) {
            ReleaseLogUtil.c("Track_FileManager", "initVideoName: ", LogAnonymous.b(e));
        }
    }

    public void a() {
        if (this.b == null) {
            ReleaseLogUtil.a("Track_FileManager", "onVideoSaveSuccess: uri is null");
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.putNull("date_expires");
        contentValues.put("is_pending", (Integer) 0);
        try {
            BaseApplication.getContext().getContentResolver().update(this.b, contentValues, null, null);
            this.e = aYD_(this.b);
        } catch (IllegalArgumentException | IllegalStateException | SecurityException e) {
            ReleaseLogUtil.c("Track_FileManager", "onVideoSaveSuccess: resolver update error ", LogAnonymous.b(e));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void c() {
        if (!TextUtils.isEmpty(this.e)) {
            File file = new File(this.e);
            try {
                try {
                    if (file.exists()) {
                        if (file.delete()) {
                            LogUtil.h("Track_FileManager", "file delete success!");
                        } else {
                            LogUtil.h("Track_FileManager", "file delete fail!");
                        }
                    }
                } catch (SecurityException e) {
                    LogUtil.h("Track_FileManager", e.getMessage());
                }
            } finally {
                this.e = null;
            }
        }
        try {
            if (this.b == null) {
                LogUtil.h("Track_FileManager", "deleteFile: uri is null");
                return;
            }
            try {
                BaseApplication.getContext().getContentResolver().delete(this.b, null, null);
            } catch (IllegalArgumentException e2) {
                ReleaseLogUtil.c("Track_FileManager", "deleteFile: error ", LogAnonymous.b((Throwable) e2));
            }
        } finally {
            this.b = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003e, code lost:
    
        if (r12 != null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x005d, code lost:
    
        return r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005a, code lost:
    
        r12.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0058, code lost:
    
        if (r12 == null) goto L25;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0063  */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String aYD_(android.net.Uri r12) {
        /*
            r11 = this;
            java.lang.String r0 = "Track_FileManager"
            java.lang.String r1 = "_data"
            java.lang.String[] r1 = new java.lang.String[]{r1}
            r8 = 1
            r9 = 0
            r10 = 0
            android.content.Context r2 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Throwable -> L43 java.lang.IllegalArgumentException -> L45
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: java.lang.Throwable -> L43 java.lang.IllegalArgumentException -> L45
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r12
            r4 = r1
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L43 java.lang.IllegalArgumentException -> L45
            if (r12 != 0) goto L2d
            java.lang.Object[] r1 = new java.lang.Object[r8]     // Catch: java.lang.IllegalArgumentException -> L41 java.lang.Throwable -> L5e
            java.lang.String r2 = "getFilePathFromContentUri: cursor is null"
            r1[r9] = r2     // Catch: java.lang.IllegalArgumentException -> L41 java.lang.Throwable -> L5e
            health.compact.a.ReleaseLogUtil.a(r0, r1)     // Catch: java.lang.IllegalArgumentException -> L41 java.lang.Throwable -> L5e
            if (r12 == 0) goto L2c
            r12.close()
        L2c:
            return r10
        L2d:
            boolean r2 = r12.moveToFirst()     // Catch: java.lang.IllegalArgumentException -> L41 java.lang.Throwable -> L5e
            if (r2 == 0) goto L3e
            r1 = r1[r9]     // Catch: java.lang.IllegalArgumentException -> L41 java.lang.Throwable -> L5e
            int r1 = r12.getColumnIndex(r1)     // Catch: java.lang.IllegalArgumentException -> L41 java.lang.Throwable -> L5e
            java.lang.String r0 = r12.getString(r1)     // Catch: java.lang.IllegalArgumentException -> L41 java.lang.Throwable -> L5e
            r10 = r0
        L3e:
            if (r12 == 0) goto L5d
            goto L5a
        L41:
            r1 = move-exception
            goto L48
        L43:
            r12 = move-exception
            goto L61
        L45:
            r12 = move-exception
            r1 = r12
            r12 = r10
        L48:
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L5e
            java.lang.String r3 = "getFilePathFromContentUri: "
            r2[r9] = r3     // Catch: java.lang.Throwable -> L5e
            java.lang.String r1 = health.compact.a.LogAnonymous.b(r1)     // Catch: java.lang.Throwable -> L5e
            r2[r8] = r1     // Catch: java.lang.Throwable -> L5e
            health.compact.a.ReleaseLogUtil.c(r0, r2)     // Catch: java.lang.Throwable -> L5e
            if (r12 == 0) goto L5d
        L5a:
            r12.close()
        L5d:
            return r10
        L5e:
            r0 = move-exception
            r10 = r12
            r12 = r0
        L61:
            if (r10 == 0) goto L66
            r10.close()
        L66:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hbl.aYD_(android.net.Uri):java.lang.String");
    }

    private int d(String str) {
        if (str == null) {
            return 3;
        }
        File file = new File(str);
        try {
            if (file.exists()) {
                return 2;
            }
            return file.mkdirs() ? 1 : 3;
        } catch (SecurityException e) {
            LogUtil.h("Track_FileManager", e.getMessage());
            return 3;
        }
    }

    public String d() {
        try {
            String str = b(BaseApplication.getContext()).getCanonicalPath() + File.separator + "DCIM/HuaweiHealth/";
            if (d(str) != 3) {
                return str;
            }
            LogUtil.h("Track_FileManager", "folder create fail");
            return null;
        } catch (IOException e) {
            LogUtil.b("Track_FileManager", LogAnonymous.b((Throwable) e));
            return null;
        }
    }

    private File b(Context context) {
        if (context == null) {
            context = BaseApplication.getContext();
        }
        File file = null;
        if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            if (PermissionUtil.a()) {
                ReleaseLogUtil.b("Track_FileManager", "PermissionUtil.isAndroidR()");
                file = context.getExternalFilesDir(null);
            } else {
                ReleaseLogUtil.b("Track_FileManager", "Version is Under R");
                file = Environment.getExternalStorageDirectory();
            }
        }
        if (file != null) {
            return file;
        }
        ReleaseLogUtil.a("Track_FileManager", "getExternalFilesDirectory:innerContext.getFilesDir");
        return context.getFilesDir();
    }

    static class a {
        private a() {
        }

        public String e(Long l, boolean z) {
            return a(l, z) + Utils.SUFFIX_MP4;
        }

        public String a(Long l, boolean z) {
            String str = "Health" + new SimpleDateFormat(HwDrmConstant.TIME_FORMAT).format(l);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(z ? "short" : "complete");
            return sb.toString();
        }
    }
}
