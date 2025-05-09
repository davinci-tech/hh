package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.common.utils.HexUtils;
import com.huawei.health.R;
import com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil;
import com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback;
import com.huawei.health.device.util.EventBus;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.entity.UniteDevice;
import defpackage.chb;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* loaded from: classes3.dex */
public class cpu {
    public static void a(String str, UUID uuid, UUID uuid2) {
        if (TextUtils.isEmpty(str) || uuid == null || uuid2 == null) {
            LogUtil.h("HwWspMeasureUtil", " publishMsg is null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("service_uuid", uuid);
        bundle.putSerializable("characteristic_uuid", uuid2);
        EventBus.d(new EventBus.b(str, bundle));
    }

    public static void a(byte[] bArr, cfi cfiVar) {
        if (bArr == null || bArr.length == 0) {
            LogUtil.h("HwWspMeasureUtil", " publishHistoryDataMsg historyData is null");
            cgt.e().d(false);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putByteArray("historyData", bArr);
        if (cfiVar != null) {
            bundle.putInt("age", cfiVar.a());
        }
        EventBus.d(new EventBus.b("history_weight_info", bundle));
    }

    public static void e(byte[] bArr) {
        if (bArr == null) {
            LogUtil.h("HwWspMeasureUtil", " publishAuthMsg tokens is null");
        } else {
            if (bArr.length == 0) {
                EventBus.d(new EventBus.b("request_auth_failed", new Bundle()));
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putByteArray("token", bArr);
            EventBus.d(new EventBus.b("request_auth_token", bundle));
        }
    }

    public static void a(final Context context, final String str) {
        if (context == null) {
            LogUtil.h("HwWspMeasureUtil", " showToastOnUiThread context is null");
        } else if (BaseApplication.getActivity() != null) {
            BaseApplication.getActivity().runOnUiThread(new Runnable() { // from class: cpu.3
                @Override // java.lang.Runnable
                public void run() {
                    if (cpa.ax(str)) {
                        Context context2 = context;
                        nrh.d(context2, context2.getResources().getString(R.string.IDS_device_offline_sync_data_done));
                    } else {
                        Context context3 = context;
                        nrh.d(context3, context3.getResources().getString(R.string.IDS_device_sync_data_done_toast));
                    }
                }
            });
        } else {
            LogUtil.h("HwWspMeasureUtil", "HwWspMeasureController getActivity is null !");
        }
    }

    public static void d(byte[] bArr, Context context, String str) {
        if (bArr == null || bArr.length == 0) {
            LogUtil.h("HwWspMeasureUtil", "setNewWeightHonorDevice randA is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwWspMeasureUtil", "setNewWeightHonorDevice uniqueId is null");
            return;
        }
        if (context == null) {
            LogUtil.h("HwWspMeasureUtil", "setNewWeightHonorDevice context is null");
            return;
        }
        Bundle bundle = new Bundle();
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 0, bArr2, 0, 16);
        bundle.putByteArray("randA", bArr2);
        EventBus.d(new EventBus.b("request_auth", bundle));
        if (bArr.length > 16) {
            byte[] bArr3 = {bArr[16]};
            coz.b(context, CommonUtil.a(bArr3, 1), str);
            b(HexUtils.d(bArr3), str);
        }
    }

    private static void b(String str, String str2) {
        String d = knl.d(str2);
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("weightUnit");
        stringBuffer.append(d);
        KeyValDbManager.b(com.huawei.haf.application.BaseApplication.e()).e(stringBuffer.toString(), str);
    }

    public static byte[] e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b("HwWspMeasureUtil", "getStringBytes occur UnsupportedEncodingException");
            return null;
        }
    }

    public static void c(cxh cxhVar) {
        if (cxhVar == null || TextUtils.isEmpty(cxhVar.getUniqueId())) {
            return;
        }
        cjg.d().e(cpl.c().d(cxhVar.getUniqueId()));
    }

    public static cjd Kp_(Bundle bundle, byte[] bArr, byte[] bArr2, String str) {
        cjd cjdVar = new cjd();
        if (bundle != null) {
            if (bundle.getInt("guestUser", 0) == 1) {
                cjdVar.b(bundle.getInt("age"));
            } else {
                cjdVar.b(cgs.e(bundle.getInt("birthday"), bundle.getInt("age")));
            }
            cjdVar.c(bArr);
            cjdVar.c(bundle.getInt(CommonConstant.KEY_GENDER));
            cjdVar.c(bundle.getFloat("weight"));
            cjdVar.e(bArr2);
            cjdVar.e(bundle.getInt("height"));
            if (cgs.d(str)) {
                cjdVar.a(cgs.a(bundle.getInt("birthday")));
            }
        }
        return cjdVar;
    }

    public static ArrayList<byte[]> b(ArrayList<byte[]> arrayList) {
        ArrayList<byte[]> arrayList2 = new ArrayList<>(16);
        if (arrayList == null) {
            LogUtil.h("HwWspMeasureUtil", "value is null");
            return arrayList2;
        }
        Iterator<byte[]> it = arrayList.iterator();
        while (it.hasNext()) {
            ByteBuffer wrap = ByteBuffer.wrap(it.next());
            while (wrap.position() < wrap.limit()) {
                byte[] bArr = wrap.position() + 20 <= wrap.limit() ? new byte[20] : new byte[wrap.limit() - wrap.position()];
                wrap.get(bArr);
                arrayList2.add(bArr);
            }
        }
        return arrayList2;
    }

    public static void d(UUID uuid, UUID uuid2, cjq cjqVar, IAsynBleTaskCallback iAsynBleTaskCallback, cxh cxhVar) {
        if (cxhVar == null) {
            LogUtil.h("HwWspMeasureUtil", "setCharacteristicNotificationUds: mDevice is null");
            return;
        }
        String address = cxhVar.getAddress();
        if (!TextUtils.isEmpty(address)) {
            boolean b = cjqVar != null ? cjqVar.b().b() : false;
            UniteDevice d = cpl.c().d(address);
            if (uuid != null && uuid2 != null) {
                cjg.d().a(d, uuid.toString(), uuid2.toString(), b);
            }
            if (iAsynBleTaskCallback != null) {
                iAsynBleTaskCallback.success(null);
                return;
            }
            return;
        }
        LogUtil.h("HwWspMeasureUtil", "setCharacteristicNotificationUds: address is null");
    }

    public static void b(byte[] bArr, String str, cfi cfiVar) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("realTimeData", bArr);
        bundle.putString("unique_id", str);
        if (cfiVar != null) {
            bundle.putInt("age", cfiVar.a());
        }
        EventBus.d(new EventBus.b("real_time_weight_info", bundle));
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0025, code lost:
    
        if (r3.equals(r5.i()) != false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(defpackage.cfi r5) {
        /*
            r0 = 0
            java.lang.String r1 = "HwWspMeasureUtil"
            if (r5 != 0) goto L10
            java.lang.String r5 = "user is null"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r5)
            return r0
        L10:
            com.huawei.health.device.fatscale.multiusers.MultiUsersManager r2 = com.huawei.health.device.fatscale.multiusers.MultiUsersManager.INSTANCE
            cfi r2 = r2.getCurrentUser()
            java.lang.String r3 = r2.i()
            r4 = 1
            if (r3 == 0) goto L28
            java.lang.String r5 = r5.i()
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L43
            goto L38
        L28:
            int r3 = r2.n()
            if (r3 != r4) goto L3a
            int r2 = r2.n()
            int r5 = r5.n()
            if (r2 != r5) goto L3a
        L38:
            r0 = r4
            goto L43
        L3a:
            java.lang.String r5 = "could not determin, assume not same user"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r5)
        L43:
            java.lang.String r5 = "current user is same: "
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r0)
            java.lang.Object[] r5 = new java.lang.Object[]{r5, r2}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cpu.a(cfi):boolean");
    }

    public static boolean e(cxh cxhVar, String str, Map<String, Set<UUID>> map, UUID uuid) {
        if (cxhVar == null || TextUtils.isEmpty(cxhVar.getAddress())) {
            LogUtil.h("HwWspMeasureUtil", "isSupportCharacteristic device or device address is empty");
            return false;
        }
        if (cpa.ar(str)) {
            Integer num = chb.a().get(uuid);
            BleTaskQueueUtil.TaskType[] values = BleTaskQueueUtil.TaskType.values();
            if (num == null || koq.b(values, num.intValue())) {
                LogUtil.h("HwWspMeasureUtil", "isSupportCharacteristic : type is null or index is out of bound");
                return false;
            }
            chb.c cVar = chb.c().get(values[num.intValue()]);
            if (cVar == null) {
                LogUtil.h("HwWspMeasureUtil", "isSupportCharacteristic map is null");
                return false;
            }
            String address = cxhVar.getAddress();
            if (TextUtils.isEmpty(address)) {
                return false;
            }
            return cjg.d().a(cpl.c().d(address), cVar.e().toString(), cVar.c().toString());
        }
        if (map == null || map.isEmpty()) {
            LogUtil.h("HwWspMeasureUtil", "isSupportCharacteristic deviceCapacities is null or is empty");
            return false;
        }
        Set<UUID> set = map.get(cxhVar.getAddress());
        if (koq.b(set)) {
            LogUtil.h("HwWspMeasureUtil", "isSupportCharacteristic characteristicSet is null or is empty");
            return false;
        }
        return set.contains(uuid);
    }

    public static cfi d(cjd cjdVar) {
        if (cjdVar == null) {
            LogUtil.h("HwWspMeasureUtil", "buildUserBaseInfor bean is null");
            return null;
        }
        cfi cfiVar = new cfi();
        cfiVar.a(cjdVar.d());
        cfiVar.d(cjdVar.c());
        cfiVar.b(cjdVar.i());
        cfiVar.c(cjdVar.b());
        return cfiVar;
    }

    public static void b(byte[] bArr) {
        if (bArr != null && bArr.length > 0 && bArr[0] == 9) {
            chd.b().a(bArr);
        } else {
            LogUtil.a("HwWspMeasureUtil", "parseDataContents enter.");
            cfv.b().c(bArr);
        }
    }

    public static void b(cgp cgpVar, String str) {
        if (cgpVar == null) {
            LogUtil.h("HwWspMeasureUtil", "updateBondedDeviceSn deviceVersion is null");
            return;
        }
        cpa.j(str, cgpVar.c());
        String e = cgpVar.e();
        if (TextUtils.isEmpty(e)) {
            return;
        }
        LogUtil.a("HwWspMeasureUtil", "onReceiveVersionCode serialNumber is not null");
        cpa.d(e, str);
        cpa.a(str, e);
        ContentValues contentValues = new ContentValues();
        contentValues.put("sn", e);
        ceo.d().Eg_(contentValues, "uniqueId=?", new String[]{str});
    }

    public static void e(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException unused) {
            LogUtil.b("HwWspMeasureUtil", "sleep delay interrupted");
        }
    }
}
