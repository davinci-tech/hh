package com.huawei.featureuserprofile.award.ui;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.webkit.ProxyConfig;
import com.huawei.featureuserprofile.award.listener.OnClickAddressButtonListener;
import com.huawei.featureuserprofile.award.listener.OnClickCopyListener;
import com.huawei.featureuserprofile.award.model.AddressInfo;
import com.huawei.health.R;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.trade.datatype.AwardRecordsBean;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.webview.WebViewActivity;
import defpackage.koq;
import defpackage.nrh;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes7.dex */
public class AwardValidFragment extends BaseFragment implements OnClickAddressButtonListener, OnClickCopyListener {

    /* renamed from: a, reason: collision with root package name */
    private AwardValidAdapter f1976a;
    private List<AwardRecordsBean> b;
    private RelativeLayout c;
    private HealthRecycleView d;

    public static AwardValidFragment d() {
        return new AwardValidFragment();
    }

    public void e(List<AwardRecordsBean> list) {
        List<AwardRecordsBean> list2 = this.b;
        if (list2 == null) {
            this.b = new ArrayList(list.size());
        } else {
            list2.clear();
        }
        this.b.addAll(list);
        b();
        AwardValidAdapter awardValidAdapter = this.f1976a;
        if (awardValidAdapter != null) {
            awardValidAdapter.e(this.b);
            this.f1976a.notifyDataSetChanged();
        }
    }

    public void c() {
        HealthRecycleView healthRecycleView = this.d;
        if (healthRecycleView != null) {
            healthRecycleView.setVisibility(8);
        }
        RelativeLayout relativeLayout = this.c;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(0);
        }
    }

    private void b() {
        RelativeLayout relativeLayout = this.c;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
        HealthRecycleView healthRecycleView = this.d;
        if (healthRecycleView != null) {
            healthRecycleView.setVisibility(0);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_award_valid, (ViewGroup) null);
        this.d = (HealthRecycleView) inflate.findViewById(R.id.award_recycler_view);
        this.c = (RelativeLayout) inflate.findViewById(R.id.award_empty_layout);
        this.d.setLayoutManager(new LinearLayoutManager(getActivity()));
        AwardValidAdapter awardValidAdapter = new AwardValidAdapter(getActivity());
        this.f1976a = awardValidAdapter;
        awardValidAdapter.a((OnClickAddressButtonListener) this);
        this.f1976a.a((OnClickCopyListener) this);
        this.d.setAdapter(this.f1976a);
        return inflate;
    }

    @Override // com.huawei.featureuserprofile.award.listener.OnClickAddressButtonListener
    public void onClickAddressButton(AddressInfo addressInfo) {
        Intent intent = new Intent(getActivity(), (Class<?>) WriteDeliveryInfoActivity.class);
        intent.putExtra("address_info", addressInfo);
        startActivityForResult(intent, 100);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100 && i2 == -1 && intent != null) {
            b(intent.getIntExtra("click_pos", -1), intent.getStringExtra("mail_name"), intent.getStringExtra("mail_phone"), intent.getStringExtra("mail_address"), intent.getStringExtra("mail_remark"));
        }
    }

    private void b(int i, String str, String str2, String str3, String str4) {
        AwardValidAdapter awardValidAdapter;
        if (i == -1 || (awardValidAdapter = this.f1976a) == null || i >= awardValidAdapter.getItemCount() || !koq.c(this.f1976a.d())) {
            return;
        }
        AwardRecordsBean.MaillingBean maillingBean = new AwardRecordsBean.MaillingBean();
        maillingBean.setName(str);
        maillingBean.setTelephone(str2);
        maillingBean.setAddress(str3);
        maillingBean.setRemark(str4);
        this.f1976a.d().get(i).setMailling(maillingBean);
        this.f1976a.d().get(i).setExchangeStatus("is_exchange");
        this.f1976a.notifyItemChanged(i);
    }

    @Override // com.huawei.featureuserprofile.award.listener.OnClickAddressButtonListener
    public void onClickLinkButton(String str, String str2) {
        onClickCopy(str2);
        if (str.startsWith("http") || str.startsWith(ProxyConfig.MATCH_HTTPS)) {
            Intent intent = new Intent(getActivity(), (Class<?>) WebViewActivity.class);
            intent.putExtra("WebViewActivity.REQUEST_URL_KEY", str);
            intent.putExtra(Constants.JUMP_MODE_KEY, 3);
            startActivity(intent);
            return;
        }
        String str3 = str + "&pullFrom=51";
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            marketRouterApi.router(str3);
        } else {
            LogUtil.h("AwardValidFragment", "marketRouterApi = null");
        }
    }

    @Override // com.huawei.featureuserprofile.award.listener.OnClickCopyListener
    public void onClickCopy(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("AwardValidFragment", "onClickCopy code isempty");
            return;
        }
        Object systemService = ((FragmentActivity) Objects.requireNonNull(getActivity())).getSystemService("clipboard");
        if (!(systemService instanceof ClipboardManager)) {
            LogUtil.h("AwardValidFragment", "object not instanceof ClipboardManager");
            return;
        }
        try {
            ClipboardManager clipboardManager = (ClipboardManager) systemService;
            ClipData newPlainText = ClipData.newPlainText("Label", str);
            ClipDescription description = newPlainText.getDescription();
            PersistableBundle persistableBundle = new PersistableBundle();
            if (Build.VERSION.SDK_INT >= 33) {
                persistableBundle.putBoolean("android.content.extra.IS_SENSITIVE", true);
            } else {
                persistableBundle.putBoolean("android.content.extra.IS_SENSITIVE", true);
            }
            description.setExtras(persistableBundle);
            clipboardManager.setPrimaryClip(newPlainText);
            if (Build.VERSION.SDK_INT < 33) {
                nrh.b(getActivity(), R.string._2130838148_res_0x7f020284);
            }
        } catch (SecurityException unused) {
            LogUtil.b("AwardValidFragment", "securityException");
        }
    }
}
