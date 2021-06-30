package com.mengma.test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author fankai
 * @date 2021年05月12日 16:46
 */
public class CopyOfTest {
    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        a = (int[]) goodCopyOf(a, 10);
        System.out.println(Arrays.toString(a));
    }

    /**
     * @param a
     * @param len
     * @return java.lang.Object
     * @description 数组拷贝
     * @author fankai
     * @date 2021/5/12 16:52
     */
    public static Object goodCopyOf(Object a, int len) {
        Class<?> clazz = a.getClass();
        if (!clazz.isArray()) return null;

        Class<?> componentType = clazz.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType, len);
        System.arraycopy(a, 0, newArray, 0, Math.min(len, length));
        return newArray;
    }
}
