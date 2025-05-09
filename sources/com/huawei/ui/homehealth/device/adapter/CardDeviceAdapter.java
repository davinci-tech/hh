package com.huawei.ui.homehealth.device.adapter;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.manager.DeviceCloudSharePreferencesManager;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.EventMonitorRecord;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.device.activity.pairing.DevicePairGuideUtil;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.device.utlis.BluetoothPermisionUtils;
import com.huawei.ui.homehealth.device.CardDeviceFragment;
import com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter;
import com.huawei.ui.homehealth.device.callback.BitmapCallBack;
import com.huawei.ui.homehealth.device.devicelist.DeviceMoreListActivity;
import com.huawei.ui.homehealth.device.devicelist.MoreSportDeviceActivity;
import defpackage.bdx;
import defpackage.ceo;
import defpackage.cjv;
import defpackage.cpa;
import defpackage.cpl;
import defpackage.cpm;
import defpackage.cpp;
import defpackage.ctk;
import defpackage.cvc;
import defpackage.cvt;
import defpackage.cwf;
import defpackage.cwi;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.iyl;
import defpackage.jah;
import defpackage.jfu;
import defpackage.jfv;
import defpackage.jgq;
import defpackage.jkj;
import defpackage.jpt;
import defpackage.jpw;
import defpackage.jpy;
import defpackage.kxy;
import defpackage.msj;
import defpackage.nkx;
import defpackage.nrr;
import defpackage.nrt;
import defpackage.nrz;
import defpackage.nsn;
import defpackage.oad;
import defpackage.pep;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class CardDeviceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static Map<String, Bitmap> c = new HashMap(16);

    /* renamed from: a, reason: collision with root package name */
    private DeviceMoreListActivity f9380a;
    private CardDeviceFragment b;
    private int d;
    private Context e;
    private PersonalItemReconnectListener f;
    private HwVersionManager h;
    private LayoutInflater i;
    private MoreSportDeviceActivity l;
    private BtSwitchStateCallback t;
    private List<cjv> k = new ArrayList(16);
    private int o = 0;
    private boolean g = false;
    private NoTitleCustomAlertDialog n = null;
    private boolean j = false;
    private int m = 1;

    public interface PersonalItemReconnectListener {
        void onMoreEquipment();

        void onPersonalEquipment(int i, View view);

        void onReconnect(cpm cpmVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public CardDeviceAdapter(Context context, CardDeviceFragment cardDeviceFragment, PersonalItemReconnectListener personalItemReconnectListener) {
        this.h = null;
        this.e = context;
        this.b = cardDeviceFragment;
        this.f = personalItemReconnectListener;
        this.i = LayoutInflater.from(context);
        this.h = HwVersionManager.c(this.e);
    }

    public CardDeviceAdapter(Context context, DeviceMoreListActivity deviceMoreListActivity, PersonalItemReconnectListener personalItemReconnectListener) {
        this.h = null;
        this.e = context;
        this.f9380a = deviceMoreListActivity;
        this.f = personalItemReconnectListener;
        if (context != null) {
            this.i = LayoutInflater.from(context);
        }
        this.h = HwVersionManager.c(this.e);
    }

    public CardDeviceAdapter(Context context, MoreSportDeviceActivity moreSportDeviceActivity, PersonalItemReconnectListener personalItemReconnectListener) {
        this.h = null;
        this.e = context;
        this.l = moreSportDeviceActivity;
        this.f = personalItemReconnectListener;
        if (context != null) {
            this.i = LayoutInflater.from(context);
        }
        this.h = HwVersionManager.c(this.e);
    }

    public void c(List<cjv> list) {
        Object[] objArr = new Object[2];
        objArr[0] = "setDeviceList productInfoList: ";
        objArr[1] = Integer.valueOf(this.k != null ? list.size() : -1);
        ReleaseLogUtil.e("R_CardDeviceAdapter", objArr);
        this.k = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        List<cjv> list = this.k;
        if (list == null || i < 0 || i >= list.size()) {
            return 1;
        }
        cjv cjvVar = this.k.get(i);
        LogUtil.a("CardDeviceAdapter", "getItemViewType viewType:", Integer.valueOf(cjvVar.a()));
        if (cjvVar.a() == 2) {
            return 2;
        }
        if (cjvVar.b() != 2 && this.k.size() <= 1) {
            return 1;
        }
        if (this.m == 2) {
            return c();
        }
        return 3;
    }

    private int c() {
        return nsn.ag(this.e) ? 3 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 2) {
            this.d = 1;
            return new MoreViewHolder(this.i.inflate(R.layout.fragment_device_card_list_item_optimization, viewGroup, false));
        }
        if (i == 3) {
            this.d = 1;
            return new CardDeviceViewHolder(this.i.inflate(R.layout.fragment_device_card_list_item_optimization, viewGroup, false));
        }
        this.d = 2;
        return new CardDeviceViewHolder(this.i.inflate(R.layout.fragment_device_card_list_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        viewHolder.setIsRecyclable(false);
        if (viewHolder instanceof CardDeviceViewHolder) {
            b((CardDeviceViewHolder) viewHolder, i);
        } else {
            d((MoreViewHolder) viewHolder);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.2
                @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
                public int getSpanSize(int i) {
                    return (!nsn.ag(CardDeviceAdapter.this.e) && CardDeviceAdapter.this.k.size() == 1) ? 2 : 1;
                }
            });
        }
    }

    private void d(MoreViewHolder moreViewHolder) {
        if (moreViewHolder.d != null) {
            moreViewHolder.d.setVisibility(8);
        }
        if (moreViewHolder.f9388a != null) {
            moreViewHolder.f9388a.setVisibility(8);
        }
        if (LanguageUtil.bc(this.e)) {
            moreViewHolder.b.setBackgroundResource(R.mipmap._2131821443_res_0x7f110383);
        } else {
            moreViewHolder.b.setBackgroundResource(R.mipmap._2131821442_res_0x7f110382);
        }
        moreViewHolder.e.setOnClickListener(new View.OnClickListener() { // from class: ofm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CardDeviceAdapter.this.cYD_(view);
            }
        });
    }

    public /* synthetic */ void cYD_(View view) {
        ReleaseLogUtil.e("R_CardDeviceAdapter", "refreshWatchFace enter mPageType = ", Integer.valueOf(this.m));
        this.f.onMoreEquipment();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.k.size();
    }

    private void b(CardDeviceViewHolder cardDeviceViewHolder, final int i) {
        if (i < 0 || i >= this.k.size()) {
            return;
        }
        cjv cjvVar = this.k.get(i);
        if (cardDeviceViewHolder.b != null) {
            cardDeviceViewHolder.b.setVisibility(8);
        }
        c(cardDeviceViewHolder, cjvVar);
        a(cardDeviceViewHolder, cjvVar);
        e(cardDeviceViewHolder, cjvVar);
        if (nsn.s()) {
            cardDeviceViewHolder.o.setVisibility(8);
        }
        bdx bdxVar = new bdx(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReleaseLogUtil.e("R_CardDeviceAdapter", "onClick onPersonalEquipment mPageType=", Integer.valueOf(CardDeviceAdapter.this.m));
                CardDeviceAdapter.this.f.onPersonalEquipment(i, view);
                ViewClickInstrumentation.clickOnView(view);
            }
        }, true, "");
        nkx.a(bdxVar, ((BaseActivity) this.e).toString());
        cardDeviceViewHolder.q.setOnClickListener(bdxVar);
    }

    private void c(final CardDeviceViewHolder cardDeviceViewHolder, cjv cjvVar) {
        if (cjvVar.a() == 1) {
            if (cardDeviceViewHolder.z != null) {
                cardDeviceViewHolder.z.setVisibility(8);
            }
            if (cardDeviceViewHolder.ac != null) {
                cardDeviceViewHolder.ac.setVisibility(8);
            }
            if (cardDeviceViewHolder.j != null) {
                cardDeviceViewHolder.j.setVisibility(0);
            }
            cardDeviceViewHolder.ak.setVisibility(0);
            cardDeviceViewHolder.c.setVisibility(0);
            cardDeviceViewHolder.k.setVisibility(0);
            cardDeviceViewHolder.h.setVisibility(0);
            cardDeviceViewHolder.m.setVisibility(8);
            cardDeviceViewHolder.ah.setVisibility(8);
            Object i = cjvVar.i();
            if (!(i instanceof cpm)) {
                LogUtil.h("CardDeviceAdapter", "setWearDevice DeviceInfoForWear is null");
                return;
            }
            final cpm cpmVar = (cpm) i;
            if (cpmVar.g()) {
                LogUtil.a("CardDeviceAdapter", "deviceInfoForWear.getIsCloudDevice");
                cardDeviceViewHolder.f9387a.setVisibility(0);
            } else {
                cardDeviceViewHolder.f9387a.setVisibility(8);
            }
            LogUtil.a("CardDeviceAdapter", "getView wear device name:", cpmVar.d());
            String e = pep.e(this.e, cpmVar.a(), cpmVar.d(), cjvVar.j(), true);
            LogUtil.a("CardDeviceAdapter", "deviceNameAlias:", e);
            cardDeviceViewHolder.i.setText(e);
            e(cardDeviceViewHolder.i);
            cardDeviceViewHolder.d = cpmVar;
            b(cpmVar, new BitmapCallBack() { // from class: com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.3
                @Override // com.huawei.ui.homehealth.device.callback.BitmapCallBack
                public void onCallback(Bitmap bitmap) {
                    if (!cpmVar.equals(cardDeviceViewHolder.d)) {
                        if (cardDeviceViewHolder.d == null) {
                            ReleaseLogUtil.e("R_CardDeviceAdapter", "setDeviceImage holder.deviceInfoForWear is null");
                            return;
                        } else {
                            ReleaseLogUtil.e("R_CardDeviceAdapter", "setDeviceImage deviceType:", Integer.valueOf(cpmVar.i()), "  holder.deviceType:", Integer.valueOf(cardDeviceViewHolder.d.i()));
                            return;
                        }
                    }
                    cardDeviceViewHolder.h.setImageBitmap(bitmap);
                }
            });
            d(cardDeviceViewHolder, cpmVar);
        }
    }

    private void a(final CardDeviceViewHolder cardDeviceViewHolder, cjv cjvVar) {
        if (cjvVar.a() == 4) {
            c(cardDeviceViewHolder);
            final List<cjv> h = cjvVar.h();
            Object i = h.get(0).i();
            if (!(i instanceof cpm)) {
                LogUtil.h("CardDeviceAdapter", "!(object instanceof DeviceInfoForWear)");
                return;
            }
            final cpm cpmVar = (cpm) i;
            String d = cpmVar.d();
            cardDeviceViewHolder.ad.setText(d.contains(Constants.LINK) ? d.substring(0, d.lastIndexOf(Constants.LINK)) : "");
            cardDeviceViewHolder.aa.setText("" + h.size());
            e(cardDeviceViewHolder.ad);
            e(cardDeviceViewHolder.aa);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(nrr.e(this.e, 32.0f), nrr.e(this.e, 32.0f), 1.0f);
            if (h.size() == 2) {
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(nrr.e(this.e, 48.0f), nrr.e(this.e, 48.0f));
                layoutParams2.setMarginEnd(nrr.e(this.e, 16.0f));
                layoutParams = layoutParams2;
            }
            if ((this.d == 2 || this.k.size() == 1) && h.size() > 2) {
                layoutParams = new LinearLayout.LayoutParams(nrr.e(this.e, 48.0f), nrr.e(this.e, 48.0f), 1.0f);
            }
            final LinearLayout.LayoutParams layoutParams3 = layoutParams;
            cardDeviceViewHolder.d = cpmVar;
            b(cpmVar, new BitmapCallBack() { // from class: com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.1
                @Override // com.huawei.ui.homehealth.device.callback.BitmapCallBack
                public void onCallback(Bitmap bitmap) {
                    if (cpmVar.equals(cardDeviceViewHolder.d)) {
                        CardDeviceAdapter.this.j = false;
                        cardDeviceViewHolder.z.removeAllViews();
                        for (int i2 = 0; i2 < h.size(); i2++) {
                            if (((cjv) h.get(i2)).i() instanceof cpm) {
                                CardDeviceAdapter.this.cYA_((cpm) ((cjv) h.get(i2)).i(), cardDeviceViewHolder, bitmap, layoutParams3, i2);
                                if (CardDeviceAdapter.this.j && i2 > 3) {
                                    return;
                                }
                            }
                        }
                        return;
                    }
                    if (cardDeviceViewHolder.d == null) {
                        ReleaseLogUtil.e("R_CardDeviceAdapter", "setMoreSportDeviceImage holder.deviceInfoForWear is null");
                    } else {
                        ReleaseLogUtil.e("R_CardDeviceAdapter", "setMoreSportDeviceImage deviceType:", Integer.valueOf(cpmVar.i()), "  holder.deviceType:", Integer.valueOf(cardDeviceViewHolder.d.i()));
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cYA_(cpm cpmVar, CardDeviceViewHolder cardDeviceViewHolder, Bitmap bitmap, LinearLayout.LayoutParams layoutParams, int i) {
        if (i <= 3) {
            ImageView imageView = new ImageView(this.e);
            imageView.setImageBitmap(bitmap);
            if (cpmVar.e() != 2) {
                imageView.setAlpha(0.38f);
            }
            imageView.setLayoutParams(layoutParams);
            cardDeviceViewHolder.z.addView(imageView);
        }
        if (this.j || cpmVar.e() != 2) {
            return;
        }
        this.j = this.h.n(cpmVar.a());
        if (cYq_() == null) {
            LogUtil.h("CardDeviceAdapter", "fragment or activity is null");
        } else if (this.j) {
            cardDeviceViewHolder.af.setVisibility(0);
        } else {
            cardDeviceViewHolder.af.setVisibility(8);
        }
    }

    private void e(HealthTextView healthTextView) {
        if (this.d == 1) {
            if (LanguageUtil.ae(this.e)) {
                healthTextView.setTextSize(1, 10.0f);
            } else {
                healthTextView.setTextSize(1, 14.0f);
            }
        }
    }

    private void c(CardDeviceViewHolder cardDeviceViewHolder) {
        cardDeviceViewHolder.j.setVisibility(8);
        cardDeviceViewHolder.ac.setVisibility(0);
        cardDeviceViewHolder.c.setVisibility(8);
        cardDeviceViewHolder.ak.setVisibility(8);
        cardDeviceViewHolder.z.setVisibility(0);
        cardDeviceViewHolder.z.removeAllViews();
    }

    private void d(CardDeviceViewHolder cardDeviceViewHolder, cpm cpmVar) {
        if (cpmVar.e() == 2) {
            e(cardDeviceViewHolder, cpmVar);
            i(cardDeviceViewHolder, cpmVar);
            return;
        }
        if (cpmVar.e() == 1) {
            cardDeviceViewHolder.i.setAlpha(0.38f);
            cardDeviceViewHolder.h.setAlpha(0.38f);
            cardDeviceViewHolder.g.setText(R.string._2130841443_res_0x7f020f63);
            cardDeviceViewHolder.f.setVisibility(8);
            cardDeviceViewHolder.o.setVisibility(8);
            cardDeviceViewHolder.aj.setVisibility(8);
            cardDeviceViewHolder.u.setVisibility(8);
            if (cardDeviceViewHolder.v != null) {
                cardDeviceViewHolder.v.setVisibility(8);
            }
            cardDeviceViewHolder.x.setVisibility(0);
            cardDeviceViewHolder.y.setText(R.string.IDS_hw_health_wear_connect_device_connect_text);
            cardDeviceViewHolder.s.setVisibility(4);
            return;
        }
        b(cardDeviceViewHolder, cpmVar);
    }

    private void b(CardDeviceViewHolder cardDeviceViewHolder, cpm cpmVar) {
        boolean z = (this.m == 1 && this.k.size() == 1) || this.m == 2;
        if (LanguageUtil.ap(this.e) && !nsn.ae(this.e) && z) {
            cardDeviceViewHolder.u.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 2.0f));
            cardDeviceViewHolder.f.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 3.0f));
        }
        cardDeviceViewHolder.g.setText(R.string._2130841443_res_0x7f020f63);
        cardDeviceViewHolder.o.setVisibility(8);
        cardDeviceViewHolder.aj.setVisibility(8);
        cardDeviceViewHolder.x.setVisibility(8);
        cardDeviceViewHolder.f.setVisibility(0);
        int i = cpmVar.g() ? R.string._2130843256_res_0x7f021678 : R.string.IDS_hw_health_wear_connect_device_connect_button;
        a(cardDeviceViewHolder, cpmVar);
        if (cardDeviceViewHolder.v != null && !LanguageUtil.h(this.e) && this.k.size() > 1) {
            cardDeviceViewHolder.v.setVisibility(0);
            cardDeviceViewHolder.v.setText(i);
            cardDeviceViewHolder.v.setTextColor(this.e.getResources().getColor(R.color._2131298056_res_0x7f090708));
        } else {
            cardDeviceViewHolder.u.setVisibility(0);
            cardDeviceViewHolder.u.setText(i);
            cardDeviceViewHolder.u.setTextColor(this.e.getResources().getColor(R.color._2131298056_res_0x7f090708));
        }
        cardDeviceViewHolder.s.setVisibility(4);
        c(cardDeviceViewHolder, cpmVar);
        b(cardDeviceViewHolder);
    }

    private void a(CardDeviceViewHolder cardDeviceViewHolder, cpm cpmVar) {
        boolean z = LanguageUtil.h(this.e) && this.d == 1;
        if (cpmVar.g() && (z || this.d == 2)) {
            cardDeviceViewHolder.f.setVisibility(8);
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        if (cardDeviceViewHolder.f.getVisibility() == 8 && this.d == 1) {
            cardDeviceViewHolder.u.setLayoutParams(layoutParams);
        } else if (cardDeviceViewHolder.f.getVisibility() == 0 && this.d == 1) {
            layoutParams.setMarginStart(nsn.c(this.e, 8.0f));
            cardDeviceViewHolder.u.setLayoutParams(layoutParams);
        } else {
            LogUtil.h("CardDeviceAdapter", "setCloudRetryConnectTv retryConnectTextView other");
        }
    }

    private void c(CardDeviceViewHolder cardDeviceViewHolder, cpm cpmVar) {
        if (!this.g) {
            if (cardDeviceViewHolder.v != null && !LanguageUtil.h(this.e) && this.k.size() > 1) {
                cYs_(cardDeviceViewHolder.f, cardDeviceViewHolder.v, cardDeviceViewHolder.x, cpmVar);
                return;
            } else {
                cYs_(cardDeviceViewHolder.f, cardDeviceViewHolder.u, cardDeviceViewHolder.x, cpmVar);
                return;
            }
        }
        if (cardDeviceViewHolder.v != null && !LanguageUtil.h(this.e) && this.k.size() > 1) {
            cardDeviceViewHolder.v.setTextColor(this.e.getResources().getColor(R.color._2131297331_res_0x7f090433));
            cardDeviceViewHolder.v.setClickable(false);
        } else {
            cardDeviceViewHolder.u.setTextColor(this.e.getResources().getColor(R.color._2131297331_res_0x7f090433));
            cardDeviceViewHolder.u.setClickable(false);
        }
    }

    private void e(CardDeviceViewHolder cardDeviceViewHolder, cjv cjvVar) {
        int a2 = cjvVar.a();
        if (a2 == 0 || a2 == 3 || a2 == 6) {
            if (nrt.a(this.e)) {
                cardDeviceViewHolder.e.setBackground(this.e.getResources().getDrawable(R.drawable.card_device_item_gradient_darkmode_bg));
                cardDeviceViewHolder.ag.setTextColor(this.e.getResources().getColor(R.color._2131297630_res_0x7f09055e));
                cardDeviceViewHolder.ae.setTextColor(this.e.getResources().getColor(R.color._2131299243_res_0x7f090bab));
            }
            cardDeviceViewHolder.ai.setVisibility(4);
            cardDeviceViewHolder.k.setVisibility(8);
            cardDeviceViewHolder.h.setVisibility(8);
            cardDeviceViewHolder.m.setVisibility(0);
            cardDeviceViewHolder.ah.setVisibility(0);
            if (cardDeviceViewHolder.f9387a != null) {
                cardDeviceViewHolder.f9387a.setVisibility(8);
            }
            Object i = cjvVar.i();
            if (!(i instanceof dcz)) {
                LogUtil.h("CardDeviceAdapter", "setThreeWayDevice ProductInfo is null");
                return;
            }
            dcz dczVar = (dcz) i;
            ContentValues FT_ = cjvVar.FT_();
            e(cardDeviceViewHolder, a2, dczVar, cYr_(FT_));
            if (!cpa.ab(dczVar.t())) {
                LogUtil.h("CardDeviceAdapter", "is not Honour weight device");
            } else if (FT_ != null) {
                d(cardDeviceViewHolder, dczVar.t(), FT_.getAsString("uniqueId"));
            } else {
                LogUtil.h("CardDeviceAdapter", "CardDeviceAdapter isShowNewVersion deviceInfo is null");
            }
            d(cardDeviceViewHolder, cjvVar);
        }
    }

    private void d(CardDeviceViewHolder cardDeviceViewHolder, cjv cjvVar) {
        LogUtil.a("CardDeviceAdapter", "enter showCloudDevice...");
        if ("0".equals(cjvVar.FT_().getAsString(EventMonitorRecord.ADD_TIME))) {
            LogUtil.a("CardDeviceAdapter", "device is CloudDevice");
            cardDeviceViewHolder.f9387a.setVisibility(0);
            cardDeviceViewHolder.ag.setAlpha(0.38f);
            cardDeviceViewHolder.m.setAlpha(0.38f);
            cardDeviceViewHolder.ae.setAlpha(0.38f);
        }
    }

    private String cYr_(ContentValues contentValues) {
        if (contentValues == null) {
            LogUtil.h("CardDeviceAdapter", "getThreeWayDeviceName deviceInfo is null.");
            return "";
        }
        String asString = contentValues.getAsString("sn");
        String asString2 = contentValues.getAsString("uniqueId");
        if (TextUtils.isEmpty(asString)) {
            asString = cpa.l(asString2);
        }
        return TextUtils.isEmpty(asString) ? asString2 : asString;
    }

    private void e(CardDeviceViewHolder cardDeviceViewHolder, int i, dcz dczVar, String str) {
        if (dczVar.e().size() <= 0) {
            LogUtil.a("CardDeviceAdapter", "item.getDescriptions().size() <= 0");
            if (i == 3 || i == 6) {
                cardDeviceViewHolder.s.setVisibility(4);
                cardDeviceViewHolder.ae.setVisibility(4);
                cardDeviceViewHolder.ag.setText(b(dcx.d(dczVar.t(), dczVar.n().b()), str));
                cardDeviceViewHolder.m.setImageBitmap(dcx.TK_(dcq.b().a(dczVar.t(), dczVar.n().d())));
            } else {
                cardDeviceViewHolder.ag.setText(b(dczVar.n().b(), str));
                cardDeviceViewHolder.ae.setText(dczVar.n().c());
                cardDeviceViewHolder.m.setImageResource(dcx.a(dczVar.n().d()));
            }
        } else {
            LogUtil.a("CardDeviceAdapter", "item.getDescriptions().size() > 0");
            if ("9bf158ba-49b0-46aa-9fdf-ed75da1569cf".equals(dczVar.t())) {
                str = "";
            }
            if (!TextUtils.isEmpty(str)) {
                cardDeviceViewHolder.ag.setText(b(dcx.d(dczVar.t(), dczVar.n().b()), str));
            } else {
                cardDeviceViewHolder.ag.setText(dcx.d(dczVar.t(), dczVar.n().b()));
            }
            cardDeviceViewHolder.ae.setText(dcx.d(dczVar.t(), dczVar.n().c()));
            cardDeviceViewHolder.m.setImageBitmap(dcx.TK_(dcq.b().a(dczVar.t(), dczVar.n().d())));
        }
        e(cardDeviceViewHolder.ag);
    }

    private void d(CardDeviceViewHolder cardDeviceViewHolder, String str, String str2) {
        if (!cpa.v(str)) {
            LogUtil.h("CardDeviceAdapter", "CardDeviceAdapter is not new hag");
            return;
        }
        if (TextUtils.isEmpty(kxy.e(this.e, str2))) {
            String k = cpa.k(str2);
            LogUtil.a("CardDeviceAdapter", "processNotifyRed currentVersion:", k);
            kxy.b(k, this.e, str2);
        }
        if (TextUtils.isEmpty(kxy.i(this.e, str2))) {
            String a2 = kxy.a(this.e, str);
            LogUtil.a("CardDeviceAdapter", "isShowNewVersion scaleCheckNewVersion:", a2);
            kxy.j(a2, this.e, str2);
        }
        boolean j = kxy.j(this.e, str2);
        LogUtil.a("CardDeviceAdapter", "CardDeviceAdapter isShowNewVersion isNewVersion:", Boolean.valueOf(j));
        LogUtil.a("CardDeviceAdapter", "CardDeviceAdapter isShowNewVersion uniqueId:", str2, ",productId:" + str);
        if (!e(str2) && j) {
            cardDeviceViewHolder.ai.setVisibility(0);
            LogUtil.a("CardDeviceAdapter", "CardDeviceAdapter isShowNewVersion not Manager && isNewVersion");
            return;
        }
        MeasurableDevice d = ceo.d().d(str2, false);
        if (d != null) {
            if (d instanceof ctk) {
                boolean z = ((ctk) d).b().k() == 1;
                LogUtil.a("CardDeviceAdapter", "CardDeviceAdapter is new isMainUser:", Boolean.valueOf(z));
                if (z && j) {
                    cardDeviceViewHolder.ai.setVisibility(0);
                    return;
                } else {
                    cardDeviceViewHolder.ai.setVisibility(4);
                    return;
                }
            }
            LogUtil.h("CardDeviceAdapter", "CardDeviceAdapter device not WiFiDevice");
            return;
        }
        LogUtil.h("CardDeviceAdapter", "CardDeviceAdapter device is null");
    }

    private boolean e(String str) {
        DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager = new DeviceCloudSharePreferencesManager(cpp.a());
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("device_has_manager");
        stringBuffer.append(str);
        return deviceCloudSharePreferencesManager.e(stringBuffer.toString());
    }

    private String b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("CardDeviceAdapter", "name is empty");
            return "";
        }
        if (str.toUpperCase().contains(a(str2).toUpperCase())) {
            return str;
        }
        return str + a(str2).toUpperCase();
    }

    private void e(CardDeviceViewHolder cardDeviceViewHolder, cpm cpmVar) {
        if (jkj.d().c(cpmVar.a()) == 6) {
            cardDeviceViewHolder.f.setVisibility(8);
            cardDeviceViewHolder.o.setVisibility(8);
            cardDeviceViewHolder.u.setVisibility(8);
            if (cardDeviceViewHolder.v != null) {
                cardDeviceViewHolder.v.setVisibility(8);
            }
            cardDeviceViewHolder.x.setVisibility(0);
            cardDeviceViewHolder.y.setText(R.string._2130841463_res_0x7f020f77);
            LogUtil.a("CardDeviceAdapter", "wear device is OTAing");
            return;
        }
        a(cardDeviceViewHolder);
        cardDeviceViewHolder.g.setText(R.string._2130841442_res_0x7f020f62);
        cardDeviceViewHolder.f.setVisibility(0);
        int b2 = jpy.b(cpmVar.a());
        DeviceInfo b3 = jpt.b(cpmVar.a(), "CardDeviceAdapter");
        e(cardDeviceViewHolder, b2, b3);
        if (cwi.c(b3, 78)) {
            e(cardDeviceViewHolder, jgq.d().d(cpmVar.a()));
        }
        cardDeviceViewHolder.u.setVisibility(8);
        if (cardDeviceViewHolder.v != null) {
            cardDeviceViewHolder.v.setVisibility(8);
        }
        cardDeviceViewHolder.x.setVisibility(8);
    }

    private void e(CardDeviceViewHolder cardDeviceViewHolder, int i) {
        String d = jgq.d().d(i);
        cardDeviceViewHolder.aj.setVisibility(0);
        cardDeviceViewHolder.aj.setText(d);
    }

    private void e(CardDeviceViewHolder cardDeviceViewHolder, int i, DeviceInfo deviceInfo) {
        LogUtil.a("CardDeviceAdapter", "updatePower battery is ", Integer.valueOf(i));
        if (i >= 0) {
            cardDeviceViewHolder.f.setVisibility(8);
            cardDeviceViewHolder.o.setVisibility(0);
            cardDeviceViewHolder.l.setText(UnitUtil.e(i, 2, 0));
            if (this.d == 1) {
                cardDeviceViewHolder.l.setTextSize(1, 12.0f);
            } else {
                cardDeviceViewHolder.l.setTextSize(2, 14.0f);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(nrr.e(this.e, 16.0f), nrr.e(this.e, 16.0f));
            if (cwi.c(deviceInfo, 112)) {
                layoutParams = new LinearLayout.LayoutParams(nrr.e(this.e, 10.0f), nrr.e(this.e, 10.0f));
                if (this.d == 1) {
                    cardDeviceViewHolder.l.setTextSize(1, 9.0f);
                } else {
                    cardDeviceViewHolder.l.setTextSize(2, 12.0f);
                }
                cardDeviceViewHolder.n.setImageDrawable(this.e.getResources().getDrawable(R.drawable._2131432006_res_0x7f0b1246));
            } else if (LanguageUtil.bc(this.e)) {
                cardDeviceViewHolder.n.setImageDrawable(nrz.cKm_(this.e, nsn.cLd_(i)));
            } else {
                cardDeviceViewHolder.n.setImageDrawable(nsn.cLd_(i));
            }
            cardDeviceViewHolder.n.setLayoutParams(layoutParams);
            c(cardDeviceViewHolder, deviceInfo);
            return;
        }
        cardDeviceViewHolder.o.setVisibility(8);
        cardDeviceViewHolder.r.setVisibility(8);
        cardDeviceViewHolder.ab.setVisibility(8);
    }

    private void c(CardDeviceViewHolder cardDeviceViewHolder, DeviceInfo deviceInfo) {
        if (cwi.c(deviceInfo, 112)) {
            cardDeviceViewHolder.r.setVisibility(0);
            cardDeviceViewHolder.ab.setVisibility(0);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            if (this.d == 2) {
                layoutParams.topMargin = nrr.e(this.e, 4.0f);
            }
            if (LanguageUtil.bc(this.e)) {
                if (this.d == 1) {
                    layoutParams.setMarginEnd(nrr.e(this.e, 8.0f));
                } else {
                    layoutParams.setMarginEnd(nrr.e(this.e, 12.0f));
                }
            }
            cardDeviceViewHolder.ab.setLayoutParams(layoutParams);
            jpw e = jpy.e(deviceInfo.getDeviceIdentify());
            int b2 = e.b();
            int e2 = e.e();
            if (b2 != -1) {
                cardDeviceViewHolder.p.setText(UnitUtil.e(b2, 2, 0));
            } else {
                cardDeviceViewHolder.p.setText("--");
            }
            if (e2 != -1) {
                cardDeviceViewHolder.w.setText(UnitUtil.e(e2, 2, 0));
                return;
            } else {
                cardDeviceViewHolder.w.setText("--");
                return;
            }
        }
        cardDeviceViewHolder.r.setVisibility(8);
        cardDeviceViewHolder.ab.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Activity cYq_() {
        int i = this.m;
        if (i == 1) {
            CardDeviceFragment cardDeviceFragment = this.b;
            if (cardDeviceFragment != null && cardDeviceFragment.getActivity() != null) {
                return this.b.getActivity();
            }
        } else if (i == 2) {
            DeviceMoreListActivity deviceMoreListActivity = this.f9380a;
            if (deviceMoreListActivity instanceof Activity) {
                return deviceMoreListActivity;
            }
        } else if (i == 3) {
            MoreSportDeviceActivity moreSportDeviceActivity = this.l;
            if (moreSportDeviceActivity instanceof Activity) {
                return moreSportDeviceActivity;
            }
        } else {
            LogUtil.h("CardDeviceAdapter", "activity else branch");
        }
        return null;
    }

    private void i(final CardDeviceViewHolder cardDeviceViewHolder, final cpm cpmVar) {
        ThreadPoolManager.d().d("CardDeviceAdapter", new Runnable() { // from class: com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.4
            @Override // java.lang.Runnable
            public void run() {
                final boolean n = CardDeviceAdapter.this.h.n(cpmVar.a());
                Activity cYq_ = CardDeviceAdapter.this.cYq_();
                if (cYq_ == null) {
                    LogUtil.h("CardDeviceAdapter", "fragment or activity is null");
                    return;
                }
                final boolean c2 = cwi.c(jpt.e(cpmVar.a(), "CardDeviceAdapter"), 58);
                final DeviceCapability e = DeviceSettingsInteractors.d(CardDeviceAdapter.this.e).e(cpmVar.a());
                LogUtil.a("CardDeviceAdapter", "haveNewVersionTip isNew:", Boolean.valueOf(n), " isSupportDetect ", Boolean.valueOf(c2));
                cYq_.runOnUiThread(new Runnable() { // from class: com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.4.4
                    @Override // java.lang.Runnable
                    public void run() {
                        DeviceCapability deviceCapability = e;
                        if (deviceCapability != null && !deviceCapability.isOtaUpdate() && !c2) {
                            cardDeviceViewHolder.s.setVisibility(4);
                            LogUtil.h("CardDeviceAdapter", "deviceCapability.isOtaUpdate():", Boolean.valueOf(e.isOtaUpdate()));
                        } else if (jkj.d().c(cpmVar.a()) == 6) {
                            cardDeviceViewHolder.s.setVisibility(4);
                        } else if (n) {
                            cardDeviceViewHolder.s.setVisibility(0);
                        } else {
                            cardDeviceViewHolder.s.setVisibility(4);
                        }
                    }
                });
            }
        });
    }

    private void b(cpm cpmVar, BitmapCallBack bitmapCallBack) {
        int i = cpmVar.i();
        LogUtil.a("CardDeviceAdapter", "getView wear device deviceType is : ", Integer.valueOf(i));
        if (jfu.m(i)) {
            LogUtil.a("CardDeviceAdapter", "is plugin download");
            String j = jfu.j(i);
            LogUtil.a("CardDeviceAdapter", "is plugin download uuid:", j);
            boolean isResourcesAvailable = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(j);
            ReleaseLogUtil.e("R_CardDeviceAdapter", "download isPluginAvailable:", Boolean.valueOf(isResourcesAvailable), "  deviceType:", Integer.valueOf(cpmVar.i()));
            if (isResourcesAvailable) {
                a(i, j, cpmVar, bitmapCallBack);
                return;
            } else if (jfu.h(i)) {
                bitmapCallBack.onCallback(BitmapFactory.decodeResource(this.e.getResources(), R.mipmap._2131820663_res_0x7f110077));
                return;
            } else {
                bitmapCallBack.onCallback(BitmapFactory.decodeResource(this.e.getResources(), R.mipmap._2131820673_res_0x7f110081));
                return;
            }
        }
        ReleaseLogUtil.e("R_CardDeviceAdapter", "set imageView default. deviceType:", Integer.valueOf(cpmVar.i()));
        if (!TextUtils.isEmpty(cpmVar.d()) && cpmVar.d().contains("HUAWEI CM-R1P")) {
            bitmapCallBack.onCallback(BitmapFactory.decodeResource(this.e.getResources(), R.mipmap._2131821232_res_0x7f1102b0));
        } else {
            b(i, cpmVar, bitmapCallBack);
        }
    }

    private void b(int i, cpm cpmVar, BitmapCallBack bitmapCallBack) {
        Bitmap decodeResource = BitmapFactory.decodeResource(this.e.getResources(), CommonUtil.h(cpmVar.f()));
        if (decodeResource != null) {
            bitmapCallBack.onCallback(decodeResource);
        } else if (jfu.h(i)) {
            bitmapCallBack.onCallback(BitmapFactory.decodeResource(this.e.getResources(), R.mipmap._2131820663_res_0x7f110077));
        } else {
            bitmapCallBack.onCallback(BitmapFactory.decodeResource(this.e.getResources(), R.mipmap._2131820673_res_0x7f110081));
        }
    }

    private void a(final int i, final String str, final cpm cpmVar, final BitmapCallBack bitmapCallBack) {
        final String str2 = str + cpmVar.a();
        if (c.containsKey(str2) && c.get(str2) != null) {
            LogUtil.h("CardDeviceAdapter", "sBitmapList containsKey");
            bitmapCallBack.onCallback(c.get(str2));
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: ofk
            @Override // java.lang.Runnable
            public final void run() {
                CardDeviceAdapter.this.c(str, cpmVar, i, str2, bitmapCallBack);
            }
        });
    }

    public /* synthetic */ void c(String str, cpm cpmVar, int i, String str2, final BitmapCallBack bitmapCallBack) {
        final Bitmap bitmap;
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(str);
        if (pluginInfoByUuid == null || pluginInfoByUuid.f() == null) {
            bitmap = null;
        } else {
            String a2 = cwf.a(pluginInfoByUuid, 1, jpt.b(cpmVar.a(), "CardDeviceAdapter"));
            LogUtil.a("CardDeviceAdapter", "setWearDevicePluginImage image:", a2, " deviceUniqueId:", CommonUtil.l(cpmVar.a()), "version:", pluginInfoByUuid.b());
            bitmap = cYv_(pluginInfoByUuid, a2, i);
            if (bitmap != null) {
                c.put(str2, bitmap);
            }
        }
        if (bitmap == null) {
            ReleaseLogUtil.e("R_CardDeviceAdapter", "setWearDevicePluginImage loadImageForWear bitmap is null");
            if (jfu.h(i)) {
                bitmap = BitmapFactory.decodeResource(this.e.getResources(), R.mipmap._2131820663_res_0x7f110077);
            } else {
                bitmap = BitmapFactory.decodeResource(this.e.getResources(), R.mipmap._2131820673_res_0x7f110081);
            }
        }
        if (bitmap == null) {
            ReleaseLogUtil.e("R_CardDeviceAdapter", "setWearDevicePluginImage bitmap==null");
            return;
        }
        Activity cYq_ = cYq_();
        if (cYq_ == null) {
            ReleaseLogUtil.e("R_CardDeviceAdapter", "setWearDevicePluginImage activity==null");
        } else {
            cYq_.runOnUiThread(new Runnable() { // from class: ofn
                @Override // java.lang.Runnable
                public final void run() {
                    BitmapCallBack.this.onCallback(bitmap);
                }
            });
        }
    }

    private Bitmap cYv_(cvc cvcVar, String str, int i) {
        LogUtil.a("CardDeviceAdapter", "loadImageForWear enter loadImageForWear");
        if (cvcVar != null) {
            try {
                String str2 = msj.a().d(cvcVar.e()) + File.separator + cvcVar.e() + File.separator + "img" + File.separator + str + ".png";
                if (new File(str2).exists()) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    options.inInputShareable = true;
                    LogUtil.a("CardDeviceAdapter", "enter loadImageForWear have bitmap imageKey:", str, " fetchPluginUuid:", cvcVar.e());
                    return BitmapFactory.decodeFile(str2, options);
                }
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("CardDeviceAdapter", "loadImageForWear loadImage NotFoundException");
            }
        }
        if (jfu.h(i)) {
            return BitmapFactory.decodeResource(this.e.getResources(), R.mipmap._2131820663_res_0x7f110077);
        }
        return BitmapFactory.decodeResource(this.e.getResources(), R.mipmap._2131820673_res_0x7f110081);
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("CardDeviceAdapter", "getDeviceIdentification identification is null");
            return "";
        }
        if (str.replace(":", "").length() < 3) {
            LogUtil.a("CardDeviceAdapter", "identification's length less than 3");
            return Constants.LINK + str.replace(":", "");
        }
        return Constants.LINK + str.replace(":", "").substring(str.replace(":", "").length() - 3);
    }

    private void cYs_(LinearLayout linearLayout, HealthTextView healthTextView, LinearLayout linearLayout2, cpm cpmVar) {
        LogUtil.a("CardDeviceAdapter", "enter initReconnect");
        healthTextView.setClickable(true);
        if (!(this.e instanceof BaseActivity)) {
            LogUtil.h("CardDeviceAdapter", "initReconnect mContext not instanceof BaseActivity");
            return;
        }
        bdx bdxVar = new bdx(new b(this, linearLayout, healthTextView, linearLayout2, cpmVar), true, "");
        nkx.a(bdxVar, ((BaseActivity) this.e).toString());
        healthTextView.setOnClickListener(bdxVar);
    }

    static class b implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<LinearLayout> f9389a;
        private WeakReference<cpm> b;
        private WeakReference<CardDeviceAdapter> c;
        private WeakReference<HealthTextView> d;
        private WeakReference<LinearLayout> e;

        public b(CardDeviceAdapter cardDeviceAdapter, LinearLayout linearLayout, HealthTextView healthTextView, LinearLayout linearLayout2, cpm cpmVar) {
            this.c = new WeakReference<>(cardDeviceAdapter);
            this.f9389a = new WeakReference<>(linearLayout);
            this.d = new WeakReference<>(healthTextView);
            this.e = new WeakReference<>(linearLayout2);
            this.b = new WeakReference<>(cpmVar);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (nsn.o()) {
                LogUtil.h("CardDeviceAdapter", "onClick isFastClick");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (jkj.d().j()) {
                CardDeviceAdapter cardDeviceAdapter = this.c.get();
                if (cardDeviceAdapter != null) {
                    cardDeviceAdapter.f();
                }
            } else {
                CardDeviceAdapter cardDeviceAdapter2 = this.c.get();
                LinearLayout linearLayout = this.f9389a.get();
                HealthTextView healthTextView = this.d.get();
                LinearLayout linearLayout2 = this.e.get();
                cpm cpmVar = this.b.get();
                if (cardDeviceAdapter2 != null && linearLayout != null && healthTextView != null && linearLayout2 != null && cpmVar != null) {
                    cardDeviceAdapter2.cYy_(linearLayout, healthTextView, linearLayout2, cpmVar);
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cYy_(final LinearLayout linearLayout, final HealthTextView healthTextView, final LinearLayout linearLayout2, final cpm cpmVar) {
        if (Build.VERSION.SDK_INT > 30) {
            PermissionUtil.b(this.e, PermissionUtil.PermissionType.SCAN, new BluetoothPermisionUtils.NearbyPermissionAction(this.e) { // from class: com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.10
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a("CardDeviceAdapter", "nearby permission granted");
                    CardDeviceAdapter.this.cYo_(linearLayout, healthTextView, linearLayout2, cpmVar);
                }
            });
        } else if (jfu.c(cpmVar.i()).d() == 2) {
            PermissionUtil.b(this.e, PermissionUtil.PermissionType.LOCATION, new CustomPermissionAction(this.e) { // from class: com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.7
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    LogUtil.a("CardDeviceAdapter", "location permission ok.");
                    if (!oad.d(CardDeviceAdapter.this.e)) {
                        CardDeviceAdapter.this.i();
                    } else {
                        CardDeviceAdapter.this.cYo_(linearLayout, healthTextView, linearLayout2, cpmVar);
                    }
                }
            });
        } else {
            cYo_(linearLayout, healthTextView, linearLayout2, cpmVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.8
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("CardDeviceAdapter", "getHarmonyStatus getValue");
                String e = jah.c().e("scale_share_harmony_tips");
                DevicePairGuideUtil.e(e);
                LogUtil.a("CardDeviceAdapter", "getHarmonyStatus scale_share_harmony_tips: ", e);
                Activity cYq_ = CardDeviceAdapter.this.cYq_();
                if (cYq_ == null) {
                    return;
                }
                cYq_.runOnUiThread(new Runnable() { // from class: com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.8.5
                    @Override // java.lang.Runnable
                    public void run() {
                        String string;
                        if ("on".equals(DevicePairGuideUtil.d())) {
                            string = BaseApplication.getContext().getString(R.string._2130843755_res_0x7f02186b);
                        } else {
                            string = BaseApplication.getContext().getString(R.string._2130843258_res_0x7f02167a);
                        }
                        pep.d(CardDeviceAdapter.this.e, string);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cYo_(final LinearLayout linearLayout, final HealthTextView healthTextView, final LinearLayout linearLayout2, final cpm cpmVar) {
        if (BluetoothAdapter.getDefaultAdapter() != null && !BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            LogUtil.a("CardDeviceAdapter", "reConnect blue state: ", Integer.valueOf(BluetoothAdapter.getDefaultAdapter().getState()));
            NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.e).e(this.e.getResources().getString(R.string.IDS_device_bluetooth_open_request)).czC_(R.string.IDS_device_bt_right_btn_info, new View.OnClickListener() { // from class: ofh
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CardDeviceAdapter.this.cYB_(linearLayout, healthTextView, linearLayout2, cpmVar, view);
                }
            }).czz_(R.string.IDS_device_bt_left_btn_info, new View.OnClickListener() { // from class: ofg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CardDeviceAdapter.this.cYC_(view);
                }
            }).e();
            this.n = e;
            e.setCancelable(false);
            this.n.show();
            return;
        }
        cYx_(linearLayout, healthTextView, linearLayout2, cpmVar, false);
    }

    public /* synthetic */ void cYB_(final LinearLayout linearLayout, final HealthTextView healthTextView, final LinearLayout linearLayout2, final cpm cpmVar, View view) {
        LogUtil.a("CardDeviceAdapter", "user choose open BT");
        this.t = new BtSwitchStateCallback() { // from class: com.huawei.ui.homehealth.device.adapter.CardDeviceAdapter.9
            @Override // com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback
            public void onBtSwitchStateCallback(int i) {
                LogUtil.a("CardDeviceAdapter", "reconnect bluetoothSwitchState :", Integer.valueOf(i));
                if (i == 3) {
                    iyl.d().e(CardDeviceAdapter.this.t);
                    Activity cYq_ = CardDeviceAdapter.this.cYq_();
                    if (cYq_ == null) {
                        ReleaseLogUtil.d("R_CardDeviceAdapter", "reConnect onBtSwitchStateCallback activity is null mPageType: ", Integer.valueOf(CardDeviceAdapter.this.m));
                        return;
                    }
                    if (cYq_.isFinishing()) {
                        ReleaseLogUtil.d("R_CardDeviceAdapter", "reConnect onBtSwitchStateCallback activity is Finishing mPageType: ", Integer.valueOf(CardDeviceAdapter.this.m));
                    } else if (!cYq_.isDestroyed()) {
                        CardDeviceAdapter.this.cYx_(linearLayout, healthTextView, linearLayout2, cpmVar, true);
                    } else {
                        ReleaseLogUtil.d("R_CardDeviceAdapter", "reConnect onBtSwitchStateCallback activity is Destroyed mPageType: ", Integer.valueOf(CardDeviceAdapter.this.m));
                    }
                }
            }
        };
        iyl.d().d(this.t);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cYC_(View view) {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.n;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
            this.n = null;
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cYx_(LinearLayout linearLayout, HealthTextView healthTextView, LinearLayout linearLayout2, cpm cpmVar, boolean z) {
        if (z) {
            this.o = 0;
        }
        cYw_(linearLayout, cpmVar, healthTextView, linearLayout2);
        int i = this.m;
        if (i == 1) {
            this.b.h();
        } else if (i == 2) {
            this.f9380a.d();
        } else if (i == 3) {
            this.l.c(false);
        } else {
            LogUtil.h("CardDeviceAdapter", "initDeviceList else branch");
        }
        notifyDataSetChanged();
    }

    private void cYw_(LinearLayout linearLayout, cpm cpmVar, HealthTextView healthTextView, LinearLayout linearLayout2) {
        LogUtil.a("CardDeviceAdapter", "onclickReconnect deviceInfoForWear ：", cpmVar.toString());
        if (cpl.c().b(cpmVar.a()) || cpl.c().e().isEmpty()) {
            cYz_(cpmVar, linearLayout, healthTextView, linearLayout2);
        } else {
            c(cpmVar);
            cYz_(cpmVar, linearLayout, healthTextView, linearLayout2);
        }
    }

    private void c(cpm cpmVar) {
        if (cpmVar == null) {
            LogUtil.h("CardDeviceAdapter", "handleWorkMode deviceInfoForWear is null");
            return;
        }
        List<DeviceInfo> b2 = jfv.b();
        if (b2 == null) {
            LogUtil.h("CardDeviceAdapter", "handleWorkMode deviceInfoList is null");
            return;
        }
        boolean c2 = cvt.c(cpmVar.i());
        LogUtil.a("CardDeviceAdapter", "handleWorkMode goingReConnected is not AW70");
        Iterator<DeviceInfo> it = b2.iterator();
        while (it.hasNext()) {
            e(cpmVar, it.next(), c2);
        }
        cpl.c().f();
    }

    private void e(cpm cpmVar, DeviceInfo deviceInfo, boolean z) {
        if (deviceInfo == null) {
            LogUtil.h("CardDeviceAdapter", "deviceInfoList info is null");
            return;
        }
        if (cpmVar.a().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
            LogUtil.a("CardDeviceAdapter", "set device enable identify ", iyl.d().e(cpmVar.a()));
            deviceInfo.setDeviceActiveState(1);
            deviceInfo.setDeviceConnectState(1);
        } else {
            if (z) {
                if (cvt.c(deviceInfo.getProductType()) && deviceInfo.getDeviceActiveState() == 1) {
                    LogUtil.a("CardDeviceAdapter", "set aw70 device disable identify ", iyl.d().e(deviceInfo.getDeviceIdentify()));
                    deviceInfo.setDeviceActiveState(0);
                    deviceInfo.setDeviceConnectState(3);
                    return;
                }
                return;
            }
            if (deviceInfo.getAutoDetectSwitchStatus() == 1 || deviceInfo.getDeviceActiveState() != 1) {
                return;
            }
            LogUtil.a("CardDeviceAdapter", "set band mode device disable identify ", iyl.d().e(deviceInfo.getDeviceIdentify()));
            deviceInfo.setDeviceActiveState(0);
            deviceInfo.setDeviceConnectState(3);
        }
    }

    private void cYz_(cpm cpmVar, LinearLayout linearLayout, HealthTextView healthTextView, LinearLayout linearLayout2) {
        LogUtil.a("CardDeviceAdapter", "startReconnect mReconnectCount:", Integer.valueOf(this.o), "deviceInfoForWear.getIsCloudDevice:", Boolean.valueOf(cpmVar.g()));
        if (cpmVar.g()) {
            cYp_(cpmVar, linearLayout, healthTextView, linearLayout2);
        } else if (cpmVar.g() || this.o >= 3) {
            LogUtil.h("CardDeviceAdapter", "startReconnect else, mReconnectCount = ", Integer.valueOf(this.o));
        } else {
            cYp_(cpmVar, linearLayout, healthTextView, linearLayout2);
            this.o++;
        }
    }

    private void cYp_(cpm cpmVar, LinearLayout linearLayout, HealthTextView healthTextView, LinearLayout linearLayout2) {
        int i = this.m;
        if (i == 1) {
            this.b.a(cpmVar.a());
        } else if (i == 2) {
            this.f9380a.c(cpmVar.a());
        } else if (i == 3) {
            this.l.d(cpmVar.a());
        } else {
            LogUtil.h("CardDeviceAdapter", "setReconnectDevice else");
        }
        a(true);
        ReleaseLogUtil.e("R_CardDeviceAdapter", "connectDeviceView mPageType = ", Integer.valueOf(this.m));
        this.f.onReconnect(cpmVar);
        healthTextView.setVisibility(8);
        linearLayout.setVisibility(8);
        linearLayout2.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.e).e(this.e.getResources().getString(R.string.IDS_main_device_ota_error_message)).czC_(R.string._2130841554_res_0x7f020fd2, new View.OnClickListener() { // from class: off
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CardDeviceAdapter.cYu_(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    public static /* synthetic */ void cYu_(View view) {
        LogUtil.a("CardDeviceAdapter", "showTipDialog，click known button");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void e() {
        LogUtil.a("CardDeviceAdapter", "mReconnectCount = ", Integer.valueOf(this.o));
        this.o = 0;
    }

    public int b() {
        LogUtil.a("CardDeviceAdapter", "mReconnectCount = ", Integer.valueOf(this.o));
        return this.o;
    }

    public void a(boolean z) {
        this.g = z;
        LogUtil.a("CardDeviceAdapter", "enter mIsConnecting ", Boolean.valueOf(z));
    }

    public boolean a() {
        return this.g;
    }

    public static class CardDeviceViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        ImageView f9387a;
        HealthTextView aa;
        LinearLayout ab;
        RelativeLayout ac;
        HealthTextView ad;
        HealthTextView ae;
        ImageView af;
        HealthTextView ag;
        RelativeLayout ah;
        ImageView ai;
        HealthTextView aj;
        LinearLayout ak;
        HealthCardView b;
        RelativeLayout c;
        public cpm d;
        RelativeLayout e;
        LinearLayout f;
        HealthTextView g;
        ImageView h;
        HealthTextView i;
        RelativeLayout j;
        RelativeLayout k;
        HealthTextView l;
        ImageView m;
        ImageView n;
        LinearLayout o;
        HealthTextView p;
        RelativeLayout q;
        LinearLayout r;
        ImageView s;
        HealthProgressBar t;
        HealthTextView u;
        HealthTextView v;
        HealthTextView w;
        LinearLayout x;
        HealthTextView y;
        LinearLayout z;

        public CardDeviceViewHolder(View view) {
            super(view);
            this.h = (ImageView) view.findViewById(R.id.equipment_image);
            this.f9387a = (ImageView) view.findViewById(R.id.equipment_image_cloud);
            this.m = (ImageView) view.findViewById(R.id.equipment_three_image);
            this.s = (ImageView) view.findViewById(R.id.achieve_user_device_red_dot);
            this.af = (ImageView) view.findViewById(R.id.sport_equipment_red_dot);
            this.i = (HealthTextView) view.findViewById(R.id.equipment_name);
            this.g = (HealthTextView) view.findViewById(R.id.equipment_connect_state);
            this.ag = (HealthTextView) view.findViewById(R.id.tripartite_equipment_name);
            this.ae = (HealthTextView) view.findViewById(R.id.tripartite_equipment_Describe);
            this.ai = (ImageView) view.findViewById(R.id.tripartite_device_red_dot);
            this.x = (LinearLayout) view.findViewById(R.id.reconnect_loading_layout);
            this.y = (HealthTextView) view.findViewById(R.id.reconnect_loading_text);
            HealthProgressBar healthProgressBar = (HealthProgressBar) view.findViewById(R.id.reconnect_loading_img);
            this.t = healthProgressBar;
            healthProgressBar.setLayerType(1, null);
            this.u = (HealthTextView) view.findViewById(R.id.retry_connect);
            this.v = (HealthTextView) view.findViewById(R.id.retry_connect_oversea);
            this.o = (LinearLayout) view.findViewById(R.id.equipment_power);
            this.r = (LinearLayout) view.findViewById(R.id.left_earphone_power_layout);
            this.ab = (LinearLayout) view.findViewById(R.id.right_earphone_power_layout);
            this.p = (HealthTextView) view.findViewById(R.id.left_earphone_power_size);
            this.w = (HealthTextView) view.findViewById(R.id.right_earphone_power_size);
            this.l = (HealthTextView) view.findViewById(R.id.equipment_power_size);
            this.n = (ImageView) view.findViewById(R.id.equipment_power_pic);
            this.q = (RelativeLayout) view.findViewById(R.id.personal_equipment_layout);
            this.e = (RelativeLayout) view.findViewById(R.id.card_equipment_layout);
            this.k = (RelativeLayout) view.findViewById(R.id.equipment_layout);
            this.f = (LinearLayout) view.findViewById(R.id.equipment_connect);
            this.ah = (RelativeLayout) view.findViewById(R.id.tripartite_equipment_layout);
            this.b = (HealthCardView) view.findViewById(R.id.device_card_more_card_view);
            this.c = (RelativeLayout) view.findViewById(R.id.device_left_icon);
            this.ak = (LinearLayout) view.findViewById(R.id.wear_status_layout);
            this.ad = (HealthTextView) view.findViewById(R.id.sport_equipment_name);
            this.aa = (HealthTextView) view.findViewById(R.id.sport_num_tv);
            this.z = (LinearLayout) view.findViewById(R.id.sport_equipment_bg_layout);
            this.j = (RelativeLayout) view.findViewById(R.id.equipment_name_layout);
            this.ac = (RelativeLayout) view.findViewById(R.id.sport_equipment_name_layout);
            this.aj = (HealthTextView) view.findViewById(R.id.wear_place);
        }
    }

    public static class MoreViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        RelativeLayout f9388a;
        ImageView b;
        RelativeLayout d;
        LinearLayout e;

        public MoreViewHolder(View view) {
            super(view);
            this.e = (LinearLayout) view.findViewById(R.id.device_card_more_layout);
            this.b = (ImageView) view.findViewById(R.id.device_card_more_img);
            this.d = (RelativeLayout) view.findViewById(R.id.device_equipment_layout);
            this.f9388a = (RelativeLayout) view.findViewById(R.id.device_left_icon);
        }
    }

    private void b(CardDeviceViewHolder cardDeviceViewHolder) {
        LogUtil.a("CardDeviceAdapter", "updateResourceInfoUnEnable disconnected");
        cardDeviceViewHolder.g.setAlpha(0.38f);
        cardDeviceViewHolder.i.setAlpha(0.38f);
        cardDeviceViewHolder.h.setAlpha(0.38f);
    }

    private void a(CardDeviceViewHolder cardDeviceViewHolder) {
        LogUtil.a("CardDeviceAdapter", "updateResourceInfoEnable connected");
        cardDeviceViewHolder.g.setAlpha(1.0f);
        cardDeviceViewHolder.i.setAlpha(1.0f);
        cardDeviceViewHolder.h.setAlpha(1.0f);
    }
}
