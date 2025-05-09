package defpackage;

import android.text.TextUtils;
import com.alipay.sdk.m.p.e;
import com.huawei.hianalytics.core.storage.Event;
import com.huawei.openalliance.ad.constant.VastTag;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import defpackage.nc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

/* loaded from: classes8.dex */
public class my {
    private static String h(String str, int i) {
        int i2;
        int i3;
        ArrayList<ms> arrayList;
        ArrayList<ms> arrayList2;
        List<ms> subList;
        ArrayList<mw> arrayList3;
        try {
            nc.b.e("HEFileUtils", "shrinkHe20String:" + i);
            if (e(str) - i <= 500) {
                nc.b.c("HEFileUtils", "shrinkHe20String, too closed!");
                return str;
            }
            na c2 = c(str);
            if (!nf.e(c2)) {
                nc.b.b("HEFileUtils", "shrinkHe20String(), invalid HE20");
                return null;
            }
            nc.b.e("HEFileUtils", "shrinkHe20String() original pattern count:" + c2.b.size());
            Iterator<mw> it = c2.b.iterator();
            loop0: while (true) {
                if (!it.hasNext()) {
                    i2 = -1;
                    i3 = -1;
                    break;
                }
                mw next = it.next();
                ArrayList<ms> arrayList4 = next.b;
                if (arrayList4 != null) {
                    Iterator<ms> it2 = arrayList4.iterator();
                    while (it2.hasNext()) {
                        ms next2 = it2.next();
                        mr mrVar = next2.d;
                        if (mrVar != null && mrVar.b + mrVar.d + next.d >= i) {
                            i2 = next.b.indexOf(next2);
                            i3 = c2.b.indexOf(next);
                            break loop0;
                        }
                    }
                }
            }
            nc.b.e("HEFileUtils", "shrinkHe20String targetPatternListItemIndex:" + i3 + ", targetPatternItemIndex:" + i2);
            if (-1 != i3 && -1 != i2) {
                if (i2 == 0 && i3 == 0) {
                    arrayList3 = c2.b;
                } else {
                    if (i2 != 0 || i3 <= 0) {
                        if (i2 <= 0 || i3 != 0) {
                            if (i2 > 0 && i3 > 0) {
                                ArrayList<mw> arrayList5 = c2.b;
                                arrayList5.subList(i3 + 1, arrayList5.size()).clear();
                                arrayList = c2.b.get(r4.size() - 1).b;
                                arrayList2 = c2.b.get(r5.size() - 1).b;
                            }
                            nc.b.e("HEFileUtils", "shrinkHe20String()  pattern count:" + c2.b.size());
                            mw mwVar = new mw();
                            mwVar.d = i + (-48);
                            mwVar.b = new ArrayList<>();
                            mr mrVar2 = new mr();
                            mv mvVar = new mv();
                            mrVar2.e = mvVar;
                            mvVar.d = 0;
                            mvVar.f15189a = 0;
                            mrVar2.f15123a = "transient";
                            ms msVar = new ms();
                            msVar.d = mrVar2;
                            mwVar.b.add(msVar);
                            c2.b.add(mwVar);
                            String a2 = a(c2);
                            nc.d("shrink20string_in.he", str);
                            nc.d("shrink20string_out.he", a2);
                            return a2;
                        }
                        ArrayList<mw> arrayList6 = c2.b;
                        arrayList6.subList(i3 + 1, arrayList6.size()).clear();
                        arrayList = c2.b.get(r4.size() - 1).b;
                        arrayList2 = c2.b.get(r5.size() - 1).b;
                        subList = arrayList.subList(i2, arrayList2.size());
                        subList.clear();
                        nc.b.e("HEFileUtils", "shrinkHe20String()  pattern count:" + c2.b.size());
                        mw mwVar2 = new mw();
                        mwVar2.d = i + (-48);
                        mwVar2.b = new ArrayList<>();
                        mr mrVar22 = new mr();
                        mv mvVar2 = new mv();
                        mrVar22.e = mvVar2;
                        mvVar2.d = 0;
                        mvVar2.f15189a = 0;
                        mrVar22.f15123a = "transient";
                        ms msVar2 = new ms();
                        msVar2.d = mrVar22;
                        mwVar2.b.add(msVar2);
                        c2.b.add(mwVar2);
                        String a22 = a(c2);
                        nc.d("shrink20string_in.he", str);
                        nc.d("shrink20string_out.he", a22);
                        return a22;
                    }
                    arrayList3 = c2.b;
                }
                subList = arrayList3.subList(i3, arrayList3.size());
                subList.clear();
                nc.b.e("HEFileUtils", "shrinkHe20String()  pattern count:" + c2.b.size());
                mw mwVar22 = new mw();
                mwVar22.d = i + (-48);
                mwVar22.b = new ArrayList<>();
                mr mrVar222 = new mr();
                mv mvVar22 = new mv();
                mrVar222.e = mvVar22;
                mvVar22.d = 0;
                mvVar22.f15189a = 0;
                mrVar222.f15123a = "transient";
                ms msVar22 = new ms();
                msVar22.d = mrVar222;
                mwVar22.b.add(msVar22);
                c2.b.add(mwVar22);
                String a222 = a(c2);
                nc.d("shrink20string_in.he", str);
                nc.d("shrink20string_out.he", a222);
                return a222;
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String f(String str) {
        char c2;
        char c3;
        int i;
        int i2;
        int i3;
        nc.d("trimOverlapEvent_in.he", str);
        na c4 = c(str);
        if (!nf.e(c4)) {
            return str;
        }
        try {
            Iterator<mw> it = c4.b.iterator();
            while (it.hasNext()) {
                mw next = it.next();
                ArrayList<ms> arrayList = next.b;
                if (arrayList != null) {
                    Iterator<ms> it2 = arrayList.iterator();
                    while (true) {
                        c2 = 2;
                        if (!it2.hasNext()) {
                            break;
                        }
                        if (2 == it2.next().d.c) {
                            it2.remove();
                        }
                    }
                    if (1 != next.b.size()) {
                        Collections.sort(next.b, new c());
                        int i4 = 0;
                        int i5 = 0;
                        int i6 = 1;
                        for (int i7 = 1; i5 <= next.b.size() - i7 && i6 <= next.b.size() - i7; i7 = 1) {
                            mr mrVar = next.b.get(i5).d;
                            mr mrVar2 = next.b.get(i6).d;
                            int i8 = "transient".equals(mrVar.f15123a) ? 48 : mrVar.d;
                            int i9 = "transient".equals(mrVar2.f15123a) ? 48 : mrVar2.d;
                            if (mrVar2.b < mrVar.b + i8) {
                                if ("continuous".equals(mrVar2.f15123a) && (i = mrVar2.b + i9) > (i2 = mrVar.b + i8) && (i3 = i - i2) > 48) {
                                    ArrayList<mp> arrayList2 = new ArrayList<>();
                                    mp mpVar = new mp();
                                    mpVar.e = i4;
                                    mpVar.f15090a = 0.0d;
                                    mpVar.b = i4;
                                    mp mpVar2 = new mp();
                                    mpVar2.e = i3 / 3;
                                    mpVar2.f15090a = 1.0d;
                                    mpVar2.b = i4;
                                    mp mpVar3 = new mp();
                                    c3 = 2;
                                    mpVar3.e = (i3 / 3) * 2;
                                    mpVar3.f15090a = 1.0d;
                                    mpVar3.b = 0;
                                    mp mpVar4 = new mp();
                                    mpVar4.e = i3;
                                    mpVar4.f15090a = 0.0d;
                                    i4 = 0;
                                    mpVar4.b = 0;
                                    arrayList2.add(mpVar);
                                    arrayList2.add(mpVar2);
                                    arrayList2.add(mpVar3);
                                    arrayList2.add(mpVar4);
                                    mrVar2.d = i3;
                                    mrVar2.b = mrVar.b + i8;
                                    mrVar2.e.b = arrayList2;
                                    i5 = i6;
                                    i6++;
                                }
                                c3 = c2;
                                mrVar2.c = -1;
                                i6++;
                            } else {
                                c3 = c2;
                                i5 = i6;
                                i6++;
                            }
                            c2 = c3;
                        }
                        Iterator<ms> it3 = next.b.iterator();
                        while (it3.hasNext()) {
                            if (it3.next().d.c < 0) {
                                it3.remove();
                            }
                        }
                    }
                }
            }
            String a2 = a(c4);
            nc.d("trimOverlapEvent_out.he", a2);
            return a2;
        } catch (Throwable th) {
            nc.b.b("HEFileUtils", "trimOverlapEvent " + th.toString());
            return str;
        }
    }

    public static String a(String str, int i) {
        try {
            na c2 = c(str);
            for (int i2 = 0; i2 < c2.b.size(); i2++) {
                for (int i3 = 0; i3 < c2.b.get(i2).b.size(); i3++) {
                    if (c2.b.get(i2).d <= i && c2.b.get(i2).d + c2.b.get(i2).b.get(i3).d.b >= i) {
                        na naVar = new na();
                        naVar.d = new mz();
                        naVar.b = new ArrayList<>();
                        mw mwVar = new mw();
                        mwVar.d = 0;
                        ArrayList<ms> arrayList = new ArrayList<>();
                        mwVar.b = arrayList;
                        arrayList.addAll(c2.b.get(i2).b.subList(i3, c2.b.get(i2).b.size()));
                        Iterator<ms> it = mwVar.b.iterator();
                        while (it.hasNext()) {
                            mr mrVar = it.next().d;
                            mrVar.b = (mrVar.b + c2.b.get(i2).d) - i;
                        }
                        naVar.b.add(mwVar);
                        return a(naVar);
                    }
                    if (c2.b.get(i2).d > i) {
                        na naVar2 = new na();
                        naVar2.d = new mz();
                        naVar2.b = new ArrayList<>();
                        mw mwVar2 = new mw();
                        mwVar2.d = 0;
                        mwVar2.b = new ArrayList<>();
                        mr mrVar2 = new mr();
                        mv mvVar = new mv();
                        mrVar2.e = mvVar;
                        mvVar.d = 0;
                        mvVar.f15189a = 0;
                        mrVar2.f15123a = "transient";
                        ms msVar = new ms();
                        msVar.d = mrVar2;
                        mwVar2.b.add(msVar);
                        naVar2.b.add(mwVar2);
                        return a(naVar2);
                    }
                }
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String j(String str) {
        na naVar;
        try {
            naVar = c(str);
        } catch (Exception e) {
            e.printStackTrace();
            naVar = null;
        }
        if (!nf.e(naVar)) {
            nc.b.c("HEFileUtils", " , trim16pTo4p, invalid HE2.0 string!");
            return "";
        }
        Iterator<mw> it = naVar.b.iterator();
        while (it.hasNext()) {
            Iterator<ms> it2 = it.next().b.iterator();
            while (it2.hasNext()) {
                ms next = it2.next();
                mv mvVar = next.d.e;
                mvVar.b = a(mvVar.b);
                if (next.d.f15123a.equals("transient")) {
                    mv mvVar2 = next.d.e;
                    int i = mvVar2.f15189a;
                    if (i < 0) {
                        mvVar2.f15189a = 0;
                    } else if (i > 100) {
                        mvVar2.f15189a = 100;
                    }
                }
            }
        }
        return a(naVar);
    }

    public static com.apprichtap.haptic.b.a.c e(String str, int i) {
        nc.b.e("HEFileUtils", "getHeRootFromHeString, HE version:" + i);
        if (i == 1) {
            try {
                return a(str);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        if (i != 2) {
            return null;
        }
        try {
            return c(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static int d(String str) {
        try {
            return new JSONObject(str).getJSONObject("Metadata").getInt(e.g);
        } catch (Exception e) {
            nc.b.b("HEFileUtils", "getHeVersion ERROR, heString:" + str);
            e.printStackTrace();
            return 0;
        }
    }

    public static na c(String str) {
        if (2 != d(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            na naVar = new na();
            naVar.d = new mz();
            naVar.b = new ArrayList<>();
            JSONArray jSONArray = jSONObject.getJSONArray("PatternList");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                mw mwVar = new mw();
                mwVar.d = jSONObject2.getInt("AbsoluteTime");
                mwVar.b = new ArrayList<>();
                JSONArray jSONArray2 = jSONObject2.getJSONArray("Pattern");
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    JSONObject jSONObject3 = (JSONObject) jSONArray2.get(i2);
                    ms msVar = new ms();
                    msVar.d = new mr();
                    JSONObject jSONObject4 = jSONObject3.getJSONObject(Event.TAG);
                    msVar.d.f15123a = jSONObject4.getString(FaqConstants.FAQ_UPLOAD_FLAG);
                    if ("continuous".equals(msVar.d.f15123a)) {
                        msVar.d.d = jSONObject4.getInt(VastTag.DURATION);
                    } else if ("transient".equals(msVar.d.f15123a)) {
                        msVar.d.d = 48;
                    }
                    msVar.d.b = jSONObject4.getInt("RelativeTime");
                    msVar.d.c = jSONObject4.getInt("Index");
                    JSONObject jSONObject5 = jSONObject4.getJSONObject("Parameters");
                    msVar.d.e = new mv();
                    msVar.d.e.f15189a = jSONObject5.getInt("Frequency");
                    msVar.d.e.d = jSONObject5.getInt("Intensity");
                    msVar.d.e.b = new ArrayList<>();
                    if ("continuous".equals(msVar.d.f15123a)) {
                        JSONArray jSONArray3 = jSONObject5.getJSONArray("Curve");
                        for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                            JSONObject jSONObject6 = (JSONObject) jSONArray3.get(i3);
                            mp mpVar = new mp();
                            mpVar.b = jSONObject6.getInt("Frequency");
                            mpVar.f15090a = jSONObject6.getDouble("Intensity");
                            mpVar.e = jSONObject6.getInt("Time");
                            msVar.d.e.b.add(mpVar);
                        }
                    }
                    mwVar.b.add(msVar);
                }
                naVar.b.add(mwVar);
            }
            return naVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int c(String str, int i) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        com.apprichtap.haptic.b.a.c e = e(str, i);
        if (nf.e(e)) {
            return e.getDuration();
        }
        return 0;
    }

    public static String d(String str, int i) {
        na naVar;
        ArrayList<mw> arrayList;
        int i2;
        int i3;
        if (i == 0) {
            return str;
        }
        try {
            naVar = c(str);
        } catch (Exception e) {
            e.printStackTrace();
            naVar = null;
        }
        if (naVar == null || (arrayList = naVar.b) == null || arrayList.size() == 0) {
            nc.b.c("HEFileUtils", "  generatePartialHe20String, source HE invalid!");
            return "";
        }
        Iterator<mw> it = naVar.b.iterator();
        loop0: while (true) {
            if (!it.hasNext()) {
                i2 = -1;
                i3 = -1;
                break;
            }
            mw next = it.next();
            ArrayList<ms> arrayList2 = next.b;
            if (arrayList2 != null) {
                Iterator<ms> it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    ms next2 = it2.next();
                    mr mrVar = next2.d;
                    if (mrVar != null && mrVar.b + next.d >= i) {
                        i2 = next.b.indexOf(next2);
                        i3 = naVar.b.indexOf(next);
                        break loop0;
                    }
                }
            }
        }
        if (i3 < 0 || i2 < 0) {
            return "";
        }
        naVar.b.subList(0, i3).clear();
        naVar.b.get(0).b.subList(0, i2).clear();
        nc.b.e("HEFileUtils", "  generatePartialHe20String, targetPatternListItemIndex:" + i3 + ",targetPatterItemIndex:" + i2 + ", PatternList size:" + naVar.b.size() + ",Pattern size:" + naVar.b.get(0).b.size());
        Iterator<mw> it3 = naVar.b.iterator();
        while (it3.hasNext()) {
            mw next3 = it3.next();
            ArrayList<ms> arrayList3 = next3.b;
            if (arrayList3 != null) {
                int i4 = next3.d;
                if (i4 < i) {
                    Iterator<ms> it4 = arrayList3.iterator();
                    while (it4.hasNext()) {
                        mr mrVar2 = it4.next().d;
                        if (mrVar2 != null) {
                            mrVar2.b = (mrVar2.b + next3.d) - i;
                        }
                    }
                    next3.d = 0;
                } else {
                    next3.d = i4 - i;
                }
            }
        }
        if (i2 == 0 && naVar.b.get(0).d != 0) {
            mw mwVar = new mw();
            mwVar.d = 0;
            mwVar.b = new ArrayList<>();
            mr mrVar3 = new mr();
            mv mvVar = new mv();
            mrVar3.e = mvVar;
            mvVar.d = 0;
            mvVar.f15189a = 0;
            mrVar3.f15123a = "transient";
            ms msVar = new ms();
            msVar.d = mrVar3;
            mwVar.b.add(msVar);
            naVar.b.add(0, mwVar);
        }
        return a(naVar);
    }

    public static mu a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            mu muVar = new mu();
            muVar.e = new mt();
            muVar.c = new ArrayList<>();
            JSONArray jSONArray = jSONObject.getJSONArray("Pattern");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                ms msVar = new ms();
                msVar.d = new mr();
                JSONObject jSONObject3 = jSONObject2.getJSONObject(Event.TAG);
                msVar.d.f15123a = jSONObject3.getString(FaqConstants.FAQ_UPLOAD_FLAG);
                if ("continuous".equals(msVar.d.f15123a)) {
                    msVar.d.d = jSONObject3.getInt(VastTag.DURATION);
                }
                msVar.d.b = jSONObject3.getInt("RelativeTime");
                JSONObject jSONObject4 = jSONObject3.getJSONObject("Parameters");
                msVar.d.e = new mv();
                msVar.d.e.f15189a = jSONObject4.getInt("Frequency");
                msVar.d.e.d = jSONObject4.getInt("Intensity");
                msVar.d.e.b = new ArrayList<>();
                if ("continuous".equals(msVar.d.f15123a)) {
                    JSONArray jSONArray2 = jSONObject4.getJSONArray("Curve");
                    for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                        JSONObject jSONObject5 = (JSONObject) jSONArray2.get(i2);
                        mp mpVar = new mp();
                        mpVar.b = jSONObject5.getInt("Frequency");
                        mpVar.f15090a = jSONObject5.getDouble("Intensity");
                        mpVar.e = jSONObject5.getInt("Time");
                        msVar.d.e.b.add(mpVar);
                    }
                }
                muVar.c.add(msVar);
            }
            return muVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String i(String str, int i) {
        try {
            if (i - e(str) <= 500) {
                nc.b.c("HEFileUtils", "extendHe20String, too closed!");
                return str;
            }
            na c2 = c(str);
            if (!nf.e(c2)) {
                nc.b.b("HEFileUtils", "extendHe20String(), invalid HE20");
                return null;
            }
            nc.b.e("HEFileUtils", "extendHe20String()original pattern count:" + c2.b.size());
            ms msVar = new ms();
            mr mrVar = new mr();
            msVar.d = mrVar;
            mrVar.f15123a = "transient";
            mrVar.b = 0;
            mrVar.e = new mv();
            mv mvVar = msVar.d.e;
            mvVar.f15189a = 0;
            mvVar.d = 0;
            mw mwVar = new mw();
            mwVar.d = i - 500;
            ArrayList<ms> arrayList = new ArrayList<>();
            mwVar.b = arrayList;
            arrayList.add(msVar);
            c2.b.add(mwVar);
            String a2 = a(c2);
            nc.d("extend20string_in.he", str);
            nc.d("extend20string_out.he", a2);
            return a2;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static int e(String str) {
        na c2 = c(str);
        if (c2 != null) {
            return c2.getDuration();
        }
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:139:0x033e, code lost:
    
        if (4 != r14) goto L113;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int[] b(java.lang.String r22, int r23, int r24, int r25, int r26, int r27, boolean r28) {
        /*
            Method dump skipped, instructions count: 1173
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.my.b(java.lang.String, int, int, int, int, int, boolean):int[]");
    }

    private static ArrayList<mp> a(ArrayList<mp> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        int size = arrayList.size();
        nc.b.e("HEFileUtils", "trimTo4p size:" + size);
        if (size > 0 && size <= 4) {
            return arrayList;
        }
        mp mpVar = new mp();
        int i = size - 2;
        int i2 = i / 2;
        for (int i3 = 1; i3 <= i2; i3++) {
            mpVar.e += arrayList.get(i3).e;
            mpVar.f15090a += arrayList.get(i3).f15090a;
            mpVar.b += arrayList.get(i3).b;
        }
        mpVar.e /= i2;
        mpVar.f15090a = mpVar.f15090a / i2;
        mpVar.f15090a = Math.round(r7 * 10.0d) / 10.0d;
        mpVar.b /= i2;
        mp mpVar2 = new mp();
        for (int i4 = i2 + 1; i4 <= i; i4++) {
            mpVar2.e += arrayList.get(i4).e;
            mpVar2.f15090a += arrayList.get(i4).f15090a;
            mpVar2.b += arrayList.get(i4).b;
        }
        int i5 = i - i2;
        mpVar2.e /= i5;
        mpVar2.f15090a = mpVar2.f15090a / i5;
        mpVar2.f15090a = Math.round(r11 * 10.0d) / 10.0d;
        mpVar2.b /= i5;
        nc.b.e("HEFileUtils", "trimTo4p size:" + arrayList.size());
        arrayList.subList(1, size - 1).clear();
        arrayList.add(1, mpVar);
        arrayList.add(2, mpVar2);
        return arrayList;
    }

    public static String c(String str, boolean z) {
        String str2;
        if (z) {
            str = j(str);
        }
        String f = f(str);
        if (f == null || f.length() == 0) {
            str2 = "convertHE20ToHE10, null after trim";
        } else {
            na c2 = c(f);
            if (com.apprichtap.haptic.b.a.c.a(c2)) {
                Iterator<ms> it = c2.b.get(0).b.iterator();
                while (it.hasNext()) {
                    it.next().d.b += c2.b.get(0).d;
                }
                mu muVar = new mu();
                muVar.e = new mt();
                ArrayList<ms> arrayList = c2.b.get(0).b;
                muVar.c = arrayList;
                Iterator<ms> it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    ms next = it2.next();
                    mr mrVar = next.d;
                    if (mrVar != null && "continuous".equals(mrVar.f15123a)) {
                        mv mvVar = next.d.e;
                        if (-1 == mvVar.f15189a) {
                            mvVar.f15189a = 56;
                            Iterator<mp> it3 = mvVar.b.iterator();
                            while (it3.hasNext()) {
                                it3.next().b = 0;
                            }
                        }
                    }
                }
                String b = b(muVar);
                nc.b.e("HEFileUtils", "convertHE20ToHE10 result:" + b);
                return b;
            }
            str2 = "convertHE20ToHE10, empty HeRoot";
        }
        nc.b.b("HEFileUtils", str2);
        return null;
    }

    public static String e(String str, int i, int i2, int i3) {
        int i4;
        double d;
        double d2;
        double d3;
        int i5;
        String str2;
        nc.b.e("HEFileUtils", "overwriteBaseFrequencyAndIntensityOfHe20String, deltaFreq:" + i2 + ", deltaIntensity:" + i + ", majorCoreVersion:" + i3);
        if (i2 == 0 && (255 == i || 256 == i)) {
            str2 = "overwriteBaseFrequencyAndIntensityOfHe20String, do nothing!";
        } else {
            double d4 = 0.0d;
            double d5 = (255 == i || 256 == i) ? 0.0d : (i - 255.5d) / 255.5d;
            double d6 = i2 / 100.0d;
            nc.b.e("HEFileUtils", "overwriteBaseFrequencyAndIntensityOfHe20String, freqRatio:" + d6 + ", intensityRatio:" + d5);
            na c2 = c(str);
            if (nf.e(c2)) {
                Iterator<mw> it = c2.b.iterator();
                while (it.hasNext()) {
                    Iterator<ms> it2 = it.next().b.iterator();
                    while (it2.hasNext()) {
                        ms next = it2.next();
                        if ("transient".equals(next.d.f15123a)) {
                            mv mvVar = next.d.e;
                            int i6 = mvVar.f15189a;
                            if (i6 == 0 && mvVar.d == 0) {
                                nc.b.e("HEFileUtils", "overwriteBaseFrequencyAndIntensityOfHe20String, ignore placeholder event!");
                            } else {
                                if (i3 >= 24) {
                                    i5 = (int) (d6 >= d4 ? i6 + ((150 - i6) * d6) : i6 + ((i6 + 50) * d6));
                                } else {
                                    double d7 = i6;
                                    if (d6 >= 0.0d) {
                                        i6 = 100 - i6;
                                    }
                                    i5 = (int) (d7 + (i6 * d6));
                                }
                                mvVar.f15189a = i5;
                            }
                        } else if ("continuous".equals(next.d.f15123a)) {
                            mv mvVar2 = next.d.e;
                            if (d6 >= 0.0d) {
                                int i7 = mvVar2.f15189a;
                                d = i7;
                                i4 = 100 - i7;
                            } else {
                                i4 = mvVar2.f15189a;
                                d = i4;
                            }
                            mvVar2.f15189a = (int) (d + (i4 * d6));
                        }
                        mv mvVar3 = next.d.e;
                        if (d5 >= 0.0d) {
                            d3 = mvVar3.d;
                            d2 = (100 - r7) * d5;
                        } else {
                            int i8 = mvVar3.d;
                            d2 = i8;
                            d3 = i8 * d5;
                        }
                        mvVar3.d = (int) (d3 + d2);
                        d4 = 0.0d;
                    }
                }
                String a2 = a(c2);
                nc.b.e("HEFileUtils", "overwriteBaseFrequencyAndIntensityOfHe20String, result:" + a2);
                return a2;
            }
            str2 = "overwriteBaseFrequencyAndIntensityOfHe20String, do nothing as invalid he20String";
        }
        nc.b.e("HEFileUtils", str2);
        return str;
    }

    public static String b(String str, int i) {
        nc.b.e("HEFileUtils", "alignHE20DurationToMedia, target duration:" + i);
        if (48 <= i) {
            int c2 = c(str, 2);
            return i - c2 > 500 ? i(str, i) : c2 - i > 500 ? h(str, i) : str;
        }
        nc.b.c("HEFileUtils", "alignHE20DurationToMedia, target duration:" + i + ", do nothing!");
        return str;
    }

    public static String e(String str, double d) {
        double d2;
        try {
            na c2 = c(str);
            if (!nf.e(c2)) {
                return null;
            }
            Iterator<mw> it = c2.b.iterator();
            while (it.hasNext()) {
                mw next = it.next();
                ArrayList<ms> arrayList = next.b;
                if (arrayList != null) {
                    next.d = (int) (next.d / d);
                    Iterator<ms> it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        ms next2 = it2.next();
                        mr mrVar = next2.d;
                        if (mrVar != null) {
                            mrVar.b = (int) (mrVar.b / d);
                            if (!TextUtils.equals("transient", mrVar.f15123a)) {
                                mr mrVar2 = next2.d;
                                double d3 = mrVar2.d;
                                int i = (int) (d3 / d);
                                mrVar2.d = i;
                                if (i > 5000) {
                                    nc.b.a("HEFileUtils", "lower duration to 5000");
                                    next2.d.d = 5000;
                                    d2 = 5000.0d / d3;
                                } else {
                                    d2 = d;
                                }
                                Iterator<mp> it3 = next2.d.e.b.iterator();
                                while (it3.hasNext()) {
                                    mp next3 = it3.next();
                                    if (next3 != null) {
                                        next3.e = (int) (next3.e / d2);
                                    }
                                }
                                next2.d.e.b.get(r4.size() - 1).e = next2.d.d;
                            }
                        }
                    }
                }
            }
            return a(c2);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String b(String str) {
        mu muVar;
        ArrayList<ms> arrayList;
        try {
            muVar = a(str);
        } catch (Exception e) {
            e.printStackTrace();
            muVar = null;
        }
        if (muVar == null || (arrayList = muVar.c) == null || arrayList.size() == 0) {
            nc.b.c("HEFileUtils", " , convertHe10ToHe20, invalid HE1.0 string!");
            return "";
        }
        na naVar = new na();
        naVar.d = new mz();
        naVar.b = new ArrayList<>();
        mw mwVar = new mw();
        mwVar.b = muVar.c;
        mwVar.d = 0;
        naVar.b.add(mwVar);
        return a(naVar);
    }

    public static String e(mw mwVar, boolean z) {
        na naVar = new na();
        naVar.d = new mz();
        ArrayList<mw> arrayList = new ArrayList<>();
        naVar.b = arrayList;
        if (z) {
            mw mwVar2 = new mw();
            mwVar2.d = 0;
            mwVar2.b = mwVar.b;
            naVar.b.add(mwVar2);
        } else {
            arrayList.add(mwVar);
        }
        return a(naVar);
    }

    class c implements Comparator<ms> {
        @Override // java.util.Comparator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public int compare(ms msVar, ms msVar2) {
            return msVar.d.b - msVar2.d.b;
        }

        c() {
        }
    }

    public static String a(na naVar) {
        try {
            JSONStringer jSONStringer = new JSONStringer();
            jSONStringer.object();
            jSONStringer.key("Metadata").object().key("Created").value(naVar.d.c).key(VastTag.DESCRIPTION).value(naVar.d.f15236a).key(e.g).value(naVar.d.b).endObject();
            jSONStringer.key("PatternList").array();
            Iterator<mw> it = naVar.b.iterator();
            while (it.hasNext()) {
                mw next = it.next();
                jSONStringer.object().key("AbsoluteTime").value(next.d).key("Pattern").array();
                Iterator<ms> it2 = next.b.iterator();
                while (it2.hasNext()) {
                    ms next2 = it2.next();
                    jSONStringer.object();
                    jSONStringer.key(Event.TAG).object().key("Index").value(next2.d.c).key("RelativeTime").value(next2.d.b).key(FaqConstants.FAQ_UPLOAD_FLAG).value(next2.d.f15123a);
                    if ("continuous".equals(next2.d.f15123a)) {
                        jSONStringer.key(VastTag.DURATION).value(next2.d.d);
                    }
                    jSONStringer.key("Parameters").object().key("Frequency").value(next2.d.e.f15189a).key("Intensity").value(next2.d.e.d);
                    if ("continuous".equals(next2.d.f15123a)) {
                        jSONStringer.key("Curve").array();
                        Iterator<mp> it3 = next2.d.e.b.iterator();
                        while (it3.hasNext()) {
                            jSONStringer.object().key("Frequency").value(r6.b).key("Intensity").value(it3.next().f15090a).key("Time").value(r6.e).endObject();
                        }
                        jSONStringer.endArray();
                    }
                    jSONStringer.endObject().endObject().endObject();
                }
                jSONStringer.endArray().endObject();
            }
            jSONStringer.endArray().endObject();
            return jSONStringer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String b(mu muVar) {
        try {
            JSONStringer jSONStringer = new JSONStringer();
            jSONStringer.object();
            jSONStringer.key("Metadata").object().key("Created").value(muVar.e.d).key(VastTag.DESCRIPTION).value(muVar.e.b).key(e.g).value(muVar.e.c).endObject();
            jSONStringer.key("Pattern").array();
            Iterator<ms> it = muVar.c.iterator();
            while (it.hasNext()) {
                ms next = it.next();
                jSONStringer.object();
                jSONStringer.key(Event.TAG).object().key(FaqConstants.FAQ_UPLOAD_FLAG).value(next.d.f15123a).key("RelativeTime").value(next.d.b);
                if ("continuous".equals(next.d.f15123a)) {
                    jSONStringer.key(VastTag.DURATION).value(next.d.d);
                }
                jSONStringer.key("Parameters").object().key("Frequency").value(next.d.e.f15189a).key("Intensity").value(next.d.e.d);
                if ("continuous".equals(next.d.f15123a)) {
                    jSONStringer.key("Curve").array();
                    Iterator<mp> it2 = next.d.e.b.iterator();
                    while (it2.hasNext()) {
                        jSONStringer.object().key("Frequency").value(r5.b).key("Intensity").value(it2.next().f15090a).key("Time").value(r5.e).endObject();
                    }
                    jSONStringer.endArray();
                }
                jSONStringer.endObject().endObject().endObject();
            }
            jSONStringer.endArray().endObject();
            return jSONStringer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
