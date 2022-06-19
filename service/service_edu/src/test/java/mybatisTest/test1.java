package mybatisTest;


import com.boen.guli.mapper.EduChapterMapper;
import com.boen.guli.pojo.chapter.ChapterVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)// RedisTestApplication 启动类
// 下面两个是其他的配置 //注解配置类写法
@ContextConfiguration({"classpath:com/boen/guli/mapper/xml/*.xml"})  //配置文件写法   这两种写法取决于你的spring核心配置是注解形式还是xml形式，选其一就可以了
public class test1 {

    @Autowired
    private EduChapterMapper mapper;


    @Test
    public void test1(){

        List<ChapterVo> chapterVoList = mapper.getChapterofCourseId("18");
        System.out.println(chapterVoList);

    }
}
