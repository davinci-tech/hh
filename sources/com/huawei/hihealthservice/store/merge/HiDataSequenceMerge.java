package com.huawei.hihealthservice.store.merge;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import defpackage.iiz;
import defpackage.ivx;
import defpackage.iwb;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class HiDataSequenceMerge implements HiMergeCommon {
    private iiz d;

    public HiDataSequenceMerge(Context context) {
        this.d = iiz.a(context);
    }

    @Override // com.huawei.hihealthservice.store.merge.HiMergeCommon
    public boolean merge(HiHealthData hiHealthData, int i, List<Integer> list) {
        long currentTimeMillis = System.currentTimeMillis();
        List<HiHealthData> b = this.d.b(list, hiHealthData.getStartTime(), hiHealthData.getType());
        if (b == null || b.isEmpty()) {
            if (hiHealthData.getSyncStatus() == 2) {
                return true;
            }
            long a2 = this.d.a(hiHealthData, i, 0);
            LogUtil.a("Debug_HiDataSequenceMerge", "sequenceDataMerge insertSequenceData changeResult =  ", Long.valueOf(a2));
            return a2 > 0;
        }
        if (a(hiHealthData, i, b)) {
            return true;
        }
        int type = hiHealthData.getType();
        if (type == 31001) {
            Collections.sort(b, new ivx.g());
        } else {
            Collections.sort(b, new ivx.h(BleConstants.TOTAL_DISTANCE));
        }
        boolean c = c(i, b, type);
        LogUtil.c("Debug_HiDataSequenceMerge", "sequenceDataMerge use time is ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return c;
    }

    private boolean c(int i, List<HiHealthData> list, int i2) {
        boolean z = false;
        HiHealthData hiHealthData = list.get(0);
        int size = list.size();
        int i3 = 1;
        int i4 = 1;
        boolean z2 = true;
        while (i4 < size) {
            HiHealthData hiHealthData2 = list.get(i4);
            if (i2 == 30001 || i2 == 30003) {
                HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData2.getMetaData(), HiTrackMetaData.class);
                hiTrackMetaData.setDuplicated(i3);
                hiHealthData2.setMetaData(HiJsonUtil.d(hiTrackMetaData, HiTrackMetaData.class));
                hiHealthData2.setSyncStatus(0);
            }
            if (hiHealthData2.getClientId() == i) {
                boolean d = this.d.d(hiHealthData2, i, i3);
                LogUtil.c("Debug_HiDataSequenceMerge", "sequenceDataMerge insertOrUpdateSequenceData merge status change ", Boolean.valueOf(d));
                if (d) {
                    i4++;
                    i3 = 1;
                }
                z2 = false;
                i4++;
                i3 = 1;
            } else {
                long b = this.d.b(hiHealthData2, i3);
                LogUtil.c("Debug_HiDataSequenceMerge", "sequenceDataMerge insertOrUpdateSequenceData merge status change ", Long.valueOf(b));
                if (b > 0) {
                    i4++;
                    i3 = 1;
                }
                z2 = false;
                i4++;
                i3 = 1;
            }
        }
        if (hiHealthData.getClientId() == i) {
            if (z2 && this.d.d(hiHealthData, i, 0)) {
                z = true;
            }
            LogUtil.c("Debug_HiDataSequenceMerge", "sequenceDataMerge insertOrUpdateSequenceData maxData merge isSuccess =  ", Boolean.valueOf(z));
            return z;
        }
        long b2 = this.d.b(hiHealthData, hiHealthData.getClientId(), 0);
        LogUtil.c("Debug_HiDataSequenceMerge", "sequenceDataMerge updateSequenceDataMerge maxData changeResult =  ", Long.valueOf(b2));
        return z2 && b2 > 0;
    }

    private boolean a(HiHealthData hiHealthData, int i, List<HiHealthData> list) {
        Iterator<HiHealthData> it = list.iterator();
        while (true) {
            if (it.hasNext()) {
                HiHealthData next = it.next();
                if (iwb.b(next.getClientId(), i) && hiHealthData.getSyncStatus() != 2) {
                    if (next.getStartTime() == hiHealthData.getStartTime() && next.getEndTime() == hiHealthData.getEndTime()) {
                        if (next.getMetaData().equals(hiHealthData.getMetaData())) {
                            LogUtil.b("HiH_HiDataSequenceMerge", "same sequence data, does not merge!");
                            return true;
                        }
                        if (d(hiHealthData, next)) {
                            return true;
                        }
                    }
                    if (hiHealthData.getSyncStatus() != 1 || next.getInt("merged") != 2) {
                        next.putInt("merged", 0);
                    }
                    next.setSequenceData(hiHealthData.getSequenceData());
                    next.setMetaData(hiHealthData.getMetaData());
                    next.setSyncStatus(hiHealthData.getSyncStatus());
                    next.setModifiedTime(System.currentTimeMillis());
                }
            } else {
                if (hiHealthData.getSyncStatus() == 2) {
                    return false;
                }
                LogUtil.a("HiH_HiDataSequenceMerge", "mergeSequenceData is new data");
                hiHealthData.setClientId(i);
                hiHealthData.putInt("merged", 0);
                hiHealthData.setModifiedTime(System.currentTimeMillis());
                list.add(hiHealthData);
            }
        }
        return false;
    }

    private boolean d(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
        if (hiHealthData2.getType() != 30001) {
            return false;
        }
        HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData2.getMetaData(), HiTrackMetaData.class);
        HiTrackMetaData hiTrackMetaData2 = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
        HiTrackMetaData hiTrackMetaData3 = (HiTrackMetaData) HiJsonUtil.e(hiHealthData2.getMetaData(), HiTrackMetaData.class);
        if (!d(hiTrackMetaData, hiTrackMetaData2) || hiTrackMetaData3.getDuplicated() != 1) {
            return false;
        }
        LogUtil.b("HiH_HiDataSequenceMerge", "meta data only dumplicate diff, does not merge!");
        return true;
    }

    private boolean d(HiTrackMetaData hiTrackMetaData, HiTrackMetaData hiTrackMetaData2) {
        int duplicated = hiTrackMetaData.getDuplicated();
        int duplicated2 = hiTrackMetaData2.getDuplicated();
        if (duplicated == duplicated2) {
            return false;
        }
        hiTrackMetaData.setDuplicated(duplicated2);
        return HiJsonUtil.e(hiTrackMetaData).equals(HiJsonUtil.e(hiTrackMetaData2));
    }
}
