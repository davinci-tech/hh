package defpackage;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes3.dex */
public class bms {

    /* renamed from: a, reason: collision with root package name */
    private int f438a;
    private int c;
    private long j;
    private List<byte[]> b = new LinkedList();
    private List<byte[]> d = null;
    private List<byte[]> e = null;

    public bms() {
        if (bky.i()) {
            return;
        }
        this.j = Thread.currentThread().getId();
    }

    private void a() {
        if (!bky.i() && Thread.currentThread().getId() != this.j) {
            throw new RuntimeException("TlvByteCommandUtil no support multi thread. please invoke one thread");
        }
    }

    public bms b(int i) {
        a();
        if (this.e != null) {
            if (bky.i()) {
                return this;
            }
            throw new RuntimeException("no continue invoke beginCreateList need invoke endCreateList at list finish");
        }
        if (i <= 0 || i >= 128) {
            if (bky.i()) {
                return this;
            }
            throw new RuntimeException("beginCreateList is wrong : " + i);
        }
        this.e = new LinkedList();
        this.f438a = i;
        return this;
    }

    public byte[] b() {
        a();
        List<byte[]> list = this.e;
        int i = 0;
        if (list == null) {
            if (bky.i()) {
                return new byte[]{(byte) (this.f438a + 128), 0};
            }
            throw new RuntimeException("need invoke endCreateList before invoke addStructToList");
        }
        if (list.isEmpty()) {
            return new byte[]{(byte) (this.f438a + 128), 0};
        }
        Iterator<byte[]> it = this.e.iterator();
        while (it.hasNext()) {
            i += it.next().length;
        }
        byte[] g = blq.g(this.f438a + 128);
        byte[] d = blq.d(i);
        ByteBuffer allocate = ByteBuffer.allocate(i + g.length + d.length);
        allocate.put(g).put(d);
        Iterator<byte[]> it2 = this.e.iterator();
        while (it2.hasNext()) {
            allocate.put(it2.next());
        }
        this.e.clear();
        this.e = null;
        return allocate.array();
    }

    public bms c(byte[] bArr) {
        a();
        List<byte[]> list = this.e;
        if (list == null) {
            if (bky.i()) {
                return this;
            }
            throw new RuntimeException("need invoke beginCreateList before invoke addStructToList");
        }
        if (bArr != null && bArr.length != 0) {
            list.add(bArr);
        }
        return this;
    }

    public bms c(int i) {
        a();
        if (this.d != null) {
            if (bky.i()) {
                return this;
            }
            throw new RuntimeException("no continue invoke beginCreateStruct, need invoke endCreateStruct at struct finish");
        }
        if (i <= 0 || i >= 128) {
            if (bky.i()) {
                return this;
            }
            throw new RuntimeException("createStructType is wrong : " + i);
        }
        this.d = new LinkedList();
        this.c = i;
        return this;
    }

    public byte[] c() {
        a();
        List<byte[]> list = this.d;
        int i = 0;
        if (list == null) {
            if (bky.i()) {
                return new byte[]{(byte) (this.c + 128), 0};
            }
            throw new RuntimeException("need invoke beginCreateStruct before invoke endCreateStruct " + this.c);
        }
        if (list.isEmpty()) {
            return new byte[]{(byte) (this.c + 128), 0};
        }
        Iterator<byte[]> it = this.d.iterator();
        while (it.hasNext()) {
            i += it.next().length;
        }
        byte[] g = blq.g(this.c + 128);
        byte[] d = blq.d(i);
        ByteBuffer allocate = ByteBuffer.allocate(i + g.length + d.length);
        allocate.put(g).put(d);
        Iterator<byte[]> it2 = this.d.iterator();
        while (it2.hasNext()) {
            allocate.put(it2.next());
        }
        this.d.clear();
        this.d = null;
        return allocate.array();
    }

    public bms e(int i, int i2) {
        a();
        this.b.add(0, new byte[]{(byte) i, (byte) i2});
        return this;
    }

    private byte[] k(int i, int i2) {
        byte[] g = blq.g(i);
        byte[] i3 = blq.i(i2);
        byte[] d = blq.d(i3.length);
        ByteBuffer allocate = ByteBuffer.allocate(g.length + d.length + i3.length);
        allocate.put(g).put(d).put(i3);
        return allocate.array();
    }

    public bms i(int i, int i2) {
        a();
        this.b.add(k(i, i2));
        return this;
    }

    public bms c(int i, int i2) {
        a();
        this.d.add(k(i, i2));
        return this;
    }

    public bms a(int i, int i2) {
        a();
        this.b.add(g(i, i2));
        return this;
    }

    private byte[] o(int i, int i2) {
        a();
        byte[] g = blq.g(i);
        byte[] bArr = {(byte) i2};
        byte[] d = blq.d(1);
        ByteBuffer allocate = ByteBuffer.allocate(g.length + d.length + 1);
        allocate.put(g).put(d).put(bArr);
        return allocate.array();
    }

    public bms j(int i, int i2) {
        a();
        this.b.add(o(i, i2));
        return this;
    }

    public bms d(int i, int i2) {
        a();
        this.d.add(o(i, i2));
        return this;
    }

    private byte[] f(int i, int i2) {
        a();
        byte[] g = blq.g(i);
        byte[] e = blq.e(i2);
        byte[] d = blq.d(e.length);
        ByteBuffer allocate = ByteBuffer.allocate(g.length + d.length + e.length);
        allocate.put(g).put(d).put(e);
        return allocate.array();
    }

    public bms h(int i, int i2) {
        a();
        this.b.add(f(i, i2));
        return this;
    }

    public bms b(int i, int i2) {
        a();
        this.d.add(f(i, i2));
        return this;
    }

    private byte[] b(int i, String str) {
        a();
        byte[] g = blq.g(i);
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        ByteBuffer allocate = ByteBuffer.allocate(g.length + d.length + c.length);
        allocate.put(g).put(d).put(c);
        return allocate.array();
    }

    public bms d(int i, String str) {
        a();
        this.b.add(b(i, str));
        return this;
    }

    public bms e(int i, String str) {
        a();
        this.d.add(b(i, str));
        return this;
    }

    private byte[] b(int i, byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return d(i);
        }
        byte[] g = blq.g(i);
        byte[] d = blq.d(bArr.length);
        ByteBuffer allocate = ByteBuffer.allocate(g.length + d.length + bArr.length);
        allocate.put(g).put(d).put(bArr);
        return allocate.array();
    }

    public bms d(int i, byte[] bArr) {
        a();
        this.b.add(b(i, bArr));
        return this;
    }

    public bms e(int i, byte[] bArr) {
        a();
        this.d.add(b(i, bArr));
        return this;
    }

    public bms e(int i) {
        a();
        this.b.add(d(i));
        return this;
    }

    private byte[] g(int i, int i2) {
        byte[] g = blq.g(i);
        byte[] g2 = blq.g(i2);
        byte[] d = blq.d(g2.length);
        ByteBuffer allocate = ByteBuffer.allocate(g.length + d.length + g2.length);
        allocate.put(g).put(d).put(g2);
        return allocate.array();
    }

    private byte[] c(int i, long j) {
        byte[] g = blq.g(i);
        byte[] c = blq.c(j);
        byte[] d = blq.d(c.length);
        ByteBuffer allocate = ByteBuffer.allocate(g.length + d.length + c.length);
        allocate.put(g).put(d).put(c);
        return allocate.array();
    }

    public bms d(int i, long j) {
        a();
        this.b.add(c(i, j));
        return this;
    }

    public bms b(int i, long j) {
        a();
        this.d.add(c(i, j));
        return this;
    }

    public bms b(byte[] bArr) {
        a();
        if (bArr != null) {
            this.b.add(bArr);
        }
        return this;
    }

    public bms d(byte[] bArr) {
        a();
        if (bArr != null) {
            this.d.add(bArr);
        }
        return this;
    }

    public byte[] d() {
        a();
        Iterator<byte[]> it = this.b.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().length;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        Iterator<byte[]> it2 = this.b.iterator();
        while (it2.hasNext()) {
            allocate.put(it2.next());
        }
        this.b.clear();
        return allocate.array();
    }

    private byte[] d(int i) {
        return new byte[]{(byte) i, 0};
    }
}
