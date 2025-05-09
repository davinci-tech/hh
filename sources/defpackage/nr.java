package defpackage;

import android.bluetooth.BluetoothDevice;
import com.careforeyou.library.enums.Protocal_Type;
import com.careforeyou.library.enums.Weight_Digit;
import java.util.Date;

/* loaded from: classes2.dex */
public class nr {

    /* renamed from: a, reason: collision with root package name */
    public String f15451a;
    public int b;
    public String c;
    public byte d;
    public byte e;
    public int f;
    public Protocal_Type g;
    public byte h;
    public float i;
    public int j;
    public String k;
    public Date l;
    public byte m;
    public double n;

    public String toString() {
        return "ChipseaBroadcastFrame{version=" + ((int) this.m) + ", deviceType=" + ((int) this.d) + ", cmdId=" + ((int) this.e) + ", weight=" + this.n + ", productId=" + this.j + ", mac='" + this.c + "', procotalType=" + this.g + ", scaleWeight='" + this.k + "', scaleProperty=" + ((int) this.h) + ", r1=" + this.i + ", weightTime=" + this.l + ", rssi=" + this.f + ", modle=" + this.b + '}';
    }

    private void b(byte[] bArr, String str) {
        if (bArr.length < 11) {
            return;
        }
        this.c = str;
        byte b = bArr[1];
        this.m = b;
        if (b == 16) {
            this.d = bArr[3];
            byte b2 = bArr[4];
            this.e = b2;
            this.h = b2;
        } else if (b == 17) {
            this.h = bArr[3];
            this.d = bArr[4];
        }
        if (this.d != 0) {
            this.e = oc.d(this.h);
            ny a2 = oh.a(bArr[5], bArr[6], this.h, false);
            this.n = a2.f15551a;
            this.k = a2.e;
            this.j = oc.a(oc.a(bArr, 7, 4));
            return;
        }
        if (b == 16) {
            this.n = oc.a(oc.a(bArr, 8, 2)) / 10.0f;
            return;
        }
        if (b != 17) {
            return;
        }
        this.e = oc.d(this.h);
        ny a3 = oh.a(bArr[5], bArr[6], this.h, false);
        this.n = a3.f15551a;
        this.k = a3.e;
        this.j = oc.a(oc.a(bArr, 7, 4));
    }

    private void c(byte[] bArr, String str) {
        if (bArr.length < 8) {
            return;
        }
        this.c = str;
        this.m = (byte) 1;
        byte b = bArr[8];
        this.h = b;
        this.e = oc.d(b);
        this.j = oc.a(oc.a(bArr, 6, 2));
        if (this.e == 0) {
            this.n = 0.0d;
            this.k = "";
            this.i = 0.0f;
        } else {
            ny a2 = oh.a(bArr[2], bArr[3], this.h, false);
            this.n = a2.f15551a;
            this.k = a2.e;
            this.i = oc.a(oc.a(bArr, 4, 2)) / 10.0f;
        }
        if (oc.c(this.h) == 0) {
            this.d = (byte) 0;
        } else {
            this.d = (byte) 4;
        }
    }

    private void a(byte[] bArr, String str) {
        if (bArr.length < 14) {
            return;
        }
        this.c = str;
        this.m = bArr[1];
        this.j = oc.a(oc.a(bArr, 3, 4));
        if (bArr[7] == 0) {
            this.d = (byte) 0;
        } else {
            this.d = (byte) 2;
        }
        byte b = bArr[8];
        this.h = b;
        byte d = oc.d(b);
        this.e = d;
        if (d > 0) {
            ny a2 = oh.a(bArr[10], bArr[11], this.h, false);
            this.n = a2.f15551a;
            this.k = a2.e;
            this.i = oc.a(oc.a(bArr, 12, 2)) / 10.0f;
            return;
        }
        this.n = 0.0d;
        this.k = "";
        this.i = 0.0f;
    }

    private void e(byte[] bArr, String str) {
        if (bArr.length < 10) {
            return;
        }
        this.c = str;
        this.d = (byte) 1;
        this.m = bArr[2];
        byte b = bArr[3];
        this.h = b;
        this.e = (byte) 0;
        ny a2 = oh.a(bArr[5], bArr[4], b, true);
        this.n = a2.f15551a;
        this.k = a2.e;
        this.j = oc.a(oc.a(bArr, 6, 4));
    }

    private void d(byte[] bArr, String str) {
        if (bArr.length < 8) {
            return;
        }
        this.c = str;
        this.d = (byte) 3;
        byte b = bArr[0];
        if (b != -63) {
            if (b == -62) {
                this.h = (byte) 5;
                this.e = (byte) 1;
                byte b2 = bArr[3];
                if ((b2 & Byte.MIN_VALUE) == -128) {
                    this.i = 500.0f;
                } else {
                    this.i = 0.0f;
                }
                ny a2 = oh.a((byte) (b2 & Byte.MAX_VALUE), bArr[2], (byte) 5, false);
                this.n = a2.f15551a;
                this.k = a2.e;
                this.l = new Date(oc.a(new byte[]{bArr[7], bArr[6], bArr[5], bArr[4]}) * 1000);
                return;
            }
            return;
        }
        byte b3 = bArr[8];
        this.h = b3;
        byte d = oc.d(b3);
        this.e = d;
        if (d == 0) {
            this.i = 0.0f;
        } else if ((bArr[3] & Byte.MIN_VALUE) == -128) {
            this.i = 500.0f;
        }
        ny a3 = oh.a((byte) (bArr[3] & Byte.MAX_VALUE), bArr[2], this.h, false);
        this.n = a3.f15551a;
        this.k = a3.e;
        this.j = oc.a(new byte[]{bArr[7], bArr[6], bArr[5], bArr[4]});
        this.l = new Date(System.currentTimeMillis());
    }

    public nr(byte[] bArr, BluetoothDevice bluetoothDevice, int i) throws nq {
        if (bArr == null || bArr.length < 2) {
            throw new nq("帧格式错误 -- 帧为空");
        }
        this.f = i;
        byte b = bArr[0];
        if (b == -54) {
            if (bArr[1] == 32) {
                a(bArr, bluetoothDevice.getAddress());
                return;
            } else {
                b(bArr, bluetoothDevice.getAddress());
                return;
            }
        }
        if (b == -1) {
            e(bArr, bluetoothDevice.getAddress());
            return;
        }
        if (b == -64) {
            c(bArr, bluetoothDevice.getAddress());
            return;
        }
        if (b == -63 || b == -62) {
            d(bArr, bluetoothDevice.getAddress());
        } else {
            if (b == 0 && bArr[1] == 1) {
                bu_(bArr, bluetoothDevice);
                return;
            }
            throw new nq("帧格式错误 -- 帧头错误");
        }
    }

    private void bu_(byte[] bArr, BluetoothDevice bluetoothDevice) {
        if (bArr.length < 17) {
            return;
        }
        oa.e("chipseaBroadcastFrame", "xiangshan" + oc.b(bArr));
        this.c = bluetoothDevice.getAddress();
        this.f15451a = bluetoothDevice.getName();
        this.m = bArr[2];
        this.d = (byte) 5;
        this.b = oc.a(oc.a(bArr, 3, 2));
        byte b = bArr[16];
        this.h = b;
        this.e = oc.f(b);
        oa.e("chipseaBroadcastFrame", "cmdId" + ((int) this.e));
        ny e = oh.e(bArr[12], bArr[13], this.h, Weight_Digit.TWO, true);
        this.n = e.f15551a;
        this.k = e.e;
        this.i = oc.a(oc.a(bArr, 14, 2));
    }
}
