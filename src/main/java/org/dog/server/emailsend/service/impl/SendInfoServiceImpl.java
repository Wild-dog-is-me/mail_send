package org.dog.server.emailsend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dog.server.emailsend.entity.SendInfo;
import org.dog.server.emailsend.service.SendInfoService;
import org.dog.server.emailsend.mapper.SendInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author odin
* @description 针对表【send_info】的数据库操作Service实现
* @createDate 2022-11-18 15:53:17
*/
@Service
public class SendInfoServiceImpl extends ServiceImpl<SendInfoMapper, SendInfo>
    implements SendInfoService {

}




