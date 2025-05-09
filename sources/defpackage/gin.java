package defpackage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class gin {

    /* renamed from: a, reason: collision with root package name */
    private List<b> f12818a = new ArrayList();
    private int b;
    private int c;

    protected gin(a aVar) {
        this.c = aVar.b;
        this.b = aVar.f12819a.size();
        this.f12818a.addAll(aVar.f12819a);
    }

    public int b() {
        return (this.f12818a.size() * 8) + 8;
    }

    public byte[] a() {
        ByteBuffer allocate = ByteBuffer.allocate(b());
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putInt(this.c);
        allocate.putInt(this.b);
        for (b bVar : this.f12818a) {
            allocate.putInt(bVar.b);
            allocate.putInt(bVar.c);
        }
        allocate.flip();
        return allocate.array();
    }

    public static class b {
        private int b;
        private int c;

        public b(int i, int i2) {
            this.b = i;
            this.c = i2;
        }
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private List<b> f12819a = new ArrayList();
        private int b;

        public a b(int i) {
            this.b = i;
            return this;
        }

        public a a(b bVar) {
            if (bVar != null) {
                this.f12819a.add(bVar);
            }
            return this;
        }

        public gin d() {
            return new gin(this);
        }
    }
}
