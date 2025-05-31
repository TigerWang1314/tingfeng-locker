package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.CounterRequest;
import com.tencent.wxcloudrun.dto.LockerRequest;
import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.model.Locker;
import com.tencent.wxcloudrun.service.CounterService;
import com.tencent.wxcloudrun.service.LockerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * counter控制器
 */
@RestController

public class LockerController {

  final LockerService lockerService;
  final Logger logger;

  public LockerController(@Autowired LockerService lockerService) {
    this.lockerService = lockerService;
    this.logger = LoggerFactory.getLogger(LockerController.class);
  }


  /**
   * 获取当前计数
   * @return API response json
   */
  @GetMapping(value = "/api/locker")
  ApiResponse get() {
    logger.info("/api/locker get request");
    Integer count = 0;
//    if (counter.isPresent()) {
//      count = counter.get().getCount();
//    }

    return ApiResponse.ok(count);
  }


  /**
   * 增加一条记录
   * @param request {@link LockerRequest}
   * @return API response json
   */
  @PostMapping(value = "/api/locker")
  ApiResponse create(@RequestBody LockerRequest request) {
    logger.info("/api/locker post request, action: {}", request.getAction());

    try {
      Locker locker = new Locker();
      locker.setAction(request.getAction());
      locker.setName(request.getName());
      locker.setPhone(request.getPhone());
      locker.setDays(request.getDays());
      lockerService.add(locker);

      return ApiResponse.ok(0);
    } catch (Exception e) {
      logger.error(e.getMessage());
      return ApiResponse.error("存储失败");
    }
  }
  
}