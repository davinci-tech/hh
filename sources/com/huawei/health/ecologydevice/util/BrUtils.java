package com.huawei.health.ecologydevice.util;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.ecologydevice.util.BrUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes3.dex */
public class BrUtils {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2344a = false;

    public interface OnBrBondListener {
        void onBondError(int i);

        void onBondSuccess(BluetoothDevice bluetoothDevice);
    }

    public interface OnBrDiscoverListener {
        boolean onDiscoverResult(BluetoothDevice bluetoothDevice);

        void onDiscoverUnResult();
    }

    public interface OnBrOpenListener {
        void onStateError();

        void onStateOff();

        void onStateOn();
    }

    /* loaded from: classes8.dex */
    public interface OnConnectListener {
        void onConnectFailure(String str);

        void onConnectSuccess(BluetoothSocket bluetoothSocket);
    }

    public interface OnSendDataListener {
        void onCommandFailure(String str);

        boolean onCommandSuccess(byte[] bArr, int i);
    }

    public boolean d() {
        return BluetoothAdapter.getDefaultAdapter().isEnabled();
    }

    public boolean a(OnBrOpenListener onBrOpenListener) {
        c(onBrOpenListener);
        try {
            return BluetoothAdapter.getDefaultAdapter().enable();
        } catch (SecurityException e2) {
            LogUtil.b("BrUtils", "judgeBluetoothEnable SecurityException:", ExceptionUtils.d(e2));
            return false;
        }
    }

    public Set<BluetoothDevice> c() {
        HashSet hashSet = new HashSet(Arrays.asList(new BluetoothDevice[0]));
        try {
            return BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        } catch (SecurityException e2) {
            LogUtil.b("BrUtils", "getBondedDevices SecurityException:", ExceptionUtils.d(e2));
            return hashSet;
        }
    }

    public BluetoothDevice Wa_(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (BluetoothDevice bluetoothDevice : c()) {
            if (str.equals(bluetoothDevice.getAddress())) {
                return bluetoothDevice;
            }
        }
        return null;
    }

    public BluetoothDevice Wb_(String str) {
        return BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str);
    }

    public boolean d(String str) {
        if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            return false;
        }
        BluetoothDevice Wb_ = Wb_(str);
        try {
            Method declaredMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(Wb_, new Object[0]);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
            return false;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            LogUtil.b("BrUtils", "isConnectDevice Exception:", e2.getMessage());
            return false;
        }
    }

    public boolean c(OnBrDiscoverListener onBrDiscoverListener) {
        e(onBrDiscoverListener);
        e();
        try {
            return BluetoothAdapter.getDefaultAdapter().startDiscovery();
        } catch (SecurityException e2) {
            LogUtil.b("BrUtils", "discoverBr SecurityException:", ExceptionUtils.d(e2));
            return false;
        }
    }

    public void e() {
        try {
            if (BluetoothAdapter.getDefaultAdapter().isDiscovering()) {
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
            }
        } catch (SecurityException e2) {
            LogUtil.b("BrUtils", "closeBrDiscover SecurityException:", ExceptionUtils.d(e2));
        }
    }

    public boolean VZ_(BluetoothDevice bluetoothDevice, OnBrBondListener onBrBondListener) {
        if (bluetoothDevice == null) {
            LogUtil.h("BrUtils", "BluetoothDevice is null.");
            onBrBondListener.onBondError(-1);
            return false;
        }
        d(onBrBondListener);
        try {
            return bluetoothDevice.createBond();
        } catch (SecurityException e2) {
            LogUtil.b("BrUtils", "brBond SecurityException:", ExceptionUtils.d(e2));
            return false;
        }
    }

    /* renamed from: com.huawei.health.ecologydevice.util.BrUtils$3, reason: invalid class name */
    class AnonymousClass3 extends Handler {
        final /* synthetic */ BrUtils c;
        final /* synthetic */ OnSendDataListener d;

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i != 2) {
                if (i == 3) {
                    String str = (String) message.obj;
                    LogUtil.b("BrUtils", "Receiver Exception:", str);
                    this.d.onCommandFailure(str);
                    return;
                }
                LogUtil.b("BrUtils", "Unknown Exception:", Integer.valueOf(message.what));
                return;
            }
            int i2 = message.arg1;
            byte[] byteArray = message.getData().getByteArray("0x02");
            if (!this.c.f2344a) {
                this.c.f2344a = this.d.onCommandSuccess(byteArray, i2);
            }
            LogUtil.a("BrUtils", "mDataCorrect:", Boolean.valueOf(this.c.f2344a));
        }
    }

    private void c(OnBrOpenListener onBrOpenListener) {
        b bVar = new b(this, null);
        bVar.b(onBrOpenListener);
        BaseApplication.getContext().registerReceiver(bVar, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(b bVar) {
        LogUtil.c("BrUtils", "unregisterBrOpenReceiver");
        BaseApplication.getContext().unregisterReceiver(bVar);
    }

    class b extends BroadcastReceiver {
        private OnBrOpenListener d;

        private b() {
        }

        /* synthetic */ b(BrUtils brUtils, AnonymousClass3 anonymousClass3) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE)) {
                    case 10:
                        LogUtil.c("BrUtils", "Bluetooth state Off");
                        this.d.onStateOff();
                        BrUtils.this.e(this);
                        break;
                    case 11:
                        LogUtil.c("BrUtils", "Bluetooth state Turning On");
                        break;
                    case 12:
                        LogUtil.c("BrUtils", "Bluetooth state On");
                        this.d.onStateOn();
                        BrUtils.this.e(this);
                        break;
                    case 13:
                        LogUtil.c("BrUtils", "Bluetooth state Turning Off");
                        break;
                    default:
                        LogUtil.c("BrUtils", "Bluetooth state Error");
                        this.d.onStateError();
                        BrUtils.this.e(this);
                        break;
                }
            }
        }

        public void b(OnBrOpenListener onBrOpenListener) {
            this.d = onBrOpenListener;
        }
    }

    private void e(OnBrDiscoverListener onBrDiscoverListener) {
        e eVar = new e();
        eVar.c(onBrDiscoverListener);
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
            intentFilter.addAction("android.bluetooth.device.action.FOUND");
            BaseApplication.getContext().registerReceiver(eVar, intentFilter);
        } catch (SecurityException e2) {
            LogUtil.b("BrUtils", "registerBrFoundReceiver SecurityException:", ExceptionUtils.d(e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(e eVar) {
        LogUtil.c("BrUtils", "unregisterBrFoundReceiver");
        BaseApplication.getContext().unregisterReceiver(eVar);
    }

    public class e extends BroadcastReceiver {

        /* renamed from: a, reason: collision with root package name */
        private boolean f2346a = false;
        private OnBrDiscoverListener b;
        private Runnable c;
        private final Handler d;

        e() {
            Handler handler = new Handler(Looper.getMainLooper());
            this.d = handler;
            Runnable runnable = new Runnable() { // from class: djw
                @Override // java.lang.Runnable
                public final void run() {
                    BrUtils.e.this.d();
                }
            };
            this.c = runnable;
            handler.postDelayed(runnable, 8000L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            this.d.removeCallbacks(this.c);
            BrUtils.this.e();
            BrUtils.this.c(this);
            if (this.f2346a) {
                return;
            }
            this.b.onDiscoverUnResult();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            action.hashCode();
            if (action.equals("android.bluetooth.adapter.action.DISCOVERY_FINISHED")) {
                LogUtil.c("BrUtils", "Finished Found.");
                d();
            } else if (action.equals("android.bluetooth.device.action.FOUND")) {
                boolean onDiscoverResult = this.b.onDiscoverResult((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"));
                this.f2346a = onDiscoverResult;
                if (onDiscoverResult) {
                    d();
                }
            }
        }

        public void c(OnBrDiscoverListener onBrDiscoverListener) {
            this.b = onBrDiscoverListener;
        }
    }

    private void d(OnBrBondListener onBrBondListener) {
        a aVar = new a();
        aVar.a(onBrBondListener);
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
            BaseApplication.getContext().registerReceiver(aVar, intentFilter);
        } catch (SecurityException e2) {
            LogUtil.b("BrUtils", "registerBrBondReceiver SecurityException:", ExceptionUtils.d(e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(a aVar) {
        LogUtil.c("BrUtils", "unregisterBrBondReceiver");
        BaseApplication.getContext().unregisterReceiver(aVar);
    }

    public class a extends BroadcastReceiver {

        /* renamed from: a, reason: collision with root package name */
        private boolean f2345a = false;
        private final Runnable b;
        private OnBrBondListener c;
        private final Handler d;

        a() {
            Handler handler = new Handler(Looper.getMainLooper());
            this.d = handler;
            Runnable runnable = new Runnable() { // from class: djv
                @Override // java.lang.Runnable
                public final void run() {
                    BrUtils.a.this.e();
                }
            };
            this.b = runnable;
            handler.postDelayed(runnable, 12000L);
        }

        public /* synthetic */ void e() {
            this.f2345a = true;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice bluetoothDevice;
            int bondState;
            try {
                if ("android.bluetooth.device.action.BOND_STATE_CHANGED".equals(intent.getAction()) && (bondState = (bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")).getBondState()) != 11) {
                    if (bondState != 12) {
                        BrUtils.this.a(this);
                        this.d.removeCallbacks(this.b);
                        if (this.f2345a) {
                            this.c.onBondError(12000);
                        } else {
                            this.c.onBondError(bluetoothDevice.getBondState());
                        }
                    } else {
                        BrUtils.this.a(this);
                        this.d.removeCallbacks(this.b);
                        this.c.onBondSuccess(bluetoothDevice);
                    }
                }
            } catch (SecurityException e) {
                LogUtil.b("BrUtils", "onReceive SecurityException:", ExceptionUtils.d(e));
            }
        }

        public void a(OnBrBondListener onBrBondListener) {
            this.c = onBrBondListener;
        }
    }
}
