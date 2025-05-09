package com.huawei.openalliance.ad;

import android.text.TextUtils;
import com.google.flatbuffers.ByteVector;
import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.IntVector;
import com.google.flatbuffers.StringVector;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class ec {
    public static int[] a(int[] iArr) {
        return iArr == null ? new int[0] : iArr;
    }

    public static int[] a(List<String> list, FlatBufferBuilder flatBufferBuilder) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            iArr = null;
        } else {
            int size = list.size();
            iArr = new int[size];
            for (int i = 0; i < size; i++) {
                iArr[i] = flatBufferBuilder.createString(a(list.get(i)));
            }
        }
        return a(iArr);
    }

    public static int[] a(List<Integer> list) {
        int[] iArr;
        if (com.huawei.openalliance.ad.utils.bg.a(list)) {
            iArr = null;
        } else {
            int size = list.size();
            iArr = new int[size];
            for (int i = 0; i < size; i++) {
                Integer num = list.get(i);
                iArr[i] = num == null ? 0 : num.intValue();
            }
        }
        return a(iArr);
    }

    public static byte[] a(ByteVector byteVector) {
        if (byteVector == null) {
            return null;
        }
        byte[] bArr = new byte[byteVector.length()];
        for (int i = 0; i < byteVector.length(); i++) {
            bArr[i] = byteVector.get(i);
        }
        return bArr;
    }

    public static List<String> a(StringVector stringVector) {
        if (stringVector == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(stringVector.length());
        for (int i = 0; i < stringVector.length(); i++) {
            arrayList.add(stringVector.get(i));
        }
        return arrayList;
    }

    public static List<Integer> a(IntVector intVector) {
        if (intVector == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(intVector.length());
        for (int i = 0; i < intVector.length(); i++) {
            arrayList.add(Integer.valueOf(intVector.get(i)));
        }
        return arrayList;
    }

    public static String a(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }
}
