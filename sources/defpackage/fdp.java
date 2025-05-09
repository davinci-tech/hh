package defpackage;

import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class fdp implements Serializable, Cloneable {
    private SleepViewConstants.ViewTypeEnum b;
    private String c;
    private fdm d;
    private int e;
    private boolean f;
    private long g;
    private fdk i;
    private fdj j;
    private fdr l;
    private SleepViewConstants.SleepTypeEnum n;
    private Map<String, Object> m = new ConcurrentHashMap();
    private List<String> h = new CopyOnWriteArrayList();

    /* renamed from: a, reason: collision with root package name */
    private String f12461a = "SleepViewData";

    public fdr j() {
        return this.l;
    }

    public void c(fdr fdrVar) {
        this.l = fdrVar;
    }

    public fdj d() {
        return this.j;
    }

    public void a(fdj fdjVar) {
        this.j = fdjVar;
    }

    public fdm c() {
        return this.d;
    }

    public void c(fdm fdmVar) {
        this.d = fdmVar;
    }

    public fdk f() {
        return this.i;
    }

    public void d(fdk fdkVar) {
        this.i = fdkVar;
    }

    public Map<String, Object> l() {
        return this.m;
    }

    public void b(Map<String, Object> map) {
        this.m = map;
    }

    public void a(String str) {
        this.c = str;
    }

    public SleepViewConstants.SleepTypeEnum i() {
        return this.n;
    }

    public void c(SleepViewConstants.SleepTypeEnum sleepTypeEnum) {
        this.n = sleepTypeEnum;
    }

    public void c(int i) {
        this.e = i;
    }

    public SleepViewConstants.ViewTypeEnum e() {
        return this.b;
    }

    public boolean o() {
        return this.f;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public long g() {
        return this.g;
    }

    public void c(long j) {
        this.g = j;
    }

    public List<String> h() {
        return this.h;
    }

    public void a(List<String> list) {
        this.h = new CopyOnWriteArrayList(list);
    }

    public boolean m() {
        return this.l.o() || this.i.o() || this.j.o() || this.d.o();
    }

    public boolean n() {
        if (this.b == SleepViewConstants.ViewTypeEnum.DAY) {
            return false;
        }
        int i = AnonymousClass3.d[this.n.ordinal()];
        if (i == 1) {
            return ((fdo) this.i).ap();
        }
        if (i == 2) {
            return ((fdn) this.j).ae();
        }
        if (i == 3) {
            return ((fdq) this.l).bt();
        }
        if (i != 4) {
            return false;
        }
        return ((fdl) this.d).ad();
    }

    /* renamed from: fdp$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[SleepViewConstants.SleepTypeEnum.values().length];
            d = iArr;
            try {
                iArr[SleepViewConstants.SleepTypeEnum.PHONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[SleepViewConstants.SleepTypeEnum.MANUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[SleepViewConstants.SleepTypeEnum.SCIENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                d[SleepViewConstants.SleepTypeEnum.COMMON.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public boolean k() {
        return (((fdq) this.l).bp() == 0 && ((fdn) this.j).ad() == 0 && ((fdo) this.i).am() == 0 && ((fdl) this.d).v() == 0) ? false : true;
    }

    public fdp(SleepViewConstants.ViewTypeEnum viewTypeEnum) {
        if (viewTypeEnum == SleepViewConstants.ViewTypeEnum.DAY) {
            this.l = new fdr();
            this.j = new fdj();
            this.d = new fdm();
            this.i = new fdk();
        } else {
            this.l = new fdq();
            this.j = new fdn();
            this.d = new fdl();
            this.i = new fdo();
        }
        this.b = viewTypeEnum;
        this.n = SleepViewConstants.SleepTypeEnum.UNKNOWN;
    }

    public void e(List<fdp> list) {
        for (fdp fdpVar : list) {
            if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
                ((fdo) this.i).c(fdpVar.f());
            } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
                ((fdn) this.j).a(fdpVar.d());
            } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.COMMON) {
                ((fdl) this.d).b(fdpVar.c());
            } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
                ((fdq) this.l).b(fdpVar.j());
            } else {
                LogUtil.b(this.f12461a, "errorSleepType: ", fdpVar.i());
            }
        }
    }

    public void b() {
        ((fdo) this.i).ai();
        ((fdn) this.j).w();
        ((fdl) this.d).w();
        ((fdq) this.l).az();
    }

    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public fdp clone() {
        try {
            fdp fdpVar = (fdp) super.clone();
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            Map<String, Object> map = this.m;
            if (map != null) {
                concurrentHashMap.putAll(map);
            }
            fdpVar.b(concurrentHashMap);
            return fdpVar;
        } catch (CloneNotSupportedException unused) {
            LogUtil.b(this.f12461a, "CloneNotSupportedException");
            return new fdp(SleepViewConstants.ViewTypeEnum.DAY);
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(this.l);
        stringBuffer.append(",");
        stringBuffer.append(this.j);
        stringBuffer.append(",");
        stringBuffer.append(this.d);
        stringBuffer.append(",");
        stringBuffer.append(this.i);
        stringBuffer.append(",");
        stringBuffer.append(this.m);
        stringBuffer.append(",");
        stringBuffer.append("mDeviceId=" + this.c);
        stringBuffer.append(",");
        stringBuffer.append(this.n);
        stringBuffer.append(",");
        stringBuffer.append("mDeviceType=" + this.e);
        stringBuffer.append(",");
        stringBuffer.append(this.e);
        stringBuffer.append(",");
        stringBuffer.append(this.b);
        stringBuffer.append(",");
        stringBuffer.append(this.f);
        stringBuffer.append(",");
        stringBuffer.append(this.h);
        return stringBuffer.toString();
    }
}
