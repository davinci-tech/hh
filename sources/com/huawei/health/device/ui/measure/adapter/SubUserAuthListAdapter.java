package com.huawei.health.device.ui.measure.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceSubUserAuthMsg;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.koq;
import defpackage.nmn;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class SubUserAuthListAdapter extends BaseAdapter {
    private AuthButtonClickCallback b;
    private Context d;
    private List<WifiDeviceSubUserAuthMsg> e;

    public interface AuthButtonClickCallback {
        void onAuthButtonClick(WifiDeviceSubUserAuthMsg wifiDeviceSubUserAuthMsg, boolean z);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public SubUserAuthListAdapter(Context context, List<WifiDeviceSubUserAuthMsg> list, AuthButtonClickCallback authButtonClickCallback) {
        this.d = context;
        this.e = list;
        this.b = authButtonClickCallback;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<WifiDeviceSubUserAuthMsg> list = this.e;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (koq.d(this.e, i)) {
            return this.e.get(i);
        }
        LogUtil.h("SubUserAuthListAdapter", "item is null ", Integer.valueOf(i));
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        c cVar = null;
        Object[] objArr = 0;
        if (view == null) {
            c cVar2 = new c();
            View inflate = LayoutInflater.from(this.d).inflate(R.layout.sub_user_auth_list_item, (ViewGroup) null);
            cVar2.b = (ImageView) inflate.findViewById(R.id.image_head);
            cVar2.d = (HealthTextView) inflate.findViewById(R.id.text_message);
            cVar2.f2250a = (HealthButton) inflate.findViewById(R.id.btn_agree);
            cVar2.e = (HealthButton) inflate.findViewById(R.id.btn_reject);
            cVar2.c = (HealthTextView) inflate.findViewById(R.id.text_status);
            inflate.setTag(cVar2);
            cVar = cVar2;
            view = inflate;
        } else if (view.getTag() instanceof c) {
            cVar = (c) view.getTag();
        }
        if (cVar != null) {
            if (koq.d(this.e, i)) {
                a(cVar, this.e.get(i));
            } else {
                LogUtil.h("SubUserAuthListAdapter", "getView item is null ", Integer.valueOf(i));
            }
        }
        return view;
    }

    private void a(c cVar, WifiDeviceSubUserAuthMsg wifiDeviceSubUserAuthMsg) {
        cVar.b.setImageDrawable(nmn.cBh_(this.d.getResources(), new nmn.c(null, wifiDeviceSubUserAuthMsg.getSubHuid(), true)));
        cVar.d.setText(wifiDeviceSubUserAuthMsg.getSubUserNickName());
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            cVar.d.setTextDirection(4);
        }
        if (wifiDeviceSubUserAuthMsg.getStatus() == 1) {
            cVar.f2250a.setVisibility(0);
            cVar.e.setVisibility(0);
            cVar.c.setVisibility(8);
        } else {
            cVar.f2250a.setVisibility(8);
            cVar.e.setVisibility(8);
            cVar.c.setVisibility(0);
        }
        int status = wifiDeviceSubUserAuthMsg.getStatus();
        if (status == 1) {
            cVar.f2250a.setOnClickListener(new d(wifiDeviceSubUserAuthMsg, true));
            cVar.e.setOnClickListener(new d(wifiDeviceSubUserAuthMsg, false));
        } else {
            if (status == 2) {
                cVar.c.setText(R.string.IDS_hw_device_wifi_authorized_status_authorized);
                return;
            }
            if (status == 3) {
                cVar.c.setText(R.string.IDS_hw_device_wifi_authorized_status_rejected);
            } else if (status == 4) {
                cVar.c.setText(R.string.IDS_hw_device_wifi_authorized_status_expired);
            } else {
                LogUtil.h("SubUserAuthListAdapter", "status error: ", Integer.valueOf(wifiDeviceSubUserAuthMsg.getStatus()));
            }
        }
    }

    static class c {

        /* renamed from: a, reason: collision with root package name */
        HealthButton f2250a;
        ImageView b;
        HealthTextView c;
        HealthTextView d;
        HealthButton e;

        private c() {
        }
    }

    public class d implements View.OnClickListener {
        private WifiDeviceSubUserAuthMsg c;
        private boolean d;

        public d(WifiDeviceSubUserAuthMsg wifiDeviceSubUserAuthMsg, boolean z) {
            this.c = wifiDeviceSubUserAuthMsg;
            this.d = z;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SubUserAuthListAdapter.this.b.onAuthButtonClick(this.c, this.d);
            ViewClickInstrumentation.clickOnView(view);
        }
    }
}
