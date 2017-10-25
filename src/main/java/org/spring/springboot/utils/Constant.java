package org.spring.springboot.utils;

/**
 * @author zhouq
 * @email zhouq@daqsoft.com
 * @date 2017-07-10 9:47
 * @Version:
 * @Describe: 常量
 */
public class Constant {

    /**
     * 季度
     */
    public enum QuarterType{

        /**
         * 第一季度
         */
        FIRST(1),
        /**
         * 第二季度
         */
        SECOND(2),
        /**
         * 第三季度
         */
        THIRD(3),
        /**
         * 第四季度
         */
        LAST(4);

        private int value;

        public int getValue() {
            return value;
        }

        private QuarterType(int value) {
            this.value = value;
        }
    }

}
