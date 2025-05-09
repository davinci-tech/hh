package com.huawei.maps.offlinedata.service.device;

import android.text.TextUtils;
import com.huawei.maps.offlinedata.handler.dto.device.TransmitRequest;
import com.huawei.maps.offlinedata.handler.dto.persist.MapOfflineDataItemEntity;
import com.huawei.maps.offlinedata.handler.dto.persist.OfflineDataTaskEntity;
import com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapPingCallback;
import com.huawei.maps.offlinedata.logpush.dto.a;
import com.huawei.maps.offlinedata.utils.g;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private static volatile f f6488a;
    private final AtomicReference<OfflineDataTaskEntity> b = new AtomicReference<>(null);
    private final List<OfflineDataTaskEntity> c = new CopyOnWriteArrayList();
    private boolean d = true;

    private f() {
    }

    public static f a() {
        if (f6488a == null) {
            synchronized (f.class) {
                if (f6488a == null) {
                    f6488a = new f();
                }
            }
        }
        return f6488a;
    }

    public void b() {
        this.c.clear();
    }

    public AtomicReference<OfflineDataTaskEntity> c() {
        return this.b;
    }

    public void a(OfflineDataTaskEntity offlineDataTaskEntity) {
        this.d = false;
        this.b.set(offlineDataTaskEntity);
    }

    public void b(OfflineDataTaskEntity offlineDataTaskEntity) {
        g.a("OfflineMapTransmitManager", "transmitMap " + offlineDataTaskEntity.getPrintId());
        offlineDataTaskEntity.setTaskState("{\"transmit\":\"queue\"}");
        if (offlineDataTaskEntity.equals(this.b.get()) || this.c.contains(offlineDataTaskEntity)) {
            g.c("OfflineMapTransmitManager", "the task contains.");
            return;
        }
        MapOfflineDataItemEntity a2 = com.huawei.maps.offlinedata.service.persist.b.a().a(offlineDataTaskEntity.getDataType(), offlineDataTaskEntity.getCityId(), offlineDataTaskEntity.getVersion());
        if (a2 == null) {
            g.c("OfflineMapTransmitManager", "the task related item not find in db.");
            return;
        }
        if (this.b.get() != null) {
            this.c.add(offlineDataTaskEntity);
            g.a("OfflineMapTransmitManager", "A task is processing. the task will be added to waiting list. the current list size is " + this.c.size());
            return;
        }
        this.d = true;
        this.b.set(offlineDataTaskEntity);
        TransmitRequest transmitRequest = new TransmitRequest();
        String taskId = offlineDataTaskEntity.getTaskId();
        transmitRequest.setRequestId(TextUtils.isEmpty(taskId) ? 0L : Long.parseLong(taskId));
        transmitRequest.setMapType(offlineDataTaskEntity.getMapType());
        String fileId = a2.getFileId();
        transmitRequest.setFileDecompressDir(c(fileId.substring(fileId.lastIndexOf(47))));
        transmitRequest.setSyncType(TextUtils.equals(offlineDataTaskEntity.getItemState(), "updating") ? 1 : 0);
        transmitRequest.setMapSize((int) Double.parseDouble(a2.getOriginalSize()));
        offlineDataTaskEntity.setTaskState("{\"transmit\":\"doing\"}");
        a(transmitRequest, 0L);
    }

    public void a(final TransmitRequest transmitRequest, final long j) {
        g.d("OfflineMapTransmitManager", "the transmit file id is " + new File(transmitRequest.getFileDecompressDir()).getName());
        d.a().a(new IOfflineMapPingCallback() { // from class: com.huawei.maps.offlinedata.service.device.f$$ExternalSyntheticLambda1
            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapPingCallback
            public final void onPingResult(int i) {
                f.this.a(j, transmitRequest, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(long j, TransmitRequest transmitRequest, int i) {
        g.a("OfflineMapTransmitManager", "onPingResult code:" + i);
        if (i == 202) {
            if (j != 0) {
                com.huawei.maps.offlinedata.jsbridge.a.a().a(true, j);
            }
            if (!transmitRequest.getFileNameInFileDecompressDir()) {
                g.d("OfflineMapTransmitManager", "transmitMap: fileDecompressDir invalid");
            }
            g.a("OfflineMapTransmitManager", "transmitFile request:" + transmitRequest);
            d.a().a(transmitRequest);
            return;
        }
        if (j != 0) {
            com.huawei.maps.offlinedata.jsbridge.a.a().a(-1, "device is not available", j);
        }
        g.d("OfflineMapTransmitManager", "device is not available");
        this.b.get().setTaskState("{\"transmit\":\"error\"}");
        this.b.set(null);
    }

    private String c(String str) {
        return com.huawei.maps.offlinedata.service.storage.a.a().e() + str;
    }

    private void c(final OfflineDataTaskEntity offlineDataTaskEntity) {
        new Thread(new Runnable() { // from class: com.huawei.maps.offlinedata.service.device.f$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                f.this.d(offlineDataTaskEntity);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(OfflineDataTaskEntity offlineDataTaskEntity) {
        g.a("OfflineMapTransmitManager", "will start task from the waiting list.");
        this.b.set(null);
        if (this.c.isEmpty()) {
            g.a("OfflineMapTransmitManager", "the waiting list is empty.");
            return;
        }
        for (OfflineDataTaskEntity offlineDataTaskEntity2 : this.c) {
            if (TextUtils.equals(offlineDataTaskEntity.getIndexId(), offlineDataTaskEntity2.getIndexId())) {
                g.a("OfflineMapTransmitManager", "same city diff dataType firstly.");
                this.c.remove(offlineDataTaskEntity2);
                b(offlineDataTaskEntity2);
                return;
            }
        }
        b(this.c.remove(0));
    }

    public void a(String str, String str2) {
        if (!com.huawei.maps.offlinedata.service.a.a().c()) {
            this.d = true;
        }
        if (!this.d) {
            com.huawei.maps.offlinedata.jsbridge.b.a().b("deviceClient.progress", str, str2);
            return;
        }
        OfflineDataTaskEntity offlineDataTaskEntity = this.b.get();
        offlineDataTaskEntity.setTaskState("{\"transmit\":\"doing\"}");
        offlineDataTaskEntity.setTransmitProgress(Integer.valueOf(str2));
        com.huawei.maps.offlinedata.service.persist.b.a().a(offlineDataTaskEntity.getId(), offlineDataTaskEntity.getDownloadProgress().intValue(), offlineDataTaskEntity.getTransmitProgress().intValue());
        com.huawei.maps.offlinedata.jsbridge.b.a().b("syncData", com.huawei.maps.offlinedata.utils.d.a(offlineDataTaskEntity));
    }

    public void a(String str) {
        com.huawei.maps.offlinedata.logpush.dto.a c = a.C0167a.b().a("transmit").c();
        c.b("0");
        com.huawei.maps.offlinedata.logpush.b.a(c);
        if (!com.huawei.maps.offlinedata.service.a.a().c()) {
            this.d = true;
        }
        OfflineDataTaskEntity offlineDataTaskEntity = this.b.get();
        this.b.set(null);
        if (!this.d) {
            com.huawei.maps.offlinedata.jsbridge.b.a().b("deviceClient.success", String.valueOf(str));
            return;
        }
        Iterator<OfflineDataTaskEntity> it = this.c.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(it.next().getIndexId(), offlineDataTaskEntity.getIndexId())) {
                offlineDataTaskEntity.setTaskState("{\"transmit\":\"wait\"}");
            }
        }
        if (!TextUtils.equals(offlineDataTaskEntity.getTaskState(), "{\"transmit\":\"wait\"}")) {
            offlineDataTaskEntity.setTaskState("\"finished\"");
            offlineDataTaskEntity.setItemState("used");
        }
        com.huawei.maps.offlinedata.service.persist.b.a().a(offlineDataTaskEntity.getId(), 100, 100);
        com.huawei.maps.offlinedata.service.persist.b.a().b(offlineDataTaskEntity.getId(), offlineDataTaskEntity.getItemState(), offlineDataTaskEntity.getTaskState());
        com.huawei.maps.offlinedata.jsbridge.b.a().b("syncData", com.huawei.maps.offlinedata.utils.d.a(offlineDataTaskEntity));
        if (com.huawei.maps.offlinedata.service.a.a().c()) {
            return;
        }
        c(offlineDataTaskEntity);
    }

    public void b(String str) {
        com.huawei.maps.offlinedata.logpush.dto.a c = a.C0167a.b().a("transmit").c();
        c.b("060016");
        com.huawei.maps.offlinedata.logpush.b.a(c);
        if (!com.huawei.maps.offlinedata.service.a.a().c()) {
            this.d = true;
        }
        OfflineDataTaskEntity offlineDataTaskEntity = this.b.get();
        this.b.set(null);
        if (!this.d) {
            com.huawei.maps.offlinedata.jsbridge.b.a().b("deviceClient.error", String.valueOf(str));
            return;
        }
        offlineDataTaskEntity.setTaskState("{\"transmit\":\"error\"}");
        for (OfflineDataTaskEntity offlineDataTaskEntity2 : this.c) {
            if (TextUtils.equals(offlineDataTaskEntity2.getIndexId(), offlineDataTaskEntity.getIndexId())) {
                this.c.remove(offlineDataTaskEntity2);
            }
        }
        com.huawei.maps.offlinedata.service.persist.b.a().b(offlineDataTaskEntity.getId(), offlineDataTaskEntity.getItemState(), offlineDataTaskEntity.getTaskState());
        com.huawei.maps.offlinedata.jsbridge.b.a().b("syncData", com.huawei.maps.offlinedata.utils.d.a(offlineDataTaskEntity));
        if (com.huawei.maps.offlinedata.service.a.a().c()) {
            return;
        }
        c(offlineDataTaskEntity);
    }
}
