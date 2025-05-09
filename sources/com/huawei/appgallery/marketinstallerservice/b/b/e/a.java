package com.huawei.appgallery.marketinstallerservice.b.b.e;

import defpackage.agr;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes3.dex */
public abstract class a {
    public static byte[] a(byte[] bArr) {
        DataOutputStream dataOutputStream;
        if (bArr == null) {
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream2 = null;
        dataOutputStream2 = null;
        dataOutputStream2 = null;
        try {
            try {
                try {
                    dataOutputStream = new DataOutputStream(new GZIPOutputStream(byteArrayOutputStream, bArr.length));
                } catch (IOException e) {
                    agr.a("GZIPUtil", "", e);
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
            dataOutputStream = dataOutputStream2;
        }
        try {
            int length = bArr.length;
            dataOutputStream.write(bArr, 0, length);
            dataOutputStream.flush();
            dataOutputStream.close();
            dataOutputStream2 = length;
        } catch (IOException e3) {
            e = e3;
            dataOutputStream2 = dataOutputStream;
            agr.a("GZIPUtil", "gzip error!", e);
            if (dataOutputStream2 != null) {
                dataOutputStream2.close();
                dataOutputStream2 = dataOutputStream2;
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th2) {
            th = th2;
            if (dataOutputStream != null) {
                try {
                    dataOutputStream.close();
                } catch (IOException e4) {
                    agr.a("GZIPUtil", "", e4);
                }
            }
            throw th;
        }
        return byteArrayOutputStream.toByteArray();
    }
}
