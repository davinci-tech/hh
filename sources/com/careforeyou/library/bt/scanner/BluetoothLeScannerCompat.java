package com.careforeyou.library.bt.scanner;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import com.careforeyou.library.enums.Protocal_Type;
import com.careforeyou.library.intface.OnWeightScalesListener;
import com.careforeyou.library.intface.SeachDeviceCallback;
import defpackage.nn;
import defpackage.np;
import defpackage.nr;
import defpackage.oa;
import defpackage.oc;

/* loaded from: classes2.dex */
public abstract class BluetoothLeScannerCompat {
    protected static final String TAG = "CsBtUtil_v11";
    private boolean mScanning = false;
    private int mRefreshInterval = 1000;
    private OnWeightScalesListener onWeightScalesListener = null;
    private SeachDeviceCallback searchCallBack = null;
    private String mBoundMac = "";
    private Runnable mScanRunnable = new Runnable() { // from class: com.careforeyou.library.bt.scanner.BluetoothLeScannerCompat.2
        @Override // java.lang.Runnable
        public void run() {
            try {
                if (BluetoothLeScannerCompat.this.isBluetoothEnable()) {
                    BluetoothLeScannerCompat.this.stopLeScanInternal();
                    if (BluetoothLeScannerCompat.this.isBluetoothEnable()) {
                        BluetoothLeScannerCompat.this.startLeScanInternal();
                        BluetoothLeScannerCompat.this.mHandler.postDelayed(this, BluetoothLeScannerCompat.this.mRefreshInterval);
                    }
                }
            } catch (Exception e) {
                oa.c(BluetoothLeScannerCompat.TAG, e.getMessage());
            }
        }
    };
    private final Handler mHandler = new Handler();

    protected abstract boolean startLeScanInternal();

    protected abstract void stopLeScanInternal();

    public void setWeightScalesListener(OnWeightScalesListener onWeightScalesListener) {
        this.onWeightScalesListener = onWeightScalesListener;
    }

    public void starSeachBindDevice(String str, SeachDeviceCallback seachDeviceCallback) {
        if (this.mScanning) {
            return;
        }
        this.mScanning = true;
        this.searchCallBack = seachDeviceCallback;
        this.mBoundMac = str;
        if (nn.e(this instanceof np)) {
            this.mRefreshInterval = 1000;
            this.mHandler.post(this.mScanRunnable);
        } else {
            startLeScanInternal();
        }
    }

    public void stopSeachBindDevice() {
        this.mScanning = false;
        this.searchCallBack = null;
        this.mBoundMac = "";
        this.mHandler.removeCallbacks(this.mScanRunnable);
        stopLeScanInternal();
    }

    public void startSearching() {
        if (this.mScanning) {
            return;
        }
        this.mScanning = true;
        this.searchCallBack = null;
        if (nn.e(this instanceof np)) {
            oa.e(TAG, "++LollipopBluetoothLeScannerImpl+");
            this.mRefreshInterval = 1000;
            this.mHandler.post(this.mScanRunnable);
        } else {
            oa.e(TAG, "++JBBluetoothLeScannerImpl+");
            this.mRefreshInterval = 30000;
            this.mHandler.post(this.mScanRunnable);
        }
    }

    public void stopSearching() {
        this.mScanning = false;
        this.mHandler.removeCallbacks(this.mScanRunnable);
        stopLeScanInternal();
    }

    protected boolean isBluetoothEnable() {
        if (BluetoothAdapter.getDefaultAdapter() != null) {
            int state = BluetoothAdapter.getDefaultAdapter().getState();
            if (BluetoothAdapter.getDefaultAdapter().isEnabled() && state == 12) {
                return true;
            }
        }
        return false;
    }

    private d getProtocalTypeByBroadcast(BluetoothDevice bluetoothDevice, byte[] bArr) {
        d dVar = new d();
        dVar.d = Protocal_Type.UNKNOWN;
        int i = 0;
        boolean z = false;
        while (i < bArr.length) {
            try {
                byte b = bArr[i];
                int i2 = i + 1;
                if (b < 0 && i2 + b > bArr.length) {
                    break;
                }
                byte[] a2 = oc.a(bArr, i2, b);
                i = i2 + b;
                if (a2.length > 0) {
                    if (a2[0] == -1 && a2.length >= 4) {
                        dVar.c = new byte[a2.length - 1];
                        dVar.c = oc.a(a2, 1, dVar.c.length);
                        byte b2 = a2[1];
                        if (b2 != -54 && b2 != -64 && b2 != -63 && b2 != -62) {
                            if (b2 == -88 && a2[2] == 1) {
                                byte b3 = a2[3];
                            } else {
                                if (b2 == -1 && a2[2] == -16) {
                                    byte b4 = a2[3];
                                    if (b4 == 3) {
                                        dVar.d = Protocal_Type.OKOKCloudV3;
                                    } else {
                                        if (b4 != 16 && b4 != 17) {
                                            dVar.d = Protocal_Type.OKOKCloud;
                                        }
                                        dVar.d = Protocal_Type.OKOKCloudV4;
                                    }
                                } else if (b2 == 0 && a2[2] == 1) {
                                    dVar.d = Protocal_Type.XIANGSHAN;
                                }
                                z = true;
                            }
                        }
                        dVar.d = Protocal_Type.OKOK;
                        z = true;
                    }
                    if (z) {
                        break;
                    }
                }
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return dVar;
    }

    public void handleBroadcastInfo(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        oa.e(TAG, "++BluetoothDevice++name:" + bluetoothDevice.getName() + "++mac++" + bluetoothDevice.getAddress());
        d dVar = new d();
        if (this.searchCallBack != null && this.mBoundMac != null) {
            if (bluetoothDevice.getAddress().equalsIgnoreCase(this.mBoundMac)) {
                this.searchCallBack.success();
                return;
            }
        } else {
            dVar = getProtocalTypeByBroadcast(bluetoothDevice, bArr);
        }
        if (dVar.d == Protocal_Type.OKOK || dVar.d == Protocal_Type.OKOKCloud || dVar.d == Protocal_Type.OKOKCloudV3 || dVar.d == Protocal_Type.OKOKCloudV4 || dVar.d == Protocal_Type.XIANGSHAN) {
            try {
                nr nrVar = new nr(dVar.c, bluetoothDevice, i);
                nrVar.g = dVar.d;
                OnWeightScalesListener onWeightScalesListener = this.onWeightScalesListener;
                if (onWeightScalesListener != null) {
                    onWeightScalesListener.broadcastChipseaData(nrVar);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class d {
        public byte[] c;
        public Protocal_Type d;

        private d() {
        }
    }
}
