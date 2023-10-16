package club.p6e.coat.console;

import club.p6e.coat.common.utils.JsonUtil;
import com.darvi.hksi.badminton.lib.AuthCore;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ServletComponentScan
@SpringBootApplication
public class P6eCloudIndexApplication {

    public static void main(String[] args) {
        AuthCore.setDebug(true);
        SpringApplication.run(P6eCloudIndexApplication.class, args);
    }

}



    /**
     * 统一携带用户信息头
     */
    private static final String USER_HEADER = "SS-User-Info";

    /**
     * 通过线程获取认证的实例对象
     * 通过线程获取当前线程的请求对象，从而从头获取用户的认证信息头，进而读取用户的认证数据
     *
     * @return 认证的实例对象
     */
    public static AuthCore getThreadInstance() {
        if (IS_DEBUG) {
            return AC;
        } else {
            try {
                final ServletRequestAttributes servletRequestAttributes =
                        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (servletRequestAttributes != null) {
                    final HttpServletRequest request = servletRequestAttributes.getRequest();
                    final String content = request.getHeader(USER_HEADER);
                    if (content != null) {
                        return JsonUtil.fromJson(content, AuthCore.class);
                    }
                }
                throw new AuthException(AuthCore.class, "fun getThreadInstance().");
            } catch (Exception e) {
                throw new AuthException(AuthCore.class, "fun getThreadInstance().");
            }
        }
    }