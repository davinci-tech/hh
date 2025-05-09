package com.huawei.watchface;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSleepType;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class ay extends aw {
    private Bitmap l;
    private Canvas m;

    public ay(Bitmap bitmap) {
        super(bitmap);
        this.i = FitnessSleepType.HW_FITNESS_WAKE;
        this.l = Bitmap.createBitmap(this.i, this.i, Bitmap.Config.ARGB_8888);
        this.m = new Canvas(this.l);
    }

    @Override // com.huawei.watchface.aw
    public void a(Point point) {
        if (point == null) {
            HwLog.e("RadialKaleidoscopeView", "trajectoryPoint is null.");
        } else {
            super.a(point);
            a(this.e, this.m, this.l, this.d, this.c);
        }
    }
}
