package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.R;
import com.huawei.health.device.wifi.entity.utils.JsonParser;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDataModelActivity;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDetailActivity;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes7.dex */
public class rud {
    public static void e(final Context context, final CustomTitleBar customTitleBar, final int i) {
        customTitleBar.setRightButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
        customTitleBar.setRightButtonClickable(true);
        customTitleBar.setRightButtonVisibility(0);
        customTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: rue
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                rud.dQT_(context, customTitleBar, i, view);
            }
        });
    }

    static /* synthetic */ void dQT_(final Context context, CustomTitleBar customTitleBar, final int i, View view) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.common_toolbar_popupwindow, (ViewGroup) null);
        final nqc nqcVar = new nqc(context, inflate);
        nqcVar.cEh_(customTitleBar, 17);
        inflate.findViewById(R.id.popup_declare_ll).setOnClickListener(new View.OnClickListener() { // from class: rud.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                nqc.this.b();
                rud.c(i, context);
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void c(int i, Context context) {
        PageModelArgs pageModelArgs = new PageModelArgs(i, "PrivacyDataConstructor", 3, 1);
        pageModelArgs.setClassType(0);
        PrivacyDataModelActivity.b(context, pageModelArgs);
    }

    public static void c(final Context context, final CustomTitleBar customTitleBar, final int i, final PrivacyDataModel privacyDataModel) {
        customTitleBar.setRightButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
        customTitleBar.setRightButtonClickable(true);
        customTitleBar.setRightButtonVisibility(0);
        customTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: rug
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                rud.dQU_(context, customTitleBar, i, privacyDataModel, view);
            }
        });
    }

    static /* synthetic */ void dQU_(final Context context, CustomTitleBar customTitleBar, final int i, final PrivacyDataModel privacyDataModel, View view) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.common_toolbar_popupwindow, (ViewGroup) null);
        final nqc nqcVar = new nqc(context, inflate);
        nqcVar.cEh_(customTitleBar, 17);
        ((HealthTextView) inflate.findViewById(R.id.all_data_declare_text)).setText(R$string.IDS_privacy_data_detail);
        inflate.findViewById(R.id.popup_declare_ll).setOnClickListener(new View.OnClickListener() { // from class: rud.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                nqc.this.b();
                PageModelArgs pageModelArgs = new PageModelArgs();
                pageModelArgs.setDataSource(3);
                pageModelArgs.setPageType(i);
                Intent intent = new Intent();
                intent.putExtra("extra_privacy_data_model", privacyDataModel);
                intent.putExtra("extra_page_model_args", pageModelArgs);
                intent.setClass(context, PrivacyDetailActivity.class);
                context.startActivity(intent);
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void c() {
        b(ceo.d().f(), a());
    }

    private static void b(ArrayList<ContentValues> arrayList, boolean z) {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("privacy_data_source_device_name_4F32F873AE094ACAA75E35D53690B72C");
        Map hashMap = new HashMap(16);
        if (userPreference == null) {
            userPreference = new HiUserPreference();
        } else {
            hashMap = JsonParser.c(userPreference.getValue());
            if (z) {
                hashMap.clear();
            }
            userPreference.setSyncStatus(0);
        }
        d(hashMap, arrayList);
        userPreference.setKey("privacy_data_source_device_name_4F32F873AE094ACAA75E35D53690B72C");
        userPreference.setValue(JsonParser.b((Map<?, ?>) hashMap).toString());
        LogUtil.a("PrivacyDataUtil", "privacy hiUserPreference set ", Boolean.valueOf(HiHealthManager.d(BaseApplication.getContext()).setUserPreference(userPreference)));
        b();
    }

    private static void d(Map<String, Object> map, ArrayList<ContentValues> arrayList) {
        dcz d;
        Iterator<ContentValues> it = arrayList.iterator();
        while (it.hasNext()) {
            ContentValues next = it.next();
            String asString = next.getAsString("productId");
            String asString2 = next.getAsString("uniqueId");
            if (asString2 != null && asString != null && (d = ResourceManager.e().d(asString)) != null) {
                String asString3 = next.getAsString("sn");
                String d2 = dcx.d(asString, d.n().b());
                if (!TextUtils.isEmpty(asString3)) {
                    String str = d2 + Constants.LINK + c(asString3);
                    map.put(asString2, str);
                    map.put(asString3, str);
                } else {
                    map.put(asString2, d2 + Constants.LINK + c(asString2));
                }
            }
        }
    }

    private static String c(String str) {
        String replaceAll = str.replaceAll(":", "");
        return replaceAll.length() < 3 ? replaceAll : replaceAll.substring(replaceAll.length() - 3);
    }

    private static void b() {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("privacy_data_source_language_write_key_FEF629A75A8B472D886BABB73BE88952");
        if (userPreference == null) {
            userPreference = new HiUserPreference();
        } else {
            userPreference.setSyncStatus(0);
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("language", BaseApplication.getContext().getResources().getConfiguration().locale.toString());
        userPreference.setKey("privacy_data_source_language_write_key_FEF629A75A8B472D886BABB73BE88952");
        userPreference.setValue(JsonParser.b(hashMap).toString());
        LogUtil.a("PrivacyDataUtil", "updata wirte and language chage flags： ", Boolean.valueOf(HiHealthManager.d(BaseApplication.getContext()).setUserPreference(userPreference)));
    }

    private static boolean a() {
        String value;
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("privacy_data_source_language_write_key_FEF629A75A8B472D886BABB73BE88952");
        if (userPreference == null || (value = userPreference.getValue()) == null) {
            return false;
        }
        Map<String, Object> c = JsonParser.c(value);
        String locale = BaseApplication.getContext().getResources().getConfiguration().locale.toString();
        if (com.huawei.operation.utils.Constants.NULL.equals(String.valueOf(c.get("language")))) {
            return false;
        }
        return !r0.equals(locale);
    }

    public static boolean e() {
        return SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false);
    }
}
