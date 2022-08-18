package Commands;
import CommonConstants.CommonConstants;
import Contexts.UserContext;
import org.apache.commons.chain.Context;
import org.apache.commons.lang3.RandomStringUtils;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class  MD5Command extends CommonCommand{
    public static String getMd5(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String args[]) throws NoSuchAlgorithmException
    {
        String input="Gow123";
        int length =10;
        boolean useLetters = true;
        boolean useNumbers = true;
        String salt = RandomStringUtils.random(length, useLetters, useNumbers);
        String hash= input.concat(salt);
        for(int i=0;i<5;i++){
            hash=getMd5(hash);
        }

    }

    @Override
    public boolean excuteCommand(Context contex) throws IllegalArgumentException {
        UserContext user=(UserContext)contex.getOrDefault(CommonConstants.USER,null);
        if(user==null){
            throw new IllegalArgumentException("user should not be empty");
        }
        String passWord= user.getPassword();
        if(passWord==null){
              throw new IllegalArgumentException("passWord should not be empty");
        }
        int length =10;
        boolean useLetters = true;
        boolean useNumbers = true;
        String salt = RandomStringUtils.random(length, useLetters, useNumbers);
        String hash= passWord.concat(salt);
        for(int i=0;i<5;i++){
          hash=getMd5(hash);
        }
        contex.put(CommonConstants.HASH,hash);
        contex.put(CommonConstants.SALT,salt);
        return false;
    }

}
