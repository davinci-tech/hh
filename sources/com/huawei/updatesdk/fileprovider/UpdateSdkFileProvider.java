package com.huawei.updatesdk.fileprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.huawei.hms.network.base.common.trans.FileBinary;
import java.io.File;

/* loaded from: classes7.dex */
public class UpdateSdkFileProvider extends ContentProvider {
    public static final String AUTHORITIES_SUFFIX = ".updateSdk.fileProvider";
    private static final String[] COLUMNS = {"_display_name", "_size"};
    private static final String TAG = "UpdateSdkFileProvider";
    private static a mWStrategy;
    private String authority;

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates!");
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int i;
        File a2 = mWStrategy.a(uri);
        if (strArr == null) {
            strArr = COLUMNS;
        }
        String[] strArr3 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        int i2 = 0;
        for (String str3 : strArr) {
            if ("_display_name".equals(str3)) {
                strArr3[i2] = "_display_name";
                i = i2 + 1;
                objArr[i2] = a2.getName();
            } else if ("_size".equals(str3)) {
                strArr3[i2] = "_size";
                i = i2 + 1;
                objArr[i2] = Long.valueOf(a2.length());
            }
            i2 = i;
        }
        String[] copy = copy(strArr3, i2);
        Object[] copy2 = copy(objArr, i2);
        MatrixCursor matrixCursor = new MatrixCursor(copy, 1);
        matrixCursor.addRow(copy2);
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) {
        if (uri == null || uri.getAuthority() == null || !uri.getAuthority().equals(this.authority)) {
            return null;
        }
        File a2 = mWStrategy.a(uri);
        if (a2.getName().endsWith("apk")) {
            return ParcelFileDescriptor.open(a2, 268435456);
        }
        com.huawei.updatesdk.a.a.c.a.a.a.b(TAG, "can not open this file.");
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts!");
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        String mimeTypeFromExtension;
        return (uri == null || TextUtils.isEmpty(uri.toString()) || !uri.toString().endsWith("apk") || (mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension("apk")) == null) ? FileBinary.HEAD_VALUE_CONTENT_TYPE_OCTET_STREAM : mimeTypeFromExtension;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            throw new SecurityException("WiseDist Provider must not be exported");
        }
        if (!providerInfo.grantUriPermissions) {
            throw new SecurityException("WiseDist Provider must grant uri permissions");
        }
        String str = providerInfo.authority;
        this.authority = str;
        createWiseDistPathStrategy(context, str);
    }

    public static Uri getUriForFile(Context context, String str, File file) {
        createWiseDistPathStrategy(context, str);
        return mWStrategy.a(file);
    }

    private static a createWiseDistPathStrategy(Context context, String str) {
        a aVar;
        synchronized (UpdateSdkFileProvider.class) {
            if (mWStrategy == null) {
                mWStrategy = new b(str);
                if (context.getFilesDir() != null) {
                    mWStrategy.a("updatesdkapk", buildPath(context.getFilesDir(), "/"));
                }
            }
            aVar = mWStrategy;
        }
        return aVar;
    }

    private static String[] copy(String[] strArr, int i) {
        String[] strArr2 = new String[i];
        System.arraycopy(strArr, 0, strArr2, 0, i);
        return strArr2;
    }

    private static Object[] copy(Object[] objArr, int i) {
        Object[] objArr2 = new Object[i];
        System.arraycopy(objArr, 0, objArr2, 0, i);
        return objArr2;
    }

    private static File buildPath(File file, String... strArr) {
        for (String str : strArr) {
            if (str != null) {
                file = new File(file, str);
            }
        }
        return file;
    }
}
