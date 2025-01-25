package crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Ex1 {
    static String transformation = "AES/CBC/PKCS5Padding";

    static Cipher cipher;

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //cipher = Cipher.getInstance(transformation);

        String superKey = "SuperKey";
        byte[] bytes = Arrays.copyOf(superKey.getBytes(), 16);
        //byte[] bytes = superKey.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "AES");

        String originText = "One Two Three";

        System.out.println("Origin text: " + originText);

        String encryptText = encrypt(secretKeySpec, originText.getBytes()).toString();
        System.out.println("Encrypt text: " + encryptText);

        String deCryptText = decrypt(secretKeySpec, encryptText.getBytes()).toString();
        System.out.println("Encrypt text: " + deCryptText);
    }

    public static byte[] encrypt(SecretKeySpec secretKeySpec, byte[] plainText) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException {
        cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(plainText);
    }

    public static byte[] decrypt(SecretKeySpec secretKeySpec, byte[] encryptedText) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException {
        cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return cipher.doFinal(encryptedText);
    }
}
