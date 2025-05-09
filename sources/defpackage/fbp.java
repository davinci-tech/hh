package defpackage;

import com.huawei.health.section.section.MeasureCardItemHolder;
import com.huawei.hihealth.HiHealthData;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class fbp {
    private HiHealthData b;
    private HiHealthData c;
    private MeasureCardItemHolder.MeasureType f;
    private String j;
    private final List<fbl> h = new ArrayList();
    private boolean d = true;
    private boolean e = true;

    /* renamed from: a, reason: collision with root package name */
    private String[] f12427a = null;

    public fbp(MeasureCardItemHolder.MeasureType measureType) {
        this.f = measureType;
    }

    public void a() {
        synchronized (this) {
            this.c = null;
            this.b = null;
            this.h.clear();
            this.j = null;
            this.d = true;
            this.e = true;
        }
    }

    public MeasureCardItemHolder.MeasureType e() {
        return this.f;
    }

    public fbp a(HiHealthData hiHealthData) {
        synchronized (this) {
            this.c = hiHealthData;
        }
        return this;
    }

    public HiHealthData b() {
        HiHealthData hiHealthData;
        synchronized (this) {
            hiHealthData = this.b;
        }
        return hiHealthData;
    }

    public fbp d(HiHealthData hiHealthData) {
        synchronized (this) {
            this.b = hiHealthData;
        }
        return this;
    }

    public fbp a(List<fbl> list) {
        synchronized (this) {
            this.h.clear();
            if (list != null) {
                this.h.addAll(list);
            }
        }
        return this;
    }

    public HiHealthData d() {
        HiHealthData hiHealthData;
        synchronized (this) {
            hiHealthData = this.c;
        }
        return hiHealthData;
    }

    public List<fbl> f() {
        ArrayList arrayList;
        synchronized (this) {
            arrayList = new ArrayList(this.h);
        }
        return arrayList;
    }

    public String j() {
        String str;
        synchronized (this) {
            str = this.j;
        }
        return str;
    }

    public String c() {
        StringBuilder sb = new StringBuilder();
        String[] strArr = this.f12427a;
        if (strArr == null || strArr.length <= 0) {
            return sb.toString();
        }
        for (String str : strArr) {
            sb.append(str);
        }
        return sb.toString();
    }

    public fbp a(String str, String... strArr) {
        synchronized (this) {
            this.j = str;
            this.f12427a = strArr;
        }
        return this;
    }

    public boolean h() {
        boolean z;
        synchronized (this) {
            z = this.d;
        }
        return z;
    }

    public fbp c(boolean z) {
        synchronized (this) {
            this.d = z;
        }
        return this;
    }

    public boolean g() {
        boolean z;
        synchronized (this) {
            z = this.e;
        }
        return z;
    }

    public fbp d(boolean z) {
        synchronized (this) {
            this.e = z;
        }
        return this;
    }
}
