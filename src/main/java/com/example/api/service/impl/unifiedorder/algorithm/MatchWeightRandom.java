package com.example.api.service.impl.unifiedorder.algorithm;

import com.example.api.common.tools.YvanUtil;
import com.example.api.entity.bo.MatchMemberBo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 加权随机对象获取算法
 */
public class MatchWeightRandom implements MatchAlgorithm {

  @Override
  public MatchMemberBo get(List<MatchMemberBo> filterMap) {
    // 总权重
    int countWeight = filterMap.parallelStream().map(MatchMemberBo::getW)
        .reduce(0, Integer::sum);

    System.out.println("MatchWeightRandom countWeight -->" + countWeight);
    // 获取总权值之间任意一随机数
    int random = new Random().nextInt(countWeight);  //random in [0, weightSum)
    System.out.println("MatchWeightRandom random -->" + random);

    // {.},{..},{...},{....}...根据权值概率区间，获得加权随机对象
    int listsize = filterMap.size();
    for (int i = 0; i < listsize; i++) {
      MatchMemberBo curr = filterMap.get(i);
      random -= curr.getW();

      if (random <= 0) {
        return filterMap.get(i);
      }
    }

    return null;
  }

  public static void main(String[] args) {
    List<MatchMemberBo> openPayBoList = new ArrayList<>();

    MatchMemberBo o1 = new MatchMemberBo();
    o1.setAccount("1");
    o1.setW(10);
    openPayBoList.add(o1);

    MatchMemberBo o2 = new MatchMemberBo();
    o2.setAccount("1");
    o2.setW(5);
    openPayBoList.add(o2);

    MatchAlgorithm matchAlgorithm = new MatchWeightRandom();

    MatchMemberBo match = matchAlgorithm.get(openPayBoList);

    System.out.println(YvanUtil.toJson(match));

  }

}
