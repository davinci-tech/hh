package com.huawei.hihealth.dictionary.expression;

import com.huawei.hihealth.HiDataFilter;
import com.huawei.hihealth.dictionary.utils.DictUtils;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import org.slf4j.Marker;

/* loaded from: classes.dex */
public class Pattern {

    /* renamed from: a, reason: collision with root package name */
    private static final java.util.regex.Pattern f4124a;
    private static final Map<String, Integer> d;

    private static boolean c(char c) {
        return c >= '0' && c <= '9';
    }

    static {
        HashMap hashMap = new HashMap(13);
        d = hashMap;
        f4124a = java.util.regex.Pattern.compile("^[-\\+]?[.\\d]*$");
        hashMap.put(Constants.LEFT_BRACKET_ONLY, Integer.MAX_VALUE);
        hashMap.put(Constants.RIGHT_BRACKET_ONLY, Integer.MAX_VALUE);
        hashMap.put(Marker.ANY_NON_NULL_MARKER, 4);
        hashMap.put(com.huawei.openalliance.ad.constant.Constants.LINK, 4);
        hashMap.put("*", 5);
        hashMap.put("/", 5);
        hashMap.put(HiDataFilter.DataFilterExpression.BIGGER_THAN, 3);
        hashMap.put(HiDataFilter.DataFilterExpression.LESS_THAN, 3);
        hashMap.put(HiDataFilter.DataFilterExpression.BIGGER_EQUAL, 3);
        hashMap.put(HiDataFilter.DataFilterExpression.LESS_EQUAL, 3);
        hashMap.put("&&", 1);
        hashMap.put("&", 4);
        hashMap.put("||", 0);
        hashMap.put("|", 4);
        hashMap.put("=", 2);
    }

    public static Expression d(String str) {
        if (str == null || "".equals(str.trim())) {
            throw new IllegalArgumentException("data is empty");
        }
        String h = h(str);
        Stack stack = new Stack();
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        boolean z = false;
        int i = 0;
        for (int i2 = 0; i2 < h.length(); i2++) {
            if (z) {
                z = false;
            } else {
                String str2 = h.charAt(i2) + "";
                if (e(str2, h, i2)) {
                    i = i2;
                } else if (a(str2)) {
                    z = c(h, str2, i2, arrayList, stack);
                    i = z ? i2 + 2 : i2 + 1;
                } else {
                    if (i2 != h.length() - 1) {
                        if (!a(h.charAt(i2 + 1) + "")) {
                        }
                    }
                    e(h, i, i2 + 1, arrayList, hashSet);
                }
            }
        }
        while (!stack.isEmpty()) {
            arrayList.add((Token) stack.pop());
        }
        return new Expression(arrayList, hashSet);
    }

    private static boolean c(String str, String str2, int i, List<Token> list, Stack<Token> stack) {
        String str3;
        boolean z;
        boolean z2 = true;
        if ((HiDataFilter.DataFilterExpression.LESS_THAN.equals(str2) || HiDataFilter.DataFilterExpression.BIGGER_THAN.equals(str2)) && i < str.length() - 1 && "=".charAt(0) == str.charAt(i + 1)) {
            str3 = str2 + "=";
            z = true;
        } else {
            str3 = str2;
            z = false;
        }
        if (!"&".equals(str2) && !"|".equals(str2)) {
            z2 = z;
        } else {
            if (i == str.length() - 1) {
                throw new IllegalArgumentException("illegal expression: " + str);
            }
            if (!str3.equals(str.charAt(i + 1) + "")) {
                throw new IllegalArgumentException("illegal expression: " + str);
            }
            str3 = str2 + str2;
        }
        if (Constants.RIGHT_BRACKET_ONLY.equals(str3)) {
            while (!stack.isEmpty()) {
                if (Constants.LEFT_BRACKET_ONLY.equals(stack.peek().d())) {
                    stack.pop();
                    return z2;
                }
                list.add(stack.pop());
            }
            throw new IllegalArgumentException("There is no left bracket for right bracket.");
        }
        if (stack.isEmpty() || Constants.LEFT_BRACKET_ONLY.equals(str3) || c(str3) > c(stack.peek().d())) {
            stack.push(new Token(0, str3));
            return z2;
        }
        if (c(str3) <= c(stack.peek().d())) {
            while (!stack.isEmpty() && c(str3) <= c(stack.peek().d()) && c(stack.peek().d()) != Integer.MAX_VALUE) {
                list.add(stack.pop());
            }
            stack.push(new Token(0, str3));
        }
        return z2;
    }

    private static void e(String str, int i, int i2, List<Token> list, Set<String> set) {
        String substring = str.substring(i, i2);
        if (b(substring) && !e(substring)) {
            set.add(substring);
            list.add(new Token(1, substring));
        } else {
            if (e(substring)) {
                list.add(new Token(2, substring));
                return;
            }
            throw new IllegalArgumentException("data not match number or param");
        }
    }

    private static String h(String str) {
        return str.replaceAll("\\s+", "");
    }

    private static boolean e(String str) {
        return f4124a.matcher(str).matches();
    }

    private static boolean b(String str) {
        return DictUtils.a(str);
    }

    private static boolean a(String str) {
        return d.containsKey(str);
    }

    private static boolean e(String str, String str2, int i) {
        if (!str.equals(Marker.ANY_NON_NULL_MARKER) && !str.equals(com.huawei.openalliance.ad.constant.Constants.LINK)) {
            return false;
        }
        if (str2.length() <= 1 || i == str2.length() - 1) {
            throw new IllegalArgumentException("expressiong is illegal. : " + str2);
        }
        if (i == 0) {
            return c(str2.charAt(i + 1));
        }
        char charAt = str2.charAt(i - 1);
        if (!c(str2.charAt(i + 1)) || Constants.RIGHT_BRACKET_ONLY.charAt(0) == charAt) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(charAt);
        sb.append("");
        return a(sb.toString());
    }

    private static int c(String str) {
        Integer num = d.get(str);
        if (num == null) {
            return Integer.MAX_VALUE;
        }
        return num.intValue();
    }
}
