package defpackage;

import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.DeviceInfo;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class bkm {
    private static final Map<Integer, Byte> b = new HashMap(1);
    private int g = 0;
    private int c = 0;

    /* renamed from: a, reason: collision with root package name */
    private byte[] f425a = null;
    private byte[] i = null;
    private byte[] h = null;
    private bit j = new bit();
    private int d = 255;
    private int e = 10;
    private int f = 255;

    private static int c(int i, int i2) {
        return (i << 8) | i2;
    }

    static {
        ArrayList<int[]> arrayList = new ArrayList(1);
        arrayList.add(new int[]{49, 2});
        for (int[] iArr : arrayList) {
            b.put(Integer.valueOf(c(iArr[0], iArr[1])), (byte) 1);
        }
    }

    public void d(biw biwVar) {
        if (biwVar == null) {
            LogUtil.a("DataParserInitial5A", "paramete is empty use default parameter");
            return;
        }
        LogUtil.c("DataParserInitial5A", "update link parameter");
        this.d = biwVar.d();
        this.e = biwVar.b();
        this.f = biwVar.g();
    }

    public ArrayList<byte[]> a(byte[] bArr) {
        ArrayList<byte[]> arrayList = new ArrayList<>(16);
        if (bArr == null || bArr.length <= 0) {
            LogUtil.a("DataParserInitial5A", "dataContent is empty.");
            return arrayList;
        }
        int length = bArr.length;
        if (length + 6 <= this.d) {
            e(length, bArr, arrayList);
        } else {
            d(length, bArr, arrayList);
        }
        return arrayList;
    }

    public List<bil> b(byte[] bArr) {
        int i;
        ArrayList arrayList = new ArrayList(16);
        if (bArr == null || (i = this.f) == 0) {
            LogUtil.a("DataParserInitial5A", "wrapCommandPackage frame is empty or mMaxTransmissionUnit is zero.");
            return arrayList;
        }
        int length = bArr.length;
        if (length <= i) {
            bil bilVar = new bil();
            bilVar.b(bArr);
            bilVar.e(this.e);
            arrayList.add(bilVar);
        } else {
            int i2 = 0;
            int i3 = length % i > 0 ? (length / i) + 1 : length / i;
            while (i2 < i3) {
                int i4 = this.f;
                int i5 = i3 - 1;
                int i6 = i2 == i5 ? length - (i2 * i4) : i4;
                int i7 = i4 * i2;
                byte[] copyOfRange = Arrays.copyOfRange(bArr, i7, i6 + i7);
                bil bilVar2 = new bil();
                bilVar2.b(copyOfRange);
                if (i2 == i5) {
                    bilVar2.e(this.e);
                }
                arrayList.add(bilVar2);
                i2++;
            }
        }
        return arrayList;
    }

    private void e(int i, byte[] bArr, ArrayList<byte[]> arrayList) {
        byte[] a2 = blq.a(blq.c(i + 1));
        ByteBuffer allocate = ByteBuffer.allocate(i + 6);
        int i2 = i + 4;
        byte[] bArr2 = new byte[i2];
        if (i2 <= 3) {
            return;
        }
        allocate.put((byte) 90);
        bArr2[0] = 90;
        allocate.put(a2);
        System.arraycopy(a2, 0, bArr2, 1, a2.length);
        allocate.put((byte) 0);
        bArr2[3] = 0;
        allocate.put(bArr);
        System.arraycopy(bArr, 0, bArr2, 4, bArr.length);
        allocate.put(blf.b(bArr2));
        allocate.flip();
        arrayList.add(allocate.array());
    }

    private void d(int i, byte[] bArr, ArrayList<byte[]> arrayList) {
        int i2 = this.d - 7;
        if (i2 == 0) {
            LogUtil.a("DataParserInitial5A", "wrapSlicedFrames slicedDataMaxLen is zero");
            return;
        }
        int i3 = i % i2 > 0 ? (i / i2) + 1 : i / i2;
        int i4 = 0;
        while (i4 < i3) {
            int i5 = i3 - 1;
            int i6 = i4 == i5 ? i - (i4 * i2) : i2;
            byte[] a2 = blq.a(blq.c(i6 + 2));
            ByteBuffer allocate = ByteBuffer.allocate(i6 + 7);
            int i7 = i6 + 5;
            byte[] bArr2 = new byte[i7];
            if (i7 <= 0) {
                return;
            }
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
            byte b2 = (byte) i4;
            allocate.put(b2);
            bArr2[4] = b2;
            int i8 = i4 * i2;
            byte[] copyOfRange = Arrays.copyOfRange(bArr, i8, i6 + i8);
            allocate.put(copyOfRange);
            b(5, allocate, arrayList, copyOfRange, bArr2);
            i4++;
        }
    }

    private void b(int i, ByteBuffer byteBuffer, ArrayList<byte[]> arrayList, byte[] bArr, byte[] bArr2) {
        if (!d(byteBuffer, arrayList, bArr, bArr2)) {
            LogUtil.e("DataParserInitial5A", "the input param is invalid");
            return;
        }
        System.arraycopy(bArr, 0, bArr2, i, bArr.length);
        byteBuffer.put(blf.b(bArr2));
        byteBuffer.flip();
        arrayList.add(byteBuffer.array());
    }

    private boolean d(ByteBuffer byteBuffer, ArrayList<byte[]> arrayList, byte[] bArr, byte[] bArr2) {
        if (byteBuffer == null) {
            LogUtil.a("DataParserInitial5A", "frameBuffer is null.");
            return false;
        }
        if (arrayList == null) {
            LogUtil.a("DataParserInitial5A", "frameList is null.");
            return false;
        }
        if (bArr == null) {
            LogUtil.a("DataParserInitial5A", "slicedData is null.");
            return false;
        }
        if (bArr2 != null) {
            return true;
        }
        LogUtil.a("DataParserInitial5A", "checkBytes is null.");
        return false;
    }

    public byte[] e(int i, byte[] bArr) {
        if (i <= 0 || bArr == null || e(bArr)) {
            return null;
        }
        byte[] d = new bms().b(this.h).b(bArr).d();
        this.h = null;
        LogUtil.c("DataParserInitial5A", "spliceMTUPackage: ", d, " mTotalDataLen is: ", Integer.valueOf(this.g));
        if (d.length <= 0) {
            return null;
        }
        if (d[0] == 90 && this.g == 0) {
            this.i = null;
            if (d.length < 3) {
                blt.d("DataParserInitial5A", d, "dataOriginal error is ");
                return this.i;
            }
            if (e(d, 1, 3)) {
                return this.i;
            }
            return null;
        }
        return d(d, i);
    }

    private boolean e(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = this.h;
        int length2 = bArr2 != null ? bArr2.length : 0;
        byte[] bArr3 = this.f425a;
        int length3 = length + length2 + (bArr3 != null ? bArr3.length : 0);
        if (length3 < 102400) {
            return false;
        }
        LogUtil.e("DataParserInitial5A", "sumDataLen exceed is ", Integer.valueOf(length3));
        e();
        return true;
    }

    private boolean e(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return false;
        }
        this.h = null;
        if (i2 > bArr.length) {
            LogUtil.c("DataParserInitial5A", "endPosition bigger than linkDataLen.");
            this.h = bArr;
            a();
            return false;
        }
        int c = c(bArr, i, i2);
        int i3 = c + 5;
        if (i3 > bArr.length) {
            this.f425a = bArr;
            int i4 = c + 5;
            this.g = i4;
            int length = i4 - bArr.length;
            this.c = length;
            LogUtil.c("DataParserInitial5A", "handleDataContent multi mLeftDataLen is ", Integer.valueOf(length), " mTotalDataLen is ", Integer.valueOf(this.g));
            return false;
        }
        if (i3 == bArr.length) {
            LogUtil.c("DataParserInitial5A", "handleDataContent equal = ", Integer.valueOf(bArr.length));
            this.i = bArr;
            a();
            return true;
        }
        return b(bArr, i, i2);
    }

    private void a() {
        this.f425a = null;
        this.g = 0;
        this.c = 0;
    }

    private int c(byte[] bArr, int i, int i2) {
        int i3 = i2 - i;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i, bArr2, 0, i3);
        return blq.d(bArr2, 0);
    }

    private boolean b(byte[] bArr, int i, int i2) {
        blt.d("DataParserInitial5A", bArr, "handleDataContentNeedSplit tempContent : ");
        if (bArr == null) {
            return false;
        }
        int length = bArr.length;
        int i3 = 0;
        int i4 = length;
        while (true) {
            if (i3 >= length) {
                break;
            }
            this.h = null;
            if (i2 > length) {
                int i5 = (length - i) + 1;
                byte[] bArr2 = new byte[i5];
                System.arraycopy(bArr, i - 1, bArr2, 0, i5);
                this.h = bArr2;
                LogUtil.c("DataParserInitial5A", "endPosition bigger than dataLength.");
                a();
                return true;
            }
            int c = c(bArr, i, i2);
            LogUtil.c("DataParserInitial5A", "handleDataContent linkDataLen = ", Integer.valueOf(c));
            int i6 = c + 5;
            bms bmsVar = new bms();
            if (i6 < i4) {
                byte[] bArr3 = new byte[i6];
                System.arraycopy(bArr, i3, bArr3, 0, i6);
                this.i = bmsVar.b(this.i).b(bArr3).d();
                i3 += i6;
                i4 -= i6;
                i = i3 + 1;
                i2 = i3 + 3;
            } else {
                if (i6 == i4) {
                    byte[] bArr4 = new byte[i6];
                    System.arraycopy(bArr, i3, bArr4, 0, i6);
                    this.i = bmsVar.b(this.i).b(bArr4).d();
                    this.c = 0;
                    return true;
                }
                byte[] bArr5 = new byte[i4];
                System.arraycopy(bArr, i3, bArr5, 0, i4);
                this.f425a = bmsVar.b(this.f425a).b(bArr5).d();
                this.c = i6 - i4;
                this.g = i6;
            }
        }
        return true;
    }

    private byte[] d(byte[] bArr, int i) {
        if (bArr == null) {
            return null;
        }
        if (this.g <= 0) {
            this.c = 0;
            this.g = 0;
            this.h = null;
            this.i = null;
            LogUtil.c("DataParserInitial5A", "mTotalDataLen result is 0 return.");
            return null;
        }
        bms bmsVar = new bms();
        if (this.g < 3) {
            LogUtil.c("DataParserInitial5A", "getPackageNotSof is not 5A");
            byte[] d = bmsVar.b(this.f425a).b(bArr).d();
            this.f425a = d;
            return d;
        }
        int i2 = this.c;
        if (i < i2) {
            this.f425a = bmsVar.b(this.f425a).b(bArr).d();
            LogUtil.c("DataParserInitial5A", "dataLen less than mLeftDataLen.");
            this.c -= i;
            return null;
        }
        if (i == i2) {
            LogUtil.c("DataParserInitial5A", "dataLen equal mLeftDataLen.");
            this.c = 0;
            this.g = 0;
            byte[] d2 = bmsVar.b(this.f425a).b(bArr).d();
            this.f425a = d2;
            b();
            return d2;
        }
        return c(bArr);
    }

    private byte[] c(byte[] bArr) {
        LogUtil.c("DataParserInitial5A", "dealMultiPackage mLeftDataLen: ", Integer.valueOf(this.c));
        int length = bArr.length;
        int i = this.c;
        if (length <= i) {
            return null;
        }
        if (bArr[i] == 90) {
            byte[] d = new bms().b(this.f425a).b(Arrays.copyOfRange(bArr, 0, i)).d();
            this.f425a = null;
            this.i = null;
            byte[] copyOfRange = Arrays.copyOfRange(bArr, this.c, bArr.length);
            b();
            return e(copyOfRange, 1, 3) ? new bms().b(d).b(this.i).d() : d;
        }
        this.f425a = null;
        LogUtil.c("DataParserInitial5A", "dealMultiPackage data is invalid.");
        this.c = 0;
        this.g = 0;
        return this.f425a;
    }

    public List<bit> d(int i, byte[] bArr) {
        LogUtil.c("DataParserInitial5A", "parseResponsePacket dataLen: ", Integer.valueOf(i));
        ArrayList arrayList = new ArrayList(16);
        if (i <= 0 || bArr == null) {
            LogUtil.e("DataParserInitial5A", "dateLen and dateContent is invalid");
            return arrayList;
        }
        if (bArr.length < 3) {
            blt.d("DataParserInitial5A", bArr, "strDataContentHex's length is invalid with data is ");
            return arrayList;
        }
        int c = c(bArr, 1, 3);
        if (c + 5 == i) {
            return d(bArr, i, c, arrayList);
        }
        LogUtil.c("DataParserInitial5A", "Multi package.");
        ArrayList arrayList2 = new ArrayList(16);
        int length = bArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int[] d = d(bArr, arrayList, arrayList2, new int[]{i2, length, i3});
            int i4 = d[0];
            if (i4 == -1) {
                return arrayList;
            }
            i3 = d[1];
            i2 = i4;
        }
        e(i3, arrayList2, arrayList);
        return arrayList;
    }

    private List<bit> d(byte[] bArr, int i, int i2, List<bit> list) {
        LogUtil.c("DataParserInitial5A", "Single package.");
        bit bitVar = new bit();
        if (bArr[0] == 90) {
            if (bArr.length < 4) {
                blt.d("DataParserInitial5A", bArr, "strDataContentHex length is invalid with data is ");
                return list;
            }
            int c = c(bArr, 3, 4) & 3;
            if (c == 0) {
                bitVar.a(false);
                bitVar.b(a(bArr, 4, i - 2));
                bitVar.b(true);
                bitVar.e(i2 - 1);
                bitVar.c(true);
            } else {
                bitVar.a(true);
                bitVar.b(a(bArr, 5, i - 2));
                bitVar.b(true);
                bitVar.e(i2 - 2);
                if (c == 3) {
                    bitVar.c(true);
                } else {
                    bitVar.c(false);
                }
            }
            list.add(bitVar);
            return list;
        }
        bitVar.b(false);
        return null;
    }

    private byte[] a(byte[] bArr, int i, int i2) {
        try {
            return Arrays.copyOfRange(bArr, i, i2);
        } catch (IllegalArgumentException unused) {
            LogUtil.e("DataParserInitial5A", "IllegalArgumentException");
            return null;
        }
    }

    private int[] d(byte[] bArr, List<bit> list, List<byte[]> list2, int[] iArr) {
        int i;
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = i2 + 1;
        int i5 = i2 + 3;
        int[] iArr2 = new int[2];
        if (i3 < i5) {
            return e(bArr, i2, i3, iArr2);
        }
        int c = c(bArr, i4, i5);
        int i6 = i2 + 4;
        if (i3 < i6) {
            return a(bArr, i2, i3, c, iArr2);
        }
        int c2 = c(bArr, i5, i6) & 3;
        int i7 = iArr[2];
        if (c2 == 0) {
            if (c(iArr2, c(list, bArr, i2, c, i3))) {
                return iArr2;
            }
        } else {
            LogUtil.c("DataParserInitial5A", "controlFsnInfo is multi package.");
            int i8 = i2 + 5;
            int i9 = i2 + 3 + c;
            if (i9 + 2 > i3) {
                LogUtil.c("DataParserInitial5A", "finally package.");
                b(i3, c, i2, bArr);
                i = 3;
            } else {
                if (i3 < i9 || i8 > i9) {
                    return e(iArr2, bArr);
                }
                int i10 = i9 - i8;
                byte[] bArr2 = new byte[i10];
                System.arraycopy(bArr, i8, bArr2, 0, i10);
                list2.add(bArr2);
                i7 += list2.get(list2.size() - 1).length;
                i = 3;
            }
            if (c2 == i) {
                LogUtil.c("DataParserInitial5A", "Receive all Tag.");
                i7 = d(list, list2, i7, i9, i3);
            }
        }
        return a(iArr2, c, i2, i7);
    }

    private int[] a(int[] iArr, int i, int i2, int i3) {
        if (iArr.length <= 1) {
            return iArr;
        }
        iArr[0] = i + 5 + i2;
        iArr[1] = i3;
        return iArr;
    }

    private int[] e(int[] iArr, byte[] bArr) {
        blt.d("DataParserInitial5A", bArr, "checkLegal data is ");
        e();
        if (iArr.length <= 0) {
            return iArr;
        }
        iArr[0] = -1;
        return iArr;
    }

    private int d(List<bit> list, List<byte[]> list2, int i, int i2, int i3) {
        ByteBuffer allocate = ByteBuffer.allocate(i);
        Iterator<byte[]> it = list2.iterator();
        while (it.hasNext()) {
            allocate.put(it.next());
        }
        bit bitVar = new bit();
        bitVar.b(allocate.array());
        bitVar.a(true);
        bitVar.e(i);
        if (i2 > i3) {
            bitVar.c(false);
        } else {
            bitVar.c(true);
        }
        list.add(bitVar);
        list2.clear();
        return 0;
    }

    private void b(int i, int i2, int i3, byte[] bArr) {
        this.g = i2 + 5;
        if (bArr.length < i) {
            return;
        }
        int i4 = i - i3;
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArr, i3, bArr2, 0, i4);
        this.f425a = bArr2;
        this.c = this.g - i4;
    }

    private void e(int i, List<byte[]> list, List<bit> list2) {
        if (i > 0) {
            LogUtil.c("DataParserInitial5A", "slicedPayloadSize greater than zero.");
            ByteBuffer allocate = ByteBuffer.allocate(i);
            Iterator<byte[]> it = list.iterator();
            while (it.hasNext()) {
                allocate.put(it.next());
            }
            bit bitVar = new bit();
            bitVar.a(true);
            bitVar.b(allocate.array());
            bitVar.e(i);
            bitVar.c(false);
            list2.add(bitVar);
        }
    }

    private int[] e(byte[] bArr, int i, int i2, int[] iArr) {
        blt.e("DataParserInitial5A", bArr, "checkLegalLast dataContentBytes is ");
        if (bArr.length < i2 || iArr.length <= 0) {
            return iArr;
        }
        int i3 = i2 - i;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i, bArr2, 0, i3);
        this.f425a = bArr2;
        this.g = i3;
        blt.d("DataParserInitial5A", bArr2, "checkLegalLast mDataContentBytes is ");
        iArr[0] = -1;
        return iArr;
    }

    private int[] a(byte[] bArr, int i, int i2, int i3, int[] iArr) {
        LogUtil.a("DataParserInitial5A", "checkLessHeader dataContentBytes is ", bArr);
        if (bArr.length < i2 || iArr.length <= 0) {
            return iArr;
        }
        int i4 = i2 - i;
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArr, i, bArr2, 0, i4);
        this.f425a = bArr2;
        this.g = i3;
        blt.d("DataParserInitial5A", bArr2, "checkLessHeader mDataContentBytes is ");
        iArr[0] = -1;
        return iArr;
    }

    private List<bit> c(List<bit> list, byte[] bArr, int i, int i2, int i3) {
        LogUtil.c("DataParserInitial5A", "dealWithZero enter.");
        bit bitVar = new bit();
        bitVar.a(false);
        int i4 = i + 4;
        int i5 = i + 3 + i2;
        if (i5 > i3) {
            b(i3, i2, i, bArr);
            return null;
        }
        if (i4 > i5) {
            LogUtil.a("DataParserInitial5A", "dealWithZero strDataContentHex is error, data is ", bArr);
            e();
            return list;
        }
        d(list, bitVar, bArr, new int[]{i4, i5, i2});
        return null;
    }

    private void d(List<bit> list, bit bitVar, byte[] bArr, int[] iArr) {
        if (iArr.length <= 2) {
            return;
        }
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        if (bArr.length < i2) {
            return;
        }
        int i4 = i2 - i;
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArr, i, bArr2, 0, i4);
        bitVar.b(bArr2);
        bitVar.b(true);
        bitVar.e(i3 - 1);
        bitVar.c(true);
        list.add(bitVar);
    }

    private boolean c(int[] iArr, List<bit> list) {
        if (list == null) {
            return false;
        }
        if (iArr.length <= 0) {
            return true;
        }
        iArr[0] = -1;
        return true;
    }

    private void e() {
        this.g = 0;
        this.c = 0;
        this.f425a = null;
        this.i = null;
        this.h = null;
    }

    public void b() {
        this.f425a = null;
        this.c = 0;
        this.g = 0;
        this.i = null;
        this.h = null;
    }

    public void b(DeviceInfo deviceInfo, String str, biw biwVar, List<bit> list, MessageReceiveCallback messageReceiveCallback) {
        byte[] d;
        byte[] d2;
        if (deviceInfo == null || bkz.e(list) || messageReceiveCallback == null) {
            LogUtil.a("DataParserInitial5A", "dealWithOnDataReceivedForList deviceInfo parameter exception.");
            return;
        }
        for (bit bitVar : list) {
            LogUtil.c("DataParserInitial5A", "slice info : ", Boolean.valueOf(bitVar.a()), ". success info : ", Boolean.valueOf(bitVar.b()), ". receivedAll info : ", Boolean.valueOf(bitVar.c()));
            if (!bitVar.a() && bitVar.b()) {
                biu e = e(str, bitVar);
                if (biwVar != null && (d2 = bgm.d(e.a(), deviceInfo.getDeviceMac(), biwVar.a())) != null) {
                    e.d(d2);
                }
                a(e);
                messageReceiveCallback.onDataReceived(deviceInfo, e, 0);
            } else {
                if (this.j.e() != 0 && this.j.d() != null) {
                    int e2 = bitVar.e() + this.j.e();
                    LogUtil.c("DataParserInitial5A", "sumTotalLength: ", Integer.valueOf(e2));
                    if (e2 >= 102400) {
                        d();
                        return;
                    }
                    byte[] bArr = new byte[this.j.d().length + bitVar.d().length];
                    System.arraycopy(this.j.d(), 0, bArr, 0, this.j.d().length);
                    System.arraycopy(bitVar.d(), 0, bArr, this.j.d().length, bitVar.d().length);
                    this.j.b(bArr);
                } else {
                    this.j.b(bitVar.d());
                }
                this.j.e(bitVar.e() + this.j.e());
                this.j.b(bitVar.b());
                this.j.c(bitVar.c());
            }
            if (this.j.c()) {
                biu e3 = e(str, this.j);
                if (biwVar != null && (d = bgm.d(e3.a(), deviceInfo.getDeviceMac(), biwVar.a())) != null) {
                    e3.d(d);
                }
                a(e3);
                blt.d("DataParserInitial5A", e3.a(), "on received multiple frames: ");
                messageReceiveCallback.onDataReceived(deviceInfo, e3, 0);
                d();
            }
        }
    }

    private void a(biu biuVar) {
        byte[] a2 = biuVar.a();
        if (a2 == null || a2.length < 2) {
            return;
        }
        byte b2 = a2[0];
        byte b3 = a2[1];
        if (!bky.i()) {
            if (b.get(Integer.valueOf(c(b2, b3))) != null) {
                LogUtil.c("DataParserInitial5A", "on received single frame serviceId: ", Integer.valueOf(b2), " commandId: ", Integer.valueOf(b3));
                return;
            } else {
                blt.d("DataParserInitial5A", biuVar.a(), "on received single frame:");
                return;
            }
        }
        if (b2 == 27 || b2 == 39) {
            ReleaseLogUtil.b("DEVMGR_DataParserInitial5A", "response sid: ", Integer.valueOf(b2), " cid: ", Integer.valueOf(b3));
        }
        if (b2 == 9 && b3 != 3) {
            ReleaseLogUtil.b("DEVMGR_DataParserInitial5A", "on received single frame: ", blt.d(a2, true));
        }
        if (b2 == 8 && b3 == 8) {
            ReleaseLogUtil.b("DEVMGR_DataParserInitial5A", "on received single frame serviceId: ", Integer.valueOf(b2), " commandId: ", Integer.valueOf(b3));
        }
    }

    private biu e(String str, bit bitVar) {
        biu biuVar = new biu();
        if (bitVar == null) {
            LogUtil.e("DataParserInitial5A", "sliceResponse is empty");
            return biuVar;
        }
        biuVar.a(str);
        biuVar.d(bitVar.d());
        return biuVar;
    }

    public void d() {
        this.j.c(false);
        this.j.e(0);
        this.j.b((byte[]) null);
        this.j.a(false);
    }
}
