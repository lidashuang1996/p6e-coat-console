package club.p6e.coat.console.controller.su;

import club.p6e.coat.common.controller.filter.CrossDomainWebFilter;
import jakarta.servlet.annotation.WebFilter;


/**
 * @author lidashuang
 * @version 1.0
 */
@WebFilter(filterName = "CrFilter", urlPatterns = {"*"})
public class CrFilter extends CrossDomainWebFilter {
}
