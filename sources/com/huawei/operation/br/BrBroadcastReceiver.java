package com.huawei.operation.br;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.util.LogUtil;

/* loaded from: classes5.dex */
public class BrBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "BrBroadcastReceiver";
    private final OnBluetoothReceiverListener mOnBluetoothReceiverListener;

    public interface OnBluetoothReceiverListener {
        void onDeviceFound(BluetoothDevice bluetoothDevice);

        void onScanFinish();

        void onStateChanged(int i);
    }

    public BrBroadcastReceiver(Context context, OnBluetoothReceiverListener onBluetoothReceiverListener) {
        this.mOnBluetoothReceiverListener = onBluetoothReceiverListener;
        BroadcastManagerUtil.bFB_(context, this, getBluetoothIntentFilter());
    }

    private IntentFilter getBluetoothIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        try {
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
            intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
            intentFilter.addAction("android.bluetooth.device.action.FOUND");
        } catch (SecurityException e) {
            LogUtil.e(TAG, "getBluetoothIntentFilter SecurityException:", ExceptionUtils.d(e));
        }
        return intentFilter;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007d A[Catch: SecurityException -> 0x008b, TryCatch #0 {SecurityException -> 0x008b, blocks: (B:9:0x000c, B:11:0x0019, B:12:0x0028, B:25:0x006d, B:27:0x0071, B:30:0x0075, B:32:0x0079, B:35:0x007d, B:37:0x0087, B:40:0x0047, B:43:0x0051, B:46:0x005b), top: B:8:0x000c }] */
    @Override // android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onReceive(android.content.Context r8, android.content.Intent r9) {
        /*
            r7 = this;
            java.lang.String r8 = "BrBroadcastReceiver"
            if (r9 != 0) goto L5
            return
        L5:
            java.lang.String r0 = r9.getAction()
            if (r0 != 0) goto Lc
            return
        Lc:
            java.lang.String r1 = "android.bluetooth.device.extra.DEVICE"
            android.os.Parcelable r1 = r9.getParcelableExtra(r1)     // Catch: java.lang.SecurityException -> L8b
            android.bluetooth.BluetoothDevice r1 = (android.bluetooth.BluetoothDevice) r1     // Catch: java.lang.SecurityException -> L8b
            r2 = 1
            r3 = 0
            r4 = 2
            if (r1 == 0) goto L28
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch: java.lang.SecurityException -> L8b
            java.lang.String r6 = "BluetoothDevice name is:"
            r5[r3] = r6     // Catch: java.lang.SecurityException -> L8b
            java.lang.String r6 = r1.getName()     // Catch: java.lang.SecurityException -> L8b
            r5[r2] = r6     // Catch: java.lang.SecurityException -> L8b
            health.compact.a.util.LogUtil.d(r8, r5)     // Catch: java.lang.SecurityException -> L8b
        L28:
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch: java.lang.SecurityException -> L8b
            java.lang.String r6 = "action is "
            r5[r3] = r6     // Catch: java.lang.SecurityException -> L8b
            r5[r2] = r0     // Catch: java.lang.SecurityException -> L8b
            health.compact.a.util.LogUtil.d(r8, r5)     // Catch: java.lang.SecurityException -> L8b
            int r5 = r0.hashCode()     // Catch: java.lang.SecurityException -> L8b
            r6 = -1780914469(0xffffffff95d966db, float:-8.780788E-26)
            if (r5 == r6) goto L5b
            r6 = -1530327060(0xffffffffa4c90fec, float:-8.719683E-17)
            if (r5 == r6) goto L51
            r6 = 1167529923(0x459717c3, float:4834.97)
            if (r5 == r6) goto L47
            goto L65
        L47:
            java.lang.String r5 = "android.bluetooth.device.action.FOUND"
            boolean r0 = r0.equals(r5)     // Catch: java.lang.SecurityException -> L8b
            if (r0 == 0) goto L65
            r0 = r4
            goto L66
        L51:
            java.lang.String r5 = "android.bluetooth.adapter.action.STATE_CHANGED"
            boolean r0 = r0.equals(r5)     // Catch: java.lang.SecurityException -> L8b
            if (r0 == 0) goto L65
            r0 = r3
            goto L66
        L5b:
            java.lang.String r5 = "android.bluetooth.adapter.action.DISCOVERY_FINISHED"
            boolean r0 = r0.equals(r5)     // Catch: java.lang.SecurityException -> L8b
            if (r0 == 0) goto L65
            r0 = r2
            goto L66
        L65:
            r0 = -1
        L66:
            if (r0 == 0) goto L7d
            if (r0 == r2) goto L75
            if (r0 == r4) goto L6d
            goto L99
        L6d:
            com.huawei.operation.br.BrBroadcastReceiver$OnBluetoothReceiverListener r9 = r7.mOnBluetoothReceiverListener     // Catch: java.lang.SecurityException -> L8b
            if (r9 == 0) goto L99
            r9.onDeviceFound(r1)     // Catch: java.lang.SecurityException -> L8b
            goto L99
        L75:
            com.huawei.operation.br.BrBroadcastReceiver$OnBluetoothReceiverListener r9 = r7.mOnBluetoothReceiverListener     // Catch: java.lang.SecurityException -> L8b
            if (r9 == 0) goto L99
            r9.onScanFinish()     // Catch: java.lang.SecurityException -> L8b
            goto L99
        L7d:
            java.lang.String r0 = "android.bluetooth.adapter.extra.STATE"
            int r9 = r9.getIntExtra(r0, r3)     // Catch: java.lang.SecurityException -> L8b
            com.huawei.operation.br.BrBroadcastReceiver$OnBluetoothReceiverListener r0 = r7.mOnBluetoothReceiverListener     // Catch: java.lang.SecurityException -> L8b
            if (r0 == 0) goto L99
            r0.onStateChanged(r9)     // Catch: java.lang.SecurityException -> L8b
            goto L99
        L8b:
            r9 = move-exception
            java.lang.String r0 = "onReceive SecurityException:"
            java.lang.String r9 = com.huawei.haf.common.exception.ExceptionUtils.d(r9)
            java.lang.Object[] r9 = new java.lang.Object[]{r0, r9}
            health.compact.a.util.LogUtil.e(r8, r9)
        L99:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.operation.br.BrBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }
}
