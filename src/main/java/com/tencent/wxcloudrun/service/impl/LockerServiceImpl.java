package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.LockerMapper;
import com.tencent.wxcloudrun.model.Locker;
import com.tencent.wxcloudrun.service.LockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LockerServiceImpl implements LockerService {

  final LockerMapper lockerMapper;

  public LockerServiceImpl(@Autowired LockerMapper lockerMapper) {
    this.lockerMapper = lockerMapper;
  }

  @Override
  public void add(Locker locker) {
    this.lockerMapper.add(locker);
  }
}
