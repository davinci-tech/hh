package defpackage;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes7.dex */
public class rvw {
    public static void d(Context context) {
        LogUtil.a("UIDV_DefaultAiRule", "setDefaultAiRule");
        StorageParams storageParams = new StorageParams();
        c(context, storageParams);
        d(context, storageParams);
        a(context, storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30004), "ai-negativeone-001", "{\"airule\":\"ai-negativeone-001\",\"enable\":true,\"params\":{\"message_push_to_negativeone_time\":\"3\"}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30004), "ai-partiactivity-001", "{\"airule\":\"ai-partiactivity-001\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"15000\",\"params\":{\"regenerate_time_interval\":\"24\",\"recommended_time\":[{\"start_time\":\"0000\",\"end_time\":\"2400\"}]}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30005), "ai-common-001", "{\"airule\":\"ai-common-001\",\"enable\":true,\"params\":{\"message_show_to_smartcard_time\":\"3\"}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30005), "ai-common-002", "{\"airule\":\"ai-common-002\",\"enable\":true,\"params\":{\"click_info_time_on_smartcard_oneday\":\"1\"}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30006), "ai-bloodsg-001", "{\"airule\":\"ai-bloodsg-001\",\"enable\":false,\"type\":\"action\",\"params\":{\"recommend_bloodsg_scale_top_num\": \"3\",\"recently_num_days_have_data\":\"90\"}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30006), "ai-bloodsg-002", "{\"airule\":\"ai-bloodsg-002\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"6000\",\"params\":{\"recommended_time\":[{\"start_time\":\"0000\",\"end_time\":\"2400\"}],\"recently_num_days_no_data\":\"3\"}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30006), "ai-bloodsg-003", "{\"airule\":\"ai-bloodsg-003\",\"enable\":false,\"type\":\"listener\",\"params\":{\"recommend_bloodsg_service_top_num\": \"3\",\"recently_num_days_have_data\":\"90\"}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(10000), "airule_ver", "1497511191287", storageParams);
    }

    private static void c(Context context, StorageParams storageParams) {
        SharedPreferenceManager.e(context, String.valueOf(30000), "ai-walk-001", "{\"airule\":\"ai-walk-001\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"16000\",\"params\":{\"recommended_time\":[{\"start_time\":\"0000\",\"end_time\":\"2400\"}]}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30000), "ai-walk-002", "{\"airule\":\"ai-walk-002\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"17000\",\"params\":{\"recommended_time\":[{\"start_time\":\"1800\",\"end_time\":\"2300\"}]}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30000), "ai-walk-003", "{\"airule\":\"ai-walk-003\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"3000\",\"params\":{\"recommended_time\":[{\"start_time\":\"1100\",\"end_time\":\"1400\"},{\"start_time\":\"1700\",\"end_time\":\"2000\"}]}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30001), "ai-weight-001", "{\"airule\":\"ai-weight-001\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"1000\",\"params\":{\"recently_num_days_have_data\":\"90\"}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30001), "ai-weight-002", "{\"airule\":\"ai-weight-002\",\"enable\":true,\"type\":\"listener\",\"params\":{\"suggest_to_set_rational_weight_goal_gap\":\"5\"}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30001), "ai-weight-003", "{\"airule\":\"ai-weight-003\",\"enable\":true,\"type\":\"listener\",\"params\":{}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30001), "ai-weight-004", "{\"airule\":\"ai-weight-004\",\"enable\":false,\"type\":\"listener\",\"params\":{\"recommend_weight_scale_top_num\":\"3\",\"recently_num_days_have_data\":\"90\"}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30001), "ai-weight-005", "{\"airule\":\"ai-weight-005\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"5000\",\"params\":{\"recently_num_days_no_data\":\"5\",\"recommended_time\":[{\"start_time\":\"0600\",\"end_time\":\"1000\"},{\"start_time\":\"1900\",\"end_time\":\"2300\"}]}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30001), "ai-weight-006", "{\"airule\":\"ai-weight-006\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"4000\",\"params\":{\"recently_num_days_have_data\":\"90\"}}", storageParams);
    }

    private static void d(Context context, StorageParams storageParams) {
        SharedPreferenceManager.e(context, String.valueOf(30001), "ai-weight-007", "{\"airule\":\"ai-weight-007\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"15000\",\"params\":{\"recently_num_days_have_data\":\"90\"}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30001), "ai-weight-008", "{\"airule\":\"ai-weight-008\",\"enable\":false,\"type\":\"listener\",\"params\":{\"recommend_weight_service_top_num\": \"3\",\"recently_num_days_have_data\":\"90\",\"recently_num_days_change\":\"30\"}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30002), "ai-bloodp-001", "{\"airule\":\"ai-bloodp-001\",\"enable\":false,\"type\":\"action\",\"params\":{\"recommend_bloodp_scale_top_num\": \"3\",\"recently_num_days_have_data\":\"90\"}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30002), "ai-bloodp-002", "{\"airule\":\"ai-bloodp-002\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"6000\",\"params\":{\"recommended_time\":[{\"start_time\":\"0600\",\"end_time\":\"1000\"},{\"start_time\":\"2000\",\"end_time\":\"2300\"}],\"recently_num_days_no_data\":\"3\"}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30002), "ai-bloodp-003", "{\"airule\":\"ai-bloodp-003\",\"enable\":false,\"type\":\"listener\",\"params\":{\"recommend_bloodp_service_top_num\": \"3\",\"recently_num_days_have_data\":\"90\",\"suggest_bloodp_professional_service_average_data\":[{\"month_average_high_bloodp_exceed\":\"140\",\"min_age\":\"40\",\"max_age\":\"50\"},{\"month_average_high_bloodp_exceed\":\"150\",\"min_age\":\"50\",\"max_age\":\"60\"},{\"month_average_high_bloodp_exceed\":\"140\",\"min_age\":\"60\"}]}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN), "ai-info-001", "{\"airule\":\"ai-info-001\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"11000\",\"params\":{\"recently_num_days_have_data\":\"90\",\"recommended_time\":[{\"start_time\":\"0000\",\"end_time\":\"2400\"}]}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN), "ai-info-002", "{\"airule\":\"ai-info-002\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"7000\",\"params\":{\"recommended_time\":[{\"start_time\":\"0000\",\"end_time\":\"2400\"}]}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN), "ai-info-003", "{\"airule\":\"ai-info-003\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"12000\",\"params\":{\"recently_num_days_have_data\":\"90\",\"recommended_time\":[{\"start_time\":\"0000\",\"end_time\":\"2400\"}]}}", storageParams);
    }

    private static void a(Context context, StorageParams storageParams) {
        SharedPreferenceManager.e(context, String.valueOf(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN), "ai-info-004", "{\"airule\":\"ai-info-004\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"8000\",\"params\":{\"recommended_time\":[{\"start_time\":\"0000\",\"end_time\":\"2400\"}]}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN), "ai-info-005", "{\"airule\":\"ai-info-005\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"9000\",\"params\":{\"recommended_time\":[{\"start_time\":\"0000\",\"end_time\":\"2400\"}]}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN), "ai-info-006", "{\"airule\":\"ai-info-006\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"10000\",\"params\":{\"recommended_time\":[{\"start_time\":\"0000\",\"end_time\":\"2400\"}]}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN), "ai-info-007", "{\"airule\":\"ai-info-007\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"13000\",\"params\":{\"recently_num_days_have_data\":\"90\",\"recommended_time\":[{\"start_time\":\"0000\",\"end_time\":\"2400\"}]}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN), "ai-info-008", "{\"airule\":\"ai-info-008\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"14000\",\"params\":{\"recently_num_days_have_data\":\"90\",\"recommended_time\":[{\"start_time\":\"0000\",\"end_time\":\"2400\"}]}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN), "ai-info-009", "{\"airule\":\"ai-info-009\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"15000\",\"params\":{\"recommended_time\":[{\"start_time\":\"0000\",\"end_time\":\"2400\"}]}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30004), "ai-exception-001", "{\"airule\":\"ai-exception-001\",\"enable\":true,\"type\":\"cycle\",\"priority\":\"2000\",\"params\":{\"after_num_date_generate_exception_prompt\":\"7\"}}", storageParams);
        SharedPreferenceManager.e(context, String.valueOf(30004), "ai-exception-002", "{\"airule\":\"ai-exception-002\",\"enable\":true,\"params\":{\"after_num_minute_generate_onboarding_prompt\":\"10080\"}}", storageParams);
    }
}
