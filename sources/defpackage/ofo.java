package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.trade.datatype.BaseDeviceBenefitInfo;
import com.huawei.trade.datatype.DeviceBenefitProductInfo;
import com.huawei.trade.datatype.DeviceInboxInfo;
import com.huawei.trade.datatype.DevicePerfPurchaseInfo;
import com.huawei.trade.datatype.OtherServiceInfo;
import com.huawei.trade.datatype.Product;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.homehealth.device.CardDeviceFragment;
import com.huawei.uikit.hwviewpager.widget.HwPagerAdapter;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class ofo<T> extends HwPagerAdapter {
    private CardDeviceFragment b;
    private Context d;
    private Fragment f;
    private HealthViewPager i;
    private String c = "";
    private List<T> j = new ArrayList(16);
    private int g = 0;
    private String h = null;

    /* renamed from: a, reason: collision with root package name */
    private String f15645a = null;
    private List<T> e = new ArrayList(16);

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ofo(Context context, Fragment fragment, HealthViewPager healthViewPager, CardDeviceFragment cardDeviceFragment) {
        this.d = context;
        this.f = fragment;
        this.i = healthViewPager;
        this.b = cardDeviceFragment;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        List<T> list = this.e;
        if (list == null) {
            LogUtil.h("VipPaperAdapter", "getCount mDeviceList size is: 0");
            return 0;
        }
        LogUtil.a("VipPaperAdapter", "getCount mDeviceList size is: ", Integer.valueOf(list.size()));
        return this.e.size();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (viewGroup == null) {
            return;
        }
        if (koq.b(this.e, i)) {
            LogUtil.h("VipPaperAdapter", "destroyItem mDeviceList is error");
        } else if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (viewGroup == null) {
            return null;
        }
        if (koq.b(this.e, i)) {
            LogUtil.h("VipPaperAdapter", "mDeviceList is error");
            return null;
        }
        View cYG_ = cYG_(this.e.get(i), i);
        Object[] objArr = new Object[2];
        objArr[0] = "view is ";
        objArr[1] = Boolean.valueOf(cYG_ == null);
        LogUtil.a("VipPaperAdapter", objArr);
        viewGroup.addView(cYG_);
        return cYG_;
    }

    public void e(List<T> list) {
        if (list == null) {
            LogUtil.h("VipPaperAdapter", "VipPaperAdapter dataList is null");
            return;
        }
        this.e.clear();
        this.e.addAll(list);
        LogUtil.a("VipPaperAdapter", "VipPaperAdapter setData size is:", Integer.valueOf(this.e.size()));
        notifyDataSetChanged();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private View cYG_(T t, int i) {
        boolean z;
        OtherServiceInfo c;
        LogUtil.a("VipPaperAdapter", "buildCardViewLayout enter");
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.item_vip_card, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) nsy.cMd_(inflate, R.id.layout_vip_tip);
        HealthTextView healthTextView = (HealthTextView) nsy.cMd_(inflate, R.id.tv_close_click);
        HealthTextView healthTextView2 = (HealthTextView) nsy.cMd_(inflate, R.id.btn_active_click);
        healthTextView.setText(this.d.getResources().getString(R.string._2130840364_res_0x7f020b2c).toUpperCase());
        if ((t instanceof BaseDeviceBenefitInfo) && ((BaseDeviceBenefitInfo) t).getActiveStatus() == 2 && (c = ogw.c(t)) != null) {
            healthTextView.setVisibility(0);
            healthTextView2.setTextColor(ContextCompat.getColor(this.d, R.color._2131296789_res_0x7f090215));
            linearLayout.setBackgroundDrawable(this.d.getResources().getDrawable(R.drawable._2131431290_res_0x7f0b0f7a));
            this.g = c.getLinkType();
            this.h = c.getLinkValue();
            this.f15645a = this.d.getString(R.string._2130846757_res_0x7f022425);
            if (t instanceof DeviceInboxInfo) {
                this.c = ((DeviceInboxInfo) t).getDeviceInboxId();
            } else if (t instanceof DevicePerfPurchaseInfo) {
                this.c = ((DevicePerfPurchaseInfo) t).getDevicePerfPurchaseId();
            }
            b(0);
            z = true;
        } else {
            if (t instanceof DeviceInboxInfo) {
                DeviceInboxInfo deviceInboxInfo = (DeviceInboxInfo) t;
                this.g = deviceInboxInfo.getLinkType();
                this.h = deviceInboxInfo.getLinkValue();
                healthTextView.setVisibility(8);
                if (deviceInboxInfo.getBenefitType() == 2) {
                    healthTextView2.setTextColor(ContextCompat.getColor(this.d, R.color._2131296788_res_0x7f090214));
                    linearLayout.setBackgroundDrawable(this.d.getResources().getDrawable(R.drawable._2131431289_res_0x7f0b0f79));
                    this.f15645a = qqt.d(R.string._2130847589_res_0x7f022765, d(gpp.b(deviceInboxInfo)));
                } else {
                    healthTextView2.setTextColor(ContextCompat.getColor(this.d, R.color._2131296789_res_0x7f090215));
                    linearLayout.setBackgroundDrawable(this.d.getResources().getDrawable(R.drawable._2131431290_res_0x7f0b0f7a));
                    this.f15645a = gpn.d(gpp.e(deviceInboxInfo), 0);
                }
                this.c = deviceInboxInfo.getDeviceInboxId();
            } else if (t instanceof DevicePerfPurchaseInfo) {
                healthTextView.setVisibility(0);
                healthTextView2.setTextColor(ContextCompat.getColor(this.d, R.color._2131296789_res_0x7f090215));
                linearLayout.setBackgroundDrawable(this.d.getResources().getDrawable(R.drawable._2131431290_res_0x7f0b0f7a));
                DevicePerfPurchaseInfo devicePerfPurchaseInfo = (DevicePerfPurchaseInfo) t;
                this.g = devicePerfPurchaseInfo.getLinkType();
                this.h = devicePerfPurchaseInfo.getLinkValue();
                this.f15645a = gpn.d(gpp.e(devicePerfPurchaseInfo), gpn.a(devicePerfPurchaseInfo));
                this.c = devicePerfPurchaseInfo.getDevicePerfPurchaseId();
            } else {
                LogUtil.h("VipPaperAdapter", "object is other");
            }
            z = false;
        }
        LogUtil.a("VipPaperAdapter", "buildCardViewLayout description is:", this.f15645a);
        cYH_(inflate, healthTextView, healthTextView2, t, i, z);
        return inflate;
    }

    private void b(final T t, final int i) {
        LogUtil.a("VipPaperAdapter", 1, "VipPaperAdapter", "enter showEquityCloseDialog()");
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.d).e(this.d.getResources().getString(R.string._2130846239_res_0x7f02221f)).czC_(R.string._2130841938_res_0x7f021152, new View.OnClickListener() { // from class: ofq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ofo.this.cYL_(t, i, view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: oft
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ofo.cYI_(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    /* synthetic */ void cYL_(Object obj, int i, View view) {
        LogUtil.a("VipPaperAdapter", "showEquityCloseDialog ok click");
        a(1, obj);
        if (i >= this.e.size()) {
            LogUtil.h("VipPaperAdapter", "showEquityCloseDialog ok click mDeviceList size is: ", Integer.valueOf(this.e.size()), " position is: ", Integer.valueOf(i));
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (obj instanceof DevicePerfPurchaseInfo) {
            DevicePerfPurchaseInfo devicePerfPurchaseInfo = (DevicePerfPurchaseInfo) obj;
            SharedPreferenceManager.e(Integer.toString(10008), accountInfo + devicePerfPurchaseInfo.getDevicePerfPurchaseId(), true);
            LogUtil.a("VipPaperAdapter", "showEquityCloseDialog ok click DevicePerfPurchaseId is: ", devicePerfPurchaseInfo.getDevicePerfPurchaseId());
        } else if (obj instanceof DeviceInboxInfo) {
            DeviceInboxInfo deviceInboxInfo = (DeviceInboxInfo) obj;
            SharedPreferenceManager.e(Integer.toString(10008), accountInfo + "key_ignore_inbox_info" + deviceInboxInfo.getDeviceInboxId(), true);
            LogUtil.a("VipPaperAdapter", "showEquityCloseDialog ok click DeviceInboxId is: ", deviceInboxInfo.getDeviceInboxId());
        }
        this.j.clear();
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            if (i2 != i) {
                this.j.add(this.e.get(i2));
            }
        }
        LogUtil.a("VipPaperAdapter", "showEquityCloseDialog ok click new deviceList size is: ", Integer.valueOf(this.j.size()));
        this.b.c(this.j);
        if (this.j.size() <= 0) {
            this.i.setVisibility(8);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void cYI_(View view) {
        LogUtil.a("VipPaperAdapter", "showEquityCloseDialog cancel click");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void cYH_(View view, HealthTextView healthTextView, HealthTextView healthTextView2, final T t, final int i, final boolean z) {
        HealthTextView healthTextView3 = (HealthTextView) nsy.cMd_(view, R.id.tv_active_msg);
        healthTextView3.setText(this.f15645a);
        healthTextView3.setSelected(true);
        healthTextView2.setText(this.d.getResources().getString(R.string._2130846238_res_0x7f02221e).toUpperCase());
        final int i2 = this.g;
        final String str = this.h;
        healthTextView2.setOnClickListener(new View.OnClickListener() { // from class: ofs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ofo.this.cYJ_(z, i2, str, view2);
            }
        });
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: ofp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ofo.this.cYK_(z, t, i, view2);
            }
        });
    }

    /* synthetic */ void cYJ_(boolean z, int i, String str, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (z) {
            b(1);
        }
        b(this.c);
        ogj.d(this.d, i, str);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* synthetic */ void cYK_(boolean z, Object obj, int i, View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (z) {
            b(2);
        }
        b(this.c);
        a(0, obj);
        b(obj, i);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void b(String str) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        SharedPreferenceManager.e(Integer.toString(10008), "equity_click_red_dot_status" + accountInfo + str, true);
        this.b.c(false);
    }

    private String d(DeviceBenefitProductInfo deviceBenefitProductInfo) {
        String duration = deviceBenefitProductInfo != null ? deviceBenefitProductInfo.getDuration() : "";
        if (TextUtils.isEmpty(duration)) {
            duration = Product.YEAR_DURATION_FLAG;
        }
        return gpn.a(duration);
    }

    public static void a(int i, Object obj) {
        LogUtil.a("VipPaperAdapter", "setBiEnterCloseVipEquity enter");
        int i2 = 2;
        if ((obj instanceof BaseDeviceBenefitInfo) && ((BaseDeviceBenefitInfo) obj).getActiveStatus() == 2 && ogw.c(obj) != null) {
            i2 = 3;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("equityType", Integer.valueOf(i2));
        hashMap.put("reminderType", 1);
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_DEVICE_TAB_VIP_EQUITY.value(), hashMap, 0);
    }

    public static void b(int i) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_DEVICE_TAB_WATCH_BENEFIT_EVENT.value(), hashMap, 0);
    }
}
