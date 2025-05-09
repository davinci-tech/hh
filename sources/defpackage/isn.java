package defpackage;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class isn {
    private static boolean b(int i) {
        return i == 257 || i == 281 || i == 282;
    }

    public static List<HiHealthData> d(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(10);
        if (HiCommonUtil.d(list)) {
            LogUtil.h("HiH_HiTrackStat", "getLongestDistanceSequenceDatas() sequenceHealthDatas is null or empty");
            return arrayList;
        }
        double d = 0.0d;
        for (HiHealthData hiHealthData : list) {
            float totalDistance = ((HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class)).getTotalDistance();
            if (totalDistance > 0.0f) {
                if (0.0d < d) {
                    double d2 = totalDistance;
                    if (d2 > d) {
                        arrayList.remove(0);
                        arrayList.add(hiHealthData);
                        d = d2;
                    }
                } else {
                    d = totalDistance;
                    arrayList.add(hiHealthData);
                }
            }
        }
        return arrayList;
    }

    public static List<HiHealthData> e(List<HiHealthData> list, int i) {
        ArrayList arrayList = new ArrayList(10);
        if (HiCommonUtil.d(list)) {
            LogUtil.h("HiH_HiTrackStat", "getPaceDistanceSequenceDatasByReadType() sequenceMetaDatas is null or empty");
            return arrayList;
        }
        for (HiHealthData hiHealthData : list) {
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
            if (hiTrackMetaData.getAbnormalTrack() == 0) {
                int sportType = hiTrackMetaData.getSportType();
                switch (i) {
                    case 30015:
                    case 30016:
                        if (b(sportType)) {
                            arrayList.add(hiHealthData);
                            break;
                        } else {
                            break;
                        }
                    case 30017:
                    case 30018:
                        if (258 != sportType && 264 != sportType) {
                            break;
                        } else {
                            arrayList.add(hiHealthData);
                            break;
                        }
                    case 30019:
                    case 30020:
                        if (259 == sportType) {
                            arrayList.add(hiHealthData);
                            break;
                        } else {
                            break;
                        }
                }
            }
        }
        return arrayList;
    }
}
