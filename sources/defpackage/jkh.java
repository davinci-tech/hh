package defpackage;

import android.content.Context;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import health.compact.a.CommonUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jkh extends HwBaseManager implements BluetoothDataReceiveCallback {
    private static jkh b;
    private int d;
    private jfq e;
    private cwl i;
    private static Map<Integer, List<IBaseResponseCallback>> c = new HashMap(16);

    /* renamed from: a, reason: collision with root package name */
    private static List<Integer> f13907a = new ArrayList(Arrays.asList(1, 2));

    private jkh(Context context) {
        super(context);
        this.i = new cwl();
        jfq c2 = jfq.c();
        this.e = c2;
        if (c2 != null) {
            c2.e(34, this);
        } else {
            LogUtil.h("HwOneLevelMenuManager", "HwWearableManager() hwDeviceConfigManager is null");
        }
    }

    private static Object d() {
        Map<Integer, List<IBaseResponseCallback>> map;
        synchronized (jkh.class) {
            map = c;
        }
        return map;
    }

    public static jkh c(Context context) {
        jkh jkhVar;
        synchronized (d()) {
            LogUtil.a("OneLevelMenuManagerActivity", "instance = ", b);
            jkh jkhVar2 = b;
            if (jkhVar2 != null) {
                jkhVar2.onDestroy();
            }
            if (c.isEmpty()) {
                Iterator<Integer> it = f13907a.iterator();
                while (it.hasNext()) {
                    c.put(it.next(), new ArrayList(16));
                }
            }
            if (b == null && context != null) {
                b = new jkh(BaseApplication.getContext());
            }
            jkhVar = b;
        }
        return jkhVar;
    }

    private void e(byte[] bArr) {
        Object a2;
        int d = d(bArr);
        if (d == 100000) {
            this.d = 0;
        } else {
            this.d = -1;
        }
        LogUtil.a("HwOneLevelMenuManager", "getResult(): ", cvx.d(bArr));
        byte b2 = bArr[1];
        if (b2 == 1) {
            a2 = a(bArr);
        } else if (b2 == 2) {
            a2 = Integer.valueOf(d);
        } else {
            LogUtil.h("HwOneLevelMenuManager", "not support the commandIdList");
            a2 = null;
        }
        b(bArr[1], this.d, a2);
    }

    private Object a(byte[] bArr) {
        String d = cvx.d(bArr);
        HashMap hashMap = new HashMap(16);
        if (d != null && d.length() > 4) {
            try {
                for (cwd cwdVar : this.i.a(d.substring(4, d.length())).e()) {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    if (a2 == -1) {
                        LogUtil.a("HwOneLevelMenuManager", "getResult() OneLevelMenuConstants.ONE_LEVEL_MENU_ERROR_CODE");
                    } else if (a2 == 1) {
                        this.d = 0;
                        hashMap.put("all", cvx.a(cwdVar.c()));
                    } else if (a2 == 2) {
                        this.d = 0;
                        hashMap.put(JsbMapKeyNames.H5_TEXT_DOWNLOAD_OPEN, cvx.a(cwdVar.c()));
                    } else {
                        LogUtil.h("HwOneLevelMenuManager", "not support the typeId");
                    }
                }
                return hashMap;
            } catch (cwg e) {
                LogUtil.b("HwOneLevelMenuManager", "getResult() command id mune set error = ", e.getMessage());
            }
        }
        return hashMap;
    }

    private void b(int i, int i2, Object obj) {
        synchronized (d()) {
            Map<Integer, List<IBaseResponseCallback>> map = c;
            if (map == null) {
                LogUtil.h("HwOneLevelMenuManager", "procCallback callback, commandCallback is null");
                return;
            }
            List<IBaseResponseCallback> list = map.get(Integer.valueOf(i));
            if (list != null) {
                if (obj != null && list.size() != 0) {
                    LogUtil.a("HwOneLevelMenuManager", "procCallback callback,commandIdList =", Integer.valueOf(i));
                    list.get(0).d(i2, obj);
                    list.remove(0);
                } else if (list.size() != 0) {
                    list.get(0).d(100001, "UNKNOWN_ERROR");
                    list.remove(0);
                }
            } else {
                LogUtil.a("HwOneLevelMenuManager", "procCallback callback,callbackList is null");
            }
        }
    }

    public void a(String str, List<Integer> list, IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(34);
        deviceCommand.setCommandID(2);
        deviceCommand.setmIdentify(str);
        StringBuffer stringBuffer = new StringBuffer(16);
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            stringBuffer.append(cvx.e(it.next().intValue()));
        }
        String stringBuffer2 = stringBuffer.toString();
        String d = cvx.d(stringBuffer2.length() / 2);
        String e = cvx.e(2);
        ByteBuffer allocate = ByteBuffer.allocate((e.length() / 2) + (d.length() / 2) + (stringBuffer2.length() / 2));
        allocate.put(cvx.a(e + d + stringBuffer2));
        LogUtil.a("DEVMGR_SETTING", "HwOneLevelMenuManager", " transmitFile() , byteBufferAll", cvx.d(allocate.array()));
        a(deviceCommand, allocate, 2, iBaseResponseCallback);
    }

    public void b(String str, IBaseResponseCallback iBaseResponseCallback) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(34);
        deviceCommand.setCommandID(1);
        deviceCommand.setmIdentify(str);
        String d = cvx.d(0);
        String e = cvx.e(1);
        String d2 = cvx.d(0);
        String e2 = cvx.e(2);
        int length = d.length() / 2;
        int length2 = e.length() / 2;
        ByteBuffer allocate = ByteBuffer.allocate(length + length2 + (e2.length() / 2) + (d2.length() / 2));
        allocate.put(cvx.a(e));
        allocate.put(cvx.a(d));
        allocate.put(cvx.a(e2));
        allocate.put(cvx.a(d2));
        LogUtil.c("HwOneLevelMenuManager", "getBTCInfo() byteBufferAll = ", cvx.d(allocate.array()));
        a(deviceCommand, allocate, 1, iBaseResponseCallback);
    }

    private void a(DeviceCommand deviceCommand, ByteBuffer byteBuffer, int i, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (d()) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = c.get(Integer.valueOf(i));
                if (list != null) {
                    LogUtil.a("HwOneLevelMenuManager", "addToList(), callbacks != null");
                    list.add(iBaseResponseCallback);
                } else {
                    LogUtil.h("HwOneLevelMenuManager", "addToList() callbacks is null");
                }
            } else {
                LogUtil.h("HwOneLevelMenuManager", "addToList() callback is null");
            }
        }
        deviceCommand.setDataLen(byteBuffer.array().length);
        deviceCommand.setDataContent(byteBuffer.array());
        this.e.b(deviceCommand);
    }

    private static void a() {
        b = null;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
        this.e.d(34);
        synchronized (d()) {
            for (Integer num : f13907a) {
                if (c.get(num) != null) {
                    c.get(num).clear();
                }
            }
        }
        LogUtil.a("HwOneLevelMenuManager", "onDestroy()");
        a();
        LogUtil.a("HwOneLevelMenuManager", "onDestroy() complete");
    }

    private int d(byte[] bArr) {
        String d = cvx.d(bArr);
        if (d == null || d.length() <= 8) {
            return 0;
        }
        try {
            return Integer.parseInt(d.substring(8, d.length()), 16);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 34;
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr != null) {
            LogUtil.a("HwOneLevelMenuManager", "OneLevelMenu manager receive data = ", cvx.d(bArr));
            e(bArr);
        } else {
            LogUtil.h("HwOneLevelMenuManager", "data is null");
        }
    }
}
