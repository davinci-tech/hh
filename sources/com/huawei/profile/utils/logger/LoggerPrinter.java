package com.huawei.profile.utils.logger;

import android.text.TextUtils;
import android.util.Log;
import com.huawei.operation.utils.Constants;
import com.huawei.profile.utils.SensitiveUtil;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Locale;

/* loaded from: classes6.dex */
final class LoggerPrinter implements Printer {
    private static final int BLOCK_SIZE = 4000;
    private static final String ENCODING_FORMAT = "utf-8";
    private static final int INVALID_INDEX = -1;
    private static final int LEVEL_DEBUG = 3;
    private static final int LEVEL_ERROR = 6;
    private static final int LEVEL_INFO = 4;
    private static final int LEVEL_VERBOSE = 2;
    private static final int LEVEL_WARN = 5;
    private static final String LOG_SEPARATOR = ":";
    private static final int START_STACK_OFFSET = 3;
    private static final String STRING_POINT = ".";
    private static final String TAG = "LoggerPrinter";
    private String logTag;
    private final Settings loggerSettings = new Settings();
    private final Object mLock = new Object();

    LoggerPrinter() {
        init("");
    }

    @Override // com.huawei.profile.utils.logger.Printer
    public Settings init(String str) {
        if (str != null && str.trim().length() > 0) {
            this.logTag = str;
        }
        return this.loggerSettings;
    }

    @Override // com.huawei.profile.utils.logger.Printer
    public void verbose(String str, Object... objArr) {
        print(2, (Throwable) null, str, objArr);
    }

    @Override // com.huawei.profile.utils.logger.Printer
    public void debug(String str, Object... objArr) {
        print(3, (Throwable) null, str, objArr);
    }

    @Override // com.huawei.profile.utils.logger.Printer
    public void info(String str, Object... objArr) {
        print(4, (Throwable) null, str, objArr);
    }

    @Override // com.huawei.profile.utils.logger.Printer
    public void warn(String str, Object... objArr) {
        print(5, (Throwable) null, str, objArr);
    }

    @Override // com.huawei.profile.utils.logger.Printer
    public void error(String str, Object... objArr) {
        error(null, str, objArr);
    }

    @Override // com.huawei.profile.utils.logger.Printer
    public void error(Throwable th, String str, Object... objArr) {
        print(6, th, str, objArr);
    }

    private void print(int i, Throwable th, String str, Object... objArr) {
        synchronized (this.mLock) {
            print(i, this.logTag, buildMessage(str, objArr), th);
        }
    }

    private void print(int i, String str, String str2, Throwable th) {
        String str3;
        synchronized (this.mLock) {
            if (th == null) {
                str3 = str2;
            } else if (str2 == null) {
                str3 = getStackTraceString(th);
            } else {
                str3 = str2 + ":" + getStackTraceString(th);
            }
            if (TextUtils.isEmpty(str3)) {
                return;
            }
            String buildLogHeader = buildLogHeader();
            if (!buildLogHeader.isEmpty()) {
                str3 = buildLogHeader + ":" + str2;
            }
            try {
                byte[] bytes = str3.getBytes(ENCODING_FORMAT);
                int length = bytes.length;
                if (length <= 4000) {
                    printBlock(i, str, str3);
                    return;
                }
                for (int i2 = 0; i2 < length; i2 += 4000) {
                    printBlock(i, str, new String(bytes, i2, Math.min(length - i2, 4000), ENCODING_FORMAT));
                }
            } catch (IOException unused) {
                Log.e(TAG, "io exception happened");
            }
        }
    }

    private void printBlock(int i, String str, String str2) {
        LogAdapter logAdapter = this.loggerSettings.getLogAdapter();
        LogAdapter secondLogAdapter = this.loggerSettings.getSecondLogAdapter();
        if (logAdapter != null) {
            if (i == 2) {
                logAdapter.verbose(str, str2);
                if (secondLogAdapter != null) {
                    secondLogAdapter.verbose(str, str2);
                    return;
                }
                return;
            }
            if (i == 4) {
                logAdapter.info(str, str2);
                if (secondLogAdapter != null) {
                    secondLogAdapter.info(str, str2);
                    return;
                }
                return;
            }
            if (i == 5) {
                logAdapter.warn(str, str2);
                if (secondLogAdapter != null) {
                    secondLogAdapter.warn(str, str2);
                    return;
                }
                return;
            }
            if (i == 6) {
                logAdapter.error(str, str2);
                if (secondLogAdapter != null) {
                    secondLogAdapter.error(str, str2);
                    return;
                }
                return;
            }
            logAdapter.debug(str, str2);
            if (secondLogAdapter != null) {
                secondLogAdapter.debug(str, str2);
            }
        }
    }

    private String buildLogHeader() {
        StringBuilder sb = new StringBuilder();
        if (this.loggerSettings.isShowThreadInfo()) {
            sb.append("[");
            sb.append(Thread.currentThread().getName());
            sb.append("]");
        }
        if (!this.loggerSettings.isShowMethodInfo() && !this.loggerSettings.isShowLineNumber()) {
            return sb.toString();
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int stackOffset = getStackOffset(stackTrace) + this.loggerSettings.getMethodOffset();
        if (stackOffset > 0 && stackOffset < stackTrace.length) {
            if (sb.length() != 0) {
                sb.append(" ");
            }
            if (this.loggerSettings.isShowMethodInfo()) {
                sb.append(getSimpleClassName(stackTrace[stackOffset].getClassName()));
                sb.append(".");
                sb.append(stackTrace[stackOffset].getMethodName());
            }
            if (this.loggerSettings.isShowLineNumber()) {
                sb.append(" (");
                sb.append(stackTrace[stackOffset].getFileName());
                sb.append(":");
                sb.append(stackTrace[stackOffset].getLineNumber());
                sb.append(Constants.RIGHT_BRACKET_ONLY);
            }
        }
        return sb.toString();
    }

    private String buildMessage(String str, Object... objArr) {
        return (objArr == null || objArr.length == 0) ? str : String.format(Locale.ENGLISH, str, objArr);
    }

    private String getSimpleClassName(String str) {
        return str.lastIndexOf(".") >= 0 ? str.substring(str.lastIndexOf(".") + 1) : "";
    }

    private int getStackOffset(StackTraceElement[] stackTraceElementArr) {
        for (int i = 3; i < stackTraceElementArr.length; i++) {
            String className = stackTraceElementArr[i].getClassName();
            if (!className.equals(LoggerPrinter.class.getName()) && !className.equals(AndroidLogger.class.getName())) {
                return i;
            }
        }
        return -1;
    }

    private String getStackTraceString(Throwable th) {
        if (th == null) {
            return "";
        }
        for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
            if (th2 instanceof UnknownHostException) {
                return "";
            }
        }
        StackTraceElement[] stackTrace = th.getStackTrace();
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append(SensitiveUtil.getMessage(th));
        for (StackTraceElement stackTraceElement : stackTrace) {
            sb.append(System.lineSeparator());
            sb.append(stackTraceElement.toString());
        }
        return sb.toString();
    }
}
