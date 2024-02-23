package club.p6e.coat.console.domain.aggregate;

import club.p6e.coat.common.search.SearchableContext;
import club.p6e.coat.common.sortable.SortableContext;
import club.p6e.coat.common.utils.SpringUtil;
import club.p6e.coat.console.domain.ConfigurationDomain;
import club.p6e.coat.console.infrastructure.converter.SearchableConverter;
import club.p6e.coat.console.infrastructure.converter.SortableConverter;
import club.p6e.coat.console.infrastructure.model.Oauth2ClientModel;
import club.p6e.coat.console.infrastructure.repository.Oauth2ClientRepository;
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
public class Oauth2ClientListAggregate extends ConfigurationDomain {

    private final int page;
    private final int size;
    private final long total;
    private final List<Oauth2ClientModel> list;

    public static Oauth2ClientListAggregate search(
            Integer page, Integer size, String query,
            SortableContext<SortableContext.Option> sort,
            SearchableContext<SearchableContext.Option> search) {
        page = initPage(page);
        size = initSize(size);
        final Oauth2ClientRepository repository = SpringUtil.getBean(Oauth2ClientRepository.class);
        final Page<Oauth2ClientModel> pcm = repository.findAll((Specification<Oauth2ClientModel>) (rt, qy, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (query != null) {
                final String lq = "%" + query + "%";
                predicates.add(cb.or(
                        cb.like(cb.function(
                                "text",
                                String.class,
                                rt.get(Oauth2ClientModel.CLIENT_ID)
                        ), lq),
                        cb.like(cb.lower(rt.get(Oauth2ClientModel.CLIENT_ID)), lq),
                        cb.like(cb.lower(rt.get(Oauth2ClientModel.CLIENT_NAME)), lq),
                        cb.like(cb.lower(rt.get(Oauth2ClientModel.CLIENT_DESCRIPTION)), lq)
                ));
            }
            final Predicate sp = SearchableConverter.to(search, rt, cb);
            if (sp != null) {
                predicates.add(sp);
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        }, PageRequest.of(page - 1, size, SortableConverter.to(sort, Sort.by(Sort.Order.asc(Oauth2ClientModel.ID)))));
        return new Oauth2ClientListAggregate(page, size, pcm.getTotalElements(), new ArrayList<>(pcm.getContent()));
    }

    private Oauth2ClientListAggregate(int page, int size, long total, List<Oauth2ClientModel> list) {
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

    public List<Oauth2ClientModel> getList() {
        return list;
    }

}
