package com.huawei.hms.framework.network.download.internal.constants;

import com.huawei.hms.network.file.core.Constants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public interface ExceptionCode {
    public static final int BASE_TRANSPOSITION = 10046000;
    public static final int CHECK_FILE_HASH_FAILED = 1104;
    public static final int CHECK_FILE_SIZE_FAILED = 1105;
    public static final int CHECK_TASK_FAILED = 1103;
    public static final int CONNECTION_ABORT = 110205;
    public static final int CONNECTION_REFUSED = 110209;
    public static final int CONNECTION_RESET = 110204;
    public static final int CONNECT_FAILED = 110206;
    public static final int CREATE_DOWNLOAD_FILE_FAILED = 1107;
    public static final int DOWNLOAD_RENAME_ERROR = 1112;
    public static final Map<Integer, Integer> ERROR_CODE_MAP = Collections.unmodifiableMap(new HashMap<Integer, Integer>() { // from class: com.huawei.hms.framework.network.download.internal.constants.ExceptionCode.1
        {
            put(Integer.valueOf(Constants.ErrorCode.UNKNOW_ERROR.getErrorCode()), 1100);
            put(Integer.valueOf(ErrorCode.DOWNLOAD_TRANSPOSITION_UNKNOWN_ERROR.getErrorCode()), 1100);
            put(Integer.valueOf(Constants.ErrorCode.TASK_PAUSE_EXCEPTION.getErrorCode()), 1101);
            put(Integer.valueOf(Constants.ErrorCode.TASK_CANCEL_EXCEPTION.getErrorCode()), 1101);
            put(10000802, 1102);
            put(10000803, Integer.valueOf(ExceptionCode.NETWORK_READ_TIMEOUT));
            put(10000800, Integer.valueOf(ExceptionCode.UNEXPECTED_EOF));
            put(10000300, Integer.valueOf(ExceptionCode.UNABLE_TO_RESOLVE_HOST));
            put(10000601, Integer.valueOf(ExceptionCode.READ_ERROR));
            put(10000401, Integer.valueOf(ExceptionCode.CONNECTION_RESET));
            put(10000402, Integer.valueOf(ExceptionCode.CONNECTION_ABORT));
            put(10000403, Integer.valueOf(ExceptionCode.CONNECT_FAILED));
            put(10000301, Integer.valueOf(ExceptionCode.ROUTE_FAILED));
            put(10000200, Integer.valueOf(ExceptionCode.NETWORK_UNREACHABLE));
            put(10000404, Integer.valueOf(ExceptionCode.CONNECTION_REFUSED));
            put(10000500, Integer.valueOf(ExceptionCode.SSL_EXCEPTION));
            put(10000501, Integer.valueOf(ExceptionCode.SSL_HANDSHAKE_EXCEPTION));
            put(10000502, Integer.valueOf(ExceptionCode.SSL_PEERUNVERIFIED_EXCEPTION));
            put(10000804, Integer.valueOf(ExceptionCode.INTERRUPT_EXCEPTION));
            put(10000405, Integer.valueOf(ExceptionCode.INTERRUPT_CONNECT_CLOSE));
            put(10000406, Integer.valueOf(ExceptionCode.SOCKET_CLOSE));
            put(10000400, Integer.valueOf(ExceptionCode.SOCKET_CONNECT_TIMEOUT));
            put(10000600, Integer.valueOf(ExceptionCode.SOCKET_READ_TIMEOUT));
            put(10000700, Integer.valueOf(ExceptionCode.SOCKET_WRITE_TIMEOUT));
            put(Integer.valueOf(Constants.ErrorCode.TASK_DOWNLOAD_PARAMS_COMMON_ERROR.getErrorCode()), 1103);
            put(Integer.valueOf(ErrorCode.DOWNLOAD_TRANSPOSITION_PARAMS_COMMON_ERROR.getErrorCode()), 1103);
            put(Integer.valueOf(Constants.ErrorCode.REQUEST_URL_EMPTY.getErrorCode()), 1103);
            put(Integer.valueOf(Constants.ErrorCode.CHECK_FILE_HASH_FAILED.getErrorCode()), Integer.valueOf(ExceptionCode.CHECK_FILE_HASH_FAILED));
            put(Integer.valueOf(Constants.ErrorCode.FILE_SIZE_ERROR.getErrorCode()), Integer.valueOf(ExceptionCode.CHECK_FILE_SIZE_FAILED));
            put(Integer.valueOf(Constants.ErrorCode.CREATE_DOWNLOAD_FILE_FAILED.getErrorCode()), Integer.valueOf(ExceptionCode.CREATE_DOWNLOAD_FILE_FAILED));
            put(Integer.valueOf(Constants.ErrorCode.FILE_IO_EXCEPTION.getErrorCode()), Integer.valueOf(ExceptionCode.FILE_IO_EXCEPTION));
            put(Integer.valueOf(Constants.ErrorCode.SERVER_EXCEPTION.getErrorCode()), Integer.valueOf(ExceptionCode.SERVER_EXCEPTION));
            put(Integer.valueOf(Constants.ErrorCode.TASK_COMPOSE_TMPFILE_ERROR.getErrorCode()), Integer.valueOf(ExceptionCode.DOWNLOAD_RENAME_ERROR));
            put(Integer.valueOf(Constants.ErrorCode.FILE_NO_EXIST.getErrorCode()), Integer.valueOf(ExceptionCode.FILE_NO_EXIST));
        }
    });
    public static final int FILE_IO_EXCEPTION = 1109;
    public static final int FILE_NO_EXIST = 1113;
    public static final int INTERRUPT_CONNECT_CLOSE = 110214;
    public static final int INTERRUPT_EXCEPTION = 110213;
    public static final int NETWORK_IO_EXCEPTION = 1102;
    public static final int NETWORK_READ_TIMEOUT = 110200;
    public static final int NETWORK_UNREACHABLE = 110208;
    public static final int READ_ERROR = 110203;
    public static final int ROUTE_FAILED = 110207;
    public static final int SERVER_EXCEPTION = 1110;
    public static final int SOCKET_CLOSE = 110215;
    public static final int SOCKET_CONNECT_TIMEOUT = 110221;
    public static final int SOCKET_READ_TIMEOUT = 110223;
    public static final int SOCKET_WRITE_TIMEOUT = 110225;
    public static final int SSL_EXCEPTION = 110210;
    public static final int SSL_HANDSHAKE_EXCEPTION = 110211;
    public static final int SSL_PEERUNVERIFIED_EXCEPTION = 110212;
    public static final int TASK_BE_INTERRUPTED = 1101;
    public static final int UNABLE_TO_RESOLVE_HOST = 110202;
    public static final int UNEXPECTED_EOF = 110201;
    public static final int UNKNOW_ERROR = 1100;

    public enum ErrorCode {
        DOWNLOAD_TRANSPOSITION_UNKNOWN_ERROR(10046001, "download unknown error:"),
        DOWNLOAD_TRANSPOSITION_PARAMS_COMMON_ERROR(10046002, "download params error:");

        private int errorCode;
        private String errorMessage;

        ErrorCode(int i, String str) {
            this.errorCode = i;
            this.errorMessage = str;
        }

        public int getErrorCode() {
            return this.errorCode;
        }

        public String getErrorMessage() {
            return this.errorMessage;
        }
    }
}
