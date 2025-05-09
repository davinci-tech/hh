package defpackage;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import com.google.gson.JsonParseException;
import com.huawei.basichealthmodel.medicine.MedicineBean;
import com.huawei.basichealthmodel.medicine.MedicineRecord;
import com.huawei.basichealthmodel.medicine.MedicinesRule;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.util.HiJsonUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class axq {
    public static int b(String str, String str2, long j) {
        int hashCode;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        int hashCode2 = str.hashCode();
        if (TextUtils.isEmpty(str2)) {
            hashCode = String.valueOf(hashCode2 + j).hashCode();
        } else {
            hashCode = String.valueOf(hashCode2 + str2.hashCode() + j).hashCode();
        }
        return hashCode & Integer.MAX_VALUE;
    }

    public static String c(int i) {
        return "MEDICINE_MODEL_" + i;
    }

    public static SpannableString kJ_(String str, String str2, int i) {
        SpannableString spannableString = new SpannableString(str);
        if (!TextUtils.isEmpty(str2)) {
            int length = str2.length();
            int i2 = 0;
            while (true) {
                int indexOf = str.indexOf(str2, i2);
                if (indexOf == -1) {
                    break;
                }
                int i3 = indexOf + length;
                spannableString.setSpan(new ForegroundColorSpan(i), indexOf, i3, 17);
                i2 = i3;
            }
        }
        return spannableString;
    }

    public static String e(int i) {
        return i + "_start_time";
    }

    public static String b(int i) {
        return i + "_end_time";
    }

    public static boolean e(MedicinesRule medicinesRule) {
        return medicinesRule != null && medicinesRule.getRuleId() > 0 && medicinesRule.getSource() > 0 && medicinesRule.getStartTime() > 0 && medicinesRule.getEndTime() >= 0 && medicinesRule.getMedicineTime() >= 0 && medicinesRule.getRuleType() >= 0;
    }

    public static MedicineRecord b(MedicinesRule medicinesRule) {
        if (!e(medicinesRule)) {
            ReleaseLogUtil.a("R_HealthLife_MedicineUtil", "initMedicineRecord rule ", medicinesRule);
            return null;
        }
        MedicineRecord medicineRecord = new MedicineRecord();
        String name = medicinesRule.getName();
        medicineRecord.setName(name);
        int medicineTime = medicinesRule.getMedicineTime();
        medicineRecord.setMedicineTime(medicineTime);
        int ruleId = medicinesRule.getRuleId();
        if (TextUtils.isEmpty(name)) {
            medicineRecord.setRecordId(ruleId + medicineTime);
        } else {
            medicineRecord.setRecordId(ruleId);
        }
        int source = medicinesRule.getSource();
        if (source != 1) {
            source = 1;
        }
        medicineRecord.setSource(source);
        medicineRecord.setDrug(medicinesRule.getDrug());
        medicineRecord.setMark(medicinesRule.getMark());
        return medicineRecord;
    }

    public static List<MedicineRecord> c(List<MedicineRecord> list) {
        if (CollectionUtils.d(list)) {
            ReleaseLogUtil.a("R_HealthLife_MedicineUtil", "getMedicineRecordList sourceList ", list);
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<MedicineRecord> it = list.iterator();
        while (it.hasNext()) {
            MedicineRecord a2 = a(it.next());
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    private static MedicineRecord a(MedicineRecord medicineRecord) {
        if (medicineRecord == null) {
            ReleaseLogUtil.a("R_HealthLife_MedicineUtil", "getMedicineRecord medicineRecord is null");
            return null;
        }
        if (TextUtils.isEmpty(medicineRecord.getName())) {
            MedicineBean d = d(medicineRecord.getDrug());
            if (d == null) {
                return null;
            }
            medicineRecord.setDrug(d.getId());
            medicineRecord.setName(d.getName());
            medicineRecord.setMark(d.getRemarks());
            medicineRecord.setSource(d.getSource());
        }
        String name = medicineRecord.getName();
        if (TextUtils.isEmpty(name)) {
            ReleaseLogUtil.a("R_HealthLife_MedicineUtil", "getMedicineRecord name ", name, " medicineRecord ", medicineRecord);
            return null;
        }
        if (medicineRecord.getRecordId() <= 0) {
            medicineRecord.setRecordId(b(name, medicineRecord.getMark(), System.currentTimeMillis()));
        }
        return medicineRecord;
    }

    private static MedicineBean d(int i) {
        String c = c(i);
        HiUserPreference c2 = azi.c(c);
        if (c2 == null) {
            ReleaseLogUtil.a("R_HealthLife_MedicineUtil", "getMedicineBean userPreference is null key ", c);
            return null;
        }
        try {
            return (MedicineBean) HiJsonUtil.e(c2.getValue(), MedicineBean.class);
        } catch (JsonParseException e) {
            ReleaseLogUtil.c("R_HealthLife_MedicineUtil", "getMedicineBean exception ", ExceptionUtils.d(e));
            return null;
        }
    }
}
