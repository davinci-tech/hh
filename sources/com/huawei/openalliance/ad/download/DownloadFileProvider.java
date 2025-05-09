package com.huawei.openalliance.ad.download;

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
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.ho;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class DownloadFileProvider extends ContentProvider {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f6710a = {"_display_name", "_size"};
    private static final HashMap<String, m> b = new HashMap<>();
    private m c;

    private static int a() {
        return 268435456;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("DownloadFile No external updates");
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int i;
        File a2 = this.c.a(uri);
        if (strArr == null) {
            strArr = f6710a;
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
        String[] a3 = a(strArr3, i2);
        Object[] a4 = a(objArr, i2);
        MatrixCursor matrixCursor = new MatrixCursor(a3, 1);
        matrixCursor.addRow(a4);
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) {
        return ParcelFileDescriptor.open(this.c.a(uri), a());
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("DownloadFile No external inserts");
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        File a2 = this.c.a(uri);
        int lastIndexOf = a2.getName().lastIndexOf(46);
        if (lastIndexOf < 0) {
            return FileBinary.HEAD_VALUE_CONTENT_TYPE_OCTET_STREAM;
        }
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(a2.getName().substring(lastIndexOf + 1));
        return mimeTypeFromExtension != null ? mimeTypeFromExtension : FileBinary.HEAD_VALUE_CONTENT_TYPE_OCTET_STREAM;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return this.c.a(uri).delete() ? 1 : 0;
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            throw new SecurityException("Provider must not be exported");
        }
        if (!providerInfo.grantUriPermissions) {
            throw new SecurityException("Provider must grant uri permissions");
        }
        this.c = a(context, providerInfo.authority);
    }

    private static m b(Context context, String str) {
        a aVar = new a(str);
        String[] strArr = {"cache-path", "external-cache-path"};
        String[] strArr2 = {"file_cache_download", "file_download"};
        for (int i = 0; i < 2; i++) {
            String str2 = strArr[i];
            String str3 = strArr2[i];
            File cacheDir = "cache-path".equals(str2) ? context.getCacheDir() : "external-cache-path".equals(str2) ? context.getExternalCacheDir() : null;
            if (cacheDir != null) {
                aVar.a(str3, a(cacheDir, Constants.PPS_ROOT_PATH));
            }
        }
        return aVar;
    }

    private static String[] a(String[] strArr, int i) {
        String[] strArr2 = new String[i];
        System.arraycopy(strArr, 0, strArr2, 0, i);
        return strArr2;
    }

    private static Object[] a(Object[] objArr, int i) {
        Object[] objArr2 = new Object[i];
        System.arraycopy(objArr, 0, objArr2, 0, i);
        return objArr2;
    }

    private static File a(File file, String... strArr) {
        for (String str : strArr) {
            if (str != null) {
                file = new File(file, str);
            }
        }
        return file;
    }

    static class a implements m {

        /* renamed from: a, reason: collision with root package name */
        private final String f6711a;
        private final HashMap<String, File> b = new HashMap<>();

        public void a(String str, File file) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("Name must not be empty");
            }
            try {
                this.b.put(str, file.getCanonicalFile());
            } catch (IOException unused) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file);
            }
        }

        @Override // com.huawei.openalliance.ad.download.m
        public File a(Uri uri) {
            if (uri == null) {
                throw new IllegalArgumentException("Get file fail, empty uri");
            }
            String encodedPath = uri.getEncodedPath();
            if (encodedPath == null) {
                throw new IllegalArgumentException("Get file fail, failed to getEncodedPath");
            }
            int indexOf = encodedPath.indexOf(47, 1);
            File file = this.b.get(Uri.decode(encodedPath.substring(1, indexOf)));
            if (file == null) {
                throw new IllegalArgumentException("Get file fail, unable to find configured root");
            }
            try {
                File canonicalFile = new File(file, Uri.decode(encodedPath.substring(indexOf + 1))).getCanonicalFile();
                if (canonicalFile.getPath().startsWith(file.getPath())) {
                    return canonicalFile;
                }
                throw new SecurityException("Get file fail, resolved path jumped beyond configured root");
            } catch (IOException unused) {
                throw new IllegalArgumentException("Get file fail, failed to resolve canonical path");
            }
        }

        @Override // com.huawei.openalliance.ad.download.m
        public Uri a(File file) {
            try {
                String canonicalPath = file.getCanonicalPath();
                Map.Entry<String, File> entry = null;
                for (Map.Entry<String, File> entry2 : this.b.entrySet()) {
                    String path = entry2.getValue().getPath();
                    if (canonicalPath.startsWith(path) && (entry == null || path.length() > entry.getValue().getPath().length())) {
                        entry = entry2;
                    }
                }
                if (entry == null) {
                    throw new IllegalArgumentException("Failed to find configured root that contains " + canonicalPath);
                }
                String path2 = entry.getValue().getPath();
                boolean endsWith = path2.endsWith("/");
                int length = path2.length();
                if (!endsWith) {
                    length++;
                }
                return new Uri.Builder().scheme("content").authority(this.f6711a).encodedPath(Uri.encode(entry.getKey()) + '/' + Uri.encode(canonicalPath.substring(length), "/")).build();
            } catch (IOException unused) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file);
            }
        }

        public a(String str) {
            this.f6711a = str;
        }
    }

    private static m a(Context context, String str) {
        m mVar;
        HashMap<String, m> hashMap = b;
        synchronized (hashMap) {
            mVar = hashMap.get(str);
            if (mVar == null) {
                try {
                    mVar = b(context, str);
                } catch (IllegalArgumentException unused) {
                    ho.c("DownloadFileProvider", "getPathStrategy IllegalArgumentException");
                } catch (Exception e) {
                    ho.c("DownloadFileProvider", "getPathStrategy " + e.getClass().getSimpleName());
                }
                b.put(str, mVar);
            }
        }
        return mVar;
    }

    public static Uri a(Context context, String str, File file) {
        return a(context, str).a(file);
    }
}
