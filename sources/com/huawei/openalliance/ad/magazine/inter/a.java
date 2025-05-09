package com.huawei.openalliance.ad.magazine.inter;

import android.content.Context;
import com.huawei.openalliance.ad.analysis.c;
import com.huawei.openalliance.ad.beans.parameter.AdSlotParam;
import com.huawei.openalliance.ad.beans.server.AdContentRsp;
import com.huawei.openalliance.ad.constant.ErrorCode;
import com.huawei.openalliance.ad.constant.EventType;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.db.bean.DeletedContentRecord;
import com.huawei.openalliance.ad.es;
import com.huawei.openalliance.ad.ew;
import com.huawei.openalliance.ad.fh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.hy;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;
import com.huawei.openalliance.ad.magazine.inter.listener.MagLockListener;
import com.huawei.openalliance.ad.ol;
import com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager;
import com.huawei.openalliance.ad.ou;
import com.huawei.openalliance.ad.pa;
import com.huawei.openalliance.ad.pb;
import com.huawei.openalliance.ad.pg;
import com.huawei.openalliance.ad.processor.eventbeans.MagLockEventInfo;
import com.huawei.openalliance.ad.processor.eventbeans.a;
import com.huawei.openalliance.ad.processor.eventbeans.b;
import com.huawei.openalliance.ad.qe;
import com.huawei.openalliance.ad.qq;
import com.huawei.openalliance.ad.sc;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.bg;
import com.huawei.openalliance.ad.utils.bv;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.d;
import com.huawei.openalliance.ad.utils.m;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes9.dex */
public class a implements HiAdMagLock {

    /* renamed from: a, reason: collision with root package name */
    private static int[] f7195a = {200, 206};
    private static int[] b = {204, 423, 424, 425, ErrorCode.ERROR_CODE_NO_PACKAGE, ErrorCode.ERROR_CODE_NO_USERINFO};
    private static int[] c = {200, 421, 423};
    private Context d;
    private es e;
    private ew f;

    @Override // com.huawei.openalliance.ad.magazine.inter.HiAdMagLock
    public void reportEvent(final Context context, final String str, final EventType eventType, final MagLockEventInfo magLockEventInfo) {
        if (cz.b(str) || eventType == null) {
            ho.d("HiAdMagLockImpl", "params is null");
        } else {
            m.b(new Runnable() { // from class: com.huawei.openalliance.ad.magazine.inter.a.1
                @Override // java.lang.Runnable
                public void run() {
                    String str2;
                    EventType valueOf = EventType.valueOf(eventType.name());
                    ContentRecord a2 = es.a(context).a(str);
                    if (a2 == null) {
                        str2 = "reportEvent,content is null";
                    } else {
                        if (2 == a2.e()) {
                            if (EventType.SHOW == valueOf) {
                                fh.b(context).g("" + ao.c());
                            }
                            if (EventType.REMOVE == valueOf) {
                                ew.a(context).b(new DeletedContentRecord(str, ao.c()));
                                ew.a(context).b();
                                es.a(context).c(str);
                            }
                            a2.c(fh.b(context).aN());
                            Context context2 = context;
                            ou ouVar = new ou(context2, sc.a(context2, a2.e()));
                            ouVar.a(a2);
                            a.this.a(ouVar, valueOf, magLockEventInfo);
                            MagLockEventInfo magLockEventInfo2 = magLockEventInfo;
                            if (magLockEventInfo2 == null || magLockEventInfo2.b() == null) {
                                return;
                            }
                            new c(context).b(a2, eventType.value(), magLockEventInfo.b());
                            return;
                        }
                        str2 = "invalid ad type";
                    }
                    ho.d("HiAdMagLockImpl", str2);
                }
            });
        }
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.HiAdMagLock
    public void preloadAds(String[] strArr, Set<String> set, boolean z, MagLockListener magLockListener) {
        ho.a("HiAdMagLockImpl", "preloadAds!");
        a(strArr, a(set), z, magLockListener, 2);
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.HiAdMagLock
    public void onNetworkConnected(Context context) {
        a(context);
    }

    @Override // com.huawei.openalliance.ad.magazine.inter.HiAdMagLock
    public void loadAds(String[] strArr, Set<String> set, boolean z, MagLockListener magLockListener) {
        ho.a("HiAdMagLockImpl", "loadAds!");
        a(strArr, a(set), z, magLockListener, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(final Context context, final AdSlotParam adSlotParam, final List<String> list, final List<String> list2, final MagLockListener magLockListener) {
        ho.a("HiAdMagLockImpl", "oaid has been done: %s", Long.valueOf(System.currentTimeMillis()));
        m.b(new Runnable() { // from class: com.huawei.openalliance.ad.magazine.inter.a.3
            @Override // java.lang.Runnable
            public void run() {
                if (ho.a()) {
                    ho.a("HiAdMagLockImpl", "thread switch: %s", Long.valueOf(System.currentTimeMillis()));
                }
                a.b(context, new ol(context).a(adSlotParam, list, list2), magLockListener, adSlotParam.j());
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean b(com.huawei.openalliance.ad.magazine.inter.listener.MagLockListener r6, java.lang.String[] r7) {
        /*
            r5 = this;
            java.lang.String r0 = "HiAdMagLockImpl"
            r1 = 0
            if (r7 == 0) goto L1d
            int r2 = r7.length
            if (r2 != 0) goto L9
            goto L1d
        L9:
            int r2 = r7.length
            r3 = r1
        Lb:
            if (r3 >= r2) goto L1b
            r4 = r7[r3]
            boolean r4 = com.huawei.openalliance.ad.utils.cz.b(r4)
            if (r4 == 0) goto L18
            java.lang.String r7 = "some adId is empty"
            goto L1f
        L18:
            int r3 = r3 + 1
            goto Lb
        L1b:
            r7 = 1
            goto L23
        L1d:
            java.lang.String r7 = "adIds is empty"
        L1f:
            com.huawei.openalliance.ad.ho.d(r0, r7)
            r7 = r1
        L23:
            if (r6 != 0) goto L2b
            java.lang.String r6 = "listener is null"
            com.huawei.openalliance.ad.ho.d(r0, r6)
            goto L2c
        L2b:
            r1 = r7
        L2c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.magazine.inter.a.b(com.huawei.openalliance.ad.magazine.inter.listener.MagLockListener, java.lang.String[]):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, AdContentRsp adContentRsp, MagLockListener magLockListener, boolean z) {
        if (adContentRsp == null) {
            ho.b("HiAdMagLockImpl", "onAdFailed response is null!");
            magLockListener.onAdFailed(ErrorCode.ERROR_CODE_OTHER);
            return;
        }
        ArrayList arrayList = new ArrayList();
        List<ContentRecord> a2 = pg.a(adContentRsp, 2);
        if (!bg.a(a2)) {
            arrayList.addAll(a2);
        }
        List<ContentRecord> b2 = pg.b(adContentRsp, 2);
        if (!bg.a(b2)) {
            arrayList.addAll(b2);
        }
        if (ho.a() && ho.a()) {
            Iterator it = arrayList.iterator();
            String str = "[";
            while (it.hasNext()) {
                str = str + ((ContentRecord) it.next()).m() + " ";
            }
            ho.a("HiAdMagLockImpl", "received contentIds: %s", str + "]");
        }
        pa paVar = new pa(context, arrayList);
        paVar.a(2);
        paVar.a(adContentRsp.d());
        paVar.a();
        if (z) {
            paVar.a(true);
        }
        int a3 = adContentRsp.a();
        if (Arrays.binarySearch(f7195a, a3) >= 0) {
            hy a4 = pb.a(adContentRsp, context, z);
            if (a4 == null) {
                ho.c("HiAdMagLockImpl", "onAdSuccess but MagLockAdInfo is null");
            } else {
                ho.b("HiAdMagLockImpl", "onAdSuccess MagLockAdInfo: %s", a4.toString());
            }
            magLockListener.onAdSuccess(a4);
        } else if (Arrays.binarySearch(b, a3) >= 0) {
            ho.c("HiAdMagLockImpl", "onAdFailed errorCode: %s", Integer.valueOf(a3));
            magLockListener.onAdFailed(a3);
        } else {
            ho.c("HiAdMagLockImpl", "onAdFailed errorCode: %s", Integer.valueOf(ErrorCode.ERROR_CODE_OTHER));
            magLockListener.onAdFailed(ErrorCode.ERROR_CODE_OTHER);
        }
        ho.a("HiAdMagLockImpl", "load ads finish: %s", Long.valueOf(System.currentTimeMillis()));
        if (z) {
            return;
        }
        paVar.a(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, AdSlotParam adSlotParam, List<String> list, List<String> list2, MagLockListener magLockListener) {
        OAIDServiceManager.getInstance(context).requireOaid(new C0198a(context, adSlotParam, list, list2, magLockListener));
    }

    private boolean a(MagLockListener magLockListener, String[] strArr) {
        if (ao.j()) {
            return b(magLockListener, strArr);
        }
        if (magLockListener == null) {
            return false;
        }
        ho.c("HiAdMagLockImpl", "api too low! onNoSupport");
        magLockListener.onNoSupport();
        return false;
    }

    private void a(final String[] strArr, final List<String> list, final boolean z, final MagLockListener magLockListener, final int i) {
        if (a(magLockListener, strArr)) {
            if (bv.e(this.d)) {
                m.b(new Runnable() { // from class: com.huawei.openalliance.ad.magazine.inter.a.2
                    @Override // java.lang.Runnable
                    public void run() {
                        AdSlotParam.Builder builder = new AdSlotParam.Builder();
                        builder.setAdIds(Arrays.asList(strArr)).setDeviceType(4).setOrientation(1).setWidth(d.d(a.this.d)).setHeight(d.e(a.this.d)).setTest(z).setIsPreload(Boolean.valueOf(i == 2)).setRequestOptions(HiAd.getInstance(a.this.d).getRequestConfiguration());
                        List<String> a2 = a.this.f.a();
                        ho.a("HiAdMagLockImpl", "ready to loadAds , removedIds: %s,cachedContentIds: %s", a2, list);
                        a aVar = a.this;
                        aVar.b(aVar.d, builder.build(), list, a2, magLockListener);
                        a.this.f.b();
                    }
                });
            } else {
                ho.c("HiAdMagLockImpl", "onAdFailed netWork not Connected!");
                magLockListener.onAdFailed(ErrorCode.ERROR_CODE_OTHER);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(qq qqVar, EventType eventType, MagLockEventInfo magLockEventInfo) {
        switch (AnonymousClass4.f7199a[eventType.ordinal()]) {
            case 1:
                a.C0207a c0207a = new a.C0207a();
                c0207a.a(false).c((Integer) 0).b(true);
                qqVar.a(c0207a.a());
                break;
            case 2:
                MaterialClickInfo materialClickInfo = new MaterialClickInfo();
                materialClickInfo.f(1);
                b.a aVar = new b.a();
                aVar.a(0).b(0).b((String) null).b(true).a(materialClickInfo);
                qqVar.a(aVar.a());
                break;
            case 3:
                qqVar.i();
                break;
            case 4:
                qqVar.j();
                break;
            case 5:
                qqVar.k();
                break;
            case 6:
                qqVar.l();
                break;
            case 7:
                qqVar.m();
                break;
            case 8:
                qqVar.a(EventType.INTENTSUCCESS, (Integer) 1, (Integer) null, false);
                break;
            case 9:
                qqVar.a(EventType.INTENTFAIL, (Integer) 1, magLockEventInfo.a(), false);
                break;
        }
    }

    private static void a(Context context, int i) {
        qe.a(context, sc.a(context, 2), i).f();
    }

    private static void a(Context context) {
        a(context, 1);
        a(context, 2);
        a(context, 4);
        a(context, 3);
    }

    private List<String> a(Set<String> set) {
        if (set == null) {
            ho.a("HiAdMagLockImpl", "contentIds is null");
            return new ArrayList();
        }
        ho.a("HiAdMagLockImpl", "contentIds is %s", set.toString());
        return new ArrayList(set);
    }

    /* renamed from: com.huawei.openalliance.ad.magazine.inter.a$a, reason: collision with other inner class name */
    static class C0198a extends OAIDServiceManager.OaidResultCallback {

        /* renamed from: a, reason: collision with root package name */
        private Context f7200a;
        private AdSlotParam b;
        private List<String> c;
        private List<String> d;
        private MagLockListener e;

        @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
        public void a(String str, boolean z) {
            ho.b("HiAdMagLockImpl", "onOaidAcquired");
            this.b.a(str);
            this.b.a(Boolean.valueOf(z));
            a.c(this.f7200a, this.b, this.c, this.d, this.e);
        }

        @Override // com.huawei.openalliance.ad.opendeviceidentifier.OAIDServiceManager.OaidResultCallback
        public void a() {
            ho.b("HiAdMagLockImpl", "onOaidAcquireFailed");
            a.c(this.f7200a, this.b, this.c, this.d, this.e);
        }

        public C0198a(Context context, AdSlotParam adSlotParam, List<String> list, List<String> list2, MagLockListener magLockListener) {
            this.f7200a = context.getApplicationContext();
            this.b = adSlotParam;
            this.c = list;
            this.d = list2;
            this.e = magLockListener;
        }
    }

    a(Context context) {
        Arrays.sort(f7195a);
        Arrays.sort(b);
        Arrays.sort(c);
        Context applicationContext = context.getApplicationContext();
        this.d = applicationContext;
        this.e = es.a(applicationContext);
        this.f = ew.a(this.d);
    }

    /* renamed from: com.huawei.openalliance.ad.magazine.inter.a$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f7199a;

        static {
            int[] iArr = new int[EventType.values().length];
            f7199a = iArr;
            try {
                iArr[EventType.SHOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7199a[EventType.CLICK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7199a[EventType.SWIPEUP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7199a[EventType.REMOVE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7199a[EventType.SHARE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f7199a[EventType.FAVORITE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f7199a[EventType.SHOWEND.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f7199a[EventType.INTENTSUCCESS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f7199a[EventType.INTENTFAIL.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    a() {
        Arrays.sort(f7195a);
        Arrays.sort(b);
        Arrays.sort(c);
    }
}
