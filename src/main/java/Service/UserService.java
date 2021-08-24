package Service;

import Dto.Cart;
import Dto.User;
import Dto.Login;

public interface UserService {
    public String registerUser(User user);

    public String[] loginUser(Login login);

//    public String[] userservices(Login login);

    public String addcart(Cart cart);

    public String[] findcart(Cart cart);

    public int[] getqty(Cart cart);

//    public int[] getqtyor(Login login);
}
