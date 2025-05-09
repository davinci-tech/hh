package defpackage;

import java.util.List;

/* loaded from: classes7.dex */
public class iim {
    public static String c(String str, List<Integer> list, String[] strArr, int i, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Select (count(*) +(select (case t1.type_id when ? then 1 else 0 end) from sample_session t1 where t1.start_time >= ? and t1.start_time <= ?");
        if (z) {
            stringBuffer.append(" and t1.merged =?  ");
        }
        stringBuffer.append("ORDER BY start_time ASC LIMIT 0,1 )) as ");
        stringBuffer.append(str);
        stringBuffer.append(" From sample_session t1 inner join sample_session t2 on t2.start_time = (Select min(start_time) From sample_session t2 where t2.start_time >= t1.end_time)  and t1.type_id <> ? and t2.type_id = ? and t1.start_time >= ? and t1.start_time <= ? ");
        if (z) {
            stringBuffer.append(" and t1.merged =?  ");
        }
        iij.d("t1.client_id", list, stringBuffer, strArr, i);
        return stringBuffer.toString();
    }

    public static String b(String str, List<Integer> list, String[] strArr, int i, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Select (count(*) +(select (case t1.type_id when ? then 1 else 0 end) from sample_session_health t1 where t1.start_time >= ? and t1.start_time <= ?");
        if (z) {
            stringBuffer.append(" and t1.merged =?  ");
        }
        stringBuffer.append("ORDER BY start_time ASC LIMIT 0,1 )) as ");
        stringBuffer.append(str);
        stringBuffer.append(" From sample_session_health t1 inner join sample_session_health t2 on t2.start_time = (Select min(start_time) From sample_session_health t2 where t2.start_time >= t1.end_time)  and t1.type_id <> ? and t2.type_id = ? and t1.start_time >= ? and t1.start_time <= ? ");
        if (z) {
            stringBuffer.append(" and t1.merged =?  ");
        }
        iij.d("t1.client_id", list, stringBuffer, strArr, i);
        return stringBuffer.toString();
    }

    public static String a(String str, List<Integer> list, String[] strArr, int i, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Select (count(*) +(select (case t1.type_id when ? then 1 else 0 end) from sample_session_core t1 where t1.start_time >= ? and t1.start_time <= ?");
        if (z) {
            stringBuffer.append(" and t1.merged =?  ");
        }
        stringBuffer.append("ORDER BY start_time ASC LIMIT 0,1 )) as ");
        stringBuffer.append(str);
        stringBuffer.append(" From sample_session_core t1 inner join sample_session_core t2 on t2.start_time = (Select min(start_time) From sample_session_core t2 where t2.start_time >= t1.end_time)  and t1.type_id <> ? and t2.type_id = ? and t1.start_time >= ? and t1.start_time <= ? ");
        if (z) {
            stringBuffer.append(" and t1.merged =?  ");
        }
        iij.d("t1.client_id", list, stringBuffer, strArr, i);
        return stringBuffer.toString();
    }
}
