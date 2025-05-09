package defpackage;

import com.huawei.wisesecurity.kfs.crypto.key.KfsKeyPurpose;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsNotBlank;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsNotNull;

/* loaded from: classes7.dex */
public class ttf {

    /* renamed from: a, reason: collision with root package name */
    private boolean f17367a;

    @KfsNotNull
    @KfsNotBlank
    private String c;
    private int d;

    @KfsNotNull
    private KfsKeyPurpose e;

    public ttf(String str, int i, KfsKeyPurpose kfsKeyPurpose) {
        this(str, i, kfsKeyPurpose, true);
    }

    public ttf(String str, int i, KfsKeyPurpose kfsKeyPurpose, boolean z) {
        this.c = str;
        this.d = i;
        this.e = kfsKeyPurpose;
        this.f17367a = z;
    }

    public String d() {
        return this.c;
    }

    public int b() {
        return this.d;
    }

    public KfsKeyPurpose c() {
        return this.e;
    }

    public boolean a() {
        return this.f17367a;
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        private String f17368a;
        private int c;
        private boolean d = true;
        private KfsKeyPurpose e;

        public d c(String str) {
            this.f17368a = str;
            return this;
        }

        public d b(int i) {
            this.c = i;
            return this;
        }

        public d e(KfsKeyPurpose kfsKeyPurpose) {
            this.e = kfsKeyPurpose;
            return this;
        }

        public ttf b() {
            return new ttf(this.f17368a, this.c, this.e, this.d);
        }
    }
}
