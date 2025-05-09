package defpackage;

import android.content.Context;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbtsdk.btmanager.btlinklayerwrap.BTDeviceDataWrapBase;
import health.compact.a.LogUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes5.dex */
public class izq extends BTDeviceDataWrapBase {
    private Context b;
    private int c;

    private int e(int i, int i2, int i3, int i4) {
        int i5;
        if (2 == i4) {
            i5 = (i * 16384) + (i2 * 128);
        } else {
            if (1 != i4) {
                return i3;
            }
            i5 = i * 128;
        }
        return i3 + i5;
    }

    public izq(Context context, int i) {
        this.b = null;
        this.c = -1;
        if (context != null) {
            this.b = context;
        }
        this.c = i;
    }

    private byte[] b(int i) {
        String b;
        int i2 = i + 3;
        if (16384 <= i2) {
            b = blq.b((i2 >> 14) + 128) + blq.b((i2 >> 7) + 128) + blq.b(i2 & 127);
        } else if (128 <= i2) {
            b = blq.b((i2 >> 7) + 128) + blq.b(i2 & 127);
        } else {
            b = blq.b(i2);
        }
        return blq.a(b);
    }

    private void e(int i, byte[] bArr, ArrayList<byte[]> arrayList) {
        byte[] b = b(i);
        int length = b.length;
        int i2 = length + 4;
        int i3 = i + i2 + 2;
        ByteBuffer allocate = ByteBuffer.allocate(i3);
        byte[] d = d(i3, b, length, allocate);
        allocate.put((byte) 1);
        d[length + 2] = 1;
        allocate.put((byte) 1);
        d[length + 3] = 1;
        allocate.put(bArr);
        for (byte b2 : bArr) {
            d[i2] = b2;
            i2++;
        }
        allocate.put(iyq.b(this.b).a(d));
        allocate.flip();
        arrayList.add(allocate.array());
    }

    private void c(int i, byte[] bArr, ArrayList<byte[]> arrayList) {
        int i2;
        if (i <= 127) {
            i2 = 1;
        } else if (i <= 16383) {
            i2 = 2;
        } else if (i > 2080641) {
            return;
        } else {
            i2 = 3;
        }
        int i3 = ((this.c - 1) - i2) - 5;
        int i4 = i % i3 > 0 ? (i / i3) + 1 : i / i3;
        int i5 = 0;
        while (i5 < i4) {
            int i6 = i5 * i3;
            byte[] copyOfRange = Arrays.copyOfRange(bArr, i6, (i5 == i4 + (-1) ? i - (i5 * i3) : i3) + i6);
            byte[] b = b(copyOfRange.length);
            int length = b.length;
            int i7 = length + 2;
            int i8 = length + 3;
            int i9 = length + 4;
            int length2 = copyOfRange.length + i9 + 2;
            ByteBuffer allocate = ByteBuffer.allocate(length2);
            byte[] d = d(length2, b, length, allocate);
            byte b2 = (byte) i4;
            allocate.put(b2);
            d[i7] = b2;
            int i10 = i5 + 1;
            byte b3 = (byte) i10;
            allocate.put(b3);
            d[i8] = b3;
            allocate.put(copyOfRange);
            copyByHead(i9, allocate, arrayList, this.b, copyOfRange, d);
            i5 = i10;
        }
    }

    @Override // com.huawei.hwbtsdk.btmanager.btlinklayerwrap.BTDeviceDataWrapBase
    public ArrayList<byte[]> wrapCommandPackets(int i, byte[] bArr) {
        if (i <= 0 || bArr == null) {
            return null;
        }
        ArrayList<byte[]> arrayList = new ArrayList<>(1);
        if (b(i).length + 4 + i + 2 <= this.c) {
            e(i, bArr, arrayList);
        } else {
            c(i, bArr, arrayList);
        }
        return arrayList;
    }

    private int a(byte[] bArr) {
        String d = blq.d(bArr);
        if (d.length() < 4) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceV1ProtocolDataWrap", "start responseHex len is incorrect.");
            return 0;
        }
        int i = 2;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int i6 = i + 2;
            if (e(d.substring(i, i6)) > 127) {
                try {
                    i4 = Integer.parseInt(d.substring(i, i6), 16);
                } catch (NumberFormatException unused) {
                    LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceV1ProtocolDataWrap", "number format error.");
                }
                if (i5 == 0) {
                    i2 = i4 - 128;
                } else if (i5 == 1) {
                    i3 = i4 - 128;
                }
                i5++;
                if (d.length() < i + 4) {
                    LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceV1ProtocolDataWrap", "parse responseHex len is incorrect.");
                    return 0;
                }
                i = i6;
            } else {
                try {
                    i4 = Integer.parseInt(d.substring(i, i6), 16);
                } catch (NumberFormatException unused2) {
                    LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceV1ProtocolDataWrap", "num number format error.");
                }
                return e(i2, i3, i4, i5);
            }
        }
    }

    private int e(String str) {
        if (str == null) {
            return 128;
        }
        try {
            return Integer.parseInt(str, 16);
        } catch (NumberFormatException e) {
            LogUtil.e("BTDeviceV1ProtocolDataWrap", "parseInt errorcode fail,msg=", e.getMessage());
            return 128;
        }
    }

    @Override // com.huawei.hwbtsdk.btmanager.btlinklayerwrap.BTDeviceDataWrapBase
    public List<izj> parseResponsePacket(int i, byte[] bArr) {
        int i2;
        int i3;
        byte[] bArr2;
        if (i <= 0 || bArr == null) {
            return null;
        }
        int a2 = a(bArr);
        if (a2 == 0) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceV1ProtocolDataWrap", "dataTotalLen : 0.");
            return null;
        }
        int i4 = 3;
        if (16384 <= a2) {
            i3 = 6;
            i2 = 3;
            i4 = 5;
        } else if (128 <= a2) {
            i3 = 5;
            i2 = 2;
            i4 = 4;
        } else {
            i2 = 1;
            i3 = 4;
        }
        if (-86 == bArr[0]) {
            int i5 = i2 + 4;
            int i6 = i - 2;
            if (i6 > i5 && bArr.length > i6) {
                bArr2 = Arrays.copyOfRange(bArr, i5, i6);
            } else {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceV1ProtocolDataWrap", "dataTotalLen : 0.");
                return null;
            }
        } else {
            bArr2 = null;
        }
        if (bArr.length <= i4) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceV1ProtocolDataWrap", "dataContent.length less than pack_total_position so return null.");
            return null;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(c(bArr, i4, bArr2, i3, a2));
        return arrayList;
    }

    private izj c(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        byte b = bArr[i];
        izj izjVar = new izj();
        if (1 == b) {
            izjVar.e(false);
            izjVar.d(bArr2);
        } else {
            izjVar.e(true);
            if (bArr.length <= i2) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BTDeviceV1ProtocolDataWrap", "dataContent length less than pack_index_position.");
                return null;
            }
            byte b2 = bArr[i2];
            izjVar.c(i3);
            izjVar.d(bArr2);
            if (b == b2) {
                izjVar.b(true);
            } else {
                izjVar.b(false);
            }
        }
        return izjVar;
    }

    private byte[] d(int i, byte[] bArr, int i2, ByteBuffer byteBuffer) {
        byte[] bArr2 = new byte[i - 2];
        byteBuffer.put((byte) -86);
        bArr2[0] = -86;
        byteBuffer.put(bArr);
        int i3 = 0;
        while (i3 < bArr.length) {
            int i4 = i3 + 1;
            bArr2[i4] = bArr[i3];
            i3 = i4;
        }
        byteBuffer.put((byte) 0);
        bArr2[i2 + 1] = 0;
        return bArr2;
    }
}
