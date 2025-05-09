package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.wisesecurity.ucs.credential.Credential;
import com.huawei.wisesecurity.ucs.credential.CredentialClient;
import com.huawei.wisesecurity.ucs.credential.crypto.signer.CredentialSignAlg;
import com.huawei.wisesecurity.ucs.credential.crypto.signer.CredentialSigner;
import java.nio.charset.StandardCharsets;

/* loaded from: classes8.dex */
public class exz {
    private static final Object c = new Object();
    private static volatile exz d;
    private static eyn e;

    /* renamed from: a, reason: collision with root package name */
    private Context f12380a;
    private CredentialClient b;

    private exz() {
        Context a2 = eyc.a();
        this.f12380a = a2;
        if (a2 != null) {
            e();
        }
    }

    public static exz b(eyn eynVar) {
        e = eynVar;
        if (d == null) {
            synchronized (c) {
                if (d == null) {
                    d = new exz();
                }
            }
        }
        return d;
    }

    public String b() {
        eym.b("SdkAuthManager", "getAuthorization start");
        if (this.f12380a == null) {
            eym.e("SdkAuthManager", "getAuthorization: mContext is null !");
            return "";
        }
        if (e.f() == null || e.f().length() <= 0) {
            eym.e("SdkAuthManager", "getAuthorization: serCountry is null !");
            return "";
        }
        if (this.b == null) {
            e();
        }
        Credential c2 = c();
        if (c2 == null) {
            eym.e("SdkAuthManager", "getAuthorization: credential is null !");
            return "";
        }
        String accessKey = c2.getAccessKey();
        if (accessKey == null || accessKey.length() <= 0) {
            eym.e("SdkAuthManager", "getAuthorization: ak is null !");
            return "";
        }
        long currentTimeMillis = System.currentTimeMillis();
        String e2 = e(c2, this.b, e.j() + "&" + e.a() + "&" + e.b() + "&" + e.h() + "&ak=" + accessKey + "&timestamp=" + currentTimeMillis);
        if (e2 == null || e2.length() <= 0) {
            eym.e("SdkAuthManager", "getAuthorization: signatureValue is null !");
            return "";
        }
        return e.c() + " ak=" + accessKey + ", timestamp=" + currentTimeMillis + ", signature=" + e2;
    }

    private void e() {
        eym.b("SdkAuthManager", "credentialClientInit start");
        try {
            this.b = new CredentialClient.Builder().context(this.f12380a).serCountry(e.f()).networkRetryTime(e.g()).networkTimeOut(e.i()).build();
        } catch (twc unused) {
            eym.c("SdkAuthManager", "client UcsException");
        }
    }

    private Credential a() {
        eym.b("SdkAuthManager", "applyCredential start");
        try {
            Credential applyCredential = this.b.applyCredential(e.m());
            eya.a(e.d(), applyCredential.toString(), this.f12380a);
            return applyCredential;
        } catch (twc e2) {
            eym.c("SdkAuthManager", "applyCredential UcsException " + e2.getMessage());
            return null;
        }
    }

    private Credential c() {
        eym.b("SdkAuthManager", "getLocalCredential start");
        String d2 = eya.d(e.d(), "", this.f12380a);
        if (TextUtils.isEmpty(d2)) {
            eym.e("SdkAuthManager", "getLocalCredential credential not save!");
            return a();
        }
        try {
            Credential genCredentialFromString = this.b.genCredentialFromString(d2);
            if (System.currentTimeMillis() + e.e() <= genCredentialFromString.getExpireTime()) {
                return genCredentialFromString;
            }
            eym.e("SdkAuthManager", "getLocalCredential credential expired soon!");
            return a();
        } catch (twc unused) {
            return a();
        }
    }

    private String e(Credential credential, CredentialClient credentialClient, String str) {
        eym.b("SdkAuthManager", "getSignData start");
        try {
            return new CredentialSigner.Builder().withAlg(CredentialSignAlg.HMAC_SHA256).withCredential(credential).withCredentialClient(credentialClient).build().getSignHandler().from(str.getBytes(StandardCharsets.UTF_8)).signBase64();
        } catch (tvv | twc unused) {
            eym.c("SdkAuthManager", "getSignData UcsException");
            return "";
        }
    }
}
