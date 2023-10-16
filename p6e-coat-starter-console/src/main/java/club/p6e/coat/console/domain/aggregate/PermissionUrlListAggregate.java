package club.p6e.coat.console.domain.aggregate;

import club.p6e.coat.console.infrastructure.converter.SearchableConverter;
import club.p6e.coat.console.infrastructure.converter.SortableConverter;
import club.p6e.coat.console.domain.ConfigurationDomain;
import club.p6e.coat.console.infrastructure.model.PermissionUrlModel;
import club.p6e.coat.console.infrastructure.repository.PermissionUrlRepository;
import com.darvi.hksi.badminton.lib.SearchableContext;
import com.darvi.hksi.badminton.lib.SortableContext;
import com.darvi.hksi.badminton.lib.utils.SpringUtil;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public class PermissionUrlListAggregate extends ConfigurationDomain {

    private final int page;
    private final int size;
    private final long total;
    private final List<PermissionUrlModel> list;

    public static PermissionUrlListAggregate search(
            Integer page, Integer size, String query,
            SortableContext<SortableContext.Option> sort,
            SearchableContext<SearchableContext.Option> search) {
        page = initPage(page);
        size = initSize(size);
        final PermissionUrlRepository repository = SpringUtil.getBean(PermissionUrlRepository.class);
        final Page<PermissionUrlModel> pcm = repository.findAll((Specification<PermissionUrlModel>) (rt, qy, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (query != null) {
                final String lq = "%" + query + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(rt.get(PermissionUrlModel.NAME)), lq),
                        cb.like(cb.lower(rt.get(PermissionUrlModel.DESCRIPTION)), lq),
                        cb.like(cb.lower(rt.get(PermissionUrlModel.METHOD)), lq),
                        cb.like(cb.lower(
                                cb.concat(
                                        rt.get(PermissionUrlModel.BASE_URL),
                                        rt.get(PermissionUrlModel.URL)
                                )
                        ), lq)
                ));
            }
            final Predicate sp = SearchableConverter.to(search, rt, cb);
            if (sp != null) {
                predicates.add(sp);
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        }, PageRequest.of(page - 1, size, SortableConverter.to(sort, Sort.by(Sort.Order.asc(PermissionUrlModel.ID)))));
        return new PermissionUrlListAggregate(page, size, pcm.getTotalElements(), new ArrayList<>(pcm.getContent()));
    }

    private PermissionUrlListAggregate(int page, int size, long total, List<PermissionUrlModel> list) {
        this.list = list;
        this.page = page;
        this.size = size;
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotal() {
        return total;
    }

    public List<PermissionUrlModel> getList() {
        return list;
    }

}
