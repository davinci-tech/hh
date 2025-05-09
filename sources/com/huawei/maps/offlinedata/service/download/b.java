package com.huawei.maps.offlinedata.service.download;

import android.text.TextUtils;
import com.huawei.maps.offlinedata.handler.dto.DownloadRequest;
import com.huawei.maps.offlinedata.handler.dto.persist.MapOfflineDataItemEntity;
import com.huawei.maps.offlinedata.handler.dto.persist.OfflineDataTaskEntity;
import com.huawei.maps.offlinedata.service.cloud.c;
import com.huawei.maps.offlinedata.utils.g;
import com.huawei.openalliance.ad.constant.VastAttribute;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes5.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static volatile b f6502a;
    private final Set<OfflineDataTaskEntity> b = new CopyOnWriteArraySet();
    private final List<OfflineDataTaskEntity> c = new CopyOnWriteArrayList();
    private final AtomicBoolean d = new AtomicBoolean(false);

    private b() {
    }

    public static b a() {
        if (f6502a == null) {
            synchronized (b.class) {
                if (f6502a == null) {
                    f6502a = new b();
                }
            }
        }
        return f6502a;
    }

    public AtomicBoolean b() {
        return this.d;
    }

    public Set<OfflineDataTaskEntity> c() {
        return this.b;
    }

    public List<OfflineDataTaskEntity> d() {
        return this.c;
    }

    public void e() {
        this.c.clear();
    }

    public void a(OfflineDataTaskEntity offlineDataTaskEntity) {
        g.a("OfflineMapDownloadManager", "startOrResumeTask: " + offlineDataTaskEntity.getPrintId());
        MapOfflineDataItemEntity a2 = com.huawei.maps.offlinedata.service.persist.b.a().a(offlineDataTaskEntity.getDataType(), offlineDataTaskEntity.getCityId(), offlineDataTaskEntity.getVersion());
        if (a2 == null) {
            g.c("OfflineMapDownloadManager", "the task related item not find in db.");
            return;
        }
        com.huawei.maps.offlinedata.service.persist.b.a().a(offlineDataTaskEntity);
        if (!c(offlineDataTaskEntity)) {
            this.c.add(offlineDataTaskEntity);
            g.a("OfflineMapDownloadManager", "the process list is full. the task will be added to waiting list. the current list size is " + this.c.size());
            return;
        }
        if (offlineDataTaskEntity.getTaskState().contains(VastAttribute.ERROR) || offlineDataTaskEntity.getTaskState().equals("\"finished\"")) {
            g.a("OfflineMapDownloadManager", "the task is finished or failed. clear the last taskId.");
            offlineDataTaskEntity.setTaskId(null);
        }
        String fileId = a2.getFileId();
        String substring = fileId.substring(fileId.lastIndexOf(47));
        String fileCheck = a2.getFileCheck();
        String b = c.a().b(fileId);
        if (!a().b().get()) {
            a(offlineDataTaskEntity, substring, b, fileCheck);
        } else {
            g.c("OfflineMapDownloadManager", "the network is change after get download url.");
        }
    }

    private void a(OfflineDataTaskEntity offlineDataTaskEntity, String str, String str2, String str3) {
        com.huawei.maps.offlinedata.service.network.b.a().b();
        if (TextUtils.isEmpty(offlineDataTaskEntity.getTaskId()) || !a.a().a(Long.parseLong(offlineDataTaskEntity.getTaskId()), offlineDataTaskEntity)) {
            String a2 = a.a().a(new DownloadRequest.Builder().downloadUri(str2).filePath(a(str)).sha256(str3).build(), offlineDataTaskEntity);
            if (TextUtils.isEmpty(a2)) {
                g.c("OfflineMapDownloadManager", "failed to obtain the task id.");
                offlineDataTaskEntity.setTaskState("{\"download\":\"error\"}");
                com.huawei.maps.offlinedata.service.persist.b.a().b(offlineDataTaskEntity.getId(), offlineDataTaskEntity.getItemState(), offlineDataTaskEntity.getTaskState());
                this.b.remove(offlineDataTaskEntity);
                f();
                return;
            }
            offlineDataTaskEntity.setTaskId(a2);
            com.huawei.maps.offlinedata.service.persist.b.a().a(offlineDataTaskEntity);
        }
    }

    private String a(String str) {
        return com.huawei.maps.offlinedata.service.storage.a.a().d() + str;
    }

    private boolean c(OfflineDataTaskEntity offlineDataTaskEntity) {
        HashSet hashSet = new HashSet();
        Iterator<OfflineDataTaskEntity> it = this.b.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getIndexId());
        }
        if (!hashSet.contains(offlineDataTaskEntity.getIndexId()) && hashSet.size() >= 3) {
            return false;
        }
        this.b.add(offlineDataTaskEntity);
        return true;
    }

    public void f() {
        new Thread(new Runnable() { // from class: com.huawei.maps.offlinedata.service.download.b$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                b.this.h();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h() {
        try {
            synchronized (b.class) {
                g.a("OfflineMapDownloadManager", "will start task from the waiting list.");
                if (this.c.isEmpty()) {
                    g.a("OfflineMapDownloadManager", "the waiting list is empty.");
                    return;
                }
                for (OfflineDataTaskEntity offlineDataTaskEntity : this.c) {
                    if (!c(offlineDataTaskEntity)) {
                        break;
                    }
                    this.c.remove(offlineDataTaskEntity);
                    g.a("OfflineMapDownloadManager", "current the waiting list size is " + this.c.size());
                    a(offlineDataTaskEntity);
                }
            }
        } catch (Exception e) {
            g.d("OfflineMapDownloadManager", VastAttribute.ERROR + e.getMessage());
        }
    }

    public void b(OfflineDataTaskEntity offlineDataTaskEntity) {
        if (this.b.contains(offlineDataTaskEntity) || this.c.contains(offlineDataTaskEntity)) {
            g.c("OfflineMapDownloadManager", "the task is contains.");
        } else {
            offlineDataTaskEntity.setTaskState("{\"download\":\"queue\"}");
            a(offlineDataTaskEntity);
        }
    }

    public void g() {
        g.a("OfflineMapDownloadManager", "will pause all download task.");
        this.d.set(true);
        a.a().b();
        ArrayList<OfflineDataTaskEntity> arrayList = new ArrayList();
        arrayList.addAll(this.c);
        arrayList.addAll(this.b);
        g.a("OfflineMapDownloadManager", "the task size is " + arrayList.size());
        for (OfflineDataTaskEntity offlineDataTaskEntity : arrayList) {
            offlineDataTaskEntity.setTaskState("{\"download\":\"pause\"}");
            com.huawei.maps.offlinedata.service.persist.b.a().b(offlineDataTaskEntity.getId(), offlineDataTaskEntity.getItemState(), offlineDataTaskEntity.getTaskState());
        }
        this.c.clear();
        this.b.clear();
    }
}
