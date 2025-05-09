package defpackage;

import java.util.ArrayList;

/* loaded from: classes3.dex */
public class bfd {
    private ArrayList<bfl> e = new ArrayList<>(16);
    private ArrayList<bfj> c = new ArrayList<>(16);

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<bfk> f347a = new ArrayList<>(16);

    public void c(ArrayList<bfl> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            this.e.add(i, arrayList.get(i));
        }
    }

    public void a(ArrayList<bfj> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            this.c.add(i, arrayList.get(i));
        }
    }

    public void e(ArrayList<bfk> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            this.f347a.add(i, arrayList.get(i));
        }
    }

    public ArrayList<bfl> a() {
        return this.e;
    }

    public ArrayList<bfj> b() {
        return this.c;
    }

    public ArrayList<bfk> e() {
        return this.f347a;
    }
}
