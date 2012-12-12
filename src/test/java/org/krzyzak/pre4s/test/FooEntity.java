package org.krzyzak.pre4s.test;

/**
 * Created with IntelliJ IDEA.
 * User: tomasz
 * Date: 12.12.12
 * Time: 23:49
 * To change this template use File | Settings | File Templates.
 */
public class FooEntity {

    private String message;

    public FooEntity(String dupa) {
        this.message = dupa;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
