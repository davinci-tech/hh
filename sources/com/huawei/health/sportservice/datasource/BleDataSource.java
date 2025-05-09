package com.huawei.health.sportservice.datasource;

import android.os.Bundle;
import com.huawei.health.sportservice.inter.BleDataSourceCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class BleDataSource {
    private static final String TAG = "BlueDataSource";
    private Map<Integer, BleDataSourceCallback> mBleDataMap = new LinkedHashMap();
    private List<BleDataSource> mDataSourceList = new LinkedList();

    public void addBlueDataSource(BleDataSource bleDataSource) {
        if (bleDataSource != null) {
            this.mDataSourceList.add(bleDataSource);
        }
    }

    public void registerDataCallback(int i, BleDataSourceCallback bleDataSourceCallback) {
        this.mBleDataMap.put(Integer.valueOf(i), bleDataSourceCallback);
    }

    public void registerDataCallback(List<Integer> list, BleDataSourceCallback bleDataSourceCallback) {
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            this.mBleDataMap.put(it.next(), bleDataSourceCallback);
        }
    }

    public void setData(Bundle bundle) {
        if (bundle == null) {
            LogUtil.b(TAG, "setData bundle is null.");
            return;
        }
        if (bundle.getSerializable("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA") instanceof HashMap) {
            HashMap hashMap = (HashMap) bundle.getSerializable("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA");
            if (hashMap == null) {
                LogUtil.b(TAG, "setData indoorLinkData is null.");
                return;
            }
            for (Map.Entry entry : hashMap.entrySet()) {
                convertDataFromBle(((Integer) entry.getKey()).intValue(), entry.getValue());
            }
            Iterator<BleDataSource> it = this.mDataSourceList.iterator();
            while (it.hasNext()) {
                it.next().setData(bundle);
            }
        }
    }

    private <E> void convertDataFromBle(int i, E e) {
        LogUtil.a(TAG, "convertDataFromBle ", Integer.valueOf(i), "value ", e);
        Map<Integer, BleDataSourceCallback> map = this.mBleDataMap;
        if (map == null || !map.containsKey(Integer.valueOf(i))) {
            return;
        }
        this.mBleDataMap.get(Integer.valueOf(i)).onBleDataChange(i, e);
    }
}
