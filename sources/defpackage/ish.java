package defpackage;

import android.content.Context;
import android.os.RemoteException;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiDivideUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import health.compact.a.HiCommonUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes7.dex */
public class ish {
    public static void b(int i, List<Integer> list, HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener, Context context) throws RemoteException {
        a(i, list, hiDataReadOption, iDataReadResultListener, context, null);
    }

    public static void a(int i, List<Integer> list, HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener, Context context, ifl iflVar) throws RemoteException {
        List<HiHealthData> e;
        List<HiHealthData> e2;
        if (hiDataReadOption == null || list == null) {
            return;
        }
        iiz a2 = iiz.a(context);
        long startTime = hiDataReadOption.getStartTime();
        long endTime = hiDataReadOption.getEndTime();
        int readType = hiDataReadOption.getReadType();
        if (i != 31001) {
            switch (i) {
                case 30001:
                case OnRegisterSkinAttrsListener.REGISTER_BY_SCAN /* 30003 */:
                    if (readType == 0) {
                        e2 = a2.b(startTime, endTime, 30001, list);
                    } else {
                        e2 = a2.e(startTime, endTime, 30001, list);
                    }
                    HiDivideUtil.e(i, b(e2, context), iDataReadResultListener);
                    break;
                case 30002:
                    HiDivideUtil.d(i, e(list, hiDataReadOption, context, a2, readType, iflVar), iDataReadResultListener);
                    break;
                case 30004:
                    HiDivideUtil.d(i, a(list, hiDataReadOption, a2, readType), iDataReadResultListener);
                    break;
                default:
                    a(i, list, hiDataReadOption, iDataReadResultListener, context);
                    break;
            }
        }
        if (readType == 0) {
            e = a2.b(startTime, endTime, 31001, list);
        } else {
            e = a2.e(startTime, endTime, 31001, list);
        }
        HiDivideUtil.e(i, b(e, context), iDataReadResultListener);
    }

    private static List<HiHealthData> e(List<Integer> list, HiDataReadOption hiDataReadOption, Context context, iiz iizVar, int i, ifl iflVar) {
        List<HiHealthData> d;
        if (i == 0) {
            d = iizVar.c(list, hiDataReadOption, 30001, iflVar.j());
        } else {
            d = iizVar.d(list, hiDataReadOption, 30001, iflVar.j());
        }
        return b(d, context);
    }

    private static List<HiHealthData> a(List<Integer> list, HiDataReadOption hiDataReadOption, iiz iizVar, int i) {
        if (i == 0) {
            return iizVar.e(list, hiDataReadOption, 30001);
        }
        return iizVar.j(list, hiDataReadOption, 30001);
    }

    private static void a(int i, List<Integer> list, HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener, Context context) throws RemoteException {
        List<HiHealthData> d;
        LogUtil.c("HiH_HiHealthDataRd", "readHiHealthData() type == HiHealthDataType.SEQUENCE");
        iiz a2 = iiz.a(context);
        int readType = hiDataReadOption.getReadType();
        if (i != 30021 && i != 30023 && i != 30025 && i != 30027) {
            switch (i) {
                case 30005:
                case 30006:
                case 30007:
                case 30008:
                    break;
                default:
                    switch (i) {
                        case 31002:
                        case 31003:
                        case 31004:
                        case 31005:
                        case 31006:
                        case 31007:
                            List<HiHealthData> a3 = a2.a(list, hiDataReadOption, i);
                            List<HiHealthData> b = b(a3, context);
                            isi.a(context, 61001, a3);
                            HiDivideUtil.d(i, b, iDataReadResultListener);
                            break;
                        default:
                            d(i, list, hiDataReadOption, iDataReadResultListener, context);
                            break;
                    }
            }
        }
        if (readType == 0) {
            d = a2.b(list, hiDataReadOption, 30001);
        } else {
            d = a2.d(list, hiDataReadOption, 30001);
        }
        List<HiHealthData> c = c(d, i);
        if (!koq.b(c)) {
            LogUtil.c("HiH_HiHealthDataRd", "readHiHealthData() SEQUENCE  ReadSequenceDatasByReadType, sequenceType is ", Integer.valueOf(i), ", size is ", Integer.valueOf(c.size()));
        }
        HiDivideUtil.d(i, b(c, context), iDataReadResultListener);
    }

    private static void d(int i, List<Integer> list, HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener, Context context) throws RemoteException {
        LogUtil.c("HiH_HiHealthDataRd", "readHiHealthData() type == HiHealthDataType.SEQUENCE");
        iiz a2 = iiz.a(context);
        if (i == 30013) {
            List<HiHealthData> d = isn.d(a2.b(list, hiDataReadOption, 30001));
            LogUtil.c("HiH_HiHealthDataRd", "readHiHealthData() SEQUENCE  ReadSequenceDatas, sequenceType is ", Integer.valueOf(i), ", size is ", Integer.valueOf(d.size()));
            a(list, hiDataReadOption, iDataReadResultListener, d, context);
        } else {
            if (i == 30015 || i == 30017 || i == 30019) {
                List<HiHealthData> d2 = isn.d(isn.e(a2.b(list, hiDataReadOption, 30001), i));
                LogUtil.c("HiH_HiHealthDataRd", "readHiHealthData() SEQUENCE  ReadSequenceDatas, sequenceType is ", Integer.valueOf(i), ", size is ", Integer.valueOf(d2.size()));
                a(list, hiDataReadOption, iDataReadResultListener, d2, context);
                return;
            }
            iDataReadResultListener.onResult(null, i, 1);
        }
    }

    private static void a(List<Integer> list, HiDataReadOption hiDataReadOption, IDataReadResultListener iDataReadResultListener, List<HiHealthData> list2, Context context) throws RemoteException {
        if (HiCommonUtil.d(list2)) {
            LogUtil.h("HiH_HiHealthDataRd", "getSequenceDetailData() sequenceHealthDatas is null or empty");
            iDataReadResultListener.onResult(null, OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, 1);
            return;
        }
        hiDataReadOption.setStartTime(list2.get(0).getStartTime());
        hiDataReadOption.setEndTime(list2.get(0).getEndTime());
        hiDataReadOption.setReadType(0);
        hiDataReadOption.setType(new int[]{OnRegisterSkinAttrsListener.REGISTER_BY_SCAN});
        b(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, list, hiDataReadOption, iDataReadResultListener, context);
    }

    public static List<HiHealthData> c(List<HiHealthData> list, int i) {
        ArrayList arrayList = new ArrayList(10);
        if (HiCommonUtil.d(list)) {
            LogUtil.h("HiH_HiHealthDataRd", "getSequenceDatasByReadType() sequenceMetaDatas is null or empty");
            return arrayList;
        }
        d(list, i, arrayList);
        return arrayList;
    }

    private static void d(List<HiHealthData> list, int i, List<HiHealthData> list2) {
        for (HiHealthData hiHealthData : list) {
            int sportType = ((HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class)).getSportType();
            if (i != 30021 && i != 30022) {
                switch (i) {
                    case 30005:
                    case 30009:
                        if (257 != sportType && 281 != sportType && 282 != sportType) {
                            break;
                        } else {
                            list2.add(hiHealthData);
                            break;
                        }
                        break;
                    case 30006:
                    case 30010:
                        if (258 != sportType && 264 != sportType && 280 != sportType) {
                            break;
                        } else {
                            list2.add(hiHealthData);
                            break;
                        }
                        break;
                    case 30007:
                    case 30011:
                        if (259 == sportType) {
                            list2.add(hiHealthData);
                            break;
                        } else {
                            break;
                        }
                    case 30008:
                    case 30012:
                        if (c(sportType)) {
                            list2.add(hiHealthData);
                            break;
                        } else {
                            break;
                        }
                    default:
                        d(list2, hiHealthData, i, sportType);
                        break;
                }
            } else if (sportType == 262 || sportType == 266) {
                list2.add(hiHealthData);
            }
        }
    }

    private static void d(List<HiHealthData> list, HiHealthData hiHealthData, int i, int i2) {
        switch (i) {
            case 30023:
            case 30024:
                if (i2 == 271) {
                    list.add(hiHealthData);
                    break;
                }
                break;
            case 30025:
            case 30026:
                if (i2 == 217 || i2 == 218 || i2 == 219) {
                    list.add(hiHealthData);
                    break;
                }
                break;
            case 30027:
            case 30028:
                if (i2 == 220) {
                    list.add(hiHealthData);
                    break;
                }
                break;
            default:
                list.add(hiHealthData);
                break;
        }
    }

    private static boolean c(int i) {
        return !Arrays.asList(257, Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_CROSS_TRAINER), 282, 258, 264, 259, Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE), 262, Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM), Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_BASKETBALL)).contains(Integer.valueOf(i));
    }

    public static List<HiHealthData> b(List<HiHealthData> list, Context context) {
        ArrayList arrayList = new ArrayList(10);
        if (list != null) {
            for (HiHealthData hiHealthData : list) {
                int clientId = hiHealthData.getClientId();
                ikk a2 = ikk.a(context);
                hiHealthData.putInt("trackdata_deviceType", a2.b(clientId));
                HiDeviceInfo e = a2.e(clientId);
                String c = a2.c(e);
                String b = a2.b(e);
                String d = a2.d(e);
                String a3 = a2.a(e);
                hiHealthData.putString(PluginPayAdapter.KEY_DEVICE_INFO_MODEL, b);
                hiHealthData.putString(PluginPayAdapter.KEY_DEVICE_INFO_NAME, c);
                hiHealthData.putString("device_uniquecode", d);
                hiHealthData.putString("device_prodid", a3);
                arrayList.add(hiHealthData);
            }
        }
        return arrayList;
    }
}
