package com.huawei.android.hicloud.sync.service;

import android.os.RemoteException;
import android.os.SystemClock;
import android.os.TransactionTooLargeException;
import defpackage.abd;

/* loaded from: classes8.dex */
public abstract class w implements IServiceProtocol {
    private void b(byte[] bArr, long j) {
        try {
            executeBatches(bArr);
        } catch (TransactionTooLargeException e) {
            abd.b("ServiceProtocol", "sendTooLargeExecuteBatches need retry: " + e.getMessage());
            try {
                SystemClock.sleep(j * 2);
                executeBatches(bArr);
            } catch (Exception e2) {
                abd.b("ServiceProtocol", "sendTooLargeExecuteBatches retry error " + e2.getMessage());
            }
        } catch (RemoteException e3) {
            abd.b("ServiceProtocol", "sendTooLargeExecuteBatches error " + e3.getMessage());
        }
    }

    public void a(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        int length = bArr.length;
        int[] iArr = {0, length / 2, length};
        byte[] bArr2 = null;
        int i = 0;
        while (i <= 1) {
            int i2 = i + 1;
            try {
                int i3 = iArr[i2] - iArr[i];
                bArr2 = new byte[i3];
                abd.c("ServiceProtocol", "indexArr[i] = " + iArr[i] + " , indexArr[i + 1]= " + iArr[i2]);
                System.arraycopy(bArr, iArr[i], bArr2, 0, i3);
                executeBatches(bArr2);
            } catch (TransactionTooLargeException unused) {
                a(bArr2);
            } catch (RemoteException e) {
                abd.b("ServiceProtocol", e.toString());
            }
            i = i2;
        }
    }

    public void a(byte[] bArr, long j) {
        if (bArr == null) {
            return;
        }
        int length = bArr.length;
        if (length <= 102400) {
            byte[] bArr2 = new byte[102400];
            System.arraycopy(bArr, 0, bArr2, 0, 102400);
            SystemClock.sleep(j);
            b(bArr2, j);
            return;
        }
        int i = 0;
        int i2 = 0;
        while (length > 0) {
            abd.c("ServiceProtocol", "sdkResolveTooLarge times[" + i + "] remain: " + length);
            byte[] bArr3 = length >= 102400 ? new byte[102400] : new byte[length];
            System.arraycopy(bArr, i2, bArr3, 0, bArr3.length);
            length -= bArr3.length;
            i2 += bArr3.length;
            i++;
            SystemClock.sleep(j);
            b(bArr3, j);
        }
    }
}
