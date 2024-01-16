package org.fuyi.weather.infra.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.fuyi.weather.infra.common.api.ResultCode;
import org.fuyi.weather.infra.common.exception.PermissionDeniedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Jwt工具类
 *
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/5 下午5:27
 * @since: 1.0
 */
public class JwtHelper {

    private static final Logger logger = LoggerFactory.getLogger(JwtHelper.class);

    private static final String SECRET = "_micro.weather_secret=+";

    private static final Long EXPIRED_TIME = 7 * 24 * 60 * 60 * 1000L;

    public static final String CLAIM_USER_ID = "subject_id";

    public static final String CLAIM_USER_NAME = "subject_name";

    public static final String CLAIM_SUPPORT = "support";

    private static Map<String, JWTVerifier> verifierMap = new HashMap<>();
    private static Map<String, Algorithm> algorithmMap = new HashMap<>();

    private static Algorithm getAlgorithm(String signingToken) {
        Algorithm algorithm = algorithmMap.get(signingToken);
        if (algorithm == null) {
            synchronized (algorithmMap) {
                algorithm = algorithmMap.get(signingToken);
                if (algorithm == null) {
                    algorithm = Algorithm.HMAC512(signingToken);
                    algorithmMap.put(signingToken, algorithm);
                }
            }
        }
        return algorithm;
    }

    public static String generateToken(Long userId, Long duration) {
        return generateToken(userId, null, true, duration);
    }

    /**
     * 生成jwt签名
     * @author zhoujian
     * @date 17:18 2020-06-15
     * @param subjectId 账户id
     * @param subjectName 账户名称
     * @param support 当前账户是否可用
     * @param duration 过期时间 毫秒数
     * @return java.lang.String
     **/
    private static String generateToken(Long subjectId, String subjectName, boolean support, Long duration){
        if (Objects.isNull(duration)){
            duration = EXPIRED_TIME;
        }
        Algorithm algorithm = getAlgorithm(SECRET);
        String token = JWT.create()
                .withClaim(CLAIM_USER_ID, subjectId)
                .withClaim(CLAIM_USER_NAME, subjectName)
                .withClaim(CLAIM_SUPPORT, support)
                .withExpiresAt(new Date(System.currentTimeMillis() + duration))
                .sign(algorithm);
        return token;
    }

    /**
     * token 校验
     * @author zhoujian
     * @date 17:23 2020-06-15
     * @param token 生成的jwt签名
     * @return com.auth0.jwt.interfaces.DecodedJWT
     **/
    public static DecodedJWT verifyToken(String token){
        JWTVerifier verifier = verifierMap.get(SECRET);
        if (verifier == null) {
            synchronized (verifierMap) {
                verifier = verifierMap.get(SECRET);
                if (verifier == null) {
                    Algorithm algorithm = Algorithm.HMAC512(SECRET);
                    verifier = JWT.require(algorithm).build();
                    verifierMap.put(SECRET, verifier);
                }
            }
        }
        try {
            return verifier.verify(token);
        }catch (JWTVerificationException exception){
            logger.warn("Invalid token: " + exception.getMessage());
            throw new PermissionDeniedException(ResultCode.INVALID_TOKEN);
        }
    }

    /**
     * 校验并获取用户Id
     * @author zhoujian
     * @date 19:10 2020-06-17
     * @param token
     * @return java.lang.Long
     **/
    public static Long decodeForSubjectId(String token){
        return verifyToken(token).getClaim(CLAIM_USER_ID).asLong();
    }

    public static void main(String[] args) {
        System.out.println(generateToken(1L, null, true, 10000L));
        DecodedJWT decodedJWT = verifyToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWJqZWN0X2lkIjoxLCJleHAiOjE3MDQ5NjQxMTgsInN1cHBvcnQiOnRydWV9.Swu6U6_2RcZKHi5hBv0NAPMR37oCtfQqigKEJ6qJojzeA8a5m4Es4NsUt4EjjICc1b8jg8wGZMEnbj3_qxqT9w");
        System.out.println(decodedJWT);
//        System.out.println(decodeForSubjectId("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2lkIjoxLCJleHAiOjE2NDQ2NTc2ODgsInN1cHBvcnQiOnRydWUsInVzZXJuYW1lIjoiIn0.GCjYi3m0Nr8g3iKwqekIL16Cu36SXBc3-vduJGeTBAzDMEJwM3U_0YN7oAygkh0q2LLAhx3921LyanPsBI_9qA"));
    }
}
