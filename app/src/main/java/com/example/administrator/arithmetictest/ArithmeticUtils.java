package com.example.administrator.arithmetictest;

import android.util.Log;

/**
 * Created by Administrator on 2017/12/9.
 */

public class ArithmeticUtils {
    private static final String TAG = "ArithmeticUtils";

    /**
     * 正数加
     *
     * @param num1
     * @param num2
     * @param isPositive 是否为正
     * @return
     */
    public static StringBuilder positiveNumberAdd(String num1, String num2, boolean isPositive) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int tag = 0;
        int sum;
        StringBuilder str = new StringBuilder("");
        while (i >= 0 && j >= 0) {
            sum = (num1.charAt(i) - '0') + (num2.charAt(j) - '0') + tag;
            if (sum > 9) {
                tag = 1;
                sum = sum % 10;
            } else {
                tag = 0;
            }
            str.insert(0, sum);
            i--;
            j--;
        }
//把多的位拖下来
        while (i >= 0) {
            if (tag > 0) {
                sum = (num1.charAt(i) - '0') + tag;
                if (sum > 9) {
                    tag = 1;
                    sum = sum % 10;
                } else {
                    tag = 0;
                }
                str.insert(0, sum);
                i--;
            } else {
                sum = num1.charAt(i) - '0';
                str.insert(0, sum);
                i--;
            }
        }

        while (j >= 0) {
            if (tag > 0) {
                sum = num2.charAt(j) - '0' + tag;
                if (sum > 9) {
                    tag = 1;
                    sum = sum % 10;
                } else {
                    tag = 0;
                }
                str.insert(0, sum);
                j--;
            } else {
                sum = num2.charAt(j) - '0';
                str.insert(0, sum);
                j--;
            }
        }
//是否有必要往高位进一
        if (tag == 1) {
            str.insert(0, 1);
        }
        String subStr = str.toString();
        int m = 0;
        while (subStr.charAt(m) == '0' && m < subStr.length() - 1) {
            m++;
        }
//去掉大整数前面的所有无效的数字0
        subStr = subStr.substring(m);
        str.setLength(0);
        str.append(subStr);
        if (!isPositive) {
            str.insert(0, "-");
        }

        return str;
    }

    /**
     * 正数减
     *
     * @param num1
     * @param num2
     * @param isPositive  是否为正
     * @return
     */
    public static StringBuilder positiveNumberSub(String num1, String num2, boolean isPositive) {
        int tag = 0;
        int sub = 0;
        StringBuilder str = new StringBuilder("");
        //让大数减小数
        if ((num1.length() == num2.length() && num1.compareTo(num2) < 0) || (num1.length() < num2.length())) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        Log.d(TAG, "positiveNumberSub: 0" + i + " " + j);
        while (i >= 0 && j >= 0) {
            sub = (num1.charAt(i) - '0') - (num2.charAt(j) - '0') + tag;
            if (sub < 0) {
                tag = -1;
                sub = 10 + sub;
            } else {
                tag = 0;
            }
            str.insert(0, sub);
            i--;
            j--;
        }
//把大数拖下来
        while (i >= 0) {
            Log.d(TAG, "positiveNumberSub:1 " + sub);
            if (tag < 0) {
                Log.d(TAG, "positiveNumberSub: 2" + sub);
                sub = (num1.charAt(i) - '0') + tag;
                if (sub < 0) {
                    tag = -1;
                    sub = sub + 10;
                } else {
                    tag = 0;
                }
                str.insert(0, sub);
                i--;
            } else {

                sub = num1.charAt(i) - '0';
                Log.d(TAG, "positiveNumberSub: 3" + sub);
                str.insert(0, sub);
                i--;
            }
        }
        String subStr = str.toString();
        int m = 0;
        while (subStr.charAt(m) == '0' && m < subStr.length() - 1) {
            m++;
        }
//去掉大整数前面的所有无效的数字0
        subStr = subStr.substring(m);
        str.setLength(0);
        str.append(subStr);
        if (!isPositive) {
            str.insert(0, '-');
        }

        return str;
    }

    /**
     * 进行加法运算
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String add(String num1, String num2) {
        if (num1.length() <= 0 && num2.length() <= 0) return "0";
        boolean isPositive = true;
        StringBuilder result = new StringBuilder();
        if (num1.charAt(0) == '-' && num2.charAt(0) == '-') {
            int m = 1;
            int n = 1;
            while (num1.charAt(m) == '0' && m < num1.length() - 1) {
                m++;
            }
            while (num2.charAt(n) == '0' && n < num2.length() - 1) {
                n++;
            }
            num1 = "-" + num1.substring(m);//去掉大整数前面的所有无效的数字0
            num2 = "-" + num2.substring(n);//去掉大整数前面的所有无效的数字0
            //两个负数相加；去掉符号
            result = positiveNumberAdd(num1.substring(1), num2.substring(1), false);
        } else if (num1.charAt(0) != '-' && num2.charAt(0) != '-') {
            int m = 0;
            int n = 0;
            while (num1.charAt(m) == '0' && m < num1.length() - 1) {
                m++;
            }
            while (num2.charAt(n) == '0' && n < num2.length() - 1) {
                n++;
            }
            num1 = num1.substring(m);//去掉大整数前面的所有无效的数字0
            num2 = num2.substring(n);//去掉大整数前面的所有无效的数字0
            //两个正数相加
            result = positiveNumberAdd(num1, num2, true);
        } else if (num1.charAt(0) == '-') {
            int m = 1;
            int n = 0;
            while (num1.charAt(m) == '0' && m < num1.length() - 1) {
                m++;
            }
            while (num2.charAt(n) == '0' && n < num2.length() - 1) {
                n++;
            }
            num1 = "-" + num1.substring(m);//去掉大整数前面的所有无效的数字0
            num2 = num2.substring(n);//去掉大整数前面的所有无效的数字0
            //两个数异号，做减法运算
            num1 = num1.substring(1);
            //确定计算后的正负
            if ((num1.length() > num2.length()) || (num1.length() == num2.length() && num1.compareTo(num2) > 0)) {
                isPositive = false;
            } else {
                isPositive = true;
            }
            result = positiveNumberSub(num1, num2, isPositive);
        } else if (num2.charAt(0) == '-') {
            int m = 0;
            int n = 1;
            while (num1.charAt(m) == '0' && m < num1.length() - 1) {
                m++;
            }
            while (num2.charAt(n) == '0' && n < num2.length() - 1) {
                n++;
            }
            num1 = num1.substring(m);//去掉大整数前面的所有无效的数字0
            num2 = "-" + num2.substring(n);//去掉大整数前面的所有无效的数字0
            num2 = num2.substring(1);
            if ((num1.compareTo(num2) < 0 && num1.length() == num2.length()) || (num1.length() < num2.length())) {
                isPositive = false;
            } else {
                isPositive = true;
            }
            result = positiveNumberSub(num1, num2, isPositive);
        }
        Log.d(TAG, "add: " + result);
        String addResult = "" + result;
        return addResult;
    }

    /**
     * 进行减法运算
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String sub(String num1, String num2) {
        boolean isPositive = true;
        StringBuilder result = new StringBuilder();
        if (num1.charAt(0) == '-' && num2.charAt(0) == '-') {
            int m = 1;
            int n = 1;
            while (num1.charAt(m) == '0' && m < num1.length() - 1) {
                m++;
            }
            while (num2.charAt(n) == '0' && n < num2.length() - 1) {
                n++;
            }
            num1 = "-" + num1.substring(m);//去掉大整数前面的所有无效的数字0
            num2 = "-" + num2.substring(n);//去掉大整数前面的所有无效的数字0
            //两个数都为负
            if ((num1.length() > num2.length()) || (num1.length() == num2.length() && num1.compareTo(num2) > 0)) {
                isPositive = false;
            }
            result = positiveNumberSub(num1.substring(1), num2.substring(1), isPositive);
        } else if (num1.charAt(0) != '-' && num2.charAt(0) != '-') {
            int m = 0;
            int n = 0;
            while (num1.charAt(m) == '0' && m < num1.length() - 1) {
                m++;
            }
            while (num2.charAt(n) == '0' && n < num2.length() - 1) {
                n++;
            }
            num1 = num1.substring(m);//去掉大整数前面的所有无效的数字0
            num2 = num2.substring(n);//去掉大整数前面的所有无效的数字0
            if ((num1.compareTo(num2) < 0 && num1.length() == num2.length()) || (num1.length() < num2.length())) {
                isPositive = false;
            }
            result = positiveNumberSub(num1, num2, isPositive);
        } else if (num1.charAt(0) == '-') {
            int m = 1;
            int n = 0;
            while (num1.charAt(m) == '0' && m < num1.length() - 1) {
                m++;
            }
            while (num2.charAt(n) == '0' && n < num2.length() - 1) {
                n++;
            }
            num1 = "-" + num1.substring(m);//去掉大整数前面的所有无效的数字0
            num2 = num2.substring(n);//去掉大整数前面的所有无效的数字0
            isPositive = false;
            result = positiveNumberAdd(num1.substring(1), num2, isPositive);
        } else if (num2.charAt(0) == '-') {
            int m = 0;
            int n = 1;
            while (num1.charAt(m) == '0' && m < num1.length() - 1) {
                m++;
            }
            while (num2.charAt(n) == '0' && n < num2.length() - 1) {
                n++;
            }
            num1 = num1.substring(m);//去掉大整数前面的所有无效的数字0
            num2 = "-" + num2.substring(n);//去掉大整数前面的所有无效的数字0
            result = positiveNumberAdd(num1, num2.substring(1), isPositive);
        }
        Log.d(TAG, "sub: " + result);
        String subResult = "" + result;
        return subResult;
    }

    /**
     * 进行乘法运算
     *
     * @param data1
     * @param data2
     * @return
     */
    public static String mul(String data1, String data2) {
        int i = 0, j = 0;
        if (data1.charAt(0) == '-') {
            data1 = data1.substring(1);//去掉大整数前面的负号
            i++;
        }

        if (data2.charAt(0) == '-') {
            data2 = data2.substring(1);//去掉大整数前面的负号
            j++;
        }
        int[] num1 = new int[data1.length()];
        int[] num2 = new int[data2.length()];
        //将字符串转换为整数数组，倒序
        for (int x = 0; x < data1.length(); x++) {
            num1[data1.length() - 1 - x] = data1.charAt(x) - '0';
        }
        for (int y = 0; y < data2.length(); y++) {
            num2[data2.length() - 1 - y] = data2.charAt(y) - '0';
        }
        for (int x = num1.length - 1; x >= 0; x--) {
            Log.d(TAG, "doMul: " + num1[x]);
        }
        int[] result = multiply(num1, num2);
        for (int x = result.length - 1; x >= result.length / 2; x--) {
            Log.d(TAG, "result: " + result[x]);
            Log.d(TAG, "resultLength: " + result.length);
            int temp = result[x];
            result[x] = result[result.length - 1 - x];
            result[result.length - 1 - x] = temp;
            Log.d(TAG, "change_result: " + result[x]);
        }
        for (int x = 0; x < result.length; x++) {
            Log.d(TAG, "Test : " + result[x]);
        }
        String lastResult = "";
        //进行符号的判断
        if (i + j == 1) {
            lastResult = "-";
            for (int k = 0; k < num1.length + num2.length; k++) {
                lastResult += result[k];
            }
            int m = 1;
            while (lastResult.charAt(m) == '0' && m < lastResult.length() - 1) {
                m++;
            }
            lastResult ="-" +  lastResult.substring(m);//去掉大整数前面的所有无效的数字0
        } else {
            for (int k = 0; k < num1.length + num2.length; k++) {
                lastResult += result[k];
            }
            int m = 0;
            while (lastResult.charAt(m) == '0' && m < lastResult.length() - 1) {
                m++;
            }
            lastResult = lastResult.substring(m);//去掉大整数前面的所有无效的数字0
        }
        Log.d(TAG, "doMul: " + lastResult);
        return lastResult;
    }

    /**
     * 两个正数相乘
     *
     * @param num1
     * @param num2
     * @return
     */
    public static int[] multiply(int[] num1, int[] num2) {
        int lengthOfNum1 = num1.length;
        int lengthOfNum2 = num2.length;
        // 分配一个空间，用来存储运算的结果，n1长的数* n2长的数，结果不会超过n1+n2长
        int[] result = new int[lengthOfNum1 + lengthOfNum2];
        // 先不考虑进位问题，n1的第i位与n2的第j位相乘，结果应该存放在结果的第i+j位上
        for (int i = 0; i < lengthOfNum1; i++) {
            for (int j = 0; j < lengthOfNum2; j++) {
                result[i + j] += num1[i] * num2[j];
            }
        }
        // 单独处理进位问题
        for (int i = 0; i < lengthOfNum1 + lengthOfNum2 - 1; i++) {
            if (result[i] > 10) {
                result[i + 1] += result[i] / 10;
                result[i] %= 10;
            }
        }
        return result;
    }

    /**
     * 除法运算
     *
     * @param data1
     * @param data2
     * @return
     */
    public static String div(String data1, String data2) {

        int i = 0, j = 0, x = 0, y = 0;
        String divResult = "";

        if (data1.charAt(0) == '-') {
            data1 = data1.substring(1);//去掉大整数前面的负号
            x++;
        }

        if (data2.charAt(0) == '-') {
            data2 = data2.substring(1);//去掉大整数前面的负号
            y++;
        }
        Log.d(TAG, "div: g" + data2);
        while (data1.charAt(i) == '0' && i < data1.length() - 1) {
            i++;
        }

        data1 = data1.substring(i);//去掉大整数前面的所有无效的数字0
        Log.d(TAG, "div: gc" + data2);
        while (j < data2.length() - 1 && data2.charAt(j) == '0') {
            j++;
        }
        data2 = data2.substring(j);
        Log.d(TAG, "div: gcc" + data2);

        if (judgment(data2)) {
            Log.d(TAG, "div: " + "除数不能为0，请重新输入！");
            divResult = "除数不能为0";
        } else {
            divResult = division(data1, data2);
            if (x + y == 1) {
                divResult = "-" + divResult;
            }
        }
        return divResult;
    }

    public static String division(String data1, String data2) {
        String result = "";
        int num = data1.length() - data2.length();

        if ((data1.length() < data2.length()) || (data1.length() == data2.length() && data1.compareTo(data2) < 0)) {
            num = -1;
        }
        Log.d(TAG, "division: gchaha" + judgment(String.valueOf(positiveNumberSub(data1, data2, true))) + String.valueOf(positiveNumberSub(data1, data2, true)));
        if (num < 0) {
            result = "0";
        } else if (judgment(String.valueOf(positiveNumberSub(data1, data2, true)))) {
            result = "1";
        } else {
            String mulResult = "";
            while (num >= 0) {

                while (data1.length() > data2.length()) {
                    data2 = data2 + "0";
                }
                if ((data1.length() < data2.length()) || (data1.length() == data2.length() && data1.compareTo(data2) < 0)) {
                    data1 += "0";
                }
                for (int i = 9; i > 0; i--) {
                    mulResult = mul(data2, String.valueOf(i));
                    if ((mulResult.length() < data1.length()) || (mulResult.length() == data1.length() && mulResult.compareTo(data1) < 0)) {
                        data1 = String.valueOf(positiveNumberSub(data1, mul(data2, String.valueOf(i)), true));// 减去已经除去的字符段
                        result += i;
                        i = 0;
                    }
                    if (i == 1) {
                        result += 0;
                    }
                }
                num--;
            }
        }
        return result;
    }

    /**
     *  判断data是否为0
     *
     * @param data
     * @return
     */
    public static boolean judgment(String data) {
        int z = 0, num = data.length();
        Log.d(TAG, "judgment: " + data + "  " + num);
        while (num >= 0) {
            if (data.charAt(0) != '0') {
                z++;
                Log.d(TAG, "judgment: z" + z);
            }
            num--;
        }
        if (z == 0) {
            return true;
        } else {
            return false;
        }
    }
}
