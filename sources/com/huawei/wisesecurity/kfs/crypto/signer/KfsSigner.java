package com.huawei.wisesecurity.kfs.crypto.signer;

import defpackage.ttp;

/* loaded from: classes7.dex */
public interface KfsSigner {
    SignHandler getSignHandler() throws ttp;

    VerifyHandler getVerifyHandler() throws ttp;
}
