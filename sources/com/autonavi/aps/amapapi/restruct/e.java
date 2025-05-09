package com.autonavi.aps.amapapi.restruct;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoNr;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.amap.api.col.p0003sl.la;
import com.amap.api.col.p0003sl.mi;
import com.amap.api.col.p0003sl.mj;
import com.amap.api.col.p0003sl.mk;
import com.amap.api.col.p0003sl.ml;
import com.amap.api.col.p0003sl.mm;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public final class e {
    TelephonyManager b;
    SignalStrength d;
    private Context h;
    private c m;
    private TelephonyManager.CellInfoCallback q;
    private com.autonavi.aps.amapapi.c u;
    private boolean i = false;
    private boolean j = false;

    /* renamed from: a, reason: collision with root package name */
    ArrayList<d> f1628a = new ArrayList<>();
    private String k = null;
    private ArrayList<d> l = new ArrayList<>();
    private long n = 0;
    PhoneStateListener c = null;
    private boolean o = false;
    private Object p = new Object();
    private boolean r = false;
    boolean e = false;
    StringBuilder f = null;
    private String s = null;
    private String t = null;
    String g = null;

    public static boolean a(int i) {
        return i > 0 && i <= 15;
    }

    private static int b(int i) {
        return (i * 2) - 113;
    }

    static /* synthetic */ boolean b(e eVar) {
        eVar.r = true;
        return true;
    }

    public final List<mi> a() {
        ArrayList arrayList = new ArrayList();
        List<CellInfo> allCellInfo = this.b.getAllCellInfo();
        if (allCellInfo != null) {
            for (CellInfo cellInfo : allCellInfo) {
                if (cellInfo instanceof CellInfoCdma) {
                    CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                    CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
                    mj mjVar = new mj(cellInfo.isRegistered(), true);
                    mjVar.m = cellIdentity.getLatitude();
                    mjVar.n = cellIdentity.getLongitude();
                    mjVar.j = cellIdentity.getSystemId();
                    mjVar.k = cellIdentity.getNetworkId();
                    mjVar.l = cellIdentity.getBasestationId();
                    mjVar.d = cellInfoCdma.getCellSignalStrength().getAsuLevel();
                    mjVar.c = cellInfoCdma.getCellSignalStrength().getCdmaDbm();
                    arrayList.add(mjVar);
                } else if (cellInfo instanceof CellInfoGsm) {
                    CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                    CellIdentityGsm cellIdentity2 = cellInfoGsm.getCellIdentity();
                    mk mkVar = new mk(cellInfo.isRegistered(), true);
                    mkVar.f1336a = String.valueOf(cellIdentity2.getMcc());
                    mkVar.b = String.valueOf(cellIdentity2.getMnc());
                    mkVar.j = cellIdentity2.getLac();
                    mkVar.k = cellIdentity2.getCid();
                    mkVar.c = cellInfoGsm.getCellSignalStrength().getDbm();
                    mkVar.d = cellInfoGsm.getCellSignalStrength().getAsuLevel();
                    mkVar.m = cellIdentity2.getArfcn();
                    mkVar.n = cellIdentity2.getBsic();
                    arrayList.add(mkVar);
                } else if (cellInfo instanceof CellInfoLte) {
                    CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                    CellIdentityLte cellIdentity3 = cellInfoLte.getCellIdentity();
                    ml mlVar = new ml(cellInfo.isRegistered());
                    mlVar.f1336a = String.valueOf(cellIdentity3.getMcc());
                    mlVar.b = String.valueOf(cellIdentity3.getMnc());
                    mlVar.l = cellIdentity3.getPci();
                    mlVar.d = cellInfoLte.getCellSignalStrength().getAsuLevel();
                    mlVar.k = cellIdentity3.getCi();
                    mlVar.j = cellIdentity3.getTac();
                    mlVar.n = cellInfoLte.getCellSignalStrength().getTimingAdvance();
                    mlVar.c = cellInfoLte.getCellSignalStrength().getDbm();
                    mlVar.m = cellIdentity3.getEarfcn();
                    arrayList.add(mlVar);
                } else if (cellInfo instanceof CellInfoWcdma) {
                    CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                    CellIdentityWcdma cellIdentity4 = cellInfoWcdma.getCellIdentity();
                    mm mmVar = new mm(cellInfo.isRegistered(), true);
                    mmVar.f1336a = String.valueOf(cellIdentity4.getMcc());
                    mmVar.b = String.valueOf(cellIdentity4.getMnc());
                    mmVar.j = cellIdentity4.getLac();
                    mmVar.k = cellIdentity4.getCid();
                    mmVar.l = cellIdentity4.getPsc();
                    mmVar.d = cellInfoWcdma.getCellSignalStrength().getAsuLevel();
                    mmVar.c = cellInfoWcdma.getCellSignalStrength().getDbm();
                    mmVar.m = cellIdentity4.getUarfcn();
                    arrayList.add(mmVar);
                }
            }
        }
        return arrayList;
    }

    public final void a(boolean z, boolean z2) {
        try {
            this.e = com.autonavi.aps.amapapi.utils.i.a(this.h);
            if (s()) {
                b(z, z2);
                a(t());
                a(u());
            }
            if (this.e) {
                j();
            }
        } catch (SecurityException e) {
            this.g = e.getMessage();
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "CgiManager", "refresh");
        }
    }

    private void o() {
        if (this.b == null) {
            return;
        }
        p();
    }

    public final void b() {
        try {
            if (Build.VERSION.SDK_INT >= 31) {
                String str = this.h.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 ? "hasFineLocPerm" : "hasNoFineLocPerm";
                String str2 = this.h.checkSelfPermission("android.permission.READ_PHONE_STATE") == 0 ? "hasReadPhoneStatePerm" : "hasNoReadPhoneStatePerm";
                boolean z = (TextUtils.isEmpty(this.t) || this.t.equals(str)) ? false : true;
                if ((TextUtils.isEmpty(this.s) || this.s.equals(str2)) && !z) {
                    return;
                }
                p();
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002d A[Catch: Exception -> 0x0066, TryCatch #0 {Exception -> 0x0066, blocks: (B:2:0x0000, B:4:0x0004, B:5:0x000b, B:8:0x0017, B:10:0x001f, B:11:0x0022, B:12:0x0029, B:14:0x002d, B:17:0x003c, B:25:0x0052, B:28:0x0057, B:29:0x005c, B:31:0x0060, B:38:0x005a), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0060 A[Catch: Exception -> 0x0066, TRY_LEAVE, TryCatch #0 {Exception -> 0x0066, blocks: (B:2:0x0000, B:4:0x0004, B:5:0x000b, B:8:0x0017, B:10:0x001f, B:11:0x0022, B:12:0x0029, B:14:0x002d, B:17:0x003c, B:25:0x0052, B:28:0x0057, B:29:0x005c, B:31:0x0060, B:38:0x005a), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x005a A[Catch: Exception -> 0x0066, TryCatch #0 {Exception -> 0x0066, blocks: (B:2:0x0000, B:4:0x0004, B:5:0x000b, B:8:0x0017, B:10:0x001f, B:11:0x0022, B:12:0x0029, B:14:0x002d, B:17:0x003c, B:25:0x0052, B:28:0x0057, B:29:0x005c, B:31:0x0060, B:38:0x005a), top: B:1:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void p() {
        /*
            r8 = this;
            android.telephony.PhoneStateListener r0 = r8.c     // Catch: java.lang.Exception -> L66
            if (r0 != 0) goto Lb
            com.autonavi.aps.amapapi.restruct.e$b r0 = new com.autonavi.aps.amapapi.restruct.e$b     // Catch: java.lang.Exception -> L66
            r0.<init>()     // Catch: java.lang.Exception -> L66
            r8.c = r0     // Catch: java.lang.Exception -> L66
        Lb:
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L66
            java.lang.String r1 = "hasNoFineLocPerm"
            java.lang.String r2 = "android.permission.ACCESS_FINE_LOCATION"
            r3 = 31
            java.lang.String r4 = "hasFineLocPerm"
            if (r0 < r3) goto L27
            android.content.Context r0 = r8.h     // Catch: java.lang.Exception -> L66
            int r0 = r0.checkSelfPermission(r2)     // Catch: java.lang.Exception -> L66
            if (r0 != 0) goto L22
            r8.t = r4     // Catch: java.lang.Exception -> L66
            goto L27
        L22:
            r8.t = r1     // Catch: java.lang.Exception -> L66
            r0 = 320(0x140, float:4.48E-43)
            goto L29
        L27:
            r0 = 336(0x150, float:4.71E-43)
        L29:
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L66
            if (r5 < r3) goto L5a
            android.content.Context r3 = r8.h     // Catch: java.lang.Exception -> L66
            java.lang.String r5 = "android.permission.READ_PHONE_STATE"
            int r3 = r3.checkSelfPermission(r5)     // Catch: java.lang.Exception -> L66
            r5 = 0
            r6 = 1
            if (r3 != 0) goto L3b
            r3 = r6
            goto L3c
        L3b:
            r3 = r5
        L3c:
            android.content.Context r7 = r8.h     // Catch: java.lang.Exception -> L66
            int r2 = r7.checkSelfPermission(r2)     // Catch: java.lang.Exception -> L66
            if (r2 != 0) goto L45
            r5 = r6
        L45:
            if (r3 == 0) goto L4b
            if (r5 == 0) goto L4b
            r0 = r0 | 1024(0x400, float:1.435E-42)
        L4b:
            if (r3 == 0) goto L50
            java.lang.String r2 = "hasReadPhoneStatePerm"
            goto L52
        L50:
            java.lang.String r2 = "hasNoReadPhoneStatePerm"
        L52:
            r8.s = r2     // Catch: java.lang.Exception -> L66
            if (r5 == 0) goto L57
            r1 = r4
        L57:
            r8.t = r1     // Catch: java.lang.Exception -> L66
            goto L5c
        L5a:
            r0 = r0 | 1024(0x400, float:1.435E-42)
        L5c:
            android.telephony.PhoneStateListener r1 = r8.c     // Catch: java.lang.Exception -> L66
            if (r1 == 0) goto L65
            android.telephony.TelephonyManager r2 = r8.b     // Catch: java.lang.Exception -> L66
            r2.listen(r1, r0)     // Catch: java.lang.Exception -> L66
        L65:
            return
        L66:
            r0 = move-exception
            r0.printStackTrace()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.restruct.e.p():void");
    }

    public e(Context context, Handler handler) {
        this.b = null;
        this.m = null;
        this.h = context;
        if (this.b == null) {
            this.b = (TelephonyManager) com.autonavi.aps.amapapi.utils.i.a(context, "phone");
        }
        o();
        c cVar = new c(context, "cellAge", handler);
        this.m = cVar;
        cVar.a();
    }

    public final ArrayList<d> c() {
        ArrayList<d> arrayList;
        synchronized (this) {
            arrayList = new ArrayList<>();
            ArrayList<d> arrayList2 = this.f1628a;
            if (arrayList2 != null) {
                Iterator<d> it = arrayList2.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().clone());
                }
            }
        }
        return arrayList;
    }

    public final ArrayList<d> d() {
        ArrayList<d> arrayList;
        synchronized (this) {
            arrayList = new ArrayList<>();
            ArrayList<d> arrayList2 = this.l;
            if (arrayList2 != null) {
                Iterator<d> it = arrayList2.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().clone());
                }
            }
        }
        return arrayList;
    }

    public final d e() {
        synchronized (this) {
            if (this.e) {
                return null;
            }
            ArrayList<d> arrayList = this.f1628a;
            if (arrayList.size() <= 0) {
                return null;
            }
            return arrayList.get(0).clone();
        }
    }

    public final d f() {
        synchronized (this) {
            if (this.e) {
                return null;
            }
            ArrayList<d> arrayList = this.l;
            if (arrayList.size() <= 0) {
                return null;
            }
            Iterator<d> it = arrayList.iterator();
            while (it.hasNext()) {
                d next = it.next();
                if (next.n) {
                    return next.clone();
                }
            }
            return arrayList.get(0).clone();
        }
    }

    public final int g() {
        return q() | (this.i ? 4 : 0) | (this.j ? 8 : 0);
    }

    private int q() {
        d e = e();
        if (e != null) {
            return e.l;
        }
        return 0;
    }

    public final int h() {
        return q() & 3;
    }

    private CellLocation r() {
        TelephonyManager telephonyManager = this.b;
        if (telephonyManager != null) {
            try {
                CellLocation cellLocation = telephonyManager.getCellLocation();
                this.g = null;
                return cellLocation;
            } catch (SecurityException e) {
                this.g = e.getMessage();
            } catch (Throwable th) {
                this.g = null;
                com.autonavi.aps.amapapi.utils.b.a(th, "CgiManager", "getCellLocation");
            }
        }
        return null;
    }

    public final TelephonyManager i() {
        return this.b;
    }

    private boolean s() {
        return !this.e && com.autonavi.aps.amapapi.utils.i.b() - this.n >= 45000;
    }

    public final void a(boolean z) {
        PhoneStateListener phoneStateListener;
        this.m.a(z);
        this.n = 0L;
        synchronized (this.p) {
            this.o = true;
        }
        TelephonyManager telephonyManager = this.b;
        if (telephonyManager != null && (phoneStateListener = this.c) != null) {
            try {
                telephonyManager.listen(phoneStateListener, 0);
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "CgiManager", "destroy");
            }
        }
        this.c = null;
        this.d = null;
        this.b = null;
    }

    final class a extends TelephonyManager.CellInfoCallback {
        a() {
        }

        @Override // android.telephony.TelephonyManager.CellInfoCallback
        public final void onCellInfo(List<CellInfo> list) {
            try {
                if (com.autonavi.aps.amapapi.utils.i.b() - e.this.n < 500) {
                    return;
                }
                e.b(e.this);
                e.this.a(e.this.t());
                e.this.a(list);
                e.this.n = com.autonavi.aps.amapapi.utils.i.b();
            } catch (SecurityException e) {
                e.this.g = e.getMessage();
            } catch (Throwable th) {
                com.autonavi.aps.amapapi.utils.b.a(th, "Cgi", "cellInfo");
            }
        }
    }

    private void b(boolean z, boolean z2) {
        if (!this.e && this.b != null && Build.VERSION.SDK_INT >= 29 && this.h.getApplicationInfo().targetSdkVersion >= 29) {
            if (this.q == null) {
                this.q = new a();
            }
            this.b.requestCellInfoUpdate(la.a().d(), this.q);
            if (z2 || z) {
                for (int i = 0; !this.r && i < 20; i++) {
                    try {
                        Thread.sleep(5L);
                    } catch (Throwable unused) {
                    }
                }
            }
        }
        this.j = false;
        TelephonyManager telephonyManager = this.b;
        if (telephonyManager != null) {
            String networkOperator = telephonyManager.getNetworkOperator();
            this.k = networkOperator;
            if (!TextUtils.isEmpty(networkOperator)) {
                this.j = true;
            }
        }
        this.n = com.autonavi.aps.amapapi.utils.i.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CellLocation t() {
        if (this.b == null) {
            return null;
        }
        return r();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(CellLocation cellLocation) {
        synchronized (this) {
            String[] a2 = com.autonavi.aps.amapapi.utils.i.a(this.b);
            this.f1628a.clear();
            if (cellLocation instanceof GsmCellLocation) {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                d dVar = new d(1, true);
                dVar.f1627a = com.autonavi.aps.amapapi.utils.i.e(a2[0]);
                dVar.b = com.autonavi.aps.amapapi.utils.i.e(a2[1]);
                dVar.c = gsmCellLocation.getLac();
                dVar.d = gsmCellLocation.getCid();
                SignalStrength signalStrength = this.d;
                if (signalStrength != null) {
                    int gsmSignalStrength = signalStrength.getGsmSignalStrength();
                    dVar.s = gsmSignalStrength == 99 ? Integer.MAX_VALUE : b(gsmSignalStrength);
                }
                dVar.r = false;
                this.m.a((c) dVar);
                this.f1628a.add(dVar);
                return;
            }
            if (cellLocation instanceof CdmaCellLocation) {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                d dVar2 = new d(2, true);
                dVar2.f1627a = Integer.parseInt(a2[0]);
                dVar2.b = Integer.parseInt(a2[1]);
                dVar2.f = cdmaCellLocation.getBaseStationLatitude();
                dVar2.g = cdmaCellLocation.getBaseStationLongitude();
                dVar2.h = cdmaCellLocation.getSystemId();
                dVar2.i = cdmaCellLocation.getNetworkId();
                dVar2.j = cdmaCellLocation.getBaseStationId();
                SignalStrength signalStrength2 = this.d;
                if (signalStrength2 != null) {
                    dVar2.s = signalStrength2.getCdmaDbm();
                }
                dVar2.r = false;
                this.m.a((c) dVar2);
                this.f1628a.add(dVar2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<CellInfo> u() {
        TelephonyManager telephonyManager;
        List<CellInfo> list;
        try {
            if (com.autonavi.aps.amapapi.utils.i.c() < 18 || (telephonyManager = this.b) == null) {
                return null;
            }
            try {
                list = telephonyManager.getAllCellInfo();
                try {
                    this.g = null;
                } catch (SecurityException e) {
                    e = e;
                    this.g = e.getMessage();
                    return list;
                }
            } catch (SecurityException e2) {
                e = e2;
                list = null;
            }
            return list;
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "Cgi", "getNewCells");
            return null;
        }
    }

    final void a(List<CellInfo> list) {
        d a2;
        synchronized (this) {
            ArrayList<d> arrayList = this.l;
            if (arrayList != null) {
                arrayList.clear();
            }
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    CellInfo cellInfo = list.get(i);
                    if (cellInfo != null) {
                        boolean isRegistered = cellInfo.isRegistered();
                        if (cellInfo instanceof CellInfoCdma) {
                            a2 = a((CellInfoCdma) cellInfo, isRegistered);
                        } else if (cellInfo instanceof CellInfoGsm) {
                            a2 = a((CellInfoGsm) cellInfo, isRegistered);
                        } else if (cellInfo instanceof CellInfoWcdma) {
                            a2 = a((CellInfoWcdma) cellInfo, isRegistered);
                        } else if (cellInfo instanceof CellInfoLte) {
                            a2 = a((CellInfoLte) cellInfo, isRegistered);
                        } else {
                            a2 = (Build.VERSION.SDK_INT < 29 || !(cellInfo instanceof CellInfoNr)) ? null : a((CellInfoNr) cellInfo, isRegistered);
                        }
                        if (a2 != null) {
                            this.m.a((c) a2);
                            a2.m = (short) Math.min(65535L, this.m.e((c) a2));
                            a2.r = true;
                            this.l.add(a2);
                        }
                    }
                }
                this.i = false;
                ArrayList<d> arrayList2 = this.l;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    this.i = true;
                }
            }
        }
    }

    final void j() {
        synchronized (this) {
            this.g = null;
            this.f1628a.clear();
            this.l.clear();
            this.i = false;
            this.j = false;
        }
    }

    public final String k() {
        return this.g;
    }

    public final String l() {
        return this.k;
    }

    private static d a(CellInfoGsm cellInfoGsm, boolean z) {
        if (cellInfoGsm == null || cellInfoGsm.getCellIdentity() == null) {
            return null;
        }
        CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
        d a2 = a(1, z, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getLac(), cellIdentity.getCid(), cellInfoGsm.getCellSignalStrength().getDbm());
        a2.o = cellInfoGsm.getCellIdentity().getBsic();
        a2.p = cellInfoGsm.getCellIdentity().getArfcn();
        a2.q = cellInfoGsm.getCellSignalStrength().getTimingAdvance();
        a2.s = cellInfoGsm.getCellSignalStrength().getDbm();
        return a2;
    }

    private static d a(CellInfoWcdma cellInfoWcdma, boolean z) {
        if (cellInfoWcdma == null || cellInfoWcdma.getCellIdentity() == null) {
            return null;
        }
        CellIdentityWcdma cellIdentity = cellInfoWcdma.getCellIdentity();
        d a2 = a(4, z, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getLac(), cellIdentity.getCid(), cellInfoWcdma.getCellSignalStrength().getDbm());
        a2.o = cellIdentity.getPsc();
        a2.p = cellInfoWcdma.getCellIdentity().getUarfcn();
        a2.s = cellInfoWcdma.getCellSignalStrength().getDbm();
        return a2;
    }

    private static d a(CellInfoLte cellInfoLte, boolean z) {
        if (cellInfoLte == null || cellInfoLte.getCellIdentity() == null) {
            return null;
        }
        CellIdentityLte cellIdentity = cellInfoLte.getCellIdentity();
        d a2 = a(3, z, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getTac(), cellIdentity.getCi(), cellInfoLte.getCellSignalStrength().getDbm());
        a2.o = cellIdentity.getPci();
        a2.p = cellIdentity.getEarfcn();
        a2.q = cellInfoLte.getCellSignalStrength().getTimingAdvance();
        a2.s = cellInfoLte.getCellSignalStrength().getDbm();
        return a2;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.autonavi.aps.amapapi.restruct.d a(android.telephony.CellInfoNr r14, boolean r15) {
        /*
            if (r14 == 0) goto L92
            android.telephony.CellIdentity r0 = r14.getCellIdentity()
            if (r0 != 0) goto La
            goto L92
        La:
            android.telephony.CellIdentity r0 = r14.getCellIdentity()
            android.telephony.CellIdentityNr r0 = (android.telephony.CellIdentityNr) r0
            int r1 = r0.getTac()
            r2 = 2147483647(0x7fffffff, float:NaN)
            r3 = 0
            if (r1 != r2) goto L31
            java.lang.String r2 = "HUAWEI"
            java.lang.String r4 = android.os.Build.MANUFACTURER
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L31
            java.lang.Object[] r2 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L2d
            java.lang.String r4 = "getHwTac"
            int r1 = com.autonavi.aps.amapapi.utils.e.b(r0, r4, r2)     // Catch: java.lang.Throwable -> L2d
            goto L31
        L2d:
            r2 = move-exception
            r2.printStackTrace()
        L31:
            long r4 = r0.getNci()
            java.lang.String r2 = r0.getMccString()     // Catch: java.lang.Throwable -> L48
            int r2 = java.lang.Integer.parseInt(r2)     // Catch: java.lang.Throwable -> L48
            java.lang.String r6 = r0.getMncString()     // Catch: java.lang.Throwable -> L46
            int r3 = java.lang.Integer.parseInt(r6)     // Catch: java.lang.Throwable -> L46
            goto L4e
        L46:
            r6 = move-exception
            goto L4b
        L48:
            r2 = move-exception
            r6 = r2
            r2 = r3
        L4b:
            r6.printStackTrace()
        L4e:
            r9 = r2
            r10 = r3
            android.telephony.CellSignalStrength r2 = r14.getCellSignalStrength()
            android.telephony.CellSignalStrengthNr r2 = (android.telephony.CellSignalStrengthNr) r2
            int r13 = r2.getSsRsrp()
            int r11 = r0.getTac()
            r7 = 5
            r12 = 0
            r8 = r15
            com.autonavi.aps.amapapi.restruct.d r15 = a(r7, r8, r9, r10, r11, r12, r13)
            r15.e = r4
            r2 = 16777215(0xffffff, float:2.3509886E-38)
            r3 = 65535(0xffff, float:9.1834E-41)
            if (r1 <= r2) goto L72
            r15.c = r3
            goto L7b
        L72:
            if (r1 <= r3) goto L79
            r15.c = r3
            r15.q = r1
            goto L7b
        L79:
            r15.c = r1
        L7b:
            int r1 = r0.getPci()
            r15.o = r1
            int r0 = r0.getNrarfcn()
            r15.p = r0
            android.telephony.CellSignalStrength r14 = r14.getCellSignalStrength()
            int r14 = r14.getDbm()
            r15.s = r14
            return r15
        L92:
            r14 = 0
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.restruct.e.a(android.telephony.CellInfoNr, boolean):com.autonavi.aps.amapapi.restruct.d");
    }

    private d a(CellInfoCdma cellInfoCdma, boolean z) {
        int i;
        int i2;
        if (cellInfoCdma == null || cellInfoCdma.getCellIdentity() == null) {
            return null;
        }
        CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
        if (cellIdentity.getSystemId() <= 0 || cellIdentity.getNetworkId() < 0 || cellIdentity.getBasestationId() < 0) {
            return null;
        }
        CellIdentityCdma cellIdentity2 = cellInfoCdma.getCellIdentity();
        String[] a2 = com.autonavi.aps.amapapi.utils.i.a(this.b);
        int i3 = 0;
        try {
            int parseInt = Integer.parseInt(a2[0]);
            try {
                i3 = Integer.parseInt(a2[1]);
            } catch (Throwable unused) {
            }
            i2 = i3;
            i = parseInt;
        } catch (Throwable unused2) {
            i = 0;
            i2 = 0;
        }
        d a3 = a(2, z, i, i2, 0, 0, cellInfoCdma.getCellSignalStrength().getCdmaDbm());
        a3.h = cellIdentity2.getSystemId();
        a3.i = cellIdentity2.getNetworkId();
        a3.j = cellIdentity2.getBasestationId();
        a3.f = cellIdentity2.getLatitude();
        a3.g = cellIdentity2.getLongitude();
        a3.s = cellInfoCdma.getCellSignalStrength().getCdmaDbm();
        return a3;
    }

    private static d a(int i, boolean z, int i2, int i3, int i4, int i5, int i6) {
        d dVar = new d(i, z);
        dVar.f1627a = i2;
        dVar.b = i3;
        dVar.c = i4;
        dVar.d = i5;
        dVar.k = i6;
        return dVar;
    }

    public final String m() {
        String sb;
        synchronized (this) {
            if (this.e) {
                j();
            }
            StringBuilder sb2 = this.f;
            if (sb2 == null) {
                this.f = new StringBuilder();
            } else {
                sb2.delete(0, sb2.length());
            }
            if (h() == 1) {
                for (int i = 1; i < this.f1628a.size(); i++) {
                    StringBuilder sb3 = this.f;
                    sb3.append("#");
                    sb3.append(this.f1628a.get(i).b);
                    StringBuilder sb4 = this.f;
                    sb4.append("|");
                    sb4.append(this.f1628a.get(i).c);
                    StringBuilder sb5 = this.f;
                    sb5.append("|");
                    sb5.append(this.f1628a.get(i).d);
                }
            }
            for (int i2 = 1; i2 < this.l.size(); i2++) {
                d dVar = this.l.get(i2);
                if (dVar.l != 1 && dVar.l != 3 && dVar.l != 4 && dVar.l != 5) {
                    if (dVar.l == 2) {
                        StringBuilder sb6 = this.f;
                        sb6.append("#");
                        sb6.append(dVar.l);
                        StringBuilder sb7 = this.f;
                        sb7.append("|");
                        sb7.append(dVar.f1627a);
                        StringBuilder sb8 = this.f;
                        sb8.append("|");
                        sb8.append(dVar.h);
                        StringBuilder sb9 = this.f;
                        sb9.append("|");
                        sb9.append(dVar.i);
                        StringBuilder sb10 = this.f;
                        sb10.append("|");
                        sb10.append(dVar.j);
                    }
                }
                StringBuilder sb11 = this.f;
                sb11.append("#");
                sb11.append(dVar.l);
                StringBuilder sb12 = this.f;
                sb12.append("|");
                sb12.append(dVar.f1627a);
                StringBuilder sb13 = this.f;
                sb13.append("|");
                sb13.append(dVar.b);
                StringBuilder sb14 = this.f;
                sb14.append("|");
                sb14.append(dVar.c);
                StringBuilder sb15 = this.f;
                sb15.append("|");
                sb15.append(dVar.a());
            }
            if (this.f.length() > 0) {
                this.f.deleteCharAt(0);
            }
            sb = this.f.toString();
        }
        return sb;
    }

    public final boolean n() {
        try {
            TelephonyManager telephonyManager = this.b;
            if (telephonyManager != null) {
                if (!TextUtils.isEmpty(telephonyManager.getSimOperator())) {
                    return true;
                }
                if (!TextUtils.isEmpty(this.b.getSimCountryIso())) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        try {
            int a2 = com.autonavi.aps.amapapi.utils.i.a(com.autonavi.aps.amapapi.utils.i.c(this.h));
            return a2 == 0 || a2 == 4 || a2 == 2 || a2 == 5 || a2 == 3;
        } catch (Throwable unused2) {
            return false;
        }
    }

    public final void a(com.autonavi.aps.amapapi.c cVar) {
        this.u = cVar;
    }

    final class b extends PhoneStateListener {
        b() {
        }

        @Override // android.telephony.PhoneStateListener
        public final void onCellLocationChanged(CellLocation cellLocation) {
            if (com.autonavi.aps.amapapi.utils.i.b() - e.this.n < 500) {
                return;
            }
            try {
                e.this.a(cellLocation);
                e.this.a(e.this.u());
                e.this.n = com.autonavi.aps.amapapi.utils.i.b();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        @Override // android.telephony.PhoneStateListener
        public final void onDataConnectionStateChanged(int i) {
            super.onDataConnectionStateChanged(i);
        }

        @Override // android.telephony.PhoneStateListener
        public final void onSignalStrengthChanged(int i) {
            super.onSignalStrengthChanged(i);
        }

        @Override // android.telephony.PhoneStateListener
        public final void onSignalStrengthsChanged(SignalStrength signalStrength) {
            if (signalStrength == null) {
                return;
            }
            e.this.d = signalStrength;
            try {
                if (e.this.u != null) {
                    e.this.u.c();
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.telephony.PhoneStateListener
        public final void onCellInfoChanged(List<CellInfo> list) {
            try {
                if (e.this.u != null) {
                    e.this.u.c();
                }
                if (com.autonavi.aps.amapapi.utils.i.b() - e.this.n < 500) {
                    return;
                }
                e.this.a(e.this.t());
                e.this.a(list);
                e.this.n = com.autonavi.aps.amapapi.utils.i.b();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        @Override // android.telephony.PhoneStateListener
        public final void onServiceStateChanged(ServiceState serviceState) {
            try {
                int state = serviceState.getState();
                if (state == 0) {
                    e.this.a(false, false);
                } else {
                    if (state != 1) {
                        return;
                    }
                    e.this.j();
                }
            } catch (Throwable unused) {
            }
        }
    }
}
