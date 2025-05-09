package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class ijd {
    private iht c;

    private boolean b(int i) {
        return i >= 44101 && i <= 44299;
    }

    private ijd() {
        this.c = iht.c();
    }

    protected ijd(iht ihtVar) {
        this.c = ihtVar;
    }

    static class d {
        private static final ijd e = new ijd();
    }

    public static ijd c(Context context) {
        return d.e;
    }

    public long e(igo igoVar) {
        return this.c.insert(iid.bzy_(igoVar));
    }

    public int c(int i, int i2, int i3) {
        LogUtil.c("Debug_DataStatManager", "deleteStatDatas() no time limit");
        return this.c.delete("stat_type >=? and stat_type <=? and client_id =? ", new String[]{Long.toString(i), Long.toString(i2), Long.toString(i3)});
    }

    public int e(int i, int i2, int i3, int i4, int i5) {
        return this.c.delete("date =? and sync_status =? and stat_type >=? and stat_type <=? and client_id =? ", new String[]{Long.toString(i5), Long.toString(i4), Long.toString(i), Long.toString(i2), Long.toString(i3)});
    }

    public int d(long j, long j2, int i, int i2, int i3) {
        LogUtil.c("Debug_DataStatManager", "deleteStatDatas()");
        return this.c.delete("date >=? and date <=? and stat_type >=? and stat_type <=? and client_id =? ", new String[]{Integer.toString(HiDateUtil.c(j)), Integer.toString(HiDateUtil.c(j2)), Long.toString(i), Long.toString(i2), Long.toString(i3)});
    }

    public List<igo> b(long j, long j2, int[] iArr, List<Integer> list) {
        int length = iArr.length;
        int size = list.size() + length + 2;
        String[] strArr = new String[size];
        strArr[0] = Long.toString(HiDateUtil.c(j));
        strArr[1] = Long.toString(HiDateUtil.c(j2));
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("date >=? and date <=? ");
        iil.a("stat_type", iArr, stringBuffer, strArr, 2);
        iil.a("client_id", list, stringBuffer, strArr, length + 2);
        return iih.bAY_(this.c.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public igo d(int i, int i2, int i3) {
        return iih.bAL_(this.c.query(a(), b(i, i2, i3, 2), null, null, null));
    }

    public igo a(int i, int i2, int i3, int i4) {
        return iih.bAL_(this.c.query(c(), b(i, i2, i3, i4, 2), null, null, null));
    }

    public List<HiHealthData> d(HiDataReadOption hiDataReadOption, int i, int i2, ifl iflVar) {
        String a2 = iil.a("date", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount());
        return iih.bAW_(this.c.query(d(iflVar), new String[]{Integer.toString(HiDateUtil.c(hiDataReadOption.getStartTime())), Integer.toString(HiDateUtil.c(hiDataReadOption.getEndTime())), Integer.toString(i), Integer.toString(i2), Integer.toString(2)}, null, null, a2));
    }

    public List<HiHealthData> b(HiDataReadOption hiDataReadOption, int[] iArr, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] strArr = new String[iArr.length + 4];
        stringBuffer.append("date >=? and date <=? and client_id =? and sync_status !=? ");
        strArr[0] = Integer.toString(HiDateUtil.c(hiDataReadOption.getStartTime()));
        strArr[1] = Integer.toString(HiDateUtil.c(hiDataReadOption.getEndTime()));
        strArr[2] = Integer.toString(i);
        strArr[3] = Integer.toString(2);
        iil.a("stat_type", iArr, stringBuffer, strArr, 4);
        return iih.bAW_(this.c.query(stringBuffer.toString(), strArr, null, null, iil.a("date", hiDataReadOption.getSortOrder(), hiDataReadOption.getAnchor(), hiDataReadOption.getCount())));
    }

    public List<HiHealthData> d(int i, int i2) {
        return iih.bAW_(this.c.query("stat_type =? and client_id =? ", new String[]{Integer.toString(i), Integer.toString(i2)}, null, null, null));
    }

    public long d(igo igoVar, igo igoVar2) {
        int update;
        if (Double.compare(igoVar2.l(), igoVar.l()) == 0) {
            return 1L;
        }
        boolean z = false;
        if (igoVar.e() == HiDateUtil.c(System.currentTimeMillis())) {
            ReleaseLogUtil.e("HiH_DataStatManager", "updStatDt upload,statTable=", igoVar);
            igoVar.g(0);
        }
        ContentValues bzR_ = iid.bzR_(igoVar);
        if (igoVar.g() == 1) {
            bzR_.remove("sync_status");
        }
        List<Integer> e = HiHealthDictManager.d(BaseApplication.e()).e();
        if (!HiCommonUtil.d(e) && e.contains(Integer.valueOf(igoVar.b()))) {
            z = true;
        }
        if (igoVar.f() < 50 || z) {
            update = this.c.update(bzR_, c(), b(igoVar.e(), igoVar.b(), igoVar.f(), igoVar.d(), 2));
        } else {
            update = this.c.update(bzR_, a(), b(igoVar.e(), igoVar.f(), igoVar.d(), 2));
        }
        return update;
    }

    private long m(igo igoVar) {
        iid.bzR_(igoVar).put("date", Integer.valueOf(igoVar.e()));
        return this.c.update(r0, "stat_type =? and client_id =? ", new String[]{Integer.toString(igoVar.f()), Integer.toString(igoVar.d())});
    }

    public boolean a(igo igoVar) {
        return e(igoVar, false, igoVar.f() < 50);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean e(java.util.List<defpackage.igo> r7, android.content.Context r8) {
        /*
            r6 = this;
            r0 = 1
            if (r7 == 0) goto L98
            boolean r1 = r7.isEmpty()
            if (r1 == 0) goto Lb
            goto L98
        Lb:
            r1 = 0
            java.lang.Object r2 = r7.get(r1)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            igo r2 = (defpackage.igo) r2     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            int r2 = r2.f()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            boolean r2 = defpackage.ivu.i(r8, r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            if (r2 != 0) goto L2b
            java.lang.Object r2 = r7.get(r1)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            igo r2 = (defpackage.igo) r2     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            int r2 = r2.f()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            boolean r2 = defpackage.ivu.e(r8, r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5a
            goto L2c
        L2b:
            r2 = r1
        L2c:
            ije r3 = new ije     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r3.<init>()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r7.forEach(r3)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            if (r2 == 0) goto L43
            java.lang.Object r3 = r7.get(r1)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            igo r3 = (defpackage.igo) r3     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            int r3 = r3.f()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            defpackage.ivu.j(r8, r3)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
        L43:
            if (r2 == 0) goto L52
            java.lang.Object r7 = r7.get(r1)
            igo r7 = (defpackage.igo) r7
            int r7 = r7.f()
            defpackage.ivu.c(r8, r7)
        L52:
            return r0
        L53:
            r0 = move-exception
            goto L88
        L55:
            r3 = move-exception
            goto L5d
        L57:
            r0 = move-exception
            r2 = r1
            goto L88
        L5a:
            r2 = move-exception
            r3 = r2
            r2 = r1
        L5d:
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L53
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L53
            java.lang.String r5 = "Error to insertBatchList,cause:"
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L53
            java.lang.String r3 = r3.getMessage()     // Catch: java.lang.Throwable -> L53
            r4.append(r3)     // Catch: java.lang.Throwable -> L53
            java.lang.String r3 = r4.toString()     // Catch: java.lang.Throwable -> L53
            r0[r1] = r3     // Catch: java.lang.Throwable -> L53
            java.lang.String r3 = "HiH_DataStatManager"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r3, r0)     // Catch: java.lang.Throwable -> L53
            if (r2 == 0) goto L87
            java.lang.Object r7 = r7.get(r1)
            igo r7 = (defpackage.igo) r7
            int r7 = r7.f()
            defpackage.ivu.c(r8, r7)
        L87:
            return r1
        L88:
            if (r2 == 0) goto L97
            java.lang.Object r7 = r7.get(r1)
            igo r7 = (defpackage.igo) r7
            int r7 = r7.f()
            defpackage.ivu.c(r8, r7)
        L97:
            throw r0
        L98:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ijd.e(java.util.List, android.content.Context):boolean");
    }

    /* synthetic */ void d(igo igoVar) {
        if (!f(igoVar) || e(igoVar) > 0) {
            return;
        }
        ReleaseLogUtil.c("Debug_DataStatManager", "Error to insertBatchList, statTable=" + igoVar);
    }

    private boolean f(igo igoVar) {
        double l = igoVar.l();
        int f = igoVar.f();
        if (f >= 47401 && f <= 47405) {
            if (Double.compare(l, 0.0d) < 0) {
                ReleaseLogUtil.d("HiH_DataStatManager", "insOrUpdStatDt menstrual data illegal");
                return false;
            }
        } else if ((f < 44219 || f > 44234) && Double.compare(l, 0.0d) <= 0) {
            ReleaseLogUtil.d("HiH_DataStatManager", "insOrUpdStatDt newStVal<=0,tp=", Integer.valueOf(f), ", newDt=", Integer.valueOf(igoVar.e()));
            return false;
        }
        return true;
    }

    public boolean c(igo igoVar) {
        return i(igoVar);
    }

    /*  JADX ERROR: Types fix failed
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:183)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:242)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
        */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0065: MOVE (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:24:0x0065 */
    private boolean i(defpackage.igo r7) {
        /*
            r6 = this;
            r0 = 0
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            int r2 = r7.f()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            boolean r1 = defpackage.ivu.i(r1, r2)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            if (r1 != 0) goto L1c
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            int r2 = r7.f()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            boolean r1 = defpackage.ivu.e(r1, r2)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            goto L1d
        L1c:
            r1 = r0
        L1d:
            boolean r2 = r6.g(r7)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L64
            if (r1 == 0) goto L2e
            android.content.Context r3 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L64
            int r4 = r7.f()     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L64
            defpackage.ivu.j(r3, r4)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L64
        L2e:
            if (r1 == 0) goto L3b
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            int r7 = r7.f()
            defpackage.ivu.c(r0, r7)
        L3b:
            return r2
        L3c:
            r2 = move-exception
            goto L43
        L3e:
            r1 = move-exception
            goto L68
        L40:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L43:
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L64
            java.lang.String r4 = "insertOrUpdateVO2MaxDataSync: "
            r3[r0] = r4     // Catch: java.lang.Throwable -> L64
            java.lang.String r2 = health.compact.a.LogAnonymous.b(r2)     // Catch: java.lang.Throwable -> L64
            r4 = 1
            r3[r4] = r2     // Catch: java.lang.Throwable -> L64
            java.lang.String r2 = "HiH_DataStatManager"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r2, r3)     // Catch: java.lang.Throwable -> L64
            if (r1 == 0) goto L63
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()
            int r7 = r7.f()
            defpackage.ivu.c(r1, r7)
        L63:
            return r0
        L64:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
        L68:
            if (r0 == 0) goto L75
            android.content.Context r0 = com.huawei.haf.application.BaseApplication.e()
            int r7 = r7.f()
            defpackage.ivu.c(r0, r7)
        L75:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ijd.i(igo):boolean");
    }

    private boolean g(igo igoVar) {
        LogUtil.c("Debug_DataStatManager", "insertOrUpdateVO2MaxData newStat =", igoVar);
        if (igoVar.c() <= 0) {
            igoVar.a(System.currentTimeMillis());
        }
        double l = igoVar.l();
        igo bAL_ = iih.bAL_(this.c.query("stat_type =? and client_id =? ", new String[]{Integer.toString(igoVar.f()), Integer.toString(igoVar.d())}, null, null, null));
        LogUtil.c("Debug_DataStatManager", "insertOrUpdateVO2MaxData() oldStat =", bAL_);
        if (bAL_ == null) {
            LogUtil.a("Debug_DataStatManager", "insertOrUpdateVO2MaxData a new stat comes , stat is ", igoVar);
            if (l <= 0.0d) {
                LogUtil.h("Debug_DataStatManager", "insertOrUpdateVO2MaxData() newStat value <= 0 ! it is ", igoVar);
                return false;
            }
            return iil.a(e(igoVar));
        }
        if (ijp.d(bAL_, igoVar)) {
            return iil.a(m(igoVar));
        }
        return false;
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0064: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:24:0x0064 */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0067  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean e(defpackage.igo r4, boolean r5, boolean r6) {
        /*
            r3 = this;
            r0 = 0
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            int r2 = r4.f()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            boolean r1 = defpackage.ivu.i(r1, r2)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            if (r1 != 0) goto L1c
            android.content.Context r1 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            int r2 = r4.f()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            boolean r1 = defpackage.ivu.e(r1, r2)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            goto L1d
        L1c:
            r1 = r0
        L1d:
            boolean r5 = r3.c(r4, r5, r6)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L63
            if (r1 == 0) goto L2e
            android.content.Context r6 = com.huawei.haf.application.BaseApplication.e()     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L63
            int r2 = r4.f()     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L63
            defpackage.ivu.j(r6, r2)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L63
        L2e:
            if (r1 == 0) goto L3b
            android.content.Context r6 = com.huawei.haf.application.BaseApplication.e()
            int r4 = r4.f()
            defpackage.ivu.c(r6, r4)
        L3b:
            return r5
        L3c:
            r5 = move-exception
            goto L42
        L3e:
            r5 = move-exception
            goto L65
        L40:
            r5 = move-exception
            r1 = r0
        L42:
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L63
            java.lang.String r2 = "insertOrUpdateStatDataSync: "
            r6[r0] = r2     // Catch: java.lang.Throwable -> L63
            java.lang.String r5 = health.compact.a.LogAnonymous.b(r5)     // Catch: java.lang.Throwable -> L63
            r2 = 1
            r6[r2] = r5     // Catch: java.lang.Throwable -> L63
            java.lang.String r5 = "HiH_DataStatManager"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.d(r5, r6)     // Catch: java.lang.Throwable -> L63
            if (r1 == 0) goto L62
            android.content.Context r5 = com.huawei.haf.application.BaseApplication.e()
            int r4 = r4.f()
            defpackage.ivu.c(r5, r4)
        L62:
            return r0
        L63:
            r5 = move-exception
            r0 = r1
        L65:
            if (r0 == 0) goto L72
            android.content.Context r6 = com.huawei.haf.application.BaseApplication.e()
            int r4 = r4.f()
            defpackage.ivu.c(r6, r4)
        L72:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ijd.e(igo, boolean, boolean):boolean");
    }

    private boolean c(igo igoVar, boolean z, boolean z2) {
        igo d2;
        boolean z3 = (o(igoVar) || iuz.f()) ? false : true;
        if (igoVar.c() <= 0) {
            igoVar.a(System.currentTimeMillis());
        }
        if (!f(igoVar)) {
            return false;
        }
        if (z2) {
            d2 = a(igoVar.e(), igoVar.b(), igoVar.f(), igoVar.d());
        } else {
            d2 = d(igoVar.e(), igoVar.f(), igoVar.d());
        }
        if (d2 == null) {
            if (z3 || b(igoVar.f())) {
                ReleaseLogUtil.e("HiH_DataStatManager", "insOrUpdStatDt:newDt=", Integer.valueOf(igoVar.e()), ",tp=", Integer.valueOf(igoVar.f()), ",", HiCommonUtil.d(Double.valueOf(igoVar.l())), ", old=null");
            }
            return iil.a(e(igoVar));
        }
        boolean z4 = ijp.d(d2, igoVar) || z;
        if (z3 || b(igoVar.f())) {
            ReleaseLogUtil.e("HiH_DataStatManager", "insOrUpdStatDt:newDt=", Integer.valueOf(igoVar.e()), ",tp=", Integer.valueOf(igoVar.f()), ",", HiCommonUtil.d(Double.valueOf(igoVar.l())), ",old=", HiCommonUtil.d(Double.valueOf(d2.l())), ",Upd=", Boolean.valueOf(z4));
        }
        if (!z4) {
            return true;
        }
        if (47401 == igoVar.f()) {
            long a2 = HiDateUtil.a(HiDateUtil.c(System.currentTimeMillis(), 30));
            long a3 = HiDateUtil.a(HiDateUtil.c(System.currentTimeMillis(), -30));
            long a4 = HiDateUtil.a(igoVar.e());
            if (a4 > a2 && a4 < a3) {
                ReleaseLogUtil.e("HiH_DataStatManager", "insOrUpdStatDt:newDt=", Integer.valueOf(igoVar.e()), ",tp=", Integer.valueOf(igoVar.f()), ",", HiCommonUtil.d(Double.valueOf(igoVar.l())), ",old=", HiCommonUtil.d(Double.valueOf(d2.l())), ",Upd=", Boolean.valueOf(z4));
            }
        }
        if (Double.compare(d2.l(), igoVar.l()) != 0) {
            igoVar.g(0);
        }
        return iil.a(d(igoVar, d2));
    }

    public int a(int i, long j, int i2, long j2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", (Integer) 1);
        return this.c.update(contentValues, "client_id =? and date =? and stat_type =? and sync_status !=? and modified_time <=? ", new String[]{Integer.toString(i), Integer.toString(HiDateUtil.c(j)), Integer.toString(i2), Integer.toString(2), Long.toString(j2)});
    }

    public List<Integer> b(int i, int[] iArr) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] strArr = new String[iArr.length + 2];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(0);
        stringBuffer.append("client_id =? and sync_status =? ");
        iil.a("stat_type", iArr, stringBuffer, strArr, 2);
        iil.b(stringBuffer, "date");
        return iih.bAX_(this.c.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public List<Integer> a(int i, int[] iArr) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] strArr = new String[iArr.length + 2];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(2);
        stringBuffer.append("client_id =? and sync_status =? ");
        iil.a("stat_type", iArr, stringBuffer, strArr, 2);
        iil.b(stringBuffer, "date");
        return iih.bAX_(this.c.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public int c(int i, long j, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", (Integer) 1);
        return this.c.update(contentValues, "client_id =? and date =? and stat_type =? and sync_status !=? ", new String[]{Integer.toString(i), Integer.toString(HiDateUtil.c(j)), Integer.toString(i2), Integer.toString(2)});
    }

    public int d(int i, int i2, int i3, int i4, int i5) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", (Integer) 0);
        return this.c.update(contentValues, "client_id =? and date =? and stat_type >=? and stat_type <=? and sync_status =? ", new String[]{Integer.toString(i), Integer.toString(i2), Integer.toString(i3), Integer.toString(i4), Integer.toString(i5)});
    }

    private String[] b(int i, int i2, int i3, int i4) {
        return new String[]{Integer.toString(i), Long.toString(i2), Integer.toString(i3), Integer.toString(i4)};
    }

    private String[] b(int i, int i2, int i3, int i4, int i5) {
        return new String[]{Integer.toString(i), Integer.toString(i2), Long.toString(i3), Integer.toString(i4), Integer.toString(i5)};
    }

    private String d(ifl iflVar) {
        StringBuffer stringBuffer = new StringBuffer("date >=? and date <=? and stat_type =? and client_id =? and sync_status !=? ");
        if (iflVar != null && !TextUtils.isEmpty(iflVar.c())) {
            stringBuffer.append(iil.e(iflVar.c()));
        }
        return stringBuffer.toString();
    }

    public List<igo> c(int i, int i2, int i3, int i4) {
        return iih.bAY_(this.c.query("client_id =? ", new String[]{Integer.toString(i)}, null, null, iil.a("date", i2, i3, i4)));
    }

    public boolean b(igo igoVar) {
        if (igoVar.f() < 50) {
            return iil.a(h(igoVar));
        }
        return iil.a(j(igoVar));
    }

    public int b(long j) {
        return this.c.delete("_id =? ", new String[]{Long.toString(j)});
    }

    private int j(igo igoVar) {
        return this.c.delete("date =? and stat_type =? and client_id =? ", new String[]{Integer.toString(igoVar.e()), Long.toString(igoVar.f()), Long.toString(igoVar.d())});
    }

    private int h(igo igoVar) {
        return this.c.delete("date =? and hihealth_type =? and stat_type =? and client_id =? ", new String[]{Integer.toString(igoVar.e()), Long.toString(igoVar.b()), Long.toString(igoVar.f()), Long.toString(igoVar.d())});
    }

    private boolean o(igo igoVar) {
        return 47000 >= igoVar.f() && 46001 <= igoVar.f();
    }

    public boolean e() {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("stat_type =?  or stat_type =?  or stat_type =?  or stat_type =?  or stat_type =? ");
        return iih.bAv_(this.c.query(stringBuffer.toString(), new String[]{Integer.toString(46016), Integer.toString(46017), Integer.toString(46019), Integer.toString(46018), Integer.toString(46020)}, null, null, null));
    }

    public List<Integer> e(int i) {
        return iih.bAU_(this.c.query("stat_type =? and client_id =? ", new String[]{Integer.toString(44004), Integer.toString(i)}, null, null, iil.a("date", 1, 0, 7)), "date");
    }

    public void b(int i, List<String> list, int i2) {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("delete from hihealth_stat_day where date = ?  and stat_type < ?  and client_id = ").append(i2);
        int size = !koq.b(list) ? list.size() : 0;
        String[] strArr = new String[size + 2];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(50);
        if (size > 0) {
            stringBuffer.append(" and hihealth_type not in (");
            for (int i3 = 0; i3 < size; i3++) {
                stringBuffer.append("?");
                if (i3 < list.size() - 1) {
                    stringBuffer.append(",");
                } else {
                    stringBuffer.append(Constants.RIGHT_BRACKET_ONLY);
                }
                strArr[i3 + 2] = list.get(i3);
            }
        }
        this.c.execSQL(stringBuffer.toString(), strArr);
    }

    public List<igo> e(int i, int i2, List<Integer> list, int i3) {
        int size = list.size() + 2;
        String[] strArr = new String[size];
        strArr[0] = Integer.toString(i);
        strArr[1] = Integer.toString(i2);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("stat_type =? and sync_status =? ");
        iil.a("client_id", list, stringBuffer, strArr, 2);
        iij.a(stringBuffer, 0, i3);
        return iih.bAY_(this.c.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public List<igo> b(int i, int i2, int i3, int[] iArr) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] strArr = new String[iArr.length + 4];
        stringBuffer.append("date =? and client_id =? and hihealth_type =? and sync_status !=? ");
        strArr[0] = Long.toString(i);
        strArr[1] = Integer.toString(i2);
        strArr[2] = Integer.toString(i3);
        strArr[3] = Integer.toString(2);
        iil.a("stat_type", iArr, stringBuffer, strArr, 4);
        return iih.bAY_(this.c.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public List<igo> d(int i, int i2, int[] iArr) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] strArr = new String[iArr.length + 3];
        stringBuffer.append("date =? and client_id =? and sync_status !=? ");
        strArr[0] = Long.toString(i);
        strArr[1] = Integer.toString(i2);
        strArr[2] = Integer.toString(2);
        iil.a("stat_type", iArr, stringBuffer, strArr, 3);
        LogUtil.c("HiH_DataStatManager", "queryStatDataByTypes ", stringBuffer.toString());
        return iih.bAY_(this.c.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public int e(int i, int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sync_status", Integer.valueOf(i2));
        return this.c.update(contentValues, "_id =? ", new String[]{Integer.toString(i)});
    }

    public List<igo> b(int i, List<Integer> list) {
        int size = list.size() + 1;
        String[] strArr = new String[size];
        strArr[0] = Integer.toString(i);
        StringBuffer stringBuffer = new StringBuffer((size * 2) + 32);
        stringBuffer.append("sync_status =? ");
        iil.a("client_id", list, stringBuffer, strArr, 1);
        iij.a(stringBuffer, 0, 100);
        return iih.bAY_(this.c.query(stringBuffer.toString(), strArr, null, null, null));
    }

    public List<igp> d() {
        return bBk_(this.c.rawQuery("select *, count(_id) as counts , max(_id) as maxId from hihealth_stat_day where date > 20240901 group by date, hihealth_type,stat_type, client_id  having counts>1;", null));
    }

    public static List<igp> bBk_(Cursor cursor) {
        if (cursor == null) {
            ReleaseLogUtil.d("HiH_DataStatManager", "parseDelDuplicatedStat() Cursor query == null");
            return null;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursor.moveToNext()) {
            try {
                arrayList.add(bBj_(cursor));
            } finally {
                cursor.close();
            }
        }
        return arrayList;
    }

    private static igp bBj_(Cursor cursor) {
        igp igpVar = new igp();
        igpVar.c(cursor.getInt(cursor.getColumnIndex("_id")));
        igpVar.d(cursor.getInt(cursor.getColumnIndex("date")));
        igpVar.b(cursor.getInt(cursor.getColumnIndex("hihealth_type")));
        igpVar.j(cursor.getInt(cursor.getColumnIndex("stat_type")));
        igpVar.a(cursor.getInt(cursor.getColumnIndex("client_id")));
        igpVar.e(cursor.getInt(cursor.getColumnIndex("maxId")));
        return igpVar;
    }

    public int a(igp igpVar) {
        int c = igpVar.c();
        int b = igpVar.b();
        return this.c.delete("date =? and hihealth_type =? and stat_type =? and client_id =? and _id <? ", new String[]{Integer.toString(igpVar.e()), Integer.toString(igpVar.a()), Integer.toString(igpVar.d()), Integer.toString(b), Integer.toString(c)});
    }

    private String c() {
        return "date =? and hihealth_type =? and stat_type =? and client_id =? and sync_status !=? ";
    }

    private String a() {
        return "date =? and stat_type =? and client_id =? and sync_status !=? ";
    }
}
