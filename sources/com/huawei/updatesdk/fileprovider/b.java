package com.huawei.updatesdk.fileprovider;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.updatesdk.a.a.d.d;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
class b implements a {

    /* renamed from: a, reason: collision with root package name */
    private final String f10854a;
    private final HashMap<String, File> b = new HashMap<>();

    @Override // com.huawei.updatesdk.fileprovider.a
    public void a(String str, File file) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("wisedist Name must not be empty");
        }
        try {
            this.b.put(str, file.getCanonicalFile());
        } catch (Exception unused) {
            throw new IllegalArgumentException("Failed to resolve canonical path for root");
        }
    }

    @Override // com.huawei.updatesdk.fileprovider.a
    public File a(Uri uri) {
        try {
            String encodedPath = uri.getEncodedPath();
            int indexOf = encodedPath.indexOf(47, 1);
            String decode = Uri.decode(encodedPath.substring(1, indexOf));
            String decode2 = Uri.decode(encodedPath.substring(indexOf + 1));
            File file = this.b.get(decode);
            if (file == null) {
                throw new IllegalArgumentException("wisedist: Unable to find configured root for");
            }
            if (d.d(decode2)) {
                throw new IllegalArgumentException("wisedist: Not a standard path");
            }
            try {
                File canonicalFile = new File(file, decode2).getCanonicalFile();
                if (canonicalFile.getPath().startsWith(file.getPath())) {
                    return canonicalFile;
                }
                throw new SecurityException("wisedist: Resolved path jumped beyond configured root");
            } catch (IOException unused) {
                throw new IllegalArgumentException("wisedist: Failed to resolve canonical path for");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("UpdateSDKFileProvider error: " + e.getMessage());
        }
    }

    @Override // com.huawei.updatesdk.fileprovider.a
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
                throw new IllegalArgumentException("wisedist: Failed to find configured root that contains");
            }
            String path2 = entry.getValue().getPath();
            boolean endsWith = path2.endsWith("/");
            int length = path2.length();
            if (!endsWith) {
                length++;
            }
            return new Uri.Builder().scheme("content").authority(this.f10854a).encodedPath(Uri.encode(entry.getKey()) + '/' + Uri.encode(canonicalPath.substring(length), "/")).build();
        } catch (Exception unused) {
            throw new IllegalArgumentException("Failed to resolve canonical path for wisedist");
        }
    }

    protected b(String str) {
        this.f10854a = str;
    }
}
