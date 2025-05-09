package com.huawei.openalliance.ad;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.huawei.openalliance.ad.inter.data.MaterialClickInfo;

/* loaded from: classes5.dex */
public class th extends tg {
    private static MaterialClickInfo c(View view, MotionEvent motionEvent) {
        float left = view.getLeft() + motionEvent.getX();
        float top = view.getTop() + motionEvent.getY();
        StringBuilder sb = new StringBuilder();
        ViewParent parent = view.getParent();
        for (int i = 0; i < 5 && parent != null; i++) {
            if (a(parent)) {
                ViewGroup viewGroup = (ViewGroup) parent;
                int width = viewGroup.getWidth();
                int height = viewGroup.getHeight();
                sb.append(width);
                sb.append("*");
                sb.append(height);
                return new MaterialClickInfo.a().a(Integer.valueOf((int) left)).b(Integer.valueOf((int) top)).b(sb.toString()).b(Long.valueOf(System.currentTimeMillis())).a();
            }
            if (parent instanceof ViewGroup) {
                ViewGroup viewGroup2 = (ViewGroup) parent;
                left += viewGroup2.getLeft();
                top += viewGroup2.getTop();
            }
            parent = parent.getParent();
        }
        return new MaterialClickInfo();
    }

    public static void b(View view, MotionEvent motionEvent, Integer num, MaterialClickInfo materialClickInfo) {
        a(view, motionEvent, num, materialClickInfo, true);
    }

    public static MaterialClickInfo b(View view, MotionEvent motionEvent) {
        if (view == null || motionEvent == null) {
            return new MaterialClickInfo();
        }
        StringBuilder sb = new StringBuilder();
        int width = view.getWidth();
        int height = view.getHeight();
        if (a(view)) {
            return c(view, motionEvent);
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        sb.append(width);
        sb.append("*");
        sb.append(height);
        return new MaterialClickInfo.a().a(Integer.valueOf((int) x)).b(Integer.valueOf((int) y)).b(sb.toString()).b(Long.valueOf(System.currentTimeMillis())).a();
    }

    private static void a(View view, MotionEvent motionEvent, Integer num, MaterialClickInfo materialClickInfo, boolean z) {
        if (materialClickInfo == null || view == null || motionEvent == null) {
            return;
        }
        materialClickInfo.a(Long.valueOf(System.currentTimeMillis()));
        materialClickInfo.a(Float.valueOf(com.huawei.openalliance.ad.utils.d.j(view.getContext())));
        if (num != null) {
            materialClickInfo.c(num);
        }
        if (materialClickInfo.d() == null) {
            materialClickInfo.c(0);
        }
        MaterialClickInfo b = z ? b(view, motionEvent) : a(view, motionEvent);
        if (b != null) {
            materialClickInfo.d(b.a());
            materialClickInfo.e(b.b());
        }
    }

    public static void a(View view, MotionEvent motionEvent, Integer num, MaterialClickInfo materialClickInfo) {
        a(view, motionEvent, num, materialClickInfo, false);
    }

    public static MaterialClickInfo a(View view, MotionEvent motionEvent) {
        if (view == null || motionEvent == null) {
            return new MaterialClickInfo();
        }
        StringBuilder sb = new StringBuilder();
        int width = view.getWidth();
        int height = view.getHeight();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        sb.append(width);
        sb.append("*");
        sb.append(height);
        return new MaterialClickInfo.a().a(Integer.valueOf((int) x)).b(Integer.valueOf((int) y)).b(sb.toString()).b(Long.valueOf(System.currentTimeMillis())).a();
    }

    public static int a(MotionEvent motionEvent) {
        return motionEvent.getAction() & 255;
    }
}
