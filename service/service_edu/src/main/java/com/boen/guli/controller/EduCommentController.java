/*
* create by mybatis-plus-generator  https://github.com/xiweile
*/
package com.boen.guli.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boen.commonUtils.JwtUtils;
import com.boen.commonUtils.R;
import com.boen.guli.Feign.UcenterClient;
import com.boen.guli.pojo.EduComment;
import com.boen.guli.service.IEduCommentService;
import org.apache.http.protocol.RequestContent;
import org.omg.CORBA.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.io.Console;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author boen
 * @since 2022-05-29
 */
@RestController
@RequestMapping("/eduservice/comment")
@CrossOrigin
public class EduCommentController {

    @Autowired
    private IEduCommentService commentService;

    @Autowired
    private UcenterClient ucenterClient;


    /**
     * 查找课程评论
     * @param current
     * @param pagesize
     * @return
     */
    @GetMapping("/getallcomment/{current}/{pagesize}")
    public R getAllComment(@PathVariable Integer current,@PathVariable  Integer pagesize,
                           @RequestParam(required = false) String courseId){

        Page<EduComment> page = new Page<>(current,pagesize);

        QueryWrapper<EduComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);

        Page<EduComment> pages = commentService.page(page,queryWrapper);


        List<EduComment> commentList = pages.getRecords();

        System.out.println(commentList);


        Map<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", pages.getCurrent());
        map.put("pages", pages.getPages());
        map.put("size", pages.getSize());
        map.put("total", pages.getTotal());
        map.put("hasNext", pages.hasNext());
        map.put("hasPrevious", pages.hasPrevious());
        return R.ok().data("commentMap",map);



    }

    @PostMapping("/saveComment")
    public R insertComment(@RequestBody EduComment comment,HttpServletRequest request){

        String userId = JwtUtils.getMemberIdByJwtToken(request);
        if (userId.isEmpty())
        {
            return R.error().message("请登录后评论");
        }

        R client = ucenterClient.getUserInfofromId(userId);
        Map<String, Object> data = client.getData();
        HashMap<String,String> map = (HashMap<String, String>) data.get("userInfo"); //ucenterMenmber对象

        System.out.println(map);

        System.out.println(map.get("avatar"));


        comment.setAvatar(map.get("avatar"));
        comment.setMemberId(map.get("id"));
        comment.setNickname(map.get("nickname"));


//        UcenterMember userInfo = ucenterClient.getUserInfofromId(memberIdByJwtToken);
//
//        comment.setNickname(userInfo.getNickname());
//        comment.setAvatar(userInfo.getAvatar());
//        comment.setMemberId(userInfo.getId());
        commentService.save(comment);

        return R.ok();

    }

}
