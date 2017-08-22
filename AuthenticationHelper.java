import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *  For more information
 *  Refer : http://www.forte.net/devdocs/
 * */
public class AuthenticationHelper {

	/**
	 *  Secure Transaction key 
	 */
	private static final String SECURE_KEY= "YourSecureTransactionKey";

	/**
	 * Api Login Id
	 */
	public static final String API_LOGIN_ID ="YourApiLoginID";
	
	/** Allowed hash method : md5, sha1, sha256 */
	public static final String HASH_METHOD = "HMACMD5";
	
	/**
	 *  Version No
	 */
	public static final String VERSION_NO = "1.0";
	
	/**
	 * Payment Methods
	 */
	public static final String  METHOD_SALE = "sale";
	
	public static final String  METHOD_SCHEDULE = "schedule";
			
	
	/**
	 * 
	 * @param totalAmount Total Amount to be charge to user
	 * @param orderNumber Order No in your own system
	 * @param utcTime
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	public String generateSignatureForSingleAmount(String totalAmount, String orderNumber, String utcTime) 
			throws InvalidKeyException, NoSuchAlgorithmException { 		
		String value =	API_LOGIN_ID + "|" + METHOD_SALE + "|" + VERSION_NO + "|" + totalAmount +"|" + utcTime + "|" + orderNumber +"||";
		return generateSignature(value);
	}
	
	public String generateSignature(String value) throws NoSuchAlgorithmException, InvalidKeyException {		
		byte[] keyBytes = SECURE_KEY.getBytes();
		Key key = new SecretKeySpec(keyBytes, 0, keyBytes.length, HASH_METHOD); 
		Mac mac = Mac.getInstance(HASH_METHOD);
		mac.init(key); 
		return byteArrayToHex(mac.doFinal(value.getBytes()));
	}	
	
	/**
	 *  Utility Method
	 *  @param Byte array which need to convert into hex string
	 *  @return Hes String
	 * **/
	protected String byteArrayToHex(byte [] a) {
		int hn, ln, cx;
		String hexDigitChars = "0123456789abcdef";
		StringBuffer buf = new StringBuffer(a.length * 2);
		for(cx = 0; cx < a.length; cx++) {
			hn = ((int)(a[cx]) & 0x00ff) / 16;
			ln = ((int)(a[cx]) & 0x000f);
			buf.append(hexDigitChars.charAt(hn));
			buf.append(hexDigitChars.charAt(ln));
		}
		return buf.toString();
	}
}
