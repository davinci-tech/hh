package defpackage;

import java.util.ArrayList;

/* loaded from: classes5.dex */
public class krk {
    private String b;
    private ArrayList<kro> d = new ArrayList<>();

    public krk(String str) {
        this.b = str;
    }

    public String e() {
        return this.b;
    }

    public ArrayList<kro> b() {
        return this.d;
    }

    public void d(kro kroVar) {
        this.d.add(kroVar);
    }

    public void a() {
        ArrayList<kro> arrayList = this.d;
        if (arrayList != null) {
            arrayList.clear();
            this.d = new ArrayList<>();
        }
    }
}
