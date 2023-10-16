package club.p6e.coat.console;

/**
 * 认证密码加密
 *
 * @author lidashuang
 * @version 1.0
 */
public interface PasswordEncryptor {

    /**
     * 执行密码加密
     *
     * @param content 密码
     * @return 密码加密后的内容
     */
    public String execute(String content);

}
