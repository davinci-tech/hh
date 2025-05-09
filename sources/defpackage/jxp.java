package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jxr;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jxp {
    private static final Map<Integer, Integer> d = new LinkedHashMap(16);
    private static final Map<Integer, Integer> c = new HashMap(16);

    public static int c(cwe cweVar) {
        if (cweVar == null) {
            LogUtil.h("BasicUnTlvSample", "unTlvGetSampleFrameCount is null");
            return 0;
        }
        List<cwe> a2 = cweVar.a();
        int i = 0;
        for (int i2 = 0; i2 < a2.size(); i2++) {
            List<cwd> e = a2.get(i2).e();
            for (int i3 = 0; i3 < e.size(); i3++) {
                String c2 = e.get(i3).c();
                if (CommonUtil.w(e.get(i3).e()) == 2) {
                    i = CommonUtil.w(c2);
                } else {
                    LogUtil.h("BasicUnTlvSample", "unTlvGetSampleFrameCount default");
                }
            }
        }
        return i;
    }

    private static List<jxv> e(String str) {
        ArrayList arrayList = new ArrayList(16);
        if (str.length() == 0 || str.length() < 2) {
            LogUtil.h("BasicUnTlvSample", "value.length is 0 or value.length() < position + SINGLE_BYTE_STRING_LEN");
            return arrayList;
        }
        try {
            int d2 = d(str);
            byte[] a2 = cvx.a(str.substring(0, d2));
            String substring = str.substring(d2);
            if (TextUtils.isEmpty(substring)) {
                return arrayList;
            }
            Map<Integer, Integer> d3 = d();
            if (!e(a2, d3, substring)) {
                LogUtil.h("BasicUnTlvSample", "watch or band send tlv is wrong, please check : ", str);
                return arrayList;
            }
            a(a2, substring, d3, arrayList);
            return arrayList;
        } catch (StringIndexOutOfBoundsException unused) {
            LogUtil.b("BasicUnTlvSample", "parseBaseParameter: StringIndexOutOfBoundsException");
            return arrayList;
        }
    }

    private static void a(byte[] bArr, String str, Map<Integer, Integer> map, List<jxv> list) {
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int intValue = entry.getKey().intValue();
            if (CommonUtil.a(bArr, intValue)) {
                int intValue2 = (entry.getValue().intValue() * 2) + i;
                list.add(b(str.substring(i, intValue2), intValue));
                i = intValue2;
            }
        }
    }

    private static jxv b(String str, int i) {
        int w = CommonUtil.w(str);
        Integer num = b().get(Integer.valueOf(i));
        if (num != null) {
            i = num.intValue();
        }
        return new jxv(i, w);
    }

    private static int d(String str) {
        int i;
        int length = str.length();
        int i2 = 0;
        while (true) {
            i = i2 + 2;
            if (i > length || !CommonUtil.a(cvx.a(str.substring(i2, i)), 7)) {
                break;
            }
            i2 = i;
        }
        return i;
    }

    private static boolean e(byte[] bArr, Map<Integer, Integer> map, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (CommonUtil.a(bArr, entry.getKey().intValue())) {
                i += entry.getValue().intValue() * 2;
            }
        }
        return str.length() >= i;
    }

    private static Map<Integer, Integer> d() {
        Map<Integer, Integer> map = d;
        if (map.isEmpty()) {
            map.put(0, 2);
            map.put(1, 2);
            map.put(2, 2);
            map.put(3, 2);
            map.put(4, 2);
            map.put(5, 1);
            map.put(6, 1);
            map.put(8, 1);
            map.put(9, 1);
            c();
        }
        return map;
    }

    private static void c() {
        if (CommonUtil.as()) {
            int i = -1;
            for (Map.Entry<Integer, Integer> entry : d.entrySet()) {
                int intValue = entry.getKey().intValue();
                i = i % 8 == 6 ? i + 2 : i + 1;
                if (intValue != i) {
                    throw new RuntimeException("PARAMETER_INDEX_AND_LENGTH index must increase 1, if last index is (8 * n + 7),increase 2,you support index is " + intValue + ",you should put key is : " + i);
                }
                if (entry.getValue().intValue() <= 0) {
                    throw new RuntimeException("you put support index : " + intValue + " byteLength is <= 0, please check.");
                }
            }
        }
    }

    private static Map<Integer, Integer> b() {
        Map<Integer, Integer> map = c;
        if (map.isEmpty()) {
            map.put(0, 4);
            map.put(4, 5);
            map.put(5, 6);
            map.put(6, 7);
        }
        return map;
    }

    private static List<jxv> a(List<jxv> list, long j, int i, int i2, int i3) {
        long j2 = i * 60;
        for (jxv jxvVar : list) {
            jxvVar.d(j + j2);
            jxvVar.b(i2);
            jxvVar.a(i3);
        }
        return list;
    }

    public static jxw a(cwe cweVar) {
        jxw jxwVar = new jxw();
        if (cweVar == null) {
            LogUtil.h("BasicUnTlvSample", "unTlvGetSampleFrame is null");
            return jxwVar;
        }
        ArrayList arrayList = new ArrayList(16);
        List<cwe> a2 = cweVar.a();
        int i = 0;
        for (int i2 = 0; i2 < a2.size(); i2++) {
            List<cwd> e = a2.get(i2).e();
            for (int i3 = 0; i3 < e.size(); i3++) {
                String c2 = e.get(i3).c();
                if (CommonUtil.w(e.get(i3).e()) == 3) {
                    i = CommonUtil.w(c2);
                    jxwVar.d(i);
                } else {
                    LogUtil.h("BasicUnTlvSample", "unTlvGetSampleFrame default");
                }
            }
            b(arrayList, i2, a2, i);
        }
        jxwVar.a(arrayList);
        return jxwVar;
    }

    private static void b(List<jxv> list, int i, List<cwe> list2, int i2) {
        if (i < 0 || i >= list2.size()) {
            LogUtil.h("BasicUnTlvSample", "dealSamplePointList out of index");
            return;
        }
        List<cwe> a2 = list2.get(i).a();
        for (int i3 = 0; i3 < a2.size(); i3++) {
            List<cwd> e = a2.get(i3).e();
            ArrayList arrayList = new ArrayList(16);
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < e.size(); i7++) {
                String c2 = e.get(i7).c();
                int w = CommonUtil.w(e.get(i7).e());
                if (w == 5) {
                    i4 = CommonUtil.w(c2);
                } else if (w == 6) {
                    arrayList.addAll(e(c2));
                } else if (w == 8) {
                    i5 = CommonUtil.w(c2);
                } else if (w == 9) {
                    i6 = CommonUtil.w(c2);
                } else {
                    arrayList.add(new jxv(w, CommonUtil.w(c2)));
                }
            }
            a(arrayList, i2, i4, i5, i6);
            list.addAll(arrayList);
        }
    }

    public static int d(cwe cweVar) {
        int i = 0;
        if (cweVar == null) {
            LogUtil.h("BasicUnTlvSample", "unTlvGetDesFrameCount is null");
            return 0;
        }
        for (cwd cwdVar : cweVar.e()) {
            if (CommonUtil.w(cwdVar.e()) == 1) {
                i = CommonUtil.w(cwdVar.c());
            } else {
                LogUtil.h("BasicUnTlvSample", "unTlvGetDesFrameCount default");
            }
        }
        return i;
    }

    public static jxr e(cwe cweVar) {
        jxr jxrVar = new jxr();
        if (cweVar == null) {
            LogUtil.h("BasicUnTlvSample", "unTlvGetDesFrame is null");
            return jxrVar;
        }
        List<cwd> e = cweVar.e();
        List<cwe> a2 = cweVar.a();
        for (cwd cwdVar : e) {
            if (CommonUtil.w(cwdVar.e()) == 1) {
                jxrVar.e(CommonUtil.w(cwdVar.c()));
            } else {
                LogUtil.h("BasicUnTlvSample", "unTlvGetDesFrame default");
            }
        }
        Iterator<cwe> it = a2.iterator();
        while (it.hasNext()) {
            c(jxrVar, it.next());
            LogUtil.c("BasicUnTlvSample", "desFrame.getDatas().add(frame)", jxrVar.toString());
        }
        return jxrVar;
    }

    private static void c(jxr jxrVar, cwe cweVar) {
        Iterator<cwe> it = cweVar.a().iterator();
        while (it.hasNext()) {
            List<cwd> e = it.next().e();
            jxr.e eVar = new jxr.e();
            for (cwd cwdVar : e) {
                int w = CommonUtil.w(cwdVar.e());
                if (w == 4) {
                    eVar.e(CommonUtil.w(cwdVar.c()));
                } else if (w == 5) {
                    eVar.b(CommonUtil.w(cwdVar.c()));
                } else if (w == 6) {
                    eVar.a(CommonUtil.w(cwdVar.c()));
                } else {
                    LogUtil.h("BasicUnTlvSample", "unTlvGetDesFrameCount default");
                }
            }
            jxrVar.d().add(eVar);
        }
    }
}
