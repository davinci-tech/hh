package defpackage;

import java.util.List;

/* loaded from: classes3.dex */
class cdi {
    String c;
    List<cvk> d;
    String e;

    cdi() {
    }

    public List<cvk> a() {
        return this.d;
    }

    public void a(List<cvk> list) {
        this.d = list;
    }

    public String e() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    public void c(String str) {
        this.e = str;
    }

    public String toString() {
        StringBuffer append = new StringBuffer("IndexFileStruct{version='").append(this.c);
        append.append("', updatedTime='").append(this.e);
        append.append("', plugins='").append(this.d.toString()).append("'}");
        return append.toString();
    }
}
