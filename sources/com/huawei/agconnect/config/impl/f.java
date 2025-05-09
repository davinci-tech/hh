package com.huawei.agconnect.config.impl;

import android.text.TextUtils;
import android.util.Log;
import com.huawei.agconnect.config.IDecrypt;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKey;

/* loaded from: classes2.dex */
public class f implements IDecrypt {

    /* renamed from: a, reason: collision with root package name */
    private SecretKey f1708a;
    private final d b;
    private boolean c = false;

    @Override // com.huawei.agconnect.config.IDecrypt
    public String decrypt(String str, String str2) {
        StringBuilder sb;
        if (!this.c) {
            a();
        }
        if (this.f1708a == null || TextUtils.isEmpty(str)) {
            sb = new StringBuilder("decrypt exception: secretKey = ");
            sb.append(this.f1708a);
            sb.append("raw = ");
        } else {
            try {
                return new String(j.a(this.f1708a, Hex.decodeHexString(str)), "UTF-8");
            } catch (UnsupportedEncodingException | IllegalArgumentException | GeneralSecurityException e) {
                sb = new StringBuilder("decrypt exception:");
                str = e.getMessage();
            }
        }
        sb.append(str);
        Log.e("AGC_LocalResource", sb.toString());
        return str2;
    }

    private void a() {
        try {
            this.f1708a = j.a(this.b);
        } catch (IllegalArgumentException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            Log.e("AGC_LocalResource", "Exception when reading the 'K&I' for 'Config'. error is " + e.getMessage());
            this.f1708a = null;
        }
        this.c = true;
    }

    public f(d dVar) {
        this.b = dVar;
    }
}
