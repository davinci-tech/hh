package defpackage;

import android.util.LruCache;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.dictionary.model.HealthDataMergePolicy;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class ivx {

    public static class i implements Comparator<HiHealthData>, Serializable {
        private static final long serialVersionUID = 7917358048129161438L;

        @Override // java.util.Comparator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
            return Double.compare(hiHealthData2.getValue(), hiHealthData.getValue());
        }
    }

    public static class g implements Comparator<HiHealthData>, Serializable {
        private static final long serialVersionUID = 3563079841739268264L;

        @Override // java.util.Comparator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
            return Double.compare(hiHealthData2.getModifiedTime(), hiHealthData.getModifiedTime());
        }
    }

    public static class j implements Comparator<HiHealthData>, Serializable {
        private static final long serialVersionUID = -6461843525091425852L;

        @Override // java.util.Comparator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
            return Double.compare(hiHealthData.getValue(), hiHealthData2.getValue());
        }
    }

    public static class h implements Comparator<HiHealthData>, Serializable {
        private static final long serialVersionUID = 7917358048129161438L;

        /* renamed from: a, reason: collision with root package name */
        private final String f13634a;

        public h(String str) {
            this.f13634a = str;
        }

        @Override // java.util.Comparator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
            try {
                return ivx.a(hiHealthData, hiHealthData2, this.f13634a);
            } catch (JSONException e) {
                ReleaseLogUtil.d("HiH_HiCompareUtil", "SequenceComparator JSONException!, e is ", e.getMessage());
                return 0;
            }
        }
    }

    public static class m implements Comparator<HiHealthData>, Serializable {
        private static final long serialVersionUID = -6461843525091425852L;
        private final String d;

        public m(String str) {
            this.d = str;
        }

        @Override // java.util.Comparator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
            try {
                return ivx.a(hiHealthData2, hiHealthData, this.d);
            } catch (JSONException e) {
                ReleaseLogUtil.d("HiH_HiCompareUtil", "SequenceComparator JSONException!, e is ", e.getMessage());
                return 0;
            }
        }
    }

    public static class c implements Comparator<HiHealthData>, Serializable {
        private static final long serialVersionUID = -4424372499856808535L;

        /* renamed from: a, reason: collision with root package name */
        private HashMap<Integer, Integer> f13633a = new HashMap<>();
        private String b;

        private void b(HashMap<Integer, Integer> hashMap) {
            this.f13633a = hashMap;
        }

        private void b(String str) {
            this.b = str;
        }

        public c(HashMap<Integer, Integer> hashMap, String str) {
            b(hashMap);
            b(str);
        }

        @Override // java.util.Comparator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
            HiDeviceInfo d = ijf.d(BaseApplication.getContext()).d(hiHealthData2.getDeviceUuid());
            HiDeviceInfo d2 = ijf.d(BaseApplication.getContext()).d(hiHealthData.getDeviceUuid());
            if (d == null || d2 == null) {
                return 0;
            }
            int deviceType = d.getDeviceType();
            int deviceType2 = d2.getDeviceType();
            Integer num = this.f13633a.get(Integer.valueOf(deviceType));
            Integer num2 = this.f13633a.get(Integer.valueOf(deviceType2));
            if (num == null) {
                num = r3;
            }
            return ivx.d(hiHealthData, hiHealthData2, num.intValue(), (num2 != null ? num2 : 0).intValue(), this.b);
        }
    }

    public static class b implements Comparator<HiHealthData>, Serializable {

        /* renamed from: a, reason: collision with root package name */
        private static LruCache<String, String> f13632a = new LruCache<>(16);
        private static final long serialVersionUID = -4424372499856808535L;
        private Map<String, Integer> b = new HashMap();
        private String e;

        private void b(Map<String, Integer> map) {
            this.b = map;
        }

        private void e(String str) {
            this.e = str;
        }

        public b(Map<String, Integer> map, String str) {
            b(map);
            e(str);
        }

        @Override // java.util.Comparator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
            ProductMapInfo productMapInfo;
            ProductMapInfo productMapInfo2;
            String str = f13632a.get(hiHealthData2.getDeviceUuid());
            String str2 = f13632a.get(hiHealthData.getDeviceUuid());
            if (str == null || str2 == null) {
                HiDeviceInfo d = ijf.d(BaseApplication.getContext()).d(hiHealthData2.getDeviceUuid());
                HiDeviceInfo d2 = ijf.d(BaseApplication.getContext()).d(hiHealthData.getDeviceUuid());
                if (d == null || d2 == null) {
                    return 0;
                }
                int deviceType = d.getDeviceType();
                int deviceType2 = d2.getDeviceType();
                List<ProductMapInfo> b = ProductMap.b("deviceId", String.valueOf(deviceType));
                List<ProductMapInfo> b2 = ProductMap.b("deviceId", String.valueOf(deviceType2));
                if (HiCommonUtil.d(b) || HiCommonUtil.d(b2)) {
                    productMapInfo = null;
                    productMapInfo2 = null;
                } else {
                    productMapInfo = b.get(0);
                    productMapInfo2 = b2.get(0);
                }
                str = productMapInfo == null ? null : productMapInfo.e();
                str2 = productMapInfo2 == null ? null : productMapInfo2.e();
                if (str != null) {
                    f13632a.put(hiHealthData2.getDeviceUuid(), str);
                }
                if (str2 != null) {
                    f13632a.put(hiHealthData.getDeviceUuid(), str2);
                }
            }
            Integer num = this.b.get(str);
            Integer num2 = this.b.get(str2);
            if (num == null) {
                num = r3;
            }
            return ivx.d(hiHealthData, hiHealthData2, num.intValue(), (num2 != null ? num2 : 0).intValue(), this.e);
        }
    }

    public static class k implements Comparator<HiHealthData>, Serializable {
        private static final long serialVersionUID = -9060400917279988443L;

        @Override // java.util.Comparator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
            return iic.d(hiHealthData2.getType(), hiHealthData.getType());
        }
    }

    public static class e implements Comparator<HiHealthData>, Serializable {
        private static final long serialVersionUID = -8047434309060251472L;

        @Override // java.util.Comparator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
            return iic.e(hiHealthData2.getType(), hiHealthData.getType());
        }
    }

    public static class d implements Comparator<HiHealthData>, Serializable {
        private static final long serialVersionUID = -4424372499856808535L;

        @Override // java.util.Comparator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
            return iic.c(hiHealthData2.getType(), hiHealthData.getType());
        }
    }

    public static class a implements Comparator<Integer>, Serializable {
        private static final long serialVersionUID = 2979456610634806074L;

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Integer num, Integer num2) {
            HiDeviceInfo e = ikk.a(BaseApplication.getContext()).e(num.intValue());
            HiDeviceInfo e2 = ikk.a(BaseApplication.getContext()).e(num2.intValue());
            if (e == null || e2 == null) {
                LogUtil.h("HiH_HiCompareUtil", "compare error input ! lhs is ", num, " rhs is ", num2);
                return 0;
            }
            int priority = e2.getPriority() - e.getPriority();
            return priority != 0 ? priority : e2.getDeviceUniqueCode().compareTo(e.getDeviceUniqueCode());
        }
    }

    public static class f implements Comparator<HiHealthData>, Serializable {
        private static final long serialVersionUID = 2979456610634806074L;

        @Override // java.util.Comparator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public int compare(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
            int type = hiHealthData2.getType() - hiHealthData.getType();
            return type != 0 ? type : hiHealthData.getMergedStatus() - hiHealthData2.getMergedStatus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int a(HiHealthData hiHealthData, HiHealthData hiHealthData2, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(hiHealthData.getMetaData());
        JSONObject jSONObject2 = new JSONObject(hiHealthData2.getMetaData());
        return CommonUtil.d(jSONObject2, str) - CommonUtil.d(jSONObject, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int d(HiHealthData hiHealthData, HiHealthData hiHealthData2, int i2, int i3, String str) {
        char c2;
        int compare = Integer.compare(i2, i3);
        if (compare != 0) {
            return compare;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case 76100:
                if (str.equals("MAX")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 76338:
                if (str.equals("MIN")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 77184:
                if (str.equals(HealthDataMergePolicy.NEW)) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 78343:
                if (str.equals(HealthDataMergePolicy.OLD)) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            return Double.compare(hiHealthData2.getValue(), hiHealthData.getValue());
        }
        if (c2 == 1) {
            return Double.compare(hiHealthData.getValue(), hiHealthData2.getValue());
        }
        if (c2 == 2) {
            return -1;
        }
        if (c2 == 3) {
            return 1;
        }
        LogUtil.h("HiH_HiCompareUtil", "This mergePolicy ", str, " is not supported!");
        return compare;
    }
}
