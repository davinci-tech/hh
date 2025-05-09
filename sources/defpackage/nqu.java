package defpackage;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import androidx.fragment.app.Fragment;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes6.dex */
public final class nqu {

    /* renamed from: a, reason: collision with root package name */
    private Drawable f15446a;
    private Fragment b;
    private Drawable c;
    private int d;
    private Object e;
    private String f;
    private String g;
    private String h;

    public nqu(Fragment fragment, String str, int i) {
        this.b = fragment;
        this.h = str;
        this.d = i;
    }

    public void e(String str) {
        this.h = str;
    }

    public Drawable cGi_() {
        return this.c;
    }

    public void cGk_(Drawable drawable) {
        this.c = drawable;
    }

    public Fragment a() {
        return this.b;
    }

    public void e(Fragment fragment) {
        this.b = fragment;
    }

    public String j() {
        return this.h;
    }

    public int c() {
        return this.d;
    }

    public String f() {
        return this.f;
    }

    public void c(String str) {
        this.f = str;
    }

    public Object e() {
        return this.e;
    }

    public nqu b(Object obj) {
        this.e = obj;
        return this;
    }

    public String h() {
        return this.g;
    }

    public void a(String str) {
        this.g = str;
    }

    public Drawable cGj_() {
        return this.f15446a;
    }

    public void cGl_(Drawable drawable) {
        this.f15446a = drawable;
    }

    public void c(nqu nquVar) {
        if (nquVar == null) {
            return;
        }
        Drawable drawable = nquVar.c;
        if (drawable != null) {
            this.c = drawable;
        }
        Drawable drawable2 = nquVar.f15446a;
        if (drawable2 != null) {
            this.f15446a = drawable2;
        }
        if (nquVar.f != null) {
            this.f = nquVar.f();
        }
        if (nquVar.g != null) {
            this.g = nquVar.h();
        }
        if (nquVar.h != null) {
            this.h = nquVar.j();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.d == ((nqu) obj).d;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public static List<nqu> a(List<nqu> list) {
        if (list.size() <= 1) {
            return list;
        }
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        ArrayList arrayList = new ArrayList(list.size());
        for (nqu nquVar : list) {
            if (nquVar != null && !hashSet.contains(nquVar.a()) && !hashSet2.contains(Integer.valueOf(nquVar.c()))) {
                hashSet.add(nquVar.a());
                hashSet2.add(Integer.valueOf(nquVar.c()));
                arrayList.add(nquVar);
            }
        }
        return arrayList;
    }

    public static void c(List<nqu> list, List<nqu> list2) {
        c(list, list2, true);
    }

    public static void c(List<nqu> list, List<nqu> list2, boolean z) {
        if (list == null || list2 == null) {
            Object[] objArr = new Object[4];
            objArr[0] = "is fromList null: ";
            objArr[1] = Boolean.valueOf(list == null);
            objArr[2] = ", is toList null: ";
            objArr[3] = Boolean.valueOf(list2 == null);
            LogUtil.d("FragmentSubTabPagerBean", objArr);
            return;
        }
        SparseIntArray sparseIntArray = new SparseIntArray();
        for (int i = 0; i < list2.size(); i++) {
            nqu nquVar = list2.get(i);
            if (nquVar != null) {
                sparseIntArray.put(nquVar.c(), i);
            }
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            nqu nquVar2 = list.get(i2);
            if (nquVar2 != null) {
                int i3 = sparseIntArray.get(nquVar2.c(), -1);
                if (i3 < 0) {
                    list2.add(nquVar2);
                } else if (z) {
                    list2.get(i3).c(nquVar2);
                } else {
                    list2.set(i3, nquVar2);
                }
            }
        }
    }

    public static void d(List<nqu> list, List<nqu> list2) {
        SparseIntArray sparseIntArray = new SparseIntArray();
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            nqu nquVar = list.get(i2);
            if (nquVar != null) {
                sparseIntArray.put(nquVar.c(), i2);
            }
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < list2.size(); i3++) {
            nqu nquVar2 = list2.get(i3);
            if (nquVar2 != null && sparseIntArray.get(nquVar2.c(), -1) >= 0) {
                arrayList.add(nquVar2);
            }
        }
        int i4 = 0;
        while (i4 < arrayList.size()) {
            nqu nquVar3 = (nqu) arrayList.get(i4);
            if (nquVar3 != null) {
                int i5 = sparseIntArray.get(nquVar3.c(), -1);
                if (!koq.b(arrayList, i5)) {
                    if (i5 != i4) {
                        Collections.swap(arrayList, i4, i5);
                    } else {
                        i4++;
                    }
                }
            }
        }
        int i6 = 0;
        while (i < list2.size() && i6 < arrayList.size()) {
            nqu nquVar4 = list2.get(i);
            if (nquVar4 == null || sparseIntArray.get(nquVar4.c(), -1) < 0) {
                i++;
            } else {
                list2.set(i, (nqu) arrayList.get(i6));
                i++;
                i6++;
            }
        }
    }
}
