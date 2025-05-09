package com.huawei.maps.offlinedata.service.device;

import android.text.TextUtils;
import com.huawei.maps.offlinedata.handler.dto.device.DeleteMapInfoRequest;
import com.huawei.maps.offlinedata.handler.dto.device.TransmitRequest;
import com.huawei.maps.offlinedata.handler.dto.persist.OfflineDataTaskEntity;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapP2pMessage;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapPingCallback;
import com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback;
import com.huawei.maps.offlinedata.service.device.consts.a;
import com.huawei.maps.offlinedata.utils.g;
import com.huawei.openalliance.ad.constant.ParamConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static volatile d f6479a;
    private final HashMap<Long, String> b = new HashMap<>();

    private d() {
    }

    public static d a() {
        if (f6479a == null) {
            synchronized (d.class) {
                if (f6479a == null) {
                    f6479a = new d();
                }
            }
        }
        return f6479a;
    }

    public void b() {
        OfflineMapP2pMessage.Builder builder = new OfflineMapP2pMessage.Builder();
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(a.b.QUERY_DEVICE_REMAIN_SPACE.b());
        String a2 = com.huawei.maps.offlinedata.service.device.tlvtools.a.a(0);
        String c = com.huawei.maps.offlinedata.service.device.tlvtools.a.c(a2.length() / 2);
        stringBuffer.append(com.huawei.maps.offlinedata.service.device.tlvtools.a.a(1));
        stringBuffer.append(c);
        stringBuffer.append(a2);
        builder.setPayload(com.huawei.maps.offlinedata.service.device.tlvtools.a.a(stringBuffer.toString()));
        IOfflineMapSendCallback iOfflineMapSendCallback = new IOfflineMapSendCallback() { // from class: com.huawei.maps.offlinedata.service.device.d.1
            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onSendResult(int i) {
                g.a("DeviceSenderService", "queryDeviceAvailableStorage onSendResult. errCode is " + i);
            }

            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onSendProgress(long j) {
                g.a("DeviceSenderService", "queryDeviceAvailableStorage onSendProgress. count is " + j);
            }

            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onFileTransferReport(String str) {
                g.a("DeviceSenderService", "queryDeviceAvailableStorage onFileTransferReport. transferWay is " + str);
            }
        };
        g.a("DeviceSenderService", "start send queryDeviceAvailableStorage.");
        a(builder.build(), com.huawei.maps.offlinedata.service.device.tlvtools.b.a(), iOfflineMapSendCallback);
    }

    public void a(int i, int i2) {
        OfflineMapP2pMessage.Builder builder = new OfflineMapP2pMessage.Builder();
        if (i == -1) {
            i = com.huawei.maps.offlinedata.service.device.tlvtools.b.a();
        }
        int i3 = i;
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(a.b.SYNC_POLITICAL_PERSPECTIVE.b());
        if ((TextUtils.isEmpty(com.huawei.maps.offlinedata.service.cloud.a.a().b()) || TextUtils.isEmpty(com.huawei.maps.offlinedata.service.cloud.a.a().d())) && !com.huawei.maps.offlinedata.service.cloud.a.a().h()) {
            g.d("DeviceSenderService", "syncPoliticalPerspective authenticate fail.");
            return;
        }
        String b = com.huawei.maps.offlinedata.service.device.tlvtools.a.b(com.huawei.maps.offlinedata.service.cloud.a.a().b());
        String c = com.huawei.maps.offlinedata.service.device.tlvtools.a.c(b.length() / 2);
        stringBuffer.append(com.huawei.maps.offlinedata.service.device.tlvtools.a.a(2));
        stringBuffer.append(c);
        stringBuffer.append(b);
        String d = com.huawei.maps.offlinedata.service.cloud.a.a().d();
        g.a("DeviceSenderService", d);
        String b2 = com.huawei.maps.offlinedata.service.device.tlvtools.a.b(d);
        String c2 = com.huawei.maps.offlinedata.service.device.tlvtools.a.c(b2.length() / 2);
        stringBuffer.append(com.huawei.maps.offlinedata.service.device.tlvtools.a.a(20));
        stringBuffer.append(c2);
        stringBuffer.append(b2);
        String a2 = com.huawei.maps.offlinedata.service.device.tlvtools.a.a(i3);
        String c3 = com.huawei.maps.offlinedata.service.device.tlvtools.a.c(a2.length() / 2);
        stringBuffer.append(com.huawei.maps.offlinedata.service.device.tlvtools.a.a(19));
        stringBuffer.append(c3);
        stringBuffer.append(a2);
        builder.setPayload(com.huawei.maps.offlinedata.service.device.tlvtools.a.a(stringBuffer.toString()));
        IOfflineMapSendCallback iOfflineMapSendCallback = new IOfflineMapSendCallback() { // from class: com.huawei.maps.offlinedata.service.device.d.2
            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onSendResult(int i4) {
                g.a("DeviceSenderService", "syncPoliticalPerspective onSendResult. errCode is " + i4);
            }

            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onSendProgress(long j) {
                g.a("DeviceSenderService", "syncPoliticalPerspective onSendProgress. count is " + j);
            }

            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onFileTransferReport(String str) {
                g.a("DeviceSenderService", "syncPoliticalPerspective onFileTransferReport. transferWay is " + str);
            }
        };
        g.a("DeviceSenderService", "start send syncPoliticalPerspective.");
        a(builder.build(), a.b.SYNC_POLITICAL_PERSPECTIVE.a(), i3, new com.huawei.maps.offlinedata.service.device.receivers.g(i3, i2), iOfflineMapSendCallback);
    }

    public void a(final long j, Integer num) {
        OfflineMapP2pMessage.Builder builder = new OfflineMapP2pMessage.Builder();
        final int a2 = num == null ? com.huawei.maps.offlinedata.service.device.tlvtools.b.a() : num.intValue();
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(a.b.QUERY_DEVICE_DOWNLOADED_MAP_INFO.b());
        String a3 = com.huawei.maps.offlinedata.service.device.tlvtools.a.a(a2);
        String c = com.huawei.maps.offlinedata.service.device.tlvtools.a.c(a3.length() / 2);
        stringBuffer.append(com.huawei.maps.offlinedata.service.device.tlvtools.a.a(19));
        stringBuffer.append(c);
        stringBuffer.append(a3);
        builder.setPayload(com.huawei.maps.offlinedata.service.device.tlvtools.a.a(stringBuffer.toString()));
        IOfflineMapSendCallback iOfflineMapSendCallback = new IOfflineMapSendCallback() { // from class: com.huawei.maps.offlinedata.service.device.d.3
            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onSendResult(int i) {
                g.a("DeviceSenderService", "queryDeviceDownloadedMapInfo onSendResult. errCode is " + i);
                if (i == 206) {
                    if (a2 >= 10000) {
                        b.a().downLoadInfo(new ArrayList(), a2);
                    } else {
                        com.huawei.maps.offlinedata.jsbridge.a.a().a(-1, "send failed.", j);
                    }
                    e.a().a(a2);
                }
            }

            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onSendProgress(long j2) {
                g.a("DeviceSenderService", "queryDeviceDownloadedMapInfo onSendProgress. count is " + j2);
            }

            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onFileTransferReport(String str) {
                g.a("DeviceSenderService", "queryDeviceDownloadedMapInfo onFileTransferReport. transferWay is " + str);
            }
        };
        g.a("DeviceSenderService", "start send queryDeviceDownloadedMapInfo.");
        e.a().a(a2, j);
        a(builder.build(), a2, iOfflineMapSendCallback);
    }

    public void a(List<DeleteMapInfoRequest> list, int i, int i2, final int i3, final long j, int i4) {
        OfflineMapP2pMessage.Builder builder = new OfflineMapP2pMessage.Builder();
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(a.b.DELETE_DEVICE_MAP.b());
        com.huawei.maps.offlinedata.service.device.tlvtools.c cVar = new com.huawei.maps.offlinedata.service.device.tlvtools.c();
        cVar.b(4, i).b(5, i2).a(6, i4).a(19, i3);
        cVar.a(16);
        if (list != null && !list.isEmpty()) {
            for (DeleteMapInfoRequest deleteMapInfoRequest : list) {
                cVar.a(cVar.b(17).a(9, deleteMapInfoRequest.getCityId()).c(10, deleteMapInfoRequest.getMapType()).c(11, a.EnumC0168a.MAP_ERROR_DEVICE_ERROR.a()).c());
            }
        }
        cVar.b(cVar.a());
        stringBuffer.append(com.huawei.maps.offlinedata.service.device.tlvtools.a.a(cVar.d()));
        builder.setPayload(com.huawei.maps.offlinedata.service.device.tlvtools.a.a(stringBuffer.toString()));
        IOfflineMapSendCallback iOfflineMapSendCallback = new IOfflineMapSendCallback() { // from class: com.huawei.maps.offlinedata.service.device.d.4
            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onSendResult(int i5) {
                g.a("DeviceSenderService", "sendDeleteMapIds onSendResult. errCode is " + i5);
                if (i5 == 206) {
                    e.a().a(i3);
                    com.huawei.maps.offlinedata.jsbridge.a.a().a(-1, "send failed.", j);
                }
            }

            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onSendProgress(long j2) {
                g.a("DeviceSenderService", "sendDeleteMapIds onSendProgress. count is " + j2);
            }

            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onFileTransferReport(String str) {
                g.a("DeviceSenderService", "sendDeleteMapIds onFileTransferReport. transferWay is " + str);
            }
        };
        g.a("DeviceSenderService", "start send sendDeleteMapIds.");
        e.a().a(i3, j);
        a(builder.build(), i3, iOfflineMapSendCallback);
    }

    public void a(TransmitRequest transmitRequest) {
        OfflineMapP2pMessage.Builder builder = new OfflineMapP2pMessage.Builder();
        int a2 = com.huawei.maps.offlinedata.service.device.tlvtools.b.a();
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(a.b.TRANSMIT_MAP_HANDSHAKE.b());
        com.huawei.maps.offlinedata.service.device.tlvtools.c cVar = new com.huawei.maps.offlinedata.service.device.tlvtools.c();
        String b = TextUtils.isEmpty(com.huawei.maps.offlinedata.service.cloud.a.a().b()) ? "CN" : com.huawei.maps.offlinedata.service.cloud.a.a().b();
        String d = com.huawei.maps.offlinedata.service.cloud.a.a().d();
        g.a("DeviceSenderService", "sendMapHandShake reviewNumberOfWatchMap is " + d);
        stringBuffer.append(com.huawei.maps.offlinedata.service.device.tlvtools.a.a(cVar.a(2, b).a(20, d).d(13, transmitRequest.getMapSize()).b(10, transmitRequest.getMapType()).b(14, transmitRequest.getSyncType()).a(19, a2).d()));
        builder.setPayload(com.huawei.maps.offlinedata.service.device.tlvtools.a.a(stringBuffer.toString()));
        IOfflineMapSendCallback iOfflineMapSendCallback = new IOfflineMapSendCallback() { // from class: com.huawei.maps.offlinedata.service.device.d.5
            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onSendResult(int i) {
                g.a("DeviceSenderService", "sendMapHandShake onSendResult. errCode is " + i);
            }

            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onSendProgress(long j) {
                g.a("DeviceSenderService", "sendMapHandShake onSendProgress. count is " + j);
            }

            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onFileTransferReport(String str) {
                g.a("DeviceSenderService", "sendMapHandShake onFileTransferReport. transferWay is " + str);
            }
        };
        g.a("DeviceSenderService", "start send sendMapHandShake.");
        a(builder.build(), a.b.TRANSMIT_MAP_HANDSHAKE.a(), a2, new com.huawei.maps.offlinedata.service.device.receivers.c(transmitRequest.getFileDecompressDir() + File.separator + transmitRequest.getFileName(), transmitRequest.getRequestId()), iOfflineMapSendCallback);
    }

    public void a(String str, final long j) {
        g.a("DeviceSenderService", "transmitMap requestId is " + j);
        final File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            g.c("DeviceSenderService", j + "transmitMap file not exist or not file. ");
            f.a().b(String.valueOf(j));
            return;
        }
        IOfflineMapSendCallback iOfflineMapSendCallback = new IOfflineMapSendCallback() { // from class: com.huawei.maps.offlinedata.service.device.d.6
            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onSendResult(int i) {
                g.a("DeviceSenderService", "transmitMap onSendResult. errCode is " + i);
                if (i == 207) {
                    if (file.exists()) {
                        g.a("DeviceSenderService", "delete file ".concat(file.delete() ? "success" : ParamConstants.CallbackMethod.ON_FAIL));
                    }
                    g.a("DeviceSenderService", "sendMapFile onSendResult. requestId is " + j);
                    d.this.b.remove(Long.valueOf(j));
                    f.a().a(String.valueOf(j));
                    return;
                }
                f.a().b(String.valueOf(j));
            }

            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onSendProgress(long j2) {
                g.a("DeviceSenderService", "sendMapFile onSendProgress. count is " + j2);
                f.a().a(String.valueOf(j), String.valueOf(j2));
            }

            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onFileTransferReport(String str2) {
                g.a("DeviceSenderService", "sendMapFile onFileTransferReport. transferWay is " + str2);
            }
        };
        g.a("DeviceSenderService", "start send transmitMap " + j);
        this.b.put(Long.valueOf(j), str);
        a(new OfflineMapP2pMessage.Builder().setPayload(file).build(), com.huawei.maps.offlinedata.service.device.tlvtools.b.a(), iOfflineMapSendCallback);
    }

    public void a(final long j, final long j2, final boolean z) {
        if (!this.b.containsKey(Long.valueOf(j))) {
            g.c("DeviceSenderService", "requestId " + j + " is not sending!");
            com.huawei.maps.offlinedata.jsbridge.a.a().a(-1, "requestId error.", j2);
            return;
        }
        g.a("DeviceSenderService", "start cancel requestId: " + j + ", isContinueTransmit:" + z);
        String str = this.b.get(Long.valueOf(j));
        final File file = new File(str);
        IOfflineMapSendCallback iOfflineMapSendCallback = new IOfflineMapSendCallback() { // from class: com.huawei.maps.offlinedata.service.device.d.7
            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onSendResult(int i) {
                g.c("DeviceSenderService", "receive cancel result " + i);
                if (i == 20003) {
                    if (!z) {
                        d.this.b.remove(Long.valueOf(j));
                        if (file.exists()) {
                            g.a("DeviceSenderService", "mapFile delete ".concat(file.delete() ? "success" : ParamConstants.CallbackMethod.ON_FAIL));
                        }
                    }
                    OfflineDataTaskEntity offlineDataTaskEntity = f.a().c().get();
                    if (offlineDataTaskEntity != null && TextUtils.equals(offlineDataTaskEntity.getTaskId(), String.valueOf(j))) {
                        f.a().c().set(null);
                    }
                    com.huawei.maps.offlinedata.jsbridge.a.a().a(true, j2);
                }
                if (i == 20004) {
                    com.huawei.maps.offlinedata.jsbridge.a.a().a(-1, "cancel failed.", j2);
                }
            }

            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onSendProgress(long j3) {
                g.c("DeviceSenderService", "receive cancel progress " + j3);
            }

            @Override // com.huawei.maps.offlinedata.health.p2p.sender.IOfflineMapSendCallback
            public void onFileTransferReport(String str2) {
                g.c("DeviceSenderService", "receive transfer report " + str2);
            }
        };
        int a2 = com.huawei.maps.offlinedata.service.device.tlvtools.b.a();
        g.a("DeviceSenderService", "start send cancelTransmitMap, msgId is " + a2 + ", requestId is " + j);
        OfflineMapWearEngineManager.getInstance().getWearEngine4OfflineMap().sendCancelCommand(str, a2, iOfflineMapSendCallback);
    }

    public void a(IOfflineMapPingCallback iOfflineMapPingCallback) {
        g.a("DeviceSenderService", "sendPingToDevice.");
        OfflineMapWearEngineManager.getInstance().getWearEngine4OfflineMap().sendPingToDevice(iOfflineMapPingCallback, OfflineMapWearEngineManager.WEAR_PACKAGE_NAME);
    }

    private void a(OfflineMapP2pMessage offlineMapP2pMessage, int i, IOfflineMapSendCallback iOfflineMapSendCallback) {
        g.a("DeviceSenderService", "sendMsgToDevice, msgId is " + i);
        OfflineMapWearEngineManager.getInstance().getWearEngine4OfflineMap().sendMsgToDevice(offlineMapP2pMessage, i, iOfflineMapSendCallback);
    }

    private void a(OfflineMapP2pMessage offlineMapP2pMessage, int i, int i2, com.huawei.maps.offlinedata.service.device.receivers.d dVar, IOfflineMapSendCallback iOfflineMapSendCallback) {
        g.a("DeviceSenderService", "sendMsgToDevice with receiver, msgId is " + i2);
        com.huawei.maps.offlinedata.service.device.receivers.e.a().a(dVar, i, i2);
        OfflineMapWearEngineManager.getInstance().getWearEngine4OfflineMap().sendMsgToDevice(offlineMapP2pMessage, i2, iOfflineMapSendCallback);
    }
}
