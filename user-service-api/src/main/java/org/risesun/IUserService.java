package org.risesun;

import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author admin
 */
@RequestMapping("/v1")
public interface IUserService {

    /**
     * 查询用户订单信息
     * @param userId
     * @return
     */
    @RequestMapping("/getByUserId")
    String queryOrder(Long userId);

}
