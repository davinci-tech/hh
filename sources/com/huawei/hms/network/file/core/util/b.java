package com.huawei.hms.network.file.core.util;

import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hms.network.exception.NetworkException;
import com.huawei.hms.network.file.api.exception.HttpException;
import com.huawei.hms.network.file.api.exception.InternalException;
import com.huawei.hms.network.file.api.exception.InterruptedException;
import com.huawei.hms.network.file.api.exception.NetWorkErrorException;
import com.huawei.hms.network.file.api.exception.NetWorkIOException;
import com.huawei.hms.network.file.api.exception.ParamsCheckException;
import com.huawei.hms.network.file.api.exception.ServerException;
import com.huawei.hms.network.file.api.exception.UnKnownErrorException;
import com.huawei.hms.network.file.core.Constants;
import com.huawei.hms.network.file.core.FileManagerException;
import com.huawei.operation.utils.Constants;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes4.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static final List<Integer> f5639a = Collections.unmodifiableList(new a());
    private static final List<Integer> b = Collections.unmodifiableList(new C0149b());
    private static final List<Integer> c = Collections.unmodifiableList(new c());
    private static final List<Integer> d = Collections.unmodifiableList(new d());
    private static final List<Integer> e = Collections.unmodifiableList(new e());

    public static boolean a(Throwable th) {
        if (th instanceof FileManagerException) {
            th = th.getCause();
        }
        return (th == null || (th instanceof IllegalArgumentException) || (th instanceof NetworkException) || (th instanceof CancellationException) || (th instanceof RejectedExecutionException) || (th instanceof FileNotFoundException)) ? false : true;
    }

    public static com.huawei.hms.network.file.api.exception.NetworkException a(FileManagerException fileManagerException) {
        int errorCode = fileManagerException.getErrorCode();
        if (a(fileManagerException.getCause())) {
            return new NetWorkErrorException("network error(" + fileManagerException.getErrorCode() + Constants.RIGHT_BRACKET_ONLY, fileManagerException);
        }
        if (f5639a.contains(Integer.valueOf(fileManagerException.getErrorCode()))) {
            return new ParamsCheckException("params check error(" + fileManagerException.getErrorCode() + Constants.RIGHT_BRACKET_ONLY, fileManagerException);
        }
        if (b.contains(Integer.valueOf(fileManagerException.getErrorCode()))) {
            return new NetWorkIOException("io exception(" + fileManagerException.getErrorCode() + Constants.RIGHT_BRACKET_ONLY, fileManagerException);
        }
        if (c.contains(Integer.valueOf(fileManagerException.getErrorCode()))) {
            return new HttpException("http exception(" + fileManagerException.getErrorCode() + Constants.RIGHT_BRACKET_ONLY, fileManagerException);
        }
        if (d.contains(Integer.valueOf(fileManagerException.getErrorCode()))) {
            return new InterruptedException(errorCode, "interrupted exception(" + fileManagerException.getErrorCode() + Constants.RIGHT_BRACKET_ONLY, fileManagerException);
        }
        if (e.contains(Integer.valueOf(fileManagerException.getErrorCode()))) {
            return new UnKnownErrorException("unknown error(" + fileManagerException.getErrorCode() + Constants.RIGHT_BRACKET_ONLY, fileManagerException);
        }
        String str = "inner exception(" + errorCode + Constants.RIGHT_BRACKET_ONLY;
        if (errorCode >= 1000) {
            return new InternalException(str, fileManagerException);
        }
        return new ServerException(fileManagerException.getErrorCode(), "server exception(" + errorCode + Constants.RIGHT_BRACKET_ONLY, fileManagerException);
    }

    class a extends ArrayList<Integer> {
        a() {
            add(Integer.valueOf(Constants.ErrorCode.TASK_UPLOAD_PARAMS_COMMON_ERROR.getErrorCode()));
            add(Integer.valueOf(Constants.ErrorCode.TASK_DOWNLOAD_PARAMS_COMMON_ERROR.getErrorCode()));
            add(Integer.valueOf(Constants.ErrorCode.FILE_SIZE_ERROR.getErrorCode()));
            add(Integer.valueOf(Constants.ErrorCode.TASK_UPLOAD_PARAMS_ERROR.getErrorCode()));
            add(Integer.valueOf(Constants.ErrorCode.CHECK_FILE_HASH_FAILED.getErrorCode()));
        }
    }

    /* renamed from: com.huawei.hms.network.file.core.util.b$b, reason: collision with other inner class name */
    class C0149b extends ArrayList<Integer> {
        C0149b() {
            add(Integer.valueOf(Constants.ErrorCode.CREATE_DOWNLOAD_FILE_FAILED.getErrorCode()));
            add(Integer.valueOf(Constants.ErrorCode.WRITE_FILE_EXCEPTION.getErrorCode()));
            add(Integer.valueOf(Constants.ErrorCode.FILE_IO_EXCEPTION.getErrorCode()));
        }
    }

    class c extends ArrayList<Integer> {
        c() {
            add(Integer.valueOf(Constants.ErrorCode.SERVER_EXCEPTION.getErrorCode()));
        }
    }

    class d extends ArrayList<Integer> {
        d() {
            add(Integer.valueOf(Constants.ErrorCode.TASK_PAUSE_EXCEPTION.getErrorCode()));
            add(Integer.valueOf(Constants.ErrorCode.TASK_CANCEL_EXCEPTION.getErrorCode()));
        }
    }

    class e extends ArrayList<Integer> {
        e() {
            add(Integer.valueOf(Constants.ErrorCode.UNKNOW_ERROR.getErrorCode()));
            add(Integer.valueOf(Constants.ErrorCode.TASK_UPLOAD_UNKNOWN_ERROR.getErrorCode()));
        }
    }

    public static int a(IOException iOException) {
        if (iOException == null) {
            return -1;
        }
        int errorCodeFromException = ExceptionCode.getErrorCodeFromException(iOException);
        FLogger.v("ExceptionUtil", "getErrorCodeFromException errorcode from resclient: " + errorCodeFromException + ",message:" + iOException.getMessage());
        return errorCodeFromException;
    }
}
