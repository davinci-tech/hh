package defpackage;

import android.os.Message;
import android.text.TextUtils;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.hms.kit.awareness.AwarenessStatusCodes;
import com.huawei.hms.mlsdk.common.internal.client.event.MonitorResult;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwdevice.outofprocess.util.hwdfx.MaintenaceInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.AiCoreSdkConstant;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.ui.main.stories.me.adapter.AdapterWithBottomView;
import com.huawei.up.utils.ErrorCode;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jvk {
    private static final Object b = new Object();
    private static jvk e;
    private TransferFileInfo d;

    private jvk() {
    }

    public static jvk b() {
        jvk jvkVar;
        synchronized (b) {
            if (e == null) {
                e = new jvk();
            }
            jvkVar = e;
        }
        return jvkVar;
    }

    public boolean d(String str) {
        LogUtil.a("HwFileTransferTaskManagerPart", "deleteFolder = ", str);
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            return b(file.listFiles());
        }
        return true;
    }

    private boolean b(File[] fileArr) {
        boolean z = true;
        if (fileArr != null && fileArr.length > 0) {
            for (File file : fileArr) {
                z = file.delete();
                LogUtil.a("HwFileTransferTaskManagerPart", "filepath exist files,need delete, delete result = ", Boolean.valueOf(z));
            }
            jrx.c();
        }
        return z;
    }

    public boolean b(byte[] bArr) {
        String d;
        if (bArr == null || (d = cvx.d(bArr)) == null) {
            return false;
        }
        try {
            if (d.length() > 8) {
                return Integer.parseInt(d.substring(8), 16) == 110002;
            }
            return false;
        } catch (NumberFormatException unused) {
            ReleaseLogUtil.c("Dfx_HwFileTransferTaskManagerPart", "isLeoError NumberFormatException");
            return false;
        }
    }

    public void c(ExtendHandler extendHandler, List<Integer> list) {
        if (extendHandler == null || list == null) {
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 14;
        obtain.arg1 = list.get(0).intValue();
        obtain.arg2 = 10003;
        extendHandler.sendMessage(obtain);
    }

    public void b(byte[] bArr, MaintenaceInterface maintenaceInterface, IBaseResponseCallback iBaseResponseCallback) {
        int i;
        if (bArr == null || !(maintenaceInterface instanceof jrx)) {
            return;
        }
        String d = cvx.d(bArr);
        LogUtil.a("HwFileTransferTaskManagerPart", "callbackErrorCode messageHex = ", d);
        if (d != null) {
            try {
            } catch (NumberFormatException unused) {
                ReleaseLogUtil.c("Dfx_HwFileTransferTaskManagerPart", "callbackErrorCode NumberFormatException");
                i = 0;
            }
            if (d.length() < 9) {
                return;
            }
            i = Integer.parseInt(d.substring(8), 16);
            if (iBaseResponseCallback != null) {
                jsd.c(this.d, i, c(i), iBaseResponseCallback);
            }
        }
    }

    public String c(int i) {
        switch (i) {
            case 100000:
                return MonitorResult.SUCCESS;
            case 100001:
                return "UNKNOW ERROR";
            case ErrorCode.HWID_IS_STOPED /* 100002 */:
                return "NOT SUPPORT THIS REQUEST";
            case 100003:
                return "NO PERMISSION";
            case 100004:
                return "SYSTEM BUSY";
            case AdapterWithBottomView.TYPE_BOTTOM /* 100005 */:
                return "REQUEST FORMAT ERROR";
            case 100006:
                return "REQUEST PARAMETER ERROR";
            case 100007:
                return "MEMORY ERROR";
            case 100008:
                return "RESPONSE TIMEOUT";
            default:
                switch (i) {
                    case 104001:
                        return "单板不支持OTA升级";
                    case 104002:
                        return "低电量 ";
                    default:
                        LogUtil.h("HwFileTransferTaskManagerPart", "getErrorCodeInfo is default");
                        return "";
                }
        }
    }

    public void e(byte[] bArr, IBaseResponseCallback iBaseResponseCallback, MaintenaceInterface maintenaceInterface) {
        if (iBaseResponseCallback == null || bArr == null || maintenaceInterface == null) {
            return;
        }
        maintenaceInterface.onDestroyMaintenance();
        if (maintenaceInterface instanceof jrx) {
            String d = cvx.d(bArr);
            LogUtil.a("HwFileTransferTaskManagerPart", "dfxOrSleepGetFileNameCallback callbackErrorCode messageHex = ", d);
            if (d == null || d.length() < 9) {
                return;
            }
            int w = CommonUtil.w(d.substring(8));
            jsd.c(this.d, w, c(w), iBaseResponseCallback);
            return;
        }
        iBaseResponseCallback.d(10002, cvx.d(bArr));
    }

    public void a(byte[] bArr, IBaseResponseCallback iBaseResponseCallback, MaintenaceInterface maintenaceInterface) {
        if (iBaseResponseCallback == null || bArr == null || maintenaceInterface == null) {
            return;
        }
        maintenaceInterface.onDestroyMaintenance();
        if (maintenaceInterface instanceof jry) {
            maintenaceInterface.setMaintRetryResult(true);
        }
        if (maintenaceInterface instanceof jrx) {
            String d = cvx.d(bArr);
            LogUtil.a("HwFileTransferTaskManagerPart", "COMMAND_ID_MAINT_QUERY_FILE_INFORMATION messageHex = ", d);
            if (d == null || d.length() < 9) {
                return;
            }
            int w = CommonUtil.w(d.substring(8));
            jsd.c(this.d, w, c(w), iBaseResponseCallback);
            return;
        }
        iBaseResponseCallback.d(10002, cvx.d(bArr));
    }

    public void a(int i, int i2, ExtendHandler extendHandler) {
        if (extendHandler != null) {
            extendHandler.removeMessages(15);
            extendHandler.sendEmptyMessage(15, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(10);
        deviceCommand.setCommandID(1);
        byte[] g = cvx.g(i);
        byte[] g2 = cvx.g(i2);
        ByteBuffer allocate = ByteBuffer.allocate(17);
        allocate.put(new byte[]{2, 1, 4}).put((byte) -113).put(BaseType.Double).put(BaseType.Union).put((byte) 4).put(g).put(BaseType.Array).put((byte) 4).put(g2);
        deviceCommand.setDataLen(allocate.array().length);
        deviceCommand.setDataContent(allocate.array());
        c(deviceCommand);
    }

    public void d(int i, int i2, ExtendHandler extendHandler) {
        if (extendHandler != null) {
            extendHandler.removeMessages(15);
            extendHandler.sendEmptyMessage(15, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(10);
        deviceCommand.setCommandID(1);
        byte[] bArr = {7, 1, (byte) i2};
        byte[] b2 = cvx.b(i);
        byte[] bArr2 = {9, 2, b2[0], b2[1]};
        byte[] c = cvx.c(4);
        byte[] c2 = cvx.c(c.length + 8);
        ByteBuffer allocate = ByteBuffer.allocate(c2.length + 8 + c.length + 4);
        allocate.put(new byte[]{2, 1, 2}).put((byte) -122).put(c2).put(bArr).put((byte) -120).put(c).put(bArr2);
        deviceCommand.setDataLen(allocate.array().length);
        deviceCommand.setDataContent(allocate.array());
        c(deviceCommand);
    }

    public void c(int i, int i2, ExtendHandler extendHandler) {
        if (extendHandler != null) {
            extendHandler.removeMessages(15);
            extendHandler.sendEmptyMessage(15, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(10);
        deviceCommand.setCommandID(1);
        byte[] g = cvx.g(i);
        byte[] g2 = cvx.g(i2);
        ByteBuffer allocate = ByteBuffer.allocate(17);
        allocate.put(new byte[]{2, 1, 1}).put((byte) -125).put(BaseType.Double).put((byte) 4).put((byte) 4).put(g).put((byte) 5).put((byte) 4).put(g2);
        deviceCommand.setDataContent(allocate.array());
        deviceCommand.setDataLen(allocate.array().length);
        c(deviceCommand);
    }

    public void e(int i, ExtendHandler extendHandler) {
        if (extendHandler != null && i == 0) {
            LogUtil.a("HwFileTransferTaskManagerPart", "transfer next file");
            extendHandler.sendEmptyMessage(14);
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(10);
        deviceCommand.setCommandID(6);
        deviceCommand.setDataContent(new byte[]{1, 1, 1});
        deviceCommand.setDataLen(3);
        c(deviceCommand);
    }

    public void a(MaintenaceInterface maintenaceInterface, final ExtendHandler extendHandler) {
        DeviceCommand transferFileEndProcess = maintenaceInterface != null ? maintenaceInterface.transferFileEndProcess() : null;
        if (transferFileEndProcess != null) {
            c(transferFileEndProcess);
        }
        if (maintenaceInterface == null || extendHandler == null) {
            return;
        }
        if (maintenaceInterface instanceof jry) {
            maintenaceInterface.save2File(new IBaseResponseCallback() { // from class: jvk.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    extendHandler.sendEmptyMessage(14);
                }
            }, true);
            return;
        }
        if (maintenaceInterface instanceof jrx) {
            maintenaceInterface.save2File(new IBaseResponseCallback() { // from class: jvk.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    extendHandler.sendEmptyMessage(14);
                }
            }, true);
        } else if (maintenaceInterface instanceof jsb) {
            LogUtil.a("HwFileTransferTaskManagerPart", "mMaintenanceSleepOrDfxUtil instanceof PeriodRRIFileUtil");
            maintenaceInterface.save2File(new IBaseResponseCallback() { // from class: jvk.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("HwFileTransferTaskManagerPart", "PeriodRRIFileUtil WEAR OS onResponse");
                    extendHandler.sendEmptyMessage(14);
                }
            }, true);
        } else {
            extendHandler.sendEmptyMessage(14);
        }
    }

    public void d(ExtendHandler extendHandler) {
        if (extendHandler != null) {
            extendHandler.removeMessages(15);
            extendHandler.sendEmptyMessage(15, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(10);
        deviceCommand.setCommandID(2);
        ByteBuffer allocate = ByteBuffer.allocate(6);
        allocate.put(new byte[]{6, 1, 2}).put(new byte[]{7, 1, 3});
        deviceCommand.setDataContent(allocate.array());
        deviceCommand.setDataLen(allocate.array().length);
        c(deviceCommand);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0137  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e(int r18, com.huawei.hwcommonmodel.datatypes.TransferFileInfo r19, com.huawei.haf.handler.ExtendHandler r20) {
        /*
            Method dump skipped, instructions count: 329
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jvk.e(int, com.huawei.hwcommonmodel.datatypes.TransferFileInfo, com.huawei.haf.handler.ExtendHandler):void");
    }

    public void c(MaintenaceInterface maintenaceInterface, ExtendHandler extendHandler) {
        if (extendHandler != null) {
            extendHandler.removeMessages(15);
            extendHandler.sendEmptyMessage(15, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        if (maintenaceInterface != null) {
            DeviceCommand maintParametersCommand = maintenaceInterface.maintParametersCommand();
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManagerPart", "5.10.2 getMaintenanceSleepOrDfxParameters");
            c(maintParametersCommand);
        }
    }

    public void a(String str, int i, int i2, kbb kbbVar, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        c(str, i, i2, kbbVar, str2);
    }

    public void bKa_(IBaseResponseCallback iBaseResponseCallback, Message message, Map<Integer, Map<Long, double[]>> map, Map<Integer, Integer> map2, Map<Integer, List<Long>> map3) {
        HashMap hashMap = new HashMap(16);
        HashMap hashMap2 = new HashMap(16);
        HashMap hashMap3 = new HashMap(16);
        if (iBaseResponseCallback != null && map != null && map2 != null && map3 != null) {
            for (Map.Entry<Integer, Map<Long, double[]>> entry : map.entrySet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<Integer, Integer> entry2 : map2.entrySet()) {
                hashMap2.put(entry2.getKey(), entry2.getValue());
            }
            for (Map.Entry<Integer, List<Long>> entry3 : map3.entrySet()) {
                hashMap3.put(entry3.getKey(), entry3.getValue());
            }
            if (message != null && message.arg2 == 10003) {
                LogUtil.a("HwFileTransferTaskManagerPart", "no gps data");
                hashMap.put(Integer.valueOf(message.arg1), null);
                hashMap2.put(Integer.valueOf(message.arg1), null);
                hashMap3.put(Integer.valueOf(message.arg1), null);
            }
            iBaseResponseCallback.d(10000, new Object[]{hashMap, hashMap2, hashMap3});
            return;
        }
        LogUtil.h("HwFileTransferTaskManagerPart", "GpsHandle callback is null");
    }

    public void c(DeviceCommand deviceCommand) {
        blt.d("HwFileTransferTaskManagerPart", deviceCommand.getDataContent(), "sendCommand deviceCommand: ");
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
    }

    public boolean b(TransferFileInfo transferFileInfo) {
        return transferFileInfo != null && transferFileInfo.getType() == 0 && transferFileInfo.getTaskType() == 2;
    }

    public void c(byte[] bArr) {
        int i;
        LogUtil.a("HwFileTransferTaskManagerPart", "notifyCassiniDfx 5.10.12, CommonUtil.isReleaseVersion() = " + CommonUtil.bv());
        if (bArr != null && bArr.length == 8 && bArr[2] == Byte.MAX_VALUE) {
            String d = cvx.d(bArr);
            LogUtil.a("HwFileTransferTaskManagerPart", "Cassini messageHex = ", d);
            if (d != null) {
                try {
                } catch (NumberFormatException unused) {
                    ReleaseLogUtil.c("Dfx_HwFileTransferTaskManagerPart", "mDeviceResponseCallback NumberFormatException");
                    i = 0;
                }
                if (d.length() < 9) {
                    return;
                }
                i = Integer.parseInt(d.substring(8, d.length()), 16);
                LogUtil.a("HwFileTransferTaskManagerPart", "Cassini messageHex errorCode = " + i);
                if (i == 100000) {
                    LogUtil.a("HwFileTransferTaskManagerPart", "notifyCassiniDfx Cassini messageHex errorCode = 100000");
                    return;
                }
                LogUtil.a("HwFileTransferTaskManagerPart", "notifyCassiniDfx Cassini messageHex errorCode = " + i);
            }
        }
    }

    public void c(String str, int i, int i2, kbb kbbVar, String str2) {
        ByteBuffer byteBuffer;
        byte[] d = cvx.d(str);
        byte[] c = cvx.c(d.length);
        byte[] g = cvx.g(i);
        byte[] g2 = cvx.g(i2);
        if (kbbVar == null) {
            return;
        }
        String a2 = kbbVar.a();
        if (a2 != null && a2.contains("AW")) {
            byteBuffer = ByteBuffer.allocate(c.length + 1 + d.length);
            byteBuffer.put((byte) 1).put(c).put(d);
        } else {
            ByteBuffer allocate = ByteBuffer.allocate(c.length + 1 + d.length + 12);
            allocate.put((byte) 1).put(c).put(d).put((byte) 2).put((byte) 4).put(g).put((byte) 3).put((byte) 4).put(g2);
            byteBuffer = allocate;
        }
        LogUtil.a("HwFileTransferTaskManagerPart", "5.10.4 transferFileUnSuspendedPart: version:", a2);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(10);
        deviceCommand.setCommandID(4);
        deviceCommand.setDataContent(byteBuffer.array());
        deviceCommand.setmIdentify(str2);
        deviceCommand.setDataLen(byteBuffer.array().length);
        c(deviceCommand);
    }

    public void e(IBaseResponseCallback iBaseResponseCallback, Map<Integer, Map<Long, double[]>> map, Map<Integer, Integer> map2, Map<Integer, List<Long>> map3) {
        HashMap hashMap = new HashMap(16);
        HashMap hashMap2 = new HashMap(16);
        HashMap hashMap3 = new HashMap(16);
        if (iBaseResponseCallback == null || map == null || map2 == null || map3 == null) {
            return;
        }
        for (Map.Entry<Integer, Map<Long, double[]>> entry : map.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<Integer, Integer> entry2 : map2.entrySet()) {
            hashMap2.put(entry2.getKey(), entry2.getValue());
        }
        for (Map.Entry<Integer, List<Long>> entry3 : map3.entrySet()) {
            hashMap3.put(entry3.getKey(), entry3.getValue());
        }
        iBaseResponseCallback.d(AwarenessStatusCodes.AWARENESS_LOCATION_NOCACHE_CODE, new Object[]{hashMap, hashMap2, hashMap3});
    }

    public void c(byte[] bArr, int i) {
        if (CompileParameterUtil.a("SUPPORT_LOG_FEEDBACK") && !jsf.c()) {
            if (bArr == null || bArr.length <= 3 || bArr[2] != 1) {
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            try {
                String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10), "last_trigger_beta_log_time");
                LogUtil.a("HwFileTransferTaskManagerPart", "DeviceTriggerDFXReceiver startMainteFile: sharedPreference = ", b2);
                long parseLong = !TextUtils.isEmpty(b2) ? Long.parseLong(b2) : 0L;
                LogUtil.a("HwFileTransferTaskManagerPart", "DeviceTriggerDFXReceiver startMainteFile: curTime = ", Long.valueOf(currentTimeMillis), ", lastTime = ", Long.valueOf(parseLong), "Time interval is less than 2 hours");
                if (currentTimeMillis - parseLong < AiCoreSdkConstant.CHECK_SUPPORT_INTERVAL) {
                    return;
                }
            } catch (NumberFormatException unused) {
                ReleaseLogUtil.c("Dfx_HwFileTransferTaskManagerPart", "DeviceDFXReceiver NumberFormatException");
            }
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10), "last_trigger_beta_log_time", String.valueOf(currentTimeMillis), new StorageParams(0));
            jvd.d(BaseApplication.getContext()).d();
            return;
        }
        LogUtil.c("HwFileTransferTaskManagerPart", "the version is not beta. do not support 11");
    }

    public void d(IBaseResponseCallback iBaseResponseCallback, Map<Integer, Map<Long, double[]>> map, Map<Integer, Integer> map2, Map<Integer, List<Long>> map3) {
        HashMap hashMap = new HashMap(16);
        HashMap hashMap2 = new HashMap(16);
        HashMap hashMap3 = new HashMap(16);
        if (iBaseResponseCallback != null) {
            for (Map.Entry<Integer, Map<Long, double[]>> entry : map.entrySet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<Integer, Integer> entry2 : map2.entrySet()) {
                hashMap2.put(entry2.getKey(), entry2.getValue());
            }
            for (Map.Entry<Integer, List<Long>> entry3 : map3.entrySet()) {
                hashMap3.put(entry3.getKey(), entry3.getValue());
            }
            iBaseResponseCallback.d(AwarenessStatusCodes.AWARENESS_LOCATION_NOCACHE_CODE, new Object[]{hashMap, hashMap2, hashMap3});
        }
    }

    public void c(byte[] bArr, IBaseResponseCallback iBaseResponseCallback, ExtendHandler extendHandler) {
        if (bArr != null) {
            String d = cvx.d(bArr);
            LogUtil.a("HwFileTransferTaskManagerPart", "COMMAND_ID_MAINT_QUERY_FILE_INFORMATION messageHex = ", d);
            if (d == null || d.length() < 9) {
                return;
            }
            int w = CommonUtil.w(d.substring(8));
            if (iBaseResponseCallback != null) {
                jsd.c(this.d, w, c(w), iBaseResponseCallback);
            }
        }
        if (extendHandler != null) {
            extendHandler.removeMessages(7);
        }
    }

    public void d(IBaseResponseCallback iBaseResponseCallback, MaintenaceInterface maintenaceInterface) {
        if (iBaseResponseCallback != null) {
            if (maintenaceInterface != null) {
                maintenaceInterface.cutFolder(jrx.b, jrx.c);
                if (maintenaceInterface instanceof jry) {
                    maintenaceInterface.setMaintRetryResult(true);
                }
            }
            jsd.c(this.d, 10002, "package error", iBaseResponseCallback);
        }
    }

    public void e(TransferFileInfo transferFileInfo, String str) {
        if (transferFileInfo == null || !b(transferFileInfo)) {
            return;
        }
        LogUtil.a("HwFileTransferTaskManagerPart", "initContinueTask isActiveDfx");
        c("DEVICE_MACADDRESS", str);
        c("TRANSMITTED_SIZE", Integer.toString(0));
        c("IS_CONTINUE", "false");
        c("CONTINUE_LOG_LIST", (String) null);
        c("CONTINUE_LOG_NAME", (String) null);
        if (d(jrx.f14040a)) {
            return;
        }
        LogUtil.h("HwFileTransferTaskManagerPart", "mDeviceMac deleteFolder failed");
    }

    public void e(StringBuffer stringBuffer, ArrayList arrayList) {
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManagerPart", "filelist is first null");
        if (stringBuffer != null) {
            c("CONTINUE_LOG_LIST", stringBuffer.toString());
        }
        c("TRANSMITTED_SIZE", Integer.toString(0));
        c("IS_CONTINUE", "false");
        if (arrayList != null && !arrayList.isEmpty()) {
            c("CONTINUE_LOG_NAME", arrayList.get(0).toString());
        }
        if (d(jrx.f14040a)) {
            return;
        }
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManagerPart", "new task and compareList deleteFolder failed");
    }

    public boolean c(boolean z, TransferFileInfo transferFileInfo, long j, int i) {
        if (transferFileInfo != null && z && b(transferFileInfo)) {
            if ("true".equals(e("IS_CONTINUE"))) {
                return c(i, j);
            }
            c("LOG_CREATE_TIME", String.valueOf(j));
            c("TRANSMITTED_SIZE", String.valueOf(0));
            if (!d(jrx.f14040a)) {
                ReleaseLogUtil.e("Dfx_HwFileTransferTaskManagerPart", "Comparelist deletefolder failed");
            }
        }
        return false;
    }

    private boolean c(int i, long j) {
        if (i == 1) {
            if (j == CommonUtil.n(BaseApplication.getContext(), e("LOG_CREATE_TIME"))) {
                LogUtil.a("HwFileTransferTaskManagerPart", "mFileCreateTime is continue");
                return true;
            }
            if (!d(jrx.f14040a)) {
                LogUtil.a("HwFileTransferTaskManagerPart", "Comparelist deletefolder failed");
            }
        }
        return false;
    }

    public void b(TransferFileInfo transferFileInfo, IBaseResponseCallback iBaseResponseCallback, MaintenaceInterface maintenaceInterface) {
        if (maintenaceInterface != null) {
            maintenaceInterface.onDestroyMaintenance();
            if (transferFileInfo != null && b(transferFileInfo)) {
                c("IS_CONTINUE", "false");
                c("TRANSMITTED_SIZE", Integer.toString(0));
                c("CONTINUE_LOG_NAME", (String) null);
                c("CONTINUE_LOG_LIST", (String) null);
                maintenaceInterface.cutFolder(jrx.f14040a, jrx.c);
                jvh.c(jvh.d(), jvh.b());
            }
            if (iBaseResponseCallback != null) {
                maintenaceInterface.cutFolder(jrx.b, jrx.c);
                LogUtil.a("HwFileTransferTaskManagerPart", "dataPath ", maintenaceInterface.getmTransferDataContentPath());
                jvo jvoVar = new jvo();
                jvoVar.b(maintenaceInterface.getmTransferDataContentPath());
                jvoVar.a(maintenaceInterface.getmTransferStateContentPath());
                jsd.c(this.d, 10000, jvoVar, iBaseResponseCallback);
            } else {
                ReleaseLogUtil.d("Dfx_HwFileTransferTaskManagerPart", "callback is null");
            }
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManagerPart", "maintenance success");
            return;
        }
        LogUtil.h("HwFileTransferTaskManagerPart", "maintenanceSleepOrDfxUtil is null");
    }

    public void c(TransferFileInfo transferFileInfo, IBaseResponseCallback iBaseResponseCallback, MaintenaceInterface maintenaceInterface) {
        if (maintenaceInterface != null) {
            maintenaceInterface.onDestroyMaintenance();
            if (iBaseResponseCallback != null) {
                LogUtil.a("HwFileTransferTaskManagerPart", "dfxOrSleepSendLogWifiPart UploadLogUtil.ENCYPTION_PATH final = " + jsd.b.substring(0, jsd.b.length() - 1));
                maintenaceInterface.cutFolder(jrx.b, jsd.b.substring(0, jsd.b.length() + (-1)));
                LogUtil.a("HwFileTransferTaskManagerPart", "dfxOrSleepSendLogWifiPart dataPath " + maintenaceInterface.getmTransferDataContentPath());
                jvo jvoVar = new jvo();
                jvoVar.b(maintenaceInterface.getmTransferDataContentPath());
                jvoVar.a(maintenaceInterface.getmTransferStateContentPath());
                jsd.c(this.d, 10000, jvoVar, iBaseResponseCallback);
            } else {
                ReleaseLogUtil.d("Dfx_HwFileTransferTaskManagerPart", "dfxOrSleepSendLogWifiPart callback is null");
            }
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManagerPart", "dfxOrSleepSendLogWifiPart maintenance success");
            return;
        }
        LogUtil.h("HwFileTransferTaskManagerPart", "dfxOrSleepSendLogWifiPart maintenanceSleepOrDfxUtil is null");
    }

    public void d(boolean z, IBaseResponseCallback iBaseResponseCallback) {
        if (z || iBaseResponseCallback == null) {
            return;
        }
        LogUtil.a("HwFileTransferTaskManagerPart", "is LEO return callback");
        iBaseResponseCallback.d(110002, null);
    }

    public void c(String str) {
        byte[] d = cvx.d(str);
        byte[] c = cvx.c(d.length);
        ByteBuffer allocate = ByteBuffer.allocate(c.length + 1 + d.length);
        allocate.put((byte) 1).put(c).put(d);
        LogUtil.a("HwFileTransferTaskManagerPart", "5.10.3 queryFileInformation");
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(10);
        deviceCommand.setCommandID(3);
        deviceCommand.setDataContent(allocate.array());
        deviceCommand.setDataLen(allocate.array().length);
        c(deviceCommand);
    }

    public void c(StringBuffer stringBuffer, ArrayList arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManagerPart", "filelist is different from the previou");
        if (stringBuffer != null) {
            c("CONTINUE_LOG_LIST", stringBuffer.toString());
        }
        c("TRANSMITTED_SIZE", Integer.toString(0));
        c("IS_CONTINUE", "false");
        c("CONTINUE_LOG_NAME", arrayList.get(0).toString());
        if (d(jrx.f14040a)) {
            return;
        }
        ReleaseLogUtil.d("Dfx_HwFileTransferTaskManagerPart", "compareList deleteFolder failed");
    }

    public void b(IBaseResponseCallback iBaseResponseCallback, MaintenaceInterface maintenaceInterface) {
        if (maintenaceInterface != null) {
            maintenaceInterface.cutFolder(jrx.b, jrx.c);
            if (maintenaceInterface instanceof jry) {
                maintenaceInterface.setMaintRetryResult(true);
            }
        }
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(10001, "30s timeout");
        }
    }

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(10000, new Object[]{new HashMap(16), new HashMap(16), new HashMap(16)});
        } else {
            LogUtil.h("HwFileTransferTaskManagerPart", "callback is null");
        }
    }

    public void e(String str, IBaseResponseCallback iBaseResponseCallback, int i, int i2, int i3) {
        if (TextUtils.isEmpty(str) || !str.contains("sleep_data")) {
            return;
        }
        LogUtil.a("HwFileTransferTaskManagerPart", "progress is sleep_data");
        if (iBaseResponseCallback == null || i == 0 || i3 > i) {
            return;
        }
        LogUtil.a("HwFileTransferTaskManagerPart", "progress mCallback is not null");
        jvo jvoVar = new jvo();
        jvoVar.c((i2 * 100) / i);
        iBaseResponseCallback.d(20000, jvoVar);
    }

    public void e(IBaseResponseCallback iBaseResponseCallback, MaintenaceInterface maintenaceInterface, TransferFileInfo transferFileInfo, int i) {
        if (iBaseResponseCallback != null) {
            if (maintenaceInterface != null) {
                if (transferFileInfo != null && b(transferFileInfo) && i == 1) {
                    c("IS_CONTINUE", "true");
                    jvh.c(jvh.d(), jvh.b());
                }
                maintenaceInterface.cutFolder(jrx.b, jrx.c);
            }
            if (maintenaceInterface instanceof jry) {
                maintenaceInterface.setMaintRetryResult(true);
            }
            jsd.c(this.d, 10002, "package error", iBaseResponseCallback);
        }
    }

    public void c(final MaintenaceInterface maintenaceInterface, final ExtendHandler extendHandler, final TransferFileInfo transferFileInfo, final int i) {
        if (extendHandler != null) {
            extendHandler.removeMessages(13);
            extendHandler.sendEmptyMessage(13, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        if (maintenaceInterface != null) {
            maintenaceInterface.save2File(new IBaseResponseCallback() { // from class: jvk.9
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    ExtendHandler extendHandler2;
                    LogUtil.a("HwFileTransferTaskManagerPart", "onResponse fileTransferDone: ", Integer.valueOf(i2));
                    ExtendHandler extendHandler3 = extendHandler;
                    if (extendHandler3 != null) {
                        extendHandler3.removeMessages(13);
                    }
                    if (i2 == 10001 && (extendHandler2 = extendHandler) != null) {
                        extendHandler2.sendEmptyMessage(12);
                        return;
                    }
                    TransferFileInfo transferFileInfo2 = transferFileInfo;
                    if (transferFileInfo2 != null && jvk.this.b(transferFileInfo2) && i == 1) {
                        maintenaceInterface.cutFolder(jrx.f14040a, jrx.c);
                        jvk.this.c("IS_CONTINUE", "false");
                        jvk.this.c("TRANSMITTED_SIZE", Integer.toString(0));
                    }
                    ExtendHandler extendHandler4 = extendHandler;
                    if (extendHandler4 != null) {
                        extendHandler4.sendEmptyMessage(14);
                    }
                }
            }, true);
        }
    }

    public void b(final int[] iArr, final IBaseResponseCallback iBaseResponseCallback, final MaintenaceInterface maintenaceInterface, final TransferFileInfo transferFileInfo, final int i) {
        if (maintenaceInterface == null || iBaseResponseCallback == null) {
            return;
        }
        if (maintenaceInterface instanceof jrx) {
            ReleaseLogUtil.e("Dfx_HwFileTransferTaskManagerPart", "Dfx callback fail and save the collected log locally");
            maintenaceInterface.save2File(new IBaseResponseCallback() { // from class: jvk.7
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    TransferFileInfo transferFileInfo2 = transferFileInfo;
                    if (transferFileInfo2 != null && jvk.this.b(transferFileInfo2) && i == 1) {
                        jvk.this.c("IS_CONTINUE", "true");
                        jvh.c(jvh.d(), jvh.b());
                    }
                    maintenaceInterface.cutFolder(jrx.b, jrx.c);
                    jsd.c(jvk.this.d, iArr[0], jvk.this.c(iArr[0]), iBaseResponseCallback);
                }
            }, true);
        } else {
            maintenaceInterface.cutFolder(jrx.b, jrx.c);
            int i2 = iArr[0];
            iBaseResponseCallback.d(i2, c(i2));
        }
    }

    public void a(final IBaseResponseCallback iBaseResponseCallback, final MaintenaceInterface maintenaceInterface, final TransferFileInfo transferFileInfo, final int i) {
        if (maintenaceInterface == null || iBaseResponseCallback == null) {
            return;
        }
        maintenaceInterface.save2File(new IBaseResponseCallback() { // from class: jvk.10
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                TransferFileInfo transferFileInfo2 = transferFileInfo;
                if (transferFileInfo2 != null && jvk.this.b(transferFileInfo2) && i == 1) {
                    jvk.this.c("IS_CONTINUE", "true");
                    jvh.c(jvh.d(), jvh.b());
                }
                maintenaceInterface.cutFolder(jrx.b, jrx.c);
                jsd.c(jvk.this.d, 10001, "30s timeout", iBaseResponseCallback);
            }
        }, true);
    }

    public void a(MaintenaceInterface maintenaceInterface, final ExtendHandler extendHandler, TransferFileInfo transferFileInfo, int i) {
        DeviceCommand transferFileEndProcess = maintenaceInterface != null ? maintenaceInterface.transferFileEndProcess() : null;
        if (transferFileEndProcess != null) {
            LogUtil.a("HwFileTransferTaskManagerPart", "addSleepOrDfxPlayDataToList transferFileEndProcess sendDeviceData");
            c(transferFileEndProcess);
        }
        if (extendHandler == null || maintenaceInterface == null) {
            return;
        }
        if (maintenaceInterface instanceof jry) {
            maintenaceInterface.save2File(new IBaseResponseCallback() { // from class: jvk.8
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    extendHandler.sendEmptyMessage(14);
                }
            }, true);
            return;
        }
        if (maintenaceInterface instanceof jrx) {
            c(maintenaceInterface, extendHandler, transferFileInfo, i);
        } else if (maintenaceInterface instanceof jsb) {
            LogUtil.a("HwFileTransferTaskManagerPart", "addSleepOrDfxPlayDataToList mMaintenanceSleepOrDfxUtil instanceof PeriodRRIFileUtil");
            maintenaceInterface.save2File(new IBaseResponseCallback() { // from class: jvk.6
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    LogUtil.a("HwFileTransferTaskManagerPart", "PeriodRRIFileUtil save2File onResponse");
                    extendHandler.sendEmptyMessage(14);
                }
            }, true);
        } else {
            extendHandler.sendEmptyMessage(14);
        }
    }

    public void b(String str, String str2) {
        byte[] d = cvx.d(str);
        byte[] c = cvx.c(d.length);
        ByteBuffer allocate = ByteBuffer.allocate(c.length + 1 + d.length);
        allocate.put((byte) 1).put(c).put(d);
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(10);
        deviceCommand.setCommandID(3);
        deviceCommand.setmIdentify(str2);
        deviceCommand.setDataContent(allocate.array());
        deviceCommand.setDataLen(allocate.array().length);
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManagerPart", "5.10.3 querySleepOrDfxFileInformationPart");
        c(deviceCommand);
    }

    public void a(String str) {
        String e2 = cvx.e(255);
        String e3 = cvx.e(3);
        String e4 = cvx.e(0);
        String b2 = cvx.b(0L);
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (String str2 : Arrays.asList(e2, e3, b2, e4)) {
            sb.append(cvx.e(i) + cvx.e(str2.length() / 2) + str2);
            i++;
        }
        String sb2 = sb.toString();
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(10);
        deviceCommand.setCommandID(10);
        deviceCommand.setmIdentify(str);
        deviceCommand.setDataContent(cvx.a(sb2));
        deviceCommand.setDataLen(cvx.a(sb2).length);
        c(deviceCommand);
    }

    public void a(final int i, final IBaseResponseCallback iBaseResponseCallback, final MaintenaceInterface maintenaceInterface, final TransferFileInfo transferFileInfo, final int i2) {
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManagerPart", "Dfx 0a04 returns an error, save the collected log locally");
        if (maintenaceInterface == null || iBaseResponseCallback == null) {
            return;
        }
        maintenaceInterface.save2File(new IBaseResponseCallback() { // from class: jvk.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                TransferFileInfo transferFileInfo2 = transferFileInfo;
                if (transferFileInfo2 != null && jvk.this.b(transferFileInfo2) && i2 == 1) {
                    jvk.this.c("IS_CONTINUE", "true");
                    jvh.c(jvh.d(), jvh.b());
                }
                maintenaceInterface.cutFolder(jrx.b, jrx.c);
                jsd.c(jvk.this.d, i, jvk.this.c(i), iBaseResponseCallback);
            }
        }, true);
    }

    public void c(TransferFileInfo transferFileInfo) {
        if (transferFileInfo == null || !b(transferFileInfo)) {
            return;
        }
        c("TRANSMITTED_SIZE", Integer.toString(0));
        c("IS_CONTINUE", "false");
        c("CONTINUE_LOG_LIST", (String) null);
        c("CONTINUE_LOG_NAME", (String) null);
        c("LOG_CREATE_TIME", (String) null);
        jvh.c(jvh.d(), jvh.b());
    }

    public void c(ArrayList arrayList) {
        c("IS_CONTINUE", "false");
        c("TRANSMITTED_SIZE", Integer.toString(0));
        if (arrayList != null && !arrayList.isEmpty()) {
            c("CONTINUE_LOG_NAME", arrayList.get(0).toString());
        } else {
            c("CONTINUE_LOG_NAME", (String) null);
        }
    }

    public void b(IBaseResponseCallback iBaseResponseCallback, MaintenaceInterface maintenaceInterface, String str, String str2) {
        if (iBaseResponseCallback == null || maintenaceInterface == null) {
            return;
        }
        maintenaceInterface.cutFolder(jrx.b, jrx.c);
        if (maintenaceInterface instanceof jry) {
            maintenaceInterface.setMaintRetryResult(true);
        }
        jsd.c(this.d, 10002, b().a(str, str2), iBaseResponseCallback);
    }

    public void a(ArrayList arrayList) {
        b().c("IS_CONTINUE", "false");
        b().c("TRANSMITTED_SIZE", Integer.toString(0));
        if (arrayList != null && !arrayList.isEmpty()) {
            b().c("CONTINUE_LOG_NAME", arrayList.get(0).toString());
        } else {
            b().c("CONTINUE_LOG_NAME", (String) null);
        }
    }

    public String a(String str, String str2) {
        String str3 = str + ";" + str2;
        ReleaseLogUtil.e("Dfx_HwFileTransferTaskManagerPart", "reportStatusFileList result = ", str3);
        return str3;
    }

    public int c(String str, String str2) {
        return SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(1014), str, str2, (StorageParams) null);
    }

    public String e(String str) {
        return SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(1014), str);
    }

    public void e(TransferFileInfo transferFileInfo, int i) {
        if (!b(transferFileInfo) || i == -1) {
            return;
        }
        if (i == 1) {
            c("IS_CONTINUE", "true");
        } else {
            c("IS_CONTINUE", "false");
        }
    }

    public boolean d(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return true;
            default:
                LogUtil.h("HwFileTransferTaskManagerPart", "other command.");
                return false;
        }
    }

    public void c(ExtendHandler extendHandler, int i) {
        if (extendHandler != null) {
            extendHandler.removeMessages(i);
        }
    }

    public void c(int i, ExtendHandler extendHandler) {
        if (extendHandler != null) {
            Message obtain = Message.obtain();
            obtain.what = 14;
            obtain.arg1 = i;
            obtain.arg2 = 10003;
            extendHandler.sendMessage(obtain);
        }
    }

    public void d(TransferFileInfo transferFileInfo) {
        this.d = transferFileInfo;
    }
}
