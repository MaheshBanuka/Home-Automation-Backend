package Service;

import Dto.Cart;
import Dto.User;
import Dto.Login;
import Repository.CartRepo;
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
    public String[] loginUser(Login login) {
        UserRepo userRepo = new UserRepo();
        String[] name = userRepo.userLogin(login);
        return name;
    }

    @Override
//    public String[] userservices(Login login) {
//        UserRepo userRepo = new UserRepo();
//        String servicename[] = userRepo.userservices(login);
//        return servicename;
//    }

    public String addcart(Cart cart){
        CartRepo cartrepo = new CartRepo();
        String mess = cartrepo.addcart(cart);
        return mess;
    }

    public int[] getqty(Cart cart){
        CartRepo cartrepo = new CartRepo();
        int serviceqty[]=cartrepo.getqty(cart);
        return serviceqty;
    }

    public String[] findcart(Cart cart){
        CartRepo cartrepo = new CartRepo();
        String servicenames[]=cartrepo.findcart(cart);
        return servicenames;
    }

//    public int[] getqtyor(Login login){
//        UserRepo userRepo = new UserRepo();
//        int serviceqty[] = userRepo.getqtyor(login);
//        return serviceqty;
//    }
}