package com.huawei.ui.main.stories.fitness.activity.bloodoxygen.provider;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.util.SparseArray;
import android.view.View;
import com.github.mikephil.charting.data.Entry;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.knit.data.MajorProvider;
import com.huawei.health.knit.section.listener.FullScreenCallback;
import com.huawei.health.knit.section.listener.OnXRangeSetCallback;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.manager.util.TimeUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarActivity;
import com.huawei.ui.commonui.calendarview.HealthDataMarkDateTrigger;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentPresenter;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.detailactivity.HorizontalBloodOxygenDayActivity;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.provider.BloodOxygenDayChartProvider;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.utils.LastTimeHealthDataReader;
import defpackage.ecm;
import defpackage.efb;
import defpackage.jec;
import defpackage.jqi;
import defpackage.koq;
import defpackage.nom;
import defpackage.nsj;
import defpackage.pjh;
import defpackage.pkb;
import defpackage.pkr;
import defpackage.pkv;
import defpackage.qrp;
import defpackage.scg;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class BloodOxygenDayChartProvider extends MajorProvider<pkr> {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9762a;
    private boolean aa;
    private long ab;
    private String ac;
    private int ad;
    private boolean b;
    private boolean c;
    private boolean e;
    private pkb f;
    private BloodOxygenDayDetailFragmentPresenter g;
    private String k;
    private SpannableString m;
    private String n;
    private HealthCalendar o;
    private boolean p;
    private jqi q;
    private boolean r;
    private Boolean t;
    private SectionBean u;
    private boolean v;
    private Observer w;
    private Observer x;
    private Long y;
    private long z;
    private boolean h = true;
    private List<Integer> i = null;
    private pkr j = new pkr();
    private boolean d = true;
    private int l = Integer.MAX_VALUE;
    private g s = new g(this);

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(int i) {
        return i == 18;
    }

    static class g extends Handler {
        private final WeakReference<BloodOxygenDayChartProvider> e;

        public g(BloodOxygenDayChartProvider bloodOxygenDayChartProvider) {
            this.e = new WeakReference<>(bloodOxygenDayChartProvider);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            super.handleMessage(message);
            BloodOxygenDayChartProvider bloodOxygenDayChartProvider = this.e.get();
            if (bloodOxygenDayChartProvider != null && message.what == 1) {
                bloodOxygenDayChartProvider.u.e(bloodOxygenDayChartProvider.u.e());
            }
        }
    }

    public BloodOxygenDayChartProvider() {
        this.ad = 0;
        f();
        this.ad = nom.f(nom.a(System.currentTimeMillis()));
        this.g = new pkv();
        this.f = new pkb(BaseApplication.getContext(), DataInfos.BloodOxygenDayDetail, this.g);
        if (BaseApplication.getActivity() instanceof BaseActivity) {
            new LastTimeHealthDataReader((BaseActivity) BaseApplication.getActivity(), new b()).b(LastTimeHealthDataReader.CardData.BLOOD_OXYGEN);
        } else {
            LogUtil.b("BloodOxygenDayChartProvider", "BaseApplication.getActivity() return Error");
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return this.h;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        this.u = sectionBean;
        this.p = true;
        j();
        a();
    }

    private void a() {
        if (!efb.c()) {
            LogUtil.b("BloodOxygenDayChartProvider", "no release version, hide altitude");
            this.d = false;
            StorageParams storageParams = new StorageParams();
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "BLOOD_OXYGEN_SWITCH_IS_VISIBLE", "false", storageParams);
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "BLOOD_OXYGEN_CURVE_STATUS", "false", storageParams);
            return;
        }
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(b(), new e(this));
    }

    static class e implements HiDataReadResultListener {
        private final WeakReference<BloodOxygenDayChartProvider> e;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        public e(BloodOxygenDayChartProvider bloodOxygenDayChartProvider) {
            this.e = new WeakReference<>(bloodOxygenDayChartProvider);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            StorageParams storageParams = new StorageParams();
            BloodOxygenDayChartProvider bloodOxygenDayChartProvider = this.e.get();
            if (bloodOxygenDayChartProvider == null) {
                return;
            }
            if (!(obj instanceof SparseArray)) {
                LogUtil.b("BloodOxygenDayChartProvider", "data can not convert");
                bloodOxygenDayChartProvider.d = false;
                SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "BLOOD_OXYGEN_SWITCH_IS_VISIBLE", "false", storageParams);
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            if (sparseArray.size() <= 0) {
                bloodOxygenDayChartProvider.d = false;
                SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "BLOOD_OXYGEN_SWITCH_IS_VISIBLE", "false", storageParams);
                return;
            }
            new ArrayList(16);
            Object obj2 = sparseArray.get(DicDataTypeUtil.DataType.ALTITUDE_TYPE.value());
            if (obj2 instanceof List) {
                bloodOxygenDayChartProvider.d = true;
                SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "BLOOD_OXYGEN_SWITCH_IS_VISIBLE", "true", storageParams);
                LogUtil.a("BloodOxygenDayChartProvider", "altitudeValueList.size :", Integer.valueOf(((List) obj2).size()));
            } else {
                bloodOxygenDayChartProvider.d = false;
                SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "BLOOD_OXYGEN_SWITCH_IS_VISIBLE", "false", storageParams);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        jqi a2 = jqi.a();
        this.q = a2;
        a2.getSwitchSetting("highland.blood.oxygen.switch", new h(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(String str) {
        return str.equals("1") && efb.c();
    }

    private HiDataReadOption b() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setType(new int[]{DicDataTypeUtil.DataType.ALTITUDE_TYPE.value()});
        hiDataReadOption.setCount(1);
        return hiDataReadOption;
    }

    private void j() {
        Observer observer = new Observer() { // from class: com.huawei.ui.main.stories.fitness.activity.bloodoxygen.provider.BloodOxygenDayChartProvider.1
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (koq.e(objArr, 0)) {
                    Object obj = objArr[0];
                    if (obj instanceof SparseArray) {
                        SparseArray sparseArray = (SparseArray) obj;
                        List arrayList = new ArrayList(16);
                        Object obj2 = sparseArray.get(2103);
                        if (obj2 instanceof List) {
                            arrayList = (List) obj2;
                            LogUtil.a("BloodOxygenDayChartProvider", "oxygenMeasureValueList.size :", Integer.valueOf(arrayList.size()));
                        }
                        boolean c2 = koq.c(arrayList);
                        BloodOxygenDayChartProvider.this.j = new pkr();
                        List arrayList2 = new ArrayList(16);
                        if (!efb.c()) {
                            Object obj3 = sparseArray.get(2107);
                            if (obj3 instanceof List) {
                                arrayList2 = (List) obj3;
                                LogUtil.a("BloodOxygenDayChartProvider", "oxygenRemindValueList.size :", Integer.valueOf(arrayList2.size()));
                            }
                        }
                        pjh.dqd_(sparseArray, BloodOxygenDayChartProvider.this.j, false);
                        pjh.a(BloodOxygenDayChartProvider.this.z, TimeUtil.b(BloodOxygenDayChartProvider.this.z), BloodOxygenDayChartProvider.this.j, DataInfos.BloodOxygenDayDetail, new c(c2, arrayList2));
                    }
                }
            }
        };
        this.x = observer;
        ObserverManagerUtil.d(observer, "BLOOD_OXYGEN_RECORDS");
        Observer observer2 = new Observer() { // from class: com.huawei.ui.main.stories.fitness.activity.bloodoxygen.provider.BloodOxygenDayChartProvider.3
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, Object... objArr) {
                if (koq.e(objArr, 0)) {
                    Object obj = objArr[0];
                    if (obj instanceof Integer) {
                        BloodOxygenDayChartProvider.this.l = ((Integer) obj).intValue();
                        BloodOxygenDayChartProvider.this.d(((Integer) objArr[0]).intValue());
                    } else if (obj instanceof Boolean) {
                        BloodOxygenDayChartProvider.this.b(objArr);
                    }
                }
            }
        };
        this.w = observer2;
        ObserverManagerUtil.d(observer2, "BLOOD_AND_ALTITUDE_DATA");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Object[] objArr) {
        if (((Boolean) objArr[0]).booleanValue()) {
            c();
        } else {
            d();
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        this.u.e(new pkr());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, pkr pkrVar) {
        LogUtil.b("BloodOxygenDayChartProvider", "parseParams");
        hashMap.clear();
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(R.drawable._2131429944_res_0x7f0b0a38));
        arrayList.add(Integer.valueOf(R.drawable._2131429945_res_0x7f0b0a39));
        hashMap.put("ALTITUDE_IMAGE_LIST", arrayList);
        hashMap.put("BAR_CHART_DATE_TEXT", this.ac);
        hashMap.put("BAR_CHART_IS_TODAY", this.t);
        hashMap.put("BAR_CHART_PERIOD_STRING", this.k);
        hashMap.put("BAR_CHART_VALUE", this.m);
        hashMap.put("BAR_CHART_VALUE_STEP", this.n);
        hashMap.put("FULL_SCREEN_IMAGE", Integer.valueOf(R.drawable._2131429959_res_0x7f0b0a47));
        hashMap.put("BAR_COMMON_START_TIME", Integer.valueOf(this.ad));
        hashMap.put("BAR_COMMON_REFLESH_TIME", this.y);
        hashMap.put("BAR_COMMON_CHART_HOLDER", this.f);
        hashMap.put("BAR_CHART_LEGEND_TEXT", BaseApplication.getContext().getString(R$string.IDS_hw_health_blood_oxygen_elevation));
        hashMap.put("BAR_CHART_CUSOR_NAME_TEXT", BaseApplication.getContext().getString(R$string.IDS_mean_blood_oxygen));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Boolean.valueOf(this.f9762a));
        arrayList2.add(Boolean.valueOf(this.c));
        hashMap.put("VISIVILITY_LIST", arrayList2);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(Boolean.valueOf(this.d));
        arrayList3.add(Boolean.valueOf(this.r));
        arrayList3.add(Boolean.valueOf(this.b));
        arrayList3.add(Boolean.valueOf(this.e));
        hashMap.put("ALTITUDE_SWITCH_LIST", arrayList3);
        hashMap.put("BAR_CHART_REFRESH_CHART_ONLY", Boolean.valueOf(this.aa));
        e(hashMap);
        a(hashMap);
        c(hashMap);
        this.y = null;
        if (this.v) {
            hashMap.put("BAR_CHART_REFRESH", true);
        }
        this.v = false;
        this.aa = false;
    }

    private void e(HashMap<String, Object> hashMap) {
        hashMap.put("BAR_COMMON_MARK_CHANGE_CALL_BACK", new OnXRangeSetCallback() { // from class: pkw
            @Override // com.huawei.health.knit.section.listener.OnXRangeSetCallback
            public final void onRangeShow(int i, int i2, float f) {
                BloodOxygenDayChartProvider.this.e(i, i2, f);
            }
        });
    }

    public /* synthetic */ void e(int i, int i2, float f) {
        int h2 = nom.h((int) f);
        if (f != 0.0f) {
            this.o = qrp.a(this.o, h2);
        }
        long j = i;
        if (this.ab == j) {
            return;
        }
        this.ab = j;
        this.z = 60000 * j;
        Date date = new Date(this.z);
        if (date.getHours() == 0 && date.getMinutes() == 0 && date.getSeconds() == 0) {
            Boolean valueOf = Boolean.valueOf(jec.ab(date));
            this.t = valueOf;
            ObserverManagerUtil.c("BLOOD_OXYGEN_ARROW_VISIBLE", valueOf);
        }
        this.ac = nsj.d(BaseApplication.getContext(), TimeUnit.MINUTES.toMillis(j));
        Message obtain = Message.obtain();
        obtain.what = 1;
        this.s.sendMessage(obtain);
    }

    private void a(HashMap<String, Object> hashMap) {
        hashMap.put("BAR_COMMON_MARK_TEXT_CALL_BACK", new HwHealthMarkerView.OnMarkViewTextNotify() { // from class: pks
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthMarkerView.OnMarkViewTextNotify
            public final void onTextChanged(String str, List list) {
                BloodOxygenDayChartProvider.this.e(str, list);
            }
        });
    }

    public /* synthetic */ void e(String str, List list) {
        int i;
        String str2;
        String parse;
        String string = BaseApplication.getContext().getString(R$string.IDS_hw_health_blood_oxygen_elevation);
        if (this.e) {
            this.k = str;
            this.m = new SpannableString("--");
            this.n = string + " --";
            this.ad = 0;
            Message obtain = Message.obtain();
            obtain.what = 1;
            this.s.sendMessage(obtain);
            return;
        }
        int size = list.size() - 1;
        HwHealthBaseEntry hwHealthBaseEntry = ((HwHealthMarkerView.a) list.get(size)).b;
        if (hwHealthBaseEntry == null) {
            a(str, "--", string + " --", str);
            return;
        }
        Iterator it = ((HwHealthMarkerView.a) list.get(0)).e.getEntriesForXValue(hwHealthBaseEntry.getX()).iterator();
        while (true) {
            if (!it.hasNext()) {
                i = 0;
                break;
            }
            Entry entry = (Entry) it.next();
            if (entry.getY() != 0.0f) {
                i = (int) entry.getY();
                break;
            }
        }
        String str3 = "";
        if (hwHealthBaseEntry.getData() instanceof ecm) {
            if (((ecm) hwHealthBaseEntry.getData()).d() != Integer.MAX_VALUE && i == 0) {
                str2 = string + " " + UnitUtil.e(r7.d(), 1, 0);
            } else if (i != 0) {
                str2 = e(string, (List<HwHealthMarkerView.a>) list, hwHealthBaseEntry);
            } else {
                str2 = string + " --";
            }
        } else {
            str2 = "";
        }
        int e2 = e((List<HwHealthMarkerView.a>) list, hwHealthBaseEntry, i);
        String e3 = scg.e(BaseApplication.getContext(), nom.h((int) hwHealthBaseEntry.getX()) * 60000);
        if (e2 != 0) {
            parse = UnitUtil.e(e2, 1, 0);
        } else {
            parse = this.f.parse(((HwHealthMarkerView.a) list.get(size)).b);
            str3 = e3;
        }
        a(str, parse, str2, str3);
    }

    private String e(String str, List<HwHealthMarkerView.a> list, Entry entry) {
        List<T> values = list.get(0).e.getValues();
        int h2 = nom.h((int) entry.getX());
        int i = h2 % 30;
        int f = nom.f(h2 - i);
        int f2 = nom.f((h2 + 30) - i);
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MIN_VALUE;
        for (T t : values) {
            int x = (int) t.getX();
            if (x >= f && x < f2 && (t.getData() instanceof ecm)) {
                ecm ecmVar = (ecm) t.getData();
                if (ecmVar.d() != Integer.MAX_VALUE) {
                    if (ecmVar.d() > i3) {
                        i3 = ecmVar.d();
                    }
                    if (ecmVar.d() < i2) {
                        i2 = ecmVar.d();
                    }
                }
            }
        }
        if (i2 == i3) {
            return str + " " + UnitUtil.e(i2, 1, 0);
        }
        String e2 = UnitUtil.e(i2, 1, 0);
        String e3 = UnitUtil.e(i3, 1, 0);
        if (i2 != Integer.MAX_VALUE && i3 != Integer.MIN_VALUE) {
            return str + " " + e2 + " ~ " + e3;
        }
        return str + " --";
    }

    private int e(List<HwHealthMarkerView.a> list, Entry entry, int i) {
        if (i != 0) {
            return i;
        }
        int x = (int) entry.getX();
        List<T> values = list.get(0).e.getValues();
        ArrayList<Entry> arrayList = new ArrayList();
        for (T t : values) {
            if (Math.abs(x - ((int) t.getX())) <= 4 && t.getY() != 0.0f) {
                arrayList.add(t);
            }
        }
        if (arrayList.size() <= 0) {
            return i;
        }
        float f = x;
        int abs = (int) Math.abs(((Entry) arrayList.get(0)).getX() - f);
        int i2 = 0;
        for (Entry entry2 : arrayList) {
            int abs2 = (int) Math.abs(entry2.getX() - f);
            if (abs <= abs2) {
                i2 = (int) entry2.getX();
                abs = abs2;
            }
        }
        for (T t2 : list.get(0).e.getEntriesForXValue(i2)) {
            if (t2.getY() != 0.0f) {
                return (int) t2.getY();
            }
        }
        return i;
    }

    private void a(String str, String str2, String str3, String str4) {
        SpannableString spannableString;
        String string = BaseApplication.getContext().getString(R$string.IDS_hw_health_blood_oxygen_elevation);
        if ("--".equals(str2) || str2.equals("0")) {
            spannableString = new SpannableString("--");
            str = str4;
        } else {
            spannableString = UnitUtil.bCR_(BaseApplication.getContext(), "[\\d]", UnitUtil.e(CommonUtil.m(BaseApplication.getActivity(), str2), 2, 0), R.style.health_text_chart_emphasize, R.style.health_text_chart_emphasize_small);
        }
        if (str3.equals("")) {
            str3 = "";
        }
        if (this.m != null && spannableString.toString().equals(this.m.toString()) && str3.equals(this.n) && str.equals(this.k)) {
            return;
        }
        if (this.b) {
            this.n = string + " --";
        }
        this.k = str;
        this.m = spannableString;
        this.n = str3;
        Message obtain = Message.obtain();
        obtain.what = 1;
        this.s.sendMessage(obtain);
    }

    private void c(HashMap<String, Object> hashMap) {
        hashMap.put("FULL_SCREEN_CALLBACK", new FullScreenCallback() { // from class: pkp
            @Override // com.huawei.health.knit.section.listener.FullScreenCallback
            public final void click(long j) {
                BloodOxygenDayChartProvider.this.e(j);
            }
        });
        hashMap.put("BAR_CHART_CLICK_EVENT", new AnonymousClass4());
    }

    public /* synthetic */ void e(long j) {
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) HorizontalBloodOxygenDayActivity.class);
        intent.putExtra(ObserveredClassifiedView.JUMP_TIME_ID, nom.l(j));
        intent.putExtra("altitude_icon_status", !this.d ? 1 : 0);
        try {
            Activity activity = BaseApplication.getActivity();
            if (activity != null) {
                activity.startActivity(intent);
            }
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("BloodOxygenDayChartProvider", "ActivityNotFoundException", e2.getMessage());
        }
    }

    /* renamed from: com.huawei.ui.main.stories.fitness.activity.bloodoxygen.provider.BloodOxygenDayChartProvider$4, reason: invalid class name */
    public class AnonymousClass4 implements OnClickSectionListener {
        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, int i2) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, String str) {
        }

        AnonymousClass4() {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(String str) {
            if ("BAR_CHART_CALENDAR_CLICK_EVENT".equals(str)) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("calendar", BloodOxygenDayChartProvider.this.o);
                bundle.putParcelable("markDateTrigger", new HealthDataMarkDateTrigger(47204, new int[]{2107}));
                bundle.putBoolean("isSetGrayUnmarkedDate", true);
                HealthCalendarActivity.cxj_(BaseApplication.getActivity(), bundle, new CommonUiBaseResponse() { // from class: pkt
                    @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                    public final void onResponse(int i, Object obj) {
                        BloodOxygenDayChartProvider.AnonymousClass4.this.b(i, obj);
                    }
                });
            }
            if ("ALTITUDE_SWITCH_CLICK_EVENT".equals(str)) {
                SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "IS_MANUAL_OPEN_CLOSE_ALTITUDE_SWITCH", "true", new StorageParams());
                if (BloodOxygenDayChartProvider.this.r) {
                    BloodOxygenDayChartProvider.this.d();
                } else {
                    BloodOxygenDayChartProvider.this.c();
                }
                Message obtain = Message.obtain();
                obtain.what = 1;
                BloodOxygenDayChartProvider.this.s.sendMessage(obtain);
            }
        }

        public /* synthetic */ void b(int i, Object obj) {
            if (i == 1 && (obj instanceof HealthCalendar)) {
                BloodOxygenDayChartProvider.this.o = (HealthCalendar) obj;
                BloodOxygenDayChartProvider bloodOxygenDayChartProvider = BloodOxygenDayChartProvider.this;
                bloodOxygenDayChartProvider.ad = nom.f(nom.a(bloodOxygenDayChartProvider.o.transformCalendar().getTimeInMillis()));
                BloodOxygenDayChartProvider bloodOxygenDayChartProvider2 = BloodOxygenDayChartProvider.this;
                bloodOxygenDayChartProvider2.y = Long.valueOf(bloodOxygenDayChartProvider2.o.transformCalendar().getTimeInMillis());
                Message obtain = Message.obtain();
                obtain.what = 1;
                BloodOxygenDayChartProvider.this.s.sendMessage(obtain);
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.r = true;
        this.j.e(true);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "BLOOD_OXYGEN_CURVE_STATUS", "true", new StorageParams());
        ObserverManagerUtil.c("BLOOD_OXYGEN_CURVE_STATUS", true);
        this.f9762a = true;
        this.c = true;
        this.aa = true;
        ObserverManagerUtil.c("ALTITUDE_SWITCH_OPEN_CLOSE", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.r = false;
        this.j.e(false);
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "BLOOD_OXYGEN_CURVE_STATUS", "false", new StorageParams());
        ObserverManagerUtil.c("BLOOD_OXYGEN_CURVE_STATUS", false);
        this.f9762a = false;
        this.c = false;
        this.aa = true;
        ObserverManagerUtil.c("ALTITUDE_SWITCH_OPEN_CLOSE", false);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        ObserverManagerUtil.c("REMOVE_GLOBAL_LAYOUT", true);
        if (koq.c(this.i)) {
            HiHealthNativeApi.a(BaseApplication.getContext()).unSubscribeHiHealthData(this.i, new a("unSubscribeBloodOxygenData, isSuccess :"));
        }
        Observer observer = this.x;
        if (observer != null) {
            ObserverManagerUtil.e(observer, "BLOOD_OXYGEN_RECORDS");
        }
        Observer observer2 = this.w;
        if (observer2 != null) {
            ObserverManagerUtil.e(observer2, "BLOOD_AND_ALTITUDE_DATA");
        }
        ObserverManagerUtil.e("BLOOD_AND_ALTITUDE_DATA");
    }

    private void f() {
        LogUtil.a("BloodOxygenDayChartProvider", "enter subscribeBloodOxygenData");
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(18);
        HiHealthNativeApi.a(BaseApplication.getContext()).subscribeHiHealthData(arrayList, new d("BloodOxygenDayChartProvider", this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<Integer> list) {
        LogUtil.a("BloodOxygenDayChartProvider", "subscribeBloodOxygenData, onResult");
        if (koq.c(list)) {
            LogUtil.a("BloodOxygenDayChartProvider", "registerBloodOxygenListener success");
            this.i = list;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.v = true;
        Message obtain = Message.obtain();
        obtain.what = 1;
        this.s.sendMessage(obtain);
    }

    static class b implements IBaseResponseCallback {
        private final WeakReference<BloodOxygenDayChartProvider> b;

        private b(BloodOxygenDayChartProvider bloodOxygenDayChartProvider) {
            this.b = new WeakReference<>(bloodOxygenDayChartProvider);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            BloodOxygenDayChartProvider bloodOxygenDayChartProvider = this.b.get();
            if (bloodOxygenDayChartProvider == null) {
                LogUtil.h("BloodOxygenDayChartProvider", "LastTimeCallback activity is null");
                return;
            }
            if (obj instanceof HiHealthData) {
                HiHealthData hiHealthData = (HiHealthData) obj;
                if (hiHealthData.getStartTime() > 0) {
                    int f = nom.f(nom.a(hiHealthData.getStartTime()));
                    bloodOxygenDayChartProvider.ad = f;
                    bloodOxygenDayChartProvider.o = qrp.a(bloodOxygenDayChartProvider.o, f);
                }
                LogUtil.a("BloodOxygenDayChartProvider", "read last data time from database,mLastTimestamp=", Integer.valueOf(bloodOxygenDayChartProvider.ad));
            }
            bloodOxygenDayChartProvider.u.e(new pkr());
        }
    }

    static class c implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private boolean f9765a;
        private final WeakReference<BloodOxygenDayChartProvider> c;
        private List<HiHealthData> e;

        private c(BloodOxygenDayChartProvider bloodOxygenDayChartProvider, boolean z, List<HiHealthData> list) {
            this.c = new WeakReference<>(bloodOxygenDayChartProvider);
            this.f9765a = z;
            this.e = list;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            BloodOxygenDayChartProvider bloodOxygenDayChartProvider = this.c.get();
            if (bloodOxygenDayChartProvider == null) {
                LogUtil.h("BloodOxygenDayChartProvider", "LastTimeCallback activity is null");
                return;
            }
            if (i != 0) {
                return;
            }
            if (bloodOxygenDayChartProvider.r) {
                bloodOxygenDayChartProvider.j.e(true);
            } else {
                bloodOxygenDayChartProvider.j.e(false);
            }
            if (!efb.c()) {
                bloodOxygenDayChartProvider.j.a(this.e);
            }
            if (!this.f9765a) {
                bloodOxygenDayChartProvider.j = new pkr();
            }
            if (!efb.c()) {
                bloodOxygenDayChartProvider.j.e(false);
            }
            bloodOxygenDayChartProvider.notifyMinorProviders(bloodOxygenDayChartProvider.j);
        }
    }

    static class h implements IBaseResponseCallback {
        private final WeakReference<BloodOxygenDayChartProvider> b;
        private final int e;

        private h(BloodOxygenDayChartProvider bloodOxygenDayChartProvider, int i) {
            this.b = new WeakReference<>(bloodOxygenDayChartProvider);
            this.e = i;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            int i2;
            BloodOxygenDayChartProvider bloodOxygenDayChartProvider = this.b.get();
            if (bloodOxygenDayChartProvider == null) {
                LogUtil.h("BloodOxygenDayChartProvider", "LastTimeCallback activity is null");
                return;
            }
            c(bloodOxygenDayChartProvider, obj instanceof String ? bloodOxygenDayChartProvider.c((String) obj) : false);
            if (bloodOxygenDayChartProvider.b && bloodOxygenDayChartProvider.l == 1) {
                bloodOxygenDayChartProvider.aa = true;
            }
            if (bloodOxygenDayChartProvider.e && bloodOxygenDayChartProvider.l != 0) {
                bloodOxygenDayChartProvider.aa = true;
            }
            if (!bloodOxygenDayChartProvider.b && ((i2 = this.e) == 0 || i2 == 2)) {
                bloodOxygenDayChartProvider.aa = true;
            }
            int i3 = this.e;
            if (i3 == 0 || i3 == 2) {
                bloodOxygenDayChartProvider.b = true;
            } else {
                bloodOxygenDayChartProvider.b = false;
            }
            if (this.e == 0) {
                bloodOxygenDayChartProvider.e = true;
            } else {
                bloodOxygenDayChartProvider.e = false;
            }
            bloodOxygenDayChartProvider.p = false;
            Message obtain = Message.obtain();
            obtain.what = 1;
            bloodOxygenDayChartProvider.s.sendMessage(obtain);
        }

        private void c(BloodOxygenDayChartProvider bloodOxygenDayChartProvider, boolean z) {
            if (bloodOxygenDayChartProvider.p) {
                boolean equals = "true".equals(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "IS_MANUAL_OPEN_CLOSE_ALTITUDE_SWITCH"));
                boolean equals2 = "true".equals(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "BLOOD_OXYGEN_CURVE_STATUS"));
                if (equals) {
                    d(bloodOxygenDayChartProvider, equals2);
                } else {
                    d(bloodOxygenDayChartProvider, z);
                }
            }
        }

        private void d(BloodOxygenDayChartProvider bloodOxygenDayChartProvider, boolean z) {
            if (z) {
                bloodOxygenDayChartProvider.c();
            } else {
                bloodOxygenDayChartProvider.d();
            }
        }
    }

    static class d implements HiSubscribeListener {
        private final WeakReference<BloodOxygenDayChartProvider> e;

        private d(String str, BloodOxygenDayChartProvider bloodOxygenDayChartProvider) {
            this.e = new WeakReference<>(bloodOxygenDayChartProvider);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            BloodOxygenDayChartProvider bloodOxygenDayChartProvider = this.e.get();
            if (bloodOxygenDayChartProvider == null) {
                return;
            }
            bloodOxygenDayChartProvider.e(list);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            BloodOxygenDayChartProvider bloodOxygenDayChartProvider = this.e.get();
            if (bloodOxygenDayChartProvider != null && bloodOxygenDayChartProvider.e(i)) {
                bloodOxygenDayChartProvider.e();
            }
        }
    }

    static class a implements HiUnSubscribeListener {
        private final String b;

        private a(String str) {
            this.b = str;
        }

        @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
        public void onResult(boolean z) {
            LogUtil.a("BloodOxygenDayChartProvider", this.b, Boolean.valueOf(z));
        }
    }
}
