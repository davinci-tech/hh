package com.huawei.wisecloud.drmclient.license;

import com.huawei.wisecloud.drmclient.exception.HwDrmException;
import com.huawei.wisecloud.drmclient.license.entry.PayloadLicenseEntry;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes7.dex */
public interface HwDrmLicense {
    byte[] calHmac(InputStream inputStream, String str, String str2) throws HwDrmException;

    void decrypt(InputStream inputStream, OutputStream outputStream, String str, String str2, byte[] bArr) throws HwDrmException;

    byte[] decryptForLiveWallPager(byte[] bArr, String str, String str2, byte[] bArr2) throws HwDrmException;

    byte[] getEncrkey();

    PayloadLicenseEntry getLicense();

    byte[] getSecretInfo() throws HwDrmException;
}
