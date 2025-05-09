package com.huawei.ui.main.stories.discover.provider;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Pair;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.marketing.datatype.RecommendResourceInfo;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.up.model.UserInfomation;
import defpackage.koq;
import defpackage.nip;
import defpackage.npt;
import defpackage.nrv;
import defpackage.sde;
import health.compact.a.HiDateUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class MemberSportsCardReader extends MemberCardReader {
    private boolean f;
    private final String[] g;
    private final int[] h;
    private int j;
    private SportDataType k;
    private RecommendResourceInfo l;
    private long m;
    private int n;
    private final HashMap<Integer, String> o;

    enum SportDataType {
        ADD_RECORD("addRecord"),
        INADEQUATE_EXERCISE("inadequateExercise"),
        NORMAL_EXERCISE("normalExercise"),
        ADEQUATE_EXERCISE("adequateExercise"),
        INADEQUATE_STEP("inadequateSteps"),
        ADEQUATE_STEP("adequateSteps");

        private String mTypeId;

        SportDataType(String str) {
            this.mTypeId = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getTypeId() {
            return this.mTypeId;
        }
    }

    public MemberSportsCardReader(Context context) {
        super(context);
        this.o = new HashMap<Integer, String>(3) { // from class: com.huawei.ui.main.stories.discover.provider.MemberSportsCardReader.4
            {
                put(0, "Male");
                put(1, "Female");
                put(2, "None");
            }
        };
        this.h = new int[]{40002, 47101};
        this.g = new String[]{"stepUserValue", "durationUserValue"};
        this.j = 2;
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(1);
        a(arrayList, this);
        c("vipPageFitness");
        a(AnalyticsValue.VIP_INTRO_RECOMMEND_FITNESS_CARD.value());
        j("VIPCard_MemberSportsCardReader");
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberCardReader, com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void initCardReader(List<RecommendResourceInfo> list) {
        super.initCardReader(list);
        q();
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberCardReader, com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void registerCardListener(MemberCardListener memberCardListener) {
        LogUtil.a("VIPCard_MemberSportsCardReader", "registerCardListener is start");
        super.registerCardListener(memberCardListener);
        q();
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberCardReader, com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void unRegisterListener() {
        LogUtil.a("VIPCard_MemberSportsCardReader", "unRegisterListener is start");
        super.t();
        super.unRegisterListener();
        r();
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberCardReader, com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void queryLabel() {
        d(j() && e("fintness_dmp"));
        if (b() == 0) {
            LogUtil.a("VIPCard_MemberSportsCardReader", "Sport card reader:no privacy");
            if (k()) {
                r();
                this.k = SportDataType.ADD_RECORD;
                this.j = 2;
                this.f = true;
                q();
                return;
            }
            LogUtil.a("VIPCard_MemberSportsCardReader", "privacy status is not changed");
            return;
        }
        if (!l() && this.f && sde.c(this.m, System.currentTimeMillis()) && !k()) {
            LogUtil.a("VIPCard_MemberSportsCardReader", "mIsQueryLabelEnd is finished");
        } else {
            r();
            c(new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.discover.provider.MemberSportsCardReader.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("VIPCard_MemberSportsCardReader", " errorCode is ", Integer.valueOf(i), " objData is ", obj);
                    if (obj instanceof SportDataType) {
                        MemberSportsCardReader.this.k = (SportDataType) obj;
                    }
                    LogUtil.a("VIPCard_MemberSportsCardReader", "mSportsDataType type is ", MemberSportsCardReader.this.k);
                    UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
                    if (userInfo != null && userInfo.isGenderValid()) {
                        MemberSportsCardReader.this.j = userInfo.getGender();
                        LogUtil.a("VIPCard_MemberSportsCardReader", "Query gender type is ", Integer.valueOf(MemberSportsCardReader.this.j));
                    }
                    MemberSportsCardReader.this.m = System.currentTimeMillis();
                    MemberSportsCardReader.this.f = true;
                    MemberSportsCardReader.this.q();
                }
            });
        }
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberCardReader, com.huawei.ui.main.stories.discover.provider.MemberBaseCardReader
    public void setOrder(int i) {
        super.setOrder(i);
    }

    @Override // com.huawei.ui.main.stories.discover.provider.MemberCardReader
    protected void b(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            LogUtil.h("VIPCard_MemberSportsCardReader", "changeData is null in sport checkChangeValid");
            return;
        }
        String changeDataInfos = hiHealthData.getChangeDataInfos();
        List arrayList = new ArrayList(16);
        if (!TextUtils.isEmpty(changeDataInfos)) {
            arrayList = (List) nrv.c(changeDataInfos, new TypeToken<List<a>>() { // from class: com.huawei.ui.main.stories.discover.provider.MemberSportsCardReader.1
            }.getType());
        }
        LogUtil.a("VIPCard_MemberSportsCardReader", "changeInfoBeanList is ", arrayList);
        if (koq.b(arrayList)) {
            LogUtil.h("VIPCard_MemberSportsCardReader", "Json change is error");
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (e((a) it.next())) {
                setDataChangeTrue();
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (!n() || !this.f || (!k() && o())) {
            LogUtil.a("VIPCard_MemberSportsCardReader", "Do not need to buildSportsCardBean");
            return;
        }
        String str = this.k.getTypeId() + this.o.get(Integer.valueOf(this.j));
        String str2 = "vipPageFitness-" + str;
        if (!b(str2)) {
            LogUtil.h("VIPCard_MemberSportsCardReader", "no matched resources, use ADD_RECORD_NONE_ID");
            this.k = SportDataType.ADD_RECORD;
            str = "addRecordNone";
            str2 = "vipPageFitness-addRecordNone";
        }
        i(str);
        h(str2);
        RecommendResourceInfo d2 = d(h());
        this.l = d2;
        if (d2 == null || d2.isInvalid()) {
            LogUtil.b("VIPCard_MemberSportsCardReader", "content is not complete ", this.l);
            return;
        }
        RecommendResourceInfo recommendResourceInfo = this.l;
        int color = a().getColor(R.color._2131298141_res_0x7f09075d);
        doF_(recommendResourceInfo, Integer.valueOf(color), doH_(a(), this.k), Integer.valueOf(a().getColor(R.color._2131298141_res_0x7f09075d)), p());
    }

    private npt p() {
        return new npt() { // from class: com.huawei.ui.main.stories.discover.provider.MemberSportsCardReader.3
            @Override // defpackage.npt, com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                MemberSportsCardReader memberSportsCardReader = MemberSportsCardReader.this;
                memberSportsCardReader.b(i, memberSportsCardReader.l);
            }
        };
    }

    private void c(IBaseResponseCallback iBaseResponseCallback) {
        long f = HiDateUtil.f(System.currentTimeMillis()) - 86400000;
        long t = HiDateUtil.t(System.currentTimeMillis()) - 604800000;
        LogUtil.a("VIPCard_MemberSportsCardReader", "startTime ", Long.valueOf(t), " endTime ", Long.valueOf(f));
        CountDownLatch countDownLatch = new CountDownLatch(2);
        nip.d("900200006", new b(countDownLatch));
        HiAggregateOption doG_ = doG_(new Pair<>(Long.valueOf(t), Long.valueOf(f)));
        HashMap hashMap = new HashMap(1);
        HiHealthNativeApi.a(BaseApplication.e()).aggregateHiHealthData(doG_, new d(hashMap, countDownLatch));
        try {
            if (!countDownLatch.await(3000L, TimeUnit.MILLISECONDS)) {
                LogUtil.h("VIPCard_MemberSportsCardReader", "RequestData wait timeout");
                iBaseResponseCallback.d(-1, SportDataType.ADD_RECORD);
                return;
            }
        } catch (InterruptedException unused) {
            LogUtil.b("VIPCard_MemberSportsCardReader", "interrupted while waiting for requestData");
        }
        if (!hashMap.containsKey(0) || hashMap.get(0) == null) {
            iBaseResponseCallback.d(-1, SportDataType.ADD_RECORD);
        } else {
            iBaseResponseCallback.d(0, a((List<HiHealthData>) hashMap.get(0), this.n));
        }
    }

    private SportDataType a(List<HiHealthData> list, int i) {
        float f = 0.0f;
        float f2 = 0.0f;
        int i2 = 0;
        int i3 = 0;
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null) {
                int i4 = hiHealthData.getInt("stepUserValue");
                int i5 = hiHealthData.getInt("durationUserValue");
                if (i5 > 0) {
                    i2++;
                }
                if (i4 > 0) {
                    i3++;
                }
                f += i5;
                f2 += i4;
            }
        }
        int a2 = (int) UnitUtil.a(i2 == 0 ? 0.0d : f / i2, 0);
        int a3 = (int) UnitUtil.a(i3 != 0 ? f2 / i3 : 0.0d, 0);
        LogUtil.a("VIPCard_MemberSportsCardReader", " averageIntensity is ", Integer.valueOf(a2), " averageStepValue is ", Integer.valueOf(a3), " stepGoal is ", Integer.valueOf(i));
        return e(a2, a3, i);
    }

    private SportDataType e(int i, int i2, int i3) {
        if (i != 0) {
            if (i < 30) {
                return SportDataType.INADEQUATE_EXERCISE;
            }
            if (i < 60) {
                return SportDataType.NORMAL_EXERCISE;
            }
            return SportDataType.ADEQUATE_EXERCISE;
        }
        if (i2 == 0) {
            return SportDataType.ADD_RECORD;
        }
        if (i3 == 0) {
            i3 = 10000;
        }
        if (i2 < i3) {
            return SportDataType.INADEQUATE_STEP;
        }
        return SportDataType.ADEQUATE_STEP;
    }

    private HiAggregateOption doG_(Pair<Long, Long> pair) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(((Long) pair.first).longValue());
        hiAggregateOption.setEndTime(((Long) pair.second).longValue());
        hiAggregateOption.setSortOrder(0);
        hiAggregateOption.setType(this.h);
        hiAggregateOption.setConstantsKey(this.g);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(3);
        return hiAggregateOption;
    }

    /* renamed from: com.huawei.ui.main.stories.discover.provider.MemberSportsCardReader$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[SportDataType.values().length];
            b = iArr;
            try {
                iArr[SportDataType.ADD_RECORD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[SportDataType.INADEQUATE_EXERCISE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[SportDataType.INADEQUATE_STEP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[SportDataType.ADEQUATE_STEP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private SpannableString doH_(Context context, SportDataType sportDataType) {
        String e;
        String string = context.getString(R$string.IDS_member_sport_adequate);
        int i = AnonymousClass2.b[sportDataType.ordinal()];
        if (i != 1) {
            e = null;
            if (i == 2) {
                string = context.getString(R$string.IDS_member_sport_inadequate);
            } else if (i == 3) {
                string = context.getString(R$string.IDS_member_sport_not_achieved);
            } else if (i == 4) {
                string = context.getString(R$string.IDS_member_sport_achieved);
            }
        } else {
            e = UnitUtil.e(30.0d, 1, 0);
            string = context.getResources().getQuantityString(R.plurals._2130903102_res_0x7f03003e, 30, e);
        }
        if (TextUtils.isEmpty(e)) {
            e = string;
        }
        return UnitUtil.bCR_(context, e, string, R.style.member_sports_card_data, R.style.weight_management_goal_normal);
    }

    private boolean e(a aVar) {
        if (aVar == null) {
            LogUtil.h("VIPCard_MemberSportsCardReader", "changeInfoBean is null");
            return false;
        }
        if (aVar.b != 40002 && aVar.b != 47101) {
            LogUtil.a("VIPCard_MemberSportsCardReader", "Data change type is unrequired");
            return false;
        }
        if (aVar.d != 0) {
            return aVar.d < HiDateUtil.t(System.currentTimeMillis());
        }
        LogUtil.a("VIPCard_MemberSportsCardReader", "Data change with no start time");
        return false;
    }

    static class b implements IBaseResponseCallback {
        private WeakReference<MemberSportsCardReader> c;
        private final CountDownLatch e;

        private b(MemberSportsCardReader memberSportsCardReader, CountDownLatch countDownLatch) {
            this.c = new WeakReference<>(memberSportsCardReader);
            this.e = countDownLatch;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("VIPCard_MemberSportsCardReader", "getFitnessGoal errorCodeGoal is ", Integer.valueOf(i));
            MemberSportsCardReader memberSportsCardReader = this.c.get();
            if (memberSportsCardReader == null) {
                LogUtil.h("VIPCard_MemberSportsCardReader", "ReadGoalBaseResponseCallback memberSportsCardReader is null");
                this.e.countDown();
            } else {
                if (obj instanceof Integer) {
                    memberSportsCardReader.n = ((Integer) obj).intValue();
                }
                this.e.countDown();
            }
        }
    }

    static class d implements HiAggregateListener {
        private final CountDownLatch c;
        private Map<Integer, List<HiHealthData>> d;

        private d(Map<Integer, List<HiHealthData>> map, CountDownLatch countDownLatch) {
            this.d = map;
            this.c = countDownLatch;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            LogUtil.a("VIPCard_MemberSportsCardReader", "aggregateHiHealthData errorCode is ", Integer.valueOf(i));
            if (!koq.e((Object) list, HiHealthData.class)) {
                LogUtil.b("VIPCard_MemberSportsCardReader", "HiHealthData not match");
            } else {
                this.d.put(0, list);
            }
            this.c.countDown();
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            LogUtil.a("VIPCard_MemberSportsCardReader", "typeSelectByRequestData onResultIntent");
        }
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("end_time")
        long f9708a;

        @SerializedName("type")
        int b;

        @SerializedName("device_uniquecode")
        String c;

        @SerializedName("start_time")
        long d;

        private a() {
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("ChangeInfoBean{type=");
            stringBuffer.append(this.b);
            stringBuffer.append(", startTime = ").append(this.d);
            stringBuffer.append(", endTime = ").append(this.f9708a);
            stringBuffer.append(", deviceId = ").append(this.c);
            stringBuffer.append('}');
            return stringBuffer.toString();
        }
    }
}
