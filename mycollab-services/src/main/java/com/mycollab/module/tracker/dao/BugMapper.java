package com.mycollab.module.tracker.dao;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.module.tracker.domain.BugExample;
import com.mycollab.module.tracker.domain.BugWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

@SuppressWarnings({ "ucd", "rawtypes" })
public interface BugMapper extends ICrudGenericDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbg.generated Fri Dec 21 03:28:35 CST 2018
     */
    long countByExample(BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbg.generated Fri Dec 21 03:28:35 CST 2018
     */
    int deleteByExample(BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbg.generated Fri Dec 21 03:28:35 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbg.generated Fri Dec 21 03:28:35 CST 2018
     */
    int insert(BugWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbg.generated Fri Dec 21 03:28:35 CST 2018
     */
    int insertSelective(BugWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbg.generated Fri Dec 21 03:28:35 CST 2018
     */
    List<BugWithBLOBs> selectByExampleWithBLOBs(BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbg.generated Fri Dec 21 03:28:35 CST 2018
     */
    BugWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbg.generated Fri Dec 21 03:28:35 CST 2018
     */
    int updateByExampleSelective(@Param("record") BugWithBLOBs record, @Param("example") BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbg.generated Fri Dec 21 03:28:35 CST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") BugWithBLOBs record, @Param("example") BugExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbg.generated Fri Dec 21 03:28:35 CST 2018
     */
    int updateByPrimaryKeySelective(BugWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbg.generated Fri Dec 21 03:28:35 CST 2018
     */
    int updateByPrimaryKeyWithBLOBs(BugWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbg.generated Fri Dec 21 03:28:35 CST 2018
     */
    Integer insertAndReturnKey(BugWithBLOBs value);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbg.generated Fri Dec 21 03:28:35 CST 2018
     */
    void removeKeysWithSession(List primaryKeys);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_tracker_bug
     *
     * @mbg.generated Fri Dec 21 03:28:35 CST 2018
     */
    void massUpdateWithSession(@Param("record") BugWithBLOBs record, @Param("primaryKeys") List primaryKeys);
}