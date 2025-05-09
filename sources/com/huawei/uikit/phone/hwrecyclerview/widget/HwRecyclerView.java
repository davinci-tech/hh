package com.huawei.uikit.phone.hwrecyclerview.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ReceiverCallNotAllowedException;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import com.huawei.health.R;
import com.huawei.operation.utils.Constants;
import com.huawei.uikit.phone.hwunifiedinteract.widget.HwCompoundEventDetector;
import com.huawei.uikit.phone.hwunifiedinteract.widget.HwGenericEventDetector;
import com.huawei.uikit.phone.hwunifiedinteract.widget.HwKeyEventDetector;

/* loaded from: classes7.dex */
public class HwRecyclerView extends com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView {

    /* renamed from: a, reason: collision with root package name */
    private Context f10787a;
    private boolean b;
    private boolean c;
    private IntentFilter e;
    private BroadcastReceiver f;

    class d extends BroadcastReceiver {
        d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && Constants.CLICK_STATUS_BAR_ACTION.equals(intent.getAction())) {
                HwRecyclerView.this.j();
            }
        }
    }

    public HwRecyclerView(Context context) {
        super(context);
        this.f = new d();
        ejj_(super.getContext(), null, R.attr._2131100481_res_0x7f060341);
    }

    private void e() {
        Context context;
        if (!this.b || (context = this.f10787a) == null) {
            return;
        }
        try {
            context.unregisterReceiver(this.f);
            this.b = false;
        } catch (IllegalArgumentException unused) {
            Log.w("HwRecyclerView", "Receiver not registered");
        }
    }

    private void ejj_(Context context, AttributeSet attributeSet, int i) {
        if (context.getApplicationContext() != null) {
            this.f10787a = context.getApplicationContext();
        } else {
            this.f10787a = context;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{android.R.attr.choiceMode, R.attr._2131100422_res_0x7f060306, R.attr._2131100423_res_0x7f060307, R.attr._2131100424_res_0x7f060308, R.attr._2131100478_res_0x7f06033e, R.attr._2131100479_res_0x7f06033f, R.attr._2131100480_res_0x7f060340, R.attr._2131100485_res_0x7f060345, R.attr._2131100520_res_0x7f060368}, i, R.style.Widget_Emui_HwRecyclerView);
        this.c = obtainStyledAttributes.getBoolean(7, true);
        obtainStyledAttributes.recycle();
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        d();
        if (this.b) {
            this.d.egr_(this);
        }
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        e();
    }

    @Override // com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView
    public void setScrollTopEnable(boolean z) {
        if (z != this.c) {
            this.c = z;
            if (!z) {
                e();
                this.d.d();
            } else {
                d();
                if (this.b) {
                    this.d.egr_(this);
                }
            }
        }
    }

    private void d() {
        if (!this.c || this.b || this.f10787a == null) {
            return;
        }
        if (this.e == null) {
            this.e = new IntentFilter(Constants.CLICK_STATUS_BAR_ACTION);
        }
        try {
            if (Build.VERSION.SDK_INT >= 34) {
                this.f10787a.registerReceiver(this.f, this.e, Constants.SYSTEM_UI_PERMISSION, null, 2);
            } else {
                this.f10787a.registerReceiver(this.f, this.e, Constants.SYSTEM_UI_PERMISSION, null);
            }
            this.b = true;
        } catch (ReceiverCallNotAllowedException unused) {
            Log.w("HwRecyclerView", "There is a problem with the APP application scenario:BroadcastReceiver components are not allowed to register to receive intents");
            this.b = false;
        } catch (IllegalStateException unused2) {
            Log.w("HwRecyclerView", "registerReceiver IllegalStateException");
            this.b = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView
    /* renamed from: p, reason: merged with bridge method [inline-methods] */
    public HwGenericEventDetector a() {
        return new HwGenericEventDetector(getContext());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView
    /* renamed from: q, reason: merged with bridge method [inline-methods] */
    public HwCompoundEventDetector c() {
        return new HwCompoundEventDetector(getContext());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.uikit.hwrecyclerview.widget.HwRecyclerView
    /* renamed from: s, reason: merged with bridge method [inline-methods] */
    public HwKeyEventDetector i() {
        return new HwKeyEventDetector(getContext());
    }

    public HwRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = new d();
        ejj_(super.getContext(), attributeSet, R.attr._2131100481_res_0x7f060341);
    }

    public HwRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = new d();
        ejj_(super.getContext(), attributeSet, i);
    }
}
