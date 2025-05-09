package com.huawei.ui.homehealth.search.dataprovider;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.google.android.gms.wearable.PutDataRequest;
import com.huawei.browse.BrowsingBiEvent;
import com.huawei.health.R;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.device.activity.adddevice.PairingGuideActivity;
import com.huawei.ui.homehealth.search.GlobalSearchActivity;
import com.huawei.ui.homehealth.search.SearchResultFragment;
import com.huawei.ui.homehealth.search.dataprovider.DeviceSearchProvider;
import com.huawei.ui.main.R$string;
import defpackage.bkz;
import defpackage.cjv;
import defpackage.cpm;
import defpackage.cvc;
import defpackage.cwf;
import defpackage.cww;
import defpackage.dcp;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dij;
import defpackage.dks;
import defpackage.fbh;
import defpackage.jdx;
import defpackage.jfu;
import defpackage.jfv;
import defpackage.jpt;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nuc;
import defpackage.nyq;
import defpackage.oae;
import defpackage.ofr;
import defpackage.otb;
import defpackage.pep;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class DeviceSearchProvider extends BaseKnitDataProvider<Object> {
    private List<String> c;
    private Boolean e;

    /* renamed from: a, reason: collision with root package name */
    private final nuc f9600a = new nuc();
    private final oae d = oae.c(BaseApplication.getContext());

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap<String, Object> hashMap, Object obj) {
        b(context, hashMap, obj);
    }

    private void b(Context context, HashMap<String, Object> hashMap, Object obj) {
        hashMap.clear();
        if (obj instanceof Map) {
            boolean a2 = otb.a(this);
            Map map = (Map) obj;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            hashMap.put("TITLE", context.getString(R$string.IDS_device_title_use));
            int i = 203;
            if (a2) {
                hashMap.put("SHOWMORE", context.getString(R.string.IDS_device_show_more_heartrate_device));
                otb.a(context, "SHOW_MORE_CLICK_EVENT", hashMap, 203);
                hashMap.put("ITEM_LIMIT", 3);
            }
            if (koq.e(map.get("CONNECTED_DEVICE_KEY"), cjv.class)) {
                Iterator it = ((List) map.get("CONNECTED_DEVICE_KEY")).iterator();
                while (it.hasNext()) {
                    d(context, (cjv) it.next(), arrayList, arrayList2, arrayList3, arrayList4);
                    i = i;
                }
            }
            int i2 = i;
            if (koq.e(map.get("ALL_DEVICE_KEY"), nyq.class)) {
                for (nyq nyqVar : (List) map.get("ALL_DEVICE_KEY")) {
                    if (nyqVar != null) {
                        arrayList.add(nyqVar.h());
                        arrayList2.add(nyqVar.c());
                        arrayList3.add(nyqVar.d() == 1 ? Integer.valueOf(nyqVar.l()) : nyqVar.i());
                        arrayList4.add("");
                    }
                }
            }
            this.c = arrayList;
            hashMap.put("ITEM_BI_EVENT_MAP", otb.b(i2, a2 ? 200 : i2, GlobalSearchActivity.d(), this.c, arrayList));
            hashMap.put("BI_OBSERVER_LABEL", fbh.d(a2 ? 200 : i2));
            hashMap.put("ITEM_TITLE", arrayList);
            hashMap.put("ITEM_SUBTITLE", arrayList2);
            hashMap.put("ITEM_IMAGE", arrayList3);
            hashMap.put("ITEM_RIGHT_BTN", arrayList4);
            hashMap.put("HIGHLIGHTED_TEXT", GlobalSearchActivity.d());
            a(context, hashMap, obj, a2);
        }
    }

    private void d(Context context, cjv cjvVar, List<String> list, List<String> list2, List<Object> list3, List<String> list4) {
        if (cjvVar == null) {
            return;
        }
        int a2 = cjvVar.a();
        if (a2 == 0) {
            b(cjvVar, list, list2, list3, list4);
        } else {
            if (a2 != 1) {
                return;
            }
            c(context, cjvVar, list, list2, list3, list4);
        }
    }

    private void c(Context context, cjv cjvVar, List<String> list, List<String> list2, List<Object> list3, List<String> list4) {
        Object i = cjvVar.i();
        if (!(i instanceof cpm)) {
            LogUtil.h("DeviceSearchProvider", "setWearDevice DeviceInfoForWear is null");
            return;
        }
        cpm cpmVar = (cpm) i;
        String d = cpmVar.d();
        if (TextUtils.isEmpty(d)) {
            d = pep.b(context, cpmVar.a());
        } else if (d.contains(Constants.LINK)) {
            d = d.replace(Constants.LINK, " -");
        }
        LogUtil.a("DeviceSearchProvider", "deviceName: ", d);
        list.add(d);
        list2.add("");
        list3.add(dgV_(cpmVar));
        if (c(cpmVar)) {
            list4.add(nsf.h(R.string.IDS_device_list_manage));
        } else {
            list4.add(nsf.h(R.string.IDS_device_list_pair));
        }
    }

    private Bitmap dgV_(cpm cpmVar) {
        int i = cpmVar.i();
        LogUtil.a("DeviceSearchProvider", "getView wear device deviceType is : ", Integer.valueOf(i));
        if (jfu.m(i)) {
            LogUtil.a("DeviceSearchProvider", "is plugin download");
            String j = jfu.j(i);
            LogUtil.a("DeviceSearchProvider", "is plugin download uuid:", j);
            boolean isResourcesAvailable = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).isResourcesAvailable(j);
            LogUtil.a("DeviceSearchProvider", "is plugin download isPluginAvailable:", Boolean.valueOf(isResourcesAvailable));
            if (isResourcesAvailable) {
                cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(j);
                LogUtil.a("DeviceSearchProvider", "getPluginInfo success update ui");
                return dgW_(i, pluginInfoByUuid, cpmVar);
            }
            return dgT_(i);
        }
        LogUtil.a("DeviceSearchProvider", "set imageView default.");
        if (!TextUtils.isEmpty(cpmVar.d()) && cpmVar.d().contains("HUAWEI CM-R1P")) {
            return nrf.cHF_(nsf.cKq_(R.mipmap._2131821232_res_0x7f1102b0));
        }
        return dgU_(i, cpmVar);
    }

    private Bitmap dgW_(int i, cvc cvcVar, cpm cpmVar) {
        if (cvcVar != null && cvcVar.f() != null) {
            String a2 = cwf.a(cvcVar, 1, jpt.b(cpmVar.a(), "DeviceSearchProvider"));
            LogUtil.a("DeviceSearchProvider", "is plugin download image:", a2);
            return ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(cvcVar, a2);
        }
        return dgT_(i);
    }

    private Bitmap dgU_(int i, cpm cpmVar) {
        Bitmap cHF_ = nrf.cHF_(nsf.cKq_(CommonUtil.h(cpmVar.f())));
        return cHF_ != null ? cHF_ : dgT_(i);
    }

    private Bitmap dgT_(int i) {
        if (jfu.h(i)) {
            return nrf.cHF_(nsf.cKq_(R.mipmap._2131820663_res_0x7f110077));
        }
        return nrf.cHF_(nsf.cKq_(R.mipmap._2131820673_res_0x7f110081));
    }

    private void b(cjv cjvVar, List<String> list, List<String> list2, List<Object> list3, List<String> list4) {
        String str;
        String d;
        String d2;
        Bitmap TK_;
        Object i = cjvVar.i();
        if (!(i instanceof dcz)) {
            LogUtil.h("DeviceSearchProvider", "setThreeWayDevice ProductInfo is null");
            return;
        }
        dcz dczVar = (dcz) i;
        ContentValues FT_ = cjvVar.FT_();
        if (FT_ == null) {
            str = "";
        } else if (!TextUtils.isEmpty(FT_.getAsString("sn"))) {
            str = FT_.getAsString("sn");
        } else {
            str = FT_.getAsString("uniqueId");
        }
        if (dczVar.e().size() <= 0) {
            LogUtil.a("DeviceSearchProvider", "item.getDescriptions().size() <= 0");
            d = d(dczVar.n().b(), str);
            d2 = dczVar.n().c();
            TK_ = nrf.cHF_(nsf.cKq_(dcx.a(dczVar.n().d())));
        } else {
            LogUtil.a("DeviceSearchProvider", "item.getDescriptions().size() > 0");
            String str2 = "9bf158ba-49b0-46aa-9fdf-ed75da1569cf".equals(dczVar.t()) ? "" : str;
            if (!TextUtils.isEmpty(str2)) {
                d = d(dcx.d(dczVar.t(), dczVar.n().b()), str2);
            } else {
                d = dcx.d(dczVar.t(), dczVar.n().b());
            }
            d2 = dcx.d(dczVar.t(), dczVar.n().c());
            TK_ = dcx.TK_(dcq.b().a(dczVar.t(), dczVar.n().d()));
        }
        if (d == null || d2 == null || TK_ == null) {
            return;
        }
        list.add(d);
        list2.add(d2);
        list3.add(TK_);
        if ("4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c".equals(dczVar.t()) && dij.Vb_(FT_)) {
            list4.add(nsf.h(R.string.IDS_device_list_pair));
        } else {
            list4.add(nsf.h(R.string.IDS_device_list_manage));
        }
    }

    private String d(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceSearchProvider", "name is empty");
            return "";
        }
        if (str.toUpperCase().contains(e(str2).toUpperCase())) {
            return str;
        }
        return str + e(str2).toUpperCase();
    }

    private String e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("DeviceSearchProvider", "getDeviceIdentification identification is null");
            return "";
        }
        if (str.replace(":", "").length() < 3) {
            LogUtil.a("DeviceSearchProvider", "identification's length less than 3");
            return Constants.LINK + str.replace(":", "");
        }
        return Constants.LINK + str.replace(":", "").substring(str.replace(":", "").length() - 3);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return !EnvironmentInfo.k();
    }

    private void a(Context context, Map<String, Object> map, Object obj, boolean z) {
        if (obj instanceof Map) {
            Map map2 = (Map) obj;
            List<cjv> list = koq.e(map2.get("CONNECTED_DEVICE_KEY"), cjv.class) ? (List) map2.get("CONNECTED_DEVICE_KEY") : null;
            c(context, map, z, list == null ? 0 : list.size(), list, koq.e(map2.get("ALL_DEVICE_KEY"), nyq.class) ? (List) map2.get("ALL_DEVICE_KEY") : null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Context context, cjv cjvVar) {
        try {
            if (cjvVar.a() == 0) {
                dcz dczVar = (dcz) cjvVar.i();
                ContentValues FT_ = cjvVar.FT_();
                if (dczVar != null && FT_ != null) {
                    String asString = FT_.getAsString("uniqueId");
                    String t = dczVar.t();
                    if ("1".equals(dczVar.j())) {
                        dks.d(context, dczVar, t, asString);
                        return;
                    }
                    if (BleConstants.BLE_THIRD_DEVICE_H5.equals(dczVar.m().d())) {
                        e(context, t, asString, dczVar.m().d());
                        return;
                    }
                    if ("4bfc5a27-f2b9-4c41-bd9b-a4a2a18f752c".equals(t)) {
                        dgS_(context, FT_);
                        return;
                    }
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    intent.setAction("SWITCH_PLUGINDEVICE");
                    bundle.putString("arg1", "DeviceInfoList");
                    bundle.putString("productId", dczVar.t());
                    if (asString == null) {
                        asString = "";
                    }
                    bundle.putString("uniqueId", asString);
                    intent.setPackage(ofr.d);
                    intent.setClassName(ofr.d, "com.huawei.health.device.ui.DeviceMainActivity");
                    bundle.putParcelable("commonDeviceInfo", FT_);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                    return;
                }
                LogUtil.a("DeviceSearchProvider", "productInfo or deviceInfo is null");
                return;
            }
            if (cjvVar.a() == 1) {
                cpm cpmVar = (cpm) cjvVar.i();
                if (c(cpmVar)) {
                    e(context, cpmVar.a());
                    return;
                } else {
                    c(context, cpmVar);
                    return;
                }
            }
            if (cjvVar.a() == 3) {
                context.startActivity(cww.QP_(cjvVar));
            } else {
                LogUtil.a("DeviceSearchProvider", "other device");
            }
        } catch (ActivityNotFoundException e) {
            LogUtil.b("DeviceSearchProvider", "deviceConnect exception", e.getMessage());
        }
    }

    private void dgS_(Context context, ContentValues contentValues) {
        LogUtil.a("DeviceSearchProvider", "enter dealJumpNemoActivity...");
        if (context == null) {
            LogUtil.h("DeviceSearchProvider", "context is null");
            return;
        }
        if (dij.Vb_(contentValues)) {
            Intent intent = new Intent(context, (Class<?>) DeviceMainActivity.class);
            intent.putExtra("PID_FROM_QRCODE", "ZAA6");
            intent.putExtra("productId", contentValues.getAsString("productId"));
            intent.putExtra("Device_Type", "082");
            intent.putExtra("macAddress", contentValues.getAsString("uniqueId"));
            context.startActivity(intent);
            return;
        }
        new dcp().d(context, contentValues.getAsString("uniqueId"));
    }

    private boolean c(cpm cpmVar) {
        boolean booleanValue;
        boolean z = true;
        if (cpmVar.e() != 2) {
            Boolean bool = this.e;
            if (bool != null) {
                booleanValue = bool.booleanValue();
            } else {
                ArrayList<cpm> a2 = jfv.a();
                if (a2.size() == 1) {
                    Boolean valueOf = Boolean.valueOf(a2.get(0).g());
                    this.e = valueOf;
                    booleanValue = valueOf.booleanValue();
                } else {
                    z = false;
                }
            }
            z = true ^ booleanValue;
        }
        LogUtil.a("DeviceSearchProvider", "isConnectedDevice = ", Boolean.valueOf(z));
        return z;
    }

    private void c(Context context, cpm cpmVar) {
        Intent intent = new Intent();
        intent.setClassName(context, "com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect");
        intent.putExtra("is_cloud_device", cpmVar.g());
        intent.putExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME, cpmVar.d());
        intent.putExtra("device_identify", cpmVar.a());
        intent.putExtra("device_picID", cpmVar.m());
        intent.putExtra(DeviceCategoryFragment.DEVICE_TYPE, cpmVar.i());
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("DeviceSearchProvider", "ActivityNotFoundException", e.getMessage());
        }
        pep.d(context);
        LogUtil.a("DeviceSearchProvider", "onclick wear not connected name:", cpmVar.d(), "device_type :", Integer.valueOf(cpmVar.i()));
    }

    private void e(Context context, String str) {
        LogUtil.a("DeviceSearchProvider", "Enter openWearHome ");
        if (HwVersionManager.c(BaseApplication.getContext()).k(str)) {
            LogUtil.a("DeviceSearchProvider", "Enter openWearHome other device is OTAing");
            e(context, R.string.IDS_main_device_ota_error_message);
            return;
        }
        if (jpt.d("DeviceSearchProvider") != null) {
            try {
                if (HwVersionManager.c(BaseApplication.getContext()).o(str)) {
                    LogUtil.a("DeviceSearchProvider", "wear device is OTAing");
                    Intent intent = new Intent();
                    intent.setClassName(context, "com.huawei.ui.device.activity.update.UpdateVersionActivity");
                    intent.putExtra("device_id", str);
                    context.startActivity(intent);
                }
            } catch (ActivityNotFoundException e) {
                LogUtil.b("DeviceSearchProvider", "ActivityNotFoundException", e.getMessage());
                return;
            }
        }
        SharedPreferenceManager.e(context, String.valueOf(10099), "key_ui_nps_enter_wear_home", "true", (StorageParams) null);
        Intent intent2 = new Intent();
        intent2.setClassName(context, "com.huawei.ui.homewear21.home.WearHomeActivity");
        intent2.putExtra("device_id", str);
        context.startActivity(intent2);
        pep.d(context);
    }

    private void e(Context context, int i) {
        if (context == null) {
            LogUtil.h("DeviceSearchProvider", "showTipDialog mContext is null");
            return;
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(context).e(context.getResources().getString(i)).czC_(R.string._2130843756_res_0x7f02186c, new View.OnClickListener() { // from class: ost
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceSearchProvider.dgX_(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    public static /* synthetic */ void dgX_(View view) {
        LogUtil.a("DeviceSearchProvider", "showTipDialog，click known button");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(Context context, String str, String str2, String str3) {
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getContext().getPackageName());
        intent.setClassName(BaseApplication.getContext().getPackageName(), "com.huawei.operation.activity.WebViewActivity");
        intent.putExtra("url", dcq.b().c(str));
        intent.putExtra("productId", str);
        dcz d = ResourceManager.e().d(str);
        intent.putExtra("uniqueId", str2);
        if (d != null) {
            if (d.n() != null) {
                intent.putExtra("name", d.n().b());
            }
            if (d.l() != null) {
                intent.putExtra("deviceType", d.l().name());
            }
            if (d.x() != null) {
                intent.putExtra(com.huawei.operation.utils.Constants.KEY_BLE_SCAN_MODE, d.x().c());
            }
        }
        intent.putExtra("bleIntroductionType", str3);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("DeviceSearchProvider", "ActivityNotFoundException", e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Context context, nyq nyqVar) {
        SearchResultFragment a2;
        char c;
        Activity activity = BaseApplication.getActivity();
        if ((activity instanceof GlobalSearchActivity) && (a2 = ((GlobalSearchActivity) activity).a()) != null) {
            String j = nyqVar.j();
            LogUtil.a("DeviceSearchProvider", "pairGuide,:", j);
            if (TextUtils.isEmpty(j)) {
                LogUtil.h("DeviceSearchProvider", "pairGuid is empty");
                return;
            }
            j.hashCode();
            int hashCode = j.hashCode();
            if (hashCode == 51) {
                if (j.equals("3")) {
                    c = 0;
                }
                c = 65535;
            } else if (hashCode != 52) {
                if (hashCode == 54 && j.equals("6")) {
                    c = 2;
                }
                c = 65535;
            } else {
                if (j.equals("4")) {
                    c = 1;
                }
                c = 65535;
            }
            if (c == 0) {
                LogUtil.h("DeviceSearchProvider", "this device is no pair guide");
                if (nyqVar.f().startsWith("HDK")) {
                    b(nyqVar, a2);
                    return;
                } else {
                    this.f9600a.e(nyqVar, context, this.d);
                    return;
                }
            }
            if (c == 1) {
                if (nyqVar.f().startsWith("HDK")) {
                    this.f9600a.a(context, nyqVar);
                    return;
                } else {
                    this.f9600a.b(nyqVar, context);
                    return;
                }
            }
            if (c == 2) {
                if (nyqVar.f().startsWith("HDK")) {
                    LogUtil.a("DeviceSearchProvider", "not support currently");
                }
            } else if (nyqVar.f().startsWith("HDK")) {
                if (bkz.e(nyqVar.s())) {
                    return;
                }
                b(nyqVar, a2);
            } else if (nyqVar.f().startsWith(PutDataRequest.WEAR_URI_SCHEME)) {
                b(nyqVar, context);
            } else if (nyqVar.f().equals("SMART_HEADPHONES")) {
                b(nyqVar, context);
            } else {
                LogUtil.h("DeviceSearchProvider", "invalid pairGuide");
            }
        }
    }

    private void b(nyq nyqVar, Context context) {
        String j = nyqVar.j();
        LogUtil.a("DeviceSearchProvider", "pairGuide,:", j);
        if (TextUtils.isEmpty(j)) {
            LogUtil.h("DeviceSearchProvider", "pairGuid is empty");
            return;
        }
        Intent intent = new Intent(context, (Class<?>) PairingGuideActivity.class);
        List<String> s = nyqVar.s();
        if (bkz.e(s)) {
            LogUtil.h("DeviceSearchProvider", "uuidList is empty");
            return;
        }
        if (s instanceof ArrayList) {
            intent.putStringArrayListExtra("uuid_list", (ArrayList) s);
        }
        intent.putExtra("kind_id", nyqVar.f());
        intent.putExtra("pair_guide", j);
        intent.putExtra("bluetooth_type", nyqVar.b());
        intent.putExtra(AdShowExtras.DOWNLOAD_SOURCE, nyqVar.g());
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("DeviceSearchProvider", "ActivityNotFoundException", e.getMessage());
        }
    }

    private void b(nyq nyqVar, SearchResultFragment searchResultFragment) {
        LogUtil.a("DeviceSearchProvider", "startThirdDevicePairGuide");
        if (nyqVar == null || TextUtils.isEmpty(nyqVar.j())) {
            LogUtil.a("DeviceSearchProvider", "startThirdDevicePairGuide allDeviceItem or pairGuid is null");
        } else if (nyqVar.j().equals("3")) {
            searchResultFragment.a(nyqVar);
        } else {
            a(nyqVar, searchResultFragment.getContext());
        }
    }

    private void a(nyq nyqVar, Context context) {
        if (nyqVar == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) PairingGuideActivity.class);
        intent.putExtra("kind_id", nyqVar.f());
        intent.putExtra("pair_guide", nyqVar.j());
        intent.putExtra("bluetooth_type", nyqVar.b());
        intent.putExtra(AdShowExtras.DOWNLOAD_SOURCE, nyqVar.g());
        List<String> s = nyqVar.s();
        if (s instanceof ArrayList) {
            intent.putStringArrayListExtra("uuid_list", (ArrayList) s);
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("DeviceSearchProvider", "ActivityNotFoundException", e.getMessage());
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData, reason: merged with bridge method [inline-methods] */
    public void a(final Context context, final SectionBean sectionBean) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            jdx.b(new Runnable() { // from class: osv
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceSearchProvider.this.a(context, sectionBean);
                }
            });
            return;
        }
        Activity activity = BaseApplication.getActivity();
        if (!(activity instanceof GlobalSearchActivity)) {
            sectionBean.e((Object) null);
            return;
        }
        SearchResultFragment a2 = ((GlobalSearchActivity) activity).a();
        if (a2 == null) {
            sectionBean.e((Object) null);
            return;
        }
        Object b = a2.b(203);
        if (e(b)) {
            sectionBean.e(b);
        } else {
            LogUtil.b("DeviceSearchProvider", "check result return false");
            sectionBean.e((Object) null);
        }
    }

    private boolean e(Object obj) {
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (map.containsKey("ALL_DEVICE_KEY") && map.containsKey("CONNECTED_DEVICE_KEY")) {
            Object obj2 = map.get("ALL_DEVICE_KEY");
            Object obj3 = map.get("CONNECTED_DEVICE_KEY");
            if (koq.e(obj2, nyq.class) && koq.e(obj3, cjv.class)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: com.huawei.ui.homehealth.search.dataprovider.DeviceSearchProvider$2, reason: invalid class name */
    public class AnonymousClass2 implements OnClickSectionListener {
        final /* synthetic */ List b;
        final /* synthetic */ List c;
        final /* synthetic */ Context d;
        final /* synthetic */ int e;
        final /* synthetic */ boolean f;

        public static /* synthetic */ void d(int i, Object obj) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, int i2) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, String str) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(String str) {
        }

        AnonymousClass2(Context context, boolean z, int i, List list, List list2) {
            this.d = context;
            this.f = z;
            this.e = i;
            this.b = list;
            this.c = list2;
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i) {
            if (nsn.o()) {
                LogUtil.a("DeviceSearchProvider", "click too fast");
                return;
            }
            BrowsingBiEvent browsingBiEvent = LoginInit.getBrowsingBiEvent();
            if (SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false)) {
                if (browsingBiEvent != null) {
                    browsingBiEvent.showFullServiceDialog(this.d);
                    return;
                }
                return;
            }
            if (koq.d(DeviceSearchProvider.this.c, i)) {
                fbh.b(this.d, GlobalSearchActivity.d(), 203, (String) DeviceSearchProvider.this.c.get(i), (String) DeviceSearchProvider.this.c.get(i), this.f);
            }
            if (i < this.e && koq.d(this.b, i)) {
                if (!LoginInit.getInstance(this.d).getIsLogined()) {
                    LoginInit.getInstance(this.d).browsingToLogin(new IBaseResponseCallback() { // from class: oss
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public final void d(int i2, Object obj) {
                            DeviceSearchProvider.AnonymousClass2.d(i2, obj);
                        }
                    }, "");
                    return;
                } else {
                    DeviceSearchProvider.this.d(this.d, (cjv) this.b.get(i));
                    return;
                }
            }
            if (koq.d(this.c, i - this.e)) {
                nyq nyqVar = (nyq) this.c.get(i - this.e);
                Context activity = BaseApplication.getActivity();
                DeviceSearchProvider deviceSearchProvider = DeviceSearchProvider.this;
                if (!(activity instanceof GlobalSearchActivity)) {
                    activity = this.d;
                }
                deviceSearchProvider.d(activity, nyqVar);
                return;
            }
            LogUtil.h("DeviceSearchProvider", "invalid position: ", Integer.valueOf(i));
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void c(Context context, Map<String, Object> map, boolean z, int i, List<cjv> list, List<nyq> list2) {
        map.put("CLICK_EVENT_LISTENER", new AnonymousClass2(context, z, i, list, list2));
    }
}
