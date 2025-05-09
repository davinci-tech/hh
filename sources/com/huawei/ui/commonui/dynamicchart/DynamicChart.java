package com.huawei.ui.commonui.dynamicchart;

import android.content.Context;
import android.os.Message;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.ui.commonui.dynamicchart.utils.DynamicChartResponse;
import defpackage.nma;
import defpackage.nme;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class DynamicChart extends SurfaceView {
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private int f8827a;
    private nma b;
    private int d;
    private List<DynamicChartResponse> e;
    private SurfaceHolder f;
    private SurfaceHolder.Callback g;
    private int[] i;

    public DynamicChart(Context context) {
        super(context);
        this.e = new ArrayList(16);
        this.d = -1;
        this.i = new int[]{40, HeartRateThresholdConfig.HEART_RATE_LIMIT};
        this.g = new SurfaceHolder.Callback() { // from class: com.huawei.ui.commonui.dynamicchart.DynamicChart.3
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                synchronized (DynamicChart.c) {
                    DynamicChart.this.b = nma.a();
                    DynamicChart.this.b.b(DynamicChart.this.getContext(), DynamicChart.this);
                }
                DynamicChart.this.a();
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, final int i2, final int i3) {
                DynamicChart.this.b(new DynamicChartResponse() { // from class: com.huawei.ui.commonui.dynamicchart.DynamicChart.3.1
                    @Override // com.huawei.ui.commonui.dynamicchart.utils.DynamicChartResponse
                    public void exec(nma nmaVar) {
                        nmaVar.obtainMessage(1, Integer.valueOf(DynamicChart.this.d)).sendToTarget();
                        nmaVar.obtainMessage(4, DynamicChart.this.i).sendToTarget();
                        nmaVar.b(DynamicChart.this.f8827a);
                        Message obtainMessage = nmaVar.obtainMessage(2);
                        obtainMessage.arg1 = i2;
                        obtainMessage.arg2 = i3;
                        obtainMessage.sendToTarget();
                    }
                });
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                synchronized (DynamicChart.c) {
                    DynamicChart.this.b.d();
                    DynamicChart.this.b = null;
                }
            }
        };
        d();
    }

    public DynamicChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new ArrayList(16);
        this.d = -1;
        this.i = new int[]{40, HeartRateThresholdConfig.HEART_RATE_LIMIT};
        this.g = new SurfaceHolder.Callback() { // from class: com.huawei.ui.commonui.dynamicchart.DynamicChart.3
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                synchronized (DynamicChart.c) {
                    DynamicChart.this.b = nma.a();
                    DynamicChart.this.b.b(DynamicChart.this.getContext(), DynamicChart.this);
                }
                DynamicChart.this.a();
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, final int i2, final int i3) {
                DynamicChart.this.b(new DynamicChartResponse() { // from class: com.huawei.ui.commonui.dynamicchart.DynamicChart.3.1
                    @Override // com.huawei.ui.commonui.dynamicchart.utils.DynamicChartResponse
                    public void exec(nma nmaVar) {
                        nmaVar.obtainMessage(1, Integer.valueOf(DynamicChart.this.d)).sendToTarget();
                        nmaVar.obtainMessage(4, DynamicChart.this.i).sendToTarget();
                        nmaVar.b(DynamicChart.this.f8827a);
                        Message obtainMessage = nmaVar.obtainMessage(2);
                        obtainMessage.arg1 = i2;
                        obtainMessage.arg2 = i3;
                        obtainMessage.sendToTarget();
                    }
                });
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                synchronized (DynamicChart.c) {
                    DynamicChart.this.b.d();
                    DynamicChart.this.b = null;
                }
            }
        };
        d();
    }

    public DynamicChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new ArrayList(16);
        this.d = -1;
        this.i = new int[]{40, HeartRateThresholdConfig.HEART_RATE_LIMIT};
        this.g = new SurfaceHolder.Callback() { // from class: com.huawei.ui.commonui.dynamicchart.DynamicChart.3
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                synchronized (DynamicChart.c) {
                    DynamicChart.this.b = nma.a();
                    DynamicChart.this.b.b(DynamicChart.this.getContext(), DynamicChart.this);
                }
                DynamicChart.this.a();
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, final int i22, final int i3) {
                DynamicChart.this.b(new DynamicChartResponse() { // from class: com.huawei.ui.commonui.dynamicchart.DynamicChart.3.1
                    @Override // com.huawei.ui.commonui.dynamicchart.utils.DynamicChartResponse
                    public void exec(nma nmaVar) {
                        nmaVar.obtainMessage(1, Integer.valueOf(DynamicChart.this.d)).sendToTarget();
                        nmaVar.obtainMessage(4, DynamicChart.this.i).sendToTarget();
                        nmaVar.b(DynamicChart.this.f8827a);
                        Message obtainMessage = nmaVar.obtainMessage(2);
                        obtainMessage.arg1 = i22;
                        obtainMessage.arg2 = i3;
                        obtainMessage.sendToTarget();
                    }
                });
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                synchronized (DynamicChart.c) {
                    DynamicChart.this.b.d();
                    DynamicChart.this.b = null;
                }
            }
        };
        d();
    }

    private void d() {
        nme.d(getContext());
        SurfaceHolder holder = getHolder();
        this.f = holder;
        holder.addCallback(this.g);
        setZOrderOnTop(true);
        this.f.setFormat(-3);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DynamicChartResponse dynamicChartResponse) {
        synchronized (c) {
            this.e.add(dynamicChartResponse);
        }
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        synchronized (c) {
            if (this.b == null) {
                return;
            }
            Iterator<DynamicChartResponse> it = this.e.iterator();
            while (it.hasNext()) {
                it.next().exec(this.b);
            }
            this.e.clear();
        }
    }

    public void setPointCountOneScreen(int i) {
        this.d = i;
    }

    public void setColor(int i) {
        this.f8827a = i;
    }

    public void setOrdinateY(int i, int i2) {
        int[] iArr = this.i;
        iArr[0] = i;
        iArr[1] = i2;
    }

    public void a(final float f) {
        b(new DynamicChartResponse() { // from class: com.huawei.ui.commonui.dynamicchart.DynamicChart.1
            @Override // com.huawei.ui.commonui.dynamicchart.utils.DynamicChartResponse
            public void exec(nma nmaVar) {
                nmaVar.obtainMessage(3, Float.valueOf(f)).sendToTarget();
            }
        });
    }
}
