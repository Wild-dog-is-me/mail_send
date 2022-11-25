package org.dog.server.emailsend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dog.server.emailsend.entity.SendInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author odin
* @description 针对表【send_info】的数据库操作Mapper
* @createDate 2022-11-18 15:53:17
* @Entity generator.domain.SendInfo
*/

@Mapper
public interface SendInfoMapper extends BaseMapper<SendInfo> {

}




