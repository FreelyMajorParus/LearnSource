package org.risesun;

import org.springframework.web.bind.annotation.RequestMapping;

public interface IUserService {
    @RequestMapping("/getByUserId")
    String queryOrder(Long userId);

    @RequestMapping("/insertOrder")
    String insertOrder();
}
