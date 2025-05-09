package com.huawei.hihealthservice.store.merge;

import android.content.Context;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import defpackage.iic;
import defpackage.iis;
import defpackage.iix;
import defpackage.ijf;
import defpackage.ivx;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class HiDataCoreSessionMerge implements HiMergeCommon {

    /* renamed from: a, reason: collision with root package name */
    private iix f4202a;
    private Context e;
    private List<Long> d = new ArrayList(10);
    private Map<Long, Integer> c = new HashMap(16);

    public HiDataCoreSessionMerge(Context context) {
        this.e = context;
        this.f4202a = iix.a(context);
    }

    @Override // com.huawei.hihealthservice.store.merge.HiMergeCommon
    public boolean merge(HiHealthData hiHealthData, int i, List<Integer> list) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(i));
        List<HiHealthData> a2 = this.f4202a.a(hiHealthData.getStartTime(), hiHealthData.getEndTime(), arrayList);
        if (HiCommonUtil.d(a2) && hiHealthData.getSyncStatus() != 2) {
            if (hiHealthData.getMergedStatus() == 999) {
                this.f4202a.e(hiHealthData, i, 0);
            } else {
                this.f4202a.e(hiHealthData, i, hiHealthData.getMergedStatus());
            }
        } else if (hiHealthData.getSyncStatus() == 1 && (hiHealthData.getType() == 22105 || hiHealthData.getType() == 22106 || hiHealthData.getType() == 22107)) {
            if (iic.c(hiHealthData.getType(), a2.get(0).getType()) > 0) {
                if (hiHealthData.getMergedStatus() == 999) {
                    this.f4202a.b(hiHealthData, i, 0);
                } else {
                    this.f4202a.b(hiHealthData, i, hiHealthData.getMergedStatus());
                }
            }
        } else if (hiHealthData.getSyncStatus() != 2) {
            if (hiHealthData.getMergedStatus() == 999) {
                this.f4202a.b(hiHealthData, i, 0);
            } else {
                this.f4202a.b(hiHealthData, i, hiHealthData.getMergedStatus());
            }
        } else if (hiHealthData.getLastDataFlag()) {
            ReleaseLogUtil.e("HiH_HiDtaCoreSesnMrg", "delete hihealthdata sesseionData");
        }
        long l = HiDateUtil.l(hiHealthData.getStartTime());
        if (!this.d.contains(Long.valueOf(l))) {
            this.d.add(Long.valueOf(l));
            this.c.put(Long.valueOf(l), Integer.valueOf(hiHealthData.getMergedStatus()));
        }
        if (!hiHealthData.getLastDataFlag()) {
            return true;
        }
        for (Long l2 : this.d) {
            if (!b(l2, list, this.c.get(l2).intValue())) {
                ReleaseLogUtil.e("HiH_HiDtaCoreSesnMrg", "mergeCoreSleepData false");
            }
        }
        ReleaseLogUtil.e("HiH_HiDtaCoreSesnMrg", "HiDataCoreSessionMerge merge end");
        this.d.clear();
        return true;
    }

    private boolean b(Long l, List<Integer> list, int i) {
        ReleaseLogUtil.e("HiH_HiDtaCoreSesnMrg", "mergeCoreSleepData startTime ", l);
        List<HiHealthData> d = this.f4202a.d(e(l), list, 22100, 22199);
        if (HiCommonUtil.d(d)) {
            ReleaseLogUtil.e("HiH_HiDtaCoreSesnMrg", "oldCoreSleepDatas isNullEmpty");
            return false;
        }
        List<HiHealthData> b = b(d);
        d.removeAll(b);
        for (HiHealthData hiHealthData : d) {
            if (hiHealthData.getInt("merged") == 0) {
                if (i != 999) {
                    hiHealthData.setSyncStatus(0);
                }
                if (!this.f4202a.a(hiHealthData, hiHealthData.getClientId(), 1)) {
                    return false;
                }
            }
        }
        HashMap hashMap = new HashMap(16);
        for (HiHealthData hiHealthData2 : b) {
            if (hashMap.containsKey(Long.valueOf(hiHealthData2.getStartTime()))) {
                List list2 = (List) hashMap.get(Long.valueOf(hiHealthData2.getStartTime()));
                if (list2 != null) {
                    list2.add(hiHealthData2);
                    hashMap.put(Long.valueOf(hiHealthData2.getStartTime()), list2);
                }
            } else {
                ArrayList arrayList = new ArrayList(10);
                arrayList.add(hiHealthData2);
                hashMap.put(Long.valueOf(hiHealthData2.getStartTime()), arrayList);
            }
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            Collections.sort((List) entry.getValue(), new ivx.d());
            HiHealthData hiHealthData3 = (HiHealthData) ((List) entry.getValue()).get(0);
            if (hiHealthData3.getInt("merged") == 1) {
                if (i != 999) {
                    hiHealthData3.setSyncStatus(0);
                }
                this.f4202a.a(hiHealthData3, hiHealthData3.getClientId(), 0);
            }
            for (int i2 = 1; i2 < ((List) entry.getValue()).size(); i2++) {
                HiHealthData hiHealthData4 = (HiHealthData) ((List) entry.getValue()).get(i2);
                if (hiHealthData4.getInt("merged") == 0) {
                    if (i != 999) {
                        hiHealthData4.setSyncStatus(0);
                    }
                    if (!this.f4202a.a(hiHealthData4, hiHealthData4.getClientId(), 1)) {
                        return false;
                    }
                }
            }
        }
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            iis.d().l(it.next().intValue());
        }
        return true;
    }

    private HiDataReadOption e(Long l) {
        long l2 = HiDateUtil.l(l.longValue());
        long o = HiDateUtil.o(l.longValue());
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(l2);
        hiDataReadOption.setEndTime(o);
        return hiDataReadOption;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private List<HiHealthData> b(List<HiHealthData> list) {
        int c;
        char c2;
        List<HiHealthData> arrayList = new ArrayList<>(10);
        List<HiHealthData> arrayList2 = new ArrayList<>(10);
        List<HiHealthData> arrayList3 = new ArrayList<>(10);
        List<HiHealthData> arrayList4 = new ArrayList<>(10);
        List<HiHealthData> arrayList5 = new ArrayList<>(10);
        List<HiHealthData> arrayList6 = new ArrayList<>(10);
        List<HiHealthData> arrayList7 = new ArrayList<>(10);
        List<HiHealthData> arrayList8 = new ArrayList<>(10);
        ArrayList arrayList9 = new ArrayList(10);
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null && (c = c(hiHealthData, 0)) != 0) {
                List<ProductMapInfo> b = ProductMap.b("deviceId", String.valueOf(c));
                if (HiCommonUtil.d(b)) {
                    ReleaseLogUtil.e("HiH_HiDtaCoreSesnMrg", "productInfoList is null");
                    e(arrayList7, arrayList8, hiHealthData);
                } else {
                    Iterator<ProductMapInfo> it = b.iterator();
                    if (it.hasNext()) {
                        ProductMapInfo next = it.next();
                        String e = next != null ? next.e() : null;
                        if (e == null) {
                            ReleaseLogUtil.e("HiH_HiDtaCoreSesnMrg", "deviceCategory is null");
                            e(arrayList7, arrayList8, hiHealthData);
                        } else {
                            e.hashCode();
                            switch (e.hashCode()) {
                                case 47665:
                                    if (e.equals("001")) {
                                        c2 = 0;
                                        break;
                                    }
                                    c2 = 65535;
                                    break;
                                case 47685:
                                    if (e.equals("00E")) {
                                        c2 = 1;
                                        break;
                                    }
                                    c2 = 65535;
                                    break;
                                case 47696:
                                    if (e.equals("011")) {
                                        c2 = 2;
                                        break;
                                    }
                                    c2 = 65535;
                                    break;
                                case 47823:
                                    if (e.equals("054")) {
                                        c2 = 3;
                                        break;
                                    }
                                    c2 = 65535;
                                    break;
                                case 47870:
                                    if (e.equals("06D")) {
                                        c2 = 4;
                                        break;
                                    }
                                    c2 = 65535;
                                    break;
                                case 47871:
                                    if (e.equals("06E")) {
                                        c2 = 5;
                                        break;
                                    }
                                    c2 = 65535;
                                    break;
                                default:
                                    c2 = 65535;
                                    break;
                            }
                            if (c2 == 0) {
                                arrayList9.add(hiHealthData);
                            } else if (c2 == 1 || c2 == 2) {
                                e(arrayList3, arrayList4, hiHealthData);
                            } else if (c2 == 3) {
                                e(arrayList5, arrayList6, hiHealthData);
                            } else if (c2 == 4 || c2 == 5) {
                                e(arrayList, arrayList2, hiHealthData);
                            } else {
                                ReleaseLogUtil.e("HiH_HiDtaCoreSesnMrg", "deviceCategory is error ", e);
                                e(arrayList7, arrayList8, hiHealthData);
                            }
                        }
                    }
                }
            }
        }
        ReleaseLogUtil.e("HiH_HiDtaCoreSesnMrg", "Date is ", Integer.valueOf(HiDateUtil.c(list.get(0).getStartTime())), ", allCoreSleep ", Integer.valueOf(list.size()), ", wearDevCore ", Integer.valueOf(arrayList.size()), ", wearDevNoon ", Integer.valueOf(arrayList2.size()), ", phoneCore ", Integer.valueOf(arrayList3.size()), ", phoneNoon ", Integer.valueOf(arrayList4.size()), ", pillowCore ", Integer.valueOf(arrayList5.size()), ", pillowNoon ", Integer.valueOf(arrayList6.size()), ", otherCore ", Integer.valueOf(arrayList7.size()), ", otherNoon ", Integer.valueOf(arrayList8.size()), ", manualSleep ", Integer.valueOf(arrayList9.size()));
        if (!HiCommonUtil.d(arrayList)) {
            arrayList.addAll(arrayList2);
            return arrayList;
        }
        if (!HiCommonUtil.d(arrayList5)) {
            arrayList5.addAll(arrayList6);
            return arrayList5;
        }
        if (!HiCommonUtil.d(arrayList3)) {
            arrayList3.addAll(arrayList4);
            return arrayList3;
        }
        if (!HiCommonUtil.d(arrayList9)) {
            return arrayList9;
        }
        if (!HiCommonUtil.d(arrayList2)) {
            return arrayList2;
        }
        if (!HiCommonUtil.d(arrayList6)) {
            return arrayList6;
        }
        if (!HiCommonUtil.d(arrayList4)) {
            return arrayList4;
        }
        if (HiCommonUtil.d(arrayList7)) {
            return !HiCommonUtil.d(arrayList8) ? arrayList8 : new ArrayList(0);
        }
        arrayList7.addAll(arrayList8);
        return arrayList7;
    }

    private void e(List<HiHealthData> list, List<HiHealthData> list2, HiHealthData hiHealthData) {
        if (hiHealthData.getType() == 22105) {
            list2.add(hiHealthData);
        } else {
            list.add(hiHealthData);
        }
    }

    private int c(HiHealthData hiHealthData, int i) {
        HiDeviceInfo a2 = ijf.d(this.e).a(iis.d().a(hiHealthData.getClientId()));
        return a2 == null ? i : a2.getDeviceType();
    }
}
