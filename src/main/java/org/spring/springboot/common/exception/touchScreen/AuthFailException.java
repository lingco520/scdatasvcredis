/**
 *
 */
package org.spring.springboot.common.exception.touchScreen;

/**
 * @author lifaming
 */
public class AuthFailException extends Exception {

    public AuthFailException() {
        super("该API未解析");
    }

    public AuthFailException(String message) {
        super(message);
    }

}
