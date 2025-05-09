package com.huawei.wisesecurity.kfs.crypto.cipher;

import defpackage.ttp;

/* loaded from: classes7.dex */
public interface DecryptHandler {
    DecryptHandler from(byte[] bArr) throws ttp;

    DecryptHandler fromBase64(String str) throws ttp;

    DecryptHandler fromBase64Url(String str) throws ttp;

    DecryptHandler fromHex(String str) throws ttp;

    byte[] to() throws ttp;

    String toBase64() throws ttp;

    String toHex() throws ttp;

    String toRawString() throws ttp;
}
