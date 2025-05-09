package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.ContentRecord;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class sz {

    /* renamed from: a, reason: collision with root package name */
    private static final String f7528a = "sz";

    private static List<ta> a(Context context, ContentRecord contentRecord, Map<String, String> map, List<Integer> list) {
        Object svVar;
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (Integer num : list) {
            switch (num.intValue()) {
                case 0:
                    svVar = new sv();
                    break;
                case 1:
                    svVar = new su(context, contentRecord, false, map);
                    break;
                case 2:
                    svVar = new st(context, contentRecord, map);
                    break;
                case 3:
                    svVar = new sm(context, contentRecord);
                    break;
                case 4:
                    svVar = new su(context, contentRecord, true, map);
                    break;
                case 5:
                    svVar = new sx(context, contentRecord);
                    break;
                case 6:
                    svVar = new so(context, contentRecord);
                    break;
                case 7:
                    svVar = new sn(context, contentRecord);
                    break;
                case 8:
                    svVar = new sw(context, contentRecord, map);
                    break;
                case 9:
                    svVar = new sy(context, contentRecord, map);
                    break;
                case 10:
                case 13:
                case 14:
                default:
                    ho.c(f7528a, "unsupport action:" + num);
                    svVar = null;
                    break;
                case 11:
                    svVar = new sr(context, contentRecord);
                    break;
                case 12:
                    svVar = new sq(context, contentRecord);
                    break;
                case 15:
                    svVar = new sp(context, contentRecord);
                    break;
            }
            if (svVar != null) {
                arrayList.add(svVar);
            }
        }
        return arrayList;
    }

    public static ta a(Context context, ContentRecord contentRecord, Map<String, String> map) {
        if (context == null || contentRecord == null || map == null) {
            return new sv();
        }
        List<ta> a2 = a(context, contentRecord, map, contentRecord.M());
        if (a2 == null || a2.size() <= 0) {
            return new sv();
        }
        ta taVar = null;
        for (ta taVar2 : a2) {
            if (taVar != null) {
                taVar.a(taVar2);
            }
            taVar = taVar2;
        }
        return a2.get(0);
    }
}
