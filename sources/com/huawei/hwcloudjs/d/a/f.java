package com.huawei.hwcloudjs.d.a;

import com.huawei.hwcloudjs.f.h;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/* loaded from: classes9.dex */
public class f<T> {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6208a = "SerializedObject";
    private String b;

    public T b() {
        Throwable th;
        e eVar;
        FileInputStream fileInputStream;
        Closeable closeable = (T) null;
        try {
            try {
                fileInputStream = new FileInputStream(this.b);
            } catch (Throwable th2) {
                FileInputStream fileInputStream2 = fileInputStream;
                th = th2;
                closeable = (T) fileInputStream2;
            }
            try {
                eVar = new e(fileInputStream);
                try {
                    closeable = (T) eVar.readObject();
                } catch (IOException | Exception unused) {
                    com.huawei.hwcloudjs.f.d.b(f6208a, "read file error ", true);
                    h.a(fileInputStream);
                    h.a(eVar);
                    return (T) closeable;
                }
            } catch (IOException | Exception unused2) {
                eVar = null;
            } catch (Throwable th3) {
                eVar = null;
                closeable = (T) fileInputStream;
                th = th3;
                h.a(closeable);
                h.a(eVar);
                throw th;
            }
        } catch (IOException | Exception unused3) {
            fileInputStream = null;
            eVar = null;
        } catch (Throwable th4) {
            th = th4;
            eVar = null;
        }
        h.a(fileInputStream);
        h.a(eVar);
        return (T) closeable;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean a(T t) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2;
        ObjectOutputStream objectOutputStream;
        boolean z = true;
        FileOutputStream fileOutputStream3 = null;
        try {
            try {
                fileOutputStream2 = new FileOutputStream(this.b);
                try {
                    objectOutputStream = new ObjectOutputStream(fileOutputStream2);
                } catch (IOException unused) {
                }
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream3;
                fileOutputStream3 = fileOutputStream2;
            }
            try {
                objectOutputStream.writeObject(t);
                h.a(fileOutputStream2);
                h.a(objectOutputStream);
            } catch (IOException unused2) {
                fileOutputStream3 = objectOutputStream;
                com.huawei.hwcloudjs.f.d.b(f6208a, "read file error ", true);
                h.a(fileOutputStream2);
                h.a(fileOutputStream3);
                z = false;
                return z;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream3 = fileOutputStream2;
                fileOutputStream = objectOutputStream;
                h.a(fileOutputStream3);
                h.a(fileOutputStream);
                throw th;
            }
        } catch (IOException unused3) {
            fileOutputStream2 = null;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
        return z;
    }

    public long a() {
        return new File(this.b).lastModified();
    }

    public f(String str) {
        this.b = str;
    }
}
