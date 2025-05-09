package defpackage;

import android.util.SparseBooleanArray;
import com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import health.compact.a.LogUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class bgz extends HandshakeGeneralCommandBase {
    private bmn j = new bmn();
    public static final ConcurrentHashMap<String, Integer> b = new ConcurrentHashMap<>(2);

    /* renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap<String, SparseBooleanArray> f369a = new ConcurrentHashMap<>(2);
    public static final ConcurrentHashMap<String, Boolean> d = new ConcurrentHashMap<>(2);
    public static final ConcurrentHashMap<String, Boolean> i = new ConcurrentHashMap<>(2);
    public static final ConcurrentHashMap<String, List<bjd>> c = new ConcurrentHashMap<>(2);
    public static final ConcurrentHashMap<String, List<Integer>> e = new ConcurrentHashMap<>(2);
    private static int h = 0;

    bgz() {
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        return d(deviceInfo);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        biy biyVar = new biy();
        if (!bhh.c(deviceInfo, biuVar)) {
            LogUtil.a("DeviceCreateDualChannelCommand", "Get ServiceCapability parameterCheck Failed.");
            biyVar.c(13);
            biyVar.a(50160);
            return biyVar;
        }
        byte[] a2 = biuVar.a();
        blt.d("DeviceCreateDualChannelCommand", a2, "processReceivedData dataInfos:");
        if (!bhh.d(a2)) {
            LogUtil.a("DeviceCreateDualChannelCommand", "processReceivedData checkResponseCode Failed.");
            biyVar.c(13);
            biyVar.a(50160);
            return biyVar;
        }
        LogUtil.c("DeviceCreateDualChannelCommand", "processReceivedData checkResponseCode Success.");
        a(deviceInfo, a2);
        DeviceCapability b2 = bjx.a().b(deviceInfo.getDeviceMac());
        if (b2 == null) {
            LogUtil.a("DeviceCreateDualChannelCommand", "processReceivedData deviceCapability is null.");
            biyVar.c(13);
            biyVar.a(50160);
            return biyVar;
        }
        bjc a3 = a(b2);
        if (!a3.b()) {
            LogUtil.a("DeviceCreateDualChannelCommand", "judgeSetTimeZoneIdCapability Failed");
            biyVar.c(13);
            biyVar.a(50160);
            return biyVar;
        }
        this.mNextCommand = a3.d();
        if (this.mNextCommand == null) {
            biyVar.c(53);
            biyVar.a(100000);
            return biyVar;
        }
        biyVar.c(12);
        biyVar.a(100000);
        return biyVar;
    }

    private void a(DeviceInfo deviceInfo, byte[] bArr) {
        if (deviceInfo == null) {
            LogUtil.a("DeviceCreateDualChannelCommand", "handleDualSocket deviceInfo is invalid.");
            return;
        }
        String d2 = blq.d(bArr);
        if (bArr == null || bArr.length < 4) {
            LogUtil.a("DeviceCreateDualChannelCommand", "handleDualSocket dataInfos is error");
            return;
        }
        ConcurrentHashMap<String, Boolean> concurrentHashMap = i;
        boolean booleanValue = concurrentHashMap.get(deviceInfo.getDeviceMac()) != null ? concurrentHashMap.get(deviceInfo.getDeviceMac()).booleanValue() : false;
        LogUtil.c("DeviceCreateDualChannelCommand", "isSupportExtendCommand: ", Boolean.valueOf(booleanValue));
        if (booleanValue) {
            d(deviceInfo, d2);
        } else {
            c(deviceInfo, d2);
        }
    }

    private void d(DeviceInfo deviceInfo, String str) {
        ArrayList arrayList = new ArrayList();
        try {
            bmj c2 = this.j.c(str.substring(4));
            for (bmi bmiVar : c2.b()) {
                int e2 = bli.e(bmiVar.e());
                if (e2 == 1) {
                    LogUtil.c("DeviceCreateDualChannelCommand", "dealNewParse dual socket channel is ", Integer.valueOf(bli.e(bmiVar.c())));
                    int e3 = bli.e(bmiVar.c());
                    h = e3;
                    LogUtil.c("DeviceCreateDualChannelCommand", "dealNewParse channel is ", Integer.valueOf(e3));
                    bib.a().d(deviceInfo, h);
                    b.put(deviceInfo.getDeviceMac(), Integer.valueOf(h));
                } else if (e2 == 2) {
                    d(deviceInfo.getDeviceMac(), bmiVar.c());
                } else if (e2 == 3) {
                    e(deviceInfo.getDeviceMac(), bmiVar.c());
                } else {
                    LogUtil.a("DeviceCreateDualChannelCommand", "dealNewParse deal else value");
                }
            }
            List<bmj> e4 = c2.e();
            if (e4 != null && !e4.isEmpty()) {
                for (bmj bmjVar : e4.get(0).e()) {
                    bjd bjdVar = new bjd();
                    e(bmjVar, bjdVar);
                    arrayList.add(bjdVar);
                }
                LogUtil.c("DeviceCreateDualChannelCommand", "deviceIdentify: ", blt.b(deviceInfo.getDeviceMac()), " mcuCommandList: ", arrayList);
                c.put(deviceInfo.getDeviceMac(), arrayList);
                return;
            }
            LogUtil.a("DeviceCreateDualChannelCommand", "dealNewParse tlvFathers is null.");
        } catch (bmk unused) {
            LogUtil.e("DeviceCreateDualChannelCommand", "dealNewParse handleDualSocket TlvException");
        } catch (NumberFormatException unused2) {
            LogUtil.e("DeviceCreateDualChannelCommand", "dealNewParse handleDualSocket NumberFormatException");
        }
    }

    private void e(bmj bmjVar, bjd bjdVar) {
        if (bmjVar == null || bjdVar == null) {
            LogUtil.a("DeviceCreateDualChannelCommand", "parseMcuCommandData tlvFather or mcuCommand is null");
            return;
        }
        for (bmi bmiVar : bmjVar.b()) {
            int e2 = bli.e(bmiVar.e());
            if (e2 == 6) {
                bjdVar.c(bli.e(bmiVar.c()));
            } else if (e2 == 7) {
                d(bmiVar.c(), bjdVar);
            } else if (e2 == 8) {
                String d2 = blq.d(bmiVar.c());
                if (d2 == null) {
                    LogUtil.e("DeviceCreateDualChannelCommand", "packageNames is null");
                    return;
                }
                List<String> arrayList = new ArrayList<>();
                if (d2.contains("#")) {
                    arrayList = Arrays.asList(d2.split("#"));
                } else {
                    arrayList.add(d2);
                }
                bjdVar.c(arrayList);
            } else {
                LogUtil.a("DeviceCreateDualChannelCommand", "parseMcuCommandData deal else value");
            }
        }
    }

    private void d(String str, bjd bjdVar) {
        if (str == null) {
            LogUtil.e("DeviceCreateDualChannelCommand", "value is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        int length = str.length();
        for (int i2 = 2; i2 <= length; i2 += 2) {
            arrayList.add(Integer.valueOf(bli.e(str.substring(i2 - 2, i2))));
        }
        bjdVar.d(arrayList);
    }

    private void e(String str, String str2) {
        if (str != null && str2 != null) {
            if (str2.length() >= 2) {
                ArrayList arrayList = new ArrayList();
                int length = str2.length();
                for (int i2 = 2; i2 <= length; i2 += 2) {
                    arrayList.add(Integer.valueOf(bli.e(str2.substring(i2 - 2, i2))));
                }
                e.put(str, arrayList);
                LogUtil.c("DeviceCreateDualChannelCommand", "updateFileTypes, allFileTypes: ", arrayList);
                return;
            }
        }
        LogUtil.a("DeviceCreateDualChannelCommand", "updateFileTypes input is invalid.");
    }

    private void c(DeviceInfo deviceInfo, String str) {
        try {
            List<bmi> b2 = this.j.c(str.substring(4)).b();
            if (b2 == null || b2.size() <= 0) {
                return;
            }
            for (bmi bmiVar : b2) {
                int e2 = bli.e(bmiVar.e());
                if (e2 == 1) {
                    h = bli.e(bmiVar.c());
                    LogUtil.c("DeviceCreateDualChannelCommand", "dealOldParse dual socket channel is ", Integer.valueOf(bli.e(bmiVar.c())), "dealOldParse channel is ", Integer.valueOf(h));
                    bib.a().d(deviceInfo, h);
                    b.put(deviceInfo.getDeviceMac(), Integer.valueOf(h));
                } else if (e2 == 2) {
                    d(deviceInfo.getDeviceMac(), bmiVar.c());
                } else {
                    LogUtil.a("DeviceCreateDualChannelCommand", "dealOldParse parameter is invalid.");
                }
            }
        } catch (bmk unused) {
            LogUtil.e("DeviceCreateDualChannelCommand", "dealOldParse handleDualSocket TlvException");
        } catch (NumberFormatException unused2) {
            LogUtil.e("DeviceCreateDualChannelCommand", "dealOldParse handleDualSocket NumberFormatException");
        }
    }

    private void d(String str, String str2) {
        if (str != null && str2 != null) {
            if (str2.length() >= 2) {
                int length = str2.length();
                SparseBooleanArray sparseBooleanArray = f369a.get(str);
                if (sparseBooleanArray != null) {
                    sparseBooleanArray.clear();
                } else {
                    sparseBooleanArray = new SparseBooleanArray(length / 2);
                }
                for (int i2 = 2; i2 <= length; i2 += 2) {
                    sparseBooleanArray.append(bli.e(str2.substring(i2 - 2, i2)), true);
                }
                LogUtil.c("DeviceCreateDualChannelCommand", "updateDualSocketService, dualServices: ", sparseBooleanArray);
                f369a.put(str, sparseBooleanArray);
                return;
            }
        }
        LogUtil.a("DeviceCreateDualChannelCommand", "updateDualSocketService input is invalid.");
    }

    private bir d(DeviceInfo deviceInfo) {
        ByteBuffer allocate;
        LogUtil.c("DeviceCreateDualChannelCommand", "Enter constructDualChannelCapabilityCommandMessage().");
        if (deviceInfo == null) {
            LogUtil.e("DeviceCreateDualChannelCommand", "deviceInfo is null");
            return super.getDeviceCommand(deviceInfo);
        }
        ConcurrentHashMap<String, Boolean> concurrentHashMap = i;
        boolean booleanValue = concurrentHashMap.get(deviceInfo.getDeviceMac()) != null ? concurrentHashMap.get(deviceInfo.getDeviceMac()).booleanValue() : false;
        if (booleanValue) {
            allocate = ByteBuffer.allocate(10);
        } else {
            allocate = ByteBuffer.allocate(6);
        }
        allocate.put((byte) 1);
        allocate.put((byte) 60);
        allocate.put((byte) 1);
        allocate.put((byte) 0);
        allocate.put((byte) 2);
        allocate.put((byte) 0);
        if (booleanValue) {
            allocate.put((byte) 3);
            allocate.put((byte) 0);
            allocate.put((byte) 4);
            allocate.put((byte) 0);
        }
        bir deviceCommand = super.getDeviceCommand(deviceInfo);
        deviceCommand.e(allocate.array());
        return deviceCommand;
    }

    public static bjc a(DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            LogUtil.a("DeviceCreateDualChannelCommand", "deviceCapability is null");
            return new bjc(false);
        }
        if (deviceCapability.isSupportZoneId()) {
            return new bjc(true, new bhs(false));
        }
        return bhs.c(deviceCapability);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public String getTag() {
        return "013C";
    }
}
