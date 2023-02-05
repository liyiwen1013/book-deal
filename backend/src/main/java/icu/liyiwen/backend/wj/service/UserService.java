package icu.liyiwen.backend.wj.service;

import icu.liyiwen.backend.result.Result;
import icu.liyiwen.backend.wj.bean.User;
import icu.liyiwen.backend.wj.dao.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author nanfang
 * @create 2020/02/20/15:32
 */
@Service("userService")
public class UserService {

    @Resource(name="userMapper")
    private UserMapper userMapper;

    public String login(User user){
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = user.getUsername();
        username = HtmlUtils.htmlEscape(username);
        User findUser=userMapper.findByUsernameAndPassword(username,user.getPassword());
        if(findUser==null){
            return "";
        }else{
            return findUser.getUid();
        }
    }
//  注册账号密码
    public Boolean register(User user){
        String username=user.getUsername();
        String password=user.getPassword();
        int row=userMapper.insertIntoUser(username,password);
        return row!=0;
    }

    //修改密码
    public Result changePassword(Map<String,Object> requestMap){
        String uid=requestMap.get("uid").toString();
        String oldPass=requestMap.get("oldPass").toString();
        String newPass=requestMap.get("newPass").toString();
        if(userMapper.changePassword(newPass,uid,oldPass)){
            return new Result(200);
        }else{
            return new Result(400);
        }
    }
    public boolean selectUser(Map<String,Object>requestMap){
        String id=requestMap.get("uid").toString();
        Integer uid = Integer.parseInt(id);
        return userMapper.findById(uid) != null;
    }
    public boolean checkUsername(String username){
        return userMapper.countByUsername(username) != 0;
    }
}
