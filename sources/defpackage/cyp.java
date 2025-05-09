package defpackage;

import com.huawei.health.ecologydevice.fitness.datastruct.HistoryData;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class cyp extends cyt {
    public HistoryData e() {
        b();
        byte[] a2 = a();
        if (a2 == null || a2.length <= 0) {
            return null;
        }
        if (!a(a2, 0, a2.length - 1)) {
            LogUtil.d("PDROPE_HistoryDataPackets", "Invalid historical data.");
            return null;
        }
        LogUtil.d("PDROPE_HistoryDataPackets", "history protocolData = ", dis.d(a2, ""));
        HistoryData d = d(a2, 1);
        if (d == null || d.getHistoryDataItem() == null) {
            LogUtil.d("PDROPE_HistoryDataPackets", "parse oldParseHistoryDataPacket is error");
            return null;
        }
        c(cyw.e(a2, 17, 18));
        cys historyDataItem = d.getHistoryDataItem();
        if (historyDataItem == null) {
            LogUtil.c("PDROPE_HistoryDataPackets", "historyDataItem is null");
            return d;
        }
        int a3 = a(a2, historyDataItem, 19);
        if (historyDataItem.j() == 4 || historyDataItem.j() == 5) {
            int e = cyw.e(a2, 17, a3);
            historyDataItem.f(e);
            LogUtil.d("PDROPE_HistoryDataPackets", "groupNum = ", Integer.valueOf(e));
            historyDataItem.h(cyw.e(a2, 18, a3 + 1));
            int i = a3 + 3;
            ArrayList<ckk> arrayList = new ArrayList<>(e);
            for (int i2 = 0; i2 < e; i2++) {
                ckk a4 = a(a2, i);
                i += 12;
                arrayList.add(a4);
            }
            historyDataItem.a(arrayList);
        } else if (historyDataItem.j() == 3) {
            historyDataItem.e(e(a2, a3));
        } else {
            LogUtil.e("PDROPE_HistoryDataPackets", "parseHistoryDataPacket, Unrecognized Rope Data!");
        }
        return d;
    }

    private int a(byte[] bArr, cys cysVar, int i) {
        LogUtil.d("PDROPE_HistoryDataPackets", "parsePubParam");
        cysVar.j(cyw.e(bArr, 17, i));
        cysVar.d(cyw.e(bArr, 17, i + 1) == 1);
        cysVar.a(cyw.e(bArr, 18, i + 2));
        cysVar.e(cyw.e(bArr, 18, i + 4));
        return i + 6;
    }

    private ckk a(byte[] bArr, int i) {
        if (bArr == null && bArr.length == 0) {
            return null;
        }
        ckk ckkVar = new ckk();
        int e = cyw.e(bArr, 17, i);
        ckkVar.d(e);
        ckkVar.j(cyw.e(bArr, 18, i + 1));
        ckkVar.i(cyw.e(bArr, 18, i + 3));
        int e2 = cyw.e(bArr, 18, i + 5);
        ckkVar.e(e2);
        ckkVar.a(cyw.e(bArr, 18, i + 7));
        int e3 = cyw.e(bArr, 18, i + 9);
        ckkVar.b(e3);
        ckkVar.c(cyw.e(bArr, 17, i + 11));
        LogUtil.d("PDROPE_HistoryDataPackets", "mRopeIntermissionNo = ", Integer.valueOf(e), " ropeIntermissionNum =", Integer.valueOf(e2), " avgSpeed = ", Integer.valueOf(e3));
        return ckkVar;
    }

    public HistoryData d(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0 || bArr.length < 16) {
            LogUtil.d("PDROPE_HistoryDataPackets", "RopeRealData is error");
            return null;
        }
        HistoryData historyData = new HistoryData();
        int e = cyw.e(bArr, 17, i);
        int i2 = i + 1;
        if ((e & 15) == 0) {
            return null;
        }
        boolean z = (e & 32) != 0;
        LogUtil.d("PDROPE_HistoryDataPackets", "isHasMoreData = ", Boolean.valueOf(z));
        historyData.setHasMoreData(z);
        int e2 = cyw.e(bArr, 20, i2);
        cys cysVar = new cys();
        cysVar.i(e2);
        cysVar.c(cyw.e(bArr, 18, i + 5));
        int e3 = cyw.e(bArr, 18, i + 7);
        cysVar.m(e3);
        LogUtil.d("PDROPE_HistoryDataPackets", "totalSkip = ", Integer.valueOf(e3));
        cysVar.d(cyw.e(bArr, 18, i + 9));
        cysVar.b(cyw.e(bArr, 18, i + 11));
        cysVar.g(cyw.e(bArr, 18, i + 13));
        historyData.setHistoryDataItem(cysVar);
        return historyData;
    }
}
