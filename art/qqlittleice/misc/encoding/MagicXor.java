package art.qqlittleice.misc.encoding;

/**
 * A Modified XOR encryption/decryption algorithm.
 * This algorithm will encrypt/decrypt the data bytearray using the key bytearray.
 * It will use the odd index to XOR with the previous byte, and the even index to XOR with the key.
 *
 * @author QQlittleice233
 */
public class MagicXor {

    byte[] mKey;

    public MagicXor(byte[] key) {
        if (key == null || key.length == 0) {
            throw new IllegalArgumentException("Key must not be null or empty");
        }
        this.mKey = key;
    }

   /**
    *  Encrypts the given data using the key provided.
    *  This method will not modify the original data.
    *
    */
    public static byte[] encrypt(byte[] data, byte[] key) {
        if (data == null || key == null || key.length == 0) {
            throw new IllegalArgumentException("Data must not be null. Key must not be null or empty.");
        }
        byte[] tmp = data.clone();
        __encrypt(tmp, key);
        return tmp;
    }

    /**
     *  Encrypts the given data using the key provided.
     *  This method will modify the original data.
     *
     */
    public static void _encrypt(byte[] data, byte[] key) {
        if (data == null || key == null || key.length == 0) {
            throw new IllegalArgumentException("Data must not be null. Key must not be null or empty.");
        }
        __encrypt(data, key);
    }

    private static void __encrypt(byte[] data, byte[] key) {
        int keyLength = key.length;
        int keyIndex = 0;

        for (int dataIndex = 0; dataIndex < data.length; dataIndex++) {
            if (keyIndex == keyLength) {
                keyIndex = 0;
            }
            if ((dataIndex & 1) == 1) { // Odd index
                data[dataIndex] ^= data[dataIndex - 1];
            } else { // Even index
                data[dataIndex] ^= key[keyIndex++];
            }
        }
    }

    /**
     *  Decrypts the given data using the key provided in the constructor.
     *  This method will not modify the original data.
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        if (data == null || key == null || key.length == 0) {
            throw new IllegalArgumentException("Data must not be null. Key must not be null or empty.");
        }
        byte[] tmp = data.clone();
        __decrypt(tmp, key);
        return tmp;
    }

    /**
     *  Decrypts the given data using the key provided in the constructor.
     *  This method will modify the original data.
     */
    public static void _decrypt(byte[] data, byte[] key) {
        if (data == null || key == null || key.length == 0) {
            throw new IllegalArgumentException("Data must not be null. Key must not be null or empty.");
        }
        __decrypt(data, key);
    }

    private static void __decrypt(byte[] data, byte[] key) {
        int dataLength = data.length;
        int keyLength = key.length;
        int j = dataLength - 1;
        int k = (dataLength % 2 == 0) ? (dataLength / 2) % keyLength : ((dataLength / 2) + 1) % keyLength;
        while (j >= 0) {
            if ((j + 1) % 2 == 0) {
                data[j] = (byte) (data[j] ^ data[j - 1]);
            } else {
                k--;
                if (k < 0) {
                    k = keyLength - 1;
                }
                data[j] = (byte) (data[j] ^ key[k]);
            }
            j--;
        }
    }

    /**
     *  Encrypts the given data using the key provided in the constructor.
     *  This method will not modify the original data.
     */
    public byte[] encrypt(byte[] data) {
        return encrypt(data, mKey);
    }

    /**
     *  Encrypts the given data using the key provided in the constructor.
     *  This method will modify the original data.
     */
    public void _encrypt(byte[] data) {
        _encrypt(data, mKey);
    }

    /**
     *  Decrypts the given data using the key provided in the constructor.
     *  This method will not modify the original data.
     */
    public byte[] decrypt(byte[] data) {
        return decrypt(data, mKey);
    }

    /**
     *  Decrypts the given data using the key provided in the constructor.
     *  This method will modify the original data.
     */
    public void _decrypt(byte[] data) {
        _decrypt(data, mKey);
    }

}
