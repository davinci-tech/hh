package defpackage;

import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.operation.utils.Constants;
import java.util.List;

/* loaded from: classes7.dex */
public class iii {
    public static String d(List<Integer> list, String[] strArr, int i, HiAggregateOption hiAggregateOption, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select sample_point.start_time,sample_point.end_time,sample_point.type_id,sample_point.client_id,max(sample_point.modified_time) as modified_time,");
        int groupUnitType = hiAggregateOption.getGroupUnitType();
        stringBuffer.append(iij.c(groupUnitType, hiAggregateOption.getGroupUnitSize(), "sample_point"));
        String[] constantsKey = hiAggregateOption.getConstantsKey();
        int[] type = hiAggregateOption.getType();
        int length = type.length;
        for (int i2 = 0; i2 < length; i2++) {
            stringBuffer.append(iij.a(hiAggregateOption.getAggregateType(), type[i2], constantsKey[i2]));
            if (i2 < length - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(" from sample_point where sample_point.start_time >=? and sample_point.start_time <=?  and sample_point.merged");
        stringBuffer.append(z ? CommonUtil.EQUAL : " !=? ");
        if (hiAggregateOption.getModifiedEndTime() > 0) {
            iij.e(stringBuffer, "sample_point");
        }
        a(stringBuffer, "sample_point");
        iij.e("sample_point.type_id", type, stringBuffer, strArr, i);
        iij.d("sample_point.client_id", list, stringBuffer, strArr, i + type.length);
        if (groupUnitType == 0) {
            iij.c(stringBuffer, "sample_point", "start_time");
        } else if (groupUnitType == 8) {
            iij.c(stringBuffer, "sample_point", "client_id");
        } else {
            iij.c(stringBuffer, "unit_index");
        }
        iij.e(stringBuffer, "sample_point", "start_time", hiAggregateOption.getSortOrder());
        iij.a(stringBuffer, hiAggregateOption.getAnchor(), hiAggregateOption.getCount());
        return stringBuffer.toString();
    }

    public static String e(List<Integer> list, String[] strArr, int i, HiAggregateOption hiAggregateOption, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select sample_point.start_time,sample_point.end_time,sample_point.type_id,sample_point.client_id,");
        int groupUnitType = hiAggregateOption.getGroupUnitType();
        stringBuffer.append(iij.c(groupUnitType, hiAggregateOption.getGroupUnitSize(), "sample_point"));
        String[] constantsKey = hiAggregateOption.getConstantsKey();
        int[] type = hiAggregateOption.getType();
        int length = type.length;
        for (int i2 = 0; i2 < length; i2++) {
            stringBuffer.append(iij.a(hiAggregateOption.getAggregateType(), type[i2], constantsKey[i2]));
            if (i2 < length - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(" from sample_point where sample_point.start_time >=? and sample_point.start_time <=? ");
        if (z) {
            stringBuffer.append(" and sample_point.merged =? ");
        }
        iij.e("sample_point.type_id", type, stringBuffer, strArr, i);
        iij.d("sample_point.client_id", list, stringBuffer, strArr, i + type.length);
        if (groupUnitType == 0) {
            iij.c(stringBuffer, "sample_point", "start_time");
        } else if (groupUnitType == 8) {
            iij.c(stringBuffer, "sample_point", "client_id");
        } else {
            iij.c(stringBuffer, "unit_index");
        }
        iij.e(stringBuffer, "sample_point", "start_time", hiAggregateOption.getSortOrder());
        iij.a(stringBuffer, hiAggregateOption.getAnchor(), hiAggregateOption.getCount());
        return stringBuffer.toString();
    }

    public static String b(List<Integer> list, String[] strArr, int i, HiAggregateOption hiAggregateOption, boolean z, ifl iflVar) {
        HiDataFilter hiDataFilter;
        if (iflVar == null || TextUtils.isEmpty(iflVar.c())) {
            hiDataFilter = null;
        } else {
            try {
                hiDataFilter = (HiDataFilter) HiJsonUtil.e(iflVar.c(), HiDataFilter.class);
            } catch (JsonSyntaxException unused) {
                throw new JsonSyntaxException("not HiDataFilter param");
            }
        }
        HiDataFilter hiDataFilter2 = hiDataFilter;
        if (hiDataFilter2 != null) {
            return d(list, strArr, i, hiAggregateOption, z, hiDataFilter2);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select sample_point_health.start_time,sample_point_health.end_time,sample_point_health.type_id,sample_point_health.client_id,sample_point_health.metadata,sample_point_health.timeZone,max(sample_point_health.modified_time) as modified_time,");
        int groupUnitType = hiAggregateOption.getGroupUnitType();
        stringBuffer.append(iij.c(groupUnitType, hiAggregateOption.getGroupUnitSize(), "sample_point_health"));
        int[] type = hiAggregateOption.getType();
        String[] constantsKey = hiAggregateOption.getConstantsKey();
        int length = type.length;
        for (int i2 = 0; i2 < length; i2++) {
            stringBuffer.append(iij.e(hiAggregateOption.getAggregateType(), type[i2], constantsKey[i2]));
            if (i2 < length - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(" from sample_point_health where sample_point_health.start_time >=? and sample_point_health.start_time <=?  and sample_point_health.merged");
        stringBuffer.append(z ? CommonUtil.EQUAL : " !=? ");
        if (hiAggregateOption.getModifiedEndTime() > 0) {
            iij.e(stringBuffer, "sample_point_health");
        }
        a(stringBuffer, "sample_point_health");
        iij.e("sample_point_health.type_id", type, stringBuffer, strArr, i);
        iij.d("sample_point_health.client_id", list, stringBuffer, strArr, i + type.length);
        if (groupUnitType != 0) {
            iij.c(stringBuffer, "unit_index");
        } else {
            iij.c(stringBuffer, "sample_point_health", "start_time");
        }
        iij.e(stringBuffer, "sample_point_health", "start_time", hiAggregateOption.getSortOrder());
        iij.a(stringBuffer, hiAggregateOption.getAnchor(), hiAggregateOption.getCount());
        return stringBuffer.toString();
    }

    private static String d(List<Integer> list, String[] strArr, int i, HiAggregateOption hiAggregateOption, boolean z, HiDataFilter hiDataFilter) {
        StringBuffer stringBuffer = new StringBuffer();
        int groupUnitType = hiAggregateOption.getGroupUnitType();
        int[] type = hiAggregateOption.getType();
        String[] constantsKey = hiAggregateOption.getConstantsKey();
        int length = type.length;
        if (hiDataFilter.getFilterType() == 1) {
            stringBuffer.append(" select start_time,end_time,type_id,client_id,metadata,timeZone,max(modified_time) as modified_time,").append(iij.c(groupUnitType, hiAggregateOption.getGroupUnitSize(), (String) null));
            for (int i2 = 0; i2 < length; i2++) {
                stringBuffer.append(iij.e(hiAggregateOption.getAggregateType(), type[i2], constantsKey[i2]));
                if (i2 < length - 1) {
                    stringBuffer.append(",");
                }
            }
            stringBuffer.append(" from (");
        }
        stringBuffer.append(" select sample_point_health.start_time,sample_point_health.end_time,sample_point_health.type_id,sample_point_health.client_id,sample_point_health.metadata,sample_point_health.timeZone,max(sample_point_health.modified_time) as modified_time,");
        if (hiDataFilter.getFilterType() != 1) {
            stringBuffer.append(iij.c(groupUnitType, hiAggregateOption.getGroupUnitSize(), "sample_point_health"));
        }
        for (int i3 = 0; i3 < length; i3++) {
            if (hiDataFilter.getFilterType() == 1) {
                hiDataFilter.getFilterOptions().get(i3).setConstantsKey("type_id" + hiDataFilter.getFilterOptions().get(i3).getDataType());
                stringBuffer.append(iij.e(1, type[i3], hiDataFilter.getFilterOptions().get(i3).getConstantsKey()));
            } else {
                stringBuffer.append(iij.e(hiAggregateOption.getAggregateType(), type[i3], constantsKey[i3]));
            }
            if (i3 < length - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(" from sample_point_health where sample_point_health.start_time >=? and sample_point_health.start_time <=?  and sample_point_health.merged");
        stringBuffer.append(z ? CommonUtil.EQUAL : " !=? ");
        iij.e("sample_point_health.type_id", type, stringBuffer, strArr, i);
        iij.d("sample_point_health.client_id", list, stringBuffer, strArr, i + type.length);
        if (hiDataFilter.getFilterType() == 1) {
            iij.c(stringBuffer, "sample_point_health", "start_time");
            stringBuffer.append(" having ").append(iil.c(hiDataFilter)).append(Constants.RIGHT_BRACKET_ONLY);
        }
        if (groupUnitType != 0) {
            iij.c(stringBuffer, "unit_index");
        } else {
            iij.c(stringBuffer, "sample_point_health", "start_time");
        }
        if (hiDataFilter.getFilterType() == 0) {
            stringBuffer.append(" having ").append(iil.c(hiDataFilter));
        }
        if (hiDataFilter.getFilterType() == 1) {
            iij.a(stringBuffer, "start_time", hiAggregateOption.getSortOrder());
        } else {
            iij.e(stringBuffer, "sample_point_health", "start_time", hiAggregateOption.getSortOrder());
        }
        iij.a(stringBuffer, hiAggregateOption.getAnchor(), hiAggregateOption.getCount());
        return stringBuffer.toString();
    }

    public static String a(List<Integer> list, String[] strArr, int i, HiAggregateOption hiAggregateOption, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select sample_point_health_stress.start_time,sample_point_health_stress.end_time,sample_point_health_stress.type_id,max(sample_point_health_stress.modified_time) as modified_time,");
        int groupUnitType = hiAggregateOption.getGroupUnitType();
        stringBuffer.append(iij.c(groupUnitType, hiAggregateOption.getGroupUnitSize(), "sample_point_health_stress"));
        int[] type = hiAggregateOption.getType();
        String[] constantsKey = hiAggregateOption.getConstantsKey();
        int length = type.length;
        for (int i2 = 0; i2 < length; i2++) {
            stringBuffer.append(iij.d(hiAggregateOption.getAggregateType(), type[i2], constantsKey[i2]));
            if (i2 < length - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(" from sample_point_health_stress where sample_point_health_stress.start_time >=? and sample_point_health_stress.start_time <=?  and sample_point_health_stress.merged");
        stringBuffer.append(z ? CommonUtil.EQUAL : " !=? ");
        if (hiAggregateOption.getModifiedEndTime() > 0) {
            iij.e(stringBuffer, "sample_point_health_stress");
        }
        a(stringBuffer, "sample_point_health_stress");
        iij.e("sample_point_health_stress.type_id", type, stringBuffer, strArr, i);
        iij.d("sample_point_health_stress.client_id", list, stringBuffer, strArr, i + type.length);
        if (groupUnitType != 0) {
            iij.c(stringBuffer, "unit_index");
        } else {
            iij.c(stringBuffer, "sample_point_health_stress", "start_time");
        }
        iij.e(stringBuffer, "sample_point_health_stress", "start_time", hiAggregateOption.getSortOrder());
        iij.a(stringBuffer, hiAggregateOption.getAnchor(), hiAggregateOption.getCount());
        return stringBuffer.toString();
    }

    public static String c(List<Integer> list, String[] strArr, int i, int i2, int i3, String[] strArr2, int[] iArr, int i4) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select sample_point_health_stress.start_time,sample_point_health_stress.end_time,sample_point_health_stress.type_id,");
        stringBuffer.append(iij.c(i2, 1, "sample_point_health_stress"));
        int length = iArr.length;
        for (int i5 = 0; i5 < length; i5++) {
            stringBuffer.append(iij.d(iArr[i5], i3, strArr2[i5]));
            if (i5 < length - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(" from sample_point_health_stress where sample_point_health_stress.start_time >=? and sample_point_health_stress.start_time <=? and sample_point_health_stress.type_id =? and sample_point_health_stress.merged =? and sync_status !=? ");
        iij.d("sample_point_health_stress.client_id", list, stringBuffer, strArr, i);
        if (i2 != 0) {
            iij.c(stringBuffer, "unit_index");
        } else {
            iij.c(stringBuffer, "sample_point_health_stress", "start_time");
        }
        iij.e(stringBuffer, "sample_point_health_stress", "start_time", i4);
        return stringBuffer.toString();
    }

    public static String b(List<Integer> list, String[] strArr, int i, int i2, int i3, String[] strArr2, int[] iArr, int i4) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select sample_point_health.start_time,sample_point_health.end_time,sample_point_health.type_id,");
        stringBuffer.append(iij.c(i2, 1, "sample_point_health"));
        int length = iArr.length;
        for (int i5 = 0; i5 < length; i5++) {
            stringBuffer.append(iij.e(iArr[i5], i3, strArr2[i5]));
            if (i5 < length - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(" from sample_point_health where sample_point_health.start_time >=? and sample_point_health.start_time <=? and sample_point_health.type_id =? and sample_point_health.merged =? and sync_status !=? ");
        iij.d("sample_point_health.client_id", list, stringBuffer, strArr, i);
        if (i2 != 0) {
            iij.c(stringBuffer, "unit_index");
        } else {
            iij.c(stringBuffer, "sample_point_health", "start_time");
        }
        iij.e(stringBuffer, "sample_point_health", "start_time", i4);
        return stringBuffer.toString();
    }

    public static String b(List<Integer> list, String[] strArr, int i, int i2, int i3, String[] strArr2, int[] iArr, int i4, double d) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select sample_point_health.start_time,sample_point_health.end_time,sample_point_health.type_id,");
        stringBuffer.append(iij.c(i2, 1, "sample_point_health"));
        int length = iArr.length;
        for (int i5 = 0; i5 < length; i5++) {
            stringBuffer.append(iij.e(iArr[i5], i3, strArr2[i5]));
            if (i5 < length - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(" from sample_point_health where sample_point_health.start_time >=? and sample_point_health.start_time <=? and sample_point_health.type_id =? and sample_point_health.value = ");
        stringBuffer.append(d).append(" and sample_point_health.merged =? and sync_status !=? ");
        iij.d("sample_point_health.client_id", list, stringBuffer, strArr, i);
        if (i2 != 0) {
            iij.c(stringBuffer, "unit_index");
        } else {
            iij.c(stringBuffer, "sample_point_health", "start_time");
        }
        iij.e(stringBuffer, "sample_point_health", "start_time", i4);
        return stringBuffer.toString();
    }

    public static String c(List<Integer> list, String[] strArr, int i, HiAggregateOption hiAggregateOption, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select sample_point.start_time,sample_point.end_time,sample_point.type_id,");
        int groupUnitType = hiAggregateOption.getGroupUnitType();
        stringBuffer.append(iij.c(groupUnitType, hiAggregateOption.getGroupUnitSize(), "sample_point"));
        int[] type = hiAggregateOption.getType();
        String[] constantsKey = hiAggregateOption.getConstantsKey();
        int length = type.length;
        for (int i2 = 0; i2 < length; i2++) {
            stringBuffer.append(iij.a(hiAggregateOption.getAggregateType(), type[i2], constantsKey[i2]));
            if (i2 < length - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(" from sample_point where sample_point.start_time >=? and sample_point.start_time <=? and sample_point.sync_status =? ");
        if (z) {
            stringBuffer.append(" and sample_point.merged =? ");
        }
        iij.e("sample_point.type_id", type, stringBuffer, strArr, i);
        iij.d("sample_point.client_id", list, stringBuffer, strArr, i + type.length);
        if (groupUnitType != 0) {
            iij.c(stringBuffer, "unit_index");
        } else {
            iij.c(stringBuffer, "sample_point", "start_time");
        }
        iij.e(stringBuffer, "sample_point", "start_time", hiAggregateOption.getSortOrder());
        iij.a(stringBuffer, hiAggregateOption.getAnchor(), hiAggregateOption.getCount());
        return stringBuffer.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x00c3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x033c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String c(java.util.List<java.lang.Integer> r26, java.lang.String[] r27, int r28, com.huawei.hihealth.HiAggregateOption r29, boolean r30, java.lang.String r31, defpackage.ifl r32) {
        /*
            Method dump skipped, instructions count: 859
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.iii.c(java.util.List, java.lang.String[], int, com.huawei.hihealth.HiAggregateOption, boolean, java.lang.String, ifl):java.lang.String");
    }

    public static String b(List<Integer> list, int[] iArr, String[] strArr, int i, String[] strArr2, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select sample_point._id,sample_point.start_time,sample_point.end_time,sample_point.type_id,sample_point.metadata,sample_point.client_id,sample_point.timeZone,sample_point.modified_time,");
        int length = iArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            stringBuffer.append("max");
            iij.a(stringBuffer, "sample_point", "type_id", "value", iArr[i3], strArr[i3]);
            if (i3 < length - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(" from ").append("sample_point");
        stringBuffer.append(" where ").append("sample_point").append(".").append("sync_status").append(CommonUtil.EQUAL);
        iij.e("sample_point.type_id", iArr, stringBuffer, strArr2, i2);
        iij.d("sample_point.client_id", list, stringBuffer, strArr2, i2 + length);
        iij.c(stringBuffer, "sample_point", "start_time");
        stringBuffer.append(", ").append("sample_point").append(".").append("client_id");
        stringBuffer.append(" ORDER BY ").append("sample_point").append(".").append("start_time").append(" DESC ");
        stringBuffer.append(" limit ").append("0,").append(i);
        return stringBuffer.toString();
    }

    private static void a(StringBuffer stringBuffer, String str) {
        stringBuffer.append(" and ").append(str).append(".").append("sync_status").append(" != ").append(2);
    }
}
