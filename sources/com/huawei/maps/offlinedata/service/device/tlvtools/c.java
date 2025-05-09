package com.huawei.maps.offlinedata.service.device.tlvtools;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes5.dex */
public class c {
    private int b;
    private int d;
    private long f;

    /* renamed from: a, reason: collision with root package name */
    private List<byte[]> f6498a = new LinkedList();
    private List<byte[]> c = null;
    private List<byte[]> e = null;

    public static boolean b() {
        return true;
    }

    public c() {
        if (b()) {
            return;
        }
        this.f = Thread.currentThread().getId();
    }

    private void e() {
        if (!b() && Thread.currentThread().getId() != this.f) {
            throw new RuntimeException("TlvByteCommandUtil no support multi thread. please invoke one thread");
        }
    }

    public c a(int i) {
        e();
        if (this.e != null) {
            if (b()) {
                return this;
            }
            throw new RuntimeException("no continue invoke beginCreateList need invoke endCreateList at list finish");
        }
        if (i <= 0 || i >= 128) {
            if (b()) {
                return this;
            }
            throw new RuntimeException("beginCreateList is wrong : " + i);
        }
        this.e = new LinkedList();
        this.d = i;
        return this;
    }

    public byte[] a() {
        e();
        List<byte[]> list = this.e;
        int i = 0;
        if (list == null) {
            if (b()) {
                return new byte[]{(byte) (this.d + 128), 0};
            }
            throw new RuntimeException("need invoke endCreateList before invoke addStructToList");
        }
        if (list.isEmpty()) {
            return new byte[]{(byte) (this.d + 128), 0};
        }
        Iterator<byte[]> it = this.e.iterator();
        while (it.hasNext()) {
            i += it.next().length;
        }
        byte[] f = a.f(this.d + 128);
        byte[] d = a.d(i);
        ByteBuffer allocate = ByteBuffer.allocate(i + f.length + d.length);
        allocate.put(f).put(d);
        Iterator<byte[]> it2 = this.e.iterator();
        while (it2.hasNext()) {
            allocate.put(it2.next());
        }
        this.e.clear();
        this.e = null;
        return allocate.array();
    }

    public c a(byte[] bArr) {
        e();
        List<byte[]> list = this.e;
        if (list == null) {
            if (b()) {
                return this;
            }
            throw new RuntimeException("need invoke beginCreateList before invoke addStructToList");
        }
        if (bArr != null && bArr.length != 0) {
            list.add(bArr);
        }
        return this;
    }

    public c b(int i) {
        e();
        if (this.c != null) {
            if (b()) {
                return this;
            }
            throw new RuntimeException("no continue invoke beginCreateStruct, need invoke endCreateStruct at struct finish");
        }
        if (i <= 0 || i >= 128) {
            if (b()) {
                return this;
            }
            throw new RuntimeException("createStructType is wrong : " + i);
        }
        this.c = new LinkedList();
        this.b = i;
        return this;
    }

    public byte[] c() {
        e();
        List<byte[]> list = this.c;
        int i = 0;
        if (list == null) {
            if (b()) {
                return new byte[]{(byte) (this.b + 128), 0};
            }
            throw new RuntimeException("need invoke beginCreateStruct before invoke endCreateStruct " + this.b);
        }
        if (list.isEmpty()) {
            return new byte[]{(byte) (this.b + 128), 0};
        }
        Iterator<byte[]> it = this.c.iterator();
        while (it.hasNext()) {
            i += it.next().length;
        }
        byte[] f = a.f(this.b + 128);
        byte[] d = a.d(i);
        ByteBuffer allocate = ByteBuffer.allocate(i + f.length + d.length);
        allocate.put(f).put(d);
        Iterator<byte[]> it2 = this.c.iterator();
        while (it2.hasNext()) {
            allocate.put(it2.next());
        }
        this.c.clear();
        this.c = null;
        return allocate.array();
    }

    private byte[] e(int i, int i2) {
        byte[] f = a.f(i);
        byte[] e = a.e(i2);
        byte[] d = a.d(e.length);
        ByteBuffer allocate = ByteBuffer.allocate(f.length + d.length + e.length);
        allocate.put(f).put(d).put(e);
        return allocate.array();
    }

    public c a(int i, int i2) {
        e();
        this.f6498a.add(e(i, i2));
        return this;
    }

    private byte[] f(int i, int i2) {
        e();
        byte[] f = a.f(i);
        byte[] bArr = {(byte) i2};
        byte[] d = a.d(1);
        ByteBuffer allocate = ByteBuffer.allocate(f.length + d.length + 1);
        allocate.put(f).put(d).put(bArr);
        return allocate.array();
    }

    public c b(int i, int i2) {
        e();
        this.f6498a.add(f(i, i2));
        return this;
    }

    public c c(int i, int i2) {
        e();
        this.c.add(f(i, i2));
        return this;
    }

    private byte[] g(int i, int i2) {
        e();
        byte[] f = a.f(i);
        byte[] b = a.b(i2);
        byte[] d = a.d(b.length);
        ByteBuffer allocate = ByteBuffer.allocate(f.length + d.length + b.length);
        allocate.put(f).put(d).put(b);
        return allocate.array();
    }

    public c d(int i, int i2) {
        e();
        this.f6498a.add(g(i, i2));
        return this;
    }

    private byte[] b(int i, String str) {
        e();
        byte[] f = a.f(i);
        byte[] c = a.c(str);
        byte[] d = a.d(c.length);
        ByteBuffer allocate = ByteBuffer.allocate(f.length + d.length + c.length);
        allocate.put(f).put(d).put(c);
        return allocate.array();
    }

    public c a(int i, String str) {
        e();
        this.f6498a.add(b(i, str));
        return this;
    }

    private byte[] b(int i, long j) {
        byte[] f = a.f(i);
        byte[] a2 = a.a(j);
        byte[] d = a.d(a2.length);
        ByteBuffer allocate = ByteBuffer.allocate(f.length + d.length + a2.length);
        allocate.put(f).put(d).put(a2);
        return allocate.array();
    }

    public c a(int i, long j) {
        e();
        this.c.add(b(i, j));
        return this;
    }

    public c b(byte[] bArr) {
        e();
        if (bArr != null) {
            this.f6498a.add(bArr);
        }
        return this;
    }

    public byte[] d() {
        e();
        Iterator<byte[]> it = this.f6498a.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().length;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        Iterator<byte[]> it2 = this.f6498a.iterator();
        while (it2.hasNext()) {
            allocate.put(it2.next());
        }
        this.f6498a.clear();
        return allocate.array();
    }
}
