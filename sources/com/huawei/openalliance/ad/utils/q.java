package com.huawei.openalliance.ad.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.common.Constant;
import com.huawei.openalliance.ad.beans.metadata.BluetoothInfo;
import com.huawei.openalliance.ad.ho;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/* loaded from: classes5.dex */
public class q {

    public interface a {
        void a(List<BluetoothInfo> list, int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(a aVar, List<BluetoothInfo> list) {
        a(aVar, list, bg.a(list) ? 3 : 0);
    }

    private static void a(a aVar, List<BluetoothInfo> list, int i) {
        if (aVar != null) {
            try {
                Collections.sort(list);
            } catch (Throwable th) {
                ho.c("BluetoothUtils", "sort bluetoothInfos exception: %s", th.getClass().getSimpleName());
            }
            aVar.a(list, i);
        }
    }

    public static void a(Context context, final a aVar) {
        final ArrayList arrayList = new ArrayList();
        if (context == null) {
            a(aVar, arrayList, 4);
            return;
        }
        Context applicationContext = context.getApplicationContext();
        if (!cd.a(applicationContext)) {
            ho.b("BluetoothUtils", "missing permissions");
            a(aVar, arrayList, 1);
            return;
        }
        final HashMap hashMap = new HashMap();
        try {
            final BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null && defaultAdapter.isEnabled()) {
                Set<BluetoothDevice> bondedDevices = defaultAdapter.getBondedDevices();
                if (bg.a(bondedDevices)) {
                    a(aVar, arrayList, 3);
                    return;
                }
                for (BluetoothDevice bluetoothDevice : bondedDevices) {
                    if (bluetoothDevice != null) {
                        BluetoothInfo bluetoothInfo = new BluetoothInfo();
                        bluetoothInfo.a(bluetoothDevice.getName());
                        bluetoothInfo.b(bluetoothDevice.getAddress());
                        arrayList.add(bluetoothInfo);
                        hashMap.put(bluetoothInfo.a(), bluetoothInfo);
                    }
                }
                int a2 = a(defaultAdapter);
                ho.b("BluetoothUtils", "BluetoothProfile: %s", Integer.valueOf(a2));
                if (a2 == -1) {
                    b(aVar, arrayList);
                    return;
                } else {
                    defaultAdapter.getProfileProxy(applicationContext, new BluetoothProfile.ServiceListener() { // from class: com.huawei.openalliance.ad.utils.q.1
                        @Override // android.bluetooth.BluetoothProfile.ServiceListener
                        public void onServiceDisconnected(int i) {
                            ho.b("BluetoothUtils", "onServiceDisconnected");
                            q.b(a.this, arrayList);
                        }

                        @Override // android.bluetooth.BluetoothProfile.ServiceListener
                        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                            BluetoothInfo bluetoothInfo2;
                            try {
                                ho.b("BluetoothUtils", Constant.SERVICE_CONNECT_MESSAGE);
                                List<BluetoothDevice> connectedDevices = bluetoothProfile.getConnectedDevices();
                                if (!bg.a(connectedDevices)) {
                                    for (BluetoothDevice bluetoothDevice2 : connectedDevices) {
                                        if (bluetoothDevice2 != null) {
                                            String address = bluetoothDevice2.getAddress();
                                            if (!TextUtils.isEmpty(address) && (bluetoothInfo2 = (BluetoothInfo) hashMap.get(address)) != null) {
                                                bluetoothInfo2.a((Integer) 1);
                                            }
                                        }
                                    }
                                }
                            } catch (Throwable th) {
                                ho.c("BluetoothUtils", "onServiceConnected exception: %s", th.getClass().getSimpleName());
                            }
                            defaultAdapter.closeProfileProxy(i, bluetoothProfile);
                            q.b(a.this, arrayList);
                        }
                    }, a2);
                    return;
                }
            }
            ho.b("BluetoothUtils", "bluetooth service is unavailable");
            a(aVar, arrayList, 2);
        } catch (Throwable th) {
            ho.c("BluetoothUtils", "getBondedBluetoothDevices exception: %s", th.getClass().getSimpleName());
            a(aVar, arrayList, 5);
        }
    }

    private static int a(BluetoothAdapter bluetoothAdapter) {
        if (bluetoothAdapter.getProfileConnectionState(2) == 2) {
            return 2;
        }
        return bluetoothAdapter.getProfileConnectionState(1) == 2 ? 1 : -1;
    }
}
