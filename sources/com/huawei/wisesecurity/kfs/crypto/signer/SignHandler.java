package com.huawei.wisesecurity.kfs.crypto.signer;

import defpackage.ttp;

/* loaded from: classes7.dex */
public interface SignHandler {
    SignHandler from(String str) throws ttp;

    SignHandler from(byte[] bArr) throws ttp;

    SignHandler fromBase64(String str) throws ttp;

    SignHandler fromBase64Url(String str) throws ttp;

    SignHandler fromHex(String str) throws ttp;

    byte[] sign() throws ttp;

    String signBase64() throws ttp;

    String signBase64Url() throws ttp;

    String signHex() throws ttp;
}
