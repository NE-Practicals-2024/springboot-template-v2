package com.mugishap.springboot.v1.models;


import com.mugishap.springboot.v1.audits.InitiatorAudit;
import com.mugishap.springboot.v1.enums.EFileSizeType;
import com.mugishap.springboot.v1.enums.EFileStatus;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files", uniqueConstraints = {@UniqueConstraint(columnNames = "path")})
public class File extends InitiatorAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="path")
    private String path;

    @Transient
    private String url;

    @Column(name="size")
    private int size;

    @Column(name="size_type")
    @Enumerated(EnumType.STRING)
    private EFileSizeType sizeType;

    @Column(name="type")
    private String type;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private EFileStatus status;

    public File(String directory, String fileName, String extension, String fileBaseName) {
        super();
    }


    public String getUrl() {
        return "http://localhost:4008/api/v1/users/load-file" + "/" + this.getName();
    }
}

