package com.huawei.ui.main.stories.devicecapacity;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.devicecapacity.AuthActivity;
import com.huawei.wearengine.auth.AuthInfo;
import com.huawei.wearengine.auth.HiAppInfo;
import defpackage.koq;
import defpackage.pfo;
import defpackage.pfq;
import defpackage.pfs;
import defpackage.pfw;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public class AuthAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private HiAppInfo f9694a;
    private AllAuthViewHolder b;
    private AuthActivity.AuthChangeListener c;
    private List<pfq> d;
    private pfs e;
    private List<pfo> f;
    private Map<String, Boolean> g = new ConcurrentHashMap();
    private List<Boolean> h;
    private String i;
    private Context j;
    private boolean k;
    private String m;

    public interface OnClickCallback {
        void onClickCallback(String str, boolean z);
    }

    public AuthAdapter(Context context) {
        this.j = context;
    }

    public static class IconViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f9696a;
        ImageView b;
        HealthTextView d;

        public IconViewHolder(View view) {
            super(view);
            this.b = (ImageView) view.findViewById(R.id.wear_engine_third_party_auth_app_icon_id);
            this.f9696a = (HealthTextView) view.findViewById(R.id.wear_engine_third_party_auth_app_name_id);
            this.d = (HealthTextView) view.findViewById(R.id.wear_engine_third_party_auth_describe_id);
        }
    }

    public static class AllAuthViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthCheckBox f9695a;
        RelativeLayout e;

        public AllAuthViewHolder(View view) {
            super(view);
            this.f9695a = (HealthCheckBox) view.findViewById(R.id.wear_engine_capability_all_auth_switch_id);
            this.e = (RelativeLayout) view.findViewById(R.id.wear_engine_capability_all_auth_id);
        }
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        HealthRecycleView d;
        View e;

        public ContentViewHolder(View view) {
            super(view);
            HealthRecycleView healthRecycleView = (HealthRecycleView) view.findViewById(R.id.wear_engine_content_recycler_view_id);
            this.d = healthRecycleView;
            healthRecycleView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            this.d.a(false);
            this.d.d(false);
            this.e = view.findViewById(R.id.wear_engine_content_dividing_line);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new IconViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wear_engine_third_part_auth_top_icon_layout, viewGroup, false));
        }
        if (i == 1) {
            return new AllAuthViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wear_engine_third_part_auth_capability_all_layout, viewGroup, false));
        }
        return new ContentViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wear_engine_third_part_auth_content_layout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder == null) {
            LogUtil.b("AuthAdapter", "viewHolder == null");
            return;
        }
        if (viewHolder instanceof IconViewHolder) {
            d((IconViewHolder) viewHolder);
            return;
        }
        if ((viewHolder instanceof AllAuthViewHolder) && i < this.d.size()) {
            this.b = (AllAuthViewHolder) viewHolder;
            c(this.d.get(i));
            return;
        }
        if (viewHolder instanceof ContentViewHolder) {
            ContentViewHolder contentViewHolder = (ContentViewHolder) viewHolder;
            List<pfo> d = this.d.get(i).d();
            this.f = d;
            if (d != null && d.size() <= 1) {
                this.b.e.setVisibility(8);
                contentViewHolder.e.setVisibility(8);
            }
            List<pfo> list = this.f;
            if (list != null) {
                e(list);
            }
            e(contentViewHolder);
            return;
        }
        LogUtil.h("AuthAdapter", "invalid viewHolder");
    }

    private void d(IconViewHolder iconViewHolder) {
        iconViewHolder.b.setImageBitmap(pfw.doA_(this.f9694a.getByteDraw()));
        iconViewHolder.f9696a.setText(this.f9694a.getAppName());
        if ("health_app".equals(this.i)) {
            iconViewHolder.d.setText(String.format(Locale.ROOT, this.j.getResources().getString(R$string.IDS_permission_wear_engine_describe_from_health), this.f9694a.getAppName()));
        } else if (this.k) {
            iconViewHolder.d.setText(String.format(Locale.ROOT, this.j.getResources().getString(R$string.IDS_wear_engine_third_describe), this.f9694a.getAppName()));
        } else {
            iconViewHolder.d.setText(String.format(Locale.ROOT, this.j.getResources().getString(R$string.IDS_wear_third_desc), this.f9694a.getAppName()));
        }
    }

    private void c(final pfq pfqVar) {
        this.b.f9695a.setChecked(pfqVar.e());
        this.b.f9695a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.devicecapacity.AuthAdapter.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (!compoundButton.isPressed()) {
                    ViewClickInstrumentation.clickOnView(compoundButton);
                    return;
                }
                LogUtil.c("AuthAdapter", "onCheckedChanged onClick");
                AuthAdapter.this.a(pfqVar, z);
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
    }

    private void e(List<pfo> list) {
        for (pfo pfoVar : list) {
            this.g.put(pfoVar.a(), Boolean.valueOf(pfoVar.e()));
        }
    }

    private void e(ContentViewHolder contentViewHolder) {
        AuthCapabilityAdapter authCapabilityAdapter = new AuthCapabilityAdapter(this.j, this.f9694a, this.f, new OnClickCallback() { // from class: com.huawei.ui.main.stories.devicecapacity.AuthAdapter.5
            @Override // com.huawei.ui.main.stories.devicecapacity.AuthAdapter.OnClickCallback
            public void onClickCallback(String str, boolean z) {
                AuthAdapter.this.g.put(str, Boolean.valueOf(z));
                LogUtil.c("AuthAdapter", "OnClickCallback mAuthStatusMap:" + AuthAdapter.this.g.toString());
                AuthAdapter.this.b(str, z);
                if (!AuthAdapter.this.c()) {
                    AuthAdapter.this.b(false);
                } else {
                    AuthAdapter.this.b(true);
                }
                AuthAdapter.this.b();
                boolean a2 = AuthAdapter.this.a();
                if (AuthAdapter.this.c != null) {
                    AuthAdapter.this.c.updateAuth(a2);
                }
            }
        }, this.e);
        authCapabilityAdapter.b(this.m);
        authCapabilityAdapter.e(this.k);
        contentViewHolder.d.setAdapter(authCapabilityAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, boolean z) {
        for (pfo pfoVar : this.f) {
            if (pfoVar.a().equals(str)) {
                pfoVar.b(z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        for (pfq pfqVar : this.d) {
            if (pfqVar.c() == 1) {
                pfqVar.b(z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        Iterator<Boolean> it = this.g.values().iterator();
        while (it.hasNext()) {
            if (!it.next().booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<pfq> list = this.d;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (koq.b(this.d, i)) {
            LogUtil.b("AuthAdapter", "getItemViewType isOutOfBounds");
            return -1;
        }
        return this.d.get(i).c();
    }

    public void c(List<pfq> list) {
        this.d = list;
        b();
    }

    public void d(HiAppInfo hiAppInfo) {
        this.f9694a = hiAppInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(pfq pfqVar, boolean z) {
        LogUtil.c("AuthAdapter", "updateCapabilityStatus isChecked:" + z);
        if (this.f == null) {
            return;
        }
        pfqVar.b(z);
        for (pfo pfoVar : this.f) {
            pfoVar.b(z);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(pfw.d(this.f9694a.getAppUid(), this.f9694a.getUserId(), this.f9694a.getAppId()));
            stringBuffer.append("_");
            stringBuffer.append(pfoVar.a());
            AuthInfo a2 = this.e.a(stringBuffer.toString());
            if (a2 == null) {
                return;
            }
            if (z) {
                a2.setOpenStatus(1);
            } else {
                a2.setOpenStatus(0);
            }
            LogUtil.c("AuthAdapter", "updateCapabilityStatus putAuthInfoToMap key:" + stringBuffer.toString());
            LogUtil.c("AuthAdapter", "updateCapabilityStatus putAuthInfoToMap authInfo:" + a2.toString());
            this.e.b(stringBuffer.toString(), a2);
            if (this.c != null) {
                this.c.updateAuth(a());
            }
        }
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        new Handler().post(new Runnable() { // from class: com.huawei.ui.main.stories.devicecapacity.AuthAdapter.2
            @Override // java.lang.Runnable
            public void run() {
                AuthAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public void a(String str) {
        this.i = str;
    }

    public void b(AuthActivity.AuthChangeListener authChangeListener) {
        this.c = authChangeListener;
    }

    public void a(pfs pfsVar) {
        this.e = pfsVar;
    }

    public void e(String str) {
        this.m = str;
    }

    public void a(List<Boolean> list) {
        this.h = list;
    }

    public void d(boolean z) {
        this.k = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a() {
        List<Boolean> list;
        if (this.f == null || (list = this.h) == null) {
            LogUtil.h("AuthAdapter", "mAuthList or mConfirmCheck is null");
            return false;
        }
        if (list.size() != this.f.size()) {
            LogUtil.h("AuthAdapter", "mAuthList and mConfirmCheck length is not equal");
            return false;
        }
        for (int i = 0; i < this.f.size(); i++) {
            if (this.h.get(i) != null && this.h.get(i).booleanValue() != this.f.get(i).e()) {
                return true;
            }
        }
        return false;
    }
}
