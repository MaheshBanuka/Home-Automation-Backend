package Service;

import Dto.User;
import Repository.UserRepo;

public class UserServiceImpl implements UserService{
    @Override
    public String registerUser(User user) {
        UserRepo userRepo = new UserRepo();
        System.out.println(user.getUserName());
        String result = userRepo.userRegister(user);
        System.out.println(result);
        return result;
    }

    @Override
    public String loginUser(String userName, String password) {
        UserRepo userRepo = new UserRepo();
        String name = userRepo.userLogin(userName, password);
        return name;
    }
}