package com.huawei.watchface.mvp.model.datatype;

import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.da;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.IntegerUtils;

/* loaded from: classes7.dex */
public class TlvUtils {
    private static final int DATA_WRAPPER_BASE_LENGTH_HIGH_MIN = 128;
    private static final int ERROR_CODE = 127;
    private static final int ERROR_CODE_TIMEOUT = 100009;
    private static final int MESSAGE_SUB_LENGTH = 8;
    private static final int REMOVE_BYTE_HIGH_VALUE = 127;
    private static final int SHIFT_OPERATOR_SEVEN = 7;
    private static final String TAG = "TlvUtils";
    private static final int UNIT_HEX_LENGTH = 2;

    public TlvFather builderTlvList(String str) throws TlvException {
        TlvFather tlvFather = new TlvFather();
        if (str == null) {
            HwLog.w(TAG, "hexString is null");
            return tlvFather;
        }
        int i = 0;
        while (i != str.length()) {
            try {
                String substring = SafeString.substring(str, i, i + 2);
                ValuePosition lengthAndPosition = getLengthAndPosition(str, i + substring.length());
                int value = lengthAndPosition.getValue();
                i = lengthAndPosition.getPosition();
                if (value != 0) {
                    int i2 = (value * 2) + i;
                    if (i2 > str.length()) {
                        throw new TlvException();
                    }
                    String substring2 = SafeString.substring(str, i, i2);
                    i += substring2.length();
                    if ((IntegerUtils.a(substring, 16) >>> 7) == 1) {
                        tlvFather.getTlvFatherList().add(builderTlvList(substring2));
                    } else {
                        tlvFather.getTlvList().add(new Tlv(substring, value, substring2));
                    }
                } else {
                    tlvFather.getTlvList().add(new Tlv(substring, value, ""));
                }
            } catch (IndexOutOfBoundsException unused) {
                throw new TlvException();
            }
        }
        return tlvFather;
    }

    public TlvTransfer builderTlvTransfer(TlvTransfer tlvTransfer, String str, int i) throws TlvException {
        if (tlvTransfer == null || str == null || i < 0) {
            return null;
        }
        int i2 = 0;
        while (i2 != str.length()) {
            try {
                String substring = SafeString.substring(str, i2, i2 + 2);
                int i3 = (i + i2) / 2;
                ValuePosition lengthAndPosition = getLengthAndPosition(str, i2 + substring.length());
                int value = lengthAndPosition.getValue();
                i2 = lengthAndPosition.getPosition();
                if (value != 0) {
                    int i4 = (value * 2) + i2;
                    if (i4 > str.length()) {
                        throw new TlvException();
                    }
                    String substring2 = SafeString.substring(str, i2, i4);
                    int length = substring2.length() + i2;
                    buildTlvTransfer(tlvTransfer, i3, i + i2, substring, substring2);
                    i2 = length;
                } else {
                    String num = Integer.toString(IntegerUtils.a(substring, 16));
                    if (num.charAt(0) == '0') {
                        num = SafeString.substring(num, 1, num.length());
                    }
                    tlvTransfer.mTlvTransfers.add(new TlvTransfer(i3, num, num));
                }
            } catch (IndexOutOfBoundsException unused) {
                throw new TlvException();
            }
        }
        return tlvTransfer;
    }

    private void buildTlvTransfer(TlvTransfer tlvTransfer, int i, int i2, String str, String str2) throws TlvException {
        if ((IntegerUtils.a(str, 16) >>> 7) == 1) {
            String num = Integer.toString(IntegerUtils.a(str, 16) & 127);
            if (num.charAt(0) == '0') {
                num = SafeString.substring(num, 1, num.length());
            }
            tlvTransfer.mTlvTransfers.add(new TlvTransfer(i, num, num));
            builderTlvTransfer(tlvTransfer, str2, i2);
            return;
        }
        String num2 = Integer.toString(IntegerUtils.a(str, 16));
        if (num2.charAt(0) == '0') {
            num2 = SafeString.substring(num2, 1, num2.length());
        }
        tlvTransfer.mTlvTransfers.add(new TlvTransfer(i, num2, num2));
    }

    private ValuePosition getLengthAndPosition(String str, int i) throws TlvException {
        int i2;
        try {
            int a2 = IntegerUtils.a(SafeString.substring(str, i, i + 2), 16);
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (((a2 >>> 7) & 1) == 1) {
                if (i3 < 4) {
                    int i6 = i + 2;
                    int i7 = a2 & 127;
                    if (i3 == 0) {
                        i4 = i7;
                    } else if (i3 != 1) {
                        HwLog.w(TAG, "getLengthAndPosition default");
                    } else {
                        i5 = i7;
                    }
                    a2 = IntegerUtils.a(SafeString.substring(str, i6, i + 4), 16);
                    i3++;
                    i = i6;
                } else {
                    throw new TlvException();
                }
            }
            if (i3 != 2) {
                if (i3 == 1) {
                    i2 = i4 * 128;
                }
                return new ValuePosition(a2, i + 2);
            }
            i2 = (i4 * 16384) + (i5 * 128);
            a2 += i2;
            return new ValuePosition(a2, i + 2);
        } catch (IndexOutOfBoundsException unused) {
            throw new TlvException();
        }
    }

    static class ValuePosition {
        private int mPosition;
        private int mValue;

        private ValuePosition(int i, int i2) {
            this.mValue = i;
            this.mPosition = i2;
        }

        public int getValue() {
            return this.mValue;
        }

        public void setValue(int i) {
            this.mValue = i;
        }

        public int getPosition() {
            return this.mPosition;
        }

        public void setPosition(int i) {
            this.mPosition = i;
        }
    }

    public static boolean isTimeout(byte[] bArr) {
        if (bArr[2] == Byte.MAX_VALUE) {
            String a2 = da.a(bArr);
            try {
                if (IntegerUtils.a(SafeString.substring(a2, 8, a2.length()), 16) == ERROR_CODE_TIMEOUT) {
                    return true;
                }
            } catch (NumberFormatException unused) {
                HwLog.e(TAG, "isTimeout exception");
            }
        }
        return false;
    }
}
