package com.ourdax.coindocker.common.clients.achain.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Created by zhangjinyang on 2018/1/31.
 */
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@lombok.Data
@ToString
public class ActInportAsset {

  private String amount;

  @JsonProperty("asset_id")
  private String assetId;

  public String getAmount ()
  {
    return amount;
  }

}