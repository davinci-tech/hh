package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.healthcloud.plugintrack.ui.adapter.TrackGridViewAdapter;
import com.huawei.healthcloud.plugintrack.ui.viewholder.ShowDataConfig;
import com.huawei.healthcloud.plugintrack.ui.viewholder.TrackMainFragmentShowType;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class hnb implements ShowDataConfig {
    private static final Object b = new Object();
    private static volatile hnb d;

    private static boolean b(int i) {
        return i == 259 || i == 265;
    }

    private hnb() {
    }

    public static ShowDataConfig a() {
        if (d == null) {
            synchronized (b) {
                if (d == null) {
                    d = new hnb();
                }
            }
        }
        return d;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.ShowDataConfig
    public SparseArray<TrackMainFragmentShowType> getConfigFromLocal(int i, int i2, int i3) {
        String b2;
        int i4;
        SparseArray<TrackMainFragmentShowType> sparseArray = new SparseArray<>(10);
        try {
            b2 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(20002), d(i, i2, i3));
        } catch (JSONException unused) {
            LogUtil.b("ShowDataConfigManager", "getConfigFromLocal(), JSONException");
        }
        if (TextUtils.isEmpty(b2)) {
            LogUtil.b("ShowDataConfigManager", "TextUtils.isEmpty(temp)");
            return sparseArray;
        }
        JSONArray jSONArray = new JSONArray(b2);
        for (i4 = 0; i4 < jSONArray.length(); i4++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i4);
            sparseArray.put(jSONObject.getInt(MedalConstants.EVENT_KEY), d(jSONObject.getString("type")));
        }
        return sparseArray;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.ShowDataConfig
    public void saveConfigToLocal(SparseArray<TrackMainFragmentShowType> sparseArray, int i, int i2, int i3) {
        JSONArray jSONArray = new JSONArray();
        for (int i4 = 0; i4 < sparseArray.size(); i4++) {
            try {
                int keyAt = sparseArray.keyAt(i4);
                jSONArray.put(i4, a(keyAt, sparseArray.get(keyAt)));
            } catch (JSONException unused) {
                LogUtil.b("ShowDataConfigManager", "saveConfigToLocal(), JSONException");
            }
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(20002), d(i, i2, i3), jSONArray.toString(), (StorageParams) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0052  */
    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.ShowDataConfig
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.util.SparseArray<com.huawei.healthcloud.plugintrack.ui.viewholder.TrackMainFragmentShowType> getDefaultConfig(int r3, int r4, int r5) {
        /*
            r2 = this;
            r5 = -1
            r0 = 5
            if (r4 == r5) goto L2f
            if (r4 == 0) goto L2a
            r5 = 1
            if (r4 == r5) goto L25
            r5 = 2
            if (r4 == r5) goto L20
            r5 = 4
            if (r4 == r5) goto L1b
            if (r4 == r0) goto L1b
            r5 = 200(0xc8, float:2.8E-43)
            if (r4 == r5) goto L2f
            r5 = 255(0xff, float:3.57E-43)
            if (r4 == r5) goto L2f
            r5 = 0
            goto L33
        L1b:
            android.util.SparseArray r5 = r2.blr_()
            goto L33
        L20:
            android.util.SparseArray r5 = r2.blp_()
            goto L33
        L25:
            android.util.SparseArray r5 = r2.blq_()
            goto L33
        L2a:
            android.util.SparseArray r5 = r2.blt_()
            goto L33
        L2f:
            android.util.SparseArray r5 = r2.bls_()
        L33:
            if (r5 != 0) goto L52
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.String r5 = " ,sportType:"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.String r0 = "getDefaultConfig(), result == null, sportTargetType:"
            java.lang.Object[] r3 = new java.lang.Object[]{r0, r4, r5, r3}
            java.lang.String r4 = "ShowDataConfigManager"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r4, r3)
            android.util.SparseArray r3 = new android.util.SparseArray
            r4 = 16
            r3.<init>(r4)
            return r3
        L52:
            boolean r4 = b(r3)
            if (r4 == 0) goto L63
            r4 = 265(0x109, float:3.71E-43)
            if (r3 != r4) goto L60
            blo_(r5)
            goto L63
        L60:
            r2.bln_(r5)
        L63:
            r4 = 260(0x104, float:3.64E-43)
            if (r3 != r4) goto L6d
            r4 = 3
            com.huawei.healthcloud.plugintrack.ui.viewholder.TrackMainFragmentShowType r1 = com.huawei.healthcloud.plugintrack.ui.viewholder.TrackMainFragmentShowType.ALTITUDE
            r5.put(r4, r1)
        L6d:
            r4 = 258(0x102, float:3.62E-43)
            if (r3 != r4) goto L82
            boolean r3 = r2.a(r3)
            if (r3 == 0) goto L82
            com.huawei.healthcloud.plugintrack.ui.viewholder.TrackMainFragmentShowType r3 = com.huawei.healthcloud.plugintrack.ui.viewholder.TrackMainFragmentShowType.CONTACT_TIME
            r5.put(r0, r3)
            r3 = 6
            com.huawei.healthcloud.plugintrack.ui.viewholder.TrackMainFragmentShowType r4 = com.huawei.healthcloud.plugintrack.ui.viewholder.TrackMainFragmentShowType.VERTICAL_RATIO
            r5.put(r3, r4)
        L82:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hnb.getDefaultConfig(int, int, int):android.util.SparseArray");
    }

    private void bln_(SparseArray<TrackMainFragmentShowType> sparseArray) {
        sparseArray.put(3, TrackMainFragmentShowType.SPEED);
        sparseArray.put(5, TrackMainFragmentShowType.TOTAL_CLIMB);
        if (j()) {
            sparseArray.put(2, TrackMainFragmentShowType.CADENCE);
        }
        sparseArray.put(6, null);
    }

    private static void blo_(SparseArray<TrackMainFragmentShowType> sparseArray) {
        sparseArray.put(0, TrackMainFragmentShowType.TOTAL_TIME);
        sparseArray.put(1, TrackMainFragmentShowType.HEART_RATE);
        sparseArray.put(2, TrackMainFragmentShowType.CALORIE);
        sparseArray.put(3, TrackMainFragmentShowType.CADENCE);
        sparseArray.put(4, null);
        sparseArray.put(5, null);
        sparseArray.put(6, null);
    }

    private boolean a(int i) {
        return koq.c(gsy.b().e(i));
    }

    private boolean j() {
        PluginSportTrackAdapter c = gso.e().c();
        if (c == null) {
            return false;
        }
        DeviceInfo currentAw70DeviceInfo = c.getCurrentAw70DeviceInfo();
        if (currentAw70DeviceInfo != null) {
            return currentAw70DeviceInfo.getDeviceConnectState() == 2 && currentAw70DeviceInfo.getAutoDetectSwitchStatus() == 1 && gwb.e(currentAw70DeviceInfo);
        }
        LogUtil.b("ShowDataConfigManager", "deviceInfo is null");
        return false;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.viewholder.ShowDataConfig
    public List<TrackGridViewAdapter.b> getValueHolderList(int i, int i2) {
        ArrayList arrayList = new ArrayList(10);
        c(arrayList);
        if (!b(i)) {
            arrayList.add(new TrackGridViewAdapter.b(R.drawable._2131427965_res_0x7f0b027d, R.drawable._2131427966_res_0x7f0b027e, R.string._2130844083_res_0x7f0219b3, TrackMainFragmentShowType.PACE));
            arrayList.add(new TrackGridViewAdapter.b(R.drawable._2131427973_res_0x7f0b0285, R.drawable._2131427974_res_0x7f0b0286, R.string.IDS_settings_steps, TrackMainFragmentShowType.STEPS));
        }
        if (gvv.a(i)) {
            arrayList.add(new TrackGridViewAdapter.b(R.drawable._2131427943_res_0x7f0b0267, R.drawable._2131427944_res_0x7f0b0268, R.string._2130844075_res_0x7f0219ab, TrackMainFragmentShowType.STEP_RATE));
        }
        if (!b(i) && i != 257 && i != 282 && i != 260) {
            b(arrayList);
        }
        if (b(i)) {
            c(arrayList, i2);
        }
        if (i == 258 || i == 264) {
            d(arrayList);
        }
        return arrayList;
    }

    private static void c(List<TrackGridViewAdapter.b> list, int i) {
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427941_res_0x7f0b0265, R.drawable._2131427942_res_0x7f0b0266, R.string._2130843486_res_0x7f02175e, TrackMainFragmentShowType.CADENCE));
        if (i == 100) {
            list.add(new TrackGridViewAdapter.b(R.drawable._2131427963_res_0x7f0b027b, R.drawable._2131427964_res_0x7f0b027c, R.string._2130840126_res_0x7f020a3e, TrackMainFragmentShowType.MAX_SPEED));
            list.add(new TrackGridViewAdapter.b(R.drawable._2131427989_res_0x7f0b0295, R.drawable._2131427990_res_0x7f0b0296, R.string._2130839763_res_0x7f0208d3, TrackMainFragmentShowType.AVG_SPEED));
            list.add(new TrackGridViewAdapter.b(R.drawable._2131427967_res_0x7f0b027f, R.drawable._2131427968_res_0x7f0b0280, R.string._2130843491_res_0x7f021763, TrackMainFragmentShowType.POWER));
        }
    }

    private void d(List<TrackGridViewAdapter.b> list) {
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427951_res_0x7f0b026f, R.drawable._2131427952_res_0x7f0b0270, R.string._2130845165_res_0x7f021ded, TrackMainFragmentShowType.GC_TIME_BALANCE));
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427985_res_0x7f0b0291, R.drawable._2131427986_res_0x7f0b0292, R.string._2130845168_res_0x7f021df0, TrackMainFragmentShowType.VERTICAL_OSCILLATION));
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427987_res_0x7f0b0293, R.drawable._2131427988_res_0x7f0b0294, R.string._2130845218_res_0x7f021e22, TrackMainFragmentShowType.VERTICAL_RATIO));
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427957_res_0x7f0b0275, R.drawable._2131427958_res_0x7f0b0276, R.string._2130845176_res_0x7f021df8, TrackMainFragmentShowType.IMPACT_PEAK));
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427939_res_0x7f0b0263, R.drawable._2131427940_res_0x7f0b0264, R.string._2130845219_res_0x7f021e23, TrackMainFragmentShowType.AVERAGE_VERTICAL_IMPACT_RATE));
    }

    private void b(List<TrackGridViewAdapter.b> list) {
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427981_res_0x7f0b028d, R.drawable._2131427982_res_0x7f0b028e, R.string._2130842710_res_0x7f021456, TrackMainFragmentShowType.CONTACT_TIME));
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427961_res_0x7f0b0279, R.drawable._2131427962_res_0x7f0b027a, R.string._2130842712_res_0x7f021458, TrackMainFragmentShowType.GROUND_IMPACT));
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427983_res_0x7f0b028f, R.drawable._2131427984_res_0x7f0b0290, R.string._2130842760_res_0x7f021488, TrackMainFragmentShowType.EVERSION_EXCURSION));
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427975_res_0x7f0b0287, R.drawable._2131427976_res_0x7f0b0288, R.string._2130842758_res_0x7f021486, TrackMainFragmentShowType.SWING_ANGLE));
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427959_res_0x7f0b0277, R.drawable._2131427960_res_0x7f0b0278, R.string._2130843148_res_0x7f02160c, TrackMainFragmentShowType.JUMP_DURATION));
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427953_res_0x7f0b0271, R.drawable._2131427954_res_0x7f0b0272, R.string._2130843723_res_0x7f02184b, TrackMainFragmentShowType.GROUND_TO_AIR_RATIO));
    }

    private void c(List<TrackGridViewAdapter.b> list) {
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427977_res_0x7f0b0289, R.drawable._2131427978_res_0x7f0b028a, R.string._2130843686_res_0x7f021826, TrackMainFragmentShowType.TOTAL_TIME));
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427949_res_0x7f0b026d, R.drawable._2131427950_res_0x7f0b026e, R.string._2130841530_res_0x7f020fba, TrackMainFragmentShowType.TOTAL_DISTANCES));
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427971_res_0x7f0b0283, R.drawable._2131427972_res_0x7f0b0284, R.string._2130844076_res_0x7f0219ac, TrackMainFragmentShowType.SPEED));
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427979_res_0x7f0b028b, R.drawable._2131427980_res_0x7f0b028c, R.string._2130847439_res_0x7f0226cf, TrackMainFragmentShowType.CALORIE));
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427935_res_0x7f0b025f, R.drawable._2131427936_res_0x7f0b0260, R.string._2130847567_res_0x7f02274f, TrackMainFragmentShowType.ACTIVE_CALORIE));
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427937_res_0x7f0b0261, R.drawable._2131427938_res_0x7f0b0262, R.string._2130842548_res_0x7f0213b4, TrackMainFragmentShowType.ALTITUDE));
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427945_res_0x7f0b0269, R.drawable._2131427946_res_0x7f0b026a, R.string._2130842325_res_0x7f0212d5, TrackMainFragmentShowType.TOTAL_CLIMB));
        list.add(new TrackGridViewAdapter.b(R.drawable._2131427947_res_0x7f0b026b, R.drawable._2131427948_res_0x7f0b026c, R.string._2130842545_res_0x7f0213b1, TrackMainFragmentShowType.TOTAL_DECLINE));
        list.add(new TrackGridViewAdapter.b(R.drawable.data_ic_heart_nor, R.drawable.data_ic_heart_selection, R.string._2130841430_res_0x7f020f56, TrackMainFragmentShowType.HEART_RATE));
    }

    private String d(int i, int i2, int i3) {
        if (i3 == 100) {
            return "show_data_type_config_" + i + "_" + i2 + " " + i3;
        }
        return "show_data_type_config_" + i + "_" + i2;
    }

    private TrackMainFragmentShowType d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (TrackMainFragmentShowType trackMainFragmentShowType : TrackMainFragmentShowType.values()) {
            if (trackMainFragmentShowType.toString().equals(str)) {
                return trackMainFragmentShowType;
            }
        }
        return null;
    }

    private JSONObject a(int i, TrackMainFragmentShowType trackMainFragmentShowType) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MedalConstants.EVENT_KEY, i);
            jSONObject.put("type", trackMainFragmentShowType == null ? "" : trackMainFragmentShowType.toString());
        } catch (JSONException unused) {
            LogUtil.b("ShowDataConfigManager", "getConfigJsonObject(), JSONException");
        }
        return jSONObject;
    }

    private SparseArray<TrackMainFragmentShowType> bls_() {
        SparseArray<TrackMainFragmentShowType> sparseArray = new SparseArray<>(10);
        sparseArray.put(0, TrackMainFragmentShowType.TOTAL_DISTANCES);
        sparseArray.put(1, TrackMainFragmentShowType.TOTAL_TIME);
        sparseArray.put(2, TrackMainFragmentShowType.CALORIE);
        sparseArray.put(3, TrackMainFragmentShowType.PACE);
        sparseArray.put(4, TrackMainFragmentShowType.HEART_RATE);
        sparseArray.put(5, TrackMainFragmentShowType.STEPS);
        sparseArray.put(6, null);
        sparseArray.put(7, TrackMainFragmentShowType.TOTAL_TIME);
        return sparseArray;
    }

    private SparseArray<TrackMainFragmentShowType> blq_() {
        SparseArray<TrackMainFragmentShowType> sparseArray = new SparseArray<>(10);
        sparseArray.put(0, TrackMainFragmentShowType.TOTAL_DISTANCES);
        sparseArray.put(1, TrackMainFragmentShowType.TOTAL_TIME);
        sparseArray.put(2, TrackMainFragmentShowType.CALORIE);
        sparseArray.put(3, TrackMainFragmentShowType.PACE);
        sparseArray.put(4, TrackMainFragmentShowType.HEART_RATE);
        sparseArray.put(5, TrackMainFragmentShowType.STEPS);
        sparseArray.put(6, null);
        sparseArray.put(7, TrackMainFragmentShowType.TOTAL_TIME);
        return sparseArray;
    }

    private SparseArray<TrackMainFragmentShowType> blt_() {
        SparseArray<TrackMainFragmentShowType> sparseArray = new SparseArray<>(10);
        sparseArray.put(0, TrackMainFragmentShowType.TOTAL_TIME);
        sparseArray.put(1, TrackMainFragmentShowType.TOTAL_DISTANCES);
        sparseArray.put(2, TrackMainFragmentShowType.CALORIE);
        sparseArray.put(3, TrackMainFragmentShowType.PACE);
        sparseArray.put(4, TrackMainFragmentShowType.HEART_RATE);
        sparseArray.put(5, TrackMainFragmentShowType.STEPS);
        sparseArray.put(6, null);
        sparseArray.put(7, TrackMainFragmentShowType.TOTAL_TIME);
        return sparseArray;
    }

    private SparseArray<TrackMainFragmentShowType> blp_() {
        SparseArray<TrackMainFragmentShowType> sparseArray = new SparseArray<>(10);
        sparseArray.put(0, TrackMainFragmentShowType.CALORIE);
        sparseArray.put(1, TrackMainFragmentShowType.TOTAL_TIME);
        sparseArray.put(2, TrackMainFragmentShowType.TOTAL_DISTANCES);
        sparseArray.put(3, TrackMainFragmentShowType.PACE);
        sparseArray.put(4, TrackMainFragmentShowType.HEART_RATE);
        sparseArray.put(5, TrackMainFragmentShowType.STEPS);
        sparseArray.put(6, null);
        sparseArray.put(7, TrackMainFragmentShowType.TOTAL_TIME);
        return sparseArray;
    }

    private SparseArray<TrackMainFragmentShowType> blr_() {
        SparseArray<TrackMainFragmentShowType> sparseArray = new SparseArray<>(10);
        sparseArray.put(0, TrackMainFragmentShowType.HEART_RATE);
        sparseArray.put(1, TrackMainFragmentShowType.TOTAL_TIME);
        sparseArray.put(2, TrackMainFragmentShowType.CALORIE);
        sparseArray.put(3, TrackMainFragmentShowType.PACE);
        sparseArray.put(4, TrackMainFragmentShowType.TOTAL_DISTANCES);
        sparseArray.put(5, TrackMainFragmentShowType.STEPS);
        sparseArray.put(6, null);
        sparseArray.put(7, TrackMainFragmentShowType.TOTAL_TIME);
        return sparseArray;
    }
}
