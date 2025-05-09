package defpackage;

import java.util.UUID;

/* loaded from: classes3.dex */
public class cjf {
    private UUID b;
    private UUID c;
    private byte[] d;

    private cjf(b bVar) {
        this.c = bVar.b;
        this.b = bVar.f741a;
        this.d = bVar.c;
    }

    public UUID a() {
        return this.c;
    }

    public UUID b() {
        return this.b;
    }

    public byte[] d() {
        byte[] bArr = this.d;
        return bArr != null ? (byte[]) bArr.clone() : new byte[0];
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private UUID f741a;
        private UUID b;
        private byte[] c;

        public b e(String str) {
            UUID fromString = UUID.fromString(str);
            this.b = fromString;
            UUID a2 = chb.a(fromString);
            if (a2 == null) {
                a2 = null;
            }
            this.f741a = a2;
            return this;
        }

        public b b(UUID uuid) {
            this.b = uuid;
            UUID a2 = chb.a(uuid);
            if (a2 == null) {
                a2 = null;
            }
            this.f741a = a2;
            return this;
        }

        public b d(byte[] bArr) {
            if (bArr != null) {
                this.c = (byte[]) bArr.clone();
            }
            return this;
        }

        public cjf d() {
            return new cjf(this);
        }
    }
}
