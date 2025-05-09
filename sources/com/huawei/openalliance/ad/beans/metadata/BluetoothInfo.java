package com.huawei.openalliance.ad.beans.metadata;

import android.text.TextUtils;
import com.huawei.openalliance.ad.utils.cz;

/* loaded from: classes5.dex */
public class BluetoothInfo implements Comparable<BluetoothInfo> {
    private String address;
    private int bondState = 0;
    private String name;

    public void b(String str) {
        this.address = str;
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            this.name = "";
        }
        this.name = cz.n(str);
    }

    public void a(Integer num) {
        this.bondState = num.intValue();
    }

    public String a() {
        return this.address;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(BluetoothInfo bluetoothInfo) {
        if (bluetoothInfo == null) {
            return -1;
        }
        return bluetoothInfo.bondState - this.bondState;
    }
}
