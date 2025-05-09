package defpackage;

import android.content.Context;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbtsdk.btmanager.btlinklayerwrap.BTDeviceDataWrapBase;
import health.compact.a.LogUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class izs extends BTDeviceDataWrapBase {
    private String b;
    private Context c;
    private int d;
    private int j = 0;
    private int h = 0;

    /* renamed from: a, reason: collision with root package name */
    private String f13694a = "";
    private String e = "";

    public izs(Context context, int i) {
        this.c = null;
        this.d = -1;
        if (context != null) {
            this.c = context;
        }
        this.d = i;
    }

    private void d(int i, byte[] bArr, ArrayList<byte[]> arrayList) {
        byte[] a2 = blq.a(blq.c(i + 1));
        ByteBuffer allocate = ByteBuffer.allocate(i + 6);
        byte[] bArr2 = new byte[i + 4];
        allocate.put((byte) 90);
        bArr2[0] = 90;
        allocate.put(a2);
        System.arraycopy(a2, 0, bArr2, 1, a2.length);
        allocate.put((byte) 0);
        bArr2[3] = 0;
        allocate.put(bArr);
        System.arraycopy(bArr, 0, bArr2, 4, bArr.length);
        allocate.put(iyq.b(this.c).a(bArr2));
        allocate.flip();
        arrayList.add(allocate.array());
    }

    private void e(int i, byte[] bArr, ArrayList<byte[]> arrayList) {
        int i2 = this.d - 7;
        int i3 = i % i2 > 0 ? (i / i2) + 1 : i / i2;
        int i4 = 0;
        while (i4 < i3) {
            int i5 = i3 - 1;
            int i6 = i4 == i5 ? i - (i4 * i2) : i2;
            byte[] a2 = blq.a(blq.c(i6 + 2));
            ByteBuffer allocate = ByteBuffer.allocate(i6 + 7);
            byte[] bArr2 = new byte[i6 + 5];
            allocate.put((byte) 90);
            bArr2[0] = 90;
            allocate.put(a2);
            System.arraycopy(a2, 0, bArr2, 1, a2.length);
            if (i4 == 0) {
                allocate.put((byte) 1);
                bArr2[3] = 1;
            } else if (i4 == i5) {
                allocate.put((byte) 3);
                bArr2[3] = 3;
            } else {
                allocate.put((byte) 2);
                bArr2[3] = 2;
            }
            byte b = (byte) i4;
            allocate.put(b);
            bArr2[4] = b;
            int i7 = i4 * i2;
            byte[] copyOfRange = Arrays.copyOfRange(bArr, i7, i6 + i7);
            allocate.put(copyOfRange);
            copyByHead(5, allocate, arrayList, this.c, copyOfRange, bArr2);
            i4++;
        }
    }

    @Override // com.huawei.hwbtsdk.btmanager.btlinklayerwrap.BTDeviceDataWrapBase
    public ArrayList<byte[]> wrapCommandPackets(int i, byte[] bArr) {
        ArrayList<byte[]> arrayList = new ArrayList<>(12);
        if (i > 0 && bArr != null) {
            if (i + 6 <= this.d) {
                d(i, bArr, arrayList);
            } else {
                e(i, bArr, arrayList);
            }
        }
        return arrayList;
    }

    @Override // com.huawei.hwbtsdk.btmanager.btlinklayerwrap.BTDeviceDataWrapBase
    public List<izj> parseResponsePacket(int i, byte[] bArr) {
        LogUtil.c("BTDeviceV2ProtocolDataWrap", "packet dataLen: ", Integer.valueOf(i));
        if (i <= 0 || bArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(12);
        String d = blq.d(bArr);
        if (d.length() < 6) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceV2ProtocolDataWrap", "strDataContentHex length is invalid with data is ", d);
            return arrayList;
        }
        int d2 = bli.d(d.substring(2, 6), 16);
        if (d2 + 5 == i) {
            return b(bArr, d, i, d2, arrayList);
        }
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceV2ProtocolDataWrap", "Multi package.");
        ArrayList arrayList2 = new ArrayList(12);
        int length = d.length();
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int[] c = c(d, arrayList, arrayList2, i2, length, i3);
            int i4 = c[0];
            if (i4 == -1) {
                return arrayList;
            }
            i3 = c[1];
            i2 = i4;
        }
        d(i3, arrayList2, arrayList);
        return arrayList;
    }

    private int[] c(String str, List<izj> list, List<byte[]> list2, int... iArr) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = i + 6;
        int[] iArr2 = new int[2];
        if (i2 < i3) {
            return a(str, i, i2, iArr2);
        }
        int d = bli.d(str.substring(i + 2, i3), 16);
        int i4 = i + 8;
        if (i2 < i4) {
            return d(str, i, i2, d, iArr2);
        }
        int d2 = bli.d(str.substring(i3, i4), 16) & 3;
        int i5 = iArr[2];
        if (d2 == 0) {
            if (b(iArr2, c(list, str, i, d, i2))) {
                return iArr2;
            }
        } else {
            int i6 = i + 10;
            int i7 = ((d + 3) * 2) + i;
            boolean z = i7 + 4 > i2;
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceV2ProtocolDataWrap", "controlFSNInfo is multi package isLess: ", Boolean.valueOf(z));
            if (z) {
                c(i2, d, i, str);
            } else {
                if (i2 < i7 || i6 > i7) {
                    return a(iArr2, str);
                }
                list2.add(blq.a(str.substring(i6, i7)));
                i5 += list2.get(list2.size() - 1).length;
            }
            if (d2 == 3) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceV2ProtocolDataWrap", "Receive all Tag.");
                if (i5 > 0) {
                    i5 = e(list, list2, i5, i7, i2);
                }
            }
        }
        return a(iArr2, d, i, i5);
    }

    private boolean b(int[] iArr, List<izj> list) {
        if (list == null) {
            return false;
        }
        iArr[0] = -1;
        return true;
    }

    private int[] a(int[] iArr, String str) {
        LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceV2ProtocolDataWrap", "strDataContentHex length is invalid for copy common data content with data is ", str);
        d();
        iArr[0] = -1;
        return iArr;
    }

    private int[] a(int[] iArr, int i, int i2, int i3) {
        iArr[0] = ((i + 5) * 2) + i2;
        iArr[1] = i3;
        return iArr;
    }

    private List<izj> c(List<izj> list, String str, int i, int i2, int i3) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceV2ProtocolDataWrap", "controlFSNInfo is single package.");
        izj izjVar = new izj();
        izjVar.e(false);
        int i4 = i + 8;
        int i5 = ((i2 + 3) * 2) + i;
        if (i5 > i3) {
            c(i3, i2, i, str);
            return null;
        }
        if (i3 < i5 || i4 > i5) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceV2ProtocolDataWrap", "strDataContentHex length is invalid for copy common data content with data is ", str);
            d();
            ArrayList arrayList = new ArrayList(16);
            arrayList.addAll(list);
            return arrayList;
        }
        c(list, izjVar, str, i4, i5, i2);
        return null;
    }

    private void c(int i, int i2, int i3, String str) {
        this.j = i2 + 5;
        String substring = str.substring(i3, i);
        this.b = substring;
        this.h = this.j - (substring.length() / 2);
    }

    private int e(List<izj> list, List<byte[]> list2, int i, int i2, int i3) {
        ByteBuffer allocate = ByteBuffer.allocate(i);
        Iterator<byte[]> it = list2.iterator();
        while (it.hasNext()) {
            allocate.put(it.next());
        }
        izj izjVar = new izj();
        izjVar.d(allocate.array());
        izjVar.e(true);
        izjVar.c(i);
        if (i2 > i3) {
            izjVar.b(false);
        } else {
            izjVar.b(true);
        }
        list.add(izjVar);
        list2.clear();
        return 0;
    }

    private void c(List<izj> list, izj izjVar, String str, int... iArr) {
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        izjVar.d(blq.a(str.substring(i, i2)));
        izjVar.a(true);
        izjVar.c(i3 - 1);
        izjVar.b(true);
        list.add(izjVar);
    }

    private int[] a(String str, int i, int i2, int[] iArr) {
        String substring = str.substring(i, i2);
        this.b = substring;
        this.j = substring.length() / 2;
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceV2ProtocolDataWrap", "The left data len less then header length with left data is ", this.b, " strDataContentHex:", str);
        iArr[0] = -1;
        return iArr;
    }

    private List<izj> b(byte[] bArr, String str, int i, int i2, List<izj> list) {
        izj izjVar = new izj();
        if (bArr[0] == 90) {
            ArrayList arrayList = new ArrayList(16);
            if (str.length() < 8) {
                arrayList.addAll(list);
                return arrayList;
            }
            int d = bli.d(str.substring(6, 8), 16) & 3;
            if (d == 0) {
                izjVar.e(false);
                izjVar.d(Arrays.copyOfRange(bArr, 4, i - 2));
                izjVar.a(true);
                izjVar.c(i2 - 1);
                izjVar.b(true);
            } else {
                izjVar.e(true);
                int i3 = i - 2;
                if (i - 7 < 0) {
                    LogUtil.a("BTDeviceV2ProtocolDataWrap", "data is error, return");
                    return null;
                }
                izjVar.d(Arrays.copyOfRange(bArr, 5, i3));
                izjVar.a(true);
                izjVar.c(i2 - 2);
                if (3 == d) {
                    izjVar.b(true);
                } else {
                    izjVar.b(false);
                }
            }
            list.add(izjVar);
            arrayList.addAll(list);
            return arrayList;
        }
        izjVar.a(false);
        return null;
    }

    private int[] d(String str, int i, int i2, int i3, int[] iArr) {
        this.b = str.substring(i, i2);
        this.j = i3;
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceV2ProtocolDataWrap", "The left data len less then header length with left data is ", this.b, " strDataContentHex: ", str);
        iArr[0] = -1;
        return iArr;
    }

    private void d(int i, List<byte[]> list, List<izj> list2) {
        if (i > 0) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceV2ProtocolDataWrap", "Exist sliced package and not receive last package.");
            ByteBuffer allocate = ByteBuffer.allocate(i);
            Iterator<byte[]> it = list.iterator();
            while (it.hasNext()) {
                allocate.put(it.next());
            }
            izj izjVar = new izj();
            izjVar.e(true);
            izjVar.d(allocate.array());
            izjVar.c(i);
            izjVar.b(false);
            list2.add(izjVar);
        }
    }

    private void d() {
        this.j = 0;
        this.h = 0;
        this.b = "";
        this.f13694a = "";
        this.e = "";
    }

    @Override // com.huawei.hwbtsdk.btmanager.btlinklayerwrap.BTDeviceDataWrapBase
    public String spliceMTUPackage(int i, byte[] bArr) {
        if (i <= 0 || bArr == null) {
            return null;
        }
        String str = this.e + blq.d(bArr);
        this.e = "";
        LogUtil.c("BTDeviceV2ProtocolDataWrap", "package: ", new byte[str.length() / 2], "mV2DataLen: ", Integer.valueOf(this.j));
        byte[] a2 = blq.a(str);
        if (a2[0] == 90 && this.j == 0) {
            this.f13694a = "";
            if (str.length() < 6) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceV2ProtocolDataWrap", "strDataContentHex length is invalid with data is ", str);
                return "";
            }
            if (!d(a2, str, 2, 6)) {
                return "";
            }
            return this.f13694a;
        }
        int i2 = this.j;
        if (i2 <= 0) {
            a();
            return "";
        }
        if (i2 < 6) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceV2ProtocolDataWrap", "mV2TotalDataLenlessthanlinkheaderlength.");
            String str2 = this.b + blq.d(a2);
            this.b = str2;
            return str2;
        }
        return d(i, a2);
    }

    private void a() {
        this.h = 0;
        this.j = 0;
        this.e = "";
        this.f13694a = "";
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceV2ProtocolDataWrap", "mV2TotalDataLen result is 0 return.");
    }

    private String d(int i, byte[] bArr) {
        int i2 = this.h;
        if (i < i2) {
            this.b += blq.d(bArr);
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceV2ProtocolDataWrap", "data content len less than mV2LeftDataLen.");
            this.h -= i;
            return "";
        }
        if (i == i2) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceV2ProtocolDataWrap", "data content len equal mV2LeftDataLen.");
            this.h = 0;
            this.j = 0;
            String str = this.b + blq.d(bArr);
            this.b = str;
            resetPackageInfo();
            return str;
        }
        return e(bArr);
    }

    private boolean d(byte[] bArr, String str, int i, int i2) {
        this.e = "";
        if (i2 > bArr.length * 2) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceV2ProtocolDataWrap", "endPosition bigger than linkDataLen.");
            this.e = blq.d(bArr);
            c();
            return false;
        }
        int d = bli.d(str.substring(i, i2), 16);
        int i3 = d + 5;
        if (i3 > bArr.length) {
            this.b = blq.d(bArr);
            this.j = d + 5;
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 0, "BTDeviceV2ProtocolDataWrap", "mV2TotalDataLen is ", Integer.valueOf(this.j), " dataContent.length:", Integer.valueOf(bArr.length));
            this.h = this.j - bArr.length;
            return false;
        }
        if (i3 == bArr.length) {
            LogUtil.c("BTDeviceV2ProtocolDataWrap", "dataContentLen: ", Integer.valueOf(bArr.length));
            this.f13694a = blq.d(bArr);
            c();
            return true;
        }
        return c(bArr, str, i, i2);
    }

    private boolean c(byte[] bArr, String str, int i, int i2) {
        String d = blq.d(bArr);
        int length = bArr.length;
        int i3 = length;
        int i4 = 0;
        while (true) {
            if (i4 >= length) {
                break;
            }
            this.e = "";
            if (i2 > length * 2) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceV2ProtocolDataWrap", "endPosition bigger than dataLength.");
                this.e = str.substring(i - 2);
                c();
                return true;
            }
            int d2 = bli.d(str.substring(i, i2), 16);
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceV2ProtocolDataWrap", "handleDataContent linkDataLen = ", Integer.valueOf(d2));
            int i5 = d2 + 5;
            if (i5 < i3) {
                int i6 = i4 + i5;
                int i7 = i6 * 2;
                this.f13694a += d.substring(i4 * 2, i7);
                i3 -= i5;
                i = i7 + 2;
                i4 = i6;
                i2 = i7 + 6;
            } else {
                if (i5 == i3) {
                    this.f13694a += d.substring(i4 * 2, (i4 + i5) * 2);
                    this.h = 0;
                    return true;
                }
                this.b += d.substring(i4 * 2, (i4 + i3) * 2);
                this.h = i5 - i3;
                this.j = i5;
            }
        }
        return true;
    }

    private void c() {
        this.b = "";
        this.j = 0;
        this.h = 0;
    }

    private String e(byte[] bArr) {
        boolean z = bArr[this.h] == 90;
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceV2ProtocolDataWrap", "data content len more than dealMultiPackage mV2LeftDataLen:", Integer.valueOf(this.h), " isEqualsSof:", Boolean.valueOf(z));
        if (z) {
            String str = this.b + blq.d(Arrays.copyOfRange(bArr, 0, this.h));
            this.b = "";
            this.f13694a = "";
            byte[] copyOfRange = Arrays.copyOfRange(bArr, this.h, bArr.length);
            resetPackageInfo();
            if (!d(copyOfRange, blq.d(copyOfRange), 2, 6)) {
                return str;
            }
            return str + this.f13694a;
        }
        this.b = "";
        this.h = 0;
        this.j = 0;
        return "";
    }

    @Override // com.huawei.hwbtsdk.btmanager.btlinklayerwrap.BTDeviceDataWrapBase
    public void resetPackageInfo() {
        this.b = "";
        this.h = 0;
        this.j = 0;
        this.f13694a = "";
        this.e = "";
    }

    @Override // com.huawei.hwbtsdk.btmanager.btlinklayerwrap.BTDeviceDataWrapBase
    public void setDeviceMaxFrameSize(int i) {
        this.d = i;
    }
}
