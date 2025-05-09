package com.huawei.ohos.localability;

/* loaded from: classes5.dex */
public class FormException extends Exception {

    /* renamed from: a, reason: collision with root package name */
    private String f6544a;
    private FormError e;

    public enum FormError {
        INPUT_PARAM_INVALID(8585223, "invalid params received on operating form"),
        FMS_RPC_ERROR(8585246, "get fms rpc failed"),
        BMS_RPC_ERROR(8585249, "get bms rpc failed"),
        SEND_FMS_MSG_ERROR(8585248, "send request to fms failed"),
        SEND_BMS_MSG_ERROR(8585250, "send request to bms failed"),
        PERMISSION_DENY(8585218, "check permission deny, need to request ohos.permission.REQUIRE_FORM"),
        FORM_INFO_NOT_FOUND(8585220, "can't get form info by the formName"),
        GET_BUNDLE_FAILED(8585221, "the requested bundle name does not exist"),
        INIT_LAYOUT_FAILED(8585222, "can't get layout with requested dimension and orientation"),
        BIND_PROVIDER_FAILED(8585226, "fms bind provider failed"),
        FORM_DUPLICATE_ADDED(8585247, "form do not support acquire same id thrice"),
        FORM_CFG_NOT_MATCH_ID(8585224, "the form id and form config are not matched"),
        FORM_ID_NOT_EXIST(8585225, "the requested form id is not existed on fms"),
        EXCEED_MAX_SYSTEM_FORMS(8585227, "exceed max forms in system, current limit is 512"),
        EXCEED_MAX_INSTANCES_PER_FORM(8585228, "exceed max instances per form, limit is 32"),
        FORM_NOT_SELF_OWNED(8585229, "the form to be operated is not self-owned or has been deleted already"),
        PROVIDER_DELETE_FAIL(8585230, "fms notify provider to delete failed"),
        EXCEED_MAX_FORMS_PER_CLIENT(8585231, "exceed max forms per client, limit is 256"),
        EXCEED_MAX_SYSTEM_TEMP_FORMS(8585232, "exceed max temp forms in system, limit is 256"),
        MODULE_NOT_EXIST(8585233, "the module not exist in the bundle."),
        ABILITY_NOT_EXIST(8585234, "the ability not exist in the module."),
        DIMENSION_NOT_EXIST(8585235, "the dimension not exist in the form."),
        FA_NOT_INSTALLED(8585236, "the ability not installed,need install first."),
        MAX_REQUEST(8585237, "too many request,try again later."),
        MAX_REFRESH(8585238, "maximum number of refresh times reached, try again tomorrow."),
        FA_NOT_UPDATE_BY_SCREEN_OFF(8585240, "screen is off, can't update form."),
        FA_NOT_IN_CURRENT_USER(8585241, "form userId is not current userId, can't update form."),
        FA_INVISIBLE(8585242, "form is invisible, can't update form"),
        AGENT_PERMISSION_DENY(8585243, "check permission deny, need to request ohos.permission.UPDATE_FORM_AGENT"),
        INTERFACE_NOT_FOUND(8585244, "the interface is not found"),
        START_ABILITY_FAILED(8585251, "ability can not be found, can't start ability"),
        FORM_IN_RECOVER(8585252, "form is in recover status, can't do action on form"),
        INTERNAL_ERROR(8585217, "some internal server occurs");


        /* renamed from: a, reason: collision with root package name */
        private int f6545a;
        private String b;

        String b() {
            return this.b;
        }

        int a() {
            return this.f6545a;
        }

        public static FormError a(int i) {
            for (FormError formError : values()) {
                if (formError.a() == i) {
                    return formError;
                }
            }
            return null;
        }

        FormError(int i, String str) {
            this.f6545a = i;
            this.b = str;
        }
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.f6544a + ": " + super.getMessage();
    }

    public FormError c() {
        return this.e;
    }

    public FormException(FormError formError, String str) {
        super(str);
        this.f6544a = "";
        this.f6544a = formError != null ? formError.toString() : "";
        this.e = formError;
    }

    public FormException(FormError formError) {
        this(formError, formError != null ? formError.b() : "");
    }
}
