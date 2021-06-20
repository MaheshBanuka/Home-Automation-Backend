package Service;

import Dto.User;
import Dto.Login;

public interface UserService {
    public String registerUser(User user);

    public String loginUser(Login login);
}
