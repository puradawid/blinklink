package pl.edu.pb.blinklink.model.util;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Collection;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.SerializationUtils;

/**
 * Class with utils, for operations on models without influencing in content.
 * @author dawid
 */
public class BlinkUtil {
    
    /**
     * Serialize object to MD5 hash
     * @param object object to serialization i digesting
     * @return MD5 String sum.
     */
    public static String toMD5(Serializable object)
    {
        byte[] stream = SerializationUtils.serialize(object);
        return DigestUtils.md5Hex(stream);
    }
    
    /**
     * Serialize collection of objects to MD5 hash.
     * @param objects collection of objects to MD5
     * @return MD5 sum
     */
    public static String toMD5(Collection<Serializable> objects)
    {
        String md5 = "";
        for(Serializable s : objects)
        {
            md5 += BlinkUtil.toMD5(s);
        }
        return DigestUtils.md5Hex(md5);
    }
    
}
