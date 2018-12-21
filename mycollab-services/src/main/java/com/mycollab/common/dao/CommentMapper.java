package com.mycollab.common.dao;

import com.mycollab.common.domain.CommentExample;
import com.mycollab.common.domain.CommentWithBLOBs;
import com.mycollab.db.persistence.ICrudGenericDAO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface CommentMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    long countByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int deleteByExample(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int insert(CommentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int insertSelective(CommentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    List<CommentWithBLOBs> selectByExampleWithBLOBs(CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    CommentWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByExampleSelective(@Param("record") CommentWithBLOBs record, @Param("example") CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") CommentWithBLOBs record, @Param("example") CommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByPrimaryKeySelective(CommentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    int updateByPrimaryKeyWithBLOBs(CommentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    Integer insertAndReturnKey(CommentWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_comment
     *
     * @mbg.generated Fri Dec 21 03:28:34 CST 2018
     */
    void massUpdateWithSession(@Param("record") CommentWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}