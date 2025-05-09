package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes9.dex */
public class ai extends b {
    private final int y;
    private SurfaceHolder.Callback z;

    @Override // com.huawei.openalliance.ad.views.b
    protected String getLogTag() {
        return "SurfaceVideoView" + this.y;
    }

    private void b(Context context) {
        LayoutInflater.from(context).inflate(R.layout.hiad_surfaceview_video, this);
        ((SurfaceView) findViewById(R.id.hiad_id_video_surface_view)).getHolder().addCallback(this.z);
    }

    public ai(Context context) {
        super(context);
        this.y = hashCode();
        this.z = new SurfaceHolder.Callback() { // from class: com.huawei.openalliance.ad.views.ai.1
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                ai.this.e();
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                ho.b(ai.this.getLogTag(), "surfaceCreated");
                ai.this.a(surfaceHolder.getSurface());
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                ai.this.b(i2, i3);
            }
        };
        b(context);
    }
}
