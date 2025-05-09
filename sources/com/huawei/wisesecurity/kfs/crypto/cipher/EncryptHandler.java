package com.huawei.wisesecurity.kfs.crypto.cipher;

import defpackage.ttp;

/* loaded from: classes7.dex */
public interface EncryptHandler {
    EncryptHandler from(String str) throws ttp;

    EncryptHandler from(byte[] bArr) throws ttp;

    EncryptHandler fromBase64(String str) throws ttp;

    EncryptHandler fromBase64Url(String str) throws ttp;

    EncryptHandler fromHex(String str) throws ttp;

    byte[] to() throws ttp;

    String toBase64() throws ttp;

    String toBase64Url() throws ttp;

    String toHex() throws ttp;
}
