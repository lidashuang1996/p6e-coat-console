package club.p6e.coat.console.infrastructure.context;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lidashuang
 * @version 1.0
 */
public class MeContext implements Serializable {

    public static class Info implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class Vo implements Serializable {
            private Integer id;
            private Integer status;
            private Integer enabled;
            private Integer internal;
            private Integer administrator;
            private String account;
            private String phone;
            private String mailbox;
            private String name;
            private String nickname;
            private String avatar;
            private String description;
        }

        @Data
        @Accessors(chain = true)
        public static class Dto implements Serializable {
            private Integer id;
            private Integer status;
            private Integer enabled;
            private Integer internal;
            private Integer administrator;
            private String account;
            private String phone;
            private String mailbox;
            private String name;
            private String nickname;
            private String avatar;
            private String description;
        }

    }


    public static class ChangePassword implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class Request implements Serializable {
            private String oldPassword;
            private String newPassword;
        }

        @Data
        @Accessors(chain = true)
        public static class Vo implements Serializable {
            private Integer id;
            private Integer status;
            private Integer enabled;
            private Integer internal;
            private Integer administrator;
            private String account;
            private String phone;
            private String mailbox;
            private String name;
            private String nickname;
            private String avatar;
            private String description;
            private LocalDateTime createDate;
            private LocalDateTime updateDate;
            private String operator;
        }

        @Data
        @Accessors(chain = true)
        public static class Dto implements Serializable {
            private Integer id;
            private Integer status;
            private Integer enabled;
            private Integer internal;
            private Integer administrator;
            private String account;
            private String phone;
            private String mailbox;
            private String name;
            private String nickname;
            private String avatar;
            private String description;
            private LocalDateTime createDate;
            private LocalDateTime updateDate;
            private String operator;
        }

    }
}
