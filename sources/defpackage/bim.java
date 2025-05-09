package defpackage;

import com.huawei.devicesdk.entity.CharacterOperationType;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class bim {
    private String d;
    private String e;
    private List<bil> b = new ArrayList(16);

    /* renamed from: a, reason: collision with root package name */
    private CharacterOperationType f387a = CharacterOperationType.WRITE;
    private int c = 0;
    private int j = 0;

    public CharacterOperationType b() {
        return this.f387a;
    }

    public void b(CharacterOperationType characterOperationType) {
        this.f387a = characterOperationType;
    }

    public List<bil> e() {
        return this.b;
    }

    public void a(List<bil> list) {
        this.b = list;
    }

    public String c() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public String d() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public int a() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public int f() {
        return this.j;
    }

    public void c(int i) {
        this.j = i;
    }
}
