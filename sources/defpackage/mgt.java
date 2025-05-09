package defpackage;

import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public final class mgt {

    /* renamed from: a, reason: collision with root package name */
    private static final mgu f14985a;
    private static final mgu b;
    private static final mgu c;
    private static final mgu d;
    private static final mgu e;
    private static final mgu f;
    private static final mgu g;
    private static final mgu h;
    private static final mgu i;
    private static final mgu j;
    private static final mgu k;
    private static final mgu l;
    private static final mgu m;
    private static final mgu n;
    private static final mgu o;
    private static final mgu p;
    private static final List<String> q;
    private static final Map<String, mgu> r;
    private static final mgu s;

    static {
        HashMap hashMap = new HashMap();
        r = hashMap;
        ArrayList arrayList = new ArrayList();
        q = arrayList;
        mgu mguVar = new mgu(EnumAnnualType.REPORT_INITAL.value(), Arrays.asList(mgr.ag, mgr.df, mgr.du, mgr.dg, mgr.dd, mgr.ab, mgr.g));
        g = mguVar;
        mgu mguVar2 = new mgu(EnumAnnualType.REPORT_STEP.value(), Arrays.asList(mgr.f123do, mgr.dn, mgr.cs, mgr.cv, mgr.bl, mgr.bj, mgr.bp, mgr.bn, mgr.bi, mgr.bk, mgr.dm));
        l = mguVar2;
        mgu mguVar3 = new mgu(EnumAnnualType.REPORT_SLEEP.value(), Arrays.asList(mgr.ck, mgr.cn, mgr.ch, mgr.co, mgr.cj, mgr.ct, mgr.ci, mgr.cm, mgr.cp, mgr.cr, mgr.cq, mgr.bv));
        n = mguVar3;
        mgu mguVar4 = new mgu(EnumAnnualType.REPORT_RUN.value(), Arrays.asList(mgr.dh, mgr.ce, mgr.cg, mgr.cd, mgr.cc, mgr.cl, mgr.ao, mgr.aq, mgr.ap, mgr.ar, mgr.bf, mgr.bg, mgr.cf, mgr.by, mgr.cb, mgr.ca));
        m = mguVar4;
        mgu mguVar5 = new mgu(EnumAnnualType.REPORT_CYCLE.value(), Arrays.asList(mgr.db, mgr.y, mgr.v, mgr.u, mgr.x, mgr.bh));
        c = mguVar5;
        mgu mguVar6 = new mgu(EnumAnnualType.REPORT_FITNESS.value(), Arrays.asList(mgr.dj, mgr.al, mgr.an, mgr.ak, mgr.bm, mgr.ad, mgr.ai, mgr.ah, mgr.cw, mgr.cy, mgr.bo, mgr.br, mgr.cz, mgr.da, mgr.di, mgr.de, mgr.am, mgr.aa, mgr.cx, mgr.aj));
        h = mguVar6;
        mgu mguVar7 = new mgu(EnumAnnualType.REPORT_REWARD.value(), Arrays.asList(mgr.bq, mgr.as, mgr.bx));
        o = mguVar7;
        mgu mguVar8 = new mgu(EnumAnnualType.REPORT_ACTIVITY.value(), Arrays.asList(mgr.at, mgr.ax, mgr.z));
        d = mguVar8;
        mgu mguVar9 = new mgu(EnumAnnualType.REPORT_SUMARY.value(), Arrays.asList(mgr.dd, mgr.cu, mgr.dg));
        s = mguVar9;
        mgu mguVar10 = new mgu(EnumAnnualType.REPORT_HEALTH.value(), Arrays.asList(mgr.c, mgr.d, mgr.e, mgr.f14983a, mgr.i, mgr.j, mgr.h, mgr.f, mgr.b, mgr.dq, mgr.dl, mgr.dp));
        j = mguVar10;
        mgu mguVar11 = new mgu(EnumAnnualType.REPORT_DIET.value(), Arrays.asList(mgr.ac));
        b = mguVar11;
        mgu mguVar12 = new mgu(EnumAnnualType.REPORT_MUSIC.value(), Arrays.asList(mgr.aw, mgr.q, mgr.au, mgr.av, mgr.af, mgr.dk));
        k = mguVar12;
        mgu mguVar13 = new mgu(EnumAnnualType.REPORT_VIP.value(), Arrays.asList(mgr.ae, mgr.w));
        p = mguVar13;
        mgu mguVar14 = new mgu(EnumAnnualType.REPORT_LIVING.value(), Arrays.asList(mgr.dc, mgr.bd, mgr.bs, mgr.be, mgr.s));
        i = mguVar14;
        mgu mguVar15 = new mgu(EnumAnnualType.REPORT_CLIMB_HILL.value(), Arrays.asList(mgr.o, mgr.n, mgr.l, mgr.m, mgr.p, mgr.t, mgr.r, mgr.k));
        f14985a = mguVar15;
        mgu mguVar16 = new mgu(EnumAnnualType.REPORT_BADMINTON.value(), Arrays.asList(mgr.bu, mgr.bw, mgr.bt, mgr.bz));
        e = mguVar16;
        mgu mguVar17 = new mgu(EnumAnnualType.REPORT_JUMP_ROPE.value(), Arrays.asList(mgr.az, mgr.bc, mgr.ba, mgr.ay, mgr.bb));
        f = mguVar17;
        arrayList.add(EnumAnnualType.REPORT_INITAL.value());
        arrayList.add(EnumAnnualType.REPORT_STEP.value());
        arrayList.add(EnumAnnualType.REPORT_SLEEP.value());
        arrayList.add(EnumAnnualType.REPORT_RUN.value());
        arrayList.add(EnumAnnualType.REPORT_CYCLE.value());
        arrayList.add(EnumAnnualType.REPORT_FITNESS.value());
        arrayList.add(EnumAnnualType.REPORT_REWARD.value());
        arrayList.add(EnumAnnualType.REPORT_ACTIVITY.value());
        arrayList.add(EnumAnnualType.REPORT_SUMARY.value());
        arrayList.add(EnumAnnualType.REPORT_HEALTH.value());
        arrayList.add(EnumAnnualType.REPORT_DIET.value());
        arrayList.add(EnumAnnualType.REPORT_MUSIC.value());
        arrayList.add(EnumAnnualType.REPORT_VIP.value());
        arrayList.add(EnumAnnualType.REPORT_LIVING.value());
        arrayList.add(EnumAnnualType.REPORT_CLIMB_HILL.value());
        arrayList.add(EnumAnnualType.REPORT_BADMINTON.value());
        arrayList.add(EnumAnnualType.REPORT_JUMP_ROPE.value());
        hashMap.put(EnumAnnualType.REPORT_INITAL.value(), mguVar);
        hashMap.put(EnumAnnualType.REPORT_STEP.value(), mguVar2);
        hashMap.put(EnumAnnualType.REPORT_SLEEP.value(), mguVar3);
        hashMap.put(EnumAnnualType.REPORT_RUN.value(), mguVar4);
        hashMap.put(EnumAnnualType.REPORT_CYCLE.value(), mguVar5);
        hashMap.put(EnumAnnualType.REPORT_FITNESS.value(), mguVar6);
        hashMap.put(EnumAnnualType.REPORT_REWARD.value(), mguVar7);
        hashMap.put(EnumAnnualType.REPORT_ACTIVITY.value(), mguVar8);
        hashMap.put(EnumAnnualType.REPORT_SUMARY.value(), mguVar9);
        hashMap.put(EnumAnnualType.REPORT_HEALTH.value(), mguVar10);
        hashMap.put(EnumAnnualType.REPORT_DIET.value(), mguVar11);
        hashMap.put(EnumAnnualType.REPORT_MUSIC.value(), mguVar12);
        hashMap.put(EnumAnnualType.REPORT_VIP.value(), mguVar13);
        hashMap.put(EnumAnnualType.REPORT_LIVING.value(), mguVar14);
        hashMap.put(EnumAnnualType.REPORT_CLIMB_HILL.value(), mguVar15);
        hashMap.put(EnumAnnualType.REPORT_BADMINTON.value(), mguVar16);
        hashMap.put(EnumAnnualType.REPORT_JUMP_ROPE.value(), mguVar17);
    }

    public static mgu a(String str) {
        return r.get(str);
    }

    public static List<mgr> e(String str) {
        mgu a2 = a(str);
        if (a2 == null) {
            return new ArrayList();
        }
        return a2.b();
    }

    public static List<String> c() {
        return Collections.unmodifiableList(q);
    }
}
