package defpackage;

import android.graphics.Bitmap;
import health.compact.a.CommonUtil;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class jdb {
    public byte[] bFn_(Bitmap bitmap) {
        byte[] bArr = null;
        if (bitmap == null) {
            return null;
        }
        ArrayList<Integer> bFl_ = bFl_(bitmap);
        if (bFl_ != null && bFl_.size() != 0) {
            bArr = new byte[bFl_.size()];
            for (int i = 0; i < bFl_.size(); i++) {
                bArr[i] = (byte) bFl_.get(i).intValue();
            }
        }
        return bArr;
    }

    private ArrayList<Integer> bFl_(Bitmap bitmap) {
        ArrayList<Integer> arrayList = new ArrayList<>(16);
        if (bitmap == null) {
            return arrayList;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        ArrayList<String> bFm_ = bFm_(width, height, bitmap);
        for (int i = 0; i < width; i++) {
            int i2 = height / 8;
            String a2 = a(bFm_, height, i);
            if (i2 == 0) {
                arrayList.add(Integer.valueOf(CommonUtil.a(a2, 2)));
            } else {
                int i3 = 0;
                while (i3 < i2) {
                    int i4 = i3 + 1;
                    arrayList.add(Integer.valueOf(CommonUtil.a(a2.substring(i3 * 8, i4 * 8), 2)));
                    i3 = i4;
                }
                if (height % 8 != 0) {
                    String substring = a2.substring(i2 * 8);
                    arrayList.add(Integer.valueOf(CommonUtil.a("00000000".substring(substring.length()) + substring, 2)));
                }
            }
        }
        return arrayList;
    }

    private ArrayList<String> bFm_(int i, int i2, Bitmap bitmap) {
        ArrayList<String> arrayList = new ArrayList<>(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            StringBuilder sb = new StringBuilder(16);
            for (int i4 = 0; i4 < i; i4++) {
                if (((short) (bitmap.getPixel(i4, i3) & 255)) <= 127) {
                    sb.append("0");
                } else {
                    sb.append("1");
                }
            }
            arrayList.add(sb.toString());
        }
        return arrayList;
    }

    private String a(ArrayList<String> arrayList, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i; i3++) {
            if (arrayList != null && arrayList.size() > i3) {
                String str = arrayList.get(i3);
                if (StringUtils.i(str) && str.length() > i2) {
                    sb.append(str.charAt(i2));
                }
            }
        }
        return sb.toString();
    }
}
