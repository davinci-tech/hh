package com.huawei.wisesecurity.kfs.crypto.cipher;

import defpackage.ttp;

/* loaded from: classes7.dex */
public interface KfsCipher {
    DecryptHandler getDecryptHandler() throws ttp;

    EncryptHandler getEncryptHandler() throws ttp;
}
