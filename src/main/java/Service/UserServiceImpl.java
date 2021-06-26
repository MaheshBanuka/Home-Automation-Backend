package Service;

import Dto.User;
import Dto.Login;
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
    public String loginUser(Login login) {
        UserRepo userRepo = new UserRepo();
        String name = userRepo.userLogin(login);
        return name;
    }

    @Override
    public String[] userservices(Login login) {
        UserRepo userRepo = new UserRepo();
        String servicename[] = userRepo.userservices(login);
        return servicename;
    }
}