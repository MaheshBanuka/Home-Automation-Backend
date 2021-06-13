package Service;

import Dto.User;

public interface UserService {
    public String registerUser(User user);

    public String loginUser(String userName, String Password);
}
