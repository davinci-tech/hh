package defpackage;

import java.util.List;

/* loaded from: classes.dex */
public class msi {
    List<msa> c;
    String d;
    String e;

    public List<msa> d() {
        return this.c;
    }

    public void e(List<msa> list) {
        this.c = list;
    }

    public String b() {
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }

    public void a(String str) {
        this.d = str;
    }

    public String toString() {
        StringBuffer append = new StringBuffer("IndexFileStruct{version='").append(this.e);
        append.append("', updatedTime='").append(this.d);
        append.append("', plugins='").append(this.c.toString()).append("'}");
        return append.toString();
    }
}
