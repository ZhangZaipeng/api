package com.example.api.service.impl.unifiedorder.algorithm;

import com.example.api.entity.bo.MatchMemberBo;
import java.util.List;

public interface MatchAlgorithm {

  MatchMemberBo get(List<MatchMemberBo> filterMap);

}
