package com.huawei.openalliance.ad;

import com.google.flatbuffers.BaseVector;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class wb extends Table {
    public String g() {
        int __offset = __offset(20);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public wf f() {
        return a(new wf());
    }

    public xg e() {
        return a(new xg());
    }

    public wl d() {
        return a(new wl());
    }

    public xf c() {
        return a(new xf());
    }

    public String b() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public wb b(int i, ByteBuffer byteBuffer) {
        a(i, byteBuffer);
        return this;
    }

    public void a(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public xg a(xg xgVar) {
        int __offset = __offset(14);
        if (__offset != 0) {
            return xgVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public xf a(xf xfVar) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return xfVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public static final class a extends BaseVector {
        public wb a(wb wbVar, int i) {
            return wbVar.b(wb.__indirect(__element(i), this.bb), this.bb);
        }

        public wb a(int i) {
            return a(new wb(), i);
        }

        public a a(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }
    }

    public wl a(wl wlVar) {
        int __offset = __offset(12);
        if (__offset != 0) {
            return wlVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public wf a(wf wfVar) {
        int __offset = __offset(16);
        if (__offset != 0) {
            return wfVar.b(__indirect(__offset + this.bb_pos), this.bb);
        }
        return null;
    }

    public long a() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }
}
