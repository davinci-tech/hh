package com.autonavi.aps.amapapi.trans;

import android.text.TextUtils;
import androidx.core.location.LocationRequestCompat;
import com.amap.api.col.p0003sl.mp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public final class f {
    protected static String I;
    protected static String K;

    /* renamed from: a, reason: collision with root package name */
    public String f1644a = "1";
    protected short b = 0;
    protected String c = null;
    protected String d = null;
    protected String e = null;
    protected String f = null;
    protected String g = null;
    public String h = null;
    public String i = null;
    protected String j = null;
    protected String k = null;
    protected String l = null;
    protected String m = null;
    protected String n = null;
    protected String o = null;
    protected String p = null;
    protected String q = null;
    protected String r = null;
    protected String s = null;
    protected String t = null;
    protected String u = null;
    protected String v = null;
    protected String w = null;
    protected String x = null;
    protected String y = null;
    protected int z = 0;
    protected ArrayList<com.autonavi.aps.amapapi.restruct.d> A = new ArrayList<>();
    protected ArrayList<com.autonavi.aps.amapapi.restruct.d> B = new ArrayList<>();
    protected String C = null;
    protected String D = null;
    protected ArrayList<mp> E = new ArrayList<>();
    protected String F = null;
    protected String G = null;
    protected byte[] H = null;
    private byte[] Q = null;
    private int R = 0;
    protected String J = null;
    protected String L = null;
    protected String M = null;
    protected String N = null;
    protected int O = 0;
    private List<com.autonavi.aps.amapapi.restruct.f> S = null;
    private List<com.autonavi.aps.amapapi.restruct.d> T = Collections.synchronizedList(new ArrayList());
    final int P = 3;

    /* JADX WARN: Can't wrap try/catch for region: R(21:12|13|14|(17:289|(1:291)(1:391)|292|(7:294|(1:296)(1:368)|297|(1:299)(1:367)|300|(1:302)(1:366)|303)(12:(11:370|(1:372)(1:390)|373|(1:375)(1:389)|376|(1:378)(1:388)|379|(1:381)(1:387)|382|(1:384)(1:386)|385)|305|(1:307)(1:365)|(1:364)|310|(1:312)(1:363)|313|(1:315)|316|(1:318)(1:362)|319|(2:321|(1:323)(3:324|(11:326|(1:328)(1:358)|329|(1:331)(1:357)|332|(1:334)(1:356)|335|(1:355)|338|(2:353|354)(8:340|(1:342)(1:352)|343|(1:345)|346|(1:348)|349|350)|351)|359))(17:(1:361)|18|(14:22|23|24|25|(1:285)(4:28|(11:30|(3:87|(1:89)|90)(2:36|(3:38|(1:40)|41)(6:81|(3:83|(1:85)|86)|43|(1:80)|47|(3:68|(1:79)(5:70|(1:72)|(1:74)|75|(3:77|63|64)(1:78))|65)(2:53|(3:66|67|65)(7:57|(1:59)|(1:61)|62|63|64|65))))|42|43|(1:45)|80|47|(1:49)|68|(0)(0)|65)|91|92)|93|(1:284)(15:97|98|99|100|101|102|(1:104)|105|106|107|(1:277)(1:109)|110|111|(2:113|114)|116)|117|(1:119)(7:245|(1:247)(1:276)|(1:249)|250|(10:252|253|254|255|256|(1:258)(2:269|(1:271))|259|(1:268)|(2:264|265)(1:267)|266)|274|275)|120|121|122|(1:124)|(29:126|127|128|129|130|(1:132)|134|135|136|137|(3:227|228|229)|139|140|141|142|143|144|145|(1:147)(1:222)|148|(1:150)|151|(5:153|(1:155)(1:194)|156|(5:159|160|(9:163|(2:170|(6:172|(1:174)|175|176|177|178)(4:179|(3:181|(1:183)|184)|177|178))|185|(1:187)|188|176|177|178|161)|190|191)|158)|195|(4:197|(1:199)(1:215)|200|(3:202|(6:205|(1:207)|208|(2:210|211)(1:213)|212|203)|214))|216|(1:218)|219|220)(32:236|237|238|239|240|128|129|130|(0)|134|135|136|137|(0)|139|140|141|142|143|144|145|(0)(0)|148|(0)|151|(0)|195|(0)|216|(0)|219|220))|288|25|(0)|285|93|(1:95)|284|117|(0)(0)|120|121|122|(0)|(0)(0)))|304|305|(0)(0)|(0)|364|310|(0)(0)|313|(0)|316|(0)(0)|319|(0)(0))|17|18|(17:20|22|23|24|25|(0)|285|93|(0)|284|117|(0)(0)|120|121|122|(0)|(0)(0))|288|25|(0)|285|93|(0)|284|117|(0)(0)|120|121|122|(0)|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:278:0x0590, code lost:
    
        if (r0 < (-128)) goto L222;
     */
    /* JADX WARN: Removed duplicated region for block: B:119:0x05f5  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x06a4  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x06a7 A[Catch: all -> 0x06b8, TryCatch #9 {all -> 0x06b8, blocks: (B:122:0x0699, B:126:0x06a7, B:236:0x06aa), top: B:121:0x0699 }] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x06cb A[Catch: all -> 0x06ec, TRY_LEAVE, TryCatch #3 {all -> 0x06ec, blocks: (B:130:0x06c3, B:132:0x06cb), top: B:129:0x06c3 }] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0705  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0715  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x072f  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x082c  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x08cd  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0707  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x06dc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:236:0x06aa A[Catch: all -> 0x06b8, TRY_LEAVE, TryCatch #9 {all -> 0x06b8, blocks: (B:122:0x0699, B:126:0x06a7, B:236:0x06aa), top: B:121:0x0699 }] */
    /* JADX WARN: Removed duplicated region for block: B:245:0x05fa  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x035d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:291:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x0210 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:360:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:369:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x04ee  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0527 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0540  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final byte[] a() {
        /*
            Method dump skipped, instructions count: 2305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.trans.f.a():byte[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x000d, code lost:
    
        if (r0.length != 6) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private byte[] a(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = ":"
            java.lang.String[] r0 = r7.split(r0)
            r1 = 6
            byte[] r2 = new byte[r1]
            r3 = 0
            if (r0 == 0) goto Lf
            int r4 = r0.length     // Catch: java.lang.Throwable -> L3e
            if (r4 == r1) goto L1b
        Lf:
            java.lang.String[] r0 = new java.lang.String[r1]     // Catch: java.lang.Throwable -> L3e
            r4 = r3
        L12:
            if (r4 >= r1) goto L1b
            java.lang.String r5 = "0"
            r0[r4] = r5     // Catch: java.lang.Throwable -> L3e
            int r4 = r4 + 1
            goto L12
        L1b:
            r1 = r3
        L1c:
            int r4 = r0.length     // Catch: java.lang.Throwable -> L3e
            if (r1 >= r4) goto L54
            r4 = r0[r1]     // Catch: java.lang.Throwable -> L3e
            int r4 = r4.length()     // Catch: java.lang.Throwable -> L3e
            r5 = 2
            if (r4 <= r5) goto L30
            r4 = r0[r1]     // Catch: java.lang.Throwable -> L3e
            java.lang.String r4 = r4.substring(r3, r5)     // Catch: java.lang.Throwable -> L3e
            r0[r1] = r4     // Catch: java.lang.Throwable -> L3e
        L30:
            r4 = r0[r1]     // Catch: java.lang.Throwable -> L3e
            r5 = 16
            int r4 = java.lang.Integer.parseInt(r4, r5)     // Catch: java.lang.Throwable -> L3e
            byte r4 = (byte) r4     // Catch: java.lang.Throwable -> L3e
            r2[r1] = r4     // Catch: java.lang.Throwable -> L3e
            int r1 = r1 + 1
            goto L1c
        L3e:
            r0 = move-exception
            java.lang.String r1 = "getMacBa "
            java.lang.String r7 = java.lang.String.valueOf(r7)
            java.lang.String r7 = r1.concat(r7)
            java.lang.String r1 = "Req"
            com.autonavi.aps.amapapi.utils.b.a(r0, r1, r7)
            java.lang.String r7 = "00:00:00:00:00:00"
            byte[] r2 = r6.a(r7)
        L54:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.trans.f.a(java.lang.String):byte[]");
    }

    private void b() {
        String[] strArr = {this.f1644a, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.D, this.F, this.G, I, this.M, this.N};
        for (int i = 0; i < 27; i++) {
            if (TextUtils.isEmpty(strArr[i])) {
                strArr[i] = "";
            }
        }
        if (TextUtils.isEmpty(this.j)) {
            this.j = "0";
        } else if (!"0".equals(this.j) && !"2".equals(this.j)) {
            this.j = "0";
        }
        if (TextUtils.isEmpty(this.k)) {
            this.k = "0";
        } else if (!"0".equals(this.k) && !"1".equals(this.k)) {
            this.k = "0";
        }
        if (TextUtils.isEmpty(this.y)) {
            this.y = "0";
        } else if (!"1".equals(this.y) && !"2".equals(this.y)) {
            this.y = "0";
        }
        if (!com.autonavi.aps.amapapi.restruct.e.a(this.z)) {
            this.z = 0;
        }
        if (this.H == null) {
            this.H = new byte[0];
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x013c, code lost:
    
        if (r7.getSSID().getBytes("UTF-8").length >= 32) goto L68;
     */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01e9 A[Catch: all -> 0x01ef, TRY_LEAVE, TryCatch #9 {all -> 0x01ef, blocks: (B:64:0x01e1, B:66:0x01e9), top: B:63:0x01e1 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01f7 A[Catch: all -> 0x01fd, TRY_LEAVE, TryCatch #8 {all -> 0x01fd, blocks: (B:69:0x01ef, B:71:0x01f7), top: B:68:0x01ef }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0205 A[Catch: all -> 0x020b, TRY_LEAVE, TryCatch #5 {all -> 0x020b, blocks: (B:74:0x01fd, B:76:0x0205), top: B:73:0x01fd }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0213 A[Catch: all -> 0x0219, TRY_LEAVE, TryCatch #4 {all -> 0x0219, blocks: (B:79:0x020b, B:81:0x0213), top: B:78:0x020b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(android.content.Context r19, boolean r20, boolean r21, com.autonavi.aps.amapapi.restruct.e r22, com.autonavi.aps.amapapi.restruct.i r23, android.net.ConnectivityManager r24, java.lang.String r25, com.autonavi.aps.amapapi.restruct.g r26) {
        /*
            Method dump skipped, instructions count: 579
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.aps.amapapi.trans.f.a(android.content.Context, boolean, boolean, com.autonavi.aps.amapapi.restruct.e, com.autonavi.aps.amapapi.restruct.i, android.net.ConnectivityManager, java.lang.String, com.autonavi.aps.amapapi.restruct.g):void");
    }

    private void a(ArrayList<com.autonavi.aps.amapapi.restruct.d> arrayList, ArrayList<com.autonavi.aps.amapapi.restruct.d> arrayList2) {
        if (arrayList2 != null && arrayList2.size() > 0) {
            Iterator<com.autonavi.aps.amapapi.restruct.d> it = arrayList2.iterator();
            while (it.hasNext()) {
                com.autonavi.aps.amapapi.restruct.d next = it.next();
                if (next.r && next.n) {
                    a(next, this.T);
                    return;
                }
            }
        }
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        a(arrayList.get(0), this.T);
    }

    private static void a(com.autonavi.aps.amapapi.restruct.d dVar, List<com.autonavi.aps.amapapi.restruct.d> list) {
        if (dVar == null || list == null) {
            return;
        }
        int size = list.size();
        if (size == 0) {
            list.add(dVar);
            return;
        }
        int i = -1;
        long j = LocationRequestCompat.PASSIVE_INTERVAL;
        int i2 = 0;
        int i3 = -1;
        while (true) {
            if (i2 >= size) {
                i = i3;
                break;
            }
            com.autonavi.aps.amapapi.restruct.d dVar2 = list.get(i2);
            if (dVar.c() != null && dVar.c().equals(dVar2.c())) {
                if (dVar.s != dVar2.s) {
                    dVar2.t = dVar.t;
                    dVar2.s = dVar.s;
                }
            } else {
                j = Math.min(j, dVar2.t);
                if (j == dVar2.t) {
                    i3 = i2;
                }
                i2++;
            }
        }
        if (i >= 0) {
            if (size < 3) {
                list.add(dVar);
            } else {
                if (dVar.t <= j || i >= size) {
                    return;
                }
                list.remove(i);
                list.add(dVar);
            }
        }
    }

    private static int a(String str, byte[] bArr, int i) {
        try {
        } catch (Throwable th) {
            com.autonavi.aps.amapapi.utils.b.a(th, "Req", "copyContentWithByteLen");
            bArr[i] = 0;
        }
        if (TextUtils.isEmpty(str)) {
            bArr[i] = 0;
            return i + 1;
        }
        byte[] bytes = str.getBytes("GBK");
        int length = bytes.length;
        if (length > 127) {
            length = 127;
        }
        bArr[i] = (byte) length;
        int i2 = i + 1;
        System.arraycopy(bytes, 0, bArr, i2, length);
        return i2 + length;
    }
}
