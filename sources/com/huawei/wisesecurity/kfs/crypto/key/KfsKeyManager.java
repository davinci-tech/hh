package com.huawei.wisesecurity.kfs.crypto.key;

import defpackage.ttf;
import defpackage.ttn;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

/* loaded from: classes7.dex */
public interface KfsKeyManager {
    void generate(ttf ttfVar) throws ttn;

    Certificate[] getCertificateChain(String str) throws ttn;

    Key getKey(String str) throws ttn;

    PrivateKey getPrivateKey(String str) throws ttn;

    PublicKey getPublicKey(String str) throws ttn;

    boolean hasAlias(String str) throws ttn;
}
