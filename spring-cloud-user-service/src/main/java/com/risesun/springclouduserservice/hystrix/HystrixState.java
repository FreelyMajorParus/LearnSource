package com.risesun.springclouduserservice.hystrix;

public class HystrixState {

    private HystrixStatus hystrixStatus;

    public static HystrixState init() {
        HystrixState hystrixState = new HystrixState();
        hystrixState.hystrixStatus = HystrixStatus.close;
        return hystrixState;
    }

    public boolean isOpen(){
        return hystrixStatus == HystrixStatus.open;
    }

    public void open(){
        hystrixStatus = HystrixStatus.open;
    }
}
