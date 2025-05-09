package com.huawei.hms.update.provider;

import android.content.Context;
import android.net.Uri;
import com.huawei.hms.utils.Checker;
import com.huawei.hwcloudjs.g.a;
import java.io.File;
import java.io.IOException;

/* loaded from: classes9.dex */
class ContentUriHelper {
    private static final String c = File.separator + a.c;

    /* renamed from: a, reason: collision with root package name */
    private Context f6069a;
    private String b;

    private static File a(File file) {
        if (file == null) {
            return null;
        }
        try {
            return file.getCanonicalFile();
        } catch (IOException unused) {
            return null;
        }
    }

    private static String b(File file) {
        if (file == null) {
            return null;
        }
        try {
            return file.getCanonicalPath();
        } catch (IOException unused) {
            return null;
        }
    }

    public File getLocalFile(String str) {
        String a2;
        if (str == null || str.contains("..") || (a2 = a()) == null) {
            return null;
        }
        return a(new File(a2, str));
    }

    public Uri getUriForFile(File file, String str) {
        String b;
        String a2;
        if (file == null || file.getPath().contains("..") || (b = b(file)) == null || (a2 = a(b)) == null) {
            return null;
        }
        return new Uri.Builder().scheme("content").authority(str).encodedPath(a2).build();
    }

    public void setContext(Context context) {
        if (this.f6069a == null) {
            Checker.checkNonNull(context, "context must not be null.");
            this.f6069a = context;
        }
    }

    private String a() {
        String str;
        Context context = (Context) Checker.assertNonNull(this.f6069a, "mContext is null, call setContext first.");
        synchronized (this) {
            if (this.b == null) {
                if (context.getExternalCacheDir() != null) {
                    this.b = b(context.getExternalCacheDir());
                } else {
                    this.b = b(context.getFilesDir());
                }
                this.b += c;
            }
            str = this.b;
        }
        return str;
    }

    private String b(String str) {
        int indexOf;
        String b;
        String a2 = a();
        if (a2 != null && (indexOf = str.indexOf(47, 1)) >= 0 && "ContentUriHelper".equals(Uri.decode(str.substring(1, indexOf))) && (b = b(new File(a2, Uri.decode(str.substring(indexOf + 1))))) != null && b.startsWith(a2)) {
            return b;
        }
        return null;
    }

    private String a(String str) {
        int length;
        String a2 = a();
        if (a2 == null || !str.startsWith(a2)) {
            return null;
        }
        if (a2.endsWith("/")) {
            length = a2.length();
        } else {
            length = a2.length() + 1;
        }
        return Uri.encode("ContentUriHelper") + '/' + str.substring(length);
    }

    File a(Uri uri) {
        String encodedPath;
        String b;
        if (uri == null || (encodedPath = uri.getEncodedPath()) == null || (b = b(encodedPath)) == null) {
            return null;
        }
        return a(new File(b));
    }

    ContentUriHelper() {
    }
}
