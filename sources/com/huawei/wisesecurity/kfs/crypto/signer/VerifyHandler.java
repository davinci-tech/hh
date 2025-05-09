package com.huawei.wisesecurity.kfs.crypto.signer;

import defpackage.ttp;

/* loaded from: classes7.dex */
public interface VerifyHandler {
    VerifyHandler fromBase64Data(String str) throws ttp;

    VerifyHandler fromBase64UrlData(String str) throws ttp;

    VerifyHandler fromData(String str) throws ttp;

    VerifyHandler fromData(byte[] bArr) throws ttp;

    VerifyHandler fromHexData(String str) throws ttp;

    boolean verify(String str) throws ttp;

    boolean verify(byte[] bArr) throws ttp;

    boolean verifyBase64(String str) throws ttp;

    boolean verifyBase64Url(String str) throws ttp;

    boolean verifyHex(String str) throws ttp;
}
