package com.huawei.wisesecurity.kfs.crypto.codec;

import android.util.Base64;
import defpackage.ttm;
import defpackage.ttz;
import java.nio.charset.StandardCharsets;

/* loaded from: classes7.dex */
public interface Decoder {
    public static final Decoder BASE64 = new Decoder() { // from class: com.huawei.wisesecurity.kfs.crypto.codec.Decoder.2
        @Override // com.huawei.wisesecurity.kfs.crypto.codec.Decoder
        public byte[] decode(String str) throws ttm {
            try {
                return Base64.decode(str, 0);
            } catch (Exception e) {
                throw new ttm("Base64 decode fail : " + e.getMessage());
            }
        }
    };
    public static final Decoder BASE64URL = new Decoder() { // from class: com.huawei.wisesecurity.kfs.crypto.codec.Decoder.3
        @Override // com.huawei.wisesecurity.kfs.crypto.codec.Decoder
        public byte[] decode(String str) throws ttm {
            try {
                return Base64.decode(str, 8);
            } catch (Exception e) {
                throw new ttm("Base64 decode fail : " + e.getMessage());
            }
        }
    };
    public static final Decoder HEX = new Decoder() { // from class: com.huawei.wisesecurity.kfs.crypto.codec.Decoder.1
        @Override // com.huawei.wisesecurity.kfs.crypto.codec.Decoder
        public byte[] decode(String str) throws ttm {
            return ttz.e(str);
        }
    };
    public static final Decoder RAW_BYTES = new Decoder() { // from class: com.huawei.wisesecurity.kfs.crypto.codec.Decoder.4
        @Override // com.huawei.wisesecurity.kfs.crypto.codec.Decoder
        public byte[] decode(String str) throws ttm {
            return str.getBytes(StandardCharsets.UTF_8);
        }
    };

    byte[] decode(String str) throws ttm;
}
