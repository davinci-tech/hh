package com.huawei.featureuserprofile.sos.adapter;

import android.content.Context;
import android.widget.BaseAdapter;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;

/* loaded from: classes7.dex */
public class EmergencyListAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f2025a;
    private int d;
    private CharSequence[] e;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public EmergencyListAdapter(Context context, CharSequence[] charSequenceArr, int i) {
        this.e = charSequenceArr == null ? new CharSequence[0] : charSequenceArr;
        this.f2025a = context;
        this.d = i;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.e.length;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0059  */
    @Override // android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View getView(int r4, android.view.View r5, android.view.ViewGroup r6) {
        /*
            r3 = this;
            r6 = 0
            if (r5 != 0) goto L2b
            com.huawei.featureuserprofile.sos.adapter.EmergencyListAdapter$b r5 = new com.huawei.featureuserprofile.sos.adapter.EmergencyListAdapter$b
            r5.<init>()
            android.content.Context r0 = r3.f2025a
            r1 = 2131166908(0x7f0706bc, float:1.7948075E38)
            android.view.View r6 = android.view.View.inflate(r0, r1, r6)
            r0 = 2131572414(0x7f0d36be, float:1.8770539E38)
            android.view.View r0 = r6.findViewById(r0)
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = (com.huawei.ui.commonui.healthtextview.HealthTextView) r0
            r5.e = r0
            r0 = 2131567928(0x7f0d2538, float:1.876144E38)
            android.view.View r0 = r6.findViewById(r0)
            com.huawei.ui.commonui.radiobutton.HealthRadioButton r0 = (com.huawei.ui.commonui.radiobutton.HealthRadioButton) r0
            r5.d = r0
            r6.setTag(r5)
            goto L3c
        L2b:
            java.lang.Object r0 = r5.getTag()
            boolean r1 = r0 instanceof com.huawei.featureuserprofile.sos.adapter.EmergencyListAdapter.b
            if (r1 == 0) goto L36
            r6 = r0
            com.huawei.featureuserprofile.sos.adapter.EmergencyListAdapter$b r6 = (com.huawei.featureuserprofile.sos.adapter.EmergencyListAdapter.b) r6
        L36:
            if (r6 != 0) goto L39
            return r5
        L39:
            r2 = r6
            r6 = r5
            r5 = r2
        L3c:
            if (r4 < 0) goto L4a
            java.lang.CharSequence[] r0 = r3.e
            int r1 = r0.length
            if (r4 >= r1) goto L4a
            r0 = r0[r4]
            java.lang.String r0 = java.lang.String.valueOf(r0)
            goto L4c
        L4a:
            java.lang.String r0 = ""
        L4c:
            com.huawei.ui.commonui.healthtextview.HealthTextView r1 = r5.e
            r1.setText(r0)
            com.huawei.ui.commonui.radiobutton.HealthRadioButton r5 = r5.d
            int r0 = r3.d
            if (r0 != r4) goto L59
            r4 = 1
            goto L5a
        L59:
            r4 = 0
        L5a:
            r5.setChecked(r4)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.featureuserprofile.sos.adapter.EmergencyListAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public void c(int i) {
        this.d = i;
        notifyDataSetChanged();
    }

    static class b {
        HealthRadioButton d;
        HealthTextView e;

        b() {
        }
    }
}
