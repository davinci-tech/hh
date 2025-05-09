package com.huawei.hihealth.util;

import android.content.Context;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.IAggregateListener;
import com.huawei.hihealth.IAggregateListenerEx;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.data.type.HiHealthDataType;
import health.compact.a.HiCommonUtil;
import health.compact.a.util.LogUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HiDivideUtil {
    private HiDivideUtil() {
    }

    public static void d(List list, IAggregateListener iAggregateListener) throws RemoteException {
        if (HiCommonUtil.d(list)) {
            if (iAggregateListener != null) {
                iAggregateListener.onResult(null, 0, 1);
                return;
            }
            return;
        }
        int size = list.size();
        int e = e(size, list);
        LogUtil.d("HiH_HiDivideUtil", "dataList size : ", Integer.valueOf(size), ", divideList part : ", Integer.valueOf(e));
        int i = 0;
        while (i < size) {
            int i2 = i + e;
            if (i2 >= size) {
                if (iAggregateListener != null) {
                    iAggregateListener.onResult(list.subList(i, size), size, 1);
                }
            } else if (iAggregateListener != null) {
                iAggregateListener.onResult(list.subList(i, i2), i2, 0);
            }
            i = i2;
        }
    }

    public static void d(int i, List list, IAggregateListenerEx iAggregateListenerEx) throws RemoteException {
        if (HiCommonUtil.d(list)) {
            if (iAggregateListenerEx != null) {
                iAggregateListenerEx.onResult(null, i, 1);
                return;
            }
            return;
        }
        int size = list.size();
        int e = e(size, list);
        LogUtil.d("HiH_HiDivideUtil", "dataType = ", Integer.valueOf(i), ", dataList size = ", Integer.valueOf(size), ", divideList part = ", Integer.valueOf(e));
        int i2 = 0;
        while (i2 < size) {
            int i3 = i2 + e;
            if (i3 >= size) {
                if (iAggregateListenerEx != null) {
                    iAggregateListenerEx.onResult(list.subList(i2, size), i, 1);
                }
            } else if (iAggregateListenerEx != null) {
                iAggregateListenerEx.onResult(list.subList(i2, i3), i, 0);
            }
            i2 = i3;
        }
    }

    private static int e(int i, List list) {
        if (i <= 0) {
            return 400;
        }
        if (!(list.get(0) instanceof HiHealthData)) {
            LogUtil.e("HiH_HiDivideUtil", "getDivideLength dataList error");
            return 400;
        }
        int contentLength = ((HiHealthData) list.get(0)).getContentLength();
        if (contentLength <= 10) {
            return 400;
        }
        int i2 = 400 - (((contentLength - 10) / 3) * 100);
        if (i2 <= 0) {
            return 100;
        }
        return i2;
    }

    public static void d(int i, List list, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        if (!HiCommonUtil.d(list)) {
            c(iDataReadResultListener, list, i, 1);
        } else if (iDataReadResultListener != null) {
            iDataReadResultListener.onResult(new ArrayList(0), i, 1);
        }
    }

    private static void c(IDataReadResultListener iDataReadResultListener, List list, int i, int i2) throws RemoteException {
        if (iDataReadResultListener == null || list.isEmpty()) {
            return;
        }
        if (list.size() == 1) {
            LogUtil.d("HiH_HiDivideUtil", "onResultSplitIfOverLimit splitInfo: dataType=", Integer.valueOf(i), ", listSize=", Integer.valueOf(list.size()), ", parcelSize=", null, ", endFlag=", Integer.valueOf(i2));
            iDataReadResultListener.onResult(list, i, i2);
            return;
        }
        int e = e(list);
        if (e > 204800 && list.size() > 1) {
            int size = list.size() / 2;
            c(iDataReadResultListener, list.subList(0, size), i, 0);
            c(iDataReadResultListener, list.subList(size, list.size()), i, i2);
        } else {
            LogUtil.d("HiH_HiDivideUtil", "onResultSplitIfOverLimit splitInfo: dataType=", Integer.valueOf(i), ", listSize=", Integer.valueOf(list.size()), ", parcelSize=", Integer.valueOf(e), ", endFlag=", Integer.valueOf(i2));
            iDataReadResultListener.onResult(list, i, i2);
        }
    }

    private static int e(List list) {
        Parcel obtain = Parcel.obtain();
        obtain.writeList(list);
        int dataSize = obtain.dataSize();
        obtain.recycle();
        return dataSize;
    }

    public static void e(int i, List<HiHealthData> list, IDataReadResultListener iDataReadResultListener) throws RemoteException {
        if (HiCommonUtil.d(list)) {
            if (iDataReadResultListener != null) {
                iDataReadResultListener.onResult(new ArrayList(10), i, 1);
                return;
            }
            return;
        }
        HiHealthData hiHealthData = list.get(0);
        String sequenceData = hiHealthData.getSequenceData();
        int length = TextUtils.isEmpty(sequenceData) ? 0 : sequenceData.length();
        String metaData = hiHealthData.getMetaData();
        LogUtil.d("HiH_HiDivideUtil", "dataType = ", Integer.valueOf(i), ", dataList size = ", Integer.valueOf(list.size()), ", divideSequenceData length = ", Integer.valueOf(length), ", metaData length = ", Integer.valueOf(TextUtils.isEmpty(metaData) ? 0 : metaData.length()), ", divideList part = ", 131072);
        if (length <= 0) {
            if (iDataReadResultListener != null) {
                iDataReadResultListener.onResult(new ArrayList(10), i, 1);
                return;
            }
            return;
        }
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 131072;
            if (i3 >= length) {
                hiHealthData.setSequenceData(sequenceData.substring(i2, length));
                hiHealthData.setMetaData(metaData);
                ArrayList arrayList = new ArrayList();
                arrayList.add(hiHealthData);
                if (iDataReadResultListener != null) {
                    iDataReadResultListener.onResult(arrayList, i, 1);
                }
            } else {
                hiHealthData.setSequenceData(sequenceData.substring(i2, i3));
                hiHealthData.setMetaData(null);
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(hiHealthData);
                if (iDataReadResultListener != null) {
                    iDataReadResultListener.onResult(arrayList2, i, 0);
                }
            }
            i2 = i3;
        }
    }

    public static boolean c(List<HiHealthData> list, int i, List<HiHealthData> list2) {
        if (!HiCommonUtil.d(list)) {
            list2.addAll(list);
        }
        return i == 2;
    }

    public static boolean bwN_(List<HiHealthData> list, int i, int i2, List<HiHealthData> list2, SparseArray<List<HiHealthData>> sparseArray) {
        if (!HiCommonUtil.d(list)) {
            list2.addAll(list);
        }
        if (i2 != 1) {
            return i2 == 2;
        }
        if (HiCommonUtil.d(list2)) {
            return false;
        }
        bwM_(sparseArray, list2, i);
        return false;
    }

    public static boolean bwO_(Context context, List<HiHealthData> list, int[] iArr, List<HiHealthData> list2, SparseArray<List<HiHealthData>> sparseArray) {
        if (iArr == null || iArr.length != 2) {
            return false;
        }
        int i = iArr[0];
        int i2 = iArr[1];
        if (HiHealthDataType.e(i) == HiHealthDataType.Category.SEQUENCE) {
            if (HiCommonUtil.d(list)) {
                return i2 == 2;
            }
            if (list.get(0).getSequenceData() != null) {
                bwP_(context, list, iArr, list2, sparseArray);
            } else {
                bwN_(list, i, i2, list2, sparseArray);
            }
            return false;
        }
        return bwN_(list, i, i2, list2, sparseArray);
    }

    private static void bwP_(Context context, List<HiHealthData> list, int[] iArr, List<HiHealthData> list2, SparseArray<List<HiHealthData>> sparseArray) {
        HiHealthData hiHealthData;
        String str;
        if (iArr == null || iArr.length != 2) {
            return;
        }
        int i = iArr[0];
        int i2 = iArr[1];
        if (HiCommonUtil.d(list2)) {
            hiHealthData = null;
            str = null;
        } else {
            hiHealthData = list2.get(0);
            str = hiHealthData.getSequenceData();
        }
        if (!HiCommonUtil.d(list)) {
            if (hiHealthData == null) {
                hiHealthData = list.get(0);
                list2.add(hiHealthData);
            } else {
                hiHealthData.setSequenceData(str + list.get(0).getSequenceData());
            }
        }
        if (hiHealthData == null) {
            LogUtil.c("HiH_HiDivideUtil", "packageSequenceDivideData data is null");
            return;
        }
        if (i2 != 1 || HiCommonUtil.d(list2)) {
            return;
        }
        try {
            c(context, i, HiZipUtil.a(hiHealthData.getSequenceData()), hiHealthData);
            hiHealthData.setMetaData(list.get(0).getMetaData());
            bwM_(sparseArray, list2, i);
        } catch (IOException e) {
            LogUtil.e("HiH_HiDivideUtil", "packageSequenceDivideData uncompress e = ", e.getMessage());
        }
    }

    private static void c(Context context, int i, String str, HiHealthData hiHealthData) {
        if (i == 30003) {
            String str2 = "HiTrack_" + hiHealthData.getStartTime() + hiHealthData.getEndTime() + System.currentTimeMillis();
            if (!HiFileUtil.c(context, str, str2)) {
                str2 = null;
            }
            hiHealthData.setSequenceData(null);
            hiHealthData.setSequenceFileUrl(str2);
            return;
        }
        hiHealthData.setSequenceData(str);
    }

    public static List<HiDataInsertOption> e(HiDataInsertOption hiDataInsertOption) {
        List<HiHealthData> datas = hiDataInsertOption.getDatas();
        ArrayList arrayList = new ArrayList();
        if (HiCommonUtil.d(datas)) {
            arrayList.add(hiDataInsertOption);
            return arrayList;
        }
        HiHealthData hiHealthData = datas.get(0);
        if (HiHealthDataType.e(hiHealthData.getType()) == HiHealthDataType.Category.SEQUENCE) {
            if (hiHealthData.getSequenceData() != null) {
                d(datas, arrayList, hiDataInsertOption.getWriteStatType(), hiDataInsertOption.getPackageName());
            } else {
                c(datas, arrayList, hiDataInsertOption.getWriteStatType(), (String) null);
            }
            return arrayList;
        }
        int type = hiHealthData.getType();
        if (type != 2106) {
            switch (type) {
                case 2008:
                case 2009:
                case 2010:
                case 2011:
                case 2012:
                case 2013:
                case 2014:
                case 2015:
                    break;
                default:
                    c(datas, arrayList, hiDataInsertOption.getWriteStatType(), (String) null);
                    return arrayList;
            }
        }
        c(datas, arrayList, hiDataInsertOption.getWriteStatType(), hiDataInsertOption.getPackageName());
        return arrayList;
    }

    private static void d(List<HiHealthData> list, List<HiDataInsertOption> list2, int i, String str) {
        String str2 = "";
        for (HiHealthData hiHealthData : list) {
            try {
                str2 = HiZipUtil.d(hiHealthData.getSequenceData());
            } catch (IOException e) {
                LogUtil.c("HiH_HiDivideUtil", "divideInsertSequence e = ", e.getMessage());
            }
            int length = str2.length();
            LogUtil.d("HiH_HiDivideUtil", "divideInsertSequence compressed = ", Integer.valueOf(length), ", original size = ", Integer.valueOf(hiHealthData.getSequenceData().length()));
            hiHealthData.putBoolean("is_sequence_zip", true);
            if (length <= 0) {
                d(list2, hiHealthData, i, str);
            } else {
                hiHealthData.setDataId(System.currentTimeMillis());
                int i2 = 0;
                while (i2 < length) {
                    int i3 = 262144 + i2;
                    if (i3 >= length) {
                        hiHealthData.setSequenceData(str2.substring(i2, length));
                        hiHealthData.putBoolean("is_dividing", false);
                        d(list2, hiHealthData, i, str);
                        LogUtil.d("HiH_HiDivideUtil", "divideInsertSequence finally index is ", Integer.valueOf(i2), ",track size is ", Integer.valueOf(hiHealthData.getSequenceData().length()));
                    } else {
                        hiHealthData.setSequenceData(str2.substring(i2, i3));
                        hiHealthData.putBoolean("is_dividing", true);
                        d(list2, hiHealthData, i, str);
                        LogUtil.d("HiH_HiDivideUtil", "divideInsertSequence process index is ", Integer.valueOf(i2), ",track size is ", Integer.valueOf(hiHealthData.getSequenceData().length()));
                    }
                    i2 = i3;
                }
            }
        }
        LogUtil.d("HiH_HiDivideUtil", "divideInsertSequence datas size = ", Integer.valueOf(list.size()), ", options size = ", Integer.valueOf(list2.size()));
    }

    private static void c(List<HiHealthData> list, List<HiDataInsertOption> list2, int i, String str) {
        int size = list.size();
        int i2 = 0;
        while (i2 < size) {
            int i3 = i2 + 500;
            if (i3 >= size) {
                b(list2, list.subList(i2, size), i, str);
            } else {
                b(list2, list.subList(i2, i3), i, str);
            }
            i2 = i3;
        }
        LogUtil.d("HiH_HiDivideUtil", "divideInsertOther datas size = ", Integer.valueOf(size), ", options size = ", Integer.valueOf(list2.size()));
    }

    private static void bwM_(SparseArray<List<HiHealthData>> sparseArray, List<HiHealthData> list, int i) {
        List<HiHealthData> list2 = sparseArray.get(i);
        if (HiCommonUtil.d(list2)) {
            list2 = new ArrayList<>();
        }
        list2.addAll(list);
        sparseArray.append(i, list2);
        list.clear();
    }

    private static void d(List<HiDataInsertOption> list, HiHealthData hiHealthData, int i, String str) {
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setWriteStatType(i);
        hiDataInsertOption.addData(hiHealthData.copyData());
        if (!TextUtils.isEmpty(str)) {
            hiDataInsertOption.setPackageName(str);
        }
        list.add(hiDataInsertOption);
    }

    private static void b(List<HiDataInsertOption> list, List<HiHealthData> list2, int i, String str) {
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setWriteStatType(i);
        hiDataInsertOption.setDatas(list2);
        if (!TextUtils.isEmpty(str)) {
            hiDataInsertOption.setPackageName(str);
        }
        list.add(hiDataInsertOption);
    }
}
