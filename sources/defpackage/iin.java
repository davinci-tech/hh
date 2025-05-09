package defpackage;

import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import java.util.List;

/* loaded from: classes7.dex */
public class iin {
    public static String d(HiDataReadOption hiDataReadOption, List<Integer> list, String[] strArr, int i, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select sample_session.start_time as start_time,sample_session.end_time as end_time,sample_session.type_id as session_type,");
        int[] type = hiDataReadOption.getType();
        String[] constantsKey = hiDataReadOption.getConstantsKey();
        int length = type.length;
        for (int i2 = 0; i2 < length; i2++) {
            iij.e(stringBuffer, "sample_point", "type_id", "value", type[i2], constantsKey[i2]);
            if (i2 < length - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(" from ").append("sample_session").append(" INNER JOIN ").append("sample_point").append(" ON ").append("sample_session").append(".").append("start_time").append(" = ").append("sample_point").append(".").append("start_time");
        if (!z) {
            stringBuffer.append(" and ").append("sample_session").append(".").append("client_id").append(" = ").append("sample_point").append(".").append("client_id");
        }
        stringBuffer.append(" where ").append("sample_session").append(".").append("start_time").append(" >=? and ").append("sample_session").append(".").append("start_time").append(" <=? and ").append("sample_session").append(".").append("type_id").append(" >=? and ").append("sample_session").append(".").append("type_id").append(" <=? ");
        if (z) {
            stringBuffer.append(" and ").append("sample_session").append(".").append("merged").append(CommonUtil.EQUAL_END).append("sample_point").append(".").append("merged").append(CommonUtil.EQUAL);
        }
        int length2 = type.length;
        iij.e("sample_point.type_id", type, stringBuffer, strArr, i);
        int size = list.size();
        iij.d("sample_session.client_id", list, stringBuffer, strArr, length2 + i);
        iij.d("sample_point.client_id", list, stringBuffer, strArr, size + i + length2);
        iij.c(stringBuffer, "sample_session", "start_time");
        iij.a(stringBuffer, "start_time", hiDataReadOption.getSortOrder());
        iij.a(stringBuffer, hiDataReadOption.getAnchor(), hiDataReadOption.getCount());
        return stringBuffer.toString();
    }

    public static String e(int[] iArr, String[] strArr, int i, String[] strArr2, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select sample_session._id,sample_session.start_time,sample_session.end_time,sample_session.type_id,sample_session.metadata,sample_session.client_id,sample_session.timezone,sample_session.modified_time,");
        int length = iArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            iij.e(stringBuffer, "sample_point", "type_id", "value", iArr[i3], strArr[i3]);
            stringBuffer.append(",");
        }
        for (int i4 = 0; i4 < length; i4++) {
            iij.e(stringBuffer, "sample_point", "type_id", "merged", iArr[i4], strArr[i4] + "Merged");
            if (i4 < length - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(" from ").append("sample_session").append(" INNER JOIN ").append("sample_point").append(" ON ").append("sample_session").append(".").append("start_time").append(" = ").append("sample_point").append(".").append("start_time").append(" and ").append("sample_session").append(".").append("client_id").append(" = ").append("sample_point").append(".").append("client_id");
        stringBuffer.append(" where ").append("sample_session").append(".").append("sync_status").append(CommonUtil.EQUAL_END).append("sample_session").append(".").append("client_id").append(CommonUtil.EQUAL_END).append("sample_point").append(".").append("client_id").append(CommonUtil.EQUAL);
        iij.e("sample_point.type_id", iArr, stringBuffer, strArr2, i2);
        iij.c(stringBuffer, "sample_session", "start_time");
        stringBuffer.append(" ORDER BY ").append("sample_session").append(".").append("start_time").append(" DESC ");
        stringBuffer.append(" limit ").append("0,").append(i);
        return stringBuffer.toString();
    }

    public static String c(int[] iArr, String[] strArr, String[] strArr2, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select sample_session._id,sample_session.start_time,sample_session.end_time,sample_session.type_id,sample_session.metadata,sample_session.client_id,sample_session.timezone,sample_session.modified_time,");
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            iij.e(stringBuffer, "sample_point", "type_id", "value", iArr[i2], strArr[i2]);
            stringBuffer.append(",");
        }
        for (int i3 = 0; i3 < length; i3++) {
            iij.e(stringBuffer, "sample_point", "type_id", "merged", iArr[i3], strArr[i3] + "Merged");
            if (i3 < length - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(" from ").append("sample_session").append(" INNER JOIN ").append("sample_point").append(" ON ").append("sample_session").append(".").append("start_time").append(" = ").append("sample_point").append(".").append("start_time").append(" and ").append("sample_session").append(".").append("client_id").append(" = ").append("sample_point").append(".").append("client_id");
        stringBuffer.append(" where ").append("sample_session").append(".").append("sync_status").append(CommonUtil.EQUAL_END).append("sample_session").append(".").append("client_id").append(CommonUtil.EQUAL_END).append("sample_point").append(".").append("client_id").append(CommonUtil.EQUAL_END).append("sample_session").append(".").append("start_time").append(" >=? and ").append("sample_session").append(".").append("start_time").append(" <=? ");
        iij.e("sample_point.type_id", iArr, stringBuffer, strArr2, i);
        iij.c(stringBuffer, "sample_session", "start_time");
        stringBuffer.append(" ORDER BY ").append("sample_session").append(".").append("start_time").append(" DESC ");
        return stringBuffer.toString();
    }

    public static String b(List<Integer> list, String[] strArr, int i, HiAggregateOption hiAggregateOption, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select sample_session.start_time,sample_session.end_time,sample_session.type_id,max(sample_session.modified_time) as modified_time,");
        int groupUnitType = hiAggregateOption.getGroupUnitType();
        stringBuffer.append(iij.e(groupUnitType, hiAggregateOption.getGroupUnitSize()));
        int[] type = hiAggregateOption.getType();
        String[] constantsKey = hiAggregateOption.getConstantsKey();
        int length = type.length;
        for (int i2 = 0; i2 < length; i2++) {
            stringBuffer.append(iij.a(hiAggregateOption.getAggregateType(), type[i2], constantsKey[i2]));
            if (i2 < length - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(" from sample_session INNER JOIN sample_point ON sample_session.start_time = sample_point.start_time");
        if (!z) {
            stringBuffer.append(" and sample_session.client_id = sample_point.client_id");
        }
        stringBuffer.append(" where sample_session.start_time >=? and sample_session.start_time <=? and ");
        if (hiAggregateOption.getAlignType() == 20001) {
            stringBuffer.append("sample_session.type_id >=? and sample_session.type_id <=? ");
        } else {
            stringBuffer.append("sample_session.type_id =? ");
        }
        if (z) {
            stringBuffer.append(" and sample_session.merged =? and sample_point.merged =? ");
        }
        int size = list.size();
        iij.d("sample_session.client_id", list, stringBuffer, strArr, i);
        iij.d("sample_point.client_id", list, stringBuffer, strArr, size + i);
        if (groupUnitType != 0) {
            iij.c(stringBuffer, "unit_index");
        } else {
            iij.c(stringBuffer, "sample_session", "start_time");
        }
        iij.e(stringBuffer, "sample_session", "start_time", hiAggregateOption.getSortOrder());
        iij.a(stringBuffer, hiAggregateOption.getAnchor(), hiAggregateOption.getCount());
        return stringBuffer.toString();
    }

    public static String d(List<Integer> list, String[] strArr, int i, HiAggregateOption hiAggregateOption, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select sample_session.start_time,sample_session.end_time,sample_session.type_id,");
        int groupUnitType = hiAggregateOption.getGroupUnitType();
        stringBuffer.append(iij.e(groupUnitType, hiAggregateOption.getGroupUnitSize()));
        int[] type = hiAggregateOption.getType();
        String[] constantsKey = hiAggregateOption.getConstantsKey();
        int length = type.length;
        for (int i2 = 0; i2 < length; i2++) {
            stringBuffer.append(iij.a(hiAggregateOption.getAggregateType(), type[i2], constantsKey[i2]));
            if (i2 < length - 1) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(" from sample_session INNER JOIN sample_point ON sample_session.start_time = sample_point.start_time");
        if (!z) {
            stringBuffer.append(" and sample_session.client_id = sample_point.client_id");
        }
        stringBuffer.append(" where sample_session.start_time >=? and sample_session.start_time <=? and ");
        if (hiAggregateOption.getAlignType() == 20001) {
            stringBuffer.append("sample_session.type_id >=? and sample_session.type_id <=? ");
        } else {
            stringBuffer.append("sample_session.type_id =? ");
        }
        if (z) {
            stringBuffer.append(" and sample_session.merged =? and sample_point.merged =? ");
        }
        int size = list.size();
        iij.d("sample_session.client_id", list, stringBuffer, strArr, i);
        iij.d("sample_point.client_id", list, stringBuffer, strArr, size + i);
        if (groupUnitType != 0) {
            iij.c(stringBuffer, "unit_index");
        } else {
            iij.c(stringBuffer, "sample_session", "start_time");
        }
        iij.e(stringBuffer, "sample_session", "start_time", hiAggregateOption.getSortOrder());
        iij.a(stringBuffer, hiAggregateOption.getAnchor(), hiAggregateOption.getCount());
        return stringBuffer.toString();
    }

    public static String e(List<Integer> list, String[] strArr, int i, HiAggregateOption hiAggregateOption, int i2, String str, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select sample_session.start_time,sample_session.end_time,sample_session.type_id,");
        stringBuffer.append(iij.b(hiAggregateOption.getAggregateType(), str));
        stringBuffer.append(" from sample_session where sample_session.start_time >=? and sample_session.start_time <=? and ");
        if (i2 == 20001 || i2 == 22000) {
            stringBuffer.append("sample_session.type_id >=? and sample_session.type_id <=? ");
        } else {
            stringBuffer.append("sample_session.type_id =? ");
        }
        if (z) {
            stringBuffer.append(" and sample_session.merged =? ");
        }
        iij.d("sample_session.client_id", list, stringBuffer, strArr, i);
        iij.c(stringBuffer, "sample_session", "type_id");
        iij.e(stringBuffer, "sample_session", "start_time", hiAggregateOption.getSortOrder());
        iij.a(stringBuffer, hiAggregateOption.getAnchor(), hiAggregateOption.getCount());
        return stringBuffer.toString();
    }

    public static String c(List<Integer> list, String[] strArr, int i, HiAggregateOption hiAggregateOption, int i2, String str, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select sample_session_health.start_time,sample_session_health.end_time,sample_session_health.type_id,");
        stringBuffer.append(iij.d(hiAggregateOption.getAggregateType(), str));
        stringBuffer.append(" from sample_session_health where sample_session_health.start_time >=? and sample_session_health.start_time <=? and ");
        if (i2 == 20001 || i2 == 22000) {
            stringBuffer.append("sample_session_health.type_id >=? and sample_session_health.type_id <=? ");
        } else {
            stringBuffer.append("sample_session_health.type_id =? ");
        }
        if (z) {
            stringBuffer.append(" and sample_session_health.merged =? ");
        }
        iij.d("sample_session_health.client_id", list, stringBuffer, strArr, i);
        iij.c(stringBuffer, "sample_session_health", "type_id");
        iij.e(stringBuffer, "sample_session_health", "start_time", hiAggregateOption.getSortOrder());
        iij.a(stringBuffer, hiAggregateOption.getAnchor(), hiAggregateOption.getCount());
        return stringBuffer.toString();
    }

    public static String a(List<Integer> list, String[] strArr, int i, HiAggregateOption hiAggregateOption, int i2, String str, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select sample_session_core.start_time,sample_session_core.end_time,sample_session_core.type_id,");
        stringBuffer.append(iij.e(hiAggregateOption.getAggregateType(), str));
        stringBuffer.append(" from sample_session_core where sample_session_core.start_time >=? and sample_session_core.start_time <=? and ");
        if (i2 == 20001 || i2 == 22000) {
            stringBuffer.append("sample_session_core.type_id >=? and sample_session_core.type_id <=? ");
        } else {
            stringBuffer.append("sample_session_core.type_id =? ");
        }
        if (z) {
            stringBuffer.append(" and sample_session_core.merged =? ");
        }
        iij.d("sample_session_core.client_id", list, stringBuffer, strArr, i);
        iij.c(stringBuffer, "sample_session_core", "type_id");
        iij.e(stringBuffer, "sample_session_core", "start_time", hiAggregateOption.getSortOrder());
        iij.a(stringBuffer, hiAggregateOption.getAnchor(), hiAggregateOption.getCount());
        return stringBuffer.toString();
    }

    public static String a(List<Integer> list, String[] strArr, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" INSERT INTO sample_session(start_time,end_time,type_id,metadata,client_id,merged,sync_status,timezone,modified_time) select start_time,end_time,type_id,metadata,?,merged,?,timezone,? from sample_session where start_time >=? and start_time <=? and type_id >=? and type_id <=? and merged =? ");
        iij.d("client_id", list, stringBuffer, strArr, i);
        return stringBuffer.toString();
    }

    public static String d(List<Integer> list, String[] strArr, HiAggregateOption hiAggregateOption, int i, String str) {
        String str2 = i > 22099 ? "sample_session_core" : "sample_session_health";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select date((sleep_start_time/1000),'unixepoch', 'localtime') as group_tag , count(*) as ");
        stringBuffer.append(str);
        stringBuffer.append(" ,  *  from  (  select  ( ");
        stringBuffer.append(str2).append(".start_time + 14400000 )  as sleep_start_time ,  *  from ");
        stringBuffer.append(str2);
        stringBuffer.append(" where ").append(str2).append(".start_time >=? and ").append(str2).append(".start_time <=? and ").append(str2).append(".sync_status != 2 and  ( ");
        String concat = str2.concat(".type_id");
        stringBuffer.append(concat).append(CommonUtil.EQUAL);
        int length = strArr.length;
        int size = list.size();
        for (int i2 = 0; i2 < (length - size) - 3; i2++) {
            stringBuffer.append(" or ").append(concat).append(CommonUtil.EQUAL);
        }
        stringBuffer.append(" ) ");
        iij.d(str2.concat(".client_id"), list, stringBuffer, strArr, strArr.length - list.size());
        stringBuffer.append(" ) ").append(" as ").append("temp_table");
        stringBuffer.append(" group by ").append("group_tag");
        iij.e(stringBuffer, "temp_table", "start_time", hiAggregateOption.getSortOrder());
        iij.a(stringBuffer, hiAggregateOption.getAnchor(), hiAggregateOption.getCount());
        return stringBuffer.toString();
    }
}
