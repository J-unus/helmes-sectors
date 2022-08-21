package sector.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectorClassification extends BaseDomain {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    SectorClassification parent;

    @Column(nullable = false)
    String name;
}
