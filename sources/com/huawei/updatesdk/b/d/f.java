package com.huawei.updatesdk.b.d;

/* loaded from: classes7.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private static com.huawei.updatesdk.service.otaupdate.b f10835a;

    static class a implements com.huawei.updatesdk.a.b.c.c.a {

        /* renamed from: a, reason: collision with root package name */
        private final boolean f10836a;

        @Override // com.huawei.updatesdk.a.b.c.c.a
        public void a(com.huawei.updatesdk.a.b.c.c.c cVar, com.huawei.updatesdk.a.b.c.c.d dVar) {
        }

        @Override // com.huawei.updatesdk.a.b.c.c.a
        public void b(com.huawei.updatesdk.a.b.c.c.c cVar, com.huawei.updatesdk.a.b.c.c.d dVar) {
            if (f.f10835a == null) {
                return;
            }
            if (!(dVar instanceof com.huawei.updatesdk.service.appmgr.bean.b)) {
                f.f10835a.a(dVar.d());
                return;
            }
            com.huawei.updatesdk.service.appmgr.bean.b bVar = (com.huawei.updatesdk.service.appmgr.bean.b) dVar;
            if (!bVar.e()) {
                f.f10835a.b(dVar.d());
                return;
            }
            com.huawei.updatesdk.service.appmgr.bean.c f = bVar.f();
            if (f == null) {
                f.f10835a.a(dVar.d());
                return;
            }
            if (!this.f10836a) {
                com.huawei.updatesdk.b.e.e.a(false).a(f.d());
            }
            f.f10835a.a(f);
        }

        a(boolean z) {
            this.f10836a = z;
        }
    }

    public static void a(boolean z) {
        com.huawei.updatesdk.b.g.b.a(new com.huawei.updatesdk.service.appmgr.bean.a(z ? com.huawei.updatesdk.service.otaupdate.f.e().c() : null), new a(z));
    }

    public static void a(com.huawei.updatesdk.service.otaupdate.b bVar) {
        f10835a = bVar;
    }
}
