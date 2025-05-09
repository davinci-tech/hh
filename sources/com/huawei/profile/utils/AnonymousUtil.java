package com.huawei.profile.utils;

import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class AnonymousUtil {
    private static final String EMPTY_STR = "";
    private static final int END_NUM = 5;
    private static final int FRONT_NUM = 5;
    private static final int INVALID_INDEX = -1;
    private static final char[] PUNCTUATIONS = {',', '=', '\"', ';', '{', '}', ' '};

    public static String anonymousContent(String str) {
        int length;
        if (str == null || (length = str.length()) <= 10) {
            return str;
        }
        return str.substring(0, 5) + Constants.CONFUSION_CHARS + str.substring(length - 5);
    }

    public static String anonymousJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder(charArray.length);
        int i = -1;
        for (int i2 = 0; i2 < charArray.length; i2++) {
            if (isPunctuation(charArray[i2])) {
                if (i != -1) {
                    sb.append(anonymousContent(str.substring(i, i2)));
                    i = -1;
                }
                sb.append(charArray[i2]);
            } else if (i == -1) {
                i = i2;
            }
        }
        if (i != -1) {
            sb.append(anonymousContent(str.substring(i)));
        }
        return sb.toString();
    }

    private static boolean isPunctuation(char c) {
        for (char c2 : PUNCTUATIONS) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    public static String anonymousList(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(anonymousContent(it.next()));
        }
        return arrayList.toString();
    }
}
