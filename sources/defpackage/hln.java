package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportDataStaticsInfo;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwStaticsRequestData;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.SportGroupData;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.SportTypeData;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class hln {

    /* renamed from: a, reason: collision with root package name */
    private static final List<String> f13237a = new ArrayList(Arrays.asList("Track_100014", "Track_2592", "Track_2572", "Track_2582"));
    private SportGroupData b;
    private SportTypeData d;

    private hln() {
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private static final hln f13238a = new hln();
    }

    public static final hln c(Context context) {
        hln hlnVar = e.f13238a;
        hlnVar.b(context);
        hlnVar.d(context);
        return hlnVar;
    }

    private void b(Context context) {
        if (context != null && this.d == null) {
            try {
                this.d = (SportTypeData) ixu.d(context.getAssets().open("SportTypeConfig.json"), SportTypeData.class);
            } catch (IOException e2) {
                LogUtil.b("TAG_HwSportTypeManager", LogAnonymous.b((Throwable) e2));
            }
            Object[] objArr = new Object[2];
            objArr[0] = "mSportTypeData : ";
            SportTypeData sportTypeData = this.d;
            objArr[1] = sportTypeData == null ? 0 : sportTypeData.getVersion();
            LogUtil.a("TAG_HwSportTypeManager", objArr);
        }
    }

    private void d(Context context) {
        if (context != null && this.b == null) {
            try {
                this.b = (SportGroupData) ixu.d(context.getAssets().open("SportGroupConfig.json"), SportGroupData.class);
            } catch (IOException e2) {
                LogUtil.b("TAG_HwSportTypeManager", LogAnonymous.b((Throwable) e2));
            }
            Object[] objArr = new Object[2];
            objArr[0] = "mSportGroupData : ";
            SportGroupData sportGroupData = this.b;
            objArr[1] = sportGroupData == null ? 0 : sportGroupData.getVersion();
            LogUtil.a("TAG_HwSportTypeManager", objArr);
        }
    }

    public HwSportTypeInfo d(int i) {
        if (d()) {
            return null;
        }
        Iterator<HwSportTypeInfo> it = this.d.getSportTypeInfos().iterator();
        while (it.hasNext()) {
            HwSportTypeInfo next = it.next();
            if (next != null && next.getSportTypeId() == i) {
                return next;
            }
        }
        return null;
    }

    public String[] b(List<HwSportDataStaticsInfo> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            LogUtil.b("TAG_HwSportTypeManager", "sportDataStatics  is  Empty");
            return null;
        }
        Iterator<HwSportDataStaticsInfo> it = list.iterator();
        while (it.hasNext()) {
            for (HwStaticsRequestData hwStaticsRequestData : it.next().getStaticsRequestData()) {
                if (!arrayList.contains(hwStaticsRequestData.getRequestDataString())) {
                    arrayList.add(hwStaticsRequestData.getRequestDataString());
                }
            }
        }
        if (koq.b(arrayList)) {
            LogUtil.b("TAG_HwSportTypeManager", "staticsRequestString  is  Empty");
            return null;
        }
        String[] strArr = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = (String) arrayList.get(i);
        }
        return strArr;
    }

    public int[] a(List<HwSportDataStaticsInfo> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            LogUtil.b("TAG_HwSportTypeManager", "sportDataStatics  is  Empty");
            return null;
        }
        for (HwSportDataStaticsInfo hwSportDataStaticsInfo : list) {
            if (hwSportDataStaticsInfo == null) {
                LogUtil.b("TAG_HwSportTypeManager", "staticsInfo  is  Empty");
            } else {
                for (HwStaticsRequestData hwStaticsRequestData : hwSportDataStaticsInfo.getStaticsRequestData()) {
                    if (!arrayList.contains(Integer.valueOf(hwStaticsRequestData.getRequestDataId()))) {
                        arrayList.add(Integer.valueOf(hwStaticsRequestData.getRequestDataId()));
                    }
                }
            }
        }
        if (koq.b(arrayList)) {
            LogUtil.b("TAG_HwSportTypeManager", "staticsRequstId  is  Empty");
            return null;
        }
        int[] iArr = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            iArr[i] = ((Integer) arrayList.get(i)).intValue();
        }
        return iArr;
    }

    public List<String> c() {
        if (d()) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<HwSportTypeInfo> it = this.d.getSportTypeInfos().iterator();
        while (it.hasNext()) {
            HwSportTypeInfo next = it.next();
            if (next != null && next.getHistoryList() != null && koq.d(next.getHistoryList().getAllRequestString(next.getSportTypeId()), 0)) {
                arrayList.add(next.getHistoryList().getAllRequestString(next.getSportTypeId()).get(0));
                e(next, arrayList);
            }
        }
        if (!koq.c(arrayList)) {
            return null;
        }
        arrayList.add("Track_Count_Sum");
        return arrayList;
    }

    private void e(HwSportTypeInfo hwSportTypeInfo, ArrayList<String> arrayList) {
        if (koq.d(hwSportTypeInfo.getHistoryList().getMonthlyStatisticsData(), 3)) {
            arrayList.add(hwSportTypeInfo.getHistoryList().getAllRequestString(hwSportTypeInfo.getSportTypeId()).get(3));
        }
    }

    public List<HwSportTypeInfo> d(List<String> list) {
        HashMap hashMap = new HashMap();
        if (d() || koq.b(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<HwSportTypeInfo> it = this.d.getSportTypeInfos().iterator();
        while (it.hasNext()) {
            HwSportTypeInfo next = it.next();
            if (next != null && next.getHistoryList() != null && koq.d(next.getHistoryList().getAllRequestString(next.getSportTypeId()), 0)) {
                next.getHistoryList().getMainAndBackupMonthlyType(next, hashMap);
            }
        }
        for (String str : f13237a) {
            if (list.contains(str)) {
                list.remove(str);
                list.add(0, str);
            }
        }
        for (String str2 : list) {
            if (hashMap.containsKey(str2)) {
                arrayList.add((HwSportTypeInfo) hashMap.get(str2));
            }
        }
        return arrayList;
    }

    private boolean d() {
        SportTypeData sportTypeData = this.d;
        return sportTypeData == null || sportTypeData.getSportTypeInfos() == null;
    }

    public SportTypeData b() {
        SportTypeData sportTypeData = new SportTypeData();
        sportTypeData.setVersion("0");
        SportTypeData sportTypeData2 = this.d;
        return sportTypeData2 == null ? sportTypeData : sportTypeData2;
    }

    public SportGroupData a() {
        SportGroupData sportGroupData = new SportGroupData();
        sportGroupData.setVersion("0");
        SportGroupData sportGroupData2 = this.b;
        return sportGroupData2 == null ? sportGroupData : sportGroupData2;
    }

    public List<Integer> e() {
        if (d()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<HwSportTypeInfo> it = this.d.getSportTypeInfos().iterator();
        while (it.hasNext()) {
            HwSportTypeInfo next = it.next();
            if (next != null) {
                arrayList.add(Integer.valueOf(next.getSportTypeId()));
            }
        }
        return arrayList;
    }

    public List<Integer> e(String str) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("TAG_HwSportTypeManager", "getFitnessSportTypeList type is null");
            return arrayList;
        }
        Iterator<HwSportTypeInfo> it = this.d.getSportTypeInfos().iterator();
        while (it.hasNext()) {
            HwSportTypeInfo next = it.next();
            if (next != null && str.equals(next.getGroup())) {
                arrayList.add(Integer.valueOf(next.getSportTypeId()));
            }
        }
        return arrayList;
    }
}
