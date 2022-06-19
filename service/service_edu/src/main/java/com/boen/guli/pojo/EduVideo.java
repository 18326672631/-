/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.guli.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 课程视频
 * </p>
 *
 * @author boen
 * @since 2022-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_video")
@ApiModel(value="EduVideo对象", description="课程视频")
public class EduVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "视频ID")
    private String id;

    @ApiModelProperty(value = "课程ID")
    @TableField("course_id")
    private String courseId;

    @ApiModelProperty(value = "章节ID")
    @TableField("chapter_id")
    private String chapterId;

    @ApiModelProperty(value = "节点名称")
    private String title;

    @ApiModelProperty(value = "云端视频资源")
    @TableField("video_source_id")
    private String videoSourceId;

    @ApiModelProperty(value = "原始文件名称")
    @TableField("video_original_name")
    private String videoOriginalName;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    @ApiModelProperty(value = "播放次数")
    @TableField("play_count")
    private Long playCount;

    @ApiModelProperty(value = "是否可以试听：0收费 1免费")
    @TableField("is_free")
    private Integer isFree;

    @ApiModelProperty(value = "视频时长（秒）")
    private Float duration;

    @ApiModelProperty(value = "Empty未上传 Transcoding转码中  Normal正常")
    private String status;

    @ApiModelProperty(value = "视频源文件大小（字节）")
    private Long size;

    @ApiModelProperty(value = "乐观锁")
    private Long version;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
