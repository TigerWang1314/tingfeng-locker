package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Locker;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LockerMapper {

  void add(Locker locker);
}
