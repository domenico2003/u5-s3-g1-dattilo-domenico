package it.epicode.be.prenotazioni.model.login;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import jakarta.persistence.AttributeConverter;

//implemento  l'interfaccia AttributeConverter<String, String>
@Component
public class Convertering implements AttributeConverter<String, String> {
	// algoritmo
	private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
	// chiave segreta
	private static final String secret = "mysup3rs3cr3tttt";

//	cripto il codice che mi arriva attraverso l' algoritmo indicato sopra
	@Override
	public String convertToDatabaseColumn(String creditCardNumber) {

		try {
			Key key = new SecretKeySpec(secret.getBytes(), "AES");
			Cipher c = Cipher.getInstance(ALGORITHM);

			c.init(Cipher.ENCRYPT_MODE, key);

			return Base64.getEncoder().encodeToString(c.doFinal(creditCardNumber.getBytes()));

		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException();
		}

	}

//	decripto il codice del database attraverso l' algoritmo indicato sopra
	@Override
	public String convertToEntityAttribute(String encryptedCreditCard) {
		Key key = new SecretKeySpec(secret.getBytes(), "AES");
		try {
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key);

			return new String(c.doFinal(Base64.getDecoder().decode(encryptedCreditCard)));

		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException();
		}

	}

}