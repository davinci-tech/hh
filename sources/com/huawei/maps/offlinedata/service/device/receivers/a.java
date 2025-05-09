package com.huawei.maps.offlinedata.service.device.receivers;

import com.google.gson.Gson;
import com.huawei.maps.offlinedata.handler.dto.device.DeleteMapInfoResponse;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class a implements d {

    /* renamed from: a, reason: collision with root package name */
    private static volatile a f6489a;
    private final HashMap<Integer, ArrayList<DeleteMapInfoResponse>> b = new HashMap<>();

    private a() {
    }

    public static a a() {
        if (f6489a == null) {
            synchronized (a.class) {
                if (f6489a == null) {
                    f6489a = new a();
                }
            }
        }
        return f6489a;
    }

    private static void a(List<com.huawei.maps.offlinedata.service.device.tlvtools.d> list, List<DeleteMapInfoResponse> list2) {
        Iterator<com.huawei.maps.offlinedata.service.device.tlvtools.d> it = list.iterator();
        while (it.hasNext()) {
            long j = -1;
            int i = -1;
            int i2 = -1;
            for (com.huawei.maps.offlinedata.service.device.tlvtools.f fVar : it.next().a()) {
                byte[] b = fVar.b();
                byte a2 = fVar.a();
                if (a2 == 9) {
                    j = com.huawei.maps.offlinedata.service.device.tlvtools.a.a(b, -1L);
                } else if (a2 == 10) {
                    i2 = com.huawei.maps.offlinedata.service.device.tlvtools.a.b(b, -1);
                } else if (a2 == 18) {
                    i = com.huawei.maps.offlinedata.service.device.tlvtools.a.b(b, -1);
                } else {
                    com.huawei.maps.offlinedata.utils.g.c("DeleteDeviceMapInfoReceiver", "parseDownloadMapInfoTlv parse mapInfo default");
                }
            }
            DeleteMapInfoResponse deleteMapInfoResponse = new DeleteMapInfoResponse();
            deleteMapInfoResponse.setCityId(String.valueOf(j));
            deleteMapInfoResponse.setMapDeleteState(Integer.valueOf(i));
            deleteMapInfoResponse.setMapType(i2);
            list2.add(deleteMapInfoResponse);
        }
    }

    @Override // com.huawei.maps.offlinedata.service.device.receivers.d
    public void a(int i, byte[] bArr) {
        Long l = null;
        try {
            com.huawei.maps.offlinedata.service.device.tlvtools.d a2 = com.huawei.maps.offlinedata.service.device.tlvtools.e.a(bArr);
            if (a2 == null) {
                com.huawei.maps.offlinedata.utils.g.c("DeleteDeviceMapInfoReceiver", "MapDelete TlvByteFather is null");
                return;
            }
            int i2 = -1;
            int i3 = -1;
            int i4 = -1;
            int i5 = -1;
            for (com.huawei.maps.offlinedata.service.device.tlvtools.f fVar : a2.a()) {
                byte[] b = fVar.b();
                byte a3 = fVar.a();
                if (a3 == 4) {
                    i2 = com.huawei.maps.offlinedata.service.device.tlvtools.a.b(b, -1);
                } else if (a3 == 5) {
                    i3 = com.huawei.maps.offlinedata.service.device.tlvtools.a.b(b, -1);
                } else if (a3 == 6) {
                    i4 = com.huawei.maps.offlinedata.service.device.tlvtools.a.b(b, -1);
                } else if (a3 == 19) {
                    i5 = com.huawei.maps.offlinedata.service.device.tlvtools.a.b(b, -1);
                    l = com.huawei.maps.offlinedata.service.device.e.a().b().get(Integer.valueOf(i5));
                } else {
                    com.huawei.maps.offlinedata.utils.g.c("DeleteDeviceMapInfoReceiver", "handleDownloadedMapListResponse default");
                }
            }
            com.huawei.maps.offlinedata.utils.g.a("DeleteDeviceMapInfoReceiver", "totalFrameNum:" + i2 + ",frameIndex:" + i3 + ",totalMapNum:" + i4 + ", msgId:" + i5);
            List<com.huawei.maps.offlinedata.service.device.tlvtools.d> b2 = a2.b();
            if (b2 != null && !b2.isEmpty()) {
                com.huawei.maps.offlinedata.service.device.tlvtools.d dVar = b2.get(0);
                if (dVar == null) {
                    com.huawei.maps.offlinedata.utils.g.c("DeleteDeviceMapInfoReceiver", "secondChildTlvList childTlvList is null");
                }
                List<com.huawei.maps.offlinedata.service.device.tlvtools.d> b3 = dVar.b();
                if (b3 == null || b3.isEmpty()) {
                    com.huawei.maps.offlinedata.utils.g.c("DeleteDeviceMapInfoReceiver", "mapInfosTlvByte is null");
                }
                ArrayList arrayList = new ArrayList();
                a(b3, arrayList);
                com.huawei.maps.offlinedata.utils.g.a("DeleteDeviceMapInfoReceiver", "handleDeleteDeviceMapResponse deleteMapInfos:" + new Gson().toJson(arrayList));
                if (i2 == 1) {
                    if (l != null) {
                        com.huawei.maps.offlinedata.jsbridge.a.a().a(arrayList, l.longValue());
                    }
                    com.huawei.maps.offlinedata.service.device.e.a().a(i5);
                    return;
                }
                if (this.b.get(Integer.valueOf(i5)) == null) {
                    this.b.put(Integer.valueOf(i5), new ArrayList<>());
                }
                this.b.get(Integer.valueOf(i5)).addAll(arrayList);
                if (this.b.get(Integer.valueOf(i5)).size() == i4) {
                    com.huawei.maps.offlinedata.utils.g.a("DeleteDeviceMapInfoReceiver", "handleDeleteDeviceMapResponse all frame end:" + new Gson().toJson(this.b.get(Integer.valueOf(i5))));
                    if (l != null) {
                        com.huawei.maps.offlinedata.jsbridge.a.a().a(this.b.get(Integer.valueOf(i5)), l.longValue());
                    }
                    this.b.remove(Integer.valueOf(i5));
                    com.huawei.maps.offlinedata.service.device.e.a().a(i5);
                }
                if (this.b.get(Integer.valueOf(i5)).size() > i4) {
                    if (l != null) {
                        com.huawei.maps.offlinedata.jsbridge.a.a().a(-1, "data error", l.longValue());
                    }
                    this.b.remove(Integer.valueOf(i5));
                    com.huawei.maps.offlinedata.service.device.e.a().a(i5);
                    com.huawei.maps.offlinedata.utils.g.d("DeleteDeviceMapInfoReceiver", "handleDeleteDeviceMapResponse totalMapNum:" + i4 + ", size:" + this.b.get(Integer.valueOf(i5)).size());
                    return;
                }
                return;
            }
            com.huawei.maps.offlinedata.utils.g.c("DeleteDeviceMapInfoReceiver", "MapDelete tlvFathers is empty");
        } catch (com.huawei.maps.offlinedata.service.device.tlvtools.g unused) {
            com.huawei.maps.offlinedata.utils.g.d("DeleteDeviceMapInfoReceiver", "catch TlvException");
            if (0 != 0) {
                com.huawei.maps.offlinedata.jsbridge.a.a().a(-1, TrackConstants$Events.EXCEPTION, l.longValue());
            }
        }
    }
}
