package com.alipay.android.phone.mrpc.core;

/* loaded from: classes7.dex */
public class RpcException extends RuntimeException {
    public static final long serialVersionUID = -2875437994101380406L;
    public int mCode;
    public String mMsg;
    public String mOperationType;

    public void setOperationType(String str) {
        this.mOperationType = str;
    }

    public String getOperationType() {
        return this.mOperationType;
    }

    public String getMsg() {
        return this.mMsg;
    }

    public int getCode() {
        return this.mCode;
    }

    public static String a(Integer num, String str) {
        StringBuilder sb = new StringBuilder("RPCException: ");
        if (num != null) {
            sb.append("[");
            sb.append(num);
            sb.append("]");
        }
        sb.append(" : ");
        if (str != null) {
            sb.append(str);
        }
        return sb.toString();
    }

    public RpcException(String str) {
        super(str);
        this.mCode = 0;
        this.mMsg = str;
    }

    public RpcException(Integer num, Throwable th) {
        super(th);
        this.mCode = num.intValue();
    }

    public RpcException(Integer num, String str, Throwable th) {
        super(a(num, str), th);
        this.mCode = num.intValue();
        this.mMsg = str;
    }

    public RpcException(Integer num, String str) {
        super(a(num, str));
        this.mCode = num.intValue();
        this.mMsg = str;
    }
}
