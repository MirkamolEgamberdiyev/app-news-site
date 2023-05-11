package uz.fido.appnewssite.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.fido.appnewssite.entity.enums.Permission;
import uz.fido.appnewssite.entity.template.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role extends AbstractEntity {
    @Column(unique = true, nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Permission> permissionList;

    @Column(length = 600)
    private String description;

//    @Enumerated(value = EnumType.STRING)
//    private RoleType roleType;
}
