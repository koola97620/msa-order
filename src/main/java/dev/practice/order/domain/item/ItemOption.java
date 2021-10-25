package dev.practice.order.domain.item;

import dev.practice.order.common.exception.InvalidParamException;
import dev.practice.order.domain.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "item_options")
public class ItemOption extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_option_groups_id")
    private ItemOptionGroup itemOptionGroup;

    private Integer ordering;
    private String itemOptionName;
    private Long itemOptionPrice;

    @Builder
    public ItemOption(ItemOptionGroup itemOptionGroup, Integer ordering, String itemOptionName, Long itemOptionPrice) {
        if (itemOptionGroup == null) throw new InvalidParamException("");;
        if (ordering == null) throw new InvalidParamException("");
        if (StringUtils.isBlank(itemOptionName)) throw new InvalidParamException("");
        if (itemOptionPrice == null) throw new InvalidParamException("");

        this.itemOptionGroup = itemOptionGroup;
        this.ordering = ordering;
        this.itemOptionName = itemOptionName;
        this.itemOptionPrice = itemOptionPrice;
    }
}
