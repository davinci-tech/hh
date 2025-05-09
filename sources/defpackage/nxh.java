package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class nxh {
    private static volatile nxh d;

    private nxh() {
    }

    public static nxh c() {
        if (d == null) {
            synchronized (nxh.class) {
                if (d == null) {
                    d = new nxh();
                }
            }
        }
        return d;
    }

    public AnimationDrawable cRK_(Context context, ImageView imageView, int i, int i2) {
        AnimationDrawable animationDrawable = new AnimationDrawable();
        int[] c = c(context, i);
        if (c.length == 0) {
            LogUtil.h("FrameAnimationUtils", "data is zero");
            return null;
        }
        for (int i3 : c) {
            animationDrawable.addFrame(context.getDrawable(i3), i2);
        }
        imageView.setImageDrawable(animationDrawable);
        animationDrawable.setOneShot(false);
        return animationDrawable;
    }

    private int[] c(Context context, int i) {
        TypedArray obtainTypedArray = context.getResources().obtainTypedArray(i);
        if (obtainTypedArray == null) {
            return new int[0];
        }
        if (obtainTypedArray.length() == 0) {
            obtainTypedArray.recycle();
            return new int[obtainTypedArray.length()];
        }
        int length = obtainTypedArray.length();
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = obtainTypedArray.getResourceId(i2, 0);
        }
        obtainTypedArray.recycle();
        return iArr;
    }
}
