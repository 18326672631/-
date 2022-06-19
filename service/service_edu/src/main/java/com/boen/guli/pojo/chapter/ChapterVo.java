package com.boen.guli.pojo.chapter;


import lombok.Data;

import java.util.List;

@Data
public class ChapterVo {

    private String id;

    private String title;

    private List<VideoVo> videoVoList;
}
