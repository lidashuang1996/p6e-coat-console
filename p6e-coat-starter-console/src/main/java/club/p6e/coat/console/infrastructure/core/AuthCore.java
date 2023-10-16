package club.p6e.coat.console.infrastructure.core;

import club.p6e.coat.common.DownstreamProgramCore;
import club.p6e.coat.common.controller.BaseWebController;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public final class AuthCore {

    @Getter
    public static class Model implements Serializable {
        private Integer id;
        private String name;
        private String avatar;
        private String introduction;
    }


    public static void init() {
        DownstreamProgramCore.init = o -> {
            final String token = BaseWebController.getAccessToken();
            final Model model = new Model();
            model.id = 1;
            model.name = "Super Admin";
            model.avatar = "avatar";
            model.introduction = "introduction";
            return new HashMap<>() {{
                put("auth", model);
            }};
        };
    }

    public static Model getData() {
        final Map<String, Object> data = DownstreamProgramCore.getData(null);
        final Object auth = data.get("auth");
        return auth == null ? null : (Model) auth;
    }

}
