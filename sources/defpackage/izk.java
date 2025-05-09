package defpackage;

import android.bluetooth.BluetoothDevice;
import com.huawei.hwbtsdk.btdatatype.callback.BtDeviceStateCallback;

/* loaded from: classes5.dex */
public class izk {

    /* renamed from: a, reason: collision with root package name */
    private BtDeviceStateCallback f13683a;
    private String b;
    private BluetoothDevice c;
    private int d;
    private String e;
    private int f;

    public int d() {
        return this.d;
    }

    public void c(int i) {
        this.d = i;
    }

    public BluetoothDevice bDA_() {
        return this.c;
    }

    public void bDB_(BluetoothDevice bluetoothDevice) {
        this.c = bluetoothDevice;
    }

    public String c() {
        return this.b;
    }

    public void d(String str) {
        this.b = str;
    }

    public String a() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public BtDeviceStateCallback b() {
        return this.f13683a;
    }

    public void d(BtDeviceStateCallback btDeviceStateCallback) {
        this.f13683a = btDeviceStateCallback;
    }

    public int h() {
        return this.f;
    }

    public void b(int i) {
        this.f = i;
    }
}
