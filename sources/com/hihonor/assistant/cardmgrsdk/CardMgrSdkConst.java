package com.hihonor.assistant.cardmgrsdk;

import com.hihonor.android.app.GtaPkgNameConstant;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes8.dex */
public interface CardMgrSdkConst {
    public static final String ASSISTANT_SETTING = "yoyoassistantsettings";
    public static final String ASSISTANT_WIDGET_ENABLE = "hihonor_assistant_widget_enable";
    public static final String BROADCAST_DISPLAY_CARD_PERMISSION = "com.hihonor.assistant.permission.DISPLAY_CARD";
    public static final String BROADCAST_DISPLAY_EVENT_PERMISSION = "com.hihonor.assistant.permission.DISPLAY_EVENT";
    public static final String CONTENT_URL = "content://com.hihonor.assistant.displaycard";
    public static final String CONTENT_URL_CARD_CHANGED = "display_card_changed";
    public static final String CONTENT_URL_CARD_CHANGED_PARAMS = "package";
    public static final String KEY_OF_AI_ENGINE_INTELL_ABILITY_ENABLED = "intelliability_enabled";
    public static final String KEY_RECOMMEND = "recommend";
    public static final String KEY_RECOMMEND_CHANGE_TIME = "change_time";
    public static final String KEY_RECOMMEND_IS_CHANGE = "is_change";
    public static final String KEY_STATUS_AGREEMENT = "status_agreement";
    public static final String RECOMMEND_CONTENT_URL = "content://com.hihonor.assistant.recommend";
    public static final String RESULT_CODE = "resultCode";
    public static final String SP_CARDMGR_SERVICE_FILE_NAME = "cardmgr_service_sp";
    public static final String SP_SWITCH_RESET_TIME_KEY = "sp_switch_reset_time_key";
    public static final long SWITCH_RESET_TIME = 86400000;
    public static final String URL_SWITCH_QUERY = "content://com.hihonor.assistant.switchstate";

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface BizOperationType {
        public static final int OPERATION_DEFAULT = 0;
        public static final int OPERATION_DELETE = 3;
        public static final int OPERATION_LOAD = 1;
        public static final int OPERATION_NOT_STICKY = 5;
        public static final int OPERATION_ON_STICKY = 4;
        public static final int OPERATION_PARTIAL_UPDATE = 6;
        public static final int OPERATION_UPDATE = 2;
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface BroadcastAction {
        public static final String CARDSTACK_CHANGED_ACTION = "com.hihonor.assisant.action.CARDSTACK_CHANAGED";
        public static final String CARDSTACK_FAILED_ACTION = "com.hihonor.assisant.action.CARDSTACK_FAILED";
        public static final String CARD_APK_UPDATE_ACTION = "com.hihonor.assistant.cardmgr.action.CARD_APK_UPDATE";
        public static final String LOCALE_CHANGED_ACTION = "com.hihonor.assistant.cardmgr.action.CARD_LOCALE_CHANGED";
        public static final String UI_CHANGED_ACTION = "com.hihonor.assistant.cardmgr.action.CARD_UI_CHANGED";
    }

    public interface CardDispRel {
        public static final int CARD_DISPLAY_FAILURE = 201;
        public static final int CARD_DISPLAY_SUCCESS = 100;
        public static final int CARD_DISPLAY_TYPE_REMOTE_VIEWS = 0;
        public static final int CARD_DISPLAY_TYPE_TEMPLATE_CONTENT = 1;
    }

    public interface CardEventNotify {
        public static final String CARD_EVENT_AUTHORITY = "content://%s.cardevent";
        public static final String EVENT_CARD_DELETE_BY_DISPLAY = "cardDeleteByDisplay";
        public static final String EVENT_CARD_DELETE_BY_DISPLAY_FAILED = "cardDisplayFailed";
        public static final String EVENT_CARD_DELETE_BY_OVERTIME = "cardOverTime";
        public static final String EVENT_KEY_CARD_DELETE_REASON = "cardDeleteReason";
        public static final String METHOD_IGNORE_CARD = "ignoreCard";
    }

    public interface CardInfoDesc {
        public static final String CARDSTACK_REQ_ARG = "cardStackReqArg";
        public static final String CARDSTACK_REQ_ARG_LIMIT = "cardStackReqArgLimit";
        public static final String CARDSTACK_REQ_ARG_LIST = "cardStackReqArgList";
        public static final String CARDSTACK_REQ_ARG_SIEZ = "cardStackReqCardSize";
        public static final String CARDSTACK_REQ_ARG_SIZE_DESCRIBE = "cardStackReqArgSizeDescribe";
        public static final String CARDSTACK_REQ_RESULT = "cardStackReqResult";
        public static final String CARDSTACK_REQ_WIDGET_LIST = "cardStackReqWidgetList";
        public static final String CARD_STATE = "state";
        public static final String DISPLAY_ARG = "displayArg";
        public static final String DISPLAY_ARG_LIST = "displayArgList";
        public static final String DISPLAY_RESULT = "displayResult";
        public static final String DISPLAY_RESULT_LIST = "displayResultList";
        public static final String OPERATE_TYPE_ARG = "operateTypeArg";
        public static final String PARAMS_BUSINESS = "business";
        public static final String PARAMS_BUSINESS_ID = "businessId";
        public static final String PARAMS_CAN_SHOW_CARD = "argAvailable";
        public static final String PARAMS_IS_EXTERNAL_APP = "isExternalApp";
        public static final String PARAMS_LANGUAGE_TAG = "languageTag";
        public static final String PARAM_ORIGIN_SOURCE = "origin_source";
        public static final String QUERY_RESULT = "result";
    }

    public interface CardInfoDescEX {
        public static final String DISPLAY_LARGE = "large";
        public static final String DISPLAY_NORMAL = "normal";
        public static final String DISPLAY_SIZE = "card_display_size";

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes2.dex */
        public @interface DisplaySize {
        }
    }

    public interface CardMgrMethodName {
        public static final String METHOD_DISPLAY_CARD = "onDisplayCard";
        public static final String METHOD_DISPLAY_CARD_LIST = "onDisplayCardList";
        public static final String METHOD_GET_BOARD_LIMIT_CARD_LIST = "getBoardLimitCardList";
        public static final String METHOD_GET_BUSINESS_SDK_VERSION = "getBusinessVersion";
        public static final String METHOD_GET_CARD_LIST = "getCardList";
        public static final String METHOD_GET_CARD_LIST_BY_ORIGINAL = "getCardListByOriginalCards";
        public static final String METHOD_GET_DISPLAY_SDK_VERSION = "getDisplayVersion";
        public static final String METHOD_GET_LIMIT_CARD_LIST = "getLimitCardList";
        public static final String METHOD_GET_SORTED_EXCLUSIVE_CARD_LIST = "getSortedLimitCardListWithExclusive";
        public static final String METHOD_GET_SORTED_LIMIT_CARD_LIST = "getSortedLimitCardList";
        public static final String METHOD_NOTIFY_SHOW_CARD_ABLITY = "notifyShowCardAblity";
        public static final String METHOD_ON_FAIL_CARDS = "onFailCards";
        public static final String METHOD_OPERATE_CARD = "operateCard";
        public static final String METHOD_QUERY_BUSINESS = "queryBusinessInfo";
        public static final String METHOD_QUERY_CARD = "queryCardInfo";
        public static final String METHOD_QUERY_CARD_LIST = "queryCardInfoList";
        public static final String METHOD_REMOVE_CARD_STACK = "removeCardStack";
        public static final String METHOD_SWITCH_CHANGE = "switchChange";
        public static final String METHOD_UPDATE_CARDS_EXPOSE = "updateCardsExpose";
    }

    public interface CompareFlag {
        public static final int COMPARED_FLAG_CHANGE = 1;
        public static final int COMPARED_FLAG_CHANGE_NO_ANIM = 3;
        public static final int COMPARED_FLAG_CHANGE_SIZE = 2;
        public static final int COMPARED_FLAG_UNCHANGE = 0;
        public static final int COMPARED_FLAG_UNDO = -1;
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface DispOperationType {
        public static final int OPERATE_CARD_STACK_REMOVE = 4;
        public static final int OPERATE_CLICK = 2;
        public static final int OPERATE_DELETE = 1;
        public static final int OPERATE_FLIP = 3;
    }

    public interface DisplayResult {
        public static final int ON_CARD_DB_MGR_FORBID = 205;
        public static final int ON_CARD_MGR_FORBID = 204;
        public static final int ON_DISPLAY_DEFAULT = 0;
        public static final int ON_DISPLAY_FAIL = 102;
        public static final int ON_DISPLAY_FAIL_FORBID = 202;
        public static final int ON_DISPLAY_FAIL_FULL = 201;
        public static final int ON_DISPLAY_FAIL_UNKONWN = 203;
        public static final int ON_DISPLAY_PACKAGE_FORBID = 206;
        public static final int ON_DISPLAY_PARTIAL_SUCCESS = 101;
        public static final int ON_DISPLAY_SUCCESS = 100;
    }

    public interface MSGScene {
        public static final String SHOWCARD = "ShowCard";
        public static final String SYSTEM = "System";
    }

    public interface MsgNotifyResult {
        public static final int FAIL = 200;
        public static final int SUCCESS = 100;
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface PendingIntentType {
        public static final int PENDINGINTENT_TYPE_ACTIVITY = 3;
        public static final int PENDINGINTENT_TYPE_BROADCAST = 4;
        public static final int PENDINGINTENT_TYPE_FOREGROUNDSERVICE = 1;
        public static final int PENDINGINTENT_TYPE_SERVICE = 2;
    }

    public interface Query {
        public static final int QUERY_CACHE = 3;
        public static final int QUERY_DISPLAY = 2;
        public static final int QUERY_DISPLAY_SINGLE = 5;
        public static final int QUERY_FAIL = 4;
        public static final int QUERY_TOP = 1;
    }

    public interface Recommend {
        public static final String ACT_INTELLIGENT_ABILITY = "com.hihonor.aipluginengine.settings.AiEngineActivityInSetting";
        public static final String ACT_SETTING_AGREEMENT = "com.hihonor.assistant.setting.activities.YoYoAssistantAgreementActivity";
        public static final String KEY_ALL_APP_KEY = "all_app_key";
        public static final String KEY_EVENT_TYPE = "event_type";
        public static final String KEY_FROM = "from";
        public static final String KEY_IS_FIRST = "is_first";
        public static final String KEY_IS_FORCE = "is_force";
        public static final String KEY_JUMP_INFO = "jump_info";
        public static final String KEY_LAST_RECOMMEND_SUCCESS_TIME = "last_recommend_success_time";
        public static final String KEY_SERVICE_ID = "service_id";
        public static final String KEY_SERVICE_INFO = "service_info";
        public static final String KEY_STATE = "state";
        public static final String PKG_APPMARKET = GtaPkgNameConstant.GTA_PACKAGE_NAME_APPMARKET;
        public static final String PKG_INTELLIGENT_ABILITY = "com.hihonor.aipluginengine";
        public static final String PKG_YOYO = "com.hihonor.assistant";
        public static final long REQUEST_INTERVAL = 5000;
        public static final int RESERVED_APP_SIZE = 4;
        public static final int SERVICE_HEALTH_CODE = -101;
        public static final int SERVICE_TIPS = -100;
        public static final String SP_RECOMMEND_SERVICE_FILE_NAME = "recommend_service_file_name";

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes2.dex */
        public @interface Event {
            public static final String AGREEMENT = "to_agreement";
            public static final String APP_MARKET = "to_app_market";
            public static final String CARD = "to_card";
            public static final String SEND_STATE = "send_state";
            public static final String SMART_ABILITY = "to_smart_ability";
        }

        public interface Method {
            public static final String METHOD_GET_RECOMMEND_APPS = "getRecommendApps";
            public static final String METHOD_HANDLE_CARD_EVENT = "handleCardEvent";
            public static final String METHOD_RECOMMEND_STATE_CHANGE = "recommendStateChange";
            public static final String METHOD_UN_RECOMMEND_APP = "unRecommendApp";
        }

        public interface ResultCode {
            public static final int ON_FAIL = 104;
            public static final int ON_NOT_OPEN_INTELLIGENT_ABILITY = 102;
            public static final int ON_NO_AGREEMENT = 101;
            public static final int ON_NO_CHANGE = 103;
            public static final int ON_SUCCESS = 100;
        }
    }
}
