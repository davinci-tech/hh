package com.huawei.health.sport.utils;

import android.text.TextUtils;
import com.huawei.health.servicesui.R$string;
import defpackage.arx;

/* loaded from: classes4.dex */
public class ResultUtil {

    /* loaded from: classes8.dex */
    public interface ResultCode {
        public static final int ACTION_IS_NOT_EXIST = 200014;
        public static final int ACTION_LIST_IS_NULL = 200013;
        public static final int AN_AUDIO_FILE_NOT_EXIST = 200011;
        public static final int ARGS_VALID = 1001;
        public static final int ARGS_VERIFY_ERROR = 2;
        public static final int AUDIO_LIST_IS_NULL = 200012;
        public static final int AUTHENTICATION_FAILED = 1005;
        public static final int BEYOND_TRAFFIC = 100;
        public static final int CANCEL_REQUEST = -2;
        public static final int CLOCK_ERROR = 200052;
        public static final int CREATE_PATH_FAIL = -3;
        public static final int CREATE_PLAN_FAILED = 200017;
        public static final int CREATE_PLAN_NOT_EXIST = 200016;
        public static final int FORCED_UPGRADE = 999;
        public static final int HTTP_ERROR = 500;
        public static final int HTTP_SUCCESS = 200;
        public static final int HTTP_UNAUTHORIZED = 401;
        public static final int HTTP_UNREACHABLE = 503;
        public static final int LOCAL_ARGS_FAIL = -5;
        public static final int LOGS_IS_NOT_EXIST = 15030200;
        public static final int MODULE_INIT_UNFINISHED = -6;
        public static final int NO_CLOUD = -4;
        public static final int NO_NET = -404;
        public static final int NO_PLAN_DOING = 20004;
        public static final int PARAMETER_INVALID = 200024;
        public static final int PART_SUCCESS = 1;
        public static final int PLAN_IS_EXIST = 200010;
        public static final int PLAN_IS_NOT_EXIST = 200019;
        public static final int POS_RUN_PLAN_INVALID_WEEK_INFO = 20003;
        public static final int PRIVACY_POST_OFF = 200025;
        public static final int RECORD_IS_EXIST = 200030;
        public static final int SERVER_BUSY = 101;
        public static final int SERVER_DISCONNECT = -8;
        public static final int SUCCESS = 0;
        public static final int SYSTEM_ERROR = 9999;
        public static final int TEMP_NOT_PROVIDE = 99;
        public static final int TIMEOUT = 1003;
        public static final int TIME_INVALID = 20006;
        public static final int TOKEN_OVERDUE = 1004;
        public static final int UNAVAILABLE_STORAGE_SPACE = -7;
        public static final int UPDATE_PLAN_ACHIEVEMENT_FAILED = 20009;
        public static final int UPDATE_PLAN_NAME_FAILED = 20005;
        public static final int UPDATE_PLAN_REMIND_TIME_FAILED = 20008;
        public static final int USER_NO_LOGIN = 20001;
        public static final int VIDEO_LIST_IS_NULL = 200015;
        public static final int WORKOUT_COLLECT = 200027;
        public static final int WORKOUT_NOT_EXIST = 20007;
    }

    private static String a(int i) {
        if (i == 0) {
            return arx.b().getString(R$string.sug_sucess);
        }
        if (i == 1) {
            return arx.b().getString(R$string.sug_part_success);
        }
        if (i == 2) {
            return arx.b().getString(R$string.sug_args_veri_error);
        }
        if (i == 500) {
            return arx.b().getString(R$string.sug_http_server_internal_error);
        }
        if (i == 503) {
            return arx.b().getString(R$string.sug_server_is_temporarily_inaccessible);
        }
        if (i == 999) {
            return arx.b().getString(R$string.sug_forced_upgrade);
        }
        if (i == 1001) {
            return arx.b().getString(R$string.sug_args_valid);
        }
        if (i != 1003) {
            switch (i) {
                case 99:
                    return arx.b().getString(R$string.sug_temp_not_provide);
                case 100:
                    return arx.b().getString(R$string.sug_beyond_traffic);
                case 101:
                    return arx.b().getString(R$string.sug_server_busy);
                default:
                    return "";
            }
        }
        return arx.b().getString(R$string.sug_time_out);
    }

    private static String c(int i) {
        if (i == -404) {
            return arx.b().getString(R$string.IDS_plugin_suggestion_have_no_network);
        }
        if (i == -5) {
            return arx.b().getString(R$string.local_args_fail);
        }
        if (i == 9999) {
            return arx.b().getString(R$string.sug_system_error);
        }
        if (i == 20001) {
            return arx.b().getString(R$string.sug_user_no_login);
        }
        if (i == -3) {
            return arx.b().getString(R$string.sug_create_path_fail);
        }
        if (i == -2) {
            return arx.b().getString(R$string.IDS_plugin_fitnessadvice_cancal);
        }
        if (i == 1004) {
            return arx.b().getString(R$string.sug_token_overdue);
        }
        if (i == 1005) {
            return arx.b().getString(R$string.sug_authentication_failed);
        }
        if (i != 20003) {
            return i != 20004 ? "" : arx.b().getString(R$string.sug_no_plan_doing);
        }
        return arx.b().getString(R$string.sug_posrunplan_invalid_weekinfo);
    }

    private static String e(int i) {
        if (i == -8) {
            return arx.b().getString(R$string.sug_server_disconnect);
        }
        if (i != 200019) {
            switch (i) {
                case 20005:
                    return arx.b().getString(R$string.sug_update_planname_failed);
                case 20006:
                    return arx.b().getString(R$string.sug_time_invalid);
                case 20007:
                    return arx.b().getString(R$string.sug_workout_not_exsit);
                case 20008:
                    return arx.b().getString(R$string.sug_update_plan_remindtime_failed);
                case 20009:
                    return arx.b().getString(R$string.sug_update_plan_achievement_failed);
                default:
                    switch (i) {
                        case 200010:
                            return arx.b().getString(R$string.sug_plan_is_exist);
                        case 200011:
                            return arx.b().getString(R$string.sug_an_audio_file_not_exist);
                        default:
                            return "";
                    }
            }
        }
        return arx.b().getString(R$string.sug_plan_is_not_exist);
    }

    private static String b(int i) {
        if (i == -7) {
            return arx.b().getString(R$string.sug_unavail_storage_space);
        }
        if (i == -6) {
            return arx.b().getString(R$string.sug_module_init_unfinished);
        }
        if (i == -4) {
            return arx.b().getString(R$string.sug_no_cloud);
        }
        if (i == 500) {
            return arx.b().getString(R$string.sug_http_server_internal_error);
        }
        if (i != 503) {
            if (i != 200024) {
                switch (i) {
                    case 200012:
                        break;
                    case 200013:
                        return arx.b().getString(R$string.sug_action_list_is_null);
                    case 200014:
                        return arx.b().getString(R$string.sug_action_is_not_exist);
                    case ResultCode.VIDEO_LIST_IS_NULL /* 200015 */:
                        return arx.b().getString(R$string.sug_video_list_is_null);
                    case ResultCode.CREATE_PLAN_NOT_EXIST /* 200016 */:
                        return arx.b().getString(R$string.sug_create_plan_not_exist);
                    case ResultCode.CREATE_PLAN_FAILED /* 200017 */:
                        return arx.b().getString(R$string.sug_create_plan_failed);
                    default:
                        return arx.b().getString(R$string.sug_error_unknow);
                }
            } else {
                return arx.b().getString(R$string.sug_parament_invalid);
            }
        }
        return arx.b().getString(R$string.sug_audio_list_is_null);
    }

    public static String d(int i) {
        if (!TextUtils.isEmpty(a(i))) {
            return a(i);
        }
        if (!TextUtils.isEmpty(c(i))) {
            return c(i);
        }
        if (TextUtils.isEmpty(e(i))) {
            return !TextUtils.isEmpty(b(i)) ? b(i) : "";
        }
        return e(i);
    }
}
