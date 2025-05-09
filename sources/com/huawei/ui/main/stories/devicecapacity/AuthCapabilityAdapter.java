package com.huawei.ui.main.stories.devicecapacity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.devicecapacity.AuthAdapter;
import com.huawei.wearengine.auth.AuthInfo;
import com.huawei.wearengine.auth.HiAppInfo;
import defpackage.koq;
import defpackage.pfo;
import defpackage.pfs;
import defpackage.pfw;
import java.util.List;

/* loaded from: classes9.dex */
public class AuthCapabilityAdapter extends RecyclerView.Adapter<AuthCapabilityViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private HiAppInfo f9697a;
    private List<pfo> b;
    private pfs c;
    private Context d;
    private AuthAdapter.OnClickCallback e;
    private String g;
    private boolean i;

    public static class AuthCapabilityViewHolder extends RecyclerView.ViewHolder {
        HealthTextView c;
        HealthTextView d;
        HealthCheckBox e;

        public AuthCapabilityViewHolder(View view) {
            super(view);
            this.d = (HealthTextView) view.findViewById(R.id.wear_engine_capability_name_id);
            this.c = (HealthTextView) view.findViewById(R.id.wear_engine_capability_describe_id);
            this.e = (HealthCheckBox) view.findViewById(R.id.wear_engine_capability_status_id);
        }
    }

    public AuthCapabilityAdapter(Context context, HiAppInfo hiAppInfo, List<pfo> list, AuthAdapter.OnClickCallback onClickCallback, pfs pfsVar) {
        this.d = context;
        this.f9697a = hiAppInfo;
        this.b = list;
        this.e = onClickCallback;
        this.c = pfsVar;
        b();
    }

    private void b() {
        for (pfo pfoVar : this.b) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(pfw.d(this.f9697a.getAppUid(), this.f9697a.getUserId(), this.f9697a.getAppId()));
            stringBuffer.append("_");
            stringBuffer.append(pfoVar.a());
            AuthInfo authInfo = new AuthInfo();
            authInfo.setKey(stringBuffer.toString());
            if (pfoVar.e()) {
                authInfo.setOpenStatus(1);
            } else {
                authInfo.setOpenStatus(0);
            }
            authInfo.setPermission(pfoVar.a());
            authInfo.setAppUid(this.f9697a.getAppUid());
            authInfo.setAppId(this.f9697a.getAppId());
            authInfo.setUserId(this.f9697a.getUserId());
            LogUtil.c("AuthCapabilityAdapter", "storeAuthInfoToMap putAuthInfoToMap key:" + stringBuffer.toString());
            LogUtil.c("AuthCapabilityAdapter", "storeAuthInfoToMap putAuthInfoToMap authInfo:" + authInfo.toString());
            this.c.b(stringBuffer.toString(), authInfo);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: doy_, reason: merged with bridge method [inline-methods] */
    public AuthCapabilityViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LogUtil.c("AuthCapabilityAdapter", "onCreateViewHolder");
        if (viewGroup == null) {
            LogUtil.b("AuthCapabilityAdapter", "onCreateViewHolder parent is null");
            return null;
        }
        if (this.d == null) {
            this.d = viewGroup.getContext();
        }
        return new AuthCapabilityViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wear_engine_third_part_auth_capability_item_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(AuthCapabilityViewHolder authCapabilityViewHolder, int i) {
        if (koq.b(this.b, i)) {
            LogUtil.b("AuthCapabilityAdapter", "onBindViewHolder isOutOfBounds");
            return;
        }
        final pfo pfoVar = this.b.get(i);
        authCapabilityViewHolder.d.setText(pfoVar.a(this.i));
        authCapabilityViewHolder.c.setText(pfoVar.a(this.g, this.i));
        authCapabilityViewHolder.e.setChecked(pfoVar.e());
        authCapabilityViewHolder.e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.devicecapacity.AuthCapabilityAdapter.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(pfw.d(AuthCapabilityAdapter.this.f9697a.getAppUid(), AuthCapabilityAdapter.this.f9697a.getUserId(), AuthCapabilityAdapter.this.f9697a.getAppId()));
                stringBuffer.append("_");
                stringBuffer.append(pfoVar.a());
                AuthInfo a2 = AuthCapabilityAdapter.this.c.a(stringBuffer.toString());
                if (a2 == null) {
                    ViewClickInstrumentation.clickOnView(compoundButton);
                    return;
                }
                if (z) {
                    a2.setOpenStatus(1);
                    AuthCapabilityAdapter.this.e.onClickCallback(pfoVar.a(), true);
                } else {
                    a2.setOpenStatus(0);
                    AuthCapabilityAdapter.this.e.onClickCallback(pfoVar.a(), false);
                }
                LogUtil.c("AuthCapabilityAdapter", "OnCheckedChangeListener putAuthInfoToMap key:" + stringBuffer.toString());
                LogUtil.c("AuthCapabilityAdapter", "OnCheckedChangeListener putAuthInfoToMap authInfo:" + a2.toString());
                AuthCapabilityAdapter.this.c.b(stringBuffer.toString(), a2);
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<pfo> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void b(String str) {
        this.g = str;
    }

    public void e(boolean z) {
        this.i = z;
    }
}
