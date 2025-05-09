package defpackage;

import com.huawei.devicesdk.entity.CharacterOperationType;
import com.huawei.devicesdk.entity.SendMode;
import com.huawei.devicesdk.entity.SimpleDataHead;

/* loaded from: classes3.dex */
public class bir {
    private SendMode h;
    private String j;
    private String e = "";
    private CharacterOperationType i = CharacterOperationType.WRITE;

    /* renamed from: a, reason: collision with root package name */
    private byte[] f391a = new byte[0];
    private int f = 0;
    private int g = 1;
    private boolean b = true;
    private byte c = SimpleDataHead.INVALID.getDataHead();
    private int n = 0;
    private int d = 0;

    public SendMode g() {
        return this.h;
    }

    public void b(SendMode sendMode) {
        this.h = sendMode;
    }

    public String j() {
        return this.j;
    }

    public void c(String str) {
        this.j = str;
    }

    public String b() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public byte[] e() {
        return this.f391a;
    }

    public void e(byte[] bArr) {
        this.f391a = bArr;
    }

    public CharacterOperationType c() {
        return this.i;
    }

    public int f() {
        return this.f;
    }

    public int h() {
        return this.g;
    }

    public void d(int i) {
        this.g = i;
    }

    public boolean k() {
        return this.b;
    }

    public void b(boolean z) {
        this.b = z;
    }

    public byte d() {
        return this.c;
    }

    public void c(int i) {
        this.n = i;
    }

    public int i() {
        return this.n;
    }

    public void e(int i) {
        this.d = i;
    }

    public int a() {
        return this.d;
    }

    public static final class a {
        private CharacterOperationType c = CharacterOperationType.WRITE;
        private int e = 0;
        private int d = 1;

        /* renamed from: a, reason: collision with root package name */
        private boolean f392a = true;
        private byte b = SimpleDataHead.INVALID.getDataHead();

        public a d(CharacterOperationType characterOperationType) {
            this.c = characterOperationType;
            return this;
        }

        public a c(boolean z) {
            this.f392a = z;
            return this;
        }

        public a a(int i) {
            this.e = i;
            return this;
        }

        public a d(int i) {
            this.d = i;
            return this;
        }

        public a b(byte b) {
            this.b = b;
            return this;
        }

        public bir b(bir birVar) {
            if (birVar == null) {
                return birVar;
            }
            birVar.g = this.d;
            birVar.f = this.e;
            birVar.i = this.c;
            birVar.b = this.f392a;
            birVar.c = this.b;
            return birVar;
        }
    }
}
