package com.huawei.openalliance.ad;

import android.view.MotionEvent;
import android.view.View;
import com.huawei.openalliance.ad.views.PPSRewardView;

/* loaded from: classes5.dex */
public class ug implements View.OnTouchListener {

    /* renamed from: a, reason: collision with root package name */
    float f7554a;
    float b;
    float c;
    float d;
    private final PPSRewardView e;

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int a2 = a(motionEvent);
        if (a2 == 0) {
            this.f7554a = motionEvent.getX();
            this.b = motionEvent.getY();
        }
        if (a2 != 1) {
            return false;
        }
        this.c = Math.abs(motionEvent.getX() - this.f7554a);
        float abs = Math.abs(motionEvent.getY() - this.b);
        this.d = abs;
        if (this.c >= 30.0f || abs >= 30.0f) {
            return false;
        }
        ho.b("RewardContentViewOTL", "click action");
        if (this.e.r() || !this.e.j()) {
            return false;
        }
        this.e.k();
        return false;
    }

    private int a(MotionEvent motionEvent) {
        return motionEvent.getAction() & 255;
    }

    public ug(PPSRewardView pPSRewardView) {
        this.e = pPSRewardView;
    }
}
