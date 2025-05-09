package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.homehealth.runcard.trackfragments.adapters.AutoRecordHistoryAdapter;
import com.huawei.ui.homehealth.view.NoTitleCustomViewDialog;
import com.huawei.ui.main.stories.history.SportHistoryActivity;
import com.huawei.ui.main.stories.me.util.AppSettingUtil;
import defpackage.ory;
import health.compact.a.CommonUtils;
import health.compact.a.KeyValDbManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class ory {

    /* renamed from: a, reason: collision with root package name */
    private static CustomPermissionAction f15925a;

    public static void d(final Context context, final guz guzVar, boolean z, final IBaseResponseCallback iBaseResponseCallback) {
        if (context == null || guzVar == null || iBaseResponseCallback == null) {
            LogUtil.h("Track_SportAutoTrackUtils", "showAutoRecordsGuideDialog params is error");
            return;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.auto_records_guide_layout, (ViewGroup) null);
        ((HealthTextView) inflate.findViewById(R.id.tv_auto_records_guide_title)).setText(context.getString(R.string._2130845847_res_0x7f022097));
        String string = guzVar.f() ? context.getString(R.string._2130846208_res_0x7f022200) : context.getString(R.string._2130845962_res_0x7f02210a);
        final String str = z ? "auto_identify_record_guide_cancel_for_walk" : "auto_identify_record_guide_cancel";
        ((HealthTextView) inflate.findViewById(R.id.tv_auto_records_guide_content)).setText(string);
        final String value = AnalyticsValue.HEALTH_AUTO_TRACK_DIALOG_GUIDE_1040071.value();
        NoTitleCustomViewDialog.Builder builder = new NoTitleCustomViewDialog.Builder(context);
        builder.d(false).djk_(inflate).b(true).djj_(context.getString(R.string._2130845856_res_0x7f0220a0), new View.OnClickListener() { // from class: ose
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ory.dgy_(context, guzVar, str, value, iBaseResponseCallback, view);
            }
        }).dji_(context.getString(R.string._2130845098_res_0x7f021daa), new View.OnClickListener() { // from class: osc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ory.dgz_(context, str, value, iBaseResponseCallback, view);
            }
        });
        NoTitleCustomViewDialog e = builder.e();
        HealthButton d = builder.d();
        if (d != null) {
            d.setBackground(null);
            d.setTextColor(context.getColor(R.color._2131297805_res_0x7f09060d));
            e.setCancelable(false);
        }
        e.show();
        a(context, 0, value);
    }

    static /* synthetic */ void dgy_(Context context, guz guzVar, String str, String str2, IBaseResponseCallback iBaseResponseCallback, View view) {
        e(context, guzVar, str);
        a(context, 3, str2);
        iBaseResponseCallback.d(0, true);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void dgz_(Context context, String str, String str2, IBaseResponseCallback iBaseResponseCallback, View view) {
        c(context, str);
        a(context, 2, str2);
        iBaseResponseCallback.d(0, true);
        ViewClickInstrumentation.clickOnView(view);
    }

    private static void e(Context context, guz guzVar, String str) {
        guzVar.d(context);
        c(context, str);
        b(guzVar, context);
    }

    private static void c(Context context, String str) {
        SharedPreferenceManager.e(context, Integer.toString(10005), str, Boolean.toString(true), (StorageParams) null);
    }

    /* renamed from: ory$3, reason: invalid class name */
    class AnonymousClass3 extends CustomPermissionAction {
        final /* synthetic */ guz b;
        final /* synthetic */ Context e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Context context, Context context2, guz guzVar) {
            super(context);
            this.e = context2;
            this.b = guzVar;
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            LogUtil.a("Track_SportAutoTrackUtils", "Granted Permission");
            if (!ory.d(this.e)) {
                ory.d(this.e, this.b, true);
            } else {
                PermissionUtil.b(this.e, PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND, ory.f15925a);
            }
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            super.onDenied(str);
            LogUtil.h("Track_SportAutoTrackUtils", "permission denied by the user");
            if (!PermissionUtil.d() || !caq.c(this.e)) {
                ory.d(this.e, this.b, PermissionUtil.j() && caq.c(this.e));
            } else {
                ory.e(this.e);
            }
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            LogUtil.h("Track_SportAutoTrackUtils", "permission forever denied, show the guide window");
            if ((PermissionUtil.j() || permissionType != PermissionUtil.PermissionType.LOCATION) && permissionType != PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND) {
                return;
            }
            final Context context = this.e;
            final guz guzVar = this.b;
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: osd
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ory.AnonymousClass3.dgE_(context, guzVar, view);
                }
            };
            final Context context2 = this.e;
            final guz guzVar2 = this.b;
            nsn.cLJ_(context, permissionType, onClickListener, new View.OnClickListener() { // from class: osh
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ory.AnonymousClass3.dgF_(context2, guzVar2, view);
                }
            });
        }

        static /* synthetic */ void dgE_(Context context, guz guzVar, View view) {
            LogUtil.a("Track_SportAutoTrackUtils", "onForeverDenied on click confirm");
            ory.d(context, guzVar, false);
            ViewClickInstrumentation.clickOnView(view);
        }

        static /* synthetic */ void dgF_(Context context, guz guzVar, View view) {
            LogUtil.a("Track_SportAutoTrackUtils", "onForeverDenied on click cancel");
            ory.d(context, guzVar, false);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private static void b(guz guzVar, Context context) {
        f15925a = new AnonymousClass3(context, context, guzVar);
        c(context, guzVar);
    }

    private static void c(Context context, guz guzVar) {
        if (PermissionUtil.j() && caq.c(context)) {
            d(context, guzVar, true);
            return;
        }
        if (PermissionUtil.d()) {
            if (caq.c(context)) {
                e(context);
                return;
            } else {
                PermissionUtil.b(context, PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND, f15925a);
                return;
            }
        }
        PermissionUtil.b(context, PermissionUtil.PermissionType.LOCATION, f15925a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean d(Context context) {
        LogUtil.a("Track_SportAutoTrackUtils", "enter isApplyBackgroundLocationPermission");
        return (PermissionUtil.j() || PermissionUtil.e(context, PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND) == PermissionUtil.PermissionResult.GRANTED) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(Context context, guz guzVar, boolean z) {
        cao.a(context, 5, true, z, (int) guzVar.c());
        new AppSettingUtil(context).b(z, guzVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(final Context context) {
        LogUtil.a("Track_SportAutoTrackUtils", "enter showLocationTips show dialog");
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(context).e(context.getString(R.string._2130843342_res_0x7f0216ce)).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: osg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ory.dgC_(view);
            }
        }).czC_(R.string._2130842176_res_0x7f021240, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.utils.SportAutoTrackUtils$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ory.dgD_(context, view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void dgC_(View view) {
        LogUtil.a("Track_SportAutoTrackUtils", "showLocationTips onClick cancel");
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void dgD_(Context context, View view) {
        LogUtil.a("Track_SportAutoTrackUtils", "showLocationTips onClick confirm");
        PermissionUtil.b(context, caq.d(), f15925a);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void b(final Context context, List<rdn> list, final IBaseResponseCallback iBaseResponseCallback) {
        if (context == null || koq.b(list)) {
            LogUtil.h("Track_SportAutoTrackUtils", "showAutoRecordsHistoryDialog context is null or list is empty");
            iBaseResponseCallback.d(0, "");
            return;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.auto_records_history_layout, (ViewGroup) null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.htv_auto_records_title);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.htv_auto_records_time);
        healthTextView.setText(context.getResources().getQuantityString(R.plurals._2130903343_res_0x7f03012f, list.size(), Integer.valueOf(list.size())));
        healthTextView2.setText(b(list));
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.hr_auto_records);
        AutoRecordHistoryAdapter autoRecordHistoryAdapter = new AutoRecordHistoryAdapter(list, context);
        healthRecycleView.setHasFixedSize(true);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(context));
        healthRecycleView.setAdapter(autoRecordHistoryAdapter);
        final String value = AnalyticsValue.HEALTH_AUTO_TRACK_DIALOG_HISTORY_1040075.value();
        NoTitleCustomViewDialog.Builder builder = new NoTitleCustomViewDialog.Builder(context);
        builder.d(true).djk_(inflate).b(list.size() < 3).djj_(context.getString(R.string._2130845851_res_0x7f02209b), new View.OnClickListener() { // from class: orz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ory.dgA_(context, value, iBaseResponseCallback, view);
            }
        }).dji_(context.getString(R.string._2130845852_res_0x7f02209c), new View.OnClickListener() { // from class: osf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ory.dgB_(context, value, iBaseResponseCallback, view);
            }
        });
        NoTitleCustomViewDialog e = builder.e();
        e.setCancelable(false);
        e.show();
        a(context, 0, value);
    }

    static /* synthetic */ void dgA_(Context context, String str, IBaseResponseCallback iBaseResponseCallback, View view) {
        a(context, 1, str);
        iBaseResponseCallback.d(0, "");
        gnm.aPB_(context, new Intent(context, (Class<?>) SportHistoryActivity.class));
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void dgB_(Context context, String str, IBaseResponseCallback iBaseResponseCallback, View view) {
        a(context, 2, str);
        SharedPreferenceManager.e(context, Integer.toString(10005), "auto_identify_record_history_cancel", Boolean.toString(true), (StorageParams) null);
        iBaseResponseCallback.d(0, "");
        ViewClickInstrumentation.clickOnView(view);
    }

    private static String b(List<rdn> list) {
        ArrayList arrayList = new ArrayList();
        for (rdn rdnVar : list) {
            arrayList.add(Long.valueOf(rdnVar.k()));
            arrayList.add(Long.valueOf(rdnVar.i()));
        }
        long longValue = ((Long) Collections.min(arrayList)).longValue();
        long longValue2 = ((Long) Collections.max(arrayList)).longValue();
        String c = jec.c(new Date(longValue), "yyyy/MM/dd");
        String c2 = jec.c(new Date(longValue2), "MM/dd");
        if (!nsj.b(longValue, longValue2)) {
            c2 = jec.c(new Date(longValue2), "yyyy/MM/dd");
        }
        return c + Constants.LINK + c2;
    }

    public static void e(Context context, final IBaseResponseCallback iBaseResponseCallback) {
        if (context == null || iBaseResponseCallback == null) {
            LogUtil.h("Track_SportAutoTrackUtils", "getAutoRecordsData context or callback is null");
            return;
        }
        String b = SharedPreferenceManager.b(context, String.valueOf(20002), "first_open_auto_identify_record_switch_time");
        if (TextUtils.isEmpty(b)) {
            LogUtil.h("Track_SportAutoTrackUtils", "getAutoRecordsData firstOpenSwitchTime is not uninitialized");
            return;
        }
        kor.a().a(Math.max(cat.d(), CommonUtils.g(b)), System.currentTimeMillis(), new int[]{257, 258}, new IBaseResponseCallback() { // from class: ory.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                List list;
                try {
                } catch (ClassCastException unused) {
                    LogUtil.b("Track_SportAutoTrackUtils", "showAutoRecordsDialog classCastException");
                }
                if (koq.e(obj, HiHealthData.class)) {
                    list = (List) obj;
                    ArrayList arrayList = new ArrayList(10);
                    ory.a(list, arrayList);
                    IBaseResponseCallback.this.d(0, arrayList);
                }
                list = null;
                ArrayList arrayList2 = new ArrayList(10);
                ory.a(list, arrayList2);
                IBaseResponseCallback.this.d(0, arrayList2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(List<HiHealthData> list, List<rdn> list2) {
        if (koq.b(list)) {
            LogUtil.h("Track_SportAutoTrackUtils", "saveSingleTrackData trackData is empty");
            return;
        }
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                try {
                    HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
                    if (hiTrackMetaData.getTrackType() == 1) {
                        rdn rdnVar = new rdn();
                        e(rdnVar, hiTrackMetaData, hiHealthData);
                        rdnVar.a(hiHealthData.getInt("trackdata_deviceType"));
                        rdnVar.d(hiHealthData.getString("device_prodid"));
                        rdnVar.g(hiTrackMetaData.getSportType());
                        rdnVar.a(hiTrackMetaData.getAvgPace());
                        rdnVar.d(hiTrackMetaData.getTotalDistance(), hiTrackMetaData.getChiefSportDataType());
                        list2.add(rdnVar);
                    }
                } catch (JsonSyntaxException unused) {
                    LogUtil.h("Track_SportAutoTrackUtils", "parseTrackSimplifyData trackMetaData is jsonSyntaxException");
                }
            }
        }
    }

    private static rdn e(rdn rdnVar, HiTrackMetaData hiTrackMetaData, HiHealthData hiHealthData) {
        long startTime = hiHealthData.getStartTime();
        long endTime = hiHealthData.getEndTime();
        long totalTime = hiTrackMetaData.getTotalTime();
        rdnVar.b(startTime);
        rdnVar.e(endTime);
        rdnVar.c(totalTime);
        return rdnVar;
    }

    private static void a(Context context, int i, String str) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(context, str, hashMap, 0);
    }

    public static boolean d(Context context, guz guzVar) {
        if (context == null || guzVar == null) {
            LogUtil.h("Track_SportAutoTrackUtils", "isShowAutoRecordsGuideDialog context or autoTrackConfig is null");
            return false;
        }
        if (nsn.ae(context)) {
            LogUtil.a("Track_SportAutoTrackUtils", "isShowAutoRecordsGuideDialog the current device is pad");
            return false;
        }
        if (!"1".equals(KeyValDbManager.b(context).e("SUPPORT_AR_ABILITY"))) {
            LogUtil.a("Track_SportAutoTrackUtils", "isShowAutoRecordsGuideDialog AR capability is not supported");
            return false;
        }
        if (guzVar.b()) {
            return true;
        }
        LogUtil.a("Track_SportAutoTrackUtils", "isShowAutoRecordsGuideDialog is not show AutoTrackBtn");
        return false;
    }

    public static void a(Context context, int i, int i2, int i3) {
        if (context == null) {
            LogUtil.h("Track_SportAutoTrackUtils", "setAutoRecordsDataBi context is null");
            return;
        }
        HashMap hashMap = new HashMap(4);
        hashMap.put("click", 1);
        hashMap.put("runNumber", Integer.valueOf(i));
        hashMap.put("walkNumer", Integer.valueOf(i2));
        hashMap.put("totalNumber", Integer.valueOf(i3));
        ixx.d().d(context, AnalyticsValue.HEALTH_AUTO_TRACK_HISTORY_1040074.value(), hashMap, 0);
    }
}
