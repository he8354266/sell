package com.imooc.dataobject;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@ToString
@Getter
@Setter
public class SellerInfo {
   @Id
    private String sellerId;



    private String username;

    private String password;

    private String openid;


}
