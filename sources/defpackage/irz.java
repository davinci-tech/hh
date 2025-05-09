package defpackage;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiConfigDataType;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class irz {

    /* renamed from: a, reason: collision with root package name */
    private iit f13565a;
    private iiu c;

    private irz() {
        this.f13565a = iit.d();
        this.c = iiu.c();
    }

    static class e {
        private static final irz d = new irz();
    }

    public static irz d() {
        return e.d;
    }

    public boolean c(HiHealthData hiHealthData, int i, List<Integer> list) {
        if (hiHealthData != null && list != null) {
            int type = hiHealthData.getType();
            if (!HiConfigDataType.i(type)) {
                LogUtil.c("HiH_HiHealthSaveData", "not support the type=", Integer.valueOf(type));
                return false;
            }
            Object[] c2 = HiConfigDataType.c(HiConfigDataType.e(type).intValue());
            Object obj = c2[1];
            Integer num = obj instanceof Integer ? (Integer) obj : null;
            Object obj2 = c2[2];
            Integer num2 = obj2 instanceof Integer ? (Integer) obj2 : null;
            if (num == null || num2 == null) {
                LogUtil.h("HiH_HiHealthSaveData", "not set tableName or merge policy");
            } else {
                return b(hiHealthData, i, list, iwj.a(num.intValue()), num2.intValue());
            }
        }
        return false;
    }

    private boolean b(HiHealthData hiHealthData, int i, List<Integer> list, String str, int i2) {
        if (i2 == 1 || i2 == 2) {
            return c(hiHealthData, i, list, str, i2);
        }
        if (i2 == 3) {
            return true;
        }
        if (i2 != 4) {
            return false;
        }
        return a(hiHealthData, i, str);
    }

    private boolean a(HiHealthData hiHealthData, int i, String str) {
        return this.f13565a.b(str, hiHealthData, i, 0);
    }

    private boolean c(HiHealthData hiHealthData, int i, List<Integer> list, String str, int i2) {
        List<HiHealthData> a2 = this.f13565a.a(str, hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), list);
        if (a2 == null || a2.isEmpty()) {
            return this.f13565a.d(str, hiHealthData, i, 0) > 0;
        }
        Iterator<HiHealthData> it = a2.iterator();
        while (true) {
            if (it.hasNext()) {
                HiHealthData next = it.next();
                if (next.getClientId() == i) {
                    next.setValue(hiHealthData.getValue());
                    next.putInt("merged", 0);
                    next.setSyncStatus(hiHealthData.getSyncStatus());
                    break;
                }
            } else {
                hiHealthData.setClientId(i);
                hiHealthData.putInt("merged", 0);
                a2.add(hiHealthData);
                break;
            }
        }
        if (1 == i2) {
            e(a2);
        } else {
            a(a2);
        }
        HiHealthData hiHealthData2 = a2.get(0);
        boolean b2 = this.f13565a.b(str, hiHealthData2, hiHealthData2.getClientId(), 0);
        for (int i3 = 1; i3 < a2.size(); i3++) {
            HiHealthData hiHealthData3 = a2.get(i3);
            if (hiHealthData.getInt("merged") == 0) {
                boolean b3 = this.f13565a.b(str, hiHealthData3, hiHealthData3.getClientId(), 1);
                LogUtil.c("HiH_HiHealthSaveData", "pointDataMerge() insertOrUpdatePointData unmerge change = ", Boolean.valueOf(b3));
                if (!b3) {
                    b2 = false;
                }
            }
        }
        return b2;
    }

    public boolean b(HiHealthData hiHealthData, int i, int i2) {
        if (hiHealthData == null || i <= 0) {
            LogUtil.h("HiH_HiHealthSaveData", "insertConfigStatData() statClient <= 0");
            return false;
        }
        int type = hiHealthData.getType();
        if (!HiConfigDataType.f(type)) {
            LogUtil.h("HiH_HiHealthSaveData", "not support stattype=", Integer.valueOf(type));
            return false;
        }
        Integer d = HiConfigDataType.d(type);
        Integer num = HiConfigDataType.d(d.intValue(), 7) instanceof Integer ? (Integer) HiConfigDataType.d(d.intValue(), 7) : null;
        if (num == null) {
            return false;
        }
        LogUtil.a("HiH_HiHealthSaveData", "insertConfigStatData() type =", Integer.valueOf(type), ",time = ", Long.valueOf(hiHealthData.getStartTime()), ",statClient = ", Integer.valueOf(i), ",who is ", Integer.valueOf(i2));
        igo igoVar = new igo();
        igoVar.d(hiHealthData.getStartTime());
        igoVar.j(i2);
        igoVar.g(hiHealthData.getSyncStatus());
        igoVar.c(hiHealthData.getInt("hihealth_type"));
        igoVar.d(hiHealthData.getType());
        igoVar.h(hiHealthData.getPointUnit());
        igoVar.a(hiHealthData.getValue());
        igoVar.b(i);
        igoVar.a(hiHealthData.getModifiedTime());
        return this.c.d(iwj.a(num.intValue()), igoVar);
    }

    private void e(List<HiHealthData> list) {
        Collections.sort(list, new c());
    }

    private void a(List<HiHealthData> list) {
        Collections.sort(list, new b());
    }

    static class c implements Comparator<HiHealthData>, Serializable {
        private static final long serialVersionUID = -6004164342177470050L;

        private c() {
        }

        @Override // java.util.Comparator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
            return Double.compare(hiHealthData2.getValue(), hiHealthData.getValue());
        }
    }

    static class b implements Comparator<HiHealthData>, Serializable {
        private static final long serialVersionUID = -5980122579597294901L;

        private b() {
        }

        @Override // java.util.Comparator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
            return Double.compare(hiHealthData.getValue(), hiHealthData2.getValue());
        }
    }

    public boolean d(long j, long j2, int[] iArr, List<Integer> list) {
        int d;
        if (iArr != null && list != null) {
            if (!HiConfigDataType.i(iArr[0])) {
                LogUtil.h("HiH_HiHealthSaveData", "deleteConfigDataTypes not support type");
                return false;
            }
            int intValue = HiConfigDataType.e(iArr[0]).intValue();
            Integer num = HiConfigDataType.d(intValue, 1) instanceof Integer ? (Integer) HiConfigDataType.d(intValue, 1) : null;
            if (num == null) {
                LogUtil.h("HiH_HiHealthSaveData", "statdata not set tablename");
                return false;
            }
            String a2 = iwj.a(num.intValue());
            iit d2 = iit.d();
            List<HiHealthData> a3 = d2.a(a2, j, j2, iArr, list);
            LogUtil.c("HiH_HiHealthSaveData", "deleteConfigDataTypes()");
            if (a3 == null || a3.isEmpty()) {
                LogUtil.h("HiH_HiHealthSaveData", "deleteConfigDataTypes() hiHealthDatas = null");
            } else {
                for (HiHealthData hiHealthData : a3) {
                    if (hiHealthData.getSyncStatus() == 0) {
                        d = d2.b(a2, hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), hiHealthData.getClientId());
                    } else {
                        d = d2.d(a2, hiHealthData.getDataId(), 2, 2);
                    }
                    if (d <= 0) {
                        LogUtil.h("HiH_HiHealthSaveData", "deleteConfigDataTypes() result <= 0");
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
