package com.huawei.openalliance.ad;

import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class xh extends Table {
    public float o() {
        int __offset = __offset(32);
        if (__offset != 0) {
            return this.bb.getFloat(__offset + this.bb_pos);
        }
        return 0.0f;
    }

    public String n() {
        int __offset = __offset(30);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public float m() {
        int __offset = __offset(28);
        if (__offset != 0) {
            return this.bb.getFloat(__offset + this.bb_pos);
        }
        return 0.0f;
    }

    public int l() {
        int __offset = __offset(26);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int k() {
        int __offset = __offset(24);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int j() {
        int __offset = __offset(22);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int i() {
        int __offset = __offset(20);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public int h() {
        int __offset = __offset(18);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public String g() {
        int __offset = __offset(16);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public int f() {
        int __offset = __offset(14);
        if (__offset != 0) {
            return this.bb.getInt(__offset + this.bb_pos);
        }
        return 0;
    }

    public String e() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public String d() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public long c() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public xh b(int i, ByteBuffer byteBuffer) {
        a(i, byteBuffer);
        return this;
    }

    public long b() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public void a(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public String a() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }
}
