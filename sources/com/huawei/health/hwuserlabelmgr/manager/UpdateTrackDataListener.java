package com.huawei.health.hwuserlabelmgr.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.SparseArray;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.dya;
import defpackage.jdx;
import defpackage.koq;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes3.dex */
public class UpdateTrackDataListener implements HiSubscribeListener {
    private static long e;

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f2497a;
    private Context b;
    private IBaseResponseCallback c = new IBaseResponseCallback() { // from class: com.huawei.health.hwuserlabelmgr.manager.UpdateTrackDataListener.4
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (obj instanceof List) {
                List<HiHealthData> list = (List) obj;
                if (koq.b(list)) {
                    return;
                }
                Context context = UpdateTrackDataListener.this.b;
                Context unused = UpdateTrackDataListener.this.b;
                boolean z = false;
                SharedPreferences sharedPreferences = context.getSharedPreferences("user label last modify time", 0);
                long unused2 = UpdateTrackDataListener.e = sharedPreferences.getLong("user label last modify time", 0L);
                ArrayList arrayList = new ArrayList(10);
                for (HiHealthData hiHealthData : list) {
                    if (hiHealthData != null && hiHealthData.getModifiedTime() >= UpdateTrackDataListener.e) {
                        arrayList.add(hiHealthData);
                        dya.a(UpdateTrackDataListener.this.b, hiHealthData);
                        if (HiDateUtil.g(System.currentTimeMillis()) - hiHealthData.getStartTime() > 1123200000) {
                            z = true;
                        }
                    }
                }
                if (arrayList.size() > 0) {
                    long e2 = UpdateTrackDataListener.this.e((ArrayList<HiHealthData>) arrayList);
                    if (e2 > UpdateTrackDataListener.e) {
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putLong("user label last modify time", e2);
                        edit.commit();
                    }
                    LogUtil.a("HiH_UpdateTrackDataListener", "onChange,uploadThirtyDaysTrackDataFlag= ", Boolean.valueOf(z), " newModifyTime = ", Long.valueOf(e2));
                    if (z) {
                        dya.d(UpdateTrackDataListener.this.b);
                    }
                }
            }
        }
    };

    public UpdateTrackDataListener(Context context) {
        this.b = context.getApplicationContext();
    }

    public List<Integer> c() {
        return this.f2497a;
    }

    @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
    public void onResult(List<Integer> list, List<Integer> list2) {
        if (list != null && list2 != null) {
            LogUtil.a("HiH_UpdateTrackDataListener", "onResult successList size = ", Integer.valueOf(list.size()), " ,failList size = ", Integer.valueOf(list2.size()));
        } else {
            LogUtil.a("HiH_UpdateTrackDataListener", "onResult list is null");
        }
        this.f2497a = list;
    }

    @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
    public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
        LogUtil.a("HiH_UpdateTrackDataListener", "onChange");
        jdx.b(new Runnable() { // from class: com.huawei.health.hwuserlabelmgr.manager.UpdateTrackDataListener.3
            @Override // java.lang.Runnable
            public void run() {
                UpdateTrackDataListener updateTrackDataListener = UpdateTrackDataListener.this;
                updateTrackDataListener.d(updateTrackDataListener.b, UpdateTrackDataListener.this.c);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long e(ArrayList<HiHealthData> arrayList) {
        if (koq.b(arrayList)) {
            return 0L;
        }
        int size = arrayList.size();
        long[] jArr = new long[size];
        for (int i = 0; i < arrayList.size(); i++) {
            jArr[i] = arrayList.get(i).getModifiedTime();
        }
        Arrays.sort(jArr);
        return jArr[size - 1];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Context context, final IBaseResponseCallback iBaseResponseCallback) {
        long currentTimeMillis = System.currentTimeMillis();
        long currentTimeMillis2 = System.currentTimeMillis();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(currentTimeMillis - 2592000000L);
        hiDataReadOption.setEndTime(currentTimeMillis2);
        hiDataReadOption.setType(new int[]{30002});
        HiHealthManager.d(context).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.health.hwuserlabelmgr.manager.UpdateTrackDataListener.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (i == 0 && (obj instanceof SparseArray)) {
                    SparseArray sparseArray = (SparseArray) obj;
                    if (sparseArray.get(30002) instanceof List) {
                        iBaseResponseCallback.d(0, (List) sparseArray.get(30002));
                    }
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                LogUtil.c("HiH_UpdateTrackDataListener", "getThirtyDaysTrackMetaData onResultIntent");
            }
        });
    }
}
