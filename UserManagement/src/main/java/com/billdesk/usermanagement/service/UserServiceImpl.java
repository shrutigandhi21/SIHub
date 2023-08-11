package com.billdesk.usermanagement.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billdesk.usermanagement.dao.IUserDao;
import com.billdesk.usermanagement.dao.UserDaoImpl;
import com.billdesk.usermanagement.entity.User;
import com.billdesk.usermanagement.exception.UserAlreadyExist;
import com.billdesk.usermanagement.requestdto.LoginDto;
import com.billdesk.usermanagement.requestdto.SignUpDto;
@Service
public class UserServiceImpl implements IUserService {
	
@Autowired
private IUserDao dao;

@Autowired
private UserDaoImpl userDaoImpl;

Validation v = new Validation();
@Override
public User insertUser(User u) throws UserAlreadyExist{

	if (v.checkSpecialCharacters(u.getPassword())) {
		System.out.println("Password has special characters");
 		
    }
    u.setStatus("ACTIVE");
    try {
		u.setPassword(toHexString(getSHA(u.getPassword())));
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    var fetched = dao.findUserByEmail(u.getEmail());
    if(fetched.isPresent())
    	throw new UserAlreadyExist();
	return dao.save(u);
	

	}

@Override
public User deleteUserById(int id) {
	// TODO Auto-generated method stub
//	if(dao.existsById(id)) {
//		dao.deleteById(id);
//		//modify status to deleted 
//		return true;
//		}
//	else
//		return false;
		Optional<User> user = dao.findById(id);
        //user.get().setEmail("");
		user.get().setStatus("DELETED");
		return dao.save(user.get());
	
}



@Override
public User editUser(int id, User u) {
	// TODO Auto-generated method stub
	User updatedU = null;
	if(dao.existsById(id)) {
		Optional<User> userOpt = dao.findById(id);
		User user = userOpt.get();
		if(u.getFname() != null) {
			user.setFname(u.getFname());
		}
		if(u.getLname() != null) {
			user.setLname(u.getLname());
		}
		if(u.getUnumber() != null) {
			user.setUnumber(u.getUnumber());
		}
		updatedU = dao.save(user);
	}
	updatedU.setPassword(null);
	u.setStatus("USER DETAILS UPDATED");
	return updatedU;
	
}

@Override
public List<User> getAllUser() {
	List<User> list =dao.findAll();
	for (User user : list) {
		user.setPassword(null);
	}
	// TODO Auto-generated method stub
	return list;
}

@Override
public ApiResponse signUp(SignUpDto signUpDto) {
    validateSignUp(signUpDto);
    User user = new User();

    BeanUtils.copyProperties(signUpDto, user);
    userDaoImpl.save(user);
    return new ApiResponse(200, "success", user);
}

@Override
public ApiResponse login(LoginDto loginDto) {
    User user = dao.findByEmail(loginDto.getEmail());
    if(user == null) {
        throw new RuntimeException("User does not exist.");
    }
    try {
		if(!user.getPassword().equals(toHexString(getSHA(loginDto.getPassword())))){
		    throw new RuntimeException("Wrong password.");
		}
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	}
    
    return new ApiResponse(200, "Login success",user) ;

}

public static byte[] getSHA(String input) throws NoSuchAlgorithmException
{
    // Static getInstance method is called with hashing SHA
    MessageDigest md = MessageDigest.getInstance("SHA-256");

    // digest() method called
    // to calculate message digest of an input
    // and return array of byte
    return md.digest(input.getBytes(StandardCharsets.UTF_8));
}
 
public static String toHexString(byte[] hash)
{
    // Convert byte array into signum representation
    BigInteger number = new BigInteger(1, hash);

    // Convert message digest into hex value
    StringBuilder hexString = new StringBuilder(number.toString(16));

    // Pad with leading zeros
    while (hexString.length() < 64)
    {
        hexString.insert(0, '0');
    }

    return hexString.toString();
}

private void validateSignUp(SignUpDto signUpDto) {
}


}

