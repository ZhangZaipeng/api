package com.example.api.mapper;

import com.example.api.config.mybatis.DefaultMapper;
import com.example.api.entity.MemberReceivables;
import com.example.api.entity.bo.MatchMemberBo;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberReceivablesMapper extends DefaultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberReceivables record);

    int insertSelective(MemberReceivables record);

    MemberReceivables selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MemberReceivables record);

    int updateByPrimaryKey(MemberReceivables record);

    List<MatchMemberBo> selectMatchMemberList(@Param("payType") String payType, @Param("unitAmt") BigDecimal unitAmt);
}
