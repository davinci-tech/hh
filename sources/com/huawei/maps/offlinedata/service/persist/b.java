package com.huawei.maps.offlinedata.service.persist;

import android.text.TextUtils;
import com.huawei.maps.offlinedata.handler.dto.persist.MapOfflineDataItemEntity;
import com.huawei.maps.offlinedata.handler.dto.persist.OfflineDataTaskEntity;
import com.huawei.maps.offlinedata.handler.dto.persist.WorldMapOfflineDataItemEntity;
import com.huawei.maps.offlinedata.service.persist.dao.c;
import com.huawei.maps.offlinedata.service.persist.dao.e;
import com.huawei.maps.offlinedata.utils.g;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/* loaded from: classes5.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static volatile b f6511a;
    private final ThreadPoolExecutor b = a.a().b();
    private final com.huawei.maps.offlinedata.service.persist.dao.a c = OfflineMapDatabase.a(com.huawei.maps.offlinedata.utils.a.a()).a();
    private final e e = OfflineMapDatabase.a(com.huawei.maps.offlinedata.utils.a.a()).c();
    private final c d = OfflineMapDatabase.a(com.huawei.maps.offlinedata.utils.a.a()).b();

    private b() {
    }

    public static b a() {
        if (f6511a == null) {
            synchronized (b.class) {
                if (f6511a == null) {
                    f6511a = new b();
                }
            }
        }
        return f6511a;
    }

    public void b() {
        c();
        d();
        e();
    }

    public void a(final WorldMapOfflineDataItemEntity worldMapOfflineDataItemEntity) {
        this.b.execute(new Runnable() { // from class: com.huawei.maps.offlinedata.service.persist.b$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                b.this.b(worldMapOfflineDataItemEntity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(WorldMapOfflineDataItemEntity worldMapOfflineDataItemEntity) {
        try {
            this.e.a(worldMapOfflineDataItemEntity);
        } catch (Exception e) {
            g.d("RepositoryManager", "deleteAllWorldMapDataItems failed , e:" + e.getMessage());
        }
    }

    public void c() {
        this.b.execute(new Runnable() { // from class: com.huawei.maps.offlinedata.service.persist.b$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                b.this.h();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h() {
        try {
            this.e.b();
        } catch (Exception e) {
            g.d("RepositoryManager", "deleteAllWorldMapDataItems failed , e:" + e.getMessage());
        }
    }

    public void a(final com.huawei.maps.offlinedata.utils.b<List<WorldMapOfflineDataItemEntity>> bVar) {
        this.b.execute(new Runnable() { // from class: com.huawei.maps.offlinedata.service.persist.b$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                b.this.h(bVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(com.huawei.maps.offlinedata.utils.b bVar) {
        try {
            bVar.accept(this.e.a());
        } catch (Exception e) {
            g.d("RepositoryManager", "getWorldMapDataItemList failed , e:" + e.getMessage());
        }
    }

    public void a(final List<MapOfflineDataItemEntity> list) {
        this.b.execute(new Runnable() { // from class: com.huawei.maps.offlinedata.service.persist.b$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                b.this.b(list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(List list) {
        try {
            this.c.a((List<MapOfflineDataItemEntity>) list);
        } catch (Exception e) {
            g.d("RepositoryManager", "addMapDataItems failed , e:" + e.getMessage());
        }
    }

    public void a(final String str, final List<MapOfflineDataItemEntity> list, final Runnable runnable) {
        this.b.execute(new Runnable() { // from class: com.huawei.maps.offlinedata.service.persist.b$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                b.this.b(str, list, runnable);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(String str, List list, Runnable runnable) {
        try {
            this.c.a(str);
            this.c.a((List<MapOfflineDataItemEntity>) list);
            runnable.run();
        } catch (Exception e) {
            g.d("RepositoryManager", "addMapDataItems failed , e:" + e.getMessage());
        }
    }

    public void d() {
        this.b.execute(new Runnable() { // from class: com.huawei.maps.offlinedata.service.persist.b$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                b.this.g();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g() {
        try {
            this.c.b();
        } catch (Exception e) {
            g.d("RepositoryManager", "deleteAllItems failed , e:" + e.getMessage());
        }
    }

    public void b(final com.huawei.maps.offlinedata.utils.b<List<MapOfflineDataItemEntity>> bVar) {
        this.b.execute(new Runnable() { // from class: com.huawei.maps.offlinedata.service.persist.b$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                b.this.g(bVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(com.huawei.maps.offlinedata.utils.b bVar) {
        try {
            bVar.accept(this.c.a());
        } catch (Exception e) {
            g.d("RepositoryManager", "deleteAllItems failed , e:" + e.getMessage());
        }
    }

    public MapOfflineDataItemEntity a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2)) {
            List<WorldMapOfflineDataItemEntity> a2 = this.e.a();
            if (a2 == null || a2.isEmpty()) {
                return null;
            }
            return new MapOfflineDataItemEntity(a2.get(0));
        }
        return this.c.a(str2, str, str3);
    }

    public void a(final OfflineDataTaskEntity offlineDataTaskEntity) {
        this.b.execute(new Runnable() { // from class: com.huawei.maps.offlinedata.service.persist.b$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                b.this.b(offlineDataTaskEntity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(OfflineDataTaskEntity offlineDataTaskEntity) {
        try {
            this.d.a(offlineDataTaskEntity);
        } catch (Exception e) {
            g.d("RepositoryManager", "addDataTask failed , e:" + e.getMessage());
        }
    }

    public void a(final String str) {
        this.b.execute(new Runnable() { // from class: com.huawei.maps.offlinedata.service.persist.b$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                b.this.c(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(String str) {
        try {
            this.d.a(str);
        } catch (Exception e) {
            g.d("RepositoryManager", "deleteDataTask failed , e:" + e.getMessage());
        }
    }

    public void a(final String str, final int i, final int i2) {
        this.b.execute(new Runnable() { // from class: com.huawei.maps.offlinedata.service.persist.b$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                b.this.b(str, i, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(String str, int i, int i2) {
        try {
            this.d.a(str, i, i2);
        } catch (Exception e) {
            g.d("RepositoryManager", "updateProgress failed , e:" + e.getMessage());
        }
    }

    public void b(final String str, final String str2, final String str3) {
        this.b.execute(new Runnable() { // from class: com.huawei.maps.offlinedata.service.persist.b$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                b.this.c(str, str2, str3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(String str, String str2, String str3) {
        try {
            this.d.a(str, str2, str3);
        } catch (Exception e) {
            g.d("RepositoryManager", "updateState failed , e:" + e.getMessage());
        }
    }

    public void c(final com.huawei.maps.offlinedata.utils.b<List<OfflineDataTaskEntity>> bVar) {
        this.b.execute(new Runnable() { // from class: com.huawei.maps.offlinedata.service.persist.b$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                b.this.f(bVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(com.huawei.maps.offlinedata.utils.b bVar) {
        try {
            bVar.accept(this.d.a());
        } catch (Exception e) {
            g.d("RepositoryManager", "getDataTaskList failed , e:" + e.getMessage());
        }
    }

    public OfflineDataTaskEntity a(String str, String str2) {
        return this.d.a(str2, str);
    }

    public void e() {
        this.b.execute(new Runnable() { // from class: com.huawei.maps.offlinedata.service.persist.b$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                b.this.f();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f() {
        try {
            this.d.b();
        } catch (Exception e) {
            g.d("RepositoryManager", "deleteAllTasks failed , e:" + e.getMessage());
        }
    }

    public OfflineDataTaskEntity b(String str) {
        try {
            return this.d.b(str);
        } catch (Exception e) {
            g.d("RepositoryManager", "getOfflineDataTaskById failed , e:" + e.getMessage());
            return null;
        }
    }

    public void d(final com.huawei.maps.offlinedata.utils.b<List<OfflineDataTaskEntity>> bVar) {
        this.b.execute(new Runnable() { // from class: com.huawei.maps.offlinedata.service.persist.b$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                b.this.e(bVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(com.huawei.maps.offlinedata.utils.b bVar) {
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add("processing");
            arrayList.add("updating");
            bVar.accept(this.d.a(arrayList));
        } catch (Exception e) {
            g.d("RepositoryManager", "queryOfflineDataDoingTasks failed , e:" + e.getMessage());
        }
    }
}
