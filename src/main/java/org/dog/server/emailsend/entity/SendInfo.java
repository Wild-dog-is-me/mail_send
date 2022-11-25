package org.dog.server.emailsend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName send_info
 */
@TableName(value ="send_info")
@Data
public class SendInfo implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 发送人姓名
     */
    private String sender;

    /**
     * 发送人邮箱
     */
    private String senderMail;

    /**
     * 地区ID
     */
    private String cityId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
