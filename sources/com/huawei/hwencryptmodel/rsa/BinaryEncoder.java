package com.huawei.hwencryptmodel.rsa;

import health.compact.a.EncoderException;

/* loaded from: classes.dex */
public interface BinaryEncoder extends Encoder {
    byte[] encode(byte[] bArr) throws EncoderException;
}
