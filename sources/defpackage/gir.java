package defpackage;

import com.huawei.health.basesport.wearengine.SportHiWearBusinessType;
import defpackage.cba;
import defpackage.gin;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class gir {
    private List<byte[]> e = new ArrayList();

    /* JADX INFO: Access modifiers changed from: private */
    public void b(byte[] bArr) {
        this.e.add(bArr);
    }

    public byte[] a() {
        ByteBuffer allocate = ByteBuffer.allocate(c());
        Iterator<byte[]> it = this.e.iterator();
        while (it.hasNext()) {
            allocate.put(it.next());
        }
        allocate.flip();
        return allocate.array();
    }

    public int c() {
        Iterator<byte[]> it = this.e.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().length;
        }
        return i;
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        private byte[] f12820a;
        private int c;
        private int d;
        private cba.b e = new cba.b().e(SportHiWearBusinessType.FITNESS_RUN_PLAN_INFO_FILE.getTypeValue()).b(1);
        private gin.a i = new gin.a();
        private List<byte[]> b = new ArrayList();

        public d c(int i) {
            this.e.c(i);
            return this;
        }

        public d e(int i) {
            this.e.e(i);
            return this;
        }

        public d e(byte[] bArr) {
            if (bArr != null) {
                this.f12820a = (byte[]) bArr.clone();
                int length = bArr.length;
                this.d = length;
                this.i.b(length);
            }
            return this;
        }

        public d c(byte[] bArr, int i) {
            if (bArr != null) {
                this.b.add(bArr);
                this.c += bArr.length;
                this.i.a(new gin.b(bArr.length, i));
            }
            return this;
        }

        public gir c() {
            gir girVar = new gir();
            gin d = this.i.d();
            this.e.a(this.c + this.d).d(d.b());
            girVar.b(this.e.a().e());
            girVar.b(d.a());
            byte[] bArr = this.f12820a;
            if (bArr != null) {
                girVar.b(bArr);
            }
            Iterator<byte[]> it = this.b.iterator();
            while (it.hasNext()) {
                girVar.b(it.next());
            }
            return girVar;
        }
    }
}
