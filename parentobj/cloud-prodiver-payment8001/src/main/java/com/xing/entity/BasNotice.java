package com.xing.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 公告政务
 * </p>
 *
 * @author DXX
 * @since 2021-01-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BasNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer guid;

    /**
     * 公告类型（10：平台公布；20：交通政务）
     */
    private String noticeType;

    /**
     * 公告标题
     */
    private String noticeTitle;

    /**
     * 公告内容
     */
    private String noticeContent;

    /**
     * 公告状态（10：未发布；20：已发布）
     */
    private String noticeStatus;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 平台代码
     */
    private String platId;

    /**
     * 创建人
     */
    private String createUserCode;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改人
     */
    private String modiUserCode;


}
