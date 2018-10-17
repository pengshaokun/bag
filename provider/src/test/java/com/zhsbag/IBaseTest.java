package com.zhsbag;

import com.zhskg.bag.server.ProviderApplication;
import com.zhskg.bag.param.JwtParam;
import com.zhskg.bag.server.service.impl.JwtTokenServiceImpl;
import io.jsonwebtoken.Claims;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;

/**
 * @author jean
 * @date 2018/10/6
 * desc:
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProviderApplication.class})
@WebAppConfiguration
public class IBaseTest {

}
