package com.leodelmiro.cupcakes.services

import org.springframework.stereotype.Component
import java.security.SecureRandom
import java.security.spec.KeySpec
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

@Component
class EncryptService {

    fun createSalt(): ByteArray {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)
        return salt
    }

    fun encode(password: String, salt: ByteArray = createSalt()): ByteArray {
        val factory = createSecretKeyFactory(password, salt)

        return factory.encoded
    }

    fun matches(password: String, passwordSalt: ByteArray, passwordEncoded: ByteArray): Boolean =
            encode(password, passwordSalt).let {
                it.contentEquals(passwordEncoded)
            }

    private fun createSecretKeyFactory(password: String, salt: ByteArray): SecretKey {
        val spec: KeySpec = PBEKeySpec(password.toCharArray(), salt, 65536, 128)
        return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(spec)
    }
}
