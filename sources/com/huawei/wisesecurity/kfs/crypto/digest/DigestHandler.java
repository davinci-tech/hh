package com.huawei.wisesecurity.kfs.crypto.digest;

import defpackage.ttp;

/* loaded from: classes9.dex */
public interface DigestHandler {
    byte[] digest() throws ttp;

    String digestBase64() throws ttp;

    String digestBase64Url() throws ttp;

    String digestHex() throws ttp;

    DigestHandler from(String str) throws ttp;

    DigestHandler from(byte[] bArr) throws ttp;

    DigestHandler fromBase64(String str) throws ttp;

    DigestHandler fromBase64Url(String str) throws ttp;

    DigestHandler fromHex(String str) throws ttp;
}
