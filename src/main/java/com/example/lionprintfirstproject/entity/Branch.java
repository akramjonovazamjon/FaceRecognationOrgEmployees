package com.example.lionprintfirstproject.entity;

import com.example.lionprintfirstproject.dto.branch.BranchUpdate;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String info;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Organization organization;

    public void update(BranchUpdate dto, Organization organization) {
        setName(dto.name());
        setInfo(dto.info());
        setOrganization(organization);
    }
}
