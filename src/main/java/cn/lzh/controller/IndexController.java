package cn.lzh.controller;

import cn.lzh.model.User;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller//标识该类属于控制器层
public class IndexController {
    @RequestMapping(path = {"/", "/index"}, method = {RequestMethod.GET})//从url到这个方法的映射
    @ResponseBody//表示返回的是直接一个字符串而不是模板
    public String index() {
        return "这是一个首页";
    }

    @RequestMapping(path = {"/profile/{groupId}/{userId}"})//路径
    @ResponseBody
    public String index(@PathVariable("userId") int userId,//路径里的参数
                        @PathVariable("groupId") String groupId,
                        @RequestParam(value = "type", required = false) int type,//请求里的参数
                        @RequestParam(value = "key", defaultValue = "zz") String key) {
        //required = false表示这个参数可以不要;defaultValue = "zz"表示必须要参数,不写时默认是zz
        return String.format("Profile Page of %s / %d,t:%d,k:%s", groupId, userId, type, key);
    }

    @RequestMapping(path = {"/home"}, method = {RequestMethod.GET})
    public String home(Model model) {
        //传变量
        model.addAttribute("value1", "vvvv1");
        //传List
        List<String> colors = Arrays.asList(new String[]{"RED", "YELLOW", "BLUE"});
        model.addAttribute("colors", colors);
        //传map
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 4; i++)
            map.put(String.valueOf(i), String.valueOf(i * i));
        model.addAttribute("map", map);
        //传自定义对象
        model.addAttribute("user", new User("刘知昊"));
        return "home";
    }
}
