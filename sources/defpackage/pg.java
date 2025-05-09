package defpackage;

import com.github.promeg.pinyinhelper.PinyinDict;
import com.github.promeg.pinyinhelper.SegmentationSelector;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes2.dex */
final class pg {
    static final d d = new d();

    static String c(String str, uxc uxcVar, List<PinyinDict> list, String str2, SegmentationSelector segmentationSelector) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (uxcVar == null || segmentationSelector == null) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < str.length(); i++) {
                stringBuffer.append(pf.d(str.charAt(i)));
                if (i != str.length() - 1) {
                    stringBuffer.append(str2);
                }
            }
            return stringBuffer.toString();
        }
        List<uwy> select = segmentationSelector.select(uxcVar.c(str));
        Collections.sort(select, d);
        StringBuffer stringBuffer2 = new StringBuffer();
        int i2 = 0;
        int i3 = 0;
        while (i2 < str.length()) {
            if (i3 < select.size() && i2 == select.get(i3).getStart()) {
                String[] b = b(select.get(i3).c(), list);
                for (int i4 = 0; i4 < b.length; i4++) {
                    stringBuffer2.append(b[i4].toUpperCase());
                    if (i4 != b.length - 1) {
                        stringBuffer2.append(str2);
                    }
                }
                i2 += select.get(i3).size();
                i3++;
            } else {
                stringBuffer2.append(pf.d(str.charAt(i2)));
                i2++;
            }
            if (i2 != str.length()) {
                stringBuffer2.append(str2);
            }
        }
        return stringBuffer2.toString();
    }

    static String[] b(String str, List<PinyinDict> list) {
        if (list != null) {
            for (PinyinDict pinyinDict : list) {
                if (pinyinDict != null && pinyinDict.words() != null && pinyinDict.words().contains(str)) {
                    return pinyinDict.toPinyin(str);
                }
            }
        }
        throw new IllegalArgumentException("No pinyin dict contains word: " + str);
    }

    static final class d implements Comparator<uwy> {
        d() {
        }

        @Override // java.util.Comparator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public int compare(uwy uwyVar, uwy uwyVar2) {
            if (uwyVar.getStart() == uwyVar2.getStart()) {
                if (uwyVar.size() < uwyVar2.size()) {
                    return 1;
                }
                return uwyVar.size() == uwyVar2.size() ? 0 : -1;
            }
            if (uwyVar.getStart() < uwyVar2.getStart()) {
                return -1;
            }
            return uwyVar.getStart() == uwyVar2.getStart() ? 0 : 1;
        }
    }
}
