package com.mugishap.springboot.v1.audits;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties(value = {"createdBy", "updatedBy"}, allowGetters = true)
public abstract class InitiatorAudit extends TimestampAudit{
    private static final Long serialVersionUID = 1L;

    @CreatedBy
    @Column(name = "created_by")
    private Long createdBy;


    @LastModifiedBy
    @Column(name = "updated_by")
    private Long updatedBy;
}
