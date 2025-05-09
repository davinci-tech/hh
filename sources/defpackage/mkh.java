package defpackage;

import java.util.List;

/* loaded from: classes9.dex */
public class mkh {
    private mcz c;
    private List<mcz> d;

    public mkh(List<mcz> list, mcz mczVar) {
        this.d = list;
        this.c = mczVar;
    }

    public List<mcz> a() {
        return this.d;
    }

    public mcz d() {
        return this.c;
    }

    public String toString() {
        return "LevelDBData{achieveDataList=" + this.d + ", achieveData=" + this.c + '}';
    }
}
