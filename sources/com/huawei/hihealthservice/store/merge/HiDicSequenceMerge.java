package com.huawei.hihealthservice.store.merge;

import android.content.Context;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HealthDataMergePolicy;
import com.huawei.hihealth.dictionary.model.HiHealthDictMergeMode;
import com.huawei.hihealth.dictionary.model.HiHealthDictType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.iis;
import defpackage.iiz;
import defpackage.ijf;
import defpackage.ivx;
import defpackage.iwb;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class HiDicSequenceMerge implements HiMergeCommon {
    private static HashMap<Integer, Integer> b = new HashMap<>();
    private static HashMap<String, Integer> d = new HashMap<>();

    /* renamed from: a, reason: collision with root package name */
    private iiz f4205a;
    private Context c;
    private String e;
    private String f;
    private String g;
    private int h;
    private HiHealthDictManager i;

    public HiDicSequenceMerge(Context context) {
        this.c = context;
        this.f4205a = iiz.a(context);
        this.i = HiHealthDictManager.d(context);
    }

    @Override // com.huawei.hihealthservice.store.merge.HiMergeCommon
    public boolean merge(final HiHealthData hiHealthData, int i, List<Integer> list) {
        List<HiHealthData> b2;
        this.h = hiHealthData.getType();
        HiHealthDictType d2 = this.i.d(hiHealthData.getType());
        if (d2 == null) {
            ReleaseLogUtil.d("Debug_HiDicSequenceMerge", "HiHealthDictField is null, type is ", Integer.valueOf(hiHealthData.getType()));
            return false;
        }
        HiHealthDictMergeMode f = d2.f();
        if (f == null) {
            ReleaseLogUtil.d("Debug_HiDicSequenceMerge", "mergeMode is null, type is ", Integer.valueOf(hiHealthData.getType()));
            return false;
        }
        String c = f.c();
        this.g = c;
        if (HealthDataMergePolicy.SOURCE_PRIORITY.equals(c)) {
            int[] d3 = f.d();
            for (int i2 = 0; i2 < d3.length; i2++) {
                b.put(Integer.valueOf(d3[i2]), Integer.valueOf(d3.length - i2));
            }
            this.f = f.e();
        }
        if (HealthDataMergePolicy.CATEGORY_PRIORITY.equals(this.g)) {
            String[] a2 = f.a();
            for (int i3 = 0; i3 < a2.length; i3++) {
                d.put(a2[i3], Integer.valueOf(a2.length - i3));
            }
            this.f = f.e();
        }
        if ("MAX".equals(this.g) || "MIN".equals(this.g)) {
            this.e = f.b();
        }
        long currentTimeMillis = System.currentTimeMillis();
        hiHealthData.setType(this.h - hiHealthData.getSubType());
        if (hiHealthData.getType() == DicDataTypeUtil.DataType.SLEEP_DETAILS.value()) {
            b2 = this.f4205a.b(hiHealthData.getStartTime(), hiHealthData.getType(), list);
        } else if (hiHealthData.getSubType() == 0) {
            b2 = this.f4205a.c(list, hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType());
        } else {
            b2 = this.f4205a.b(list, hiHealthData.getStartTime(), hiHealthData.getType());
        }
        if (HiCommonUtil.d(b2)) {
            if (hiHealthData.getSyncStatus() == 2) {
                return true;
            }
            long a3 = this.f4205a.a(hiHealthData, i, 0);
            ThreadPoolManager.d().execute(new Runnable() { // from class: isj
                @Override // java.lang.Runnable
                public final void run() {
                    irs.c().process(HiHealthData.this);
                }
            });
            LogUtil.a("Debug_HiDicSequenceMerge", "sequenceDataMerge insertSequenceData changeResult =  ", Long.valueOf(a3));
            hiHealthData.setType(this.h);
            return a3 > 0;
        }
        if (!e(hiHealthData, i, b2)) {
            if (HealthDataMergePolicy.SOURCE_PRIORITY.equals(this.g) || HealthDataMergePolicy.CATEGORY_PRIORITY.equals(this.g)) {
                for (HiHealthData hiHealthData2 : b2) {
                    HiDeviceInfo a4 = ijf.d(this.c).a(iis.d().a(hiHealthData2.getClientId()));
                    if (a4 == null) {
                        ReleaseLogUtil.d("Debug_HiDicSequenceMerge", "deviceInfo is null");
                        return false;
                    }
                    hiHealthData2.setDeviceUuid(a4.getDeviceUniqueCode());
                }
            }
            e(b2);
            boolean b3 = b(i, b2);
            hiHealthData.setType(this.h);
            LogUtil.c("Debug_HiDicSequenceMerge", "sequenceDataMerge use time is ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            return b3;
        }
        hiHealthData.setType(this.h);
        return true;
    }

    private boolean b(int i, List<HiHealthData> list) {
        int i2;
        boolean z;
        int size = list.size();
        boolean z2 = true;
        for (1; i2 < size; i2 + 1) {
            HiHealthData hiHealthData = list.get(i2);
            if (hiHealthData.getClientId() == i) {
                boolean d2 = this.f4205a.d(hiHealthData, i, 1);
                LogUtil.c("Debug_HiDicSequenceMerge", "sequenceDataMerge insertOrUpdateSequenceData merge status change ", Boolean.valueOf(d2));
                i2 = d2 ? i2 + 1 : 1;
                z2 = false;
            } else {
                long b2 = this.f4205a.b(hiHealthData, 1);
                LogUtil.c("Debug_HiDicSequenceMerge", "sequenceDataMerge insertOrUpdateSequenceData merge status change ", Long.valueOf(b2));
                if (b2 > 0) {
                }
                z2 = false;
            }
        }
        HiHealthData hiHealthData2 = list.get(0);
        if (hiHealthData2.getClientId() == i) {
            z = this.f4205a.d(hiHealthData2, i, 0);
            LogUtil.c("Debug_HiDicSequenceMerge", "sequenceDataMerge insertOrUpdateSequenceData maxData merge isSuccess =  ", Boolean.valueOf(z2));
        } else {
            long b3 = this.f4205a.b(hiHealthData2, hiHealthData2.getClientId(), 0);
            LogUtil.c("Debug_HiDicSequenceMerge", "sequenceDataMerge updateSequenceDataMerge maxData changeResult =  ", Long.valueOf(b3));
            z = b3 > 0;
        }
        return z2 && z;
    }

    private boolean e(HiHealthData hiHealthData, int i, List<HiHealthData> list) {
        Iterator<HiHealthData> it = list.iterator();
        while (true) {
            if (it.hasNext()) {
                HiHealthData next = it.next();
                if (iwb.b(next.getClientId(), i) && hiHealthData.getSyncStatus() != 2) {
                    if (hiHealthData.getType() == DicDataTypeUtil.DataType.SLEEP_DETAILS.value()) {
                        if (next.getEndTime() > hiHealthData.getEndTime()) {
                            LogUtil.a("HiH_HiDicSequenceMerge", "SLEEP_DETAILS time small");
                            return true;
                        }
                        if (next.getEndTime() < hiHealthData.getEndTime()) {
                            this.f4205a.d(next.getDataId(), 2, 2);
                        } else {
                            LogUtil.a("HiH_HiDicSequenceMerge", "SLEEP_DETAILS same time");
                        }
                    }
                    if (next.getStartTime() == hiHealthData.getStartTime() && next.getEndTime() == hiHealthData.getEndTime() && next.getInt("merged") == 0) {
                        if (next.getMetaData() != null && next.getMetaData().equals(hiHealthData.getMetaData()) && next.getSimpleData() != null && next.getSimpleData().equals(hiHealthData.getSimpleData())) {
                            LogUtil.b("HiH_HiDicSequenceMerge", "same sequence data, does not merge!");
                            return true;
                        }
                        try {
                            if (hiHealthData.getSubType() != 0) {
                                if (b(hiHealthData, next)) {
                                    return true;
                                }
                            }
                        } catch (JSONException e) {
                            ReleaseLogUtil.d("HiH_HiDicSequenceMerge", "mergeSequenceData JSONException!, e is ", e.getMessage());
                            return false;
                        }
                    }
                    if (hiHealthData.getSyncStatus() != 1 || next.getInt("merged") != 2) {
                        next.putInt("merged", 0);
                    }
                    next.setSequenceData(hiHealthData.getSequenceData());
                    next.setMetaData(hiHealthData.getMetaData());
                    next.setSyncStatus(hiHealthData.getSyncStatus());
                    next.setSimpleData(hiHealthData.getSimpleData());
                    next.setModifiedTime(System.currentTimeMillis());
                    next.setEndTime(hiHealthData.getEndTime());
                }
            } else {
                if (hiHealthData.getSyncStatus() == 2) {
                    return false;
                }
                LogUtil.a("HiH_HiDicSequenceMerge", "mergeSequenceData is new data");
                hiHealthData.setClientId(i);
                hiHealthData.putInt("merged", 0);
                hiHealthData.setModifiedTime(System.currentTimeMillis());
                list.add(hiHealthData);
            }
        }
        return false;
    }

    private boolean b(HiHealthData hiHealthData, HiHealthData hiHealthData2) throws JSONException {
        if (hiHealthData2.getType() == 30001) {
            JSONObject jSONObject = new JSONObject(hiHealthData2.getMetaData());
            JSONObject jSONObject2 = new JSONObject(hiHealthData.getMetaData());
            JSONObject jSONObject3 = new JSONObject(hiHealthData2.getMetaData());
            int d2 = CommonUtil.d(jSONObject, "mDuplicated");
            int d3 = CommonUtil.d(jSONObject2, "mDuplicated");
            if (d2 == d3) {
                return false;
            }
            jSONObject.put("mDuplicated", d3);
            if (HiJsonUtil.e(jSONObject).equals(HiJsonUtil.e(jSONObject2)) && CommonUtil.d(jSONObject3, "mDuplicated") == 1) {
                LogUtil.b("HiH_HiDicSequenceMerge", "meta data only duplicate diff, does not merge!");
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void e(List<HiHealthData> list) {
        char c;
        Comparator bVar;
        String str = this.g;
        str.hashCode();
        switch (str.hashCode()) {
            case -758469691:
                if (str.equals(HealthDataMergePolicy.CATEGORY_PRIORITY)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 76100:
                if (str.equals("MAX")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 76338:
                if (str.equals("MIN")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 77184:
                if (str.equals(HealthDataMergePolicy.NEW)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 78343:
                if (str.equals(HealthDataMergePolicy.OLD)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1010216264:
                if (str.equals(HealthDataMergePolicy.SOURCE_PRIORITY)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            bVar = new ivx.b(d, this.f);
        } else if (c == 1) {
            bVar = new ivx.h(this.e);
        } else if (c == 2) {
            bVar = new ivx.m(this.e);
        } else if (c == 3) {
            bVar = new ivx.g();
        } else {
            if (c == 4) {
                return;
            }
            if (c == 5) {
                bVar = new ivx.c(b, this.f);
            } else {
                LogUtil.h("Debug_HiDicSequenceMerge", "This mergePolicy is not supported! mergePolicy is ", this.g);
                return;
            }
        }
        Collections.sort(list, bVar);
    }
}
