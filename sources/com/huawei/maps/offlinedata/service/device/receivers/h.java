package com.huawei.maps.offlinedata.service.device.receivers;

import com.google.gson.Gson;
import com.huawei.maps.offlinedata.handler.dto.device.DownloadMapInfo;
import com.huawei.maps.offlinedata.health.init.OnObtainMapDataListener;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class h implements d {

    /* renamed from: a, reason: collision with root package name */
    private static volatile h f6496a;
    private final HashMap<Integer, ArrayList<DownloadMapInfo>> b = new HashMap<>();

    private h() {
    }

    public static h a() {
        if (f6496a == null) {
            synchronized (h.class) {
                if (f6496a == null) {
                    f6496a = new h();
                }
            }
        }
        return f6496a;
    }

    private OnObtainMapDataListener b() {
        return com.huawei.maps.offlinedata.service.device.b.a();
    }

    private static void a(List<com.huawei.maps.offlinedata.service.device.tlvtools.d> list, List<DownloadMapInfo> list2) {
        Iterator<com.huawei.maps.offlinedata.service.device.tlvtools.d> it = list.iterator();
        while (it.hasNext()) {
            long j = -1;
            int i = -1;
            int i2 = -1;
            for (com.huawei.maps.offlinedata.service.device.tlvtools.f fVar : it.next().a()) {
                byte[] b = fVar.b();
                switch (fVar.a()) {
                    case 9:
                        j = com.huawei.maps.offlinedata.service.device.tlvtools.a.a(b, -1L);
                        break;
                    case 10:
                        i2 = com.huawei.maps.offlinedata.service.device.tlvtools.a.b(b, -1);
                        break;
                    case 11:
                        i = com.huawei.maps.offlinedata.service.device.tlvtools.a.b(b, -1);
                        break;
                    default:
                        com.huawei.maps.offlinedata.utils.g.c("QueryDeviceDownloadedMapInfoReceiver", "parseDownloadMapInfoTlv parse mapInfo default");
                        break;
                }
            }
            DownloadMapInfo downloadMapInfo = new DownloadMapInfo();
            downloadMapInfo.setCityId(String.valueOf(j));
            downloadMapInfo.setVersion(i);
            downloadMapInfo.setMspType(i2);
            list2.add(downloadMapInfo);
        }
    }

    @Override // com.huawei.maps.offlinedata.service.device.receivers.d
    public void a(int i, byte[] bArr) {
        Long l = null;
        int i2 = 0;
        try {
            com.huawei.maps.offlinedata.service.device.tlvtools.d a2 = com.huawei.maps.offlinedata.service.device.tlvtools.e.a(bArr);
            if (a2 == null) {
                com.huawei.maps.offlinedata.utils.g.c("QueryDeviceDownloadedMapInfoReceiver", "LocalMapList TlvByteFather is null");
                return;
            }
            try {
                int i3 = -1;
                int i4 = -1;
                int i5 = -1;
                int i6 = -1;
                for (com.huawei.maps.offlinedata.service.device.tlvtools.f fVar : a2.a()) {
                    try {
                        byte[] b = fVar.b();
                        byte a3 = fVar.a();
                        if (a3 == 4) {
                            i4 = com.huawei.maps.offlinedata.service.device.tlvtools.a.b(b, -1);
                        } else if (a3 == 5) {
                            i5 = com.huawei.maps.offlinedata.service.device.tlvtools.a.b(b, -1);
                        } else if (a3 == 6) {
                            i6 = com.huawei.maps.offlinedata.service.device.tlvtools.a.b(b, -1);
                        } else if (a3 == 19) {
                            i3 = com.huawei.maps.offlinedata.service.device.tlvtools.a.b(b, -1);
                            l = com.huawei.maps.offlinedata.service.device.e.a().b().get(Integer.valueOf(i3));
                        } else {
                            com.huawei.maps.offlinedata.utils.g.c("QueryDeviceDownloadedMapInfoReceiver", "handleDownloadedMapListResponse default");
                        }
                    } catch (com.huawei.maps.offlinedata.service.device.tlvtools.g e) {
                        e = e;
                        i2 = i3;
                        a(i2, -1, TrackConstants$Events.EXCEPTION, l);
                        com.huawei.maps.offlinedata.utils.g.d("QueryDeviceDownloadedMapInfoReceiver", "catch TlvException:" + e.getMessage());
                        return;
                    } catch (Exception e2) {
                        e = e2;
                        i2 = i3;
                        a(i2, -1, TrackConstants$Events.EXCEPTION, l);
                        com.huawei.maps.offlinedata.utils.g.d("QueryDeviceDownloadedMapInfoReceiver", "catch Exception:" + e.getMessage());
                        return;
                    }
                }
                com.huawei.maps.offlinedata.utils.g.a("QueryDeviceDownloadedMapInfoReceiver", "totalFrameNum:" + i4 + ",frameIndex:" + i5 + ",totalMapNum:" + i6 + ", msgId:" + i3);
                if (i6 == 0) {
                    a(i3, l, new ArrayList<>());
                    return;
                }
                List<com.huawei.maps.offlinedata.service.device.tlvtools.d> b2 = a2.b();
                if (b2 != null && !b2.isEmpty()) {
                    com.huawei.maps.offlinedata.service.device.tlvtools.d dVar = b2.get(0);
                    if (dVar == null) {
                        com.huawei.maps.offlinedata.utils.g.d("QueryDeviceDownloadedMapInfoReceiver", "LocalMapList childTlvList is null");
                        return;
                    }
                    List<com.huawei.maps.offlinedata.service.device.tlvtools.d> b3 = dVar.b();
                    if (b3 != null && !b3.isEmpty()) {
                        ArrayList<DownloadMapInfo> arrayList = new ArrayList<>();
                        a(b3, arrayList);
                        com.huawei.maps.offlinedata.utils.g.a("QueryDeviceDownloadedMapInfoReceiver", "handleDownloadedMapListResponse callbackId " + l + ", size:" + arrayList.size() + ", downloadMapInfos:" + new Gson().toJson(arrayList));
                        if (i4 == 1) {
                            a(i3, l, arrayList);
                            return;
                        }
                        if (this.b.get(Integer.valueOf(i3)) == null) {
                            this.b.put(Integer.valueOf(i3), arrayList);
                        } else {
                            this.b.get(Integer.valueOf(i3)).addAll(arrayList);
                        }
                        if (this.b.get(Integer.valueOf(i3)).size() == i6) {
                            com.huawei.maps.offlinedata.utils.g.a("QueryDeviceDownloadedMapInfoReceiver", "handleDownloadedMapListResponse all frame end:" + new Gson().toJson(this.b.get(Integer.valueOf(i3))));
                            a(i3, l, this.b.get(Integer.valueOf(i3)));
                            this.b.remove(Integer.valueOf(i3));
                            return;
                        }
                        if (this.b.get(Integer.valueOf(i3)).size() > i6) {
                            a(i3, -1, "data error", l);
                            com.huawei.maps.offlinedata.utils.g.d("QueryDeviceDownloadedMapInfoReceiver", "handleDownloadedMapListResponse totalMapNum:" + i6 + ", size:" + this.b.get(Integer.valueOf(i3)).size());
                            this.b.remove(Integer.valueOf(i3));
                            return;
                        }
                        com.huawei.maps.offlinedata.utils.g.c("QueryDeviceDownloadedMapInfoReceiver", "handleDownloadedMapListResponse wait next frame.");
                        return;
                    }
                    com.huawei.maps.offlinedata.utils.g.d("QueryDeviceDownloadedMapInfoReceiver", "mapInfosTlvByte is null");
                    return;
                }
                com.huawei.maps.offlinedata.utils.g.d("QueryDeviceDownloadedMapInfoReceiver", "LocalMapList tlvFathers is empty");
            } catch (com.huawei.maps.offlinedata.service.device.tlvtools.g e3) {
                e = e3;
                i2 = -1;
            } catch (Exception e4) {
                e = e4;
                i2 = -1;
            }
        } catch (com.huawei.maps.offlinedata.service.device.tlvtools.g e5) {
            e = e5;
        } catch (Exception e6) {
            e = e6;
        }
    }

    private void a(int i, Long l, ArrayList<DownloadMapInfo> arrayList) {
        if (i >= 10000) {
            b().downLoadInfo(arrayList, i);
        } else if (l != null) {
            com.huawei.maps.offlinedata.jsbridge.a.a().a(arrayList, l.longValue());
        }
        com.huawei.maps.offlinedata.service.device.e.a().a(i);
    }

    private void a(int i, int i2, String str, Long l) {
        if (i >= 10000) {
            b().downLoadInfo(new ArrayList(), i);
        } else if (l != null) {
            com.huawei.maps.offlinedata.jsbridge.a.a().a(i2, str, l.longValue());
        }
        com.huawei.maps.offlinedata.service.device.e.a().a(i);
    }
}
