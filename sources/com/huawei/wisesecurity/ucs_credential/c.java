package com.huawei.wisesecurity.ucs_credential;

import android.content.Context;
import com.huawei.wisesecurity.ucs.credential.Credential;
import com.huawei.wisesecurity.ucs.credential.CredentialClient;
import com.huawei.wisesecurity.ucs.credential.outer.NetworkCapability;
import com.huawei.wisesecurity.ucs.credential.outer.NetworkRequest;
import com.huawei.wisesecurity.ucs.credential.outer.NetworkResponse;
import defpackage.twc;
import defpackage.twf;
import defpackage.two;
import defpackage.twr;
import defpackage.twy;
import java.io.IOException;

/* loaded from: classes7.dex */
public abstract class c {

    /* renamed from: a, reason: collision with root package name */
    public NetworkCapability f11253a;
    public Context b;
    public String c;
    public String d;
    public String e;
    public String f;
    public CredentialClient g;

    public abstract Credential a(String str) throws twc;

    public Credential a(String str, String str2, String str3, String str4) throws twc {
        try {
            a();
            this.c = str;
            this.d = str2;
            this.e = str3;
            this.f = str4;
            NetworkResponse post = this.f11253a.post(new NetworkRequest(str, twy.e(str2, str3, str4), twy.b(b())));
            int code = post.getCode();
            if ((code < 200 || code >= 300) && code != 400 && code != 403 && code != 500) {
                throw new twc(1013L, "tsms req error, return " + post.getCode());
            }
            return a(a(post));
        } catch (IOException e) {
            StringBuilder e2 = twf.e("get credential from TSMS fail : ");
            e2.append(e.getMessage());
            String sb = e2.toString();
            throw two.e("ApplyCredentialHandler", sb, new Object[0], 1006L, sb);
        }
    }

    public abstract Credential a(String str, String str2, String str3, String str4, twr twrVar) throws twc;

    public abstract String a(NetworkResponse networkResponse) throws twc;

    public abstract void a() throws twc;

    public abstract String b() throws twc;

    public static boolean b(String str) {
        return "tsms.1018".equalsIgnoreCase(str) || "tsms.1019".equalsIgnoreCase(str);
    }

    public c(CredentialClient credentialClient, Context context, NetworkCapability networkCapability) {
        this.g = credentialClient;
        this.b = context;
        this.f11253a = networkCapability;
    }
}
