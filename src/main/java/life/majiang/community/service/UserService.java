package life.majiang.community.service;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


    public void createOrUpdate(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users=userMapper.selectByExample(example);
        if(users.size()==0){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            User updateUser=new User();
            updateUser.setGmtCreate(System.currentTimeMillis());
            updateUser.setGmtModified(updateUser.getGmtCreate());
            updateUser.setToken(user.getToken());
            updateUser.setName(user.getName());
            UserExample example1 = new UserExample();
            example1.createCriteria().andAccountIdEqualTo(user.getAccountId());
            userMapper.updateByExampleSelective(updateUser, example1);
        }
    }
}
